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
 * This class is a wrapper for {@link AccountLinkLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountLinkLocalService
 * @generated
 */
public class AccountLinkLocalServiceWrapper implements AccountLinkLocalService,
	ServiceWrapper<AccountLinkLocalService> {
	public AccountLinkLocalServiceWrapper(
		AccountLinkLocalService accountLinkLocalService) {
		_accountLinkLocalService = accountLinkLocalService;
	}

	/**
	* Adds the account link to the database. Also notifies the appropriate model listeners.
	*
	* @param accountLink the account link
	* @return the account link that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountLink addAccountLink(
		com.liferay.osb.model.AccountLink accountLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLinkLocalService.addAccountLink(accountLink);
	}

	/**
	* Creates a new account link with the primary key. Does not add the account link to the database.
	*
	* @param accountLinkId the primary key for the new account link
	* @return the new account link
	*/
	public com.liferay.osb.model.AccountLink createAccountLink(
		long accountLinkId) {
		return _accountLinkLocalService.createAccountLink(accountLinkId);
	}

	/**
	* Deletes the account link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountLinkId the primary key of the account link
	* @return the account link that was removed
	* @throws PortalException if a account link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountLink deleteAccountLink(
		long accountLinkId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLinkLocalService.deleteAccountLink(accountLinkId);
	}

	/**
	* Deletes the account link from the database. Also notifies the appropriate model listeners.
	*
	* @param accountLink the account link
	* @return the account link that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountLink deleteAccountLink(
		com.liferay.osb.model.AccountLink accountLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLinkLocalService.deleteAccountLink(accountLink);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountLinkLocalService.dynamicQuery();
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
		return _accountLinkLocalService.dynamicQuery(dynamicQuery);
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
		return _accountLinkLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _accountLinkLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _accountLinkLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AccountLink fetchAccountLink(
		long accountLinkId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLinkLocalService.fetchAccountLink(accountLinkId);
	}

	/**
	* Returns the account link with the primary key.
	*
	* @param accountLinkId the primary key of the account link
	* @return the account link
	* @throws PortalException if a account link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountLink getAccountLink(long accountLinkId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLinkLocalService.getAccountLink(accountLinkId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLinkLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the account links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @return the range of account links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountLink> getAccountLinks(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLinkLocalService.getAccountLinks(start, end);
	}

	/**
	* Returns the number of account links.
	*
	* @return the number of account links
	* @throws SystemException if a system exception occurred
	*/
	public int getAccountLinksCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLinkLocalService.getAccountLinksCount();
	}

	/**
	* Updates the account link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountLink the account link
	* @return the account link that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountLink updateAccountLink(
		com.liferay.osb.model.AccountLink accountLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLinkLocalService.updateAccountLink(accountLink);
	}

	/**
	* Updates the account link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountLink the account link
	* @param merge whether to merge the account link with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the account link that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountLink updateAccountLink(
		com.liferay.osb.model.AccountLink accountLink, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLinkLocalService.updateAccountLink(accountLink, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountLinkLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountLinkLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountLinkLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void addAccountLinks(long userId, long accountEntryId,
		java.lang.String[] urls)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountLinkLocalService.addAccountLinks(userId, accountEntryId, urls);
	}

	public java.util.List<com.liferay.osb.model.AccountLink> getAccountLinks(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLinkLocalService.getAccountLinks(accountEntryId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountLinkLocalService getWrappedAccountLinkLocalService() {
		return _accountLinkLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountLinkLocalService(
		AccountLinkLocalService accountLinkLocalService) {
		_accountLinkLocalService = accountLinkLocalService;
	}

	public AccountLinkLocalService getWrappedService() {
		return _accountLinkLocalService;
	}

	public void setWrappedService(
		AccountLinkLocalService accountLinkLocalService) {
		_accountLinkLocalService = accountLinkLocalService;
	}

	private AccountLinkLocalService _accountLinkLocalService;
}