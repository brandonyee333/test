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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.SearchFilterServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.SearchFilterServiceSoap
 * @generated
 */
@ProviderType
public class SearchFilterSoap implements Serializable {
	public static SearchFilterSoap toSoapModel(SearchFilter model) {
		SearchFilterSoap soapModel = new SearchFilterSoap();

		soapModel.setSearchFilterId(model.getSearchFilterId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setName(model.getName());
		soapModel.setFilter(model.getFilter());
		soapModel.setVisibility(model.getVisibility());

		return soapModel;
	}

	public static SearchFilterSoap[] toSoapModels(SearchFilter[] models) {
		SearchFilterSoap[] soapModels = new SearchFilterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SearchFilterSoap[][] toSoapModels(SearchFilter[][] models) {
		SearchFilterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SearchFilterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SearchFilterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SearchFilterSoap[] toSoapModels(List<SearchFilter> models) {
		List<SearchFilterSoap> soapModels = new ArrayList<SearchFilterSoap>(models.size());

		for (SearchFilter model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SearchFilterSoap[soapModels.size()]);
	}

	public SearchFilterSoap() {
	}

	public long getPrimaryKey() {
		return _searchFilterId;
	}

	public void setPrimaryKey(long pk) {
		setSearchFilterId(pk);
	}

	public long getSearchFilterId() {
		return _searchFilterId;
	}

	public void setSearchFilterId(long searchFilterId) {
		_searchFilterId = searchFilterId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getFilter() {
		return _filter;
	}

	public void setFilter(String filter) {
		_filter = filter;
	}

	public int getVisibility() {
		return _visibility;
	}

	public void setVisibility(int visibility) {
		_visibility = visibility;
	}

	private long _searchFilterId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private String _name;
	private String _filter;
	private int _visibility;
}