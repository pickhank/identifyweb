parent.__WOO_CACHE = parent.__WOO_CACHE || {};
var _WOO = {};
// Common
_WOO.c = {
	alertInfo: function(title) {
		top.$.messager.alert({
			title: "操作结果",
			msg: title,
			icon: "info",
			top: 100,
			closable: false,
			onClose: function() {
				if (parent.Popup) {
					parent.Popup.hide();
				}
			}
		});
	},
	alertError: function(title) {
		top.$.messager.alert("操作结果", title, "error");
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
			ignoreCommon: options.ignoreCommon,
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
				if (options.ignoreCommon || data.state === 'Successful') {
					deferred.resolveWith(deferred, [ data, textStatus, jqXHR ]);
				} else {
					deferred.rejectWith(deferred, [ data.message || options.errorMsg, textStatus, jqXHR, data ]);
				}
			}
		});
		return deferred.promise();
	},
	errorHandler: function(errorMsg, textStatus, jqXHR, data) {
		top.$.messager.alert('错误', errorMsg || '操作失败!', 'error');
	},
	setCache: function(key, value) {
		parent.__WOO_CACHE[key] = value;
	},
	getCache: function(key) {
		return parent.__WOO_CACHE[key];
	}
};

$(function() {
	$("body").delegate(".operate-delete", "click", function(ev) {
		ev.preventDefault();
		var $this = $(this);
		top.$.messager.confirm('系统提示', '确认删除此条记录？', function(r) {
			if (r) {
				_WOO.c.send({
					url: $this.attr('href'),
					success: function() {
						var trObj = $this.closest('tr');
						var labalbox = $this.parent();
						if (labalbox.is('.labal-box')) {
							labalbox.fadeOut('fast', function() {
								labalbox.remove();
							});
						} else {
							trObj.fadeOut('fast', function() {
								trObj.remove();
							});
						}
					}
				});
			}
		});
	});
	
	$(".search-form input[type=submit][value=搜索]").click(function(){
		$(this).closest(".search-form").find("input[name=pageIndex]").val("0");
	});

	$("body").delegate(".operate-check", "click", function(ev) {
		ev.preventDefault();
		var $this = $(this);
		_WOO.c.send({
			url: $this.attr('href'),
			success: function() {
				$(".search-form").submit();
			}
		});
	});
	$("body").delegate(".operate-detail", "click", function(ev) {
		console.log("111");
		ev.preventDefault();
		console.log(parent.Popup);
		if (parent.Popup) {
			debugger;
			var $this = $(this);
			var width = $this.attr('ref-width');
			parent.Popup.show($this.attr("href"), $this.attr("title"), width).always(function() {
				$(".search-form").submit();
			});
		}
		return false;
	});
	
	$("body").delegate(".operate-detail-check", "click", function(ev) {
		ev.preventDefault();
		var $this = $(this);
		_WOO.c.send({
			url: $this.attr('href'),
			success: function() {
				window.location.reload();
			}
		});
	});
	
	$("body").delegate(".operate-audit", "click", function(ev) {
		ev.preventDefault();
		var that = $(this);
		
		if (!that.attr("confirm-message") || confirm(that.attr("confirm-message"))) {
			_WOO.c.send({
				url: that.attr('href'),
				type: that.attr('method'),
				ignoreCommon: true,
				success: function(json) {
					that.closest("td").html(json.message);
				}
			});
		}
		return false;
	});
	

	$(".detail-form").submit(function(ev) {
		if (!$(this).form("validate")) {
			ev.preventDefault();
		}
	});

	$("body").delegate(".operate-choose", "click", function(ev) {
		ev.preventDefault();
		if (parent.PopupChoose) {
			var $this = $(this);
			parent.PopupChoose.hide($this.data("item"));
		}
		return false;
	});

	$("body").delegate(".operate-chooses", "click", function(ev) {
		ev.preventDefault();
		if (parent.PopupChoose) {
			var items = [];
			$(".choose-checkbox:checked").each(function() {
				var item = $(this).val();
				if (typeof item === 'string') {
					item = JSON.parse(item);
				}
				items.push(item);
			});
			parent.PopupChoose.hide(items);
		}
		return false;
	});

	$("body").delegate(".open-choose", "click", function(ev) {
		ev.preventDefault();
		if (parent.PopupChoose) {
			var $this = $(this);
			parent.PopupChoose.show($this.attr("href"), "选择").always(function(data) {
				var handler = $this.attr("choose-handle");
				console.log(handler, window[handler]);
				if (handler && typeof window[handler] === 'function') {
					window[handler](data);
				}
			});
		}
		return false;
	});
	
	$("body").delegate(".operate-pass","click",function(ev) {
		ev.preventDefault();
		var $this = $(this);
		top.$.messager.confirm('系统提示', '确认把此条通过？', function(r) {
			if (r) {
				_WOO.c.send({
					url: $this.attr('href'),
					success: function() {
						location.reload();
					}
				});
			};
		});
	});

});