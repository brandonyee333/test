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

import com.liferay.osb.loop.model.LoopParticipantAssignment;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the loop participant assignment service. This utility wraps <code>com.liferay.osb.loop.service.persistence.impl.LoopParticipantAssignmentPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopParticipantAssignmentPersistence
 * @generated
 */
public class LoopParticipantAssignmentUtil {

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
		LoopParticipantAssignment loopParticipantAssignment) {

		getPersistence().clearCache(loopParticipantAssignment);
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
	public static Map<Serializable, LoopParticipantAssignment>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LoopParticipantAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoopParticipantAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoopParticipantAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoopParticipantAssignment> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoopParticipantAssignment update(
		LoopParticipantAssignment loopParticipantAssignment) {

		return getPersistence().update(loopParticipantAssignment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoopParticipantAssignment update(
		LoopParticipantAssignment loopParticipantAssignment,
		ServiceContext serviceContext) {

		return getPersistence().update(
			loopParticipantAssignment, serviceContext);
	}

	/**
	 * Returns the loop participant assignment where loopDivisionId = &#63; and loopPersonId = &#63; or throws a <code>NoSuchLoopParticipantAssignmentException</code> if it could not be found.
	 *
	 * @param loopDivisionId the loop division ID
	 * @param loopPersonId the loop person ID
	 * @return the matching loop participant assignment
	 * @throws NoSuchLoopParticipantAssignmentException if a matching loop participant assignment could not be found
	 */
	public static LoopParticipantAssignment findByLDI_LPI(
			long loopDivisionId, long loopPersonId)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopParticipantAssignmentException {

		return getPersistence().findByLDI_LPI(loopDivisionId, loopPersonId);
	}

	/**
	 * Returns the loop participant assignment where loopDivisionId = &#63; and loopPersonId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param loopDivisionId the loop division ID
	 * @param loopPersonId the loop person ID
	 * @return the matching loop participant assignment, or <code>null</code> if a matching loop participant assignment could not be found
	 */
	public static LoopParticipantAssignment fetchByLDI_LPI(
		long loopDivisionId, long loopPersonId) {

		return getPersistence().fetchByLDI_LPI(loopDivisionId, loopPersonId);
	}

	/**
	 * Returns the loop participant assignment where loopDivisionId = &#63; and loopPersonId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param loopDivisionId the loop division ID
	 * @param loopPersonId the loop person ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching loop participant assignment, or <code>null</code> if a matching loop participant assignment could not be found
	 */
	public static LoopParticipantAssignment fetchByLDI_LPI(
		long loopDivisionId, long loopPersonId, boolean useFinderCache) {

		return getPersistence().fetchByLDI_LPI(
			loopDivisionId, loopPersonId, useFinderCache);
	}

	/**
	 * Removes the loop participant assignment where loopDivisionId = &#63; and loopPersonId = &#63; from the database.
	 *
	 * @param loopDivisionId the loop division ID
	 * @param loopPersonId the loop person ID
	 * @return the loop participant assignment that was removed
	 */
	public static LoopParticipantAssignment removeByLDI_LPI(
			long loopDivisionId, long loopPersonId)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopParticipantAssignmentException {

		return getPersistence().removeByLDI_LPI(loopDivisionId, loopPersonId);
	}

	/**
	 * Returns the number of loop participant assignments where loopDivisionId = &#63; and loopPersonId = &#63;.
	 *
	 * @param loopDivisionId the loop division ID
	 * @param loopPersonId the loop person ID
	 * @return the number of matching loop participant assignments
	 */
	public static int countByLDI_LPI(long loopDivisionId, long loopPersonId) {
		return getPersistence().countByLDI_LPI(loopDivisionId, loopPersonId);
	}

	/**
	 * Caches the loop participant assignment in the entity cache if it is enabled.
	 *
	 * @param loopParticipantAssignment the loop participant assignment
	 */
	public static void cacheResult(
		LoopParticipantAssignment loopParticipantAssignment) {

		getPersistence().cacheResult(loopParticipantAssignment);
	}

	/**
	 * Caches the loop participant assignments in the entity cache if it is enabled.
	 *
	 * @param loopParticipantAssignments the loop participant assignments
	 */
	public static void cacheResult(
		List<LoopParticipantAssignment> loopParticipantAssignments) {

		getPersistence().cacheResult(loopParticipantAssignments);
	}

	/**
	 * Creates a new loop participant assignment with the primary key. Does not add the loop participant assignment to the database.
	 *
	 * @param loopParticipantAssignmentId the primary key for the new loop participant assignment
	 * @return the new loop participant assignment
	 */
	public static LoopParticipantAssignment create(
		long loopParticipantAssignmentId) {

		return getPersistence().create(loopParticipantAssignmentId);
	}

	/**
	 * Removes the loop participant assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopParticipantAssignmentId the primary key of the loop participant assignment
	 * @return the loop participant assignment that was removed
	 * @throws NoSuchLoopParticipantAssignmentException if a loop participant assignment with the primary key could not be found
	 */
	public static LoopParticipantAssignment remove(
			long loopParticipantAssignmentId)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopParticipantAssignmentException {

		return getPersistence().remove(loopParticipantAssignmentId);
	}

	public static LoopParticipantAssignment updateImpl(
		LoopParticipantAssignment loopParticipantAssignment) {

		return getPersistence().updateImpl(loopParticipantAssignment);
	}

	/**
	 * Returns the loop participant assignment with the primary key or throws a <code>NoSuchLoopParticipantAssignmentException</code> if it could not be found.
	 *
	 * @param loopParticipantAssignmentId the primary key of the loop participant assignment
	 * @return the loop participant assignment
	 * @throws NoSuchLoopParticipantAssignmentException if a loop participant assignment with the primary key could not be found
	 */
	public static LoopParticipantAssignment findByPrimaryKey(
			long loopParticipantAssignmentId)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopParticipantAssignmentException {

		return getPersistence().findByPrimaryKey(loopParticipantAssignmentId);
	}

	/**
	 * Returns the loop participant assignment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopParticipantAssignmentId the primary key of the loop participant assignment
	 * @return the loop participant assignment, or <code>null</code> if a loop participant assignment with the primary key could not be found
	 */
	public static LoopParticipantAssignment fetchByPrimaryKey(
		long loopParticipantAssignmentId) {

		return getPersistence().fetchByPrimaryKey(loopParticipantAssignmentId);
	}

	/**
	 * Returns all the loop participant assignments.
	 *
	 * @return the loop participant assignments
	 */
	public static List<LoopParticipantAssignment> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the loop participant assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopParticipantAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop participant assignments
	 * @param end the upper bound of the range of loop participant assignments (not inclusive)
	 * @return the range of loop participant assignments
	 */
	public static List<LoopParticipantAssignment> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the loop participant assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopParticipantAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop participant assignments
	 * @param end the upper bound of the range of loop participant assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop participant assignments
	 */
	public static List<LoopParticipantAssignment> findAll(
		int start, int end,
		OrderByComparator<LoopParticipantAssignment> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the loop participant assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopParticipantAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop participant assignments
	 * @param end the upper bound of the range of loop participant assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop participant assignments
	 */
	public static List<LoopParticipantAssignment> findAll(
		int start, int end,
		OrderByComparator<LoopParticipantAssignment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the loop participant assignments from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of loop participant assignments.
	 *
	 * @return the number of loop participant assignments
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LoopParticipantAssignmentPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		LoopParticipantAssignmentPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile LoopParticipantAssignmentPersistence _persistence;

}