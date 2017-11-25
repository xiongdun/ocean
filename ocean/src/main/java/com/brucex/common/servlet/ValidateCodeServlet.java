/**
 * 
 */
package com.brucex.common.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description 验证码生成Servlet
 * @author xiongdun
 * @datetime 2017年4月8日下午7:51:34
 */
public class ValidateCodeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5591929549414870922L;
	
	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		super.service(arg0, arg1);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
}
