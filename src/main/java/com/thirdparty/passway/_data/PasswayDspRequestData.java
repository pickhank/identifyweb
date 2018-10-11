package com.thirdparty.passway._data;

import java.util.List;

import com.woodare.template.jersery.servicedata.downdspinvoice.DownDspImageFileData;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceData;

/**
 * @author Luke
 */
public class PasswayDspRequestData extends BaseRequestData {
	private static final long serialVersionUID = 7160936603971200572L;

	// 明细表
	private DownDspInvoiceData invoice;

	private List<DownDspImageFileData> localFiles;

	// 异步回调地址
	private String notifyUrl;

	//
	private String extraText;

	//
	private Object extraObj;

	/**
	 * @return the localFiles
	 */
	public List<DownDspImageFileData> getLocalFiles() {
		return localFiles;
	}

	/**
	 * @param localFiles
	 *            the localFiles to set
	 */
	public void setLocalFiles(List<DownDspImageFileData> localFiles) {
		this.localFiles = localFiles;
	}

	/**
	 * @return the invoice
	 */
	public DownDspInvoiceData getInvoice() {
		return invoice;
	}

	/**
	 * @param invoice
	 *            the invoice to set
	 */
	public void setInvoice(DownDspInvoiceData invoice) {
		this.invoice = invoice;
	}

	/**
	 * @return the notifyUrl
	 */
	public String getNotifyUrl() {
		return notifyUrl;
	}

	/**
	 * @param notifyUrl
	 *            the notifyUrl to set
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	/**
	 * @return the extraText
	 */
	public String getExtraText() {
		return extraText;
	}

	/**
	 * @param extraText
	 *            the extraText to set
	 */
	public void setExtraText(String extraText) {
		this.extraText = extraText;
	}

	/**
	 * @return the extraObj
	 */
	public Object getExtraObj() {
		return extraObj;
	}

	/**
	 * @param extraObj
	 *            the extraObj to set
	 */
	public void setExtraObj(Object extraObj) {
		this.extraObj = extraObj;
	}

}
