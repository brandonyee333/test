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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.UserJsonWrapper;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Cristina González
 */
public class UserJsonWrapperStdSerializer
	extends StdSerializer<UserJsonWrapper> {

	public UserJsonWrapperStdSerializer() {
		this(null);
	}

	public UserJsonWrapperStdSerializer(
		Class<UserJsonWrapper> userJsonWrapper) {

		super(userJsonWrapper);
	}

	@Override
	public void serialize(
			UserJsonWrapper userJsonWrapper, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider)
		throws IOException {

		jsonGenerator.writeStartObject();

		jsonGenerator.writeObject(userJsonWrapper.getUser());

		List<Object> customFields = userJsonWrapper.getCustomFields();

		if (customFields == null) {
			customFields = new ArrayList<>();
		}

		Stream<Object> customFieldStream = customFields.stream();

		customFieldStream.forEach(
			customField -> {
				try {
					jsonGenerator.writeObject(customField);
				}
				catch (IOException ioe) {
					_log.error(ioe);
				}
			});

		jsonGenerator.writeEndObject();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserJsonWrapperStdSerializer.class);

}