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

package com.liferay.osb.asah.common.repository.util;

import com.google.cloud.bigquery.FieldValue;

import java.util.Date;

/**
 * @author Matthew Kong
 */
public class BigQueryUtil {

	public static Date getDate(FieldValue fieldValue) {
		return new Date(fieldValue.getTimestampValue());
	}

	public static long getLong(FieldValue fieldValue) {
		return fieldValue.getLongValue();
	}

	public static String getString(FieldValue fieldValue) {
		return fieldValue.getStringValue();
	}

}