<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form class="search-form" action="<c:url value="/admin/meav/index" />" method="post">
	<div class="ms-col-fix-100 clear">
		<label for="transNo" class="custom-d-cate">流水号：</label>
		<input type="text" class="input-box search-input-box" placeholder="流水号" name="transNo" value="${search.transNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="userId" class="custom-d-cate">用户ID：</label>
		<input type="text" class="input-box search-input-box" placeholder="用户ID" name="userId" value="${search.userId}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="status">状态：</label>
		<select name="status" class="input-box search-input-box">
			<option value="">全部</option>
			<option value="CHECK" <c:if test="${search.status eq 'CHECK'}">selected</c:if>>待处理</option>
			<option value="SUCCESS" <c:if test="${search.status eq 'SUCCESS'}">selected</c:if>>已处理</option>
		</select>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="startDate" class="custom-d-cate">From：</label>
		<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="开始时间" name="startDate" value="${search.startDate}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="endDate" class="custom-d-cate">To：</label>
		<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="结束时间" name="endDate" value="${search.endDate}"></input>
		<script>
			$(".date-picker").datepicker($.datepicker.regional[ "zh-CN" ]);
			$("select[name='dateCate']").change(function(){
				$(".custom-d-cate").css("visibility", ($(this).val() == 'CUSTOM' ? "visible" : "hidden"));
			}).change();
		</script>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="space"></div>
	<input type="submit" value="搜索" class="btn"></input>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>所属商户</td>
		<td>交易流水号</td>
		<td>上游商户号</td>
		<td>订单时间</td>
		<td>结算卡户名</td>
		<td>结算卡卡号</td>
		<td>结算卡手机号码 </td>
		<td>结算卡身份证号</td>
		<td>结算卡银行名称</td>
		<td>审核状态</td>
		<td>提交日期</td>
		<td class="td-width-20">操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td>
				<c:out value="${item.mchName}" />(<c:out value="${item.mchNo}" />)
			</td>
			<td>
				<c:out value="${item.transNo}" />
			</td>
			<td>
				<c:out value="${item.userId}" />
			</td>
			<td>
				<c:out value="${item.orderDate}" />
			</td>
			<td>
				<c:out value="${item.settleCardName}" /> 
			</td>
			<td>
				<c:out value="${item.settleCardNo}" />
			</td>
			<td>
				<c:out value="${item.settleCardMobile}" />
			</td>
			<td>
				<c:out value="${item.settleCardCertId}" />
			</td>
			<td>
				<c:out value="${item.settleCardBankNm}" />
			</td>
			<td>
				<c:if test="${item.status eq 'CHECK'}">待审核</c:if>
				<c:if test="${item.status eq 'SUCCESS'}">审核通过</c:if>
			</td>
			<td>
				<c:out value="${item.checkDate}" />
			</td>
			<td>
				<a href="<c:url value="/admin/meav/details?id=${item.id}" />" class="choose-link operate-detail">详细</a>
				<c:if test="${item.status eq 'CHECK'}"><a href="<c:url value="/admin/meav/pass/${item.id}" />" class="choose-link operate-check">审核通过</a></c:if>
				<c:if test="${item.status eq 'CHECK'}"><a href="<c:url value="/admin/meav/del/${item.id}" />" class="choose-link operate-check">删除</a></c:if>
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="12">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>
