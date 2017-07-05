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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.exception.NoSuchAccountInformationException;
import com.liferay.osb.model.AccountInformation;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the account information service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.AccountInformationPersistenceImpl
 * @see AccountInformationUtil
 * @generated
 */
@ProviderType
public interface AccountInformationPersistence extends BasePersistence<AccountInformation> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountInformationUtil} to access the account information persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @return the matching account informations
	*/
	public java.util.List<AccountInformation> findByAEI_API(
		long accountEntryId, long accountProjectId);

	/**
	* Returns a range of all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @return the range of matching account informations
	*/
	public java.util.List<AccountInformation> findByAEI_API(
		long accountEntryId, long accountProjectId, int start, int end);

	/**
	* Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account informations
	*/
	public java.util.List<AccountInformation> findByAEI_API(
		long accountEntryId, long accountProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountInformation> orderByComparator);

	/**
	* Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account informations
	*/
	public java.util.List<AccountInformation> findByAEI_API(
		long accountEntryId, long accountProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountInformation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account information
	* @throws NoSuchAccountInformationException if a matching account information could not be found
	*/
	public AccountInformation findByAEI_API_First(long accountEntryId,
		long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountInformation> orderByComparator)
		throws NoSuchAccountInformationException;

	/**
	* Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account information, or <code>null</code> if a matching account information could not be found
	*/
	public AccountInformation fetchByAEI_API_First(long accountEntryId,
		long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountInformation> orderByComparator);

	/**
	* Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account information
	* @throws NoSuchAccountInformationException if a matching account information could not be found
	*/
	public AccountInformation findByAEI_API_Last(long accountEntryId,
		long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountInformation> orderByComparator)
		throws NoSuchAccountInformationException;

	/**
	* Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account information, or <code>null</code> if a matching account information could not be found
	*/
	public AccountInformation fetchByAEI_API_Last(long accountEntryId,
		long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountInformation> orderByComparator);

	/**
	* Returns the account informations before and after the current account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountInformationId the primary key of the current account information
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account information
	* @throws NoSuchAccountInformationException if a account information with the primary key could not be found
	*/
	public AccountInformation[] findByAEI_API_PrevAndNext(
		long accountInformationId, long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountInformation> orderByComparator)
		throws NoSuchAccountInformationException;

	/**
	* Removes all the account informations where accountEntryId = &#63; and accountProjectId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	*/
	public void removeByAEI_API(long accountEntryId, long accountProjectId);

	/**
	* Returns the number of account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @return the number of matching account informations
	*/
	public int countByAEI_API(long accountEntryId, long accountProjectId);

	/**
	* Returns all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @return the matching account informations
	*/
	public java.util.List<AccountInformation> findByAEI_NotAPI(
		long accountEntryId, long accountProjectId);

	/**
	* Returns a range of all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @return the range of matching account informations
	*/
	public java.util.List<AccountInformation> findByAEI_NotAPI(
		long accountEntryId, long accountProjectId, int start, int end);

	/**
	* Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account informations
	*/
	public java.util.List<AccountInformation> findByAEI_NotAPI(
		long accountEntryId, long accountProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountInformation> orderByComparator);

	/**
	* Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account informations
	*/
	public java.util.List<AccountInformation> findByAEI_NotAPI(
		long accountEntryId, long accountProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountInformation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account information
	* @throws NoSuchAccountInformationException if a matching account information could not be found
	*/
	public AccountInformation findByAEI_NotAPI_First(long accountEntryId,
		long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountInformation> orderByComparator)
		throws NoSuchAccountInformationException;

	/**
	* Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account information, or <code>null</code> if a matching account information could not be found
	*/
	public AccountInformation fetchByAEI_NotAPI_First(long accountEntryId,
		long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountInformation> orderByComparator);

	/**
	* Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account information
	* @throws NoSuchAccountInformationException if a matching account information could not be found
	*/
	public AccountInformation findByAEI_NotAPI_Last(long accountEntryId,
		long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountInformation> orderByComparator)
		throws NoSuchAccountInformationException;

	/**
	* Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account information, or <code>null</code> if a matching account information could not be found
	*/
	public AccountInformation fetchByAEI_NotAPI_Last(long accountEntryId,
		long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountInformation> orderByComparator);

	/**
	* Returns the account informations before and after the current account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountInformationId the primary key of the current account information
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account information
	* @throws NoSuchAccountInformationException if a account information with the primary key could not be found
	*/
	public AccountInformation[] findByAEI_NotAPI_PrevAndNext(
		long accountInformationId, long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountInformation> orderByComparator)
		throws NoSuchAccountInformationException;

	/**
	* Removes all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	*/
	public void removeByAEI_NotAPI(long accountEntryId, long accountProjectId);

	/**
	* Returns the number of account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @return the number of matching account informations
	*/
	public int countByAEI_NotAPI(long accountEntryId, long accountProjectId);

	/**
	* Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or throws a {@link NoSuchAccountInformationException} if it could not be found.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @return the matching account information
	* @throws NoSuchAccountInformationException if a matching account information could not be found
	*/
	public AccountInformation findByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId)
		throws NoSuchAccountInformationException;

	/**
	* Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @return the matching account information, or <code>null</code> if a matching account information could not be found
	*/
	public AccountInformation fetchByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId);

	/**
	* Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching account information, or <code>null</code> if a matching account information could not be found
	*/
	public AccountInformation fetchByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId, boolean retrieveFromCache);

	/**
	* Removes the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @return the account information that was removed
	*/
	public AccountInformation removeByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId)
		throws NoSuchAccountInformationException;

	/**
	* Returns the number of account informations where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @return the number of matching account informations
	*/
	public int countByAEI_API_FI(long accountEntryId, long accountProjectId,
		int fieldId);

	/**
	* Caches the account information in the entity cache if it is enabled.
	*
	* @param accountInformation the account information
	*/
	public void cacheResult(AccountInformation accountInformation);

	/**
	* Caches the account informations in the entity cache if it is enabled.
	*
	* @param accountInformations the account informations
	*/
	public void cacheResult(
		java.util.List<AccountInformation> accountInformations);

	/**
	* Creates a new account information with the primary key. Does not add the account information to the database.
	*
	* @param accountInformationId the primary key for the new account information
	* @return the new account information
	*/
	public AccountInformation create(long accountInformationId);

	/**
	* Removes the account information with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountInformationId the primary key of the account information
	* @return the account information that was removed
	* @throws NoSuchAccountInformationException if a account information with the primary key could not be found
	*/
	public AccountInformation remove(long accountInformationId)
		throws NoSuchAccountInformationException;

	public AccountInformation updateImpl(AccountInformation accountInformation);

	/**
	* Returns the account information with the primary key or throws a {@link NoSuchAccountInformationException} if it could not be found.
	*
	* @param accountInformationId the primary key of the account information
	* @return the account information
	* @throws NoSuchAccountInformationException if a account information with the primary key could not be found
	*/
	public AccountInformation findByPrimaryKey(long accountInformationId)
		throws NoSuchAccountInformationException;

	/**
	* Returns the account information with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountInformationId the primary key of the account information
	* @return the account information, or <code>null</code> if a account information with the primary key could not be found
	*/
	public AccountInformation fetchByPrimaryKey(long accountInformationId);

	@Override
	public java.util.Map<java.io.Serializable, AccountInformation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the account informations.
	*
	* @return the account informations
	*/
	public java.util.List<AccountInformation> findAll();

	/**
	* Returns a range of all the account informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @return the range of account informations
	*/
	public java.util.List<AccountInformation> findAll(int start, int end);

	/**
	* Returns an ordered range of all the account informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account informations
	*/
	public java.util.List<AccountInformation> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountInformation> orderByComparator);

	/**
	* Returns an ordered range of all the account informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of account informations
	*/
	public java.util.List<AccountInformation> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountInformation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the account informations from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of account informations.
	*
	* @return the number of account informations
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}