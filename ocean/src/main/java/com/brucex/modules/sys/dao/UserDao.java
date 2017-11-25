/**
 * 
 */
package com.brucex.modules.sys.dao;

import com.brucex.common.dao.CrudDao;
import com.brucex.modules.sys.entity.User;

/**
 * @description 用户查询接口
 * @author xiongdun
 * @datetime 2017年4月8日下午4:17:40
 */
public interface UserDao extends CrudDao<User> {

	/**
	 * @description 按登录名查询
	 * @author xiongdun
	 * @datetime 2017年4月25日下午10:44:38
	 * @param user
	 * @return
	 */
	public User selectByLoginName(User user);
	
	/**
	 * @description 根据 login_name, email, phone, idcard 查询数据库中相关记录数
	 * 都无纪录表示可以插入数据，存在则不能
	 * @author xiongdun
	 * @datetime 2017年5月16日下午11:17:16
	 * @param user
	 * @return
	 */
	public int selectUserCount(User user);
	
	/**
	 * @description 插入用户拥有的角色，入sys_user_role 关联表
	 * @author xiongdun
	 * @datetime 2017年5月17日下午9:27:33
	 * @param user
	 * @return
	 */
	public int insertUserRoles(User user);
	
}
