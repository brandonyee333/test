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

import com.liferay.osb.exception.SearchFilterNameException;
import com.liferay.osb.model.SearchFilter;
import com.liferay.osb.service.base.SearchFilterLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class SearchFilterLocalServiceImpl
	extends SearchFilterLocalServiceBaseImpl {

	public SearchFilter addSearchFilter(
			long userId, long classNameId, String name, String filter,
			int visibility)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(name);

		long searchFilterId = counterLocalService.increment();

		SearchFilter searchFilter = searchFilterPersistence.create(
			searchFilterId);

		searchFilter.setUserId(user.getUserId());
		searchFilter.setUserName(user.getFullName());
		searchFilter.setCreateDate(now);
		searchFilter.setModifiedDate(now);
		searchFilter.setClassNameId(classNameId);
		searchFilter.setName(name);
		searchFilter.setFilter(filter);
		searchFilter.setVisibility(visibility);

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		searchFilterPersistence.update(searchFilter, serviceContext);

		return searchFilter;
	}

	public void deleteSearchFilters(long userId) {
		searchFilterPersistence.removeByUserId(userId);
	}

	public List<SearchFilter> getSearchFilters(long userId, long classNameId) {
		return searchFilterPersistence.findByU_C(userId, classNameId);
	}

	public SearchFilter updateSearchFilter(
			long searchFilterId, String name, String filter, int visibility)
		throws PortalException {

		validate(name);

		SearchFilter searchFilter = searchFilterPersistence.findByPrimaryKey(
			searchFilterId);

		searchFilter.setModifiedDate(new Date());
		searchFilter.setName(name);
		searchFilter.setFilter(filter);
		searchFilter.setVisibility(visibility);

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		searchFilterPersistence.update(searchFilter, serviceContext);

		return searchFilter;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new SearchFilterNameException();
		}
	}

}