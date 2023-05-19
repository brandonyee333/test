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

import com.liferay.osb.loop.model.LoopTopicAssignment;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the loop topic assignment service. This utility wraps <code>com.liferay.osb.loop.service.persistence.impl.LoopTopicAssignmentPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopTopicAssignmentPersistence
 * @generated
 */
public class LoopTopicAssignmentUtil {

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
	public static void clearCache(LoopTopicAssignment loopTopicAssignment) {
		getPersistence().clearCache(loopTopicAssignment);
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
	public static Map<Serializable, LoopTopicAssignment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LoopTopicAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoopTopicAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoopTopicAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoopTopicAssignment> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoopTopicAssignment update(
		LoopTopicAssignment loopTopicAssignment) {

		return getPersistence().update(loopTopicAssignment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoopTopicAssignment update(
		LoopTopicAssignment loopTopicAssignment,
		ServiceContext serviceContext) {

		return getPersistence().update(loopTopicAssignment, serviceContext);
	}

	/**
	 * Caches the loop topic assignment in the entity cache if it is enabled.
	 *
	 * @param loopTopicAssignment the loop topic assignment
	 */
	public static void cacheResult(LoopTopicAssignment loopTopicAssignment) {
		getPersistence().cacheResult(loopTopicAssignment);
	}

	/**
	 * Caches the loop topic assignments in the entity cache if it is enabled.
	 *
	 * @param loopTopicAssignments the loop topic assignments
	 */
	public static void cacheResult(
		List<LoopTopicAssignment> loopTopicAssignments) {

		getPersistence().cacheResult(loopTopicAssignments);
	}

	/**
	 * Creates a new loop topic assignment with the primary key. Does not add the loop topic assignment to the database.
	 *
	 * @param loopTopicAssignmentId the primary key for the new loop topic assignment
	 * @return the new loop topic assignment
	 */
	public static LoopTopicAssignment create(long loopTopicAssignmentId) {
		return getPersistence().create(loopTopicAssignmentId);
	}

	/**
	 * Removes the loop topic assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopTopicAssignmentId the primary key of the loop topic assignment
	 * @return the loop topic assignment that was removed
	 * @throws NoSuchLoopTopicAssignmentException if a loop topic assignment with the primary key could not be found
	 */
	public static LoopTopicAssignment remove(long loopTopicAssignmentId)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopTopicAssignmentException {

		return getPersistence().remove(loopTopicAssignmentId);
	}

	public static LoopTopicAssignment updateImpl(
		LoopTopicAssignment loopTopicAssignment) {

		return getPersistence().updateImpl(loopTopicAssignment);
	}

	/**
	 * Returns the loop topic assignment with the primary key or throws a <code>NoSuchLoopTopicAssignmentException</code> if it could not be found.
	 *
	 * @param loopTopicAssignmentId the primary key of the loop topic assignment
	 * @return the loop topic assignment
	 * @throws NoSuchLoopTopicAssignmentException if a loop topic assignment with the primary key could not be found
	 */
	public static LoopTopicAssignment findByPrimaryKey(
			long loopTopicAssignmentId)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopTopicAssignmentException {

		return getPersistence().findByPrimaryKey(loopTopicAssignmentId);
	}

	/**
	 * Returns the loop topic assignment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopTopicAssignmentId the primary key of the loop topic assignment
	 * @return the loop topic assignment, or <code>null</code> if a loop topic assignment with the primary key could not be found
	 */
	public static LoopTopicAssignment fetchByPrimaryKey(
		long loopTopicAssignmentId) {

		return getPersistence().fetchByPrimaryKey(loopTopicAssignmentId);
	}

	/**
	 * Returns all the loop topic assignments.
	 *
	 * @return the loop topic assignments
	 */
	public static List<LoopTopicAssignment> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the loop topic assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopTopicAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topic assignments
	 * @param end the upper bound of the range of loop topic assignments (not inclusive)
	 * @return the range of loop topic assignments
	 */
	public static List<LoopTopicAssignment> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the loop topic assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopTopicAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topic assignments
	 * @param end the upper bound of the range of loop topic assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop topic assignments
	 */
	public static List<LoopTopicAssignment> findAll(
		int start, int end,
		OrderByComparator<LoopTopicAssignment> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the loop topic assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopTopicAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topic assignments
	 * @param end the upper bound of the range of loop topic assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop topic assignments
	 */
	public static List<LoopTopicAssignment> findAll(
		int start, int end,
		OrderByComparator<LoopTopicAssignment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the loop topic assignments from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of loop topic assignments.
	 *
	 * @return the number of loop topic assignments
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LoopTopicAssignmentPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		LoopTopicAssignmentPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile LoopTopicAssignmentPersistence _persistence;

}