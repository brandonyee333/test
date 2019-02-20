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

package com.liferay.osb.customer.release.tool.web.internal.util;

import com.liferay.dynamic.data.mapping.storage.Field;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Amos Fong
 */
public class DDMFieldsUtil {

	public static String getString(Fields ddmFields, String fieldName) {
		Field ddmField = ddmFields.get(fieldName);

		if (ddmField == null) {
			return StringPool.BLANK;
		}

		return GetterUtil.getString(ddmField.getValue());
	}

}