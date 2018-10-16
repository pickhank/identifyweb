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
<script>
$(function() {
	$(".date-picker").datepicker($.datepicker.regional[ "zh-CN" ]);
});
</script>
<form class="search-form" action="<c:url value="/content/merchantDailySettle/index" />" method="post">
	<div class="ms-col-fix-300 clear" style="width: 400px;">
		<label for="startDate">交易日期：</label>
		<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="开始日期" name="startDate" value="${search.startDate}"></input>
		<span style="float:left;margin: 2px 5px;">~</span>
		<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="结束日期" name="endDate" value="${search.endDate}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="submit" value="搜索" class="btn"></input>
</form>
<form action="<c:url value="/content/merchantDailySettle/export" />" target="_blank" method="post" class="export-form">
	<input type="submit" value="导出" class="btn btn-orange export-btn" ></input>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>机构</td>
		
		<td>汇总日期</td>
		
		<td align='right'>扣款总额(元)</td>
		<td align='right'>扣款笔数</td>
		<td align='right'>扣款交易分润(元)</td>
		
		<td align='right'>还款总额(元)</td>
		<td align='right'>还款笔数</td>
		<td align='right'>还款交易分润(元)</td>
		
		<td align='right'>日净分润(元)</td>
		
		<td>生成时间</td>
		
		<td align='center'>对账单</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td><c:out value="${item.mchName}" />(<c:out value="${item.mchNo}" />)</td>
			
			<td><c:out value="${item.settleDate}" /></td>
			
			<td align='right'><fmt:formatNumber type='number' value='${item.tradeSucAmt / 100.0}' pattern='0.00' maxFractionDigits='2'/></td>
			<td align='right'><c:out value="${item.tradeSucCnt}" /></td>
			<td align='right'><fmt:formatNumber type='number' value='${item.tradeMercProfit / 100.0}' pattern='0.00' maxFractionDigits='2'/></td>
			
			<td align='right'><fmt:formatNumber type='number' value='${item.depositSucAmt / 100.0}' pattern='0.00' maxFractionDigits='2'/></td>
			<td align='right'><c:out value="${item.depositSucCnt}" /></td>
			<td align='right'><fmt:formatNumber type='number' value='${item.depositMercProfit / 100.0}' pattern='0.00' maxFractionDigits='2'/></td>
			
			<td align='right'><fmt:formatNumber type='number' value='${(item.tradeMercProfit + item.depositMercProfit) / 100.0}' pattern='0.00' maxFractionDigits='2'/></td>
			
			<td><fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
			
			<td align='center'>
				<c:if test="${not empty item.statementPayPath}"><a href="<c:url value='/uploads/${item.statementPayPath}' />" target="_blank">扣款</a></c:if>
				<c:if test="${not empty item.statementTransferPath}"><a href="<c:url value='/uploads/${item.statementTransferPath}' />" target="_blank">提现</a></c:if>
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="10">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>