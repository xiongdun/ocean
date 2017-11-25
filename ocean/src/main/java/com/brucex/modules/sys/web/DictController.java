/**
 * 
 */
package com.brucex.modules.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brucex.common.web.BaseController;
import com.brucex.modules.sys.entity.Dict;
import com.brucex.modules.sys.service.DictService;

/**
 * @description DictController
 * @author xiongdun
 * @datetime 2017年4月8日下午4:17:11
 */
@RequestMapping(value = "sys/dict")
@Controller
public class DictController extends BaseController {
	
	@Autowired
	private DictService dictService;
	
	@ModelAttribute("dict")
	public Dict get() {
		return null;
	}
	
	@RequestMapping(value = "/list")
	public void list() {
		Dict dict = new Dict();
		dict.setStatus("0");
		dictService.findList(dict);
	}
	
	@RequestMapping(value = "/pagelist")
	public String pageList() {
		return "manage/modules/sys/dict/dict_list";
	}

	@RequestMapping(value = "/form")
	public String form() {
		return "manage/modules/sys/dict/dict_form";
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
