/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.shield.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author André de Oliveira
 */
@ExtendedObjectClassDefinition(category = "foundation")
@Meta.OCD(
	id = "com.liferay.portal.search.elasticsearch.shield.configuration.ShieldConfiguration",
	localization = "content/Language", name = "shield-configuration-name"
)
public interface ShieldConfiguration {

	@Meta.AD(
		deflt = "liferay", description = "password-help", name = "password",
		required = false, type = Meta.Type.Password
	)
	public String password();

	@Meta.AD(
		deflt = "false", description = "requires-authentication-help",
		name = "requires-authentication", required = false
	)
	public boolean requiresAuthentication();

	@Meta.AD(
		deflt = "true", description = "requires-ssl-help",
		name = "requires-ssl", required = false
	)
	public boolean requiresSSL();

	@Meta.AD(
		description = "ssl-keystore-key-password-help",
		name = "ssl-keystore-key-password", required = false,
		type = Meta.Type.Password
	)
	public String sslKeystoreKeyPassword();

	@Meta.AD(
		deflt = "liferay", description = "ssl-keystore-password-help",
		name = "ssl-keystore-password", required = false,
		type = Meta.Type.Password
	)
	public String sslKeystorePassword();

	@Meta.AD(
		deflt = "/path/to/keystore.jks", description = "ssl-keystore-path-help",
		name = "ssl-keystore-path", required = false
	)
	public String sslKeystorePath();

	@Meta.AD(
		description = "ssl-truststore-password-help",
		name = "ssl-truststore-password", required = false,
		type = Meta.Type.Password
	)
	public String sslTruststorePassword();

	@Meta.AD(
		description = "ssl-truststore-path-help", name = "ssl-truststore-path",
		required = false
	)
	public String sslTruststorePath();

	@Meta.AD(
		deflt = "liferay", description = "username-help", name = "username",
		required = false
	)
	public String username();

}