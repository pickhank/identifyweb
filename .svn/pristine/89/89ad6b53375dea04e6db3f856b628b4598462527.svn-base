package com.woodare.core.web.common.controller;

import java.awt.Color;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.octo.captcha.service.CaptchaServiceException;
import com.woodare.core.component.captcha.VerificationService;
import com.woodare.core.jpa.model.SystemUser;
import com.woodare.core.jpa.model.data.EnumUserRole;
import com.woodare.core.jpa.service.ISystemUserDAO;
import com.woodare.core.security.UserDetailsImpl;
import com.woodare.core.web.common.viewdata.AjaxResponseData;
import com.woodare.framework.data.EResponseState;
import com.woodare.framework.utils.BrowserTypeUtils;
import com.woodare.template.helper.cache.Kbins;
import com.woodare.template.jpa.persistence.data.kbin.KbinData;

/**
 * @author lu_feng
 */
@Controller
public class IndexController {
	private static Logger log = Logger.getLogger(IndexController.class);

	@Autowired
	private ISystemUserDAO systemUserDAO;

	@RequestMapping({ "/", "/index", "/admin", "/admin/index" ,"/content", "/content/index","/agent","/agent/index" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ModelAndView getDashBoard(HttpServletRequest request, HttpServletResponse response) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SystemUser user = null;
		try {
			if (principal instanceof UserDetailsImpl) {
				UserDetailsImpl userDetails = (UserDetailsImpl) principal;
				user = systemUserDAO.findOne(userDetails.getUserId());
				user.setLastLoginDate(new Date());
				systemUserDAO.update(user);
			}
		} catch (Exception e) {
			log.warn("Do not get correct user info.");
		}
		if (user == null) {
			return new ModelAndView("redirect:/system/user/logout");
		} else {
			if (BrowserTypeUtils.checkAgentIsMobile(request.getHeader("user-agent"))) {
				EnumUserRole role = user.getUserRole();
				if (role == null || role == EnumUserRole.SUPERVISOR) {
					return new ModelAndView("/mobile/admin/index");
				} else if (role == EnumUserRole.AGENT) {
					return new ModelAndView("/mobile/agent/index");
				} else if (role == EnumUserRole.MERCHANT) {
					return new ModelAndView("/merchant/index");
				} else if (role == EnumUserRole.USER) {
					return new ModelAndView("/mobile/content/index");
				}
				return new ModelAndView("/mobile/admin/index");
			} else {
				EnumUserRole role = user.getUserRole();
				if (role == null || role == EnumUserRole.SUPERVISOR) {
					return new ModelAndView("/admin/index");
				} else if (role == EnumUserRole.AGENT) {
					return new ModelAndView("/agent/index");
				} else if (role == EnumUserRole.MERCHANT) {
					return new ModelAndView("/merchant/index");
				} else if (role == EnumUserRole.USER) {
					return new ModelAndView("/content/index");
				}
				return new ModelAndView("/admin/index");
			}
		}
	}

	@RequestMapping({ "/working" })
	@Transactional(readOnly = true)
	@ResponseBody
	public ModelAndView initWorking(HttpServletRequest request, HttpServletResponse response) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SystemUser user = null;
		try {
			if (principal instanceof UserDetailsImpl) {
				UserDetailsImpl userDetails = (UserDetailsImpl) principal;
				user = systemUserDAO.findOne(userDetails.getUserId());
				user.setLastLoginDate(new Date());
				systemUserDAO.update(user);
			}
		} catch (Exception e) {
			log.warn("Do not get correct user info.");
		}
		if (user == null) {
			return new ModelAndView("redirect:/system/user/logout");
		} else {
			EnumUserRole role = user.getUserRole();
			if (role == EnumUserRole.USER) {
				return new ModelAndView("redirect:/content/home/index");
			}
			return new ModelAndView("/home");
		}
	}

	/**
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/regcode/renew", method = RequestMethod.GET)
	public @ResponseBody void newregcode(@Context HttpServletResponse response, @Context HttpServletRequest request) {
		try {
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/png");
			ServletOutputStream out = response.getOutputStream();
			try {
				ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
				cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
				
				RandomWordFactory wordFactory = new RandomWordFactory();
				wordFactory.setCharacters("0123456789");
				wordFactory.setMaxLength(6);
				wordFactory.setMinLength(6);

				cs.setWordFactory(wordFactory);
				cs.setWidth(200);
				cs.setHeight(50);
				cs.setFilterFactory(new WobbleRippleFilterFactory());
				String captchaId = request.getSession(true).getId();
				String imgText = EncoderHelper.getChallangeAndWriteImage(cs, "png", out);
				VerificationService.saveVerificationCode(captchaId, imgText);
				out.flush();
			} catch (CaptchaServiceException e) {
			} finally {
				out.close();
			}
		} catch (IOException e) {
			log.error(e, e);
		}
	}
 
	@RequestMapping(value = "/parseCardNo")
	public @ResponseBody AjaxResponseData<KbinData> parseCardNo(@Context HttpServletRequest request) {
		AjaxResponseData<KbinData> ret = new AjaxResponseData<KbinData>();
		
		KbinData kbin = Kbins.getByCardNo(request.getParameter("cardNo"));
		if(kbin != null) {
			ret.setPayload(kbin);
			ret.setState(EResponseState.Successful);	
		}
		else {
			ret.setMessage("卡号信息非法");
			ret.setState(EResponseState.CustomMsg);
		}
		return ret;
	}
	
}
