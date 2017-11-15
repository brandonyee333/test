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

import com.liferay.portal.kernel.model.Role;

import java.io.IOException;

/**
 * @author Cristina González
 */
public class RoleModelStdSerializer extends StdSerializer<Role> {

	public RoleModelStdSerializer() {
		this(null);
	}

	public RoleModelStdSerializer(Class<Role> roleClass) {
		super(roleClass);
	}

	@Override
	public void serialize(
			Role role, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider)
		throws IOException {

		jsonGenerator.writeStartObject();

		jsonGenerator.writeNumberField("roleId", role.getRoleId());

		jsonGenerator.writeStringField("description", role.getDescription());

		jsonGenerator.writeStringField("name", role.getName());

		jsonGenerator.writeNumberField("type", role.getType());

		jsonGenerator.writeObject(role.getExpandoBridge());

		jsonGenerator.writeEndObject();
	}

}