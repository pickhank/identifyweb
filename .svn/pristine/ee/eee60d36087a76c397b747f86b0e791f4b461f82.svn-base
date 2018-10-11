package com.woodare.core.jpa.rws;

/**
 * @author Luke
 */
public class RwDataSourceHolder {
	public static final String MASTER = "master";   // 主(写)连接池
	public static final String SLAVE = "slave";     // 从(读)连接池

	public static final ThreadLocal<String> holder = new ThreadLocal<String>();

	public static void localMaster() {
		holder.set(MASTER);
	}

	public static void localSlave() {
		holder.set(SLAVE);
	}

	public static String getDataSouce() {
		return holder.get();
	}
}
