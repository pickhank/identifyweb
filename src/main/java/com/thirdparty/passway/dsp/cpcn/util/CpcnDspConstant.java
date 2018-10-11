package com.thirdparty.passway.dsp.cpcn.util;

import java.util.ArrayList;
import java.util.List;

import com.woodare.core.util.AesUtils;
import com.woodare.framework.utils.SysProperties;
import com.woodare.template.helper.cache.PasswayDspMerchants;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.model.data.EnumDspChannel;
import com.woodare.template.jpa.persistence.data.passwaydspmerchant.PasswayDspMerchantData;

import cpcn.dsp.institution.api.system.DSPInstitutionEnvironment;

/**
 * @author Luke
 */
public class CpcnDspConstant {

	// 中金测试
	public static String MERC_TEST = "100174";

	/**
	 * 
	 */
	public static void initMerchantCerts() {
		try {
			String dspConfigPath = SysProperties.getInstance().getProperty("cpcn.certs.path", "F:\\1_svnDemo\\xuesongli_identifyweb\\target\\classes\\certs\\dsp");

			// 初始化支付环境
			DSPInstitutionEnvironment.initialize(dspConfigPath);
			// 初始化行业分类字典
			SystemEnvironment.initIndustry(dspConfigPath);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * 
	 */
	public static void initPasswayMerchants() {

		try {
			List<PasswayDspMerchantData> items = new ArrayList<PasswayDspMerchantData>();

			// 测试账户
			PasswayDspMerchantData param = new PasswayDspMerchantData();
			param.setChannel(EnumDspChannel.CPCN);
			param.setChannelAccName("中金测试");
			param.setChannelAccNo(MERC_TEST);
			param.setSignKey(AesUtils.encrypt("0123456789ABCDEF", EnumDspChannel.CPCN.getAesKey()));
			param.setEncKey(AesUtils.encrypt("0123456789ABCDEF", EnumDownNoCardChannel.ZHFU.getAesKey()));
			param.setExtra("222.72.248.38:30011");
			param.setStatus(EnumDownUserStatus.ACTIVE);
			items.add(param);

			PasswayDspMerchants.resetAll(items);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
