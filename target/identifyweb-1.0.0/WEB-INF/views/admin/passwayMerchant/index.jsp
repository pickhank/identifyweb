<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${not empty error}">
<script>
$(function(){
	$.messager.alert('错误','${error}');
});
</script>
</c:if>
<form class="search-form" action="<c:url value="/admin/passwayMerchant/index" />" method="post">
	<div class="ms-col-fix-100 clear">
		<label for="channel">通道：</label>
		<utils:combo mode="sel2" name="channel" cssName="input-box search-input-box" value="${search.channel}"></utils:combo>
	</div>
	<div class="ms-col-fix-300 clear">
		<label for="keywords">过滤条件：</label>
		<input type="text" class="input-box search-input-box" style="width: 200px;" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="space"></div>
	<input type="submit" value="搜索" class="btn"></input>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>通道</td>
		<td>通道商编</td>
		<td>商户名称</td>
		<td>开户姓名</td>
		<td>开户账户</td>
		<td>银行名称</td>
		<td>结算费率</td>
		<td>创建时间</td>
		<td>报备状态</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td>
				<utils:combo mode="lbl" value="${item.channel}" />
			</td>
			<td>
				<c:out value="${item.passwayMercNo}" />
			</td>
			<td>
				<c:out value="${item.mercName}" />
			</td>
			<td>
				<c:out value="${item.settleCardName}" />
			</td>
			<td>
				<c:out value="${item.settleCardNo}" />
			</td>
			<td>
				<c:out value="${item.settleCardBankNm}" />
			</td>
			<td>
				<c:out value="${item.feeRatio}" /> \ <c:out value="${item.feeAmt}" />
			</td>
			<td>
				<%-- <c:out value="${item.createDate }" /> --%>
				<fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd HH:mm:ss"/>
			</td>
			<td>
				<c:if test="${item.mercStatus eq '0'}" >未报备</c:if>
				<c:if test="${item.mercStatus eq '1'}" >已报备</c:if>
				<c:if test="${item.mercStatus eq '2'}" >未开通</c:if>
			</td>
			<td>
				<c:if test="${not empty item.mercStatus and item.mercStatus ne '0'}" >
					<a href="<c:url value="/admin/passwayMerchant/updateAcct?id=${item.id}" />" class="choose-link operate-detail">变更信息</a>
				</c:if>
				<a href="<c:url value="/admin/passwayMerchant/delete/${item.id}" />" class="choose-link operate-delete" >删除</a>
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="11">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>