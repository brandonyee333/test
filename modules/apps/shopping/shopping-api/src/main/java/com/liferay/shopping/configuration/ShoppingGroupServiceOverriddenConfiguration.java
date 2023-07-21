/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.configuration;

import com.liferay.portal.kernel.settings.Settings;
import com.liferay.shopping.configuration.definition.ShoppingGroupServiceConfigurationOverrideImpl;

/**
 * @author Brian Wing Shun Chan
 * @author Eduardo García
 */
@Settings.OverrideClass(ShoppingGroupServiceConfigurationOverrideImpl.class)
public interface ShoppingGroupServiceOverriddenConfiguration
	extends ShoppingGroupServiceConfiguration,
			ShoppingGroupServiceConfigurationOverride {
}