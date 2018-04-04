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

package com.liferay.osb.community.akismet.client.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Jamie Sammons
 */
@ExtendedObjectClassDefinition(category = "other")
@Meta.OCD(
	id = "com.liferay.osb.community.akismet.client.configuration.AkismetServiceConfiguration",
	localization = "content/Language",
	name = "osb-community-akismet-configuration-name"
)
public interface AkismetServiceConfiguration {

	@Meta.AD(name = "api-key", required = false)
	public String akismetApiKey();

	@Meta.AD(name = "enable-for-message-boards", required = false)
	public boolean messageBoardsEnabled();

	@Meta.AD(deflt = "30", name = "reportable-time", required = false)
	public int akismetReportableTime();

	@Meta.AD(deflt = "50", name = "check-threshold", required = false)
	public int akismetCheckThreshold();

	@Meta.AD(deflt = "30", name = "retain-spam-time", required = false)
	public int akismetRetainSpamTime();

}