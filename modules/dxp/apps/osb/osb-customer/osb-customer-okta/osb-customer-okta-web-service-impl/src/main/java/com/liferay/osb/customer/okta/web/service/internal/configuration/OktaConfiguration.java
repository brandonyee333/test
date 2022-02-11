/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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