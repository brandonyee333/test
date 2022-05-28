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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountEnvironmentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentLocalService
 * @generated
 */
public class AccountEnvironmentLocalServiceWrapper
	implements AccountEnvironmentLocalService,
			   ServiceWrapper<AccountEnvironmentLocalService> {

	public AccountEnvironmentLocalServiceWrapper(
		AccountEnvironmentLocalService accountEnvironmentLocalService) {

		_accountEnvironmentLocalService = accountEnvironmentLocalService;
	}

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
	@Override
	public com.liferay.osb.customer.admin.model.AccountEnvironment
		addAccountEnvironment(
			com.liferay.osb.customer.admin.model.AccountEnvironment
				accountEnvironment) {

		return _accountEnvironmentLocalService.addAccountEnvironment(
			accountEnvironment);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEnvironment
			addAccountEnvironment(
				long userId, long accountEntryId, long productEntryId,
				String name, int envOS, String envOSCustom, int envDB,
				int envJVM, int envAS, int envLFR, int envCommerce,
				int envBrowser, int envCS, String envSearch)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEnvironmentLocalService.addAccountEnvironment(
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
	@Override
	public com.liferay.osb.customer.admin.model.AccountEnvironment
		createAccountEnvironment(long accountEnvironmentId) {

		return _accountEnvironmentLocalService.createAccountEnvironment(
			accountEnvironmentId);
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
	@Override
	public com.liferay.osb.customer.admin.model.AccountEnvironment
		deleteAccountEnvironment(
			com.liferay.osb.customer.admin.model.AccountEnvironment
				accountEnvironment) {

		return _accountEnvironmentLocalService.deleteAccountEnvironment(
			accountEnvironment);
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
	@Override
	public com.liferay.osb.customer.admin.model.AccountEnvironment
			deleteAccountEnvironment(long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEnvironmentLocalService.deleteAccountEnvironment(
			accountEnvironmentId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEnvironmentLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountEnvironmentLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _accountEnvironmentLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _accountEnvironmentLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _accountEnvironmentLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _accountEnvironmentLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _accountEnvironmentLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEnvironment
		fetchAccountEnvironment(long accountEnvironmentId) {

		return _accountEnvironmentLocalService.fetchAccountEnvironment(
			accountEnvironmentId);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEnvironment
		fetchAccountEnvironment(
			long accountEntryId, long productEntryId, String name) {

		return _accountEnvironmentLocalService.fetchAccountEnvironment(
			accountEntryId, productEntryId, name);
	}

	/**
	 * Returns the account environment with the primary key.
	 *
	 * @param accountEnvironmentId the primary key of the account environment
	 * @return the account environment
	 * @throws PortalException if a account environment with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.admin.model.AccountEnvironment
			getAccountEnvironment(long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEnvironmentLocalService.getAccountEnvironment(
			accountEnvironmentId);
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
	@Override
	public java.util.List
		<com.liferay.osb.customer.admin.model.AccountEnvironment>
			getAccountEnvironments(int start, int end) {

		return _accountEnvironmentLocalService.getAccountEnvironments(
			start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.customer.admin.model.AccountEnvironment>
				getAccountEnvironments(long accountEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEnvironmentLocalService.getAccountEnvironments(
			accountEntryId);
	}

	@Override
	public java.util.List
		<com.liferay.osb.customer.admin.model.AccountEnvironment>
			getAccountEnvironments(long accountEntryId, long productEntryId) {

		return _accountEnvironmentLocalService.getAccountEnvironments(
			accountEntryId, productEntryId);
	}

	/**
	 * Returns the number of account environments.
	 *
	 * @return the number of account environments
	 */
	@Override
	public int getAccountEnvironmentsCount() {
		return _accountEnvironmentLocalService.getAccountEnvironmentsCount();
	}

	@Override
	public java.util.Map
		<String,
		 java.util.List
			 <com.liferay.osb.customer.admin.model.AccountEnvironment>>
					getAccountEnvironmentsMap(long accountEntryId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEnvironmentLocalService.getAccountEnvironmentsMap(
			accountEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _accountEnvironmentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _accountEnvironmentLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountEnvironmentLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEnvironmentLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public com.liferay.osb.customer.admin.model.AccountEnvironment
		updateAccountEnvironment(
			com.liferay.osb.customer.admin.model.AccountEnvironment
				accountEnvironment) {

		return _accountEnvironmentLocalService.updateAccountEnvironment(
			accountEnvironment);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEnvironment
			updateAccountEnvironment(
				long userId, long accountEnvironmentId, long productEntryId,
				String name, int envOS, String envOSCustom, int envDB,
				int envJVM, int envAS, int envLFR, int envCommerce,
				int envBrowser, int envCS, String envSearch)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEnvironmentLocalService.updateAccountEnvironment(
			userId, accountEnvironmentId, productEntryId, name, envOS,
			envOSCustom, envDB, envJVM, envAS, envLFR, envCommerce, envBrowser,
			envCS, envSearch);
	}

	@Override
	public AccountEnvironmentLocalService getWrappedService() {
		return _accountEnvironmentLocalService;
	}

	@Override
	public void setWrappedService(
		AccountEnvironmentLocalService accountEnvironmentLocalService) {

		_accountEnvironmentLocalService = accountEnvironmentLocalService;
	}

	private AccountEnvironmentLocalService _accountEnvironmentLocalService;

}