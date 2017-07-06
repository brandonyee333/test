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

import com.liferay.osb.exception.NoSuchAssetReceiptLicenseException;
import com.liferay.osb.model.AssetReceiptLicense;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset receipt license service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.AssetReceiptLicensePersistenceImpl
 * @see AssetReceiptLicenseUtil
 * @generated
 */
@ProviderType
public interface AssetReceiptLicensePersistence extends BasePersistence<AssetReceiptLicense> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetReceiptLicenseUtil} to access the asset receipt license persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the asset receipt licenses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching asset receipt licenses
	*/
	public java.util.List<AssetReceiptLicense> findByUuid(java.lang.String uuid);

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
	public java.util.List<AssetReceiptLicense> findByUuid(
		java.lang.String uuid, int start, int end);

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
	public java.util.List<AssetReceiptLicense> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetReceiptLicense> orderByComparator);

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
	public java.util.List<AssetReceiptLicense> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetReceiptLicense> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license
	* @throws NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	*/
	public AssetReceiptLicense findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssetReceiptLicense> orderByComparator)
		throws NoSuchAssetReceiptLicenseException;

	/**
	* Returns the first asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	*/
	public AssetReceiptLicense fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssetReceiptLicense> orderByComparator);

	/**
	* Returns the last asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license
	* @throws NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	*/
	public AssetReceiptLicense findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssetReceiptLicense> orderByComparator)
		throws NoSuchAssetReceiptLicenseException;

	/**
	* Returns the last asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	*/
	public AssetReceiptLicense fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssetReceiptLicense> orderByComparator);

	/**
	* Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param assetReceiptLicenseId the primary key of the current asset receipt license
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt license
	* @throws NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	*/
	public AssetReceiptLicense[] findByUuid_PrevAndNext(
		long assetReceiptLicenseId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssetReceiptLicense> orderByComparator)
		throws NoSuchAssetReceiptLicenseException;

	/**
	* Removes all the asset receipt licenses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of asset receipt licenses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching asset receipt licenses
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Caches the asset receipt license in the entity cache if it is enabled.
	*
	* @param assetReceiptLicense the asset receipt license
	*/
	public void cacheResult(AssetReceiptLicense assetReceiptLicense);

	/**
	* Caches the asset receipt licenses in the entity cache if it is enabled.
	*
	* @param assetReceiptLicenses the asset receipt licenses
	*/
	public void cacheResult(
		java.util.List<AssetReceiptLicense> assetReceiptLicenses);

	/**
	* Creates a new asset receipt license with the primary key. Does not add the asset receipt license to the database.
	*
	* @param assetReceiptLicenseId the primary key for the new asset receipt license
	* @return the new asset receipt license
	*/
	public AssetReceiptLicense create(long assetReceiptLicenseId);

	/**
	* Removes the asset receipt license with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license that was removed
	* @throws NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	*/
	public AssetReceiptLicense remove(long assetReceiptLicenseId)
		throws NoSuchAssetReceiptLicenseException;

	public AssetReceiptLicense updateImpl(
		AssetReceiptLicense assetReceiptLicense);

	/**
	* Returns the asset receipt license with the primary key or throws a {@link NoSuchAssetReceiptLicenseException} if it could not be found.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license
	* @throws NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	*/
	public AssetReceiptLicense findByPrimaryKey(long assetReceiptLicenseId)
		throws NoSuchAssetReceiptLicenseException;

	/**
	* Returns the asset receipt license with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license, or <code>null</code> if a asset receipt license with the primary key could not be found
	*/
	public AssetReceiptLicense fetchByPrimaryKey(long assetReceiptLicenseId);

	@Override
	public java.util.Map<java.io.Serializable, AssetReceiptLicense> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the asset receipt licenses.
	*
	* @return the asset receipt licenses
	*/
	public java.util.List<AssetReceiptLicense> findAll();

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
	public java.util.List<AssetReceiptLicense> findAll(int start, int end);

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
	public java.util.List<AssetReceiptLicense> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetReceiptLicense> orderByComparator);

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
	public java.util.List<AssetReceiptLicense> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetReceiptLicense> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the asset receipt licenses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of asset receipt licenses.
	*
	* @return the number of asset receipt licenses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}