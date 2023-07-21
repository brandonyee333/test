/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class ResourceResponseFactory {

	public static ResourceResponseImpl create(
		ResourceRequestImpl resourceRequestImpl, HttpServletResponse response) {

		ResourceResponseImpl resourceResponseImpl = new ResourceResponseImpl();

		resourceResponseImpl.init(resourceRequestImpl, response);

		return resourceResponseImpl;
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #create(ResourceRequestImpl, HttpServletResponse)}
	 */
	@Deprecated
	public static ResourceResponseImpl create(
			ResourceRequestImpl resourceRequestImpl,
			HttpServletResponse response, String portletName, long companyId)
		throws Exception {

		return create(resourceRequestImpl, response);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #create(ResourceRequestImpl, HttpServletResponse)}
	 */
	@Deprecated
	public static ResourceResponseImpl create(
			ResourceRequestImpl resourceRequestImpl,
			HttpServletResponse response, String portletName, long companyId,
			long plid)
		throws Exception {

		return create(resourceRequestImpl, response);
	}

}