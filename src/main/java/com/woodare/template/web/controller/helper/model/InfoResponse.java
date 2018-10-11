/******************************************************************************
 *                                                                             
 *                      Woodare PROPRIETARY INFORMATION                        
 *                                                                             
 *          The information contained herein is proprietary to Woodare         
 *           and shall not be reproduced or disclosed in whole or in part      
 *                    or used for any design or manufacture                    
 *              without direct written authorization from FengDa.              
 *                                                                             
 *            Copyright (c) 2013 by Woodare.  All rights reserved.             
 *                                                                             
 *****************************************************************************/
package com.woodare.template.web.controller.helper.model;

/**
 * ClassName: InfoResponse
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Oct 13, 2016
 * 
 */
public class InfoResponse extends POSPResult {

	private String TRM_NM;
	private String MERC_CNM;
	private String MERC_ID;
	private String MSG_TYP;
	private String MERC_ABBR;
	private String TRM_STS;
	private String TRM_NO;
	private String INS_AD;
	private String ORG_APP_IDS;

	public String getTRM_NM() {
		return TRM_NM;
	}

	public void setTRM_NM(String tRM_NM) {
		TRM_NM = tRM_NM;
	}

	public String getMERC_CNM() {
		return MERC_CNM;
	}

	public void setMERC_CNM(String mERC_CNM) {
		MERC_CNM = mERC_CNM;
	}

	public String getMERC_ID() {
		return MERC_ID;
	}

	public void setMERC_ID(String mERC_ID) {
		MERC_ID = mERC_ID;
	}

	public String getMSG_TYP() {
		return MSG_TYP;
	}

	public void setMSG_TYP(String mSG_TYP) {
		MSG_TYP = mSG_TYP;
	}

	public String getMERC_ABBR() {
		return MERC_ABBR;
	}

	public void setMERC_ABBR(String mERC_ABBR) {
		MERC_ABBR = mERC_ABBR;
	}

	public String getTRM_STS() {
		return TRM_STS;
	}

	public void setTRM_STS(String tRM_STS) {
		TRM_STS = tRM_STS;
	}

	public String getTRM_NO() {
		return TRM_NO;
	}

	public void setTRM_NO(String tRM_NO) {
		TRM_NO = tRM_NO;
	}

	public String getINS_AD() {
		return INS_AD;
	}

	public void setINS_AD(String iNS_AD) {
		INS_AD = iNS_AD;
	}

	public String getORG_APP_IDS() {
		return ORG_APP_IDS;
	}

	public void setORG_APP_IDS(String oRG_APP_IDS) {
		ORG_APP_IDS = oRG_APP_IDS;
	}

}
