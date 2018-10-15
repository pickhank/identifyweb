/******************************************************************************
 *                                                                             
 *                      Woodare PROPRIETARY INFORMATION                        
 *                                                                             
 *          The information contained herein is proprietary to Woodare         
 *           and shall not be reproduced or disclosed in whole or in part      
 *                    or used for any design or manufacture                    
 *              without direct written authorization from FengDa.              
 *                                                                             
 *            Copyright (c) 2013 by Woodare.  All rights reserved.             
 *                                                                             
 *****************************************************************************/
package com.woodare.template.web.controller;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.woodare.template.jpa.persistence.data.sumary.DownDsapInoviceHis;
import com.woodare.template.jpa.persistence.persistence.ISummaryDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woodare.core.base.BaseController;
import com.woodare.core.exception.ControllerException;
import com.woodare.core.util.SDFFactory;
import com.woodare.core.web.common.viewdata.ListResponseData;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.utils.ExcelUtils;
import com.woodare.framework.utils.ExcelUtils.ExportSetInfo;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.framework.utils.StringUtils;
import com.woodare.framework.utils.SysProperties;
import com.woodare.template.constant.SystemPropertiesConstant;
import com.woodare.template.helper.cache.Kbins;
import com.woodare.template.jpa.model.DownDspInvoice;
import com.woodare.template.jpa.model.data.EnumDateCate;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceSumData;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceUpSumData;
import com.woodare.template.jpa.persistence.data.kbin.KbinData;
import com.woodare.template.jpa.persistence.persistence.IDownDspInvoiceDAO;
import com.woodare.template.web.controller.helper.CellFormatterFactory;
import com.woodare.template.web.viewdata.downdspinvoice.DownDspInvoiceViewData;
import com.woodare.template.web.viewdata.downdspinvoice.SearchDownDspInvoiceViewData;

/**
 * ClassName: AdminDownDspInvoiceController
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2018/09/16
 */
@Controller
@RequestMapping("/admin/downDspInvoice")
public class AdminDownDspInvoiceController extends BaseController {
	private static Logger log = Logger.getLogger(AdminDownDspInvoiceController.class);

	@Autowired
	private IDownDspInvoiceDAO downNoCardInvoiceDAO;

	@Autowired
	private ISummaryDAO iSummaryDAO;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping({ "/sum", "/exportSum" })
	public ModelAndView sum(SearchDownDspInvoiceViewData searchData, HttpServletResponse response) throws ControllerException {
		formatSearchData(searchData);

		List<DownDspInvoiceSumData> items = downNoCardInvoiceDAO.sumInvoice(searchData);

		if (searchData.getDoExportFlag() != null && searchData.getDoExportFlag()) {
			String responName = "dsp-invoice-" + SDFFactory.DATETIME.format(new Date()) + ".csv";
			return this.exportToResponse(response, responName, formatSumString(items));
		}

		ModelAndView mav = new ModelAndView(getTemplate("/admin/downDspInvoice/sum"));
		mav.addObject("items", items);
		mav.addObject("search", searchData);
		return mav;
	}

