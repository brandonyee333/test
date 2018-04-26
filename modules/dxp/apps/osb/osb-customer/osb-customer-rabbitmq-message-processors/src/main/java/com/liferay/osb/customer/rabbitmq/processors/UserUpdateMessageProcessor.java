/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.customer.rabbitmq.processors;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.util.ExpandoConverterUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.User;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Iterator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "routing.key=entity.user.update",
	service = UserUpdateMessageProcessor.class
)
public class UserUpdateMessageProcessor extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		User user = fetchUser(jsonObject);

		if (user == null) {
			return;
		}

		String emailAddress = jsonObject.getString("emailAddress");
		String firstName = jsonObject.getString("firstName");
		String jobTitle = jsonObject.getString("jobTitle");
		String languageId = jsonObject.getString("languageId");
		String lastName = jsonObject.getString("lastName");
		String middleName = jsonObject.getString("middleName");
		String screenName = jsonObject.getString("screenName");
		String timeZoneId = jsonObject.getString("timeZoneId");

		Contact contact = user.getContact();

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(contact.getBirthday());

		user = userLocalService.updateUser(
			user.getUserId(), null, null, null, false, null, null, screenName,
			emailAddress, user.getFacebookId(), user.getOpenId(), false, null,
			languageId, timeZoneId, user.getGreeting(), user.getComments(),
			firstName, middleName, lastName, contact.getPrefixId(),
			contact.getSuffixId(), contact.isMale(),
			calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE),
			calendar.get(Calendar.YEAR), contact.getSmsSn(),
			contact.getFacebookSn(), contact.getJabberSn(),
			contact.getSkypeSn(), contact.getTwitterSn(), jobTitle, null, null,
			null, null, null, null);

		JSONObject expandosJSONObject = jsonObject.getJSONObject("expandos");

		if (expandosJSONObject != null) {
			ExpandoBridge expandoBridge = user.getExpandoBridge();

			Iterator<String> iterator = expandosJSONObject.keys();

			while (iterator.hasNext()) {
				String expandoTableName = iterator.next();

				JSONObject expandoTableJSONObject =
					expandosJSONObject.getJSONObject(expandoTableName);

				Iterator<String> expandoTableIterator =
					expandoTableJSONObject.keys();

				while (expandoTableIterator.hasNext()) {
					String expandoColumnName = expandoTableIterator.next();

					ExpandoColumn expandoColumn =
						expandoColumnLocalService.getColumn(
							user.getCompanyId(), User.class.getName(),
							expandoTableName, expandoColumnName);

					if (expandoColumn == null) {
						continue;
					}

					String expandoValueData = expandoTableJSONObject.getString(
						expandoColumnName);

					Serializable serializable =
						ExpandoConverterUtil.getAttributeFromString(
							expandoColumn.getType(), expandoValueData);

					expandoBridge.setAttribute(
						expandoColumnName, serializable, false);
				}
			}
		}
	}

	@Reference(unbind = "-")
	protected void setExpandoColumnLocalService(
		ExpandoColumnLocalService expandoColumnLocalService) {

		this.expandoColumnLocalService = expandoColumnLocalService;
	}

	protected ExpandoColumnLocalService expandoColumnLocalService;

}