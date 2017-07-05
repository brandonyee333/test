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

import com.liferay.osb.exception.NoSuchSearchFilterException;
import com.liferay.osb.model.SearchFilter;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the search filter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.SearchFilterPersistenceImpl
 * @see SearchFilterUtil
 * @generated
 */
@ProviderType
public interface SearchFilterPersistence extends BasePersistence<SearchFilter> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SearchFilterUtil} to access the search filter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the search filters where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching search filters
	*/
	public java.util.List<SearchFilter> findByUserId(long userId);

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
	public java.util.List<SearchFilter> findByUserId(long userId, int start,
		int end);

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
	public java.util.List<SearchFilter> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SearchFilter> orderByComparator);

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
	public java.util.List<SearchFilter> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SearchFilter> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first search filter in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching search filter
	* @throws NoSuchSearchFilterException if a matching search filter could not be found
	*/
	public SearchFilter findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<SearchFilter> orderByComparator)
		throws NoSuchSearchFilterException;

	/**
	* Returns the first search filter in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching search filter, or <code>null</code> if a matching search filter could not be found
	*/
	public SearchFilter fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<SearchFilter> orderByComparator);

	/**
	* Returns the last search filter in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching search filter
	* @throws NoSuchSearchFilterException if a matching search filter could not be found
	*/
	public SearchFilter findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<SearchFilter> orderByComparator)
		throws NoSuchSearchFilterException;

	/**
	* Returns the last search filter in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching search filter, or <code>null</code> if a matching search filter could not be found
	*/
	public SearchFilter fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<SearchFilter> orderByComparator);

	/**
	* Returns the search filters before and after the current search filter in the ordered set where userId = &#63;.
	*
	* @param searchFilterId the primary key of the current search filter
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next search filter
	* @throws NoSuchSearchFilterException if a search filter with the primary key could not be found
	*/
	public SearchFilter[] findByUserId_PrevAndNext(long searchFilterId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<SearchFilter> orderByComparator)
		throws NoSuchSearchFilterException;

	/**
	* Removes all the search filters where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of search filters where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching search filters
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the search filters where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @return the matching search filters
	*/
	public java.util.List<SearchFilter> findByU_C(long userId, long classNameId);

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
	public java.util.List<SearchFilter> findByU_C(long userId,
		long classNameId, int start, int end);

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
	public java.util.List<SearchFilter> findByU_C(long userId,
		long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SearchFilter> orderByComparator);

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
	public java.util.List<SearchFilter> findByU_C(long userId,
		long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SearchFilter> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first search filter in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching search filter
	* @throws NoSuchSearchFilterException if a matching search filter could not be found
	*/
	public SearchFilter findByU_C_First(long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<SearchFilter> orderByComparator)
		throws NoSuchSearchFilterException;

	/**
	* Returns the first search filter in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching search filter, or <code>null</code> if a matching search filter could not be found
	*/
	public SearchFilter fetchByU_C_First(long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<SearchFilter> orderByComparator);

	/**
	* Returns the last search filter in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching search filter
	* @throws NoSuchSearchFilterException if a matching search filter could not be found
	*/
	public SearchFilter findByU_C_Last(long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<SearchFilter> orderByComparator)
		throws NoSuchSearchFilterException;

	/**
	* Returns the last search filter in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching search filter, or <code>null</code> if a matching search filter could not be found
	*/
	public SearchFilter fetchByU_C_Last(long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<SearchFilter> orderByComparator);

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
	public SearchFilter[] findByU_C_PrevAndNext(long searchFilterId,
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<SearchFilter> orderByComparator)
		throws NoSuchSearchFilterException;

	/**
	* Removes all the search filters where userId = &#63; and classNameId = &#63; from the database.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	*/
	public void removeByU_C(long userId, long classNameId);

	/**
	* Returns the number of search filters where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @return the number of matching search filters
	*/
	public int countByU_C(long userId, long classNameId);

	/**
	* Caches the search filter in the entity cache if it is enabled.
	*
	* @param searchFilter the search filter
	*/
	public void cacheResult(SearchFilter searchFilter);

	/**
	* Caches the search filters in the entity cache if it is enabled.
	*
	* @param searchFilters the search filters
	*/
	public void cacheResult(java.util.List<SearchFilter> searchFilters);

	/**
	* Creates a new search filter with the primary key. Does not add the search filter to the database.
	*
	* @param searchFilterId the primary key for the new search filter
	* @return the new search filter
	*/
	public SearchFilter create(long searchFilterId);

	/**
	* Removes the search filter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param searchFilterId the primary key of the search filter
	* @return the search filter that was removed
	* @throws NoSuchSearchFilterException if a search filter with the primary key could not be found
	*/
	public SearchFilter remove(long searchFilterId)
		throws NoSuchSearchFilterException;

	public SearchFilter updateImpl(SearchFilter searchFilter);

	/**
	* Returns the search filter with the primary key or throws a {@link NoSuchSearchFilterException} if it could not be found.
	*
	* @param searchFilterId the primary key of the search filter
	* @return the search filter
	* @throws NoSuchSearchFilterException if a search filter with the primary key could not be found
	*/
	public SearchFilter findByPrimaryKey(long searchFilterId)
		throws NoSuchSearchFilterException;

	/**
	* Returns the search filter with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param searchFilterId the primary key of the search filter
	* @return the search filter, or <code>null</code> if a search filter with the primary key could not be found
	*/
	public SearchFilter fetchByPrimaryKey(long searchFilterId);

	@Override
	public java.util.Map<java.io.Serializable, SearchFilter> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the search filters.
	*
	* @return the search filters
	*/
	public java.util.List<SearchFilter> findAll();

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
	public java.util.List<SearchFilter> findAll(int start, int end);

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
	public java.util.List<SearchFilter> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SearchFilter> orderByComparator);

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
	public java.util.List<SearchFilter> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SearchFilter> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the search filters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of search filters.
	*
	* @return the number of search filters
	*/
	public int countAll();
}