	/**
	 * 交易汇总
	 * @param searchData
	 * @return
	 * @throws ControllerException
	 */
    @Transactional(propagation = Propagation.NEVER)
    @RequestMapping(value = "sumTwo")
    public ModelAndView sumTwo(SearchDownDspInvoiceViewData searchData) throws ControllerException {
        formatSearchData(searchData);

        List<DownDsapInoviceHis> items = iSummaryDAO.summary(searchData);

        ModelAndView mav = new ModelAndView(getTemplate("/admin/downDspInvoice/sumTwo"));
        mav.addObject("items", items);
        mav.addObject("search", searchData);
        return mav;
    }

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping({ "/upSum", "/exportUpSum" })
	public ModelAndView upSum(SearchDownDspInvoiceViewData searchData, HttpServletResponse response) throws ControllerException {
		formatSearchData(searchData);

		List<DownDspInvoiceUpSumData> items = downNoCardInvoiceDAO.upSumInvoice(searchData);
		if (searchData.getDoExportFlag() != null && searchData.getDoExportFlag()) {
			String responName = "dsp-invoice-upSum-" + SDFFactory.DATETIME.format(new Date()) + ".csv";
			return this.exportToResponse(response, responName, formatUpSumString(items));
		}

		ModelAndView mav = new ModelAndView(getTemplate("/admin/downDspInvoice/upSum"));
		mav.addObject("items", items);
		mav.addObject("search", searchData);
		return mav;
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping({ "/index", "/export" })
	public ModelAndView index(SearchDownDspInvoiceViewData searchData, HttpServletResponse response) throws ControllerException {
		formatSearchData(searchData);
		/*鉴权交易*/
		IPagedList<DownDspInvoice> items = downNoCardInvoiceDAO.searchItems(searchData);

		if (searchData.getDoExportFlag() != null && searchData.getDoExportFlag()) {
			/*生成Excel*/
			String responName = "dsp-invoice-" + SDFFactory.DATETIME.format(new Date()) + ".xls";
			/*关键字*/
			return this.exportToResponse(response, responName, formatString(items));
		}

		ModelAndView mav = new ModelAndView(getTemplate("/admin/downDspInvoice/index"));
		mav.addObject("search", searchData);
		mav.addObject("res", toViewData(items));
		return mav;
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(DownDspInvoiceViewData data) throws ControllerException {
		ModelAndView mav = new ModelAndView(getTemplate("/admin/downDspInvoice/detail"));
		DownDspInvoice model = null;
		if (StringUtils.isNotEmpty(data.getId())) {
			model = downNoCardInvoiceDAO.findOne(data.getId());
		}

		DownDspInvoiceViewData viewData = toViewData(model);

		mav.addObject("item", viewData);
		return mav;
	}

	private byte[] formatString(List<DownDspInvoice> items) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String[]> headNames = new ArrayList<String[]>();
		/* 装到List里面  */
		headNames.add(new String[] { "渠道", "渠道商户", "平台流水号", "机构号", "机构名称", "鉴权类型", "手续费", "机构流水号", "订单时间", "状态", "状态描述", "验证结论" });
		List<String[]> fieldNames = new ArrayList<String[]>();
		fieldNames.add(new String[] { "channel", "channelAccName", "transNo", "mchNo", "mchName", "dspMode", "mchPayFee", "tradeNo", "orderDate", "status", "statusDesc", "verifyStatus" });

		ExportSetInfo setInfo = new ExportSetInfo();
		setInfo.addFomatter("channel", CellFormatterFactory.NO_CARD_CHANNEL);
		setInfo.addFomatter("status", CellFormatterFactory.DSP_STATUS);
		setInfo.addFomatter("dspMode", CellFormatterFactory.DSP_MODE);

		LinkedHashMap<String, List<?>> objsMap = new LinkedHashMap<String, List<?>>();
		objsMap.put("数据", items);
		setInfo.setObjsMap(objsMap);
		setInfo.setFieldNames(fieldNames);
		setInfo.setTitles(new String[] { "交易记录" });
		setInfo.setHeadNames(headNames);
		setInfo.setOut(baos);
		try {
			ExcelUtils.export2Excel(setInfo);
		} catch (Exception e) {
			log.error(e, e);
		}
		return baos.toByteArray();
	}

	/**
	 * @param searchData
	 */
	private void formatSearchData(SearchDownDspInvoiceViewData searchData) {
		if (searchData.getDoExportFlag() != null && searchData.getDoExportFlag()) {
			searchData.setPageSize(Integer.MAX_VALUE);
		}

		if (searchData.getDateCate() == null) {
			searchData.setDateCate(EnumDateCate.CUSTOM);
		}
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.MILLISECOND, -1);
		switch (searchData.getDateCate()) {
		case TODAY:
			searchData.setEndDate(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, -1);
			c.add(Calendar.MILLISECOND, 1);
			searchData.setStartDate(c.getTime());
			break;
		case YESTERDAY:
			c.add(Calendar.DAY_OF_MONTH, -1);
			searchData.setEndDate(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, -1);
			c.add(Calendar.MILLISECOND, 1);
			searchData.setStartDate(c.getTime());
			break;
		case LAST_7_DAYS:
			searchData.setEndDate(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, -7);
			c.add(Calendar.MILLISECOND, 1);
			searchData.setStartDate(c.getTime());
			break;
		case LAST_30_DAYS:
			searchData.setEndDate(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, -30);
			c.add(Calendar.MILLISECOND, 1);
			searchData.setStartDate(c.getTime());
			break;
		default:
			if (searchData.getEndDate() == null) {
				searchData.setEndDate(c.getTime());
			}
			else {
				Calendar c1 = Calendar.getInstance();
				c1.setTime(searchData.getEndDate());
				c1.set(Calendar.HOUR_OF_DAY, 0);
				c1.set(Calendar.MINUTE, 0);
				c1.set(Calendar.SECOND, 0);
				c1.set(Calendar.MILLISECOND, 0);
				c1.add(Calendar.DAY_OF_MONTH, 1);
				c1.add(Calendar.MILLISECOND, -1);
				searchData.setEndDate(c1.getTime());
			}
			if (searchData.getStartDate() == null) {
				c.add(Calendar.DAY_OF_MONTH, -1);
				c.add(Calendar.MILLISECOND, 1);
				searchData.setStartDate(c.getTime());
			}
			else {
				Calendar c1 = Calendar.getInstance();
				c1.setTime(searchData.getStartDate());
				c1.set(Calendar.HOUR_OF_DAY, 0);
				c1.set(Calendar.MINUTE, 0);
				c1.set(Calendar.SECOND, 0);
				c1.set(Calendar.MILLISECOND, 0);
				searchData.setStartDate(c1.getTime());
			}

			// 如果使用精确条件检索，则忽略掉时间戳
			if (StringUtils.isEmpty(searchData.getTradeNo()) && StringUtils.isEmpty(searchData.getTransNo())) {
				if (SDFFactory.DATE.format(searchData.getEndDate()).equals(SDFFactory.DATE.format(searchData.getStartDate()))) {
					String date = SDFFactory.DATE.format(searchData.getEndDate());
					try {
						if (StringUtils.isNotEmpty(searchData.getStartTime())) {
							searchData.setStartDate(SDFFactory.DATETIME.parse(date + searchData.getStartTime().replaceAll(":", "")));
						}
						if (StringUtils.isNotEmpty(searchData.getEndTime())) {
							searchData.setEndDate(SDFFactory.DATETIME.parse(date + searchData.getEndTime().replaceAll(":", "")));
						}
					} catch (Exception e) {
					}
				}
			}
			break;
		}
	}

