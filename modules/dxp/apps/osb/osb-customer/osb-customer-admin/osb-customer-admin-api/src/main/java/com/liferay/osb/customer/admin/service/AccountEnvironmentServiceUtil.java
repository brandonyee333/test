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

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountEnvironmentServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountEnvironmentServiceUtil} to access the account environment remote service. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountEnvironmentServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static com.liferay.osb.customer.admin.model.AccountEnvironment
			addAccountEnvironment(
				long accountEntryId, long productEntryId, String name,
				int envOS, String envOSCustom, int envDB, int envJVM, int envAS,
				int envLFR, int envCommerce, int envBrowser, int envCS,
				String envSearch,
				java.util.List
					<com.liferay.portal.kernel.util.ObjectValuePair
						<String, java.io.File>> files,
				java.util.List<Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addAccountEnvironment(
			accountEntryId, productEntryId, name, envOS, envOSCustom, envDB,
			envJVM, envAS, envLFR, envCommerce, envBrowser, envCS, envSearch,
			files, types);
	}

	public static com.liferay.osb.customer.admin.model.AccountEnvironment
			deleteAccountEnvironment(long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteAccountEnvironment(accountEnvironmentId);
	}

	public static com.liferay.osb.customer.admin.model.AccountEnvironment
			getAccountEnvironment(long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountEnvironment(accountEnvironmentId);
	}

	public static java.util.List
		<com.liferay.osb.customer.admin.model.AccountEnvironment>
				getAccountEnvironments(long accountEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountEnvironments(accountEntryId);
	}

	public static java.util.Map
		<String,
		 java.util.List
			 <com.liferay.osb.customer.admin.model.AccountEnvironment>>
					getAccountEnvironmentsMap(long accountEntryId)
				throws com.liferay.portal.kernel.exception.PortalException {

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

	public static com.liferay.osb.customer.admin.model.AccountEnvironment
			updateAccountEnvironment(
				long accountEnvironmentId, long productEntryId, String name,
				int envOS, String envOSCustom, int envDB, int envJVM, int envAS,
				int envLFR, int envCommerce, int envBrowser, int envCS,
				String envSearch,
				java.util.List
					<com.liferay.portal.kernel.util.ObjectValuePair
						<String, java.io.File>> files,
				java.util.List<Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateAccountEnvironment(
			accountEnvironmentId, productEntryId, name, envOS, envOSCustom,
			envDB, envJVM, envAS, envLFR, envCommerce, envBrowser, envCS,
			envSearch, files, types);
	}

	public static AccountEnvironmentService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<AccountEnvironmentService, AccountEnvironmentService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			AccountEnvironmentService.class);

		ServiceTracker<AccountEnvironmentService, AccountEnvironmentService>
			serviceTracker =
				new ServiceTracker
					<AccountEnvironmentService, AccountEnvironmentService>(
						bundle.getBundleContext(),
						AccountEnvironmentService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}