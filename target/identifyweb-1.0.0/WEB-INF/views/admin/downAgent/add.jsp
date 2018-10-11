<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<style>
.input-box-short {
	width:43%;
}
.liner-box-left {
	padding: 2px 10px;
}
.liner-box-right {
	padding: 0;
}
input.input-box[readonly], select.input-box[readonly] {
	background-color: rgb(235, 235, 228);
}
</style>
<form action="<c:url value="/admin/downAgent/add" />" method="post">
	<input type="hidden" name="id" value="${item.id}" />
	
	
	<div class="ms-row">
		<c:if test="${not empty item.id }">
		<div class="ms-row ms-border" style="margin: 0 0.5%;">
			<div class="ms-header">配置产品</div>
			<c:if test="${not empty item.id }">
				<table class="list-wapper" style="margin-bottom: 5px;">
					<tr class="list-header">
						<td>名称</td>
						<!-- <td>交易费率(‰)</td> -->
						<td>单笔费用(元)</td>
						<!-- <td>垫资费率(‰)</td>
						<td>单笔代付费(元)</td> -->
					</tr>
					<c:forEach var="item" items="${products}" varStatus="status">
						<c:set var="inputflg" value="required" />
						<c:set var="checkflg" value="checked='checked'" />
						<c:if test="${not empty item.status and item.status eq 'PENDING' }">
							<c:set var="inputflg" value="readonly='readonly' " />
							<c:set var="checkflg" value="" />
						</c:if>
						<tr class="list-item">
							<td>
								<input type="hidden" name="productTypeArr" value="${item.dspMode }">
								<input type="checkbox" ${checkflg }  class="input-check" style='width: 20px;' name="selProductTypeArr" id="productType${item.dspMode }" value="${item.dspMode }">
								<label for="productType${item.dspMode }"><c:out value="${item.dspMode.desc }" /></label>
							</td>
							<%-- <td>
								<input type="text" ${inputflg } class="input-box" placeholder="单位：千分之" name="feeRatioArr" value="${item.feeRatio}" />
							</td> --%>
							<td>
								<input type="text" ${inputflg } class="input-box" placeholder="单位：元" name="addFeeAmtArr" value="${item.addFeeAmt}" />
							</td>
							<%-- <td>
								<input type="text" ${inputflg } class="input-box" placeholder="单位：千分之 " name="drawFeeRatioArr" value="${item.drawFeeRatio}" />
							</td>
							<td>
								<input type="text" ${inputflg } class="input-box" placeholder="单位：元" name="addDrawFeeAmtArr" value="${item.addDrawFeeAmt}" />
							</td> --%>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
		<br />
		</c:if>
		<div class="ms-row ms-border" style="margin: 0 0.5%;">
			<div class="ms-header">基本信息</div>
			<table class="liner-box">
				<c:if test="${not empty error}">
					<tr class="liner-error">
						<td class="liner-box-left"></td>
						<td class="liner-box-right">${error}</td>
					</tr>
				</c:if>
				<tr>
					<td class="liner-box-left required">代理商编号:</td>
					<td class="liner-box-right">
						<c:if test="${empty item.id }">
							<input type="text" class="input-box" placeholder="代理商编号" required="required" name="agentNo" value="${item.agentNo}" />
						</c:if>
						<c:if test="${not empty item.id }">
							<input type="hidden" class="input-box" placeholder="代理商编号" name="agentNo" value="${item.agentNo}"/>
							<input type="text" class="input-box" value="${item.agentNo}" readonly/>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">姓名:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="姓名" name="name" value="${item.name}" />
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">电话:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="电话" name="phone" value="${item.phone}" />
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">结算卡银行:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="结算卡银行" name="bankName" value="${item.bankName}" />
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">结算卡卡号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="结算卡卡号" name="cardNo" value="${item.cardNo}" />
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">收支明细:</td>
					<td class="liner-box-right">
						<select name="enableBalChgFlg" class="input-box">
							<option value="false" <c:if test="${empty item.enableBalChgFlg or not item.enableBalChgFlg}">selected</c:if>>关闭</option>
							<option value="true" <c:if test="${not empty item.enableBalChgFlg and item.enableBalChgFlg}">selected</c:if>>打开</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">状态:</td>
					<td class="liner-box-right">
						<select name="status" class="input-box">
							<option value="PENDING" <c:if test="${item.status eq 'PENDING'}">selected</c:if>>未使用
							<option value="ACTIVE" <c:if test="${item.status eq 'ACTIVE'}">selected</c:if>>使用中
							<option value="HOLD" <c:if test="${item.status eq 'HOLD'}">selected</c:if>>暂停使用
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="liner-box-one-line">
						<input type="submit" value="提交" class="btn" />
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
<script type="text/javascript">
	$(function () {
		$('input[name=selProductTypeArr]').click(function() {
			var inputs = $(this).closest('tr').find('.input-box');
			if($(this).is(":checked")) {
				inputs.removeAttr('readonly').attr("required", "required");
			}
			else {
				inputs.attr('readonly', 'readonly').removeAttr("required");
			}
		});
	});
</script>