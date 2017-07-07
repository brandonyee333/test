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

import com.liferay.osb.model.AuditAction;
import com.liferay.osb.service.base.AuditActionLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class AuditActionLocalServiceImpl
	extends AuditActionLocalServiceBaseImpl {

	public void deleteAuditActions(Date modifiedDate) throws SystemException {
		auditActionPersistence.removeByLtModifiedDate(modifiedDate);
	}

	public List<AuditAction> getAuditActions(
			Date modifiedDate, long classNameId, boolean mapping, int action)
		throws SystemException {

		if (mapping) {
			return auditActionPersistence.findByGtMD_C_GtMC_A(
				modifiedDate, classNameId, 0, action);
		}
		else {
			return auditActionPersistence.findByGtMD_C_MC_A(
				modifiedDate, classNameId, 0, action);
		}
	}

	public AuditAction updateAuditAction(
			long classNameId, long classPK, long mappingClassPK, int action)
		throws SystemException {

		AuditAction auditAction = auditActionPersistence.fetchByC_C_MC(
			classNameId, classPK, mappingClassPK);

		if (auditAction == null) {
			long auditActionId = counterLocalService.increment();

			auditAction = auditActionPersistence.create(auditActionId);

			auditAction.setClassNameId(classNameId);
			auditAction.setClassPK(classPK);
			auditAction.setMappingClassPK(mappingClassPK);
		}

		auditAction.setModifiedDate(new Date());
		auditAction.setAction(action);
		
		//TODO implement serviceContext how needed
		
		ServiceContext serviceContext = new ServiceContext();

		auditActionPersistence.update(auditAction, serviceContext);

		return auditAction;
	}

}