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

package com.liferay.osb.asah.common.util;

import java.lang.reflect.Field;

import java.sql.Array;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Inácio Nery
 */
public class BeanUtils {

	public static void copyProperties(
		Map<String, Object> source, Object target) {

		Map<String, Field> fieldMap = CacheFieldMap.getFieldMap(
			target.getClass());

		Collection<Field> fields = fieldMap.values();

		fields.forEach(
			field -> {
				String fieldName = field.getName();

				fieldName = fieldName.toLowerCase();

				Object value = source.get(fieldName.replace("_", ""));

				if (Objects.nonNull(value)) {
					field.setAccessible(true);

					try {
						if (value instanceof Array) {
							Array array = (Array)value;

							value = new LinkedHashSet<>(
								Arrays.asList((Long[])array.getArray()));
						}

						field.set(target, value);
					}
					catch (Exception exception) {
						_log.error(exception, exception);
					}
				}
			});
	}

	public static void copyProperties(Object source, Object target) {
		Map<String, Field> sourceFieldMap = CacheFieldMap.getFieldMap(
			source.getClass());

		Map<String, Field> targetFieldMap = CacheFieldMap.getFieldMap(
			target.getClass());

		Collection<Field> targetFields = targetFieldMap.values();

		targetFields.forEach(
			targetField -> {
				String fieldName = targetField.getName();

				fieldName = fieldName.toLowerCase();

				Field sourceField = sourceFieldMap.get(
					fieldName.replace("_", ""));

				if (sourceField != null) {
					sourceField.setAccessible(true);
					targetField.setAccessible(true);

					try {
						Object value = sourceField.get(source);

						if (Objects.nonNull(value)) {
							if (value instanceof Array) {
								Array array = (Array)value;

								value = array.getArray();
							}

							targetField.set(target, value);
						}
					}
					catch (Exception exception) {
						_log.error(exception, exception);
					}
				}
			});
	}

	private static final Log _log = LogFactory.getLog(BeanUtils.class);

	private static class CacheFieldMap {

		public static Map<String, Field> getFieldMap(Class clazz) {
			Map<String, Field> fields = _cacheMap.get(clazz.getName());

			if (fields == null) {
				synchronized (CacheFieldMap.class) {
					fields = new HashMap<>();

					for (Field field : clazz.getDeclaredFields()) {
						String fieldName = field.getName();

						fieldName = fieldName.toLowerCase();

						fields.put(fieldName.replace("_", ""), field);
					}

					_cacheMap.put(clazz.getName(), fields);
				}
			}

			return fields;
		}

		private static final Map<String, Map<String, Field>> _cacheMap =
			new HashMap<>();

	}

}