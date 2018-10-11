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
<form class="search-form" action="<c:url value="/admin/kbin/index" />" method="post">
	<div class="ms-col-fix-100 clear">
		<label for="keywords">过滤条件：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="startNum">卡bin头：</label>
		<input type="text" class="input-box search-input-box" placeholder="首数字" name="startNum" value="${search.startNum}"></input>
	</div>
	<%-- <div class="ms-col-fix-100 clear">
		<label for="bankName">银行名称：</label>
		<input type="text" class="input-box search-input-box" placeholder="银行名称" name="bankName" value="${search.bankName}"></input>
	</div> --%>
	<div class="ms-col-fix-100 clear">
		<label for="bankNameAbbr">银行缩写：</label>
		<input type="text" class="input-box search-input-box" placeholder="银行缩写" name="bankNameAbbr" value="${search.bankNameAbbr}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="bankNo">银行联行号：</label>
		<input type="text" class="input-box search-input-box" placeholder="银行联行号" name="bankNo" value="${search.bankNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="bankCode">银行编号：</label>
		<input type="text" class="input-box search-input-box" placeholder="银行编号" name="bankCode" value="${search.bankCode}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="cardAttr">卡类别：</label>
		<select name="cardAttr" class="input-box search-input-box">
			<option value="">全部
			<option value="1" <c:if test="${search.cardAttr eq '1'}">selected</c:if>>借记卡
			<option value="2" <c:if test="${search.cardAttr eq '2'}">selected</c:if>>贷记卡
			<option value="3" <c:if test="${search.cardAttr eq '3'}">selected</c:if>>准贷记卡
			<option value="4" <c:if test="${search.cardAttr eq '4'}">selected</c:if>>预付卡
			<option value="5" <c:if test="${search.cardAttr eq '5'}">selected</c:if>>其他
		</select>
	</div>
	<%-- <div class="ms-col-fix-100 clear">
		<label for="cardLength">长度：</label>
		<input type="text" class="input-box search-input-box" placeholder="长度" name="cardLength" value="${search.cardLength}"></input>
	</div> --%>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/admin/kbin/add" />" class="btn btn-orange pull-right operate-detail">新建</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>卡bin</td>
		<td>银行名称</td>
		<td>银行缩写</td>
		<td>银行联行号</td>
		<td>银行编号</td>
		<td>长度</td>
		<td>卡类型</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td>
				<c:out value="${item.startNum}" />
			</td>
			<td>
				<c:out value="${item.bankName}" />
			</td>
			<td>
				<c:out value="${item.bankNameAbbr}" />
			</td>
			<td>
				<c:out value="${item.bankNo}" />
			</td>
			<td>
				<c:out value="${item.bankCode}" />
			</td>
			<td>
				<c:out value="${item.cardLength}" />
			</td>
			<td>
				<c:choose>
					<c:when test="${item.cardAttr eq '1' }">借记卡</c:when>
					<c:when test="${item.cardAttr eq '2' }">贷记卡</c:when>
					<c:when test="${item.cardAttr eq '3' }">准贷记卡</c:when>
					<c:when test="${item.cardAttr eq '4' }">预付卡</c:when>
					<c:otherwise>其他</c:otherwise>
				</c:choose>
			</td>
			
			<td>
				<a href="<c:url value="/admin/kbin/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<a href="<c:url value="/admin/kbin/delete/${item.id}" />" class="choose-link operate-delete" >删除</a>
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="7">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>