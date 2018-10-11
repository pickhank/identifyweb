var SWFUpload,swfuploads=0,swfuVal=[];

function $$(id){return document.getElementById(id)}

if (SWFUpload == undefined) {
	SWFUpload = function (settings) {
		this.initSWFUpload(settings);
	};
}

SWFUpload.prototype.initSWFUpload = function (settings) {
	try {
		this.customSettings = {};	// A container where developers can place their own settings associated with this instance.
		this.settings = settings;
		this.eventQueue = [];
		this.movieName = 'SWFUpload_' + SWFUpload.movieCount++;
		this.movieElement = null;


		// Setup global control tracking
		SWFUpload.instances[this.movieName] = this;

		// Load the settings.  Load the Flash movie.
		this.initSettings();
		this.loadFlash();
		this.displayDebugInfo();
	} catch (ex) {
		delete SWFUpload.instances[this.movieName];
		throw ex;
	}
};

/* *************** */
/* Static Members  */
/* *************** */
SWFUpload.instances = {};
SWFUpload.movieCount = 0;
SWFUpload.version = '2.2.0 2009-03-25';
SWFUpload.QUEUE_ERROR = {
	QUEUE_LIMIT_EXCEEDED	  		: -100,
	FILE_EXCEEDS_SIZE_LIMIT  		: -110,
	ZERO_BYTE_FILE			  		: -120,
	INVALID_FILETYPE		  		: -130
};
SWFUpload.UPLOAD_ERROR = {
	HTTP_ERROR				  		: -200,
	MISSING_UPLOAD_URL	      		: -210,
	IO_ERROR				  		: -220,
	SECURITY_ERROR			  		: -230,
	UPLOAD_LIMIT_EXCEEDED	  		: -240,
	UPLOAD_FAILED			  		: -250,
	SPECIFIED_FILE_ID_NOT_FOUND		: -260,
	FILE_VALIDATION_FAILED	  		: -270,
	FILE_CANCELLED			  		: -280,
	UPLOAD_STOPPED					: -290
};
SWFUpload.FILE_STATUS = {
	QUEUED		 : -1,
	IN_PROGRESS	 : -2,
	ERROR		 : -3,
	COMPLETE	 : -4,
	CANCELLED	 : -5
};
SWFUpload.BUTTON_ACTION = {
	SELECT_FILE  : -100,
	SELECT_FILES : -110,
	START_UPLOAD : -120
};
SWFUpload.CURSOR = {
	ARROW : -1,
	HAND : -2
};
SWFUpload.WINDOW_MODE = {
	WINDOW : 'window',
	TRANSPARENT : 'transparent',
	OPAQUE : 'opaque'
};

