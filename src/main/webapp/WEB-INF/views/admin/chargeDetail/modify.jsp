<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<jsp:useBean id="currentTime" class="java.util.Date"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/admin/chargeDetail/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">录入人:</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="createName" value="${item.createName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户号:</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchNo" value="${item.mchNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户名:</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchName" value="${item.mchName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">充值处理状态</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="status" onkeyup="value=value.replace(/[^\d]/g,'')" style="width: 80px;" value="${item.status}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">审核时间:</td>
			<td class="liner-box-right">
				<%--<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="审核时间" name="auditDate" value="<fmt:formatDate value="${currentTime}" pattern="yyyy/MM/dd"/>">--%>
					<div class="ms-col-fix-100 clear">
						<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="yyyy/MM/dd" name="startDate" value="<fmt:formatDate value="${search.startDate}" pattern="yyyy/MM/dd"/>"></input>
						<script>
                            $(".date-picker").datepicker($.datepicker.regional[ "zh-CN" ]);
                            $("select[name='dateCate']").change(function(){
                                $(".custom-d-cate").css("visibility", ($(this).val() == 'CUSTOM' ? "visible" : "hidden"));
                            }).change();
						</script>
					</div>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">充值金额（元）:</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="RearMoney" style="width: 80px" onkeyup="value=value.replace(/[^\d]/g,'')" value="${item.rearMoney}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">备注</td>
			<td class="liner-box-right">
				<textarea class="input-box" style="height: 180px;" name="feeText">${item.remark}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="确认修改" class="btn" />
			</td>
		</tr>
	</table>
</form>

