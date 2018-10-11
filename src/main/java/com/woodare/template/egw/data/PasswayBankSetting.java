package com.woodare.template.egw.data;

import java.math.BigDecimal;

/**
 * 
 * @author Luke
 *
 */
public class PasswayBankSetting {

    // 银行清算行号
    private String bankUnionCode;

    // 银行名称
    private String bankName;

    // 最大允许交易金额
    private BigDecimal maxAmt;

    public PasswayBankSetting(String bankName, BigDecimal maxAmt) {
        // this.bankUnionCode = bankUnionCode;
        this.bankName = bankName;
        this.maxAmt = maxAmt;
    }
    
    public PasswayBankSetting() {
       
    }

    /**
     * @return the bankUnionCode
     */
    public String getBankUnionCode() {
        return bankUnionCode;
    }

    /**
     * @param bankUnionCode
     *            the bankUnionCode to set
     */
    public void setBankUnionCode(String bankUnionCode) {
        this.bankUnionCode = bankUnionCode;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName
     *            the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the maxAmt
     */
    public BigDecimal getMaxAmt() {
        return maxAmt;
    }

    /**
     * @param maxAmt
     *            the maxAmt to set
     */
    public void setMaxAmt(BigDecimal maxAmt) {
        this.maxAmt = maxAmt;
    }
}
