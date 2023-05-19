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

import com.liferay.osb.customer.admin.model.AccountEnvironment;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

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

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountEnvironmentLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the account environment to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountEnvironmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountEnvironment the account environment
	 * @return the account environment that was added
	 */
	public static AccountEnvironment addAccountEnvironment(
		AccountEnvironment accountEnvironment) {

		return getService().addAccountEnvironment(accountEnvironment);
	}

	public static AccountEnvironment addAccountEnvironment(
			long userId, long accountEntryId, long productEntryId, String name,
			int envOS, String envOSCustom, int envDB, int envJVM, int envAS,
			int envLFR, int envCommerce, int envBrowser, int envCS,
			String envSearch)
		throws PortalException {

		return getService().addAccountEnvironment(
			userId, accountEntryId, productEntryId, name, envOS, envOSCustom,
			envDB, envJVM, envAS, envLFR, envCommerce, envBrowser, envCS,
			envSearch);
	}

	/**
	 * Creates a new account environment with the primary key. Does not add the account environment to the database.
	 *
	 * @param accountEnvironmentId the primary key for the new account environment
	 * @return the new account environment
	 */
	public static AccountEnvironment createAccountEnvironment(
		long accountEnvironmentId) {

		return getService().createAccountEnvironment(accountEnvironmentId);
	}

	/**
	 * Deletes the account environment from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountEnvironmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountEnvironment the account environment
	 * @return the account environment that was removed
	 */
	public static AccountEnvironment deleteAccountEnvironment(
		AccountEnvironment accountEnvironment) {

		return getService().deleteAccountEnvironment(accountEnvironment);
	}

	/**
	 * Deletes the account environment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountEnvironmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountEnvironmentId the primary key of the account environment
	 * @return the account environment that was removed
	 * @throws PortalException if a account environment with the primary key could not be found
	 */
	public static AccountEnvironment deleteAccountEnvironment(
			long accountEnvironmentId)
		throws PortalException {

		return getService().deleteAccountEnvironment(accountEnvironmentId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
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
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static AccountEnvironment fetchAccountEnvironment(
		long accountEnvironmentId) {

		return getService().fetchAccountEnvironment(accountEnvironmentId);
	}

	public static AccountEnvironment fetchAccountEnvironment(
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
	public static AccountEnvironment getAccountEnvironment(
			long accountEnvironmentId)
		throws PortalException {

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
	public static List<AccountEnvironment> getAccountEnvironments(
		int start, int end) {

		return getService().getAccountEnvironments(start, end);
	}

	public static List<AccountEnvironment> getAccountEnvironments(
			long accountEntryId)
		throws PortalException {

		return getService().getAccountEnvironments(accountEntryId);
	}

	public static List<AccountEnvironment> getAccountEnvironments(
		long accountEntryId, long productEntryId) {

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

	public static Map<String, List<AccountEnvironment>>
			getAccountEnvironmentsMap(long accountEntryId)
		throws PortalException {

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

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the account environment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountEnvironmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountEnvironment the account environment
	 * @return the account environment that was updated
	 */
	public static AccountEnvironment updateAccountEnvironment(
		AccountEnvironment accountEnvironment) {

		return getService().updateAccountEnvironment(accountEnvironment);
	}

	public static AccountEnvironment updateAccountEnvironment(
			long userId, long accountEnvironmentId, long productEntryId,
			String name, int envOS, String envOSCustom, int envDB, int envJVM,
			int envAS, int envLFR, int envCommerce, int envBrowser, int envCS,
			String envSearch)
		throws PortalException {

		return getService().updateAccountEnvironment(
			userId, accountEnvironmentId, productEntryId, name, envOS,
			envOSCustom, envDB, envJVM, envAS, envLFR, envCommerce, envBrowser,
			envCS, envSearch);
	}

	public static AccountEnvironmentLocalService getService() {
		return _service;
	}

	public static void setService(AccountEnvironmentLocalService service) {
		_service = service;
	}

	private static volatile AccountEnvironmentLocalService _service;

}