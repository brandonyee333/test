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

import com.liferay.osb.model.AppEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the app entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppEntryPersistenceImpl
 * @see AppEntryUtil
 * @generated
 */
public interface AppEntryPersistence extends BasePersistence<AppEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AppEntryUtil} to access the app entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the app entry in the entity cache if it is enabled.
	*
	* @param appEntry the app entry
	*/
	public void cacheResult(com.liferay.osb.model.AppEntry appEntry);

	/**
	* Caches the app entries in the entity cache if it is enabled.
	*
	* @param appEntries the app entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AppEntry> appEntries);

	/**
	* Creates a new app entry with the primary key. Does not add the app entry to the database.
	*
	* @param appEntryId the primary key for the new app entry
	* @return the new app entry
	*/
	public com.liferay.osb.model.AppEntry create(long appEntryId);

	/**
	* Removes the app entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appEntryId the primary key of the app entry
	* @return the app entry that was removed
	* @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry remove(long appEntryId)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppEntry updateImpl(
		com.liferay.osb.model.AppEntry appEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entry with the primary key or throws a {@link com.liferay.osb.NoSuchAppEntryException} if it could not be found.
	*
	* @param appEntryId the primary key of the app entry
	* @return the app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry findByPrimaryKey(long appEntryId)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appEntryId the primary key of the app entry
	* @return the app entry, or <code>null</code> if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry fetchByPrimaryKey(long appEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @return the range of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entries before and after the current app entry in the ordered set where uuid = &#63;.
	*
	* @param appEntryId the primary key of the current app entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry[] findByUuid_PrevAndNext(
		long appEntryId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app entries that the user has permission to view where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> filterFindByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app entries that the user has permission to view where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @return the range of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> filterFindByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app entries that the user has permissions to view where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> filterFindByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entries before and after the current app entry in the ordered set of app entries that the user has permission to view where uuid = &#63;.
	*
	* @param appEntryId the primary key of the current app entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry[] filterFindByUuid_PrevAndNext(
		long appEntryId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app entries where developerEntryId = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @return the matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findByDeveloperEntryId(
		long developerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app entries where developerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param developerEntryId the developer entry ID
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @return the range of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findByDeveloperEntryId(
		long developerEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app entries where developerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param developerEntryId the developer entry ID
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findByDeveloperEntryId(
		long developerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app entry in the ordered set where developerEntryId = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry findByDeveloperEntryId_First(
		long developerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app entry in the ordered set where developerEntryId = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry fetchByDeveloperEntryId_First(
		long developerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app entry in the ordered set where developerEntryId = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry findByDeveloperEntryId_Last(
		long developerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app entry in the ordered set where developerEntryId = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry fetchByDeveloperEntryId_Last(
		long developerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entries before and after the current app entry in the ordered set where developerEntryId = &#63;.
	*
	* @param appEntryId the primary key of the current app entry
	* @param developerEntryId the developer entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry[] findByDeveloperEntryId_PrevAndNext(
		long appEntryId, long developerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app entries that the user has permission to view where developerEntryId = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @return the matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> filterFindByDeveloperEntryId(
		long developerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app entries that the user has permission to view where developerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param developerEntryId the developer entry ID
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @return the range of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> filterFindByDeveloperEntryId(
		long developerEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app entries that the user has permissions to view where developerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param developerEntryId the developer entry ID
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> filterFindByDeveloperEntryId(
		long developerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entries before and after the current app entry in the ordered set of app entries that the user has permission to view where developerEntryId = &#63;.
	*
	* @param appEntryId the primary key of the current app entry
	* @param developerEntryId the developer entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry[] filterFindByDeveloperEntryId_PrevAndNext(
		long appEntryId, long developerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entry where title = &#63; or throws a {@link com.liferay.osb.NoSuchAppEntryException} if it could not be found.
	*
	* @param title the title
	* @return the matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry findByTitle(java.lang.String title)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entry where title = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param title the title
	* @return the matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry fetchByTitle(java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entry where title = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param title the title
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry fetchByTitle(java.lang.String title,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app entries where status = &#63;.
	*
	* @param status the status
	* @return the matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findByStatus(
		int status) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app entries where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @return the range of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findByStatus(
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app entries where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findByStatus(
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry findByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry fetchByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry findByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry fetchByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entries before and after the current app entry in the ordered set where status = &#63;.
	*
	* @param appEntryId the primary key of the current app entry
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry[] findByStatus_PrevAndNext(
		long appEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app entries that the user has permission to view where status = &#63;.
	*
	* @param status the status
	* @return the matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> filterFindByStatus(
		int status) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app entries that the user has permission to view where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @return the range of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> filterFindByStatus(
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app entries that the user has permissions to view where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> filterFindByStatus(
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entries before and after the current app entry in the ordered set of app entries that the user has permission to view where status = &#63;.
	*
	* @param appEntryId the primary key of the current app entry
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry[] filterFindByStatus_PrevAndNext(
		long appEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app entries where developerEntryId = &#63; and title LIKE &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @return the matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findByDEI_LikeT(
		long developerEntryId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app entries where developerEntryId = &#63; and title LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @return the range of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findByDEI_LikeT(
		long developerEntryId, java.lang.String title, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app entries where developerEntryId = &#63; and title LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findByDEI_LikeT(
		long developerEntryId, java.lang.String title, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app entry in the ordered set where developerEntryId = &#63; and title LIKE &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry findByDEI_LikeT_First(
		long developerEntryId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app entry in the ordered set where developerEntryId = &#63; and title LIKE &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry fetchByDEI_LikeT_First(
		long developerEntryId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app entry in the ordered set where developerEntryId = &#63; and title LIKE &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry findByDEI_LikeT_Last(
		long developerEntryId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app entry in the ordered set where developerEntryId = &#63; and title LIKE &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry fetchByDEI_LikeT_Last(
		long developerEntryId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entries before and after the current app entry in the ordered set where developerEntryId = &#63; and title LIKE &#63;.
	*
	* @param appEntryId the primary key of the current app entry
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry[] findByDEI_LikeT_PrevAndNext(
		long appEntryId, long developerEntryId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app entries that the user has permission to view where developerEntryId = &#63; and title LIKE &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @return the matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> filterFindByDEI_LikeT(
		long developerEntryId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app entries that the user has permission to view where developerEntryId = &#63; and title LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @return the range of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> filterFindByDEI_LikeT(
		long developerEntryId, java.lang.String title, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app entries that the user has permissions to view where developerEntryId = &#63; and title LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> filterFindByDEI_LikeT(
		long developerEntryId, java.lang.String title, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entries before and after the current app entry in the ordered set of app entries that the user has permission to view where developerEntryId = &#63; and title LIKE &#63;.
	*
	* @param appEntryId the primary key of the current app entry
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry[] filterFindByDEI_LikeT_PrevAndNext(
		long appEntryId, long developerEntryId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app entries where developerEntryId = &#63; and status = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @return the matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findByDEI_S(
		long developerEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app entries where developerEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @return the range of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findByDEI_S(
		long developerEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app entries where developerEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findByDEI_S(
		long developerEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app entry in the ordered set where developerEntryId = &#63; and status = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry findByDEI_S_First(
		long developerEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app entry in the ordered set where developerEntryId = &#63; and status = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry fetchByDEI_S_First(
		long developerEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app entry in the ordered set where developerEntryId = &#63; and status = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry findByDEI_S_Last(
		long developerEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app entry in the ordered set where developerEntryId = &#63; and status = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry fetchByDEI_S_Last(
		long developerEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entries before and after the current app entry in the ordered set where developerEntryId = &#63; and status = &#63;.
	*
	* @param appEntryId the primary key of the current app entry
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry[] findByDEI_S_PrevAndNext(
		long appEntryId, long developerEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app entries that the user has permission to view where developerEntryId = &#63; and status = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @return the matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> filterFindByDEI_S(
		long developerEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app entries that the user has permission to view where developerEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @return the range of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> filterFindByDEI_S(
		long developerEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app entries that the user has permissions to view where developerEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> filterFindByDEI_S(
		long developerEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entries before and after the current app entry in the ordered set of app entries that the user has permission to view where developerEntryId = &#63; and status = &#63;.
	*
	* @param appEntryId the primary key of the current app entry
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry[] filterFindByDEI_S_PrevAndNext(
		long appEntryId, long developerEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app entries.
	*
	* @return the app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @return the range of app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of app entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntry> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app entries where developerEntryId = &#63; from the database.
	*
	* @param developerEntryId the developer entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByDeveloperEntryId(long developerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the app entry where title = &#63; from the database.
	*
	* @param title the title
	* @return the app entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry removeByTitle(java.lang.String title)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app entries where status = &#63; from the database.
	*
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app entries where developerEntryId = &#63; and title LIKE &#63; from the database.
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @throws SystemException if a system exception occurred
	*/
	public void removeByDEI_LikeT(long developerEntryId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app entries where developerEntryId = &#63; and status = &#63; from the database.
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByDEI_S(long developerEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entries that the user has permission to view where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entries where developerEntryId = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @return the number of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByDeveloperEntryId(long developerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entries that the user has permission to view where developerEntryId = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @return the number of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByDeveloperEntryId(long developerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entries where title = &#63;.
	*
	* @param title the title
	* @return the number of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByTitle(java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entries where status = &#63;.
	*
	* @param status the status
	* @return the number of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entries that the user has permission to view where status = &#63;.
	*
	* @param status the status
	* @return the number of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entries where developerEntryId = &#63; and title LIKE &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @return the number of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByDEI_LikeT(long developerEntryId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entries that the user has permission to view where developerEntryId = &#63; and title LIKE &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @return the number of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByDEI_LikeT(long developerEntryId,
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entries where developerEntryId = &#63; and status = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @return the number of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByDEI_S(long developerEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entries that the user has permission to view where developerEntryId = &#63; and status = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @return the number of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByDEI_S(long developerEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entries.
	*
	* @return the number of app entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}