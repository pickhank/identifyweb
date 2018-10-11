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
<form class="search-form" action="<c:url value="/content/downMerchantBalance/index" />" method="post" style="min-height: 30px;">
	<div class="ms-col-fix-100 clear">
		<label for="balanceNo">账单编号：</label>
		<input type="text" class="input-box search-input-box" placeholder="账单流水号" name="balanceNo" value="${search.balanceNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="referTransNo">业务流水号：</label>
		<input type="text" class="input-box search-input-box" placeholder="交易或代付流水号" name="referTransNo" value="${search.referTransNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="accountType">账户：</label>
		<select name="accType" class="input-box search-input-box">
			<option value="">全部
			<option value="Settle" <c:if test="${search.accType eq 'Settle'}">selected</c:if>>结算账户
			<option value="Credit" <c:if test="${search.accType eq 'Credit'}">selected</c:if>>实时账户
		</select>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="sourceType">类别：</label>
		<select name="sourceType" class="input-box search-input-box">
			<option value="">全部
			<option value="Invoice" <c:if test="${search.sourceType eq 'Invoice'}">selected</c:if>>快捷交易
			<option value="Deposit" <c:if test="${search.sourceType eq 'Deposit'}">selected</c:if>>代付清算
			<option value="Settle" <c:if test="${search.sourceType eq 'Settle'}">selected</c:if>>跑批结算
		</select>
	</div>
	
	<div class="ms-col-fix-150 clear">
		<label for="startDate" class="custom-d-cate">账单日期：</label>
		<input type="text" class="input-box search-input-box date-picker custom-d-cate" required="required" placeholder="开始" name="startDate" value="<fmt:formatDate value="${search.startDate}" pattern="yyyy/MM/dd"/>"></input>
		<span style="float:left;">&nbsp;~&nbsp;</span>
		<input type="text" class="input-box search-input-box date-picker custom-d-cate" required="required" placeholder="结束" name="endDate" value="<fmt:formatDate value="${search.endDate}" pattern="yyyy/MM/dd"/>"></input>
		<script>
			$(".date-picker").datepicker($.datepicker.regional[ "zh-CN" ]);
		</script>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class=" clear ms-col-fix-100" >
	<input type="submit" value="搜索" class="btn"></input>
	</div>
</form>
<form action="<c:url value="/content/downMerchantBalance/export" />" target="_blank" method="post" class="export-form">
	<input type="submit" value="导出" class="btn btn-orange export-btn" ></input>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td align='center'>账单编号</td>
		<td align='center'>账户</td>
		
		<td align='center'>业务类别</td>
		<td align='center'>业务流水号</td>
		
		<td align='center'>原账户金额</td>
		
		<td align='center'>变动金额</td>
		<td align='center'>账户金额</td>
		
		<td align='center'>登记时间</td>
		<td align='center'>原因</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td>
				<c:out value="${item.balanceNo}" />
			</td>
			<td align="center"><c:choose>
					<c:when test="${item.accType eq 'Settle' }">结算账户</c:when>
					<c:when test="${item.accType eq 'Credit' }">实时账户</c:when>
					<c:otherwise>其他</c:otherwise>
				</c:choose>
			</td>
			<td align="center"><c:choose>
					<c:when test="${item.sourceType eq 'Invoice'}"><label style="color:blue;">快捷交易</label></c:when>
					<c:when test="${item.sourceType eq 'Deposit'}"><label style="color:red;">代付清算</label></c:when>
					<c:when test="${item.sourceType eq 'Settle'}">跑批结算</c:when>
			
					<c:otherwise>其他</c:otherwise>
				</c:choose>
			</td>
			<td>
				<c:out value="${item.referTransNo}" />
			</td>
			<td align="right">
				<fmt:formatNumber value="${item.balAmt - item.diffAmt }" pattern="#,##0.00#"/>&nbsp;
			</td>
			<td align="right"><c:set var="discolor" value='' />
				<c:if test='${item.diffAmt gt 0 }'><c:set var="discolor" value="color:blue;" /></c:if>
				<c:if test='${item.diffAmt lt 0 }'><c:set var="discolor" value="color:red;" /></c:if>
				
				<label style="${discolor}"><fmt:formatNumber value="${item.diffAmt }" pattern="#,##0.00#"/>&nbsp;</label>
			</td>
			<td align="right">
				<label style="${discolor}"><fmt:formatNumber value="${item.balAmt }" pattern="#,##0.00#"/>&nbsp;</label>
			</td>
			<td>
				<c:out value="${item.createDate}" />
			</td>
			<td>
				<c:out value="${item.remark}" />
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="9">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>