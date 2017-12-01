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
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.portal.kernel.model.Contact;

import java.io.IOException;

import java.text.DateFormat;

/**
 * @author Cristina González
 */
public class ContactModelStdSerializer extends StdSerializer<Contact> {

	public ContactModelStdSerializer() {
		this(null);
	}

	public ContactModelStdSerializer(Class<Contact> contactClass) {
		super(contactClass);
	}

	@Override
	public void serialize(
			Contact contact, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider)
		throws IOException {

		if (contact.isUser()) {
			jsonGenerator.writeStartObject();

			jsonGenerator.writeNumberField("contactId", contact.getContactId());

			if (contact.getBirthday() != null) {
				DateFormat dateFormat = ISO8601DateFormat.getDateInstance();

				jsonGenerator.writeStringField(
					"birthday", dateFormat.format(contact.getBirthday()));
			}

			jsonGenerator.writeStringField(
				"employeeNumber", contact.getEmployeeNumber());
			jsonGenerator.writeStringField(
				"employeeStatusId", contact.getEmployeeStatusId());
			jsonGenerator.writeStringField(
				"facebookSn", contact.getFacebookSn());
			jsonGenerator.writeStringField("jabberSn", contact.getJabberSn());
			jsonGenerator.writeBooleanField("male", contact.getMale());
			jsonGenerator.writeStringField("skypeSn", contact.getSkypeSn());
			jsonGenerator.writeStringField("twitterSn", contact.getTwitterSn());

			jsonGenerator.writeObject(contact.getExpandoBridge());

			jsonGenerator.writeEndObject();
		}
	}

}