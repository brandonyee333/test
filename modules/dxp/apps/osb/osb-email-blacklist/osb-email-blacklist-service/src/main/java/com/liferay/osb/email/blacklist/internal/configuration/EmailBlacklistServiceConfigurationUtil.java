/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.email.blacklist.internal.configuration;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * @author Ryan Park
 */
@Component(
	configurationPid = "com.liferay.osb.email.blacklist.internal.configuration.EmailBlacklistServiceConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true
)
public class EmailBlacklistServiceConfigurationUtil {

	public static int getBounceLimit() {
		return _emailBlacklistServiceConfiguration.bounceLimit();
	}

	public static int getBounceLimitWithinDays() {
		return _emailBlacklistServiceConfiguration.bounceLimitWithinDays();
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_emailBlacklistServiceConfiguration =
			ConfigurableUtil.createConfigurable(
				EmailBlacklistServiceConfiguration.class, properties);
	}

	private static volatile EmailBlacklistServiceConfiguration
		_emailBlacklistServiceConfiguration;

}