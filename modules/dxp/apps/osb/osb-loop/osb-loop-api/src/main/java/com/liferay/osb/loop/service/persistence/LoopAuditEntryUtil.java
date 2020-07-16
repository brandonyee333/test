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

import com.liferay.osb.loop.model.LoopAuditEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the loop audit entry service. This utility wraps <code>com.liferay.osb.loop.service.persistence.impl.LoopAuditEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopAuditEntryPersistence
 * @generated
 */
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
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, LoopAuditEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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
	public static LoopAuditEntry update(
		LoopAuditEntry loopAuditEntry, ServiceContext serviceContext) {

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
	 * Returns the loop audit entry with the primary key or throws a <code>NoSuchLoopAuditEntryException</code> if it could not be found.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopAuditEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopAuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop audit entries
	 * @param end the upper bound of the range of loop audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop audit entries
	 */
	public static List<LoopAuditEntry> findAll(
		int start, int end,
		OrderByComparator<LoopAuditEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the loop audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopAuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop audit entries
	 * @param end the upper bound of the range of loop audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop audit entries
	 */
	public static List<LoopAuditEntry> findAll(
		int start, int end, OrderByComparator<LoopAuditEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
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

	private static ServiceTracker
		<LoopAuditEntryPersistence, LoopAuditEntryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			LoopAuditEntryPersistence.class);

		ServiceTracker<LoopAuditEntryPersistence, LoopAuditEntryPersistence>
			serviceTracker =
				new ServiceTracker
					<LoopAuditEntryPersistence, LoopAuditEntryPersistence>(
						bundle.getBundleContext(),
						LoopAuditEntryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}