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
package com.woodare.template.jersery.webservice.utils;

import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ClassName: TransExpireSet
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Feb 25, 2017
 * 
 */
public class TransExpireSet<K> extends ConcurrentHashMap<K, Object> {
	private static final long serialVersionUID = 3883547126660410769L;
	{
		Timer timer = new Timer(true);
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				KeyValue<K> kv = null;
				long currentTime = System.currentTimeMillis();
				while (true) {
					kv = queue.peek();
					if (kv != null && kv.value < currentTime) {
						set.remove(kv.key);
						queue.poll();
					} else {
						break;
					}
				}

			}
		}, 1000 * 1, 1000 * 1);
	}

	private Queue<KeyValue<K>> queue = new ConcurrentLinkedQueue<TransExpireSet<K>.KeyValue<K>>();

	private int period = 10;
	private Map<K, Object> set = this;

	public TransExpireSet() {
	}

	/**
	 *
	 * @param period
	 *            过期时间(秒)
	 */
	public TransExpireSet(int period) {
		this.period = period;
	}

	public Object add(K e) {
		KeyValue<K> kv = new KeyValue<K>(e, System.currentTimeMillis() + 1000 * period);
		queue.offer(kv);
		return super.put(e, kv);
	}

	@SuppressWarnings("unchecked")
	public long getExpiredTime(String key) {
		KeyValue<K> kv = (KeyValue<K>)this.get(key);
		return kv != null ? kv.value : -1;
	}
	
	protected class KeyValue<KK> {
		KK key;
		long value;

		public KeyValue(KK key, long value) {
			this.key = key;
			this.value = value;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final TransExpireSet<String> set = new TransExpireSet<String>(5);
		set.add("123");
		for (int j = 0; j < 100; j++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10000; i++) {
						System.out.println("[Start]" + System.currentTimeMillis() + "[aaa " + i + "]");
						set.add("---" + i);
						System.out.println("[end  ]" + System.currentTimeMillis() + "[aaa " + i + "]");
					}
				}
			}).start();
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 1000; i++) {
						System.out.println("[Start]" + System.currentTimeMillis() + "[aaa " + i + "]");
						set.add("---" + i);
						System.out.println("[end  ]" + System.currentTimeMillis() + "[aaa " + i + "]");
					}
				}
			}).start();
		}

		for (int i = 0; i < 10000; i++) {
			System.out.println("[Start]" + System.currentTimeMillis() + "[kkk " + i + "]");
			System.out.println(set.get("aaa" + i) != null);
			System.out.println("[end  ]" + System.currentTimeMillis() + "[kkk " + i + "]");
		}
	}

}
