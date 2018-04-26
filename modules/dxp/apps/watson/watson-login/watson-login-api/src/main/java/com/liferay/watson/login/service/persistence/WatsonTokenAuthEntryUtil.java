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

package com.liferay.watson.login.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.watson.login.model.WatsonTokenAuthEntry;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the watson token auth entry service. This utility wraps {@link com.liferay.watson.login.service.persistence.impl.WatsonTokenAuthEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonTokenAuthEntryPersistence
 * @see com.liferay.watson.login.service.persistence.impl.WatsonTokenAuthEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class WatsonTokenAuthEntryUtil {
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
	public static void clearCache(WatsonTokenAuthEntry watsonTokenAuthEntry) {
		getPersistence().clearCache(watsonTokenAuthEntry);
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
	public static List<WatsonTokenAuthEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonTokenAuthEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonTokenAuthEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonTokenAuthEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonTokenAuthEntry update(
		WatsonTokenAuthEntry watsonTokenAuthEntry) {
		return getPersistence().update(watsonTokenAuthEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonTokenAuthEntry update(
		WatsonTokenAuthEntry watsonTokenAuthEntry, ServiceContext serviceContext) {
		return getPersistence().update(watsonTokenAuthEntry, serviceContext);
	}

	/**
	* Returns the watson token auth entry where userId = &#63; or throws a {@link NoSuchEntryException} if it could not be found.
	*
	* @param userId the user ID
	* @return the matching watson token auth entry
	* @throws NoSuchEntryException if a matching watson token auth entry could not be found
	*/
	public static WatsonTokenAuthEntry findByUserId(long userId)
		throws com.liferay.watson.login.exception.NoSuchEntryException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns the watson token auth entry where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @return the matching watson token auth entry, or <code>null</code> if a matching watson token auth entry could not be found
	*/
	public static WatsonTokenAuthEntry fetchByUserId(long userId) {
		return getPersistence().fetchByUserId(userId);
	}

	/**
	* Returns the watson token auth entry where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching watson token auth entry, or <code>null</code> if a matching watson token auth entry could not be found
	*/
	public static WatsonTokenAuthEntry fetchByUserId(long userId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUserId(userId, retrieveFromCache);
	}

	/**
	* Removes the watson token auth entry where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @return the watson token auth entry that was removed
	*/
	public static WatsonTokenAuthEntry removeByUserId(long userId)
		throws com.liferay.watson.login.exception.NoSuchEntryException {
		return getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of watson token auth entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching watson token auth entries
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the watson token auth entries where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the matching watson token auth entries
	*/
	public static List<WatsonTokenAuthEntry> findByC_U(long companyId,
		long userId) {
		return getPersistence().findByC_U(companyId, userId);
	}

	/**
	* Returns a range of all the watson token auth entries where companyId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonTokenAuthEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param start the lower bound of the range of watson token auth entries
	* @param end the upper bound of the range of watson token auth entries (not inclusive)
	* @return the range of matching watson token auth entries
	*/
	public static List<WatsonTokenAuthEntry> findByC_U(long companyId,
		long userId, int start, int end) {
		return getPersistence().findByC_U(companyId, userId, start, end);
	}

	/**
	* Returns an ordered range of all the watson token auth entries where companyId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonTokenAuthEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param start the lower bound of the range of watson token auth entries
	* @param end the upper bound of the range of watson token auth entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching watson token auth entries
	*/
	public static List<WatsonTokenAuthEntry> findByC_U(long companyId,
		long userId, int start, int end,
		OrderByComparator<WatsonTokenAuthEntry> orderByComparator) {
		return getPersistence()
				   .findByC_U(companyId, userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson token auth entries where companyId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonTokenAuthEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param start the lower bound of the range of watson token auth entries
	* @param end the upper bound of the range of watson token auth entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching watson token auth entries
	*/
	public static List<WatsonTokenAuthEntry> findByC_U(long companyId,
		long userId, int start, int end,
		OrderByComparator<WatsonTokenAuthEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_U(companyId, userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first watson token auth entry in the ordered set where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching watson token auth entry
	* @throws NoSuchEntryException if a matching watson token auth entry could not be found
	*/
	public static WatsonTokenAuthEntry findByC_U_First(long companyId,
		long userId, OrderByComparator<WatsonTokenAuthEntry> orderByComparator)
		throws com.liferay.watson.login.exception.NoSuchEntryException {
		return getPersistence()
				   .findByC_U_First(companyId, userId, orderByComparator);
	}

	/**
	* Returns the first watson token auth entry in the ordered set where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching watson token auth entry, or <code>null</code> if a matching watson token auth entry could not be found
	*/
	public static WatsonTokenAuthEntry fetchByC_U_First(long companyId,
		long userId, OrderByComparator<WatsonTokenAuthEntry> orderByComparator) {
		return getPersistence()
				   .fetchByC_U_First(companyId, userId, orderByComparator);
	}

	/**
	* Returns the last watson token auth entry in the ordered set where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching watson token auth entry
	* @throws NoSuchEntryException if a matching watson token auth entry could not be found
	*/
	public static WatsonTokenAuthEntry findByC_U_Last(long companyId,
		long userId, OrderByComparator<WatsonTokenAuthEntry> orderByComparator)
		throws com.liferay.watson.login.exception.NoSuchEntryException {
		return getPersistence()
				   .findByC_U_Last(companyId, userId, orderByComparator);
	}

	/**
	* Returns the last watson token auth entry in the ordered set where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching watson token auth entry, or <code>null</code> if a matching watson token auth entry could not be found
	*/
	public static WatsonTokenAuthEntry fetchByC_U_Last(long companyId,
		long userId, OrderByComparator<WatsonTokenAuthEntry> orderByComparator) {
		return getPersistence()
				   .fetchByC_U_Last(companyId, userId, orderByComparator);
	}

	/**
	* Returns the watson token auth entries before and after the current watson token auth entry in the ordered set where companyId = &#63; and userId = &#63;.
	*
	* @param watsonTokenAuthEntryId the primary key of the current watson token auth entry
	* @param companyId the company ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next watson token auth entry
	* @throws NoSuchEntryException if a watson token auth entry with the primary key could not be found
	*/
	public static WatsonTokenAuthEntry[] findByC_U_PrevAndNext(
		long watsonTokenAuthEntryId, long companyId, long userId,
		OrderByComparator<WatsonTokenAuthEntry> orderByComparator)
		throws com.liferay.watson.login.exception.NoSuchEntryException {
		return getPersistence()
				   .findByC_U_PrevAndNext(watsonTokenAuthEntryId, companyId,
			userId, orderByComparator);
	}

	/**
	* Removes all the watson token auth entries where companyId = &#63; and userId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param userId the user ID
	*/
	public static void removeByC_U(long companyId, long userId) {
		getPersistence().removeByC_U(companyId, userId);
	}

	/**
	* Returns the number of watson token auth entries where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the number of matching watson token auth entries
	*/
	public static int countByC_U(long companyId, long userId) {
		return getPersistence().countByC_U(companyId, userId);
	}

	/**
	* Caches the watson token auth entry in the entity cache if it is enabled.
	*
	* @param watsonTokenAuthEntry the watson token auth entry
	*/
	public static void cacheResult(WatsonTokenAuthEntry watsonTokenAuthEntry) {
		getPersistence().cacheResult(watsonTokenAuthEntry);
	}

	/**
	* Caches the watson token auth entries in the entity cache if it is enabled.
	*
	* @param watsonTokenAuthEntries the watson token auth entries
	*/
	public static void cacheResult(
		List<WatsonTokenAuthEntry> watsonTokenAuthEntries) {
		getPersistence().cacheResult(watsonTokenAuthEntries);
	}

	/**
	* Creates a new watson token auth entry with the primary key. Does not add the watson token auth entry to the database.
	*
	* @param watsonTokenAuthEntryId the primary key for the new watson token auth entry
	* @return the new watson token auth entry
	*/
	public static WatsonTokenAuthEntry create(long watsonTokenAuthEntryId) {
		return getPersistence().create(watsonTokenAuthEntryId);
	}

	/**
	* Removes the watson token auth entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonTokenAuthEntryId the primary key of the watson token auth entry
	* @return the watson token auth entry that was removed
	* @throws NoSuchEntryException if a watson token auth entry with the primary key could not be found
	*/
	public static WatsonTokenAuthEntry remove(long watsonTokenAuthEntryId)
		throws com.liferay.watson.login.exception.NoSuchEntryException {
		return getPersistence().remove(watsonTokenAuthEntryId);
	}

	public static WatsonTokenAuthEntry updateImpl(
		WatsonTokenAuthEntry watsonTokenAuthEntry) {
		return getPersistence().updateImpl(watsonTokenAuthEntry);
	}

	/**
	* Returns the watson token auth entry with the primary key or throws a {@link NoSuchEntryException} if it could not be found.
	*
	* @param watsonTokenAuthEntryId the primary key of the watson token auth entry
	* @return the watson token auth entry
	* @throws NoSuchEntryException if a watson token auth entry with the primary key could not be found
	*/
	public static WatsonTokenAuthEntry findByPrimaryKey(
		long watsonTokenAuthEntryId)
		throws com.liferay.watson.login.exception.NoSuchEntryException {
		return getPersistence().findByPrimaryKey(watsonTokenAuthEntryId);
	}

	/**
	* Returns the watson token auth entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonTokenAuthEntryId the primary key of the watson token auth entry
	* @return the watson token auth entry, or <code>null</code> if a watson token auth entry with the primary key could not be found
	*/
	public static WatsonTokenAuthEntry fetchByPrimaryKey(
		long watsonTokenAuthEntryId) {
		return getPersistence().fetchByPrimaryKey(watsonTokenAuthEntryId);
	}

	public static java.util.Map<java.io.Serializable, WatsonTokenAuthEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the watson token auth entries.
	*
	* @return the watson token auth entries
	*/
	public static List<WatsonTokenAuthEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson token auth entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonTokenAuthEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson token auth entries
	* @param end the upper bound of the range of watson token auth entries (not inclusive)
	* @return the range of watson token auth entries
	*/
	public static List<WatsonTokenAuthEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson token auth entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonTokenAuthEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson token auth entries
	* @param end the upper bound of the range of watson token auth entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson token auth entries
	*/
	public static List<WatsonTokenAuthEntry> findAll(int start, int end,
		OrderByComparator<WatsonTokenAuthEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson token auth entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonTokenAuthEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson token auth entries
	* @param end the upper bound of the range of watson token auth entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson token auth entries
	*/
	public static List<WatsonTokenAuthEntry> findAll(int start, int end,
		OrderByComparator<WatsonTokenAuthEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson token auth entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson token auth entries.
	*
	* @return the number of watson token auth entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WatsonTokenAuthEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonTokenAuthEntryPersistence, WatsonTokenAuthEntryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonTokenAuthEntryPersistence.class);

		ServiceTracker<WatsonTokenAuthEntryPersistence, WatsonTokenAuthEntryPersistence> serviceTracker =
			new ServiceTracker<WatsonTokenAuthEntryPersistence, WatsonTokenAuthEntryPersistence>(bundle.getBundleContext(),
				WatsonTokenAuthEntryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}