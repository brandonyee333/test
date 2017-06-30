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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AccountWorkerLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountWorkerLocalService
 * @generated
 */
public class AccountWorkerLocalServiceWrapper
	implements AccountWorkerLocalService,
		ServiceWrapper<AccountWorkerLocalService> {
	public AccountWorkerLocalServiceWrapper(
		AccountWorkerLocalService accountWorkerLocalService) {
		_accountWorkerLocalService = accountWorkerLocalService;
	}

	/**
	* Adds the account worker to the database. Also notifies the appropriate model listeners.
	*
	* @param accountWorker the account worker
	* @return the account worker that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountWorker addAccountWorker(
		com.liferay.osb.model.AccountWorker accountWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.addAccountWorker(accountWorker);
	}

	/**
	* Creates a new account worker with the primary key. Does not add the account worker to the database.
	*
	* @param accountWorkerId the primary key for the new account worker
	* @return the new account worker
	*/
	public com.liferay.osb.model.AccountWorker createAccountWorker(
		long accountWorkerId) {
		return _accountWorkerLocalService.createAccountWorker(accountWorkerId);
	}

	/**
	* Deletes the account worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountWorkerId the primary key of the account worker
	* @return the account worker that was removed
	* @throws PortalException if a account worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountWorker deleteAccountWorker(
		long accountWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.deleteAccountWorker(accountWorkerId);
	}

	/**
	* Deletes the account worker from the database. Also notifies the appropriate model listeners.
	*
	* @param accountWorker the account worker
	* @return the account worker that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountWorker deleteAccountWorker(
		com.liferay.osb.model.AccountWorker accountWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.deleteAccountWorker(accountWorker);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountWorkerLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AccountWorker fetchAccountWorker(
		long accountWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.fetchAccountWorker(accountWorkerId);
	}

	/**
	* Returns the account worker with the primary key.
	*
	* @param accountWorkerId the primary key of the account worker
	* @return the account worker
	* @throws PortalException if a account worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountWorker getAccountWorker(
		long accountWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.getAccountWorker(accountWorkerId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the account workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @return the range of account workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountWorker> getAccountWorkers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.getAccountWorkers(start, end);
	}

	/**
	* Returns the number of account workers.
	*
	* @return the number of account workers
	* @throws SystemException if a system exception occurred
	*/
	public int getAccountWorkersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.getAccountWorkersCount();
	}

	/**
	* Updates the account worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountWorker the account worker
	* @return the account worker that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountWorker updateAccountWorker(
		com.liferay.osb.model.AccountWorker accountWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.updateAccountWorker(accountWorker);
	}

	/**
	* Updates the account worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountWorker the account worker
	* @param merge whether to merge the account worker with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the account worker that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountWorker updateAccountWorker(
		com.liferay.osb.model.AccountWorker accountWorker, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.updateAccountWorker(accountWorker,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountWorkerLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountWorkerLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountWorkerLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void addAccountWorkers(long userId, long[] userIds,
		long accountEntryId, int[] roles, int[] notifications)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountWorkerLocalService.addAccountWorkers(userId, userIds,
			accountEntryId, roles, notifications);
	}

	public void deleteAccountEntryAccountWorkers(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountWorkerLocalService.deleteAccountEntryAccountWorkers(accountEntryId);
	}

	public void deleteAccountWorkers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountWorkerLocalService.deleteAccountWorkers(userId);
	}

	public void deleteAccountWorkers(long userId, long[] userIds,
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountWorkerLocalService.deleteAccountWorkers(userId, userIds,
			accountEntryId);
	}

	public com.liferay.osb.model.AccountWorker getAccountWorker(long userId,
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.getAccountWorker(userId,
			accountEntryId);
	}

	public java.util.List<com.liferay.osb.model.AccountWorker> getAccountWorkers(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.getAccountWorkers(accountEntryId);
	}

	public java.util.List<com.liferay.osb.model.AccountWorker> getAccountWorkers(
		long accountEntryId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.getAccountWorkers(accountEntryId, role);
	}

	public java.util.List<com.liferay.osb.model.AccountWorker> getUserAccountWorkers(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.getUserAccountWorkers(userId);
	}

	public boolean hasAccountWorker(long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.hasAccountWorker(userId,
			accountEntryId);
	}

	public boolean hasAccountWorkerRole(long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorkerLocalService.hasAccountWorkerRole(userId, role);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountWorkerLocalService getWrappedAccountWorkerLocalService() {
		return _accountWorkerLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountWorkerLocalService(
		AccountWorkerLocalService accountWorkerLocalService) {
		_accountWorkerLocalService = accountWorkerLocalService;
	}

	public AccountWorkerLocalService getWrappedService() {
		return _accountWorkerLocalService;
	}

	public void setWrappedService(
		AccountWorkerLocalService accountWorkerLocalService) {
		_accountWorkerLocalService = accountWorkerLocalService;
	}

	private AccountWorkerLocalService _accountWorkerLocalService;
}