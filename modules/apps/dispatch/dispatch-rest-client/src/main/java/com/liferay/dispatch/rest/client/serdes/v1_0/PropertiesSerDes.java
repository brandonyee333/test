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

package com.liferay.dispatch.rest.client.serdes.v1_0;

import com.liferay.dispatch.rest.client.dto.v1_0.Properties;
import com.liferay.dispatch.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Nilton Vieira
 * @generated
 */
@Generated("")
public class PropertiesSerDes {

	public static Properties toDTO(String json) {
		PropertiesJSONParser propertiesJSONParser = new PropertiesJSONParser();

		return propertiesJSONParser.parseToDTO(json);
	}

	public static Properties[] toDTOs(String json) {
		PropertiesJSONParser propertiesJSONParser = new PropertiesJSONParser();

		return propertiesJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Properties properties) {
		if (properties == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (properties.getSchema() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"schema\": ");

			sb.append("\"");

			sb.append(_escape(properties.getSchema()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		PropertiesJSONParser propertiesJSONParser = new PropertiesJSONParser();

		return propertiesJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Properties properties) {
		if (properties == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (properties.getSchema() == null) {
			map.put("schema", null);
		}
		else {
			map.put("schema", String.valueOf(properties.getSchema()));
		}

		return map;
	}

	public static class PropertiesJSONParser
		extends BaseJSONParser<Properties> {

		@Override
		protected Properties createDTO() {
			return new Properties();
		}

		@Override
		protected Properties[] createDTOArray(int size) {
			return new Properties[size];
		}

		@Override
		protected void setField(
			Properties properties, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "schema")) {
				if (jsonParserFieldValue != null) {
					properties.setSchema((String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}