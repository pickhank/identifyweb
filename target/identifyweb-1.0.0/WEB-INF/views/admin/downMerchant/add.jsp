<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>

<script type="text/javascript" src="<c:url value='/resources/js/common/jquery.md5.js' />"></script>
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
.ms-row .ms-border {
    min-height: 220px;
}
.liner-box-left {
    vertical-align: top;
    color: #333;
    font-family: 'Microsoft YaHei', Arial, Helvetica, sans-serif;
    font-size: 0.9rem;
        line-height: 34px;
}

.input-box, form label {
    color: #333;
    font-family: 'Microsoft YaHei', Arial, Helvetica, sans-serif;
    font-size: 0.9rem;
}
.btn {
    line-height: 30px;
    padding: 0 10px;
    /* margin: 0; */
    height: 30px;
}
input.input-box[readonly], select.input-box[readonly] {
	background-color: rgb(235, 235, 228);
}
td.special {
background-color:#c35c7a;
}
</style>

<link href="<c:url value='/resources/js/bootstrap/bootstrap-select.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/js/bootstrap/bootstrap.min.css' />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap/bootstrap-select.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap/bootstrap.min.js' />"></script>

<form action="<c:url value="/admin/downMerchant/add" />" method="post">
	<input type="hidden" name="id" value="${item.id}" />
	
	<c:if test="${not empty error}">
		<div class="liner-error">
			<div class="liner-box-right">${error}</div>
		</div>
	</c:if>
	<div class="ms-row">
		<c:if test="${not empty item.id }">
		<div class="ms-row ms-border" style="margin: 0 0.5%;">
			<div class="ms-header">配置产品</div>
			<c:if test="${not empty item.id }">
				<table class="list-wapper" style="margin-bottom: 5px;">
					<tr class="list-header">
						<td width="200px">名称</td>
						<!-- <td width="100px">交易费率(‰)</td> -->
						<td width="100px">单笔费用(元)</td>
						<!-- <td width="100px">垫资费率(‰)</td>
						<td width="100px">单笔代付费(元)</td>
						
						<td class="special">单笔最小金额(元)</td>
						<td class="special">单笔最大金额(元)</td>
						<td class="special">日累计限额(元)</td> -->
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
								<input type="hidden" name="productTypeArr" value="${item.dspMode }">
								<input type="checkbox" ${checkflg }  class="input-check" style='width: 20px;' name="selProductTypeArr" id="productType${item.dspMode }" value="${item.dspMode }">
								<label for="productType${item.dspMode }"><c:out value="${item.dspMode.desc }" /></label>
							</td>
							<%-- <td width="100px">
								<input type="text" ${inputflg } class="input-box" placeholder="单位：千分之" name="feeRatioArr" value="${item.feeRatio}" />
							</td> --%>
							<td width="100px">
								<input type="text" ${inputflg } class="input-box" placeholder="单位：元" name="addFeeAmtArr" value="${item.addFeeAmt}" />
							</td>
							<%-- <td width="100px">
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
							</td> --%>
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
		<div class="ms-col-5 ms-border">
			<div class="ms-header">基本信息</div>
			<table class="liner-box">
				<tr>
					<td class="liner-box-left required">机构号:</td>
					<td class="liner-box-right">
						<input type="hidden" class="input-box" name="mchNo" value="${item.mchNo}" />
						<input type="text" class="input-box" placeholder="机构编号" value="${item.mchNo}" disabled />
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">所属代理商:</td>
					<td class="liner-box-right">
						<select name="agentId" class="input-box">
							<option value="">--请选择--</option>
							
							<c:forEach items="${agents}" var="agent">
								<option value="${agent.agentNo}" <c:if test="${item.agentId eq agent.agentNo}">selected</c:if> >${agent.name} - ${agent.agentNo}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">类别:</td>
					<td class="liner-box-right">
						<utils:merccate mode="sel" name="mercCategory" needDefaultValue="请选择" cssName="input-box" value="${item.mercCategory}" extraAttr="required='required' " />
					</td>
				</tr>
				
				<tr>
					<td class="liner-box-left required">预支权限:</td>
					<td class="liner-box-right">
						<select name="preAuthDspFlg" class="input-box">
							<option value="false" <c:if test="${empty item.preAuthDspFlg or not item.preAuthDspFlg}">selected</c:if>>关闭</option>
							<option value="true" <c:if test="${not empty item.preAuthDspFlg and item.preAuthDspFlg}">selected</c:if>>打开</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">机构名称:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="机构名称" required="required" name="name" value="${item.name}" />
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">联系电话:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="电话" name="phone" value="${item.phone}" />
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">结算卡号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="结算卡号" name="accCardNo" value="${item.accCardNo}" />
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">结算户名:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="结算户名" name="accCardHolder" value="${item.accCardHolder}" />
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">结算卡银行:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="结算卡银行" name="accBankName" value="${item.accBankName}" />
					</td>
				</tr>
			</table>
		</div>
		<div class="ms-col-5 ms-border">
			<div class="ms-header">配置信息</div>
			<table class="liner-box">
				<tr>
					<td class="liner-box-left required">状态:</td>
					<td class="liner-box-right">
						<select name="status" class="input-box">
							<option value="PENDING" <c:if test="${item.status eq 'PENDING'}">selected</c:if>>未使用</option>
							<option value="ACTIVE" <c:if test="${item.status eq 'ACTIVE'}">selected</c:if>>使用中</option>
							<option value="HOLD" <c:if test="${item.status eq 'HOLD'}">selected</c:if>>暂停使用</option>
						</select>
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
					<td class="liner-box-left required">异步通知:</td>
					<td class="liner-box-right">
						<select name="enableNotify" class="input-box">
							<option value="false" <c:if test="${empty item.enableNotify or not item.enableNotify}">selected</c:if>>不启用</option>
							<option value="true" <c:if test="${not empty item.enableNotify and item.enableNotify}">selected</c:if>>启用</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">信任IPs:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="逗号分割" name="limitIps" value="${item.limitIps}" />
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">T0授信比:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="授信比例" name="creditRatio" value="${item.creditRatio}" />%
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">加密Key:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" style="width: 180px;" placeholder="加密Key" name="encKey" value="${item.encKey}" />
						<a href="javascript:void(0);" class="btn btn-orange" onclick="genkey();">自动生成</a>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">签名Key:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" style="width: 180px;" placeholder="签名Key" name="signKey" value="${item.signKey}" />
					</td>
				</tr>
				<%-- <tr>
					<td class="liner-box-left required">代付Key:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" style="width: 180px;" placeholder="支付Key" name="payKey" value="${item.payKey}" />
					</td>
				</tr> --%>
				<tr>
					<td class="liner-box-left">&nbsp;</td>
					<td class="liner-box-right">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">&nbsp;</td>
					<td class="liner-box-right">
						&nbsp;
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="liner-box-one-line">
		<input type="submit" id="confirmBtn" value="提交" class="btn" />
	</div>
	<br/><br/><br/>
</form>
<script type="text/javascript">
	function genkey () {
		var d = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789$%^&*()#@!_+=<>.,/?";
		var r = Math.random() * 80;
		var s = 30;
		while((s--)>0){
			c = Math.round(Math.random() * 80);
			r += d.substring(c, c + 1);
		}
		var k = $.md5(r.substring(0, 20)).toUpperCase();
		$('input[name=encKey]').val(k.substring(0, 16));
		$('input[name=signKey]').val(k.substring(16));
		$('input[name=payKey]').val($.md5(r.substring(20)).toUpperCase().substring(1, 17));
	}
	$(function () {
		$('.selectpicker').selectpicker({
	        'selectedText': 'cat'
	    });
		
		$('input[name=selProductTypeArr]').click(function() {
		    var trObj = $(this).closest('tr');
			var inputs = trObj.find('.input-box');
			if($(this).is(":checked")) {
				inputs.removeAttr('readonly').attr("required", "required");
				// trObj.find("input").removeAttr("disabled");
			}
			else {
				inputs.attr('readonly', 'readonly').removeAttr("required");
				// trObj.find("input").attr("disabled", "disabled");
			}
		});
		// $('input[name=selProductTypeArr]checked').click();
	});
</script>