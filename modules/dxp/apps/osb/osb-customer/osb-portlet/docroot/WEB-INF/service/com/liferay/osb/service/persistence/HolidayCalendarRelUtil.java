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

package com.liferay.osb.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.HolidayCalendarRel;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the holiday calendar rel service. This utility wraps {@link com.liferay.osb.service.persistence.impl.HolidayCalendarRelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayCalendarRelPersistence
 * @see com.liferay.osb.service.persistence.impl.HolidayCalendarRelPersistenceImpl
 * @generated
 */
@ProviderType
public class HolidayCalendarRelUtil {
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
	public static void clearCache(HolidayCalendarRel holidayCalendarRel) {
		getPersistence().clearCache(holidayCalendarRel);
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
	public static List<HolidayCalendarRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HolidayCalendarRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HolidayCalendarRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HolidayCalendarRel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HolidayCalendarRel update(
		HolidayCalendarRel holidayCalendarRel) {
		return getPersistence().update(holidayCalendarRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HolidayCalendarRel update(
		HolidayCalendarRel holidayCalendarRel, ServiceContext serviceContext) {
		return getPersistence().update(holidayCalendarRel, serviceContext);
	}

	/**
	* Returns all the holiday calendar rels where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @return the matching holiday calendar rels
	*/
	public static List<HolidayCalendarRel> findByHolidayCalendarId(
		long holidayCalendarId) {
		return getPersistence().findByHolidayCalendarId(holidayCalendarId);
	}

	/**
	* Returns a range of all the holiday calendar rels where holidayCalendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param start the lower bound of the range of holiday calendar rels
	* @param end the upper bound of the range of holiday calendar rels (not inclusive)
	* @return the range of matching holiday calendar rels
	*/
	public static List<HolidayCalendarRel> findByHolidayCalendarId(
		long holidayCalendarId, int start, int end) {
		return getPersistence()
				   .findByHolidayCalendarId(holidayCalendarId, start, end);
	}

	/**
	* Returns an ordered range of all the holiday calendar rels where holidayCalendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param start the lower bound of the range of holiday calendar rels
	* @param end the upper bound of the range of holiday calendar rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching holiday calendar rels
	*/
	public static List<HolidayCalendarRel> findByHolidayCalendarId(
		long holidayCalendarId, int start, int end,
		OrderByComparator<HolidayCalendarRel> orderByComparator) {
		return getPersistence()
				   .findByHolidayCalendarId(holidayCalendarId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the holiday calendar rels where holidayCalendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param start the lower bound of the range of holiday calendar rels
	* @param end the upper bound of the range of holiday calendar rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching holiday calendar rels
	*/
	public static List<HolidayCalendarRel> findByHolidayCalendarId(
		long holidayCalendarId, int start, int end,
		OrderByComparator<HolidayCalendarRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByHolidayCalendarId(holidayCalendarId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday calendar rel
	* @throws NoSuchHolidayCalendarRelException if a matching holiday calendar rel could not be found
	*/
	public static HolidayCalendarRel findByHolidayCalendarId_First(
		long holidayCalendarId,
		OrderByComparator<HolidayCalendarRel> orderByComparator)
		throws com.liferay.osb.exception.NoSuchHolidayCalendarRelException {
		return getPersistence()
				   .findByHolidayCalendarId_First(holidayCalendarId,
			orderByComparator);
	}

	/**
	* Returns the first holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	*/
	public static HolidayCalendarRel fetchByHolidayCalendarId_First(
		long holidayCalendarId,
		OrderByComparator<HolidayCalendarRel> orderByComparator) {
		return getPersistence()
				   .fetchByHolidayCalendarId_First(holidayCalendarId,
			orderByComparator);
	}

	/**
	* Returns the last holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday calendar rel
	* @throws NoSuchHolidayCalendarRelException if a matching holiday calendar rel could not be found
	*/
	public static HolidayCalendarRel findByHolidayCalendarId_Last(
		long holidayCalendarId,
		OrderByComparator<HolidayCalendarRel> orderByComparator)
		throws com.liferay.osb.exception.NoSuchHolidayCalendarRelException {
		return getPersistence()
				   .findByHolidayCalendarId_Last(holidayCalendarId,
			orderByComparator);
	}

	/**
	* Returns the last holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	*/
	public static HolidayCalendarRel fetchByHolidayCalendarId_Last(
		long holidayCalendarId,
		OrderByComparator<HolidayCalendarRel> orderByComparator) {
		return getPersistence()
				   .fetchByHolidayCalendarId_Last(holidayCalendarId,
			orderByComparator);
	}

	/**
	* Returns the holiday calendar rels before and after the current holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarRelId the primary key of the current holiday calendar rel
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next holiday calendar rel
	* @throws NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	*/
	public static HolidayCalendarRel[] findByHolidayCalendarId_PrevAndNext(
		long holidayCalendarRelId, long holidayCalendarId,
		OrderByComparator<HolidayCalendarRel> orderByComparator)
		throws com.liferay.osb.exception.NoSuchHolidayCalendarRelException {
		return getPersistence()
				   .findByHolidayCalendarId_PrevAndNext(holidayCalendarRelId,
			holidayCalendarId, orderByComparator);
	}

	/**
	* Removes all the holiday calendar rels where holidayCalendarId = &#63; from the database.
	*
	* @param holidayCalendarId the holiday calendar ID
	*/
	public static void removeByHolidayCalendarId(long holidayCalendarId) {
		getPersistence().removeByHolidayCalendarId(holidayCalendarId);
	}

	/**
	* Returns the number of holiday calendar rels where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @return the number of matching holiday calendar rels
	*/
	public static int countByHolidayCalendarId(long holidayCalendarId) {
		return getPersistence().countByHolidayCalendarId(holidayCalendarId);
	}

	/**
	* Returns the holiday calendar rel where holidayCalendarId = &#63; and userId = &#63; or throws a {@link NoSuchHolidayCalendarRelException} if it could not be found.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param userId the user ID
	* @return the matching holiday calendar rel
	* @throws NoSuchHolidayCalendarRelException if a matching holiday calendar rel could not be found
	*/
	public static HolidayCalendarRel findByHC_U(long holidayCalendarId,
		long userId)
		throws com.liferay.osb.exception.NoSuchHolidayCalendarRelException {
		return getPersistence().findByHC_U(holidayCalendarId, userId);
	}

	/**
	* Returns the holiday calendar rel where holidayCalendarId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param userId the user ID
	* @return the matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	*/
	public static HolidayCalendarRel fetchByHC_U(long holidayCalendarId,
		long userId) {
		return getPersistence().fetchByHC_U(holidayCalendarId, userId);
	}

	/**
	* Returns the holiday calendar rel where holidayCalendarId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	*/
	public static HolidayCalendarRel fetchByHC_U(long holidayCalendarId,
		long userId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByHC_U(holidayCalendarId, userId, retrieveFromCache);
	}

	/**
	* Removes the holiday calendar rel where holidayCalendarId = &#63; and userId = &#63; from the database.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param userId the user ID
	* @return the holiday calendar rel that was removed
	*/
	public static HolidayCalendarRel removeByHC_U(long holidayCalendarId,
		long userId)
		throws com.liferay.osb.exception.NoSuchHolidayCalendarRelException {
		return getPersistence().removeByHC_U(holidayCalendarId, userId);
	}

	/**
	* Returns the number of holiday calendar rels where holidayCalendarId = &#63; and userId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param userId the user ID
	* @return the number of matching holiday calendar rels
	*/
	public static int countByHC_U(long holidayCalendarId, long userId) {
		return getPersistence().countByHC_U(holidayCalendarId, userId);
	}

	/**
	* Caches the holiday calendar rel in the entity cache if it is enabled.
	*
	* @param holidayCalendarRel the holiday calendar rel
	*/
	public static void cacheResult(HolidayCalendarRel holidayCalendarRel) {
		getPersistence().cacheResult(holidayCalendarRel);
	}

	/**
	* Caches the holiday calendar rels in the entity cache if it is enabled.
	*
	* @param holidayCalendarRels the holiday calendar rels
	*/
	public static void cacheResult(List<HolidayCalendarRel> holidayCalendarRels) {
		getPersistence().cacheResult(holidayCalendarRels);
	}

	/**
	* Creates a new holiday calendar rel with the primary key. Does not add the holiday calendar rel to the database.
	*
	* @param holidayCalendarRelId the primary key for the new holiday calendar rel
	* @return the new holiday calendar rel
	*/
	public static HolidayCalendarRel create(long holidayCalendarRelId) {
		return getPersistence().create(holidayCalendarRelId);
	}

	/**
	* Removes the holiday calendar rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarRelId the primary key of the holiday calendar rel
	* @return the holiday calendar rel that was removed
	* @throws NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	*/
	public static HolidayCalendarRel remove(long holidayCalendarRelId)
		throws com.liferay.osb.exception.NoSuchHolidayCalendarRelException {
		return getPersistence().remove(holidayCalendarRelId);
	}

	public static HolidayCalendarRel updateImpl(
		HolidayCalendarRel holidayCalendarRel) {
		return getPersistence().updateImpl(holidayCalendarRel);
	}

	/**
	* Returns the holiday calendar rel with the primary key or throws a {@link NoSuchHolidayCalendarRelException} if it could not be found.
	*
	* @param holidayCalendarRelId the primary key of the holiday calendar rel
	* @return the holiday calendar rel
	* @throws NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	*/
	public static HolidayCalendarRel findByPrimaryKey(long holidayCalendarRelId)
		throws com.liferay.osb.exception.NoSuchHolidayCalendarRelException {
		return getPersistence().findByPrimaryKey(holidayCalendarRelId);
	}

	/**
	* Returns the holiday calendar rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param holidayCalendarRelId the primary key of the holiday calendar rel
	* @return the holiday calendar rel, or <code>null</code> if a holiday calendar rel with the primary key could not be found
	*/
	public static HolidayCalendarRel fetchByPrimaryKey(
		long holidayCalendarRelId) {
		return getPersistence().fetchByPrimaryKey(holidayCalendarRelId);
	}

	public static java.util.Map<java.io.Serializable, HolidayCalendarRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the holiday calendar rels.
	*
	* @return the holiday calendar rels
	*/
	public static List<HolidayCalendarRel> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the holiday calendar rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holiday calendar rels
	* @param end the upper bound of the range of holiday calendar rels (not inclusive)
	* @return the range of holiday calendar rels
	*/
	public static List<HolidayCalendarRel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the holiday calendar rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holiday calendar rels
	* @param end the upper bound of the range of holiday calendar rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of holiday calendar rels
	*/
	public static List<HolidayCalendarRel> findAll(int start, int end,
		OrderByComparator<HolidayCalendarRel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the holiday calendar rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holiday calendar rels
	* @param end the upper bound of the range of holiday calendar rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of holiday calendar rels
	*/
	public static List<HolidayCalendarRel> findAll(int start, int end,
		OrderByComparator<HolidayCalendarRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the holiday calendar rels from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of holiday calendar rels.
	*
	* @return the number of holiday calendar rels
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static HolidayCalendarRelPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HolidayCalendarRelPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					HolidayCalendarRelPersistence.class.getName());

			ReferenceRegistry.registerReference(HolidayCalendarRelUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static HolidayCalendarRelPersistence _persistence;
}