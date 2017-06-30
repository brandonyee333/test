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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the app flag service. This utility wraps {@link AppFlagPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppFlagPersistence
 * @see AppFlagPersistenceImpl
 * @generated
 */
public class AppFlagUtil {
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
	public static void clearCache(AppFlag appFlag) {
		getPersistence().clearCache(appFlag);
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
	public static List<AppFlag> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AppFlag> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AppFlag> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AppFlag update(AppFlag appFlag, boolean merge)
		throws SystemException {
		return getPersistence().update(appFlag, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AppFlag update(AppFlag appFlag, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(appFlag, merge, serviceContext);
	}

	/**
	* Caches the app flag in the entity cache if it is enabled.
	*
	* @param appFlag the app flag
	*/
	public static void cacheResult(com.liferay.osb.model.AppFlag appFlag) {
		getPersistence().cacheResult(appFlag);
	}

	/**
	* Caches the app flags in the entity cache if it is enabled.
	*
	* @param appFlags the app flags
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AppFlag> appFlags) {
		getPersistence().cacheResult(appFlags);
	}

	/**
	* Creates a new app flag with the primary key. Does not add the app flag to the database.
	*
	* @param appFlagId the primary key for the new app flag
	* @return the new app flag
	*/
	public static com.liferay.osb.model.AppFlag create(long appFlagId) {
		return getPersistence().create(appFlagId);
	}

	/**
	* Removes the app flag with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appFlagId the primary key of the app flag
	* @return the app flag that was removed
	* @throws com.liferay.osb.NoSuchAppFlagException if a app flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppFlag remove(long appFlagId)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(appFlagId);
	}

	public static com.liferay.osb.model.AppFlag updateImpl(
		com.liferay.osb.model.AppFlag appFlag, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(appFlag, merge);
	}

	/**
	* Returns the app flag with the primary key or throws a {@link com.liferay.osb.NoSuchAppFlagException} if it could not be found.
	*
	* @param appFlagId the primary key of the app flag
	* @return the app flag
	* @throws com.liferay.osb.NoSuchAppFlagException if a app flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppFlag findByPrimaryKey(long appFlagId)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(appFlagId);
	}

	/**
	* Returns the app flag with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appFlagId the primary key of the app flag
	* @return the app flag, or <code>null</code> if a app flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppFlag fetchByPrimaryKey(
		long appFlagId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(appFlagId);
	}

	/**
	* Returns all the app flags where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching app flags
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppFlag> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

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
	public static java.util.List<com.liferay.osb.model.AppFlag> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppFlag> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first app flag in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app flag
	* @throws com.liferay.osb.NoSuchAppFlagException if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppFlag findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first app flag in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app flag, or <code>null</code> if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppFlag fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last app flag in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app flag
	* @throws com.liferay.osb.NoSuchAppFlagException if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppFlag findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last app flag in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app flag, or <code>null</code> if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppFlag fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

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
	public static com.liferay.osb.model.AppFlag[] findByUuid_PrevAndNext(
		long appFlagId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(appFlagId, uuid, orderByComparator);
	}

	/**
	* Returns all the app flags where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @return the matching app flags
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppFlag> findByAppVersionId(
		long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppVersionId(appVersionId);
	}

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
	public static java.util.List<com.liferay.osb.model.AppFlag> findByAppVersionId(
		long appVersionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppVersionId(appVersionId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppFlag> findByAppVersionId(
		long appVersionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId(appVersionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first app flag in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app flag
	* @throws com.liferay.osb.NoSuchAppFlagException if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppFlag findByAppVersionId_First(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId_First(appVersionId, orderByComparator);
	}

	/**
	* Returns the first app flag in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app flag, or <code>null</code> if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppFlag fetchByAppVersionId_First(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppVersionId_First(appVersionId, orderByComparator);
	}

	/**
	* Returns the last app flag in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app flag
	* @throws com.liferay.osb.NoSuchAppFlagException if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppFlag findByAppVersionId_Last(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId_Last(appVersionId, orderByComparator);
	}

	/**
	* Returns the last app flag in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app flag, or <code>null</code> if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppFlag fetchByAppVersionId_Last(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppVersionId_Last(appVersionId, orderByComparator);
	}

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
	public static com.liferay.osb.model.AppFlag[] findByAppVersionId_PrevAndNext(
		long appFlagId, long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId_PrevAndNext(appFlagId, appVersionId,
			orderByComparator);
	}

	/**
	* Returns the app flag where appVersionId = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchAppFlagException} if it could not be found.
	*
	* @param appVersionId the app version ID
	* @param type the type
	* @return the matching app flag
	* @throws com.liferay.osb.NoSuchAppFlagException if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppFlag findByAVI_T(long appVersionId,
		int type)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAVI_T(appVersionId, type);
	}

	/**
	* Returns the app flag where appVersionId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param appVersionId the app version ID
	* @param type the type
	* @return the matching app flag, or <code>null</code> if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppFlag fetchByAVI_T(
		long appVersionId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByAVI_T(appVersionId, type);
	}

	/**
	* Returns the app flag where appVersionId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param appVersionId the app version ID
	* @param type the type
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching app flag, or <code>null</code> if a matching app flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppFlag fetchByAVI_T(
		long appVersionId, int type, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAVI_T(appVersionId, type, retrieveFromCache);
	}

	/**
	* Returns all the app flags.
	*
	* @return the app flags
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppFlag> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.AppFlag> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppFlag> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the app flags where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes all the app flags where appVersionId = &#63; from the database.
	*
	* @param appVersionId the app version ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAppVersionId(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAppVersionId(appVersionId);
	}

	/**
	* Removes the app flag where appVersionId = &#63; and type = &#63; from the database.
	*
	* @param appVersionId the app version ID
	* @param type the type
	* @return the app flag that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppFlag removeByAVI_T(
		long appVersionId, int type)
		throws com.liferay.osb.NoSuchAppFlagException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByAVI_T(appVersionId, type);
	}

	/**
	* Removes all the app flags from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of app flags where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching app flags
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of app flags where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @return the number of matching app flags
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAppVersionId(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAppVersionId(appVersionId);
	}

	/**
	* Returns the number of app flags where appVersionId = &#63; and type = &#63;.
	*
	* @param appVersionId the app version ID
	* @param type the type
	* @return the number of matching app flags
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAVI_T(long appVersionId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAVI_T(appVersionId, type);
	}

	/**
	* Returns the number of app flags.
	*
	* @return the number of app flags
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AppFlagPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AppFlagPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AppFlagPersistence.class.getName());

			ReferenceRegistry.registerReference(AppFlagUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AppFlagPersistence persistence) {
	}

	private static AppFlagPersistence _persistence;
}