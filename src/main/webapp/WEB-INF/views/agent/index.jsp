<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div title="总览" data-options="iconCls:'tree-folder'" style="padding:10px;">
	<ul class="easyui-datalist" fit=true border=false>
		<li>
			<i class="fa fa-user-secret"></i>
			<a class="zhyk-nav-link" data-href="<c:url value="/agent/downMerchant/index"/>" onclick="openTabs(this);return false;">机构管理</a>
		</li>
		<li>
			<i class="fa fa-credit-card"></i>
			<a class="zhyk-nav-link" data-href="<c:url value="/agent/downNoCardInvoice/index"/>" onclick="openTabs(this);return false;">快捷交易</a>
		</li>
		<li>
			<i class="fa fa-paypal"></i>
			<a class="zhyk-nav-link" data-href="<c:url value="/agent/downMerchantDeposit/index"/>" onclick="openTabs(this);return false;">代付交易</a>
		</li>
		<li>
			<i class="fa fa-money"></i>
			<a class="zhyk-nav-link" data-href="<c:url value="/agent/downMerchantBalance/index"/>" onclick="openTabs(this);return false;">分润交易</a>
		</li>
		<li>
			<i class="fa fa-bitcoin"></i>
			<a class="zhyk-nav-link" data-href="<c:url value="/agent/agentUser/resetPassword"/>" onclick="openTabs(this);return false;">修改密码</a>
		</li>
		<li>
			<i class="fa fa-anchor"></i>
			<a class="zhyk-nav-link" onclick="confirmExit();return false;">退出系统</a>
		</li>
	</ul>
</div>