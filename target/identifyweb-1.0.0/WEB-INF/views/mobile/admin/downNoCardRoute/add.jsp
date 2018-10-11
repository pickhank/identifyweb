<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<div class="page__hd">
	<h1 class="page__title">NoCardRoute</h1>
	<p class="page__desc">机构交易路由</p>
</div>
<c:if test="${not empty status}">
	<article class="weui-article" style="background:#FFF;">
		<c:if test="${status}">
		<div><i class="weui-icon-success"></i>操作成功</div>
		</c:if>
		<c:if test="${not status}">
		<div><i class="weui-icon-warn"></i>${error}</div>
		</c:if>
	</article>
</c:if>
<form action="<c:url value="/admin/downNoCardRoute/add" />" method="post">
	<input type="hidden" name="id" value="${item.id}" />
	<input type="hidden" name="expDate" value="${item.expDate}" />
	<input type="hidden" name="expTime" value="${item.expTime}" />
	<input type="hidden" name="priority" value="${item.priority}" />
	
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">路由类别</label></div>
			<div class="weui-cell__bd">
				<select name="routeCategory" required="required"  class="weui-select">
					<option value="MERCHANT" <c:if test="${item.routeCategory eq 'MERCHANT'}">selected</c:if>>按机构</option>
					<option value="CATEGORY" <c:if test="${item.routeCategory eq 'CATEGORY'}">selected</c:if>>按类别</option>
				</select>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">指定类别</label></div>
			<div class="weui-cell__bd">
				<utils:merccate mode="sel" name="mercCategory" needDefaultValue="请选择" cssName="weui-select" value="${item.mercCategory}" />
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">指定机构</label></div>
			<div class="weui-cell__bd">
				<input type="text" class="weui-input" placeholder="商户编号" name="downMchNo" value="${item.downMchNo}" />
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">交易通道</label></div>
			<div class="weui-cell__bd">
				<utils:combo mode="sel3" name="channel" extraAttr="required='required'" needDefaultValue="请选择" cssName="weui-select" value="${item.channel}"></utils:combo>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">交易商户</label></div>
			<div class="weui-cell__bd" id="channelMercNoTd">
				<input type='hidden' id='channelMercNoHidden' value="${item.channelMercNo }" />
				<select name='channelMercNo' style='width:90%;' class='weui-select'>
					<option value=''>请选择</option>
					<c:forEach var="routeMerchant" items="${routeMerchants }" varStatus="routeMerchantStatus" >
						<option value="<c:out value="${routeMerchant.channelAccNo }" />" <c:if test="${routeMerchant.channelAccNo eq item.channelMercNo }">selected="selected"</c:if> ><c:out value="${routeMerchant.channelAccName}" /></option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">代付商户</label></div>
			<div class="weui-cell__bd" id="transferMercNoTd">
				<input type='hidden' id='transferMercNoHidden' value="${item.transferMercNo }" />
				<select name='transferMercNo' style='width:90%;' class='weui-select'>
					<option value=''>请选择</option>
					<c:forEach var="routeMerchant" items="${routeMerchants }" varStatus="routeMerchantStatus" >
						<option value="<c:out value="${routeMerchant.channelAccNo }" />" <c:if test="${routeMerchant.channelAccNo eq item.transferMercNo   }">selected="selected"</c:if> ><c:out value="${routeMerchant.channelAccName}" /></option>
					</c:forEach>
				</select><p style='margin-left:10px; display: inline-block;'>为空时，默认使用交易商户代付</p>
			</div>
		</div>
		<script>
			$(function(){
				$("select[name=channel]").change(function() {
					$("#channelMercNoTd").html("<select name='channelMercNo'  style='width:90%;' class='weui-select'></select>");
					$("#transferMercNoTd").html("<select name='transferMercNo' style='width:90%;' class='weui-select' ><option value=''>请选择</option></select><p style='margin-left:10px; display: inline-block;'>为空时，默认使用交易商户代付</p>");
					// $("#transferMercNoTd select").combobox({});
					
					var selval = $(this).val();
					if(selval) {
						_WOO.c.send({
							url: '<c:url value="/admin/downNoCardRoute/getRouteMerchant" />',
							data: {
								'channel': selval
							},
							success: function(json) {
								var items = json.payload;
								if(items && items.length > 0) {
									$("#channelMercNoTd").html("<select name='channelMercNo' style='width:90%;' class='weui-select'></select>");
									$("#transferMercNoTd").html("<select name='transferMercNo' style='width:90%;' class='weui-select'><option value=''>请选择</option></select><p style='margin-left:10px; display: inline-block;'>为空时，默认使用交易商户代付</p>");
									var selDom = $("#channelMercNoTd select"), selDom1 = $("#transferMercNoTd select");
									for(var i=0; i<items.length; i++) {
										selDom.append("<option value=\"" + items[i].channelAccNo + "\">" + items[i].channelAccName + "</option>");
										selDom1.append("<option value=\"" + items[i].channelAccNo + "\">" + items[i].channelAccName + "</option>");
									}
								}
								//$("#channelMercNoTd select").combobox({});
								// $("#transferMercNoTd select").combobox({});
							}
						});
					}
				});
			});
		</script>
	</div>
	<div style="text-align: center; background: #FFF; padding: 8px; border-bottom: 1px solid #267ad2;">
		<input type="submit" value="提交" class="weui-btn weui-btn_mini weui-btn_primary"></input>
	</div>
</form>