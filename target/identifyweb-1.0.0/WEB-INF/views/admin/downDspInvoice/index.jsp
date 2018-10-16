<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${not empty error}">
	<script>
	$(function(){
		alert("${error}");
	});
	</script>
</c:if>
<form class="search-form clear" action="<c:url value="/admin/downDspInvoice/index" />" method="post" style="min-height: 64px;">
	<div class="ms-col-fix-100 clear">
		<label for="keywords">过滤条件：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="mchNo">机构号：</label>
		<input type="text" class="input-box search-input-box" placeholder="商户号" name="mchNo" value="${search.mchNo}"></input>
	</div>
	
	<div class="ms-col-fix-100 clear">
		<label for="dspMode">交易模式：</label>
		<utils:enum key="EnumDspMode" name="dspMode" cssName="input-box search-input-box" value="${search.dspMode}"></utils:enum>
	</div>
	
	<div class="ms-col-fix-100 clear">
		<label for="transNo">平台流水号：</label>
		<input type="text" class="input-box search-input-box" placeholder="流水号" name="transNo" value="${search.transNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="tradeNo">下游流水号：</label>
		<input type="text" class="input-box search-input-box" placeholder="下游流水号" name="tradeNo" value="${search.tradeNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="channel">通道号：</label>
		<utils:combo name="channel" cssName="input-box search-input-box" value="${search.channel}"></utils:combo>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="status">交易状态：</label>
		<select name="status" class="input-box search-input-box">
			<option value="">全部
			<option value="00" <c:if test="${search.status eq '00'}">selected</c:if>>成功
			<option value="01" <c:if test="${search.status eq '01'}">selected</c:if>>交易中
			<option value="09" <c:if test="${search.status eq '09'}">selected</c:if>>发起交易中
			<option value="02" <c:if test="${search.status eq '02'}">selected</c:if>>失败
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
	<div class="ms-col-fix-100 clear">
		<label for="startDate" class="custom-d-cate">From：</label>
		<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="开始时间" name="startDate" value="<fmt:formatDate value="${search.startDate}" pattern="yyyy-MM-dd"/>"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="endDate" class="custom-d-cate">To：</label>
		<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="结束时间" name="endDate" value="<fmt:formatDate value="${search.endDate}" pattern="yyyy-MM-dd"/>"></input>
		<script>
			$(".date-picker").datepicker($.datepicker.regional[ "zh-CN" ]);
			$("select[name='dateCate']").change(function(){
				$(".custom-d-cate").css("visibility", ($(this).val() == 'CUSTOM' ? "visible" : "hidden"));
			}).change();
		</script>
	</div>
	<div class="ms-col-fix-100 clear" style="width: 400px;">
		<label for="keywords">受理时间：</label>
		<input type="text" class="input-box search-input-box datetime-picker " placeholder="开始时间" name="startTime" value="${search.startTime}"></input>
		<span style="float:left;">~</span>
		<input type="text" class="input-box search-input-box datetime-picker " placeholder="结束时间" name="endTime" value="${search.endTime}"></input>
		<script>
			$('.datetime-picker').timepicker({ 'timeFormat': 'H:i:s' });
		</script>
	</div>
		
	<div class="ms-col-fix-100 clear">
	<label for="statusDesc">状态描述：</label>
	<input type="text" class="input-box search-input-box" placeholder="状态描述" name="statusDesc" value="${search.statusDesc}"></input>
	</div>
	
	<div class="ms-col-fix-300 clear" >
		<utils:hideColumn defaultVal="渠道商户,手续费（元）,代理分润（元）,平台分润（元）" />
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<!-- <div class="ms-col-fix-all clear"> -->
	<input type="submit" value="搜索" class="btn"></input>
	<!-- </div> -->
</form>
<form action="<c:url value="/admin/downDspInvoice/export" />" target="_blank" method="post" class="export-form">
	<input type="submit" value="导出" class="btn btn-orange export-btn" ></input>
