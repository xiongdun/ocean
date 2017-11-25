/**
 * 
 */
package com.brucex.modules.sys.utils;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.brucex.common.cache.EhCacheUtils;
import com.brucex.common.service.BaseService;
import com.brucex.common.spring.SpringContextHolder;
import com.brucex.modules.sys.entity.Area;
import com.brucex.modules.sys.entity.Menu;
import com.brucex.modules.sys.entity.Office;
import com.brucex.modules.sys.entity.Role;
import com.brucex.modules.sys.entity.User;
import com.brucex.modules.sys.service.AreaService;
import com.brucex.modules.sys.service.MenuService;
import com.brucex.modules.sys.service.OfficeService;
import com.brucex.modules.sys.service.RoleService;
import com.brucex.modules.sys.service.UserService;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @description 用户数据处理工具类
 * @author xiongdun
 * @datetime 2017年4月11日下午10:45:07
 */
public class UserCacheUtils {

	private static final Logger logger = Logger.getLogger(UserCacheUtils.class);
	
	private static UserService userService = SpringContextHolder.getBean(UserService.class);
	private static RoleService roleService = SpringContextHolder.getBean(RoleService.class);
	private static MenuService menuService = SpringContextHolder.getBean(MenuService.class);
	private static OfficeService officeService = SpringContextHolder.getBean(OfficeService.class);
	private static AreaService cityService = SpringContextHolder.getBean(AreaService.class);

	public static final String USER_CACHE = "userCache"; // ehcache 缓存名称
	
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";

	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_CITY_LIST = "cityList";
	public static final String CACHE_OFFICE_LIST = "officeList";
	public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";

