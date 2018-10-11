$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {

		var key = this.name, obj = o;

		var selectorName = this.name.replace(/\./g, '\\\\.');
		var keys = key.split("."), len = keys.length, rel = $("input[name='" + selectorName + "'], select[name='" + selectorName + "'], textarea[name='" + selectorName + "']").attr("rel");

		var val = this.value;
		if (rel) {
		} else if (val == '') {
			val = null;
		}

		for ( var i = 0; i < len; i++) {
			var n = keys[i];

			if (i == len - 1) {
				if (n.indexOf("[") != -1) {
					var k = n.substring(0, n.indexOf("["));
					obj = obj[k] = !obj[k] ? [] : obj[k];
				} else {
					key = n;
				}
			} else if (n.indexOf("[") != -1) {
				var k = n.substring(0, n.indexOf("["));
				var arr = obj[k] = !obj[k] ? [] : obj[k];
				var seq = parseInt(n.substring(n.indexOf("[") + 1, n.indexOf("]")));
				if (!arr[seq]) {
					arr[seq] = {};
				}
				obj = arr[seq];
			} else {
				var k = n;
				obj = obj[k] = !obj[k] ? {} : obj[k];
			}
		}

		if (obj.push) {
			obj.push(val);
		} else {
			obj[key] = val;
		}
	});
	return isEmpty(o) ? null : o;

	// bakup
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [
					o[this.name]
				];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

function isEmpty(obj) {
	for ( var prop in obj) {
		if (obj.hasOwnProperty(prop))
			return false;
	}

	return true;
}

var callbacks = callbacks || {

};

var common = {

	synchronizeData: {
		UUID: "uid-test-uid",
		token: null,
		timestamp: 0,
		payload: null
	},

	bindFormSubmit: function(selector) {
		$(selector).submit(function() {
			try {
				var formObj = $(this), url = this.action, method = this.method;
				var formData = formObj.serializeObject();
				var d = data.synchronizeData;
				d.payload = formData;
				d.timestamp = new Date().getTime();
				console.info("Request:\n" + JSON.stringify(d));
				$.ajax({
					contentType: 'application/json',
					dataType: 'json',
					data: JSON.stringify(d),
					success: function(json) {
						var jsonString = JSON.stringify(json);
						console.info("response: \n" + jsonString);
						if (formObj.attr("callback")) {
							var callback = callbacks[formObj.attr("callback")];
							if (callback) {
								callback(json);
							}
						}
					},
					error: function() {
						alert("Error Service!");
					},
					type: method,
					url: url
				});
			} catch (e) {
				console.info(e);
			}
			return false;
		});
	}
};

var data = {
	synchronizeData: {
		token: "e33e708f-8980-476b-b541-4c74eeab078a",
		payload: null
	}
};

var callbacks = {
	doHandleLogin: function(jsonData) {
		if (jsonData.state == 'Successful') {
			var payload = jsonData.payload;
			data.synchronizeData.token = payload.authToken;
		}
	}
};

