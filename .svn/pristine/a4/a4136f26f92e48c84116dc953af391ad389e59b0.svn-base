package com.woodare.template.web.controller.agent;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.woodare.core.base.BaseController;
import com.woodare.core.jpa.model.SystemUser;
import com.woodare.core.jpa.service.ISystemUserDAO;
import com.woodare.core.web.system.viewdata.systemuser.SystemUserViewData;
import com.woodare.framework.utils.JavaMD5Hash;

/**
 * @author luke
 */
@Controller
@RequestMapping("/agent/agentUser")
public class AgentSystemUserController extends BaseController {
	private static Logger log = Logger.getLogger(AgentSystemUserController.class);

	@Autowired
	private ISystemUserDAO systemUserDAO;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public ModelAndView resetPassword(SystemUserViewData resetData) {
		log.debug("Reset password: " + resetData);
		ModelAndView mav = new ModelAndView(getTemplate("/agent/agentUser/resetPassword"));
		return mav;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ModelAndView saveResetPassword(SystemUserViewData resetData) {
		ModelAndView mav = new ModelAndView(getTemplate("/agent/agentUser/resetPassword"));
		log.debug("save reset password: " + resetData);
		SystemUser user = systemUserDAO.findOne(this.getUserId());
		if (!user.getPassword().equals(JavaMD5Hash.md5(resetData.getPassword()))) {
			mav.addObject("status", false);
			mav.addObject("error", "密码错误！");
		}
		else if (resetData.getNewPassword().equals("")) {
			mav.addObject("status", false);
			mav.addObject("error", "密码不能为空");

		}
		else if (!resetData.getNewPassword().equals(resetData.getNewPasswordConfirm())) {
			mav.addObject("status", false);
			mav.addObject("error", "两次密码不一致");
		}
		else {
			user.setPassword(JavaMD5Hash.md5(resetData.getNewPassword()));
			this.systemUserDAO.save(user);
			mav.addObject("success", "修改成功！");
			mav.addObject("status", true);
		}
		return mav;
	}
}
