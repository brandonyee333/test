/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;

import javax.portlet.PortletModeException;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class EventResponseFactory {

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #create(EventRequestImpl, HttpServletResponse, User, Layout)}
	 */
	@Deprecated
	public static EventResponseImpl create(
			EventRequestImpl eventRequestImpl, HttpServletResponse response,
			String portletName, User user, Layout layout)
		throws Exception {

		return create(eventRequestImpl, response, user, layout);
	}

	public static EventResponseImpl create(
			EventRequestImpl eventRequestImpl, HttpServletResponse response,
			User user, Layout layout)
		throws PortletModeException, WindowStateException {

		EventResponseImpl eventResponseImpl = new EventResponseImpl();

		eventResponseImpl.init(eventRequestImpl, response, user, layout);

		return eventResponseImpl;
	}

}