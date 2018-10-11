package com.thirdparty.passway._data;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author Luke
 *
 */
public class BaseRequestData extends JSONObject {
    private static final long serialVersionUID = -611043588489072189L;

    private String reqNo;

    private String reqDate;

    /**
     * @return the reqNo
     */
    public String getReqNo() {
        return reqNo;
    }

    /**
     * @param reqNo
     *            the reqNo to set
     */
    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    /**
     * @return the reqDate
     */
    public String getReqDate() {
        return reqDate;
    }

    /**
     * @param reqDate
     *            the reqDate to set
     */
    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

}