// Private: takes a URL, determines if it is relative and converts to an absolute URL
// using the current site. Only processes the URL if it can, otherwise returns the URL untouched
SWFUpload.completeURL = function(url) {
	if (typeof(url) !== 'string' || url.match(/^https?:\/\//i) || url.match(/^\//)) {
		return url;
	}
	
	var currentURL = window.location.protocol + '//' + window.location.hostname + (window.location.port ? ':' + window.location.port : '');
	
	var indexSlash = window.location.pathname.lastIndexOf('/');
	if (indexSlash <= 0) {
		path = '/';
	} else {
		path = window.location.pathname.substr(0, indexSlash) + '/';
	}
	
	return /*currentURL +*/ path + url;
	
};


/* ******************** */
/* Instance Members  */
/* ******************** */

// Private: initSettings ensures that all the
// settings are set, getting a default value if one was not assigned.
SWFUpload.prototype.initSettings = function () {
	this.ensureDefault = function (settingName, defaultValue) {
		this.settings[settingName] = (this.settings[settingName] == undefined) ? defaultValue : this.settings[settingName];
	};
	
	// Upload backend settings
	this.ensureDefault('upload_url', '');
	this.ensureDefault('preserve_relative_urls', false);
	this.ensureDefault('file_post_name', 'Filedata');
	this.ensureDefault('post_params', {});
	this.ensureDefault('use_query_string', false);
	this.ensureDefault('requeue_on_error', false);
	this.ensureDefault('http_success', []);
	this.ensureDefault('assume_success_timeout', 0);
	
	// File Settings
	this.ensureDefault('file_types', '*.*');
	this.ensureDefault('file_types_description', 'All Files');
	this.ensureDefault('file_size_limit', 0);	// Default zero means 'unlimited'
	this.ensureDefault('file_upload_limit', 0);
	this.ensureDefault('file_queue_limit', 0);

	// Flash Settings
	this.ensureDefault('flash_url', 'swfupload.swf');
	this.ensureDefault('prevent_swf_caching', true);
	
	// Button Settings
	this.ensureDefault('button_image_url', '');
	this.ensureDefault('button_width', 1);
	this.ensureDefault('button_height', 1);
	this.ensureDefault('button_text', '');
	this.ensureDefault('button_text_style', 'color: #000000; font-size: 16pt;');
	this.ensureDefault('button_text_top_padding', 0);
	this.ensureDefault('button_text_left_padding', 0);
	this.ensureDefault('button_action', SWFUpload.BUTTON_ACTION.SELECT_FILES);
	this.ensureDefault('button_disabled', false);
	this.ensureDefault('button_placeholder_id', '');
	this.ensureDefault('button_placeholder', null);
	this.ensureDefault('button_cursor', SWFUpload.CURSOR.ARROW);
	this.ensureDefault('button_window_mode', SWFUpload.WINDOW_MODE.WINDOW);
	
	// Debug Settings
	this.ensureDefault('debug', false);
	this.settings.debug_enabled = this.settings.debug;	// Here to maintain v2 API
	
	// Event Handlers
	this.settings.return_upload_start_handler = this.returnUploadStart;
	this.ensureDefault('swfupload_loaded_handler', null);
	this.ensureDefault('file_dialog_start_handler', null);
	this.ensureDefault('file_queued_handler', null);
	this.ensureDefault('file_queue_error_handler', null);
	this.ensureDefault('file_dialog_complete_handler', null);
	
	this.ensureDefault('upload_start_handler', null);
	this.ensureDefault('upload_progress_handler', null);
	this.ensureDefault('upload_error_handler', null);
	this.ensureDefault('upload_success_handler', null);
	this.ensureDefault('upload_complete_handler', null);
	
	this.ensureDefault('debug_handler', this.debugMessage);

	this.ensureDefault('custom_settings', {});

	// Other settings
	this.customSettings = this.settings.custom_settings;
	
	// Update the flash url if needed
	if (!!this.settings.prevent_swf_caching) {
		this.settings.flash_url = this.settings.flash_url + (this.settings.flash_url.indexOf('?') < 0 ? '?' : '&') + 'preventswfcaching=' + new Date().getTime();
	}
	
	if (!this.settings.preserve_relative_urls) {
		//this.settings.flash_url = SWFUpload.completeURL(this.settings.flash_url);	// Don't need to do this one since flash doesn't look at it
		this.settings.upload_url = SWFUpload.completeURL(this.settings.upload_url);
		this.settings.button_image_url = SWFUpload.completeURL(this.settings.button_image_url);
	}
	
	delete this.ensureDefault;
};

// Private: loadFlash replaces the button_placeholder element with the flash movie.
SWFUpload.prototype.loadFlash = function () {
	var targetElement, tempParent, su=this;

	// Make sure an element with the ID we are going to use doesn't already exist
	if ($$(this.movieName) !== null) {
		throw 'ID ' + this.movieName + ' is already in use. The Flash Object could not be added';
	}

	// Get the element where we will be placing the flash movie
	targetElement = $$(this.settings.button_placeholder_id) || this.settings.button_placeholder;

	if (targetElement == undefined) {
		throw 'Could not find the placeholder element: ' + this.settings.button_placeholder_id;
	}

	// Append the container and load the flash
	tempParent = document.createElement('div');
	tempParent.innerHTML = this.getFlashHTML();	// Using innerHTML is non-standard but the only sensible way to dynamically add Flash in IE (and maybe other browsers)
	targetElement.parentNode.replaceChild(tempParent.firstChild, targetElement);

	// Fix IE Flash/Form bug
	if (window[this.movieName] == undefined) {
		window[this.movieName] = this.getMovieElement();
	}
	$$(this.customSettings.uploadButtonId).onclick = function(){
		if(su.getStats().files_queued > 0)$$(su.customSettings.cancelButtonId).disabled = false;
		this.disabled = true;
		su.startUpload();
	}

};

// Private: getFlashHTML generates the object tag needed to embed the flash in to the document
SWFUpload.prototype.getFlashHTML = function () {
	// Flash Satay object syntax: http://www.alistapart.com/articles/flashsatay
	return ['<object id="', this.movieName, '" type="application/x-shockwave-flash" data="', this.settings.flash_url, '" width="', this.settings.button_width, '" height="', this.settings.button_height, '" class="swfupload">',
				'<param name="wmode" value="', this.settings.button_window_mode, '" />',
				'<param name="movie" value="', this.settings.flash_url, '" />',
				'<param name="quality" value="high" />',
				'<param name="menu" value="false" />',
				'<param name="allowScriptAccess" value="always" />',
				'<param name="flashvars" value="' + this.getFlashVars() + '" />',
				'</object>'].join('');
};

// Private: getFlashVars builds the parameter string that will be passed
// to flash in the flashvars param.
SWFUpload.prototype.getFlashVars = function () {
	// Build a string from the post param object
	var paramString = this.buildParamString();
	var httpSuccessString = this.settings.http_success.join(',');

	// Build the parameter string
	return ['movieName=', encodeURIComponent(this.movieName),
			'&uploadURL=', encodeURIComponent(this.settings.upload_url),
			'&useQueryString=', encodeURIComponent(this.settings.use_query_string),
			'&requeueOnError=', encodeURIComponent(this.settings.requeue_on_error),
			'&httpSuccess=', encodeURIComponent(httpSuccessString),
			'&assumeSuccessTimeout=', encodeURIComponent(this.settings.assume_success_timeout),
			'&params=', encodeURIComponent(paramString),
			'&filePostName=', encodeURIComponent(this.settings.file_post_name),
			'&fileTypes=', encodeURIComponent(this.settings.file_types),
			'&fileTypesDescription=', encodeURIComponent(this.settings.file_types_description),
			'&fileSizeLimit=', encodeURIComponent(this.settings.file_size_limit),
			'&fileUploadLimit=', encodeURIComponent(this.settings.file_upload_limit),
			'&fileQueueLimit=', encodeURIComponent(this.settings.file_queue_limit),
			'&debugEnabled=', encodeURIComponent(this.settings.debug_enabled),
			'&buttonImageURL=', encodeURIComponent(this.settings.button_image_url),
			'&buttonWidth=', encodeURIComponent(this.settings.button_width),
			'&buttonHeight=', encodeURIComponent(this.settings.button_height),
			'&buttonText=', encodeURIComponent(this.settings.button_text),
			'&buttonTextTopPadding=', encodeURIComponent(this.settings.button_text_top_padding),
			'&buttonTextLeftPadding=', encodeURIComponent(this.settings.button_text_left_padding),
			'&buttonTextStyle=', encodeURIComponent(this.settings.button_text_style),
			'&buttonAction=', encodeURIComponent(this.settings.button_action),
			'&buttonDisabled=', encodeURIComponent(this.settings.button_disabled),
			'&buttonCursor=', encodeURIComponent(this.settings.button_cursor)
		].join('');
};

// Public: getMovieElement retrieves the DOM reference to the Flash element added by SWFUpload
// The element is cached after the first lookup
SWFUpload.prototype.getMovieElement = function () {
	if (this.movieElement == undefined) {
		this.movieElement = $$(this.movieName);
	}

	if (this.movieElement === null) {
		throw 'Could not find Flash element';
	}
	
	return this.movieElement;
};

// Private: buildParamString takes the name/value pairs in the post_params setting object
// and joins them up in to a string formatted 'name=value&name=value'
SWFUpload.prototype.buildParamString = function () {
	var postParams = this.settings.post_params; 
	var paramStringPairs = [];

	if (typeof(postParams) === 'object') {
		for (var name in postParams) {
			if (postParams.hasOwnProperty(name) && postParams[name]!==null) {
				paramStringPairs.push(encodeURIComponent(name.toString()) + '=' + encodeURIComponent(postParams[name].toString()));
			}
		}
	}

	return paramStringPairs.join('&amp;');
};

// Public: Used to remove a SWFUpload instance from the page. This method strives to remove
// all references to the SWF, and other objects so memory is properly freed.
// Returns true if everything was destroyed. Returns a false if a failure occurs leaving SWFUpload in an inconsistant state.
// Credits: Major improvements provided by steffen
SWFUpload.prototype.destroy = function () {
	try {
		// Make sure Flash is done before we try to remove it
		this.cancelUpload(null, false);
		

		// Remove the SWFUpload DOM nodes
		var movieElement = null;
		movieElement = this.getMovieElement();
		
		if (movieElement && typeof(movieElement.CallFunction) === 'unknown') { // We only want to do this in IE
			// Loop through all the movie's properties and remove all function references (DOM/JS IE 6/7 memory leak workaround)
			for (var i in movieElement) {
				try {
					if (typeof(movieElement[i]) === 'function') {
						movieElement[i] = null;
					}
				} catch (ex1) {}
			}

			// Remove the Movie Element from the page
			try {
				movieElement.parentNode.removeChild(movieElement);
			} catch (ex) {}
		}
		
		// Remove IE form fix reference
		window[this.movieName] = null;

		// Destroy other references
		SWFUpload.instances[this.movieName] = null;
		delete SWFUpload.instances[this.movieName];

		this.movieElement = null;
		this.settings = null;
		this.customSettings = null;
		this.eventQueue = null;
		this.movieName = null;
		
		
		return true;
	} catch (ex2) {
		return false;
	}
};


// Public: displayDebugInfo prints out settings and configuration
// information about this SWFUpload instance.
// This function (and any references to it) can be deleted when placing
// SWFUpload in production.
SWFUpload.prototype.displayDebugInfo = function () {
	this.debug(
		[
			"---SWFUpload Instance Info---\n",
			"Version: ", SWFUpload.version, "\n",
			"Movie Name: ", this.movieName, "\n",
			"Settings:\n",
			"\t", "upload_url:               ", this.settings.upload_url, "\n",
			"\t", "flash_url:                ", this.settings.flash_url, "\n",
			"\t", "use_query_string:         ", this.settings.use_query_string.toString(), "\n",
			"\t", "requeue_on_error:         ", this.settings.requeue_on_error.toString(), "\n",
			"\t", "http_success:             ", this.settings.http_success.join(", "), "\n",
			"\t", "assume_success_timeout:   ", this.settings.assume_success_timeout, "\n",
			"\t", "file_post_name:           ", this.settings.file_post_name, "\n",
			"\t", "post_params:              ", this.settings.post_params.toString(), "\n",
			"\t", "file_types:               ", this.settings.file_types, "\n",
			"\t", "file_types_description:   ", this.settings.file_types_description, "\n",
			"\t", "file_size_limit:          ", this.settings.file_size_limit, "\n",
			"\t", "file_upload_limit:        ", this.settings.file_upload_limit, "\n",
			"\t", "file_queue_limit:         ", this.settings.file_queue_limit, "\n",
			"\t", "debug:                    ", this.settings.debug.toString(), "\n",

			"\t", "prevent_swf_caching:      ", this.settings.prevent_swf_caching.toString(), "\n",

			"\t", "button_placeholder_id:    ", this.settings.button_placeholder_id.toString(), "\n",
			"\t", "button_placeholder:       ", (this.settings.button_placeholder ? "Set" : "Not Set"), "\n",
			"\t", "button_image_url:         ", this.settings.button_image_url.toString(), "\n",
			"\t", "button_width:             ", this.settings.button_width.toString(), "\n",
			"\t", "button_height:            ", this.settings.button_height.toString(), "\n",
			"\t", "button_text:              ", this.settings.button_text.toString(), "\n",
			"\t", "button_text_style:        ", this.settings.button_text_style.toString(), "\n",
			"\t", "button_text_top_padding:  ", this.settings.button_text_top_padding.toString(), "\n",
			"\t", "button_text_left_padding: ", this.settings.button_text_left_padding.toString(), "\n",
			"\t", "button_action:            ", this.settings.button_action.toString(), "\n",
			"\t", "button_disabled:          ", this.settings.button_disabled.toString(), "\n",

			"\t", "custom_settings:          ", this.settings.custom_settings.toString(), "\n",
			"Event Handlers:\n",
			"\t", "swfupload_loaded_handler assigned:  ", (typeof this.settings.swfupload_loaded_handler === "function").toString(), "\n",
			"\t", "file_dialog_start_handler assigned: ", (typeof this.settings.file_dialog_start_handler === "function").toString(), "\n",
			"\t", "file_queued_handler assigned:       ", (typeof this.settings.file_queued_handler === "function").toString(), "\n",
			"\t", "file_queue_error_handler assigned:  ", (typeof this.settings.file_queue_error_handler === "function").toString(), "\n",
			"\t", "upload_start_handler assigned:      ", (typeof this.settings.upload_start_handler === "function").toString(), "\n",
			"\t", "upload_progress_handler assigned:   ", (typeof this.settings.upload_progress_handler === "function").toString(), "\n",
			"\t", "upload_error_handler assigned:      ", (typeof this.settings.upload_error_handler === "function").toString(), "\n",
			"\t", "upload_success_handler assigned:    ", (typeof this.settings.upload_success_handler === "function").toString(), "\n",
			"\t", "upload_complete_handler assigned:   ", (typeof this.settings.upload_complete_handler === "function").toString(), "\n",
			"\t", "debug_handler assigned:             ", (typeof this.settings.debug_handler === "function").toString(), "\n"
		].join('')
	);
};

/* Note: addSetting and getSetting are no longer used by SWFUpload but are included
	the maintain v2 API compatibility
*/
// Public: (Deprecated) addSetting adds a setting value. If the value given is undefined or null then the default_value is used.
SWFUpload.prototype.addSetting = function (name, value, default_value) {
    if (value == undefined) {
        return (this.settings[name] = default_value);
    } else {
        return (this.settings[name] = value);
	}
};

// Public: (Deprecated) getSetting gets a setting. Returns an empty string if the setting was not found.
SWFUpload.prototype.getSetting = function (name) {
    if (this.settings[name] != undefined) {
        return this.settings[name];
	}

    return '';
};



// Private: callFlash handles function calls made to the Flash element.
// Calls are made with a setTimeout for some functions to work around
// bugs in the ExternalInterface library.
SWFUpload.prototype.callFlash = function (functionName, argumentArray) {
	argumentArray = argumentArray || [];
	
	var movieElement = this.getMovieElement();
	var returnValue, returnString;

	// Flash's method if calling ExternalInterface methods (code adapted from MooTools).
	try {
		returnString = movieElement.CallFunction('<invoke name="' + functionName + '" returntype="javascript">' + __flash__argumentsToXML(argumentArray, 0) + '</invoke>');
		returnValue = eval(returnString);
	} catch (ex) {
		throw 'Call to ' + functionName + ' failed';
	}
	
	// Unescape file post param values
	if (returnValue != undefined && typeof returnValue.post === 'object') {
		returnValue = this.unescapeFilePostParams(returnValue);
	}

	return returnValue;
};

/* *****************************
	-- Flash control methods --
	Your UI should use these
	to operate SWFUpload
   ***************************** */

// WARNING: this function does not work in Flash Player 10
// Public: selectFile causes a File Selection Dialog window to appear.  This
// dialog only allows 1 file to be selected.
SWFUpload.prototype.selectFile = function () {
	this.callFlash('SelectFile');
};

// WARNING: this function does not work in Flash Player 10
// Public: selectFiles causes a File Selection Dialog window to appear/ This
// dialog allows the user to select any number of files
// Flash Bug Warning: Flash limits the number of selectable files based on the combined length of the file names.
// If the selection name length is too long the dialog will fail in an unpredictable manner.  There is no work-around
// for this bug.
SWFUpload.prototype.selectFiles = function () {

	this.callFlash('SelectFiles');
};


// Public: startUpload starts uploading the first file in the queue unless
// the optional parameter 'fileID' specifies the ID 
SWFUpload.prototype.startUpload = function (fileID) {
	this.callFlash('StartUpload', [fileID]);
};

// Public: cancelUpload cancels any queued file.  The fileID parameter may be the file ID or index.
// If you do not specify a fileID the current uploading file or first file in the queue is cancelled.
// If you do not want the uploadError event to trigger you can specify false for the triggerErrorEvent parameter.
SWFUpload.prototype.cancelUpload = function (fileID, triggerErrorEvent) {
	if (triggerErrorEvent !== false) {
		triggerErrorEvent = true;
	}
	canuploads++;
	this.callFlash('CancelUpload', [fileID, triggerErrorEvent]);
};

// Public: stopUpload stops the current upload and requeues the file at the beginning of the queue.
// If nothing is currently uploading then nothing happens.
SWFUpload.prototype.stopUpload = function () {
	this.callFlash('StopUpload');
};

/* ************************
 * Settings methods
 *   These methods change the SWFUpload settings.
 *   SWFUpload settings should not be changed directly on the settings object
 *   since many of the settings need to be passed to Flash in order to take
 *   effect.
 * *********************** */

// Public: getStats gets the file statistics object.
SWFUpload.prototype.getStats = function () {
	return this.callFlash('GetStats');
};

// Public: setStats changes the SWFUpload statistics.  You shouldn't need to 
// change the statistics but you can.  Changing the statistics does not
// affect SWFUpload accept for the successful_uploads count which is used
// by the upload_limit setting to determine how many files the user may upload.
SWFUpload.prototype.setStats = function (statsObject) {
	this.callFlash('SetStats', [statsObject]);
};

// Public: getFile retrieves a File object by ID or Index.  If the file is
// not found then 'null' is returned.
SWFUpload.prototype.getFile = function (fileID) {
	if (typeof(fileID) === 'number') {
		return this.callFlash('GetFileByIndex', [fileID]);
	} else {
		return this.callFlash('GetFile', [fileID]);
	}
};

// Public: addFileParam sets a name/value pair that will be posted with the
// file specified by the Files ID.  If the name already exists then the
// exiting value will be overwritten.
SWFUpload.prototype.addFileParam = function (fileID, name, value) {
	return this.callFlash('AddFileParam', [fileID, name, value]);
};

// Public: removeFileParam removes a previously set (by addFileParam) name/value
// pair from the specified file.
SWFUpload.prototype.removeFileParam = function (fileID, name) {
	this.callFlash('RemoveFileParam', [fileID, name]);
};

// Public: setUploadUrl changes the upload_url setting.
SWFUpload.prototype.setUploadURL = function (url) {
	this.settings.upload_url = url.toString();
	this.callFlash('SetUploadURL', [url]);
};

// Public: setPostParams changes the post_params setting
SWFUpload.prototype.setPostParams = function (paramsObject) {
	this.settings.post_params = paramsObject;
	this.callFlash('SetPostParams', [paramsObject]);
};

// Public: addPostParam adds post name/value pair.  Each name can have only one value.
SWFUpload.prototype.addPostParam = function (name, value) {
	this.settings.post_params[name] = value;
	this.callFlash('SetPostParams', [this.settings.post_params]);
};

// Public: removePostParam deletes post name/value pair.
SWFUpload.prototype.removePostParam = function (name) {
	delete this.settings.post_params[name];
	this.callFlash('SetPostParams', [this.settings.post_params]);
};

// Public: setFileTypes changes the file_types setting and the file_types_description setting
SWFUpload.prototype.setFileTypes = function (types, description) {
	this.settings.file_types = types;
	this.settings.file_types_description = description;
	this.callFlash('SetFileTypes', [types, description]);
};

// Public: setFileSizeLimit changes the file_size_limit setting
SWFUpload.prototype.setFileSizeLimit = function (fileSizeLimit) {
	this.settings.file_size_limit = fileSizeLimit;
	this.callFlash('SetFileSizeLimit', [fileSizeLimit]);
};

// Public: setFileUploadLimit changes the file_upload_limit setting
SWFUpload.prototype.setFileUploadLimit = function (fileUploadLimit) {
	this.settings.file_upload_limit = fileUploadLimit;
	this.callFlash('SetFileUploadLimit', [fileUploadLimit]);
};

// Public: setFileQueueLimit changes the file_queue_limit setting
SWFUpload.prototype.setFileQueueLimit = function (fileQueueLimit) {
	this.settings.file_queue_limit = fileQueueLimit;
	this.callFlash('SetFileQueueLimit', [fileQueueLimit]);
};

// Public: setFilePostName changes the file_post_name setting
SWFUpload.prototype.setFilePostName = function (filePostName) {
	this.settings.file_post_name = filePostName;
	this.callFlash('SetFilePostName', [filePostName]);
};

// Public: setUseQueryString changes the use_query_string setting
SWFUpload.prototype.setUseQueryString = function (useQueryString) {
	this.settings.use_query_string = useQueryString;
	this.callFlash('SetUseQueryString', [useQueryString]);
};

// Public: setRequeueOnError changes the requeue_on_error setting
SWFUpload.prototype.setRequeueOnError = function (requeueOnError) {
	this.settings.requeue_on_error = requeueOnError;
	this.callFlash('SetRequeueOnError', [requeueOnError]);
};

// Public: setHTTPSuccess changes the http_success setting
SWFUpload.prototype.setHTTPSuccess = function (http_status_codes) {
	if (typeof http_status_codes === 'string') {
		http_status_codes = http_status_codes.replace(' ', '').split(',');
	}
	
	this.settings.http_success = http_status_codes;
	this.callFlash('SetHTTPSuccess', [http_status_codes]);
};

// Public: setHTTPSuccess changes the http_success setting
SWFUpload.prototype.setAssumeSuccessTimeout = function (timeout_seconds) {
	this.settings.assume_success_timeout = timeout_seconds;
	this.callFlash('SetAssumeSuccessTimeout', [timeout_seconds]);
};

// Public: setDebugEnabled changes the debug_enabled setting
SWFUpload.prototype.setDebugEnabled = function (debugEnabled) {
	this.settings.debug_enabled = debugEnabled;
	this.callFlash('SetDebugEnabled', [debugEnabled]);
};

// Public: setButtonImageURL loads a button image sprite
SWFUpload.prototype.setButtonImageURL = function (buttonImageURL) {
	if (buttonImageURL == undefined) {
		buttonImageURL = '';
	}
	
	this.settings.button_image_url = buttonImageURL;
	this.callFlash('SetButtonImageURL', [buttonImageURL]);
};

// Public: setButtonDimensions resizes the Flash Movie and button
SWFUpload.prototype.setButtonDimensions = function (width, height) {
	this.settings.button_width = width;
	this.settings.button_height = height;
	
	var movie = this.getMovieElement();
	if (movie != undefined) {
		movie.style.width = width + 'px';
		movie.style.height = height + 'px';
	}
	
	this.callFlash('SetButtonDimensions', [width, height]);
};
// Public: setButtonText Changes the text overlaid on the button
SWFUpload.prototype.setButtonText = function (html) {
	this.settings.button_text = html;
	this.callFlash('SetButtonText', [html]);
};
// Public: setButtonTextPadding changes the top and left padding of the text overlay
SWFUpload.prototype.setButtonTextPadding = function (left, top) {
	this.settings.button_text_top_padding = top;
	this.settings.button_text_left_padding = left;
	this.callFlash('SetButtonTextPadding', [left, top]);
};

// Public: setButtonTextStyle changes the CSS used to style the HTML/Text overlaid on the button
SWFUpload.prototype.setButtonTextStyle = function (css) {
	this.settings.button_text_style = css;
	this.callFlash('SetButtonTextStyle', [css]);
};
// Public: setButtonDisabled disables/enables the button
SWFUpload.prototype.setButtonDisabled = function (isDisabled) {
	this.settings.button_disabled = isDisabled;
	this.callFlash('SetButtonDisabled', [isDisabled]);
};
// Public: setButtonAction sets the action that occurs when the button is clicked
SWFUpload.prototype.setButtonAction = function (buttonAction) {
	this.settings.button_action = buttonAction;
	this.callFlash('SetButtonAction', [buttonAction]);
};

// Public: setButtonCursor changes the mouse cursor displayed when hovering over the button
SWFUpload.prototype.setButtonCursor = function (cursor) {
	this.settings.button_cursor = cursor;
	this.callFlash('SetButtonCursor', [cursor]);
};

/* *******************************
	Flash Event Interfaces
	These functions are used by Flash to trigger the various
	events.
	
	All these functions a Private.
	
	Because the ExternalInterface library is buggy the event calls
	are added to a queue and the queue then executed by a setTimeout.
	This ensures that events are executed in a determinate order and that
	the ExternalInterface bugs are avoided.
******************************* */

SWFUpload.prototype.queueEvent = function (handlerName, argumentArray) {
	// Warning: Don't call this.debug inside here or you'll create an infinite loop
	
	if (argumentArray == undefined) {
		argumentArray = [];
	} else if (!(argumentArray instanceof Array)) {
		argumentArray = [argumentArray];
	}
	
	var self = this;
	if (typeof this.settings[handlerName] === 'function') {
		// Queue the event
		this.eventQueue.push(function () {
			this.settings[handlerName].apply(this, argumentArray);
		});
		
		// Execute the next queued event
		setTimeout(function () {
			self.executeNextEvent();
		}, 0);
		
	} else if (this.settings[handlerName] !== null) {
		throw 'Event handler ' + handlerName + ' is unknown or is not a function';
	}
};

// Private: Causes the next event in the queue to be executed.  Since events are queued using a setTimeout
// we must queue them in order to garentee that they are executed in order.
SWFUpload.prototype.executeNextEvent = function () {
	// Warning: Don't call this.debug inside here or you'll create an infinite loop

	var  f = this.eventQueue ? this.eventQueue.shift() : null;
	if (typeof(f) === 'function') {
		f.apply(this);
	}
};

// Private: unescapeFileParams is part of a workaround for a flash bug where objects passed through ExternalInterface cannot have
// properties that contain characters that are not valid for JavaScript identifiers. To work around this
// the Flash Component escapes the parameter names and we must unescape again before passing them along.
SWFUpload.prototype.unescapeFilePostParams = function (file) {
	var reg = /[$]([0-9a-f]{4})/i;
	var unescapedPost = {};
	var uk;

	if (file != undefined) {
		for (var k in file.post) {
			if (file.post.hasOwnProperty(k)) {
				uk = k;
				var match;
				while ((match = reg.exec(uk)) !== null) {
					uk = uk.replace(match[0], String.fromCharCode(parseInt('0x' + match[1], 16)));
				}
				unescapedPost[uk] = file.post[k];
			}
		}

		file.post = unescapedPost;
	}

	return file;
};

// Private: Called by Flash to see if JS can call in to Flash (test if External Interface is working)
SWFUpload.prototype.testExternalInterface = function () {
	try {
		return this.callFlash('TestExternalInterface');
	} catch (ex) {
		return false;
	}
};

// Private: This event is called by Flash when it has finished loading. Don't modify this.
// Use the swfupload_loaded_handler event setting to execute custom code when SWFUpload has loaded.
SWFUpload.prototype.flashReady = function () {
	// Check that the movie element is loaded correctly with its ExternalInterface methods defined
	var movieElement = this.getMovieElement();

	if (!movieElement) {
		this.debug("Flash called back ready but the flash movie can't be found.");
		return;
	}

	this.cleanUp(movieElement);
	
	this.queueEvent('swfupload_loaded_handler');
};

// Private: removes Flash added fuctions to the DOM node to prevent memory leaks in IE.
// This function is called by Flash each time the ExternalInterface functions are created.
SWFUpload.prototype.cleanUp = function (movieElement) {
	// Pro-actively unhook all the Flash functions
	try {
		if (this.movieElement && typeof(movieElement.CallFunction) === 'unknown') { // We only want to do this in IE
			this.debug('Removing Flash functions hooks (this should only run in IE and should prevent memory leaks)');
			for (var key in movieElement) {
				try {
					if (typeof(movieElement[key]) === 'function') {
						movieElement[key] = null;
					}
				} catch (ex) {
				}
			}
		}
	} catch (ex1) {
	
	}

	// Fix Flashes own cleanup code so if the SWFMovie was removed from the page
	// it doesn't display errors.
	window['__flash__removeCallback'] = function (instance, name) {
		try {
			if (instance) {
				instance[name] = null;
			}
		} catch (flashEx) {
		
		}
	};

};


/* This is a chance to do something before the browse window opens */
SWFUpload.prototype.fileDialogStart = function () {
	this.queueEvent('file_dialog_start_handler');
};


/* Called when a file is successfully added to the queue. */
SWFUpload.prototype.fileQueued = function (file) {
	file = this.unescapeFilePostParams(file);
	this.queueEvent('file_queued_handler', file);
};


/* Handle errors that occur when an attempt to queue a file fails. */
SWFUpload.prototype.fileQueueError = function (file, errorCode, message) {
	file = this.unescapeFilePostParams(file);
	this.queueEvent('file_queue_error_handler', [file, errorCode, message]);
};

/* Called after the file dialog has closed and the selected files have been queued.
	You could call startUpload here if you want the queued files to begin uploading immediately. */
SWFUpload.prototype.fileDialogComplete = function (numFilesSelected, numFilesQueued, numFilesInQueue) {
	this.queueEvent('file_dialog_complete_handler', [numFilesSelected, numFilesQueued, numFilesInQueue]);
};

SWFUpload.prototype.uploadStart = function (file) {
	file = this.unescapeFilePostParams(file);
	this.queueEvent('return_upload_start_handler', file);
};

SWFUpload.prototype.returnUploadStart = function (file) {
	var returnValue;
	if (typeof this.settings.upload_start_handler === 'function') {
		file = this.unescapeFilePostParams(file);
		returnValue = this.settings.upload_start_handler.call(this, file);
	} else if (this.settings.upload_start_handler != undefined) {
		throw 'upload_start_handler must be a function';
	}

	// Convert undefined to true so if nothing is returned from the upload_start_handler it is
	// interpretted as 'true'.
	if (returnValue === undefined) {
		returnValue = true;
	}
	
	returnValue = !!returnValue;
	
	this.callFlash('ReturnUploadStart', [returnValue]);
};



SWFUpload.prototype.uploadProgress = function (file, bytesComplete, bytesTotal) {
	file = this.unescapeFilePostParams(file);
	this.queueEvent('upload_progress_handler', [file, bytesComplete, bytesTotal]);
};

SWFUpload.prototype.uploadError = function (file, errorCode, message) {
	file = this.unescapeFilePostParams(file);
	this.queueEvent('upload_error_handler', [file, errorCode, message]);
};

SWFUpload.prototype.uploadSuccess = function (file, serverData, responseReceived) {
	file = this.unescapeFilePostParams(file);
	this.queueEvent('upload_success_handler', [file, serverData, responseReceived]);
};

SWFUpload.prototype.uploadComplete = function (file) {
	file = this.unescapeFilePostParams(file);
	this.queueEvent('upload_complete_handler', file);
};

/* Called by SWFUpload JavaScript and Flash functions when debug is enabled. By default it writes messages to the
   internal debug console.  You can override this event and have messages written where you want. */
SWFUpload.prototype.debug = function (message) {
	this.queueEvent('debug_handler', message);
};


/* **********************************
	Debug Console
	The debug console is a self contained, in page location
	for debug message to be sent.  The Debug Console adds
	itself to the body if necessary.

	The console is automatically scrolled as messages appear.
	
	If you are using your own debug handler or when you deploy to production and
	have debug disabled you can remove these functions to reduce the file size
	and complexity.
********************************** */
   
// Private: debugMessage is the default debug_handler.  If you want to print debug messages
// call the debug() function.  When overriding the function your own function should
// check to see if the debug setting is true before outputting debug information.
SWFUpload.prototype.debugMessage = function (message) {
	if (this.settings.debug) {
		var exceptionMessage, exceptionValues = [];

		// Check for an exception object and print it nicely
		if (typeof message === 'object' && typeof message.name === 'string' && typeof message.message === 'string') {
			for (var key in message) {
				if (message.hasOwnProperty(key)) {
					exceptionValues.push(key + ': ' + message[key]);
				}
			}
			exceptionMessage = exceptionValues.join("\n") || '';
			exceptionValues = exceptionMessage.split("\n");
			exceptionMessage = 'EXCEPTION: ' + exceptionValues.join("\nEXCEPTION: ");
			SWFUpload.Console.writeLine(exceptionMessage);
		} else {
			SWFUpload.Console.writeLine(message);
		}
	}
};

SWFUpload.Console = {};
SWFUpload.Console.writeLine = function (message) {
	var console, documentForm;

	try {
		console = $$('SWFUpload_Console');

		if (!console) {
			documentForm = document.createElement('form');
			document.getElementsByTagName('body')[0].appendChild(documentForm);

			console = document.createElement('textarea');
			console.id = 'SWFUpload_Console';
			console.style.fontFamily = 'monospace';
			console.setAttribute('wrap', 'off');
			console.wrap = 'off';
			console.style.overflow = 'auto';
			console.style.width = '700px';
			console.style.height = '350px';
			console.style.margin = '5px';
			documentForm.appendChild(console);
		}

		console.value += message + "\n";

		console.scrollTop = console.scrollHeight - console.clientHeight;
	} catch (ex) {
		alert('Exception: ' + ex.name + ' Message: ' + ex.message);
	}
};
//content swfupload.queue.js
SWFUpload.queue = {};

SWFUpload.prototype.initSettings = (function (oldInitSettings) {
	return function () {
		if (typeof(oldInitSettings) === 'function') {
			oldInitSettings.call(this);
		}
		
		this.queueSettings = {};
		
		this.queueSettings.queue_cancelled_flag = false;
		this.queueSettings.queue_upload_count = 0;
		
		this.queueSettings.user_upload_complete_handler = this.settings.upload_complete_handler;
		this.queueSettings.user_upload_start_handler = this.settings.upload_start_handler;
		this.settings.upload_complete_handler = SWFUpload.queue.uploadCompleteHandler;
		this.settings.upload_start_handler = SWFUpload.queue.uploadStartHandler;
		
		this.settings.queue_complete_handler = this.settings.queue_complete_handler || null;
	};
})(SWFUpload.prototype.initSettings);

SWFUpload.prototype.startUpload = function (fileID) {
	this.queueSettings.queue_cancelled_flag = false;
	this.callFlash('StartUpload', [fileID]);
};

SWFUpload.prototype.cancelQueue = function () {
	this.queueSettings.queue_cancelled_flag = true;
	this.stopUpload();
	
	var stats = this.getStats();
	while (stats.files_queued > 0) {
		this.cancelUpload();
		stats = this.getStats();
	}
};

SWFUpload.queue.uploadStartHandler = function (file) {
	var returnValue;
	if (typeof(this.queueSettings.user_upload_start_handler) === 'function') {
		returnValue = this.queueSettings.user_upload_start_handler.call(this, file);
	}
	
	// To prevent upload a real 'FALSE' value must be returned, otherwise default to a real 'TRUE' value.
	returnValue = (returnValue === false) ? false : true;
	
	this.queueSettings.queue_cancelled_flag = !returnValue;
	return returnValue;
};

SWFUpload.queue.uploadCompleteHandler = function (file) {
	var user_upload_complete_handler = this.queueSettings.user_upload_complete_handler;
	var continueUpload;
	
	if (file.filestatus === SWFUpload.FILE_STATUS.COMPLETE) {
		this.queueSettings.queue_upload_count++;
	}

	if (typeof(user_upload_complete_handler) === 'function') {
		continueUpload = (user_upload_complete_handler.call(this, file) === false) ? false : true;
	} else if (file.filestatus === SWFUpload.FILE_STATUS.QUEUED) {
		// If the file was stopped and re-queued don't restart the upload
		continueUpload = false;
	} else {
		continueUpload = true;
	}
	
	if (continueUpload) {
		var stats = this.getStats();
		if (stats.files_queued > 0 && this.queueSettings.queue_cancelled_flag === false) {
			this.startUpload();
		} else if (this.queueSettings.queue_cancelled_flag === false) {
			this.queueEvent('queue_complete_handler', [this.queueSettings.queue_upload_count]);
			this.queueSettings.queue_upload_count = 0;
		} else {
			this.queueSettings.queue_cancelled_flag = false;
			this.queueSettings.queue_upload_count = 0;
		}
	}
};

//content swfupload.swfobject.js
var swfobject=function(){var X="undefined",P="object",a="visibility:visible",e="visibility:hidden",B="Shockwave Flash",h="ShockwaveFlash.ShockwaveFlash",V="application/x-shockwave-flash",K="SWFObjectExprInst",G=window,g=document,N=navigator,f=[],H=[],Q=null,L=null,S=false,C=false;var Y=function(){var l=typeof g.getElementById!=X&&typeof g.getElementsByTagName!=X&&typeof g.createElement!=X&&typeof g.appendChild!=X&&typeof g.replaceChild!=X&&typeof g.removeChild!=X&&typeof g.cloneNode!=X,t=[0,0,0],n=null;if(typeof N.plugins!=X&&typeof N.plugins[B]==P){n=N.plugins[B].description;if(n){n=n.replace(/^.*\s+(\S+\s+\S+$)/,"$1");t[0]=parseInt(n.replace(/^(.*)\..*$/,"$1"),10);t[1]=parseInt(n.replace(/^.*\.(.*)\s.*$/,"$1"),10);t[2]=/r/.test(n)?parseInt(n.replace(/^.*r(.*)$/,"$1"),10):0}}else{if(typeof G.ActiveXObject!=X){var o=null,s=false;try{o=new ActiveXObject(h+".7")}catch(k){try{o=new ActiveXObject(h+".6");t=[6,0,21];o.AllowScriptAccess="always"}catch(k){if(t[0]==6){s=true}}if(!s){try{o=new ActiveXObject(h)}catch(k){}}}if(!s&&o){try{n=o.GetVariable("$version");if(n){n=n.split(" ")[1].split(",");t=[parseInt(n[0],10),parseInt(n[1],10),parseInt(n[2],10)]}}catch(k){}}}}var v=N.userAgent.toLowerCase(),j=N.platform.toLowerCase(),r=/webkit/.test(v)?parseFloat(v.replace(/^.*webkit\/(\d+(\.\d+)?).*$/,"$1")):false,i=false,q=j?/win/.test(j):/win/.test(v),m=j?/mac/.test(j):/mac/.test(v);/*@cc_on i=true;@if(@_win32)q=true;@elif(@_mac)m=true;@end@*/return{w3cdom:l,pv:t,webkit:r,ie:i,win:q,mac:m}}();var d=function(){if(!Y.w3cdom){return }J(I);if(Y.ie&&Y.win){try{g.write("<script id=__ie_ondomload defer=true src=//:><\/script>");var i=b("__ie_ondomload");if(i){i.onreadystatechange=function(){if(this.readyState=="complete"){this.parentNode.removeChild(this);U()}}}}catch(j){}}if(Y.webkit&&typeof g.readyState!=X){Q=setInterval(function(){if(/loaded|complete/.test(g.readyState)){U()}},10)}if(typeof g.addEventListener!=X){g.addEventListener("DOMContentLoaded",U,null)}M(U)}();function U(){if(S){return }if(Y.ie&&Y.win){var m=W("span");try{var l=g.getElementsByTagName("body")[0].appendChild(m);l.parentNode.removeChild(l)}catch(n){return }}S=true;if(Q){clearInterval(Q);Q=null}var j=f.length;for(var k=0;k<j;k++){f[k]()}}function J(i){if(S){i()}else{f[f.length]=i}}function M(j){if(typeof G.addEventListener!=X){G.addEventListener("load",j,false)}else{if(typeof g.addEventListener!=X){g.addEventListener("load",j,false)}else{if(typeof G.attachEvent!=X){G.attachEvent("onload",j)}else{if(typeof G.onload=="function"){var i=G.onload;G.onload=function(){i();j()}}else{G.onload=j}}}}}function I(){var l=H.length;for(var j=0;j<l;j++){var m=H[j].id;if(Y.pv[0]>0){var k=b(m);if(k){H[j].width=k.getAttribute("width")?k.getAttribute("width"):"0";H[j].height=k.getAttribute("height")?k.getAttribute("height"):"0";if(O(H[j].swfVersion)){if(Y.webkit&&Y.webkit<312){T(k)}}else{if(H[j].expressInstall&&!C&&O("6.0.65")&&(Y.win||Y.mac)){D(H[j])}else{c(k)}}}}A("#"+m,a)}}function T(m){var k=m.getElementsByTagName(P)[0];if(k){var p=W("embed"),r=k.attributes;if(r){var o=r.length;for(var n=0;n<o;n++){if(r[n].nodeName.toLowerCase()=="data"){p.setAttribute("src",r[n].nodeValue)}else{p.setAttribute(r[n].nodeName,r[n].nodeValue)}}}var q=k.childNodes;if(q){var s=q.length;for(var l=0;l<s;l++){if(q[l].nodeType==1&&q[l].nodeName.toLowerCase()=="param"){p.setAttribute(q[l].getAttribute("name"),q[l].getAttribute("value"))}}}m.parentNode.replaceChild(p,m)}}function F(i){if(Y.ie&&Y.win&&O("8.0.0")){G.attachEvent("onunload",function(){var k=b(i);for(var j in k){if(typeof k[j]=="function"){k[j]=function(){}}}k.parentNode.removeChild(k)})}}function D(j){C=true;var o=b(j.id);if(o){if(j.altContentId){var l=b(j.altContentId);if(l){L=l}}else{L=Z(o)}if(!(/%$/.test(j.width))&&parseInt(j.width,10)<310){j.width="310"}if(!(/%$/.test(j.height))&&parseInt(j.height,10)<137){j.height="137"}g.title=g.title.slice(0,47)+" - Flash Player Installation";var n=Y.ie&&Y.win?"ActiveX":"PlugIn",k=g.title,m="MMredirectURL="+G.location+"&MMplayerType="+n+"&MMdoctitle="+k,p=j.id;if(Y.ie&&Y.win&&o.readyState!=4){var i=W("div");p+="SWFObjectNew";i.setAttribute("id",p);o.parentNode.insertBefore(i,o);o.style.display="none";G.attachEvent("onload",function(){o.parentNode.removeChild(o)})}R({data:j.expressInstall,id:K,width:j.width,height:j.height},{flashvars:m},p)}}function c(j){if(Y.ie&&Y.win&&j.readyState!=4){var i=W("div");j.parentNode.insertBefore(i,j);i.parentNode.replaceChild(Z(j),i);j.style.display="none";G.attachEvent("onload",function(){j.parentNode.removeChild(j)})}else{j.parentNode.replaceChild(Z(j),j)}}function Z(n){var m=W("div");if(Y.win&&Y.ie){m.innerHTML=n.innerHTML}else{var k=n.getElementsByTagName(P)[0];if(k){var o=k.childNodes;if(o){var j=o.length;for(var l=0;l<j;l++){if(!(o[l].nodeType==1&&o[l].nodeName.toLowerCase()=="param")&&!(o[l].nodeType==8)){m.appendChild(o[l].cloneNode(true))}}}}}return m}function R(AE,AC,q){var p,t=b(q);if(typeof AE.id==X){AE.id=q}if(Y.ie&&Y.win){var AD="";for(var z in AE){if(AE[z]!=Object.prototype[z]){if(z=="data"){AC.movie=AE[z]}else{if(z.toLowerCase()=="styleclass"){AD+=' class="'+AE[z]+'"'}else{if(z!="classid"){AD+=" "+z+'="'+AE[z]+'"'}}}}}var AB="";for(var y in AC){if(AC[y]!=Object.prototype[y]){AB+='<param name="'+y+'" value="'+AC[y]+'" />'}}t.outerHTML='<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"'+AD+">"+AB+"</object>";F(AE.id);p=b(AE.id)}else{if(Y.webkit&&Y.webkit<312){var AA=W("embed");AA.setAttribute("type",V);for(var x in AE){if(AE[x]!=Object.prototype[x]){if(x=="data"){AA.setAttribute("src",AE[x])}else{if(x.toLowerCase()=="styleclass"){AA.setAttribute("class",AE[x])}else{if(x!="classid"){AA.setAttribute(x,AE[x])}}}}}for(var w in AC){if(AC[w]!=Object.prototype[w]){if(w!="movie"){AA.setAttribute(w,AC[w])}}}t.parentNode.replaceChild(AA,t);p=AA}else{var s=W(P);s.setAttribute("type",V);for(var v in AE){if(AE[v]!=Object.prototype[v]){if(v.toLowerCase()=="styleclass"){s.setAttribute("class",AE[v])}else{if(v!="classid"){s.setAttribute(v,AE[v])}}}}for(var u in AC){if(AC[u]!=Object.prototype[u]&&u!="movie"){E(s,u,AC[u])}}t.parentNode.replaceChild(s,t);p=s}}return p}function E(k,i,j){var l=W("param");l.setAttribute("name",i);l.setAttribute("value",j);k.appendChild(l)}function b(i){return g.getElementById(i)}function W(i){return g.createElement(i)}function O(k){var j=Y.pv,i=k.split(".");i[0]=parseInt(i[0],10);i[1]=parseInt(i[1],10);i[2]=parseInt(i[2],10);return(j[0]>i[0]||(j[0]==i[0]&&j[1]>i[1])||(j[0]==i[0]&&j[1]==i[1]&&j[2]>=i[2]))?true:false}function A(m,j){if(Y.ie&&Y.mac){return }var l=g.getElementsByTagName("head")[0],k=W("style");k.setAttribute("type","text/css");k.setAttribute("media","screen");if(!(Y.ie&&Y.win)&&typeof g.createTextNode!=X){k.appendChild(g.createTextNode(m+" {"+j+"}"))}l.appendChild(k);if(Y.ie&&Y.win&&typeof g.styleSheets!=X&&g.styleSheets.length>0){var i=g.styleSheets[g.styleSheets.length-1];if(typeof i.addRule==P){i.addRule(m,j)}}}return{registerObject:function(l,i,k){if(!Y.w3cdom||!l||!i){return }var j={};j.id=l;j.swfVersion=i;j.expressInstall=k?k:false;H[H.length]=j;A("#"+l,e)},getObjectById:function(l){var i=null;if(Y.w3cdom&&S){var j=b(l);if(j){var k=j.getElementsByTagName(P)[0];if(!k||(k&&typeof j.SetVariable!=X)){i=j}else{if(typeof k.SetVariable!=X){i=k}}}}return i},embedSWF:function(n,u,r,t,j,m,k,p,s){if(!Y.w3cdom||!n||!u||!r||!t||!j){return }r+="";t+="";if(O(j)){A("#"+u,e);var q=(typeof s==P)?s:{};q.data=n;q.width=r;q.height=t;var o=(typeof p==P)?p:{};if(typeof k==P){for(var l in k){if(k[l]!=Object.prototype[l]){if(typeof o.flashvars!=X){o.flashvars+="&"+l+"="+k[l]}else{o.flashvars=l+"="+k[l]}}}}J(function(){R(q,o,u);A("#"+u,a)})}else{if(m&&!C&&O("6.0.65")&&(Y.win||Y.mac)){A("#"+u,e);J(function(){var i={};i.id=i.altContentId=u;i.width=r;i.height=t;i.expressInstall=m;D(i);A("#"+u,a)})}}},getFlashPlayerVersion:function(){return{major:Y.pv[0],minor:Y.pv[1],release:Y.pv[2]}},hasFlashPlayerVersion:O,createSWF:function(k,j,i){if(Y.w3cdom&&S){return R(k,j,i)}else{return undefined}},createCSS:function(j,i){if(Y.w3cdom){A(j,i)}},addDomLoadEvent:J,addLoadEvent:M,getQueryParamValue:function(m){var l=g.location.search||g.location.hash;if(m==null){return l}if(l){var k=l.substring(1).split("&");for(var j=0;j<k.length;j++){if(k[j].substring(0,k[j].indexOf("="))==m){return k[j].substring((k[j].indexOf("=")+1))}}}return""},expressInstallCallback:function(){if(C&&L){var i=b(K);if(i){i.parentNode.replaceChild(L,i);L=null;C=false}}}}}();


SWFUpload.onload = function () {};

swfobject.addDomLoadEvent(function () {
	if (typeof(SWFUpload.onload) === 'function') {
		SWFUpload.onload.call(window);
	}
});

SWFUpload.prototype.initSettings = (function (oldInitSettings) {
	return function () {
		if (typeof(oldInitSettings) === 'function') {
			oldInitSettings.call(this);
		}
			this.ensureDefault = function (settingName, defaultValue) {
			this.settings[settingName] = (this.settings[settingName] == undefined) ? defaultValue : this.settings[settingName];
		};
			this.ensureDefault('minimum_flash_version', '9.0.28');
		this.ensureDefault('swfupload_load_failed_handler', null);
			delete this.ensureDefault;
		};
})(SWFUpload.prototype.initSettings);

SWFUpload.prototype.loadFlash = function (oldLoadFlash) {
	return function () {
		var hasFlash = swfobject.hasFlashPlayerVersion(this.settings.minimum_flash_version);
		
		if (hasFlash) {
			this.queueEvent('swfupload_pre_load_handler');
			if (typeof(oldLoadFlash) === 'function') {
				oldLoadFlash.call(this);
			}
		} else {
			this.queueEvent('swfupload_load_failed_handler');
		}
	};
		
}(SWFUpload.prototype.loadFlash);
		
SWFUpload.prototype.displayDebugInfo = function (oldDisplayDebugInfo) {
	return function () {
		if (typeof(oldDisplayDebugInfo) === 'function') {
			oldDisplayDebugInfo.call(this);
		}
		
		this.debug(
			[
				'SWFUpload.SWFObject Plugin settings:', "\n",
				"\t", 'minimum_flash_version:                      ', this.settings.minimum_flash_version, "\n",
				"\t", 'swfupload_load_failed_handler assigned:     ', (typeof(this.settings.swfupload_load_failed_handler) === 'function').toString(), "\n",
			].join('')
		);
	};	
}(SWFUpload.prototype.displayDebugInfo);

//content fileprogress.js
function FileProgress(file, targetID) {
	var layout;
	this.fileProgressID = file.id;

	this.height = 0;
	this.opacity = 100;
	
	this.fileProgressWrapper = $$(this.fileProgressID);
	if (!this.fileProgressWrapper) {
		this.layouts = FileProgress.assoc[file.id] = {};
		this.fileProgressWrapper = document.createElement('div');
		this.fileProgressWrapper.className = 'progressWrapper';
		this.fileProgressWrapper.id = this.fileProgressID;

		this.fileProgressElement = document.createElement('div');
		this.fileProgressElement.className = 'progressContainer';

		layout = this.layouts.cancel = document.createElement('a');
		layout.className = 'progressCancel';
		layout.href = 'javascript:;';
		layout.style.visibility = 'hidden';
		layout.appendChild(document.createTextNode(' '));

		layout = this.layouts.text = document.createElement('div');
		layout.className = 'progressName';
		layout.appendChild(document.createTextNode(file.name));

		layout = this.layouts.bar = document.createElement('div');
		layout.className = 'progressBarInProgress';

		layout = this.layouts.status = document.createElement('div');
		layout.className = 'progressBarStatus';
		layout.appendChild(document.createTextNode(' '));

		this.fileProgressElement.appendChild(this.layouts.text);
		this.fileProgressElement.appendChild(this.layouts.cancel);
		this.fileProgressElement.appendChild(this.layouts.status);

		if(watermark_id){
			layout = this.layouts.mark = document.createElement(dhua.ie ? '<input type="checkbox" checked/>' : 'input');
			layout.id = 'wmid_' + file.id;
			layout.type = 'checkbox';
			layout.checked = true;
			layout.className = 'progressWatermark';
	
			layout = this.layouts.label = document.createElement('label');
			layout.className = 'progressWatermarkLabel';
			layout.setAttribute('for', 'wmid_' + file.id);
			layout.setAttribute('htmlFor', 'wmid_' + file.id);
			layout.appendChild(document.createTextNode('\u6c34\u5370'));
	
			this.fileProgressElement.appendChild(this.layouts.label);
			this.fileProgressElement.appendChild(this.layouts.mark);
		}

		this.fileProgressElement.appendChild(this.layouts.bar);
		this.fileProgressWrapper.appendChild(this.fileProgressElement);

		$$(targetID).appendChild(this.fileProgressWrapper);
	} else {
		this.layouts = FileProgress.assoc[file.id];
		this.fileProgressElement = this.fileProgressWrapper.firstChild;
		this.reset();
	}

	this.height = this.fileProgressWrapper.offsetHeight;
	this.setTimer(null);


}

FileProgress.assoc = {};

FileProgress.prototype.setTimer = function (timer) {
	this.fileProgressElement['FP_TIMER'] = timer;
};
FileProgress.prototype.getTimer = function (timer) {
	return this.fileProgressElement['FP_TIMER'] || null;
};

FileProgress.prototype.reset = function () {
	this.fileProgressElement.className = 'progressContainer';

	this.layouts.status.innerHTML = '&nbsp;';
	this.layouts.status.className = 'progressBarStatus';
	
	this.layouts.bar.className = 'progressBarInProgress';
	this.layouts.bar.style.width = '0%';
	
	this.appear();	
};

FileProgress.prototype.setProgress = function (percentage) {
	this.fileProgressElement.className = 'progressContainer green';
	this.layouts.bar.className = 'progressBarInProgress';
	this.layouts.bar.style.width = percentage + '%';

	this.appear();	
};
FileProgress.prototype.setComplete = function () {
	
	this.fileProgressElement.className = 'progressContainer blue';
	this.layouts.bar.className = 'progressBarComplete';
	this.layouts.bar.style.width = '';

	var oSelf = this;
	this.setTimer(setTimeout(function () {
		oSelf.disappear();
	}, 3000));
};
FileProgress.prototype.setError = function () {
	this.fileProgressElement.className = 'progressContainer red';
	this.layouts.bar.className = 'progressBarError';
	this.layouts.bar.style.width = '';

	var oSelf = this;
	this.setTimer(setTimeout(function () {
		oSelf.disappear();
	}, 2000));
};
FileProgress.prototype.setCancelled = function () {
	this.fileProgressElement.className = 'progressContainer';
	this.layouts.bar.className = 'progressBarError';
	this.layouts.bar.style.width = '';

	var oSelf = this;
	this.setTimer(setTimeout(function () {
		oSelf.disappear();
	}, 1000));
};
FileProgress.prototype.setStatus = function (status) {
	this.layouts.status.innerHTML = status;
};

// Show/Hide the cancel button
FileProgress.prototype.toggleCancel = function (show, swfUploadInstance) {
	this.layouts.cancel.style.visibility = show ? 'visible' : 'hidden';
	if (swfUploadInstance) {
		var fileID = this.fileProgressID;
		this.layouts.cancel.onclick = function () {
			currfiles--;
			swfUploadInstance.cancelUpload(fileID);
			return false;
		};
	}
};

FileProgress.prototype.appear = function () {
	if (this.getTimer() !== null) {
		clearTimeout(this.getTimer());
		this.setTimer(null);
	}
	
	if (this.fileProgressWrapper.filters) {
		try {
			this.fileProgressWrapper.filters.item('DXImageTransform.Microsoft.Alpha').opacity = 100;
		} catch (e) {
			// If it is not set initially, the browser will throw an error.  This will set it if it is not set yet.
			this.fileProgressWrapper.style.filter = 'progid:DXImageTransform.Microsoft.Alpha(opacity=100)';
		}
	} else {
		this.fileProgressWrapper.style.opacity = 1;
	}
		
	this.fileProgressWrapper.style.height = '';
	
	this.height = this.fileProgressWrapper.offsetHeight;
	this.opacity = 100;
	this.fileProgressWrapper.style.display = '';
	
};

// Fades out and clips away the FileProgress box.
FileProgress.prototype.disappear = function () {

	var reduceOpacityBy = 15;
	var reduceHeightBy = 4;
	var rate = 30;	// 15 fps

	if (this.opacity > 0) {
		this.opacity -= reduceOpacityBy;
		if (this.opacity < 0) {
			this.opacity = 0;
		}

		if (this.fileProgressWrapper.filters) {
			try {
				this.fileProgressWrapper.filters.item('DXImageTransform.Microsoft.Alpha').opacity = this.opacity;
			} catch (e) {
				// If it is not set initially, the browser will throw an error.  This will set it if it is not set yet.
				this.fileProgressWrapper.style.filter = 'progid:DXImageTransform.Microsoft.Alpha(opacity=' + this.opacity + ')';
			}
		} else {
			this.fileProgressWrapper.style.opacity = this.opacity / 100;
		}
	}

	if (this.height > 0) {
		this.height -= reduceHeightBy;
		if (this.height < 0) {
			this.height = 0;
		}

		this.fileProgressWrapper.style.height = this.height + 'px';
	}

	if (this.height > 0 || this.opacity > 0) {
		var oSelf = this;
		this.setTimer(setTimeout(function () {
			oSelf.disappear();
		}, rate));
	} else {
		this.fileProgressWrapper.style.display = 'none';
		this.fileProgressWrapper.parentNode.removeChild(this.fileProgressWrapper);
		this.setTimer(null);
	}
};

//content handlers.js
function swfUploadPreLoad() {
	var self = this;
	var loading = function () {
		//$$('divSWFUploadUI').style.display = 'none';
		$$('divLoadingContent').style.display = '';

		var longLoad = function () {
			$$('divLoadingContent').style.display = 'none';
			$$('divLongLoading').style.display = '';
		};
		this.customSettings.loadingTimeout = setTimeout(function () {
			longLoad.call(self)
		}, 15 * 1000);
	};
	
	this.customSettings.loadingTimeout = setTimeout(function () {
		loading.call(self);
	}, 1 * 1000);
}
function swfUploadLoaded() {
	var self = this;
	clearTimeout(this.customSettings.loadingTimeout);
	$$('divLoadingContent').style.display = 'none';
	$$('divLongLoading').style.display = 'none';
	$$('divAlternateContent').style.display = 'none';
	
	$$(this.customSettings.cancelButtonId).onclick = function () { self.cancelQueue(); };
}
   
function swfUploadLoadFailed() {
	clearTimeout(this.customSettings.loadingTimeout);
	$$('divLoadingContent').style.display = 'none';
	$$('divLongLoading').style.display = 'none';
	$$('divAlternateContent').style.display = '';
}
   
   
function fileQueued(file) {
	try {
		var su = this, lmt = filelimit[file.type.substr(1).toLowerCase()], v = file.size < lmt[0] || file.size > lmt[1],
		progress = new FileProgress(file, su.customSettings.progressTarget);
		progress.setStatus(v ? '\u9644\u4ef6\u5927\u5c0f\u4e0d\u5408\u6cd5' : '\u7b49\u5f85\u4e0a\u4f20...');
		progress.toggleCancel(!v, su);
		if(v){
			currfiles--;
			su.cancelUpload(file.id,false);
			progress.setError();
			if(su.getStats().files_queued === 0)setTimeout(function(){$$(su.customSettings.uploadButtonId).disabled = true},20);
		}else{
			swfuVal.push(file);
		}
	} catch (ex) {
		this.debug(ex);
	}

}

function fileQueueError(file, errorCode, message) {
	try {
		if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
			alert('\u8d85\u51fa\u9644\u4ef6\u6700\u5927\u6570\u91cf\uff01');
			return;
		}

		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);

		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			progress.setStatus('File is too big.');
			this.debug('Error Code: File too big, File name: ' + file.name + ', File size: ' + file.size + ', Message: ' + message);
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			progress.setStatus('Cannot upload Zero Byte files.');
			this.debug('Error Code: Zero byte file, File name: ' + file.name + ', File size: ' + file.size + ', Message: ' + message);
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			progress.setStatus('Invalid File Type.');
			this.debug('Error Code: Invalid File Type, File name: ' + file.name + ', File size: ' + file.size + ', Message: ' + message);
			break;
		default:
			if (file !== null) {
				progress.setStatus('Unhandled Error');
			}
			this.debug('Error Code: ' + errorCode + ', File name: ' + file.name + ', File size: ' + file.size + ', Message: ' + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

function fileDialogComplete(numFilesSelected, numFilesQueued) {
	try {
		currfiles+=numFilesQueued;
		canuploads-=numFilesQueued;
		if (numFilesQueued > 0) {
			$$(this.customSettings.uploadButtonId).disabled = false;
		}
		/* I want auto start the upload and I can do that here */
		
	} catch (ex)  {
        this.debug(ex);
	}
}

function uploadStart(file) {
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setStatus('\u6b63\u5728\u8fdb\u884c\u4e0a\u4f20...');
		progress.toggleCancel(true, this);
		watermark_id && progress.layouts.mark.checked ? this.addPostParam('wmid', watermark_id) : this.removePostParam('wmid');
	}
	catch (ex) {}
	
	return true;
}

function uploadProgress(file, bytesLoaded, bytesTotal) {
	try {
		var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setStatus('\u6b63\u5728\u8fdb\u884c\u4e0a\u4f20...(' + percent + '%)');
		progress.setProgress(percent);
	} catch (ex) {
		this.debug(ex);
	}
}

function uploadSuccess(file, serverData) {
	try {
		var k,val,status,progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.toggleCancel(false);
		if(dhua.ie)Cookie($setkey,$params[$setkey]);
		try{
			val=serverData.split('|');
            if(typeof val[0] != undefined) val[0] = val[0].replace(/\r/, '').replace(/\n/, '');
			if(val[0]=='-1'||val[0]=='1'){
				this.queueSettings.queue_cancelled_flag = true;
				$$(this.customSettings.cancelButtonId).disabled = true;
				$$(this.customSettings.uploadButtonId).disabled = this.getStats().files_queued === 0;
				progress.setError();
				progress.setStatus(val[0]=='1'?'\u8d85\u51fa\u9644\u4ef6\u7a7a\u95f4\u5927\u5c0f\u6216\u4e0a\u4f20\u6743\u9650\u4e0d\u8db3\uff01':'\u672a\u767b\u9646\u6216\u5df2\u8d85\u65f6\uff0c\u8bf7\u767b\u9646\u540e\u518d\u8bd5\uff01');
				canuploads++;
				return;
			}
			if(val[0]!='0')throw '';
			addfileitem(FileIndex++,val[1]);
			progress.setComplete();
			progress.setStatus('\u4e0a\u4f20\u6210\u529f\uff01');
			swfuploads++;
			status = $$('swfStatus');
			status.innerHTML = swfuploads;
		}catch(e){
			progress.setError();
			progress.setStatus('\u9644\u4ef6\u4e0d\u7b26\u5408\u6ca1\u6709\u6dfb\u52a0\uff01');
			canuploads++;
		}
	} catch (ex) {
		this.debug(ex);
	}
}

function uploadError(file, errorCode, message) {
	try {
		var num, progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);

		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			progress.setStatus('Upload Error: ' + message);
			this.debug('Error Code: HTTP Error, File name: ' + file.name + ', Message: ' + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			progress.setStatus('Upload Failed.');
			this.debug('Error Code: Upload Failed, File name: ' + file.name + ', File size: ' + file.size + ', Message: ' + message);
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			progress.setStatus('Server (IO) Error');
			this.debug('Error Code: IO Error, File name: ' + file.name + ', Message: ' + message);
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			progress.setStatus('Security Error');
			this.debug('Error Code: Security Error, File name: ' + file.name + ', Message: ' + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			progress.setStatus('Upload limit exceeded.');
			this.debug('Error Code: Upload Limit Exceeded, File name: ' + file.name + ', File size: ' + file.size + ', Message: ' + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
			progress.setStatus('Failed Validation.  Upload skipped.');
			this.debug('Error Code: File Validation Failed, File name: ' + file.name + ', File size: ' + file.size + ', Message: ' + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			// If there aren't any files left (they were all cancelled) disable the cancel button
			num = this.getStats().files_queued;
			if (num === 0) {
				$$(this.customSettings.cancelButtonId).disabled = true;
				$$(this.customSettings.uploadButtonId).disabled = true;
			}
			progress.setStatus('\u53d6\u6d88\u4e0a\u4f20');
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			progress.setStatus('\u4e2d\u6b62\u4e0a\u4f20 ');
			break;
		default:
			progress.setStatus('Unhandled Error: ' + errorCode);
			this.debug('Error Code: ' + errorCode + ', File name: ' + file.name + ', File size: ' + file.size + ', Message: ' + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

function uploadComplete(file) {
	currfiles--;
	if (this.getStats().files_queued === 0) {
		$$(this.customSettings.cancelButtonId).disabled = true;
	}
}

// This event comes from the Queue Plugin
function queueComplete() {
	if(swfuploads!=0)tbButtons[0].onclick();
	swfuploads=0;
}