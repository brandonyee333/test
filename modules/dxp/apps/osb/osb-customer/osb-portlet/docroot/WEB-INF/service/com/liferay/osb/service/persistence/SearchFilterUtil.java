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

import com.liferay.osb.model.SearchFilter;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the search filter service. This utility wraps {@link com.liferay.osb.service.persistence.impl.SearchFilterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SearchFilterPersistence
 * @see com.liferay.osb.service.persistence.impl.SearchFilterPersistenceImpl
 * @generated
 */
@ProviderType
public class SearchFilterUtil {
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
	public static void clearCache(SearchFilter searchFilter) {
		getPersistence().clearCache(searchFilter);
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
	public static List<SearchFilter> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SearchFilter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SearchFilter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SearchFilter> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SearchFilter update(SearchFilter searchFilter) {
		return getPersistence().update(searchFilter);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SearchFilter update(SearchFilter searchFilter,
		ServiceContext serviceContext) {
		return getPersistence().update(searchFilter, serviceContext);
	}

	/**
	* Returns all the search filters where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching search filters
	*/
	public static List<SearchFilter> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the search filters where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @return the range of matching search filters
	*/
	public static List<SearchFilter> findByUserId(long userId, int start,
		int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the search filters where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching search filters
	*/
	public static List<SearchFilter> findByUserId(long userId, int start,
		int end, OrderByComparator<SearchFilter> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the search filters where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching search filters
	*/
	public static List<SearchFilter> findByUserId(long userId, int start,
		int end, OrderByComparator<SearchFilter> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first search filter in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching search filter
	* @throws NoSuchSearchFilterException if a matching search filter could not be found
	*/
	public static SearchFilter findByUserId_First(long userId,
		OrderByComparator<SearchFilter> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSearchFilterException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first search filter in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching search filter, or <code>null</code> if a matching search filter could not be found
	*/
	public static SearchFilter fetchByUserId_First(long userId,
		OrderByComparator<SearchFilter> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last search filter in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching search filter
	* @throws NoSuchSearchFilterException if a matching search filter could not be found
	*/
	public static SearchFilter findByUserId_Last(long userId,
		OrderByComparator<SearchFilter> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSearchFilterException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last search filter in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching search filter, or <code>null</code> if a matching search filter could not be found
	*/
	public static SearchFilter fetchByUserId_Last(long userId,
		OrderByComparator<SearchFilter> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the search filters before and after the current search filter in the ordered set where userId = &#63;.
	*
	* @param searchFilterId the primary key of the current search filter
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next search filter
	* @throws NoSuchSearchFilterException if a search filter with the primary key could not be found
	*/
	public static SearchFilter[] findByUserId_PrevAndNext(long searchFilterId,
		long userId, OrderByComparator<SearchFilter> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSearchFilterException {
		return getPersistence()
				   .findByUserId_PrevAndNext(searchFilterId, userId,
			orderByComparator);
	}

	/**
	* Removes all the search filters where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of search filters where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching search filters
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the search filters where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @return the matching search filters
	*/
	public static List<SearchFilter> findByU_C(long userId, long classNameId) {
		return getPersistence().findByU_C(userId, classNameId);
	}

	/**
	* Returns a range of all the search filters where userId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @return the range of matching search filters
	*/
	public static List<SearchFilter> findByU_C(long userId, long classNameId,
		int start, int end) {
		return getPersistence().findByU_C(userId, classNameId, start, end);
	}

	/**
	* Returns an ordered range of all the search filters where userId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching search filters
	*/
	public static List<SearchFilter> findByU_C(long userId, long classNameId,
		int start, int end, OrderByComparator<SearchFilter> orderByComparator) {
		return getPersistence()
				   .findByU_C(userId, classNameId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the search filters where userId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching search filters
	*/
	public static List<SearchFilter> findByU_C(long userId, long classNameId,
		int start, int end, OrderByComparator<SearchFilter> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_C(userId, classNameId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first search filter in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching search filter
	* @throws NoSuchSearchFilterException if a matching search filter could not be found
	*/
	public static SearchFilter findByU_C_First(long userId, long classNameId,
		OrderByComparator<SearchFilter> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSearchFilterException {
		return getPersistence()
				   .findByU_C_First(userId, classNameId, orderByComparator);
	}

	/**
	* Returns the first search filter in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching search filter, or <code>null</code> if a matching search filter could not be found
	*/
	public static SearchFilter fetchByU_C_First(long userId, long classNameId,
		OrderByComparator<SearchFilter> orderByComparator) {
		return getPersistence()
				   .fetchByU_C_First(userId, classNameId, orderByComparator);
	}

	/**
	* Returns the last search filter in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching search filter
	* @throws NoSuchSearchFilterException if a matching search filter could not be found
	*/
	public static SearchFilter findByU_C_Last(long userId, long classNameId,
		OrderByComparator<SearchFilter> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSearchFilterException {
		return getPersistence()
				   .findByU_C_Last(userId, classNameId, orderByComparator);
	}

	/**
	* Returns the last search filter in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching search filter, or <code>null</code> if a matching search filter could not be found
	*/
	public static SearchFilter fetchByU_C_Last(long userId, long classNameId,
		OrderByComparator<SearchFilter> orderByComparator) {
		return getPersistence()
				   .fetchByU_C_Last(userId, classNameId, orderByComparator);
	}

	/**
	* Returns the search filters before and after the current search filter in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param searchFilterId the primary key of the current search filter
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next search filter
	* @throws NoSuchSearchFilterException if a search filter with the primary key could not be found
	*/
	public static SearchFilter[] findByU_C_PrevAndNext(long searchFilterId,
		long userId, long classNameId,
		OrderByComparator<SearchFilter> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSearchFilterException {
		return getPersistence()
				   .findByU_C_PrevAndNext(searchFilterId, userId, classNameId,
			orderByComparator);
	}

	/**
	* Removes all the search filters where userId = &#63; and classNameId = &#63; from the database.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	*/
	public static void removeByU_C(long userId, long classNameId) {
		getPersistence().removeByU_C(userId, classNameId);
	}

	/**
	* Returns the number of search filters where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @return the number of matching search filters
	*/
	public static int countByU_C(long userId, long classNameId) {
		return getPersistence().countByU_C(userId, classNameId);
	}

	/**
	* Caches the search filter in the entity cache if it is enabled.
	*
	* @param searchFilter the search filter
	*/
	public static void cacheResult(SearchFilter searchFilter) {
		getPersistence().cacheResult(searchFilter);
	}

	/**
	* Caches the search filters in the entity cache if it is enabled.
	*
	* @param searchFilters the search filters
	*/
	public static void cacheResult(List<SearchFilter> searchFilters) {
		getPersistence().cacheResult(searchFilters);
	}

	/**
	* Creates a new search filter with the primary key. Does not add the search filter to the database.
	*
	* @param searchFilterId the primary key for the new search filter
	* @return the new search filter
	*/
	public static SearchFilter create(long searchFilterId) {
		return getPersistence().create(searchFilterId);
	}

	/**
	* Removes the search filter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param searchFilterId the primary key of the search filter
	* @return the search filter that was removed
	* @throws NoSuchSearchFilterException if a search filter with the primary key could not be found
	*/
	public static SearchFilter remove(long searchFilterId)
		throws com.liferay.osb.exception.NoSuchSearchFilterException {
		return getPersistence().remove(searchFilterId);
	}

	public static SearchFilter updateImpl(SearchFilter searchFilter) {
		return getPersistence().updateImpl(searchFilter);
	}

	/**
	* Returns the search filter with the primary key or throws a {@link NoSuchSearchFilterException} if it could not be found.
	*
	* @param searchFilterId the primary key of the search filter
	* @return the search filter
	* @throws NoSuchSearchFilterException if a search filter with the primary key could not be found
	*/
	public static SearchFilter findByPrimaryKey(long searchFilterId)
		throws com.liferay.osb.exception.NoSuchSearchFilterException {
		return getPersistence().findByPrimaryKey(searchFilterId);
	}

	/**
	* Returns the search filter with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param searchFilterId the primary key of the search filter
	* @return the search filter, or <code>null</code> if a search filter with the primary key could not be found
	*/
	public static SearchFilter fetchByPrimaryKey(long searchFilterId) {
		return getPersistence().fetchByPrimaryKey(searchFilterId);
	}

	public static java.util.Map<java.io.Serializable, SearchFilter> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the search filters.
	*
	* @return the search filters
	*/
	public static List<SearchFilter> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the search filters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @return the range of search filters
	*/
	public static List<SearchFilter> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the search filters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of search filters
	*/
	public static List<SearchFilter> findAll(int start, int end,
		OrderByComparator<SearchFilter> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the search filters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of search filters
	*/
	public static List<SearchFilter> findAll(int start, int end,
		OrderByComparator<SearchFilter> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the search filters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of search filters.
	*
	* @return the number of search filters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SearchFilterPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SearchFilterPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					SearchFilterPersistence.class.getName());

			ReferenceRegistry.registerReference(SearchFilterUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static SearchFilterPersistence _persistence;
}