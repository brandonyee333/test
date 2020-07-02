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

package com.liferay.osb.customer.admin.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

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
	public static com.liferay.osb.customer.admin.model.AccountEntry
			deleteAccountEntry(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteAccountEntry(accountEntryId);
	}

	public static com.liferay.osb.customer.admin.model.AccountEntry
			fetchCorpProjectAccountEntry(String corpProjectUuid)
		throws Exception {

		return getService().fetchCorpProjectAccountEntry(corpProjectUuid);
	}

	public static com.liferay.osb.customer.admin.model.AccountEntry
			fetchKoroneikiAccountEntry(String koroneikiAccountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchKoroneikiAccountEntry(koroneikiAccountKey);
	}

	public static java.util.List
		<com.liferay.osb.customer.admin.model.AccountEntry> getAccountEntries(
				String userUuid, long[] productEntryIds)
			throws Exception {

		return getService().getAccountEntries(userUuid, productEntryIds);
	}

	public static com.liferay.osb.customer.admin.model.AccountEntry
			getAccountEntry(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountEntry(accountEntryId);
	}

	public static com.liferay.osb.customer.admin.model.AccountEntry
			getAccountEntryByCode(String code)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountEntryByCode(code);
	}

	public static com.liferay.osb.customer.admin.model.AccountEntry
			getCorpProjectAccountEntry(String corpProjectUuid)
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
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().syncToZendesk(koroneikiAccountKey);
	}

	public static com.liferay.osb.customer.admin.model.AccountEntry
			updateInstructions(long accountEntryId, String instructions)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateInstructions(accountEntryId, instructions);
	}

	public static com.liferay.osb.customer.admin.model.AccountEntry
			updateInstructions(String koroneikiAccountKey, String instructions)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateInstructions(
			koroneikiAccountKey, instructions);
	}

	public static com.liferay.osb.customer.admin.model.AccountEntry
			updateLanguageId(String koroneikiAccountKey, String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateLanguageId(koroneikiAccountKey, languageId);
	}

	public static AccountEntryService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AccountEntryService, AccountEntryService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AccountEntryService.class);

		ServiceTracker<AccountEntryService, AccountEntryService>
			serviceTracker =
				new ServiceTracker<AccountEntryService, AccountEntryService>(
					bundle.getBundleContext(), AccountEntryService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}