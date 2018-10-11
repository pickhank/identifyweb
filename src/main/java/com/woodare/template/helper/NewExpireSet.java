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
package com.woodare.template.helper;

import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ClassName: TransExpireSet
 * 
 * @description 首次进队列后，开始计数，每天添加存在数值时计数器扣减一次，归零后返回成功
 * @author luke
 * @Date May 26, 2018
 */
public class NewExpireSet extends ConcurrentHashMap<String, Object> {
	private static final long serialVersionUID = -7724014524816740436L;

	private Queue<String> queue = new ConcurrentLinkedQueue<String>();

	private int period = 10;
	private NewExpireSet set = this;
	{
		Timer timer = new Timer(true);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				long ctime = System.currentTimeMillis();
				while (true) {
					String k = queue.peek();
					if (k == null) {
						break;
					}
					KeyValue kv = (KeyValue) set.get(k);
					if (kv != null && kv.expiretime < ctime) {
						set.remove(kv.key);
						queue.poll();
					}
					else {
						break;
					}
				}
			}
		}, 1000 * 1, 1000 * 1);
	}

	/**
	 * @param max
	 * @param period
	 */
	public NewExpireSet(int period) {
		this.period = period;
	}

	/**
	 * @param key
	 * @return
	 */
	public boolean add(String key) {
		boolean ret = true;
		if (super.contains(key)) {
			ret = false;
		}
		else {
			KeyValue kv = null;
			synchronized (this) {
				kv = new KeyValue(key, System.currentTimeMillis() + 1000 * period);
				queue.offer(key);
			}
			super.put(key, kv);
		}
		return ret;
	}

	public long getExpiredTime(String key) {
		KeyValue kv = (KeyValue) this.get(key);
		return kv != null ? kv.expiretime : -1;
	}

	protected class KeyValue {
		String key;
		long expiretime;

		public KeyValue(String key, long expiretime) {
			this.key = key;
			this.expiretime = expiretime;
		}

		@Override
		public boolean equals(Object val) {
			if (val instanceof KeyValue) {
				return key.equals(((KeyValue) val).key);
			}
			return false;
		}
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		final NewExpireSet set = new NewExpireSet( 2);

		if (set.add("1")) {
			System.out.println("Succ1");
		}
		else {
			System.out.println("Fail1");
		}

		if (set.add("1")) {
			System.out.println("Succ2");
		}
		else {
			System.out.println("Fail2");
		}

		if (set.add("1")) {
			System.out.println("Succ3");
		}
		else {
			System.out.println("Fail3");
		}

		Thread.sleep(3 * 1000);
		if (set.add("1")) {
			System.out.println("Succ3");
		}
		else {
			System.out.println("Fail3");
		}
		//
		// for (int i = 0; i < 10000; i++) {
		// System.out.println("[Start]" + System.currentTimeMillis() + "[kkk " + i + "]");
		// System.out.println(set.get("aaa" + i) != null);
		// System.out.println("[end ]" + System.currentTimeMillis() + "[kkk " + i + "]");
		// }
	}
}
