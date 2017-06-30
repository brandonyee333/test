/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.hook.action;

import com.liferay.compat.portal.kernel.util.HttpUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.util.OSBCookieKeys;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ryan Park
 */
public class MarketplaceServerLogoutAction extends BaseStrutsAction {

	@Override
	public String execute(
			StrutsAction originalStrutsAction, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		String clientURL = ParamUtil.getString(request, "mpClientURL");

		Cookie cookie = new Cookie(OSBCookieKeys.MP_CLIENT_URL, clientURL);

		cookie.setDomain(request.getServerName());
		cookie.setPath(StringPool.SLASH);
		cookie.setSecure(request.isSecure());
		cookie.setVersion(0);

		if (Validator.isNotNull(clientURL)) {
			cookie.setMaxAge(31536000);
		}
		else {
			cookie.setMaxAge(0);
		}

		response.addCookie(cookie);

		if (BrowserSnifferUtil.isIe(request)) {
			originalStrutsAction.execute(request, response);

			String referer = ParamUtil.getString(request, "referer");

			referer = HttpUtil.addParameter(
				referer, "mp_client_url", HttpUtil.encodeURL(clientURL));

			response.sendRedirect(referer);

			return StringPool.BLANK;
		}
		else {
			return originalStrutsAction.execute(request, response);
		}
	}

}