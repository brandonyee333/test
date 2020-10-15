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

	@Meta.AD(
		deflt = "is-lrsd-uat@liferay.com", name = "error-email-address",
		required = false
	)
	public String errorEmailAddress();

}