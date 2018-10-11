<%@ tag pageEncoding='UTF-8' trimDirectiveWhitespaces="true" language="java"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="data" type="com.woodare.framework.data.IPagedList" required="false" rtexprvalue="true" description="the pagination information data" %>
<%@attribute name="searchData" type="com.woodare.framework.data.ISearchData" required="false" rtexprvalue="true" description="extra search data" %>
<%@attribute name="paginationUrl" type="java.lang.String" required="true" rtexprvalue="true" description="pagination form pat" %>
<%@attribute name="resultDivSelector" type="java.lang.String" required="true" rtexprvalue="true" description="where to locate at" %>
<%@attribute name="method" type="java.lang.String" required="true" rtexprvalue="true" description="pagination method" %>

<c:if test="${empty formId}">
	<c:set var="formId" value="paginationForm"></c:set>
</c:if>
<c:if test="${not empty formId}">
	<c:set var="formId" value="${formId}"></c:set>
</c:if>
<c:if test="${(empty render or render) and (not empty data)}">
	<c:set var="firstPage" />
	<c:set var="prevPage" />
	<c:set var="nextPage" />
	<c:set var="lastPage" />
	<c:set var="totalSize" value="0" />

	<c:set var="page">
		<spring:eval expression="data.getPageIndex()" htmlEscape="false" />
	</c:set>
	<c:set var="size">
		<spring:eval expression="data.getPageSize()" htmlEscape="false" />
	</c:set>
	<c:set var="maxPages">
		<spring:eval expression="data.getMaxPages()" htmlEscape="false" />
	</c:set>
	<c:set var="totalSize">
		<spring:eval expression="data.getTotalSize()" htmlEscape="false" />
	</c:set>
	<c:if test="${empty page || page lt 0}">
		<c:set var="page" value="0" />
	</c:if>
	<c:if test="${empty size || size lt 1}">
		<c:set var="size" value="10" />
	</c:if>
	<c:if test="${empty maxPages || maxPages lt 1}">
		<c:set var="maxPages" value="1" />
	</c:if>
	<c:if test="${page gt 0}">
		<c:set var="firstPage" value="0" />
		<c:set var="prevPage" value="${page - 1}" />
	</c:if>

	<c:if test="${maxPages - page gt 1}">
		<c:set var="nextPage" value="${page + 1}" />
		<c:set var="lastPage" value="${maxPages - 1}" />
	</c:if>

	<c:url value="" var="resizeUrl">
		<c:param name="page" value="${page}" />
	</c:url>

	<div class="badoo" id="${formId}_div">
		每页条数: <select refType="pageSize" class="pagination pagination-size" style="height: 21px;" >
			<c:forTokens items="10,50,100" delims="," var="sizeValue">
				<c:choose>
					<c:when test="${size == sizeValue}">
						<option selected="selected" value="${sizeValue }">${sizeValue
							}</option>
					</c:when>
					<c:otherwise>
						<option value="${sizeValue }">${sizeValue }</option>
					</c:otherwise>
				</c:choose>
			</c:forTokens>
		</select>
		<span style="float: left;">总条数: ${totalSize }</span>
		<span>总页数: ${maxPages }</span>
		<c:choose>
			<c:when test="${not empty firstPage}">
				<a href="#" refType="pageIndex" refValue="0"
					class="pagination pagination-links">首页</a>
			</c:when>
			<c:otherwise>
				<span class="disabled">首页</span>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${not empty prevPage}">
				<a href="#" refType="pageIndex" refValue="${prevPage}"
					class="pagination pagination-links">上一页</a>
			</c:when>
			<c:otherwise>
				<span class="disabled">上一页</span>
			</c:otherwise>
		</c:choose>

		<c:set var="beginPart" value="false" />
		<c:set var="endPart" value="false" />

		<c:set var="begin" value="${page - 2}" />
		<c:if test="${ begin lt 0 }">
			<c:set var="begin" value="0" />
		</c:if>
		<c:set var="end" value="${begin + 4 }" />

		<c:if test="${ begin > 0 }">
			<c:set var="beginPart" value="true" />
		</c:if>
		<c:choose>
			<c:when test="${ end lt maxPages - 1 }">
				<c:set var="endPart" value="true" />
			</c:when>
			<c:otherwise>
				<c:if test="${begin lt 0}">
					<c:set var="beginPart" value="false" />
					<c:set var="begin" value="0" />
				</c:if>
				<c:set var="end" value="${maxPages - 1 }" />
			</c:otherwise>
		</c:choose>

		<c:if test="${ beginPart }">
			<c:out value=" ... " />
		</c:if>

		<c:forEach var="i" begin="${begin}" end="${end}">
			<c:choose>
				<c:when test="${ i == page }">
					<span class="current"><c:out value="${i + 1}" /></span>
				</c:when>
				<c:otherwise>
					<a href="#" refType="pageIndex" refValue="${i}"
						class="pagination pagination-links"><c:out value="${i + 1}" /></a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:if test="${ endPart }">
			<c:out value=" ... " />
		</c:if>

		<c:choose>
			<c:when test="${not empty nextPage}">
				<a href="#" refType="pageIndex" refValue="${ nextPage }"
					class="pagination pagination-links">下一页</a>
			</c:when>
			<c:otherwise>
				<span class="disabled">下一页</span>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${not empty lastPage}">
				<a href="#" refType="pageIndex" refValue="${ lastPage }"
					class="pagination pagination-links">最后一页</a>
			</c:when>
			<c:otherwise>
				<span class="disabled">最后一页</span>
			</c:otherwise>
		</c:choose>

		<input type="text" id="gotoTxt" style="width: 50px;height:17px;text-align: center;display: none;" /> 
		<input id="go" type="button" value="Go" refType="pageIndex"
			maxPage="${maxPages }" style="cursor: pointer;height: 21px;display: none;" class="pagination pagination-goto goto" />

		<form id="${formId}" action="${paginationUrl }" method="${method}"
			style="display: none;">
			<c:if test="${not empty searchData }">
				<c:set var="searchFields">
					<spring:eval expression="searchData.getSearchFields()"
						htmlEscape="false" />
				</c:set>
				<c:forTokens items="${searchFields }" delims="," var="key">
					<c:set var="fieldValue" value="${ searchData[key] }" />
					<c:if test="${not empty fieldValue }">
						<input type="hidden" name="${key }" value="${fieldValue }" />
					</c:if>
				</c:forTokens>

			</c:if>
			<input type="hidden" name="pageIndex" value="${page}" /> 
			<input type="hidden" name="pageSize" value="${size}" />
		
		</form>
	</div>

	<script type="text/javascript">
		$(function() {
			var formId = "#"+"${formId}";
			var formObj = $(formId);
			formObj.submit(function() {
				var formObj = $(this);
				$.ajax({
				   method: formObj.attr('method'),
				   url: formObj.attr('action'),
				   data: formObj.serialize(),
				   dataType: "html",
				   success: function(html) {
						$("${resultDivSelector}").html(html);
					}
				});
				
				return false;
		    });
			var eventListener = function() {
				var obj = $(this);
				var proName = obj.attr('refType');
				var proValue = obj.attr('refValue');
				
				if (obj.hasClass('pagination-links')) {
					formObj.find('input[name=' + proName + ']').val(proValue);
					formObj.submit();
				} else if (obj.hasClass('pagination-goto')) {
					var newPar = /^[0-9]*[1-9][0-9]*$/;
					var links = obj.attr('ref');
					var pageVal = document.getElementById('gotoTxt').value;
					if (newPar.test(pageVal)) {
						if (pageVal != '' && !isNaN(pageVal)) {
							if (parseInt(obj.attr('maxPage'), 10) < parseInt(pageVal, 10)) {
								document.getElementById('gotoTxt').value = '';
								return;
							}
							formObj.find('input[name=' + proName + ']').val(pageVal);
							formObj.submit();
						}else {
							document.getElementById('gotoTxt').value = '';
						};
					} else {
						document.getElementById('gotoTxt').value = '';
					};

				} else if (obj.hasClass('pagination-size')) {
					formObj.find('input[name=' + proName + ']').val(obj.val());
					// addCookie("size",obj.val());
					formObj.find('input[name=page]').val(1);
					formObj.submit();
				}
				return false;
			};				
			
			$(formId+"_div"+" .pagination").each(function() {
				var obj = $(this);
				if (obj.hasClass('pagination-size')) {
					
					obj.change(eventListener);
				} else {
					obj.click(eventListener);
				}
			});
			
			
			$("#gotoTxt").keydown(function(e){
				
				if(e.keyCode ==13){
					var obj = $("#go");
					var proName = obj.attr('refType');
					var proValue = obj.attr('refValue');
					var newPar = /^[0-9]*[1-9][0-9]*$/;
					var pageVal = document.getElementById('gotoTxt').value;
					if (newPar.test(pageVal)) {
						if (pageVal != '' && !isNaN(pageVal)) {
							if (parseInt(obj.attr('maxPage'), 10) < parseInt(pageVal, 10)) {
								document.getElementById('gotoTxt').value = '';
								return;
							}
							formObj.find('input[name=' + proName + ']').val(pageVal);
							formObj.submit();
						}else {
							document.getElementById('gotoTxt').value = '';
						};
					} else {
						document.getElementById('gotoTxt').value = '';
					};
					
					return false;
				}
		
			});
		});
	</script>
</c:if>