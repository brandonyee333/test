/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for AccountEntry. This utility wraps
 * <code>com.liferay.osb.customer.admin.service.impl.AccountEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryService
 * @generated
 */
public class AccountEntryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static AccountEntry deleteAccountEntry(long accountEntryId)
		throws PortalException {

		return getService().deleteAccountEntry(accountEntryId);
	}

	public static AccountEntry fetchCorpProjectAccountEntry(
			String corpProjectUuid)
		throws Exception {

		return getService().fetchCorpProjectAccountEntry(corpProjectUuid);
	}

	public static AccountEntry fetchKoroneikiAccountEntry(
			String koroneikiAccountKey)
		throws PortalException {

		return getService().fetchKoroneikiAccountEntry(koroneikiAccountKey);
	}

	public static List<AccountEntry> getAccountEntries(
			String userUuid, long[] productEntryIds)
		throws Exception {

		return getService().getAccountEntries(userUuid, productEntryIds);
	}

	public static AccountEntry getAccountEntry(long accountEntryId)
		throws PortalException {

		return getService().getAccountEntry(accountEntryId);
	}

	public static AccountEntry getAccountEntryByCode(String code)
		throws PortalException {

		return getService().getAccountEntryByCode(code);
	}

	public static AccountEntry getCorpProjectAccountEntry(
			String corpProjectUuid)
		throws Exception {

		return getService().getCorpProjectAccountEntry(corpProjectUuid);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void syncToZendesk(String koroneikiAccountKey)
		throws Exception {

		getService().syncToZendesk(koroneikiAccountKey);
	}

	public static AccountEntry updateInstructions(
			long accountEntryId, String instructions)
		throws PortalException {

		return getService().updateInstructions(accountEntryId, instructions);
	}

	public static AccountEntry updateInstructions(
			String koroneikiAccountKey, String instructions)
		throws PortalException {

		return getService().updateInstructions(
			koroneikiAccountKey, instructions);
	}

	public static AccountEntry updateLanguageId(
			String koroneikiAccountKey, String languageId)
		throws PortalException {

		return getService().updateLanguageId(koroneikiAccountKey, languageId);
	}

	public static AccountEntryService getService() {
		return _service;
	}

	public static void setService(AccountEntryService service) {
		_service = service;
	}

	private static volatile AccountEntryService _service;

}