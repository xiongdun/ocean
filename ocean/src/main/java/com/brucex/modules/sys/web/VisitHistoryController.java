/**
 * 
 */
package com.brucex.modules.sys.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brucex.common.web.BaseController;
import com.brucex.modules.sys.entity.PageParam;
import com.brucex.modules.sys.entity.VisitHistory;
import com.brucex.modules.sys.service.VisitHistoryService;
import com.github.pagehelper.PageInfo;

/**
 * @description LogController
 * @author xiongdun
 * @datetime 2017年4月8日下午4:17:11
 */
@RequestMapping(value = "sys/visithis")
@Controller
public class VisitHistoryController extends BaseController {

	@Autowired
	private VisitHistoryService visitHistoryService;
	
	@RequestMapping(value = "/list")
	public String list() {
		return "";
	}
	
	@RequestMapping(value = "/pagelist")
	//@ResponseBody
	public String pageList(VisitHistory visitHistory, Model model, HttpServletRequest request) {
		logger.debug("分页查询访问历史记录");
		PageParam pageParam = getPageParameters(request);
		visitHistory.getPage().setPageNum(pageParam.getStart());
		visitHistory.getPage().setPageSize(pageParam.getLength());
		PageInfo<VisitHistory> histories = visitHistoryService.findPage(visitHistory);
		model.addAttribute("page", histories);
		return "manage/modules/sys/log/visit_his_list";
	}
	
	@RequestMapping(value = "/form")
	@ResponseBody
	public String form(VisitHistory visitHistory, Model model, HttpServletRequest request) {
		return "";
	}
	
	@RequestMapping(value = "/remove")
	@ResponseBody
	public String remove(VisitHistory visitHistory, Model model, HttpServletRequest request) {
		return "";
	}
	
	@RequestMapping(value = "/modify")
	@ResponseBody
	public String modify(VisitHistory visitHistory, Model model, HttpServletRequest request) {
		return "";
	}
}
