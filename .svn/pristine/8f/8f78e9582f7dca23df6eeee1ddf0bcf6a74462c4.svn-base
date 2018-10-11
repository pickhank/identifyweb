<%@tag import="com.woodare.template.helper.cache.Banks"%>
<%@tag import="com.woodare.template.helper.cache.Banks.BankItem"%>
<%@tag pageEncoding='UTF-8' trimDirectiveWhitespaces="true" language="java"  %>
<%@taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="mode"             type="java.lang.String" required="false" rtexprvalue="false" description="显示方式" %>
<%@attribute name="id"               type="java.lang.String" required="false" rtexprvalue="false" description="下拉框ID" %>
<%@attribute name="name"             type="java.lang.String" required="false" rtexprvalue="false" description="下拉框名称" %>
<%@attribute name="cssName"          type="java.lang.String" required="false" rtexprvalue="true"  description="样式定义值" %>
<%@attribute name="value"            type="java.lang.String" required="false" rtexprvalue="true"  description="当前值" %>
<%@attribute name="needDefaultValue" type="java.lang.String" required="false" rtexprvalue="false" description="默认的选项值" %>
<%@attribute name="extra"            type="java.lang.String" required="false" rtexprvalue="true"  description="其他属性设置" %>
<%
	java.util.List<BankItem> _banks = Banks.banks;
	request.setAttribute("combobanks", _banks);
%>
<c:set var="combobanks" value="${combobanks}" scope="request" />
<c:if test="${empty needDefaultValue }" ><c:set var="needDefaultValue" value="全部" /></c:if>
<c:if test="${empty mode }" ><c:set var="mode" value="all" /></c:if>
<c:if test="${mode eq 'sel' }"><select id="${id }" name="${name }" class="${cssName }" ${extra }>
		<%-- <option value="" >${needDefaultValue }</option> --%>
		<c:forEach var="item" items="${requestScope.combobanks }" varStatus="status" >
			<option value="<c:out value="${item.code }" />" <c:if test="${fn:contains(value,item.code)==true}">selected</c:if>><c:out value="${item.name}" /></option>
		</c:forEach>
	</select>
</c:if>
<c:if test="${mode eq 'lbl' }"><c:forEach var="item" items="${requestScope.combobanks}" varStatus="status" >
		<c:if test="${fn:contains(value,item.code)==true}">${item.name}，</c:if>
	</c:forEach>
</c:if>
