<%@ tag import="com.woodare.core.web.taglibs.DicTaglibHelper" %>
<%@ tag import="com.woodare.core.web.common.viewdata.CodeAndName" %>
<%@ tag pageEncoding='UTF-8' trimDirectiveWhitespaces="true" language="java"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="mode" type="java.lang.String" required="false" rtexprvalue="false" description="显示方式" %>
<%@attribute name="id" type="java.lang.String" required="false" rtexprvalue="false" description="下拉框ID" %>
<%@attribute name="name" type="java.lang.String" required="false" rtexprvalue="false" description="下拉框名称" %>
<%@attribute name="cssName" type="java.lang.String" required="false" rtexprvalue="true" description="样式定义值" %>
<%@attribute name="value" type="java.lang.String" required="false" rtexprvalue="true" description="当前值" %>
<%@attribute name="needDefaultValue" type="java.lang.String" required="false" rtexprvalue="false" description="默认的选项值" %>
<%@attribute name="extraAttr" type="java.lang.String" required="false" rtexprvalue="false" description="默认的选项值" %>

<c:if test="${empty extraAttr }" >
	<c:set var="extraAttr" value="" />
</c:if>
<c:if test="${empty needDefaultValue }" >
	<c:set var="needDefaultValue" value="全部" />
</c:if>
<c:if test="${empty mode }" >
	<c:set var="mode" value="sel" />
</c:if>
<c:if test="${mode eq 'sel' }">
	<select id="${id }" name="${name }" class="${cssName }" ${extraAttr }>
		<option value="" >${needDefaultValue }</option>
		<option value="01" <c:if test="${value eq '01'}">selected</c:if>>网银</option>
		<option value="02" <c:if test="${value eq '02'}">selected</c:if>>银联在线</option>
		<option value="03" <c:if test="${value eq '03'}">selected</c:if>>转账码</option>
		<option value="10" <c:if test="${value eq '10'}">selected</c:if>>银联扫码</option>
		<option value="11" <c:if test="${value eq '11'}">selected</c:if>>银联条码</option>
		<option value="12" <c:if test="${value eq '12'}">selected</c:if>>QQ钱包</option>
		<option value="12" <c:if test="${value eq '13'}">selected</c:if>>支付宝扫码</option>
		<option value="12" <c:if test="${value eq '14'}">selected</c:if>>支付宝H5</option>
		<option value="12" <c:if test="${value eq '15'}">selected</c:if>>微信H5</option>
	</select>
</c:if>
<c:if test="${mode eq 'lbl' }">
	<c:choose>
		<c:when test="${value eq '01'}">网银</c:when>
		<c:when test="${value eq '02'}">银联在线</c:when>
		<c:when test="${value eq '03'}">转账码</c:when>
		<c:when test="${value eq '10'}">银联扫码</c:when>
		<c:when test="${value eq '11'}">银联条码</c:when>
		<c:when test="${value eq '12'}">QQ钱包</c:when>
		<c:when test="${value eq '13'}">支付宝扫码</c:when>
		<c:when test="${value eq '14'}">支付宝H5</c:when>
		<c:when test="${value eq '15'}">微信H5</c:when>
		<c:otherwise>${value}</c:otherwise>
	</c:choose>
</c:if>
