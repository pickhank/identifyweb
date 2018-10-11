<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>

<script type="text/javascript" src="<c:url value='/resources/js/common/jquery.md5.js' />"></script>
<style>
.input-box-short {
	width:43%;
}
.liner-box-left {
	padding: 2px 10px;
}
.liner-box-right {
	padding: 0;
}
.ms-row .ms-border {
    min-height: 220px;
}
.liner-box-left {
    vertical-align: top;
    color: #333;
    font-family: 'Microsoft YaHei', Arial, Helvetica, sans-serif;
    font-size: 0.9rem;
        line-height: 34px;
}

.input-box, form label {
    color: #333;
    font-family: 'Microsoft YaHei', Arial, Helvetica, sans-serif;
    font-size: 0.9rem;
}
.btn {
    line-height: 30px;
    padding: 0 10px;
    /* margin: 0; */
    height: 30px;
}
</style>

<link href="<c:url value='/resources/js/bootstrap/bootstrap-select.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/js/bootstrap/bootstrap.min.css' />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap/bootstrap-select.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap/bootstrap.min.js' />"></script>

<form action="<c:url value="/admin/downMerchantFundAccount/frozen" />" method="post">
	<input type="hidden" name="id" value="${item.id}" />
	<c:if test="${not empty error}">
		<div class="liner-error">
			<div class="liner-box-right">${error}</div>
		</div>
	</c:if>
	<div class="ms-row">
	<div class="ms-col-12 ">
		<div class="ms-header">基本信息</div>
		<table class="liner-box">
			<tr>
				<td class="liner-box-left required">机构编码:</td>
				<td class="liner-box-right">
					<input type="hidden" name="mchNo" value="${item.mchNo}" />
					<input type="text" class="input-box" placeholder="机构编码" required="required" disabled="disabled" value="${item.mchNo}" />
				</td>
			</tr>
			<tr>
				<td class="liner-box-left required">机构名称:</td>
				<td class="liner-box-right">
					<input type="hidden" name="mchName" value="${item.mchName}" />
					<input type="text" class="input-box" placeholder="机构名称" required="required" disabled="disabled" value="${item.mchName}" />
				</td>
			</tr>
			<tr>
				<td class="liner-box-left required">T1可用额:</td>
				<td class="liner-box-right">
					<input type="hidden" name="settleInAmt" value="${item.settleInAmt}" />
					<input type="hidden" name="settleOutAmt" value="${item.settleOutAmt}" />
					<input type="text" class="input-box" placeholder="" disabled="disabled" value="<fmt:formatNumber value="${(item.settleInAmt - item.settleOutAmt) / 100}" pattern="#,##0.00#"/>" />元
				</td>
			</tr>
			<tr>
				<td class="liner-box-left required">T0可结额:</td>
				<td class="liner-box-right">
					<input type="hidden" name="curInAmt" value="${item.curInAmt}" />
					<input type="hidden" name="curOutAmt" value="${item.curOutAmt}" />
					<input type="text" class="input-box" placeholder="" disabled="disabled" value="<fmt:formatNumber value="${(item.curInAmt - item.curOutAmt) / 100}" pattern="#,##0.00#"/>" />元
				</td>
			</tr>
			<tr>
				<td class="liner-box-left required">原冻结金额:</td>
				<td class="liner-box-right">
					<input type="hidden" name="frozenAmt" value="${item.frozenAmt}" />
					<input type="text" class="input-box" placeholder="" disabled="disabled" value="<fmt:formatNumber value="${item.frozenAmt / 100}" pattern="#,##0.00#"/>" />元
				</td>
			</tr>
			<tr>
				<td class="liner-box-left required">冻结金额:</td>
				<td class="liner-box-right">
					<input type="text" class="input-box" placeholder="冻结金额" name="curFrozenAmt" required="required" value="${item.curFrozenAmt}" />元<br>
					<p>整数表示增加冻结款项，负数解除冻结款项</p>
				</td>
			</tr>
			<tr>
				<td class="liner-box-left required">备注:</td>
				<td class="liner-box-right">
					<textarea name="remark" class="input-box"  style="height: 60px; width: 90%;" required="required">${remark }</textarea>
				</td>
			</tr>
		</table>
	</div>
	</div>
	<div class="liner-box-one-line">
		<input type="submit" id="confirmBtn" value="提交" class="btn" />
	</div>
	<br><br><br>
</form>