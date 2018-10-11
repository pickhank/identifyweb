package com.woodare.core.util;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.woodare.framework.utils.SysProperties;

/**
 * @author Luke
 */
public class MemCacheSequence {
	private static String _PREFIX = SysProperties.getInstance().getProperty("server.position", "D");

	private SimpleDateFormat PARTFULL = new _ThreadSaftyDateFormat("yyMMddHHmmssSSS");
	private SimpleDateFormat FULL = new _ThreadSaftyDateFormat("yyyyMMddHHmmssSSS");

	private static class _ThreadSaftyDateFormat extends SimpleDateFormat {
		private static final long serialVersionUID = -797037383479610096L;

		_ThreadSaftyDateFormat(String format) {
			super(format);
		}

		public synchronized StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
			return super.format(date, toAppendTo, fieldPosition);
		}
	}

	/**
	 * 可读
	 */
	public static int READABLE = 0;

	/**
	 * 随机
	 */
	public static int RANDABLE = 1;

	private String _lastSequenceNo;

	private int start = 0;
	private int current = 0;
	private int end = 0;

	private int mode = 0;

	/**
	 * @param size
	 * @param mode
	 */
	public MemCacheSequence(int size, int mode) {
		current = start = (int) Math.pow(10, size);
		end = (int) Math.pow(10, size + 1) - 1;
		this.mode = mode;
	}

	/**
	 * @return
	 */
	private String _getFormatedValue() {
		return _PREFIX + _getCleanFormatedValue();
	}

	/**
	 * @return
	 */
	private String _getCleanFormatedValue() {
		String val = null;

		if (this.mode == READABLE) {
			val = PARTFULL.format(new Date());
		}
		else if (this.mode == RANDABLE) {
			val = new Date().getTime() + "";
			val = val.substring(2);
		}
		else {
			val = PARTFULL.format(new Date());
		}

		return val + current;
	}

	/**
	 * @return
	 */
	private String _getDateFormatedValue() {
		return FULL.format(new Date()) + "" + current;
	}

	/**
	 * @return
	 */
	public synchronized String next() {
		String val = _getFormatedValue();
		if (current >= end) {
			current = start;
			if (val.equals(_lastSequenceNo)) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
			}
			_lastSequenceNo = val;
		}
		else {
			current++;
		}
		// 增加一位随机数
		return val; // + new Random().nextInt(10);
	}

	/**
	 * @return
	 */
	public synchronized String nextCleaned() {
		String val = _getCleanFormatedValue();
		if (current >= end) {
			current = start;
			if (val.equals(_lastSequenceNo)) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
			}
			_lastSequenceNo = val;
		}
		else {
			current++;
		}
		// 增加一位随机数
		return val; // + new Random().nextInt(10);
	}

	/**
	 * @return
	 */
	public synchronized String nextDateCleaned() {
		String val = this._getDateFormatedValue();
		if (current >= end) {
			current = start;
			if (val.equals(_lastSequenceNo)) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
			}
			_lastSequenceNo = val;
		}
		else {
			current++;
		}
		// 增加一位随机数
		return val; // + new Random().nextInt(10);
	}

	/**
	 * @return
	 */
	public synchronized String nextBase64() {
		String val = _getCleanFormatedValue();
		if (current >= end) {
			current = start;
			if (val.equals(_lastSequenceNo)) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
			}
			_lastSequenceNo = val;
		}
		else {
			current++;
		}
		// 增加一位随机数
		return UrlBase64Coder.encoded(HexCodec.hexDecode(val));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MemCacheSequence tool = new MemCacheSequence(3, MemCacheSequence.RANDABLE);
		for (int i = 0; i < 100; i++) {
			System.out.println(tool.nextBase64());
		}
	}
}
