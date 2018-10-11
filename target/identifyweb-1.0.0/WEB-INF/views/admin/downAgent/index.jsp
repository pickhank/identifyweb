<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<form class="search-form" action="<c:url value="/admin/downAgent/index" />" method="post">
	<label for="keywords">过滤条件：</label>
	<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="space"></div>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/admin/downAgent/add" />" class="btn btn-orange pull-right operate-detail">新建</a>
</form>
<form action="<c:url value="/admin/downAgent/export" />" target="_blank" method="post" class="export-form">
	<input type="submit" value="导出" class="btn btn-orange export-btn" ></input>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>代理编号</td>
		<td>姓名</td>
		<td>电话</td>
		<td>结算卡银行</td>
		<td>结算卡卡号</td>
		<td>开通产品</td>
		<td>创建时间</td>
		<td>状态</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td>
				<c:out value="${item.agentNo}" />
			</td>
			<td>
				<c:out value="${item.name}" />
			</td>
			<td>
				<c:out value="${item.phone}" />
			</td>
			<td>
				<c:out value="${item.bankName}" />
			</td>
			<td>
				<c:out value="${item.cardNo}" />
			</td>
			<td>
				${item.productTypeSummary }
			</td>
			<td>
				<c:out value="${item.createDate}" />
			</td>
			<td>
				<c:out value="${item.status}" />
			</td>
			<td>
				<a href="<c:url value="/admin/downAgent/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<a href="<c:url value="/admin/downAgent/delete/${item.id}" />" class="choose-link operate-delete" >删除</a>
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="7">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>