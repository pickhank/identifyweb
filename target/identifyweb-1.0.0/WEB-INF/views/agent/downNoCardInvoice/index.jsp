<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.custom-d-cate {
width: 90px;
}
</style>
<form class="search-form" action="<c:url value="/agent/downNoCardInvoice/index" />" method="post" style="overflow: hidden;">
	<div class="ms-col-fix-100 clear">
		<label for="transNo">平台流水号：</label>
		<input type="text" class="input-box search-input-box" placeholder="平台流水号" name="transNo" value="${search.transNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="tradeNo">机构流水号：</label>
		<input type="text" class="input-box search-input-box" placeholder="机构流水号" name="tradeNo" value="${search.tradeNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="status">交易状态：</label>
		<select name="status" class="input-box search-input-box">
			<option value="">全部</option>
			<option value="00" <c:if test="${search.status eq '00'}">selected</c:if>>成功</option>
			<option value="09" <c:if test="${search.status eq '09'}">selected</c:if>>下单</option>
			<option value="01" <c:if test="${search.status eq '01'}">selected</c:if>>交易中</option>
			<option value="02" <c:if test="${search.status eq '02'}">selected</c:if>>失败</option>
		</select>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="dateCate">订单日期：</label>
		<select name="dateCate" class="input-box search-input-box">
			<option value="CUSTOM" <c:if test="${'CUSTOM' eq search.dateCate}">selected</c:if>>自定义
			<option value="TODAY" <c:if test="${'TODAY' eq search.dateCate}">selected</c:if>>今天
			<option value="YESTERDAY" <c:if test="${'YESTERDAY' eq search.dateCate}">selected</c:if>>昨天
			<option value="LAST_7_DAYS" <c:if test="${'LAST_7_DAYS' eq search.dateCate}">selected</c:if>>过去七天
			<option value="LAST_30_DAYS" <c:if test="${'LAST_30_DAYS' eq search.dateCate}">selected</c:if>>过去30天
		</select>
	</div>
	<div class="ms-col-fix-150 clear">
		<label for="startDate" class="custom-d-cate">日期：</label>
		<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="开始" name="startDate" value="<fmt:formatDate value="${search.startDate}" pattern="yyyy/MM/dd"/>"></input>
		<span style="float:left;">&nbsp;~&nbsp;</span>
		<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="结束" name="endDate" value="<fmt:formatDate value="${search.endDate}" pattern="yyyy/MM/dd"/>"></input>
		<script>
			$(".date-picker").datepicker($.datepicker.regional[ "zh-CN" ]);
			$("select[name='dateCate']").change(function(){
				$(".custom-d-cate").css("visibility", ($(this).val() == 'CUSTOM' ? "visible" : "hidden"));
			}).change();
		</script>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="ms-col-fix-100 clear">
	<input type="submit" value="搜索" class="btn"></input>
	</div>
</form>
<form action="<c:url value="/agent/downNoCardInvoice/export" />" target="_blank" method="post" class="export-form">
	<input type="submit" value="导出" class="btn btn-orange export-btn" ></input>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>机构号</td>
		<td>机构名称</td>
		<td>机构流水号</td>
		<!-- <td>商品标题</td> -->
		<td>交易方式</td>
		<td>平台流水号</td>
		<td>受理时间</td>
		<td>交易金额</td>
		<td>机构订单时间</td>
		<td>手续费</td>
		<td>分润额</td>
		<td>交易状态</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td>
				<c:out value="${item.mchNo}" />
			</td>
			<td>
				<c:out value="${item.mchName}" />
			</td>
			<td>
				<c:out value="${item.tradeNo}" />
			</td>
			<%-- <td>
				<c:out value="${item.subject}" />
			</td> --%>
			<td>
				<utils:paytype mode='lbl' value="${item.payType }" />
			</td>
			<td>
				<c:out value="${item.transNo}" />
			</td>
			<td>
				<c:out value="${item.createDate}" />
			</td>
			<td>
				<c:out value="${item.price}" />
			</td>
			<td>
				<c:out value="${item.orderDate}" />
			</td>
			<td>
				<c:out value="${item.downPayFee}" />
			</td>
			<td>
				<c:out value="${item.agtProfit}" />
			</td>
			<td>
				<%-- <c:out value="${item.status}" /> -  --%><c:out value="${item.statusDesc}" />
			</td>
			<td>
				<a href="<c:url value="/agent/downNoCardInvoice/details?id=${item.id}" />" class="choose-link operate-detail" >详情</a>
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="9">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>