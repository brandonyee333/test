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

package com.liferay.osb.testray.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.model.TestrayCaseResultWarning;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the testray case result warning service. This utility wraps {@link com.liferay.osb.testray.service.persistence.impl.TestrayCaseResultWarningPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayCaseResultWarningPersistence
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayCaseResultWarningPersistenceImpl
 * @generated
 */
@ProviderType
public class TestrayCaseResultWarningUtil {
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
		TestrayCaseResultWarning testrayCaseResultWarning) {
		getPersistence().clearCache(testrayCaseResultWarning);
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
	public static List<TestrayCaseResultWarning> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayCaseResultWarning> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayCaseResultWarning> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayCaseResultWarning update(
		TestrayCaseResultWarning testrayCaseResultWarning) {
		return getPersistence().update(testrayCaseResultWarning);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayCaseResultWarning update(
		TestrayCaseResultWarning testrayCaseResultWarning,
		ServiceContext serviceContext) {
		return getPersistence().update(testrayCaseResultWarning, serviceContext);
	}

	/**
	* Returns all the testray case result warnings where testrayCaseResultId = &#63;.
	*
	* @param testrayCaseResultId the testray case result ID
	* @return the matching testray case result warnings
	*/
	public static List<TestrayCaseResultWarning> findByTestrayCaseResultId(
		long testrayCaseResultId) {
		return getPersistence().findByTestrayCaseResultId(testrayCaseResultId);
	}

	/**
	* Returns a range of all the testray case result warnings where testrayCaseResultId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseResultWarningModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param testrayCaseResultId the testray case result ID
	* @param start the lower bound of the range of testray case result warnings
	* @param end the upper bound of the range of testray case result warnings (not inclusive)
	* @return the range of matching testray case result warnings
	*/
	public static List<TestrayCaseResultWarning> findByTestrayCaseResultId(
		long testrayCaseResultId, int start, int end) {
		return getPersistence()
				   .findByTestrayCaseResultId(testrayCaseResultId, start, end);
	}

	/**
	* Returns an ordered range of all the testray case result warnings where testrayCaseResultId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseResultWarningModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param testrayCaseResultId the testray case result ID
	* @param start the lower bound of the range of testray case result warnings
	* @param end the upper bound of the range of testray case result warnings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching testray case result warnings
	*/
	public static List<TestrayCaseResultWarning> findByTestrayCaseResultId(
		long testrayCaseResultId, int start, int end,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator) {
		return getPersistence()
				   .findByTestrayCaseResultId(testrayCaseResultId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the testray case result warnings where testrayCaseResultId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseResultWarningModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param testrayCaseResultId the testray case result ID
	* @param start the lower bound of the range of testray case result warnings
	* @param end the upper bound of the range of testray case result warnings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching testray case result warnings
	*/
	public static List<TestrayCaseResultWarning> findByTestrayCaseResultId(
		long testrayCaseResultId, int start, int end,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTestrayCaseResultId(testrayCaseResultId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first testray case result warning in the ordered set where testrayCaseResultId = &#63;.
	*
	* @param testrayCaseResultId the testray case result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching testray case result warning
	* @throws NoSuchTestrayCaseResultWarningException if a matching testray case result warning could not be found
	*/
	public static TestrayCaseResultWarning findByTestrayCaseResultId_First(
		long testrayCaseResultId,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator)
		throws com.liferay.osb.testray.exception.NoSuchTestrayCaseResultWarningException {
		return getPersistence()
				   .findByTestrayCaseResultId_First(testrayCaseResultId,
			orderByComparator);
	}

	/**
	* Returns the first testray case result warning in the ordered set where testrayCaseResultId = &#63;.
	*
	* @param testrayCaseResultId the testray case result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching testray case result warning, or <code>null</code> if a matching testray case result warning could not be found
	*/
	public static TestrayCaseResultWarning fetchByTestrayCaseResultId_First(
		long testrayCaseResultId,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator) {
		return getPersistence()
				   .fetchByTestrayCaseResultId_First(testrayCaseResultId,
			orderByComparator);
	}

	/**
	* Returns the last testray case result warning in the ordered set where testrayCaseResultId = &#63;.
	*
	* @param testrayCaseResultId the testray case result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching testray case result warning
	* @throws NoSuchTestrayCaseResultWarningException if a matching testray case result warning could not be found
	*/
	public static TestrayCaseResultWarning findByTestrayCaseResultId_Last(
		long testrayCaseResultId,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator)
		throws com.liferay.osb.testray.exception.NoSuchTestrayCaseResultWarningException {
		return getPersistence()
				   .findByTestrayCaseResultId_Last(testrayCaseResultId,
			orderByComparator);
	}

	/**
	* Returns the last testray case result warning in the ordered set where testrayCaseResultId = &#63;.
	*
	* @param testrayCaseResultId the testray case result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching testray case result warning, or <code>null</code> if a matching testray case result warning could not be found
	*/
	public static TestrayCaseResultWarning fetchByTestrayCaseResultId_Last(
		long testrayCaseResultId,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator) {
		return getPersistence()
				   .fetchByTestrayCaseResultId_Last(testrayCaseResultId,
			orderByComparator);
	}

	/**
	* Returns the testray case result warnings before and after the current testray case result warning in the ordered set where testrayCaseResultId = &#63;.
	*
	* @param testrayCaseResultWarningId the primary key of the current testray case result warning
	* @param testrayCaseResultId the testray case result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next testray case result warning
	* @throws NoSuchTestrayCaseResultWarningException if a testray case result warning with the primary key could not be found
	*/
	public static TestrayCaseResultWarning[] findByTestrayCaseResultId_PrevAndNext(
		long testrayCaseResultWarningId, long testrayCaseResultId,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator)
		throws com.liferay.osb.testray.exception.NoSuchTestrayCaseResultWarningException {
		return getPersistence()
				   .findByTestrayCaseResultId_PrevAndNext(testrayCaseResultWarningId,
			testrayCaseResultId, orderByComparator);
	}

	/**
	* Removes all the testray case result warnings where testrayCaseResultId = &#63; from the database.
	*
	* @param testrayCaseResultId the testray case result ID
	*/
	public static void removeByTestrayCaseResultId(long testrayCaseResultId) {
		getPersistence().removeByTestrayCaseResultId(testrayCaseResultId);
	}

	/**
	* Returns the number of testray case result warnings where testrayCaseResultId = &#63;.
	*
	* @param testrayCaseResultId the testray case result ID
	* @return the number of matching testray case result warnings
	*/
	public static int countByTestrayCaseResultId(long testrayCaseResultId) {
		return getPersistence().countByTestrayCaseResultId(testrayCaseResultId);
	}

	/**
	* Caches the testray case result warning in the entity cache if it is enabled.
	*
	* @param testrayCaseResultWarning the testray case result warning
	*/
	public static void cacheResult(
		TestrayCaseResultWarning testrayCaseResultWarning) {
		getPersistence().cacheResult(testrayCaseResultWarning);
	}

	/**
	* Caches the testray case result warnings in the entity cache if it is enabled.
	*
	* @param testrayCaseResultWarnings the testray case result warnings
	*/
	public static void cacheResult(
		List<TestrayCaseResultWarning> testrayCaseResultWarnings) {
		getPersistence().cacheResult(testrayCaseResultWarnings);
	}

	/**
	* Creates a new testray case result warning with the primary key. Does not add the testray case result warning to the database.
	*
	* @param testrayCaseResultWarningId the primary key for the new testray case result warning
	* @return the new testray case result warning
	*/
	public static TestrayCaseResultWarning create(
		long testrayCaseResultWarningId) {
		return getPersistence().create(testrayCaseResultWarningId);
	}

	/**
	* Removes the testray case result warning with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayCaseResultWarningId the primary key of the testray case result warning
	* @return the testray case result warning that was removed
	* @throws NoSuchTestrayCaseResultWarningException if a testray case result warning with the primary key could not be found
	*/
	public static TestrayCaseResultWarning remove(
		long testrayCaseResultWarningId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayCaseResultWarningException {
		return getPersistence().remove(testrayCaseResultWarningId);
	}

	public static TestrayCaseResultWarning updateImpl(
		TestrayCaseResultWarning testrayCaseResultWarning) {
		return getPersistence().updateImpl(testrayCaseResultWarning);
	}

	/**
	* Returns the testray case result warning with the primary key or throws a {@link NoSuchTestrayCaseResultWarningException} if it could not be found.
	*
	* @param testrayCaseResultWarningId the primary key of the testray case result warning
	* @return the testray case result warning
	* @throws NoSuchTestrayCaseResultWarningException if a testray case result warning with the primary key could not be found
	*/
	public static TestrayCaseResultWarning findByPrimaryKey(
		long testrayCaseResultWarningId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayCaseResultWarningException {
		return getPersistence().findByPrimaryKey(testrayCaseResultWarningId);
	}

	/**
	* Returns the testray case result warning with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testrayCaseResultWarningId the primary key of the testray case result warning
	* @return the testray case result warning, or <code>null</code> if a testray case result warning with the primary key could not be found
	*/
	public static TestrayCaseResultWarning fetchByPrimaryKey(
		long testrayCaseResultWarningId) {
		return getPersistence().fetchByPrimaryKey(testrayCaseResultWarningId);
	}

	public static java.util.Map<java.io.Serializable, TestrayCaseResultWarning> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the testray case result warnings.
	*
	* @return the testray case result warnings
	*/
	public static List<TestrayCaseResultWarning> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the testray case result warnings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseResultWarningModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray case result warnings
	* @param end the upper bound of the range of testray case result warnings (not inclusive)
	* @return the range of testray case result warnings
	*/
	public static List<TestrayCaseResultWarning> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the testray case result warnings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseResultWarningModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray case result warnings
	* @param end the upper bound of the range of testray case result warnings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray case result warnings
	*/
	public static List<TestrayCaseResultWarning> findAll(int start, int end,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the testray case result warnings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseResultWarningModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray case result warnings
	* @param end the upper bound of the range of testray case result warnings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray case result warnings
	*/
	public static List<TestrayCaseResultWarning> findAll(int start, int end,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the testray case result warnings from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of testray case result warnings.
	*
	* @return the number of testray case result warnings
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TestrayCaseResultWarningPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestrayCaseResultWarningPersistence, TestrayCaseResultWarningPersistence> _serviceTracker =
		ServiceTrackerFactory.open(TestrayCaseResultWarningPersistence.class);
}