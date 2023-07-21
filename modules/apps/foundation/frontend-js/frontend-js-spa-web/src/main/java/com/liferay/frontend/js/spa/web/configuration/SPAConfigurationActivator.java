/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.js.spa.web.configuration;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Bruno Basto
 */
@Component(
	configurationPid = "com.liferay.frontend.js.spa.web.configuration.SPAConfiguration",
	immediate = true,
	property = {
		"cacheExpirationTime=-1", "requestTimeout=0",
		"userNotificationTimeout=30000"
	},
	service = SPAConfigurationActivator.class
)
public class SPAConfigurationActivator {

	public SPAConfiguration getSPAConfiguration() {
		return _spaConfiguration;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_spaConfiguration = ConfigurableUtil.createConfigurable(
			SPAConfiguration.class, properties);
	}

	@Deactivate
	protected void deactivate() {
		_spaConfiguration = null;
	}

	private volatile SPAConfiguration _spaConfiguration;

}