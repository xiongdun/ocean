/**
 * 
 */
package com.brucex.modules.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brucex.common.web.BaseController;
import com.brucex.modules.sys.entity.Log;
import com.brucex.modules.sys.service.LogService;

/**
 * @description LogController
 * @author xiongdun
 * @datetime 2017年4月8日下午4:17:11
 */
@RequestMapping(value = "sys/log")
@Controller
public class LogController extends BaseController {

	@Autowired
	private LogService logService;
	
	@RequestMapping(value = "/list")
	public String list() {
		return "manage/modules/sys/log/log_list";
	}
	
	@RequestMapping(value = "/pagelist")
	public String pageList() {
		Log entity = new Log();
		logService.findPage(entity);
		return "manage/modules/sys/log/log_list";
	}
	
	@RequestMapping(value = "/form")
	public String form() {
		return "";
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
