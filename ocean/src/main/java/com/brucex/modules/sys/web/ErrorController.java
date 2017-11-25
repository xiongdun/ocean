/**
 * 
 */
package com.brucex.modules.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brucex.common.web.BaseController;

/**
 * @description 异常或错误处理controller
 * @author xiongdun
 * @datetime 2017年5月18日下午10:08:41
 */
@RequestMapping(value = "error")
@Controller
public class ErrorController extends BaseController {

	/**
	 * @description 页面没找到
	 * @author xiongdun
	 * @datetime 2017年5月18日下午10:14:11
	 * @return
	 */
	@RequestMapping(value = "/404")
	public String error404() {
		return "error/404";
	}
	
	/**
	 * @description 出现异常
	 * @author xiongdun
	 * @datetime 2017年5月18日下午10:14:02
	 * @return
	 */
	@RequestMapping(value = "/501")
	public String error501() {
		return "error/501";
	}
	
	/**
	 * @description 跳转到帮助页面
	 * @author xiongdun
	 * @datetime 2017年5月18日下午10:13:43
	 * @return
	 */
	@RequestMapping(value = "/help")
	public String errorHelp() {
		return "error/help";
	}
	
}
