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

package com.liferay.osb.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SearchFilterLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SearchFilterLocalService
 * @generated
 */
public class SearchFilterLocalServiceWrapper implements SearchFilterLocalService,
	ServiceWrapper<SearchFilterLocalService> {
	public SearchFilterLocalServiceWrapper(
		SearchFilterLocalService searchFilterLocalService) {
		_searchFilterLocalService = searchFilterLocalService;
	}

	/**
	* Adds the search filter to the database. Also notifies the appropriate model listeners.
	*
	* @param searchFilter the search filter
	* @return the search filter that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SearchFilter addSearchFilter(
		com.liferay.osb.model.SearchFilter searchFilter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.addSearchFilter(searchFilter);
	}

	/**
	* Creates a new search filter with the primary key. Does not add the search filter to the database.
	*
	* @param searchFilterId the primary key for the new search filter
	* @return the new search filter
	*/
	public com.liferay.osb.model.SearchFilter createSearchFilter(
		long searchFilterId) {
		return _searchFilterLocalService.createSearchFilter(searchFilterId);
	}

	/**
	* Deletes the search filter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param searchFilterId the primary key of the search filter
	* @return the search filter that was removed
	* @throws PortalException if a search filter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SearchFilter deleteSearchFilter(
		long searchFilterId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.deleteSearchFilter(searchFilterId);
	}

	/**
	* Deletes the search filter from the database. Also notifies the appropriate model listeners.
	*
	* @param searchFilter the search filter
	* @return the search filter that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SearchFilter deleteSearchFilter(
		com.liferay.osb.model.SearchFilter searchFilter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.deleteSearchFilter(searchFilter);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _searchFilterLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.SearchFilter fetchSearchFilter(
		long searchFilterId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.fetchSearchFilter(searchFilterId);
	}

	/**
	* Returns the search filter with the primary key.
	*
	* @param searchFilterId the primary key of the search filter
	* @return the search filter
	* @throws PortalException if a search filter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SearchFilter getSearchFilter(
		long searchFilterId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.getSearchFilter(searchFilterId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.liferay.osb.model.SearchFilter> getSearchFilters(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.getSearchFilters(start, end);
	}

	/**
	* Returns the number of search filters.
	*
	* @return the number of search filters
	* @throws SystemException if a system exception occurred
	*/
	public int getSearchFiltersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.getSearchFiltersCount();
	}

	/**
	* Updates the search filter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param searchFilter the search filter
	* @return the search filter that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SearchFilter updateSearchFilter(
		com.liferay.osb.model.SearchFilter searchFilter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.updateSearchFilter(searchFilter);
	}

	/**
	* Updates the search filter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param searchFilter the search filter
	* @param merge whether to merge the search filter with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the search filter that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SearchFilter updateSearchFilter(
		com.liferay.osb.model.SearchFilter searchFilter, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.updateSearchFilter(searchFilter, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _searchFilterLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_searchFilterLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _searchFilterLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.SearchFilter addSearchFilter(long userId,
		long classNameId, java.lang.String name, java.lang.String filter,
		int visibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.addSearchFilter(userId, classNameId,
			name, filter, visibility);
	}

	public void deleteSearchFilters(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_searchFilterLocalService.deleteSearchFilters(userId);
	}

	public java.util.List<com.liferay.osb.model.SearchFilter> getSearchFilters(
		long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.getSearchFilters(userId, classNameId);
	}

	public com.liferay.osb.model.SearchFilter updateSearchFilter(
		long searchFilterId, java.lang.String name, java.lang.String filter,
		int visibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterLocalService.updateSearchFilter(searchFilterId,
			name, filter, visibility);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SearchFilterLocalService getWrappedSearchFilterLocalService() {
		return _searchFilterLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSearchFilterLocalService(
		SearchFilterLocalService searchFilterLocalService) {
		_searchFilterLocalService = searchFilterLocalService;
	}

	public SearchFilterLocalService getWrappedService() {
		return _searchFilterLocalService;
	}

	public void setWrappedService(
		SearchFilterLocalService searchFilterLocalService) {
		_searchFilterLocalService = searchFilterLocalService;
	}

	private SearchFilterLocalService _searchFilterLocalService;
}