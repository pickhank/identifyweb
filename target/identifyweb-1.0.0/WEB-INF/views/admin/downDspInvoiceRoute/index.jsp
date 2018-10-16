<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<c:if test="${not empty error}">
	<script>
		$(function() {
			$.messager.alert('错误', '${error}');
		});
	</script>
</c:if>
<script>
	$(function() {
		$(".date-picker").datepicker($.datepicker.regional[ "zh-CN" ]);
		$('.datetime-picker').timepicker({ 'timeFormat': 'H:i:s' });
	});
</script>
<div style="overflow:hidden;">
<form class="search-form" action="<c:url value="/admin/downDspInvoiceRoute/index" />" method="post">
	<div class="ms-col-fix-100 clear">
		<label for="keywords">过滤条件：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="mercCategory">商户类别：</label>
		<utils:merccate id="mercCategory" name="mercCategory" cssName="input-box search-input-box" value="${search.mercCategory}"></utils:merccate>
	</div>
	<div class="ms-col-fix-100 clear" style="width: 400px;">
		<label for="keywords">失效日期：</label>
		<input type="text" autocomplete="off" class="input-box search-input-box date-picker custom-d-cate" placeholder="yyyy/MM/dd" name="startDate" value="${search.startDate}"></input>
		<span style="float:left;margin: 2px 5px;">~</span>
		<input type="text" autocomplete="off" class="input-box search-input-box date-picker custom-d-cate" placeholder="yyyy/MM/dd" name="endDate" value="${search.endDate}"></input>
	</div>
	<div class="ms-col-fix-100 clear" style="width: 400px;">
		<label for="keywords">失效时间：</label>
		<input type="text" autocomplete="off" class="input-box search-input-box datetime-picker " placeholder="hh:mm:ss" name="startTime" value="${search.startTime}"></input>
		<span style="float:left;">~</span>
		<input type="text" autocomplete="off" class="input-box search-input-box datetime-picker " placeholder="hh:mm:ss" name="endTime" value="${search.endTime}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="space"></div>
	<div class="ms-col-fix-100 clear">
		<input type="submit" value="搜索" class="btn"></input>
	</div>
</form>
<a href="<c:url value="/admin/downDspInvoiceRoute/add" />" class="btn btn-orange pull-right operate-detail" style="margin-right: 10px;">新建</a>
</div>
<table class="list-wapper">
	<tr class="list-header">
		<td>通道商户</td>
		
		<td>机构</td>
		<td>商户类别</td>
		<td>交易类型</td>
		
		<td>有效日期</td>
		<td>有效时间</td>
		
		<td>优先级</td>
		
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td>
				<c:out value="${item.channelMercName}" />(<utils:dcombo mode="lbl" value="${item.channel}" />)
			</td>
			
			<td>
				<c:if test="${empty item.mchNo}">-</c:if>
				<c:if test="${not empty item.mchNo}"><c:out value="${item.mchName}" />(<c:out value="${item.mchNo}" />)</c:if>
			</td>
			<td>
				<utils:merccate mode="lbl" value="${item.mercCategory}" />
			</td>
			<td>
				${item.supportDspModeNames}
			</td>
			
			<td>
				<utils:dateFormat value="${item.startDate}" />~<utils:dateFormat value="${item.endDate}" />
			</td>
			<td>
				<utils:dateFormat value="${item.startTime}" />~<utils:dateFormat value="${item.endTime}" />
			</td>
			<td>
				<c:out value="${item.priority}" />
			</td>
			<td>
				<a href="<c:url value="/admin/downDspInvoiceRoute/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<a href="<c:url value="/admin/downDspInvoiceRoute/delete/${item.id}" />" class="choose-link operate-delete">删除</a>
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="8">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>