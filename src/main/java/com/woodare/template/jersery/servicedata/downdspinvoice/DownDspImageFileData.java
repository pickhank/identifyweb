package com.woodare.template.jersery.servicedata.downdspinvoice;

/**
 * @author Luke
 */
public class DownDspImageFileData {
	// 请求参数 - 文件标识名称
	private String fileName;
	// 请求参数 - 文件内容（BASE64加密）
	private String fileContent;
	// 返回结果 - 登记成功的ID
	private String fileId;

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId
	 *            the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileContent
	 */
	public String getFileContent() {
		return fileContent;
	}

	/**
	 * @param fileContent
	 *            the fileContent to set
	 */
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

}
