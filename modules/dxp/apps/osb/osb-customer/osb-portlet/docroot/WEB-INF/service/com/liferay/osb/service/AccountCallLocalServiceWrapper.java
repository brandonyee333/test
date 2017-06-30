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
 * This class is a wrapper for {@link AccountCallLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountCallLocalService
 * @generated
 */
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
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCall addAccountCall(
		com.liferay.osb.model.AccountCall accountCall)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCallLocalService.addAccountCall(accountCall);
	}

	/**
	* Creates a new account call with the primary key. Does not add the account call to the database.
	*
	* @param accountCallId the primary key for the new account call
	* @return the new account call
	*/
	public com.liferay.osb.model.AccountCall createAccountCall(
		long accountCallId) {
		return _accountCallLocalService.createAccountCall(accountCallId);
	}

	/**
	* Deletes the account call with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCallId the primary key of the account call
	* @return the account call that was removed
	* @throws PortalException if a account call with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCall deleteAccountCall(
		long accountCallId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountCallLocalService.deleteAccountCall(accountCallId);
	}

	/**
	* Deletes the account call from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCall the account call
	* @return the account call that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCall deleteAccountCall(
		com.liferay.osb.model.AccountCall accountCall)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCallLocalService.deleteAccountCall(accountCall);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountCallLocalService.dynamicQuery();
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
		return _accountCallLocalService.dynamicQuery(dynamicQuery);
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
		return _accountCallLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _accountCallLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _accountCallLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AccountCall fetchAccountCall(
		long accountCallId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCallLocalService.fetchAccountCall(accountCallId);
	}

	/**
	* Returns the account call with the primary key.
	*
	* @param accountCallId the primary key of the account call
	* @return the account call
	* @throws PortalException if a account call with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCall getAccountCall(long accountCallId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountCallLocalService.getAccountCall(accountCallId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountCallLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the account calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account calls
	* @param end the upper bound of the range of account calls (not inclusive)
	* @return the range of account calls
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountCall> getAccountCalls(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCallLocalService.getAccountCalls(start, end);
	}

	/**
	* Returns the number of account calls.
	*
	* @return the number of account calls
	* @throws SystemException if a system exception occurred
	*/
	public int getAccountCallsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCallLocalService.getAccountCallsCount();
	}

	/**
	* Updates the account call in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountCall the account call
	* @return the account call that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCall updateAccountCall(
		com.liferay.osb.model.AccountCall accountCall)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCallLocalService.updateAccountCall(accountCall);
	}

	/**
	* Updates the account call in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountCall the account call
	* @param merge whether to merge the account call with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the account call that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCall updateAccountCall(
		com.liferay.osb.model.AccountCall accountCall, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCallLocalService.updateAccountCall(accountCall, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountCallLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountCallLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountCallLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public java.util.List<com.liferay.osb.model.AccountCall> getAccountCalls(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCallLocalService.getAccountCalls(accountEntryId);
	}

	public com.liferay.osb.model.AccountCall updateAccountCall(long userId,
		long accountCallId, long accountEntryId, int type, int callDateMonth,
		int callDateDay, int callDateYear, int callDateHour,
		int callDateMinute, long callLength, java.lang.String summary,
		java.lang.String clientsPresent, java.lang.String notes,
		java.lang.String actionItems)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountCallLocalService.updateAccountCall(userId,
			accountCallId, accountEntryId, type, callDateMonth, callDateDay,
			callDateYear, callDateHour, callDateMinute, callLength, summary,
			clientsPresent, notes, actionItems);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountCallLocalService getWrappedAccountCallLocalService() {
		return _accountCallLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountCallLocalService(
		AccountCallLocalService accountCallLocalService) {
		_accountCallLocalService = accountCallLocalService;
	}

	public AccountCallLocalService getWrappedService() {
		return _accountCallLocalService;
	}

	public void setWrappedService(
		AccountCallLocalService accountCallLocalService) {
		_accountCallLocalService = accountCallLocalService;
	}

	private AccountCallLocalService _accountCallLocalService;
}