var bindForm = function() {
	$('input[name=mode]').click(function() {
		$('.auth-mode').hide();
		$('.auth-mode.' + $(this).val()).show();
	});
	$('input[name=mode]:checked').click();

	$('.binary-form').each(function() {
		var formObj = $(this), url = this.action, method = this.method;
		if (formObj.hasClass('binded')) {
			return;
		} else {
			formObj.addClass('binded');

			var that = $(this);
			that.addClass('binded');

			that.find('input[name=mode]').click(function() {
				that.find('.auth-mode').hide();
				that.find('.auth-mode.' + $(this).val()).show();
			});
			that.find('input[name=mode]').eq(0).click();
		}
		$(".request-url").html("URI: " + url.substring(url.indexOf('service') + 7));
		$(".request-data textarea").html("binary form");
		$(".response-status input").val("loading...");
		$(".response-data textarea").html("loading...");

		formObj.ajaxForm({
			target: formObj.attr('output'),
			beforeSend: function() {
				console.info("Begining to upload file");
			},
			beforeSubmit: function(formData, jqForm, options) {
				console.info("XXX: Begining to upload file");
			},
			uploadProgress: function(event, position, total, percentComplete) {
				console.info("Upload processing : " + percentComplete);
			},
			success: function(responseText, statusText, xhr, $form) {
				if (statusText == "success") {
					var json = eval("(" + xhr.responseText + ")");
					var jsonString = JSON.stringify(json);
					if (console)
						console.info("response payload: \n" + jsonString);
					$(".response-status input").val(json.state);
					if (json.state != 'Successful') {
						$(".response-data textarea").html(json.message);
					} else {
						$(".response-data textarea").html(JSON.stringify(json.payload, null, '\t'));
					}
					if (formObj.attr("callback")) {
						var callback = callbacks[formObj.attr("callback")];
						if (callback) {
							callback(json);
						}
					}
				} else {
					alert('status: ' + statusText + '\n\nresponseText: \n' + responseText + '\n\nThe output div should have already been updated with the responseText.');
				}
			},
			complete: function(xhr) {
				console.info("Upload response: " + xhr.responseText);
			}
		});

	});

	$('.service-form').each(function() {
		if ($(this).hasClass('binded')) {
			return;
		} else {

			var that = $(this);
			that.addClass('binded');

			that.find('input[name=mode]').click(function() {
				that.find('.auth-mode').hide();
				that.find('.auth-mode.' + $(this).val()).show();
			});
			that.find('input[name=mode]').eq(0).click();
		}

		$(this).submit(function() {
			try {
				var d = data.synchronizeData;
				var formObj = $(this), url = this.action, method = this.method;
				var formData = formObj.serializeObject();
				if (!that.hasClass("payload-data")) {
					d.payload = formData;
					if (console) {
						console.info("Request payload:\n" + JSON.stringify(formData));
					}
				} else {
					var payl = {};
					for(var key in formData) {
						try {
							payl[key] = JSON.parse(formData[key]);
						} catch(e) {
							payl[key] = formData[key];
						}
					}
					d.payload = payl;
				}
				$(".request-url").html("URI: " + url.substring(url.indexOf('service') + 7));
				$(".request-data textarea").html(JSON.stringify(d, null, '\t'));
				$(".response-status input").val("loading...");
				$(".response-data textarea").html("loading...");
				$.ajax({
					contentType: 'application/json',
					dataType: 'json',
					data: JSON.stringify(d),
					success: function(json) {
						var jsonString = JSON.stringify(json);
						if (console)
							console.info("response payload: \n" + jsonString);
						$(".response-status input").val(json.state);
						if (json.state != 'Successful') {
							$(".response-data textarea").html(JSON.stringify(json, null, '\t'));
						} else {
							$(".response-data textarea").html(JSON.stringify(json, null, '\t'));
						}
						if (formObj.attr("callback")) {
							var callback = callbacks[formObj.attr("callback")];
							if (callback) {
								callback(json);
							}
						}
					},
					error: function() {
						alert("Error Service!");
					},
					type: method,
					url: url
				});
			} catch (e) {
				console.info(e);
			}
			return false;
		});
	});
	if (typeof window.bindEncForm == 'function') {
		bindEncForm();
	}
}
$(function() {
	$("#tabs").tabs({
		cache: true
	});
	$(window).resize(function() {
		var leftHeight = $(window).height() - 150;
		$("#tabs > div").css("height", leftHeight);

		$(".watching textarea").css("height", Math.round(leftHeight * 0.4));
	});
	$(window).resize();
});

function changeIndex(selector) {
	$(selector).each(function(idx) {
		console.info(idx);

		$(this).find('input').each(function() {
			var item = $(this);
			var n = item.attr("name");
			if (n.indexOf("[") != -1) {
				var nn = n.substring(0, n.indexOf("[") + 1) + (idx) + n.substring(n.indexOf("]"));
				item.attr("name", nn);
				console.info(nn)
			}
		})

	})
}