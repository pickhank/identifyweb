package com.woodare.template.jersery.servicedata.downdspinvoice;

import java.util.List;

import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceData;

/**
 * @author Luke
 */
public class DownDspInvoiceServiceData extends DownDspInvoiceData {
	private static final long serialVersionUID = -3104155212286803156L;

	private List<DownDspImageFileData> files;

	/**
	 * @return the files
	 */
	public List<DownDspImageFileData> getFiles() {
		return files;
	}

	/**
	 * @param files
	 *            the files to set
	 */
	public void setFiles(List<DownDspImageFileData> files) {
		this.files = files;
	}

}
