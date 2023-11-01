/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.rest.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

/**
 * @author Feliphe Marinho
 */
@Meta.OCD(
	name="function-object-entry-manager-configuration",
	factory = true,
	localization = "content/Language",
	id = "com.liferay.object.rest.internal.configuration.FunctionObjectEntryManagerConfiguration"
)
public interface FunctionObjectEntryManagerConfiguration {

	@Meta.AD(type = Meta.Type.String,name="oauth2-application-external-reference-code" , required = false)
	public String oAuth2ApplicationExternalReferenceCode();

	@Meta.AD(type = Meta.Type.String, name="resource-path", required = false)
	public String resourcePath();

}