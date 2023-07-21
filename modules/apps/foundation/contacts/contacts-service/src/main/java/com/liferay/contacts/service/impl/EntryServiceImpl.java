/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.contacts.service.impl;

import com.liferay.contacts.model.Entry;
import com.liferay.contacts.service.base.EntryServiceBaseImpl;
import com.liferay.contacts.util.ContactsUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.List;

/**
 * @author Bruno Farache
 */
public class EntryServiceImpl extends EntryServiceBaseImpl {

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public void afterPropertiesSet() {
		_enabled = GetterUtil.getBoolean(
			PropsUtil.get("contacts.entry.service.enabled"));

		super.afterPropertiesSet();
	}

	@Override
	public JSONArray searchUsersAndContacts(
			long companyId, String keywords, int start, int end)
		throws PortalException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		if (!_enabled) {
			throw new UnsupportedOperationException(
				"This service is disabled by default. To enable it, set the " +
					"property \"contacts.entry.service.enabled\" to true in " +
						"portal.properties.");
		}

		long userId = getUserId();

		List<BaseModel<?>> contacts = entryLocalService.searchUsersAndContacts(
			companyId, userId, keywords, start, end);

		for (BaseModel<?> contact : contacts) {
			JSONObject jsonObject = null;

			if (contact instanceof User) {
				jsonObject = ContactsUtil.getUserJSONObject(
					userId, (User)contact);
			}
			else {
				jsonObject = ContactsUtil.getEntryJSONObject((Entry)contact);
			}

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	private volatile boolean _enabled;

}