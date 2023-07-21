/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.okta.web.service.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Jenny Chen
 */
@ExtendedObjectClassDefinition(category = "other")
@Meta.OCD(
	id = "com.liferay.osb.customer.okta.web.service.internal.configuration.OktaConfiguration",
	localization = "content/Language", name = "okta-name"
)
public interface OktaConfiguration {

	@Meta.AD(
		deflt = "liferay-sandbox.oktapreview.com", name = "host",
		required = false
	)
	public String host();

	@Meta.AD(deflt = "test", name = "api-token", required = false)
	public String apiToken();

}