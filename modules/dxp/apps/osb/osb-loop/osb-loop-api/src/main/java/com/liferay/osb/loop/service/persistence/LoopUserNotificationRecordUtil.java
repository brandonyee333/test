/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.loop.service.persistence;

import com.liferay.osb.loop.model.LoopUserNotificationRecord;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the loop user notification record service. This utility wraps <code>com.liferay.osb.loop.service.persistence.impl.LoopUserNotificationRecordPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationRecordPersistence
 * @generated
 */
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
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, LoopUserNotificationRecord>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

		return getPersistence().update(
			loopUserNotificationRecord, serviceContext);
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
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationRecordException {

		return getPersistence().remove(loopUserNotificationRecordId);
	}

	public static LoopUserNotificationRecord updateImpl(
		LoopUserNotificationRecord loopUserNotificationRecord) {

		return getPersistence().updateImpl(loopUserNotificationRecord);
	}

	/**
	 * Returns the loop user notification record with the primary key or throws a <code>NoSuchLoopUserNotificationRecordException</code> if it could not be found.
	 *
	 * @param loopUserNotificationRecordId the primary key of the loop user notification record
	 * @return the loop user notification record
	 * @throws NoSuchLoopUserNotificationRecordException if a loop user notification record with the primary key could not be found
	 */
	public static LoopUserNotificationRecord findByPrimaryKey(
			long loopUserNotificationRecordId)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationRecordException {

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationRecordModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationRecordModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification records
	 * @param end the upper bound of the range of loop user notification records (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop user notification records
	 */
	public static List<LoopUserNotificationRecord> findAll(
		int start, int end,
		OrderByComparator<LoopUserNotificationRecord> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the loop user notification records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationRecordModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification records
	 * @param end the upper bound of the range of loop user notification records (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop user notification records
	 */
	public static List<LoopUserNotificationRecord> findAll(
		int start, int end,
		OrderByComparator<LoopUserNotificationRecord> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
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
		return _persistence;
	}

	public static void setPersistence(
		LoopUserNotificationRecordPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile LoopUserNotificationRecordPersistence _persistence;

}