/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class ActionRequestImpl
	extends ClientDataRequestImpl implements ActionRequest {

	@Override
	public String getLifecycle() {
		return PortletRequest.ACTION_PHASE;
	}

}