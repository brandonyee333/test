/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.akismet.client.util;

import com.liferay.akismet.client.configuration.AkismetServiceConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Jamie Sammons
 */
@Component(
	configurationPid = "com.liferay.akismet.client.configuration.AkismetConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true
)
public class AkismetServiceConfigurationUtil {

	public static int getAkismetCheckThreshold() {
		return _akismetServiceConfiguration.akismetCheckThreshold();
	}

	public static AkismetServiceConfiguration getAkismetServiceConfiguration() {
		return _akismetServiceConfiguration;
	}

	public static String getAPIKey() {
		String apiKey = null;

		try {
			apiKey = _akismetServiceConfiguration.akismetApiKey();
		}
		catch (NullPointerException npe) {
		}

		return apiKey;
	}

	public static Date getReportableTime(long companyId) {
		int reportableTime =
			_akismetServiceConfiguration.akismetReportableTime();

		return new Date(
			System.currentTimeMillis() - (reportableTime * Time.DAY));
	}

	public static Date getRetainSpamTime() {
		return new Date(
			System.currentTimeMillis() -
				(_akismetServiceConfiguration.akismetRetainSpamTime() *
					Time.DAY));
	}

	public static boolean isMessageBoardsEnabled() {
		if (Validator.isNull(getAPIKey())) {
			return false;
		}

		return _akismetServiceConfiguration.messageBoardsEnabled();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_akismetServiceConfiguration = ConfigurableUtil.createConfigurable(
			AkismetServiceConfiguration.class, properties);
	}

	private static volatile AkismetServiceConfiguration
		_akismetServiceConfiguration;

}