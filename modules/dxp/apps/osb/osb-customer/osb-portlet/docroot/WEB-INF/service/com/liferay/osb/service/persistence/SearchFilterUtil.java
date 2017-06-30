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

import com.liferay.osb.model.SearchFilter;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the search filter service. This utility wraps {@link SearchFilterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SearchFilterPersistence
 * @see SearchFilterPersistenceImpl
 * @generated
 */
public class SearchFilterUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(SearchFilter searchFilter) {
		getPersistence().clearCache(searchFilter);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SearchFilter> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SearchFilter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SearchFilter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static SearchFilter update(SearchFilter searchFilter, boolean merge)
		throws SystemException {
		return getPersistence().update(searchFilter, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static SearchFilter update(SearchFilter searchFilter, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(searchFilter, merge, serviceContext);
	}

	/**
	* Caches the search filter in the entity cache if it is enabled.
	*
	* @param searchFilter the search filter
	*/
	public static void cacheResult(
		com.liferay.osb.model.SearchFilter searchFilter) {
		getPersistence().cacheResult(searchFilter);
	}

	/**
	* Caches the search filters in the entity cache if it is enabled.
	*
	* @param searchFilters the search filters
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.SearchFilter> searchFilters) {
		getPersistence().cacheResult(searchFilters);
	}

	/**
	* Creates a new search filter with the primary key. Does not add the search filter to the database.
	*
	* @param searchFilterId the primary key for the new search filter
	* @return the new search filter
	*/
	public static com.liferay.osb.model.SearchFilter create(long searchFilterId) {
		return getPersistence().create(searchFilterId);
	}

	/**
	* Removes the search filter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param searchFilterId the primary key of the search filter
	* @return the search filter that was removed
	* @throws com.liferay.osb.NoSuchSearchFilterException if a search filter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SearchFilter remove(long searchFilterId)
		throws com.liferay.osb.NoSuchSearchFilterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(searchFilterId);
	}

	public static com.liferay.osb.model.SearchFilter updateImpl(
		com.liferay.osb.model.SearchFilter searchFilter, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(searchFilter, merge);
	}

	/**
	* Returns the search filter with the primary key or throws a {@link com.liferay.osb.NoSuchSearchFilterException} if it could not be found.
	*
	* @param searchFilterId the primary key of the search filter
	* @return the search filter
	* @throws com.liferay.osb.NoSuchSearchFilterException if a search filter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SearchFilter findByPrimaryKey(
		long searchFilterId)
		throws com.liferay.osb.NoSuchSearchFilterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(searchFilterId);
	}

	/**
	* Returns the search filter with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param searchFilterId the primary key of the search filter
	* @return the search filter, or <code>null</code> if a search filter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SearchFilter fetchByPrimaryKey(
		long searchFilterId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(searchFilterId);
	}

	/**
	* Returns all the search filters where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching search filters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SearchFilter> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the search filters where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @return the range of matching search filters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SearchFilter> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the search filters where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching search filters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SearchFilter> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first search filter in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching search filter
	* @throws com.liferay.osb.NoSuchSearchFilterException if a matching search filter could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SearchFilter findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSearchFilterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first search filter in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching search filter, or <code>null</code> if a matching search filter could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SearchFilter fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last search filter in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching search filter
	* @throws com.liferay.osb.NoSuchSearchFilterException if a matching search filter could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SearchFilter findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSearchFilterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last search filter in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching search filter, or <code>null</code> if a matching search filter could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SearchFilter fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the search filters before and after the current search filter in the ordered set where userId = &#63;.
	*
	* @param searchFilterId the primary key of the current search filter
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next search filter
	* @throws com.liferay.osb.NoSuchSearchFilterException if a search filter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SearchFilter[] findByUserId_PrevAndNext(
		long searchFilterId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSearchFilterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(searchFilterId, userId,
			orderByComparator);
	}

	/**
	* Returns all the search filters where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @return the matching search filters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SearchFilter> findByU_C(
		long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_C(userId, classNameId);
	}

	/**
	* Returns a range of all the search filters where userId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @return the range of matching search filters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SearchFilter> findByU_C(
		long userId, long classNameId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_C(userId, classNameId, start, end);
	}

	/**
	* Returns an ordered range of all the search filters where userId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching search filters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SearchFilter> findByU_C(
		long userId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_C(userId, classNameId, start, end, orderByComparator);
	}

	/**
	* Returns the first search filter in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching search filter
	* @throws com.liferay.osb.NoSuchSearchFilterException if a matching search filter could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SearchFilter findByU_C_First(
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSearchFilterException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SearchFilter fetchByU_C_First(
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchSearchFilterException if a matching search filter could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SearchFilter findByU_C_Last(
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSearchFilterException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SearchFilter fetchByU_C_Last(
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchSearchFilterException if a search filter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SearchFilter[] findByU_C_PrevAndNext(
		long searchFilterId, long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSearchFilterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_C_PrevAndNext(searchFilterId, userId, classNameId,
			orderByComparator);
	}

	/**
	* Returns all the search filters.
	*
	* @return the search filters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SearchFilter> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the search filters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @return the range of search filters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SearchFilter> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the search filters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of search filters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SearchFilter> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the search filters where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes all the search filters where userId = &#63; and classNameId = &#63; from the database.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_C(long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_C(userId, classNameId);
	}

	/**
	* Removes all the search filters from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of search filters where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching search filters
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of search filters where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @return the number of matching search filters
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_C(long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_C(userId, classNameId);
	}

	/**
	* Returns the number of search filters.
	*
	* @return the number of search filters
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
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

	/**
	 * @deprecated
	 */
	public void setPersistence(SearchFilterPersistence persistence) {
	}

	private static SearchFilterPersistence _persistence;
}