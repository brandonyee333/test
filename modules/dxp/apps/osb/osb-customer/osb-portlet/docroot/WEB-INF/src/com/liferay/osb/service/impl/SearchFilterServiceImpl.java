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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.SearchFilter;
import com.liferay.osb.service.base.SearchFilterServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.auth.PrincipalException;

import java.util.LinkedHashMap;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class SearchFilterServiceImpl extends SearchFilterServiceBaseImpl {

	public SearchFilter addSearchFilter(
			long userId, long classNameId, String name, String filter,
			int visibility)
		throws PortalException, SystemException {

		LinkedHashMap<String, Object> userParams =
			new LinkedHashMap<String, Object>();

		userParams.put("partnerWorker", new Long(getUserId()));

		if ((accountEntryLocalService.searchCount(null, userParams) <= 0) &&
			!organizationLocalService.hasUserOrganization(
				getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			throw new PrincipalException();
		}

		return searchFilterLocalService.addSearchFilter(
			userId, classNameId, name, filter, visibility);
	}

	public void deleteSearchFilter(long searchFilterId)
		throws PortalException, SystemException {

		SearchFilter searchFilter = searchFilterLocalService.getSearchFilter(
			searchFilterId);

		if (searchFilter.getUserId() != getUserId()) {
			throw new PrincipalException();
		}

		searchFilterLocalService.deleteSearchFilter(searchFilter);
	}

	public SearchFilter getSearchFilter(long searchFilterId)
		throws PortalException, SystemException {

		SearchFilter searchFilter = searchFilterLocalService.getSearchFilter(
			searchFilterId);

		if ((searchFilter.getUserId() != getUserId()) &&
			(searchFilter.getVisibility() != VisibilityConstants.PUBLIC)) {

			throw new PrincipalException();
		}

		return searchFilter;
	}

	public SearchFilter updateSearchFilter(
			long searchFilterId, String name, String filter, int visibility)
		throws PortalException, SystemException {

		SearchFilter searchFilter = searchFilterLocalService.getSearchFilter(
			searchFilterId);

		if (searchFilter.getUserId() != getUserId()) {
			throw new PrincipalException();
		}

		return searchFilterLocalService.updateSearchFilter(
			searchFilterId, name, filter, visibility);
	}

}