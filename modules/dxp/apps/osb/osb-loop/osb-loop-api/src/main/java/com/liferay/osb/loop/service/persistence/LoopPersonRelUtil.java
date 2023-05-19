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

import com.liferay.osb.loop.model.LoopPersonRel;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the loop person rel service. This utility wraps <code>com.liferay.osb.loop.service.persistence.impl.LoopPersonRelPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopPersonRelPersistence
 * @generated
 */
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
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, LoopPersonRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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
	public static LoopPersonRel update(
		LoopPersonRel loopPersonRel, ServiceContext serviceContext) {

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
	 * Returns the loop person rel with the primary key or throws a <code>NoSuchLoopPersonRelException</code> if it could not be found.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopPersonRelModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopPersonRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop person rels
	 * @param end the upper bound of the range of loop person rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop person rels
	 */
	public static List<LoopPersonRel> findAll(
		int start, int end,
		OrderByComparator<LoopPersonRel> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the loop person rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopPersonRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop person rels
	 * @param end the upper bound of the range of loop person rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop person rels
	 */
	public static List<LoopPersonRel> findAll(
		int start, int end, OrderByComparator<LoopPersonRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
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

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LoopPersonRelPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(LoopPersonRelPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile LoopPersonRelPersistence _persistence;

}