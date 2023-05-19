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

import com.liferay.osb.loop.model.LoopStatsEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the loop stats entry service. This utility wraps <code>com.liferay.osb.loop.service.persistence.impl.LoopStatsEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopStatsEntryPersistence
 * @generated
 */
public class LoopStatsEntryUtil {

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
	public static void clearCache(LoopStatsEntry loopStatsEntry) {
		getPersistence().clearCache(loopStatsEntry);
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
	public static Map<Serializable, LoopStatsEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LoopStatsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoopStatsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoopStatsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoopStatsEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoopStatsEntry update(LoopStatsEntry loopStatsEntry) {
		return getPersistence().update(loopStatsEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoopStatsEntry update(
		LoopStatsEntry loopStatsEntry, ServiceContext serviceContext) {

		return getPersistence().update(loopStatsEntry, serviceContext);
	}

	/**
	 * Caches the loop stats entry in the entity cache if it is enabled.
	 *
	 * @param loopStatsEntry the loop stats entry
	 */
	public static void cacheResult(LoopStatsEntry loopStatsEntry) {
		getPersistence().cacheResult(loopStatsEntry);
	}

	/**
	 * Caches the loop stats entries in the entity cache if it is enabled.
	 *
	 * @param loopStatsEntries the loop stats entries
	 */
	public static void cacheResult(List<LoopStatsEntry> loopStatsEntries) {
		getPersistence().cacheResult(loopStatsEntries);
	}

	/**
	 * Creates a new loop stats entry with the primary key. Does not add the loop stats entry to the database.
	 *
	 * @param loopStatsEntryId the primary key for the new loop stats entry
	 * @return the new loop stats entry
	 */
	public static LoopStatsEntry create(long loopStatsEntryId) {
		return getPersistence().create(loopStatsEntryId);
	}

	/**
	 * Removes the loop stats entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopStatsEntryId the primary key of the loop stats entry
	 * @return the loop stats entry that was removed
	 * @throws NoSuchLoopStatsEntryException if a loop stats entry with the primary key could not be found
	 */
	public static LoopStatsEntry remove(long loopStatsEntryId)
		throws com.liferay.osb.loop.exception.NoSuchLoopStatsEntryException {

		return getPersistence().remove(loopStatsEntryId);
	}

	public static LoopStatsEntry updateImpl(LoopStatsEntry loopStatsEntry) {
		return getPersistence().updateImpl(loopStatsEntry);
	}

	/**
	 * Returns the loop stats entry with the primary key or throws a <code>NoSuchLoopStatsEntryException</code> if it could not be found.
	 *
	 * @param loopStatsEntryId the primary key of the loop stats entry
	 * @return the loop stats entry
	 * @throws NoSuchLoopStatsEntryException if a loop stats entry with the primary key could not be found
	 */
	public static LoopStatsEntry findByPrimaryKey(long loopStatsEntryId)
		throws com.liferay.osb.loop.exception.NoSuchLoopStatsEntryException {

		return getPersistence().findByPrimaryKey(loopStatsEntryId);
	}

	/**
	 * Returns the loop stats entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopStatsEntryId the primary key of the loop stats entry
	 * @return the loop stats entry, or <code>null</code> if a loop stats entry with the primary key could not be found
	 */
	public static LoopStatsEntry fetchByPrimaryKey(long loopStatsEntryId) {
		return getPersistence().fetchByPrimaryKey(loopStatsEntryId);
	}

	/**
	 * Returns all the loop stats entries.
	 *
	 * @return the loop stats entries
	 */
	public static List<LoopStatsEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the loop stats entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopStatsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop stats entries
	 * @param end the upper bound of the range of loop stats entries (not inclusive)
	 * @return the range of loop stats entries
	 */
	public static List<LoopStatsEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the loop stats entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopStatsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop stats entries
	 * @param end the upper bound of the range of loop stats entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop stats entries
	 */
	public static List<LoopStatsEntry> findAll(
		int start, int end,
		OrderByComparator<LoopStatsEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the loop stats entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopStatsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop stats entries
	 * @param end the upper bound of the range of loop stats entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop stats entries
	 */
	public static List<LoopStatsEntry> findAll(
		int start, int end, OrderByComparator<LoopStatsEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the loop stats entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of loop stats entries.
	 *
	 * @return the number of loop stats entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LoopStatsEntryPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(LoopStatsEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile LoopStatsEntryPersistence _persistence;

}