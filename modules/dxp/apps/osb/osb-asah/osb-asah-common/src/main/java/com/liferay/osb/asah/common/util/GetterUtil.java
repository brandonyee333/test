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

import com.google.cloud.bigquery.FieldValue;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/**
 * @author Marcellus Tavares
 */
public class GetterUtil {

	public static String getDateString(Object object) {
		if (object instanceof FieldValue) {
			FieldValue fieldValue = (FieldValue)object;

			return fieldValue.getStringValue();
		}

		OffsetDateTime offsetDateTime = (OffsetDateTime)object;

		LocalDateTime localDateTime = offsetDateTime.toLocalDateTime();

		return localDateTime.toString();
	}

	public static int getInteger(Object object) {
		if (object instanceof FieldValue) {
			FieldValue fieldValue = (FieldValue)object;

			return (int)fieldValue.getLongValue();
		}

		return (int)object;
	}

	public static Number getNumber(Object object) {
		if (object instanceof FieldValue) {
			FieldValue fieldValue = (FieldValue)object;

			return fieldValue.getNumericValue();
		}

		return (Number)object;
	}

	public static Object getObject(Object object) {
		if (object instanceof FieldValue) {
			FieldValue fieldValue = (FieldValue)object;

			return fieldValue.getValue();
		}

		return object;
	}

}