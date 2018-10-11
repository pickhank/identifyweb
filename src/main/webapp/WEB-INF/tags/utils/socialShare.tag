<%@ tag pageEncoding='UTF-8' trimDirectiveWhitespaces="true" language="java"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@attribute name="url" type="java.lang.String" required="false" rtexprvalue="true" description="The default target url" %>
<%@attribute name="summary" type="java.lang.String" required="true" rtexprvalue="true" description="Description" %>
<%@attribute name="title" type="java.lang.String" required="true" rtexprvalue="true" description="Title" %>
<%@attribute name="pic" type="java.lang.String" required="false" rtexprvalue="true" description="Picture" %>
<%@attribute name="pics" type="java.lang.String" required="false" rtexprvalue="true" description="The list of picture" %>

<% jspContext.setAttribute("newline1", "\r\n"); %>
<% jspContext.setAttribute("newline2", "\n"); %>

<c:if test="${empty url }">
	<c:set var="url" value="http://e.weibo.com/harmonycity" />
</c:if>
<c:if test="${not empty summary }">
	<c:set var="summary" value="${fn:replace(summary, newline1, ' ')}" />
	<c:set var="summary" value="${fn:replace(summary, newline2, ' ')}" />
</c:if>
<c:if test="${not empty title }">
	<c:set var="title" value="${fn:replace(title, newline1, ' ')}" />
	<c:set var="title" value="${fn:replace(title, newline2, ' ')}" />
</c:if>
<c:if test="${not empty pics }">
	<c:set var="pics" value="${fn:replace(pics, newline1, ',')}" />
	<c:set var="pics" value="${fn:replace(pics, newline2, ',')}" />
</c:if>

<div style="display:none;" id="jiathisDivImagesId" >
	<c:if test="${not empty pic }">
		<img src="${pic }" class="JIATHIS_IMG_OK" />
	</c:if>
	<c:if test="${not empty pics }">
		<c:forTokens items="${pics }" delims="," var="picitem">
			<img src="${picitem }" class="JIATHIS_IMG_OK" />
		</c:forTokens>
	</c:if>
</div>

<c:set var="shareImageUrl" value="${pic }" />
<c:if test="${not empty pics }">
	<c:forTokens items="${pics }" delims="," var="picitem">
		<c:if test="${empty shareImageUrl }" >
			<c:set var="shareImageUrl" value="${picitem }" />
		</c:if>
	</c:forTokens>
</c:if>
	
	
<!-- JiaThis Button BEGIN -->
<div class="jiathis_style_32x32">
<a class="jiathis_button_tsina"></a>
<a class="jiathis_button_weixin"></a>
<a class="jiathis_button_qzone"></a>
<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank"></a>
<a class="jiathis_counter_style"></a>
</div>
<script type="text/javascript" >
var jiathis_config={
	url:"${url}",
	summary:"<c:out value="${summary }" />",
	title:"<c:out value="${title }" /> #圆融星座#",
	<c:if test="${not empty shareImageUrl}">pic: "${shareImageUrl }",</c:if>
	shortUrl: false,
	hideMore: false
};

//shareImg:{
//	"showType":"MARK",
//	 "divname":"jiathisDivImagesId"
//},
</script>
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
<!-- JiaThis Button END -->