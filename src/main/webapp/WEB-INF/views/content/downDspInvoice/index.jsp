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
<form class="search-form" action="<c:url value="/content/downDspInvoice/index" />" method="post">
	<div class="search-group">
		<label for="keywords">关键字：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input>
	<input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/content/downDspInvoice/add" />" class="btn btn-orange pull-right operate-detail" >新建</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>接口版本号</td>
		<td>鉴权类型</td>
		<td>商户号 *</td>
		<td>商户名 *</td>
		<td>平台流水号</td>
		<td>平台日期yyyyMMdd</td>
		<td>下游交易流水号</td>
		<td>订单时间, 格式：yyyyMMddHHmmss</td>
		<td>异步通知地址</td>
		<td>代理商 *</td>
		<td>通道标识</td>
		<td>通道账户编号</td>
		<td>通道账户名称</td>
		<td>单笔费，单位：分</td>
		<td>卡姓名</td>
		<td>手机号</td>
		<td>证件号</td>
		<td>卡号</td>
		<td>卡CVV</td>
		<td>付款卡-卡有效期-MMYY</td>
		<td>商户交易手续费</td>
		<td>渠道交易手续费</td>
		<td>代理商利润</td>
		<td>平台利润</td>
		<td>平台利润-额外</td>
		<td>已提交上游</td>
		<td>上游流水号</td>
		<td>交易状态, 00=处理成功，计费、01=处理中、02=处理失败，不计费</td>
		<td>交易状态描述</td>
		<td>状态更新时间</td>
		<td>验证结果, 00=验证成功、02=验证失败</td>
		<td>验证结果, 00=验证成功、02=验证失败</td>
		<td>保留缺省域1</td>
		<td>保留缺省域2</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="itemStatus" >
		<tr class="list-item">
			<td>
				<c:out value="${item.versionNo}" />
			</td>
			<td>
				<c:out value="${item.dspMode}" />
			</td>
			<td>
				<c:out value="${item.mchNo}" />
			</td>
			<td>
				<c:out value="${item.mchName}" />
			</td>
			<td>
				<c:out value="${item.transNo}" />
			</td>
			<td>
				<c:out value="${item.transDate}" />
			</td>
			<td>
				<c:out value="${item.tradeNo}" />
			</td>
			<td>
				<c:out value="${item.orderDate}" />
			</td>
			<td>
				<c:out value="${item.notifyUrl}" />
			</td>
			<td>
				<c:out value="${item.agentNo}" />
			</td>
			<td>
				<c:out value="${item.channel}" />
			</td>
			<td>
				<c:out value="${item.channelAccNo}" />
			</td>
			<td>
				<c:out value="${item.channelAccName}" />
			</td>
			<td>
				<c:out value="${item.addFeeAmt}" />
			</td>
			<td>
				<c:out value="${item.holderName}" />
			</td>
			<td>
				<c:out value="${item.mobile}" />
			</td>
			<td>
				<c:out value="${item.idCard}" />
			</td>
			<td>
				<c:out value="${item.cardNo}" />
			</td>
			<td>
				<c:out value="${item.cardCvn}" />
			</td>
			<td>
				<c:out value="${item.cardExpDate}" />
			</td>
			<td>
				<c:out value="${item.mchPayFee}" />
			</td>
			<td>
				<c:out value="${item.channelPayFee}" />
			</td>
			<td>
				<c:out value="${item.agentProfit}" />
			</td>
			<td>
				<c:out value="${item.profit}" />
			</td>
			<td>
				<c:out value="${item.xtraProfit}" />
			</td>
			<td>
				<c:out value="${item.sentUp}" />
			</td>
			<td>
				<c:out value="${item.upTransNo}" />
			</td>
			<td>
				<c:out value="${item.status}" />
			</td>
			<td>
				<c:out value="${item.statusDesc}" />
			</td>
			<td>
				<c:out value="${item.statusChgDate}" />
			</td>
			<td>
				<c:out value="${item.verifyStatus}" />
			</td>
			<td>
				<c:out value="${item.verifyStatusDesc}" />
			</td>
			<td>
				<c:out value="${item.resv1}" />
			</td>
			<td>
				<c:out value="${item.resv2}" />
			</td>
			<td>
				<a href="<c:url value="/content/downDspInvoice/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<!-- a href="<c:url value="/content/downDspInvoice/delete/${item.id}" />" class="operate-delete">删除</a-->
			</td>
		</tr>
	</c:forEach>
</table>
<utils:pager/>
