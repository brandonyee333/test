/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.marvel.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Miguel Angelo Caldas Gallindo
 * @author Artur Aquino
 * @author André de Oliveira
 */
@ExtendedObjectClassDefinition(category = "foundation")
@Meta.OCD(
	id = "com.liferay.portal.search.elasticsearch.marvel.web.configuration.MarvelWebConfiguration",
	localization = "content/Language", name = "marvel-web-configuration-name"
)
public interface MarvelWebConfiguration {

	@Meta.AD(
		deflt = "http://localhost:5601", description = "kibana-url-help",
		name = "kibana-url", required = false
	)
	public String kibanaURL();

	@Meta.AD(
		description = "proxy-servlet-log-enable-help",
		name = "proxy-servlet-log-enable", required = false
	)
	public boolean proxyServletLogEnable();

	@Meta.AD(
		deflt = "liferay", description = "shield-password-help",
		name = "shield-password", required = false, type = Meta.Type.Password
	)
	public String shieldPassword();

	@Meta.AD(
		deflt = "liferay", description = "shield-username-help",
		name = "shield-username", required = false
	)
	public String shieldUserName();

}