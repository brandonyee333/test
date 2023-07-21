/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.internal.ddm;

import com.liferay.dynamic.data.mapping.util.DDMStructurePermissionSupport;
import com.liferay.portlet.documentlibrary.service.permission.DLPermission;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true,
	property = {
		"default.model.resource.name=true",
		"model.class.name=com.liferay.document.library.kernel.util.RawMetadataProcessor"
	}
)
public class RawMetadataProcessorDDMPermissionSupport
	implements DDMStructurePermissionSupport {

	@Override
	public String getResourceName() {
		return DLPermission.RESOURCE_NAME;
	}

}