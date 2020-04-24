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

import com.liferay.osb.loop.model.LoopDivisionRel;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the loop division rel service. This utility wraps {@link com.liferay.osb.loop.service.persistence.impl.LoopDivisionRelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopDivisionRelPersistence
 * @see com.liferay.osb.loop.service.persistence.impl.LoopDivisionRelPersistenceImpl
 * @generated
 */
@ProviderType
public class LoopDivisionRelUtil {
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
	public static void clearCache(LoopDivisionRel loopDivisionRel) {
		getPersistence().clearCache(loopDivisionRel);
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
	public static List<LoopDivisionRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoopDivisionRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoopDivisionRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoopDivisionRel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoopDivisionRel update(LoopDivisionRel loopDivisionRel) {
		return getPersistence().update(loopDivisionRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoopDivisionRel update(LoopDivisionRel loopDivisionRel,
		ServiceContext serviceContext) {
		return getPersistence().update(loopDivisionRel, serviceContext);
	}

	/**
	* Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; or throws a {@link NoSuchLoopDivisionRelException} if it could not be found.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @return the matching loop division rel
	* @throws NoSuchLoopDivisionRelException if a matching loop division rel could not be found
	*/
	public static LoopDivisionRel findByCLDI_LPI(long childLoopDivisionId,
		long loopPersonId)
		throws com.liferay.osb.loop.exception.NoSuchLoopDivisionRelException {
		return getPersistence().findByCLDI_LPI(childLoopDivisionId, loopPersonId);
	}

	/**
	* Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	*/
	public static LoopDivisionRel fetchByCLDI_LPI(long childLoopDivisionId,
		long loopPersonId) {
		return getPersistence()
				   .fetchByCLDI_LPI(childLoopDivisionId, loopPersonId);
	}

	/**
	* Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	*/
	public static LoopDivisionRel fetchByCLDI_LPI(long childLoopDivisionId,
		long loopPersonId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCLDI_LPI(childLoopDivisionId, loopPersonId,
			retrieveFromCache);
	}

	/**
	* Removes the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; from the database.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @return the loop division rel that was removed
	*/
	public static LoopDivisionRel removeByCLDI_LPI(long childLoopDivisionId,
		long loopPersonId)
		throws com.liferay.osb.loop.exception.NoSuchLoopDivisionRelException {
		return getPersistence()
				   .removeByCLDI_LPI(childLoopDivisionId, loopPersonId);
	}

	/**
	* Returns the number of loop division rels where childLoopDivisionId = &#63; and loopPersonId = &#63;.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @return the number of matching loop division rels
	*/
	public static int countByCLDI_LPI(long childLoopDivisionId,
		long loopPersonId) {
		return getPersistence()
				   .countByCLDI_LPI(childLoopDivisionId, loopPersonId);
	}

	/**
	* Returns the loop division rel where loopPersonId = &#63; and parentLoopDivisionId = &#63; or throws a {@link NoSuchLoopDivisionRelException} if it could not be found.
	*
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @return the matching loop division rel
	* @throws NoSuchLoopDivisionRelException if a matching loop division rel could not be found
	*/
	public static LoopDivisionRel findByLPI_PLDI(long loopPersonId,
		long parentLoopDivisionId)
		throws com.liferay.osb.loop.exception.NoSuchLoopDivisionRelException {
		return getPersistence()
				   .findByLPI_PLDI(loopPersonId, parentLoopDivisionId);
	}

	/**
	* Returns the loop division rel where loopPersonId = &#63; and parentLoopDivisionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	*/
	public static LoopDivisionRel fetchByLPI_PLDI(long loopPersonId,
		long parentLoopDivisionId) {
		return getPersistence()
				   .fetchByLPI_PLDI(loopPersonId, parentLoopDivisionId);
	}

	/**
	* Returns the loop division rel where loopPersonId = &#63; and parentLoopDivisionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	*/
	public static LoopDivisionRel fetchByLPI_PLDI(long loopPersonId,
		long parentLoopDivisionId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByLPI_PLDI(loopPersonId, parentLoopDivisionId,
			retrieveFromCache);
	}

	/**
	* Removes the loop division rel where loopPersonId = &#63; and parentLoopDivisionId = &#63; from the database.
	*
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @return the loop division rel that was removed
	*/
	public static LoopDivisionRel removeByLPI_PLDI(long loopPersonId,
		long parentLoopDivisionId)
		throws com.liferay.osb.loop.exception.NoSuchLoopDivisionRelException {
		return getPersistence()
				   .removeByLPI_PLDI(loopPersonId, parentLoopDivisionId);
	}

	/**
	* Returns the number of loop division rels where loopPersonId = &#63; and parentLoopDivisionId = &#63;.
	*
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @return the number of matching loop division rels
	*/
	public static int countByLPI_PLDI(long loopPersonId,
		long parentLoopDivisionId) {
		return getPersistence()
				   .countByLPI_PLDI(loopPersonId, parentLoopDivisionId);
	}

	/**
	* Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63; or throws a {@link NoSuchLoopDivisionRelException} if it could not be found.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @return the matching loop division rel
	* @throws NoSuchLoopDivisionRelException if a matching loop division rel could not be found
	*/
	public static LoopDivisionRel findByCLDI_LPI_PLDI(
		long childLoopDivisionId, long loopPersonId, long parentLoopDivisionId)
		throws com.liferay.osb.loop.exception.NoSuchLoopDivisionRelException {
		return getPersistence()
				   .findByCLDI_LPI_PLDI(childLoopDivisionId, loopPersonId,
			parentLoopDivisionId);
	}

	/**
	* Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	*/
	public static LoopDivisionRel fetchByCLDI_LPI_PLDI(
		long childLoopDivisionId, long loopPersonId, long parentLoopDivisionId) {
		return getPersistence()
				   .fetchByCLDI_LPI_PLDI(childLoopDivisionId, loopPersonId,
			parentLoopDivisionId);
	}

	/**
	* Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	*/
	public static LoopDivisionRel fetchByCLDI_LPI_PLDI(
		long childLoopDivisionId, long loopPersonId, long parentLoopDivisionId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCLDI_LPI_PLDI(childLoopDivisionId, loopPersonId,
			parentLoopDivisionId, retrieveFromCache);
	}

	/**
	* Removes the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63; from the database.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @return the loop division rel that was removed
	*/
	public static LoopDivisionRel removeByCLDI_LPI_PLDI(
		long childLoopDivisionId, long loopPersonId, long parentLoopDivisionId)
		throws com.liferay.osb.loop.exception.NoSuchLoopDivisionRelException {
		return getPersistence()
				   .removeByCLDI_LPI_PLDI(childLoopDivisionId, loopPersonId,
			parentLoopDivisionId);
	}

	/**
	* Returns the number of loop division rels where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63;.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @return the number of matching loop division rels
	*/
	public static int countByCLDI_LPI_PLDI(long childLoopDivisionId,
		long loopPersonId, long parentLoopDivisionId) {
		return getPersistence()
				   .countByCLDI_LPI_PLDI(childLoopDivisionId, loopPersonId,
			parentLoopDivisionId);
	}

	/**
	* Caches the loop division rel in the entity cache if it is enabled.
	*
	* @param loopDivisionRel the loop division rel
	*/
	public static void cacheResult(LoopDivisionRel loopDivisionRel) {
		getPersistence().cacheResult(loopDivisionRel);
	}

	/**
	* Caches the loop division rels in the entity cache if it is enabled.
	*
	* @param loopDivisionRels the loop division rels
	*/
	public static void cacheResult(List<LoopDivisionRel> loopDivisionRels) {
		getPersistence().cacheResult(loopDivisionRels);
	}

	/**
	* Creates a new loop division rel with the primary key. Does not add the loop division rel to the database.
	*
	* @param loopDivisionRelId the primary key for the new loop division rel
	* @return the new loop division rel
	*/
	public static LoopDivisionRel create(long loopDivisionRelId) {
		return getPersistence().create(loopDivisionRelId);
	}

	/**
	* Removes the loop division rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopDivisionRelId the primary key of the loop division rel
	* @return the loop division rel that was removed
	* @throws NoSuchLoopDivisionRelException if a loop division rel with the primary key could not be found
	*/
	public static LoopDivisionRel remove(long loopDivisionRelId)
		throws com.liferay.osb.loop.exception.NoSuchLoopDivisionRelException {
		return getPersistence().remove(loopDivisionRelId);
	}

	public static LoopDivisionRel updateImpl(LoopDivisionRel loopDivisionRel) {
		return getPersistence().updateImpl(loopDivisionRel);
	}

	/**
	* Returns the loop division rel with the primary key or throws a {@link NoSuchLoopDivisionRelException} if it could not be found.
	*
	* @param loopDivisionRelId the primary key of the loop division rel
	* @return the loop division rel
	* @throws NoSuchLoopDivisionRelException if a loop division rel with the primary key could not be found
	*/
	public static LoopDivisionRel findByPrimaryKey(long loopDivisionRelId)
		throws com.liferay.osb.loop.exception.NoSuchLoopDivisionRelException {
		return getPersistence().findByPrimaryKey(loopDivisionRelId);
	}

	/**
	* Returns the loop division rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param loopDivisionRelId the primary key of the loop division rel
	* @return the loop division rel, or <code>null</code> if a loop division rel with the primary key could not be found
	*/
	public static LoopDivisionRel fetchByPrimaryKey(long loopDivisionRelId) {
		return getPersistence().fetchByPrimaryKey(loopDivisionRelId);
	}

	public static java.util.Map<java.io.Serializable, LoopDivisionRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the loop division rels.
	*
	* @return the loop division rels
	*/
	public static List<LoopDivisionRel> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the loop division rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop division rels
	* @param end the upper bound of the range of loop division rels (not inclusive)
	* @return the range of loop division rels
	*/
	public static List<LoopDivisionRel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the loop division rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop division rels
	* @param end the upper bound of the range of loop division rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop division rels
	*/
	public static List<LoopDivisionRel> findAll(int start, int end,
		OrderByComparator<LoopDivisionRel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the loop division rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop division rels
	* @param end the upper bound of the range of loop division rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop division rels
	*/
	public static List<LoopDivisionRel> findAll(int start, int end,
		OrderByComparator<LoopDivisionRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the loop division rels from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of loop division rels.
	*
	* @return the number of loop division rels
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LoopDivisionRelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LoopDivisionRelPersistence, LoopDivisionRelPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopDivisionRelPersistence.class);

		ServiceTracker<LoopDivisionRelPersistence, LoopDivisionRelPersistence> serviceTracker =
			new ServiceTracker<LoopDivisionRelPersistence, LoopDivisionRelPersistence>(bundle.getBundleContext(),
				LoopDivisionRelPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}