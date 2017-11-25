/**
 * 
 */
package com.brucex.common.sercurity.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

import com.brucex.common.config.Global;
import com.brucex.common.utils.Servlets;
import com.brucex.common.utils.StringUtils;
import com.google.common.collect.Sets;

/**
 * @description CacheSessionDAO
 * @author xiongdun
 * @datetime 2017年4月29日下午10:40:01
 */
public class CacheSessionDAO extends EnterpriseCacheSessionDAO implements SessionDao {

	private static Logger logger = Logger.getLogger(CacheSessionDAO.class);

	@Override
	protected Serializable doCreate(Session session) {
		HttpServletRequest request = Servlets.getRequest();
		if (request != null) {
			String uri = request.getRequestURI();
			if (Servlets.isStaticFile(uri)) {
				return null;
			}
		}
		super.doCreate(session);
		return session.getId();
	}

	@Override
	public Session readSession(Serializable sessionId) throws UnknownSessionException {
		try {
			Session s = null;
			HttpServletRequest request = Servlets.getRequest();
			if (request != null) {
				String uri = request.getRequestURI();
				if (Servlets.isStaticFile(uri)) {
					return null;
				}
				s = (Session) request.getAttribute("session_" + sessionId);
			}

			if (s != null) {
				return s;
			}
			Session session = super.readSession(sessionId);
			if (request != null && session != null) {
				request.setAttribute("session_" + sessionId, session);
			}
			return session;
		} catch (Exception e) {
			logger.debug("session 异常，异常原因：" + e.getMessage());
			return null;
		}

	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		return super.doReadSession(sessionId);
	}

	@Override
	protected void doUpdate(Session session) {
		if (session == null || session.getId() == null) {
			return;
		}
		HttpServletRequest request = Servlets.getRequest();
		if (request != null) {
			String uri = request.getRequestURI();
			if (Servlets.isStaticFile(uri)) {
				return;
			}
			if (StringUtils.startsWith(uri, Global.getString("web.view.prefix"))
					&& StringUtils.endsWith(uri, Global.getString("web.view.suffix"))) {
				return;
			}
			// 手动控制不更新session
			String updateSession = request.getParameter("updateSession");
			if (Global.FALSE.equals(updateSession) || Global.NO.equals(updateSession)) {
				return;
			}
		}
		super.doUpdate(session);
	}

	@Override
	protected void doDelete(Session session) {
		if (session == null || session.getId() == null) {
			return;
		}
		super.doUpdate(session);
	}

	public Collection<Session> getActiveSessions(boolean includeLeave) {

		return null;
	}

	public Collection<Session> getActiveSessions(boolean includeLeave, Object principal, Session filterSession) {

		if (includeLeave && principal == null) {
			return this.getActiveSessions();
		}
		Set<Session> sessions = Sets.newHashSet();
		for (Session session : getActiveSessions()) {
			boolean isActiveSession = true;
			sessions.add(session);
		}
		return sessions;
	}
}
