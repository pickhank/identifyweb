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
package com.woodare.template.jpa.persistence.data.downagent;

import java.util.Date;
import com.woodare.framework.data.impl.AbstractData;

import com.woodare.template.jpa.model.data.EnumDownUserStatus;

/**
 * 
 * ClassName: DownAgentData
 * 
 * @description
 * @author woo_maven_auto_generate
 * 
 */
public class DownAgentData extends AbstractData {
	private static final long serialVersionUID = -1838815663502921782L;

	private String id;

	private Date createDate;

	private String agentNo;

	private String name;

	private String phone;

	private String bankName;

	private String cardNo;

	private Boolean enableBalChgFlg;

	private EnumDownUserStatus status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Boolean getEnableBalChgFlg() {
		return enableBalChgFlg;
	}

	public void setEnableBalChgFlg(Boolean enableBalChgFlg) {
		this.enableBalChgFlg = enableBalChgFlg;
	}

	public EnumDownUserStatus getStatus() {
		return status;
	}

	public void setStatus(EnumDownUserStatus status) {
		this.status = status;
	}

}
