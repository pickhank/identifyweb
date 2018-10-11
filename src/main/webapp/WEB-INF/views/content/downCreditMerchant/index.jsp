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
<form class="search-form" action="<c:url value="/content/downCreditMerchant/index" />" method="post">
	<div class="search-group">
		<label for="keywords">关键字：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input>
	<input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/content/downCreditMerchant/add" />" class="btn btn-orange pull-right operate-detail" >新建</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>通道</td>
		<td>通道账户名称</td>
		<td>通道账户编号</td>
		<td>本地商户唯一标识</td>
		<td>渠道下发的用户编号</td>
		<td>渠道下发的用户相关</td>
		<td>商户简称</td>
		<td>商户名称</td>
		<td>商户地址</td>
		<td>商户手机号码</td>
		<td>结算卡户名</td>
		<td>结算卡卡号</td>
		<td>结算卡手机号码</td>
		<td>结算卡身份证号</td>
		<td>结算卡银行名称</td>
		<td>结算卡联行号</td>
		<td>结算卡联行名称</td>
		<td>商户交易费率</td>
		<td>商户交易单笔手续费</td>
		<td>商户状态，0未提交、1报备成功、2待开通</td>
		<td>保留缺省域1</td>
		<td>保留缺省域2</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="itemStatus" >
		<tr class="list-item">
			<td>
				<c:out value="${item.channel}" />
			</td>
			<td>
				<c:out value="${item.channelAccName}" />
			</td>
			<td>
				<c:out value="${item.channelAccNo}" />
			</td>
			<td>
				<c:out value="${item.localeUsrNo}" />
			</td>
			<td>
				<c:out value="${item.passwayUsrNo}" />
			</td>
			<td>
				<c:out value="${item.passwayUsrRefer}" />
			</td>
			<td>
				<c:out value="${item.usrShortName}" />
			</td>
			<td>
				<c:out value="${item.usrName}" />
			</td>
			<td>
				<c:out value="${item.usrAddr}" />
			</td>
			<td>
				<c:out value="${item.usrMobile}" />
			</td>
			<td>
				<c:out value="${item.cardHolder}" />
			</td>
			<td>
				<c:out value="${item.cardNo}" />
			</td>
			<td>
				<c:out value="${item.cardMobile}" />
			</td>
			<td>
				<c:out value="${item.certId}" />
			</td>
			<td>
				<c:out value="${item.cardBankName}" />
			</td>
			<td>
				<c:out value="${item.bankUnionNo}" />
			</td>
			<td>
				<c:out value="${item.bankUnionName}" />
			</td>
			<td>
				<c:out value="${item.feeRatio}" />
			</td>
			<td>
				<c:out value="${item.feeAmt}" />
			</td>
			<td>
				<c:out value="${item.status}" />
			</td>
			<td>
				<c:out value="${item.merResv1}" />
			</td>
			<td>
				<c:out value="${item.merResv2}" />
			</td>
			<td>
				<a href="<c:url value="/content/downCreditMerchant/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<!-- a href="<c:url value="/content/downCreditMerchant/delete/${item.id}" />" class="operate-delete">删除</a-->
			</td>
		</tr>
	</c:forEach>
</table>
<utils:pager/>
