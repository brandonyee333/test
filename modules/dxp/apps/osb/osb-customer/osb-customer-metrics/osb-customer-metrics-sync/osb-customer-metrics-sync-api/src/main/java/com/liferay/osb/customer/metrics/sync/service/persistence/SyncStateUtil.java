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

package com.liferay.osb.customer.metrics.sync.service.persistence;

import com.liferay.osb.customer.metrics.sync.model.SyncState;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the sync state service. This utility wraps <code>com.liferay.osb.customer.metrics.sync.service.persistence.impl.SyncStatePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncStatePersistence
 * @generated
 */
public class SyncStateUtil {

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
	public static void clearCache(SyncState syncState) {
		getPersistence().clearCache(syncState);
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
	public static Map<Serializable, SyncState> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SyncState> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SyncState> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SyncState> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SyncState> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SyncState update(SyncState syncState) {
		return getPersistence().update(syncState);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SyncState update(
		SyncState syncState, ServiceContext serviceContext) {

		return getPersistence().update(syncState, serviceContext);
	}

	/**
	 * Returns the sync state where modelName = &#63; or throws a <code>NoSuchSyncStateException</code> if it could not be found.
	 *
	 * @param modelName the model name
	 * @return the matching sync state
	 * @throws NoSuchSyncStateException if a matching sync state could not be found
	 */
	public static SyncState findByModelName(String modelName)
		throws com.liferay.osb.customer.metrics.sync.exception.
			NoSuchSyncStateException {

		return getPersistence().findByModelName(modelName);
	}

	/**
	 * Returns the sync state where modelName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param modelName the model name
	 * @return the matching sync state, or <code>null</code> if a matching sync state could not be found
	 */
	public static SyncState fetchByModelName(String modelName) {
		return getPersistence().fetchByModelName(modelName);
	}

	/**
	 * Returns the sync state where modelName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param modelName the model name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching sync state, or <code>null</code> if a matching sync state could not be found
	 */
	public static SyncState fetchByModelName(
		String modelName, boolean useFinderCache) {

		return getPersistence().fetchByModelName(modelName, useFinderCache);
	}

	/**
	 * Removes the sync state where modelName = &#63; from the database.
	 *
	 * @param modelName the model name
	 * @return the sync state that was removed
	 */
	public static SyncState removeByModelName(String modelName)
		throws com.liferay.osb.customer.metrics.sync.exception.
			NoSuchSyncStateException {

		return getPersistence().removeByModelName(modelName);
	}

	/**
	 * Returns the number of sync states where modelName = &#63;.
	 *
	 * @param modelName the model name
	 * @return the number of matching sync states
	 */
	public static int countByModelName(String modelName) {
		return getPersistence().countByModelName(modelName);
	}

	/**
	 * Caches the sync state in the entity cache if it is enabled.
	 *
	 * @param syncState the sync state
	 */
	public static void cacheResult(SyncState syncState) {
		getPersistence().cacheResult(syncState);
	}

	/**
	 * Caches the sync states in the entity cache if it is enabled.
	 *
	 * @param syncStates the sync states
	 */
	public static void cacheResult(List<SyncState> syncStates) {
		getPersistence().cacheResult(syncStates);
	}

	/**
	 * Creates a new sync state with the primary key. Does not add the sync state to the database.
	 *
	 * @param syncStateId the primary key for the new sync state
	 * @return the new sync state
	 */
	public static SyncState create(long syncStateId) {
		return getPersistence().create(syncStateId);
	}

	/**
	 * Removes the sync state with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param syncStateId the primary key of the sync state
	 * @return the sync state that was removed
	 * @throws NoSuchSyncStateException if a sync state with the primary key could not be found
	 */
	public static SyncState remove(long syncStateId)
		throws com.liferay.osb.customer.metrics.sync.exception.
			NoSuchSyncStateException {

		return getPersistence().remove(syncStateId);
	}

	public static SyncState updateImpl(SyncState syncState) {
		return getPersistence().updateImpl(syncState);
	}

	/**
	 * Returns the sync state with the primary key or throws a <code>NoSuchSyncStateException</code> if it could not be found.
	 *
	 * @param syncStateId the primary key of the sync state
	 * @return the sync state
	 * @throws NoSuchSyncStateException if a sync state with the primary key could not be found
	 */
	public static SyncState findByPrimaryKey(long syncStateId)
		throws com.liferay.osb.customer.metrics.sync.exception.
			NoSuchSyncStateException {

		return getPersistence().findByPrimaryKey(syncStateId);
	}

	/**
	 * Returns the sync state with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param syncStateId the primary key of the sync state
	 * @return the sync state, or <code>null</code> if a sync state with the primary key could not be found
	 */
	public static SyncState fetchByPrimaryKey(long syncStateId) {
		return getPersistence().fetchByPrimaryKey(syncStateId);
	}

	/**
	 * Returns all the sync states.
	 *
	 * @return the sync states
	 */
	public static List<SyncState> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the sync states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SyncStateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync states
	 * @param end the upper bound of the range of sync states (not inclusive)
	 * @return the range of sync states
	 */
	public static List<SyncState> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the sync states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SyncStateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync states
	 * @param end the upper bound of the range of sync states (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sync states
	 */
	public static List<SyncState> findAll(
		int start, int end, OrderByComparator<SyncState> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the sync states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SyncStateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync states
	 * @param end the upper bound of the range of sync states (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sync states
	 */
	public static List<SyncState> findAll(
		int start, int end, OrderByComparator<SyncState> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the sync states from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of sync states.
	 *
	 * @return the number of sync states
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SyncStatePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(SyncStatePersistence persistence) {
		_persistence = persistence;
	}

	private static volatile SyncStatePersistence _persistence;

}