/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Jenny Chen
 */
public interface DXPCloudStatusPageWebService {

	public JSONObject deleteSubscriber(String subscriberId)
		throws PortalException;

	public JSONArray getSubscribers() throws PortalException;

	public JSONObject postSubscriber(String emailAddress)
		throws PortalException;

}