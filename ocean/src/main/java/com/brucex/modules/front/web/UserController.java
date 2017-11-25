/**
 * 
 */
package com.brucex.modules.front.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.brucex.common.web.BaseController;
import com.brucex.modules.sys.entity.User;

/**
 * @description 前置 应用web 登录控制器
 * @author xiongdun
 * @datetime 2017年4月26日下午11:22:55
 */
@RequestMapping(value = "user")
@Controller
public class UserController extends BaseController {
	
	@RequestMapping(value = "/login")
	public String login(User user, ModelAndView mav) {
		return "redict:index";
	}
	
	@RequestMapping(value = "/index")
	public void index() {
		
	}
}
