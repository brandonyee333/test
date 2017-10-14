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

package com.liferay.watson.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.watson.service.ClpSerializer;
import com.liferay.watson.service.WatsonRelationshipLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class WatsonRelationshipClp extends BaseModelImpl<WatsonRelationship>
	implements WatsonRelationship {
	public WatsonRelationshipClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonRelationship.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonRelationship.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _watsonRelationshipId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setWatsonRelationshipId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonRelationshipId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonRelationshipId", getWatsonRelationshipId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("watsonIncidentId", getWatsonIncidentId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("classNameId1", getClassNameId1());
		attributes.put("classPK1", getClassPK1());
		attributes.put("classNameId2", getClassNameId2());
		attributes.put("classPK2", getClassPK2());
		attributes.put("description", getDescription());
		attributes.put("status", getStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonRelationshipId = (Long)attributes.get("watsonRelationshipId");

		if (watsonRelationshipId != null) {
			setWatsonRelationshipId(watsonRelationshipId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

		Long watsonIncidentId = (Long)attributes.get("watsonIncidentId");

		if (watsonIncidentId != null) {
			setWatsonIncidentId(watsonIncidentId);
		}

		Long typeWatsonListTypeId = (Long)attributes.get("typeWatsonListTypeId");

		if (typeWatsonListTypeId != null) {
			setTypeWatsonListTypeId(typeWatsonListTypeId);
		}

		Long classNameId1 = (Long)attributes.get("classNameId1");

		if (classNameId1 != null) {
			setClassNameId1(classNameId1);
		}

		Long classPK1 = (Long)attributes.get("classPK1");

		if (classPK1 != null) {
			setClassPK1(classPK1);
		}

		Long classNameId2 = (Long)attributes.get("classNameId2");

		if (classNameId2 != null) {
			setClassNameId2(classNameId2);
		}

		Long classPK2 = (Long)attributes.get("classPK2");

		if (classPK2 != null) {
			setClassPK2(classPK2);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getWatsonRelationshipId() {
		return _watsonRelationshipId;
	}

	@Override
	public void setWatsonRelationshipId(long watsonRelationshipId) {
		_watsonRelationshipId = watsonRelationshipId;

		if (_watsonRelationshipRemoteModel != null) {
			try {
				Class<?> clazz = _watsonRelationshipRemoteModel.getClass();

				Method method = clazz.getMethod("setWatsonRelationshipId",
						long.class);

				method.invoke(_watsonRelationshipRemoteModel,
					watsonRelationshipId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_watsonRelationshipRemoteModel != null) {
			try {
				Class<?> clazz = _watsonRelationshipRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_watsonRelationshipRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_watsonRelationshipRemoteModel != null) {
			try {
				Class<?> clazz = _watsonRelationshipRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_watsonRelationshipRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_watsonRelationshipRemoteModel != null) {
			try {
				Class<?> clazz = _watsonRelationshipRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_watsonRelationshipRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_watsonRelationshipRemoteModel != null) {
			try {
				Class<?> clazz = _watsonRelationshipRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_watsonRelationshipRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_watsonRelationshipRemoteModel != null) {
			try {
				Class<?> clazz = _watsonRelationshipRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_watsonRelationshipRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getWatsonIncidentId() {
		return _watsonIncidentId;
	}

	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonIncidentId = watsonIncidentId;

		if (_watsonRelationshipRemoteModel != null) {
			try {
				Class<?> clazz = _watsonRelationshipRemoteModel.getClass();

				Method method = clazz.getMethod("setWatsonIncidentId",
						long.class);

				method.invoke(_watsonRelationshipRemoteModel, watsonIncidentId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getTypeWatsonListTypeId() {
		return _typeWatsonListTypeId;
	}

	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_typeWatsonListTypeId = typeWatsonListTypeId;

		if (_watsonRelationshipRemoteModel != null) {
			try {
				Class<?> clazz = _watsonRelationshipRemoteModel.getClass();

				Method method = clazz.getMethod("setTypeWatsonListTypeId",
						long.class);

				method.invoke(_watsonRelationshipRemoteModel,
					typeWatsonListTypeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getClassNameId1() {
		return _classNameId1;
	}

	@Override
	public void setClassNameId1(long classNameId1) {
		_classNameId1 = classNameId1;

		if (_watsonRelationshipRemoteModel != null) {
			try {
				Class<?> clazz = _watsonRelationshipRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId1", long.class);

				method.invoke(_watsonRelationshipRemoteModel, classNameId1);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getClassPK1() {
		return _classPK1;
	}

	@Override
	public void setClassPK1(long classPK1) {
		_classPK1 = classPK1;

		if (_watsonRelationshipRemoteModel != null) {
			try {
				Class<?> clazz = _watsonRelationshipRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK1", long.class);

				method.invoke(_watsonRelationshipRemoteModel, classPK1);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getClassNameId2() {
		return _classNameId2;
	}

	@Override
	public void setClassNameId2(long classNameId2) {
		_classNameId2 = classNameId2;

		if (_watsonRelationshipRemoteModel != null) {
			try {
				Class<?> clazz = _watsonRelationshipRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId2", long.class);

				method.invoke(_watsonRelationshipRemoteModel, classNameId2);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getClassPK2() {
		return _classPK2;
	}

	@Override
	public void setClassPK2(long classPK2) {
		_classPK2 = classPK2;

		if (_watsonRelationshipRemoteModel != null) {
			try {
				Class<?> clazz = _watsonRelationshipRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK2", long.class);

				method.invoke(_watsonRelationshipRemoteModel, classPK2);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public void setDescription(String description) {
		_description = description;

		if (_watsonRelationshipRemoteModel != null) {
			try {
				Class<?> clazz = _watsonRelationshipRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_watsonRelationshipRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;

		if (_watsonRelationshipRemoteModel != null) {
			try {
				Class<?> clazz = _watsonRelationshipRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_watsonRelationshipRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getWatsonRelationshipRemoteModel() {
		return _watsonRelationshipRemoteModel;
	}

	public void setWatsonRelationshipRemoteModel(
		BaseModel<?> watsonRelationshipRemoteModel) {
		_watsonRelationshipRemoteModel = watsonRelationshipRemoteModel;
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

		Class<?> remoteModelClass = _watsonRelationshipRemoteModel.getClass();

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

		Object returnValue = method.invoke(_watsonRelationshipRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			WatsonRelationshipLocalServiceUtil.addWatsonRelationship(this);
		}
		else {
			WatsonRelationshipLocalServiceUtil.updateWatsonRelationship(this);
		}
	}

	@Override
	public WatsonRelationship toEscapedModel() {
		return (WatsonRelationship)ProxyUtil.newProxyInstance(WatsonRelationship.class.getClassLoader(),
			new Class[] { WatsonRelationship.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		WatsonRelationshipClp clone = new WatsonRelationshipClp();

		clone.setWatsonRelationshipId(getWatsonRelationshipId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setWatsonIncidentId(getWatsonIncidentId());
		clone.setTypeWatsonListTypeId(getTypeWatsonListTypeId());
		clone.setClassNameId1(getClassNameId1());
		clone.setClassPK1(getClassPK1());
		clone.setClassNameId2(getClassNameId2());
		clone.setClassPK2(getClassPK2());
		clone.setDescription(getDescription());
		clone.setStatus(getStatus());

		return clone;
	}

	@Override
	public int compareTo(WatsonRelationship watsonRelationship) {
		long primaryKey = watsonRelationship.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonRelationshipClp)) {
			return false;
		}

		WatsonRelationshipClp watsonRelationship = (WatsonRelationshipClp)obj;

		long primaryKey = watsonRelationship.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{watsonRelationshipId=");
		sb.append(getWatsonRelationshipId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", watsonIncidentId=");
		sb.append(getWatsonIncidentId());
		sb.append(", typeWatsonListTypeId=");
		sb.append(getTypeWatsonListTypeId());
		sb.append(", classNameId1=");
		sb.append(getClassNameId1());
		sb.append(", classPK1=");
		sb.append(getClassPK1());
		sb.append(", classNameId2=");
		sb.append(getClassNameId2());
		sb.append(", classPK2=");
		sb.append(getClassPK2());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.watson.model.WatsonRelationship");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>watsonRelationshipId</column-name><column-value><![CDATA[");
		sb.append(getWatsonRelationshipId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
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
			"<column><column-name>watsonIncidentId</column-name><column-value><![CDATA[");
		sb.append(getWatsonIncidentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>typeWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getTypeWatsonListTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId1</column-name><column-value><![CDATA[");
		sb.append(getClassNameId1());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK1</column-name><column-value><![CDATA[");
		sb.append(getClassPK1());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId2</column-name><column-value><![CDATA[");
		sb.append(getClassNameId2());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK2</column-name><column-value><![CDATA[");
		sb.append(getClassPK2());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _watsonRelationshipId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _watsonIncidentId;
	private long _typeWatsonListTypeId;
	private long _classNameId1;
	private long _classPK1;
	private long _classNameId2;
	private long _classPK2;
	private String _description;
	private int _status;
	private BaseModel<?> _watsonRelationshipRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}