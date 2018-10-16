<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div title="交易信息" data-options="iconCls:'tree-folder'" style="padding:10px;">
	<ul class="easyui-datalist" fit=true border=false>
		<li>
			<i class="tree-file"></i>
			<a class="zhyk-nav-link" data-href="<c:url value="/merchant/invoice/merchant"/>" onclick="openTabs(this);return false;">交易统计</a>
		</li>
		<li>
			<i class="tree-file"></i>
			<a class="zhyk-nav-link" data-href="<c:url value="/merchant/invoice/sub"/>" onclick="openTabs(this);return false;">子商户交易统计</a>
		</li>
	</ul>
</div>
<div title="系统设置" data-options="iconCls:'tree-folder'" style="padding:10px;">
	<ul class="easyui-datalist" fit=true border=false>
		<li>
			<i class="tree-file"></i>
			<a class="zhyk-nav-link" onclick="confirmExit();return false;">退出系统</a>
		</li>
	</ul>
</div>