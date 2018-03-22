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

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link SearchFilter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SearchFilter
 * @generated
 */
@ProviderType
public class SearchFilterWrapper implements SearchFilter,
	ModelWrapper<SearchFilter> {
	public SearchFilterWrapper(SearchFilter searchFilter) {
		_searchFilter = searchFilter;
	}

	@Override
	public Class<?> getModelClass() {
		return SearchFilter.class;
	}

	@Override
	public String getModelClassName() {
		return SearchFilter.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public SearchFilter toEscapedModel() {
		return new SearchFilterWrapper(_searchFilter.toEscapedModel());
	}

	@Override
	public SearchFilter toUnescapedModel() {
		return new SearchFilterWrapper(_searchFilter.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _searchFilter.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _searchFilter.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _searchFilter.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _searchFilter.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SearchFilter> toCacheModel() {
		return _searchFilter.toCacheModel();
	}

	@Override
	public int compareTo(SearchFilter searchFilter) {
		return _searchFilter.compareTo(searchFilter);
	}

	/**
	* Returns the visibility of this search filter.
	*
	* @return the visibility of this search filter
	*/
	@Override
	public int getVisibility() {
		return _searchFilter.getVisibility();
	}

	@Override
	public int hashCode() {
		return _searchFilter.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _searchFilter.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SearchFilterWrapper((SearchFilter)_searchFilter.clone());
	}

	/**
	* Returns the fully qualified class name of this search filter.
	*
	* @return the fully qualified class name of this search filter
	*/
	@Override
	public java.lang.String getClassName() {
		return _searchFilter.getClassName();
	}

	/**
	* Returns the filter of this search filter.
	*
	* @return the filter of this search filter
	*/
	@Override
	public java.lang.String getFilter() {
		return _searchFilter.getFilter();
	}

	/**
	* Returns the name of this search filter.
	*
	* @return the name of this search filter
	*/
	@Override
	public java.lang.String getName() {
		return _searchFilter.getName();
	}

	/**
	* Returns the user name of this search filter.
	*
	* @return the user name of this search filter
	*/
	@Override
	public java.lang.String getUserName() {
		return _searchFilter.getUserName();
	}

	/**
	* Returns the user uuid of this search filter.
	*
	* @return the user uuid of this search filter
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _searchFilter.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _searchFilter.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _searchFilter.toXmlString();
	}

	/**
	* Returns the create date of this search filter.
	*
	* @return the create date of this search filter
	*/
	@Override
	public Date getCreateDate() {
		return _searchFilter.getCreateDate();
	}

	/**
	* Returns the modified date of this search filter.
	*
	* @return the modified date of this search filter
	*/
	@Override
	public Date getModifiedDate() {
		return _searchFilter.getModifiedDate();
	}

	/**
	* Returns the class name ID of this search filter.
	*
	* @return the class name ID of this search filter
	*/
	@Override
	public long getClassNameId() {
		return _searchFilter.getClassNameId();
	}

	/**
	* Returns the primary key of this search filter.
	*
	* @return the primary key of this search filter
	*/
	@Override
	public long getPrimaryKey() {
		return _searchFilter.getPrimaryKey();
	}

	/**
	* Returns the search filter ID of this search filter.
	*
	* @return the search filter ID of this search filter
	*/
	@Override
	public long getSearchFilterId() {
		return _searchFilter.getSearchFilterId();
	}

	/**
	* Returns the user ID of this search filter.
	*
	* @return the user ID of this search filter
	*/
	@Override
	public long getUserId() {
		return _searchFilter.getUserId();
	}

	@Override
	public void persist() {
		_searchFilter.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_searchFilter.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_searchFilter.setClassName(className);
	}

	/**
	* Sets the class name ID of this search filter.
	*
	* @param classNameId the class name ID of this search filter
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_searchFilter.setClassNameId(classNameId);
	}

	/**
	* Sets the create date of this search filter.
	*
	* @param createDate the create date of this search filter
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_searchFilter.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_searchFilter.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_searchFilter.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_searchFilter.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the filter of this search filter.
	*
	* @param filter the filter of this search filter
	*/
	@Override
	public void setFilter(java.lang.String filter) {
		_searchFilter.setFilter(filter);
	}

	/**
	* Sets the modified date of this search filter.
	*
	* @param modifiedDate the modified date of this search filter
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_searchFilter.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this search filter.
	*
	* @param name the name of this search filter
	*/
	@Override
	public void setName(java.lang.String name) {
		_searchFilter.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_searchFilter.setNew(n);
	}

	/**
	* Sets the primary key of this search filter.
	*
	* @param primaryKey the primary key of this search filter
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_searchFilter.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_searchFilter.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the search filter ID of this search filter.
	*
	* @param searchFilterId the search filter ID of this search filter
	*/
	@Override
	public void setSearchFilterId(long searchFilterId) {
		_searchFilter.setSearchFilterId(searchFilterId);
	}

	/**
	* Sets the user ID of this search filter.
	*
	* @param userId the user ID of this search filter
	*/
	@Override
	public void setUserId(long userId) {
		_searchFilter.setUserId(userId);
	}

	/**
	* Sets the user name of this search filter.
	*
	* @param userName the user name of this search filter
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_searchFilter.setUserName(userName);
	}

	/**
	* Sets the user uuid of this search filter.
	*
	* @param userUuid the user uuid of this search filter
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_searchFilter.setUserUuid(userUuid);
	}

	/**
	* Sets the visibility of this search filter.
	*
	* @param visibility the visibility of this search filter
	*/
	@Override
	public void setVisibility(int visibility) {
		_searchFilter.setVisibility(visibility);
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

		if (Objects.equals(_searchFilter, searchFilterWrapper._searchFilter)) {
			return true;
		}

		return false;
	}

	@Override
	public SearchFilter getWrappedModel() {
		return _searchFilter;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _searchFilter.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _searchFilter.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_searchFilter.resetOriginalValues();
	}

	private final SearchFilter _searchFilter;
}