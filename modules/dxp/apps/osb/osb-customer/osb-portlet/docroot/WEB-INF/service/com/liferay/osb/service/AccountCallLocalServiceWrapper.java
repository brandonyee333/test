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
 * Provides a wrapper for {@link AccountCallLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountCallLocalService
 * @generated
 */
@ProviderType
public class AccountCallLocalServiceWrapper implements AccountCallLocalService,
	ServiceWrapper<AccountCallLocalService> {
	public AccountCallLocalServiceWrapper(
		AccountCallLocalService accountCallLocalService) {
		_accountCallLocalService = accountCallLocalService;
	}

	/**
	* Adds the account call to the database. Also notifies the appropriate model listeners.
	*
	* @param accountCall the account call
	* @return the account call that was added
	*/
	@Override
	public com.liferay.osb.model.AccountCall addAccountCall(
		com.liferay.osb.model.AccountCall accountCall) {
		return _accountCallLocalService.addAccountCall(accountCall);
	}

	/**
	* Creates a new account call with the primary key. Does not add the account call to the database.
	*
	* @param accountCallId the primary key for the new account call
	* @return the new account call
	*/
	@Override
	public com.liferay.osb.model.AccountCall createAccountCall(
		long accountCallId) {
		return _accountCallLocalService.createAccountCall(accountCallId);
	}

	/**
	* Deletes the account call from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCall the account call
	* @return the account call that was removed
	*/
	@Override
	public com.liferay.osb.model.AccountCall deleteAccountCall(
		com.liferay.osb.model.AccountCall accountCall) {
		return _accountCallLocalService.deleteAccountCall(accountCall);
	}

	/**
	* Deletes the account call with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCallId the primary key of the account call
	* @return the account call that was removed
	* @throws PortalException if a account call with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AccountCall deleteAccountCall(
		long accountCallId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCallLocalService.deleteAccountCall(accountCallId);
	}

	@Override
	public com.liferay.osb.model.AccountCall fetchAccountCall(
		long accountCallId) {
		return _accountCallLocalService.fetchAccountCall(accountCallId);
	}

	/**
	* Returns the account call with the primary key.
	*
	* @param accountCallId the primary key of the account call
	* @return the account call
	* @throws PortalException if a account call with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AccountCall getAccountCall(long accountCallId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCallLocalService.getAccountCall(accountCallId);
	}

	/**
	* Updates the account call in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountCall the account call
	* @return the account call that was updated
	*/
	@Override
	public com.liferay.osb.model.AccountCall updateAccountCall(
		com.liferay.osb.model.AccountCall accountCall) {
		return _accountCallLocalService.updateAccountCall(accountCall);
	}

	@Override
	public com.liferay.osb.model.AccountCall updateAccountCall(long userId,
		long accountCallId, long accountEntryId, int type, int callDateMonth,
		int callDateDay, int callDateYear, int callDateHour,
		int callDateMinute, long callLength, java.lang.String summary,
		java.lang.String clientsPresent, java.lang.String notes,
		java.lang.String actionItems)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCallLocalService.updateAccountCall(userId,
			accountCallId, accountEntryId, type, callDateMonth, callDateDay,
			callDateYear, callDateHour, callDateMinute, callLength, summary,
			clientsPresent, notes, actionItems);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _accountCallLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountCallLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _accountCallLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCallLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCallLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of account calls.
	*
	* @return the number of account calls
	*/
	@Override
	public int getAccountCallsCount() {
		return _accountCallLocalService.getAccountCallsCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountCallLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _accountCallLocalService.getOSGiServiceIdentifier();
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
		return _accountCallLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accountCallLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accountCallLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the account calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account calls
	* @param end the upper bound of the range of account calls (not inclusive)
	* @return the range of account calls
	*/
	@Override
	public java.util.List<com.liferay.osb.model.AccountCall> getAccountCalls(
		int start, int end) {
		return _accountCallLocalService.getAccountCalls(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountCall> getAccountCalls(
		long accountEntryId) {
		return _accountCallLocalService.getAccountCalls(accountEntryId);
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
		return _accountCallLocalService.dynamicQueryCount(dynamicQuery);
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
		return _accountCallLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public AccountCallLocalService getWrappedService() {
		return _accountCallLocalService;
	}

	@Override
	public void setWrappedService(
		AccountCallLocalService accountCallLocalService) {
		_accountCallLocalService = accountCallLocalService;
	}

	private AccountCallLocalService _accountCallLocalService;
}