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

import com.liferay.osb.loop.model.LoopDivision;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the loop division service. This utility wraps {@link com.liferay.osb.loop.service.persistence.impl.LoopDivisionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopDivisionPersistence
 * @see com.liferay.osb.loop.service.persistence.impl.LoopDivisionPersistenceImpl
 * @generated
 */
@ProviderType
public class LoopDivisionUtil {
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
	public static void clearCache(LoopDivision loopDivision) {
		getPersistence().clearCache(loopDivision);
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
	public static List<LoopDivision> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoopDivision> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoopDivision> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoopDivision> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoopDivision update(LoopDivision loopDivision) {
		return getPersistence().update(loopDivision);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoopDivision update(LoopDivision loopDivision,
		ServiceContext serviceContext) {
		return getPersistence().update(loopDivision, serviceContext);
	}

	/**
	* Returns the loop division where organizationId = &#63; or throws a {@link NoSuchLoopDivisionException} if it could not be found.
	*
	* @param organizationId the organization ID
	* @return the matching loop division
	* @throws NoSuchLoopDivisionException if a matching loop division could not be found
	*/
	public static LoopDivision findByOrganizationId(long organizationId)
		throws com.liferay.osb.loop.exception.NoSuchLoopDivisionException {
		return getPersistence().findByOrganizationId(organizationId);
	}

	/**
	* Returns the loop division where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param organizationId the organization ID
	* @return the matching loop division, or <code>null</code> if a matching loop division could not be found
	*/
	public static LoopDivision fetchByOrganizationId(long organizationId) {
		return getPersistence().fetchByOrganizationId(organizationId);
	}

	/**
	* Returns the loop division where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param organizationId the organization ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching loop division, or <code>null</code> if a matching loop division could not be found
	*/
	public static LoopDivision fetchByOrganizationId(long organizationId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByOrganizationId(organizationId, retrieveFromCache);
	}

	/**
	* Removes the loop division where organizationId = &#63; from the database.
	*
	* @param organizationId the organization ID
	* @return the loop division that was removed
	*/
	public static LoopDivision removeByOrganizationId(long organizationId)
		throws com.liferay.osb.loop.exception.NoSuchLoopDivisionException {
		return getPersistence().removeByOrganizationId(organizationId);
	}

	/**
	* Returns the number of loop divisions where organizationId = &#63;.
	*
	* @param organizationId the organization ID
	* @return the number of matching loop divisions
	*/
	public static int countByOrganizationId(long organizationId) {
		return getPersistence().countByOrganizationId(organizationId);
	}

	/**
	* Returns all the loop divisions where companyId = &#63; and type = &#63;.
	*
	* @param companyId the company ID
	* @param type the type
	* @return the matching loop divisions
	*/
	public static List<LoopDivision> findByCI_T(long companyId, int type) {
		return getPersistence().findByCI_T(companyId, type);
	}

	/**
	* Returns a range of all the loop divisions where companyId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param type the type
	* @param start the lower bound of the range of loop divisions
	* @param end the upper bound of the range of loop divisions (not inclusive)
	* @return the range of matching loop divisions
	*/
	public static List<LoopDivision> findByCI_T(long companyId, int type,
		int start, int end) {
		return getPersistence().findByCI_T(companyId, type, start, end);
	}

	/**
	* Returns an ordered range of all the loop divisions where companyId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param type the type
	* @param start the lower bound of the range of loop divisions
	* @param end the upper bound of the range of loop divisions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching loop divisions
	*/
	public static List<LoopDivision> findByCI_T(long companyId, int type,
		int start, int end, OrderByComparator<LoopDivision> orderByComparator) {
		return getPersistence()
				   .findByCI_T(companyId, type, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the loop divisions where companyId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param type the type
	* @param start the lower bound of the range of loop divisions
	* @param end the upper bound of the range of loop divisions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching loop divisions
	*/
	public static List<LoopDivision> findByCI_T(long companyId, int type,
		int start, int end, OrderByComparator<LoopDivision> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCI_T(companyId, type, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first loop division in the ordered set where companyId = &#63; and type = &#63;.
	*
	* @param companyId the company ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching loop division
	* @throws NoSuchLoopDivisionException if a matching loop division could not be found
	*/
	public static LoopDivision findByCI_T_First(long companyId, int type,
		OrderByComparator<LoopDivision> orderByComparator)
		throws com.liferay.osb.loop.exception.NoSuchLoopDivisionException {
		return getPersistence()
				   .findByCI_T_First(companyId, type, orderByComparator);
	}

	/**
	* Returns the first loop division in the ordered set where companyId = &#63; and type = &#63;.
	*
	* @param companyId the company ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching loop division, or <code>null</code> if a matching loop division could not be found
	*/
	public static LoopDivision fetchByCI_T_First(long companyId, int type,
		OrderByComparator<LoopDivision> orderByComparator) {
		return getPersistence()
				   .fetchByCI_T_First(companyId, type, orderByComparator);
	}

	/**
	* Returns the last loop division in the ordered set where companyId = &#63; and type = &#63;.
	*
	* @param companyId the company ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching loop division
	* @throws NoSuchLoopDivisionException if a matching loop division could not be found
	*/
	public static LoopDivision findByCI_T_Last(long companyId, int type,
		OrderByComparator<LoopDivision> orderByComparator)
		throws com.liferay.osb.loop.exception.NoSuchLoopDivisionException {
		return getPersistence()
				   .findByCI_T_Last(companyId, type, orderByComparator);
	}

	/**
	* Returns the last loop division in the ordered set where companyId = &#63; and type = &#63;.
	*
	* @param companyId the company ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching loop division, or <code>null</code> if a matching loop division could not be found
	*/
	public static LoopDivision fetchByCI_T_Last(long companyId, int type,
		OrderByComparator<LoopDivision> orderByComparator) {
		return getPersistence()
				   .fetchByCI_T_Last(companyId, type, orderByComparator);
	}

	/**
	* Returns the loop divisions before and after the current loop division in the ordered set where companyId = &#63; and type = &#63;.
	*
	* @param loopDivisionId the primary key of the current loop division
	* @param companyId the company ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next loop division
	* @throws NoSuchLoopDivisionException if a loop division with the primary key could not be found
	*/
	public static LoopDivision[] findByCI_T_PrevAndNext(long loopDivisionId,
		long companyId, int type,
		OrderByComparator<LoopDivision> orderByComparator)
		throws com.liferay.osb.loop.exception.NoSuchLoopDivisionException {
		return getPersistence()
				   .findByCI_T_PrevAndNext(loopDivisionId, companyId, type,
			orderByComparator);
	}

	/**
	* Removes all the loop divisions where companyId = &#63; and type = &#63; from the database.
	*
	* @param companyId the company ID
	* @param type the type
	*/
	public static void removeByCI_T(long companyId, int type) {
		getPersistence().removeByCI_T(companyId, type);
	}

	/**
	* Returns the number of loop divisions where companyId = &#63; and type = &#63;.
	*
	* @param companyId the company ID
	* @param type the type
	* @return the number of matching loop divisions
	*/
	public static int countByCI_T(long companyId, int type) {
		return getPersistence().countByCI_T(companyId, type);
	}

	/**
	* Caches the loop division in the entity cache if it is enabled.
	*
	* @param loopDivision the loop division
	*/
	public static void cacheResult(LoopDivision loopDivision) {
		getPersistence().cacheResult(loopDivision);
	}

	/**
	* Caches the loop divisions in the entity cache if it is enabled.
	*
	* @param loopDivisions the loop divisions
	*/
	public static void cacheResult(List<LoopDivision> loopDivisions) {
		getPersistence().cacheResult(loopDivisions);
	}

	/**
	* Creates a new loop division with the primary key. Does not add the loop division to the database.
	*
	* @param loopDivisionId the primary key for the new loop division
	* @return the new loop division
	*/
	public static LoopDivision create(long loopDivisionId) {
		return getPersistence().create(loopDivisionId);
	}

	/**
	* Removes the loop division with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopDivisionId the primary key of the loop division
	* @return the loop division that was removed
	* @throws NoSuchLoopDivisionException if a loop division with the primary key could not be found
	*/
	public static LoopDivision remove(long loopDivisionId)
		throws com.liferay.osb.loop.exception.NoSuchLoopDivisionException {
		return getPersistence().remove(loopDivisionId);
	}

	public static LoopDivision updateImpl(LoopDivision loopDivision) {
		return getPersistence().updateImpl(loopDivision);
	}

	/**
	* Returns the loop division with the primary key or throws a {@link NoSuchLoopDivisionException} if it could not be found.
	*
	* @param loopDivisionId the primary key of the loop division
	* @return the loop division
	* @throws NoSuchLoopDivisionException if a loop division with the primary key could not be found
	*/
	public static LoopDivision findByPrimaryKey(long loopDivisionId)
		throws com.liferay.osb.loop.exception.NoSuchLoopDivisionException {
		return getPersistence().findByPrimaryKey(loopDivisionId);
	}

	/**
	* Returns the loop division with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param loopDivisionId the primary key of the loop division
	* @return the loop division, or <code>null</code> if a loop division with the primary key could not be found
	*/
	public static LoopDivision fetchByPrimaryKey(long loopDivisionId) {
		return getPersistence().fetchByPrimaryKey(loopDivisionId);
	}

	public static java.util.Map<java.io.Serializable, LoopDivision> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the loop divisions.
	*
	* @return the loop divisions
	*/
	public static List<LoopDivision> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the loop divisions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop divisions
	* @param end the upper bound of the range of loop divisions (not inclusive)
	* @return the range of loop divisions
	*/
	public static List<LoopDivision> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the loop divisions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop divisions
	* @param end the upper bound of the range of loop divisions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop divisions
	*/
	public static List<LoopDivision> findAll(int start, int end,
		OrderByComparator<LoopDivision> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the loop divisions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop divisions
	* @param end the upper bound of the range of loop divisions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop divisions
	*/
	public static List<LoopDivision> findAll(int start, int end,
		OrderByComparator<LoopDivision> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the loop divisions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of loop divisions.
	*
	* @return the number of loop divisions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LoopDivisionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LoopDivisionPersistence, LoopDivisionPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopDivisionPersistence.class);

		ServiceTracker<LoopDivisionPersistence, LoopDivisionPersistence> serviceTracker =
			new ServiceTracker<LoopDivisionPersistence, LoopDivisionPersistence>(bundle.getBundleContext(),
				LoopDivisionPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}