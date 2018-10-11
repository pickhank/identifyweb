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





var encData = {
		synchronizeData : {
			payload: null,
			userName: "D0000",
		},
		versionNo: 1,
		reqTime: "0",
		authKey: getSignString("000000")
	}; 

	function bindEncForm() {
		$('.enc-service-form').each(function() {
			if($(this).hasClass('enc-binded')){
				return ;
			} else {
				var that = $(this);
				that.addClass('enc-binded');
			}
			$(this).submit(function() {
				try{
					var now = new Date();
					var d = encData.synchronizeData;
					d.reqTime = now.getTime() + "";
					
					var formObj = $(this), url = this.action, method = this.method;
					
					formObj.find('.required-md5').each(function() {
						var md5val = getSignString($(this).val());
						console.info(md5val);
						$($(this).attr('ref-selector')).each(function() {
							var hidden = $(this);
							if(hidden.hasClass('md5')) {
								hidden.val(md5val);
							}
							else {
								hidden.val(getSignString(md5val + d.reqTime));
							}
						});
					});
					
					var formData = formObj.serializeObject();
					formData = formData || {};
					d.payload = formData;
					d.payload.userName = d.userName;
					d.payload.versionNo = encData.versionNo;
					
					formObj.find('textarea[ref-json]').each(function() {
						var val = $(this).val();
						d.payload[$(this).attr('ref-json')] = JSON.parse(val);
					});
					
					var key = encData.authKey;
					if(formObj.attr('ref-auth-key')) {
						key = formObj.find(formObj.attr('ref-auth-key')).val();
						
						console.info("req_key=" + key);
					}
					else {
						console.info("req_key=" + key);
					}
					
					var rkey = getSignString(key + d.reqTime);
					console.info("req_real_key=" + rkey);
					
					var signKey= rkey.substring(0, 16);
					var encKey = rkey.substring(16);
					$(".request-url").html("URI: " + url.substring(url.indexOf('service') + 7));
					$(".request-data textarea").html(JSON.stringify(d, null, '  '));
					$(".response-status input").val("loading...");
					$(".response-data textarea").html("loading...");
					d.payload = getAesString(JSON.stringify(d.payload), encKey);
					//d.payload = JSON.stringify(d.payload);
					d.sign = getSignString(d.payload + signKey);
					
					$.ajax({
						contentType: 'application/json',
						dataType: 'json',
						data: JSON.stringify(d),
						success: function(json){
							var jsonString = JSON.stringify(json);
							$(".response-status input").val(json.state);
							if(json.state != 'Successful') {
								$(".response-data textarea").html(JSON.stringify(json, null, '  '));
							} else {
								//$(".response-data textarea").html(JSON.stringify(JSON.parse(json.payload), null, '  '));
								var respTime = json.respTime;
								console.log("resp_key=" + key + ", time=" + respTime);
								var rkey = getSignString(key + respTime);
								console.log("resp_real_key=" + rkey);
								var signKey= rkey.substring(0, 16);
								var encKey = rkey.substring(16);

								console.log("resp_real_sign_key=" + signKey);
								console.log("resp_real_enc_key=" + encKey);
								console.log("resp_enc_payload=" + json.payload);
								console.log("resp_sign_data=" + json.sign);
								
								var resSign = getSignString(json.payload + signKey);
								if (resSign == json.sign) {
									var dec = decodeAesString(json.payload, encKey);
									var decodedJson = JSON.parse(dec);
									$(".response-data textarea").html(JSON.stringify(decodedJson, null, '  '));
									if(formObj.attr("ref-auth-key")) {
										encData.authKey = decodedJson.authKey;
									}
								} else {
									$(".response-data textarea").html("Sign check error");
								}
							}
						},
						error: function(){
							alert("Error Service!");
						},
						type: method,
						url: url
					});
				}
				catch(e) {
					console.log(e);
				}
				return false;
			});
		});
	}

	function getAesString(data, key) {//加密  
		var iv = "0102030405060708";
		var key = CryptoJS.enc.Latin1.parse(key);
		var iv = CryptoJS.enc.Latin1.parse(iv);
		var srcs = CryptoJS.enc.Utf8.parse(data);

		var encrypted = CryptoJS.AES.encrypt(srcs, key, {
			iv : iv,
			mode : CryptoJS.mode.CBC,
			padding : CryptoJS.pad.Pkcs7,
			formatter: CryptoJS.enc.Base64
		});
		return encrypted.toString();
	}

	function decodeAesString(data, key) {//加密  
		var iv = "0102030405060708";
		var key = CryptoJS.enc.Latin1.parse(key);
		var iv = CryptoJS.enc.Latin1.parse(iv);

		var decoded = CryptoJS.AES.decrypt(data, key, {
			iv : iv,
			mode : CryptoJS.mode.CBC,
			padding : CryptoJS.pad.Pkcs7
		});
		return decoded.toString(CryptoJS.enc.Utf8);
	}

	function getSignString(data) {//加密  
		var srcs = CryptoJS.enc.Utf8.parse(data);
		var encrypted = CryptoJS.MD5(srcs);
		return encrypted.toString().toUpperCase();
	}