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

import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.model.AccountEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the account entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.AccountEntryPersistenceImpl
 * @see AccountEntryUtil
 * @generated
 */
@ProviderType
public interface AccountEntryPersistence extends BasePersistence<AccountEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountEntryUtil} to access the account entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the account entry where corpProjectUuid = &#63; or throws a {@link NoSuchAccountEntryException} if it could not be found.
	*
	* @param corpProjectUuid the corp project uuid
	* @return the matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByCorpProjectUuid(java.lang.String corpProjectUuid)
		throws NoSuchAccountEntryException;

	/**
	* Returns the account entry where corpProjectUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param corpProjectUuid the corp project uuid
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByCorpProjectUuid(java.lang.String corpProjectUuid);

	/**
	* Returns the account entry where corpProjectUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param corpProjectUuid the corp project uuid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByCorpProjectUuid(
		java.lang.String corpProjectUuid, boolean retrieveFromCache);

	/**
	* Removes the account entry where corpProjectUuid = &#63; from the database.
	*
	* @param corpProjectUuid the corp project uuid
	* @return the account entry that was removed
	*/
	public AccountEntry removeByCorpProjectUuid(
		java.lang.String corpProjectUuid) throws NoSuchAccountEntryException;

	/**
	* Returns the number of account entries where corpProjectUuid = &#63;.
	*
	* @param corpProjectUuid the corp project uuid
	* @return the number of matching account entries
	*/
	public int countByCorpProjectUuid(java.lang.String corpProjectUuid);

	/**
	* Returns the account entry where corpProjectId = &#63; or throws a {@link NoSuchAccountEntryException} if it could not be found.
	*
	* @param corpProjectId the corp project ID
	* @return the matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByCorpProjectId(long corpProjectId)
		throws NoSuchAccountEntryException;

	/**
	* Returns the account entry where corpProjectId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByCorpProjectId(long corpProjectId);

	/**
	* Returns the account entry where corpProjectId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByCorpProjectId(long corpProjectId,
		boolean retrieveFromCache);

	/**
	* Removes the account entry where corpProjectId = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	* @return the account entry that was removed
	*/
	public AccountEntry removeByCorpProjectId(long corpProjectId)
		throws NoSuchAccountEntryException;

	/**
	* Returns the number of account entries where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @return the number of matching account entries
	*/
	public int countByCorpProjectId(long corpProjectId);

	/**
	* Returns all the account entries where name = &#63;.
	*
	* @param name the name
	* @return the matching account entries
	*/
	public java.util.List<AccountEntry> findByName(java.lang.String name);

	/**
	* Returns a range of all the account entries where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public java.util.List<AccountEntry> findByName(java.lang.String name,
		int start, int end);

	/**
	* Returns an ordered range of all the account entries where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByName(java.lang.String name,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns an ordered range of all the account entries where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByName(java.lang.String name,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByName_First(java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns the first account entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByName_First(java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns the last account entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByName_Last(java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns the last account entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByName_Last(java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns the account entries before and after the current account entry in the ordered set where name = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public AccountEntry[] findByName_PrevAndNext(long accountEntryId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Removes all the account entries where name = &#63; from the database.
	*
	* @param name the name
	*/
	public void removeByName(java.lang.String name);

	/**
	* Returns the number of account entries where name = &#63;.
	*
	* @param name the name
	* @return the number of matching account entries
	*/
	public int countByName(java.lang.String name);

	/**
	* Returns the account entry where code = &#63; or throws a {@link NoSuchAccountEntryException} if it could not be found.
	*
	* @param code the code
	* @return the matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByCode(java.lang.String code)
		throws NoSuchAccountEntryException;

	/**
	* Returns the account entry where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param code the code
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByCode(java.lang.String code);

	/**
	* Returns the account entry where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param code the code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByCode(java.lang.String code,
		boolean retrieveFromCache);

	/**
	* Removes the account entry where code = &#63; from the database.
	*
	* @param code the code
	* @return the account entry that was removed
	*/
	public AccountEntry removeByCode(java.lang.String code)
		throws NoSuchAccountEntryException;

	/**
	* Returns the number of account entries where code = &#63;.
	*
	* @param code the code
	* @return the number of matching account entries
	*/
	public int countByCode(java.lang.String code);

	/**
	* Returns all the account entries where redirectAccountEntryId = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @return the matching account entries
	*/
	public java.util.List<AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId);

	/**
	* Returns a range of all the account entries where redirectAccountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public java.util.List<AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId, int start, int end);

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account entry in the ordered set where redirectAccountEntryId = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByRedirectAccountEntryId_First(
		long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns the first account entry in the ordered set where redirectAccountEntryId = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByRedirectAccountEntryId_First(
		long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns the last account entry in the ordered set where redirectAccountEntryId = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByRedirectAccountEntryId_Last(
		long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns the last account entry in the ordered set where redirectAccountEntryId = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByRedirectAccountEntryId_Last(
		long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns the account entries before and after the current account entry in the ordered set where redirectAccountEntryId = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public AccountEntry[] findByRedirectAccountEntryId_PrevAndNext(
		long accountEntryId, long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Removes all the account entries where redirectAccountEntryId = &#63; from the database.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	*/
	public void removeByRedirectAccountEntryId(long redirectAccountEntryId);

	/**
	* Returns the number of account entries where redirectAccountEntryId = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @return the number of matching account entries
	*/
	public int countByRedirectAccountEntryId(long redirectAccountEntryId);

	/**
	* Returns all the account entries where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @return the matching account entries
	*/
	public java.util.List<AccountEntry> findByPartnerEntryId(
		long partnerEntryId);

	/**
	* Returns a range of all the account entries where partnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public java.util.List<AccountEntry> findByPartnerEntryId(
		long partnerEntryId, int start, int end);

	/**
	* Returns an ordered range of all the account entries where partnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByPartnerEntryId(
		long partnerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns an ordered range of all the account entries where partnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByPartnerEntryId(
		long partnerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account entry in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByPartnerEntryId_First(long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns the first account entry in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByPartnerEntryId_First(long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns the last account entry in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByPartnerEntryId_Last(long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns the last account entry in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByPartnerEntryId_Last(long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns the account entries before and after the current account entry in the ordered set where partnerEntryId = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public AccountEntry[] findByPartnerEntryId_PrevAndNext(
		long accountEntryId, long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Removes all the account entries where partnerEntryId = &#63; from the database.
	*
	* @param partnerEntryId the partner entry ID
	*/
	public void removeByPartnerEntryId(long partnerEntryId);

	/**
	* Returns the number of account entries where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @return the number of matching account entries
	*/
	public int countByPartnerEntryId(long partnerEntryId);

	/**
	* Returns all the account entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the matching account entries
	*/
	public java.util.List<AccountEntry> findByU_T(long userId, int type);

	/**
	* Returns a range of all the account entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public java.util.List<AccountEntry> findByU_T(long userId, int type,
		int start, int end);

	/**
	* Returns an ordered range of all the account entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByU_T(long userId, int type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns an ordered range of all the account entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByU_T(long userId, int type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByU_T_First(long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns the first account entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByU_T_First(long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns the last account entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByU_T_Last(long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns the last account entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByU_T_Last(long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns the account entries before and after the current account entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public AccountEntry[] findByU_T_PrevAndNext(long accountEntryId,
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Removes all the account entries where userId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param type the type
	*/
	public void removeByU_T(long userId, int type);

	/**
	* Returns the number of account entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the number of matching account entries
	*/
	public int countByU_T(long userId, int type);

	/**
	* Returns all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @return the matching account entries
	*/
	public java.util.List<AccountEntry> findByRAEI_S(
		long redirectAccountEntryId, int status);

	/**
	* Returns a range of all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public java.util.List<AccountEntry> findByRAEI_S(
		long redirectAccountEntryId, int status, int start, int end);

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByRAEI_S(
		long redirectAccountEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByRAEI_S(
		long redirectAccountEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByRAEI_S_First(long redirectAccountEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByRAEI_S_First(long redirectAccountEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns the last account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByRAEI_S_Last(long redirectAccountEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns the last account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByRAEI_S_Last(long redirectAccountEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns the account entries before and after the current account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public AccountEntry[] findByRAEI_S_PrevAndNext(long accountEntryId,
		long redirectAccountEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns all the account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param statuses the statuses
	* @return the matching account entries
	*/
	public java.util.List<AccountEntry> findByRAEI_S(
		long redirectAccountEntryId, int[] statuses);

	/**
	* Returns a range of all the account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param statuses the statuses
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public java.util.List<AccountEntry> findByRAEI_S(
		long redirectAccountEntryId, int[] statuses, int start, int end);

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param statuses the statuses
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByRAEI_S(
		long redirectAccountEntryId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByRAEI_S(
		long redirectAccountEntryId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the account entries where redirectAccountEntryId = &#63; and status = &#63; from the database.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	*/
	public void removeByRAEI_S(long redirectAccountEntryId, int status);

	/**
	* Returns the number of account entries where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @return the number of matching account entries
	*/
	public int countByRAEI_S(long redirectAccountEntryId, int status);

	/**
	* Returns the number of account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param statuses the statuses
	* @return the number of matching account entries
	*/
	public int countByRAEI_S(long redirectAccountEntryId, int[] statuses);

	/**
	* Returns all the account entries where partnerEntryId = &#63; and partnerManagedSupport = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param partnerManagedSupport the partner managed support
	* @return the matching account entries
	*/
	public java.util.List<AccountEntry> findByPEI_PMS(long partnerEntryId,
		boolean partnerManagedSupport);

	/**
	* Returns a range of all the account entries where partnerEntryId = &#63; and partnerManagedSupport = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param partnerManagedSupport the partner managed support
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public java.util.List<AccountEntry> findByPEI_PMS(long partnerEntryId,
		boolean partnerManagedSupport, int start, int end);

	/**
	* Returns an ordered range of all the account entries where partnerEntryId = &#63; and partnerManagedSupport = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param partnerManagedSupport the partner managed support
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByPEI_PMS(long partnerEntryId,
		boolean partnerManagedSupport, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns an ordered range of all the account entries where partnerEntryId = &#63; and partnerManagedSupport = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param partnerManagedSupport the partner managed support
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByPEI_PMS(long partnerEntryId,
		boolean partnerManagedSupport, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account entry in the ordered set where partnerEntryId = &#63; and partnerManagedSupport = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param partnerManagedSupport the partner managed support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByPEI_PMS_First(long partnerEntryId,
		boolean partnerManagedSupport,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns the first account entry in the ordered set where partnerEntryId = &#63; and partnerManagedSupport = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param partnerManagedSupport the partner managed support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByPEI_PMS_First(long partnerEntryId,
		boolean partnerManagedSupport,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns the last account entry in the ordered set where partnerEntryId = &#63; and partnerManagedSupport = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param partnerManagedSupport the partner managed support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByPEI_PMS_Last(long partnerEntryId,
		boolean partnerManagedSupport,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns the last account entry in the ordered set where partnerEntryId = &#63; and partnerManagedSupport = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param partnerManagedSupport the partner managed support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByPEI_PMS_Last(long partnerEntryId,
		boolean partnerManagedSupport,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns the account entries before and after the current account entry in the ordered set where partnerEntryId = &#63; and partnerManagedSupport = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param partnerEntryId the partner entry ID
	* @param partnerManagedSupport the partner managed support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public AccountEntry[] findByPEI_PMS_PrevAndNext(long accountEntryId,
		long partnerEntryId, boolean partnerManagedSupport,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Removes all the account entries where partnerEntryId = &#63; and partnerManagedSupport = &#63; from the database.
	*
	* @param partnerEntryId the partner entry ID
	* @param partnerManagedSupport the partner managed support
	*/
	public void removeByPEI_PMS(long partnerEntryId,
		boolean partnerManagedSupport);

	/**
	* Returns the number of account entries where partnerEntryId = &#63; and partnerManagedSupport = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param partnerManagedSupport the partner managed support
	* @return the number of matching account entries
	*/
	public int countByPEI_PMS(long partnerEntryId, boolean partnerManagedSupport);

	/**
	* Returns all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @return the matching account entries
	*/
	public java.util.List<AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int type, int status);

	/**
	* Returns a range of all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public java.util.List<AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int type, int status, int start, int end);

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int type, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int type, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByRAEI_NotT_S_First(long redirectAccountEntryId,
		int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByRAEI_NotT_S_First(long redirectAccountEntryId,
		int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns the last account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByRAEI_NotT_S_Last(long redirectAccountEntryId,
		int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns the last account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByRAEI_NotT_S_Last(long redirectAccountEntryId,
		int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns the account entries before and after the current account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public AccountEntry[] findByRAEI_NotT_S_PrevAndNext(long accountEntryId,
		long redirectAccountEntryId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns all the account entries where redirectAccountEntryId = &#63; and type &ne; all &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param types the types
	* @param statuses the statuses
	* @return the matching account entries
	*/
	public java.util.List<AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int[] types, int[] statuses);

	/**
	* Returns a range of all the account entries where redirectAccountEntryId = &#63; and type &ne; all &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param types the types
	* @param statuses the statuses
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public java.util.List<AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int[] types, int[] statuses, int start,
		int end);

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and type &ne; all &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param types the types
	* @param statuses the statuses
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int[] types, int[] statuses, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int[] types, int[] statuses, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63; from the database.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	*/
	public void removeByRAEI_NotT_S(long redirectAccountEntryId, int type,
		int status);

	/**
	* Returns the number of account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @return the number of matching account entries
	*/
	public int countByRAEI_NotT_S(long redirectAccountEntryId, int type,
		int status);

	/**
	* Returns the number of account entries where redirectAccountEntryId = &#63; and type &ne; all &#63; and status = any &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param types the types
	* @param statuses the statuses
	* @return the number of matching account entries
	*/
	public int countByRAEI_NotT_S(long redirectAccountEntryId, int[] types,
		int[] statuses);

	/**
	* Returns all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @return the matching account entries
	*/
	public java.util.List<AccountEntry> findByN_C_RAEI(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId);

	/**
	* Returns a range of all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public java.util.List<AccountEntry> findByN_C_RAEI(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId, int start, int end);

	/**
	* Returns an ordered range of all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByN_C_RAEI(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns an ordered range of all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public java.util.List<AccountEntry> findByN_C_RAEI(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByN_C_RAEI_First(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns the first account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByN_C_RAEI_First(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns the last account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public AccountEntry findByN_C_RAEI_Last(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Returns the last account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public AccountEntry fetchByN_C_RAEI_Last(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns the account entries before and after the current account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public AccountEntry[] findByN_C_RAEI_PrevAndNext(long accountEntryId,
		java.lang.String name, java.lang.String code,
		long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	* Removes all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63; from the database.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	*/
	public void removeByN_C_RAEI(java.lang.String name, java.lang.String code,
		long redirectAccountEntryId);

	/**
	* Returns the number of account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @return the number of matching account entries
	*/
	public int countByN_C_RAEI(java.lang.String name, java.lang.String code,
		long redirectAccountEntryId);

	/**
	* Caches the account entry in the entity cache if it is enabled.
	*
	* @param accountEntry the account entry
	*/
	public void cacheResult(AccountEntry accountEntry);

	/**
	* Caches the account entries in the entity cache if it is enabled.
	*
	* @param accountEntries the account entries
	*/
	public void cacheResult(java.util.List<AccountEntry> accountEntries);

	/**
	* Creates a new account entry with the primary key. Does not add the account entry to the database.
	*
	* @param accountEntryId the primary key for the new account entry
	* @return the new account entry
	*/
	public AccountEntry create(long accountEntryId);

	/**
	* Removes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry that was removed
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public AccountEntry remove(long accountEntryId)
		throws NoSuchAccountEntryException;

	public AccountEntry updateImpl(AccountEntry accountEntry);

	/**
	* Returns the account entry with the primary key or throws a {@link NoSuchAccountEntryException} if it could not be found.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public AccountEntry findByPrimaryKey(long accountEntryId)
		throws NoSuchAccountEntryException;

	/**
	* Returns the account entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry, or <code>null</code> if a account entry with the primary key could not be found
	*/
	public AccountEntry fetchByPrimaryKey(long accountEntryId);

	@Override
	public java.util.Map<java.io.Serializable, AccountEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the account entries.
	*
	* @return the account entries
	*/
	public java.util.List<AccountEntry> findAll();

	/**
	* Returns a range of all the account entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of account entries
	*/
	public java.util.List<AccountEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the account entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account entries
	*/
	public java.util.List<AccountEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns an ordered range of all the account entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of account entries
	*/
	public java.util.List<AccountEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the account entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of account entries.
	*
	* @return the number of account entries
	*/
	public int countAll();

	/**
	* Returns the primaryKeys of support regions associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @return long[] of the primaryKeys of support regions associated with the account entry
	*/
	public long[] getSupportRegionPrimaryKeys(long pk);

	/**
	* Returns all the support regions associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @return the support regions associated with the account entry
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk);

	/**
	* Returns a range of all the support regions associated with the account entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the account entry
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of support regions associated with the account entry
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end);

	/**
	* Returns an ordered range of all the support regions associated with the account entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the account entry
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support regions associated with the account entry
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.SupportRegion> orderByComparator);

	/**
	* Returns the number of support regions associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @return the number of support regions associated with the account entry
	*/
	public int getSupportRegionsSize(long pk);

	/**
	* Returns <code>true</code> if the support region is associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPK the primary key of the support region
	* @return <code>true</code> if the support region is associated with the account entry; <code>false</code> otherwise
	*/
	public boolean containsSupportRegion(long pk, long supportRegionPK);

	/**
	* Returns <code>true</code> if the account entry has any support regions associated with it.
	*
	* @param pk the primary key of the account entry to check for associations with support regions
	* @return <code>true</code> if the account entry has any support regions associated with it; <code>false</code> otherwise
	*/
	public boolean containsSupportRegions(long pk);

	/**
	* Adds an association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPK the primary key of the support region
	*/
	public void addSupportRegion(long pk, long supportRegionPK);

	/**
	* Adds an association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegion the support region
	*/
	public void addSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion);

	/**
	* Adds an association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPKs the primary keys of the support regions
	*/
	public void addSupportRegions(long pk, long[] supportRegionPKs);

	/**
	* Adds an association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegions the support regions
	*/
	public void addSupportRegions(long pk,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions);

	/**
	* Clears all associations between the account entry and its support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry to clear the associated support regions from
	*/
	public void clearSupportRegions(long pk);

	/**
	* Removes the association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPK the primary key of the support region
	*/
	public void removeSupportRegion(long pk, long supportRegionPK);

	/**
	* Removes the association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegion the support region
	*/
	public void removeSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion);

	/**
	* Removes the association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPKs the primary keys of the support regions
	*/
	public void removeSupportRegions(long pk, long[] supportRegionPKs);

	/**
	* Removes the association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegions the support regions
	*/
	public void removeSupportRegions(long pk,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions);

	/**
	* Sets the support regions associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPKs the primary keys of the support regions to be associated with the account entry
	*/
	public void setSupportRegions(long pk, long[] supportRegionPKs);

	/**
	* Sets the support regions associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegions the support regions to be associated with the account entry
	*/
	public void setSupportRegions(long pk,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions);

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}