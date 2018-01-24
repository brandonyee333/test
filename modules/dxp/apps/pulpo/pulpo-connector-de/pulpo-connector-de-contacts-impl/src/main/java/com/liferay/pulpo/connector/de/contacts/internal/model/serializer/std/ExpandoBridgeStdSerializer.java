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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;
import java.io.Serializable;

import java.util.Map;

/**
 * @author Cristina González
 */
public class ExpandoBridgeStdSerializer extends StdSerializer<ExpandoBridge> {

	public ExpandoBridgeStdSerializer() {
		this(null);
	}

	@Override
	public void serialize(
			ExpandoBridge expandoBridge, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider)
		throws IOException {

		try {
			String fieldPrefix = "custom" + StringPool.POUND;

			Map<String, Serializable> attributes = expandoBridge.getAttributes(
				false);

			attributes.forEach(
				(k, v) -> {
					try {
						jsonGenerator.writeStringField(
							fieldPrefix + k, v.toString());
					}
					catch (IOException ioe) {
						throw new RuntimeException(ioe);
					}
				});
		}
		catch (Exception e) {
			throw new IOException(e);
		}
	}

	protected ExpandoBridgeStdSerializer(Class<ExpandoBridge> t) {
		super(t);
	}

}