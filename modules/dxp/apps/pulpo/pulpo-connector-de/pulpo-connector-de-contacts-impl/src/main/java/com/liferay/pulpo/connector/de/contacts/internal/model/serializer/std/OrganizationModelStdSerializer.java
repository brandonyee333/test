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

import com.liferay.portal.kernel.model.Organization;

import java.io.IOException;

/**
 * @author Cristina González
 */
public class OrganizationModelStdSerializer
	extends StdSerializer<Organization> {

	public OrganizationModelStdSerializer() {
		this(null);
	}

	public OrganizationModelStdSerializer(Class<Organization> organization) {
		super(organization);
	}

	@Override
	public void serialize(
			Organization organization, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider)
		throws IOException {

		jsonGenerator.writeStartObject();

		jsonGenerator.writeNumberField(
			"organizationId", organization.getOrganizationId());

		jsonGenerator.writeStringField("name", organization.getName());

		jsonGenerator.writeStringField("type", organization.getType());

		jsonGenerator.writeObject(organization.getExpandoBridge());

		jsonGenerator.writeEndObject();
	}

}