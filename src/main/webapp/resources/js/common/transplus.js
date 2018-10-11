try {
	$(function() {
		function GetRequest() {
			var url = location.search; // 获取url中"?"符后的字串
			var theRequest = new Object();
			if (url && url.indexOf("?") != -1) {
				var str = url.substr(1);
				strs = str.split("&");
				for (var i = 0; i < strs.length; i++) {
					theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
				}
			}
			return theRequest;
		}   
		
		try {
			var flg = false;
			var formObj = $("#unionpayOnline form");
			var inputObj = null;
			if(formObj.length > 0) {
				inputObj = formObj.find("input[name=accNo]");
				if(inputObj.length > 0) {
					flg = true;
				}	
			}
			// var obj = GetRequest();
			var baseurl = window.location.href;
			
			if(flg && invId && baseurl.indexOf("/bas/FrontTrans") != -1) {
				var pdiv = inputObj.closest('div');
				inputObj.focus(function() {
					pdiv.find('#ddd').remove();
				});
				baseurl = baseurl.substring(0, baseurl.indexOf("/bas/FrontTrans"));
				formObj.submit(function() {
					pdiv.find('#ddd').remove();
					var ret = false;
					$.ajax({
						url: baseurl + "/dgateway/transmit/verify/" + invId,
						type: 'POST',
						data: {
							"accNo": formObj.find("input[name=accNo]").val()
						},
						async: false,
						dataType: 'json',
						timeout: 10000
					}).done(function(res){
						ret = true;
						if (res.state && res.message && res.state != 'Successful') {
							ret = false;
							pdiv.append("<label id='ddd' style='color: #ec0b0b;font-size: 13px;float: left;display: inline-block;'>" + res.message + "</label>");
						} 
					});
					return ret;
				});
			}
		}
		catch(e) {
		}
	});
}
catch(e) {
	
}