/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Peter Fellwock
 */
@ExtendedObjectClassDefinition(
	category = "other", scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.wsrp.configuration.WSRPGroupServiceConfiguration",
	localization = "content/Language", name = "wsrp-service-configuration-name"
)
public interface WSRPGroupServiceConfiguration {

	@Meta.AD(deflt = "", name = "consumer-request-extensions", required = false)
	public String[] consumerRequestExtensions();

	@Meta.AD(
		deflt = "com.liferay.wsrp.util.AttributeExtensionHelper",
		name = "extension-helper-impl", required = false
	)
	public String extensionHelperImpl();

	@Meta.AD(
		deflt = "0", name = "failed-consumers-check-interval", required = false
	)
	public int failedConsumersCheckInterval();

	@Meta.AD(
		deflt = "127.0.0.1|SERVER_IP", name = "proxy-url-ips-allowed",
		required = false
	)
	public String[] proxyUrlIpsAllowed();

	@Meta.AD(
		deflt = "false", name = "secure-resource-urls-enabled", required = false
	)
	public boolean secureResourceUrlsEnabled();

	@Meta.AD(
		deflt = "salt", name = "secure-resource-urls-salt", required = false
	)
	public String secureResourceUrlsSalt();

	@Meta.AD(deflt = "false", name = "soap-debug", required = false)
	public boolean soapDebug();

}