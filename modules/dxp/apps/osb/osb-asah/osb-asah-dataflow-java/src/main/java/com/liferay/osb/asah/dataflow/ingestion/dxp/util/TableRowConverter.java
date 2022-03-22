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

	public static TableRow asRow(final Object child) {
		if (null == child) {
			return null;
		}

		TableRow row = new TableRow();

		Class<?> childClass = child.getClass();

		Field[] publicFields = childClass.getFields();

		for (Field field : publicFields) {
			String name = field.getName();

			try {
				Object value = field.get(child);

				Class<?> type = field.getType();

				String typeCanonicalName = type.getCanonicalName();

				if (type.isPrimitive() ||
					typeCanonicalName.startsWith("java.lang.") ||
					typeCanonicalName.startsWith("java.util.")) {

					if (typeCanonicalName.equals("java.util.List")) {
						List<TableRow> tableRows = new ArrayList<>();

						List<?> values = (List)value;

						if (values.isEmpty()) {
							row.set(name, tableRows);
						}
						else {
							Object item = values.get(0);

							Class<?> itemClass = item.getClass();

							Package itemClassPackage = itemClass.getPackage();

							String itemClassPackageName =
								itemClassPackage.getName();

							if (itemClassPackageName.startsWith(
									_BASE_PACKAGE)) {

								for (Object listValue : values) {
									tableRows.add(asRow(listValue));
								}
							}
							else {
								row.set(name, values);
							}
						}
					}
					else if (typeCanonicalName.equals("java.util.Map")) {
						row.set(
							name, ObjectMapperUtil.writeValueAsString(value));
					}
					else {
						row.set(name, value);
					}
				}
				else if (typeCanonicalName.startsWith(_BASE_PACKAGE)) {
					row.set(name, asRow(value));
				}
				else {
					_logger.warn(
						String.format("Unknown type: %s", typeCanonicalName));
				}
			}
			catch (IllegalAccessException illegalAccessException) {
				_logger.error(
					illegalAccessException.getMessage(),
					illegalAccessException);
			}
		}

		return row;
	}

	private static final String _BASE_PACKAGE =
		"com.liferay.osb.asah.dataflow.ingestion.dxp.entity";

	private static final Logger _logger = LoggerFactory.getLogger(
		TableRowConverter.class);

}