</form>
<%-- <form action="<c:url value="/admin/downDspInvoice/checkTotal" />" method="post" style="position: absolute; right: 2.5%; top: 50px;">
	<input type="submit" value="同步" class="btn btn-orange" ></input>
</form> --%>
<table class="list-wapper">
	<tr class="list-header">
		<td>机构</td>
		<td>渠道</td>
		<td>渠道商户</td>
		<td>平台流水号</td>
		<td>交易类型</td>
		<td>姓名</td>
		<td align="right">手续费（元）</td>
		<td align="right">代理分润（元）</td>
		<td align="right">平台分润（元）</td>
		<td>机构流水号</td>
		<td>受理时间</td>
		<td>交易状态</td>
		<td>状态时间</td>
		<td>操作</td>
	</tr>
	
	<c:set var="rowNum" value="0" />
	
	<c:set var="mchPayFee" value="0" />
	<c:set var="agentProfit" value="0" />
	<c:set var="profit" value="0" />
	
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td><c:out value="${item.mchName}" />(<c:out value="${item.mchNo}" />)</td>
			<td><utils:combo mode="lbl" value="${item.channel}" /></td>
			<td><utils:combo mode="lbl" value="${item.channelAccName}" />(${item.channelAccNo})</td>
			<td><c:out value="${item.transNo}" /></td>
			<td>
				<utils:enum mode='lbl' key="EnumDspMode" value="${item.dspMode }" />
			</td>
			<td><c:out value="${item.holderName}" /></td>
			<td align="right">
				<fmt:formatNumber value="${item.mchPayFee}" pattern="#,##0.00#"/>&nbsp;
			</td>
			<td align="right">
				<fmt:formatNumber value="${item.agentProfit}" pattern="#,##0.00#"/>&nbsp;
			</td>
			<td align="right">
				<fmt:formatNumber value="${item.profit}" pattern="#,##0.00#"/>&nbsp;
			</td>
			<td><c:out value="${item.tradeNo}" /></td>
			<td>
				<fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd HH:mm:ss"/>
			</td>
			<td class="longtext-hide" style="max-width: 120px;">
				<label style="weight: bold;"><c:out value="${item.status}" /></label> - <c:out value="${item.statusDesc}" />
			</td>
			<td>
				<fmt:formatDate value="${item.statusChgDate}" pattern="MM/dd HH:mm:ss"/>
			</td>
			<td>
				<a href="<c:url value="/admin/downDspInvoice/detail?id=${item.id}" />" class="choose-link operate-detail">详情</a>
			</td>
			
			
			<c:set var="rowNum" value="${rowNum + 1 }" />
			<c:set var="mchPayFee" value="${mchPayFee + item.mchPayFee }" />
			<c:set var="agentProfit" value="${agentProfit + item.agentProfit }" />
			<c:set var="profit" value="${profit + item.profit }" />
		</tr>
	</c:forEach>
	
	<c:if test="${rowNum gt 0 }">
		<tr class="list-item" style="font-weight: bold;">
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><label style="font-weight: bold; text-align: right;">合计（本页）</label></td>
			<td>&nbsp;</td>
			<td align='right'><label style="font-weight: bold; ">
				<fmt:formatNumber type='number' value='${mchPayFee}' pattern='0.00' maxFractionDigits='2'/></label>
			</td>
			<td align='right'><label style="font-weight: bold; color: blue;">
				<fmt:formatNumber type='number' value='${agentProfit}' pattern='0.00' maxFractionDigits='2'/></label>
			</td>
			<td align='right'><label style="font-weight: bold; color: blue;">
				<fmt:formatNumber type='number' value='${profit}' pattern='0.00' maxFractionDigits='2'/></label>
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</c:if>
</table>
<div style="border: none; text-align: right;width: 98%;margin: 0px auto; color: #1C1C1E; font-size: 0.9rem;">
	<utils:pager></utils:pager>
</div>