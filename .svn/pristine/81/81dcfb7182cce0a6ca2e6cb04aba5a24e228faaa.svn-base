package com.woodare.template.jpa.model.data;

/**
 * @author Luke
 */
public enum EnumNotifyRecordType {
	// 无卡快捷
	NOCARD("downNoCardInvoiceService"),
	// 代付交易
	DEPOSIT("downMerchantDepositService"),
	// 同名交易
	SELF("downCreditCardInvoiceService"),
	;

	public String serviceName;

	/**
	 * @param serviceName 对应Service服务需要实现通知
	 */
	EnumNotifyRecordType(String serviceName) {
		this.serviceName = serviceName;
	}
}
