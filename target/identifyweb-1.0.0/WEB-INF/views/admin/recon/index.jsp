<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<form class="search-form" action="<c:url value="/admin/recon/index" />" method="post" style="padding-right:0px;">
	<label for="keywords">过滤条件：</label>
	<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	<label for="mchNo">商户号：</label>
	<input type="text" class="input-box search-input-box" placeholder="商户号" name="mchNo" value="${search.mchNo}"></input>
	<label for="orderDate">交易日期：</label>
	<input type="text" class="input-box search-input-box" placeholder="YYYYMMDD" name="orderDate" value="${search.orderDate}"></input>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="space"></div>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/admin/recon/add" />" style="margin-right:10px;" class="btn btn-orange pull-right operate-detail">对账</a>
	<a href="<c:url value="/admin/recon/export" />"  style="margin-right:10px;" class="btn btn-orange pull-right">下载对账单</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>商户号</td>
		<td>交易日期</td>
		<td>总交易额</td>
		<td>总清算额</td>
		<td>总成功笔数</td>
		<td class="td-width-20">对账单</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td>
				<c:out value="${item.mchNo}" />
			</td>
			<td>
				<c:out value="${item.orderDate}" />
			</td>
			<td>
				<c:out value="${item.price}" />
			</td>
			<td>
				<c:out value="${item.realPrice}" />
			</td>
			<td>
				<c:out value="${item.successCount}" />
			</td>
			<td>
				<a href="http://pay.mishua.cn/zhonlinepay/uploads/${item.filePath}" target="_blank">下载</a>
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="6">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>