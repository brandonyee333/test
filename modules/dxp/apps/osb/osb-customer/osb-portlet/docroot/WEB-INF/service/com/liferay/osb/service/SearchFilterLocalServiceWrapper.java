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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SearchFilterLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SearchFilterLocalService
 * @generated
 */
@ProviderType
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
	*/
	@Override
	public com.liferay.osb.model.SearchFilter addSearchFilter(
		com.liferay.osb.model.SearchFilter searchFilter) {
		return _searchFilterLocalService.addSearchFilter(searchFilter);
	}

	@Override
	public com.liferay.osb.model.SearchFilter addSearchFilter(long userId,
		long classNameId, java.lang.String name, java.lang.String filter,
		int visibility)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _searchFilterLocalService.addSearchFilter(userId, classNameId,
			name, filter, visibility);
	}

	/**
	* Creates a new search filter with the primary key. Does not add the search filter to the database.
	*
	* @param searchFilterId the primary key for the new search filter
	* @return the new search filter
	*/
	@Override
	public com.liferay.osb.model.SearchFilter createSearchFilter(
		long searchFilterId) {
		return _searchFilterLocalService.createSearchFilter(searchFilterId);
	}

	/**
	* Deletes the search filter from the database. Also notifies the appropriate model listeners.
	*
	* @param searchFilter the search filter
	* @return the search filter that was removed
	*/
	@Override
	public com.liferay.osb.model.SearchFilter deleteSearchFilter(
		com.liferay.osb.model.SearchFilter searchFilter) {
		return _searchFilterLocalService.deleteSearchFilter(searchFilter);
	}

	/**
	* Deletes the search filter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param searchFilterId the primary key of the search filter
	* @return the search filter that was removed
	* @throws PortalException if a search filter with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SearchFilter deleteSearchFilter(
		long searchFilterId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _searchFilterLocalService.deleteSearchFilter(searchFilterId);
	}

	@Override
	public com.liferay.osb.model.SearchFilter fetchSearchFilter(
		long searchFilterId) {
		return _searchFilterLocalService.fetchSearchFilter(searchFilterId);
	}

	/**
	* Returns the search filter with the primary key.
	*
	* @param searchFilterId the primary key of the search filter
	* @return the search filter
	* @throws PortalException if a search filter with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SearchFilter getSearchFilter(
		long searchFilterId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _searchFilterLocalService.getSearchFilter(searchFilterId);
	}

	/**
	* Updates the search filter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param searchFilter the search filter
	* @return the search filter that was updated
	*/
	@Override
	public com.liferay.osb.model.SearchFilter updateSearchFilter(
		com.liferay.osb.model.SearchFilter searchFilter) {
		return _searchFilterLocalService.updateSearchFilter(searchFilter);
	}

	@Override
	public com.liferay.osb.model.SearchFilter updateSearchFilter(
		long searchFilterId, java.lang.String name, java.lang.String filter,
		int visibility)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _searchFilterLocalService.updateSearchFilter(searchFilterId,
			name, filter, visibility);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _searchFilterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _searchFilterLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _searchFilterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _searchFilterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _searchFilterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of search filters.
	*
	* @return the number of search filters
	*/
	@Override
	public int getSearchFiltersCount() {
		return _searchFilterLocalService.getSearchFiltersCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _searchFilterLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _searchFilterLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _searchFilterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _searchFilterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _searchFilterLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the search filters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @return the range of search filters
	*/
	@Override
	public java.util.List<com.liferay.osb.model.SearchFilter> getSearchFilters(
		int start, int end) {
		return _searchFilterLocalService.getSearchFilters(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SearchFilter> getSearchFilters(
		long userId, long classNameId) {
		return _searchFilterLocalService.getSearchFilters(userId, classNameId);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _searchFilterLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _searchFilterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteSearchFilters(long userId) {
		_searchFilterLocalService.deleteSearchFilters(userId);
	}

	@Override
	public SearchFilterLocalService getWrappedService() {
		return _searchFilterLocalService;
	}

	@Override
	public void setWrappedService(
		SearchFilterLocalService searchFilterLocalService) {
		_searchFilterLocalService = searchFilterLocalService;
	}

	private SearchFilterLocalService _searchFilterLocalService;
}