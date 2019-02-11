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

package com.liferay.osb.customer.downloads.display.web.internal.util;

import com.liferay.dynamic.data.mapping.storage.Field;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Amos Fong
 */
public class DDMFieldsUtil {

	public static boolean getBoolean(Fields ddmFields, String fieldName) {
		Field ddmField = ddmFields.get(fieldName);

		if (ddmField == null) {
			return false;
		}

		return GetterUtil.getBoolean(ddmField.getValue());
	}

	public static String getSelectOption(Fields ddmFields, String fieldName) {
		Field ddmField = ddmFields.get(fieldName);

		String value = GetterUtil.getString(ddmField.getValue());

		return value.substring(2, value.length() - 2);
	}

	public static String[] getSelectOptions(
		Fields ddmFields, String fieldName) {

		Field ddmField = ddmFields.get(fieldName);

		String value = GetterUtil.getString(ddmField.getValue());

		value = value.substring(2, value.length() - 2);

		return StringUtil.split(value, "\",\"");
	}

	public static String getString(Fields ddmFields, String fieldName) {
		Field ddmField = ddmFields.get(fieldName);

		if (ddmField == null) {
			return StringPool.BLANK;
		}

		return GetterUtil.getString(ddmField.getValue());
	}

}