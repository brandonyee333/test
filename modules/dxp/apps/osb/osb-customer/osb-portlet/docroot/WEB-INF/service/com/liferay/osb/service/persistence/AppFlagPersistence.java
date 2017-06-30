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

import com.liferay.osb.model.AppFlag;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the app flag service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppFlagPersistenceImpl
 * @see AppFlagUtil
 * @generated
 */
public interface AppFlagPersistence extends BasePersistence<AppFlag> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AppFlagUtil} to access the app flag persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the app flag in the entity cache if it is enabled.
	*
	* @param appFlag the app flag
	*/
	public void cacheResult(com.liferay.osb.model.AppFlag appFlag);

	/**
	* Caches the app flags in the entity cache if it is enabled.
	*
	* @param appFlags the app flags
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AppFlag> appFlags);

	/**
	* Creates a new app flag with the primary key. Does not add the app flag to the database.
	*
	* @param appFlagId the primary key for the new app flag
	* @return the new app flag
	*/
	public com.liferay.osb.model.AppFlag create(long appFlagId);

	/**
	* Removes the app flag with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appFlagId the primary key of the app flag
	* @return the app flag that was removed
	* @throws com.liferay.osb.NoSuchAppFlagException if a app flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag remove(long appFlagId)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppFlag updateImpl(
		com.liferay.osb.model.AppFlag appFlag, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app flag with the primary key or throws a {@link com.liferay.osb.NoSuchAppFlagException} if it could not be found.
	*
	* @param appFlagId the primary key of the app flag
	* @return the app flag
	* @throws com.liferay.osb.NoSuchAppFlagException if a app flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag findByPrimaryKey(long appFlagId)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app flag with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appFlagId the primary key of the app flag
	* @return the app flag, or <code>null</code> if a app flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag fetchByPrimaryKey(long appFlagId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app flags where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching app flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppFlag> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app flags where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of app flags
	* @param end the upper bound of the range of app flags (not inclusive)
	* @return the range of matching app flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppFlag> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app flags where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of app flags
	* @param end the upper bound of the range of app flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppFlag> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app flag in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app flag
	* @throws com.liferay.osb.NoSuchAppFlagException if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app flag in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app flag, or <code>null</code> if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app flag in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app flag
	* @throws com.liferay.osb.NoSuchAppFlagException if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app flag in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app flag, or <code>null</code> if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app flags before and after the current app flag in the ordered set where uuid = &#63;.
	*
	* @param appFlagId the primary key of the current app flag
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app flag
	* @throws com.liferay.osb.NoSuchAppFlagException if a app flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag[] findByUuid_PrevAndNext(
		long appFlagId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app flags where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @return the matching app flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppFlag> findByAppVersionId(
		long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app flags where appVersionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appVersionId the app version ID
	* @param start the lower bound of the range of app flags
	* @param end the upper bound of the range of app flags (not inclusive)
	* @return the range of matching app flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppFlag> findByAppVersionId(
		long appVersionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app flags where appVersionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appVersionId the app version ID
	* @param start the lower bound of the range of app flags
	* @param end the upper bound of the range of app flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppFlag> findByAppVersionId(
		long appVersionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app flag in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app flag
	* @throws com.liferay.osb.NoSuchAppFlagException if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag findByAppVersionId_First(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app flag in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app flag, or <code>null</code> if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag fetchByAppVersionId_First(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app flag in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app flag
	* @throws com.liferay.osb.NoSuchAppFlagException if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag findByAppVersionId_Last(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app flag in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app flag, or <code>null</code> if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag fetchByAppVersionId_Last(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app flags before and after the current app flag in the ordered set where appVersionId = &#63;.
	*
	* @param appFlagId the primary key of the current app flag
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app flag
	* @throws com.liferay.osb.NoSuchAppFlagException if a app flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag[] findByAppVersionId_PrevAndNext(
		long appFlagId, long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app flag where appVersionId = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchAppFlagException} if it could not be found.
	*
	* @param appVersionId the app version ID
	* @param type the type
	* @return the matching app flag
	* @throws com.liferay.osb.NoSuchAppFlagException if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag findByAVI_T(long appVersionId, int type)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app flag where appVersionId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param appVersionId the app version ID
	* @param type the type
	* @return the matching app flag, or <code>null</code> if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag fetchByAVI_T(long appVersionId,
		int type) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app flag where appVersionId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param appVersionId the app version ID
	* @param type the type
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching app flag, or <code>null</code> if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag fetchByAVI_T(long appVersionId,
		int type, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app flags.
	*
	* @return the app flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppFlag> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app flags.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app flags
	* @param end the upper bound of the range of app flags (not inclusive)
	* @return the range of app flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppFlag> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app flags.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app flags
	* @param end the upper bound of the range of app flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of app flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppFlag> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app flags where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app flags where appVersionId = &#63; from the database.
	*
	* @param appVersionId the app version ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAppVersionId(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the app flag where appVersionId = &#63; and type = &#63; from the database.
	*
	* @param appVersionId the app version ID
	* @param type the type
	* @return the app flag that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag removeByAVI_T(long appVersionId,
		int type)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app flags from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app flags where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching app flags
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app flags where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @return the number of matching app flags
	* @throws SystemException if a system exception occurred
	*/
	public int countByAppVersionId(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app flags where appVersionId = &#63; and type = &#63;.
	*
	* @param appVersionId the app version ID
	* @param type the type
	* @return the number of matching app flags
	* @throws SystemException if a system exception occurred
	*/
	public int countByAVI_T(long appVersionId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app flags.
	*
	* @return the number of app flags
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}