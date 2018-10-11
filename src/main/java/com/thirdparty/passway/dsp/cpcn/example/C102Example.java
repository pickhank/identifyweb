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
 * 身份信息二要素鉴权示例
 * 
 * @author Luke
 */
public class C102Example {
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
		dspInv.setIdentifyNo("321323198606045338");
		dspInv.setIdentifyType("0");
		//不填->验证成功，1->验证失败，其他->请求失败
		dspInv.setRemark("");
		dspInv.setTransNo(SDFFactory.getOrderNo());

		PasswayDspRequestData reqData = new PasswayDspRequestData();
		reqData.setInvoice(dspInv);

		PasswayDspResponseData respData = api.processC102(reqData);
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
