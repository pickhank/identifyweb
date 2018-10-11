<%@ tag pageEncoding='UTF-8' trimDirectiveWhitespaces="true" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${res.pages > 0}">
	<script>
		$(function() {
			$(".pager-quick-filter").click(function(e) {
				var index = Number($(this).text()) - 1;
				if (${res.index} == index) {
					return;
				}
				if (parent && parent.showLoading) {
					parent.showLoading();
				}
				$(".search-form input[name=pageIndex]").val(index);
				$(".search-form").submit();
				return false;
			});
			$(".pager-box").change(function(){
				var index = Number($(this).val()) ;
				if (index > ${res.pages}) {
					index = ${res.pages};
					$(this).val(index);
				} else if (index < 1) {
					index = 1;
					$(this).val(index);
				}
				if (${res.index + 1} == index) {
					return;
				}
				if (parent && parent.showLoading) {
					parent.showLoading();
				}
				$(".search-form input[name=pageIndex]").val(index - 1);
				$(".search-form").submit();
			})
		});
	</script>
	<div class="pager-line-1 clear">
	<span style="float:left;">总 ${res.total} 条记录</span>
	<div style="float:right;">
		第 <input type="text" class="pager-box" value="${res.index + 1}" /> 页, 共  <c:out value="${res.pages}" /> 页
	</div>
	</div>
	<div class="pager-line-2">
	<c:set var="minPager" value="1" />
	<c:set var="maxPager" value="${res.pages}" />
	<c:if test="${res.pages > 10}">
		<c:if test="${res.index - 4 > minPager}">
			<c:set var="minPager" value="${res.index - 4}" />
		</c:if>
		<c:if test="${res.index + 6 < maxPager}">
			<c:set var="maxPager" value="${res.index + 6}" />
		</c:if>
	</c:if>
	
	<c:if test="${minPager > 1}">
		...
	</c:if>
	<c:forEach var="count" begin="${minPager}" end="${maxPager}" step="1" varStatus="status">
		<a href="javascript:void(0)" class="pager-quick-filter <c:if test="${res.index + 1 == count}">selected</c:if>">${count}</a>
	</c:forEach>
	
	<c:if test="${maxPager < res.pages}">
		...
	</c:if>
	</div>
</c:if>