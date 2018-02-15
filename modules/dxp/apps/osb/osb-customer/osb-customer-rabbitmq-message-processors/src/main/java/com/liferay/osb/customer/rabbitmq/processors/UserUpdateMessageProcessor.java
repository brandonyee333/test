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

import com.liferay.osb.customer.rabbitmq.connector.processor.MessageProcessor;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.util.PortalInstances;

import java.util.Calendar;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = {"routing.key=entity.user.update"},
	service = UserUpdateMessageProcessor.class
)
public class UserUpdateMessageProcessor implements MessageProcessor {

	@Override
	public void process(
		String routingKey, String message, Map<String, Object> properties) {

		try {
			updateUser(message);
		}
		catch (Exception e) {
			_log.error(message);

			_log.error(e, e);
		}
	}

	protected void updateUser(String message) throws Exception {
		JSONObject jsonObject = _jsonFactory.createJSONObject(message.trim());

		String uuid = jsonObject.getString("uuid");

		User user = _userLocalService.fetchUserByUuidAndCompanyId(
			uuid, PortalInstances.getDefaultCompanyId());

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

		_userLocalService.updateUser(
			user.getUserId(), null, null, null, false, null, null, screenName,
			emailAddress, user.getFacebookId(), user.getOpenId(), false, null,
			languageId, timeZoneId, user.getGreeting(), user.getComments(),
			firstName, middleName, lastName, contact.getPrefixId(),
			contact.getSuffixId(), contact.getMale(),
			calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE),
			calendar.get(Calendar.YEAR), contact.getSmsSn(),
			contact.getFacebookSn(), contact.getJabberSn(),
			contact.getSkypeSn(), contact.getTwitterSn(), jobTitle, null, null,
			null, null, null, null);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserUpdateMessageProcessor.class);

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private UserLocalService _userLocalService;

}