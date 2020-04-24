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

import com.liferay.osb.loop.model.LoopUserNotificationRecord;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the loop user notification record service. This utility wraps {@link com.liferay.osb.loop.service.persistence.impl.LoopUserNotificationRecordPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationRecordPersistence
 * @see com.liferay.osb.loop.service.persistence.impl.LoopUserNotificationRecordPersistenceImpl
 * @generated
 */
@ProviderType
public class LoopUserNotificationRecordUtil {
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
	public static void clearCache(
		LoopUserNotificationRecord loopUserNotificationRecord) {
		getPersistence().clearCache(loopUserNotificationRecord);
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
	public static List<LoopUserNotificationRecord> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoopUserNotificationRecord> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoopUserNotificationRecord> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoopUserNotificationRecord> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoopUserNotificationRecord update(
		LoopUserNotificationRecord loopUserNotificationRecord) {
		return getPersistence().update(loopUserNotificationRecord);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoopUserNotificationRecord update(
		LoopUserNotificationRecord loopUserNotificationRecord,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(loopUserNotificationRecord, serviceContext);
	}

	/**
	* Caches the loop user notification record in the entity cache if it is enabled.
	*
	* @param loopUserNotificationRecord the loop user notification record
	*/
	public static void cacheResult(
		LoopUserNotificationRecord loopUserNotificationRecord) {
		getPersistence().cacheResult(loopUserNotificationRecord);
	}

	/**
	* Caches the loop user notification records in the entity cache if it is enabled.
	*
	* @param loopUserNotificationRecords the loop user notification records
	*/
	public static void cacheResult(
		List<LoopUserNotificationRecord> loopUserNotificationRecords) {
		getPersistence().cacheResult(loopUserNotificationRecords);
	}

	/**
	* Creates a new loop user notification record with the primary key. Does not add the loop user notification record to the database.
	*
	* @param loopUserNotificationRecordId the primary key for the new loop user notification record
	* @return the new loop user notification record
	*/
	public static LoopUserNotificationRecord create(
		long loopUserNotificationRecordId) {
		return getPersistence().create(loopUserNotificationRecordId);
	}

	/**
	* Removes the loop user notification record with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopUserNotificationRecordId the primary key of the loop user notification record
	* @return the loop user notification record that was removed
	* @throws NoSuchLoopUserNotificationRecordException if a loop user notification record with the primary key could not be found
	*/
	public static LoopUserNotificationRecord remove(
		long loopUserNotificationRecordId)
		throws com.liferay.osb.loop.exception.NoSuchLoopUserNotificationRecordException {
		return getPersistence().remove(loopUserNotificationRecordId);
	}

	public static LoopUserNotificationRecord updateImpl(
		LoopUserNotificationRecord loopUserNotificationRecord) {
		return getPersistence().updateImpl(loopUserNotificationRecord);
	}

	/**
	* Returns the loop user notification record with the primary key or throws a {@link NoSuchLoopUserNotificationRecordException} if it could not be found.
	*
	* @param loopUserNotificationRecordId the primary key of the loop user notification record
	* @return the loop user notification record
	* @throws NoSuchLoopUserNotificationRecordException if a loop user notification record with the primary key could not be found
	*/
	public static LoopUserNotificationRecord findByPrimaryKey(
		long loopUserNotificationRecordId)
		throws com.liferay.osb.loop.exception.NoSuchLoopUserNotificationRecordException {
		return getPersistence().findByPrimaryKey(loopUserNotificationRecordId);
	}

	/**
	* Returns the loop user notification record with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param loopUserNotificationRecordId the primary key of the loop user notification record
	* @return the loop user notification record, or <code>null</code> if a loop user notification record with the primary key could not be found
	*/
	public static LoopUserNotificationRecord fetchByPrimaryKey(
		long loopUserNotificationRecordId) {
		return getPersistence().fetchByPrimaryKey(loopUserNotificationRecordId);
	}

	public static java.util.Map<java.io.Serializable, LoopUserNotificationRecord> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the loop user notification records.
	*
	* @return the loop user notification records
	*/
	public static List<LoopUserNotificationRecord> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the loop user notification records.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop user notification records
	* @param end the upper bound of the range of loop user notification records (not inclusive)
	* @return the range of loop user notification records
	*/
	public static List<LoopUserNotificationRecord> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the loop user notification records.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop user notification records
	* @param end the upper bound of the range of loop user notification records (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop user notification records
	*/
	public static List<LoopUserNotificationRecord> findAll(int start, int end,
		OrderByComparator<LoopUserNotificationRecord> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the loop user notification records.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop user notification records
	* @param end the upper bound of the range of loop user notification records (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop user notification records
	*/
	public static List<LoopUserNotificationRecord> findAll(int start, int end,
		OrderByComparator<LoopUserNotificationRecord> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the loop user notification records from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of loop user notification records.
	*
	* @return the number of loop user notification records
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LoopUserNotificationRecordPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LoopUserNotificationRecordPersistence, LoopUserNotificationRecordPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopUserNotificationRecordPersistence.class);

		ServiceTracker<LoopUserNotificationRecordPersistence, LoopUserNotificationRecordPersistence> serviceTracker =
			new ServiceTracker<LoopUserNotificationRecordPersistence, LoopUserNotificationRecordPersistence>(bundle.getBundleContext(),
				LoopUserNotificationRecordPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}