/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;

import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class ActionResponseFactory {

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #create(ActionRequestImpl, HttpServletResponse, User,
	 *             Layout)}
	 */
	@Deprecated
	public static ActionResponseImpl create(
			ActionRequestImpl actionRequestImpl, HttpServletResponse response,
			String portletName, User user, Layout layout,
			WindowState windowState, PortletMode portletMode)
		throws Exception {

		return create(actionRequestImpl, response, user, layout);
	}

	public static ActionResponseImpl create(
			ActionRequestImpl actionRequestImpl, HttpServletResponse response,
			User user, Layout layout)
		throws PortletException {

		ActionResponseImpl actionResponseImpl = new ActionResponseImpl();

		actionResponseImpl.init(
			actionRequestImpl, response, user, layout, true);

		return actionResponseImpl;
	}

}