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
package com.woodare.template.web.controller.helper;

import java.math.BigDecimal;

import com.woodare.framework.utils.ExcelUtils.CellFormatter;
import com.woodare.template.jpa.model.data.EnumBalSourceType;
import com.woodare.template.jpa.model.data.EnumDepositMode;
import com.woodare.template.jpa.model.data.EnumDspMode;
import com.woodare.template.jpa.model.data.EnumDspStatus;
import com.woodare.template.jpa.model.data.EnumNotifyStatus;
import com.woodare.template.jpa.model.data.EnumQinfenStatus;

/**
 * ClassName: CellFormatterFactory
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Jun 7, 2017
 */
public class CellFormatterFactory {
	public static CellFormatter QF_STATUS = new CellFormatter() {
		@Override
		public String format(Object o) {
			if (EnumQinfenStatus.FAILED.equals(o)) {
				return "代付失败";
			}
			else if (EnumQinfenStatus.IN_PROCESS.equals(o)) {
				return "代付中";
			}
			else if (EnumQinfenStatus.NOT_QF.equals(o)) {
				return "未代付";
			}
			else if (EnumQinfenStatus.SUCCESS.equals(o)) {
				return "代付成功";
			}
			return null;
		}
	};

	public static CellFormatter NOTIFY_STATUS = new CellFormatter() {
		@Override
		public String format(Object o) {
			if (EnumNotifyStatus.CREATED.equals(o)) {
				return "创建";
			}
			else if (EnumNotifyStatus.PENDING.equals(o)) {
				return "等待";
			}
			else if (EnumNotifyStatus.SUCCESS.equals(o)) {
				return "成功";
			}
			else if (EnumNotifyStatus.FAILED.equals(o)) {
				return "失败";
			}
			else if (EnumNotifyStatus.NONEED.equals(o)) {
				return "不需要通知";
			}
			return null;
		}
	};

	public static CellFormatter NO_CARD_CHANNEL = new CellFormatter() {
		@Override
		public String format(Object o) {
			return o != null ? o.toString() : "";
		}
	};

	public static CellFormatter NO_CARD_STATUS = new CellFormatter() {
		@Override
		public String format(Object o) {
			if (o == null) {
				return "";
			}
			else if (o.equals("00")) {
				return "成功";
			}
			else if (o.equals("01") || o.equals("09")) {
				return "交易中";
			}
			else if (o.equals("02")) {
				return "失败";
			}
			else {
				return "";
			}
		}
	};

	public static CellFormatter DSP_STATUS = new CellFormatter() {
		@Override
		public String format(Object o) {
			if (o == null) {
				return "";
			}
			else {
				return ((EnumDspStatus) o).getDesc();
			}
		}
	};

	public static CellFormatter DSP_MODE = new CellFormatter() {
		@Override
		public String format(Object o) {
			return o != null ? ((EnumDspMode) o).getDesc() : "";
		}
	};

	public static CellFormatter TRANSFER_MODE = new CellFormatter() {
		@Override
		public String format(Object o) {
			if (EnumDepositMode.S0.equals(o)) {
				return "T0";
			}
			else if (EnumDepositMode.S1.equals(o)) {
				return "T1";
			}
			else {
				return "";
			}
		}
	};

	public static CellFormatter TRANSFER_STATUS = new CellFormatter() {
		@Override
		public String format(Object o) {
			if (o.equals("00")) {
				return "代付成功";
			}
			else if (o.equals("01")) {
				return "待查证";
			}
			else if (o.equals("09")) {
				return "处理中";
			}
			else if (o.equals("02")) {
				return "代付失败";
			}
			return null;
		}
	};

	public static CellFormatter BAL_SOURCE_TYPE = new CellFormatter() {
		@Override
		public String format(Object o) {
			if (EnumBalSourceType.Deposit.equals(o)) {
				return "代付清算";
			}
			else if (EnumBalSourceType.Invoice.equals(o)) {
				return "快捷交易";
			}
			else if (EnumBalSourceType.Other.equals(o)) {
				return "跑批结算";
			}
			else {
				return "其他";
			}
		}
	};

	public static CellFormatter CARD_TYPE_STATUS = new CellFormatter() {
		@Override
		public String format(Object o) {
			if ("CREDIT".equals(o)) {
				return "信用卡";
			}
			else if ("DEBIT".equals(o)) {
				return "借记卡";
			}
			return "";
		}
	};

	public static CellFormatter FEN_2_YUAN = new CellFormatter() {

		@Override
		public String format(Object o) {
			if (o == null) {
				return "";
			}

			BigDecimal val = null;
			if (o instanceof Long) {
				val = new BigDecimal((long) o);
			}
			if (o instanceof BigDecimal) {
				val = (BigDecimal) o;
			}
			else {
				String sval = o.toString();
				if ("".equals(sval)) {
					return "";
				}
				val = new BigDecimal(sval);
			}
			return val.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_EVEN).toString();
		}

	};

}
