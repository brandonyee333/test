/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.opensaml.integration.internal.transport;

import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Tomas Polesovsky
 */
public class ProxyPathRequestWrapper extends HttpServletRequestWrapper {

	public ProxyPathRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getRemoteAddr() {
		HttpServletRequest request = (HttpServletRequest)getRequest();

		if (GetterUtil.getBoolean(
				PropsUtil.get(PropsKeys.WEB_SERVER_FORWARDED_HOST_ENABLED))) {

			String headerString = request.getHeader(
				HttpHeaders.X_FORWARDED_FOR);

			if (Validator.isNotNull(headerString)) {
				return StringUtil.split(headerString)[0];
			}
		}

		return request.getRemoteAddr();
	}

	@Override
	public String getRemoteHost() {
		return PortalUtil.getForwardedHost((HttpServletRequest)getRequest());
	}

	@Override
	public StringBuffer getRequestURL() {
		StringBuffer sb = new StringBuffer();

		HttpServletRequest request = (HttpServletRequest)super.getRequest();

		sb.append(PortalUtil.getPortalURL(request));

		String pathContext = PortalUtil.getPathContext();
		String uri = request.getRequestURI();

		if (!uri.startsWith(pathContext)) {
			sb.append(pathContext);
		}

		sb.append(uri);

		return sb;
	}

	@Override
	public boolean isSecure() {
		return PortalUtil.isSecure((HttpServletRequest)getRequest());
	}

}