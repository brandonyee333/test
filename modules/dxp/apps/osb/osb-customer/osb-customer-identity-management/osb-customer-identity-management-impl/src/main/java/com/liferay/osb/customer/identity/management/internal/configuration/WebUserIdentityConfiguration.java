/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.identity.management.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Amos Fong
 */
@ExtendedObjectClassDefinition(category = "other")
@Meta.OCD(
	id = "com.liferay.osb.customer.identity.management.internal.configuration.WebUserIdentityConfiguration",
	localization = "content/Language", name = "web-user-identity-name"
)
public interface WebUserIdentityConfiguration {

	@Meta.AD(
		deflt = "web-uat.liferay.com", name = "host-name", required = false
	)
	public String host();

	@Meta.AD(deflt = "443", name = "port", required = false)
	public int port();

	@Meta.AD(deflt = "https", name = "protocol", required = false)
	public String protocol();

	@Meta.AD(deflt = "test", name = "api-token", required = false)
	public String apiToken();

}