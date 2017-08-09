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
import com.liferay.watson.service.WatsonIncidentRelLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class WatsonIncidentRelClp extends BaseModelImpl<WatsonIncidentRel>
	implements WatsonIncidentRel {
	public WatsonIncidentRelClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonIncidentRel.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonIncidentRel.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _watsonIncidentRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setWatsonIncidentRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonIncidentRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonIncidentRelId", getWatsonIncidentRelId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("watsonIncidentId1", getWatsonIncidentId1());
		attributes.put("watsonIncidentId2", getWatsonIncidentId2());
		attributes.put("type", getType());
		attributes.put("status", getStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonIncidentRelId = (Long)attributes.get("watsonIncidentRelId");

		if (watsonIncidentRelId != null) {
			setWatsonIncidentRelId(watsonIncidentRelId);
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

		Long watsonIncidentId1 = (Long)attributes.get("watsonIncidentId1");

		if (watsonIncidentId1 != null) {
			setWatsonIncidentId1(watsonIncidentId1);
		}

		Long watsonIncidentId2 = (Long)attributes.get("watsonIncidentId2");

		if (watsonIncidentId2 != null) {
			setWatsonIncidentId2(watsonIncidentId2);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getWatsonIncidentRelId() {
		return _watsonIncidentRelId;
	}

	@Override
	public void setWatsonIncidentRelId(long watsonIncidentRelId) {
		_watsonIncidentRelId = watsonIncidentRelId;

		if (_watsonIncidentRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonIncidentRelRemoteModel.getClass();

				Method method = clazz.getMethod("setWatsonIncidentRelId",
						long.class);

				method.invoke(_watsonIncidentRelRemoteModel, watsonIncidentRelId);
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

		if (_watsonIncidentRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonIncidentRelRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_watsonIncidentRelRemoteModel, companyId);
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

		if (_watsonIncidentRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonIncidentRelRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_watsonIncidentRelRemoteModel, userId);
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

		if (_watsonIncidentRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonIncidentRelRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_watsonIncidentRelRemoteModel, userName);
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

		if (_watsonIncidentRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonIncidentRelRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_watsonIncidentRelRemoteModel, createDate);
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

		if (_watsonIncidentRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonIncidentRelRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_watsonIncidentRelRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getWatsonIncidentId1() {
		return _watsonIncidentId1;
	}

	@Override
	public void setWatsonIncidentId1(long watsonIncidentId1) {
		_watsonIncidentId1 = watsonIncidentId1;

		if (_watsonIncidentRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonIncidentRelRemoteModel.getClass();

				Method method = clazz.getMethod("setWatsonIncidentId1",
						long.class);

				method.invoke(_watsonIncidentRelRemoteModel, watsonIncidentId1);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getWatsonIncidentId2() {
		return _watsonIncidentId2;
	}

	@Override
	public void setWatsonIncidentId2(long watsonIncidentId2) {
		_watsonIncidentId2 = watsonIncidentId2;

		if (_watsonIncidentRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonIncidentRelRemoteModel.getClass();

				Method method = clazz.getMethod("setWatsonIncidentId2",
						long.class);

				method.invoke(_watsonIncidentRelRemoteModel, watsonIncidentId2);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getType() {
		return _type;
	}

	@Override
	public void setType(String type) {
		_type = type;

		if (_watsonIncidentRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonIncidentRelRemoteModel.getClass();

				Method method = clazz.getMethod("setType", String.class);

				method.invoke(_watsonIncidentRelRemoteModel, type);
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

		if (_watsonIncidentRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonIncidentRelRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_watsonIncidentRelRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getWatsonIncidentRelRemoteModel() {
		return _watsonIncidentRelRemoteModel;
	}

	public void setWatsonIncidentRelRemoteModel(
		BaseModel<?> watsonIncidentRelRemoteModel) {
		_watsonIncidentRelRemoteModel = watsonIncidentRelRemoteModel;
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

		Class<?> remoteModelClass = _watsonIncidentRelRemoteModel.getClass();

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

		Object returnValue = method.invoke(_watsonIncidentRelRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			WatsonIncidentRelLocalServiceUtil.addWatsonIncidentRel(this);
		}
		else {
			WatsonIncidentRelLocalServiceUtil.updateWatsonIncidentRel(this);
		}
	}

	@Override
	public WatsonIncidentRel toEscapedModel() {
		return (WatsonIncidentRel)ProxyUtil.newProxyInstance(WatsonIncidentRel.class.getClassLoader(),
			new Class[] { WatsonIncidentRel.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		WatsonIncidentRelClp clone = new WatsonIncidentRelClp();

		clone.setWatsonIncidentRelId(getWatsonIncidentRelId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setWatsonIncidentId1(getWatsonIncidentId1());
		clone.setWatsonIncidentId2(getWatsonIncidentId2());
		clone.setType(getType());
		clone.setStatus(getStatus());

		return clone;
	}

	@Override
	public int compareTo(WatsonIncidentRel watsonIncidentRel) {
		long primaryKey = watsonIncidentRel.getPrimaryKey();

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

		if (!(obj instanceof WatsonIncidentRelClp)) {
			return false;
		}

		WatsonIncidentRelClp watsonIncidentRel = (WatsonIncidentRelClp)obj;

		long primaryKey = watsonIncidentRel.getPrimaryKey();

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
		StringBundler sb = new StringBundler(21);

		sb.append("{watsonIncidentRelId=");
		sb.append(getWatsonIncidentRelId());
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
		sb.append(", watsonIncidentId1=");
		sb.append(getWatsonIncidentId1());
		sb.append(", watsonIncidentId2=");
		sb.append(getWatsonIncidentId2());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.watson.model.WatsonIncidentRel");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>watsonIncidentRelId</column-name><column-value><![CDATA[");
		sb.append(getWatsonIncidentRelId());
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
			"<column><column-name>watsonIncidentId1</column-name><column-value><![CDATA[");
		sb.append(getWatsonIncidentId1());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>watsonIncidentId2</column-name><column-value><![CDATA[");
		sb.append(getWatsonIncidentId2());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _watsonIncidentRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _watsonIncidentId1;
	private long _watsonIncidentId2;
	private String _type;
	private int _status;
	private BaseModel<?> _watsonIncidentRelRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}