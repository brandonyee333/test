/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mika Koivisto
 */
public class JspUtil {

	public static final String PATH_PORTAL_SAML_ERROR =
		"/portal/saml/error.jsp";

	public static final String PATH_PORTAL_SAML_SLO = "/portal/saml/slo.jsp";

	public static final String PATH_PORTAL_SAML_SLO_SP_STATUS =
		"/portal/saml/slo_sp_status.jsp";

	public static void dispatch(
			HttpServletRequest request, HttpServletResponse response,
			String path, String title)
		throws Exception {

		dispatch(request, response, path, title, false);
	}

	public static void dispatch(
			HttpServletRequest request, HttpServletResponse response,
			String path, String title, boolean popUp)
		throws Exception {

		request.setAttribute("tilesContent", path);
		request.setAttribute("tilesPopUp", String.valueOf(popUp));
		request.setAttribute("tilesTitle", title);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(
			_PATH_HTML_PORTAL_SAML_SAML_PORTAL);

		requestDispatcher.include(request, response);
	}

	private static final String _PATH_HTML_PORTAL_SAML_SAML_PORTAL =
		"/html/portal/saml/saml_portal.jsp";

}