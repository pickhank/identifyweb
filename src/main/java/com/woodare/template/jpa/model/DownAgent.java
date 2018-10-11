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
package com.woodare.template.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;

/**
 * ClassName: DownAgent
 * 
 * @description
 * @author Luke
 * @Date Mar 7, 2018
 */
@Entity
@EntityDescriptor(name = "代理商", category = "content")
public class DownAgent extends AbstractBusiModel {
	private static final long serialVersionUID = -1838815663502921782L;

	/* 代理商编号 */
	@Column(length = 20, unique = true, nullable = false)
	private String agentNo;

	/* 姓名 */
	@Column(length = 128)
	private String name;

	/* 电话 */
	@Column(length = 256)
	private String phone;

	/* 结算卡银行 */
	@Column(length = 256)
	private String bankName;

	/* 结算卡卡号 */
	@Column(length = 256)
	private String cardNo;

	/** 开启资金明细 */
	private Boolean enableBalChgFlg;

	/* 状态 */
	@Enumerated(EnumType.STRING)
	private EnumDownUserStatus status;

	/**
	 * @return the enableBalChgFlg
	 */
	public Boolean getEnableBalChgFlg() {
		return enableBalChgFlg;
	}

	/**
	 * @param enableBalChgFlg the enableBalChgFlg to set
	 */
	public void setEnableBalChgFlg(Boolean enableBalChgFlg) {
		this.enableBalChgFlg = enableBalChgFlg;
	}

	/**
	 * @return the agentNo
	 */
	public String getAgentNo() {
		return agentNo;
	}

	/**
	 * @param agentNo
	 *            the agentNo to set
	 */
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

	public EnumDownUserStatus getStatus() {
		return status;
	}

	public void setStatus(EnumDownUserStatus status) {
		this.status = status;
	}

}
