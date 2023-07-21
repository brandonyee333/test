/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.twitter.configuration.definition;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;
import com.liferay.twitter.internal.configuration.TwitterGroupServiceConfiguration;

import org.osgi.service.component.annotations.Component;

/**
 * @author Peter Fellwock
 */
@Component
public class TwitterGroupServiceConfigurationBeanDeclaration
	implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return TwitterGroupServiceConfiguration.class;
	}

}