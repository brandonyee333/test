/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.email.blacklist.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Jamie Sammons
 */
@ExtendedObjectClassDefinition(category = "other")
@Meta.OCD(
	id = "com.liferay.osb.email.blacklist.internal.configuration.EmailBlacklistServiceConfiguration",
	localization = "content/Language",
	name = "osb-email-blacklist-service-configuration-name"
)
public interface EmailBlacklistServiceConfiguration {

	@Meta.AD(
		deflt = "10", description = "bounce-limit-help", name = "bounce-limit",
		required = false
	)
	public int bounceLimit();

	@Meta.AD(
		deflt = "30", description = "bounce-limit-within-days-help",
		name = "bounce-limit-within-days", required = false
	)
	public int bounceLimitWithinDays();

}