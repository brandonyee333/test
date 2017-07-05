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

import com.liferay.osb.exception.NoSuchAssetLicenseException;
import com.liferay.osb.model.AssetLicense;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset license service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.AssetLicensePersistenceImpl
 * @see AssetLicenseUtil
 * @generated
 */
@ProviderType
public interface AssetLicensePersistence extends BasePersistence<AssetLicense> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetLicenseUtil} to access the asset license persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the asset licenses where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching asset licenses
	*/
	public java.util.List<AssetLicense> findByC_C(long classNameId, long classPK);

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
	public java.util.List<AssetLicense> findByC_C(long classNameId,
		long classPK, int start, int end);

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
	public java.util.List<AssetLicense> findByC_C(long classNameId,
		long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public java.util.List<AssetLicense> findByC_C(long classNameId,
		long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license
	* @throws NoSuchAssetLicenseException if a matching asset license could not be found
	*/
	public AssetLicense findByC_C_First(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	*/
	public AssetLicense fetchByC_C_First(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license
	* @throws NoSuchAssetLicenseException if a matching asset license could not be found
	*/
	public AssetLicense findByC_C_Last(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	*/
	public AssetLicense fetchByC_C_Last(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public AssetLicense[] findByC_C_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	*/
	public void removeByC_C(long classNameId, long classPK);

	/**
	* Returns the number of asset licenses where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching asset licenses
	*/
	public int countByC_C(long classNameId, long classPK);

	/**
	* Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param status the status
	* @return the matching asset licenses
	*/
	public java.util.List<AssetLicense> findByC_C_S(long classNameId,
		long classPK, int status);

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
	public java.util.List<AssetLicense> findByC_C_S(long classNameId,
		long classPK, int status, int start, int end);

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
	public java.util.List<AssetLicense> findByC_C_S(long classNameId,
		long classPK, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public java.util.List<AssetLicense> findByC_C_S(long classNameId,
		long classPK, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetLicense findByC_C_S_First(long classNameId, long classPK,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

	/**
	* Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	*/
	public AssetLicense fetchByC_C_S_First(long classNameId, long classPK,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public AssetLicense findByC_C_S_Last(long classNameId, long classPK,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

	/**
	* Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	*/
	public AssetLicense fetchByC_C_S_Last(long classNameId, long classPK,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public AssetLicense[] findByC_C_S_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param status the status
	*/
	public void removeByC_C_S(long classNameId, long classPK, int status);

	/**
	* Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param status the status
	* @return the number of matching asset licenses
	*/
	public int countByC_C_S(long classNameId, long classPK, int status);

	/**
	* Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @return the matching asset licenses
	*/
	public java.util.List<AssetLicense> findByC_C_UT_LT(long classNameId,
		long classPK, int usageType, int licenseType);

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
	public java.util.List<AssetLicense> findByC_C_UT_LT(long classNameId,
		long classPK, int usageType, int licenseType, int start, int end);

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
	public java.util.List<AssetLicense> findByC_C_UT_LT(long classNameId,
		long classPK, int usageType, int licenseType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public java.util.List<AssetLicense> findByC_C_UT_LT(long classNameId,
		long classPK, int usageType, int licenseType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetLicense findByC_C_UT_LT_First(long classNameId, long classPK,
		int usageType, int licenseType,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

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
	public AssetLicense fetchByC_C_UT_LT_First(long classNameId, long classPK,
		int usageType, int licenseType,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public AssetLicense findByC_C_UT_LT_Last(long classNameId, long classPK,
		int usageType, int licenseType,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

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
	public AssetLicense fetchByC_C_UT_LT_Last(long classNameId, long classPK,
		int usageType, int licenseType,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public AssetLicense[] findByC_C_UT_LT_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK, int usageType, int licenseType,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	*/
	public void removeByC_C_UT_LT(long classNameId, long classPK,
		int usageType, int licenseType);

	/**
	* Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @return the number of matching asset licenses
	*/
	public int countByC_C_UT_LT(long classNameId, long classPK, int usageType,
		int licenseType);

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
	public java.util.List<AssetLicense> findByC_C_UT_LT_LTA(long classNameId,
		long classPK, int usageType, int licenseType, long licenseTypeAllotment);

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
	public java.util.List<AssetLicense> findByC_C_UT_LT_LTA(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int start, int end);

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
	public java.util.List<AssetLicense> findByC_C_UT_LT_LTA(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public java.util.List<AssetLicense> findByC_C_UT_LT_LTA(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetLicense findByC_C_UT_LT_LTA_First(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

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
	public AssetLicense fetchByC_C_UT_LT_LTA_First(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public AssetLicense findByC_C_UT_LT_LTA_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

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
	public AssetLicense fetchByC_C_UT_LT_LTA_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public AssetLicense[] findByC_C_UT_LT_LTA_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param licenseTypeAllotment the license type allotment
	*/
	public void removeByC_C_UT_LT_LTA(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment);

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
	public int countByC_C_UT_LT_LTA(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment);

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
	public java.util.List<AssetLicense> findByC_C_UT_LT_S(long classNameId,
		long classPK, int usageType, int licenseType, int status);

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
	public java.util.List<AssetLicense> findByC_C_UT_LT_S(long classNameId,
		long classPK, int usageType, int licenseType, int status, int start,
		int end);

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
	public java.util.List<AssetLicense> findByC_C_UT_LT_S(long classNameId,
		long classPK, int usageType, int licenseType, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public java.util.List<AssetLicense> findByC_C_UT_LT_S(long classNameId,
		long classPK, int usageType, int licenseType, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetLicense findByC_C_UT_LT_S_First(long classNameId, long classPK,
		int usageType, int licenseType, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

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
	public AssetLicense fetchByC_C_UT_LT_S_First(long classNameId,
		long classPK, int usageType, int licenseType, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public AssetLicense findByC_C_UT_LT_S_Last(long classNameId, long classPK,
		int usageType, int licenseType, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

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
	public AssetLicense fetchByC_C_UT_LT_S_Last(long classNameId, long classPK,
		int usageType, int licenseType, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public AssetLicense[] findByC_C_UT_LT_S_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK, int usageType, int licenseType,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

	/**
	* Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param usageType the usage type
	* @param licenseType the license type
	* @param status the status
	*/
	public void removeByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status);

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
	public int countByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status);

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
	public java.util.List<AssetLicense> findByC_C_UT_LT_LTA_S(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status);

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
	public java.util.List<AssetLicense> findByC_C_UT_LT_LTA_S(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status, int start, int end);

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
	public java.util.List<AssetLicense> findByC_C_UT_LT_LTA_S(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public java.util.List<AssetLicense> findByC_C_UT_LT_LTA_S(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetLicense findByC_C_UT_LT_LTA_S_First(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

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
	public AssetLicense fetchByC_C_UT_LT_LTA_S_First(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public AssetLicense findByC_C_UT_LT_LTA_S_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

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
	public AssetLicense fetchByC_C_UT_LT_LTA_S_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public AssetLicense[] findByC_C_UT_LT_LTA_S_PrevAndNext(
		long assetLicenseId, long classNameId, long classPK, int usageType,
		int licenseType, long licenseTypeAllotment, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException;

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
	public void removeByC_C_UT_LT_LTA_S(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment, int status);

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
	public int countByC_C_UT_LT_LTA_S(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment, int status);

	/**
	* Caches the asset license in the entity cache if it is enabled.
	*
	* @param assetLicense the asset license
	*/
	public void cacheResult(AssetLicense assetLicense);

	/**
	* Caches the asset licenses in the entity cache if it is enabled.
	*
	* @param assetLicenses the asset licenses
	*/
	public void cacheResult(java.util.List<AssetLicense> assetLicenses);

	/**
	* Creates a new asset license with the primary key. Does not add the asset license to the database.
	*
	* @param assetLicenseId the primary key for the new asset license
	* @return the new asset license
	*/
	public AssetLicense create(long assetLicenseId);

	/**
	* Removes the asset license with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetLicenseId the primary key of the asset license
	* @return the asset license that was removed
	* @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	*/
	public AssetLicense remove(long assetLicenseId)
		throws NoSuchAssetLicenseException;

	public AssetLicense updateImpl(AssetLicense assetLicense);

	/**
	* Returns the asset license with the primary key or throws a {@link NoSuchAssetLicenseException} if it could not be found.
	*
	* @param assetLicenseId the primary key of the asset license
	* @return the asset license
	* @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	*/
	public AssetLicense findByPrimaryKey(long assetLicenseId)
		throws NoSuchAssetLicenseException;

	/**
	* Returns the asset license with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetLicenseId the primary key of the asset license
	* @return the asset license, or <code>null</code> if a asset license with the primary key could not be found
	*/
	public AssetLicense fetchByPrimaryKey(long assetLicenseId);

	@Override
	public java.util.Map<java.io.Serializable, AssetLicense> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the asset licenses.
	*
	* @return the asset licenses
	*/
	public java.util.List<AssetLicense> findAll();

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
	public java.util.List<AssetLicense> findAll(int start, int end);

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
	public java.util.List<AssetLicense> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator);

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
	public java.util.List<AssetLicense> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the asset licenses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of asset licenses.
	*
	* @return the number of asset licenses
	*/
	public int countAll();
}