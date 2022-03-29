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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Riccardo Ferrari
 */
public class TableRowConverter {

	public static TableRow asTableRow(Object child) {
		if (null == child) {
			return null;
		}

		TableRow tableRow = new TableRow();

		Class<?> childClass = child.getClass();

		Field[] publicFields = childClass.getFields();

		for (Field field : publicFields) {
			String name = field.getName();

			try {
				Object value = field.get(child);

				Class<?> fieldClass = field.getType();

				String fieldClassName = fieldClass.getCanonicalName();

				if (fieldClass.isPrimitive() ||
					fieldClassName.startsWith("java.lang.") ||
					fieldClassName.startsWith("java.util.")) {

					if (fieldClassName.equals("java.util.List")) {
						List<TableRow> tableRows = new ArrayList<>();

						List<?> values = (List)value;

						if (values.isEmpty()) {
							tableRow.set(name, tableRows);
						}
						else {
							Object item = values.get(0);

							Class<?> itemClass = item.getClass();

							Package itemPackage = itemClass.getPackage();

							String itemPackageName = itemPackage.getName();

							if (itemPackageName.startsWith(_PACKAGE_NAME)) {

								for (Object listValue : values) {
									tableRows.add(asRow(listValue));
								}
							}
							else {
								tableRow.set(name, values);
							}
						}
					}
					else if (fieldClassName.equals("java.util.Map")) {
						tableRow.set(
							name, ObjectMapperUtil.writeValueAsString(value));
					}
					else {
						tableRow.set(name, value);
					}
				}
				else if (fieldClassName.startsWith(_PACKAGE_NAME)) {
					tableRow.set(name, asRow(value));
				}
				else {
					_logger.warn(
						String.format("Unknown type: %s", fieldClassName));
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