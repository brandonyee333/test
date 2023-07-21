/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.model.Portlet;

import java.util.Map;

import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.ResourceURL;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Raymond Augé
 */
public interface LiferayPortletResponse extends PortletResponse {

	public void addDateHeader(String name, long date);

	public void addHeader(String name, String value);

	public void addIntHeader(String name, int value);

	public PortletURL createActionURL();

	public LiferayPortletURL createActionURL(String portletName);

	public LiferayPortletURL createLiferayPortletURL(
		long plid, String portletName, String lifecycle);

	public LiferayPortletURL createLiferayPortletURL(
		long plid, String portletName, String lifecycle,
		boolean includeLinkToLayoutUuid);

	public LiferayPortletURL createLiferayPortletURL(String lifecycle);

	public LiferayPortletURL createLiferayPortletURL(
		String portletName, String lifecycle);

	public PortletURL createRenderURL();

	public LiferayPortletURL createRenderURL(String portletName);

	public ResourceURL createResourceURL();

	public LiferayPortletURL createResourceURL(String portletName);

	public HttpServletResponse getHttpServletResponse();

	public Portlet getPortlet();

	public Map<String, String[]> getProperties();

	public void setDateHeader(String name, long date);

	public void setHeader(String name, String value);

	public void setIntHeader(String name, int value);

	public void transferMarkupHeadElements();

}