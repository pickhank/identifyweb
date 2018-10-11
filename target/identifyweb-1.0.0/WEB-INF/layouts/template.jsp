<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
String resourceUrl = com.woodare.framework.utils.SysProperties.getInstance().getProperty(com.woodare.template.constant.SystemPropertiesConstant.CODE_ROOT_RESOURCE_URL, "http://open.mishua.cn");%>

<!DOCTYPE html>
<html style="width: 100%; height: 100%;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8" />
<title>业务管理平台</title><%-- 
<link href="<%=resourceUrl%>/resources/js/easyui/themes/metro/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=resourceUrl%>/resources/js/easyui/themes/icon.css" rel="stylesheet" type="text/css" /> --%>
<link rel="stylesheet" type="text/css" href="<%=resourceUrl%>/resources/jquery-easyui-1.5.5/themes/gray/easyui.css?1">
<link rel="stylesheet" type="text/css" href="<%=resourceUrl%>/resources/jquery-easyui-1.5.5/themes/icon.css">


<link href="<%=resourceUrl%>/resources/css/common.css" rel="stylesheet" type="text/css" />
<link href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet" /> 
  
<!-- load included css files -->
<tiles:importAttribute name="styles" scope="request" ignore="true" />
<c:if test="${not empty styles }">
	<c:forEach var="cssStyle" items="${styles}">
		<link rel="stylesheet" type="text/css" href="<%=resourceUrl%>/${cssStyle}"></link>
	</c:forEach>
</c:if>
<script type="text/javascript" src="<%=resourceUrl%>/resources/jquery-easyui-1.5.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/jquery-easyui-1.5.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/jquery-easyui-1.5.5/locale/easyui-lang-zh_CN.js"></script>

<%-- <script type="text/javascript" src="<%=resourceUrl%>/resources/js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/js/easyui/easyui-lang-zh_CN.js"></script> --%>
<style>
.easyui-layout .window-proxy-mask, .window-mask {
	background: #333;
}
.tree-icon {
	font: normal normal normal 12px/1 FontAwesome;
	font-size: 18px !important;
	background: none;
}
.tree-folder:BEFORE {
}
.tree-folder-open:BEFORE {
}
.tree-file:BEFORE {
}
body,.panel-body {
    background: #f9f3f3;
}
</style> 
<script>
	function confirmExit(){
		$.messager.confirm('系统提示', '是否确定退出本系统', function(r){
			if (r){
				window.location.href = "<c:url value='/system/user/logout'/>";
			}
		});
	}

	
	var Popup = {
			_defd: null,
			show: function(url, title) {
				this._defd = $.Deferred();
				$("#popup-wrapper").window({
					title: title,
					modal:true,
					closed: false
				});
				var $pop = $(".iframe-popup");
				$pop.contents().find("body").html("正在加载...")
				$pop.attr("src", url);
				return this._defd.promise();
			},
			hide: function(data) {
				$("#popup-wrapper").window("close");
				$(".iframe-popup").attr("src", "");
				if (this._defd && this._defd.state() === 'pending') {
					if (data) {
						this._defd.resolve(data);	
					} else {
						this._defd.reject();
					}
				}
			}
		};
	
	function renderMenu(root, nodes) {
		root.tree({
			data: nodes
		});
	}
</script>
<!-- load included js files -->
<tiles:importAttribute name="scripts" scope="request" ignore="true" />
<c:if test="${not empty scripts }">
	<c:forEach var="js" items="${scripts}">
		<script type="text/javascript" src="<%=resourceUrl%>/${js}"></script>
	</c:forEach>
</c:if>
</head>

<!-- 开始弹出框 -->
	<div id="popup-wrapper" class="easyui-window" title="详细" data-options="iconCls:'icon-save',collapsible:false,minimizable:false,closed:true,resizable:false"
		style="width: 80%; height: 70%; min-width: 480px; max-width: 1000px;">
		<iframe class="iframe-popup iframe-box" src="javascript:void(0);" width="100%" height="100%" border=0></iframe>
	</div>
<!-- 结束弹出框 -->
<body class="easyui-layout" data-options="fit:'true'">
	<div data-options="region:'north',border:'false'"
		style="background: #2d3e50; color: #fff; direction: ltr;">
		<table style="float:right; text-align:right; ">
			<tr>
				<td style="line-height:30px;">
					当前用户: <font style="color: red"><sec:authentication property="principal.username" /></font>,欢迎您登录业务管理平台！ 
					<a href="#" class="easyui-menubutton" data-options="iconCls:'icon-back'" onclick="confirmExit();">注销</a>
					
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'west',split:true,border:'false'" title="导航菜单" style="width: 200px;">
		<div class="easyui-accordion" fit=true border=false id="leftMenuId">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<div data-options="region:'center'">
		<div class="easyui-tabs" fit=true id="body-wapper-box" border=false>
			<div title="首页" style="padding:10px">
				<iframe class="iframe-box" src="<c:url value='/working' />"></iframe>
			</div>
		</div>
	</div>
	<div data-options="region:'south'" style="height: 31px; text-align: center; line-height: 28px;     background: rgb(45, 62, 80);color: rgb(255, 255, 255);">
		<span class="copyright">Copyright © 2010 ~ 2018</span>
	</div>
	<script>
	function openTabs($el){
		$el = $($el);
		var title = $el.text();
		var href = $el.data("href");
		if ($('#body-wapper-box').tabs('exists',title)){
			$('#body-wapper-box').tabs('select', title);
		} else {
			$('#body-wapper-box').tabs('add', {
				title: title,
				iconCls: $el.closest('.datagrid-cell').find('i.fa').attr('class'),
				content: '<iframe class="iframe-box" src="' + href + '"></iframe>',
				closable: true,
				extractor: function(data) {
					data = $.fn.panel.defaults.extractor(data);
					return data;
				}
			});
		}
	}
	</script>
</body>
</html>