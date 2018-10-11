<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div title="交易管理" data-options="iconCls:'fa fa-gear'" style="padding:10px;">
	<ul class="easyui-datalist" fit=true border=false>
		<li>
			<i class="fa fa-credit-card"></i>
			<a class="zhyk-nav-link" data-href="<c:url value="/content/downNoCardInvoice/index"/>" onclick="openTabs(this);return false;">快捷交易</a>
		</li>
		<li>
			<i class="fa fa-paypal"></i>
			<a class="zhyk-nav-link" data-href="<c:url value="/content/downMerchantDeposit/index"/>" onclick="openTabs(this);return false;">代付交易</a>
		</li>
		<li>
			<i class="fa fa-money"></i>
			<a class="zhyk-nav-link" data-href="<c:url value="/content/downMerchantBalance/index"/>" onclick="openTabs(this);return false;">收支明细</a>
		</li>
		<%-- <li>
			<i class="tree-file"></i>
			<a class="zhyk-nav-link" data-href="<c:url value="/content/downNoCardInvoice/sum"/>" onclick="openTabs(this);return false;">快捷交易汇总</a>
		</li> --%>
	</ul>
</div>
<div title="系统设置" data-options="iconCls:'tree-folder'" style="padding:10px;">
	<ul class="easyui-datalist" fit=true border=false>
		<li>
			<i class="fa fa-user-secret"></i>
			<a class="zhyk-nav-link" data-href="<c:url value="/content/systemUser/resetPassword"/>" onclick="openTabs(this);return false;">修改密码</a>
		</li>
		<li>
			<i class="fa fa-anchor"></i>
			<a class="zhyk-nav-link" onclick="confirmExit();return false;">退出系统</a>
		</li>
	</ul>
</div>