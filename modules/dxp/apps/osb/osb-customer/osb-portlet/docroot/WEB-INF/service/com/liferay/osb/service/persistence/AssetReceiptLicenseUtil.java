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

import com.liferay.osb.model.AssetReceiptLicense;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the asset receipt license service. This utility wraps {@link com.liferay.osb.service.persistence.impl.AssetReceiptLicensePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptLicensePersistence
 * @see com.liferay.osb.service.persistence.impl.AssetReceiptLicensePersistenceImpl
 * @generated
 */
@ProviderType
public class AssetReceiptLicenseUtil {
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
	public static void clearCache(AssetReceiptLicense assetReceiptLicense) {
		getPersistence().clearCache(assetReceiptLicense);
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
	public static List<AssetReceiptLicense> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetReceiptLicense> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetReceiptLicense> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AssetReceiptLicense> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AssetReceiptLicense update(
		AssetReceiptLicense assetReceiptLicense) {
		return getPersistence().update(assetReceiptLicense);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AssetReceiptLicense update(
		AssetReceiptLicense assetReceiptLicense, ServiceContext serviceContext) {
		return getPersistence().update(assetReceiptLicense, serviceContext);
	}

	/**
	* Returns all the asset receipt licenses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching asset receipt licenses
	*/
	public static List<AssetReceiptLicense> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the asset receipt licenses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetReceiptLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @return the range of matching asset receipt licenses
	*/
	public static List<AssetReceiptLicense> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt licenses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetReceiptLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipt licenses
	*/
	public static List<AssetReceiptLicense> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<AssetReceiptLicense> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset receipt licenses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetReceiptLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset receipt licenses
	*/
	public static List<AssetReceiptLicense> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<AssetReceiptLicense> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license
	* @throws NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	*/
	public static AssetReceiptLicense findByUuid_First(java.lang.String uuid,
		OrderByComparator<AssetReceiptLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetReceiptLicenseException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	*/
	public static AssetReceiptLicense fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<AssetReceiptLicense> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license
	* @throws NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	*/
	public static AssetReceiptLicense findByUuid_Last(java.lang.String uuid,
		OrderByComparator<AssetReceiptLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetReceiptLicenseException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	*/
	public static AssetReceiptLicense fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<AssetReceiptLicense> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param assetReceiptLicenseId the primary key of the current asset receipt license
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt license
	* @throws NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	*/
	public static AssetReceiptLicense[] findByUuid_PrevAndNext(
		long assetReceiptLicenseId, java.lang.String uuid,
		OrderByComparator<AssetReceiptLicense> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAssetReceiptLicenseException {
		return getPersistence()
				   .findByUuid_PrevAndNext(assetReceiptLicenseId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the asset receipt licenses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of asset receipt licenses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching asset receipt licenses
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Caches the asset receipt license in the entity cache if it is enabled.
	*
	* @param assetReceiptLicense the asset receipt license
	*/
	public static void cacheResult(AssetReceiptLicense assetReceiptLicense) {
		getPersistence().cacheResult(assetReceiptLicense);
	}

	/**
	* Caches the asset receipt licenses in the entity cache if it is enabled.
	*
	* @param assetReceiptLicenses the asset receipt licenses
	*/
	public static void cacheResult(
		List<AssetReceiptLicense> assetReceiptLicenses) {
		getPersistence().cacheResult(assetReceiptLicenses);
	}

	/**
	* Creates a new asset receipt license with the primary key. Does not add the asset receipt license to the database.
	*
	* @param assetReceiptLicenseId the primary key for the new asset receipt license
	* @return the new asset receipt license
	*/
	public static AssetReceiptLicense create(long assetReceiptLicenseId) {
		return getPersistence().create(assetReceiptLicenseId);
	}

	/**
	* Removes the asset receipt license with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license that was removed
	* @throws NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	*/
	public static AssetReceiptLicense remove(long assetReceiptLicenseId)
		throws com.liferay.osb.exception.NoSuchAssetReceiptLicenseException {
		return getPersistence().remove(assetReceiptLicenseId);
	}

	public static AssetReceiptLicense updateImpl(
		AssetReceiptLicense assetReceiptLicense) {
		return getPersistence().updateImpl(assetReceiptLicense);
	}

	/**
	* Returns the asset receipt license with the primary key or throws a {@link NoSuchAssetReceiptLicenseException} if it could not be found.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license
	* @throws NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	*/
	public static AssetReceiptLicense findByPrimaryKey(
		long assetReceiptLicenseId)
		throws com.liferay.osb.exception.NoSuchAssetReceiptLicenseException {
		return getPersistence().findByPrimaryKey(assetReceiptLicenseId);
	}

	/**
	* Returns the asset receipt license with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license, or <code>null</code> if a asset receipt license with the primary key could not be found
	*/
	public static AssetReceiptLicense fetchByPrimaryKey(
		long assetReceiptLicenseId) {
		return getPersistence().fetchByPrimaryKey(assetReceiptLicenseId);
	}

	public static java.util.Map<java.io.Serializable, AssetReceiptLicense> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the asset receipt licenses.
	*
	* @return the asset receipt licenses
	*/
	public static List<AssetReceiptLicense> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the asset receipt licenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetReceiptLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @return the range of asset receipt licenses
	*/
	public static List<AssetReceiptLicense> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt licenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetReceiptLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset receipt licenses
	*/
	public static List<AssetReceiptLicense> findAll(int start, int end,
		OrderByComparator<AssetReceiptLicense> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset receipt licenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetReceiptLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of asset receipt licenses
	*/
	public static List<AssetReceiptLicense> findAll(int start, int end,
		OrderByComparator<AssetReceiptLicense> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the asset receipt licenses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset receipt licenses.
	*
	* @return the number of asset receipt licenses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AssetReceiptLicensePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetReceiptLicensePersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AssetReceiptLicensePersistence.class.getName());

			ReferenceRegistry.registerReference(AssetReceiptLicenseUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static AssetReceiptLicensePersistence _persistence;
}