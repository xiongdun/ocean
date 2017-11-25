/**
 * 
 */
package com.brucex.modules.sys.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brucex.common.web.BaseController;
import com.brucex.modules.sys.entity.User;

/**
 * @description 系统应用首页控制器
 * @author xiongdun
 * @datetime 2017年4月27日上午12:07:06
 */
@RequestMapping(value = "sys/index")
@Controller
public class IndexController extends BaseController {

	@RequestMapping(value = "")
	public String index(Model model) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		model.addAttribute("user", user);
		return "manage/index";
	}
	
	// 首页上的统计图表可以从这里开始加载，
	// 菜单信息可以通过自定义标签进行查询
}
