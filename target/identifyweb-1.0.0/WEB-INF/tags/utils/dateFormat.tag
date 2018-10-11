<%@ tag pageEncoding='UTF-8' trimDirectiveWhitespaces="true" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@attribute name="value" type="java.lang.String" required="true" rtexprvalue="true"%>
<c:if test="${not empty value}">
	<c:if test="${fn:length(value) eq 6 }">
		${fn:substring(value, 0, 2)}:${fn:substring(value, 2, 4)}:${fn:substring(value, 4, 6)}
	</c:if>
	<c:if test="${fn:length(value) eq 8 }">
		${fn:substring(value, 0, 4)}/${fn:substring(value, 4, 6)}/${fn:substring(value, 6, 8)}
	</c:if>
	<c:if test="${fn:length(value) eq 14 }">
		<c:out value="${fn:substring(value, 0, 4)}/${fn:substring(value, 4, 6)}/${fn:substring(value, 6, 8)}" />
		<c:out value=" " />
		<c:out value="${fn:substring(value, 8, 10)}:${fn:substring(value, 10, 12)}:${fn:substring(value, 12, 14)}" />
	</c:if>
</c:if>