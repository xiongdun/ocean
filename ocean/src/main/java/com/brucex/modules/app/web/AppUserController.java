/**
 * 
 */
package com.brucex.modules.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brucex.common.web.BaseController;

/**
 * @description 预留移动App 登录接口
 * @author xiongdun
 * @datetime 2017年4月26日下午11:25:45
 */
@RequestMapping(value = "appuser")
@Controller
public class AppUserController extends BaseController {

	@RequestMapping(value = "/login")
	public void appLogin(String message) {
		
	}
}
