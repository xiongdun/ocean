/**
 * 
 */
package com.brucex.modules.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brucex.common.web.BaseController;
import com.brucex.modules.sys.entity.Area;
import com.brucex.modules.sys.service.AreaService;

/**
 * @description CityController
 * @author xiongdun
 * @datetime 2017年4月8日下午4:17:11
 */
@RequestMapping(value = "sys/area")
@Controller
public class AreaController extends BaseController {

	@Autowired
	private AreaService areaService;
	
	@ModelAttribute("area")
	public Area get() {
		return null;
	}
	
	@RequestMapping(value = "/list")
	public String list() {
		return "manage/modules/sys/area/area_list";
	}
	
	@RequestMapping(value = "/pagelist")
	public String pageList(Area area, Model model) {
		model.addAttribute("page", areaService.findPage(null, null));
		return "manage/modules/sys/area/area_list";
	}
	
	@RequestMapping(value = "/form")
	public String form() {
		return "manage/modules/sys/area/area_form";
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
