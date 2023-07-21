/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.email.blacklist.amazon.ses.listener.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Jamie Sammons
 */
@ExtendedObjectClassDefinition(category = "other")
@Meta.OCD(
	id = "com.liferay.osb.email.blacklist.amazon.ses.listener.internal.configuration.EmailBlacklistAmazonSESListenerConfiguration",
	localization = "content/Language",
	name = "osb-email-blacklist-amazon-ses-listener-configuration-name"
)
public interface EmailBlacklistAmazonSESListenerConfiguration {

	@Meta.AD(
		deflt = "amazon-arn-resource-name",
		description = "ses-bounce-notification-resource-name-help",
		name = "ses-bounce-notification-resource-name", required = false
	)
	public String sesBounceNotificationResourceName();

	@Meta.AD(
		deflt = "true",
		description = "ses-bounce-notification-validation-enabled-help",
		name = "ses-bounce-notification-validation-enabled", required = false
	)
	public boolean sesNotificationValidationEnabled();

}