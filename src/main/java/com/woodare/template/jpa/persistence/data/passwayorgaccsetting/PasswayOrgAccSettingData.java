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
package com.woodare.template.jpa.persistence.data.passwayorgaccsetting;

import java.util.Date;
import com.woodare.framework.data.impl.AbstractData;


/**
 * 
 * ClassName: PasswayOrgAccSettingData
 * 
 * @description
 * @author woo_maven_auto_generate
 * 
 */
public class PasswayOrgAccSettingData extends AbstractData {
	private static final long serialVersionUID = -8425020894521497833L;

	private String id;

	private Date createDate;

	private String egwName;

	private String egwMercId;

	private String channelKey;

	private String egwDisName;

	private String tradeKey;

	private String transferKey;

	private String startTime;

	private String endTime;

	private String riskBanks;

	private String whiteBanks;

	private String blackBanks;

	private String extra1;

	private String extra2;

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

	public String getEgwName() {
		return egwName;
	}

	public void setEgwName(String egwName) {
		this.egwName = egwName;
	}

	public String getEgwMercId() {
		return egwMercId;
	}

	public void setEgwMercId(String egwMercId) {
		this.egwMercId = egwMercId;
	}

	public String getChannelKey() {
		return channelKey;
	}

	public void setChannelKey(String channelKey) {
		this.channelKey = channelKey;
	}

	public String getEgwDisName() {
		return egwDisName;
	}

	public void setEgwDisName(String egwDisName) {
		this.egwDisName = egwDisName;
	}

	public String getTradeKey() {
		return tradeKey;
	}

	public void setTradeKey(String tradeKey) {
		this.tradeKey = tradeKey;
	}

	public String getTransferKey() {
		return transferKey;
	}

	public void setTransferKey(String transferKey) {
		this.transferKey = transferKey;
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

	public String getRiskBanks() {
		return riskBanks;
	}

	public void setRiskBanks(String riskBanks) {
		this.riskBanks = riskBanks;
	}

	public String getWhiteBanks() {
		return whiteBanks;
	}

	public void setWhiteBanks(String whiteBanks) {
		this.whiteBanks = whiteBanks;
	}

	public String getBlackBanks() {
		return blackBanks;
	}

	public void setBlackBanks(String blackBanks) {
		this.blackBanks = blackBanks;
	}

	public String getExtra1() {
		return extra1;
	}

	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}

	public String getExtra2() {
		return extra2;
	}

	public void setExtra2(String extra2) {
		this.extra2 = extra2;
	}

}
