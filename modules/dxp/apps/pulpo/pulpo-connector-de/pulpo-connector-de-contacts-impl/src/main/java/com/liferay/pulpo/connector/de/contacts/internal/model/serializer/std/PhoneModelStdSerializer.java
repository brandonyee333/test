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

import com.liferay.portal.kernel.model.Phone;

import java.io.IOException;

/**
 * @author Cristina González
 */
public class PhoneModelStdSerializer extends StdSerializer<Phone> {

	public PhoneModelStdSerializer() {
		this(null);
	}

	public PhoneModelStdSerializer(Class<Phone> phoneClass) {
		super(phoneClass);
	}

	@Override
	public void serialize(
			Phone phone, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider)
		throws IOException {

		jsonGenerator.writeStartObject();
		jsonGenerator.writeNumberField("phoneId", phone.getPhoneId());

		jsonGenerator.writeStringField("extension", phone.getExtension());
		jsonGenerator.writeStringField("number", phone.getNumber());

		jsonGenerator.writeBooleanField("primary", phone.getPrimary());

		jsonGenerator.writeStringField(
			"typeId", String.valueOf(phone.getTypeId()));

		jsonGenerator.writeObject(phone.getExpandoBridge());

		jsonGenerator.writeEndObject();
	}

}