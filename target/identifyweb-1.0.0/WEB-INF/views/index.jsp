<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div title="微信设置" data-options="iconCls:'tree-folder'" style="padding:10px;">
	<ul class="easyui-datalist" fit=true border=false>
		<li>
			<i class="tree-file"></i>
			<a class="zhyk-nav-link" data-href="<c:url value="/admin/wxAccount/index"/>" onclick="openTabs(this);return false;">微信商户列表</a>
		</li>
		<li>
			<i class="tree-file"></i>
			<a class="zhyk-nav-link" data-href="<c:url value="/admin/wxAccount/add"/>" onclick="openTabs(this);return false;">新建微信商户</a>
		</li>
		<li>
			<i class="tree-file"></i>
			<a class="zhyk-nav-link" data-href="<c:url value="/admin/routeMap/index"/>" onclick="openTabs(this);return false;">微信路由列表</a>
		</li>
		<li>
			<i class="tree-file"></i>
			<a class="zhyk-nav-link" data-href="<c:url value="/admin/routeMap/add"/>" onclick="openTabs(this);return false;">新建微信路由</a>
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