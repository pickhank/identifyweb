package com.thirdparty.passway.dsp.cpcn.example;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.thirdparty.passway._data.PasswayDspRequestData;
import com.thirdparty.passway._data.PasswayDspResponseData;
import com.thirdparty.passway.dsp.cpcn.CpcnFileDspApi;
import com.thirdparty.passway.dsp.cpcn.util.CpcnDspConstant;
import com.woodare.core.util.ImageCommonUtils;
import com.woodare.core.util.SDFFactory;
import com.woodare.framework.exception.AbstractWooException;
import com.woodare.framework.utils.JsonUtils;
import com.woodare.template.jersery.servicedata.downdspinvoice.DownDspImageFileData;
import com.woodare.template.jpa.model.data.EnumDspChannel;
import com.woodare.template.jpa.model.data.EnumDspStatus;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceData;

/**
 * 文件批量上传
 * 
 * @author Luke
 */
public class D201Example {

	static CpcnFileDspApi api = new CpcnFileDspApi();

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
		// 有值或无值作为两个case
		dspInv.setRemark("");
		// 流水号
		dspInv.setTransNo(SDFFactory.getDateOrderNo());

		String imgFile = "F:\\1_svnDemo\\xuesongli_identifyweb\\extra\\test.png";
		String imgBase64String = ImageCommonUtils.getImageBase64String(imgFile);

		DownDspImageFileData localFile = new DownDspImageFileData();
		localFile.setFileContent(imgBase64String);
		localFile.setFileName("测试.png");

		List<DownDspImageFileData> localFiles = new ArrayList<DownDspImageFileData>();
		localFiles.add(localFile);

		PasswayDspRequestData reqData = new PasswayDspRequestData();
		reqData.setInvoice(dspInv);
		reqData.setLocalFiles(localFiles);

		PasswayDspResponseData respData = api.processD3201(reqData);
		System.out.println(JsonUtils.toJson(respData));

		if (EnumDspStatus.SUCCESS.equals(respData.getStatus())) {
			System.out.println("处理成功，请求流水号：" + dspInv.getTransNo() + ", 下发流水号：" + respData.getPasswayOrderId());
			System.out.println("返回码：" + respData.getRetCode() + "=" + respData.getRetDesc());
			System.out.println("返回ID：" + JSON.toJSONString(respData.getRespFiles()));
		}
		else if (EnumDspStatus.FAIL.equals(respData.getStatus())) {
			System.out.println("处理失败，请求流水号：" + dspInv.getTransNo() + ", 下发流水号：" + respData.getPasswayOrderId());
			System.out.println("返回码：" + respData.getRetCode() + "=" + respData.getRetDesc());
		}
	}

}
