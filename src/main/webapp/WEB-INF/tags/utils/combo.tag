<%@ tag import="com.woodare.core.web.taglibs.DicTaglibHelper" %>
<%@ tag import="com.woodare.core.web.common.viewdata.CodeAndName" %>
<%@ tag pageEncoding='UTF-8' trimDirectiveWhitespaces="true" language="java"  %>
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
<%@attribute name="extraAttr" type="java.lang.String" required="false" rtexprvalue="false" description="默认的选项值" %>

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
	<select id="${id }" name="${name }" class="${cssName }" ${extraAttr }>
		<option value="" >${needDefaultValue }</option>
		<option value="AINO" <c:if test="${value eq 'AINO'}">selected</c:if>>爱农-AINO</option>
		<option value="GOPAY" <c:if test="${value eq 'GOPAY'}">selected</c:if>>国付宝-GOPAY</option>
		<option value="EPAY" <c:if test="${value eq 'EPAY'}">selected</c:if>>双乾-EPAY</option>
		<option value="QRCODE" <c:if test="${value eq 'QRCODE'}">selected</c:if>>码付-QRCODE</option>
		<option value="YISHI" <c:if test="${value eq 'YISHI'}">selected</c:if>>易势-YISHI</option>
		<option value="HAODIAN" <c:if test="${value eq 'HAODIAN'}">selected</c:if>>好店-YISHI</option>
	</select>
</c:if>
<!-- 路由配置&外发商户 -->
<c:if test="${mode eq 'sel3' }">
	<select id="${id }" name="${name }" class="${cssName }" ${extraAttr }>
		<option value="" >${needDefaultValue }</option>
		<option value="AINO" <c:if test="${value eq 'AINO'}">selected</c:if>>爱农-AINO</option>
		<option value="GOPAY" <c:if test="${value eq 'GOPAY'}">selected</c:if>>国付宝-GOPAY</option>
		<option value="EPAY" <c:if test="${value eq 'EPAY'}">selected</c:if>>双乾-EPAY</option>
		<option value="YISHI" <c:if test="${value eq 'YISHI'}">selected</c:if>>易势-YISHI</option>
		<option value="YEEPAY" <c:if test="${value eq 'YEEPAY'}">selected</c:if>>易宝-YEEPAY</option>
		<option value="HAODIAN" <c:if test="${value eq 'HAODIAN'}">selected</c:if>>好店-YISHI</option>
	</select>
</c:if>
<!-- 可提现通道 -->
<c:if test="${mode eq 'sel4' }">
	<select id="${id }" name="${name }" class="${cssName }" ${extraAttr }>
		<option value="" >${needDefaultValue }</option>
		<option value="AINO" <c:if test="${value eq 'AINO'}">selected</c:if>>爱农-AINO</option>
		<option value="GOPAY" <c:if test="${value eq 'GOPAY'}">selected</c:if>>国付宝-GOPAY</option>
		<option value="EPAY" <c:if test="${value eq 'EPAY'}">selected</c:if>>双乾-EPAY</option>
	</select>
</c:if>
<c:if test="${mode eq 'lbl' }">
	<c:choose>
		<c:when test="${value eq 'AINO'}">AINO</c:when>
		<c:when test="${value eq 'GOPAY'}">GOPAY</c:when>
		<c:when test="${value eq 'EPAY'}">EPAY</c:when>
		<c:when test="${value eq 'QRCODE'}">QRCODE</c:when>
		<c:when test="${value eq 'YISHI'}">YISHI</c:when>
		<c:otherwise>${value}</c:otherwise>
	</c:choose>
</c:if>
