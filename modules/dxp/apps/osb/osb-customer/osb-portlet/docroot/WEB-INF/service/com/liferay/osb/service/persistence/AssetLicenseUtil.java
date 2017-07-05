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

import com.liferay.osb.model.AssetLicense;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the asset license service. This utility wraps {@link com.liferay.osb.service.persistence.impl.AssetLicensePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetLicensePersistence
 * @see com.liferay.osb.service.persistence.impl.AssetLicensePersistenceImpl
 * @generated
 */
@ProviderType
public class AssetLicenseUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(AssetLicense assetLicense) {
		getPersistence().clearCache(assetLicense);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AssetLicense> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetLicense> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetLicense> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AssetLicense update(AssetLicense assetLicense) {
		return getPersistence().update(assetLicense);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AssetLicense update(AssetLicense assetLicense,
		ServiceContext serviceContext) {
		return getPersistence().update(assetLicense, serviceContext);
	}

	/**
	* Returns all the asset licenses where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching asset licenses
	*/
	public static List<AssetLicense> findByC_C(long classNameId, long classPK) {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	* Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @return the range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C(long classNameId, long classPK,
		int start, int end) {
		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C(long classNameId, long classPK,
		int start, int end, OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C(long classNameId, long classPK,
		int start, int end, OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license
	* @throws NoSuchAssetLicenseException if a matching asset license could not be found
	*/
	public static AssetLicense findByC_C_First(long classNameId, long classPK,
		OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	*/
	public static AssetLicense fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license
	* @throws NoSuchAssetLicenseException if a matching asset license could not be found
	*/
	public static AssetLicense findByC_C_Last(long classNameId, long classPK,
		OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	*/
	public static AssetLicense fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param assetLicenseId the primary key of the current asset license
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset license
	* @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	*/
	public static AssetLicense[] findByC_C_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK,
		OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_PrevAndNext(assetLicenseId, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	*/
	public static void removeByC_C(long classNameId, long classPK) {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Returns the number of asset licenses where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching asset licenses
	*/
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	* Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param status the status
	* @return the matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_S(long classNameId,
		long classPK, int status) {
		return getPersistence().findByC_C_S(classNameId, classPK, status);
	}

	/**
	* Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param status the status
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @return the range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_S(long classNameId,
		long classPK, int status, int start, int end) {
		return getPersistence()
				   .findByC_C_S(classNameId, classPK, status, start, end);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param status the status
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_S(long classNameId,
		long classPK, int status, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .findByC_C_S(classNameId, classPK, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param status the status
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_S(long classNameId,
		long classPK, int status, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_C_S(classNameId, classPK, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license
	* @throws NoSuchAssetLicenseException if a matching asset license could not be found
	*/
	public static AssetLicense findByC_C_S_First(long classNameId,
		long classPK, int status,
		OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_S_First(classNameId, classPK, status,
			orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	*/
	public static AssetLicense fetchByC_C_S_First(long classNameId,
		long classPK, int status,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_S_First(classNameId, classPK, status,
			orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license
	* @throws NoSuchAssetLicenseException if a matching asset license could not be found
	*/
	public static AssetLicense findByC_C_S_Last(long classNameId, long classPK,
		int status, OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_S_Last(classNameId, classPK, status,
			orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	*/
	public static AssetLicense fetchByC_C_S_Last(long classNameId,
		long classPK, int status,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_S_Last(classNameId, classPK, status,
			orderByComparator);
	}

	/**
	* Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param assetLicenseId the primary key of the current asset license
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset license
	* @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	*/
	public static AssetLicense[] findByC_C_S_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK, int status,
		OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_S_PrevAndNext(assetLicenseId, classNameId,
			classPK, status, orderByComparator);
	}

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param status the status
	*/
	public static void removeByC_C_S(long classNameId, long classPK, int status) {
		getPersistence().removeByC_C_S(classNameId, classPK, status);
	}

	/**
	* Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param status the status
	* @return the number of matching asset licenses
	*/
	public static int countByC_C_S(long classNameId, long classPK, int status) {
		return getPersistence().countByC_C_S(classNameId, classPK, status);
	}

	/**
	* Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @return the matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_UT_LT(long classNameId,
		long classPK, int usageType, int licenseType) {
		return getPersistence()
				   .findByC_C_UT_LT(classNameId, classPK, usageType, licenseType);
	}

	/**
	* Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @return the range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_UT_LT(long classNameId,
		long classPK, int usageType, int licenseType, int start, int end) {
		return getPersistence()
				   .findByC_C_UT_LT(classNameId, classPK, usageType,
			licenseType, start, end);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_UT_LT(long classNameId,
		long classPK, int usageType, int licenseType, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .findByC_C_UT_LT(classNameId, classPK, usageType,
			licenseType, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_UT_LT(long classNameId,
		long classPK, int usageType, int licenseType, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_C_UT_LT(classNameId, classPK, usageType,
			licenseType, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license
	* @throws NoSuchAssetLicenseException if a matching asset license could not be found
	*/
	public static AssetLicense findByC_C_UT_LT_First(long classNameId,
		long classPK, int usageType, int licenseType,
		OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_UT_LT_First(classNameId, classPK, usageType,
			licenseType, orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	*/
	public static AssetLicense fetchByC_C_UT_LT_First(long classNameId,
		long classPK, int usageType, int licenseType,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_UT_LT_First(classNameId, classPK, usageType,
			licenseType, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license
	* @throws NoSuchAssetLicenseException if a matching asset license could not be found
	*/
	public static AssetLicense findByC_C_UT_LT_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_UT_LT_Last(classNameId, classPK, usageType,
			licenseType, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	*/
	public static AssetLicense fetchByC_C_UT_LT_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_UT_LT_Last(classNameId, classPK, usageType,
			licenseType, orderByComparator);
	}

	/**
	* Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* @param assetLicenseId the primary key of the current asset license
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset license
	* @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	*/
	public static AssetLicense[] findByC_C_UT_LT_PrevAndNext(
		long assetLicenseId, long classNameId, long classPK, int usageType,
		int licenseType, OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_UT_LT_PrevAndNext(assetLicenseId, classNameId,
			classPK, usageType, licenseType, orderByComparator);
	}

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	*/
	public static void removeByC_C_UT_LT(long classNameId, long classPK,
		int usageType, int licenseType) {
		getPersistence()
			.removeByC_C_UT_LT(classNameId, classPK, usageType, licenseType);
	}

	/**
	* Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @return the number of matching asset licenses
	*/
	public static int countByC_C_UT_LT(long classNameId, long classPK,
		int usageType, int licenseType) {
		return getPersistence()
				   .countByC_C_UT_LT(classNameId, classPK, usageType,
			licenseType);
	}

	/**
	* Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @return the matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_UT_LT_LTA(long classNameId,
		long classPK, int usageType, int licenseType, long licenseTypeAllotment) {
		return getPersistence()
				   .findByC_C_UT_LT_LTA(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment);
	}

	/**
	* Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @return the range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_UT_LT_LTA(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int start, int end) {
		return getPersistence()
				   .findByC_C_UT_LT_LTA(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, start, end);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_UT_LT_LTA(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .findByC_C_UT_LT_LTA(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_UT_LT_LTA(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_C_UT_LT_LTA(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license
	* @throws NoSuchAssetLicenseException if a matching asset license could not be found
	*/
	public static AssetLicense findByC_C_UT_LT_LTA_First(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_First(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	*/
	public static AssetLicense fetchByC_C_UT_LT_LTA_First(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_UT_LT_LTA_First(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license
	* @throws NoSuchAssetLicenseException if a matching asset license could not be found
	*/
	public static AssetLicense findByC_C_UT_LT_LTA_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_Last(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	*/
	public static AssetLicense fetchByC_C_UT_LT_LTA_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_UT_LT_LTA_Last(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, orderByComparator);
	}

	/**
	* Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* @param assetLicenseId the primary key of the current asset license
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset license
	* @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	*/
	public static AssetLicense[] findByC_C_UT_LT_LTA_PrevAndNext(
		long assetLicenseId, long classNameId, long classPK, int usageType,
		int licenseType, long licenseTypeAllotment,
		OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_PrevAndNext(assetLicenseId,
			classNameId, classPK, usageType, licenseType, licenseTypeAllotment,
			orderByComparator);
	}

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	*/
	public static void removeByC_C_UT_LT_LTA(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment) {
		getPersistence()
			.removeByC_C_UT_LT_LTA(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment);
	}

	/**
	* Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @return the number of matching asset licenses
	*/
	public static int countByC_C_UT_LT_LTA(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment) {
		return getPersistence()
				   .countByC_C_UT_LT_LTA(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment);
	}

	/**
	* Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @return the matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_UT_LT_S(long classNameId,
		long classPK, int usageType, int licenseType, int status) {
		return getPersistence()
				   .findByC_C_UT_LT_S(classNameId, classPK, usageType,
			licenseType, status);
	}

	/**
	* Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @return the range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_UT_LT_S(long classNameId,
		long classPK, int usageType, int licenseType, int status, int start,
		int end) {
		return getPersistence()
				   .findByC_C_UT_LT_S(classNameId, classPK, usageType,
			licenseType, status, start, end);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_UT_LT_S(long classNameId,
		long classPK, int usageType, int licenseType, int status, int start,
		int end, OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .findByC_C_UT_LT_S(classNameId, classPK, usageType,
			licenseType, status, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_UT_LT_S(long classNameId,
		long classPK, int usageType, int licenseType, int status, int start,
		int end, OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_C_UT_LT_S(classNameId, classPK, usageType,
			licenseType, status, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license
	* @throws NoSuchAssetLicenseException if a matching asset license could not be found
	*/
	public static AssetLicense findByC_C_UT_LT_S_First(long classNameId,
		long classPK, int usageType, int licenseType, int status,
		OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_UT_LT_S_First(classNameId, classPK, usageType,
			licenseType, status, orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	*/
	public static AssetLicense fetchByC_C_UT_LT_S_First(long classNameId,
		long classPK, int usageType, int licenseType, int status,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_UT_LT_S_First(classNameId, classPK, usageType,
			licenseType, status, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license
	* @throws NoSuchAssetLicenseException if a matching asset license could not be found
	*/
	public static AssetLicense findByC_C_UT_LT_S_Last(long classNameId,
		long classPK, int usageType, int licenseType, int status,
		OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_UT_LT_S_Last(classNameId, classPK, usageType,
			licenseType, status, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	*/
	public static AssetLicense fetchByC_C_UT_LT_S_Last(long classNameId,
		long classPK, int usageType, int licenseType, int status,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_UT_LT_S_Last(classNameId, classPK, usageType,
			licenseType, status, orderByComparator);
	}

	/**
	* Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* @param assetLicenseId the primary key of the current asset license
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset license
	* @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	*/
	public static AssetLicense[] findByC_C_UT_LT_S_PrevAndNext(
		long assetLicenseId, long classNameId, long classPK, int usageType,
		int licenseType, int status,
		OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_UT_LT_S_PrevAndNext(assetLicenseId, classNameId,
			classPK, usageType, licenseType, status, orderByComparator);
	}

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	*/
	public static void removeByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status) {
		getPersistence()
			.removeByC_C_UT_LT_S(classNameId, classPK, usageType, licenseType,
			status);
	}

	/**
	* Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	* @return the number of matching asset licenses
	*/
	public static int countByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status) {
		return getPersistence()
				   .countByC_C_UT_LT_S(classNameId, classPK, usageType,
			licenseType, status);
	}

	/**
	* Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @return the matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_UT_LT_LTA_S(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status) {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status);
	}

	/**
	* Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @return the range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_UT_LT_LTA_S(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status, int start, int end) {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status, start, end);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_UT_LT_LTA_S(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset licenses
	*/
	public static List<AssetLicense> findByC_C_UT_LT_LTA_S(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license
	* @throws NoSuchAssetLicenseException if a matching asset license could not be found
	*/
	public static AssetLicense findByC_C_UT_LT_LTA_S_First(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_S_First(classNameId, classPK,
			usageType, licenseType, licenseTypeAllotment, status,
			orderByComparator);
	}

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	*/
	public static AssetLicense fetchByC_C_UT_LT_LTA_S_First(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_UT_LT_LTA_S_First(classNameId, classPK,
			usageType, licenseType, licenseTypeAllotment, status,
			orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license
	* @throws NoSuchAssetLicenseException if a matching asset license could not be found
	*/
	public static AssetLicense findByC_C_UT_LT_LTA_S_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_S_Last(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status, orderByComparator);
	}

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	*/
	public static AssetLicense fetchByC_C_UT_LT_LTA_S_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		OrderByComparator<AssetLicense> orderByComparator) {
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
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset license
	* @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	*/
	public static AssetLicense[] findByC_C_UT_LT_LTA_S_PrevAndNext(
		long assetLicenseId, long classNameId, long classPK, int usageType,
		int licenseType, long licenseTypeAllotment, int status,
		OrderByComparator<AssetLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence()
				   .findByC_C_UT_LT_LTA_S_PrevAndNext(assetLicenseId,
			classNameId, classPK, usageType, licenseType, licenseTypeAllotment,
			status, orderByComparator);
	}

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	*/
	public static void removeByC_C_UT_LT_LTA_S(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment, int status) {
		getPersistence()
			.removeByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status);
	}

	/**
	* Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	* @param status the status
	* @return the number of matching asset licenses
	*/
	public static int countByC_C_UT_LT_LTA_S(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment, int status) {
		return getPersistence()
				   .countByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status);
	}

	/**
	* Caches the asset license in the entity cache if it is enabled.
	*
	* @param assetLicense the asset license
	*/
	public static void cacheResult(AssetLicense assetLicense) {
		getPersistence().cacheResult(assetLicense);
	}

	/**
	* Caches the asset licenses in the entity cache if it is enabled.
	*
	* @param assetLicenses the asset licenses
	*/
	public static void cacheResult(List<AssetLicense> assetLicenses) {
		getPersistence().cacheResult(assetLicenses);
	}

	/**
	* Creates a new asset license with the primary key. Does not add the asset license to the database.
	*
	* @param assetLicenseId the primary key for the new asset license
	* @return the new asset license
	*/
	public static AssetLicense create(long assetLicenseId) {
		return getPersistence().create(assetLicenseId);
	}

	/**
	* Removes the asset license with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetLicenseId the primary key of the asset license
	* @return the asset license that was removed
	* @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	*/
	public static AssetLicense remove(long assetLicenseId)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence().remove(assetLicenseId);
	}

	public static AssetLicense updateImpl(AssetLicense assetLicense) {
		return getPersistence().updateImpl(assetLicense);
	}

	/**
	* Returns the asset license with the primary key or throws a {@link NoSuchAssetLicenseException} if it could not be found.
	*
	* @param assetLicenseId the primary key of the asset license
	* @return the asset license
	* @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	*/
	public static AssetLicense findByPrimaryKey(long assetLicenseId)
		throws com.liferay.osb.exception.NoSuchAssetLicenseException {
		return getPersistence().findByPrimaryKey(assetLicenseId);
	}

	/**
	* Returns the asset license with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetLicenseId the primary key of the asset license
	* @return the asset license, or <code>null</code> if a asset license with the primary key could not be found
	*/
	public static AssetLicense fetchByPrimaryKey(long assetLicenseId) {
		return getPersistence().fetchByPrimaryKey(assetLicenseId);
	}

	public static java.util.Map<java.io.Serializable, AssetLicense> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the asset licenses.
	*
	* @return the asset licenses
	*/
	public static List<AssetLicense> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the asset licenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @return the range of asset licenses
	*/
	public static List<AssetLicense> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the asset licenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset licenses
	*/
	public static List<AssetLicense> findAll(int start, int end,
		OrderByComparator<AssetLicense> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset licenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of asset licenses
	*/
	public static List<AssetLicense> findAll(int start, int end,
		OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the asset licenses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset licenses.
	*
	* @return the number of asset licenses
	*/
	public static int countAll() {
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

	private static AssetLicensePersistence _persistence;
}