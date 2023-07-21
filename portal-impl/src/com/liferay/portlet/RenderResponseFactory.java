/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class RenderResponseFactory {

	public static RenderResponseImpl create(
		RenderRequestImpl renderRequestImpl, HttpServletResponse response) {

		RenderResponseImpl renderResponseImpl = new RenderResponseImpl();

		renderResponseImpl.init(renderRequestImpl, response);

		return renderResponseImpl;
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #create(RenderRequestImpl, HttpServletResponse)}
	 */
	@Deprecated
	public static RenderResponseImpl create(
			RenderRequestImpl renderRequestImpl, HttpServletResponse response,
			String portletName, long companyId)
		throws Exception {

		return create(renderRequestImpl, response);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #create(RenderRequestImpl, HttpServletResponse)}
	 */
	@Deprecated
	public static RenderResponseImpl create(
			RenderRequestImpl renderRequestImpl, HttpServletResponse response,
			String portletName, long companyId, long plid)
		throws Exception {

		return create(renderRequestImpl, response);
	}

}