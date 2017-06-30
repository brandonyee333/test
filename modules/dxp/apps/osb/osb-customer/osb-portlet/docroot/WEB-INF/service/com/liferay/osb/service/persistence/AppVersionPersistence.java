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

import com.liferay.osb.model.AppVersion;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the app version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppVersionPersistenceImpl
 * @see AppVersionUtil
 * @generated
 */
public interface AppVersionPersistence extends BasePersistence<AppVersion> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AppVersionUtil} to access the app version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the app version in the entity cache if it is enabled.
	*
	* @param appVersion the app version
	*/
	public void cacheResult(com.liferay.osb.model.AppVersion appVersion);

	/**
	* Caches the app versions in the entity cache if it is enabled.
	*
	* @param appVersions the app versions
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AppVersion> appVersions);

	/**
	* Creates a new app version with the primary key. Does not add the app version to the database.
	*
	* @param appVersionId the primary key for the new app version
	* @return the new app version
	*/
	public com.liferay.osb.model.AppVersion create(long appVersionId);

	/**
	* Removes the app version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appVersionId the primary key of the app version
	* @return the app version that was removed
	* @throws com.liferay.osb.NoSuchAppVersionException if a app version with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion remove(long appVersionId)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppVersion updateImpl(
		com.liferay.osb.model.AppVersion appVersion, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app version with the primary key or throws a {@link com.liferay.osb.NoSuchAppVersionException} if it could not be found.
	*
	* @param appVersionId the primary key of the app version
	* @return the app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a app version with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion findByPrimaryKey(long appVersionId)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app version with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appVersionId the primary key of the app version
	* @return the app version, or <code>null</code> if a app version with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion fetchByPrimaryKey(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app versions where appEntryId = &#63;.
	*
	* @param appEntryId the app entry ID
	* @return the matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findByAppEntryId(
		long appEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app versions where appEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appEntryId the app entry ID
	* @param start the lower bound of the range of app versions
	* @param end the upper bound of the range of app versions (not inclusive)
	* @return the range of matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findByAppEntryId(
		long appEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app versions where appEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appEntryId the app entry ID
	* @param start the lower bound of the range of app versions
	* @param end the upper bound of the range of app versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findByAppEntryId(
		long appEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app version in the ordered set where appEntryId = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion findByAppEntryId_First(
		long appEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app version in the ordered set where appEntryId = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app version, or <code>null</code> if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion fetchByAppEntryId_First(
		long appEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app version in the ordered set where appEntryId = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion findByAppEntryId_Last(
		long appEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app version in the ordered set where appEntryId = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app version, or <code>null</code> if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion fetchByAppEntryId_Last(
		long appEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app versions before and after the current app version in the ordered set where appEntryId = &#63;.
	*
	* @param appVersionId the primary key of the current app version
	* @param appEntryId the app entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a app version with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion[] findByAppEntryId_PrevAndNext(
		long appVersionId, long appEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app versions where status = &#63;.
	*
	* @param status the status
	* @return the matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findByStatus(
		int status) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app versions where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of app versions
	* @param end the upper bound of the range of app versions (not inclusive)
	* @return the range of matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findByStatus(
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app versions where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of app versions
	* @param end the upper bound of the range of app versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findByStatus(
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app version in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion findByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app version in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app version, or <code>null</code> if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion fetchByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app version in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion findByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app version in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app version, or <code>null</code> if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion fetchByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app versions before and after the current app version in the ordered set where status = &#63;.
	*
	* @param appVersionId the primary key of the current app version
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a app version with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion[] findByStatus_PrevAndNext(
		long appVersionId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app version where appEntryId = &#63; and version = &#63; or throws a {@link com.liferay.osb.NoSuchAppVersionException} if it could not be found.
	*
	* @param appEntryId the app entry ID
	* @param version the version
	* @return the matching app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion findByAEI_V(long appEntryId,
		java.lang.String version)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app version where appEntryId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param appEntryId the app entry ID
	* @param version the version
	* @return the matching app version, or <code>null</code> if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion fetchByAEI_V(long appEntryId,
		java.lang.String version)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app version where appEntryId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param appEntryId the app entry ID
	* @param version the version
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching app version, or <code>null</code> if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion fetchByAEI_V(long appEntryId,
		java.lang.String version, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app versions where appEntryId = &#63; and versionOrder &ge; &#63;.
	*
	* @param appEntryId the app entry ID
	* @param versionOrder the version order
	* @return the matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findByAEI_GtVO(
		long appEntryId, int versionOrder)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app versions where appEntryId = &#63; and versionOrder &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appEntryId the app entry ID
	* @param versionOrder the version order
	* @param start the lower bound of the range of app versions
	* @param end the upper bound of the range of app versions (not inclusive)
	* @return the range of matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findByAEI_GtVO(
		long appEntryId, int versionOrder, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app versions where appEntryId = &#63; and versionOrder &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appEntryId the app entry ID
	* @param versionOrder the version order
	* @param start the lower bound of the range of app versions
	* @param end the upper bound of the range of app versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findByAEI_GtVO(
		long appEntryId, int versionOrder, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app version in the ordered set where appEntryId = &#63; and versionOrder &ge; &#63;.
	*
	* @param appEntryId the app entry ID
	* @param versionOrder the version order
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion findByAEI_GtVO_First(
		long appEntryId, int versionOrder,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app version in the ordered set where appEntryId = &#63; and versionOrder &ge; &#63;.
	*
	* @param appEntryId the app entry ID
	* @param versionOrder the version order
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app version, or <code>null</code> if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion fetchByAEI_GtVO_First(
		long appEntryId, int versionOrder,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app version in the ordered set where appEntryId = &#63; and versionOrder &ge; &#63;.
	*
	* @param appEntryId the app entry ID
	* @param versionOrder the version order
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion findByAEI_GtVO_Last(
		long appEntryId, int versionOrder,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app version in the ordered set where appEntryId = &#63; and versionOrder &ge; &#63;.
	*
	* @param appEntryId the app entry ID
	* @param versionOrder the version order
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app version, or <code>null</code> if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion fetchByAEI_GtVO_Last(
		long appEntryId, int versionOrder,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app versions before and after the current app version in the ordered set where appEntryId = &#63; and versionOrder &ge; &#63;.
	*
	* @param appVersionId the primary key of the current app version
	* @param appEntryId the app entry ID
	* @param versionOrder the version order
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a app version with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion[] findByAEI_GtVO_PrevAndNext(
		long appVersionId, long appEntryId, int versionOrder,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app versions where appEntryId = &#63; and releaseType = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param releaseType the release type
	* @return the matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findByAEI_RT(
		long appEntryId, int releaseType)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app versions where appEntryId = &#63; and releaseType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appEntryId the app entry ID
	* @param releaseType the release type
	* @param start the lower bound of the range of app versions
	* @param end the upper bound of the range of app versions (not inclusive)
	* @return the range of matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findByAEI_RT(
		long appEntryId, int releaseType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app versions where appEntryId = &#63; and releaseType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appEntryId the app entry ID
	* @param releaseType the release type
	* @param start the lower bound of the range of app versions
	* @param end the upper bound of the range of app versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findByAEI_RT(
		long appEntryId, int releaseType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app version in the ordered set where appEntryId = &#63; and releaseType = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param releaseType the release type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion findByAEI_RT_First(
		long appEntryId, int releaseType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app version in the ordered set where appEntryId = &#63; and releaseType = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param releaseType the release type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app version, or <code>null</code> if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion fetchByAEI_RT_First(
		long appEntryId, int releaseType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app version in the ordered set where appEntryId = &#63; and releaseType = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param releaseType the release type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion findByAEI_RT_Last(long appEntryId,
		int releaseType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app version in the ordered set where appEntryId = &#63; and releaseType = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param releaseType the release type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app version, or <code>null</code> if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion fetchByAEI_RT_Last(
		long appEntryId, int releaseType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app versions before and after the current app version in the ordered set where appEntryId = &#63; and releaseType = &#63;.
	*
	* @param appVersionId the primary key of the current app version
	* @param appEntryId the app entry ID
	* @param releaseType the release type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a app version with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion[] findByAEI_RT_PrevAndNext(
		long appVersionId, long appEntryId, int releaseType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app versions where appEntryId = &#63; and status = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param status the status
	* @return the matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findByAEI_S(
		long appEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app versions where appEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appEntryId the app entry ID
	* @param status the status
	* @param start the lower bound of the range of app versions
	* @param end the upper bound of the range of app versions (not inclusive)
	* @return the range of matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findByAEI_S(
		long appEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app versions where appEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appEntryId the app entry ID
	* @param status the status
	* @param start the lower bound of the range of app versions
	* @param end the upper bound of the range of app versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findByAEI_S(
		long appEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app version in the ordered set where appEntryId = &#63; and status = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion findByAEI_S_First(long appEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app version in the ordered set where appEntryId = &#63; and status = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app version, or <code>null</code> if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion fetchByAEI_S_First(
		long appEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app version in the ordered set where appEntryId = &#63; and status = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion findByAEI_S_Last(long appEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app version in the ordered set where appEntryId = &#63; and status = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app version, or <code>null</code> if a matching app version could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion fetchByAEI_S_Last(long appEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app versions before and after the current app version in the ordered set where appEntryId = &#63; and status = &#63;.
	*
	* @param appVersionId the primary key of the current app version
	* @param appEntryId the app entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app version
	* @throws com.liferay.osb.NoSuchAppVersionException if a app version with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion[] findByAEI_S_PrevAndNext(
		long appVersionId, long appEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app versions.
	*
	* @return the app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app versions
	* @param end the upper bound of the range of app versions (not inclusive)
	* @return the range of app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app versions
	* @param end the upper bound of the range of app versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app versions where appEntryId = &#63; from the database.
	*
	* @param appEntryId the app entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAppEntryId(long appEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app versions where status = &#63; from the database.
	*
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the app version where appEntryId = &#63; and version = &#63; from the database.
	*
	* @param appEntryId the app entry ID
	* @param version the version
	* @return the app version that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion removeByAEI_V(long appEntryId,
		java.lang.String version)
		throws com.liferay.osb.NoSuchAppVersionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app versions where appEntryId = &#63; and versionOrder &ge; &#63; from the database.
	*
	* @param appEntryId the app entry ID
	* @param versionOrder the version order
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAEI_GtVO(long appEntryId, int versionOrder)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app versions where appEntryId = &#63; and releaseType = &#63; from the database.
	*
	* @param appEntryId the app entry ID
	* @param releaseType the release type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAEI_RT(long appEntryId, int releaseType)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app versions where appEntryId = &#63; and status = &#63; from the database.
	*
	* @param appEntryId the app entry ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAEI_S(long appEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app versions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app versions where appEntryId = &#63;.
	*
	* @param appEntryId the app entry ID
	* @return the number of matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public int countByAppEntryId(long appEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app versions where status = &#63;.
	*
	* @param status the status
	* @return the number of matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public int countByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app versions where appEntryId = &#63; and version = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param version the version
	* @return the number of matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_V(long appEntryId, java.lang.String version)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app versions where appEntryId = &#63; and versionOrder &ge; &#63;.
	*
	* @param appEntryId the app entry ID
	* @param versionOrder the version order
	* @return the number of matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_GtVO(long appEntryId, int versionOrder)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app versions where appEntryId = &#63; and releaseType = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param releaseType the release type
	* @return the number of matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_RT(long appEntryId, int releaseType)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app versions where appEntryId = &#63; and status = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param status the status
	* @return the number of matching app versions
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_S(long appEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app versions.
	*
	* @return the number of app versions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}