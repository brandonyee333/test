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

import com.liferay.osb.service.ClpSerializer;
import com.liferay.osb.service.SearchFilterLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class SearchFilterClp extends BaseModelImpl<SearchFilter>
	implements SearchFilter {
	public SearchFilterClp() {
	}

	public Class<?> getModelClass() {
		return SearchFilter.class;
	}

	public String getModelClassName() {
		return SearchFilter.class.getName();
	}

	public long getPrimaryKey() {
		return _searchFilterId;
	}

	public void setPrimaryKey(long primaryKey) {
		setSearchFilterId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_searchFilterId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	public long getSearchFilterId() {
		return _searchFilterId;
	}

	public void setSearchFilterId(long searchFilterId) {
		_searchFilterId = searchFilterId;

		if (_searchFilterRemoteModel != null) {
			try {
				Class<?> clazz = _searchFilterRemoteModel.getClass();

				Method method = clazz.getMethod("setSearchFilterId", long.class);

				method.invoke(_searchFilterRemoteModel, searchFilterId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;

		if (_searchFilterRemoteModel != null) {
			try {
				Class<?> clazz = _searchFilterRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_searchFilterRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;

		if (_searchFilterRemoteModel != null) {
			try {
				Class<?> clazz = _searchFilterRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_searchFilterRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_searchFilterRemoteModel != null) {
			try {
				Class<?> clazz = _searchFilterRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_searchFilterRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_searchFilterRemoteModel != null) {
			try {
				Class<?> clazz = _searchFilterRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_searchFilterRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;

		if (_searchFilterRemoteModel != null) {
			try {
				Class<?> clazz = _searchFilterRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_searchFilterRemoteModel, classNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;

		if (_searchFilterRemoteModel != null) {
			try {
				Class<?> clazz = _searchFilterRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_searchFilterRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getFilter() {
		return _filter;
	}

	public void setFilter(String filter) {
		_filter = filter;

		if (_searchFilterRemoteModel != null) {
			try {
				Class<?> clazz = _searchFilterRemoteModel.getClass();

				Method method = clazz.getMethod("setFilter", String.class);

				method.invoke(_searchFilterRemoteModel, filter);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getVisibility() {
		return _visibility;
	}

	public void setVisibility(int visibility) {
		_visibility = visibility;

		if (_searchFilterRemoteModel != null) {
			try {
				Class<?> clazz = _searchFilterRemoteModel.getClass();

				Method method = clazz.getMethod("setVisibility", int.class);

				method.invoke(_searchFilterRemoteModel, visibility);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSearchFilterRemoteModel() {
		return _searchFilterRemoteModel;
	}

	public void setSearchFilterRemoteModel(BaseModel<?> searchFilterRemoteModel) {
		_searchFilterRemoteModel = searchFilterRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _searchFilterRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_searchFilterRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			SearchFilterLocalServiceUtil.addSearchFilter(this);
		}
		else {
			SearchFilterLocalServiceUtil.updateSearchFilter(this);
		}
	}

	@Override
	public SearchFilter toEscapedModel() {
		return (SearchFilter)ProxyUtil.newProxyInstance(SearchFilter.class.getClassLoader(),
			new Class[] { SearchFilter.class }, new AutoEscapeBeanHandler(this));
	}

	public SearchFilter toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		SearchFilterClp clone = new SearchFilterClp();

		clone.setSearchFilterId(getSearchFilterId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setClassNameId(getClassNameId());
		clone.setName(getName());
		clone.setFilter(getFilter());
		clone.setVisibility(getVisibility());

		return clone;
	}

	public int compareTo(SearchFilter searchFilter) {
		int value = 0;

		value = getName().toLowerCase()
					.compareTo(searchFilter.getName().toLowerCase());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SearchFilterClp)) {
			return false;
		}

		SearchFilterClp searchFilter = (SearchFilterClp)obj;

		long primaryKey = searchFilter.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{searchFilterId=");
		sb.append(getSearchFilterId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", filter=");
		sb.append(getFilter());
		sb.append(", visibility=");
		sb.append(getVisibility());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.SearchFilter");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>searchFilterId</column-name><column-value><![CDATA[");
		sb.append(getSearchFilterId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>filter</column-name><column-value><![CDATA[");
		sb.append(getFilter());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>visibility</column-name><column-value><![CDATA[");
		sb.append(getVisibility());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _searchFilterId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private String _name;
	private String _filter;
	private int _visibility;
	private BaseModel<?> _searchFilterRemoteModel;
}