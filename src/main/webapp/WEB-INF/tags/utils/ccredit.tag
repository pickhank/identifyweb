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
<c:if test="${mode eq 'sel1' }">
	<select id="${id }" name="${name }" class="${cssName }" ${extraAttr }>
		<option value="">${needDefaultValue }</option>
		<option value="DTFT_PROD" <c:if test="${value eq 'DTFT_PROD'}">selected</c:if>>DTFT生产</option>
		<option value="HLB_PROD" <c:if test="${value eq 'HLB_PROD'}">selected</c:if>>HLB生产</option>
	</select>
</c:if>
<c:if test="${mode eq 'sel' }">
	<select id="${id }" name="${name }" class="${cssName }" ${extraAttr }>
		<option value="">${needDefaultValue }</option>
		<option value="DTFT_PROD" <c:if test="${value eq 'DTFT_PROD'}">selected</c:if>>DTFT生产</option>
		<option value="DTFT_TEST" <c:if test="${value eq 'DTFT_TEST'}">selected</c:if>>DTFT测试</option>
		<option value="HLB_PROD" <c:if test="${value eq 'HLB_PROD'}">selected</c:if>>HLB生产</option>
		<option value="HLB_CREDIT" <c:if test="${value eq 'HLB_CREDIT'}">selected</c:if>>HLB测试</option>
	</select>
</c:if>
<c:if test="${mode eq 'lbl' }">
	<c:choose>
		<c:when test="${value eq 'DTFT_PROD'}">DTFT生产</c:when>
		<c:when test="${value eq 'DTFT_TEST'}">DTFT测试</c:when>
		<c:when test="${value eq 'HLB_PROD'}">HLB生产</c:when>
		<c:when test="${value eq 'HLB_CREDIT'}">HLB测试</c:when>
		<c:otherwise>${value}</c:otherwise>
	</c:choose>
</c:if>
