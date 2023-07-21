/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import com.liferay.portal.kernel.servlet.HttpSessionWrapper;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletServletSession extends HttpSessionWrapper {

	public PortletServletSession(
		HttpSession session, PortletRequestImpl portletRequestImpl) {

		super(session);

		_portletRequestImplReference = new WeakReference<>(portletRequestImpl);
	}

	@Override
	public void invalidate() {
		super.invalidate();

		PortletRequestImpl portletRequestImpl =
			_portletRequestImplReference.get();

		if (portletRequestImpl != null) {
			portletRequestImpl.invalidateSession();
		}
	}

	private final Reference<PortletRequestImpl> _portletRequestImplReference;

}