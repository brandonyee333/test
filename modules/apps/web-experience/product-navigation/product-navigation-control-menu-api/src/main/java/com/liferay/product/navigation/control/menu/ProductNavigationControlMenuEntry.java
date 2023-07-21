/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.product.navigation.control.menu;

import com.liferay.portal.kernel.exception.PortalException;

import java.io.IOException;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Julio Camarero
 */
public interface ProductNavigationControlMenuEntry {

	public Map<String, Object> getData(HttpServletRequest request);

	public String getIcon(HttpServletRequest request);

	public String getIconCssClass(HttpServletRequest request);

	public String getKey();

	public String getLabel(Locale locale);

	public String getLinkCssClass(HttpServletRequest request);

	public String getMarkupView(HttpServletRequest request);

	public String getURL(HttpServletRequest request);

	public boolean includeBody(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException;

	public boolean includeIcon(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException;

	public boolean isShow(HttpServletRequest request) throws PortalException;

	public boolean isUseDialog();

}