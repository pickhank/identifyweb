package com.thirdparty.passway._data;

import java.util.List;

import com.woodare.template.jersery.servicedata.downdspinvoice.DownDspImageFileData;
import com.woodare.template.jpa.model.data.EnumDspStatus;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceData;

/**
 * 验证返回参数
 * 
 * @author Luke
 */
public class PasswayDspResponseData {

	// 订单状态 N2
	private EnumDspStatus status;

	// 验证结果, 00=验证成功、02=验证失败
	private String verifyStatus;

	// 交易返回码 ANS20 C 订单应答码，见附件
	private String retCode;

	// 交易返回码描述 ANS1..1024 C 订单返回码描述
	private String retDesc;

	// 通道订单编号
	private String passwayOrderId;

	// 额外字段
	private DownDspInvoiceData extraInvoice;

	// 处理成功文件
	private List<DownDspImageFileData> respFiles;

	/**
	 * @return the respFiles
	 */
	public List<DownDspImageFileData> getRespFiles() {
		return respFiles;
	}

	/**
	 * @param respFiles
	 *            the respFiles to set
	 */
	public void setRespFiles(List<DownDspImageFileData> respFiles) {
		this.respFiles = respFiles;
	}

	/**
	 * @return the verifyStatus
	 */
	public String getVerifyStatus() {
		return verifyStatus;
	}

	/**
	 * @param verifyStatus
	 *            the verifyStatus to set
	 */
	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	/**
	 * @return the status
	 */
	public EnumDspStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(EnumDspStatus status) {
		this.status = status;
	}

	/**
	 * @return the retCode
	 */
	public String getRetCode() {
		return retCode;
	}

	/**
	 * @param retCode
	 *            the retCode to set
	 */
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	/**
	 * @return the retDesc
	 */
	public String getRetDesc() {
		return retDesc;
	}

	/**
	 * @param retDesc
	 *            the retDesc to set
	 */
	public void setRetDesc(String retDesc) {
		this.retDesc = retDesc;
	}

	/**
	 * @return the passwayOrderId
	 */
	public String getPasswayOrderId() {
		return passwayOrderId;
	}

	/**
	 * @param passwayOrderId
	 *            the passwayOrderId to set
	 */
	public void setPasswayOrderId(String passwayOrderId) {
		this.passwayOrderId = passwayOrderId;
	}

	/**
	 * @return the extraInvoice
	 */
	public DownDspInvoiceData getExtraInvoice() {
		return extraInvoice;
	}

	/**
	 * @param extraInvoice
	 *            the extraInvoice to set
	 */
	public void setExtraInvoice(DownDspInvoiceData extraInvoice) {
		this.extraInvoice = extraInvoice;
	}
}
