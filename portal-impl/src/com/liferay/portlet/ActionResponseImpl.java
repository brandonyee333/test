/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import java.io.IOException;

import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class ActionResponseImpl
	extends StateAwareResponseImpl implements ActionResponse {

	@Override
	public String getLifecycle() {
		return PortletRequest.ACTION_PHASE;
	}

	@Override
	public void sendRedirect(String location) throws IOException {
		if ((location == null) ||
			(!location.startsWith("/") && !location.contains("://") &&
			 !location.startsWith("wsrp_rewrite?"))) {

			throw new IllegalArgumentException(
				location + " is not a valid redirect");
		}

		// This is needed because app servers will try to prepend a host if they
		// see an invalid URL

		if (location.startsWith("wsrp_rewrite?")) {
			location = "http://wsrp-rewrite-holder?" + location;
		}

		if (isCalledSetRenderParameter()) {
			throw new IllegalStateException(
				"Set render parameter has already been called");
		}

		setRedirectLocation(location);
	}

	@Override
	public void sendRedirect(String location, String renderUrlParamName)
		throws IOException {
	}

}