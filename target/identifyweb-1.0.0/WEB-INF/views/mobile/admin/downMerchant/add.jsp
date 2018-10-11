<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="page__hd">
	<h1 class="page__title">Merchant</h1>
	<p class="page__desc">机构</p>
</div>
<c:if test="${not empty status}">
	<article class="weui-article" style="background:#FFF;">
		<c:if test="${status}">
		<div><i class="weui-icon-success"></i>操作成功</div>
		</c:if>
		<c:if test="${not status}">
		<div><i class="weui-icon-warn"></i>${error}</div>
		</c:if>
	</article>
</c:if>
<form action="<c:url value="/admin/downMerchant/add" />" method="post">
	<input type="hidden" name="id" value="${item.id}" /></input>
	<input type="hidden" name="agentId" value="${item.agentId}" />
	<input type="hidden" name="phone" value="${item.phone}" />
	<input type="hidden" name="mercCategory" value="${item.mercCategory}" />
	<input type="hidden" name="enableBalChgFlg" value="${item.enableBalChgFlg}" />
	<input type="hidden" name="enableNotify" value="${item.enableNotify}" />
	<input type="hidden" name="accCardNo" value="${item.accCardNo}" />
	<input type="hidden" name="accCardHolder" value="${item.accCardHolder}" />
	<input type="hidden" name="accBankName" value="${item.accBankName}" />
	<input type="hidden" name="encKey" value="${item.encKey}" />
	<input type="hidden" name="signKey" value="${item.signKey}" />
	
	
	<c:if test="${not empty item.id }">
	<div class="ms-row ms-border" style="display:none; margin: 0 0.5%;">
		<div class="ms-header">配置产品</div>
		<c:if test="${not empty item.id }">
			<table class="list-wapper" style="margin-bottom: 5px;">
				<tr class="list-header">
					<td width="110px">名称</td>
					<td width="100px">交易费率(‰)</td>
					<td width="100px">单笔费用(元)</td>
					<td width="100px">垫资费率(‰)</td>
					<td width="100px">单笔代付费(元)</td>
					
					<td class="special">单笔最小金额(元)</td>
					<td class="special">单笔最大金额(元)</td>
					<td class="special">日累计限额(元)</td>
					<!-- <td>状态</td> -->
				</tr>
				<c:forEach var="item" items="${products}" varStatus="status">
					<c:set var="inputflg" value="required" />
					<c:set var="checkflg" value="checked='checked'" />
					<c:if test="${not empty item.status and item.status eq 'PENDING' }">
						<c:set var="inputflg" value="readonly='readonly' " />
						<c:set var="checkflg" value="" />
					</c:if>
					<tr class="list-item">
						<td width="110px">
							<input type="hidden" name="productTypeArr" value="${item.productType }">
							<input type="checkbox" ${checkflg }  class="input-check" style='width: 20px;' name="selProductTypeArr" id="productType${item.productType }" value="${item.productType }">
							<label for="productType${item.productType }"><c:out value="${item.productType.desc }" /></label>
						</td>
						<td width="100px">
							<input type="text" ${inputflg } class="input-box" placeholder="单位：千分之" name="feeRatioArr" value="${item.feeRatio}" />
						</td>
						<td width="100px">
							<input type="text" ${inputflg } class="input-box" placeholder="单位：元" name="addFeeAmtArr" value="${item.addFeeAmt}" />
						</td>
						<td width="100px">
							<input type="text" ${inputflg } class="input-box" placeholder="单位：千分之 " name="drawFeeRatioArr" value="${item.drawFeeRatio}" />
						</td>
						<td width="100px">
							<input type="text" ${inputflg } class="input-box" placeholder="单位：元" name="addDrawFeeAmtArr" value="${item.addDrawFeeAmt}" />
						</td>
						<td class="special">
							<input type="text" ${inputflg } class="input-box" placeholder="空或者0，则不限额" name="minPerAmtArr" value="${item.minPerAmt}" />
						</td>
						<td class="special">
							<input type="text" ${inputflg } class="input-box" placeholder="空或者0，则不限额" name="maxPerAmtArr" value="${item.maxPerAmt}" />
						</td class="special">
						<td class="special">
							<input type="text" ${inputflg } class="input-box" placeholder="空或者0，则不限额" name="maxTotalAmtArr" value="${item.maxTotalAmt}" />
						</td>
						<%-- <td>
							<select name="status" required ${inputflg }  class="input-box">
								<option value="PENDING" <c:if test="${item.status eq 'PENDING'}">selected</c:if>>未激活</option>
								<option value="ACTIVE" <c:if test="${item.status eq 'ACTIVE'}">selected</c:if>>正常</option>
								<option value="HOLD" <c:if test="${item.status eq 'HOLD'}">selected</c:if>>暂停</option>
							</select>
						</td> --%>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
	<br />
	</c:if>
	
	<c:if test="${empty item.id}">
		<div class="weui-cells__title">新建</div>
	</c:if>
	<c:if test="${not empty item.id}">
		<div class="weui-cells__title">编辑</div>
	</c:if>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">机构编号</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="商户编号-自动生成" name="mchNo" value="${item.mchNo}" readonly>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">机构名称</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="机构名称" name="name" value="${item.name}">
			</div>
		</div>
		<div class="weui-cell weui-cell_select weui-cell_select-after">
			<div class="weui-cell__hd"><label class="weui-label">状态</label></div>
			<div class="weui-cell__bd">
				<select name="status" class="weui-select">
					<option value="PENDING" <c:if test="${item.status eq 'PENDING'}">selected</c:if>>未使用</option>
					<option value="ACTIVE" <c:if test="${item.status eq 'ACTIVE'}">selected</c:if>>使用中</option>
					<option value="HOLD" <c:if test="${item.status eq 'HOLD'}">selected</c:if>>暂停使用</option>
				</select>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">信任IP</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" name="limitIps" value="${item.limitIps}">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">T0授信比(%)</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" name="creditRatio" value="${item.creditRatio}">
			</div>
		</div>
	</div>
	<div style="text-align: center; background: #FFF; padding: 8px; border-bottom: 1px solid #267ad2;">
		<input type="submit" value="提交" class="weui-btn weui-btn_mini weui-btn_primary"></input>
	</div>
</form>