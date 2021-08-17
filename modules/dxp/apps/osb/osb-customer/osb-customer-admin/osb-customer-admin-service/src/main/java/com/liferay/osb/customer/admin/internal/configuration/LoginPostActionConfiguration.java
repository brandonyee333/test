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

package com.liferay.osb.customer.admin.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Kyle Bischof
 */
@ExtendedObjectClassDefinition(category = "other")
@Meta.OCD(
	id = "com.liferay.osb.customer.admin.internal.configuration.LoginPostActionConfiguration",
	localization = "content/Language", name = "login-post-action-name"
)
public interface LoginPostActionConfiguration {

	@Meta.AD(deflt = "true", name = "sync-koroneiki", required = false)
	public boolean syncKoroneiki();

	@Meta.AD(deflt = "true", name = "sync-web", required = false)
	public boolean syncWeb();

	@Meta.AD(deflt = "true", name = "sync-web-user", required = false)
	public boolean syncWebUser();

}