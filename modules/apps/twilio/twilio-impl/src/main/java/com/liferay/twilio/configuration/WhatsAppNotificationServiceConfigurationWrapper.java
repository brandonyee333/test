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

package com.liferay.twilio.configuration;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Map;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Peter Richards
 */
@Component(
	configurationPid = WhatsAppNotificationServiceConfiguration.PID,
	immediate = true,
	service = WhatsAppNotificationServiceConfigurationWrapper.class
)
public class WhatsAppNotificationServiceConfigurationWrapper {

	public String getAccountSid() {
		return _configuration.accountSid();
	}

	public String getAuthToken() {
		return _configuration.authToken();
	}

	public String getDefaultCountryCode() {
		return _configuration.defaultCountryCode();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		if (log.isTraceEnabled()) {
			Stream keySetStream = properties.keyset(
			).stream();
			String simpleClassName = getClass().getSimpleName();

			log.trace(
				"Activating {} : {}", simpleClassName,
				keySetStream.map(
					key -> {
						Object value = properties.get(key);

						return key + "=" + value.toString();
					}
				).collect(
					Collectors.joining(", ", "{", "}")
				));
		}

		_configuration = ConfigurableUtil.createConfigurable(
			WhatsAppNotificationServiceConfiguration.class, properties);
	}

	protected final Log log = LogFactoryUtil.getLog(
		WhatsAppNotificationServiceConfigurationWrapper.class);

	private volatile WhatsAppNotificationServiceConfiguration _configuration;

}