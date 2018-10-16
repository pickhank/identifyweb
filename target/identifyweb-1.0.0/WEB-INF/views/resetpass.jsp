<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %><!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Type your new password for Smallisto</title>
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
	<link rel="stylesheet" href="<c:url value='/resources/_assets/jqm-default.css' />">
	<link rel="shortcut icon" href="favicon.ico">
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
	<script src="<c:url value='/resources/_assets/jquery.validate.min.js' />"></script>
	<style>
	label.error {
		color: red;
		font-size: 16px;
		font-weight: normal;
		line-height: 1.4;
		margin-top: 0.5em;
		width: 100%;
		float: none;
	}
	@media screen and (orientation: portrait) {
		label.error {
			margin-left: 0;
			display: block;
		}
	}
	@media screen and (orientation: landscape) {
		label.error {
			display: inline-block;
			margin-left: 22%;
		}
	}
	em {
		color: red;
		font-weight: bold;
		padding-right: .25em;
	}
	</style>
</head>
<body>
<div data-role="page" class="jqm-demos" data-quicklinks="true">

	<div data-role="header" class="jqm-header">
		<h2><a href="<c:url value='/' />" title="Smallisto home"><img src="#" alt="Smallisto"></a></h2>
		<p><span class="jqm-version"></span> House Trade</p>
	</div><!-- /header -->

	<form method="POST" >
    <div role="main" class="ui-content jqm-content">
        <h2>Create New password</h2>

		<p>Welcome <c:if test="${not empty item.firstName }">${item.firstName }</c:if>
			<c:if test="${empty item.firstName }">${item.email }</c:if>,
			please type your new password in below</p>

        <div data-demo-html="true">
             <label for="password">Password:</label>
             <input type="password" name="password" id="password" value="" maxlength="20" autocomplete="off">
        </div><!-- /demo-html -->
        
        <div data-demo-html="true">
             <label for="password">Repeat Password:</label>
             <input type="password" name="repassword" id="repassword" value="" maxlength="20" autocomplete="off">
        </div><!-- /demo-html -->
        
		<button type="submit" name="submit" value="Update Password" data-theme="b">Update Password</button>
    </div>
    </form>
</div>
<script>
$( ".jqm-demos" ).on("pageinit", function() {
	$( "form").validate({
		rules: {
			password: {
				required: true
			},
			repassword: {
				required: true,
				equalTo: "#password"
			}
		},
		errorPlacement: function( error, element ) {
			error.insertAfter( element.parent() );
		}
	});
});
</script>
</body>
</html>