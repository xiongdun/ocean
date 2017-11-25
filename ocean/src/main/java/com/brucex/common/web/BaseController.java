/**
 * 
 */
package com.brucex.common.web;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.brucex.common.utils.DateUtils;
import com.brucex.common.utils.StringUtils;
import com.brucex.common.validator.BeanValidator;
import com.brucex.modules.sys.entity.Log;
import com.brucex.modules.sys.entity.PageParam;
import com.brucex.modules.sys.entity.VisitHistory;
import com.brucex.modules.sys.service.LogService;
import com.brucex.modules.sys.service.VisitHistoryService;
import com.github.pagehelper.PageInfo;

/**
 * @description 公共controller 用于抽取controller 共有方法
 * @author xiongdun
 * @datetime 2017年4月19日下午11:16:53
 */
public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	private Validator validator;

	/**
	 * 新增访问历史纪录service对象
	 */
	@Autowired
	private VisitHistoryService visitHistoryService;

	/**
	 * 日志持久化service 对象
	 */
	@Autowired
	private LogService logService;
	
	/**
	 * @description 服务端参数有效性验证
	 * @author xiong
	 * @param object
	 *            验证的实体对象
	 * @param groups
	 *            void 验证组，不传入此参数时，同@Valid注解验证
	 * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
	 * @date 2017年6月25日下午12:01:41
	 */
	protected void beanValidator(Object object, Class<?>... groups) {
		BeanValidator.validateWithException(validator, object, groups);
	}

	/**
	 * @description 服务端参数有效性验证
	 * @author xiong
	 * @param object
	 *            验证的实体对象
	 * @param groups
	 *            验证组
	 * @return boolean 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
	 * @date 2017年6月25日上午11:55:50
	 */
	protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
		try {
			BeanValidator.validateWithException(validator, object, groups);
		} catch (ConstraintViolationException e) {
			List<String> messageList = BeanValidator.extractPropertyAndMessageAsList(e, ": ");
			messageList.add(0, "数据验证失败：");
			addMessage(model, messageList.toArray(new String[] {}));
			return false;
		}
		return true;
	}

	/**
	 * @description 服务端参数有效性验证
	 * @author xiong
	 * @param redirectAttributes
	 *            视图返回对象
	 * @param object
	 *            验证的实体对象
	 * @param groups
	 *            验证组
	 * @return boolean 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
	 * @date 2017年6月25日上午11:55:50
	 */
	protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
		try {
			BeanValidator.validateWithException(validator, object, groups);
		} catch (ConstraintViolationException e) {
			List<String> messageList = BeanValidator.extractPropertyAndMessageAsList(e, ": ");
			messageList.add(0, "数据验证失败：");
			addMessage(redirectAttributes, messageList.toArray(new String[] {}));
			return false;
		}
		return true;
	}

	/**
	 * @description 添加页面提示消息（不带动态样式效果）
	 * @author xiong
	 * @param model
	 * @param messages
	 *            void
	 * @date 2017年6月25日上午11:50:46
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < messages.length; i++) {
			sb.append(messages[i]).append(messages.length > 1 ? "<br/>" : "");
		}
		model.addAttribute("messasge", sb.toString());
	}

	/**
	 * @description 添加Falsh 提示消息 （在页面上可以显示动态样式提示框）
	 * @author xiong
	 * @param redirectAttributes
	 * @param messages
	 *            void
	 * @date 2017年6月25日上午11:43:32
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < messages.length; i++) {
			sb.append(messages[i]).append(messages.length > 1 ? "<br/>" : "");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}

	/**
	 * @description 获取访问页 参数对象
	 * 				从request 对象中
	 * @author xiong
	 * @param request
	 * @return PageParam
	 * @date 2017年7月3日下午11:53:13
	 */
	protected PageParam getPageParameters(HttpServletRequest request) {
		PageParam pageParam = new PageParam();
		pageParam.setDraw(Integer.parseInt(request.getParameter("draw") == null ? "0" : request.getParameter("draw")));
		pageParam.setStart(Integer.parseInt(request.getParameter("start") == null ? "0" : request.getParameter("start")));
		pageParam.setLength(Integer.parseInt(request.getParameter("length") == null ? "0" : request.getParameter("length")));
		pageParam.setRecordsFiltered(Integer.parseInt(request.getParameter("recordsFiltered") == null ? "0" : request.getParameter("recordsFiltered")));
		pageParam.setRecordsTotal(Integer.parseInt(request.getParameter("recordsTotal") == null ? "0" : request.getParameter("recordsTotal")));
		pageParam.setOrderColumn(request.getParameter("order[0][column]"));
		pageParam.setOrderDir(request.getParameter("order[0][dir]"));
		pageParam.setSearchValue(request.getParameter("search[value]"));
		return pageParam;
	}
	
	/**
	 * @description 客户端返回JSON字符串
	 * @author xiong
	 * @param response
	 * @param object
	 * @return String
	 * @date 2017年6月25日下午2:25:15
	 */
	protected String renderString(HttpServletResponse response, Object object) {
		return renderString(response, JSON.toJSONString(object), "application/json");
	}

	/**
	 * @description 客户端返回 Page 对象 JSON字符串
	 * @author xiong
	 * @param response
	 * @param page
	 * @return String
	 * @date 2017年7月3日下午11:24:48
	 */
	protected String renderString(HttpServletResponse response, PageInfo<?> page) {
		try {
			Map<Object, Object> returnData = new HashMap<Object, Object>();
			returnData.put("data", page.getList());
			returnData.put("recordsTotal", page.getTotal());
			returnData.put("recordsFiltered", page.getTotal());
			return renderString(response, JSON.toJSONString(returnData), "application/json");
		} catch (Exception e) {
			logger.debug("Json 数据生成异常，异常原因：" + e.getMessage());
		}
		return null;
	}
	
	/**
	 * @description 客户端返回字符串
	 * @author xiong
	 * @param response
	 *            HttpServletResponse
	 * @param str
	 * @param type
	 * @return String
	 * @date 2017年6月25日下午2:24:19
	 */
	protected String renderString(HttpServletResponse response, String str, String type) {
		try {
			response.reset();
			response.setContentType(type);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(str);
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * @description 400 参数绑定异常
	 * @author xiong
	 * @return String
	 * @date 2017年6月25日下午4:10:11
	 */
	@ExceptionHandler({ BindException.class, ConstraintViolationException.class, ValidationException.class })
	public String bindException() {
		return "error/400";
	}

	/**
	 * @description 403 授权认证异常
	 * @author xiong
	 * @return String
	 * @date 2017年6月25日下午4:10:59
	 */
	@ExceptionHandler({ AuthenticationException.class })
	public String authenticationException() {
		return "error/403";
	}

	/**
	 * @description 初始化数据绑定 1. 将所有传递进来的String进行HTML编码，防止XSS攻击 2.
	 *              将字段中Date类型转换为String类型
	 * @author xiong
	 * @param binder
	 *            void
	 * @date 2017年6月25日下午4:12:37
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String 类型转换，将所有传递进来的String 进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			
			// 重写 text getter and setter 方法
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		
		/**
		 * Date 类型转换
		 */
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
		});
	}

	/**
	 * @description 插入访问历史纪录
	 * @author xiong
	 * @param request
	 *            void
	 * @date 2017年6月25日下午4:09:06
	 */
	public void addVistHistory(HttpServletRequest request) {
		VisitHistory vh = new VisitHistory();
		// 应该判断 缓存中user对象是否为空，不为空则为登录访问，为空则为游客访问
		vh.setBrowser(StringUtils.getRequestBrowserInfo(request));
		vh.setOs(StringUtils.getRequestSystemInfo(request));
		vh.setIp(StringUtils.getRemoteAddr(request));
		vh.setDescription("用户登录访问");
		visitHistoryService.save(vh);
	}
	
	/**
	 * @description 对日志数据库持久化
	 * @author xiong void
	 * @date 2017年7月4日上午12:16:25
	 */
	public void addLog() {
		Log log = new Log();
		logService.save(log);
	}
}
