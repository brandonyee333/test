/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.configuration;

import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactory;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.security.lang.DoPrivilegedUtil;

/**
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class ConfigurationFactoryImpl implements ConfigurationFactory {

	@Override
	public Configuration getConfiguration(
		ClassLoader classLoader, String name) {

		return DoPrivilegedUtil.wrap(
			new ConfigurationImpl(
				classLoader, name, CompanyConstants.SYSTEM, null));
	}

}