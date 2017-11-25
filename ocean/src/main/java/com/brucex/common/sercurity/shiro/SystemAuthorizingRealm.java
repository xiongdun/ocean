/**
 * 
 */
package com.brucex.common.sercurity.shiro;

import java.io.Serializable;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.brucex.common.entity.DataEntity;
import com.brucex.modules.sys.entity.User;
import com.brucex.modules.sys.service.UserService;
import com.brucex.modules.sys.utils.UserCacheUtils;

/**
 * @description systemAuthorizingRealm
 * @author xiongdun
 * @datetime 2017年4月29日下午10:30:21
 */
//@Service("systemAuthorizingRealm")
public class SystemAuthorizingRealm extends AuthorizingRealm {//implements InitializingBean {

	@Autowired
	private UserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	/*@Override
	public void afterPropertiesSet() throws Exception {
		//  Auto-generated method stub

	}*/

	/**
	 * 授权
	 * org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache
	 * .shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String loginName = (String) principals.fromRealm(getName()).iterator().next();
		return null;
	}

	/**
	 * 认证 (non-Javadoc)
	 * 
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		// 获取基于用户名和密码的令牌
		// 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// 根据登录名获取用户信息
		User user = userService.getByLoginName(new User(token.getUsername()));
		
		if (user == null) {
			throw new UnknownAccountException("用户不存在");
		}
		// 状态为0 表示该账号不能登陆
		if (DataEntity.UNUSERABLE_STATUS_CODE.equals(user.getStatus())) {
			throw new LockedAccountException("msg:该帐号已禁止登录.");
		}
		return new SimpleAuthenticationInfo(user, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
	}

	/**
	 * @description 当前subject 
	 * @author xiongdun
	 * @datetime 2017年5月17日下午10:56:12
	 */
	public static class Principal implements Serializable {

		private static final long serialVersionUID = 1L;

		private String id; // 编号
		private String loginName; // 登录名
		private String name; // 姓名
		private boolean isMobileLogin;// 是否为手机登录

		public Principal(User user, boolean isMoibleLogin) {
			this.id = user.getId();
			this.loginName = user.getLoginName();
			this.name = user.getName();
			this.isMobileLogin = isMoibleLogin;
		}

		public String getId() {
			return id;
		}

		public boolean isMobileLogin() {
			return isMobileLogin;
		}

		public String getLoginName() {
			return loginName;
		}

		public String getName() {
			return name;
		}

		/**
		 * 获取SESSIONID
		 */
		public String getSessionid() {
			try {
				return (String) UserCacheUtils.getSession().getId();
			} catch (Exception e) {
				return "";
			}
		}

		@Override
		public String toString() {
			return id;
		}

	}

}
