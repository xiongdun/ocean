/**
 * 
 */
package com.brucex.common.entity;

import java.util.HashMap;

/**
 * @description 查询参数类
 * @author xiongdun
 * @datetime 2017年4月22日上午11:54:12
 */
public class Parameter extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	/**
	 * 构造类，例：new Parameter(id, parentIds)
	 * 
	 * @param values
	 *            参数值
	 */
	public Parameter(Object... values) {
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				put("p" + (i + 1), values[i]);
			}
		}
	}

	/**
	 * 构造类，例：new Parameter(new Object[][]{{"id", id}, {"parentIds", parentIds}})
	 * 
	 * @param parameters
	 *            参数二维数组
	 */
	public Parameter(Object[][] parameters) {
		if (parameters != null) {
			for (Object[] os : parameters) {
				if (os.length == 2) {
					put((String) os[0], os[1]);
				}
			}
		}
	}

}
