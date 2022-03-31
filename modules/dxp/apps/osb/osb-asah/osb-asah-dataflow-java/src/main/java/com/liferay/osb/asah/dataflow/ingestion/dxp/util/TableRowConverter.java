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

package com.liferay.osb.asah.dataflow.ingestion.dxp.util;

import com.google.api.services.bigquery.model.TableRow;

import com.liferay.osb.asah.dataflow.common.ObjectMapperUtil;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Riccardo Ferrari
 */
public class TableRowConverter {

	public static TableRow asTableRow(Object obj) {
		if (null == obj) {
			return null;
		}

		TableRow tableRow = new TableRow();

		Class<?> objClass = obj.getClass();

		Field[] fields = objClass.getFields();

		for (Field field : fields) {
			String fieldName = field.getName();

			try {
				Object value = field.get(obj);

				Class<?> fieldClass = field.getType();

				String fieldClassName = fieldClass.getCanonicalName();

				if (fieldClass.isPrimitive() ||
					fieldClassName.startsWith("java.lang.") ||
					fieldClassName.startsWith("java.util.")) {

					if (fieldClassName.equals("java.util.List")) {
						List<?> values = (List)value;

						if ((values == null) || values.isEmpty()) {
							tableRow.set(fieldName, new ArrayList<>());
						}
						else {
							Object item = values.get(0);

							Class<?> itemClass = item.getClass();

							if (itemClass.isAnonymousClass()) {
								itemClass = itemClass.getSuperclass();
							}

							String itemClassName = itemClass.getName();

							if (itemClassName.startsWith(_PACKAGE_NAME)) {
								Stream<?> valuesStream = values.stream();

								tableRow.set(
									fieldName,
									valuesStream.map(
										TableRowConverter::asTableRow
									).collect(
										Collectors.toList()
									));
							}
							else if (item instanceof Map) {
								Stream<?> valuesStream = values.stream();

								tableRow.set(
									fieldName,
									valuesStream.map(
										ObjectMapperUtil::writeValueAsString
									).collect(
										Collectors.toList()
									));
							}
							else {
								tableRow.set(fieldName, values);
							}
						}
					}
					else if (fieldClassName.equals("java.util.Map")) {
						tableRow.set(
							fieldName,
							ObjectMapperUtil.writeValueAsString(value));
					}
					else {
						tableRow.set(fieldName, value);
					}
				}
				else if (fieldClassName.startsWith(_PACKAGE_NAME)) {
					tableRow.set(fieldName, asTableRow(value));
				}
				else {
					_logger.warn(
						String.format("Unknown fieldType: %s", fieldClassName));
				}
			}
			catch (IllegalAccessException illegalAccessException) {
				_logger.error(
					illegalAccessException.getMessage(),
					illegalAccessException);
			}
		}

		return tableRow;
	}

	private static final String _PACKAGE_NAME =
		"com.liferay.osb.asah.dataflow.ingestion.dxp.entity";

	private static final Logger _logger = LoggerFactory.getLogger(
		TableRowConverter.class);

}