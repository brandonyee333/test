/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletURLImplWrapper extends PortletURLImpl {

	public PortletURLImplWrapper(
		PortletResponseImpl portletResponseImpl, long plid, String lifecycle) {

		super(
			portletResponseImpl.getPortletRequest(),
			portletResponseImpl.getPortlet(), null, lifecycle);

		setPlid(plid);
	}

}