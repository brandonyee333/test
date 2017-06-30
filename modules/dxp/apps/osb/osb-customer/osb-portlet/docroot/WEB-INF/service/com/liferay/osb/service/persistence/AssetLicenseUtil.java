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

import com.liferay.osb.model.AssetLicense;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the asset license service. This utility wraps {@link AssetLicensePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetLicensePersistence
 * @see AssetLicensePersistenceImpl
 * @generated
 */
public class AssetLicenseUtil {
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
	public static void clearCache(AssetLicense assetLicense) {
		getPersistence().clearCache(assetLicense);
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
	public static List<AssetLicense> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetLicense> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetLicense> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AssetLicense update(AssetLicense assetLicense, boolean merge)
		throws SystemException {
		return getPersistence().update(assetLicense, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AssetLicense update(AssetLicense assetLicense, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(assetLicense, merge, serviceContext);
	}

	/**
	* Caches the asset license in the entity cache if it is enabled.
	*
	* @param assetLicense the asset license
	*/
	public static void cacheResult(
		com.liferay.osb.model.AssetLicense assetLicense) {
		getPersistence().cacheResult(assetLicense);
	}

	/**
	* Caches the asset licenses in the entity cache if it is enabled.
	*
	* @param assetLicenses the asset licenses
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AssetLicense> assetLicenses) {
		getPersistence().cacheResult(assetLicenses);
	}

	/**
	* Creates a new asset license with the primary key. Does not add the asset license to the database.
	*
	* @param assetLicenseId the primary key for the new asset license
	* @return the new asset license
	*/
	public static com.liferay.osb.model.AssetLicense create(long assetLicenseId) {
		return getPersistence().create(assetLicenseId);
	}

	/**
	* Removes the asset license with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetLicenseId the primary key of the asset license
	* @return the asset license that was removed
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense remove(long assetLicenseId)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(assetLicenseId);
	}

	public static com.liferay.osb.model.AssetLicense updateImpl(
		com.liferay.osb.model.AssetLicense assetLicense, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(assetLicense, merge);
	}

	/**
	* Returns the asset license with the primary key or throws a {@link com.liferay.osb.NoSuchAssetLicenseException} if it could not be found.
	*
	* @param assetLicenseId the primary key of the asset license
	* @return the asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense findByPrimaryKey(
		long assetLicenseId)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(assetLicenseId);
	}

	/**
	* Returns the asset license with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetLicenseId the primary key of the asset license
	* @return the asset license, or <code>null</code> if a asset license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense fetchByPrimaryKey(
		long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(assetLicenseId);
	}

	/**
	* Returns all the asset licenses where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	* Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @return the range of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param assetLicenseId the primary key of the current asset license
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense[] findByC_C_PrevAndNext(
		long assetLicenseId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_PrevAndNext(assetLicenseId, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @return the matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C_S(
		long classNameId, long classPK, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C_S(classNameId, classPK, status);
	}

	/**
	* Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @return the range of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C_S(
		long classNameId, long classPK, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_S(classNameId, classPK, status, start, end);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C_S(
		long classNameId, long classPK, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_S(classNameId, classPK, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense findByC_C_S_First(
		long classNameId, long classPK, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_S_First(classNameId, classPK, status,
			orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense fetchByC_C_S_First(
		long classNameId, long classPK, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_S_First(classNameId, classPK, status,
			orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense findByC_C_S_Last(
		long classNameId, long classPK, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_S_Last(classNameId, classPK, status,
			orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense fetchByC_C_S_Last(
		long classNameId, long classPK, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_S_Last(classNameId, classPK, status,
			orderByComparator);
	}

	/**
	* Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param assetLicenseId the primary key of the current asset license
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense[] findByC_C_S_PrevAndNext(
		long assetLicenseId, long classNameId, long classPK, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_S_PrevAndNext(assetLicenseId, classNameId,
			classPK, status, orderByComparator);
	}

	/**
	* Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @return the matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C_UT_LT(
		long classNameId, long classPK, int usageType, int licenseType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT(classNameId, classPK, usageType, licenseType);
	}

	/**
	* Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @return the range of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C_UT_LT(
		long classNameId, long classPK, int usageType, int licenseType,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT(classNameId, classPK, usageType,
			licenseType, start, end);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C_UT_LT(
		long classNameId, long classPK, int usageType, int licenseType,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT(classNameId, classPK, usageType,
			licenseType, start, end, orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense findByC_C_UT_LT_First(
		long classNameId, long classPK, int usageType, int licenseType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_First(classNameId, classPK, usageType,
			licenseType, orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense fetchByC_C_UT_LT_First(
		long classNameId, long classPK, int usageType, int licenseType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_UT_LT_First(classNameId, classPK, usageType,
			licenseType, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense findByC_C_UT_LT_Last(
		long classNameId, long classPK, int usageType, int licenseType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_Last(classNameId, classPK, usageType,
			licenseType, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense fetchByC_C_UT_LT_Last(
		long classNameId, long classPK, int usageType, int licenseType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_UT_LT_Last(classNameId, classPK, usageType,
			licenseType, orderByComparator);
	}

	/**
	* Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* @param assetLicenseId the primary key of the current asset license
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense[] findByC_C_UT_LT_PrevAndNext(
		long assetLicenseId, long classNameId, long classPK, int usageType,
		int licenseType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_PrevAndNext(assetLicenseId, classNameId,
			classPK, usageType, licenseType, orderByComparator);
	}

	/**
	* Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @return the matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C_UT_LT_LTA(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment);
	}

	/**
	* Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @return the range of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C_UT_LT_LTA(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, start, end);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C_UT_LT_LTA(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, start, end, orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense findByC_C_UT_LT_LTA_First(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_First(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense fetchByC_C_UT_LT_LTA_First(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_UT_LT_LTA_First(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense findByC_C_UT_LT_LTA_Last(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_Last(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense fetchByC_C_UT_LT_LTA_Last(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_UT_LT_LTA_Last(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, orderByComparator);
	}

	/**
	* Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* @param assetLicenseId the primary key of the current asset license
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense[] findByC_C_UT_LT_LTA_PrevAndNext(
		long assetLicenseId, long classNameId, long classPK, int usageType,
		int licenseType, long licenseTypeAllotment,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_PrevAndNext(assetLicenseId,
			classNameId, classPK, usageType, licenseType, licenseTypeAllotment,
			orderByComparator);
	}

	/**
	* Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @return the matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C_UT_LT_S(
		long classNameId, long classPK, int usageType, int licenseType,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_S(classNameId, classPK, usageType,
			licenseType, status);
	}

	/**
	* Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @return the range of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C_UT_LT_S(
		long classNameId, long classPK, int usageType, int licenseType,
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_S(classNameId, classPK, usageType,
			licenseType, status, start, end);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C_UT_LT_S(
		long classNameId, long classPK, int usageType, int licenseType,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_S(classNameId, classPK, usageType,
			licenseType, status, start, end, orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense findByC_C_UT_LT_S_First(
		long classNameId, long classPK, int usageType, int licenseType,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_S_First(classNameId, classPK, usageType,
			licenseType, status, orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense fetchByC_C_UT_LT_S_First(
		long classNameId, long classPK, int usageType, int licenseType,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_UT_LT_S_First(classNameId, classPK, usageType,
			licenseType, status, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense findByC_C_UT_LT_S_Last(
		long classNameId, long classPK, int usageType, int licenseType,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_S_Last(classNameId, classPK, usageType,
			licenseType, status, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense fetchByC_C_UT_LT_S_Last(
		long classNameId, long classPK, int usageType, int licenseType,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_UT_LT_S_Last(classNameId, classPK, usageType,
			licenseType, status, orderByComparator);
	}

	/**
	* Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* @param assetLicenseId the primary key of the current asset license
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense[] findByC_C_UT_LT_S_PrevAndNext(
		long assetLicenseId, long classNameId, long classPK, int usageType,
		int licenseType, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_S_PrevAndNext(assetLicenseId, classNameId,
			classPK, usageType, licenseType, status, orderByComparator);
	}

	/**
	* Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @return the matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C_UT_LT_LTA_S(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status);
	}

	/**
	* Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @return the range of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C_UT_LT_LTA_S(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status, start, end);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findByC_C_UT_LT_LTA_S(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense findByC_C_UT_LT_LTA_S_First(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_S_First(classNameId, classPK,
			usageType, licenseType, licenseTypeAllotment, status,
			orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense fetchByC_C_UT_LT_LTA_S_First(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_UT_LT_LTA_S_First(classNameId, classPK,
			usageType, licenseType, licenseTypeAllotment, status,
			orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense findByC_C_UT_LT_LTA_S_Last(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_S_Last(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense fetchByC_C_UT_LT_LTA_S_Last(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_UT_LT_LTA_S_Last(classNameId, classPK,
			usageType, licenseType, licenseTypeAllotment, status,
			orderByComparator);
	}

	/**
	* Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* @param assetLicenseId the primary key of the current asset license
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset license
	* @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense[] findByC_C_UT_LT_LTA_S_PrevAndNext(
		long assetLicenseId, long classNameId, long classPK, int usageType,
		int licenseType, long licenseTypeAllotment, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_S_PrevAndNext(assetLicenseId,
			classNameId, classPK, usageType, licenseType, licenseTypeAllotment,
			status, orderByComparator);
	}

	/**
	* Returns all the asset licenses.
	*
	* @return the asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the asset licenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @return the range of asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the asset licenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C_S(long classNameId, long classPK, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_C_S(classNameId, classPK, status);
	}

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C_UT_LT(long classNameId, long classPK,
		int usageType, int licenseType)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByC_C_UT_LT(classNameId, classPK, usageType, licenseType);
	}

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C_UT_LT_LTA(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByC_C_UT_LT_LTA(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment);
	}

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByC_C_UT_LT_S(classNameId, classPK, usageType, licenseType,
			status);
	}

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C_UT_LT_LTA_S(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status);
	}

	/**
	* Removes all the asset licenses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset licenses where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	* Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @return the number of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C_S(long classNameId, long classPK, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C_S(classNameId, classPK, status);
	}

	/**
	* Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @return the number of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C_UT_LT(long classNameId, long classPK,
		int usageType, int licenseType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_C_UT_LT(classNameId, classPK, usageType,
			licenseType);
	}

	/**
	* Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @return the number of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C_UT_LT_LTA(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_C_UT_LT_LTA(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment);
	}

	/**
	* Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @return the number of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_C_UT_LT_S(classNameId, classPK, usageType,
			licenseType, status);
	}

	/**
	* Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @return the number of matching asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C_UT_LT_LTA_S(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status);
	}

	/**
	* Returns the number of asset licenses.
	*
	* @return the number of asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AssetLicensePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetLicensePersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AssetLicensePersistence.class.getName());

			ReferenceRegistry.registerReference(AssetLicenseUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AssetLicensePersistence persistence) {
	}

	private static AssetLicensePersistence _persistence;
}