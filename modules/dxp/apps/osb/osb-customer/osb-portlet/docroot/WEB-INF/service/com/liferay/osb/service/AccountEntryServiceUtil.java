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

package com.liferay.osb.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for AccountEntry. This utility wraps
 * {@link com.liferay.osb.service.impl.AccountEntryServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryService
 * @see com.liferay.osb.service.base.AccountEntryServiceBaseImpl
 * @see com.liferay.osb.service.impl.AccountEntryServiceImpl
 * @generated
 */
@ProviderType
public class AccountEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AccountEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.model.AccountEntry deleteAccountEntry(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAccountEntry(accountEntryId);
	}

	public static com.liferay.osb.model.AccountEntry fetchCorpProjectAccountEntry(
		java.lang.String corpProjectUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchCorpProjectAccountEntry(corpProjectUuid);
	}

	public static com.liferay.osb.model.AccountEntry getAccountEntry(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAccountEntry(accountEntryId);
	}

	public static com.liferay.osb.model.AccountEntry getAccountEntryByCode(
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAccountEntryByCode(code);
	}

	public static com.liferay.osb.model.AccountEntry getCorpProjectAccountEntry(
		java.lang.String corpProjectUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCorpProjectAccountEntry(corpProjectUuid);
	}

	public static com.liferay.osb.model.AccountEntry updateInstructions(
		long accountEntryId, java.lang.String instructions)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateInstructions(accountEntryId, instructions);
	}

	public static int searchCount(java.lang.Long createUserId,
		int createDateGTDay, int createDateGTMonth, int createDateGTYear,
		int createDateLTDay, int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear,
		java.lang.String dossieraAccountKey, java.lang.String corpEntryName,
		java.lang.String name, java.lang.String code, int[] industries,
		java.lang.Boolean partnerManagedSupport, int[] tiers, int[] statuses,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String partnerEntryCode, java.lang.String street,
		java.lang.Long countryId, java.lang.Long regionId,
		java.lang.String city, java.lang.String zip,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCount(createUserId, createDateGTDay,
			createDateGTMonth, createDateGTYear, createDateLTDay,
			createDateLTMonth, createDateLTYear, modifiedUserId,
			modifiedDateGTDay, modifiedDateGTMonth, modifiedDateGTYear,
			modifiedDateLTDay, modifiedDateLTMonth, modifiedDateLTYear,
			dossieraAccountKey, corpEntryName, name, code, industries,
			partnerManagedSupport, tiers, statuses, instructions, notes,
			partnerEntryCode, street, countryId, regionId, city, zip, params,
			andOperator);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		java.lang.String userUuid, long[] productEntryIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAccountEntries(userUuid, productEntryIds);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> search(
		java.lang.Long createUserId, int createDateGTDay,
		int createDateGTMonth, int createDateGTYear, int createDateLTDay,
		int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear,
		java.lang.String dossieraAccountKey, java.lang.String corpEntryName,
		java.lang.String name, java.lang.String code, int[] industries,
		java.lang.Boolean partnerManagedSupport, int[] tiers, int[] statuses,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String partnerEntryCode, java.lang.String street,
		java.lang.Long countryId, java.lang.Long regionId,
		java.lang.String city, java.lang.String zip,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .search(createUserId, createDateGTDay, createDateGTMonth,
			createDateGTYear, createDateLTDay, createDateLTMonth,
			createDateLTYear, modifiedUserId, modifiedDateGTDay,
			modifiedDateGTMonth, modifiedDateGTYear, modifiedDateLTDay,
			modifiedDateLTMonth, modifiedDateLTYear, dossieraAccountKey,
			corpEntryName, name, code, industries, partnerManagedSupport,
			tiers, statuses, instructions, notes, partnerEntryCode, street,
			countryId, regionId, city, zip, params, andOperator, start, end, obc);
	}

	public static void clearService() {
		_service = null;
	}

	public static AccountEntryService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AccountEntryService.class.getName());

			if (invokableService instanceof AccountEntryService) {
				_service = (AccountEntryService)invokableService;
			}
			else {
				_service = new AccountEntryServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(AccountEntryServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static AccountEntryService _service;
}