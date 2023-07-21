/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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

	@Meta.AD(deflt = "true", name = "sync-web-user", required = false)
	public boolean syncWebUser();

}