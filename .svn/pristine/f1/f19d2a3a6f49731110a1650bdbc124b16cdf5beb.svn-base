package com.woodare.template.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.model.data.EnumDspChannel;

/**
 * @author Luke
 */
@Entity
@EntityDescriptor(name = "验证通道商户", category = "content")
public class PasswayDspMerchant extends AbstractBusiModel {
	private static final long serialVersionUID = -552716943758151832L;

	/** 通道标识 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EnumDspChannel channel;

	/** 通道账户编号 */
	@Column(length = 64)
	private String channelAccNo;

	/** 通道账户名称 */
	@Column(length = 20)
	private String channelAccName;

	/** 渠道签名KEY密文 */
	@Column(length = 512)
	private String signKey;

	/** 渠道加密KEY密文 */
	@Column(length = 512)
	private String encKey;

	/** 额外参数 **/
	@Lob
	private String extra;

	/** 额外参数 **/
	@Column(length = 512)
	private String remark;

	/** 费率配置信息 **/
	@Column(length = 500)
	private String feeText;

	/** 状态 */
	@Enumerated(EnumType.STRING)
	private EnumDownUserStatus status;

	/**
	 * @return the channel
	 */
	public EnumDspChannel getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(EnumDspChannel channel) {
		this.channel = channel;
	}

	/**
	 * @return the channelAccNo
	 */
	public String getChannelAccNo() {
		return channelAccNo;
	}

	/**
	 * @param channelAccNo
	 *            the channelAccNo to set
	 */
	public void setChannelAccNo(String channelAccNo) {
		this.channelAccNo = channelAccNo;
	}

	/**
	 * @return the channelAccName
	 */
	public String getChannelAccName() {
		return channelAccName;
	}

	/**
	 * @param channelAccName
	 *            the channelAccName to set
	 */
	public void setChannelAccName(String channelAccName) {
		this.channelAccName = channelAccName;
	}

	/**
	 * @return the signKey
	 */
	public String getSignKey() {
		return signKey;
	}

	/**
	 * @param signKey
	 *            the signKey to set
	 */
	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	/**
	 * @return the encKey
	 */
	public String getEncKey() {
		return encKey;
	}

	/**
	 * @param encKey
	 *            the encKey to set
	 */
	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}

	/**
	 * @return the extra
	 */
	public String getExtra() {
		return extra;
	}

	/**
	 * @param extra
	 *            the extra to set
	 */
	public void setExtra(String extra) {
		this.extra = extra;
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
	 * @return the feeText
	 */
	public String getFeeText() {
		return feeText;
	}

	/**
	 * @param feeText
	 *            the feeText to set
	 */
	public void setFeeText(String feeText) {
		this.feeText = feeText;
	}

	/**
	 * @return the status
	 */
	public EnumDownUserStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(EnumDownUserStatus status) {
		this.status = status;
	}
}
