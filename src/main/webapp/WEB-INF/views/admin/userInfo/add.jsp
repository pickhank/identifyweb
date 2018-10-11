<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<link href="<c:url value='/resources/js/bootstrap/bootstrap-select.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/js/bootstrap/bootstrap.min.css' />" rel="stylesheet" type="text/css" />

<style>
.input-box-short {
	width:43%;
}
.liner-box-left {
	padding: 2px 10px;
}
.liner-box-right {
	padding: 2px 10px;
}
.input-box, .box-body label {
    color: #333;
    font-family: 'Microsoft YaHei', Arial, Helvetica, sans-serif;
    font-size: 0.9rem;
}
td {
	vertical-align: top;
}
.box-body .btn {
    padding: 5px 20px;
    text-align: center;
    cursor: pointer;
    display: inline-block;
    border-radius: 3px;
    font-size: 12px;
    height: 27px;
    box-sizing: border-box;
    vertical-align: middle;
}

</style>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap/bootstrap-select.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap/bootstrap.min.js' />"></script>

<form action="<c:url value="/admin/userInfo/add" />" method="post">
	<input type="hidden" name="id" value="${item.id}" />
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left">登录名:</td>
			<td class="liner-box-right">
				<c:if test="${empty item.id}">
					<select name="username" class="selectpicker bla bla bli" data-live-search='true' >
						<c:forEach var="item" items="${merchants }" varStatus="status">
							<option value="${item.mchNo}">
								<utils:merccate mode="lbl" value="${item.mercCategory}" />
								&nbsp;&nbsp;${item.mchNo}
								&nbsp;&nbsp;${item.name}
							</option>
						</c:forEach>
					</select>
					<script>
						$(function() {
							$('.selectpicker').selectpicker({
						        'selectedText': 'cat'
						    });
						});
					</script>
				</c:if>
				<c:if test="${not empty item.id}">
					<input type="text" class="input-box" placeholder="登录名" name="username" value="${item.username}" readonly/>
				</c:if>
				
			</td>
		</tr>
		<c:if test="${not empty item.id}">
		<tr>
			<td class="liner-box-left">商户名称:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="商户名称" name="name" value="${item.name}" readonly/>
			</td>
		</tr>
		</c:if>
		<tr>
			<td class="liner-box-left">邮箱:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="邮箱" name="email" value="${item.email}" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left">密码:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="密码" name="password" value="" />
				<br>不填时，继续使用原密码， 新建时，不填密码，默认值与登录名一致
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>