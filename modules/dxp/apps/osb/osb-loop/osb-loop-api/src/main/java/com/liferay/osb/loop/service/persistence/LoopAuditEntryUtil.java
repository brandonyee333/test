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

package com.liferay.osb.loop.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.model.LoopAuditEntry;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the loop audit entry service. This utility wraps {@link com.liferay.osb.loop.service.persistence.impl.LoopAuditEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopAuditEntryPersistence
 * @see com.liferay.osb.loop.service.persistence.impl.LoopAuditEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class LoopAuditEntryUtil {
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
	public static void clearCache(LoopAuditEntry loopAuditEntry) {
		getPersistence().clearCache(loopAuditEntry);
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
	public static List<LoopAuditEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoopAuditEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoopAuditEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoopAuditEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoopAuditEntry update(LoopAuditEntry loopAuditEntry) {
		return getPersistence().update(loopAuditEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoopAuditEntry update(LoopAuditEntry loopAuditEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(loopAuditEntry, serviceContext);
	}

	/**
	* Caches the loop audit entry in the entity cache if it is enabled.
	*
	* @param loopAuditEntry the loop audit entry
	*/
	public static void cacheResult(LoopAuditEntry loopAuditEntry) {
		getPersistence().cacheResult(loopAuditEntry);
	}

	/**
	* Caches the loop audit entries in the entity cache if it is enabled.
	*
	* @param loopAuditEntries the loop audit entries
	*/
	public static void cacheResult(List<LoopAuditEntry> loopAuditEntries) {
		getPersistence().cacheResult(loopAuditEntries);
	}

	/**
	* Creates a new loop audit entry with the primary key. Does not add the loop audit entry to the database.
	*
	* @param loopAuditEntryId the primary key for the new loop audit entry
	* @return the new loop audit entry
	*/
	public static LoopAuditEntry create(long loopAuditEntryId) {
		return getPersistence().create(loopAuditEntryId);
	}

	/**
	* Removes the loop audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopAuditEntryId the primary key of the loop audit entry
	* @return the loop audit entry that was removed
	* @throws NoSuchLoopAuditEntryException if a loop audit entry with the primary key could not be found
	*/
	public static LoopAuditEntry remove(long loopAuditEntryId)
		throws com.liferay.osb.loop.exception.NoSuchLoopAuditEntryException {
		return getPersistence().remove(loopAuditEntryId);
	}

	public static LoopAuditEntry updateImpl(LoopAuditEntry loopAuditEntry) {
		return getPersistence().updateImpl(loopAuditEntry);
	}

	/**
	* Returns the loop audit entry with the primary key or throws a {@link NoSuchLoopAuditEntryException} if it could not be found.
	*
	* @param loopAuditEntryId the primary key of the loop audit entry
	* @return the loop audit entry
	* @throws NoSuchLoopAuditEntryException if a loop audit entry with the primary key could not be found
	*/
	public static LoopAuditEntry findByPrimaryKey(long loopAuditEntryId)
		throws com.liferay.osb.loop.exception.NoSuchLoopAuditEntryException {
		return getPersistence().findByPrimaryKey(loopAuditEntryId);
	}

	/**
	* Returns the loop audit entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param loopAuditEntryId the primary key of the loop audit entry
	* @return the loop audit entry, or <code>null</code> if a loop audit entry with the primary key could not be found
	*/
	public static LoopAuditEntry fetchByPrimaryKey(long loopAuditEntryId) {
		return getPersistence().fetchByPrimaryKey(loopAuditEntryId);
	}

	public static java.util.Map<java.io.Serializable, LoopAuditEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the loop audit entries.
	*
	* @return the loop audit entries
	*/
	public static List<LoopAuditEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the loop audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop audit entries
	* @param end the upper bound of the range of loop audit entries (not inclusive)
	* @return the range of loop audit entries
	*/
	public static List<LoopAuditEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the loop audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop audit entries
	* @param end the upper bound of the range of loop audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop audit entries
	*/
	public static List<LoopAuditEntry> findAll(int start, int end,
		OrderByComparator<LoopAuditEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the loop audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop audit entries
	* @param end the upper bound of the range of loop audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop audit entries
	*/
	public static List<LoopAuditEntry> findAll(int start, int end,
		OrderByComparator<LoopAuditEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the loop audit entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of loop audit entries.
	*
	* @return the number of loop audit entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LoopAuditEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LoopAuditEntryPersistence, LoopAuditEntryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopAuditEntryPersistence.class);

		ServiceTracker<LoopAuditEntryPersistence, LoopAuditEntryPersistence> serviceTracker =
			new ServiceTracker<LoopAuditEntryPersistence, LoopAuditEntryPersistence>(bundle.getBundleContext(),
				LoopAuditEntryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}