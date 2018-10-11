package com.woodare.template.web.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.woodare.core.base.BaseController;
import com.woodare.core.exception.ControllerException;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.template.helper.cache.DownMerchants;
import com.woodare.template.jpa.model.DownMerchantFundAccount;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.jpa.persistence.data.downmerchantfundaccount.DownMerchantFundAccountData;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantFundAccountDAO;
import com.woodare.template.web.viewdata.downmerchantbalance.SearchDownMerchantBalanceViewData;

/**
 * @author lu_feng
 */
@Controller
@RequestMapping("/content/home")
public class ContentHomeController extends BaseController {

	@Autowired
	private IDownMerchantFundAccountDAO downMerchantFundAccountDAO;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping({ "/index", "/" })
	public ModelAndView index(SearchDownMerchantBalanceViewData searchData) throws ControllerException {
		DownMerchantFundAccount fund = downMerchantFundAccountDAO.findByMchNo(this.getUsername());
		DownMerchantData mch = DownMerchants.getByCode(this.getUsername());

		ModelAndView mav = new ModelAndView(getTemplate("/content/home"));
		mav.addObject("mch", mch);
		mav.addObject("fund", SaftyBeanUtils.cloneTo(fund, DownMerchantFundAccountData.class));
		return mav;
	}

}
