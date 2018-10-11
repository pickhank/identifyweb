<%@ tag pageEncoding='UTF-8' trimDirectiveWhitespaces="true" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils" %>

<%@attribute name="data" type="com.woodare.template.web.viewdata.category.CategoryViewData" required="true"
	rtexprvalue="true" description="Category"%>
<%@attribute name="loopIndex" type="java.lang.Integer" required="true" rtexprvalue="true"
	description="pagination method"%>
<%@attribute name="topId" type="java.lang.String" required="true" rtexprvalue="true"
	description="pagination method"%>
<%@attribute name="parentId" type="java.lang.String" required="true" rtexprvalue="true"
	description="pagination method"%>
<div class="loop-${loopIndex}">
	${data.name}
	<a>编辑</a>
	<a>删除</a>
	<a href="javascript:addCategory('${topId}', '${data.id}')">新增子科目aa</a>
	<c:if test="${not empty data.subItems}">
		<c:forEach var="sub" items="${data.subItems}" varStatus="status">
			<utils:category data="${sub}" loopIndex="${loopIndex + 1}" topId="${topId}" parentId="${data.id}"></utils:category>
		</c:forEach>
	</c:if>
</div>