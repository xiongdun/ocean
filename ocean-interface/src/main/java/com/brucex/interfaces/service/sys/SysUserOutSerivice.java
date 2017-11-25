/**
 * 
 */
package com.brucex.interfaces.service.sys;

import com.brucex.interfaces.entity.modules.sys.UserDTO;

import java.util.List;

/**
 * @description 系统用户对外服务接口
 * @author xiongdun
 * @datetime 2017年4月17日下午11:10:35
 */
public interface SysUserOutSerivice {

	/**
	 *
	 * @return
	 */
	List<UserDTO> findAllSysUserService();

	/**
	 * @param id
	 * @return
	 */
	UserDTO findUserById(String id);
}
