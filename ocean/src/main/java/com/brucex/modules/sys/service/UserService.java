/**
 * 
 */
package com.brucex.modules.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brucex.common.service.CrudService;
import com.brucex.common.utils.StringUtils;
import com.brucex.modules.sys.dao.AreaDao;
import com.brucex.modules.sys.dao.MenuDao;
import com.brucex.modules.sys.dao.OfficeDao;
import com.brucex.modules.sys.dao.RoleDao;
import com.brucex.modules.sys.dao.UserDao;
import com.brucex.modules.sys.entity.User;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * @description userService
 * @author xiongdun
 * @datetime 2017年4月8日下午7:03:46
 */
@Service
@Transactional(readOnly = true)
public class UserService extends CrudService<UserDao, User> {

	@Autowired
	private RoleDao roleDao; // 权限
	
	@Autowired
	private MenuDao menuDao; // 菜单
	
	@Autowired
	private OfficeDao officeDao; // 机构
	
	@Autowired
	private AreaDao cityDao; // 城市
	
	/**
	 * @description 用户登录
	 * @datetime 2017年4月8日下午9:33:46
	 * @author xiongdun
	 * @param user
	 * @return
	 */
	public User login(User user) {
		return null;
	}
	
	/**
	 * @description 用户登出
	 * @datetime 2017年4月8日下午9:33:57
	 * @author xiongdun
	 * @param user
	 * @return
	 */
	public boolean logout(User user) {
		this.dao.delete(new User() {
			/* (non-Javadoc)
			 * @see com.brucex.modules.sys.entity.User#getAge()
			 */
			@Override
			public Integer getAge() {
				// TODO Auto-generated method stub
				return super.getAge();
			}
		});
		return false;
	}
	
	/**
	 * @description 新增用户
	 * @author xiongdun
	 * @datetime 2017年4月25日下午11:02:31
	 * @param user
	 * @return
	 */
	public boolean addUser(User user) {
		// 新增用户信息 需要确保，登录名，电话号码，电子邮箱，唯一
		if (isNotUserExist(user) != 00000) {
			return false;
		}
		if (this.save(user)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @description 修改密码
	 * @author xiongdun
	 * @datetime 2017年4月25日下午11:02:47
	 * @param user
	 * @return
	 */
	public boolean modifyPassword(User user) {
		return this.dao.update(user) > 0 ? true : false;
	}
	
	public String modifyUser(User user) {
		// 修改用户信息，需要确保，登录名，电话号码，电子邮箱，身份证号，唯一
		if (isNotUserExist(user) == 00000) {
			
		}
		return "";
	}
	
	/**
	 * @description 判读用户是否已经存在
	 * @author xiongdun
	 * @datetime 2017年5月16日下午11:34:23
	 * @param user
	 * @return
	 */
	public int isNotUserExist(User user) {
		// 需要将该方法改为 代码返回值，用于前端直接校验显示
		if (StringUtils.isNotEmpty(user.getPhone())) {
			User entity = new User();
			entity.setPhone(user.getPhone());
			if (this.dao.selectUserCount(entity) > 0) {
				// 表示电话号码已经存在
				return 00001;
			}
		} 
		if (StringUtils.isNotEmpty(user.getEmail())) {
			User entity = new User();
			entity.setEmail(user.getEmail());
			if (this.dao.selectUserCount(entity) > 0) {
				// 电子邮箱
				return 00002;
			}
		} 
		if (StringUtils.isNotEmpty(user.getIdCard())) {
			User entity = new User();
			entity.setIdCard(user.getIdCard());
			if (this.dao.selectUserCount(entity) > 0) {
				// 身份证号
				return 00003;
			}
		} 
		if (StringUtils.isNotEmpty(user.getLoginName())) {
			User entity = new User();
			entity.setLoginName(user.getLoginName());
			if (this.dao.selectUserCount(entity) > 0) {
				// 登录名
				return 00004;
			}
		}
		return 00000;
	}
	
	/**
	 * @description 按登录名查询用户信息，用于登录
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:07:59
	 * @param user
	 * @return
	 */
	public User getByLoginName(User user) {
		// 可以通过 loginName, phone, email 查询
		Preconditions.checkArgument(!Strings.isNullOrEmpty(user.getLoginName()), "登录名不能为空!");
		// 判断是否为电话号码
		if (StringUtils.isMobileNumber(user.getLoginName())) {
			user.setPhone(user.getLoginName());
			user.setLoginName(null);
		} else if (StringUtils.isEmail(user.getLoginName())) {
			// 是否电子邮箱
			user.setEmail(user.getLoginName());
			user.setLoginName(null);
		} 
		// 复制给登录名
		return this.dao.selectByLoginName(user);
	}
	
	public List<User> findUsersByOfficeAndRole(Map<String, String> map) {
		return null;
	}
	
	public List<User> findUsersByRole(Map<String, String> map) {
		return null;
	}
	
}