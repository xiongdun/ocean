/**
 * 
 */
package com.brucex.modules.sys.dao;

import java.util.List;

import com.brucex.common.dao.CrudDao;
import com.brucex.modules.sys.entity.Role;

/**
 * @description 城市Dao
 * @author xiongdun
 * @datetime 2017年4月8日下午4:17:40
 */
public interface RoleDao extends CrudDao<Role> {

	/**
	 * @description 查询当前用户权限列表
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:34:36
	 * @param userId
	 * @return
	 */
	public List<Role> selectCurRoleList(String userId);
}
