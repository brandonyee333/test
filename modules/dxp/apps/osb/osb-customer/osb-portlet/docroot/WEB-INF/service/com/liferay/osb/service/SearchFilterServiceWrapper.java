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
 * This class is a wrapper for {@link SearchFilterService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SearchFilterService
 * @generated
 */
public class SearchFilterServiceWrapper implements SearchFilterService,
	ServiceWrapper<SearchFilterService> {
	public SearchFilterServiceWrapper(SearchFilterService searchFilterService) {
		_searchFilterService = searchFilterService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _searchFilterService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_searchFilterService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _searchFilterService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.osb.model.SearchFilter addSearchFilter(long userId,
		long classNameId, java.lang.String name, java.lang.String filter,
		int visibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterService.addSearchFilter(userId, classNameId, name,
			filter, visibility);
	}

	public void deleteSearchFilter(long searchFilterId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_searchFilterService.deleteSearchFilter(searchFilterId);
	}

	public com.liferay.osb.model.SearchFilter getSearchFilter(
		long searchFilterId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterService.getSearchFilter(searchFilterId);
	}

	public com.liferay.osb.model.SearchFilter updateSearchFilter(
		long searchFilterId, java.lang.String name, java.lang.String filter,
		int visibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchFilterService.updateSearchFilter(searchFilterId, name,
			filter, visibility);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SearchFilterService getWrappedSearchFilterService() {
		return _searchFilterService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSearchFilterService(
		SearchFilterService searchFilterService) {
		_searchFilterService = searchFilterService;
	}

	public SearchFilterService getWrappedService() {
		return _searchFilterService;
	}

	public void setWrappedService(SearchFilterService searchFilterService) {
		_searchFilterService = searchFilterService;
	}

	private SearchFilterService _searchFilterService;
}