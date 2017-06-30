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

import com.liferay.osb.model.DeveloperEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the developer entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DeveloperEntryPersistenceImpl
 * @see DeveloperEntryUtil
 * @generated
 */
public interface DeveloperEntryPersistence extends BasePersistence<DeveloperEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DeveloperEntryUtil} to access the developer entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the developer entry in the entity cache if it is enabled.
	*
	* @param developerEntry the developer entry
	*/
	public void cacheResult(com.liferay.osb.model.DeveloperEntry developerEntry);

	/**
	* Caches the developer entries in the entity cache if it is enabled.
	*
	* @param developerEntries the developer entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.DeveloperEntry> developerEntries);

	/**
	* Creates a new developer entry with the primary key. Does not add the developer entry to the database.
	*
	* @param developerEntryId the primary key for the new developer entry
	* @return the new developer entry
	*/
	public com.liferay.osb.model.DeveloperEntry create(long developerEntryId);

	/**
	* Removes the developer entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param developerEntryId the primary key of the developer entry
	* @return the developer entry that was removed
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry remove(long developerEntryId)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.DeveloperEntry updateImpl(
		com.liferay.osb.model.DeveloperEntry developerEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the developer entry with the primary key or throws a {@link com.liferay.osb.NoSuchDeveloperEntryException} if it could not be found.
	*
	* @param developerEntryId the primary key of the developer entry
	* @return the developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry findByPrimaryKey(
		long developerEntryId)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the developer entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param developerEntryId the primary key of the developer entry
	* @return the developer entry, or <code>null</code> if a developer entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByPrimaryKey(
		long developerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the developer entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the developer entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @return the range of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the developer entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first developer entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first developer entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching developer entry, or <code>null</code> if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last developer entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last developer entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching developer entry, or <code>null</code> if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the developer entries before and after the current developer entry in the ordered set where userId = &#63;.
	*
	* @param developerEntryId the primary key of the current developer entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry[] findByUserId_PrevAndNext(
		long developerEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the developer entry where dossieraAccountKey = &#63; or throws a {@link com.liferay.osb.NoSuchDeveloperEntryException} if it could not be found.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the matching developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry findByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the developer entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the matching developer entry, or <code>null</code> if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the developer entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossieraAccountKey the dossiera account key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching developer entry, or <code>null</code> if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByDossieraAccountKey(
		java.lang.String dossieraAccountKey, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the developer entries where status = &#63;.
	*
	* @param status the status
	* @return the matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByStatus(
		int status) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the developer entries where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @return the range of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByStatus(
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the developer entries where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByStatus(
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first developer entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry findByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first developer entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching developer entry, or <code>null</code> if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByStatus_First(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last developer entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry findByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last developer entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching developer entry, or <code>null</code> if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the developer entries before and after the current developer entry in the ordered set where status = &#63;.
	*
	* @param developerEntryId the primary key of the current developer entry
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry[] findByStatus_PrevAndNext(
		long developerEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the developer entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByUI_T(
		long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the developer entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @return the range of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByUI_T(
		long userId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the developer entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByUI_T(
		long userId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first developer entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry findByUI_T_First(long userId,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first developer entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching developer entry, or <code>null</code> if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByUI_T_First(long userId,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last developer entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry findByUI_T_Last(long userId,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last developer entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching developer entry, or <code>null</code> if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByUI_T_Last(long userId,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the developer entries before and after the current developer entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param developerEntryId the primary key of the current developer entry
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry[] findByUI_T_PrevAndNext(
		long developerEntryId, long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the developer entries where domainName = &#63; and domainStatus = &#63;.
	*
	* @param domainName the domain name
	* @param domainStatus the domain status
	* @return the matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByDN_DS(
		java.lang.String domainName, int domainStatus)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the developer entries where domainName = &#63; and domainStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param domainName the domain name
	* @param domainStatus the domain status
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @return the range of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByDN_DS(
		java.lang.String domainName, int domainStatus, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the developer entries where domainName = &#63; and domainStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param domainName the domain name
	* @param domainStatus the domain status
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByDN_DS(
		java.lang.String domainName, int domainStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first developer entry in the ordered set where domainName = &#63; and domainStatus = &#63;.
	*
	* @param domainName the domain name
	* @param domainStatus the domain status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry findByDN_DS_First(
		java.lang.String domainName, int domainStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first developer entry in the ordered set where domainName = &#63; and domainStatus = &#63;.
	*
	* @param domainName the domain name
	* @param domainStatus the domain status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching developer entry, or <code>null</code> if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByDN_DS_First(
		java.lang.String domainName, int domainStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last developer entry in the ordered set where domainName = &#63; and domainStatus = &#63;.
	*
	* @param domainName the domain name
	* @param domainStatus the domain status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry findByDN_DS_Last(
		java.lang.String domainName, int domainStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last developer entry in the ordered set where domainName = &#63; and domainStatus = &#63;.
	*
	* @param domainName the domain name
	* @param domainStatus the domain status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching developer entry, or <code>null</code> if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByDN_DS_Last(
		java.lang.String domainName, int domainStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the developer entries before and after the current developer entry in the ordered set where domainName = &#63; and domainStatus = &#63;.
	*
	* @param developerEntryId the primary key of the current developer entry
	* @param domainName the domain name
	* @param domainStatus the domain status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry[] findByDN_DS_PrevAndNext(
		long developerEntryId, java.lang.String domainName, int domainStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the developer entries where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @return the matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByT_S(
		int type, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the developer entries where type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @return the range of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByT_S(
		int type, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the developer entries where type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByT_S(
		int type, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first developer entry in the ordered set where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry findByT_S_First(int type,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first developer entry in the ordered set where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching developer entry, or <code>null</code> if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByT_S_First(int type,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last developer entry in the ordered set where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry findByT_S_Last(int type,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last developer entry in the ordered set where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching developer entry, or <code>null</code> if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByT_S_Last(int type,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the developer entries before and after the current developer entry in the ordered set where type = &#63; and status = &#63;.
	*
	* @param developerEntryId the primary key of the current developer entry
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry[] findByT_S_PrevAndNext(
		long developerEntryId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the developer entries where userId = &#63; and type = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param status the status
	* @return the matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByUI_T_S(
		long userId, int type, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the developer entries where userId = &#63; and type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @return the range of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByUI_T_S(
		long userId, int type, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the developer entries where userId = &#63; and type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByUI_T_S(
		long userId, int type, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first developer entry in the ordered set where userId = &#63; and type = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry findByUI_T_S_First(
		long userId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first developer entry in the ordered set where userId = &#63; and type = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching developer entry, or <code>null</code> if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByUI_T_S_First(
		long userId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last developer entry in the ordered set where userId = &#63; and type = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry findByUI_T_S_Last(long userId,
		int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last developer entry in the ordered set where userId = &#63; and type = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching developer entry, or <code>null</code> if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByUI_T_S_Last(
		long userId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the developer entries before and after the current developer entry in the ordered set where userId = &#63; and type = &#63; and status = &#63;.
	*
	* @param developerEntryId the primary key of the current developer entry
	* @param userId the user ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry[] findByUI_T_S_PrevAndNext(
		long developerEntryId, long userId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the developer entries where legalEntityName = &#63; and type = &#63; and status = &#63;.
	*
	* @param legalEntityName the legal entity name
	* @param type the type
	* @param status the status
	* @return the matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByLEN_T_S(
		java.lang.String legalEntityName, int type, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the developer entries where legalEntityName = &#63; and type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param legalEntityName the legal entity name
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @return the range of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByLEN_T_S(
		java.lang.String legalEntityName, int type, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the developer entries where legalEntityName = &#63; and type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param legalEntityName the legal entity name
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findByLEN_T_S(
		java.lang.String legalEntityName, int type, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first developer entry in the ordered set where legalEntityName = &#63; and type = &#63; and status = &#63;.
	*
	* @param legalEntityName the legal entity name
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry findByLEN_T_S_First(
		java.lang.String legalEntityName, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first developer entry in the ordered set where legalEntityName = &#63; and type = &#63; and status = &#63;.
	*
	* @param legalEntityName the legal entity name
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching developer entry, or <code>null</code> if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByLEN_T_S_First(
		java.lang.String legalEntityName, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last developer entry in the ordered set where legalEntityName = &#63; and type = &#63; and status = &#63;.
	*
	* @param legalEntityName the legal entity name
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry findByLEN_T_S_Last(
		java.lang.String legalEntityName, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last developer entry in the ordered set where legalEntityName = &#63; and type = &#63; and status = &#63;.
	*
	* @param legalEntityName the legal entity name
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching developer entry, or <code>null</code> if a matching developer entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry fetchByLEN_T_S_Last(
		java.lang.String legalEntityName, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the developer entries before and after the current developer entry in the ordered set where legalEntityName = &#63; and type = &#63; and status = &#63;.
	*
	* @param developerEntryId the primary key of the current developer entry
	* @param legalEntityName the legal entity name
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next developer entry
	* @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry[] findByLEN_T_S_PrevAndNext(
		long developerEntryId, java.lang.String legalEntityName, int type,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the developer entries.
	*
	* @return the developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the developer entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @return the range of developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the developer entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the developer entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the developer entry where dossieraAccountKey = &#63; from the database.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the developer entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry removeByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.osb.NoSuchDeveloperEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the developer entries where status = &#63; from the database.
	*
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the developer entries where userId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUI_T(long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the developer entries where domainName = &#63; and domainStatus = &#63; from the database.
	*
	* @param domainName the domain name
	* @param domainStatus the domain status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByDN_DS(java.lang.String domainName, int domainStatus)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the developer entries where type = &#63; and status = &#63; from the database.
	*
	* @param type the type
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByT_S(int type, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the developer entries where userId = &#63; and type = &#63; and status = &#63; from the database.
	*
	* @param userId the user ID
	* @param type the type
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUI_T_S(long userId, int type, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the developer entries where legalEntityName = &#63; and type = &#63; and status = &#63; from the database.
	*
	* @param legalEntityName the legal entity name
	* @param type the type
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByLEN_T_S(java.lang.String legalEntityName, int type,
		int status) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the developer entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of developer entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of developer entries where dossieraAccountKey = &#63;.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the number of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByDossieraAccountKey(java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of developer entries where status = &#63;.
	*
	* @param status the status
	* @return the number of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of developer entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the number of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUI_T(long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of developer entries where domainName = &#63; and domainStatus = &#63;.
	*
	* @param domainName the domain name
	* @param domainStatus the domain status
	* @return the number of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByDN_DS(java.lang.String domainName, int domainStatus)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of developer entries where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @return the number of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByT_S(int type, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of developer entries where userId = &#63; and type = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param status the status
	* @return the number of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUI_T_S(long userId, int type, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of developer entries where legalEntityName = &#63; and type = &#63; and status = &#63;.
	*
	* @param legalEntityName the legal entity name
	* @param type the type
	* @param status the status
	* @return the number of matching developer entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByLEN_T_S(java.lang.String legalEntityName, int type,
		int status) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of developer entries.
	*
	* @return the number of developer entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}