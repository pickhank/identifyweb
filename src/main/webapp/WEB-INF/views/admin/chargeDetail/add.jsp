<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<link href="<c:url value='/resources/js/bootstrap/bootstrap-select.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/js/bootstrap/bootstrap.min.css' />" rel="stylesheet" type="text/css" />

<style>
.input-box-short {
	width:43%;
}
.liner-box-left {
	padding: 2px 10px;
}
.liner-box-right {
	padding: 2px 10px;
}
.input-box, .box-body label {
    color: #333;
    font-family: 'Microsoft YaHei', Arial, Helvetica, sans-serif;
    font-size: 0.9rem;
}
td {
	vertical-align: top;
}
.box-body .btn {
    padding: 5px 20px;
    text-align: center;
    cursor: pointer;
    display: inline-block;
    border-radius: 3px;
    font-size: 12px;
    height: 27px;
    box-sizing: border-box;
    vertical-align: middle;
}

</style>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap/bootstrap-select.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap/bootstrap.min.js' />"></script>

<form class="search-form clear" method="post" enctype="multipart/form-data" action="<c:url value="/admin/chargeDetail/add" />">
	<input type="hidden" name="id" value="${item.id}">

	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">充值机构:</td>
			<td class="liner-box-right">
				<select name="mchNo" class="selectpicker bla bla bli" data-live-search='true' >
					<c:forEach var="item" items="${merchants }" varStatus="status">
						<option value="${item.mchNo}">
							&nbsp;&nbsp;${item.mchNo}
							&nbsp;&nbsp;${item.name}
						</option>
					</c:forEach>
				</select>
				<script>
					$(function() {
						$('.selectpicker').selectpicker({
					        'selectedText': 'cat'
					    });
					});
				</script>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">金额（元）:</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="rearMoneyYuan" style="width: 80px" value="${item.rearMoneyYuan }" onkeyup="value=value.replace(/[^\d]/g,'')">
			</td>
		</tr>
		<!-- <tr>
			<td class="liner-box-left required">审核时间:</td>
			<td class="liner-box-right">
				<div class="ms-col-fix-100 clear">
					<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="yyyy/MM/dd" name="auditDate"/>
					<script>
                        $(".date-picker").datepicker($.datepicker.regional[ "zh-CN" ]);
                        $("select[name='dateCate']").change(function(){
                            $(".custom-d-cate").css("visibility", ($(this).val() == 'CUSTOM' ? "visible" : "hidden"));
                        }).change();
					</script>
				</div>
			</td>
		</tr> -->
		<tr>
			<td class="liner-box-left required">备注:</td>
			<td class="liner-box-right">
				<textarea class="input-box" style="height: 180px;" name="remark">${item.remark }</textarea>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left ">&nbsp;</td>
			<td class="liner-box-right">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>
