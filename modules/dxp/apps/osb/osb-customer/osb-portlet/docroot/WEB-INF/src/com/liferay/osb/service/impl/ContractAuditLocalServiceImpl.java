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

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.ContractAudit;
import com.liferay.osb.model.ContractEntry;
import com.liferay.osb.service.base.ContractAuditLocalServiceBaseImpl;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Douglas Wong
 */
public class ContractAuditLocalServiceImpl
	extends ContractAuditLocalServiceBaseImpl {

	public void addContractAudit(
			long userId, long contractEntryId, String signatoryClassName,
			long signatoryClassPK, String productClassName, long productClassPK)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		ContractEntry contractEntry = contractEntryPersistence.findByPrimaryKey(
			contractEntryId);
		long signatoryClassNameId = PortalUtil.getClassNameId(
			signatoryClassName);
		long productClassNameId = PortalUtil.getClassNameId(productClassName);
		Date now = new Date();

		long contractAuditId = counterLocalService.increment();

		ContractAudit contractAudit = contractAuditPersistence.create(
			contractAuditId);

		contractAudit.setUserId(userId);
		contractAudit.setUserName(user.getFullName());
		contractAudit.setUserEmailAddress(user.getEmailAddress());
		contractAudit.setCreateDate(now);
		contractAudit.setModifiedDate(now);
		contractAudit.setContractEntryId(contractEntryId);
		contractAudit.setSignatoryClassNameId(signatoryClassNameId);
		contractAudit.setSignatoryClassPK(signatoryClassPK);
		contractAudit.setProductClassNameId(productClassNameId);
		contractAudit.setProductClassPK(productClassPK);
		contractAudit.setType(contractEntry.getTypeLabel());

		if (!PortletPropsValues.CONTRACT_ENTRY_LOCALIZED_ENABLED) {
			contractAudit.setLanguageId("en_US");
		}

		contractAudit.setVersion(contractEntry.getVersion());

		contractAuditPersistence.update(contractAudit, false);
	}

	public int getContractAuditCount(long userId, long contractEntryId)
		throws SystemException {

		return contractAuditPersistence.countByU_CEI(userId, contractEntryId);
	}

	public int getContractAuditCount(
			long contractEntryId, String signatoryClassName,
			long signatoryClassPK)
		throws SystemException {

		long signatoryClassNameId = PortalUtil.getClassNameId(
			signatoryClassName);

		return contractAuditPersistence.countByCEI_SCN_SCP(
			contractEntryId, signatoryClassNameId, signatoryClassPK);
	}

	public boolean hasContractAudit(long userId, long contractEntryId)
		throws SystemException {

		int count = contractAuditPersistence.countByU_CEI(
			userId, contractEntryId);

		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasContractAudit(
			long classNameId, long classPK, int type, String signatoryClassName,
			long signatoryClassPK)
		throws PortalException, SystemException {

		List<ContractEntry> contractEntries =
			contractEntryLocalService.getContractEntries(
				classNameId, classPK, type, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (ContractEntry contractEntry : contractEntries) {
			if (hasContractAudit(
					contractEntry.getContractEntryId(), signatoryClassName,
					signatoryClassPK)) {

				return true;
			}
		}

		return false;
	}

	public boolean hasContractAudit(
			long contractEntryId, String signatoryClassName,
			long signatoryClassPK)
		throws SystemException {

		int count = getContractAuditCount(
			contractEntryId, signatoryClassName, signatoryClassPK);

		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasLatestContractAudit(
			long classNameId, long classPK, int type, String signatoryClassName,
			long signatoryClassPK)
		throws PortalException, SystemException {

		ContractEntry contractEntry =
			contractEntryLocalService.getLatestContractEntry(
				classNameId, classPK, type);

		return hasContractAudit(
			contractEntry.getContractEntryId(), signatoryClassName,
			signatoryClassPK);
	}

}