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

package com.liferay.osb.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SearchFilter}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SearchFilter
 * @generated
 */
public class SearchFilterWrapper implements SearchFilter,
	ModelWrapper<SearchFilter> {
	public SearchFilterWrapper(SearchFilter searchFilter) {
		_searchFilter = searchFilter;
	}

	public Class<?> getModelClass() {
		return SearchFilter.class;
	}

	public String getModelClassName() {
		return SearchFilter.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("searchFilterId", getSearchFilterId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("name", getName());
		attributes.put("filter", getFilter());
		attributes.put("visibility", getVisibility());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long searchFilterId = (Long)attributes.get("searchFilterId");

		if (searchFilterId != null) {
			setSearchFilterId(searchFilterId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String filter = (String)attributes.get("filter");

		if (filter != null) {
			setFilter(filter);
		}

		Integer visibility = (Integer)attributes.get("visibility");

		if (visibility != null) {
			setVisibility(visibility);
		}
	}

	/**
	* Returns the primary key of this search filter.
	*
	* @return the primary key of this search filter
	*/
	public long getPrimaryKey() {
		return _searchFilter.getPrimaryKey();
	}

	/**
	* Sets the primary key of this search filter.
	*
	* @param primaryKey the primary key of this search filter
	*/
	public void setPrimaryKey(long primaryKey) {
		_searchFilter.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the search filter ID of this search filter.
	*
	* @return the search filter ID of this search filter
	*/
	public long getSearchFilterId() {
		return _searchFilter.getSearchFilterId();
	}

	/**
	* Sets the search filter ID of this search filter.
	*
	* @param searchFilterId the search filter ID of this search filter
	*/
	public void setSearchFilterId(long searchFilterId) {
		_searchFilter.setSearchFilterId(searchFilterId);
	}

	/**
	* Returns the user ID of this search filter.
	*
	* @return the user ID of this search filter
	*/
	public long getUserId() {
		return _searchFilter.getUserId();
	}

	/**
	* Sets the user ID of this search filter.
	*
	* @param userId the user ID of this search filter
	*/
	public void setUserId(long userId) {
		_searchFilter.setUserId(userId);
	}

	/**
	* Returns the user uuid of this search filter.
	*
	* @return the user uuid of this search filter
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchFilter.getUserUuid();
	}

	/**
	* Sets the user uuid of this search filter.
	*
	* @param userUuid the user uuid of this search filter
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_searchFilter.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this search filter.
	*
	* @return the user name of this search filter
	*/
	public java.lang.String getUserName() {
		return _searchFilter.getUserName();
	}

	/**
	* Sets the user name of this search filter.
	*
	* @param userName the user name of this search filter
	*/
	public void setUserName(java.lang.String userName) {
		_searchFilter.setUserName(userName);
	}

	/**
	* Returns the create date of this search filter.
	*
	* @return the create date of this search filter
	*/
	public java.util.Date getCreateDate() {
		return _searchFilter.getCreateDate();
	}

	/**
	* Sets the create date of this search filter.
	*
	* @param createDate the create date of this search filter
	*/
	public void setCreateDate(java.util.Date createDate) {
		_searchFilter.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this search filter.
	*
	* @return the modified date of this search filter
	*/
	public java.util.Date getModifiedDate() {
		return _searchFilter.getModifiedDate();
	}

	/**
	* Sets the modified date of this search filter.
	*
	* @param modifiedDate the modified date of this search filter
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_searchFilter.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the fully qualified class name of this search filter.
	*
	* @return the fully qualified class name of this search filter
	*/
	public java.lang.String getClassName() {
		return _searchFilter.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_searchFilter.setClassName(className);
	}

	/**
	* Returns the class name ID of this search filter.
	*
	* @return the class name ID of this search filter
	*/
	public long getClassNameId() {
		return _searchFilter.getClassNameId();
	}

	/**
	* Sets the class name ID of this search filter.
	*
	* @param classNameId the class name ID of this search filter
	*/
	public void setClassNameId(long classNameId) {
		_searchFilter.setClassNameId(classNameId);
	}

	/**
	* Returns the name of this search filter.
	*
	* @return the name of this search filter
	*/
	public java.lang.String getName() {
		return _searchFilter.getName();
	}

	/**
	* Sets the name of this search filter.
	*
	* @param name the name of this search filter
	*/
	public void setName(java.lang.String name) {
		_searchFilter.setName(name);
	}

	/**
	* Returns the filter of this search filter.
	*
	* @return the filter of this search filter
	*/
	public java.lang.String getFilter() {
		return _searchFilter.getFilter();
	}

	/**
	* Sets the filter of this search filter.
	*
	* @param filter the filter of this search filter
	*/
	public void setFilter(java.lang.String filter) {
		_searchFilter.setFilter(filter);
	}

	/**
	* Returns the visibility of this search filter.
	*
	* @return the visibility of this search filter
	*/
	public int getVisibility() {
		return _searchFilter.getVisibility();
	}

	/**
	* Sets the visibility of this search filter.
	*
	* @param visibility the visibility of this search filter
	*/
	public void setVisibility(int visibility) {
		_searchFilter.setVisibility(visibility);
	}

	public boolean isNew() {
		return _searchFilter.isNew();
	}

	public void setNew(boolean n) {
		_searchFilter.setNew(n);
	}

	public boolean isCachedModel() {
		return _searchFilter.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_searchFilter.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _searchFilter.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _searchFilter.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_searchFilter.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _searchFilter.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_searchFilter.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SearchFilterWrapper((SearchFilter)_searchFilter.clone());
	}

	public int compareTo(com.liferay.osb.model.SearchFilter searchFilter) {
		return _searchFilter.compareTo(searchFilter);
	}

	@Override
	public int hashCode() {
		return _searchFilter.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.SearchFilter> toCacheModel() {
		return _searchFilter.toCacheModel();
	}

	public com.liferay.osb.model.SearchFilter toEscapedModel() {
		return new SearchFilterWrapper(_searchFilter.toEscapedModel());
	}

	public com.liferay.osb.model.SearchFilter toUnescapedModel() {
		return new SearchFilterWrapper(_searchFilter.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _searchFilter.toString();
	}

	public java.lang.String toXmlString() {
		return _searchFilter.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_searchFilter.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SearchFilterWrapper)) {
			return false;
		}

		SearchFilterWrapper searchFilterWrapper = (SearchFilterWrapper)obj;

		if (Validator.equals(_searchFilter, searchFilterWrapper._searchFilter)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public SearchFilter getWrappedSearchFilter() {
		return _searchFilter;
	}

	public SearchFilter getWrappedModel() {
		return _searchFilter;
	}

	public void resetOriginalValues() {
		_searchFilter.resetOriginalValues();
	}

	private SearchFilter _searchFilter;
}