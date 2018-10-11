<%@ tag import="com.woodare.core.web.taglibs.DicTaglibHelper" %>
<%@ tag import="com.woodare.core.web.common.viewdata.CodeAndName" %>
<%@ tag pageEncoding='UTF-8' trimDirectiveWhitespaces="true" language="java"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="id" type="java.lang.String" required="false" rtexprvalue="false" description="下拉框ID" %>
<%@attribute name="name" type="java.lang.String" required="true" rtexprvalue="false" description="下拉框名称" %>
<%@attribute name="dicItems" type="java.util.Collection" required="false" rtexprvalue="true" description="列表值" %>
<%@attribute name="extraAttr" type="java.lang.String" required="false" rtexprvalue="false" description="额外属性" %>
<%@attribute name="isMulti" type="java.lang.Boolean" required="false" rtexprvalue="false" description="是否为多选" %> 
<%@attribute name="size" type="java.lang.Integer" required="false" rtexprvalue="false" description="显示size" %> 
<%@attribute name="linkageName" type="java.lang.String" required="false" rtexprvalue="false" description="被联动下拉框" %>
<%@attribute name="dicParentCode" type="java.lang.String" required="false" rtexprvalue="true" description="字段表父节点CODE值" %>
<%@attribute name="value" type="java.lang.String" required="false" rtexprvalue="true" description="当前值" %>
<%@attribute name="needDefault" type="java.lang.Boolean" required="false" rtexprvalue="false" description="是否需要默认选项" %>
<%@attribute name="needDefaultValue" type="java.lang.String" required="false" rtexprvalue="false" description="默认的选项值" %>


<%@attribute name="needRights" type="java.lang.Boolean" required="false" rtexprvalue="false" description="需要权限" %>
<%@attribute name="dicType" type="java.lang.String" required="false" rtexprvalue="false" description="权限类型" %>

<c:if test="${empty needDefaultValue }" >
	<c:set var="needDefaultValue" value="--请选择--" />
</c:if>
<c:if test="${empty needRights }" >
	<c:set var="needRights" value="false" />
</c:if>
<c:if test="${empty dicType }" >
	<c:set var="dicType" value="" />
</c:if>
<c:if test="${empty id }" >
	<c:set var="id" value="${name }" />
</c:if>
<c:set var="dicParentCode" value="${dicParentCode }" scope="request" />
<c:set var="dicItems" value="" scope="request" />
<c:if test="${empty dicItems and not empty dicParentCode}" >
<%
	java.util.List<CodeAndName> dicItems = DicTaglibHelper.getOptions((String)request.getAttribute("dicParentCode"));
	request.setAttribute("dicItems", dicItems);
%>
</c:if>

<c:if test="${isMulti}" >
<%
	java.lang.String[] values = value.split(",");
%>
</c:if>
<select ${extraAttr } 
	<c:if test="${not empty size}">size='${size }' </c:if>
	<c:if test="${not empty isMulti and isMulti}">multiple='multiple' </c:if> 
	<c:if test="${empty needDefault or needDefault}">defaultVal="${needDefaultValue }" </c:if> 
	<c:if test="${not empty linkageName }">onchange="common.linkageDroplist(this, '${linkageName }', ${needRights}, '${dicType}' )"</c:if> 
	id="${id }" name="${name }" parentCode="${parentCode }" >
	<c:if test="${empty needDefault or needDefault }"><option value="" >${needDefaultValue }</option></c:if>
	<c:if test="${not empty dicItems }">
		
		<c:forEach var="item" items="${dicItems }" varStatus="itemStatus" >
			<c:set var="opts" value="" />
			<c:choose>
				<c:when test="${isMulti}">
					<c:forTokens items="${value }" delims="," var="valItem">
						<c:choose>
							<c:when test="${item.code eq valItem}">
								<c:set var="opts" value="selected='selected'" />
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</c:forTokens>
				</c:when>
				<c:otherwise>
					<c:if test="${item.code eq value}">
						<c:set var="opts" value="selected='selected'" />
					</c:if>
				</c:otherwise>
			</c:choose>
			<option value="${item.code }"  ${opts } >
				<c:out value='${item.name }' />
			</option>
		</c:forEach>
	</c:if>
</select>
