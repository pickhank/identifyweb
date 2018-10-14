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
package com.woodare.template.jpa.persistence.data.downdspinvoice;

import java.math.BigDecimal;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;
import com.woodare.framework.utils.CommonUtils;
import com.woodare.template.jpa.model.data.EnumDspMode;

import javax.persistence.Entity;

/**
 * ClassName: DownDspInvoiceSumData
 * 
 * @description
 * @author Luke
 * @Date Sep 15, 2018
 */
@Entity
@EntityDescriptor(name = "交易汇总", category = "content")
public class DownDspInvoiceSumData extends AbstractBusiModel {
	private static final long serialVersionUID = 1560550373825532962L;

	/** 商户号 * */
	private String mchNo;

	/** 商户名 * */
	private String mchName;

	/** 验证类型 */
	private EnumDspMode dspMode;

	/** 总交易笔数 */
	private Long totalCount;

	/** 成功交易笔数 */
	private Long count;

	/** 商户手续费总额 */
	private BigDecimal mchFeeAmt;

	/** 渠道手续费总额 */
	private BigDecimal channelFeeAmt;

	/** 代理分润总额 */
	private BigDecimal agentProfitAmt;

	/** 平台利润总额 */
	private BigDecimal profitAmt;

	/** 平台返点分润 */
	private BigDecimal xtraProfitAmt;

	private String tans_date;

	public String getTans_date() {
		return tans_date;
	}

	public void setTans_date(String tans_date) {
		this.tans_date = tans_date;
	}

	@SuppressWarnings("unchecked")
	private static <T> T convert(Object o, Class<T> target) {
		try {
			if (o == null || target == null) {
				return null;
			}
			else if (target.isAssignableFrom(Long.class)) {
				return (T) CommonUtils.toLong(o);
			}
			else if (target.isAssignableFrom(String.class)) {
				return (T) CommonUtils.toStr(o);
			}
			else if (target.isAssignableFrom(BigDecimal.class)) {
				return (T) new BigDecimal(o.toString());
			}
			else {
				return (T) o;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DownDspInvoiceSumData() {
	}

	public DownDspInvoiceSumData(Object[] data) {
		int index = 0;
		this.mchNo = convert(data[index++], String.class);
		// String strDspMode = (String) data[index++];
		// this.dspMode = strDspMode != null ? EnumDspMode.fromString(strDspMode) : null;
		this.totalCount = convert(data[index++], Long.class);
		this.count = convert(data[index++], Long.class);
		this.mchFeeAmt = convert(data[index++], BigDecimal.class);
		this.channelFeeAmt = convert(data[index++], BigDecimal.class);
		this.agentProfitAmt = convert(data[index++], BigDecimal.class);
		this.profitAmt = convert(data[index++], BigDecimal.class);
		this.xtraProfitAmt = convert(data[index++], BigDecimal.class);

		this.mchName = convert(data[index++], String.class);
	}

	/**
	 * @return the mchNo
	 */
	public String getMchNo() {
		return mchNo;
	}

	/**
	 * @return the mchName
	 */
	public String getMchName() {
		return mchName;
	}

	/**
	 * @return the dspMode
	 */
	public EnumDspMode getDspMode() {
		return dspMode;
	}

	/**
	 * @return the totalCount
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * @return the mchFeeAmt
	 */
	public BigDecimal getMchFeeAmt() {
		return mchFeeAmt;
	}

	/**
	 * @return the channelFeeAmt
	 */
	public BigDecimal getChannelFeeAmt() {
		return channelFeeAmt;
	}

	/**
	 * @return the agentProfitAmt
	 */
	public BigDecimal getAgentProfitAmt() {
		return agentProfitAmt;
	}

	/**
	 * @return the profitAmt
	 */
	public BigDecimal getProfitAmt() {
		return profitAmt;
	}

	/**
	 * @return the xtraProfitAmt
	 */
	public BigDecimal getXtraProfitAmt() {
		return xtraProfitAmt;
	}

}
