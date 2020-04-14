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