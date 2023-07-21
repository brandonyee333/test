/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;

import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class EventResponseImpl
	extends StateAwareResponseImpl implements EventResponse {

	@Override
	public String getLifecycle() {
		return PortletRequest.EVENT_PHASE;
	}

	@Override
	public void setRenderParameters(EventRequest eventRequest) {
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #init(PortletRequestImpl, HttpServletResponse, User, Layout)}
	 */
	@Deprecated
	protected void init(
			PortletRequestImpl portletRequestImpl, HttpServletResponse response,
			String portletName, User user, Layout layout)
		throws PortletModeException, WindowStateException {

		init(portletRequestImpl, response, user, layout);
	}

	protected void init(
			PortletRequestImpl portletRequestImpl, HttpServletResponse response,
			User user, Layout layout)
		throws PortletModeException, WindowStateException {

		init(portletRequestImpl, response, user, layout, false);
	}

}