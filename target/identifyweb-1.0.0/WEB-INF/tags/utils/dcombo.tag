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
<!-- 路由配置&外发商户 -->
<c:if test="${mode eq 'sel' }">
	<select id="${id }" name="${name }" class="${cssName }" ${extraAttr }>
		<option value="" >${needDefaultValue }</option>
		<option value="CPCN" <c:if test="${value eq 'CPCN'}">selected</c:if>>中金-CPCN</option>
		<option value="BANK" <c:if test="${value eq 'BANK'}">selected</c:if>>人行-BANK</option>
	</select>
</c:if>
<c:if test="${mode eq 'lbl' }">
	<c:choose>
		<c:when test="${value eq 'CPCN'}">CPCN</c:when>
		<c:when test="${value eq 'BANK'}">BANK</c:when>
		<c:otherwise>${value}</c:otherwise>
	</c:choose>
</c:if>
