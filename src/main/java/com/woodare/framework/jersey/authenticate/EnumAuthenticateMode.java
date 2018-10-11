package com.woodare.framework.jersey.authenticate;

/**
 * 
 * @author lu_feng
 * 
 */
public enum EnumAuthenticateMode {

    /** 快速登录 */
    FAST,

    /** token登录 */
    AUTHTOKEN,

    /** 密码登录 */
    PASSWD,

    /** 社交登录 */
    SOCIAL
}
