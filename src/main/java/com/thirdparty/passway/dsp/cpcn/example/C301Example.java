package com.thirdparty.passway.dsp.cpcn.example;

import com.thirdparty.passway._data.PasswayDspRequestData;
import com.thirdparty.passway._data.PasswayDspResponseData;
import com.thirdparty.passway.dsp.cpcn.CpcnDspApi;
import com.thirdparty.passway.dsp.cpcn.util.CpcnDspConstant;
import com.woodare.core.util.SDFFactory;
import com.woodare.framework.exception.AbstractWooException;
import com.woodare.framework.utils.JsonUtils;
import com.woodare.template.jpa.model.data.EnumDspChannel;
import com.woodare.template.jpa.model.data.EnumDspMode;
import com.woodare.template.jpa.model.data.EnumDspStatus;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceData;

/**
 * @author Luke
 */
public class C301Example {
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
		dspInv.setDspMode(EnumDspMode.C301);
		dspInv.setHolderName("卢峰");
		dspInv.setIdentifyType("0");
		dspInv.setIdentifyNo("321323198606045338");
		//不填->验证成功，1->验证失败，其他->请求失败
		dspInv.setRemark("");
		dspInv.setTransNo(SDFFactory.getDateOrderNo());

		// Delta R NOT NULL 用于校验上传图片的加密字符串
		String resv1 = "11111";
		dspInv.setResv1(resv1);

		// ImageBest R NOT NULL 质量最佳图片文件 ID（先通过接口 3201 上传图片，获取文件 ID）
		String ImageBest = "1809221616581634667593070";
		// ImageEnv R NOT NULL 假脸判定图片文件 ID（先通过接口3201 上传图片，获取文件 ID）
		String ImageEnv = "1809221616581634667593070";
		String resv2 = ImageBest + "," + ImageEnv;
		dspInv.setResv2(resv2);

		// Remark O 备注

		PasswayDspRequestData reqData = new PasswayDspRequestData();
		reqData.setInvoice(dspInv);

		PasswayDspResponseData respData = api.processC301(reqData);
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
