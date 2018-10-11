parent.__WOO_CACHE = parent.__WOO_CACHE || {};
var _WOO = {};
// Common
_WOO.c = {
	alertInfo: function(title) {
		/*
		 * $.messager.alert({ title: "操作结果", msg: title, icon: "info", top: 100, closable: false, onClose: function() { if (parent.Popup) { parent.Popup.hide(); } } });
		 */
	},
	alertError: function(title) {
		// $.messager.alert("操作结果", title, "error");
	},
	sendWithJSON: function(options) {
		if (typeof options.data !== 'string') {
			options.data = JSON.stringify(options.data);
		}
		options.headers = {
			'Content-Type': 'application/json'
		};
		return this.send(options);
	},
	/**
	 * options: { type : 'POST', url : {URL}, data : {data}, dataType : 'application/json', timeout : 3e4, msg : '加载时显示的提示语', errorMsg: "错误提示语", success: function() {}, error: function() {} }
	 */
	send: function(options) {
		var self = this;
		var deferred = new $.Deferred();
		var req = $.ajax({
			type: options.type || 'POST',
			url: options.url.replace(/\/+/g, "/"),
			data: options.data,
			dataType: options.dataType || 'json',
			timeout: options.timeout || 3e4,
			headers: options.headers || {},
			beforeSend: function() {
				if (!options.noLoading) {
				}
			}
		});
		deferred.fail(self.errorHandler).fail(options.error).done(options.success);
		req.fail(function(jqXHR, textStatus, errorThrown) {
			deferred.rejectWith(deferred, [ options.errorMsg, textStatus, jqXHR, errorThrown ]);
		}).done(function(data, textStatus, jqXHR) {
			if (options.dataType === 'html') {
				if (data) {
					deferred.resolveWith(deferred, [ data, textStatus, jqXHR ]);
				} else {
					deferred.rejectWith(deferred, [ options.errorMsg, textStatus, jqXHR, data ]);
				}
			} else {
				if (data.state === 'Successful') {
					deferred.resolveWith(deferred, [ data, textStatus, jqXHR ]);
				} else {
					deferred.rejectWith(deferred, [ data.message || options.errorMsg, textStatus, jqXHR, data ]);
				}
			}
		});
		return deferred.promise();
	},
	errorHandler: function(errorMsg, textStatus, jqXHR, data) {
	},
	setCache: function(key, value) {
		parent.__WOO_CACHE[key] = value;
	},
	getCache: function(key) {
		return parent.__WOO_CACHE[key];
	}
};

$(function() {
	$(".backToTop").goToTop();
	$(".backToTop").html('');
	$(window).bind('scroll resize', function() {
		$(".backToTop").goToTop();
	});

	$(".export-form").submit(function() {
		var params = $(".search-form").serializeArray();
		$(".export-form input[type='hidden']").remove();
		var $form = $(".export-form");
		$(params).each(function() {
			$form.append('<input type="hidden" name="' + this.name + '" value="' + this.value + '"> ');
		});
	});

	$(".operate-delete").on("click", function(ev) {
		ev.preventDefault();
		var $this = $(this);
		_WOO.c.send({
			url: $this.attr('href'),
			success: function() {
				var trObj = $this.closest('.weui-form-preview');
				$(trObj).hide('fast', function() {
					$(trObj).next().remove();
					$(trObj).remove();
				});
			}
		});
	});
	
	$("body").delegate(".operate-check", "click", function(ev) {
		ev.preventDefault();
		var $this = $(this);
		_WOO.c.send({
			url: $this.attr('href'),
			success: function(json) {
				$($this.attr("ref")).html(json.message);
			}
		});
	});
});