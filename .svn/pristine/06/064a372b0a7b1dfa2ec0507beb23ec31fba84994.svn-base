package com.woodare.template.egw.data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;

/**
 * @author Luke
 */
public class PasswayRouteVerifyData {

	private EnumDownNoCardChannel channel;

	// 最小交易额，单位：元
	private BigDecimal minAmt;

	// 最大交易额，单位：元
	private BigDecimal maxAmt;

	// 最小交易费率，千分位单位，如：3.8表示费率0.0038
	private BigDecimal minFeeRatio;

	// 最小单笔手续费
	private BigDecimal minDrawFee;

	// 开始时间
	private String startTime;

	// 结束时间
	private String endTime;

	private Map<String, PasswayBankSetting> bankMap = new HashMap<String, PasswayBankSetting>();

	/** 支付卡-支持银行 **/
	private List<String> supportBanks = null;

	/** 支付卡-不支持银行 **/
	private List<String> blackBanks = null;

	/** 结算卡-支持银行 **/
	private List<String> chargeSupportBanks = null;

	/**
	 * @return the blackBanks
	 */
	public List<String> getBlackBanks() {
		return blackBanks;
	}

	/**
	 * @param blackBanks
	 *            the blackBanks to set
	 */
	public void setBlackBanks(List<String> blackBanks) {
		this.blackBanks = blackBanks;
	}

	/**
	 * @return the chargeSupportBanks
	 */
	public List<String> getChargeSupportBanks() {
		return chargeSupportBanks;
	}

	/**
	 * @param chargeSupportBanks
	 *            the chargeSupportBanks to set
	 */
	public void setChargeSupportBanks(List<String> chargeSupportBanks) {
		this.chargeSupportBanks = chargeSupportBanks;
	}

	/**
	 * @return the supportBanks
	 */
	public List<String> getSupportBanks() {
		return supportBanks;
	}

	/**
	 * @param supportBanks
	 *            the supportBanks to set
	 */
	public void setSupportBanks(List<String> supportBanks) {
		this.supportBanks = supportBanks;
	}

	/**
	 * @return the maxAmt
	 */
	public BigDecimal getMaxAmt() {
		return maxAmt;
	}

	/**
	 * @param maxAmt
	 *            the maxAmt to set
	 */
	public void setMaxAmt(BigDecimal maxAmt) {
		this.maxAmt = maxAmt;
	}

	public PasswayRouteVerifyData() {
	}

	public PasswayRouteVerifyData(EnumDownNoCardChannel channel) {
		this.channel = channel;
	}

	public PasswayRouteVerifyData(EnumDownNoCardChannel channel, BigDecimal minAmt) {
		this.channel = channel;
		this.minAmt = minAmt;
	}

	/**
	 * @return the channel
	 */
	public EnumDownNoCardChannel getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(EnumDownNoCardChannel channel) {
		this.channel = channel;
	}

	/**
	 * @return the minAmt
	 */
	public BigDecimal getMinAmt() {
		return minAmt;
	}

	/**
	 * @param minAmt
	 *            the minAmt to set
	 */
	public void setMinAmt(BigDecimal minAmt) {
		this.minAmt = minAmt;
	}

	/**
	 * @return the minFeeRatio
	 */
	public BigDecimal getMinFeeRatio() {
		return minFeeRatio;
	}

	/**
	 * @param minFeeRatio
	 *            the minFeeRatio to set
	 */
	public void setMinFeeRatio(BigDecimal minFeeRatio) {
		this.minFeeRatio = minFeeRatio;
	}

	/**
	 * @return the minDrawFee
	 */
	public BigDecimal getMinDrawFee() {
		return minDrawFee;
	}

	/**
	 * @param minDrawFee
	 *            the minDrawFee to set
	 */
	public void setMinDrawFee(BigDecimal minDrawFee) {
		this.minDrawFee = minDrawFee;
	}

	/**
	 * @return the bankMap
	 */
	public Map<String, PasswayBankSetting> getBankMap() {
		return bankMap;
	}

	/**
	 * @param bankMap
	 *            the bankMap to set
	 */
	public void setBankMap(Map<String, PasswayBankSetting> bankMap) {
		this.bankMap = bankMap;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
