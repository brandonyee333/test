/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.js.spa.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Bruno Basto
 */
@ExtendedObjectClassDefinition(category = "foundation")
@Meta.OCD(
	description = "spa-configuration-description",
	id = "com.liferay.frontend.js.spa.web.configuration.SPAConfiguration",
	localization = "content/Language", name = "spa-configuration-name"
)
public interface SPAConfiguration {

	@Meta.AD(
		deflt = "-1", description = "cache-expiration-time-description",
		name = "cache-expiration-time-name"
	)
	public String cacheExpirationTime();

	@Meta.AD(
		description = "custom-excluded-paths-description",
		name = "custom-excluded-paths-name", required = false
	)
	public String[] customExcludedPaths();

	@Meta.AD(
		deflt = "false",
		description = "disable-in-internet-explorer-description",
		name = "disable-in-internet-explorer", required = false
	)
	public boolean disableInInternetExplorer();

	@Meta.AD(
		deflt = "false",
		description = "disable-in-internet-explorer-11-description",
		name = "disable-in-internet-explorer-11", required = false
	)
	public boolean disableInInternetExplorer11();

	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/javascript.single.page.application.timeout}",
		description = "request-timeout-description",
		name = "request-timeout-name"
	)
	public String requestTimeout();

	@Meta.AD(
		deflt = "30000", description = "user-notification-timeout-description",
		name = "user-notification-timeout-name"
	)
	public String userNotificationTimeout();

}