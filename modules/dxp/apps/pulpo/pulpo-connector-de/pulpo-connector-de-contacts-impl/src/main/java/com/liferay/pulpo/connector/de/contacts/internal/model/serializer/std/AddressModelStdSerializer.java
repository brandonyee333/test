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

import com.liferay.portal.kernel.model.Address;

import java.io.IOException;

/**
 * @author Cristina González
 */
public class AddressModelStdSerializer extends StdSerializer<Address> {

	public AddressModelStdSerializer() {
		this(null);
	}

	public AddressModelStdSerializer(Class<Address> addressClass) {
		super(addressClass);
	}

	@Override
	public void serialize(
			Address address, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider)
		throws IOException {

		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField("zip", address.getZip());
		jsonGenerator.writeStringField("city", address.getCity());
		jsonGenerator.writeStringField("region", address.getRegion().getName());
		jsonGenerator.writeStringField(
			"country", address.getCountry().getName());
		jsonGenerator.writeBooleanField("primary", address.getPrimary());
		jsonGenerator.writeBooleanField("mailing", address.getMailing());
		jsonGenerator.writeStringField("street1", address.getStreet1());
		jsonGenerator.writeStringField("street2", address.getStreet2());
		jsonGenerator.writeStringField("street3", address.getStreet3());

		jsonGenerator.writeObject(address.getExpandoBridge());

		jsonGenerator.writeEndObject();
	}

}