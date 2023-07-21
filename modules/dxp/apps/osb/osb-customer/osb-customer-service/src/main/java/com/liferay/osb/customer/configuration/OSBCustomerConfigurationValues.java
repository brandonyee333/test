/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Amos Fong
 */
public class OSBCustomerConfigurationValues {

	public static final String LIFERAY_DOCS_BUILD_SCRIPT = GetterUtil.getString(
		OSBCustomerConfigurationUtil.get("liferay.docs.build.script"));

}