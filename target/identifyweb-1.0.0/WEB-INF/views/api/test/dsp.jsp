<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
	$(function() {
		bindForm();
		
		$(".refer-fields").click(function() {
			var that = $(this);
			var selVal = $(this).val();
			$("." + that.attr('name')).hide();
			$("." + that.attr('name')).find('input').attr('disabled', 'disabled');

			$("." + selVal).show();
			$("." + selVal).find('input').removeAttr('disabled');
		})
	});
</script>
<div id="tabs-down" style="overflow-y: auto;">

	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">0</span>绑定支付参数</legend> 
		<form action="#" method="post" class="enc-bind-form">
			<table class="editor">
				<tr>
					<td class="label">接口版本:</td>
					<td class="field"><input type="text" id="versionNo" value="1" /></td>
				</tr>
				<tr>
					<td class="label">商户号:</td>
					<td class="field"><input type="text" id="mchNo" value="A0001" /></td>
				</tr>
				<tr>
					<td class="label">加密KEY:</td>
					<td class="field"><input type="text"  id="encKey" value="1234567812345678" /></td>
				</tr>
				<tr>
					<td class="label">签名Key:</td>
					<td class="field"><input type="text" id="signKey" value="1234" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="绑定" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">1</span>银行卡要素验证</legend> 
		<form action="<spring:url value="/ws/trans/dsp/makeOrder"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label"><font style="color:red">*</font> 订单号:</td>
					<td class="field"><input type="text" required name="tradeNo" value="<fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyyMMddHHmmssSSS"/>" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 订单日期:</td>
					<td class="field"><input type="text" required name="orderDate" value="<fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyyMMddHHmmss"/>" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 支付方式:</td>
					<td class="field">
						<input type="radio" style="width: 30px;" class="refer-fields" checked="checked" name="dspMode" id="C102"  value="C102" /><label for="C102" >C102（姓名+身份证）</label><br>
						
						<input type="radio" style="width: 30px;" class="refer-fields" name="dspMode" id="C112" value="C112" /><label for="C112" >C112（姓名+银行卡号）</label><br>
						<input type="radio" style="width: 30px;" class="refer-fields" name="dspMode" id="C115" value="C115" /><label for="C115" >C115（姓名+身份证+银行卡号）</label><br>
						<input type="radio" style="width: 30px;" class="refer-fields" name="dspMode" id="C116" value="C116" /><label for="C116" >C116（姓名+身份证+手机号+银行卡号）</label><br>
						
						<input type="radio" style="width: 30px;" class="refer-fields" name="dspMode" id="C123" value="C123" /><label for="C123" >C123（姓名+身份证+手机号）</label><br>
					</td>
				</tr>
				<tr class="dspMode C102 C112 C115 C116 C123">
					<td class="label">姓名:</td>
					<td class="field">
						<input type="text" name="holderName" value="卢峰" /><br>
					</td>
				</tr>
				<tr class="dspMode C112 C115 C116 ">
					<td class="label">银行卡号:</td>
					<td class="field">
						<input type="text" name="cardNo" value="6217001370031080435" /><br>
					</td>
				</tr>
				<tr class="dspMode C102 C115 C116 C123">
					<td class="label">证件类型:</td>
					<td class="field">
						<input type="text" name="identifyType" value="0" /><br>
						0=身份证（暂只支持身份证-0）
					</td>
				</tr>
				<tr class="dspMode C102 C115 C116 C123">
					<td class="label">证件号码:</td>
					<td class="field">
						<input type="text" name="identifyNo" value="321323198606045338" /><br>
					</td>
				</tr>
				<tr class="dspMode C116 C123">
					<td class="label">手机号:</td>
					<td class="field">
						<input type="text" name="mobile" value="18951812474" /><br>
					</td>
				</tr>
				<tr>
					<td class="label">备注:</td>
					<td class="field"><input type="text" name="remark" value="1" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	
	
</div>