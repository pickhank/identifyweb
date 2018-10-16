<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>

<form method="post" enctype="multipart/form-data" >
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">录入人:</td>
			<td class="liner-box-right">
				<input class="input-box" readonly="readonly" type="text" value="${item.createName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">机构:</td>
			<td class="liner-box-right">
				<input class="input-box" readonly="readonly" type="text" value="${item.mchName}(${item.mchNo})">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">充值金额（元）:</td>
			<td class="liner-box-right">
				<input class="input-box" readonly="readonly" type="text" value="${item.rearMoneyYuan }">
				
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">备注</td>
			<td class="liner-box-right">
				<textarea readonly="readonly" class="input-box" style="height: 180px;" name="feeText">${item.remark}</textarea>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">状态</td>
			<td class="liner-box-right">
				<c:choose>
					<c:when test="${(item.status) == 0}">待审核</c:when>
					<c:when test="${(item.status) == 1}"><label style='color:blue'>审核成功</label></c:when>
					<c:when test="${(item.status) == 2}"><label style='color:red'>审核拒绝</label></c:when>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">审核时间:</td>
			<td class="liner-box-right">
				<div class="ms-col-fix-100 clear">
					<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="yyyy/MM/dd" value="<utils:dateFormat value='${item.auditDate}' />"></input>
				</div>
			</td>
		</tr>
	</table>
</form>
