<%@tag import="com.woodare.template.helper.cache.PasswayOrgAccSettings"%>
<%@tag import="com.woodare.template.jpa.persistence.data.passwayorgaccsetting.PasswayOrgAccSettingData"%>
<%@tag import="com.woodare.core.web.taglibs.DicTaglibHelper" %>
<%@tag import="com.woodare.core.web.common.viewdata.CodeAndName" %>
<%@tag pageEncoding='UTF-8' trimDirectiveWhitespaces="true" language="java"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="defaultVal" type="java.lang.String" required="true" rtexprvalue="true" description="pagination method"%>
	
<link href="<c:url value='/resources/js/bootstrap/bootstrap-select.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/js/bootstrap/bootstrap.min.css' />" rel="stylesheet" type="text/css" />
<style>
	.input-box, .box-body label {
	    color: #333;
	    font-family: 'Microsoft YaHei', Arial, Helvetica, sans-serif;
	    font-size: 0.9rem;
	}
	.box-body .btn {
	    padding: 5px 20px;
	    text-align: center;
	    cursor: pointer;
	    display: inline-block;
	    border-radius: 3px;
	    font-size: 12px;
	    height: 27px;
	    box-sizing: border-box;
	    vertical-align: middle;
	}
	.tl-cell-hide {
		display: none;
	}
</style>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap/bootstrap-select.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap/bootstrap.min.js' />"></script>

<label for="keywords">隐藏指定列：</label>
<c:if test="${empty search.columnFields }"><c:set var="columnFields" value="${defaultVal}" /></c:if>
<c:if test="${not empty search.columnFields }"><c:set var="columnFields" value="${search.columnFields}" /></c:if>
<input type="hidden" name="columnFields" value="${columnFields}" />
<select name="columnFieldArr" class="selectpicker bla bla bli" multiple='multiple' data-live-search='true' >
</select>
<script>
	$(function() {
		var columnFieldsObj = $("input[name=columnFields]");
		var selFieldsObj = $("select[name=columnFieldArr]");

		var tableObj = $('.list-wapper');
		var items = tableObj.find('tr');
		for(var i = 0; i< items.length; i++) {
			var cols = items.eq(i).find('td');
			for(var j = 0; j < cols.length; j++) {
				cols.eq(j).addClass("tl-cell-" + j);
			}
		}
		
		var headerItems = tableObj.find('.list-header td');
		
		var keyMap = {};
		var selFields = columnFieldsObj.val().split(",");
		headerItems.each(function(index, item) {
			var k = $(item).text();
			keyMap[k] = index;
			selFieldsObj.append("<option " + (selFields.indexOf(k) != -1 ? "selected" : "") + " value='" + k + "'>" + k + "</option>");
		});

		$('.selectpicker').selectpicker({
	        'selectedText': 'cat'
	    });
		
		selFieldsObj.change(function() {
			var selFields = $(this).val() == null ? [] : $(this).val();
			tableObj.find(".tl-cell-hide").removeClass("tl-cell-hide");
			for(var j = 0; j < selFields.length; j++) {
				if(keyMap[selFields[j]] != null) {
					tableObj.find(".tl-cell-" + keyMap[selFields[j]]).addClass("tl-cell-hide");
				}
			}
			columnFieldsObj.val(selFields.join(","));
		}).change();
		
	});
</script>