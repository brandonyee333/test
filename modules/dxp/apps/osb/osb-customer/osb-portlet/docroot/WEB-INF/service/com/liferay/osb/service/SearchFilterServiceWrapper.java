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
 * Provides a wrapper for {@link SearchFilterService}.
 *
 * @author Brian Wing Shun Chan
 * @see SearchFilterService
 * @generated
 */
@ProviderType
public class SearchFilterServiceWrapper implements SearchFilterService,
	ServiceWrapper<SearchFilterService> {
	public SearchFilterServiceWrapper(SearchFilterService searchFilterService) {
		_searchFilterService = searchFilterService;
	}

	@Override
	public com.liferay.osb.model.SearchFilter addSearchFilter(long userId,
		long classNameId, java.lang.String name, java.lang.String filter,
		int visibility)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _searchFilterService.addSearchFilter(userId, classNameId, name,
			filter, visibility);
	}

	@Override
	public com.liferay.osb.model.SearchFilter getSearchFilter(
		long searchFilterId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _searchFilterService.getSearchFilter(searchFilterId);
	}

	@Override
	public com.liferay.osb.model.SearchFilter updateSearchFilter(
		long searchFilterId, java.lang.String name, java.lang.String filter,
		int visibility)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _searchFilterService.updateSearchFilter(searchFilterId, name,
			filter, visibility);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _searchFilterService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _searchFilterService.getOSGiServiceIdentifier();
	}

	@Override
	public void deleteSearchFilter(long searchFilterId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_searchFilterService.deleteSearchFilter(searchFilterId);
	}

	@Override
	public SearchFilterService getWrappedService() {
		return _searchFilterService;
	}

	@Override
	public void setWrappedService(SearchFilterService searchFilterService) {
		_searchFilterService = searchFilterService;
	}

	private SearchFilterService _searchFilterService;
}