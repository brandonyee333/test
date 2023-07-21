/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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