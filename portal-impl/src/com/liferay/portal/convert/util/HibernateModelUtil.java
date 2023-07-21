/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.convert.util;

import com.liferay.portal.kernel.model.BaseModel;

import java.util.List;

/**
 * @author     Cristina González
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class HibernateModelUtil {

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static List<Class<? extends BaseModel<?>>> getModelClassNames(
		ClassLoader classLoader, String regex) {

		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	protected static Class<? extends BaseModel<?>> getImplClass(
		ClassLoader classLoader, String implClassName) {

		throw new UnsupportedOperationException();
	}

}