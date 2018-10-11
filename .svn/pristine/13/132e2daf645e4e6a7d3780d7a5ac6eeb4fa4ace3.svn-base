<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>Welcome to Use API test Page</title>
	
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	
	<link rel="stylesheet"  href="<spring:url value="/resources/css/black-tie/jquery.ui.all.css"></spring:url>">
	<link rel="stylesheet"  href="<spring:url value="/resources/css/ui-lightness/jquery-ui-sub-tabs-smoothness.css"></spring:url>">
	<link rel="stylesheet"  href="<spring:url value="/resources/css/ui-lightness/jq-new.css"></spring:url>">
	<link rel="stylesheet"  href="<spring:url value="/resources/css/colorbox/colorbox.css"></spring:url>">
	<script type="text/javascript" src="<spring:url value="/resources/js/jquery/jquery-1.11.1.min.js"></spring:url>"></script>
	<script type="text/javascript" src="<spring:url value="/resources/js/jquery/jquery-ui-1.9.1.custom.min.js"></spring:url>"></script>
	<script type="text/javascript" src="<spring:url value="/resources/js/jquery/jquery.colorbox.js"></spring:url>"></script>
    <script type="text/javascript" src="<spring:url value="/resources/js/jquery/jquery.tmpl.min.js"></spring:url>"></script>
	<script type="text/javascript" src="<spring:url value="/resources/js/jquery/jquery.form.js"></spring:url>"></script>
	<script type="text/javascript" src="<spring:url value="/resources/js/common/crypto-js.js"></spring:url>"></script>
	<script type="text/javascript" src="<spring:url value="/resources/js/test/common.js"></spring:url>?1.1"></script>
	<script type="text/javascript" src="<spring:url value="/resources/js/test/enc.js"></spring:url>?1.1"></script>
</head>
<body>
	<div class="container">  
		<div id="tabs" style="width: 48%; margin-left: 1%; float: left; height: 98%; overflow: hidden;">
		    <ul>
		        <li><a href="<c:url value='/api/test/dsp/index' />">验签接口</a></li>
		    </ul>
		    <div id="tabs-setting" class="area-input" >
		    	
		   	</div>
		</div>
		<div class="watching"  style="width: 48%; margin-left: 1%; float: left; height: 100%; BACKGROUND: #DDD; overflow-y: auto;">
			<div class="request-url" style="font-weight: bold; text-align: LEFT; PADDING: 5PX 0 5px 10PX;">URI: &nbsp;</div>
			<div class="request-data" STYLE="font-weight: bold; text-align: LEFT; PADDING: 5PX 0 0 10PX; height">Request Data<BR><textarea STYLE="WIDTH: 80%;  height:40%;" id=""></textarea></div>
			<div class="response-status" STYLE="font-weight: bold; text-align: LEFT; PADDING: 5PX 0 0 10PX;">Response Status<BR><input type="text" /></div>
			<div class="response-data" STYLE="font-weight: bold; text-align: LEFT; PADDING: 5PX 0 0 10PX;">Response Data<BR> 
				<textarea id="outputAreaId" STYLE="WIDTH: 80%;  height:40%" ></textarea>
			</div>
		</div>
	</div>
	
	
	<style type="text/css">
		.watching {
			overflow: hidden;
		}
		.watching > div {
			float: left;
			width: 100%;
		}
		.border {
			border: 1px solid #000;
		}
		#tabs > div {
			overflow-y: auto;
		}
		#tabs table.editor {
			width: 98%;
		}
		.editor input.button {
			padding: 8px 10px;
		}
		.editor td {
			padding: 8px 0 8px 10px;
		}
		.editor  .tmpl-tab td {
			padding: 2px 0 2px 5px;
		}
		.editor td.label{
			font-weight: bold;
			text-align: right;
			min-width: 100px;
			width: 200px;
			vertical-align: top;
		}
		.editor td.field p {
			font-size: 12px;
			color: #aaa;
			padding-top: 0px;
			padding-left: 0px;
			margin-top: 5px;
		}
		.editor td.field input {
			width: 90%;
		}
		fieldset {
		    padding:10px;
		    margin:10px;
		    color:#333; 
		    border:#06c dashed 1px;
		} 
		legend {
		    color:#06c;
		    font-weight:800; 
		    background:#fff;
		} 
		p {
			display: inline;
			font-size: 14px;
			padding-left: 10px;
		}
	</style>
</body>
</html>