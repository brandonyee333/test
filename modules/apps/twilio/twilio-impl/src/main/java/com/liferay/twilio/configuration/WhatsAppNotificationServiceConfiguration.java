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

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Peter Richards
 */
@ExtendedObjectClassDefinition(
	category = "third-party", scope = ExtendedObjectClassDefinition.Scope.GROUP
)
@Meta.OCD(
	description = "config-whatsapp-notifier-description",
	id = WhatsAppNotificationServiceConfiguration.PID,
	localization = "content/Language", name = "config-whatsapp-notifier-name"
)
public interface WhatsAppNotificationServiceConfiguration {

	String PID =
		"com.liferay.twilio.whatsapp.configuration.WhatsAppNotificationServiceConfiguration";

	@Meta.AD(
		deflt = "", description = "config-account-sid-description",
		name = "config-account-sid-name", required = false
	)
	String accountSid();

	@Meta.AD(
		deflt = "", description = "config-auth-token-description",
		name = "config-auth-token-name", required = false
	)
	String authToken();

	@Meta.AD(
		deflt = "+1", description = "config-default-country-code-description",
		name = "config-default-country-code-name", required = false
	)
	String defaultCountryCode();

}