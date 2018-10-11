var encData = {
	synchronizeData : {
		payload: null,
		mchNo: "A0001",
	},
	versionNo: 1,
	encKey : "1234567812345678",
	signKey: "1234"
}; 

function bindEncForm() {
	$('.enc-bind-form').submit(function() {
		encData.synchronizeData.mchNo = $('#mchNo').val();
		encData.encKey = $('#encKey').val();
		encData.signKey = $('#signKey').val();
		encData.versionNo = $('#versionNo').val();
		return false;
	});
	$('.enc-service-form').each(function() {
		if($(this).hasClass('enc-binded')){
			return ;
		} else {
			var that = $(this);
			that.addClass('enc-binded');
		}
		$(this).submit(function() {
			try{
				var formObj = $(this), url = this.action, method = this.method;
				var formData = formObj.serializeObject();
				formData = formData || {};
				var d = encData.synchronizeData;
				d.payload = formData;
				d.payload.mchNo = d.mchNo;
				d.payload.versionNo = encData.versionNo;
				var encKey = encData.encKey;
				var signKey = encData.signKey;
				$(".request-url").html("URI: " + url.substring(url.indexOf('service') + 7));
				$(".request-data textarea").html(JSON.stringify(d, null, '  '));
				$(".response-status input").val("loading...");
				$(".response-data textarea").html("loading...");
				d.payload = getAesString(JSON.stringify(d.payload), encKey);
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
							var resSign = getSignString(json.payload + signKey);
							if (resSign == json.sign) {
								var dec = decodeAesString(json.payload, encKey);
								var decodedJson = JSON.parse(dec);
								$(".response-data textarea").html(JSON.stringify(decodedJson, null, '  '));
								if(formObj.attr("callback")) {
									var callback = callbacks[formObj.attr("callback")];
									if(callback) {
										callback(decodedJson);
									}
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