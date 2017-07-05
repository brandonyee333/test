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

import com.liferay.osb.model.LicenseKeySet;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the license key set service. This utility wraps {@link com.liferay.osb.service.persistence.impl.LicenseKeySetPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeySetPersistence
 * @see com.liferay.osb.service.persistence.impl.LicenseKeySetPersistenceImpl
 * @generated
 */
@ProviderType
public class LicenseKeySetUtil {
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
	public static void clearCache(LicenseKeySet licenseKeySet) {
		getPersistence().clearCache(licenseKeySet);
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
	public static List<LicenseKeySet> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LicenseKeySet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LicenseKeySet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LicenseKeySet update(LicenseKeySet licenseKeySet) {
		return getPersistence().update(licenseKeySet);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LicenseKeySet update(LicenseKeySet licenseKeySet,
		ServiceContext serviceContext) {
		return getPersistence().update(licenseKeySet, serviceContext);
	}

	/**
	* Returns all the license key sets where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching license key sets
	*/
	public static List<LicenseKeySet> findByAccountEntryId(long accountEntryId) {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	* Returns a range of all the license key sets where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @return the range of matching license key sets
	*/
	public static List<LicenseKeySet> findByAccountEntryId(
		long accountEntryId, int start, int end) {
		return getPersistence().findByAccountEntryId(accountEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the license key sets where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license key sets
	*/
	public static List<LicenseKeySet> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the license key sets where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license key sets
	*/
	public static List<LicenseKeySet> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license key set in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key set
	* @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	*/
	public static LicenseKeySet findByAccountEntryId_First(
		long accountEntryId, OrderByComparator<LicenseKeySet> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeySetException {
		return getPersistence()
				   .findByAccountEntryId_First(accountEntryId, orderByComparator);
	}

	/**
	* Returns the first license key set in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key set, or <code>null</code> if a matching license key set could not be found
	*/
	public static LicenseKeySet fetchByAccountEntryId_First(
		long accountEntryId, OrderByComparator<LicenseKeySet> orderByComparator) {
		return getPersistence()
				   .fetchByAccountEntryId_First(accountEntryId,
			orderByComparator);
	}

	/**
	* Returns the last license key set in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key set
	* @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	*/
	public static LicenseKeySet findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<LicenseKeySet> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeySetException {
		return getPersistence()
				   .findByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the last license key set in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key set, or <code>null</code> if a matching license key set could not be found
	*/
	public static LicenseKeySet fetchByAccountEntryId_Last(
		long accountEntryId, OrderByComparator<LicenseKeySet> orderByComparator) {
		return getPersistence()
				   .fetchByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the license key sets before and after the current license key set in the ordered set where accountEntryId = &#63;.
	*
	* @param licenseKeySetId the primary key of the current license key set
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key set
	* @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	*/
	public static LicenseKeySet[] findByAccountEntryId_PrevAndNext(
		long licenseKeySetId, long accountEntryId,
		OrderByComparator<LicenseKeySet> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeySetException {
		return getPersistence()
				   .findByAccountEntryId_PrevAndNext(licenseKeySetId,
			accountEntryId, orderByComparator);
	}

	/**
	* Removes all the license key sets where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public static void removeByAccountEntryId(long accountEntryId) {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the number of license key sets where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching license key sets
	*/
	public static int countByAccountEntryId(long accountEntryId) {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	* Returns all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @return the matching license key sets
	*/
	public static List<LicenseKeySet> findByU_AEI_N(long userId,
		long accountEntryId, java.lang.String name) {
		return getPersistence().findByU_AEI_N(userId, accountEntryId, name);
	}

	/**
	* Returns a range of all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @return the range of matching license key sets
	*/
	public static List<LicenseKeySet> findByU_AEI_N(long userId,
		long accountEntryId, java.lang.String name, int start, int end) {
		return getPersistence()
				   .findByU_AEI_N(userId, accountEntryId, name, start, end);
	}

	/**
	* Returns an ordered range of all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license key sets
	*/
	public static List<LicenseKeySet> findByU_AEI_N(long userId,
		long accountEntryId, java.lang.String name, int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator) {
		return getPersistence()
				   .findByU_AEI_N(userId, accountEntryId, name, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license key sets
	*/
	public static List<LicenseKeySet> findByU_AEI_N(long userId,
		long accountEntryId, java.lang.String name, int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_AEI_N(userId, accountEntryId, name, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key set
	* @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	*/
	public static LicenseKeySet findByU_AEI_N_First(long userId,
		long accountEntryId, java.lang.String name,
		OrderByComparator<LicenseKeySet> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeySetException {
		return getPersistence()
				   .findByU_AEI_N_First(userId, accountEntryId, name,
			orderByComparator);
	}

	/**
	* Returns the first license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key set, or <code>null</code> if a matching license key set could not be found
	*/
	public static LicenseKeySet fetchByU_AEI_N_First(long userId,
		long accountEntryId, java.lang.String name,
		OrderByComparator<LicenseKeySet> orderByComparator) {
		return getPersistence()
				   .fetchByU_AEI_N_First(userId, accountEntryId, name,
			orderByComparator);
	}

	/**
	* Returns the last license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key set
	* @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	*/
	public static LicenseKeySet findByU_AEI_N_Last(long userId,
		long accountEntryId, java.lang.String name,
		OrderByComparator<LicenseKeySet> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeySetException {
		return getPersistence()
				   .findByU_AEI_N_Last(userId, accountEntryId, name,
			orderByComparator);
	}

	/**
	* Returns the last license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key set, or <code>null</code> if a matching license key set could not be found
	*/
	public static LicenseKeySet fetchByU_AEI_N_Last(long userId,
		long accountEntryId, java.lang.String name,
		OrderByComparator<LicenseKeySet> orderByComparator) {
		return getPersistence()
				   .fetchByU_AEI_N_Last(userId, accountEntryId, name,
			orderByComparator);
	}

	/**
	* Returns the license key sets before and after the current license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* @param licenseKeySetId the primary key of the current license key set
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key set
	* @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	*/
	public static LicenseKeySet[] findByU_AEI_N_PrevAndNext(
		long licenseKeySetId, long userId, long accountEntryId,
		java.lang.String name,
		OrderByComparator<LicenseKeySet> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeySetException {
		return getPersistence()
				   .findByU_AEI_N_PrevAndNext(licenseKeySetId, userId,
			accountEntryId, name, orderByComparator);
	}

	/**
	* Removes all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	*/
	public static void removeByU_AEI_N(long userId, long accountEntryId,
		java.lang.String name) {
		getPersistence().removeByU_AEI_N(userId, accountEntryId, name);
	}

	/**
	* Returns the number of license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @return the number of matching license key sets
	*/
	public static int countByU_AEI_N(long userId, long accountEntryId,
		java.lang.String name) {
		return getPersistence().countByU_AEI_N(userId, accountEntryId, name);
	}

	/**
	* Caches the license key set in the entity cache if it is enabled.
	*
	* @param licenseKeySet the license key set
	*/
	public static void cacheResult(LicenseKeySet licenseKeySet) {
		getPersistence().cacheResult(licenseKeySet);
	}

	/**
	* Caches the license key sets in the entity cache if it is enabled.
	*
	* @param licenseKeySets the license key sets
	*/
	public static void cacheResult(List<LicenseKeySet> licenseKeySets) {
		getPersistence().cacheResult(licenseKeySets);
	}

	/**
	* Creates a new license key set with the primary key. Does not add the license key set to the database.
	*
	* @param licenseKeySetId the primary key for the new license key set
	* @return the new license key set
	*/
	public static LicenseKeySet create(long licenseKeySetId) {
		return getPersistence().create(licenseKeySetId);
	}

	/**
	* Removes the license key set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKeySetId the primary key of the license key set
	* @return the license key set that was removed
	* @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	*/
	public static LicenseKeySet remove(long licenseKeySetId)
		throws com.liferay.osb.exception.NoSuchLicenseKeySetException {
		return getPersistence().remove(licenseKeySetId);
	}

	public static LicenseKeySet updateImpl(LicenseKeySet licenseKeySet) {
		return getPersistence().updateImpl(licenseKeySet);
	}

	/**
	* Returns the license key set with the primary key or throws a {@link NoSuchLicenseKeySetException} if it could not be found.
	*
	* @param licenseKeySetId the primary key of the license key set
	* @return the license key set
	* @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	*/
	public static LicenseKeySet findByPrimaryKey(long licenseKeySetId)
		throws com.liferay.osb.exception.NoSuchLicenseKeySetException {
		return getPersistence().findByPrimaryKey(licenseKeySetId);
	}

	/**
	* Returns the license key set with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param licenseKeySetId the primary key of the license key set
	* @return the license key set, or <code>null</code> if a license key set with the primary key could not be found
	*/
	public static LicenseKeySet fetchByPrimaryKey(long licenseKeySetId) {
		return getPersistence().fetchByPrimaryKey(licenseKeySetId);
	}

	public static java.util.Map<java.io.Serializable, LicenseKeySet> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the license key sets.
	*
	* @return the license key sets
	*/
	public static List<LicenseKeySet> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the license key sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @return the range of license key sets
	*/
	public static List<LicenseKeySet> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the license key sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of license key sets
	*/
	public static List<LicenseKeySet> findAll(int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the license key sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of license key sets
	*/
	public static List<LicenseKeySet> findAll(int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the license key sets from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of license key sets.
	*
	* @return the number of license key sets
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LicenseKeySetPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (LicenseKeySetPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					LicenseKeySetPersistence.class.getName());

			ReferenceRegistry.registerReference(LicenseKeySetUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static LicenseKeySetPersistence _persistence;
}