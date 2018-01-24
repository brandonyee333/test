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

package com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ImageServiceUtil;

import java.io.IOException;

import java.util.TimeZone;

/**
 * @author Cristina González
 */
public class UserModelStdSerializer extends StdSerializer<User> {

	public UserModelStdSerializer() {
		this(null);
	}

	public UserModelStdSerializer(Class<User> user) {
		super(user);
	}

	@Override
	public void serialize(
			User user, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider)
		throws IOException {

		jsonGenerator.writeStringField("projectId", _PROJECT_ID);
		jsonGenerator.writeNumberField("userId", user.getUserId());
		jsonGenerator.writeStringField("emailAddress", user.getEmailAddress());

		jsonGenerator.writeStringField("firstName", user.getFirstName());

		jsonGenerator.writeStringField("googleUserId", user.getGoogleUserId());

		jsonGenerator.writeStringField("jobTitle", user.getJobTitle());

		jsonGenerator.writeStringField("languageId", user.getLanguageId());

		jsonGenerator.writeStringField("lastName", user.getLastName());

		jsonGenerator.writeStringField("middleName", user.getMiddleName());

		jsonGenerator.writeStringField("openId", user.getOpenId());

		try {
			if (user.getPortraitId() > 0) {
				Image image = ImageServiceUtil.getImage(user.getPortraitId());

				if ((image != null) && (image.getTextObj() != null)) {
					jsonGenerator.writeBinaryField(
						"portrait", image.getTextObj());
				}
			}
		}
		catch (PortalException pe) {
			_log.error(
				"Can't serialize portrait of user " + user.getUserId(), pe);
		}

		jsonGenerator.writeStringField("screenName", user.getScreenName());

		jsonGenerator.writeNumberField("status", user.getStatus());

		TimeZone timeZone = user.getTimeZone();

		jsonGenerator.writeStringField("timeZoneId", timeZone.getID());

		jsonGenerator.writeObject(user.getExpandoBridge());

		jsonGenerator.writeObjectField("addresses", user.getAddresses());

		try {
			jsonGenerator.writeObjectField("contact", user.getContact());
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Can't serialize contacts of user " + user.getUserId(), pe);
			}
		}

		jsonGenerator.writeObjectField(
			"emailAddresses", user.getEmailAddresses());

		jsonGenerator.writeObjectField("groups", user.getGroups());

		try {
			jsonGenerator.writeObjectField(
				"organizations", user.getOrganizations());
		}
		catch (PortalException pe) {
			_log.error(
				"Can't serialize organizations of user " + user.getUserId(),
				pe);
		}

		jsonGenerator.writeObjectField("phones", user.getPhones());

		jsonGenerator.writeObjectField("teams", user.getTeams());

		jsonGenerator.writeObjectField("roles", user.getRoles());

		jsonGenerator.writeObjectField("userGroups", user.getUserGroups());

		jsonGenerator.writeObjectField("websites", user.getWebsites());
	}

	private static final String _PROJECT_ID = System.getenv("PULPO_PROJECT_ID");

	private static final Log _log = LogFactoryUtil.getLog(
		UserModelStdSerializer.class);

}