/**
 * 
 */
package com.brucex.modules.sys.dao;

import java.util.List;

import com.brucex.common.dao.CrudDao;
import com.brucex.modules.sys.entity.Menu;

/**
 * @description menuDao
 * @author xiongdun
 * @datetime 2017年4月8日下午4:17:40
 */
public interface MenuDao extends CrudDao<Menu> {

	/**
	 * @description 按User_id 查询 菜单列表
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:22:48
	 * @param userId
	 * @return
	 */
	public List<Menu> selectByUserId(String userId);
}
