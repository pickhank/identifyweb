package com.woodare.template.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.woodare.framework.utils.SysProperties;

/**
 * @author Luke
 */
public class QrcodesHelper {

	/**
	 * @param templateId
	 * @return
	 */
	public static List<QrcodeSettingTemplate> getAll() {
		List<QrcodeSettingTemplate> items = new ArrayList<QrcodeSettingTemplate>();

		String strQrcodeSettings = SysProperties.getInstance().getProperty("qrcode.limit.amt", "A,9-29,10,2;B,0.01-0.05,0.01,3;");
		String[] qrcodeSettingsArr = strQrcodeSettings.split(";", -1);
		for (String qrcodeSettings : qrcodeSettingsArr) {
			String[] settings = qrcodeSettings.split(",", -1);
			if (settings.length >= 4) {
				String tmplId = settings[0];
				String range = settings[1];
				String strStep = settings[2];
				String strNum = settings[3];

				QrcodeSettingTemplate res = new QrcodeSettingTemplate();
				res.tmplId = tmplId;
				res.s = new BigDecimal(range.split("[-]")[0]).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				res.e = new BigDecimal(range.split("[-]")[1]).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				res.step = new BigDecimal(strStep).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				res.num = Integer.parseInt(strNum);

				items.add(res);
			}
		}
		return items;
	}

	/**
	 * @param templateId
	 * @return
	 */
	public static QrcodeSettingTemplate getByTemplateId(String templateId) {
		QrcodeSettingTemplate res = null;

		if (templateId != null) {
			String strQrcodeSettings = SysProperties.getInstance().getProperty("qrcode.limit.amt", "A,9-29,10,2;B,0.01-0.05,0.01,3;");
			String[] qrcodeSettingsArr = strQrcodeSettings.split(";", -1);
			for (String qrcodeSettings : qrcodeSettingsArr) {
				if (qrcodeSettings.startsWith(templateId)) {
					String[] settings = qrcodeSettings.split(",", -1);
					if (settings.length >= 4) {
						String range = qrcodeSettings.split(",")[1];
						String strStep = qrcodeSettings.split(",")[2];
						String strNum = qrcodeSettings.split(",")[3];

						res = new QrcodeSettingTemplate();
						res.s = new BigDecimal(range.split("[-]")[0]).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						res.e = new BigDecimal(range.split("[-]")[1]).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						res.step = new BigDecimal(strStep).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						res.num = Integer.parseInt(strNum);

						break;
					}
				}
			}
		}
		return res;
	}

	public static class QrcodeSettingTemplate {
		private String tmplId;
		private BigDecimal s;
		private BigDecimal e;
		private BigDecimal step;
		private Integer num;

		/**
		 * @return the tmplId
		 */
		public String getTmplId() {
			return tmplId;
		}

		/**
		 * @param tmplId
		 *            the tmplId to set
		 */
		public void setTmplId(String tmplId) {
			this.tmplId = tmplId;
		}

		/**
		 * @return the s
		 */
		public BigDecimal getS() {
			return s;
		}

		/**
		 * @param s
		 *            the s to set
		 */
		public void setS(BigDecimal s) {
			this.s = s;
		}

		/**
		 * @return the e
		 */
		public BigDecimal getE() {
			return e;
		}

		/**
		 * @param e
		 *            the e to set
		 */
		public void setE(BigDecimal e) {
			this.e = e;
		}

		/**
		 * @return the step
		 */
		public BigDecimal getStep() {
			return step;
		}

		/**
		 * @param step
		 *            the step to set
		 */
		public void setStep(BigDecimal step) {
			this.step = step;
		}

		/**
		 * @return the num
		 */
		public Integer getNum() {
			return num;
		}

		/**
		 * @param num
		 *            the num to set
		 */
		public void setNum(Integer num) {
			this.num = num;
		}
	}
}
