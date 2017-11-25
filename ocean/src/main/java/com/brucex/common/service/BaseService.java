/**
 * 
 */
package com.brucex.common.service;

import org.springframework.transaction.annotation.Transactional;

import com.brucex.modules.sys.entity.User;

/**
 * @description baseService
 * @author xiongdun
 * @datetime 2017年4月23日下午4:00:28
 */
@Transactional(readOnly = true)
public abstract class BaseService {

	public static String dataScopeFilter(User user, String str, String st) {
		return null;
	}
}
