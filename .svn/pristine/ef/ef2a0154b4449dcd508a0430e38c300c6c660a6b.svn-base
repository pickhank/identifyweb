package com.thirdparty.passway.dsp.cpcn.example;

import com.thirdparty.passway._data.PasswayDspRequestData;
import com.thirdparty.passway._data.PasswayDspResponseData;
import com.thirdparty.passway.dsp.cpcn.CpcnDspApi;
import com.thirdparty.passway.dsp.cpcn.util.CpcnDspConstant;
import com.woodare.core.util.SDFFactory;
import com.woodare.framework.exception.AbstractWooException;
import com.woodare.framework.utils.JsonUtils;
import com.woodare.template.jpa.model.data.EnumDspChannel;
import com.woodare.template.jpa.model.data.EnumDspStatus;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceData;

/**
 * 银行卡二要素鉴权示例
 * 
 * @author Luke
 */
public class C112Example {
	static CpcnDspApi api = new CpcnDspApi();

	/**
	 * @param args
	 * @throws AbstractWooException
	 */
	public static void main(String[] args) throws AbstractWooException {
		CpcnDspConstant.initPasswayMerchants();

		// PasswayDspResponseData processC102(PasswayDspRequestData reqData) throws AbstractWooException {
		DownDspInvoiceData dspInv = new DownDspInvoiceData();
		dspInv.setChannel(EnumDspChannel.CPCN);
		dspInv.setChannelAccNo(CpcnDspConstant.MERC_TEST);
		dspInv.setHolderName("卢峰");
		dspInv.setCardNo("6217001370031080435");
		dspInv.setTransNo(SDFFactory.getOrderNo());
		//不填->验证成功，1->验证失败，其他->请求失败
		dspInv.setRemark("");

		PasswayDspRequestData reqData = new PasswayDspRequestData();
		reqData.setInvoice(dspInv);

		PasswayDspResponseData respData = api.processC112(reqData);
		System.out.println(JsonUtils.toJson(respData));

		if (EnumDspStatus.SUCCESS.equals(respData.getStatus())) {
			System.out.println("处理成功，请求流水号：" + dspInv.getTransNo() + ", 下发流水号：" + respData.getPasswayOrderId());
			System.out.println("返回码：" + respData.getRetCode() + "=" + respData.getRetDesc());
			System.out.println("验证结果：" + respData.getVerifyStatus());
		}
		else if (EnumDspStatus.FAIL.equals(respData.getStatus())) {
			System.out.println("处理失败，请求流水号：" + dspInv.getTransNo() + ", 下发流水号：" + respData.getPasswayOrderId());
			System.out.println("返回码：" + respData.getRetCode() + "=" + respData.getRetDesc());
		}
	}
}
