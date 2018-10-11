package com.woodare.core.web.api.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woodare.framework.utils.SysProperties;

/**
 * 
 * @author lu_feng
 *
 */
@Controller
@RequestMapping("/api/app")
public class ApiAppController {

    @RequestMapping({ "/", "/index" })
    public ModelAndView getDashBoard(HttpServletResponse response) throws Exception {
        if(!SysProperties.getInstance().getBooleanProperty("api.test", false)) {
            throw new Exception("No page is found");
        }
        return new ModelAndView("/api/app/index");
    }

    @RequestMapping(value = "/{moduleName}/index", method = RequestMethod.GET)
    public ModelAndView getDashBoard(@PathVariable String moduleName) throws Exception {
        if(!SysProperties.getInstance().getBooleanProperty("api.test", false)) {
            throw new Exception("No page is found");
        }
        return new ModelAndView("/api/app/" + moduleName);
    }
}
