package com.woodare.framework.exception;

/**
 * @author Luke
 */
public class EmbedWooException extends Exception {
	private static final long serialVersionUID = -1913976497560604607L;

	private Exception e;
	private Object obj;

	/**
	 * @param e
	 * @param obj
	 */
	public EmbedWooException(Exception e) {
		this.e = e;
	}

	/**
	 * @param e
	 * @param obj
	 */
	public EmbedWooException(Exception e, Object obj) {
		this.e = e;
		this.obj = obj;
	}

	/**
	 * @return the e
	 */
	public Exception getE() {
		return e;
	}

	/**
	 * @param e
	 *            the e to set
	 */
	public void setE(Exception e) {
		this.e = e;
	}

	/**
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * @param obj
	 *            the obj to set
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}
}
