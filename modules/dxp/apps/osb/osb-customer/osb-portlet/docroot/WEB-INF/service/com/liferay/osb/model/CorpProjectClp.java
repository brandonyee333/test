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

import com.liferay.osb.service.ClpSerializer;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;

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

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class CorpProjectClp extends BaseModelImpl<CorpProject>
	implements CorpProject {
	public CorpProjectClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return CorpProject.class;
	}

	@Override
	public String getModelClassName() {
		return CorpProject.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _corpProjectId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCorpProjectId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _corpProjectId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("corpProjectId", getCorpProjectId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossieraProjectKey", getDossieraProjectKey());
		attributes.put("salesforceProjectKey", getSalesforceProjectKey());
		attributes.put("name", getName());
		attributes.put("organizationId", getOrganizationId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long corpProjectId = (Long)attributes.get("corpProjectId");

		if (corpProjectId != null) {
			setCorpProjectId(corpProjectId);
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

		String dossieraProjectKey = (String)attributes.get("dossieraProjectKey");

		if (dossieraProjectKey != null) {
			setDossieraProjectKey(dossieraProjectKey);
		}

		String salesforceProjectKey = (String)attributes.get(
				"salesforceProjectKey");

		if (salesforceProjectKey != null) {
			setSalesforceProjectKey(salesforceProjectKey);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long organizationId = (Long)attributes.get("organizationId");

		if (organizationId != null) {
			setOrganizationId(organizationId);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_corpProjectRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_corpProjectRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCorpProjectId() {
		return _corpProjectId;
	}

	@Override
	public void setCorpProjectId(long corpProjectId) {
		_corpProjectId = corpProjectId;

		if (_corpProjectRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setCorpProjectId", long.class);

				method.invoke(_corpProjectRemoteModel, corpProjectId);
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

		if (_corpProjectRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_corpProjectRemoteModel, userId);
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

		if (_corpProjectRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_corpProjectRemoteModel, userName);
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

		if (_corpProjectRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_corpProjectRemoteModel, createDate);
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

		if (_corpProjectRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_corpProjectRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getDossieraProjectKey() {
		return _dossieraProjectKey;
	}

	@Override
	public void setDossieraProjectKey(String dossieraProjectKey) {
		_dossieraProjectKey = dossieraProjectKey;

		if (_corpProjectRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setDossieraProjectKey",
						String.class);

				method.invoke(_corpProjectRemoteModel, dossieraProjectKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSalesforceProjectKey() {
		return _salesforceProjectKey;
	}

	@Override
	public void setSalesforceProjectKey(String salesforceProjectKey) {
		_salesforceProjectKey = salesforceProjectKey;

		if (_corpProjectRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setSalesforceProjectKey",
						String.class);

				method.invoke(_corpProjectRemoteModel, salesforceProjectKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;

		if (_corpProjectRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_corpProjectRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getOrganizationId() {
		return _organizationId;
	}

	@Override
	public void setOrganizationId(long organizationId) {
		_organizationId = organizationId;

		if (_corpProjectRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setOrganizationId", long.class);

				method.invoke(_corpProjectRemoteModel, organizationId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getCorpProjectRemoteModel() {
		return _corpProjectRemoteModel;
	}

	public void setCorpProjectRemoteModel(BaseModel<?> corpProjectRemoteModel) {
		_corpProjectRemoteModel = corpProjectRemoteModel;
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

		Class<?> remoteModelClass = _corpProjectRemoteModel.getClass();

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

		Object returnValue = method.invoke(_corpProjectRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			CorpProjectLocalServiceUtil.addCorpProject(this);
		}
		else {
			CorpProjectLocalServiceUtil.updateCorpProject(this);
		}
	}

	@Override
	public CorpProject toEscapedModel() {
		return (CorpProject)ProxyUtil.newProxyInstance(CorpProject.class.getClassLoader(),
			new Class[] { CorpProject.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		CorpProjectClp clone = new CorpProjectClp();

		clone.setUuid(getUuid());
		clone.setCorpProjectId(getCorpProjectId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setDossieraProjectKey(getDossieraProjectKey());
		clone.setSalesforceProjectKey(getSalesforceProjectKey());
		clone.setName(getName());
		clone.setOrganizationId(getOrganizationId());

		return clone;
	}

	@Override
	public int compareTo(CorpProject corpProject) {
		long primaryKey = corpProject.getPrimaryKey();

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

		if (!(obj instanceof CorpProjectClp)) {
			return false;
		}

		CorpProjectClp corpProject = (CorpProjectClp)obj;

		long primaryKey = corpProject.getPrimaryKey();

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

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", corpProjectId=");
		sb.append(getCorpProjectId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", dossieraProjectKey=");
		sb.append(getDossieraProjectKey());
		sb.append(", salesforceProjectKey=");
		sb.append(getSalesforceProjectKey());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", organizationId=");
		sb.append(getOrganizationId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.CorpProject");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>corpProjectId</column-name><column-value><![CDATA[");
		sb.append(getCorpProjectId());
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
			"<column><column-name>dossieraProjectKey</column-name><column-value><![CDATA[");
		sb.append(getDossieraProjectKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>salesforceProjectKey</column-name><column-value><![CDATA[");
		sb.append(getSalesforceProjectKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>organizationId</column-name><column-value><![CDATA[");
		sb.append(getOrganizationId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _corpProjectId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _dossieraProjectKey;
	private String _salesforceProjectKey;
	private String _name;
	private long _organizationId;
	private BaseModel<?> _corpProjectRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}