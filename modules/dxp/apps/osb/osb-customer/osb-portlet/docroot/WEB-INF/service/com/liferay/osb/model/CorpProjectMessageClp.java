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
import com.liferay.osb.service.CorpProjectMessageLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
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
public class CorpProjectMessageClp extends BaseModelImpl<CorpProjectMessage>
	implements CorpProjectMessage {
	public CorpProjectMessageClp() {
	}

	public Class<?> getModelClass() {
		return CorpProjectMessage.class;
	}

	public String getModelClassName() {
		return CorpProjectMessage.class.getName();
	}

	public long getPrimaryKey() {
		return _corpProjectMessageId;
	}

	public void setPrimaryKey(long primaryKey) {
		setCorpProjectMessageId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_corpProjectMessageId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

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
		attributes.put("displayLESA", getDisplayLESA());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
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

		Boolean displayLESA = (Boolean)attributes.get("displayLESA");

		if (displayLESA != null) {
			setDisplayLESA(displayLESA);
		}
	}

	public long getCorpProjectMessageId() {
		return _corpProjectMessageId;
	}

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

	public long getUserId() {
		return _userId;
	}

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

	public Date getCreateDate() {
		return _createDate;
	}

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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

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

	public long getCorpProjectId() {
		return _corpProjectId;
	}

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

	public int getType() {
		return _type;
	}

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

	public int getSeverityLevel() {
		return _severityLevel;
	}

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

	public String getTitle() {
		return _title;
	}

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

	public String getContent() {
		return _content;
	}

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

	public boolean getDisplayCP() {
		return _displayCP;
	}

	public boolean isDisplayCP() {
		return _displayCP;
	}

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

	public boolean getDisplayLCS() {
		return _displayLCS;
	}

	public boolean isDisplayLCS() {
		return _displayLCS;
	}

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

	public boolean getDisplayLESA() {
		return _displayLESA;
	}

	public boolean isDisplayLESA() {
		return _displayLESA;
	}

	public void setDisplayLESA(boolean displayLESA) {
		_displayLESA = displayLESA;

		if (_corpProjectMessageRemoteModel != null) {
			try {
				Class<?> clazz = _corpProjectMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setDisplayLESA", boolean.class);

				method.invoke(_corpProjectMessageRemoteModel, displayLESA);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public void setAccountEntry(com.liferay.osb.model.AccountEntry accountEntry) {
		try {
			String methodName = "setAccountEntry";

			Class<?>[] parameterTypes = new Class<?>[] {
					com.liferay.osb.model.AccountEntry.class
				};

			Object[] parameterValues = new Object[] { accountEntry };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

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

	public com.liferay.osb.model.AccountEntry getAccountEntry() {
		try {
			String methodName = "getAccountEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.AccountEntry returnObj = (com.liferay.osb.model.AccountEntry)invokeOnRemoteModel(methodName,
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

	public void persist() throws SystemException {
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

	public CorpProjectMessage toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		CorpProjectMessageClp clone = new CorpProjectMessageClp();

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
		clone.setDisplayLESA(getDisplayLESA());

		return clone;
	}

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

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{corpProjectMessageId=");
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
		sb.append(", displayLESA=");
		sb.append(getDisplayLESA());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.CorpProjectMessage");
		sb.append("</model-name>");

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
		sb.append(
			"<column><column-name>displayLESA</column-name><column-value><![CDATA[");
		sb.append(getDisplayLESA());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _corpProjectMessageId;
	private long _userId;
	private String _userUuid;
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
	private boolean _displayLESA;
	private BaseModel<?> _corpProjectMessageRemoteModel;
}