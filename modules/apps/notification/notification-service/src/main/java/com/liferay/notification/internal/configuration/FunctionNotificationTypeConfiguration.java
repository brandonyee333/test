/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.notification.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

/**
 * @author Feliphe Marinho
 */


@Meta.OCD(
	factory = true,
	id = "com.liferay.notification.internal.configuration.FunctionNotificationTypeConfiguration",
	localization = "content/Language",
	name = "function-notification-type-configuration"
)
public interface FunctionNotificationTypeConfiguration {

	@Meta.AD(type = Meta.Type.String,
		name="oauth2-application-external-reference-code" , required = false)
	public String oAuth2ApplicationExternalReferenceCode();

	@Meta.AD(type = Meta.Type.String,
		name="resource-path" , required = false)
	public String resourcePath();

}