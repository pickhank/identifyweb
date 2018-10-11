package com.woodare.template.jersery.webservice.busi.base;

import com.thirdparty.passway._data.PasswayDspResponseData;
import com.woodare.framework.exception.MessageWooException;
import com.woodare.framework.exception.RollbackMessageWooException;
import com.woodare.template.jpa.model.DownDspInvoice;
import com.woodare.template.jpa.model.DownMerchantBalance;
import com.woodare.template.jpa.model.data.EnumBalanceAccType;
import com.woodare.template.jpa.model.data.EnumBalanceType;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;

/**
 * 机构收支明细处理服务
 * 
 * @author Luke
 */
public interface IDownMerchantBalanceService {

	/**
	 * 账户余额操作
	 * 
	 * @param merchant
	 * @param balanceDetail
	 * @param accType
	 * @param balanceType
	 * @param txnAmt
	 * @param feeAmt
	 * @throws RollbackMessageWooException
	 */
	void addMerchantBalance(DownMerchantData merchant, DownMerchantBalance balanceDetail, EnumBalanceAccType accType, EnumBalanceType balanceType, long txnAmt, long feeAmt) throws RollbackMessageWooException;
	
	/**
	 * 处理验签交易结果
	 * 
	 * @param oriModel
	 * @param respData
	 * @return
	 * @throws MessageWooException
	 */
	DownDspInvoice handleDspResult(DownDspInvoice oriModel, PasswayDspResponseData respData) throws MessageWooException;
	
	/**
	 * 验签交易收支额度处理
	 * 
	 * @param invoice
	 * @param direction
	 * @param remark
	 * @return
	 * @throws MessageWooException
	 */
	boolean recordDspInvoice(DownDspInvoice invoice, int direction, String remark) throws MessageWooException;
	
	/**
	 * 结算统计记账
	 * 
	 * @param mchNo
	 * @param startOfDay
	 * @param endOfDay
	 * @return
	 * @throws Exception
	 */
	boolean recordSettle() throws Exception;
}
