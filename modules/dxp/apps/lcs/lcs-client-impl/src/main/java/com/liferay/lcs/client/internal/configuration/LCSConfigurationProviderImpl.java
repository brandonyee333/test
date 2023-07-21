/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.configuration;

import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.configuration.LCSConfigurationProvider;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Igor Beslic
 */
@Component(
	configurationPid = "com.liferay.lcs.client.configuration.LCSConfiguration",
	immediate = true, service = LCSConfigurationProvider.class
)
public class LCSConfigurationProviderImpl implements LCSConfigurationProvider {

	public LCSConfiguration getLCSConfiguration() {
		return new LCSConfigurationDecorator(_lcsConfiguration);
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_lcsConfiguration = ConfigurableUtil.createConfigurable(
			LCSConfiguration.class, properties);
	}

	@Deactivate
	protected void deactivate() {
		_lcsConfiguration = null;
	}

	private volatile LCSConfiguration _lcsConfiguration;

}