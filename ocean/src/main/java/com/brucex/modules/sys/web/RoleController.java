/**
 * 
 */
package com.brucex.modules.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brucex.common.web.BaseController;
import com.brucex.modules.sys.entity.Role;
import com.brucex.modules.sys.service.RoleService;

/**
 * @description rolecontroller
 * @author xiongdun
 * @datetime 2017年5月22日下午9:34:36
 */
@RequestMapping(value = "sys/role")
@Controller
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;
	
	@ModelAttribute("role")
	public Role get() {
		return new Role();
	}
	
	@RequestMapping(value = "/list")
	public String list() {
		return "manage/modules/sys/role/role_list";
	}
	
	@RequestMapping(value = "/form")
	public String form() {
		return "manage/modules/sys/role/role_form";
	}
	
	@RequestMapping(value = "/remove")
	public String remove() {
		roleService.remove(new Role());
		return "";
	}
	
	@RequestMapping(value = "/modify")
	public String modify() {
		return "";
	}
}
