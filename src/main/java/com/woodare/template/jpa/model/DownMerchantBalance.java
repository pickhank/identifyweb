package com.woodare.template.jpa.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;
import com.woodare.template.jpa.model.data.EnumBalSourceType;
import com.woodare.template.jpa.model.data.EnumBalanceAccType;

/**
 * ClassName: DownMerchantBalance
 * 唯一索引：balanceNo
 * 索引：mchNo
 * 索引：balanceDate
 * 索引：referTransNo
 * 
 * @description
 * @author Luke
 * @Date Feb 28, 2018
 */
@Entity
@EntityDescriptor(name = "机构收支明细", category = "content")
public class DownMerchantBalance extends AbstractBusiModel {
	private static final long serialVersionUID = -2003913977068224361L;

	/** 机构号 **/
	@Column(length = 20, nullable = false)
	private String mchNo;

	/** 机构名称 **/
	@Column(length = 100)
	private String mchName;

	/** 收支日期YYYYMMDD */
	@Column(length = 8, nullable = false)
	private String balanceDate;

	/** 收支流水号 */
	@Column(length = 64, nullable = false, unique = false)
	private String balanceNo;

	/** 收支序号 */
	private Short sortNum;

	/** 资金账户 */
	@Enumerated(EnumType.ORDINAL)
	private EnumBalanceAccType accType;

	/** 来源类型 */
	@Enumerated(EnumType.ORDINAL)
	private EnumBalSourceType sourceType;

	/** 调整资金（元） **/
	@Column(scale = 2, precision = 9)
	private BigDecimal diffAmt;

	/** 账户余额（元） **/
	@Column(scale = 2, precision = 9)
	private BigDecimal balAmt;

	/** 关联流水编号 **/
	@Column(length = 64)
	private String referTransNo;

	/** 关联渠道 **/
	@Column(length = 20)
	private String referChannel;

	/** 关联渠道商户 **/
	@Column(length = 64)
	private String referChannelAccNo;

	/** 备注 **/
	@Column(length = 100)
	private String remark;

	/** 保留字段1 **/
	@Column(length = 100)
	private String balResv1;

	/** 保留字段2 **/
	@Column(length = 200)
	private String balResv2;

	/**
	 * @return the sortNum
	 */
	public Short getSortNum() {
		return sortNum;
	}

	/**
	 * @param sortNum
	 *            the sortNum to set
	 */
	public void setSortNum(Short sortNum) {
		this.sortNum = sortNum;
	}

	/**
	 * @return the mchNo
	 */
	public String getMchNo() {
		return mchNo;
	}

	/**
	 * @param mchNo
	 *            the mchNo to set
	 */
	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
	}

	/**
	 * @return the mchName
	 */
	public String getMchName() {
		return mchName;
	}

	/**
	 * @param mchName
	 *            the mchName to set
	 */
	public void setMchName(String mchName) {
		this.mchName = mchName;
	}

	/**
	 * @return the balanceDate
	 */
	public String getBalanceDate() {
		return balanceDate;
	}

	/**
	 * @param balanceDate
	 *            the balanceDate to set
	 */
	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}

	/**
	 * @return the balanceNo
	 */
	public String getBalanceNo() {
		return balanceNo;
	}

	/**
	 * @param balanceNo
	 *            the balanceNo to set
	 */
	public void setBalanceNo(String balanceNo) {
		this.balanceNo = balanceNo;
	}

	/**
	 * @return the accType
	 */
	public EnumBalanceAccType getAccType() {
		return accType;
	}

	/**
	 * @param accType
	 *            the accType to set
	 */
	public void setAccType(EnumBalanceAccType accType) {
		this.accType = accType;
	}

	/**
	 * @return the sourceType
	 */
	public EnumBalSourceType getSourceType() {
		return sourceType;
	}

	/**
	 * @param sourceType
	 *            the sourceType to set
	 */
	public void setSourceType(EnumBalSourceType sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * @return the diffAmt
	 */
	public BigDecimal getDiffAmt() {
		return diffAmt;
	}

	/**
	 * @param diffAmt
	 *            the diffAmt to set
	 */
	public void setDiffAmt(BigDecimal diffAmt) {
		this.diffAmt = diffAmt;
	}

	/**
	 * @return the balAmt
	 */
	public BigDecimal getBalAmt() {
		return balAmt;
	}

	/**
	 * @param balAmt
	 *            the balAmt to set
	 */
	public void setBalAmt(BigDecimal balAmt) {
		this.balAmt = balAmt;
	}

	/**
	 * @return the referTransNo
	 */
	public String getReferTransNo() {
		return referTransNo;
	}

	/**
	 * @param referTransNo
	 *            the referTransNo to set
	 */
	public void setReferTransNo(String referTransNo) {
		this.referTransNo = referTransNo;
	}

	/**
	 * @return the referChannel
	 */
	public String getReferChannel() {
		return referChannel;
	}

	/**
	 * @param referChannel
	 *            the referChannel to set
	 */
	public void setReferChannel(String referChannel) {
		this.referChannel = referChannel;
	}

	/**
	 * @return the referChannelAccNo
	 */
	public String getReferChannelAccNo() {
		return referChannelAccNo;
	}

	/**
	 * @param referChannelAccNo
	 *            the referChannelAccNo to set
	 */
	public void setReferChannelAccNo(String referChannelAccNo) {
		this.referChannelAccNo = referChannelAccNo;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the balResv1
	 */
	public String getBalResv1() {
		return balResv1;
	}

	/**
	 * @param balResv1
	 *            the balResv1 to set
	 */
	public void setBalResv1(String balResv1) {
		this.balResv1 = balResv1;
	}

	/**
	 * @return the balResv2
	 */
	public String getBalResv2() {
		return balResv2;
	}

	/**
	 * @param balResv2
	 *            the balResv2 to set
	 */
	public void setBalResv2(String balResv2) {
		this.balResv2 = balResv2;
	}

}
