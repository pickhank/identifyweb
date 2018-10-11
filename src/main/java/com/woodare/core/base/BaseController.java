package com.woodare.core.base;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.woodare.core.jpa.model.Attachment;
import com.woodare.core.security.UserDetailData;
import com.woodare.core.security.UserDetailsImpl;
import com.woodare.core.util.ImageCommonUtils;
import com.woodare.core.util.SDFFactory;
import com.woodare.framework.exception.MessageWithExtraWooException;
import com.woodare.framework.utils.BrowserTypeUtils;
import com.woodare.template.helper.EnumHelper;

/**
 * @author lu_feng
 */
public abstract class BaseController {

	private static Logger log = Logger.getLogger(BaseController.class);

	@Autowired
	private HttpServletRequest request;

	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				try {
					if (StringUtils.hasLength(value)) {
						if (value.length() == 10) {
							Date parsedDate = null;
							if (value.indexOf("-") >= 0) {
								parsedDate = SDFFactory.DATE_DASH.parse(value);
							}
							else {
								parsedDate = SDFFactory.DATE_SLASH.parse(value);
							}
							setValue(parsedDate);
						}
						else {
							Date parsedDate = null;
							if (value.indexOf("-") >= 0) {
								parsedDate = SDFFactory.DATETIME_DASH_SHORT.parse(value);
							}
							else {
								parsedDate = SDFFactory.DATETIME_SLASH.parse(value);
							}
							setValue(parsedDate);
						}
					}

				} catch (ParseException e) {
					setValue(null);
				}
			}
		});
	}

	/**
	 * @param response
	 * @param request
	 * @param respString
	 * @throws IOException
	 */
	protected void writeToResponse(HttpServletResponse response, HttpServletRequest request, String respString) {
		try {
			if (respString != null) {
				if (isIEBrowserType(request)) {
					response.setContentType(MediaType.TEXT_HTML);
				}
				else {
					response.setContentType(MediaType.APPLICATION_JSON);
				}
				response.getWriter().write(respString);
				response.getWriter().close();
			}
		} catch (IOException e) {
			log.error(e, e);
			// then ignore error
		}
	}

	private boolean isIEBrowserType(HttpServletRequest req) {
		String s = req.getHeader("user-agent");
		if (s == null)
			return false;
		if (s.indexOf("MSIE") > -1)
			return true;
		return false;
	}

	protected boolean isWeiXinBrowserType(HttpServletRequest req) {
		String s = req.getHeader("user-agent");
		if (s == null)
			return false;
		if (s.toLowerCase().indexOf("micromessenger") > -1)
			return true;
		return false;
	}

	protected boolean isAliBrowserType(HttpServletRequest req) {
		String s = req.getHeader("user-agent");
		if (s == null)
			return false;
		if (s.toLowerCase().indexOf("alipay") > -1)
			return true;
		return false;
	}

	/**
	 * 进行入库前的路径设置 TODO： 目前仅处理了图片img
	 * 
	 * @param displayContent
	 * @return
	 */
	protected String getSavingEditorContent(String displayContent) {
		return ImageCommonUtils.trimEditorPrefixUrl(displayContent);
	}

	/**
	 * 进行显示前的路径设置 TODO： 目前仅处理了图片img
	 * 
	 * @param savedContent
	 * @return
	 */
	protected String getDisplayEditorContent(String savedContent) {
		// Add image base url
		return ImageCommonUtils.addEditorPrefixUrl(savedContent);
	}

	/**
	 * @param ids
	 * @return
	 */
	protected String parseImageUrl(String id, List<Attachment> items) {
		return parseImageUrl(id, items, null, null);
	}

	/**
	 * @param ids
	 * @return
	 */
	protected String parseImageUrl(String id, List<Attachment> items, Integer width, Integer height) {
		String imageUrl = null;

		if (StringUtils.hasLength(id)) {
			for (Attachment item : items) {
				if (id.equals(item.getId())) {
					imageUrl = ImageCommonUtils.getCropImageUrl(item.getPath(), width, height);
					break;
				}
			}
		}
		return imageUrl;
	}

	/**
	 * @param message
	 * @return
	 */
	protected ModelAndView alertAppMessage(String title, String message) {
		ModelAndView mav = new ModelAndView("/common/appmessage");
		mav.addObject("message", message);
		mav.addObject("title", title);
		return mav;
	}

	/**
	 * @param message
	 * @return
	 */
	protected ModelAndView alertMessage(String message) {
		ModelAndView mav = new ModelAndView("/common/message");
		mav.addObject("message", message);
		return mav;
	}

	protected ModelAndView renderResult(Boolean isSuccess, String message) {
		ModelAndView mav = new ModelAndView("/common/result");
		mav.addObject("isSuccess", isSuccess);
		mav.addObject("message", message);
		return mav;
	}

	/**
	 * @param message
	 * @return
	 */
	protected ModelAndView renderPage(String message, String page) {
		ModelAndView mav = new ModelAndView(page);
		mav.addObject("message", message);
		return mav;
	}

	/**
	 * @param message
	 * @return
	 */
	protected ModelAndView alertMessageWithOwner(String message, Boolean reloadFlag) {
		ModelAndView mav = new ModelAndView("/common/message");
		mav.addObject("message", message);
		mav.addObject("reload", reloadFlag != null && reloadFlag);
		return mav;
	}

	/**
	 * @param message
	 * @return
	 */
	protected ModelAndView alertMessage(String message, Boolean reloadParentPage) {
		ModelAndView mav = new ModelAndView("/common/message");
		mav.addObject("message", message);
		mav.addObject("reloadParent", reloadParentPage != null && reloadParentPage);
		return mav;
	}

	protected UserDetailData getUser() {
		UserDetailData user = null;
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetailsImpl) {
				UserDetailsImpl userDetails = (UserDetailsImpl) principal;
				user = userDetails.getUser();
			}
		} catch (Exception e) {
		}
		return user;
	}

	protected String getUserId() {
		return getUser().getId();
	}

	protected String getUsername() {
		String username = "UNKOWN";
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetailsImpl) {
				UserDetailsImpl userDetails = (UserDetailsImpl) principal;

				UserDetailData userData = userDetails.getUser();
				if (userData != null && StringUtils.hasLength(userData.getReferNo())) {
					return userData.getReferNo();
				}
				else {
					return "NONE";
				}
			}
		} catch (Exception e) {
			return "UNEXCEPTION";
		}
		return username;
	}

	protected boolean hasRole(String roleName) {
		boolean ret = false;
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetailsImpl) {
				UserDetailsImpl userDetails = (UserDetailsImpl) principal;
				for (GrantedAuthority auth : userDetails.getAuthorities()) {
					if (auth.getAuthority().equals(roleName)) {
						ret = true;
						break;
					}
				}
			}
		} catch (Exception e) {
		}
		return ret;
	}

	protected ModelAndView alertSuccess(String path, Object item) {
		return alertStatus(path, true, item, null);
	}

	protected ModelAndView alertFailed(String path, Object item, String error) {
		return alertStatus(path, false, item, error);
	}

	private ModelAndView alertStatus(String path, boolean status, Object item, String error) {
		ModelAndView mav = new ModelAndView(path);
		mav.addObject("status", status);
		mav.addObject("error", error);
		mav.addObject("item", item);
		return mav;
	}

	protected String getTemplate(String path) {
		if (BrowserTypeUtils.checkAgentIsMobile(request.getHeader("user-agent"))) {
			return "/mobile" + path;
		}
		else {
			return path;
		}
	}

	/**
	 * @param response
	 * @param fileName
	 * @param respString
	 */
	protected ModelAndView exportToResponse(HttpServletResponse response, String fileName, String respString) {
		return exportToResponse(response, fileName, respString.getBytes(Charset.forName("GBK")));
	}

	/**
	 * @param response
	 * @param fileName
	 * @param content
	 * @return
	 */
	protected ModelAndView exportToResponse(HttpServletResponse response, String fileName, byte[] content) {
		try {
			// String fileContent = formatSumString(items);
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Content-Length", "" + content.length);
			response.setContentType("application/octet-stream");
			response.setCharacterEncoding("GBK");
			response.getOutputStream().write(content);
			response.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param errorMsg
	 * @param errLst
	 * @throws MessageWithExtraWooException
	 */
	protected void addErrorWithLimit(String errorMsg, List<String> errLst) {
		if (errLst.size() < 4) {
			errLst.add(errorMsg);
		}
	}

	protected String formatValue(Object o) {
		return formatValue(o, false);
	}

	protected String formatEnumValue(Object o) {
		String val = "";
		Map<String, String> code2names = null;
		if (o != null) {
			val = o.toString();
			code2names = EnumHelper.getMapByKey(o.getClass().getName());
		}
		if (code2names != null && code2names.containsKey(val)) {
			val = code2names.get(val);
		}
		return formatValue(val, false);
	}

	protected String formatValue(Object o, boolean addTab) {
		String result = "";
		if (o != null) {
			String val = "";
			if (o instanceof Date) {
				val = SDFFactory.DATETIME_SLASH.format((Date) o);
			}
			else {
				val = o.toString();
			}
			result = "\"" + val + (addTab ? "\t" : "") + "\",";
		}
		else {
			result = "\"\",";
		}
		return result;
	}

	protected BigDecimal plusBigDecimal(BigDecimal a1, BigDecimal a2) {
		BigDecimal result = new BigDecimal(0);
		if (a1 != null) {
			result = result.add(a1);
		}
		if (a2 != null) {
			result = result.add(a2);
		}
		return result;
	}

	protected Long plusLong(Long a1, Long a2) {
		Long result = 0L;
		if (a1 != null) {
			result += a1;
		}
		if (a2 != null) {
			result += a2;
		}
		return result;
	}

	protected static String doFormatValue(Object o) {
		return doFormatValue(o, false);
	}

	protected static String doFormatValue(Object o, boolean addTab) {
		String result = "";
		if (o != null) {
			result = "\"" + o + (addTab ? "\t" : "") + "\",";
		}
		else {
			result = "\"\",";
		}
		return result;
	}
}
