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

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.ContractEntryContentException;
import com.liferay.osb.NoSuchContractEntryException;
import com.liferay.osb.model.ContractEntry;
import com.liferay.osb.service.base.ContractEntryLocalServiceBaseImpl;
import com.liferay.osb.util.comparator.ContractEntryVersionComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Douglas Wong
 */
public class ContractEntryLocalServiceImpl
	extends ContractEntryLocalServiceBaseImpl {

	public ContractEntry addContractEntry(
			long userId, long classNameId, long classPK, int type,
			Map<Locale, String> contentMap, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(contentMap);

		int version = 1;

		try {
			ContractEntry latestContractEntry = getLatestContractEntry(
				classNameId, classPK, type);

			version = latestContractEntry.getVersion() + 1;
		}
		catch (NoSuchContractEntryException nscee) {
		}

		long contractEntryId = counterLocalService.increment();

		ContractEntry contractEntry = contractEntryPersistence.create(
			contractEntryId);

		contractEntry.setUserId(userId);
		contractEntry.setUserName(user.getFullName());
		contractEntry.setCreateDate(now);
		contractEntry.setClassNameId(classNameId);
		contractEntry.setClassPK(classPK);
		contractEntry.setType(type);
		contractEntry.setVersion(version);

		String contentDefaultLanguageId = ParamUtil.getString(
			serviceContext, "contentDefaultLanguageId");

		if (Validator.isNotNull(contentDefaultLanguageId)) {
			Locale locale = LocaleUtil.fromLanguageId(contentDefaultLanguageId);

			contractEntry.setContentMap(contentMap, locale);
		}
		else {
			contractEntry.setContentMap(contentMap);
		}

		contractEntryPersistence.update(contractEntry, false);

		return contractEntry;
	}

	public ContractEntry addContractEntry(
			long userId, String className, long classPK, int type,
			Map<Locale, String> contentMap, ServiceContext serviceContext)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return addContractEntry(
			userId, classNameId, classPK, type, contentMap, serviceContext);
	}

	public List<ContractEntry> getContractEntries(
			long classNameId, long classPK, int type, int start, int end)
		throws SystemException {

		return contractEntryPersistence.findByCN_CP_T(
			classNameId, classPK, type, start, end);
	}

	public int getContractEntriesCount(long classNameId, long classPK, int type)
		throws SystemException {

		return contractEntryPersistence.countByCN_CP_T(
			classNameId, classPK, type);
	}

	public ContractEntry getLatestContractEntry(
			long classNameId, long classPK, int type)
		throws PortalException, SystemException {

		return contractEntryPersistence.findByCN_CP_T_Last(
			classNameId, classPK, type, new ContractEntryVersionComparator());
	}

	public ContractEntry getLatestContractEntry(
			String className, long classPK, int type)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return getLatestContractEntry(classNameId, classPK, type);
	}

	public ContractEntry updateContractEntry(
			long userId, long contractEntryId, Map<Locale, String> contentMap,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		validate(contentMap);

		ContractEntry contractEntry = contractEntryPersistence.findByPrimaryKey(
			contractEntryId);

		contractEntry.setUserId(userId);
		contractEntry.setUserName(user.getFullName());

		String defaultLanguageId = ParamUtil.getString(
			serviceContext, "defaultLanguageId");

		if (Validator.isNotNull(defaultLanguageId)) {
			Locale locale = LocaleUtil.fromLanguageId(defaultLanguageId);

			contractEntry.setContentMap(contentMap, locale);
		}
		else {
			contractEntry.setContentMap(contentMap);
		}

		contractEntryPersistence.update(contractEntry, false);

		return contractEntry;
	}

	private void validate(Map<Locale, String> contentMap)
		throws PortalException {

		if ((contentMap == null) ||
			Validator.isNull(contentMap.get(LocaleUtil.US))) {

			throw new ContractEntryContentException();
		}
	}

}