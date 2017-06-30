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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the app entry service. This utility wraps {@link AppEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppEntryPersistence
 * @see AppEntryPersistenceImpl
 * @generated
 */
public class AppEntryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(AppEntry appEntry) {
		getPersistence().clearCache(appEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AppEntry> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AppEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AppEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AppEntry update(AppEntry appEntry, boolean merge)
		throws SystemException {
		return getPersistence().update(appEntry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AppEntry update(AppEntry appEntry, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(appEntry, merge, serviceContext);
	}

	/**
	* Caches the app entry in the entity cache if it is enabled.
	*
	* @param appEntry the app entry
	*/
	public static void cacheResult(com.liferay.osb.model.AppEntry appEntry) {
		getPersistence().cacheResult(appEntry);
	}

	/**
	* Caches the app entries in the entity cache if it is enabled.
	*
	* @param appEntries the app entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AppEntry> appEntries) {
		getPersistence().cacheResult(appEntries);
	}

	/**
	* Creates a new app entry with the primary key. Does not add the app entry to the database.
	*
	* @param appEntryId the primary key for the new app entry
	* @return the new app entry
	*/
	public static com.liferay.osb.model.AppEntry create(long appEntryId) {
		return getPersistence().create(appEntryId);
	}

	/**
	* Removes the app entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appEntryId the primary key of the app entry
	* @return the app entry that was removed
	* @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry remove(long appEntryId)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(appEntryId);
	}

	public static com.liferay.osb.model.AppEntry updateImpl(
		com.liferay.osb.model.AppEntry appEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(appEntry, merge);
	}

	/**
	* Returns the app entry with the primary key or throws a {@link com.liferay.osb.NoSuchAppEntryException} if it could not be found.
	*
	* @param appEntryId the primary key of the app entry
	* @return the app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry findByPrimaryKey(
		long appEntryId)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(appEntryId);
	}

	/**
	* Returns the app entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appEntryId the primary key of the app entry
	* @return the app entry, or <code>null</code> if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry fetchByPrimaryKey(
		long appEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(appEntryId);
	}

	/**
	* Returns all the app entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppEntry> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first app entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first app entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last app entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last app entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

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
	public static com.liferay.osb.model.AppEntry[] findByUuid_PrevAndNext(
		long appEntryId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(appEntryId, uuid, orderByComparator);
	}

	/**
	* Returns all the app entries that the user has permission to view where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppEntry> filterFindByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByUuid(uuid);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> filterFindByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByUuid(uuid, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> filterFindByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByUuid(uuid, start, end, orderByComparator);
	}

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
	public static com.liferay.osb.model.AppEntry[] filterFindByUuid_PrevAndNext(
		long appEntryId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByUuid_PrevAndNext(appEntryId, uuid,
			orderByComparator);
	}

	/**
	* Returns all the app entries where developerEntryId = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @return the matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppEntry> findByDeveloperEntryId(
		long developerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByDeveloperEntryId(developerEntryId);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> findByDeveloperEntryId(
		long developerEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByDeveloperEntryId(developerEntryId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> findByDeveloperEntryId(
		long developerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByDeveloperEntryId(developerEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first app entry in the ordered set where developerEntryId = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry findByDeveloperEntryId_First(
		long developerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByDeveloperEntryId_First(developerEntryId,
			orderByComparator);
	}

	/**
	* Returns the first app entry in the ordered set where developerEntryId = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry fetchByDeveloperEntryId_First(
		long developerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByDeveloperEntryId_First(developerEntryId,
			orderByComparator);
	}

	/**
	* Returns the last app entry in the ordered set where developerEntryId = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry findByDeveloperEntryId_Last(
		long developerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByDeveloperEntryId_Last(developerEntryId,
			orderByComparator);
	}

	/**
	* Returns the last app entry in the ordered set where developerEntryId = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry fetchByDeveloperEntryId_Last(
		long developerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByDeveloperEntryId_Last(developerEntryId,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AppEntry[] findByDeveloperEntryId_PrevAndNext(
		long appEntryId, long developerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByDeveloperEntryId_PrevAndNext(appEntryId,
			developerEntryId, orderByComparator);
	}

	/**
	* Returns all the app entries that the user has permission to view where developerEntryId = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @return the matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppEntry> filterFindByDeveloperEntryId(
		long developerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByDeveloperEntryId(developerEntryId);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> filterFindByDeveloperEntryId(
		long developerEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByDeveloperEntryId(developerEntryId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> filterFindByDeveloperEntryId(
		long developerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByDeveloperEntryId(developerEntryId, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AppEntry[] filterFindByDeveloperEntryId_PrevAndNext(
		long appEntryId, long developerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByDeveloperEntryId_PrevAndNext(appEntryId,
			developerEntryId, orderByComparator);
	}

	/**
	* Returns the app entry where title = &#63; or throws a {@link com.liferay.osb.NoSuchAppEntryException} if it could not be found.
	*
	* @param title the title
	* @return the matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry findByTitle(
		java.lang.String title)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTitle(title);
	}

	/**
	* Returns the app entry where title = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param title the title
	* @return the matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry fetchByTitle(
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTitle(title);
	}

	/**
	* Returns the app entry where title = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param title the title
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry fetchByTitle(
		java.lang.String title, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTitle(title, retrieveFromCache);
	}

	/**
	* Returns all the app entries where status = &#63;.
	*
	* @param status the status
	* @return the matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppEntry> findByStatus(
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus(status);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> findByStatus(
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus(status, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> findByStatus(
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByStatus(status, start, end, orderByComparator);
	}

	/**
	* Returns the first app entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry findByStatus_First(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the first app entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry fetchByStatus_First(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the last app entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry
	* @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry findByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the last app entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry fetchByStatus_Last(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByStatus_Last(status, orderByComparator);
	}

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
	public static com.liferay.osb.model.AppEntry[] findByStatus_PrevAndNext(
		long appEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByStatus_PrevAndNext(appEntryId, status,
			orderByComparator);
	}

	/**
	* Returns all the app entries that the user has permission to view where status = &#63;.
	*
	* @param status the status
	* @return the matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppEntry> filterFindByStatus(
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByStatus(status);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> filterFindByStatus(
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByStatus(status, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> filterFindByStatus(
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByStatus(status, start, end, orderByComparator);
	}

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
	public static com.liferay.osb.model.AppEntry[] filterFindByStatus_PrevAndNext(
		long appEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByStatus_PrevAndNext(appEntryId, status,
			orderByComparator);
	}

	/**
	* Returns all the app entries where developerEntryId = &#63; and title LIKE &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @return the matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppEntry> findByDEI_LikeT(
		long developerEntryId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByDEI_LikeT(developerEntryId, title);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> findByDEI_LikeT(
		long developerEntryId, java.lang.String title, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByDEI_LikeT(developerEntryId, title, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> findByDEI_LikeT(
		long developerEntryId, java.lang.String title, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByDEI_LikeT(developerEntryId, title, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AppEntry findByDEI_LikeT_First(
		long developerEntryId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByDEI_LikeT_First(developerEntryId, title,
			orderByComparator);
	}

	/**
	* Returns the first app entry in the ordered set where developerEntryId = &#63; and title LIKE &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry fetchByDEI_LikeT_First(
		long developerEntryId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByDEI_LikeT_First(developerEntryId, title,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AppEntry findByDEI_LikeT_Last(
		long developerEntryId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByDEI_LikeT_Last(developerEntryId, title,
			orderByComparator);
	}

	/**
	* Returns the last app entry in the ordered set where developerEntryId = &#63; and title LIKE &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry fetchByDEI_LikeT_Last(
		long developerEntryId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByDEI_LikeT_Last(developerEntryId, title,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AppEntry[] findByDEI_LikeT_PrevAndNext(
		long appEntryId, long developerEntryId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByDEI_LikeT_PrevAndNext(appEntryId, developerEntryId,
			title, orderByComparator);
	}

	/**
	* Returns all the app entries that the user has permission to view where developerEntryId = &#63; and title LIKE &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @return the matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppEntry> filterFindByDEI_LikeT(
		long developerEntryId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByDEI_LikeT(developerEntryId, title);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> filterFindByDEI_LikeT(
		long developerEntryId, java.lang.String title, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByDEI_LikeT(developerEntryId, title, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> filterFindByDEI_LikeT(
		long developerEntryId, java.lang.String title, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByDEI_LikeT(developerEntryId, title, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AppEntry[] filterFindByDEI_LikeT_PrevAndNext(
		long appEntryId, long developerEntryId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByDEI_LikeT_PrevAndNext(appEntryId,
			developerEntryId, title, orderByComparator);
	}

	/**
	* Returns all the app entries where developerEntryId = &#63; and status = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @return the matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppEntry> findByDEI_S(
		long developerEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByDEI_S(developerEntryId, status);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> findByDEI_S(
		long developerEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByDEI_S(developerEntryId, status, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> findByDEI_S(
		long developerEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByDEI_S(developerEntryId, status, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AppEntry findByDEI_S_First(
		long developerEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByDEI_S_First(developerEntryId, status,
			orderByComparator);
	}

	/**
	* Returns the first app entry in the ordered set where developerEntryId = &#63; and status = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry fetchByDEI_S_First(
		long developerEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByDEI_S_First(developerEntryId, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AppEntry findByDEI_S_Last(
		long developerEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByDEI_S_Last(developerEntryId, status, orderByComparator);
	}

	/**
	* Returns the last app entry in the ordered set where developerEntryId = &#63; and status = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry, or <code>null</code> if a matching app entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry fetchByDEI_S_Last(
		long developerEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByDEI_S_Last(developerEntryId, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AppEntry[] findByDEI_S_PrevAndNext(
		long appEntryId, long developerEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByDEI_S_PrevAndNext(appEntryId, developerEntryId,
			status, orderByComparator);
	}

	/**
	* Returns all the app entries that the user has permission to view where developerEntryId = &#63; and status = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @return the matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppEntry> filterFindByDEI_S(
		long developerEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByDEI_S(developerEntryId, status);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> filterFindByDEI_S(
		long developerEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByDEI_S(developerEntryId, status, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> filterFindByDEI_S(
		long developerEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByDEI_S(developerEntryId, status, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AppEntry[] filterFindByDEI_S_PrevAndNext(
		long appEntryId, long developerEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByDEI_S_PrevAndNext(appEntryId, developerEntryId,
			status, orderByComparator);
	}

	/**
	* Returns all the app entries.
	*
	* @return the app entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the app entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes all the app entries where developerEntryId = &#63; from the database.
	*
	* @param developerEntryId the developer entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByDeveloperEntryId(long developerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByDeveloperEntryId(developerEntryId);
	}

	/**
	* Removes the app entry where title = &#63; from the database.
	*
	* @param title the title
	* @return the app entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry removeByTitle(
		java.lang.String title)
		throws com.liferay.osb.NoSuchAppEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByTitle(title);
	}

	/**
	* Removes all the app entries where status = &#63; from the database.
	*
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByStatus(status);
	}

	/**
	* Removes all the app entries where developerEntryId = &#63; and title LIKE &#63; from the database.
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByDEI_LikeT(long developerEntryId,
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByDEI_LikeT(developerEntryId, title);
	}

	/**
	* Removes all the app entries where developerEntryId = &#63; and status = &#63; from the database.
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByDEI_S(long developerEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByDEI_S(developerEntryId, status);
	}

	/**
	* Removes all the app entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of app entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of app entries that the user has permission to view where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByUuid(uuid);
	}

	/**
	* Returns the number of app entries where developerEntryId = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @return the number of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByDeveloperEntryId(long developerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByDeveloperEntryId(developerEntryId);
	}

	/**
	* Returns the number of app entries that the user has permission to view where developerEntryId = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @return the number of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByDeveloperEntryId(long developerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByDeveloperEntryId(developerEntryId);
	}

	/**
	* Returns the number of app entries where title = &#63;.
	*
	* @param title the title
	* @return the number of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTitle(java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTitle(title);
	}

	/**
	* Returns the number of app entries where status = &#63;.
	*
	* @param status the status
	* @return the number of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByStatus(status);
	}

	/**
	* Returns the number of app entries that the user has permission to view where status = &#63;.
	*
	* @param status the status
	* @return the number of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByStatus(status);
	}

	/**
	* Returns the number of app entries where developerEntryId = &#63; and title LIKE &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @return the number of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByDEI_LikeT(long developerEntryId,
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByDEI_LikeT(developerEntryId, title);
	}

	/**
	* Returns the number of app entries that the user has permission to view where developerEntryId = &#63; and title LIKE &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param title the title
	* @return the number of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByDEI_LikeT(long developerEntryId,
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByDEI_LikeT(developerEntryId, title);
	}

	/**
	* Returns the number of app entries where developerEntryId = &#63; and status = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @return the number of matching app entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByDEI_S(long developerEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByDEI_S(developerEntryId, status);
	}

	/**
	* Returns the number of app entries that the user has permission to view where developerEntryId = &#63; and status = &#63;.
	*
	* @param developerEntryId the developer entry ID
	* @param status the status
	* @return the number of matching app entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByDEI_S(long developerEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByDEI_S(developerEntryId, status);
	}

	/**
	* Returns the number of app entries.
	*
	* @return the number of app entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AppEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AppEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AppEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(AppEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AppEntryPersistence persistence) {
	}

	private static AppEntryPersistence _persistence;
}