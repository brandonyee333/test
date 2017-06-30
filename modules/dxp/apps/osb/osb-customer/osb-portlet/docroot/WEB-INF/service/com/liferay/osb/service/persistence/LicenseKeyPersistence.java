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

import com.liferay.osb.model.LicenseKey;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the license key service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyPersistenceImpl
 * @see LicenseKeyUtil
 * @generated
 */
public interface LicenseKeyPersistence extends BasePersistence<LicenseKey> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LicenseKeyUtil} to access the license key persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the license key in the entity cache if it is enabled.
	*
	* @param licenseKey the license key
	*/
	public void cacheResult(com.liferay.osb.model.LicenseKey licenseKey);

	/**
	* Caches the license keies in the entity cache if it is enabled.
	*
	* @param licenseKeies the license keies
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.LicenseKey> licenseKeies);

	/**
	* Creates a new license key with the primary key. Does not add the license key to the database.
	*
	* @param licenseKeyId the primary key for the new license key
	* @return the new license key
	*/
	public com.liferay.osb.model.LicenseKey create(long licenseKeyId);

	/**
	* Removes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key that was removed
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey remove(long licenseKeyId)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.LicenseKey updateImpl(
		com.liferay.osb.model.LicenseKey licenseKey, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license key with the primary key or throws a {@link com.liferay.osb.NoSuchLicenseKeyException} if it could not be found.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByPrimaryKey(long licenseKeyId)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license key with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key, or <code>null</code> if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByPrimaryKey(long licenseKeyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where userId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByUserId_PrevAndNext(
		long licenseKeyId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByLicenseKeySetId(
		long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where licenseKeySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param licenseKeySetId the license key set ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByLicenseKeySetId(
		long licenseKeySetId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where licenseKeySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param licenseKeySetId the license key set ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByLicenseKeySetId(
		long licenseKeySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByLicenseKeySetId_First(
		long licenseKeySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByLicenseKeySetId_First(
		long licenseKeySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByLicenseKeySetId_Last(
		long licenseKeySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByLicenseKeySetId_Last(
		long licenseKeySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByLicenseKeySetId_PrevAndNext(
		long licenseKeyId, long licenseKeySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where accountEntryId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByAccountEntryId_PrevAndNext(
		long licenseKeyId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOfferingEntryId(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where offeringEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOfferingEntryId(
		long offeringEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOfferingEntryId(
		long offeringEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByOfferingEntryId_First(
		long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByOfferingEntryId_First(
		long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByOfferingEntryId_Last(
		long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByOfferingEntryId_Last(
		long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByOfferingEntryId_PrevAndNext(
		long licenseKeyId, long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOrderEntryId(
		long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where orderEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param orderEntryId the order entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOrderEntryId(
		long orderEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where orderEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param orderEntryId the order entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOrderEntryId(
		long orderEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByOrderEntryId_First(
		long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByOrderEntryId_First(
		long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByOrderEntryId_Last(
		long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByOrderEntryId_Last(
		long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where orderEntryId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByOrderEntryId_PrevAndNext(
		long licenseKeyId, long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByU_AEI(
		long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where userId = &#63; and accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByU_AEI(
		long userId, long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where userId = &#63; and accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByU_AEI(
		long userId, long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByU_AEI_First(long userId,
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByU_AEI_First(long userId,
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByU_AEI_Last(long userId,
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByU_AEI_Last(long userId,
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByU_AEI_PrevAndNext(
		long licenseKeyId, long userId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_A(
		long assetReceiptLicenseId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_A(
		long assetReceiptLicenseId, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_A(
		long assetReceiptLicenseId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByARLI_A_First(
		long assetReceiptLicenseId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByARLI_A_First(
		long assetReceiptLicenseId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByARLI_A_Last(
		long assetReceiptLicenseId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByARLI_A_Last(
		long assetReceiptLicenseId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByARLI_A_PrevAndNext(
		long licenseKeyId, long assetReceiptLicenseId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_CI(
		long offeringEntryId, long clusterId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_CI(
		long offeringEntryId, long clusterId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_CI(
		long offeringEntryId, long clusterId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByOEI_CI_First(
		long offeringEntryId, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByOEI_CI_First(
		long offeringEntryId, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByOEI_CI_Last(
		long offeringEntryId, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByOEI_CI_Last(
		long offeringEntryId, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByOEI_CI_PrevAndNext(
		long licenseKeyId, long offeringEntryId, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_O(
		long orderEntryId, java.lang.String owner)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where orderEntryId = &#63; and owner = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_O(
		long orderEntryId, java.lang.String owner, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where orderEntryId = &#63; and owner = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_O(
		long orderEntryId, java.lang.String owner, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByOEI_O_First(
		long orderEntryId, java.lang.String owner,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByOEI_O_First(
		long orderEntryId, java.lang.String owner,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByOEI_O_Last(
		long orderEntryId, java.lang.String owner,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByOEI_O_Last(
		long orderEntryId, java.lang.String owner,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByOEI_O_PrevAndNext(
		long licenseKeyId, long orderEntryId, java.lang.String owner,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByPI_SI(
		java.lang.String productId, java.lang.String serverId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where productId = &#63; and serverId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByPI_SI(
		java.lang.String productId, java.lang.String serverId, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where productId = &#63; and serverId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByPI_SI(
		java.lang.String productId, java.lang.String serverId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByPI_SI_First(
		java.lang.String productId, java.lang.String serverId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByPI_SI_First(
		java.lang.String productId, java.lang.String serverId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByPI_SI_Last(
		java.lang.String productId, java.lang.String serverId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByPI_SI_Last(
		java.lang.String productId, java.lang.String serverId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where productId = &#63; and serverId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param productId the product ID
	* @param serverId the server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByPI_SI_PrevAndNext(
		long licenseKeyId, java.lang.String productId,
		java.lang.String serverId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByU_ARLI_PI(
		long userId, long assetReceiptLicenseId, java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByU_ARLI_PI(
		long userId, long assetReceiptLicenseId, java.lang.String productId,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByU_ARLI_PI(
		long userId, long assetReceiptLicenseId, java.lang.String productId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByU_ARLI_PI_First(long userId,
		long assetReceiptLicenseId, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByU_ARLI_PI_First(
		long userId, long assetReceiptLicenseId, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByU_ARLI_PI_Last(long userId,
		long assetReceiptLicenseId, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByU_ARLI_PI_Last(long userId,
		long assetReceiptLicenseId, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByU_ARLI_PI_PrevAndNext(
		long licenseKeyId, long userId, long assetReceiptLicenseId,
		java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_C_A(
		long assetReceiptLicenseId, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_C_A(
		long assetReceiptLicenseId, boolean complimentary, boolean active,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_C_A(
		long assetReceiptLicenseId, boolean complimentary, boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByARLI_C_A_First(
		long assetReceiptLicenseId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByARLI_C_A_First(
		long assetReceiptLicenseId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByARLI_C_A_Last(
		long assetReceiptLicenseId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByARLI_C_A_Last(
		long assetReceiptLicenseId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByARLI_C_A_PrevAndNext(
		long licenseKeyId, long assetReceiptLicenseId, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByOEI_CI_A_First(
		long offeringEntryId, long clusterId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByOEI_CI_A_First(
		long offeringEntryId, long clusterId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByOEI_CI_A_Last(
		long offeringEntryId, long clusterId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByOEI_CI_A_Last(
		long offeringEntryId, long clusterId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByOEI_CI_A_PrevAndNext(
		long licenseKeyId, long offeringEntryId, long clusterId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByOEI_C_A_First(
		long offeringEntryId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByOEI_C_A_First(
		long offeringEntryId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByOEI_C_A_Last(
		long offeringEntryId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByOEI_C_A_Last(
		long offeringEntryId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByOEI_C_A_PrevAndNext(
		long licenseKeyId, long offeringEntryId, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryIds the offering entry IDs
	* @param complimentary the complimentary
	* @param active the active
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_C_A(
		long[] offeringEntryIds, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryIds the offering entry IDs
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_C_A(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryIds the offering entry IDs
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_C_A(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByPEN_SI_A(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByPEN_SI_A(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByPEN_SI_A(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByPEN_SI_A_First(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByPEN_SI_A_First(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByPEN_SI_A_Last(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByPEN_SI_A_Last(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByPEN_SI_A_PrevAndNext(
		long licenseKeyId, java.lang.String productEntryName,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_PI_SI_A(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_PI_SI_A(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_PI_SI_A(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByARLI_PI_SI_A_First(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByARLI_PI_SI_A_First(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByARLI_PI_SI_A_Last(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByARLI_PI_SI_A_Last(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByARLI_PI_SI_A_PrevAndNext(
		long licenseKeyId, long assetReceiptLicenseId,
		java.lang.String productId, java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_LET_C_A(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_LET_C_A(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_LET_C_A(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByOEI_LET_C_A_First(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByOEI_LET_C_A_First(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByOEI_LET_C_A_Last(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByOEI_LET_C_A_Last(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByOEI_LET_C_A_PrevAndNext(
		long licenseKeyId, long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByOEI_NotLET_C_A_First(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByOEI_NotLET_C_A_First(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey findByOEI_NotLET_C_A_Last(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey fetchByOEI_NotLET_C_A_Last(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKey[] findByOEI_NotLET_C_A_PrevAndNext(
		long licenseKeyId, long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the license keies.
	*
	* @return the license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the license keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the license keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of license keies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKey> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where licenseKeySetId = &#63; from the database.
	*
	* @param licenseKeySetId the license key set ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByLicenseKeySetId(long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where offeringEntryId = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOfferingEntryId(long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where orderEntryId = &#63; from the database.
	*
	* @param orderEntryId the order entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOrderEntryId(long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where userId = &#63; and accountEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_AEI(long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where assetReceiptLicenseId = &#63; and active = &#63; from the database.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public void removeByARLI_A(long assetReceiptLicenseId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where offeringEntryId = &#63; and clusterId = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOEI_CI(long offeringEntryId, long clusterId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where orderEntryId = &#63; and owner = &#63; from the database.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOEI_O(long orderEntryId, java.lang.String owner)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where productId = &#63; and serverId = &#63; from the database.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByPI_SI(java.lang.String productId,
		java.lang.String serverId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63; from the database.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_ARLI_PI(long userId, long assetReceiptLicenseId,
		java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63; from the database.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public void removeByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOEI_CI_A(long offeringEntryId, long clusterId,
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOEI_C_A(long offeringEntryId, boolean complimentary,
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63; from the database.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public void removeByPEN_SI_A(java.lang.String productEntryName,
		java.lang.String serverId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63; from the database.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public void removeByARLI_PI_SI_A(long assetReceiptLicenseId,
		java.lang.String productId, java.lang.String serverId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOEI_LET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOEI_NotLET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the license keies from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByLicenseKeySetId(long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByOfferingEntryId(long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByOrderEntryId(long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_AEI(long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByARLI_A(long assetReceiptLicenseId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByOEI_CI(long offeringEntryId, long clusterId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByOEI_O(long orderEntryId, java.lang.String owner)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByPI_SI(java.lang.String productId,
		java.lang.String serverId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_ARLI_PI(long userId, long assetReceiptLicenseId,
		java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByOEI_CI_A(long offeringEntryId, long clusterId,
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByOEI_C_A(long offeringEntryId, boolean complimentary,
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryIds the offering entry IDs
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByOEI_C_A(long[] offeringEntryIds, boolean complimentary,
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByPEN_SI_A(java.lang.String productEntryName,
		java.lang.String serverId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByARLI_PI_SI_A(long assetReceiptLicenseId,
		java.lang.String productId, java.lang.String serverId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByOEI_LET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countByOEI_NotLET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of license keies.
	*
	* @return the number of license keies
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}