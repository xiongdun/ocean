/**
 * 
 */
package com.brucex.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brucex.common.service.CrudService;
import com.brucex.modules.sys.dao.RoleDao;
import com.brucex.modules.sys.entity.Role;

/**
 * @description RoleService
 * @author xiongdun
 * @datetime 2017年4月23日下午7:29:46
 */
@Service
@Transactional(readOnly = true)
public class RoleService extends CrudService<RoleDao, Role> {

	/**
	 * @description 获取当前用户权限列表
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:33:47
	 * @param userId
	 * @return
	 */
	public List<Role> findCurRoleList(String userId) {
		return this.dao.selectCurRoleList(userId);
	}
}
