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

package com.liferay.osb.service.persistence;

import com.liferay.osb.model.AccountInformation;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the account information service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountInformationPersistenceImpl
 * @see AccountInformationUtil
 * @generated
 */
public interface AccountInformationPersistence extends BasePersistence<AccountInformation> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountInformationUtil} to access the account information persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the account information in the entity cache if it is enabled.
	*
	* @param accountInformation the account information
	*/
	public void cacheResult(
		com.liferay.osb.model.AccountInformation accountInformation);

	/**
	* Caches the account informations in the entity cache if it is enabled.
	*
	* @param accountInformations the account informations
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AccountInformation> accountInformations);

	/**
	* Creates a new account information with the primary key. Does not add the account information to the database.
	*
	* @param accountInformationId the primary key for the new account information
	* @return the new account information
	*/
	public com.liferay.osb.model.AccountInformation create(
		long accountInformationId);

	/**
	* Removes the account information with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountInformationId the primary key of the account information
	* @return the account information that was removed
	* @throws com.liferay.osb.NoSuchAccountInformationException if a account information with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation remove(
		long accountInformationId)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AccountInformation updateImpl(
		com.liferay.osb.model.AccountInformation accountInformation,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account information with the primary key or throws a {@link com.liferay.osb.NoSuchAccountInformationException} if it could not be found.
	*
	* @param accountInformationId the primary key of the account information
	* @return the account information
	* @throws com.liferay.osb.NoSuchAccountInformationException if a account information with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation findByPrimaryKey(
		long accountInformationId)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account information with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountInformationId the primary key of the account information
	* @return the account information, or <code>null</code> if a account information with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation fetchByPrimaryKey(
		long accountInformationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @return the matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountInformation> findByAEI_API(
		long accountEntryId, long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @return the range of matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountInformation> findByAEI_API(
		long accountEntryId, long accountProjectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountInformation> findByAEI_API(
		long accountEntryId, long accountProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account information
	* @throws com.liferay.osb.NoSuchAccountInformationException if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation findByAEI_API_First(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account information, or <code>null</code> if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation fetchByAEI_API_First(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account information
	* @throws com.liferay.osb.NoSuchAccountInformationException if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation findByAEI_API_Last(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account information, or <code>null</code> if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation fetchByAEI_API_Last(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account informations before and after the current account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountInformationId the primary key of the current account information
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account information
	* @throws com.liferay.osb.NoSuchAccountInformationException if a account information with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation[] findByAEI_API_PrevAndNext(
		long accountInformationId, long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @return the matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountInformation> findByAEI_NotAPI(
		long accountEntryId, long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @return the range of matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountInformation> findByAEI_NotAPI(
		long accountEntryId, long accountProjectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountInformation> findByAEI_NotAPI(
		long accountEntryId, long accountProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account information
	* @throws com.liferay.osb.NoSuchAccountInformationException if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation findByAEI_NotAPI_First(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account information, or <code>null</code> if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation fetchByAEI_NotAPI_First(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account information
	* @throws com.liferay.osb.NoSuchAccountInformationException if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation findByAEI_NotAPI_Last(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account information, or <code>null</code> if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation fetchByAEI_NotAPI_Last(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account informations before and after the current account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountInformationId the primary key of the current account information
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account information
	* @throws com.liferay.osb.NoSuchAccountInformationException if a account information with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation[] findByAEI_NotAPI_PrevAndNext(
		long accountInformationId, long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or throws a {@link com.liferay.osb.NoSuchAccountInformationException} if it could not be found.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @return the matching account information
	* @throws com.liferay.osb.NoSuchAccountInformationException if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation findByAEI_API_FI(
		long accountEntryId, long accountProjectId, int fieldId)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @return the matching account information, or <code>null</code> if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation fetchByAEI_API_FI(
		long accountEntryId, long accountProjectId, int fieldId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching account information, or <code>null</code> if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation fetchByAEI_API_FI(
		long accountEntryId, long accountProjectId, int fieldId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account informations.
	*
	* @return the account informations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountInformation> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the account informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @return the range of account informations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountInformation> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the account informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account informations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountInformation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account informations where accountEntryId = &#63; and accountProjectId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAEI_API(long accountEntryId, long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAEI_NotAPI(long accountEntryId, long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @return the account information that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation removeByAEI_API_FI(
		long accountEntryId, long accountProjectId, int fieldId)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account informations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @return the number of matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_API(long accountEntryId, long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @return the number of matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_NotAPI(long accountEntryId, long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account informations where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @return the number of matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_API_FI(long accountEntryId, long accountProjectId,
		int fieldId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account informations.
	*
	* @return the number of account informations
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}