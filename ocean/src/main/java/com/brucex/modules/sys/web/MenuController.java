/**
 * 
 */
package com.brucex.modules.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brucex.common.web.BaseController;
import com.brucex.modules.sys.entity.Menu;
import com.brucex.modules.sys.service.MenuService;

/**
 * @description MemuController
 * @author xiongdun
 * @datetime 2017年4月8日下午4:17:11
 */
@RequestMapping(value = "sys/menu")
@Controller
public class MenuController extends BaseController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/list")
	public String list() {
		return "manage/modules/sys/menu/menu_list";
	}
	
	@RequestMapping(value = "/form")
	public String form() {
		Menu menu = new Menu();
		menuService.findPage(menu);
		return "manage/modules/sys/menu/menu_form";
	}
	
	@RequestMapping(value = "/remove")
	public String remove() {
		return "";
	}
	
	@RequestMapping(value = "/modify")
	public String modify() {
		return "";
	}
}
