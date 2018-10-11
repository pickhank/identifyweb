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

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;

/**
 * ClassName: reconciliation
 * 
 * @description 对账
 * @author Xinxing Jiang
 * @Date Nov 30, 2016
 * 
 */
@Entity
@EntityDescriptor(name = "对账", category = "content")
public class Recon extends AbstractBusiModel {

	private static final long serialVersionUID = 7542723883017638771L;

	/** 商户号 **/
	@Column(length = 256)
	private String mchNo;

	/** 对账时间 */
	@Column(length = 64)
	private String orderDate;

	/** 代理商 **/
	@Column(length = 256)
	private String agentNo;

	/** 对账单路径 **/
	@Column(length = 256)
	private String filePath;

	/** 交易额 */
	@Column(scale = 2, precision = 19)
	private BigDecimal price;

	/** 待清算额 */
	@Column(scale = 2, precision = 19)
	private BigDecimal realPrice;

	/** 总交易笔数 */
	private Long totalCount;

	/** 总成功笔数 */
	private Long successCount;

	public String getMchNo() {
		return mchNo;
	}

	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Long successCount) {
		this.successCount = successCount;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

}
