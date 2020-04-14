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