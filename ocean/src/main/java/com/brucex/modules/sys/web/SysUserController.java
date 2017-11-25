/**
 * 
 */
package com.brucex.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brucex.common.sercurity.ShiroEndecryptUtils;
import com.brucex.common.utils.StringUtils;
import com.brucex.common.web.BaseController;
import com.brucex.modules.sys.entity.PageParam;
import com.brucex.modules.sys.entity.User;
import com.brucex.modules.sys.service.UserService;
import com.brucex.modules.sys.utils.UserCacheUtils;
import com.github.pagehelper.PageInfo;

/**
 * @description 用户控制器
 * @author xiongdun
 * @datetime 2017年4月8日下午4:17:11
 */
@RequestMapping(value = "sys/user")
@Controller
public class SysUserController extends BaseController {

	@Autowired
	private UserService userService;
	
	/**
	 * @description 进入登录页
	 * @author xiongdun
	 * @datetime 2017年4月19日下午11:35:32
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, User user) {
		logger.debug("进入登录页面");
		//addVistHistory(request);
		
		return "manage/login";
	}
	
	/**
	 * @description 登录
	 * @author xiongdun
	 * @datetime 2017年4月19日下午11:35:40
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public String doLogin(Model model, HttpServletRequest request) {
		
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
			model.addAttribute("message", "登录信息不能为空.");
			return "redirect:login";
		}
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
		token.setRememberMe(true);
	    try {
	        subject.login(token);
	        // 添加访问记录
			addVistHistory(request);
	    } catch (IncorrectCredentialsException e) {
	        model.addAttribute("message", "登录密码错误.");
	    } catch (ExcessiveAttemptsException e) {
	        model.addAttribute("message", "登录失败次数过多.");
	    } catch (LockedAccountException e) {
	        model.addAttribute("message", "帐号已被锁定.");
	    } catch (DisabledAccountException e) {
	        model.addAttribute("message", "帐号已被禁用.");
	    } catch (UnknownAccountException e) {
	        model.addAttribute("message", "帐号不存在.");  
	    } catch (AuthenticationException e) {
	        model.addAttribute("message", "其他错误：" + e.getMessage());
	    }
        if (subject.isAuthenticated()) {  
        	// model.addAttribute("userinfo", (User) subject.getPrincipal());
            return "redirect:/sys/index";
        }
        token.clear();
	    return "redirect:login";
	}
	
	/**
	 * @description 推出登录
	 * @author xiongdun
	 * @datetime 2017年5月16日下午10:15:05
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(User user) {
		SecurityUtils.getSubject().logout();
		return "redirect:login";
	}
	
	@RequestMapping(value = "/register")
	public String register() {
		//addMessage("", "", "");
		return "";
	}
	
	/**
	 * @description 注册
	 * @author xiongdun
	 * @datetime 2017年5月16日下午10:15:22
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/doregister", method = RequestMethod.POST)
	public String doRegister(User user) {
		if (user == null) {
			return "redirect:login";
		}
		User entity = ShiroEndecryptUtils.md5Password(user.getLoginName(), user.getPassword());
		if (StringUtils.isBlank(user.getName())) {
			entity.setName(user.getLoginName());
		}
		user.setEnglishName(UserCacheUtils.getPinYin(user.getLoginName()));
		user.setPassword(entity.getPassword());
		user.setPasswordSalt(entity.getPasswordSalt());
		userService.addUser(user);
		return "redirect:login";
	}
	
	@RequestMapping(value = "/index")
	public String index() {
		return "manage/index";
	}
	
	@RequestMapping(value = "/dovalidate", method = RequestMethod.POST)
	@ResponseBody
	public String doValidateUserInfo(HttpServletRequest request, User user, Model model) {
		
		switch (userService.isNotUserExist(user)) {
		case 00001:
			// 00003 表示电话号码存在
			break;
		case 00002:
			// 00004 电子邮箱已经存在
			break;
		case 00003:
			// 00001 表示身份证已经存在
			break;
		case 00004:
			// 00002 表示登录名已经存在
			break;
		default:
			// 00000 表示成功
			break;
		}
		return "";
	}
	
	@ModelAttribute("user")
	public User get(@RequestParam(required = false) String id) {
		User user = null;
		if (StringUtils.isNotEmpty(id)) {
			user = userService.getById(id);
		}
		return user;
	}
	
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) {
		return "manage/modules/sys/user/user_list";
	}
	
	@RequestMapping(value = "/pagelist")
	@ResponseBody
	public String pageList(HttpServletRequest request, HttpServletResponse response, Model model) {
		PageParam pageParam = getPageParameters(request);
		User user = new User();
		user.getPage().setPageNum(pageParam.getStart());
		user.getPage().setPageSize(pageParam.getLength());
		PageInfo<User> page = userService.findPage(user);
		return renderString(response, page);
	}
	
	@RequestMapping(value = "/profile")
	public String profile(Model model) {
		return "manage/modules/sys/user/user_profile";
	}
	
	@RequestMapping(value = "/form")
	public String form() {
		return "manage/modules/sys/user/user_form";
	}
	
	@RequestMapping(value = "/remove")
	@ResponseBody
	public String remove(@RequestBody User user, HttpServletResponse response, Model model) {
		userService.remove(user.getId());
		return renderString(response, "删除成功！");
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpServletRequest request, User user, Model model) {
		return "manage/modules/sys/user/user_form";
	}
	
	@RequestMapping(value = "/modifypassword", method = RequestMethod.POST)
	public String modifyPassword(HttpServletRequest request, User user, Model model) {
		return "";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, User user, Model model) {
		return "manage/modules/sys/user/user_form";
	}
	
}
