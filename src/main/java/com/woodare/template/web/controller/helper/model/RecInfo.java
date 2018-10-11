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

import java.util.List;

/**
 * ClassName: RecInfo
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Sep 1, 2016
 * 
 */
public class RecInfo {
	private String BELANCECOUNT;
	private String COUNT;
	private String AMOUNT;
	private String FEE;
	private List<TrdeInfo> TRDELIST;

	public String getBELANCECOUNT() {
		return BELANCECOUNT;
	}

	public void setBELANCECOUNT(String bELANCECOUNT) {
		BELANCECOUNT = bELANCECOUNT;
	}

	public String getCOUNT() {
		return COUNT;
	}

	public void setCOUNT(String cOUNT) {
		COUNT = cOUNT;
	}

	public String getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(String aMOUNT) {
		AMOUNT = aMOUNT;
	}

	public List<TrdeInfo> getTRDELIST() {
		return TRDELIST;
	}

	public void setTRDELIST(List<TrdeInfo> tRDELIST) {
		TRDELIST = tRDELIST;
	}

	public String getFEE() {
		return FEE;
	}

	public void setFEE(String fEE) {
		FEE = fEE;
	}
}
