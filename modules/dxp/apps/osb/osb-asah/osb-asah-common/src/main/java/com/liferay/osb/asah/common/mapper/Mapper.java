/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.common.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import com.liferay.osb.asah.common.date.DateUtil;

import java.io.IOException;

import java.text.ParseException;

import java.time.LocalDateTime;

import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.core.GenericTypeResolver;
import org.springframework.util.ObjectUtils;

/**
 * @author Inácio Nery
 */
public abstract class Mapper<S, T> {

	public SimpleModule getSimpleModule() {
		return _simpleModule;
	}

	@SuppressWarnings({"serial", "unchecked"})
	protected Mapper() {
		Class<?>[] classes = GenericTypeResolver.resolveTypeArguments(
			getClass(), Mapper.class);

		if (classes == null) {
			throw new IllegalArgumentException(
				"Expected classes on Mapper interface");
		}

		Class<S> clazz = (Class<S>)classes[0];

		_simpleModule = new SimpleModule(clazz.getSimpleName());

		StdDeserializer<S> stdDeserializer = new StdDeserializer<S>(clazz) {

			@Override
			public S deserialize(
					JsonParser jsonParser,
					DeserializationContext deserializationContext)
				throws IOException, JsonProcessingException {

				return toModel(jsonParser.readValueAs(JSONObject.class));
			}

		};

		_simpleModule.addDeserializer(clazz, stdDeserializer);

		StdSerializer<S> stdSerializer = new StdSerializer<S>(clazz) {

			@Override
			public void serialize(
					S entity, JsonGenerator jsonGenerator,
					SerializerProvider provider)
				throws IOException {

				jsonGenerator.writeObject(toDTO(entity));
			}

		};

		_simpleModule.addSerializer(stdSerializer);
	}

	protected abstract T toDTO(S entity);

	protected abstract S toModel(JSONObject jsonObject);

	protected Date toUTCDate(Object dateObject) {
		String dateString = ObjectUtils.nullSafeToString(dateObject);

		if (NumberUtils.isCreatable(dateString)) {
			return new Date(Long.parseLong(dateString));
		}

		try {
			return DateUtil.toUTCDate(dateString);
		}
		catch (ParseException parseException) {
			try {
				return DateUtil.toUTCDate(LocalDateTime.parse(dateString));
			}
			catch (Exception exception) {
				_log.error(exception);
			}
		}

		return null;
	}

	private static final Log _log = LogFactory.getLog(Mapper.class);

	private final SimpleModule _simpleModule;

}