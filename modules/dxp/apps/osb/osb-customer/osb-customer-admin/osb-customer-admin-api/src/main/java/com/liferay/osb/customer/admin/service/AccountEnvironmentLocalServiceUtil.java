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
 * Provides the local service utility for AccountEnvironment. This utility wraps
 * <code>com.liferay.osb.customer.admin.service.impl.AccountEnvironmentLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentLocalService
 * @generated
 */
public class AccountEnvironmentLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountEnvironmentLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the account environment to the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEnvironment the account environment
	 * @return the account environment that was added
	 */
	public static com.liferay.osb.customer.admin.model.AccountEnvironment
		addAccountEnvironment(
			com.liferay.osb.customer.admin.model.AccountEnvironment
				accountEnvironment) {

		return getService().addAccountEnvironment(accountEnvironment);
	}

	public static com.liferay.osb.customer.admin.model.AccountEnvironment
			addAccountEnvironment(
				long userId, long accountEntryId, long productEntryId,
				String name, int envOS, String envOSCustom, int envDB,
				int envJVM, int envAS, int envLFR, int envCommerce,
				int envBrowser, int envCS, String envSearch,
				java.util.List
					<com.liferay.portal.kernel.util.ObjectValuePair
						<String, java.io.File>> files,
				java.util.List<Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addAccountEnvironment(
			userId, accountEntryId, productEntryId, name, envOS, envOSCustom,
			envDB, envJVM, envAS, envLFR, envCommerce, envBrowser, envCS,
			envSearch, files, types);
	}

	/**
	 * Creates a new account environment with the primary key. Does not add the account environment to the database.
	 *
	 * @param accountEnvironmentId the primary key for the new account environment
	 * @return the new account environment
	 */
	public static com.liferay.osb.customer.admin.model.AccountEnvironment
		createAccountEnvironment(long accountEnvironmentId) {

		return getService().createAccountEnvironment(accountEnvironmentId);
	}

	/**
	 * Deletes the account environment from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEnvironment the account environment
	 * @return the account environment that was removed
	 */
	public static com.liferay.osb.customer.admin.model.AccountEnvironment
		deleteAccountEnvironment(
			com.liferay.osb.customer.admin.model.AccountEnvironment
				accountEnvironment) {

		return getService().deleteAccountEnvironment(accountEnvironment);
	}

	/**
	 * Deletes the account environment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEnvironmentId the primary key of the account environment
	 * @return the account environment that was removed
	 * @throws PortalException if a account environment with the primary key could not be found
	 */
	public static com.liferay.osb.customer.admin.model.AccountEnvironment
			deleteAccountEnvironment(long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteAccountEnvironment(accountEnvironmentId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountEnvironmentModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountEnvironmentModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.osb.customer.admin.model.AccountEnvironment
		fetchAccountEnvironment(long accountEnvironmentId) {

		return getService().fetchAccountEnvironment(accountEnvironmentId);
	}

	public static com.liferay.osb.customer.admin.model.AccountEnvironment
		fetchAccountEnvironment(
			long accountEntryId, long productEntryId, String name) {

		return getService().fetchAccountEnvironment(
			accountEntryId, productEntryId, name);
	}

	/**
	 * Returns the account environment with the primary key.
	 *
	 * @param accountEnvironmentId the primary key of the account environment
	 * @return the account environment
	 * @throws PortalException if a account environment with the primary key could not be found
	 */
	public static com.liferay.osb.customer.admin.model.AccountEnvironment
			getAccountEnvironment(long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountEnvironment(accountEnvironmentId);
	}

	/**
	 * Returns a range of all the account environments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountEnvironmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @return the range of account environments
	 */
	public static java.util.List
		<com.liferay.osb.customer.admin.model.AccountEnvironment>
			getAccountEnvironments(int start, int end) {

		return getService().getAccountEnvironments(start, end);
	}

	public static java.util.List
		<com.liferay.osb.customer.admin.model.AccountEnvironment>
				getAccountEnvironments(long accountEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountEnvironments(accountEntryId);
	}

	public static java.util.List
		<com.liferay.osb.customer.admin.model.AccountEnvironment>
			getAccountEnvironments(long accountEntryId, long productEntryId) {

		return getService().getAccountEnvironments(
			accountEntryId, productEntryId);
	}

	/**
	 * Returns the number of account environments.
	 *
	 * @return the number of account environments
	 */
	public static int getAccountEnvironmentsCount() {
		return getService().getAccountEnvironmentsCount();
	}

	public static java.util.Map
		<String,
		 java.util.List
			 <com.liferay.osb.customer.admin.model.AccountEnvironment>>
					getAccountEnvironmentsMap(long accountEntryId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountEnvironmentsMap(accountEntryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the account environment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param accountEnvironment the account environment
	 * @return the account environment that was updated
	 */
	public static com.liferay.osb.customer.admin.model.AccountEnvironment
		updateAccountEnvironment(
			com.liferay.osb.customer.admin.model.AccountEnvironment
				accountEnvironment) {

		return getService().updateAccountEnvironment(accountEnvironment);
	}

	public static com.liferay.osb.customer.admin.model.AccountEnvironment
			updateAccountEnvironment(
				long userId, long accountEnvironmentId, long productEntryId,
				String name, int envOS, String envOSCustom, int envDB,
				int envJVM, int envAS, int envLFR, int envCommerce,
				int envBrowser, int envCS, String envSearch,
				java.util.List
					<com.liferay.portal.kernel.util.ObjectValuePair
						<String, java.io.File>> files,
				java.util.List<Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateAccountEnvironment(
			userId, accountEnvironmentId, productEntryId, name, envOS,
			envOSCustom, envDB, envJVM, envAS, envLFR, envCommerce, envBrowser,
			envCS, envSearch, files, types);
	}

	public static AccountEnvironmentLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<AccountEnvironmentLocalService, AccountEnvironmentLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			AccountEnvironmentLocalService.class);

		ServiceTracker
			<AccountEnvironmentLocalService, AccountEnvironmentLocalService>
				serviceTracker =
					new ServiceTracker
						<AccountEnvironmentLocalService,
						 AccountEnvironmentLocalService>(
							 bundle.getBundleContext(),
							 AccountEnvironmentLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}