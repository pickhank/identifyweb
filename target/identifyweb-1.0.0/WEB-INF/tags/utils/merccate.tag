<%@tag import="com.woodare.template.jpa.model.data.EnumMercCategory"%>
<%@ tag pageEncoding='UTF-8' trimDirectiveWhitespaces="true" language="java"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="mode" type="java.lang.String" required="false" rtexprvalue="false" description="显示方式" %>
<%@attribute name="id" type="java.lang.String" required="false" rtexprvalue="false" description="下拉框ID" %>
<%@attribute name="name" type="java.lang.String" required="false" rtexprvalue="false" description="下拉框名称" %>
<%@attribute name="cssName" type="java.lang.String" required="false" rtexprvalue="true" description="样式定义值" %>
<%@attribute name="value" type="java.lang.String" required="false" rtexprvalue="true" description="当前值" %>
<%@attribute name="needDefaultValue" type="java.lang.String" required="false" rtexprvalue="false" description="默认的选项值" %>
<%@attribute name="extraAttr" type="java.lang.String" required="false" rtexprvalue="false" description="默认的选项值" %>
<%-- 
<c:set var="_allMercCates" value="${_allMercCates}" scope="request" /> --%>

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
	<%
	request.setAttribute("_mercCates", EnumMercCategory.allItems);
/* 	request.setAttribute("_mercCates1", EnumMercCategory.maps.get("1"));
	request.setAttribute("_mercCates2", EnumMercCategory.maps.get("2"));
	request.setAttribute("_mercCates3", EnumMercCategory.maps.get("3"));
	request.setAttribute("_mercCates4", EnumMercCategory.maps.get("4")); */
	%>
	<select id="${id }" name="${name }" class="${cssName }" ${extraAttr }>
		<option value="" >${needDefaultValue }</option>
		<c:forEach var="item" items="${requestScope._mercCates }" varStatus="status" >
			<option value="<c:out value="${item.code }" />" <c:if test="${fn:contains(value,item.code)==true}">selected</c:if>><c:out value="${item.name}" /></option>
		</c:forEach>
	</select>
</c:if>
<c:if test="${mode eq 'lbl' }">
	<%
	request.setAttribute("_allMercCates", EnumMercCategory.allItems);
	%>
	<c:set var="values" value="${value}${','}"></c:set>
	<c:forEach var="item" items="${requestScope._allMercCates}" varStatus="status" >
		<c:set var="itemcode" value="${item.code}${','}"></c:set>
		<c:if test="${fn:contains(values,itemcode) == true }">${item.name}</c:if>
	</c:forEach>
</c:if>
