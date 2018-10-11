<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
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
	$('.datetime-picker').timepicker({ 'timeFormat': 'H:i:s' });
});
</script>
<div style="overflow:hidden;">
<form class="search-form" action="<c:url value="/admin/downTradeLog/index" />" method="post">
	<input type="hidden" name="searchFlag" value="true" />
	<div class="ms-col-fix-100 clear">
		<label for="mchNo">通道：</label>
		<utils:combo mode="sel" name="channel" cssName="input-box search-input-box" value="${search.channel}"></utils:combo>
	</div>
	
	<div class="ms-col-fix-100 clear">
		<label for="keywords">机构号：</label>
		<input type="text" class="input-box search-input-box" placeholder="商户编号" name="mchNo" value="${search.mchNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="keywords">机构流水号：</label>
		<input type="text" class="input-box search-input-box" placeholder="商户流水号" name="tradeNo" value="${search.tradeNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="keywords">错误码：</label>
		<input type="text" class="input-box search-input-box" placeholder="错误码" name="errCode" value="${search.errCode}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="keywords">错误信息：</label>
		<input type="text" class="input-box search-input-box" placeholder="错误信息" name="errMsg" value="${search.errMsg}"></input>
	</div>
	<div class="ms-col-fix-100 clear" style="width: 400px;">
		<label for="keywords">日期：</label>
		<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="开始" name="startDt" value="${search.startDt}"></input>
		<span style="float:left;margin: 2px 5px;">~</span>
		<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="结束" name="endDt" value="${search.endDt}"></input>
	</div>
	<div class="ms-col-fix-100 clear" style="width: 400px;">
		<label for="keywords">时间点：</label>
		<input type="text" class="input-box search-input-box datetime-picker " placeholder="开始时间" name="startTime" value="${search.startTime}"></input>
		<span style="float:left;">~</span>
		<input type="text" class="input-box search-input-box datetime-picker " placeholder="结束时间" name="endTime" value="${search.endTime}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="space"></div>
	<input type="submit" value="搜索" class="btn"></input>
</form>
</div>
<table class="list-wapper">
	<tr class="list-header">
		<td>日志ID</td>
		<td>日期</td>
		<td>商户名称</td>
		<td>交易流水号</td>
		<td>受理时间</td>
		<td>金额</td>
		<td>交易信息</td>
		<td>通道</td>
		<td>报错信息</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td>
				${item.id}
			</td>
			<td>
				<utils:dateFormat value="${item.recordDt}"></utils:dateFormat>
			</td>
			<td>
				<c:out value="${item.mchName}" />(<c:out value="${item.mchNo}" />)
			</td>
			<td>
				<c:out value="${item.tradeNo}" />
			</td>
			<td>
				<utils:dateFormat value="${item.receiveDtime}"></utils:dateFormat>
				~
				<utils:dateFormat value="${item.replyDtime}"></utils:dateFormat>
			</td>
			<td>
				<c:out value="${item.price}" />
			</td>
			<td>
				<c:out value="${item.accBankName}" /><br>
				<c:out value="${item.accCardNo}" />
			</td>
			<td>
				<utils:combo mode="lbl" value="${item.channel}" />
			</td>
			<td>
				<c:out value="${item.errMsg}" />(<c:out value="${item.errCode}" />)
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="11">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>