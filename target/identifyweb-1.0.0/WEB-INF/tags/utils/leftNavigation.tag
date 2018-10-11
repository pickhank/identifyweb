<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="subNavigations" type="java.util.Map" required="false" rtexprvalue="true" description="the list of subNavigations" %>
<%@attribute name="actions" type="java.util.Map" required="false" rtexprvalue="true" description="the list of action" %>
<c:if test="${not empty subNavigations }" >
	<c:forEach items="${subNavigations}" var="entry">
		<ul id="submenus_${entry.key}" style="display: none" parentmenuId="${entry.key}">
			<c:if test="${not empty entry.value }">
				<c:forEach var="item" items="${entry.value }" varStatus="itemStatus" >
					<c:if test="${empty item.pageUrl }">
						<li><em><a href="javascript:void(0);" rel="operate_${item.id }">${item.name }</a></em></li>
					</c:if>
					<c:if test="${not empty item.pageUrl }">
						<c:url var="pageUrl" value="${item.pageUrl }" >
							<c:param name="noiframe" value="true"></c:param>
						</c:url>
						<li><em><a href="${pageUrl }" class="menu-item menu-sub-nav" target="main">${item.name }</a></em></li>
					</c:if>
				</c:forEach>
			</c:if>
		</ul>
	</c:forEach>
</c:if>
<div id="operateitem" class="operateMenu" style="display: none;">
</div>
<div style="display: none;">
	<c:if test="${not empty actions }" >
		<c:forEach items="${actions}" var="entry">
			<div id="operate_${entry.key}" parentmenuId="${entry.key}">
				<ul>
					<c:if test="${not empty entry.value }">
						<c:forEach var="item" items="${entry.value }" varStatus="itemStatus" >
							<c:if test="${empty item.pageUrl }">
								<li><a href="javascript:void(0);" onclick="alert('未定义路由');">${item.name }</a></li>
							</c:if>
							<c:if test="${not empty item.pageUrl }">
								<c:url var="pageUrl" value="${item.pageUrl }" >
									<c:param name="noiframe" value="true"></c:param>
								</c:url>
								<li><a href="${pageUrl }" class="menu-item menu-action" target="main">${item.name }</a></li>
							</c:if>
						</c:forEach>
					</c:if>
				</ul>
			</div> 
		</c:forEach>
	</c:if>
</div>
