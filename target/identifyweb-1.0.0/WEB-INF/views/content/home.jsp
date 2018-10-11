<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String resourceUrl = com.woodare.framework.utils.SysProperties.getInstance().getProperty(com.woodare.template.constant.SystemPropertiesConstant.CODE_ROOT_RESOURCE_URL, "http://open.mishua.cn");%>

<link href="<%=resourceUrl%>/resources/js/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="<%=resourceUrl%>/resources/css/content/_style.css" rel="stylesheet" type="text/css" />
<link href="<%=resourceUrl%>/resources/css/content/_input.css" rel="stylesheet" type="text/css" />
<link href="<%=resourceUrl%>/resources/web-fonts-with-css/css/fontawesome.min.css" rel="stylesheet" type="text/css" />
<style>
#cbid {
	background-color: #f2f2f0;
}
</style>
<script type="text/javascript" src="<%=resourceUrl%>/resources/js/bootstrap/bootstrap.min.js"></script>
<div >
<div class="index_content" style="overflow:hidden;">
	<div class="col-lg-8">
	<DIV class="panel index_pro"><DIV class="ppro-body clearfix">
		<div class="col-lg-12">
			<H4>${mch.name }<!-- <form action="" method="post"><input type="submit" value="刷新" class="btn btn-orange export-btn" ></form> --></H4>
			<P>机构号：${mch.mchNo }</P>
		</div>
		<div class="col-lg-12">
			<div class="ppb_main clearfix">
				<div class="col-lg-1 col-sm-1 col-xs-4">
					<I class="bg-red02"><span class="zhye"></span></I>
				</div>
				<div class="col-lg-11 col-sm-11 col-xs-8">
					<div class="index_pro_box">
						<div class="pro-txt">
							<span>账户余额(元)：</span><STRONG class="doller font30"><fmt:formatNumber value="${(fund.settleInAmt - fund.settleOutAmt) / 100 }" pattern="#,##0.00#"/></STRONG>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4 padright0">
			<div class="ppb_main clearfix">
				<div class="col-lg-3 col-sm-3 col-xs-4">
					<I class="bg-yellow02"><span class="drrj"></span></I>
				</div>
				<div class="col-lg-9 col-sm-9 col-xs-8">
					<div class="index_pro_box">
						<div class="pro-txt">
							<span>当日入金(元)：</span><STRONG class="doller02"><fmt:formatNumber value="${fund.curInAmt / 100 }" pattern="#,##0.00#"/></STRONG>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4 padright0" id="djsje">
			<div class="ppb_main clearfix">
				<div class="col-lg-3 col-sm-3 col-xs-4">
					<I class="bg-blue02"><span class="djs"></span></I>
				</div>
				<div class="col-lg-9 col-sm-9 col-xs-8">
					<div class="index_pro_box">
						<div class="pro-txt">
							<span>待结算金额(元)：</span><STRONG class="doller02"><fmt:formatNumber value="${(fund.curInAmt - fund.curOutAmt) / 100 }" pattern="#,##0.00#"/></STRONG>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4" id="creditLines">
			<div class="ppb_main clearfix">
				<div class="col-lg-3 col-sm-3 col-xs-4">
					<I class="bg-grey02"><span class="t0"></span></I>
				</div>
				<div class="col-lg-9 col-sm-9 col-xs-8">
					<div class="index_pro_box">
						<div class="pro-txt">
							<%-- <span>T0当前额度(元)：</span><STRONG class="doller02"><fmt:formatNumber value="${fund.curInAmt * mch.creditRatio / 10000 }" pattern="#,##0.00#"/></STRONG> --%>
							<span>剩余T0额度(元)：</span><STRONG class="doller02"><fmt:formatNumber value="${(fund.curInAmt * mch.creditRatio / 100 - fund.curOutAmt) / 100 }" pattern="#,##0.00#"/></STRONG>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4 padright0">
			<div class="ppb_main clearfix">
				<div class="col-lg-3 col-sm-3 col-xs-4">
					<I class="bg-red02"><span class="ycsxf"></span></I>
				</div>
				<div class="col-lg-9 col-sm-9 col-xs-8">
					<div class="index_pro_box">
						<div class="pro-txt">
							<span>T0已用额度(元)：</span><STRONG class="doller02"><fmt:formatNumber value="${fund.curOutAmt / 100 }" pattern="#,##0.00#"/></STRONG>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div></div>
	</div>
	<!--左侧内容 end-->
	<!--右侧内容 start-->
	<div class="col-lg-4">
		<!-- <div class="index_rtop clearfix">
			上次登录IP: <span class="pull-right">上次登录的时间： </span>
			<div style="clear: both;"></div>
			你的预留信息：
		</div> -->
		<div class="portlet">
			<div class="portlet-header">
				<div class="caption text-uppercase">
					<I class="fa fa-volume-down"
						style="font-size: 17px; margin-top: 2px;"></I>&nbsp;&nbsp;<span>公告</span>
				</div>
			</div>
			<div class="portlet-body">
				<ul>
					<LI><I class="fa fa-envelope colored"></I>&nbsp;<A href="#">欢迎使用快捷交易平台</A></LI>
				</ul>
			</div>
		</div>
	</div>
</div>
</div>
