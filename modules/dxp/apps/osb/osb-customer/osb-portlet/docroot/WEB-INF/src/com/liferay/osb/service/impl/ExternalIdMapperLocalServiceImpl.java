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