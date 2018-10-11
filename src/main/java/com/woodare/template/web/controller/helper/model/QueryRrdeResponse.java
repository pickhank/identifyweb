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
 * ClassName: QueryRrdeResponse
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Sep 1, 2016
 * 
 */
public class QueryRrdeResponse extends POSPResult {

	private String TOKEN_ID;
	private List<RecInfo> REC;

	public String getTOKEN_ID() {
		return TOKEN_ID;
	}

	public void setTOKEN_ID(String tOKEN_ID) {
		TOKEN_ID = tOKEN_ID;
	}

	public List<RecInfo> getREC() {
		return REC;
	}

	public void setREC(List<RecInfo> rEC) {
		REC = rEC;
	}

}
