/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.token.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.liferay.portal.security.sso.token.security.auth.TokenLocation;

/**
 * @author Michael C. Han
 * @author Mika Koivisto
 */
@ExtendedObjectClassDefinition(
	category = "foundation", scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.portal.security.sso.token.internal.configuration.TokenConfiguration",
	localization = "content/Language", name = "token-configuration-name"
)
public interface TokenConfiguration {

	@Meta.AD(deflt = "false", name = "enabled", required = false)
	public boolean enabled();

	@Meta.AD(
		deflt = "false", description = "import-from-ldap-help",
		name = "import-from-ldap", required = false
	)
	public boolean importFromLDAP();

	@Meta.AD(
		deflt = "SM_USER", description = "user-token-name-help",
		name = "user-token-name", required = false
	)
	public String userTokenName();

	@Meta.AD(
		deflt = "REQUEST_HEADER", description = "token-location-help",
		name = "token-location", required = false
	)
	public TokenLocation tokenLocation();

	@Meta.AD(
		deflt = "SMIDENTITY|SMSESSION",
		description = "authentication-cookies-help",
		name = "authentication-cookies", required = false
	)
	public String[] authenticationCookies();

	@Meta.AD(name = "logout-redirect-url", required = false)
	public String logoutRedirectURL();

}