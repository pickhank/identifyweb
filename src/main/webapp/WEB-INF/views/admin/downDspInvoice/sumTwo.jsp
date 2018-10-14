<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://www.woodare.com/jsp/taglib/my"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
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
table.list-wapper th {
	text-align: center;
}
table.list-wapper td.num {
	text-align: right;
}
</style>
<form class="search-form" action="<c:url value="/admin/downDspInvoice/sumTwo" />" method="post">
	<label for="mchNo">检索条件：</label>
	<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>

	<label for="mchNo">机构号：</label>
	<input type="text" class="input-box search-input-box" placeholder="机构号" name="mchNo" value="${search.mchNo}"></input>

	<label for="dspMode">交易模式：</label>
	<utils:enum key="EnumDspMode" name="dspMode" cssName="input-box search-input-box" value="${search.dspMode}"></utils:enum>

	<label for="dateCate">时间：</label>
	<select name="dateCate" class="input-box search-input-box">
		<option value="CUSTOM" <c:if test="${'CUSTOM' eq search.dateCate}">selected</c:if>>自定义
		<option value="TODAY" <c:if test="${'TODAY' eq search.dateCate}">selected</c:if>>今天
		<option value="YESTERDAY" <c:if test="${'YESTERDAY' eq search.dateCate}">selected</c:if>>昨天
		<option value="LAST_7_DAYS" <c:if test="${'LAST_7_DAYS' eq search.dateCate}">selected</c:if>>过去七天
		<option value="LAST_30_DAYS" <c:if test="${'LAST_30_DAYS' eq search.dateCate}">selected</c:if>>过去30天
	</select>

	<label for="startDate" class="custom-d-cate">From：</label>
	<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="开始时间" name="startDate" value="<fmt:formatDate value="${search.startDate}" pattern="yyyy/MM/dd"/>">
	<label for="endDate" class="custom-d-cate">To：</label>
	<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="结束时间" name="endDate" value="<fmt:formatDate value="${search.endDate}" pattern="yyyy/MM/dd"/>">

	<script>
		$(".date-picker").datepicker($.datepicker.regional[ "zh-CN" ]);
		$("select[name='dateCate']").change(function(){
			$(".custom-d-cate")[$(this).val() == 'CUSTOM' ? "show" : "hide"]();
		}).change();
	</script>
	<input type="submit" value="搜索" class="btn"></input>
</form>
<form action="<c:url value="/admin/downDspInvoice/exportSum" />" target="_blank" method="post" class="export-form">
	<input type="submit" value="导出" class="btn btn-orange export-btn" ></input>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<th>机构</th>
		<th>统计时间</th>
		<th>成功率</th>
		<th>总数</th>
		<th>成功数</th>
		<th>总手续费（元）</th>
		<th>总代理分润（元）</th>
		<th>总平台分润（元）</th>

		<c:set var="rowNum" value="0" />
		<c:set var="totalCount" value="0" />
		<c:set var="count" value="0" />
		<c:set var="mchFeeAmt" value="0" />
		<c:set var="agentProfitAmt" value="0" />
		<c:set var="profitAmt" value="0" />
	</tr>
	<c:forEach var="item" items="${items}" varStatus="status">
		<tr class="list-item">
			<td style="text-align:left;">
				<c:out value="${item.mchName}" />(<c:out value="${item.mchNo}" />)
			</td>
			<td>
				<c:out value="${item.transDate}" />&nbsp;
				<%--<fmt:formatDate value="${item.transDate}" pattern="yyyy/MM/dd HH:mm:ss"/>--%>
				<%--<fmt:formatDate pattern="yyyy-MM-dd" value="${item.transDate}" />--%>
			</td>
			<td class="warning">
				<my:percent value="${item.count}" total="${item.totalCount}"/>
			</td>
			<td class="num">
				<c:out value="${item.totalCount}" />&nbsp;
			</td>
			<td class="num">
				<c:out value="${item.count}" />&nbsp;
			</td>
			<td class="num">
				<fmt:formatNumber value="${item.mchFeeAmt}" pattern="#,##0.00#"/>&nbsp;
			</td>
			<td class="num">
				<fmt:formatNumber value="${item.agentProfitAmt}" pattern="#,##0.00#"/>&nbsp;
			</td>
			<td class="num">
				<fmt:formatNumber value="${item.profitAmt}" pattern="#,##0.00#"/>&nbsp;
			</td>
		</tr>
	</c:forEach>

	<c:if test="${rowNum gt 0 }">
		<tr class="list-item" style="font-weight: bold;">
			<td>&nbsp;</td>

			<td><label style="font-weight: bold; text-align: right;">合计（本页）</label></td>
			<td>&nbsp;</td>

			<td>&nbsp;</td>
			<td class="num"><c:out value="${totalCount}" />&nbsp;</td>
			<td class="num"><c:out value="${count}" />&nbsp;</td>

			<td class="num"><fmt:formatNumber type='number' value='${mchFeeAmt}' pattern='#,##0.00#' maxFractionDigits='2'/>&nbsp;</td>
			<td class="num"><fmt:formatNumber type='number' value='${agentProfitAmt}' pattern='#,##0.00#' maxFractionDigits='2'/>&nbsp;</td>
			<td class="num"><fmt:formatNumber type='number' value='${profitAmt}' pattern='#,##0.00#' maxFractionDigits='2'/>&nbsp;</td>
		</tr>
	</c:if>
</table>