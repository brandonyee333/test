/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryService
 * @generated
 */
public class AccountEntryServiceWrapper
	implements AccountEntryService, ServiceWrapper<AccountEntryService> {

	public AccountEntryServiceWrapper(AccountEntryService accountEntryService) {
		_accountEntryService = accountEntryService;
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry deleteAccountEntry(
			long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryService.deleteAccountEntry(accountEntryId);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry
			fetchCorpProjectAccountEntry(String corpProjectUuid)
		throws Exception {

		return _accountEntryService.fetchCorpProjectAccountEntry(
			corpProjectUuid);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry
			fetchKoroneikiAccountEntry(String koroneikiAccountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryService.fetchKoroneikiAccountEntry(
			koroneikiAccountKey);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
			getAccountEntries(String userUuid, long[] productEntryIds)
		throws Exception {

		return _accountEntryService.getAccountEntries(
			userUuid, productEntryIds);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry getAccountEntry(
			long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryService.getAccountEntry(accountEntryId);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry
			getAccountEntryByCode(String code)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryService.getAccountEntryByCode(code);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry
			getCorpProjectAccountEntry(String corpProjectUuid)
		throws Exception {

		return _accountEntryService.getCorpProjectAccountEntry(corpProjectUuid);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public void syncToZendesk(String koroneikiAccountKey) throws Exception {
		_accountEntryService.syncToZendesk(koroneikiAccountKey);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry updateInstructions(
			long accountEntryId, String instructions)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryService.updateInstructions(
			accountEntryId, instructions);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry updateInstructions(
			String koroneikiAccountKey, String instructions)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryService.updateInstructions(
			koroneikiAccountKey, instructions);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry updateLanguageId(
			String koroneikiAccountKey, String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryService.updateLanguageId(
			koroneikiAccountKey, languageId);
	}

	@Override
	public AccountEntryService getWrappedService() {
		return _accountEntryService;
	}

	@Override
	public void setWrappedService(AccountEntryService accountEntryService) {
		_accountEntryService = accountEntryService;
	}

	private AccountEntryService _accountEntryService;

}