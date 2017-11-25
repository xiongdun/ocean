/**
 * 
 */
package com.brucex.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brucex.common.service.CrudService;
import com.brucex.modules.sys.dao.MenuDao;
import com.brucex.modules.sys.entity.Menu;

/**
 * @description MenuService
 * @author xiongdun
 * @datetime 2017年4月16日下午8:03:46
 */
@Service
@Transactional(readOnly = true)
public class MenuService extends CrudService<MenuDao, Menu> {

	/**
	 * @description 按用户Id查询
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:20:42
	 * @param userId
	 * @return
	 */
	public List<Menu> findByUserId(String userId) {
		List<Menu> menus = this.dao.selectByUserId(userId);
		return menus;
	}
}
