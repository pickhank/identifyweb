<%@tag import="com.woodare.template.helper.cache.PasswayOrgAccSettings"%>
<%@tag import="com.woodare.template.jpa.persistence.data.passwayorgaccsetting.PasswayOrgAccSettingData"%>
<%@tag import="com.woodare.core.web.taglibs.DicTaglibHelper" %>
<%@tag import="com.woodare.core.web.common.viewdata.CodeAndName" %>
<%@tag pageEncoding='UTF-8' trimDirectiveWhitespaces="true" language="java"  %>
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
<%
java.util.List<PasswayOrgAccSettingData> accSettingItems = PasswayOrgAccSettings.listItems();
request.setAttribute("accSettingItems", accSettingItems);
%>
<c:set var="accSettingItems" value="${accSettingItems}" scope="request" />
<c:if test="${empty needDefaultValue }" ><c:set var="needDefaultValue" value="全部" /></c:if>
<c:if test="${empty mode }" ><c:set var="mode" value="all" /></c:if>
<c:if test="${mode eq 'transfer' }"><select id="${id }" name="${name }" class="${cssName }">
		<option value="" >${needDefaultValue }</option>
		<c:forEach var="item" items="${requestScope.accSettingItems }" varStatus="status" >
			<option value="<c:out value="${item.channelKey }" />" <c:if test="${value eq item.channelKey }">selected</c:if>><c:out value="${item.egwDisName}" /></option>
		</c:forEach>
	</select>
</c:if>
<c:if test="${mode eq 'all' }"><select id="${id }" name="${name }" class="${cssName }">
		<option value="" >${needDefaultValue }</option>
		<c:forEach var="item" items="${requestScope.accSettingItems }" varStatus="status" >
			<option value="<c:out value="${item.channelKey }" />" <c:if test="${value eq item.channelKey }">selected</c:if>><c:out value="${item.egwDisName}" /></option>
		</c:forEach>
	</select>
</c:if>
<c:if test="${mode eq 'lbl' }"><c:forEach var="item" items="${requestScope.accSettingItems}" varStatus="status" >
		<c:if test="${value eq item.channelKey}">${item.egwDisName}</c:if>
	</c:forEach>
</c:if>
