<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://www.woodare.com/jsp/taglib/my"%>
<div class="page__hd">
	<h1 class="page__title">Sum</h1>
	<p class="page__desc">无卡交易汇总</p>
</div>
<div class="weui-flex exec-btn-wapper">
	<div class="weui-flex__item"><a href="javascript:$('.search-form').toggle('slow');" class="weui-btn weui-btn_mini weui-btn_default">搜索</a></div>
</div>
<form class="search-form" action="<c:url value="/content/downNoCardInvoice/sum" />" method="post" style="display:none;">
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="weui-cells__title">搜索条件</div>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">关键字</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="关键字" name="keywords" value="${search.keywords}">
			</div>
		</div>
		<!-- <div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">商户号</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="商户号" name="mchNo" value="${search.mchNo}">
			</div>
		</div> -->
		<div class="weui-cell">
			<div class="weui-cell__hd"><label for="" class="weui-label">开始日期</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="date" name="startDate" value="<fmt:formatDate value="${search.startDate}" pattern="yyyy-MM-dd"/>">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label for="" class="weui-label">结束日期</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="date" name="endDate" value="<fmt:formatDate value="${search.endDate}" pattern="yyyy-MM-dd"/>">
			</div>
		</div>
	</div>
	<div style="text-align: center; background: #FFF; padding: 8px; border-bottom: 1px solid #267ad2;">
		<input type="submit" value="查询" class="weui-btn weui-btn_mini weui-btn_primary"></input>
	</div>
</form>
<utils:mobilepager/>
<c:forEach var="item" items="${items}" varStatus="status">
	<div class="weui-form-preview">
		<div class="weui-form-preview__hd">
			<label class="weui-form-preview__label">商户号</label>
			<em class="weui-form-preview__value"><c:out value="${item.mchNo}" /></em>
		</div>
		<div class="weui-form-preview__bd">
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">交易成功率</label>
			<span class="weui-form-preview__value"><my:percent value="${item.count}" total="${item.count + item.failCount}"/></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">代付成功率</label>
			<span class="weui-form-preview__value"><my:percent value="${item.advanceCount}" total="${item.advanceTotalCount}"/></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">总交易金额</label>
			<span class="weui-form-preview__value"><c:out value="${item.totalPrice}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">总交易数</label>
			<span class="weui-form-preview__value"><c:out value="${item.totalCount}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">成功交易金额</label>
			<span class="weui-form-preview__value"><c:out value="${item.price}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">成功交易数</label>
			<span class="weui-form-preview__value"><c:out value="${item.count}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">失败交易数</label>
			<span class="weui-form-preview__value"><c:out value="${item.failCount}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">未支付交易数</label>
			<span class="weui-form-preview__value"><c:out value="${item.totalCount - item.count - item.failCount}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">清算金额</label>
			<span class="weui-form-preview__value"><c:out value="${item.realPrice}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">总费率</label>
			<span class="weui-form-preview__value"><c:out value="${item.feePrice}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">接入机构利润</label>
			<span class="weui-form-preview__value"><c:out value="${item.profit}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">代付总金额</label>
			<span class="weui-form-preview__value"><c:out value="${item.advanceTotalPrice}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">代付总数</label>
			<span class="weui-form-preview__value"><c:out value="${item.advanceTotalCount}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">成功代付金额</label>
			<span class="weui-form-preview__value"><c:out value="${item.advancePrice}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">成功代付数</label>
			<span class="weui-form-preview__value"><c:out value="${item.advanceCount}" /></span>
		</div>	
		</div>
	</div>
</c:forEach>