/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service;

import com.liferay.osb.customer.admin.model.AccountEnvironment;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service utility for AccountEnvironment. This utility wraps
 * <code>com.liferay.osb.customer.admin.service.impl.AccountEnvironmentServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentService
 * @generated
 */
public class AccountEnvironmentServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountEnvironmentServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static AccountEnvironment addAccountEnvironment(
			long accountEntryId, long productEntryId, String name, int envOS,
			String envOSCustom, int envDB, int envJVM, int envAS, int envLFR,
			int envCommerce, int envBrowser, int envCS, String envSearch)
		throws PortalException {

		return getService().addAccountEnvironment(
			accountEntryId, productEntryId, name, envOS, envOSCustom, envDB,
			envJVM, envAS, envLFR, envCommerce, envBrowser, envCS, envSearch);
	}

	public static AccountEnvironment deleteAccountEnvironment(
			long accountEnvironmentId)
		throws PortalException {

		return getService().deleteAccountEnvironment(accountEnvironmentId);
	}

	public static AccountEnvironment getAccountEnvironment(
			long accountEnvironmentId)
		throws PortalException {

		return getService().getAccountEnvironment(accountEnvironmentId);
	}

	public static List<AccountEnvironment> getAccountEnvironments(
			long accountEntryId)
		throws PortalException {

		return getService().getAccountEnvironments(accountEntryId);
	}

	public static Map<String, List<AccountEnvironment>>
			getAccountEnvironmentsMap(long accountEntryId)
		throws PortalException {

		return getService().getAccountEnvironmentsMap(accountEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static AccountEnvironment updateAccountEnvironment(
			long accountEnvironmentId, long productEntryId, String name,
			int envOS, String envOSCustom, int envDB, int envJVM, int envAS,
			int envLFR, int envCommerce, int envBrowser, int envCS,
			String envSearch)
		throws PortalException {

		return getService().updateAccountEnvironment(
			accountEnvironmentId, productEntryId, name, envOS, envOSCustom,
			envDB, envJVM, envAS, envLFR, envCommerce, envBrowser, envCS,
			envSearch);
	}

	public static AccountEnvironmentService getService() {
		return _service;
	}

	public static void setService(AccountEnvironmentService service) {
		_service = service;
	}

	private static volatile AccountEnvironmentService _service;

}