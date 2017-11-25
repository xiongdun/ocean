/**
 * 
 */
package com.brucex.modules.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brucex.common.web.BaseController;
import com.brucex.modules.sys.service.OfficeService;

/**
 * @description OfficeController
 * @author xiongdun
 * @datetime 2017年4月8日下午4:17:11
 */
@RequestMapping(value = "sys/office")
@Controller
public class OfficeController extends BaseController {
	
	@Autowired
	private OfficeService offficeService;

	@RequestMapping(value = "/list")
	public String list() {
		offficeService.findList(null);
		return "manage/modules/sys/office/office_list";
	}
	
	@RequestMapping(value = "/form")
	public String form() {
		return "manage/modules/sys/office/office_form";
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
