/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.service.impl;

import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.service.base.ExternalIdMapperLocalServiceBaseImpl;
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