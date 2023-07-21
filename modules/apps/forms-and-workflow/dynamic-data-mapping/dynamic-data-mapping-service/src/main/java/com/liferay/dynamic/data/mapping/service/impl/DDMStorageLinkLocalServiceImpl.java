/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service.impl;

import com.liferay.dynamic.data.mapping.model.DDMStorageLink;
import com.liferay.dynamic.data.mapping.service.base.DDMStorageLinkLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 */
public class DDMStorageLinkLocalServiceImpl
	extends DDMStorageLinkLocalServiceBaseImpl {

	@Override
	public DDMStorageLink addStorageLink(
		long classNameId, long classPK, long structureId,
		ServiceContext serviceContext) {

		long storageLinkId = counterLocalService.increment();

		DDMStorageLink storageLink = ddmStorageLinkPersistence.create(
			storageLinkId);

		storageLink.setClassNameId(classNameId);
		storageLink.setClassPK(classPK);
		storageLink.setStructureId(structureId);

		return ddmStorageLinkPersistence.update(storageLink);
	}

	@Override
	public void deleteClassStorageLink(long classPK) {
		DDMStorageLink storageLink = ddmStorageLinkPersistence.fetchByClassPK(
			classPK);

		if (storageLink != null) {
			deleteStorageLink(storageLink);
		}
	}

	@Override
	public void deleteStorageLink(DDMStorageLink storageLink) {
		ddmStorageLinkPersistence.remove(storageLink);
	}

	@Override
	public void deleteStorageLink(long storageLinkId) {
		DDMStorageLink storageLink =
			ddmStorageLinkPersistence.fetchByPrimaryKey(storageLinkId);

		if (storageLink != null) {
			deleteStorageLink(storageLink);
		}
	}

	@Override
	public void deleteStructureStorageLinks(long structureId) {
		List<DDMStorageLink> storageLinks =
			ddmStorageLinkPersistence.findByStructureId(structureId);

		for (DDMStorageLink storageLink : storageLinks) {
			deleteStorageLink(storageLink);
		}
	}

	@Override
	public DDMStorageLink getClassStorageLink(long classPK)
		throws PortalException {

		return ddmStorageLinkPersistence.findByClassPK(classPK);
	}

	@Override
	public DDMStorageLink getStorageLink(long storageLinkId)
		throws PortalException {

		return ddmStorageLinkPersistence.findByPrimaryKey(storageLinkId);
	}

	@Override
	public List<DDMStorageLink> getStructureStorageLinks(long structureId) {
		return ddmStorageLinkPersistence.findByStructureId(structureId);
	}

	@Override
	public int getStructureStorageLinksCount(long structureId) {
		return ddmStorageLinkPersistence.countByStructureId(structureId);
	}

	@Override
	public DDMStorageLink updateStorageLink(
			long storageLinkId, long classNameId, long classPK)
		throws PortalException {

		DDMStorageLink storageLink = ddmStorageLinkPersistence.findByPrimaryKey(
			storageLinkId);

		storageLink.setClassNameId(classNameId);
		storageLink.setClassPK(classPK);

		return ddmStorageLinkPersistence.update(storageLink);
	}

}