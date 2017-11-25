/**
 * 
 */
package com.brucex.common.sercurity.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import com.brucex.common.utils.StringUtils;

/**
 * @description SessionManager
 * @author xiongdun
 * @datetime 2017年4月29日下午10:35:42
 */
public class SessionManager extends DefaultWebSessionManager {

	/*
	 * DefaultWebSessionManager 实现了DefaultSessionManager的功能 并在其上实现了web的功能
	 * 也就是在上面实现了将SessionId 存到了Cookie中
	 */
	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

		String sid = request.getParameter("_sid");
		if (StringUtils.isNotBlank(sid)) {
			if (WebUtils.isTrue(request, "_cookie")) {
				HttpServletRequest req = (HttpServletRequest) request;
				HttpServletResponse resp = (HttpServletResponse) response;
				Cookie template = getSessionIdCookie();
				Cookie cookie = new SimpleCookie(template);
				cookie.setValue(sid);
				cookie.saveTo(req, resp);
			}
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
					ShiroHttpServletRequest.URL_SESSION_ID_SOURCE);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sid);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
			return sid;
		}

		return super.getSessionId(request, response);
	}

	@Override
	protected Session doCreateSession(SessionContext context) {
		try {
			return super.doCreateSession(context);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	protected Session newSessionInstance(SessionContext context) {

		Session session = super.newSessionInstance(context);
		session.setTimeout(getGlobalSessionTimeout());
		return session;
	}

	@Override
	protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {

		try {
			return super.retrieveSession(sessionKey);
		} catch (Exception e) {
			// 获取不到SESSION不报错
			return null;
		}

	}

	@Override
	public void validateSessions() {
		super.validateSessions();
	}

	@Override
	public Session start(SessionContext context) {
		try {
			return super.start(context);
		} catch (Exception e) {
			SimpleSession session = new SimpleSession();
			session.setId(0);
			return session;
		}
	}

	@Override
	public Date getStartTimestamp(SessionKey key) {
		try {
			return super.getStartTimestamp(key);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Date getLastAccessTime(SessionKey key) {
		try {
			return super.getLastAccessTime(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public long getTimeout(SessionKey key) throws InvalidSessionException {
		try {
			return super.getTimeout(key);
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public void setTimeout(SessionKey key, long maxIdleTimeInMillis) throws InvalidSessionException {
		try {
			super.setTimeout(key, maxIdleTimeInMillis);
		} catch (Exception e) {

		}
	}

	@Override
	public void touch(SessionKey key) throws InvalidSessionException {
		try {
			super.touch(key);
		} catch (Exception e) {
		}

	}

	@Override
	public String getHost(SessionKey key) {
		try {
			return super.getHost(key);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Collection<Object> getAttributeKeys(SessionKey key) {
		try {
			return super.getAttributeKeys(key);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Object getAttribute(SessionKey sessionKey, Object attributeKey) throws InvalidSessionException {
		try {
			return super.getAttribute(sessionKey, attributeKey);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Object removeAttribute(SessionKey sessionKey, Object attributeKey) throws InvalidSessionException {
		try {
			return super.removeAttribute(sessionKey, attributeKey);
		} catch (Exception e) {
			return null;
		}
		//
	}

	@Override
	public void stop(SessionKey key) throws InvalidSessionException {
		try {
			super.stop(key);
		} catch (Exception e) {

		}

	}

	@Override
	public void checkValid(SessionKey key) throws InvalidSessionException {
		try {
			super.checkValid(key);
		} catch (Exception e) {
		}
	}
}
