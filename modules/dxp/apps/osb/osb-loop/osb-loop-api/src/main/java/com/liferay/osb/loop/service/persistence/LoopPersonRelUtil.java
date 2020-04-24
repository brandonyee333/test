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

import com.liferay.osb.loop.model.LoopPersonRel;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the loop person rel service. This utility wraps {@link com.liferay.osb.loop.service.persistence.impl.LoopPersonRelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopPersonRelPersistence
 * @see com.liferay.osb.loop.service.persistence.impl.LoopPersonRelPersistenceImpl
 * @generated
 */
@ProviderType
public class LoopPersonRelUtil {
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
	public static void clearCache(LoopPersonRel loopPersonRel) {
		getPersistence().clearCache(loopPersonRel);
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
	public static List<LoopPersonRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoopPersonRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoopPersonRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoopPersonRel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoopPersonRel update(LoopPersonRel loopPersonRel) {
		return getPersistence().update(loopPersonRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoopPersonRel update(LoopPersonRel loopPersonRel,
		ServiceContext serviceContext) {
		return getPersistence().update(loopPersonRel, serviceContext);
	}

	/**
	* Caches the loop person rel in the entity cache if it is enabled.
	*
	* @param loopPersonRel the loop person rel
	*/
	public static void cacheResult(LoopPersonRel loopPersonRel) {
		getPersistence().cacheResult(loopPersonRel);
	}

	/**
	* Caches the loop person rels in the entity cache if it is enabled.
	*
	* @param loopPersonRels the loop person rels
	*/
	public static void cacheResult(List<LoopPersonRel> loopPersonRels) {
		getPersistence().cacheResult(loopPersonRels);
	}

	/**
	* Creates a new loop person rel with the primary key. Does not add the loop person rel to the database.
	*
	* @param loopPersonRelId the primary key for the new loop person rel
	* @return the new loop person rel
	*/
	public static LoopPersonRel create(long loopPersonRelId) {
		return getPersistence().create(loopPersonRelId);
	}

	/**
	* Removes the loop person rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopPersonRelId the primary key of the loop person rel
	* @return the loop person rel that was removed
	* @throws NoSuchLoopPersonRelException if a loop person rel with the primary key could not be found
	*/
	public static LoopPersonRel remove(long loopPersonRelId)
		throws com.liferay.osb.loop.exception.NoSuchLoopPersonRelException {
		return getPersistence().remove(loopPersonRelId);
	}

	public static LoopPersonRel updateImpl(LoopPersonRel loopPersonRel) {
		return getPersistence().updateImpl(loopPersonRel);
	}

	/**
	* Returns the loop person rel with the primary key or throws a {@link NoSuchLoopPersonRelException} if it could not be found.
	*
	* @param loopPersonRelId the primary key of the loop person rel
	* @return the loop person rel
	* @throws NoSuchLoopPersonRelException if a loop person rel with the primary key could not be found
	*/
	public static LoopPersonRel findByPrimaryKey(long loopPersonRelId)
		throws com.liferay.osb.loop.exception.NoSuchLoopPersonRelException {
		return getPersistence().findByPrimaryKey(loopPersonRelId);
	}

	/**
	* Returns the loop person rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param loopPersonRelId the primary key of the loop person rel
	* @return the loop person rel, or <code>null</code> if a loop person rel with the primary key could not be found
	*/
	public static LoopPersonRel fetchByPrimaryKey(long loopPersonRelId) {
		return getPersistence().fetchByPrimaryKey(loopPersonRelId);
	}

	public static java.util.Map<java.io.Serializable, LoopPersonRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the loop person rels.
	*
	* @return the loop person rels
	*/
	public static List<LoopPersonRel> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the loop person rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopPersonRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop person rels
	* @param end the upper bound of the range of loop person rels (not inclusive)
	* @return the range of loop person rels
	*/
	public static List<LoopPersonRel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the loop person rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopPersonRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop person rels
	* @param end the upper bound of the range of loop person rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop person rels
	*/
	public static List<LoopPersonRel> findAll(int start, int end,
		OrderByComparator<LoopPersonRel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the loop person rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopPersonRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop person rels
	* @param end the upper bound of the range of loop person rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop person rels
	*/
	public static List<LoopPersonRel> findAll(int start, int end,
		OrderByComparator<LoopPersonRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the loop person rels from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of loop person rels.
	*
	* @return the number of loop person rels
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LoopPersonRelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LoopPersonRelPersistence, LoopPersonRelPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopPersonRelPersistence.class);

		ServiceTracker<LoopPersonRelPersistence, LoopPersonRelPersistence> serviceTracker =
			new ServiceTracker<LoopPersonRelPersistence, LoopPersonRelPersistence>(bundle.getBundleContext(),
				LoopPersonRelPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}