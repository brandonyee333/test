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

import com.liferay.osb.model.LCSSubscriptionEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the lcs subscription entry service. This utility wraps {@link com.liferay.osb.service.persistence.impl.LCSSubscriptionEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LCSSubscriptionEntryPersistence
 * @see com.liferay.osb.service.persistence.impl.LCSSubscriptionEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class LCSSubscriptionEntryUtil {
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
	public static void clearCache(LCSSubscriptionEntry lcsSubscriptionEntry) {
		getPersistence().clearCache(lcsSubscriptionEntry);
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
	public static List<LCSSubscriptionEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LCSSubscriptionEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LCSSubscriptionEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LCSSubscriptionEntry update(
		LCSSubscriptionEntry lcsSubscriptionEntry) {
		return getPersistence().update(lcsSubscriptionEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LCSSubscriptionEntry update(
		LCSSubscriptionEntry lcsSubscriptionEntry, ServiceContext serviceContext) {
		return getPersistence().update(lcsSubscriptionEntry, serviceContext);
	}

	/**
	* Caches the lcs subscription entry in the entity cache if it is enabled.
	*
	* @param lcsSubscriptionEntry the lcs subscription entry
	*/
	public static void cacheResult(LCSSubscriptionEntry lcsSubscriptionEntry) {
		getPersistence().cacheResult(lcsSubscriptionEntry);
	}

	/**
	* Caches the lcs subscription entries in the entity cache if it is enabled.
	*
	* @param lcsSubscriptionEntries the lcs subscription entries
	*/
	public static void cacheResult(
		List<LCSSubscriptionEntry> lcsSubscriptionEntries) {
		getPersistence().cacheResult(lcsSubscriptionEntries);
	}

	/**
	* Creates a new lcs subscription entry with the primary key. Does not add the lcs subscription entry to the database.
	*
	* @param lcsSubscriptionEntryId the primary key for the new lcs subscription entry
	* @return the new lcs subscription entry
	*/
	public static LCSSubscriptionEntry create(long lcsSubscriptionEntryId) {
		return getPersistence().create(lcsSubscriptionEntryId);
	}

	/**
	* Removes the lcs subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntryId the primary key of the lcs subscription entry
	* @return the lcs subscription entry that was removed
	* @throws NoSuchLCSSubscriptionEntryException if a lcs subscription entry with the primary key could not be found
	*/
	public static LCSSubscriptionEntry remove(long lcsSubscriptionEntryId)
		throws com.liferay.osb.exception.NoSuchLCSSubscriptionEntryException {
		return getPersistence().remove(lcsSubscriptionEntryId);
	}

	public static LCSSubscriptionEntry updateImpl(
		LCSSubscriptionEntry lcsSubscriptionEntry) {
		return getPersistence().updateImpl(lcsSubscriptionEntry);
	}

	/**
	* Returns the lcs subscription entry with the primary key or throws a {@link NoSuchLCSSubscriptionEntryException} if it could not be found.
	*
	* @param lcsSubscriptionEntryId the primary key of the lcs subscription entry
	* @return the lcs subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a lcs subscription entry with the primary key could not be found
	*/
	public static LCSSubscriptionEntry findByPrimaryKey(
		long lcsSubscriptionEntryId)
		throws com.liferay.osb.exception.NoSuchLCSSubscriptionEntryException {
		return getPersistence().findByPrimaryKey(lcsSubscriptionEntryId);
	}

	/**
	* Returns the lcs subscription entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsSubscriptionEntryId the primary key of the lcs subscription entry
	* @return the lcs subscription entry, or <code>null</code> if a lcs subscription entry with the primary key could not be found
	*/
	public static LCSSubscriptionEntry fetchByPrimaryKey(
		long lcsSubscriptionEntryId) {
		return getPersistence().fetchByPrimaryKey(lcsSubscriptionEntryId);
	}

	public static java.util.Map<java.io.Serializable, LCSSubscriptionEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the lcs subscription entries.
	*
	* @return the lcs subscription entries
	*/
	public static List<LCSSubscriptionEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the lcs subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lcs subscription entries
	* @param end the upper bound of the range of lcs subscription entries (not inclusive)
	* @return the range of lcs subscription entries
	*/
	public static List<LCSSubscriptionEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the lcs subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lcs subscription entries
	* @param end the upper bound of the range of lcs subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of lcs subscription entries
	*/
	public static List<LCSSubscriptionEntry> findAll(int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the lcs subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lcs subscription entries
	* @param end the upper bound of the range of lcs subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of lcs subscription entries
	*/
	public static List<LCSSubscriptionEntry> findAll(int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the lcs subscription entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of lcs subscription entries.
	*
	* @return the number of lcs subscription entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LCSSubscriptionEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (LCSSubscriptionEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					LCSSubscriptionEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(LCSSubscriptionEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static LCSSubscriptionEntryPersistence _persistence;
}