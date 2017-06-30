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

import com.liferay.osb.model.LicenseEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the license entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntryPersistenceImpl
 * @see LicenseEntryUtil
 * @generated
 */
public interface LicenseEntryPersistence extends BasePersistence<LicenseEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LicenseEntryUtil} to access the license entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the license entry in the entity cache if it is enabled.
	*
	* @param licenseEntry the license entry
	*/
	public void cacheResult(com.liferay.osb.model.LicenseEntry licenseEntry);

	/**
	* Caches the license entries in the entity cache if it is enabled.
	*
	* @param licenseEntries the license entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.LicenseEntry> licenseEntries);

	/**
	* Creates a new license entry with the primary key. Does not add the license entry to the database.
	*
	* @param licenseEntryId the primary key for the new license entry
	* @return the new license entry
	*/
	public com.liferay.osb.model.LicenseEntry create(long licenseEntryId);

	/**
	* Removes the license entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseEntryId the primary key of the license entry
	* @return the license entry that was removed
	* @throws com.liferay.osb.NoSuchLicenseEntryException if a license entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry remove(long licenseEntryId)
		throws com.liferay.osb.NoSuchLicenseEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.LicenseEntry updateImpl(
		com.liferay.osb.model.LicenseEntry licenseEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license entry with the primary key or throws a {@link com.liferay.osb.NoSuchLicenseEntryException} if it could not be found.
	*
	* @param licenseEntryId the primary key of the license entry
	* @return the license entry
	* @throws com.liferay.osb.NoSuchLicenseEntryException if a license entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry findByPrimaryKey(
		long licenseEntryId)
		throws com.liferay.osb.NoSuchLicenseEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param licenseEntryId the primary key of the license entry
	* @return the license entry, or <code>null</code> if a license entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry fetchByPrimaryKey(
		long licenseEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license entries where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @return the matching license entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseEntry> findByProductEntryId(
		long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license entries where productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @return the range of matching license entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseEntry> findByProductEntryId(
		long productEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license entries where productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseEntry> findByProductEntryId(
		long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license entry in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license entry
	* @throws com.liferay.osb.NoSuchLicenseEntryException if a matching license entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry findByProductEntryId_First(
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license entry in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry fetchByProductEntryId_First(
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license entry in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license entry
	* @throws com.liferay.osb.NoSuchLicenseEntryException if a matching license entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry findByProductEntryId_Last(
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license entry in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry fetchByProductEntryId_Last(
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license entries before and after the current license entry in the ordered set where productEntryId = &#63;.
	*
	* @param licenseEntryId the primary key of the current license entry
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license entry
	* @throws com.liferay.osb.NoSuchLicenseEntryException if a license entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry[] findByProductEntryId_PrevAndNext(
		long licenseEntryId, long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license entries where type = &#63;.
	*
	* @param type the type
	* @return the matching license entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseEntry> findByType(
		java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license entries where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @return the range of matching license entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseEntry> findByType(
		java.lang.String type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license entries where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseEntry> findByType(
		java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license entry in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license entry
	* @throws com.liferay.osb.NoSuchLicenseEntryException if a matching license entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry findByType_First(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license entry in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry fetchByType_First(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license entry in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license entry
	* @throws com.liferay.osb.NoSuchLicenseEntryException if a matching license entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry findByType_Last(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license entry in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry fetchByType_Last(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license entries before and after the current license entry in the ordered set where type = &#63;.
	*
	* @param licenseEntryId the primary key of the current license entry
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license entry
	* @throws com.liferay.osb.NoSuchLicenseEntryException if a license entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry[] findByType_PrevAndNext(
		long licenseEntryId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license entry where productEntryId = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchLicenseEntryException} if it could not be found.
	*
	* @param productEntryId the product entry ID
	* @param type the type
	* @return the matching license entry
	* @throws com.liferay.osb.NoSuchLicenseEntryException if a matching license entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry findByPEI_T(long productEntryId,
		java.lang.String type)
		throws com.liferay.osb.NoSuchLicenseEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license entry where productEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param productEntryId the product entry ID
	* @param type the type
	* @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry fetchByPEI_T(
		long productEntryId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license entry where productEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param productEntryId the product entry ID
	* @param type the type
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry fetchByPEI_T(
		long productEntryId, java.lang.String type, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @return the matching license entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseEntry> findByPEI_PV(
		long productEntryId, int portalVersionMin)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @return the range of matching license entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseEntry> findByPEI_PV(
		long productEntryId, int portalVersionMin, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseEntry> findByPEI_PV(
		long productEntryId, int portalVersionMin, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license entry
	* @throws com.liferay.osb.NoSuchLicenseEntryException if a matching license entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry findByPEI_PV_First(
		long productEntryId, int portalVersionMin,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry fetchByPEI_PV_First(
		long productEntryId, int portalVersionMin,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license entry
	* @throws com.liferay.osb.NoSuchLicenseEntryException if a matching license entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry findByPEI_PV_Last(
		long productEntryId, int portalVersionMin,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry fetchByPEI_PV_Last(
		long productEntryId, int portalVersionMin,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license entries before and after the current license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* @param licenseEntryId the primary key of the current license entry
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license entry
	* @throws com.liferay.osb.NoSuchLicenseEntryException if a license entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry[] findByPEI_PV_PrevAndNext(
		long licenseEntryId, long productEntryId, int portalVersionMin,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license entries.
	*
	* @return the license entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @return the range of license entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of license entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license entries where productEntryId = &#63; from the database.
	*
	* @param productEntryId the product entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByProductEntryId(long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license entries where type = &#63; from the database.
	*
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByType(java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the license entry where productEntryId = &#63; and type = &#63; from the database.
	*
	* @param productEntryId the product entry ID
	* @param type the type
	* @return the license entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry removeByPEI_T(
		long productEntryId, java.lang.String type)
		throws com.liferay.osb.NoSuchLicenseEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63; from the database.
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @throws SystemException if a system exception occurred
	*/
	public void removeByPEI_PV(long productEntryId, int portalVersionMin)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license entries where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @return the number of matching license entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByProductEntryId(long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license entries where type = &#63;.
	*
	* @param type the type
	* @return the number of matching license entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByType(java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license entries where productEntryId = &#63; and type = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param type the type
	* @return the number of matching license entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByPEI_T(long productEntryId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @return the number of matching license entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByPEI_PV(long productEntryId, int portalVersionMin)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license entries.
	*
	* @return the number of license entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}