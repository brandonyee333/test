/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.zendesk.web.service.internal;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.util.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.customer.zendesk.web.service.exception.NoSuchZendeskUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ZendeskUserWebService.class)
public class ZendeskUserWebServiceImpl implements ZendeskUserWebService {

	public ZendeskUser getZendeskUser(long userId) throws PortalException {
		User user = _userLocalService.getUser(userId);

		Map<String, String> parameters = new HashMap<>();

		parameters.put("external_id", user.getUuid());

		JSONObject responseJSONObject = _zendeskBaseWebService.get(
			ZendeskRESTEndpoints.URL_API_V2 + "users/search.json", parameters);

		JSONArray usersJSONArray = responseJSONObject.getJSONArray("users");

		if (usersJSONArray.length() <= 0) {
			throw new NoSuchZendeskUserException();
		}

		return _translate(usersJSONArray.getJSONObject(0));
	}

	private ZendeskUser _translate(JSONObject jsonObject) {
		ZendeskUser zendeskUser = new ZendeskUser();

		zendeskUser.setEmail(jsonObject.getString("email"));
		zendeskUser.setZendeskUserId(jsonObject.getLong("id"));
		zendeskUser.setName(jsonObject.getString("name"));

		return zendeskUser;
	}

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}