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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class ExternalIdMapperLocalServiceImpl
	extends ExternalIdMapperLocalServiceBaseImpl {

	public ExternalIdMapper addExternalIdMapper(
			long classNameId, long classPK, int type, String externalId)
		throws SystemException {

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
		
		//TODO implement serviceContext how needed
		
		ServiceContext serviceContext = new ServiceContext();

		externalIdMapperPersistence.update(externalIdMapper, serviceContext);

		return externalIdMapper;
	}

	public void deleteExternalIdMapper(long classNameId, long classPK, int type)
		throws SystemException {

		externalIdMapperPersistence.removeByC_C_T(classNameId, classPK, type);
	}

	public List<ExternalIdMapper> getExternalIdMappers(
			long classNameId, int type, String externalId)
		throws SystemException {

		return externalIdMapperPersistence.findByC_T_EI(
			classNameId, type, externalId);
	}

	public List<ExternalIdMapper> getExternalIdMappers(
			long classNameId, long classPK, int type)
		throws SystemException {

		return externalIdMapperPersistence.findByC_C_T(
			classNameId, classPK, type);
	}

	public ExternalIdMapper updateExternalIdMapper(
			long externalIdMapperId, long classNameId, long classPK, int type,
			String externalId)
		throws PortalException, SystemException {

		ExternalIdMapper externalIdMapper =
			externalIdMapperPersistence.findByPrimaryKey(externalIdMapperId);

		externalIdMapper.setModifiedDate(new Date());
		externalIdMapper.setClassNameId(classNameId);
		externalIdMapper.setClassPK(classPK);
		externalIdMapper.setType(type);
		externalIdMapper.setExternalId(externalId);
		
		//TODO implement serviceContext how needed
		
		ServiceContext serviceContext = new ServiceContext();

		externalIdMapperPersistence.update(externalIdMapper, serviceContext);

		return externalIdMapper;
	}

}