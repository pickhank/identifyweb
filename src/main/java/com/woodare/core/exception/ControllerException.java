package com.woodare.core.exception;

/**
 * 
 * @author lu_feng
 * 
 */
public class ControllerException extends Exception {

    private static final long serialVersionUID = 2331798360727461128L;

    public ControllerException() {
        super();
    }

    public ControllerException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ControllerException(String detailMessage) {
        super(detailMessage);
    }

    public ControllerException(Throwable throwable) {
        super(throwable);
    }

}