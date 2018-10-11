package com.woodare.template.web.viewdata.passwaydspmerchant;

import java.math.BigDecimal;

import com.woodare.template.jpa.model.data.EnumDspMode;

/**
 * @author Luke
 */
public class PasswayDspMerchantFeeData {

	private EnumDspMode dspMode;

	/** 单笔加收费，单位：元 */
	private BigDecimal addFeeAmt;

	/** 返点费用，单位：元 */
	private BigDecimal feedFeeAmt;

	/**
	 * @param values
	 */
	public PasswayDspMerchantFeeData(String[] values) {
		dspMode = EnumDspMode.fromString(values[0]);
		addFeeAmt = new BigDecimal(values[1].replaceAll("[^0.-9]", "")).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		feedFeeAmt = new BigDecimal(values[2].replaceAll("[^0.-9]", "")).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * @return the dspMode
	 */
	public EnumDspMode getDspMode() {
		return dspMode;
	}

	/**
	 * @return the addFeeAmt
	 */
	public BigDecimal getAddFeeAmt() {
		return addFeeAmt;
	}

	/**
	 * @return the feedFeeAmt
	 */
	public BigDecimal getFeedFeeAmt() {
		return feedFeeAmt;
	}

}
