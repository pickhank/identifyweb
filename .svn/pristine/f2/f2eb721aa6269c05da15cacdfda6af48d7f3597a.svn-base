<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp" />
<style>
.input-box-short {
	width: 43%;
}

.liner-box-left {
	padding: 2px 10px;
}

.liner-box-right {
	padding: 0;
}

.select2-container .select2-selection--multiple{
	min-height:17px;
}

table tr {    
	line-height: 30px;
}
</style>
<script>
	$.fn.datebox.defaults.formatter = function(date) {
		return moment(date).format("YYYY/MM/DD");
	};
	$.fn.datebox.defaults.parser = function(s){
		var t = moment(s, "YYYY/MM/DD").toDate();
		if (!isNaN(t)){
			return t;
		} else {
			return new Date();
		}
	};

	$(function() {
		$(".date-picker").datepicker($.datepicker.regional[ "zh-CN" ]);
		$('.datetime-picker').timepicker({ 'timeFormat': 'H:i:s' });
	});
</script>
<form action="<c:url value="/admin/downDspInvoiceRoute/add" />" method="post">
	<input type="hidden" name="id" value="${item.id}" />
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		
		<tr>
			<td class="liner-box-left required">通道:</td>
			<td class="liner-box-right">
				<utils:dcombo mode="sel" name="channel" extraAttr="required='required'" needDefaultValue="请选择" cssName="input-box" value="${item.channel}"></utils:dcombo>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left">交易商户:</td>
			<td class="liner-box-right" id="channelMercNoTd">
				<input type='hidden' id='channelMercNoHidden' value="${item.channelMercNo }" />
				<select name='channelMercNo' style='width:50%;' class='input-box'>
					<option value=''>请选择</option>
					<c:forEach var="routeMerchant" items="${routeMerchants }" varStatus="routeMerchantStatus" >
						<option value="<c:out value="${routeMerchant.channelAccNo }" />" <c:if test="${routeMerchant.channelAccNo eq item.channelMercNo }">selected="selected"</c:if> ><c:out value="${routeMerchant.channelAccName}" /></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		 
		<script>
			$(function(){
				
				$("select[name=channel]").change(function() {
					$("#channelMercNoTd").html("<select name='channelMercNo' labelPosition='top' style='width:90%;' class='input-box'></select>");
					
					var selval = $(this).val();
					if(selval) {
						_WOO.c.send({
							url: '<c:url value="/admin/downDspInvoiceRoute/getRouteMerchant" />',
							data: {
								'channel': selval
							},
							success: function(json) {
								var items = json.payload;
								if(items && items.length > 0) {
									$("#channelMercNoTd").html("<select name='channelMercNo' labelPosition='top' style='width:90%;' class='input-box'></select>");
									
									var selDom = $("#channelMercNoTd select"), selDom1 = $("#transferMercNoTd select");
									for(var i=0; i<items.length; i++) {
										selDom.append("<option value=\"" + items[i].channelAccNo + "\">" + items[i].channelAccName + "</option>");
									}
								}
							}
						});
					}
				});
			});
		</script>
		<tr>
			<td class="liner-box-left required">生效日期:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box date-picker" required="required" placeholder="yyyy/MM/dd" name="startDate" value="<utils:dateFormat value='${item.startDate}' />" style="width:120px"/>
				~
				<input type="text" class="input-box date-picker" required="required" placeholder="yyyy/MM/dd" name="endDate" value="<utils:dateFormat value='${item.endDate}' />" style="width:120px"/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">生效时间:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box datetime-picker" style="width:80px;" data-options="showSeconds:true" placeholder="hh:mm:ss" required="required" name="startTime" value="<utils:dateFormat value='${item.startTime}' />" ></input>
				~
				<input type="text" class="input-box datetime-picker" style="width:80px;" data-options="showSeconds:true" placeholder="hh:mm:ss" required="required" name="endTime" value="<utils:dateFormat value='${item.endTime}' />" ></input>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">优先级:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" required="required" placeholder="优先级" name="priority" value="${item.priority}" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left">机构编号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="机构编号" name="mchNo" value="${item.mchNo}" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left ">商户类别:</td>
			<td class="liner-box-right">
				<utils:merccate mode="sel" name="mercCategory" needDefaultValue="请选择" cssName="input-box" value="${item.mercCategory}" />
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn btn-noe" />
			</td>
		</tr>
	</table>
</form>