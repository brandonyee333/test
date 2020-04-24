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

import com.liferay.osb.loop.model.LoopStreamEntry;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the loop stream entry service. This utility wraps {@link com.liferay.osb.loop.service.persistence.impl.LoopStreamEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopStreamEntryPersistence
 * @see com.liferay.osb.loop.service.persistence.impl.LoopStreamEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class LoopStreamEntryUtil {
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
	public static void clearCache(LoopStreamEntry loopStreamEntry) {
		getPersistence().clearCache(loopStreamEntry);
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
	public static List<LoopStreamEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoopStreamEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoopStreamEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoopStreamEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoopStreamEntry update(LoopStreamEntry loopStreamEntry) {
		return getPersistence().update(loopStreamEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoopStreamEntry update(LoopStreamEntry loopStreamEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(loopStreamEntry, serviceContext);
	}

	/**
	* Returns the loop stream entry where loopPersonId = &#63; and loopStreamId = &#63; and following = &#63; or throws a {@link NoSuchLoopStreamEntryException} if it could not be found.
	*
	* @param loopPersonId the loop person ID
	* @param loopStreamId the loop stream ID
	* @param following the following
	* @return the matching loop stream entry
	* @throws NoSuchLoopStreamEntryException if a matching loop stream entry could not be found
	*/
	public static LoopStreamEntry findByCPI_CSI_F(long loopPersonId,
		long loopStreamId, boolean following)
		throws com.liferay.osb.loop.exception.NoSuchLoopStreamEntryException {
		return getPersistence()
				   .findByCPI_CSI_F(loopPersonId, loopStreamId, following);
	}

	/**
	* Returns the loop stream entry where loopPersonId = &#63; and loopStreamId = &#63; and following = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param loopPersonId the loop person ID
	* @param loopStreamId the loop stream ID
	* @param following the following
	* @return the matching loop stream entry, or <code>null</code> if a matching loop stream entry could not be found
	*/
	public static LoopStreamEntry fetchByCPI_CSI_F(long loopPersonId,
		long loopStreamId, boolean following) {
		return getPersistence()
				   .fetchByCPI_CSI_F(loopPersonId, loopStreamId, following);
	}

	/**
	* Returns the loop stream entry where loopPersonId = &#63; and loopStreamId = &#63; and following = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param loopPersonId the loop person ID
	* @param loopStreamId the loop stream ID
	* @param following the following
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching loop stream entry, or <code>null</code> if a matching loop stream entry could not be found
	*/
	public static LoopStreamEntry fetchByCPI_CSI_F(long loopPersonId,
		long loopStreamId, boolean following, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCPI_CSI_F(loopPersonId, loopStreamId, following,
			retrieveFromCache);
	}

	/**
	* Removes the loop stream entry where loopPersonId = &#63; and loopStreamId = &#63; and following = &#63; from the database.
	*
	* @param loopPersonId the loop person ID
	* @param loopStreamId the loop stream ID
	* @param following the following
	* @return the loop stream entry that was removed
	*/
	public static LoopStreamEntry removeByCPI_CSI_F(long loopPersonId,
		long loopStreamId, boolean following)
		throws com.liferay.osb.loop.exception.NoSuchLoopStreamEntryException {
		return getPersistence()
				   .removeByCPI_CSI_F(loopPersonId, loopStreamId, following);
	}

	/**
	* Returns the number of loop stream entries where loopPersonId = &#63; and loopStreamId = &#63; and following = &#63;.
	*
	* @param loopPersonId the loop person ID
	* @param loopStreamId the loop stream ID
	* @param following the following
	* @return the number of matching loop stream entries
	*/
	public static int countByCPI_CSI_F(long loopPersonId, long loopStreamId,
		boolean following) {
		return getPersistence()
				   .countByCPI_CSI_F(loopPersonId, loopStreamId, following);
	}

	/**
	* Returns the loop stream entry where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchLoopStreamEntryException} if it could not be found.
	*
	* @param loopPersonId the loop person ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching loop stream entry
	* @throws NoSuchLoopStreamEntryException if a matching loop stream entry could not be found
	*/
	public static LoopStreamEntry findByCPI_CNI_CP(long loopPersonId,
		long classNameId, long classPK)
		throws com.liferay.osb.loop.exception.NoSuchLoopStreamEntryException {
		return getPersistence()
				   .findByCPI_CNI_CP(loopPersonId, classNameId, classPK);
	}

	/**
	* Returns the loop stream entry where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param loopPersonId the loop person ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching loop stream entry, or <code>null</code> if a matching loop stream entry could not be found
	*/
	public static LoopStreamEntry fetchByCPI_CNI_CP(long loopPersonId,
		long classNameId, long classPK) {
		return getPersistence()
				   .fetchByCPI_CNI_CP(loopPersonId, classNameId, classPK);
	}

	/**
	* Returns the loop stream entry where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param loopPersonId the loop person ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching loop stream entry, or <code>null</code> if a matching loop stream entry could not be found
	*/
	public static LoopStreamEntry fetchByCPI_CNI_CP(long loopPersonId,
		long classNameId, long classPK, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCPI_CNI_CP(loopPersonId, classNameId, classPK,
			retrieveFromCache);
	}

	/**
	* Removes the loop stream entry where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param loopPersonId the loop person ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the loop stream entry that was removed
	*/
	public static LoopStreamEntry removeByCPI_CNI_CP(long loopPersonId,
		long classNameId, long classPK)
		throws com.liferay.osb.loop.exception.NoSuchLoopStreamEntryException {
		return getPersistence()
				   .removeByCPI_CNI_CP(loopPersonId, classNameId, classPK);
	}

	/**
	* Returns the number of loop stream entries where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param loopPersonId the loop person ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching loop stream entries
	*/
	public static int countByCPI_CNI_CP(long loopPersonId, long classNameId,
		long classPK) {
		return getPersistence()
				   .countByCPI_CNI_CP(loopPersonId, classNameId, classPK);
	}

	/**
	* Returns the loop stream entry where loopPersonId = &#63; and loopStreamId = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchLoopStreamEntryException} if it could not be found.
	*
	* @param loopPersonId the loop person ID
	* @param loopStreamId the loop stream ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching loop stream entry
	* @throws NoSuchLoopStreamEntryException if a matching loop stream entry could not be found
	*/
	public static LoopStreamEntry findByCPI_CSI_CNI_CP(long loopPersonId,
		long loopStreamId, long classNameId, long classPK)
		throws com.liferay.osb.loop.exception.NoSuchLoopStreamEntryException {
		return getPersistence()
				   .findByCPI_CSI_CNI_CP(loopPersonId, loopStreamId,
			classNameId, classPK);
	}

	/**
	* Returns the loop stream entry where loopPersonId = &#63; and loopStreamId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param loopPersonId the loop person ID
	* @param loopStreamId the loop stream ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching loop stream entry, or <code>null</code> if a matching loop stream entry could not be found
	*/
	public static LoopStreamEntry fetchByCPI_CSI_CNI_CP(long loopPersonId,
		long loopStreamId, long classNameId, long classPK) {
		return getPersistence()
				   .fetchByCPI_CSI_CNI_CP(loopPersonId, loopStreamId,
			classNameId, classPK);
	}

	/**
	* Returns the loop stream entry where loopPersonId = &#63; and loopStreamId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param loopPersonId the loop person ID
	* @param loopStreamId the loop stream ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching loop stream entry, or <code>null</code> if a matching loop stream entry could not be found
	*/
	public static LoopStreamEntry fetchByCPI_CSI_CNI_CP(long loopPersonId,
		long loopStreamId, long classNameId, long classPK,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCPI_CSI_CNI_CP(loopPersonId, loopStreamId,
			classNameId, classPK, retrieveFromCache);
	}

	/**
	* Removes the loop stream entry where loopPersonId = &#63; and loopStreamId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param loopPersonId the loop person ID
	* @param loopStreamId the loop stream ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the loop stream entry that was removed
	*/
	public static LoopStreamEntry removeByCPI_CSI_CNI_CP(long loopPersonId,
		long loopStreamId, long classNameId, long classPK)
		throws com.liferay.osb.loop.exception.NoSuchLoopStreamEntryException {
		return getPersistence()
				   .removeByCPI_CSI_CNI_CP(loopPersonId, loopStreamId,
			classNameId, classPK);
	}

	/**
	* Returns the number of loop stream entries where loopPersonId = &#63; and loopStreamId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param loopPersonId the loop person ID
	* @param loopStreamId the loop stream ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching loop stream entries
	*/
	public static int countByCPI_CSI_CNI_CP(long loopPersonId,
		long loopStreamId, long classNameId, long classPK) {
		return getPersistence()
				   .countByCPI_CSI_CNI_CP(loopPersonId, loopStreamId,
			classNameId, classPK);
	}

	/**
	* Caches the loop stream entry in the entity cache if it is enabled.
	*
	* @param loopStreamEntry the loop stream entry
	*/
	public static void cacheResult(LoopStreamEntry loopStreamEntry) {
		getPersistence().cacheResult(loopStreamEntry);
	}

	/**
	* Caches the loop stream entries in the entity cache if it is enabled.
	*
	* @param loopStreamEntries the loop stream entries
	*/
	public static void cacheResult(List<LoopStreamEntry> loopStreamEntries) {
		getPersistence().cacheResult(loopStreamEntries);
	}

	/**
	* Creates a new loop stream entry with the primary key. Does not add the loop stream entry to the database.
	*
	* @param loopStreamEntryId the primary key for the new loop stream entry
	* @return the new loop stream entry
	*/
	public static LoopStreamEntry create(long loopStreamEntryId) {
		return getPersistence().create(loopStreamEntryId);
	}

	/**
	* Removes the loop stream entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopStreamEntryId the primary key of the loop stream entry
	* @return the loop stream entry that was removed
	* @throws NoSuchLoopStreamEntryException if a loop stream entry with the primary key could not be found
	*/
	public static LoopStreamEntry remove(long loopStreamEntryId)
		throws com.liferay.osb.loop.exception.NoSuchLoopStreamEntryException {
		return getPersistence().remove(loopStreamEntryId);
	}

	public static LoopStreamEntry updateImpl(LoopStreamEntry loopStreamEntry) {
		return getPersistence().updateImpl(loopStreamEntry);
	}

	/**
	* Returns the loop stream entry with the primary key or throws a {@link NoSuchLoopStreamEntryException} if it could not be found.
	*
	* @param loopStreamEntryId the primary key of the loop stream entry
	* @return the loop stream entry
	* @throws NoSuchLoopStreamEntryException if a loop stream entry with the primary key could not be found
	*/
	public static LoopStreamEntry findByPrimaryKey(long loopStreamEntryId)
		throws com.liferay.osb.loop.exception.NoSuchLoopStreamEntryException {
		return getPersistence().findByPrimaryKey(loopStreamEntryId);
	}

	/**
	* Returns the loop stream entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param loopStreamEntryId the primary key of the loop stream entry
	* @return the loop stream entry, or <code>null</code> if a loop stream entry with the primary key could not be found
	*/
	public static LoopStreamEntry fetchByPrimaryKey(long loopStreamEntryId) {
		return getPersistence().fetchByPrimaryKey(loopStreamEntryId);
	}

	public static java.util.Map<java.io.Serializable, LoopStreamEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the loop stream entries.
	*
	* @return the loop stream entries
	*/
	public static List<LoopStreamEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the loop stream entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStreamEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop stream entries
	* @param end the upper bound of the range of loop stream entries (not inclusive)
	* @return the range of loop stream entries
	*/
	public static List<LoopStreamEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the loop stream entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStreamEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop stream entries
	* @param end the upper bound of the range of loop stream entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop stream entries
	*/
	public static List<LoopStreamEntry> findAll(int start, int end,
		OrderByComparator<LoopStreamEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the loop stream entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStreamEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop stream entries
	* @param end the upper bound of the range of loop stream entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop stream entries
	*/
	public static List<LoopStreamEntry> findAll(int start, int end,
		OrderByComparator<LoopStreamEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the loop stream entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of loop stream entries.
	*
	* @return the number of loop stream entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LoopStreamEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LoopStreamEntryPersistence, LoopStreamEntryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopStreamEntryPersistence.class);

		ServiceTracker<LoopStreamEntryPersistence, LoopStreamEntryPersistence> serviceTracker =
			new ServiceTracker<LoopStreamEntryPersistence, LoopStreamEntryPersistence>(bundle.getBundleContext(),
				LoopStreamEntryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}