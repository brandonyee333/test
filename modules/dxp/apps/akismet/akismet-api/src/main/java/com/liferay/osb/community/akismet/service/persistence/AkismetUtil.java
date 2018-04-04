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

package com.liferay.osb.community.akismet.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.community.akismet.model.Akismet;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the akismet service. This utility wraps {@link com.liferay.osb.community.akismet.service.persistence.impl.AkismetPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jamie Sammons
 * @see AkismetPersistence
 * @see com.liferay.osb.community.akismet.service.persistence.impl.AkismetPersistenceImpl
 * @generated
 */
@ProviderType
public class AkismetUtil {
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
	public static void clearCache(Akismet akismet) {
		getPersistence().clearCache(akismet);
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
	public static List<Akismet> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Akismet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Akismet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Akismet> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Akismet update(Akismet akismet) {
		return getPersistence().update(akismet);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Akismet update(Akismet akismet, ServiceContext serviceContext) {
		return getPersistence().update(akismet, serviceContext);
	}

	/**
	* Returns all the akismets where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the matching akismets
	*/
	public static List<Akismet> findByLtModifiedDate(Date modifiedDate) {
		return getPersistence().findByLtModifiedDate(modifiedDate);
	}

	/**
	* Returns a range of all the akismets where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of akismets
	* @param end the upper bound of the range of akismets (not inclusive)
	* @return the range of matching akismets
	*/
	public static List<Akismet> findByLtModifiedDate(Date modifiedDate,
		int start, int end) {
		return getPersistence().findByLtModifiedDate(modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the akismets where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of akismets
	* @param end the upper bound of the range of akismets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching akismets
	*/
	public static List<Akismet> findByLtModifiedDate(Date modifiedDate,
		int start, int end, OrderByComparator<Akismet> orderByComparator) {
		return getPersistence()
				   .findByLtModifiedDate(modifiedDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the akismets where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of akismets
	* @param end the upper bound of the range of akismets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching akismets
	*/
	public static List<Akismet> findByLtModifiedDate(Date modifiedDate,
		int start, int end, OrderByComparator<Akismet> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLtModifiedDate(modifiedDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first akismet in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching akismet
	* @throws NoSuchAkismetException if a matching akismet could not be found
	*/
	public static Akismet findByLtModifiedDate_First(Date modifiedDate,
		OrderByComparator<Akismet> orderByComparator)
		throws com.liferay.osb.community.akismet.exception.NoSuchAkismetException {
		return getPersistence()
				   .findByLtModifiedDate_First(modifiedDate, orderByComparator);
	}

	/**
	* Returns the first akismet in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching akismet, or <code>null</code> if a matching akismet could not be found
	*/
	public static Akismet fetchByLtModifiedDate_First(Date modifiedDate,
		OrderByComparator<Akismet> orderByComparator) {
		return getPersistence()
				   .fetchByLtModifiedDate_First(modifiedDate, orderByComparator);
	}

	/**
	* Returns the last akismet in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching akismet
	* @throws NoSuchAkismetException if a matching akismet could not be found
	*/
	public static Akismet findByLtModifiedDate_Last(Date modifiedDate,
		OrderByComparator<Akismet> orderByComparator)
		throws com.liferay.osb.community.akismet.exception.NoSuchAkismetException {
		return getPersistence()
				   .findByLtModifiedDate_Last(modifiedDate, orderByComparator);
	}

	/**
	* Returns the last akismet in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching akismet, or <code>null</code> if a matching akismet could not be found
	*/
	public static Akismet fetchByLtModifiedDate_Last(Date modifiedDate,
		OrderByComparator<Akismet> orderByComparator) {
		return getPersistence()
				   .fetchByLtModifiedDate_Last(modifiedDate, orderByComparator);
	}

	/**
	* Returns the akismets before and after the current akismet in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param akismetId the primary key of the current akismet
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next akismet
	* @throws NoSuchAkismetException if a akismet with the primary key could not be found
	*/
	public static Akismet[] findByLtModifiedDate_PrevAndNext(long akismetId,
		Date modifiedDate, OrderByComparator<Akismet> orderByComparator)
		throws com.liferay.osb.community.akismet.exception.NoSuchAkismetException {
		return getPersistence()
				   .findByLtModifiedDate_PrevAndNext(akismetId, modifiedDate,
			orderByComparator);
	}

	/**
	* Removes all the akismets where modifiedDate &lt; &#63; from the database.
	*
	* @param modifiedDate the modified date
	*/
	public static void removeByLtModifiedDate(Date modifiedDate) {
		getPersistence().removeByLtModifiedDate(modifiedDate);
	}

	/**
	* Returns the number of akismets where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the number of matching akismets
	*/
	public static int countByLtModifiedDate(Date modifiedDate) {
		return getPersistence().countByLtModifiedDate(modifiedDate);
	}

	/**
	* Returns the akismet where classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchAkismetException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching akismet
	* @throws NoSuchAkismetException if a matching akismet could not be found
	*/
	public static Akismet findByC_C(long classNameId, long classPK)
		throws com.liferay.osb.community.akismet.exception.NoSuchAkismetException {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	* Returns the akismet where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching akismet, or <code>null</code> if a matching akismet could not be found
	*/
	public static Akismet fetchByC_C(long classNameId, long classPK) {
		return getPersistence().fetchByC_C(classNameId, classPK);
	}

	/**
	* Returns the akismet where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching akismet, or <code>null</code> if a matching akismet could not be found
	*/
	public static Akismet fetchByC_C(long classNameId, long classPK,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByC_C(classNameId, classPK, retrieveFromCache);
	}

	/**
	* Removes the akismet where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the akismet that was removed
	*/
	public static Akismet removeByC_C(long classNameId, long classPK)
		throws com.liferay.osb.community.akismet.exception.NoSuchAkismetException {
		return getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Returns the number of akismets where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching akismets
	*/
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	* Caches the akismet in the entity cache if it is enabled.
	*
	* @param akismet the akismet
	*/
	public static void cacheResult(Akismet akismet) {
		getPersistence().cacheResult(akismet);
	}

	/**
	* Caches the akismets in the entity cache if it is enabled.
	*
	* @param akismets the akismets
	*/
	public static void cacheResult(List<Akismet> akismets) {
		getPersistence().cacheResult(akismets);
	}

	/**
	* Creates a new akismet with the primary key. Does not add the akismet to the database.
	*
	* @param akismetId the primary key for the new akismet
	* @return the new akismet
	*/
	public static Akismet create(long akismetId) {
		return getPersistence().create(akismetId);
	}

	/**
	* Removes the akismet with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param akismetId the primary key of the akismet
	* @return the akismet that was removed
	* @throws NoSuchAkismetException if a akismet with the primary key could not be found
	*/
	public static Akismet remove(long akismetId)
		throws com.liferay.osb.community.akismet.exception.NoSuchAkismetException {
		return getPersistence().remove(akismetId);
	}

	public static Akismet updateImpl(Akismet akismet) {
		return getPersistence().updateImpl(akismet);
	}

	/**
	* Returns the akismet with the primary key or throws a {@link NoSuchAkismetException} if it could not be found.
	*
	* @param akismetId the primary key of the akismet
	* @return the akismet
	* @throws NoSuchAkismetException if a akismet with the primary key could not be found
	*/
	public static Akismet findByPrimaryKey(long akismetId)
		throws com.liferay.osb.community.akismet.exception.NoSuchAkismetException {
		return getPersistence().findByPrimaryKey(akismetId);
	}

	/**
	* Returns the akismet with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param akismetId the primary key of the akismet
	* @return the akismet, or <code>null</code> if a akismet with the primary key could not be found
	*/
	public static Akismet fetchByPrimaryKey(long akismetId) {
		return getPersistence().fetchByPrimaryKey(akismetId);
	}

	public static java.util.Map<java.io.Serializable, Akismet> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the akismets.
	*
	* @return the akismets
	*/
	public static List<Akismet> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the akismets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of akismets
	* @param end the upper bound of the range of akismets (not inclusive)
	* @return the range of akismets
	*/
	public static List<Akismet> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the akismets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of akismets
	* @param end the upper bound of the range of akismets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of akismets
	*/
	public static List<Akismet> findAll(int start, int end,
		OrderByComparator<Akismet> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the akismets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of akismets
	* @param end the upper bound of the range of akismets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of akismets
	*/
	public static List<Akismet> findAll(int start, int end,
		OrderByComparator<Akismet> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the akismets from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of akismets.
	*
	* @return the number of akismets
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AkismetPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AkismetPersistence, AkismetPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AkismetPersistence.class);
}