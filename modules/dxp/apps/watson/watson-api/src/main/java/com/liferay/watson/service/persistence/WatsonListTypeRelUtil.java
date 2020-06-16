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

package com.liferay.watson.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.watson.model.WatsonListTypeRel;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the watson list type rel service. This utility wraps {@link com.liferay.watson.service.persistence.impl.WatsonListTypeRelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonListTypeRelPersistence
 * @see com.liferay.watson.service.persistence.impl.WatsonListTypeRelPersistenceImpl
 * @generated
 */
@ProviderType
public class WatsonListTypeRelUtil {
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
	public static void clearCache(WatsonListTypeRel watsonListTypeRel) {
		getPersistence().clearCache(watsonListTypeRel);
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
	public static List<WatsonListTypeRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonListTypeRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonListTypeRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonListTypeRel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonListTypeRel update(WatsonListTypeRel watsonListTypeRel) {
		return getPersistence().update(watsonListTypeRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonListTypeRel update(
		WatsonListTypeRel watsonListTypeRel, ServiceContext serviceContext) {
		return getPersistence().update(watsonListTypeRel, serviceContext);
	}

	/**
	* Caches the watson list type rel in the entity cache if it is enabled.
	*
	* @param watsonListTypeRel the watson list type rel
	*/
	public static void cacheResult(WatsonListTypeRel watsonListTypeRel) {
		getPersistence().cacheResult(watsonListTypeRel);
	}

	/**
	* Caches the watson list type rels in the entity cache if it is enabled.
	*
	* @param watsonListTypeRels the watson list type rels
	*/
	public static void cacheResult(List<WatsonListTypeRel> watsonListTypeRels) {
		getPersistence().cacheResult(watsonListTypeRels);
	}

	/**
	* Creates a new watson list type rel with the primary key. Does not add the watson list type rel to the database.
	*
	* @param watsonListTypeRelId the primary key for the new watson list type rel
	* @return the new watson list type rel
	*/
	public static WatsonListTypeRel create(long watsonListTypeRelId) {
		return getPersistence().create(watsonListTypeRelId);
	}

	/**
	* Removes the watson list type rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonListTypeRelId the primary key of the watson list type rel
	* @return the watson list type rel that was removed
	* @throws NoSuchListTypeRelException if a watson list type rel with the primary key could not be found
	*/
	public static WatsonListTypeRel remove(long watsonListTypeRelId)
		throws com.liferay.watson.exception.NoSuchListTypeRelException {
		return getPersistence().remove(watsonListTypeRelId);
	}

	public static WatsonListTypeRel updateImpl(
		WatsonListTypeRel watsonListTypeRel) {
		return getPersistence().updateImpl(watsonListTypeRel);
	}

	/**
	* Returns the watson list type rel with the primary key or throws a {@link NoSuchListTypeRelException} if it could not be found.
	*
	* @param watsonListTypeRelId the primary key of the watson list type rel
	* @return the watson list type rel
	* @throws NoSuchListTypeRelException if a watson list type rel with the primary key could not be found
	*/
	public static WatsonListTypeRel findByPrimaryKey(long watsonListTypeRelId)
		throws com.liferay.watson.exception.NoSuchListTypeRelException {
		return getPersistence().findByPrimaryKey(watsonListTypeRelId);
	}

	/**
	* Returns the watson list type rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonListTypeRelId the primary key of the watson list type rel
	* @return the watson list type rel, or <code>null</code> if a watson list type rel with the primary key could not be found
	*/
	public static WatsonListTypeRel fetchByPrimaryKey(long watsonListTypeRelId) {
		return getPersistence().fetchByPrimaryKey(watsonListTypeRelId);
	}

	public static java.util.Map<java.io.Serializable, WatsonListTypeRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the watson list type rels.
	*
	* @return the watson list type rels
	*/
	public static List<WatsonListTypeRel> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson list type rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson list type rels
	* @param end the upper bound of the range of watson list type rels (not inclusive)
	* @return the range of watson list type rels
	*/
	public static List<WatsonListTypeRel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson list type rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson list type rels
	* @param end the upper bound of the range of watson list type rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson list type rels
	*/
	public static List<WatsonListTypeRel> findAll(int start, int end,
		OrderByComparator<WatsonListTypeRel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson list type rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson list type rels
	* @param end the upper bound of the range of watson list type rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson list type rels
	*/
	public static List<WatsonListTypeRel> findAll(int start, int end,
		OrderByComparator<WatsonListTypeRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson list type rels from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson list type rels.
	*
	* @return the number of watson list type rels
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WatsonListTypeRelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonListTypeRelPersistence, WatsonListTypeRelPersistence> _serviceTracker =
		ServiceTrackerFactory.open(WatsonListTypeRelPersistence.class);
}