	/**
	 * @description 按ID获取user对象
	 * @datetime 2017年4月11日下午10:46:53
	 * @author xiongdun
	 * @param id
	 * @return
	 */
	public static User getById(String id) {
		User user = (User) EhCacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user == null) {
			user = userService.getById(id);
			if (user == null) {
				return null;
			}
		}
		return null;
	}

	/**
	 * @description 根据ID获取用户
	 * @author xiong
	 * @param id
	 * @return User 取不到返回null
	 * @date 2017年6月25日上午11:11:02
	 */
	public static User get(String id) {
		User user = (User) EhCacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user == null) {
			user = userService.getById(id);
			if (user == null) {
				return null;
			}
			user.setRoleList(roleService.findList(new Role(user)));
			EhCacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			EhCacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}

	/**
	 * 根据登录名获取用户
	 * 
	 * @param loginName
	 * @return 取不到返回null
	 */
	public static User getByLoginName(String loginName) {
		User user = (User) EhCacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
		if (user == null) {
			user = userService.getByLoginName(new User(null, loginName));
			if (user == null) {
				return null;
			}
			user.setRoleList(roleService.findList(new Role(user)));
			EhCacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			EhCacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}
	
	public static Office getOfficeByUserId(String userId) {
		return officeService.getById(userId);
	}
	
	public static Area getCityByUserId(String userId) {
		return cityService.getById(userId);
	}

	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache() {
		removeCache(CACHE_ROLE_LIST);
		removeCache(CACHE_MENU_LIST);
		removeCache(CACHE_OFFICE_LIST);
		removeCache(CACHE_OFFICE_ALL_LIST);
		clearCache(getUser());
	}

	/**
	 * 清除指定用户缓存
	 * 
	 * @param user
	 */
	public static void clearCache(User user) {
		EhCacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		EhCacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName());
		// EhCacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ +
		if (user.getOffice() != null && user.getOffice().getId() != null) {
			EhCacheUtils.remove(USER_CACHE, USER_CACHE_LIST_BY_OFFICE_ID_ + user.getOffice().getId());
		}
	}

	/**
	 * @description 获取当前用户
	 * @author xiongdun
	 * @date 2017年5月2日下午9:29:13
	 * @return 获取不到则直接实例化一个新的user
	 */
	public static User getUser() {
		User principal = getPrincipal();
		if (principal != null) {
			User user = get(principal.getId());
			if (user != null) {
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}

	/**
	 * @description 先根据登录名获取用户，取不到再取当前用户
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:29:46
	 * @param loginName
	 * @return
	 */
	public static User getByLoginNameAndUserRole(String loginName) {
		User user = null;
		user = userService.getByLoginName(new User(null, loginName));
		if (user == null) {
			User principal = getPrincipal();
			if (principal != null) {
				user = get(principal.getId());
				if (user != null) {
					return user;
				}
				return new User();
			}
		}
		return user;
	}

	/**
	 * @description 获取所有角色列表
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:18:56
	 * @return
	 */
	public static List<Role> getRoleList() {
		@SuppressWarnings("unchecked")
		List<Role> roleList = (List<Role>) getCache(CACHE_ROLE_LIST);
		if (roleList == null) {
			User user = getUser();// 获取当前登录用户
			Role role = new Role();
			role.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "o", "u"));
			roleList = roleService.findList(role);
			putCache(CACHE_ROLE_LIST, roleList);
		}
		return roleList;
	}

	/**
	 * @description 获取当前用户授权菜单
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:15:07
	 * @return
	 */
	public static List<Menu> getMenuList() {
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>) getCache(CACHE_MENU_LIST);
		if (menuList == null) {
			menuList = menuService.findByUserId(getUser().getId());
			putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}

	/**
	 * @description 获取当前用户授权的城市
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:15:21
	 * @return
	 */
	public static List<Area> getCityList() {
		@SuppressWarnings("unchecked")
		List<Area> cityList = (List<Area>) getCache(CACHE_CITY_LIST);
		if (cityList == null) {
			cityList = cityService.findAllList(new Area());
			putCache(CACHE_CITY_LIST, cityList);
		}
		return cityList;
	}

	/**
	 * @description 获取当前用户有权限访问的部门
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:24:43
	 * @return
	 */
	public static List<Office> getOfficeList() {
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>) getCache(CACHE_OFFICE_LIST);
		if (officeList == null) {
			User user = getUser();
			Office office = new Office();
			office.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
			officeList = officeService.findList(office);
			putCache(CACHE_OFFICE_LIST, officeList);
		}
		return officeList;
	}

	/**
	 * @description 获取当前用户有权限访问的部门
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:18:29
	 * @return
	 */
	public static List<Office> getOfficeAllList() {
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>) getCache(CACHE_OFFICE_ALL_LIST);
		if (officeList == null) {
			officeList = officeService.findAllList(new Office());
		}
		return officeList;
	}

	/**
	 * @description 获取授权主要对象
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:18:22
	 * @return
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * @description 获取当前登录者对象
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:18:11
	 * @return
	 */
	public static User getPrincipal() {
		try {
			Subject subject = SecurityUtils.getSubject();
			User principal = (User) subject.getPrincipal();
			if (principal != null) {
				return principal;
			}
		} catch (UnavailableSecurityManagerException e) {
			logger.debug("不可用安全管理异常，异常原因：" + e.getMessage());
		} catch (InvalidSessionException e) {
			logger.debug("不可法Session异常，异常原因：" + e.getMessage());
		}
		return null;
	}

	/**
	 * @description shiro 会话
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:25:02
	 * @return
	 */
	public static Session getSession() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null) {
				session = subject.getSession();
			}
			if (session != null) {
				return session;
			}
		} catch (InvalidSessionException e) {
			logger.debug("不可法Session异常，异常原因：" + e.getMessage());
		}
		return null;
	}

	// ============== User Cache ==============

	/**
	 * @description 获取缓存
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:25:35
	 * @param key
	 * @return
	 */
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	public static Object getCache(String key, Object defaultValue) {
		Object obj = getSession().getAttribute(key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * @description 设置缓存
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:26:01
	 * @param key
	 * @param value
	 */
	public static void putCache(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	/**
	 * @description 清除缓存
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:26:20
	 * @param key
	 */
	public static void removeCache(String key) {
		getSession().removeAttribute(key);
	}

	/**
	 * @description 按机构和权限获取用户列表
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:38:53
	 * @param paramMap
	 * @return
	 */
	public static List<User> findUsersByOfficeAndRole(Map<String, String> paramMap) {
		List<User> userList = userService.findUsersByOfficeAndRole(paramMap);
		return userList;
	}

	/**
	 * @description 权限获取用户列表
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:39:07
	 * @param paramMap
	 * @return
	 */
	public static List<User> findUsersByRole(Map<String, String> paramMap) {
		List<User> userList = userService.findUsersByRole(paramMap);
		return userList;
	}

	/**
	 * @description 将汉字转化为拼音
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:31:55
	 * @param src
	 * @return
	 */
	public static String getPinYin(String src) {

		char[] t1 = null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];

		// 设置汉字拼音输出的格式
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);

		String t4 = "";
		int t0 = t1.length;

		try {
			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字符
				if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中
					t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
				} else {
					// 如果不是汉字字符，直接取出字符并连接到字符串t4后
					t4 += Character.toString(t1[i]);
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			logger.debug("汉字字符格式存在问题，导致异常，异常原因：" + e.getMessage());
		}
		return t4;
	}

	/**
	 * @description 获取当前用户角色列表
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:38:39
	 * @return
	 */
	public static List<Role> getCurrRoleList() {
		return roleService.findCurRoleList(getUser().getId());
	}

	/**
	 * @description 判断用户是否拥有该角色
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:38:31
	 * @param roleid
	 * @return
	 */
	public static Boolean hasRole(String roleid) {
		// ---------------------------------------modify by hetao 20160818
		Boolean flag = false;
		List<Role> roleList = roleService.findCurRoleList(getUser().getId());
		for (Role role : roleList) {
			if (roleid.equals(role.getId())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * @description 判断用户是否拥有某些角色
	 * @author xiongdun
	 * @datetime 2017年5月2日下午9:38:23
	 * @param userid
	 * @param roleids
	 * @return
	 */
	public static Boolean hasRole(String userid, String roleids) {
		Boolean flag = false;
		List<Role> roleList = roleService.findCurRoleList(userid);
		for (Role role : roleList) {
			String[] roleArray = roleids.split(",");
			for (String roleid : roleArray) {
				if (roleid.equals(role.getId())) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
}
