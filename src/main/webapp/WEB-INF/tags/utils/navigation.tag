<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="navigations" type="java.util.List" required="false" rtexprvalue="true" description="the list of navigation" %>
<ul id="topmenu">
	<c:if test="${not empty navigations }">
		<c:forEach var="item" items="${navigations }" varStatus="itemStatus" >
			<li>
				<a id="mainmenu_${item.id }" href="javascript:void(0);" rel="${item.id }">${item.name }</a>
			</li>
		</c:forEach>
	</c:if>
</ul>
