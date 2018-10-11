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
</style>

<link href="<c:url value='/resources/js/bootstrap/bootstrap-select.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/js/bootstrap/bootstrap.min.css' />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap/bootstrap-select.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap/bootstrap.min.js' />"></script>

<form action="<c:url value="/admin/downMerchantFundAccount/add" />" method="post">
	<input type="hidden" name="id" value="${item.id}" />
	<c:if test="${not empty error}">
		<div class="liner-error">
			<div class="liner-box-right">${error}</div>
		</div>
	</c:if>
	<div class="ms-row">
		<div class="ms-col-5 ms-border">
			<div class="ms-header">基本信息</div>
			<table class="liner-box">
				<tr>
					<td class="liner-box-left">所属代理商:</td>
					<td class="liner-box-right">
						<select name="agentId" class="input-box">
							<c:forEach items="${agents}" var="agent">
								<option value="${agent.id}" <c:if test="${item.agentId eq agent.id}">selected</c:if>>${agent.name} - ${agent.orderNo}</option>
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
					<td class="liner-box-left required">开通产品:</td>
					<td class="liner-box-right">
						<input type="hidden" name="supportPayType" value="${item.supportPayType}" />
						<select id="supportPayTypeItems" cssName="selectpicker bla bla bli" multiple data-live-search='true'>
							<option value="01" <c:if test="${fn:contains(item.supportPayType,'01')==true}">selected</c:if>>网银支付</option>
							<option value="02" <c:if test="${fn:contains(item.supportPayType,'02')==true}">selected</c:if>>银联在线</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">交易费率:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="无卡费率" name="feeRatio" value="${item.feeRatio}" />‰
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">每笔交易费:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="T1每笔代付费" name="addFeeAmt" value="${item.addFeeAmt}" />元
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">代付费率:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="D0每笔代付费" name="drawFeeRatio" value="${item.drawFeeRatio}" />‰
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">每笔代付费:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="T1每笔代付费" name="addDrawFeeAmt" value="${item.addDrawFeeAmt}" />元
					</td>
				</tr>
			</table>
		</div>
		
		<div class="ms-col-5 ms-border">
			<div class="ms-header">配置信息</div>
			<table class="liner-box">
				<tr>
					<td class="liner-box-left required">机构号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="商户编号" name="mchNo" value="${item.mchNo}" readonly />
					</td>
				</tr>
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
					<td class="liner-box-left required">异步通知:</td>
					<td class="liner-box-right">
						<select name="enableNotify" class="input-box">
							<option value="true" <c:if test="${empty item.enableNotify or not item.enableNotify}">selected</c:if>>不启用</option>
							<option value="false" <c:if test="${not empty item.enableNotify and item.enableNotify}">selected</c:if>>启用</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">信任IPs:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="授信比例" name="limitIps" value="${item.limitIps}" />
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">授信比例:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="授信比例" name="creditRatio" value="${item.creditRatio}" />（%）
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
				<tr>
					<td class="liner-box-left required">代付Key:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" style="width: 180px;" placeholder="支付Key" name="payKey" value="${item.payKey}" />
					</td>
				</tr>
			</table>
		</div>
	</div>
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
			$('#confirmBtn').click(function() {
				$('input[name=supportPayType]').val($('#supportPayTypeItems').val() ? $('#supportPayTypeItems').val().join(',') : "");
			});
		});
	</script>
	<div class="liner-box-one-line">
		<input type="submit" id="confirmBtn" value="提交" class="btn" />
	</div>
	<br><br><br>
</form>