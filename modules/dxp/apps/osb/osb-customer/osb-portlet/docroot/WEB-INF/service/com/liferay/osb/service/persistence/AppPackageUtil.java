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

import com.liferay.osb.model.AppPackage;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the app package service. This utility wraps {@link AppPackagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPackagePersistence
 * @see AppPackagePersistenceImpl
 * @generated
 */
public class AppPackageUtil {
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
	public static void clearCache(AppPackage appPackage) {
		getPersistence().clearCache(appPackage);
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
	public static List<AppPackage> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AppPackage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AppPackage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AppPackage update(AppPackage appPackage, boolean merge)
		throws SystemException {
		return getPersistence().update(appPackage, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AppPackage update(AppPackage appPackage, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(appPackage, merge, serviceContext);
	}

	/**
	* Caches the app package in the entity cache if it is enabled.
	*
	* @param appPackage the app package
	*/
	public static void cacheResult(com.liferay.osb.model.AppPackage appPackage) {
		getPersistence().cacheResult(appPackage);
	}

	/**
	* Caches the app packages in the entity cache if it is enabled.
	*
	* @param appPackages the app packages
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AppPackage> appPackages) {
		getPersistence().cacheResult(appPackages);
	}

	/**
	* Creates a new app package with the primary key. Does not add the app package to the database.
	*
	* @param appPackageId the primary key for the new app package
	* @return the new app package
	*/
	public static com.liferay.osb.model.AppPackage create(long appPackageId) {
		return getPersistence().create(appPackageId);
	}

	/**
	* Removes the app package with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appPackageId the primary key of the app package
	* @return the app package that was removed
	* @throws com.liferay.osb.NoSuchAppPackageException if a app package with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage remove(long appPackageId)
		throws com.liferay.osb.NoSuchAppPackageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(appPackageId);
	}

	public static com.liferay.osb.model.AppPackage updateImpl(
		com.liferay.osb.model.AppPackage appPackage, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(appPackage, merge);
	}

	/**
	* Returns the app package with the primary key or throws a {@link com.liferay.osb.NoSuchAppPackageException} if it could not be found.
	*
	* @param appPackageId the primary key of the app package
	* @return the app package
	* @throws com.liferay.osb.NoSuchAppPackageException if a app package with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage findByPrimaryKey(
		long appPackageId)
		throws com.liferay.osb.NoSuchAppPackageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(appPackageId);
	}

	/**
	* Returns the app package with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appPackageId the primary key of the app package
	* @return the app package, or <code>null</code> if a app package with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage fetchByPrimaryKey(
		long appPackageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(appPackageId);
	}

	/**
	* Returns all the app packages where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @return the matching app packages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackage> findByAppVersionId(
		long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppVersionId(appVersionId);
	}

	/**
	* Returns a range of all the app packages where appVersionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appVersionId the app version ID
	* @param start the lower bound of the range of app packages
	* @param end the upper bound of the range of app packages (not inclusive)
	* @return the range of matching app packages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackage> findByAppVersionId(
		long appVersionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppVersionId(appVersionId, start, end);
	}

	/**
	* Returns an ordered range of all the app packages where appVersionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appVersionId the app version ID
	* @param start the lower bound of the range of app packages
	* @param end the upper bound of the range of app packages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app packages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackage> findByAppVersionId(
		long appVersionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId(appVersionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first app package in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package
	* @throws com.liferay.osb.NoSuchAppPackageException if a matching app package could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage findByAppVersionId_First(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId_First(appVersionId, orderByComparator);
	}

	/**
	* Returns the first app package in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package, or <code>null</code> if a matching app package could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage fetchByAppVersionId_First(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppVersionId_First(appVersionId, orderByComparator);
	}

	/**
	* Returns the last app package in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package
	* @throws com.liferay.osb.NoSuchAppPackageException if a matching app package could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage findByAppVersionId_Last(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId_Last(appVersionId, orderByComparator);
	}

	/**
	* Returns the last app package in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package, or <code>null</code> if a matching app package could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage fetchByAppVersionId_Last(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppVersionId_Last(appVersionId, orderByComparator);
	}

	/**
	* Returns the app packages before and after the current app package in the ordered set where appVersionId = &#63;.
	*
	* @param appPackageId the primary key of the current app package
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app package
	* @throws com.liferay.osb.NoSuchAppPackageException if a app package with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage[] findByAppVersionId_PrevAndNext(
		long appPackageId, long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId_PrevAndNext(appPackageId, appVersionId,
			orderByComparator);
	}

	/**
	* Returns the app package where appVersionId = &#63; and compatibility = &#63; or throws a {@link com.liferay.osb.NoSuchAppPackageException} if it could not be found.
	*
	* @param appVersionId the app version ID
	* @param compatibility the compatibility
	* @return the matching app package
	* @throws com.liferay.osb.NoSuchAppPackageException if a matching app package could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage findByAVI_C(
		long appVersionId, int compatibility)
		throws com.liferay.osb.NoSuchAppPackageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAVI_C(appVersionId, compatibility);
	}

	/**
	* Returns the app package where appVersionId = &#63; and compatibility = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param appVersionId the app version ID
	* @param compatibility the compatibility
	* @return the matching app package, or <code>null</code> if a matching app package could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage fetchByAVI_C(
		long appVersionId, int compatibility)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByAVI_C(appVersionId, compatibility);
	}

	/**
	* Returns the app package where appVersionId = &#63; and compatibility = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param appVersionId the app version ID
	* @param compatibility the compatibility
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching app package, or <code>null</code> if a matching app package could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage fetchByAVI_C(
		long appVersionId, int compatibility, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAVI_C(appVersionId, compatibility, retrieveFromCache);
	}

	/**
	* Returns all the app packages where appVersionId = &#63; and compatibilityPlus = &#63;.
	*
	* @param appVersionId the app version ID
	* @param compatibilityPlus the compatibility plus
	* @return the matching app packages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackage> findByAVI_CP(
		long appVersionId, boolean compatibilityPlus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAVI_CP(appVersionId, compatibilityPlus);
	}

	/**
	* Returns a range of all the app packages where appVersionId = &#63; and compatibilityPlus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appVersionId the app version ID
	* @param compatibilityPlus the compatibility plus
	* @param start the lower bound of the range of app packages
	* @param end the upper bound of the range of app packages (not inclusive)
	* @return the range of matching app packages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackage> findByAVI_CP(
		long appVersionId, boolean compatibilityPlus, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAVI_CP(appVersionId, compatibilityPlus, start, end);
	}

	/**
	* Returns an ordered range of all the app packages where appVersionId = &#63; and compatibilityPlus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appVersionId the app version ID
	* @param compatibilityPlus the compatibility plus
	* @param start the lower bound of the range of app packages
	* @param end the upper bound of the range of app packages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app packages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackage> findByAVI_CP(
		long appVersionId, boolean compatibilityPlus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAVI_CP(appVersionId, compatibilityPlus, start, end,
			orderByComparator);
	}

	/**
	* Returns the first app package in the ordered set where appVersionId = &#63; and compatibilityPlus = &#63;.
	*
	* @param appVersionId the app version ID
	* @param compatibilityPlus the compatibility plus
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package
	* @throws com.liferay.osb.NoSuchAppPackageException if a matching app package could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage findByAVI_CP_First(
		long appVersionId, boolean compatibilityPlus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAVI_CP_First(appVersionId, compatibilityPlus,
			orderByComparator);
	}

	/**
	* Returns the first app package in the ordered set where appVersionId = &#63; and compatibilityPlus = &#63;.
	*
	* @param appVersionId the app version ID
	* @param compatibilityPlus the compatibility plus
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package, or <code>null</code> if a matching app package could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage fetchByAVI_CP_First(
		long appVersionId, boolean compatibilityPlus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAVI_CP_First(appVersionId, compatibilityPlus,
			orderByComparator);
	}

	/**
	* Returns the last app package in the ordered set where appVersionId = &#63; and compatibilityPlus = &#63;.
	*
	* @param appVersionId the app version ID
	* @param compatibilityPlus the compatibility plus
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package
	* @throws com.liferay.osb.NoSuchAppPackageException if a matching app package could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage findByAVI_CP_Last(
		long appVersionId, boolean compatibilityPlus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAVI_CP_Last(appVersionId, compatibilityPlus,
			orderByComparator);
	}

	/**
	* Returns the last app package in the ordered set where appVersionId = &#63; and compatibilityPlus = &#63;.
	*
	* @param appVersionId the app version ID
	* @param compatibilityPlus the compatibility plus
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package, or <code>null</code> if a matching app package could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage fetchByAVI_CP_Last(
		long appVersionId, boolean compatibilityPlus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAVI_CP_Last(appVersionId, compatibilityPlus,
			orderByComparator);
	}

	/**
	* Returns the app packages before and after the current app package in the ordered set where appVersionId = &#63; and compatibilityPlus = &#63;.
	*
	* @param appPackageId the primary key of the current app package
	* @param appVersionId the app version ID
	* @param compatibilityPlus the compatibility plus
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app package
	* @throws com.liferay.osb.NoSuchAppPackageException if a app package with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage[] findByAVI_CP_PrevAndNext(
		long appPackageId, long appVersionId, boolean compatibilityPlus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAVI_CP_PrevAndNext(appPackageId, appVersionId,
			compatibilityPlus, orderByComparator);
	}

	/**
	* Returns all the app packages.
	*
	* @return the app packages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackage> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the app packages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app packages
	* @param end the upper bound of the range of app packages (not inclusive)
	* @return the range of app packages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackage> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the app packages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app packages
	* @param end the upper bound of the range of app packages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of app packages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackage> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the app packages where appVersionId = &#63; from the database.
	*
	* @param appVersionId the app version ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAppVersionId(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAppVersionId(appVersionId);
	}

	/**
	* Removes the app package where appVersionId = &#63; and compatibility = &#63; from the database.
	*
	* @param appVersionId the app version ID
	* @param compatibility the compatibility
	* @return the app package that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackage removeByAVI_C(
		long appVersionId, int compatibility)
		throws com.liferay.osb.NoSuchAppPackageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByAVI_C(appVersionId, compatibility);
	}

	/**
	* Removes all the app packages where appVersionId = &#63; and compatibilityPlus = &#63; from the database.
	*
	* @param appVersionId the app version ID
	* @param compatibilityPlus the compatibility plus
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAVI_CP(long appVersionId,
		boolean compatibilityPlus)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAVI_CP(appVersionId, compatibilityPlus);
	}

	/**
	* Removes all the app packages from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of app packages where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @return the number of matching app packages
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAppVersionId(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAppVersionId(appVersionId);
	}

	/**
	* Returns the number of app packages where appVersionId = &#63; and compatibility = &#63;.
	*
	* @param appVersionId the app version ID
	* @param compatibility the compatibility
	* @return the number of matching app packages
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAVI_C(long appVersionId, int compatibility)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAVI_C(appVersionId, compatibility);
	}

	/**
	* Returns the number of app packages where appVersionId = &#63; and compatibilityPlus = &#63;.
	*
	* @param appVersionId the app version ID
	* @param compatibilityPlus the compatibility plus
	* @return the number of matching app packages
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAVI_CP(long appVersionId, boolean compatibilityPlus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAVI_CP(appVersionId, compatibilityPlus);
	}

	/**
	* Returns the number of app packages.
	*
	* @return the number of app packages
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AppPackagePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AppPackagePersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AppPackagePersistence.class.getName());

			ReferenceRegistry.registerReference(AppPackageUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AppPackagePersistence persistence) {
	}

	private static AppPackagePersistence _persistence;
}