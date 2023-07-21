/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.InvokerPortlet;

import javax.portlet.PortletContext;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class ActionRequestFactory {

	public static ActionRequestImpl create(
			HttpServletRequest request, Portlet portlet,
			InvokerPortlet invokerPortlet, PortletContext portletContext,
			WindowState windowState, PortletMode portletMode,
			PortletPreferences preferences, long plid)
		throws Exception {

		ActionRequestImpl actionRequestImpl = new ActionRequestImpl();

		actionRequestImpl.init(
			request, portlet, invokerPortlet, portletContext, windowState,
			portletMode, preferences, plid);

		return actionRequestImpl;
	}

}