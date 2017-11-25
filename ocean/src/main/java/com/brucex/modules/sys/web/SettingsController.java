/**
 * 
 */
package com.brucex.modules.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brucex.common.web.BaseController;

/**
 * @description 系统设置 controller
 * @author xiongdun
 * @datetime 2017年4月8日下午4:17:11
 */
@RequestMapping(value = "sys/settings")
@Controller
public class SettingsController extends BaseController {

	@RequestMapping(value = "/userSettings")
	public String userSettings(Model model) {
		return "manage/modules/sys/user/user_settings";
	}
}