	private String formatUpSumString(List<DownDspInvoiceUpSumData> items) {
		StringBuffer sb = new StringBuffer();
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);

		boolean isAdminFlag = SysProperties.getInstance().getProperty(SystemPropertiesConstant.CODE_SUPER_ACCOUNTNAME, "").equals(this.getUsername());
		if (isAdminFlag) {
			sb.append("通道号,通道商户,产品类型,成功率,总笔数,成功笔数,手续费总额,代理分润总额,利润总额,返点总额\n");
		}
		else {
			sb.append("通道号,通道商户,产品类型,成功率,总笔数,成功笔数,手续费总额,代理分润总额,利润总额\n");
		}

		if (items != null) {
			for (DownDspInvoiceUpSumData item : items) {
				sb.append(formatValue(item.getChannel()));
				sb.append(formatValue(item.getChannelAccName()));
				sb.append(formatValue(item.getDspMode().getDesc()));

				if (item.getTotalCount() > 0) {
					sb.append(formatValue(numberFormat.format((float) item.getCount() / (float) item.getTotalCount() * 100) + "%"));
				}
				else {
					sb.append(formatValue("0%"));
				}
				// 总交易笔数
				sb.append(formatValue(item.getTotalCount()));
				// 成功交易笔数
				sb.append(formatValue(item.getCount()));
				// 商户手续费总额
				sb.append(formatValue(item.getMchFeeAmt()));
				// 渠道手续费总额
				sb.append(formatValue(item.getChannelFeeAmt()));
				// 代理分润总额
				sb.append(formatValue(item.getAgentProfitAmt()));
				// 平台利润总额
				sb.append(formatValue(item.getProfitAmt()));
				// 平台返点分润
				if (isAdminFlag) {
					sb.append(formatValue(item.getXtraProfitAmt()));
				}
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	/**
	 * @param items
	 * @return
	 * @throws ParseException
	 */
	private String formatSumString(List<DownDspInvoiceSumData> items) {
		StringBuffer sb = new StringBuffer();
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);

		boolean isAdminFlag = SysProperties.getInstance().getProperty(SystemPropertiesConstant.CODE_SUPER_ACCOUNTNAME, "").equals(this.getUsername());

		if (isAdminFlag) {
			sb.append("机构号,机构名称,产品类型,成功率,总笔数,成功笔数,手续费总额,代理分润总额,利润总额,返点总额\n");
		}
		else {
			sb.append("机构号,机构名称,产品类型,成功率,总笔数,成功笔数,手续费总额,代理分润总额,利润总额\n");
		}

		if (items != null) {
			for (DownDspInvoiceSumData item : items) {
				sb.append(formatValue(item.getMchNo(), true));
				sb.append(formatValue(item.getMchName()));
				sb.append(formatValue(item.getDspMode().getDesc()));

				if (item.getTotalCount() > 0) {
					if (item.getCount() > 0L && item.getTotalCount() > 0L) {
						sb.append(formatValue(numberFormat.format((float) item.getCount() / (float) (item.getTotalCount()) * 100) + "%"));
					}
					else {
						sb.append(formatValue("0%"));
					}
				}
				else {
					sb.append(formatValue("0%"));
				}
				// 总交易笔数
				sb.append(formatValue(item.getTotalCount()));
				// 成功交易笔数
				sb.append(formatValue(item.getCount()));
				// 商户手续费总额
				sb.append(formatValue(item.getMchFeeAmt()));
				// 渠道手续费总额
				sb.append(formatValue(item.getChannelFeeAmt()));
				// 代理分润总额
				sb.append(formatValue(item.getAgentProfitAmt()));
				// 平台利润总额
				sb.append(formatValue(item.getProfitAmt()));
				// 平台返点分润
				if (isAdminFlag) {
					sb.append(formatValue(item.getXtraProfitAmt()));
				}
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	/**
	 * @param items
	 * @return
	 */
	private ListResponseData<DownDspInvoiceViewData> toViewData(IPagedList<DownDspInvoice> items) {
		ListResponseData<DownDspInvoiceViewData> response = new ListResponseData<DownDspInvoiceViewData>();
		if (items != null) {
			for (DownDspInvoice item : items) {
				response.addItem(toViewData(item));
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	/**
	 * @param item
	 * @return
	 */
	private DownDspInvoiceViewData toViewData(DownDspInvoice item) {
		DownDspInvoiceViewData viewData = SaftyBeanUtils.cloneTo(item, DownDspInvoiceViewData.class);
		if (viewData != null) {
			String payCardBankName = null;
			KbinData kbin = Kbins.getByCardNo(viewData.getCardNo());
			if (kbin != null) {
				payCardBankName = kbin.getBankName();
			}
			if (StringUtils.isEmpty(payCardBankName)) {
				payCardBankName = "未知";
			}
			viewData.setCardBankName(payCardBankName);
		}

		return viewData;
	}
}
