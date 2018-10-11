<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="page__hd">
	<h1 class="page__title">NoCardInvoice</h1>
	<p class="page__desc">无卡交易</p>
</div>
<div class="weui-form-preview">
	<div class="weui-form-preview__hd">
		<label class="weui-form-preview__label"></label>
		<em class="weui-form-preview__value"><c:out value="订单基本信息" /></em>
	</div>
	<div class="weui-form-preview__bd">
		<div class="weui-form-preview__item">支付方式:</div>
			<span class="weui-form-preview__value">
				<c:choose>
					<c:when test="${item.payType eq '01' }"><c:set var="payType" value="网银" /></c:when>
					<c:when test="${item.payType eq '02' }"><c:set var="payType" value="银联在线" /></c:when>
					<c:otherwise><c:set var="payType" value="${item.payType }" /></c:otherwise>
				</c:choose>
				<input type="text" class="input-box" placeholder="支付方式" value="${payType}" readonly/>
			</span>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">平台流水号:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="平台流水号" name="transNo" value="${item.transNo}" readonly/>
			</span>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">平台受理时间:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="平台受理时间" name="createDate" value="${item.createDate}" readonly/>
			</span>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">机构号:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="机构号" name="mchNo" value="${item.mchNo}" readonly/>
			</span>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">机构名称:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="机构名称" name="mchName" value="${item.mchName}" readonly/>
			</span>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">交易通道:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="通道" name="channel" value="${item.channel}" readonly/>
			</span>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">通道账户号:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="通道账户号" name="channelAccNo" value="${item.channelAccNo}" readonly/>
			</span>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">通道商户:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="通道商户" name="channelAccName" value="${item.channelAccName}" readonly/>
			</span>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">交易费率:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="交易费率" name="price" value="${item.feeRatio}" readonly/>%。
			</span>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">单笔加收费:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="单笔加收费" name="price" value="${item.addFeeAmt}" readonly/>元
			</span>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">商品标题:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="商品标题" name="subject" value="${item.subject}" readonly/>
			</span>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">商品描述:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="商品描述" name="description" value="${item.description}" readonly/>
			</span>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">同步回调地址:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="同步回调地址" name="callbackUrl" value="${item.callbackUrl}" readonly/>
			</span>
		</div>
	</div>
</div>
<div class="weui-form-preview">
	<div class="weui-form-preview__hd">
		<label class="weui-form-preview__label"></label>
		<em class="weui-form-preview__value"><c:out value="机构状态信息" /></em>
	</div>
	<div class="weui-form-preview__bd">
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">机构流水号:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="机构流水号" name="tradeNo" value="${item.tradeNo}" readonly/>
			</span>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">机构订单时间:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="订单时间" name="orderDate" value="${item.orderDate}" readonly/>
			</span>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">交易状态:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="交易状态" name="status" value="${item.status}" readonly/>
			</span>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">状态描述:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="交易状态描述" name="statusDesc" value="${item.statusDesc}" readonly/>
			</span>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">状态更新时间:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="状态更新时间" name="successDate" value="${item.statusChgDate}" readonly/>
			</span>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">交易金额:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="交易金额" name="price" value="${item.price}" readonly/>元
			</span>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">交易手续费:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="商户手续费金额" name="downPayFee" value="${item.downPayFee}" readonly/>元
			</span>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">结算金额:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="结算金额" name="downRealT1Price" value="${item.downRealT1Price}" readonly/>元
			</span>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">异步通知地址:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="通知地址" name="notifyUrl" value="${item.notifyUrl}" readonly/>
			</span>
		</div>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">异步通知是否成功:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="异步通知是否成功" name="notifySuccess" value="${item.notifySuccess}" readonly/>
			</span>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">异步通知次数:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="异步通知次数" name="notifyTimes" value="${item.notifyTimes}" readonly/>
			</span>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">末次异步通知时间:</div>
			<span class="weui-form-preview__value">
				<input type="text" class="input-box" placeholder="末次异步通知时间" name="notifyLastDate" value="${item.notifyLastDate}" readonly/>
			</div>
		</div>
	</div>
</div>
<div class="weui-form-preview">
	<div class="weui-form-preview__hd">
		<label class="weui-form-preview__label"></label>
		<em class="weui-form-preview__value"><c:out value="上游代付信息" /></em>
	</div>
	<div class="weui-form-preview__bd">
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">代付状态</label>
			<span class="weui-form-preview__value"><c:out value="${item.upDrawStatus}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">代付状态描述</label>
			<span class="weui-form-preview__value"><c:out value="${item.upDrawStatusDesc}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">代付类型（T0|T1）</label>
			<span class="weui-form-preview__value"><c:out value="${item.upDrawType}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">代付金额</label>
			<span class="weui-form-preview__value"><c:out value="${item.upDrawAmt}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">接口版本号</label>
			<span class="weui-form-preview__value"><c:out value="${item.versionNo}" /></span>
		</div>
	</div>
</div>
<div class="weui-form-preview">
	<div class="weui-form-preview__hd">
		<label class="weui-form-preview__label"></label>
		<em class="weui-form-preview__value"><c:out value="接口信息"/></em>
	</div>
	<div class="weui-form-preview__bd">
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">下游发起交易路径</label>
			<span class="weui-form-preview__value"><c:out value="${item.tranStr}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">前端回调地址</label>
			<span class="weui-form-preview__value"><c:out value="${item.callbackUrl}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">异步通知地址</label>
			<span class="weui-form-preview__value"><c:out value="${item.notifyUrl}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">异步通知是否成功</label>
			<span class="weui-form-preview__value"><c:out value="${item.notifySuccess}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">异步通知次数</label>
			<span class="weui-form-preview__value"><c:out value="${item.notifyTimes}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">末次异步通知时间</label>
			<span class="weui-form-preview__value"><c:out value="${item.notifyLastDate}" /></span>
		</div>
	</div>
</div>
