<%@tag import="com.woodare.template.helper.EnumHelper"%>
<%@ tag pageEncoding='UTF-8' trimDirectiveWhitespaces="true" language="java"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="mode" type="java.lang.String" required="false" rtexprvalue="false" description="显示方式" %>
<%@attribute name="key" type="java.lang.String" required="false" rtexprvalue="false" description="下拉框ID" %>
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
<c:set var="_key" value="${key }" scope="request" />
<c:if test="${mode eq 'sel' }">
	<%
		request.setAttribute("_enums", EnumHelper.getByKey((String)request.getAttribute("_key")));
	%>
	<select id="${id }" name="${name }" class="${cssName }" ${extraAttr }>
		<option value="" >${needDefaultValue }</option>
		<c:forEach var="item" items="${requestScope._enums }" varStatus="status" >
			<option value="<c:out value="${item.code }" />" <c:if test="${fn:contains(value,item.code)==true}">selected</c:if>><c:out value="${item.name}" /></option>
		</c:forEach>
	</select>
</c:if>
<c:if test="${mode eq 'lbl' }">
	<c:if test="${not empty value }">
		<c:set var="_value" value="${value }" scope="request" />
	<%
		java.util.Map<String, String> code2Names = EnumHelper.getMapByKey((String)request.getAttribute("_key"));
		String names = "";
		String[] values = ((String)request.getAttribute("_value")).split(",");
		for(String value : values) {
			names += "," + (code2Names.get(value) == null ? value : code2Names.get(value));
		}
		request.setAttribute("_names", names.substring(1));
	%>
		<c:out value="${_names }" />
	</c:if>
</c:if>
