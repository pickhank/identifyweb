<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<c:if test="${not empty errorMsg}">
	<script>
        $(function(){
            $.messager.alert('错误','${errorMsg}');
        });
	</script>
</c:if>
<form class="search-form clear" action="<c:url value="/admin/chargeDetail/index"/>" method="post">
	<div class="ms-col-fix-100 clear">
		<label for="keywords">过滤条件：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" id="txt_ide" list="ide"/>
		<%-- <datalist id="ide">
			<c:forEach var="item" items="${res.items}">
				<option value="${item.mchName}" />
			</c:forEach>
		</datalist> --%>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="startDate" class="custom-d-cate">状态：</label>
		<select name="status" class="input-box search-input-box">
			<option value="">全部</option>
			<option <c:if test="${search.status eq '0' }">selected</c:if> value="0">待审核</option>
			<option <c:if test="${search.status eq '0' }">selected</c:if> value="1"><label style='color:blue'>审核成功</label></option>
			<option <c:if test="${search.status eq '0' }">selected</c:if> value="2"><label style='color:red'>审核拒绝</label></option>
		</select>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="startDate" class="custom-d-cate">开始日期：</label>
		<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="yyyy/MM/dd" name="startDate" value="${search.startDate }"/>
		<script>
            $(".date-picker").datepicker($.datepicker.regional[ "zh-CN" ]);
            $("select[name='dateCate']").change(function(){
                $(".custom-d-cate").css("visibility", ($(this).val() == 'CUSTOM' ? "visible" : "hidden"));
            }).change();
		</script>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="startDate" class="custom-d-cate">结束日期：</label>
		<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="yyyy/MM/dd" name="endDate" value="${search.endDate }"/>
		<script>
            $(".date-picker").datepicker($.datepicker.regional[ "zh-CN" ]);
		</script>
	</div>

	<input type="submit" value="搜索" class="btn">
	<a href="<c:url value="/admin/chargeDetail/add" />" class="btn btn-orange pull-right operate-detail" >充值</a>
</form>
<form action="<c:url value="/admin/chargeDetail/export" />" target="_blank" method="post" class="export-form">
	<input type="submit" value="导出" class="btn btn-orange export-btn" >
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>机构</td>
		<td>金额(元)</td>
		<td>备注</td>
		<td>创建时间</td>
		<td>创建人</td>
		<td>处理状态</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="itemStatus" >
		<tr class="list-item">
			<td>
				<c:out value="${item.mchName}" />(<c:out value="${item.mchNo}" />)
			</td>
			<td>
				<c:out value="${item.rearMoneyYuan}" />
			</td>
			<td>
				<c:out value="${item.remark}" />
			</td>
			<td>
				<c:out value="${item.createDate}" />
			</td>
			<td>
				<c:out value="${item.createName}" />
			</td>
			<td>
				<c:choose>
					<c:when test="${(item.status) == 0}">待审核</c:when>
					<c:when test="${(item.status) == 1}"><label style='color:blue'>审核成功</label></c:when>
					<c:when test="${(item.status) == 2}"><label style='color:red'>审核拒绝</label></c:when>
				</c:choose>
			</td>
			<td>
				<c:choose>
					<c:when test="${(item.status) == 0}">
						<a href="<c:url value="/admin/chargeDetail/audit/${item.id}?status=1" />" class="choose-link operate-check">通过</a>
						<a href="<c:url value="/admin/chargeDetail/audit/${item.id}?status=2" />" class="choose-link operate-check">拒绝</a>
					</c:when>
					<c:when test="${(item.status) == 1}">
						<a href="<c:url value="/admin/chargeDetail/add?id=${item.id}&mode=View" />" class="choose-link operate-detail">查看</a>
					</c:when>
					<c:when test="${(item.status) == 2}">
						<a href="<c:url value="/admin/chargeDetail/add?id=${item.id}&mode=View" />" class="choose-link operate-detail">查看</a>
					</c:when>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
</table>
<utils:pager/>
