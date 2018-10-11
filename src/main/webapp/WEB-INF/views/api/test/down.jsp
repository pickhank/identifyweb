<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
	$(function() {
		bindForm();
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
		<legend><span class="step-counter ui-widget-header ui-corner-all">1</span>同名进出-下单</legend> 
		<form action="<spring:url value="/ws/trans/credit/makeOrder"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label"><font style="color:red">*</font> 订单号:</td>
					<td class="field"><input type="text" name="tradeNo" value="<fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyyMMddHHmmssSSS"/>" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 订单日期:</td>
					<td class="field"><input type="text" name="orderDate" value="<fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyyMMddHHmmss"/>" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 金额:</td>
					<td class="field"><input type="text" name="price" value="10.00" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 支付方式:</td>
					<td class="field">
						<input type="radio" style="width: 30px;" name="payType" id="payType21" checked="checked" value="21" /><label for="payType21" >同名进出</label><br>
						
					</td>
				</tr>
				<tr>
					<td class="label">支付卡号:</td>
					<td class="field">
						<input type="text" name="payCardNo" value="4367480059593577" /><br>
					</td>
				</tr>
				<tr>
					<td class="label">交易费率（千分位）:</td>
					<td class="field">
						<input type="text" name="feeRatio" value="4.0" /><br>
					</td>
				</tr>
				<tr>
					<td class="label">单笔手续费（元）:</td>
					<td class="field">
						<input type="text" name="addFeeAmt" value="0.5" /><br>
					</td>
				</tr>
				<tr>
					<td class="label">姓名:</td>
					<td class="field">
						<input type="text" name="settleName" value="卢峰" /><br>
					</td>
				</tr>
				<tr>
					<td class="label">身份证号:</td>
					<td class="field">
						<input type="text" name="settleCertId" value="321323198606045338" /><br>
					</td>
				</tr>
				<tr>
					<td class="label">结算卡号:</td>
					<td class="field">
						<input type="text" name="settleCardNo" value="6217001370031080435" /><br>
					</td>
				</tr>
				<tr>
					<td class="label">结算人手机:</td>
					<td class="field">
						<input type="text" name="settleMobile" value="18951812474" /><br>
					</td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 订单描述:</td>
					<td class="field"><input type="text" name="description" value="订单描述" /></td>
				</tr>
				<tr>
					<td class="label">异步通知地址:</td>
					<td class="field"><input type="text" name="notifyUrl" value="http://www.baidu.com" /></td>
				</tr>
				<tr>
					<td class="label">同步回调地址:</td>
					<td class="field"><input type="text" name="callbackUrl" value="http://www.baidu.com" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">1</span>借记卡支付</legend> 
		<form action="<spring:url value="/ws/trans/nocard/makeOrder"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label"><font style="color:red">*</font> 订单号:</td>
					<td class="field"><input type="text" name="tradeNo" value="<fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyyMMddHHmmssSSS"/>" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 订单日期:</td>
					<td class="field"><input type="text" name="orderDate" value="<fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyyMMddHHmmss"/>" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 金额:</td>
					<td class="field"><input type="text" name="price" value="10.00" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 支付方式:</td>
					<td class="field">
						<input type="radio" style="width: 30px;" name="payType" id="payType00" checked="checked" value="" /><label for="payType00" >默认</label><br>
						<input type="radio" style="width: 30px;" name="payType" id="payType02" value="02" /><label for="payType02" >银联在线</label><br>
						<input type="radio" style="width: 30px;" name="payType" id="payType01" value="01" /><label for="payType01" >网银</label><br>
						<input type="radio" style="width: 30px;" name="payType" id="payType03" value="03" /><label for="payType03" >转账码</label><br>
						<input type="radio" style="width: 30px;" name="payType" id="payType10" value="10" /><label for="payType10" >银联码-主扫</label><br>
						<input type="radio" style="width: 30px;" name="payType" id="payType11" value="11" /><label for="payType11" >银联码-被扫</label><br>
						
						<input type="radio" style="width: 30px;" name="payType" id="payType12" value="12" /><label for="payType12" >QQ-主扫</label><br>
						<input type="radio" style="width: 30px;" name="payType" id="payType13" value="13" /><label for="payType13" >支付宝-主扫</label><br>
						<input type="radio" style="width: 30px;" name="payType" id="payType13" value="14" /><label for="payType14" >支付宝-H5</label><br>
						<input type="radio" style="width: 30px;" name="payType" id="payType13" value="15" /><label for="payType15" >微信-H5</label><br>
					</td>
				</tr>
				<tr>
					<td class="label">指定银行（仅网银有效）:</td>
					<td class="field">
						<input type="text" name="payBankCode" value="" /><br>
						<p>常用银行：农业银行-01030000; 中国银行-01040000; 建设银行-01050000; 交通银行-03010000;</p>
					</td>
				</tr>
				<tr>
					<td class="label">支付卡号:</td>
					<td class="field">
						<input type="text" name="payCardNo" value="" /><br>
					</td>
				</tr>
				<tr>
					<td class="label">买家姓名:</td>
					<td class="field">
						<input type="text" name="payerName" value="卢峰" /><br>
					</td>
				</tr>
				<tr>
					<td class="label">支付卡号:</td>
					<td class="field">
						<input type="text" name="payIdCard" value="123456789" /><br>
					</td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 商品名称:</td>
					<td class="field"><input type="text" name="subject" value="测试名称" /></td>
				</tr>
				<tr>
					<td class="label">订单描述:</td>
					<td class="field"><input type="text" name="description" value="订单描述" /></td>
				</tr>
				
				<tr>
					<td class="label">异步通知地址:</td>
					<td class="field"><input type="text" name="notifyUrl" value="http://www.baidu.com" /></td>
				</tr>
				<tr>
					<td class="label">同步回调地址:</td>
					<td class="field"><input type="text" name="callbackUrl" value="http://www.baidu.com" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">2</span>借记卡支付查询</legend> 
		<form action="<spring:url value="/ws/trans/nocard/orderQuery"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label">订单号:</td>
					<td class="field"><input type="text" name="tradeNo" value="" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">3</span>机构余额查询</legend> 
		<form action="<spring:url value="/ws/trans/nocard/accBalQuery"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">4</span>代付付款</legend> 
		<form action="<spring:url value="/ws/trans/nocard/transferApply"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label"><font style="color:red">*</font> 订单号:</td>
					<td class="field"><input type="text" name="tradeNo" value="<fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyyMMddHHmmssSSS"/>" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 订单日期:</td>
					<td class="field"><input type="text" name="orderDate" value="<fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyyMMddHHmmss"/>" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 清算类型:</td>
					<td class="field">
						<input type="radio" style="width: 30px;" name="mode" checked="checked" id="modes1" value="S1" /><label for="modes1" >T1清算</label><br>
						<input type="radio" style="width: 30px;" name="mode" id="modes0" value="S0" /><label for="modes0" >T0清算</label>
					</td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 金额:</td>
					<td class="field"><input type="text" name="price" value="10.00" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 收款银行卡号:</td>
					<td class="field"><input type="text" name="accCardNo" value="6217001370031080435" /></td>
				<tr>
					<td class="label"><font style="color:red">*</font> 收款人:</td>
					<td class="field"><input type="text" name="accName" value="卢峰" /></td>
				</tr>
				</tr>
				<tr>
					<td class="label">收款卡预留电话:</td>
					<td class="field"><input type="text" name="accTel" value="" /></td>
				</tr>
				<tr>
					<td class="label">收款人身份证号:</td>
					<td class="field"><input type="text" name="accIdCard" value="" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 异步通知地址:</td>
					<td class="field"><input type="text" name="notifyUrl" value="http://www.baidu.com" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 用途:</td>
					<td class="field"><input type="text" name="purpose" value="" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">5</span>代付结果查询</legend> 
		<form action="<spring:url value="/ws/trans/nocard/transferQuery"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label"><font style="color:red">*</font> 订单号:</td>
					<td class="field"><input type="text" name="tradeNo" value="" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
</div>