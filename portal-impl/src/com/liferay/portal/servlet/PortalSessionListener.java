/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.filters.compoundsessionid.CompoundSessionIdHttpSession;
import com.liferay.portal.kernel.servlet.filters.compoundsessionid.CompoundSessionIdSplitterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		if (CompoundSessionIdSplitterUtil.hasSessionDelimiter()) {
			CompoundSessionIdHttpSession compoundSessionIdHttpSession =
				new CompoundSessionIdHttpSession(httpSessionEvent.getSession());

			httpSessionEvent = new HttpSessionEvent(
				compoundSessionIdHttpSession);
		}

		HttpSession session = httpSessionEvent.getSession();

		new PortalSessionCreator(session);

		if ((PropsValues.SESSION_MAX_ALLOWED > 0) &&
			(_counter.incrementAndGet() > PropsValues.SESSION_MAX_ALLOWED)) {

			session.setAttribute(WebKeys.SESSION_MAX_ALLOWED, Boolean.TRUE);

			_log.error(
				StringBundler.concat(
					"Exceeded maximum number of ",
					String.valueOf(PropsValues.SESSION_MAX_ALLOWED),
					" sessions allowed. You may be experiencing a DoS ",
					"attack."));
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		if (CompoundSessionIdSplitterUtil.hasSessionDelimiter()) {
			CompoundSessionIdHttpSession compoundSessionIdHttpSession =
				new CompoundSessionIdHttpSession(httpSessionEvent.getSession());

			httpSessionEvent = new HttpSessionEvent(
				compoundSessionIdHttpSession);
		}

		new PortalSessionDestroyer(httpSessionEvent.getSession());

		if (PropsValues.SESSION_MAX_ALLOWED > 0) {
			_counter.decrementAndGet();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortalSessionListener.class);

	private final AtomicInteger _counter = new AtomicInteger();

}