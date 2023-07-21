/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.memberships.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Rodrigo Paulino
 */
@ExtendedObjectClassDefinition(category = "web-experience")
@Meta.OCD(
	id = "com.liferay.site.memberships.web.internal.configuration.SiteMembershipsConfiguration",
	localization = "content/Language",
	name = "site-memberships-configuration-name"
)
public interface SiteMembershipsConfiguration {

	@Meta.AD(
		description = "enable-assign-unassign-roles-of-user-groups-actions-help",
		name = "enable-assign-unassign-roles-of-user-groups-actions",
		required = false
	)
	public boolean enableAssignUnassignUserGroupsRolesActions();

}