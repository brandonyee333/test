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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the license key service. This utility wraps {@link LicenseKeyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyPersistence
 * @see LicenseKeyPersistenceImpl
 * @generated
 */
public class LicenseKeyUtil {
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
	public static void clearCache(LicenseKey licenseKey) {
		getPersistence().clearCache(licenseKey);
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
	public static List<LicenseKey> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LicenseKey> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LicenseKey> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static LicenseKey update(LicenseKey licenseKey, boolean merge)
		throws SystemException {
		return getPersistence().update(licenseKey, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static LicenseKey update(LicenseKey licenseKey, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(licenseKey, merge, serviceContext);
	}

	/**
	* Caches the license key in the entity cache if it is enabled.
	*
	* @param licenseKey the license key
	*/
	public static void cacheResult(com.liferay.osb.model.LicenseKey licenseKey) {
		getPersistence().cacheResult(licenseKey);
	}

	/**
	* Caches the license keies in the entity cache if it is enabled.
	*
	* @param licenseKeies the license keies
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.LicenseKey> licenseKeies) {
		getPersistence().cacheResult(licenseKeies);
	}

	/**
	* Creates a new license key with the primary key. Does not add the license key to the database.
	*
	* @param licenseKeyId the primary key for the new license key
	* @return the new license key
	*/
	public static com.liferay.osb.model.LicenseKey create(long licenseKeyId) {
		return getPersistence().create(licenseKeyId);
	}

	/**
	* Removes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key that was removed
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey remove(long licenseKeyId)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(licenseKeyId);
	}

	public static com.liferay.osb.model.LicenseKey updateImpl(
		com.liferay.osb.model.LicenseKey licenseKey, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(licenseKey, merge);
	}

	/**
	* Returns the license key with the primary key or throws a {@link com.liferay.osb.NoSuchLicenseKeyException} if it could not be found.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey findByPrimaryKey(
		long licenseKeyId)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(licenseKeyId);
	}

	/**
	* Returns the license key with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key, or <code>null</code> if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByPrimaryKey(
		long licenseKeyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(licenseKeyId);
	}

	/**
	* Returns all the license keies where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByUserId_PrevAndNext(
		long licenseKeyId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(licenseKeyId, userId,
			orderByComparator);
	}

	/**
	* Returns all the license keies where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByLicenseKeySetId(
		long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByLicenseKeySetId(licenseKeySetId);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByLicenseKeySetId(
		long licenseKeySetId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLicenseKeySetId(licenseKeySetId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByLicenseKeySetId(
		long licenseKeySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLicenseKeySetId(licenseKeySetId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey findByLicenseKeySetId_First(
		long licenseKeySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLicenseKeySetId_First(licenseKeySetId,
			orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByLicenseKeySetId_First(
		long licenseKeySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByLicenseKeySetId_First(licenseKeySetId,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey findByLicenseKeySetId_Last(
		long licenseKeySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLicenseKeySetId_Last(licenseKeySetId,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByLicenseKeySetId_Last(
		long licenseKeySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByLicenseKeySetId_Last(licenseKeySetId,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByLicenseKeySetId_PrevAndNext(
		long licenseKeyId, long licenseKeySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLicenseKeySetId_PrevAndNext(licenseKeyId,
			licenseKeySetId, orderByComparator);
	}

	/**
	* Returns all the license keies where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountEntryId(accountEntryId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_First(accountEntryId, orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAccountEntryId_First(accountEntryId,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByAccountEntryId_PrevAndNext(
		long licenseKeyId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_PrevAndNext(licenseKeyId,
			accountEntryId, orderByComparator);
	}

	/**
	* Returns all the license keies where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOfferingEntryId(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOfferingEntryId(offeringEntryId);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOfferingEntryId(
		long offeringEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOfferingEntryId(offeringEntryId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOfferingEntryId(
		long offeringEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOfferingEntryId(offeringEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey findByOfferingEntryId_First(
		long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOfferingEntryId_First(offeringEntryId,
			orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByOfferingEntryId_First(
		long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOfferingEntryId_First(offeringEntryId,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey findByOfferingEntryId_Last(
		long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOfferingEntryId_Last(offeringEntryId,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByOfferingEntryId_Last(
		long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOfferingEntryId_Last(offeringEntryId,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByOfferingEntryId_PrevAndNext(
		long licenseKeyId, long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOfferingEntryId_PrevAndNext(licenseKeyId,
			offeringEntryId, orderByComparator);
	}

	/**
	* Returns all the license keies where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOrderEntryId(
		long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOrderEntryId(orderEntryId);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOrderEntryId(
		long orderEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOrderEntryId(orderEntryId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOrderEntryId(
		long orderEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOrderEntryId(orderEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey findByOrderEntryId_First(
		long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOrderEntryId_First(orderEntryId, orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByOrderEntryId_First(
		long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOrderEntryId_First(orderEntryId, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey findByOrderEntryId_Last(
		long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOrderEntryId_Last(orderEntryId, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByOrderEntryId_Last(
		long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOrderEntryId_Last(orderEntryId, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByOrderEntryId_PrevAndNext(
		long licenseKeyId, long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOrderEntryId_PrevAndNext(licenseKeyId, orderEntryId,
			orderByComparator);
	}

	/**
	* Returns all the license keies where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByU_AEI(
		long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_AEI(userId, accountEntryId);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByU_AEI(
		long userId, long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_AEI(userId, accountEntryId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByU_AEI(
		long userId, long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_AEI(userId, accountEntryId, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByU_AEI_First(
		long userId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_AEI_First(userId, accountEntryId, orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByU_AEI_First(
		long userId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_AEI_First(userId, accountEntryId, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByU_AEI_Last(
		long userId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_AEI_Last(userId, accountEntryId, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByU_AEI_Last(
		long userId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_AEI_Last(userId, accountEntryId, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByU_AEI_PrevAndNext(
		long licenseKeyId, long userId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_AEI_PrevAndNext(licenseKeyId, userId,
			accountEntryId, orderByComparator);
	}

	/**
	* Returns all the license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_A(
		long assetReceiptLicenseId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByARLI_A(assetReceiptLicenseId, active);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_A(
		long assetReceiptLicenseId, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_A(assetReceiptLicenseId, active, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_A(
		long assetReceiptLicenseId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_A(assetReceiptLicenseId, active, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByARLI_A_First(
		long assetReceiptLicenseId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_A_First(assetReceiptLicenseId, active,
			orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByARLI_A_First(
		long assetReceiptLicenseId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARLI_A_First(assetReceiptLicenseId, active,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByARLI_A_Last(
		long assetReceiptLicenseId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_A_Last(assetReceiptLicenseId, active,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByARLI_A_Last(
		long assetReceiptLicenseId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARLI_A_Last(assetReceiptLicenseId, active,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByARLI_A_PrevAndNext(
		long licenseKeyId, long assetReceiptLicenseId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_A_PrevAndNext(licenseKeyId,
			assetReceiptLicenseId, active, orderByComparator);
	}

	/**
	* Returns all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_CI(
		long offeringEntryId, long clusterId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOEI_CI(offeringEntryId, clusterId);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_CI(
		long offeringEntryId, long clusterId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_CI(offeringEntryId, clusterId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_CI(
		long offeringEntryId, long clusterId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_CI(offeringEntryId, clusterId, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByOEI_CI_First(
		long offeringEntryId, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_CI_First(offeringEntryId, clusterId,
			orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByOEI_CI_First(
		long offeringEntryId, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOEI_CI_First(offeringEntryId, clusterId,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByOEI_CI_Last(
		long offeringEntryId, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_CI_Last(offeringEntryId, clusterId,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByOEI_CI_Last(
		long offeringEntryId, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOEI_CI_Last(offeringEntryId, clusterId,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByOEI_CI_PrevAndNext(
		long licenseKeyId, long offeringEntryId, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_CI_PrevAndNext(licenseKeyId, offeringEntryId,
			clusterId, orderByComparator);
	}

	/**
	* Returns all the license keies where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_O(
		long orderEntryId, java.lang.String owner)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOEI_O(orderEntryId, owner);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_O(
		long orderEntryId, java.lang.String owner, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOEI_O(orderEntryId, owner, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_O(
		long orderEntryId, java.lang.String owner, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_O(orderEntryId, owner, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByOEI_O_First(
		long orderEntryId, java.lang.String owner,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_O_First(orderEntryId, owner, orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByOEI_O_First(
		long orderEntryId, java.lang.String owner,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOEI_O_First(orderEntryId, owner, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByOEI_O_Last(
		long orderEntryId, java.lang.String owner,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_O_Last(orderEntryId, owner, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByOEI_O_Last(
		long orderEntryId, java.lang.String owner,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOEI_O_Last(orderEntryId, owner, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByOEI_O_PrevAndNext(
		long licenseKeyId, long orderEntryId, java.lang.String owner,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_O_PrevAndNext(licenseKeyId, orderEntryId, owner,
			orderByComparator);
	}

	/**
	* Returns all the license keies where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByPI_SI(
		java.lang.String productId, java.lang.String serverId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPI_SI(productId, serverId);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByPI_SI(
		java.lang.String productId, java.lang.String serverId, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPI_SI(productId, serverId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByPI_SI(
		java.lang.String productId, java.lang.String serverId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPI_SI(productId, serverId, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByPI_SI_First(
		java.lang.String productId, java.lang.String serverId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPI_SI_First(productId, serverId, orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByPI_SI_First(
		java.lang.String productId, java.lang.String serverId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPI_SI_First(productId, serverId, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByPI_SI_Last(
		java.lang.String productId, java.lang.String serverId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPI_SI_Last(productId, serverId, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey fetchByPI_SI_Last(
		java.lang.String productId, java.lang.String serverId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPI_SI_Last(productId, serverId, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByPI_SI_PrevAndNext(
		long licenseKeyId, java.lang.String productId,
		java.lang.String serverId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPI_SI_PrevAndNext(licenseKeyId, productId, serverId,
			orderByComparator);
	}

	/**
	* Returns all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByU_ARLI_PI(
		long userId, long assetReceiptLicenseId, java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_ARLI_PI(userId, assetReceiptLicenseId, productId);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByU_ARLI_PI(
		long userId, long assetReceiptLicenseId, java.lang.String productId,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_ARLI_PI(userId, assetReceiptLicenseId, productId,
			start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByU_ARLI_PI(
		long userId, long assetReceiptLicenseId, java.lang.String productId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_ARLI_PI(userId, assetReceiptLicenseId, productId,
			start, end, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByU_ARLI_PI_First(
		long userId, long assetReceiptLicenseId, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_ARLI_PI_First(userId, assetReceiptLicenseId,
			productId, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey fetchByU_ARLI_PI_First(
		long userId, long assetReceiptLicenseId, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_ARLI_PI_First(userId, assetReceiptLicenseId,
			productId, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByU_ARLI_PI_Last(
		long userId, long assetReceiptLicenseId, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_ARLI_PI_Last(userId, assetReceiptLicenseId,
			productId, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey fetchByU_ARLI_PI_Last(
		long userId, long assetReceiptLicenseId, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_ARLI_PI_Last(userId, assetReceiptLicenseId,
			productId, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByU_ARLI_PI_PrevAndNext(
		long licenseKeyId, long userId, long assetReceiptLicenseId,
		java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_ARLI_PI_PrevAndNext(licenseKeyId, userId,
			assetReceiptLicenseId, productId, orderByComparator);
	}

	/**
	* Returns all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_C_A(
		long assetReceiptLicenseId, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_C_A(assetReceiptLicenseId, complimentary, active);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_C_A(
		long assetReceiptLicenseId, boolean complimentary, boolean active,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_C_A(assetReceiptLicenseId, complimentary,
			active, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_C_A(
		long assetReceiptLicenseId, boolean complimentary, boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_C_A(assetReceiptLicenseId, complimentary,
			active, start, end, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByARLI_C_A_First(
		long assetReceiptLicenseId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_C_A_First(assetReceiptLicenseId, complimentary,
			active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey fetchByARLI_C_A_First(
		long assetReceiptLicenseId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARLI_C_A_First(assetReceiptLicenseId, complimentary,
			active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByARLI_C_A_Last(
		long assetReceiptLicenseId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_C_A_Last(assetReceiptLicenseId, complimentary,
			active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey fetchByARLI_C_A_Last(
		long assetReceiptLicenseId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARLI_C_A_Last(assetReceiptLicenseId, complimentary,
			active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByARLI_C_A_PrevAndNext(
		long licenseKeyId, long assetReceiptLicenseId, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_C_A_PrevAndNext(licenseKeyId,
			assetReceiptLicenseId, complimentary, active, orderByComparator);
	}

	/**
	* Returns all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_CI_A(offeringEntryId, clusterId, active);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_CI_A(offeringEntryId, clusterId, active, start,
			end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_CI_A(offeringEntryId, clusterId, active, start,
			end, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByOEI_CI_A_First(
		long offeringEntryId, long clusterId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_CI_A_First(offeringEntryId, clusterId, active,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey fetchByOEI_CI_A_First(
		long offeringEntryId, long clusterId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOEI_CI_A_First(offeringEntryId, clusterId, active,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByOEI_CI_A_Last(
		long offeringEntryId, long clusterId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_CI_A_Last(offeringEntryId, clusterId, active,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey fetchByOEI_CI_A_Last(
		long offeringEntryId, long clusterId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOEI_CI_A_Last(offeringEntryId, clusterId, active,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByOEI_CI_A_PrevAndNext(
		long licenseKeyId, long offeringEntryId, long clusterId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_CI_A_PrevAndNext(licenseKeyId, offeringEntryId,
			clusterId, active, orderByComparator);
	}

	/**
	* Returns all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_C_A(offeringEntryId, complimentary, active);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_C_A(offeringEntryId, complimentary, active,
			start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_C_A(offeringEntryId, complimentary, active,
			start, end, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByOEI_C_A_First(
		long offeringEntryId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_C_A_First(offeringEntryId, complimentary, active,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey fetchByOEI_C_A_First(
		long offeringEntryId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOEI_C_A_First(offeringEntryId, complimentary,
			active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByOEI_C_A_Last(
		long offeringEntryId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_C_A_Last(offeringEntryId, complimentary, active,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey fetchByOEI_C_A_Last(
		long offeringEntryId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOEI_C_A_Last(offeringEntryId, complimentary, active,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByOEI_C_A_PrevAndNext(
		long licenseKeyId, long offeringEntryId, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_C_A_PrevAndNext(licenseKeyId, offeringEntryId,
			complimentary, active, orderByComparator);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_C_A(
		long[] offeringEntryIds, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_C_A(offeringEntryIds, complimentary, active);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_C_A(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_C_A(offeringEntryIds, complimentary, active,
			start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_C_A(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_C_A(offeringEntryIds, complimentary, active,
			start, end, orderByComparator);
	}

	/**
	* Returns all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @return the matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByPEN_SI_A(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPEN_SI_A(productEntryName, serverId, active);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByPEN_SI_A(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPEN_SI_A(productEntryName, serverId, active, start,
			end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByPEN_SI_A(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPEN_SI_A(productEntryName, serverId, active, start,
			end, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByPEN_SI_A_First(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPEN_SI_A_First(productEntryName, serverId, active,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey fetchByPEN_SI_A_First(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPEN_SI_A_First(productEntryName, serverId, active,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByPEN_SI_A_Last(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPEN_SI_A_Last(productEntryName, serverId, active,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey fetchByPEN_SI_A_Last(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPEN_SI_A_Last(productEntryName, serverId, active,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByPEN_SI_A_PrevAndNext(
		long licenseKeyId, java.lang.String productEntryName,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPEN_SI_A_PrevAndNext(licenseKeyId, productEntryName,
			serverId, active, orderByComparator);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_PI_SI_A(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_PI_SI_A(assetReceiptLicenseId, productId,
			serverId, active);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_PI_SI_A(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_PI_SI_A(assetReceiptLicenseId, productId,
			serverId, active, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByARLI_PI_SI_A(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_PI_SI_A(assetReceiptLicenseId, productId,
			serverId, active, start, end, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByARLI_PI_SI_A_First(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_PI_SI_A_First(assetReceiptLicenseId, productId,
			serverId, active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey fetchByARLI_PI_SI_A_First(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARLI_PI_SI_A_First(assetReceiptLicenseId, productId,
			serverId, active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByARLI_PI_SI_A_Last(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_PI_SI_A_Last(assetReceiptLicenseId, productId,
			serverId, active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey fetchByARLI_PI_SI_A_Last(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARLI_PI_SI_A_Last(assetReceiptLicenseId, productId,
			serverId, active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByARLI_PI_SI_A_PrevAndNext(
		long licenseKeyId, long assetReceiptLicenseId,
		java.lang.String productId, java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARLI_PI_SI_A_PrevAndNext(licenseKeyId,
			assetReceiptLicenseId, productId, serverId, active,
			orderByComparator);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_LET_C_A(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_LET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_LET_C_A(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_LET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_LET_C_A(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_LET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, start, end, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByOEI_LET_C_A_First(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_LET_C_A_First(offeringEntryId, licenseEntryType,
			complimentary, active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey fetchByOEI_LET_C_A_First(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOEI_LET_C_A_First(offeringEntryId, licenseEntryType,
			complimentary, active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByOEI_LET_C_A_Last(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_LET_C_A_Last(offeringEntryId, licenseEntryType,
			complimentary, active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey fetchByOEI_LET_C_A_Last(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOEI_LET_C_A_Last(offeringEntryId, licenseEntryType,
			complimentary, active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByOEI_LET_C_A_PrevAndNext(
		long licenseKeyId, long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_LET_C_A_PrevAndNext(licenseKeyId,
			offeringEntryId, licenseEntryType, complimentary, active,
			orderByComparator);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, start, end, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByOEI_NotLET_C_A_First(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_NotLET_C_A_First(offeringEntryId,
			licenseEntryType, complimentary, active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey fetchByOEI_NotLET_C_A_First(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOEI_NotLET_C_A_First(offeringEntryId,
			licenseEntryType, complimentary, active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey findByOEI_NotLET_C_A_Last(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_NotLET_C_A_Last(offeringEntryId,
			licenseEntryType, complimentary, active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey fetchByOEI_NotLET_C_A_Last(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOEI_NotLET_C_A_Last(offeringEntryId,
			licenseEntryType, complimentary, active, orderByComparator);
	}

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
	public static com.liferay.osb.model.LicenseKey[] findByOEI_NotLET_C_A_PrevAndNext(
		long licenseKeyId, long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchLicenseKeyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOEI_NotLET_C_A_PrevAndNext(licenseKeyId,
			offeringEntryId, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	* Returns all the license keies.
	*
	* @return the license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.LicenseKey> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the license keies where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes all the license keies where licenseKeySetId = &#63; from the database.
	*
	* @param licenseKeySetId the license key set ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByLicenseKeySetId(long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByLicenseKeySetId(licenseKeySetId);
	}

	/**
	* Removes all the license keies where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	* Removes all the license keies where offeringEntryId = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOfferingEntryId(long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByOfferingEntryId(offeringEntryId);
	}

	/**
	* Removes all the license keies where orderEntryId = &#63; from the database.
	*
	* @param orderEntryId the order entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOrderEntryId(long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByOrderEntryId(orderEntryId);
	}

	/**
	* Removes all the license keies where userId = &#63; and accountEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_AEI(long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_AEI(userId, accountEntryId);
	}

	/**
	* Removes all the license keies where assetReceiptLicenseId = &#63; and active = &#63; from the database.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByARLI_A(long assetReceiptLicenseId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByARLI_A(assetReceiptLicenseId, active);
	}

	/**
	* Removes all the license keies where offeringEntryId = &#63; and clusterId = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOEI_CI(long offeringEntryId, long clusterId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByOEI_CI(offeringEntryId, clusterId);
	}

	/**
	* Removes all the license keies where orderEntryId = &#63; and owner = &#63; from the database.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOEI_O(long orderEntryId, java.lang.String owner)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByOEI_O(orderEntryId, owner);
	}

	/**
	* Removes all the license keies where productId = &#63; and serverId = &#63; from the database.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByPI_SI(java.lang.String productId,
		java.lang.String serverId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByPI_SI(productId, serverId);
	}

	/**
	* Removes all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63; from the database.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_ARLI_PI(long userId,
		long assetReceiptLicenseId, java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByU_ARLI_PI(userId, assetReceiptLicenseId, productId);
	}

	/**
	* Removes all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63; from the database.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByARLI_C_A(assetReceiptLicenseId, complimentary, active);
	}

	/**
	* Removes all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOEI_CI_A(long offeringEntryId, long clusterId,
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByOEI_CI_A(offeringEntryId, clusterId, active);
	}

	/**
	* Removes all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByOEI_C_A(offeringEntryId, complimentary, active);
	}

	/**
	* Removes all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63; from the database.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByPEN_SI_A(java.lang.String productEntryName,
		java.lang.String serverId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByPEN_SI_A(productEntryName, serverId, active);
	}

	/**
	* Removes all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63; from the database.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByARLI_PI_SI_A(long assetReceiptLicenseId,
		java.lang.String productId, java.lang.String serverId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByARLI_PI_SI_A(assetReceiptLicenseId, productId, serverId,
			active);
	}

	/**
	* Removes all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOEI_LET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByOEI_LET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active);
	}

	/**
	* Removes all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOEI_NotLET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active);
	}

	/**
	* Removes all the license keies from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of license keies where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of license keies where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByLicenseKeySetId(long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByLicenseKeySetId(licenseKeySetId);
	}

	/**
	* Returns the number of license keies where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the number of license keies where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOfferingEntryId(long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByOfferingEntryId(offeringEntryId);
	}

	/**
	* Returns the number of license keies where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOrderEntryId(long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByOrderEntryId(orderEntryId);
	}

	/**
	* Returns the number of license keies where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_AEI(long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns the number of license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByARLI_A(long assetReceiptLicenseId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByARLI_A(assetReceiptLicenseId, active);
	}

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOEI_CI(long offeringEntryId, long clusterId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByOEI_CI(offeringEntryId, clusterId);
	}

	/**
	* Returns the number of license keies where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOEI_O(long orderEntryId, java.lang.String owner)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByOEI_O(orderEntryId, owner);
	}

	/**
	* Returns the number of license keies where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPI_SI(java.lang.String productId,
		java.lang.String serverId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByPI_SI(productId, serverId);
	}

	/**
	* Returns the number of license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_ARLI_PI(long userId, long assetReceiptLicenseId,
		java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByU_ARLI_PI(userId, assetReceiptLicenseId, productId);
	}

	/**
	* Returns the number of license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByARLI_C_A(assetReceiptLicenseId, complimentary, active);
	}

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOEI_CI_A(long offeringEntryId, long clusterId,
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByOEI_CI_A(offeringEntryId, clusterId, active);
	}

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByOEI_C_A(offeringEntryId, complimentary, active);
	}

	/**
	* Returns the number of license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryIds the offering entry IDs
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByOEI_C_A(offeringEntryIds, complimentary, active);
	}

	/**
	* Returns the number of license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @return the number of matching license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPEN_SI_A(java.lang.String productEntryName,
		java.lang.String serverId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByPEN_SI_A(productEntryName, serverId, active);
	}

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
	public static int countByARLI_PI_SI_A(long assetReceiptLicenseId,
		java.lang.String productId, java.lang.String serverId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByARLI_PI_SI_A(assetReceiptLicenseId, productId,
			serverId, active);
	}

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
	public static int countByOEI_LET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByOEI_LET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active);
	}

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
	public static int countByOEI_NotLET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active);
	}

	/**
	* Returns the number of license keies.
	*
	* @return the number of license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static LicenseKeyPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (LicenseKeyPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					LicenseKeyPersistence.class.getName());

			ReferenceRegistry.registerReference(LicenseKeyUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(LicenseKeyPersistence persistence) {
	}

	private static LicenseKeyPersistence _persistence;
}