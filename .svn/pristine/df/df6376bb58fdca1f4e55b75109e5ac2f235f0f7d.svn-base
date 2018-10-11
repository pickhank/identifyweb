/******************************************************************************
 *                                                                             
 *                      Woodare PROPRIETARY INFORMATION                        
 *                              日期工具                       
 *          The information contained herein is proprietary to Woodare         
 *           and shall not be reproduced or disclosed in whole or in part      
 *                    or used for any design or manufacture                    
 *              without direct written authorization from FengDa.              
 *                                                                             
 *            Copyright (c) 2013 by Woodare.  All rights reserved.             
 *                                                                             
 *****************************************************************************/
package com.woodare.core.util;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * ClassName: SDFFactory
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Apr 4, 2017
 */
public class SDFFactory {
	public static class ThreadSaftyDateFormat extends SimpleDateFormat {
		private static final long serialVersionUID = -797037383479610096L;

		ThreadSaftyDateFormat(String format) {
			super(format);
		}

		public synchronized StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
			return super.format(date, toAppendTo, fieldPosition);
		}

		public synchronized Date parse(String text, ParsePosition pos) {
			return super.parse(text, pos);
		}

		public Date sparse(String text) {
			try {
				return super.parse(text);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * yyyyMMddHHmmssSSS
	 */
	public static final ThreadSaftyDateFormat FULL = new ThreadSaftyDateFormat("yyyyMMddHHmmssSSS");

	/**
	 * yyMMddHHmmssSSS
	 */
	public static final ThreadSaftyDateFormat PARTFULL = new ThreadSaftyDateFormat("yyMMddHHmmssSSS");

	/**
	 * yyyyMMddHHmmss
	 */
	public static final ThreadSaftyDateFormat DATETIME = new ThreadSaftyDateFormat("yyyyMMddHHmmss");

	/**
	 * yyyyMM
	 */
	public static final ThreadSaftyDateFormat MONTH = new ThreadSaftyDateFormat("yyyyMM");

	/**
	 * yyMM
	 */
	public static final ThreadSaftyDateFormat PARTMONTH = new ThreadSaftyDateFormat("yyMM");

	/**
	 * yyyyMMdd
	 */
	public static final ThreadSaftyDateFormat DATE = new ThreadSaftyDateFormat("yyyyMMdd");

	/**
	 * HHmmss
	 */
	public static final ThreadSaftyDateFormat TIME = new ThreadSaftyDateFormat("HHmmss");

	/**
	 * HHmmss
	 */
	public static final ThreadSaftyDateFormat TIME_COLON = new ThreadSaftyDateFormat("HH:mm:ss");

	/**
	 * HHmm
	 */
	public static final ThreadSaftyDateFormat TIME_SHORT = new ThreadSaftyDateFormat("HHmm");

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final ThreadSaftyDateFormat DATETIME_DASH = new ThreadSaftyDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static final ThreadSaftyDateFormat DATETIME_DASH_SHORT = new ThreadSaftyDateFormat("yyyy-MM-dd HH:mm");

	/**
	 * yyyy-MM-dd
	 */
	public static final ThreadSaftyDateFormat DATE_DASH = new ThreadSaftyDateFormat("yyyy-MM-dd");

	/**
	 * yyyy/MM/dd HH:mm
	 */
	public static final ThreadSaftyDateFormat DATETIME_SLASH = new ThreadSaftyDateFormat("yyyy/MM/dd HH:mm");

	/**
	 * yyyy/MM/dd
	 */
	public static final ThreadSaftyDateFormat DATE_SLASH = new ThreadSaftyDateFormat("yyyy/MM/dd");

	/**
	 * yyMMddHHmm
	 */
	public static final ThreadSaftyDateFormat SHORT_DATETIME = new ThreadSaftyDateFormat("yyMMddHHmm");

	/**
	 * @return
	 */
	public static Date getTodayWithCleanTime() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);

		return today.getTime();
	}

	/**
	 * @return
	 */
	public static Date getDateWithDiff(int diff) {
		return getDateWithDiff(new Date(), diff);
	}

	/**
	 * @return
	 */
	public static Date getDateWithDiff(Date date, int amount) {
		return getDateWithDiff(date, amount, Calendar.DATE);
	}

	/**
	 * @return
	 */
	public static Date getDateWithDiff(Date date, int amount, int field) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * @param date
	 * @return
	 */
	public static Date getStartTimeInDay(String dateStr) {
		try {
			Date date = SDFFactory.DATE.parse(dateStr);

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			return cal.getTime();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param date
	 * @return
	 */
	public static Date getEndTimeInDay(String dateStr) {
		try {
			Date date = SDFFactory.DATE.parse(dateStr);

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 999);

			return cal.getTime();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @return
	 */
	private static MemCacheSequence _orderSequence = new MemCacheSequence(3, MemCacheSequence.READABLE);

	public static synchronized String getOrderNo() {
		return _orderSequence.next();
	}

	public static synchronized String getDateOrderNo() {
		return _orderSequence.nextDateCleaned();
	}

	/**
	 * @return
	 */
	private static MemCacheSequence _balanceSequence = new MemCacheSequence(3, MemCacheSequence.READABLE);

	public static synchronized String getBalanceNo() {
		return "VIN" + _balanceSequence.nextCleaned();
	}

	/**
	 * @return
	 */
	private static MemCacheSequence _qrcodeSequence = new MemCacheSequence(3, MemCacheSequence.READABLE);

	public static synchronized String getQrcodeNo() {
		return _qrcodeSequence.nextBase64();
	}

	/**
	 * @return
	 */
	private static MemCacheSequence _logSequence = new MemCacheSequence(3, MemCacheSequence.RANDABLE);

	public static String getLogId() {
		return _logSequence.next();
	}

	/**
	 * @return
	 */
	private static MemCacheSequence _rlogSequence = new MemCacheSequence(3, MemCacheSequence.RANDABLE);

	public static String getRestLogId() {
		return _rlogSequence.next();
	}

	/**
	 * 
	 */
	private static MemCacheSequence _notifySequence = new MemCacheSequence(3, MemCacheSequence.RANDABLE);

	public static String getNotifyId() {
		return "N" + _notifySequence.nextCleaned();
	}

	public static void main(String[] args) {
		final List<String> existedIds = new ArrayList<String>();

		for (int i = 0; i < 100000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					String id = getNotifyId();
					if (existedIds.contains(id)) {
						throw new RuntimeException("Duplicate");
					}
				}
			}).start();
		}
	}
}
