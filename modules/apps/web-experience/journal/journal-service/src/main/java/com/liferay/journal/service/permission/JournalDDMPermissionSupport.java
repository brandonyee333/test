/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.service.permission;

import com.liferay.dynamic.data.mapping.util.DDMStructurePermissionSupport;
import com.liferay.dynamic.data.mapping.util.DDMTemplatePermissionSupport;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.journal.model.JournalArticle"
)
public class JournalDDMPermissionSupport
	implements DDMStructurePermissionSupport, DDMTemplatePermissionSupport {

	@Override
	public String getResourceName() {
		return JournalPermission.RESOURCE_NAME;
	}

	@Override
	public String getResourceName(long classNameId) {
		return JournalPermission.RESOURCE_NAME;
	}

}