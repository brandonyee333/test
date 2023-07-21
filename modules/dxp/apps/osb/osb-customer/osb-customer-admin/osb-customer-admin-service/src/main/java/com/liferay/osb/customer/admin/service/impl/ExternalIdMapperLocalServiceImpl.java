/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service.impl;

import com.liferay.osb.customer.admin.model.ExternalIdMapper;
import com.liferay.osb.customer.admin.service.base.ExternalIdMapperLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class ExternalIdMapperLocalServiceImpl
	extends ExternalIdMapperLocalServiceBaseImpl {

	public ExternalIdMapper addExternalIdMapper(
		long classNameId, long classPK, int type, String externalId) {

		Date now = new Date();

		long externalIdMapperId = counterLocalService.increment();

		ExternalIdMapper externalIdMapper = externalIdMapperPersistence.create(
			externalIdMapperId);

		externalIdMapper.setCreateDate(now);
		externalIdMapper.setModifiedDate(now);
		externalIdMapper.setClassNameId(classNameId);
		externalIdMapper.setClassPK(classPK);
		externalIdMapper.setType(type);
		externalIdMapper.setExternalId(externalId);

		return externalIdMapperPersistence.update(externalIdMapper);
	}

	public void deleteExternalIdMapper(
		long classNameId, long classPK, int type) {

		externalIdMapperPersistence.removeByC_C_T(classNameId, classPK, type);
	}

	public void deleteExternalIdMappers(long classNameId, long classPK) {
		externalIdMapperPersistence.removeByC_C(classNameId, classPK);
	}

	public List<ExternalIdMapper> getExternalIdMappers(
		long classNameId, int type, String externalId) {

		return externalIdMapperPersistence.findByC_T_EI(
			classNameId, type, externalId);
	}

	public List<ExternalIdMapper> getExternalIdMappers(
		long classNameId, long classPK, int type) {

		return externalIdMapperPersistence.findByC_C_T(
			classNameId, classPK, type);
	}

	public boolean hasExternalIdMappers(
		long classNameId, long classPK, int type) {

		int count = externalIdMapperPersistence.countByC_C_T(
			classNameId, classPK, type);

		if (count > 0) {
			return true;
		}

		return false;
	}

	public ExternalIdMapper updateExternalIdMapper(
			long externalIdMapperId, long classNameId, long classPK, int type,
			String externalId)
		throws PortalException {

		ExternalIdMapper externalIdMapper =
			externalIdMapperPersistence.findByPrimaryKey(externalIdMapperId);

		externalIdMapper.setModifiedDate(new Date());
		externalIdMapper.setClassNameId(classNameId);
		externalIdMapper.setClassPK(classPK);
		externalIdMapper.setType(type);
		externalIdMapper.setExternalId(externalId);

		return externalIdMapperPersistence.update(externalIdMapper);
	}

}