<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<c:if test="${not empty errorMsg}">
<script>
$(function(){
	$.messager.alert('错误','${errorMsg}');
});
</script>
</c:if>
<form class="search-form" action="<c:url value="/content/downMerchant/index" />" method="post">
	<div class="search-group">
		<label for="keywords">关键字：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input>
	<input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/content/downMerchant/add" />" class="btn btn-orange pull-right operate-detail" >新建</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>类别</td>
		<td>机构编号</td>
		<td>姓名</td>
		<td>电话</td>
		<td>结算卡卡号</td>
		<td>结算卡户名</td>
		<td>结算银行名称</td>
		<td>代理商ID</td>
		<td>加密Key</td>
		<td>签名Key</td>
		<td>代付KEY</td>
		<td>信任IP</td>
		<td>默认代付费率，单位：千分之</td>
		<td>默认单笔代付加收费，单位：元</td>
		<td>通知消息</td>
		<td>开启资金明细</td>
		<td>状态</td>
		<td>T0授信资金比，单位：百分之</td>
		<td>下一笔结算日期(由系统自动维护)</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="itemStatus" >
		<tr class="list-item">
			<td>
				<c:out value="${item.mercCategory}" />
			</td>
			<td>
				<c:out value="${item.mchNo}" />
			</td>
			<td>
				<c:out value="${item.name}" />
			</td>
			<td>
				<c:out value="${item.phone}" />
			</td>
			<td>
				<c:out value="${item.accCardNo}" />
			</td>
			<td>
				<c:out value="${item.accCardHolder}" />
			</td>
			<td>
				<c:out value="${item.accBankName}" />
			</td>
			<td>
				<c:out value="${item.agentId}" />
			</td>
			<td>
				<c:out value="${item.encKey}" />
			</td>
			<td>
				<c:out value="${item.signKey}" />
			</td>
			<td>
				<c:out value="${item.payKey}" />
			</td>
			<td>
				<c:out value="${item.limitIps}" />
			</td>
			<td>
				<c:out value="${item.drawFeeRatio}" />
			</td>
			<td>
				<c:out value="${item.addDrawFeeAmt}" />
			</td>
			<td>
				<c:out value="${item.enableNotify}" />
			</td>
			<td>
				<c:out value="${item.enableBalChgFlg}" />
			</td>
			<td>
				<c:out value="${item.status}" />
			</td>
			<td>
				<c:out value="${item.creditRatio}" />
			</td>
			<td>
				<c:out value="${item.settleDate}" />
			</td>
			<td>
				<a href="<c:url value="/content/downMerchant/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<!-- a href="<c:url value="/content/downMerchant/delete/${item.id}" />" class="operate-delete">删除</a-->
			</td>
		</tr>
	</c:forEach>
</table>
<utils:pager/>
