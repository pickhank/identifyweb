<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
String resourceUrl = com.woodare.framework.utils.SysProperties.getInstance().getProperty(com.woodare.template.constant.SystemPropertiesConstant.CODE_ROOT_RESOURCE_URL, "http://open.mishua.cn");%>

<!DOCTYPE html>
<html lang="en-US">
<head>
<script>
	if (parent != window) {
		parent.location.reload()
	}
</script>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="msapplication-tap-highlight" content="no" />
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Page-Enter" content="blendTrans(duration=1)" />
<meta http-equiv="Page-Exit" content="blendTrans(duration=1)" />
<meta name="format-detection" content="telephone=no" />
<meta name="format-detection" content="email=no" />
<meta name="format-detection" content="address=no" />
<meta charset="utf-8" />
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>业务管理平台</title>
<link rel="stylesheet" type="text/css" href="<%=resourceUrl%>/resources/login/style_log.css">
<link rel="stylesheet" type="text/css" href="<%=resourceUrl%>/resources/login/style.css">
<script type="text/javascript" src="<%=resourceUrl%>/resources/js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/login/particles.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/js/common/jquery.md5.js?1"></script>

<style>
#particles-js {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background: #445e84;
	z-index: -1;
}
.header {
    margin: auto;
    width: 280px;
}
.header h2{
	color:white;
	font-size:18px;
	
}
.txt_input{
	height: 20px;
	width: 260px;
	margin-top: 10px;
	margin-bottom: 10px;
	border-radius:2px;
	line-height: 15px;
	font-size:15px;
	padding:5px 10px;
	border-style:none;
}
input::-webkit-input-placeholder {
	/* placeholder字体大小  */
	font-size: 15px;
	padding:10px 0px;
	}
.input-box-code{
	width: 130px;
}
.input-box-image{
	height:30px;
	float: right;
	margin-top: 10px;
}

.rem_sub input.sub_button{
	background: rgba(255,255,255,0.8);
	height: 30px;
	font-size: 15px;
	width: 120px;
	border: 1px solid #FFF;
	border-radius: 2px;
	color: #107ace;
}

</style>
</head>
<body>
	<div id="particles-js"></div>
	<div class="header">
		<c:if test="${not empty errorMsg}">
			<div style="font-size: 30px; color: #ff3800; text-align: center; margin-top: 50px;">${errorMsg}</div>
		</c:if>
		<div class="login_logo" style="margin-top: 50px;">
			<h2 style="font-size:28px;">运营平台</h2>
			</div>
			<c:url value="/j_spring_security_check" var="loginurl" />
				<form method="post" name="login" action="${loginurl }" style="width:280px;">
				<h2>用户名</h2>
					<label>
						<input type="text" id="username" class="txt_input" placeholder="用户名" name="j_username" autocomplete="off">
					</label>
					<h2>密码</h2>
					<label>
						<input type="hidden" name="j_password" >
						<input type="password" placeholder="密码" id="j_password" autocomplete="off" class="txt_input">
					</label>
					<%-- <h2>验证码</h2>
					<label>
						<input type="text" class="txt_input input-box-code" placeholder="验证码" name="regcode" autocomplete="off" />
						<img src="<c:url value='/regcode/renew' />?<%=(new java.util.Date().getTime())%>" class="input-box-image" onclick="this.src+=1" />
					</label> --%>
					<div class="rem_sub" style="margin-top:10px;">
						<div class="rem_sub_l">
							<input type="checkbox" id="remember_me" name="_spring_security_remember_me" style="zoom:100%;">
							<span for="checkbox" style="font-size: 15px;color:#fff;">下次自动登录</span>
						</div>
						<label>
							<input type="submit" class="sub_button" name="button" id="button" value="登录" style="opacity: 0.7;" onclick="doLogin();">
						</label>
					</div>
				</form>
		
	</div>
	<script>
		function doLogin() {
			$('input[name="j_password"]').val($.dmd5($('#j_password').val()));
			return true;
		}
		particlesJS("particles-js", {
			"particles" : {
				"number" : {
					"value" : 40,
					"density" : {
						"enable" : true,
						"value_area" : 1000
					}
				},
				"color" : {
					"value" : "#ffffff"
				},
				"shape" : {
					"type" : "circle",
					"stroke" : {
						"width" : 0,
						"color" : "#000000"
					},
					"polygon" : {
						"nb_sides" : 5
					}
				},
				"opacity" : {
					"value" : 0.5,
					"random" : false,
					"anim" : {
						"enable" : false,
						"speed" : 1,
						"opacity_min" : 0.1,
						"sync" : false
					}
				},
				"size" : {
					"value" : 3,
					"random" : true,
					"anim" : {
						"enable" : false,
						"speed" : 40,
						"size_min" : 0.1,
						"sync" : false
					}
				},
				"line_linked" : {
					"enable" : true,
					"distance" : 150,
					"color" : "#ffffff",
					"opacity" : 0.4,
					"width" : 1
				},
				"move" : {
					"enable" : true,
					"speed" : 6,
					"direction" : "none",
					"random" : false,
					"straight" : false,
					"out_mode" : "out",
					"bounce" : false,
					"attract" : {
						"enable" : false,
						"rotateX" : 600,
						"rotateY" : 1200
					}
				}
			},
			"interactivity" : {
				"detect_on" : "canvas",
				"events" : {
					"onhover" : {
						"enable" : true,
						"mode" : "grab"
					},
					"onclick" : {
						"enable" : true,
						"mode" : "push"
					},
					"resize" : true
				},
				"modes" : {
					"grab" : {
						"distance" : 140,
						"line_linked" : {
							"opacity" : 1
						}
					},
					"bubble" : {
						"distance" : 400,
						"size" : 40,
						"duration" : 2,
						"opacity" : 8,
						"speed" : 3
					},
					"repulse" : {
						"distance" : 200,
						"duration" : 0.4
					},
					"push" : {
						"particles_nb" : 2
					},
					"remove" : {
						"particles_nb" : 2
					}
				}
			},
			"retina_detect" : true
		});
	</script>
</body>
</html>