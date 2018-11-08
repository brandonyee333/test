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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountWorkerLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountWorkerLocalService
 * @generated
 */
@ProviderType
public class AccountWorkerLocalServiceWrapper
	implements AccountWorkerLocalService,
		ServiceWrapper<AccountWorkerLocalService> {
	public AccountWorkerLocalServiceWrapper(
		AccountWorkerLocalService accountWorkerLocalService) {
		_accountWorkerLocalService = accountWorkerLocalService;
	}

	@Override
	public boolean hasAccountWorker(long userId, long accountEntryId) {
		return _accountWorkerLocalService.hasAccountWorker(userId,
			accountEntryId);
	}

	@Override
	public boolean hasAccountWorkerRole(long userId, int role) {
		return _accountWorkerLocalService.hasAccountWorkerRole(userId, role);
	}

	/**
	* Adds the account worker to the database. Also notifies the appropriate model listeners.
	*
	* @param accountWorker the account worker
	* @return the account worker that was added
	*/
	@Override
	public com.liferay.osb.model.AccountWorker addAccountWorker(
		com.liferay.osb.model.AccountWorker accountWorker) {
		return _accountWorkerLocalService.addAccountWorker(accountWorker);
	}

	@Override
	public com.liferay.osb.model.AccountWorker addAccountWorker(long userId,
		java.lang.String emailAddress, long accountEntryId, int role,
		int notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountWorkerLocalService.addAccountWorker(userId,
			emailAddress, accountEntryId, role, notifications);
	}

	@Override
	public com.liferay.osb.model.AccountWorker addAccountWorker(long userId,
		long workerUserId, long accountEntryId, int role, int notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountWorkerLocalService.addAccountWorker(userId,
			workerUserId, accountEntryId, role, notifications);
	}

	/**
	* Creates a new account worker with the primary key. Does not add the account worker to the database.
	*
	* @param accountWorkerId the primary key for the new account worker
	* @return the new account worker
	*/
	@Override
	public com.liferay.osb.model.AccountWorker createAccountWorker(
		long accountWorkerId) {
		return _accountWorkerLocalService.createAccountWorker(accountWorkerId);
	}

	/**
	* Deletes the account worker from the database. Also notifies the appropriate model listeners.
	*
	* @param accountWorker the account worker
	* @return the account worker that was removed
	*/
	@Override
	public com.liferay.osb.model.AccountWorker deleteAccountWorker(
		com.liferay.osb.model.AccountWorker accountWorker) {
		return _accountWorkerLocalService.deleteAccountWorker(accountWorker);
	}

	/**
	* Deletes the account worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountWorkerId the primary key of the account worker
	* @return the account worker that was removed
	* @throws PortalException if a account worker with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AccountWorker deleteAccountWorker(
		long accountWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountWorkerLocalService.deleteAccountWorker(accountWorkerId);
	}

	@Override
	public com.liferay.osb.model.AccountWorker fetchAccountWorker(
		long accountWorkerId) {
		return _accountWorkerLocalService.fetchAccountWorker(accountWorkerId);
	}

	/**
	* Returns the account worker with the primary key.
	*
	* @param accountWorkerId the primary key of the account worker
	* @return the account worker
	* @throws PortalException if a account worker with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AccountWorker getAccountWorker(
		long accountWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountWorkerLocalService.getAccountWorker(accountWorkerId);
	}

	@Override
	public com.liferay.osb.model.AccountWorker getAccountWorker(long userId,
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountWorkerLocalService.getAccountWorker(userId,
			accountEntryId);
	}

	/**
	* Updates the account worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountWorker the account worker
	* @return the account worker that was updated
	*/
	@Override
	public com.liferay.osb.model.AccountWorker updateAccountWorker(
		com.liferay.osb.model.AccountWorker accountWorker) {
		return _accountWorkerLocalService.updateAccountWorker(accountWorker);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _accountWorkerLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountWorkerLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _accountWorkerLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountWorkerLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountWorkerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of account workers.
	*
	* @return the number of account workers
	*/
	@Override
	public int getAccountWorkersCount() {
		return _accountWorkerLocalService.getAccountWorkersCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountWorkerLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _accountWorkerLocalService.getOSGiServiceIdentifier();
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
		return _accountWorkerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accountWorkerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accountWorkerLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the account workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @return the range of account workers
	*/
	@Override
	public java.util.List<com.liferay.osb.model.AccountWorker> getAccountWorkers(
		int start, int end) {
		return _accountWorkerLocalService.getAccountWorkers(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountWorker> getAccountWorkers(
		long accountEntryId) {
		return _accountWorkerLocalService.getAccountWorkers(accountEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountWorker> getAccountWorkers(
		long accountEntryId, int role) {
		return _accountWorkerLocalService.getAccountWorkers(accountEntryId, role);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountWorker> getUserAccountWorkers(
		long userId) {
		return _accountWorkerLocalService.getUserAccountWorkers(userId);
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
		return _accountWorkerLocalService.dynamicQueryCount(dynamicQuery);
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
		return _accountWorkerLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteAccountEntryAccountWorkers(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_accountWorkerLocalService.deleteAccountEntryAccountWorkers(accountEntryId);
	}

	@Override
	public void deleteAccountWorker(long userId, long accountWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_accountWorkerLocalService.deleteAccountWorker(userId, accountWorkerId);
	}

	@Override
	public void deleteAccountWorkers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_accountWorkerLocalService.deleteAccountWorkers(userId);
	}

	@Override
	public void updateAccountWorker(long userId, long accountWorkerId,
		int role, int notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		_accountWorkerLocalService.updateAccountWorker(userId, accountWorkerId,
			role, notifications);
	}

	@Override
	public AccountWorkerLocalService getWrappedService() {
		return _accountWorkerLocalService;
	}

	@Override
	public void setWrappedService(
		AccountWorkerLocalService accountWorkerLocalService) {
		_accountWorkerLocalService = accountWorkerLocalService;
	}

	private AccountWorkerLocalService _accountWorkerLocalService;
}