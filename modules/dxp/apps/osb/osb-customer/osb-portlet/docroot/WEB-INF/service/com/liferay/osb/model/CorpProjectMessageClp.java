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
import com.liferay.osb.service.CorpProjectMessageLocalServiceUtil;

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
public class CorpProjectMessageClp extends BaseModelImpl<CorpProjectMessage>
	implements CorpProjectMessage {
	public CorpProjectMessageClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return CorpProjectMessage.class;
	}

	@Override
	public String getModelClassName() {
		return CorpProjectMessage.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _corpProjectMessageId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCorpProjectMessageId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _corpProjectMessageId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("corpProjectMessageId", getCorpProjectMessageId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("corpProjectId", getCorpProjectId());
		attributes.put("type", getType());
		attributes.put("severityLevel", getSeverityLevel());
		attributes.put("title", getTitle());
		attributes.put("content", getContent());
		attributes.put("displayCP", getDisplayCP());
		attributes.put("displayLCS", getDisplayLCS());

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

		Long corpProjectMessageId = (Long)attributes.get("corpProjectMessageId");

		if (corpProjectMessageId != null) {
			setCorpProjectMessageId(corpProjectMessageId);
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

		Long corpProjectId = (Long)attributes.get("corpProjectId");

		if (corpProjectId != null) {
			setCorpProjectId(corpProjectId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer severityLevel = (Integer)attributes.get("severityLevel");

		if (severityLevel != null) {
			setSeverityLevel(severityLevel);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Boolean displayCP = (Boolean)attributes.get("displayCP");

		if (displayCP != null) {
			setDisplayCP(displayCP);
		}

		Boolean displayLCS = (Boolean)attributes.get("displayLCS");

		if (displayLCS != null) {
			setDisplayLCS(displayLCS);
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

		if (_corpProjectMessageRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_corpProjectMessageRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCorpProjectMessageId() {
		return _corpProjectMessageId;
	}

	@Override
	public void setCorpProjectMessageId(long corpProjectMessageId) {
		_corpProjectMessageId = corpProjectMessageId;

		if (_corpProjectMessageRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setCorpProjectMessageId",
						long.class);

				method.invoke(_corpProjectMessageRemoteModel,
					corpProjectMessageId);
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

		if (_corpProjectMessageRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_corpProjectMessageRemoteModel, userId);
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

		if (_corpProjectMessageRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_corpProjectMessageRemoteModel, userName);
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

		if (_corpProjectMessageRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_corpProjectMessageRemoteModel, createDate);
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

		if (_corpProjectMessageRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_corpProjectMessageRemoteModel, modifiedDate);
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

		if (_corpProjectMessageRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setCorpProjectId", long.class);

				method.invoke(_corpProjectMessageRemoteModel, corpProjectId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_type = type;

		if (_corpProjectMessageRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_corpProjectMessageRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSeverityLevel() {
		return _severityLevel;
	}

	@Override
	public void setSeverityLevel(int severityLevel) {
		_severityLevel = severityLevel;

		if (_corpProjectMessageRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setSeverityLevel", int.class);

				method.invoke(_corpProjectMessageRemoteModel, severityLevel);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getTitle() {
		return _title;
	}

	@Override
	public void setTitle(String title) {
		_title = title;

		if (_corpProjectMessageRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setTitle", String.class);

				method.invoke(_corpProjectMessageRemoteModel, title);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getContent() {
		return _content;
	}

	@Override
	public void setContent(String content) {
		_content = content;

		if (_corpProjectMessageRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setContent", String.class);

				method.invoke(_corpProjectMessageRemoteModel, content);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getDisplayCP() {
		return _displayCP;
	}

	@Override
	public boolean isDisplayCP() {
		return _displayCP;
	}

	@Override
	public void setDisplayCP(boolean displayCP) {
		_displayCP = displayCP;

		if (_corpProjectMessageRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setDisplayCP", boolean.class);

				method.invoke(_corpProjectMessageRemoteModel, displayCP);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getDisplayLCS() {
		return _displayLCS;
	}

	@Override
	public boolean isDisplayLCS() {
		return _displayLCS;
	}

	@Override
	public void setDisplayLCS(boolean displayLCS) {
		_displayLCS = displayLCS;

		if (_corpProjectMessageRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setDisplayLCS", boolean.class);

				method.invoke(_corpProjectMessageRemoteModel, displayLCS);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public java.lang.String getSeverityLevelLabel() {
		try {
			String methodName = "getSeverityLevelLabel";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getTypeLabel() {
		try {
			String methodName = "getTypeLabel";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getCorpProjectMessageRemoteModel() {
		return _corpProjectMessageRemoteModel;
	}

	public void setCorpProjectMessageRemoteModel(
		BaseModel<?> corpProjectMessageRemoteModel) {
		_corpProjectMessageRemoteModel = corpProjectMessageRemoteModel;
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

		Class<?> remoteModelClass = _corpProjectMessageRemoteModel.getClass();

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

		Object returnValue = method.invoke(_corpProjectMessageRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			CorpProjectMessageLocalServiceUtil.addCorpProjectMessage(this);
		}
		else {
			CorpProjectMessageLocalServiceUtil.updateCorpProjectMessage(this);
		}
	}

	@Override
	public CorpProjectMessage toEscapedModel() {
		return (CorpProjectMessage)ProxyUtil.newProxyInstance(CorpProjectMessage.class.getClassLoader(),
			new Class[] { CorpProjectMessage.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		CorpProjectMessageClp clone = new CorpProjectMessageClp();

		clone.setUuid(getUuid());
		clone.setCorpProjectMessageId(getCorpProjectMessageId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setCorpProjectId(getCorpProjectId());
		clone.setType(getType());
		clone.setSeverityLevel(getSeverityLevel());
		clone.setTitle(getTitle());
		clone.setContent(getContent());
		clone.setDisplayCP(getDisplayCP());
		clone.setDisplayLCS(getDisplayLCS());

		return clone;
	}

	@Override
	public int compareTo(CorpProjectMessage corpProjectMessage) {
		long primaryKey = corpProjectMessage.getPrimaryKey();

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

		if (!(obj instanceof CorpProjectMessageClp)) {
			return false;
		}

		CorpProjectMessageClp corpProjectMessage = (CorpProjectMessageClp)obj;

		long primaryKey = corpProjectMessage.getPrimaryKey();

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
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", corpProjectMessageId=");
		sb.append(getCorpProjectMessageId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", corpProjectId=");
		sb.append(getCorpProjectId());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", severityLevel=");
		sb.append(getSeverityLevel());
		sb.append(", title=");
		sb.append(getTitle());
		sb.append(", content=");
		sb.append(getContent());
		sb.append(", displayCP=");
		sb.append(getDisplayCP());
		sb.append(", displayLCS=");
		sb.append(getDisplayLCS());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.CorpProjectMessage");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>corpProjectMessageId</column-name><column-value><![CDATA[");
		sb.append(getCorpProjectMessageId());
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
			"<column><column-name>corpProjectId</column-name><column-value><![CDATA[");
		sb.append(getCorpProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>severityLevel</column-name><column-value><![CDATA[");
		sb.append(getSeverityLevel());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>content</column-name><column-value><![CDATA[");
		sb.append(getContent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>displayCP</column-name><column-value><![CDATA[");
		sb.append(getDisplayCP());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>displayLCS</column-name><column-value><![CDATA[");
		sb.append(getDisplayLCS());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _corpProjectMessageId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _corpProjectId;
	private int _type;
	private int _severityLevel;
	private String _title;
	private String _content;
	private boolean _displayCP;
	private boolean _displayLCS;
	private BaseModel<?> _corpProjectMessageRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}