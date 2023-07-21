/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service.impl;

import com.liferay.dynamic.data.mapping.model.DDMStructureVersion;
import com.liferay.dynamic.data.mapping.service.base.DDMStructureVersionServiceBaseImpl;
import com.liferay.dynamic.data.mapping.service.permission.DDMStructurePermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Pablo Carvalho
 */
public class DDMStructureVersionServiceImpl
	extends DDMStructureVersionServiceBaseImpl {

	@Override
	public DDMStructureVersion getLatestStructureVersion(long structureId)
		throws PortalException {

		DDMStructurePermission.check(
			getPermissionChecker(), structureId, ActionKeys.VIEW);

		return ddmStructureVersionLocalService.getLatestStructureVersion(
			structureId);
	}

	@Override
	public DDMStructureVersion getStructureVersion(long structureVersionId)
		throws PortalException {

		DDMStructureVersion structureVersion =
			ddmStructureVersionLocalService.getStructureVersion(
				structureVersionId);

		DDMStructurePermission.check(
			getPermissionChecker(), structureVersion.getStructureId(),
			ActionKeys.VIEW);

		return structureVersion;
	}

	@Override
	public List<DDMStructureVersion> getStructureVersions(
			long structureId, int start, int end,
			OrderByComparator<DDMStructureVersion> orderByComparator)
		throws PortalException {

		DDMStructurePermission.check(
			getPermissionChecker(), structureId, ActionKeys.VIEW);

		return ddmStructureVersionLocalService.getStructureVersions(
			structureId, start, end, orderByComparator);
	}

	@Override
	public int getStructureVersionsCount(long structureId)
		throws PortalException {

		DDMStructurePermission.check(
			getPermissionChecker(), structureId, ActionKeys.VIEW);

		return ddmStructureVersionLocalService.getStructureVersionsCount(
			structureId);
	}

}