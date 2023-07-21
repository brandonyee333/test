/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class JSONUtil {

	public static JsonNode readTree(InputStream inputStream)
		throws IOException {

		return _objectMapper.readTree(inputStream);
	}

	public static JsonNode readTree(String content) throws IOException {
		return _objectMapper.readTree(content);
	}

	public static <T> T readValue(String content, Class<T> clazz)
		throws IOException {

		ObjectReader objectReader = getObjectReader(clazz);

		return objectReader.readValue(content);
	}

	public static <T> T readValue(
			String content, TypeReference<?> typeReference)
		throws IOException {

		ObjectReader objectReader = getObjectReader(typeReference);

		return objectReader.readValue(content);
	}

	public static void writeValue(File file, Object object) throws IOException {
		ObjectWriter objectWriter = getObjectWriter();

		objectWriter.writeValue(file, object);
	}

	public static byte[] writeValueAsBytes(Object object) throws IOException {
		ObjectWriter objectWriter = getObjectWriter();

		return objectWriter.writeValueAsBytes(object);
	}

	public static String writeValueAsString(Object object) throws IOException {
		ObjectWriter objectWriter = getObjectWriter();

		return objectWriter.writeValueAsString(object);
	}

	protected static ObjectReader getObjectReader(Class<?> clazz) {
		ObjectReader objectReader = _classObjectReaderMap.get(clazz);

		if (objectReader == null) {
			objectReader = _objectMapper.readerFor(clazz);

			_classObjectReaderMap.put(clazz, objectReader);
		}

		return objectReader;
	}

	protected static ObjectReader getObjectReader(
		TypeReference<?> typeReference) {

		ObjectReader objectReader = _typeReferenceObjectReaderMap.get(
			typeReference);

		if (objectReader == null) {
			objectReader = _objectMapper.readerFor(typeReference);

			_typeReferenceObjectReaderMap.put(typeReference, objectReader);
		}

		return objectReader;
	}

	protected static ObjectWriter getObjectWriter() {
		if (_objectWriter == null) {
			_objectWriter = _objectMapper.writer();
		}

		return _objectWriter;
	}

	private static final Map<Class<?>, ObjectReader> _classObjectReaderMap =
		new HashMap<>();
	private static final ObjectMapper _objectMapper = new ObjectMapper();
	private static ObjectWriter _objectWriter;
	private static final Map<TypeReference<?>, ObjectReader>
		_typeReferenceObjectReaderMap = new HashMap<>();

}