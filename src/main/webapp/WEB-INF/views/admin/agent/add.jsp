<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<style>
.input-box-short {
	width:43%;
}
.liner-box-left {
	padding: 2px 10px;
}
.liner-box-right {
	padding: 0;
}
</style>
<form action="<c:url value="/admin/agent/add" />" method="post">
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
					<select name="username" class="input-box">
						<option value="">请选择--
						<c:forEach var="item" items="${downagents}" varStatus="status">
							<option value="${item.agentNo}">${item.agentNo}&nbsp;&nbsp;${item.name}&nbsp;&nbsp;${item.phone}
						</c:forEach>
					</select>
				</c:if>
				<c:if test="${not empty item.id}">
					<input type="text" class="input-box" placeholder="登录名" name="username" value="${item.username}" readonly/>
				</c:if>
				
			</td>
		</tr>
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