(function($) {
  var oldAjax = $.ajax;
  var baseUrl = "/"; 
  $(function() {
	 baseUrl = $('#glabolBaseUrl').val();  
  });
  var getAbsoluteUrl = function(url) {
	  if(url.indexOf('/') == 0) {
		  url = url.substring(1);
	  }
	  return baseUrl + url;
  };
  var handlerAccessDenied = function(reps, param2, jqXHR) {
	  if(jqXHR.getResponseHeader('accessDenied')) {
		  setTimeout(function() {
			  $.colorbox.close();
		  }, 0);
		  setTimeout(function() {
			  if(confirm("该功能需登录后使用，现在开始登录?")) {
				  var errorResp = jQuery.type(reps) === "string" ? JSON.parse(reps) : reps;
				  window.location.href = getAbsoluteUrl(errorResp.payload) + "?returnUrl=" + encodeURIComponent($(location).attr('href'));
			  }
		  }, 0);
		  return true;
	  }
	  return false;
  };
  var handlerErrorMsg = function(reps, param2, jqXHR) {
	  var flag = false;
	  try {
		  var errorResp = jQuery.type(reps) === "string" ? JSON.parse(reps) : reps;
		  if(errorResp.state == 'Failed') {
			  alert("服务器连接错误，请重试！");
			  flag = true;
		  }
		  else if(errorResp.state == 'CustomMsg') {
			  alert(errorResp.message);
			  flag = true;
		  }
	  }catch(e) {
	  }
	  return flag;
  };
  
  $.ajaxSetup({
	method: 'GET',
    headers: {
      "requestType": "ajax"
    },
    dataType: 'html',
    showMask: true,
    error: function(jqXHR, textStatus, errorThrown) {
        $('#_shadow_bg').hide();
        if(console && console.info) {
            console.info("unexpected exception-------------------Start");
            console.info(jqXHR);
            console.info(textStatus);
            console.info(errorThrown);
            console.info("unexpected exception--------------------End");
        }
        alert("服务器连接错误，请重试！");
     }
  });
  
  $.wooajax = $.ajax = function() {
	  if(arguments && arguments.length == 1 && jQuery.type(arguments[0]) === 'object') {
		    var dataType = null;
			if(arguments[0].dataType) {
				if(!arguments[0].headers) {
					arguments[0].headers = {};
				}
				arguments[0].headers.requestDataType = arguments[0].dataType;
				dataType = arguments[0].dataType;
			}
		    var successCallback = arguments[0].success;
		    arguments[0].success = function(resp, param2, jqXHR) {
			    $('#_shadow_bg').hide();
			    if(!handlerAccessDenied(resp, param2, jqXHR) && successCallback) {
			    	 if('json' == dataType && handlerErrorMsg(resp)) {
			    		 return;
			    	 }
		    		 successCallback(resp, param2, jqXHR);
			    	 
		    	}
			};
			if(arguments[0].showMask) {
			    $('#_shadow_bg').css('top', document.body.scrollTop+"px").show();
			}
	  }
	  return oldAjax.apply($, arguments);
  };
})(jQuery);

Date.prototype.format = function(format) //author: meizz 
{ 
  var o = { 
    "M+" : this.getMonth()+1, //month 
    "d+" : this.getDate(),    //day 
    "h+" : this.getHours(),   //hour 
    "m+" : this.getMinutes(), //minute 
    "s+" : this.getSeconds(), //second 
    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter 
    "S" : this.getMilliseconds() //millisecond 
  };
  if(/(y+)/.test(format)) format=format.replace(RegExp.$1, 
    (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
  for(var k in o)if(new RegExp("("+ k +")").test(format)) 
    format = format.replace(RegExp.$1, 
      RegExp.$1.length==1 ? o[k] : 
        ("00"+ o[k]).substr((""+ o[k]).length)); 
  return format; 
};

$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a,
			function() {

				var key = this.name, obj = o;

				var selectorName = this.name.replace(/\./g, '\\\\.');
				var keys = key.split("."), len = keys.length, rel = $("input[name='" + selectorName + "'], select[name='" + selectorName + "'], textarea[name='" + selectorName + "']").attr("rel");

				var val = this.value;
				if (rel) {
					if (rel == 'float') {
						val = parseFloat(val);
					} else if (rel == 'bool') {
						val = val === 'true';
					}
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
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};
function isEmpty(obj) {
    for(var prop in obj) {
        if(obj.hasOwnProperty(prop))
            return false;
    }

    return true;
}

