<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://www.woodare.com/jsp/taglib/my"%>
<style>
.list-wapper td {
	border: 1px solid #DEDEDE;
}
td.need-settlement {
	color: blue;
}
td.not-need-settlement {
	color: red;
}
td.all-settlement {
	color: gray;
}
tr.total-data {
	font-weight:700;
}
table.list-wapper td {
	text-align: center;
}
table.list-wapper td.num {
	text-align: right;
}
</style>
<form class="search-form" action="<c:url value="/admin/downMerchantDeposit/sum" />" method="post">
	<label for="mchNo">检索条件：</label>
	<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	
	<label for="mchNo">机构号：</label>
	<input type="text" class="input-box search-input-box" placeholder="机构号" name="mchNo" value="${search.mchNo}"></input>
	
	<label for="dateCate">时间：</label>
	<select name="dateCate" class="input-box search-input-box">
		<option value="CUSTOM" <c:if test="${'CUSTOM' eq search.dateCate}">selected</c:if>>自定义
		<option value="TODAY" <c:if test="${'TODAY' eq search.dateCate}">selected</c:if>>今天
		<option value="YESTERDAY" <c:if test="${'YESTERDAY' eq search.dateCate}">selected</c:if>>昨天
		<option value="LAST_7_DAYS" <c:if test="${'LAST_7_DAYS' eq search.dateCate}">selected</c:if>>过去七天
		<option value="LAST_30_DAYS" <c:if test="${'LAST_30_DAYS' eq search.dateCate}">selected</c:if>>过去30天
	</select>
	
	<label for="startDate" class="custom-d-cate">From：</label>
	<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="开始时间" name="startDate" value="<fmt:formatDate value="${search.startDate}" pattern="yyyy/MM/dd"/>"></input>
	<label for="endDate" class="custom-d-cate">To：</label>
	<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="结束时间" name="endDate" value="<fmt:formatDate value="${search.endDate}" pattern="yyyy/MM/dd"/>"></input>
	
	<script>
		$(".date-picker").datepicker($.datepicker.regional[ "zh-CN" ]);
		$("select[name='dateCate']").change(function(){
			$(".custom-d-cate")[$(this).val() == 'CUSTOM' ? "show" : "hide"]();
		}).change();
	</script>
	<label >&nbsp;</label>
	<input type="submit" value="搜索" class="btn"></input>
</form>
<form action="<c:url value="/admin/downMerchantDeposit/exportSum" />" target="_blank" method="post" class="export-form">
	<input type="submit" value="导出" class="btn btn-orange export-btn" ></input>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>机构号</td>
		
		<td>机构名称</td>
		<td>代付类型</td>
		
		<td>成功率</td>
		<td>总金额（元）</td>
		<td>总笔数</td>
		
		<td>待查金额（元）</td>
		<td>待查笔数</td>
		
		<td>成功金额（元）</td>
		<td>成功笔数</td>
		<td>手续费（元）</td>
		
		<td>代理分润（元）</td>
		<td>平台分润（元）</td>
	</tr>
	
	<c:set var="rowNum" value="0" />
	
	<c:set var="totalPrice" value="0" />
	<c:set var="totalCount" value="0" />
	<c:set var="pendingPrice" value="0" />
	<c:set var="pendingCount" value="0" />
	<c:set var="price" value="0" />
	<c:set var="count" value="0" />
	<c:set var="feePrice" value="0" />
	<c:set var="agtProfit" value="0" />
	<c:set var="profit" value="0" />
	<c:forEach var="item" items="${items}" varStatus="status">
		<tr class="list-item">
			<td>
				<c:out value="${item.mchNo}" />
			</td>
			<td style="text-align:left;">
				<c:out value="${item.mchName}" />
			</td>
			<td>
				<c:choose>
					<c:when test="${item.mode eq '0' }">T1清算</c:when>
					<c:when test="${item.mode eq '1' }">T0代付</c:when>
					<c:otherwise>${item.mode }</c:otherwise>
				</c:choose>
			</td>
			<td class="warning">
				<my:percent value="${item.count}" total="${item.totalCount}"/>
			</td>
			
			<td class="num"><fmt:formatNumber value="${item.totalPrice}" pattern="#,##0.00#"/>&nbsp;</td>
			<td class="num"><c:out value="${item.totalCount}" />&nbsp;</td>
			
			<td class="num"><fmt:formatNumber value="${item.pendingPrice}" pattern="#,##0.00#"/>&nbsp;</td>
			<td class="num"><c:out value="${item.pendingCount}" />&nbsp;</td>
			
			<td class="num"><fmt:formatNumber value="${item.price}" pattern="#,##0.00#"/>&nbsp;</td>
			<td class="num"><c:out value="${item.count}" />&nbsp;</td>
			
			<td class="num"><fmt:formatNumber value="${item.feePrice}" pattern="#,##0.00#"/>&nbsp;</td>
			<td class="num"><fmt:formatNumber value="${item.agtProfit}" pattern="#,##0.00#"/>&nbsp;</td>
			<td class="num"><fmt:formatNumber value="${item.profit}" pattern="#,##0.00#"/>&nbsp;</td>
			
			<c:set var="rowNum" value="${rowNum + 1 }" />
			<c:set var="totalPrice" value="${totalPrice + item.totalPrice }" />
			<c:set var="totalCount" value="${totalCount + item.totalCount }" />
			<c:set var="pendingPrice" value="${pendingPrice + item.pendingPrice }" />
			<c:set var="pendingCount" value="${pendingCount + item.pendingCount }" />
			<c:set var="price" value="${price + item.price }" />
			<c:set var="count" value="${count + item.count }" />
			
			<c:set var="feePrice" value="${feePrice + item.feePrice }" />
			<c:set var="agtProfit" value="${agtProfit + item.agtProfit }" />
			<c:set var="profit" value="${profit + item.profit }" />
		</tr>
	</c:forEach>
	
	<c:if test="${rowNum gt 0 }">
		<tr class="list-item" style="font-weight: bold;">
			<td>&nbsp;</td>
			
			<td><label style="font-weight: bold; text-align: right;">合计（本页）</label></td>
			<td>&nbsp;</td>
			
			<td>&nbsp;</td>
			<td class="num"><fmt:formatNumber type='number' value='${totalPrice}' pattern='#,##0.00#' maxFractionDigits='2'/>&nbsp;</td>
			<td class="num"><c:out value="${totalCount }" />&nbsp;</td>
			
			<td class="num"><fmt:formatNumber type='number' value='${pendingPrice}' pattern='#,##0.00#' maxFractionDigits='2'/>&nbsp;</td>
			<td class="num"><c:out value="${pendingCount }" />&nbsp;</td>
			
			<td class="num"><fmt:formatNumber type='number' value='${price}' pattern='#,##0.00#' maxFractionDigits='2'/>&nbsp;</td>
			<td class="num"><c:out value="${count }" />&nbsp;</td>
			
			<td class="num"><fmt:formatNumber type='number' value='${feePrice}' pattern='#,##0.00#' maxFractionDigits='2'/>&nbsp;</td>
			<td class="num"><fmt:formatNumber type='number' value='${agtProfit}' pattern='#,##0.00#' maxFractionDigits='2'/>&nbsp;</td>
			<td class="num"><fmt:formatNumber type='number' value='${profit}' pattern='#,##0.00#' maxFractionDigits='2'/>&nbsp;</td>
		</tr>
	</c:if>
</table>