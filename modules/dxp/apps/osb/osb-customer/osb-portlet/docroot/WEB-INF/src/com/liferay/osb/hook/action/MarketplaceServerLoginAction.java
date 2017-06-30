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

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.servlet.NoRedirectServletResponse;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Peter Shin
 */
public class MarketplaceServerLoginAction extends BaseStrutsAction {

	@Override
	public String execute(
			StrutsAction originalStrutsAction, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		NoRedirectServletResponse noRedirectServletResponse =
			new NoRedirectServletResponse(response);

		try {
			originalStrutsAction.execute(request, noRedirectServletResponse);
		}
		catch (NullPointerException npe) {
		}

		String redirect = ParamUtil.getString(
			request, _PORTLET_NAMESPACE.concat("redirect"));

		String redirectLocation =
			noRedirectServletResponse.getRedirectLocation();

		if (Validator.isNotNull(redirect)) {
			response.sendRedirect(redirect);
		}
		else if (Validator.isNotNull(redirectLocation)) {
			response.sendRedirect(redirectLocation);
		}

		return null;
	}

	private static final String _PORTLET_NAMESPACE =
		PortalUtil.getPortletNamespace(OSBPortletKeys.OSB_MARKETPLACE_SERVER);

}