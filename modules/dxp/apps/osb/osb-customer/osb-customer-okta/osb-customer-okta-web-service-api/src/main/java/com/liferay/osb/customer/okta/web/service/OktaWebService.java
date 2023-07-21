/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.okta.web.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Jenny Chen
 */
public interface OktaWebService {

	public JSONObject getUser(String emailAddress) throws Exception;

	public JSONArray getUserGroups(String oktaUserId) throws Exception;

}