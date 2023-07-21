/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.account.entry.details.web.internal.display.context.util;

import com.liferay.portal.kernel.display.context.util.BaseRequestHelper;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class AccountEntryDetailsRequestHelper extends BaseRequestHelper {

	public AccountEntryDetailsRequestHelper(PortletRequest portletRequest) {
		super(PortalUtil.getHttpServletRequest(portletRequest));
	}

}