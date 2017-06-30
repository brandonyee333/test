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
import com.liferay.osb.service.TicketCommentLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
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
public class TicketCommentClp extends BaseModelImpl<TicketComment>
	implements TicketComment {
	public TicketCommentClp() {
	}

	public Class<?> getModelClass() {
		return TicketComment.class;
	}

	public String getModelClassName() {
		return TicketComment.class.getName();
	}

	public long getPrimaryKey() {
		return _ticketCommentId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTicketCommentId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_ticketCommentId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketCommentId", getTicketCommentId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("body", getBody());
		attributes.put("type", getType());
		attributes.put("format", getFormat());
		attributes.put("visibility", getVisibility());
		attributes.put("settings", getSettings());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketCommentId = (Long)attributes.get("ticketCommentId");

		if (ticketCommentId != null) {
			setTicketCommentId(ticketCommentId);
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

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		String body = (String)attributes.get("body");

		if (body != null) {
			setBody(body);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String format = (String)attributes.get("format");

		if (format != null) {
			setFormat(format);
		}

		Integer visibility = (Integer)attributes.get("visibility");

		if (visibility != null) {
			setVisibility(visibility);
		}

		String settings = (String)attributes.get("settings");

		if (settings != null) {
			setSettings(settings);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	public long getTicketCommentId() {
		return _ticketCommentId;
	}

	public void setTicketCommentId(long ticketCommentId) {
		_ticketCommentId = ticketCommentId;

		if (_ticketCommentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCommentRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketCommentId", long.class);

				method.invoke(_ticketCommentRemoteModel, ticketCommentId);
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

		if (_ticketCommentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCommentRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_ticketCommentRemoteModel, userId);
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

		if (_ticketCommentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCommentRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_ticketCommentRemoteModel, userName);
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

		if (_ticketCommentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCommentRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_ticketCommentRemoteModel, createDate);
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

		if (_ticketCommentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCommentRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_ticketCommentRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getTicketEntryId() {
		return _ticketEntryId;
	}

	public void setTicketEntryId(long ticketEntryId) {
		_ticketEntryId = ticketEntryId;

		if (_ticketCommentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCommentRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketEntryId", long.class);

				method.invoke(_ticketCommentRemoteModel, ticketEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getBody() {
		return _body;
	}

	public void setBody(String body) {
		_body = body;

		if (_ticketCommentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCommentRemoteModel.getClass();

				Method method = clazz.getMethod("setBody", String.class);

				method.invoke(_ticketCommentRemoteModel, body);
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

		if (_ticketCommentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCommentRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_ticketCommentRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getFormat() {
		return _format;
	}

	public void setFormat(String format) {
		_format = format;

		if (_ticketCommentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCommentRemoteModel.getClass();

				Method method = clazz.getMethod("setFormat", String.class);

				method.invoke(_ticketCommentRemoteModel, format);
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

		if (_ticketCommentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCommentRemoteModel.getClass();

				Method method = clazz.getMethod("setVisibility", int.class);

				method.invoke(_ticketCommentRemoteModel, visibility);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getSettings() {
		return _settings;
	}

	public void setSettings(String settings) {
		_settings = settings;

		if (_ticketCommentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCommentRemoteModel.getClass();

				Method method = clazz.getMethod("setSettings", String.class);

				method.invoke(_ticketCommentRemoteModel, settings);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;

		if (_ticketCommentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCommentRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_ticketCommentRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public void setSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties settingsProperties) {
		try {
			String methodName = "setSettingsProperties";

			Class<?>[] parameterTypes = new Class<?>[] {
					com.liferay.portal.kernel.util.UnicodeProperties.class
				};

			Object[] parameterValues = new Object[] { settingsProperties };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getVisibilityLabel() {
		try {
			String methodName = "getVisibilityLabel";

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

	public void setSettingsProperty(java.lang.String key, java.lang.String value) {
		try {
			String methodName = "setSettingsProperty";

			Class<?>[] parameterTypes = new Class<?>[] {
					java.lang.String.class, java.lang.String.class
				};

			Object[] parameterValues = new Object[] { key, value };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getSettingsProperty(java.lang.String key) {
		try {
			String methodName = "getSettingsProperty";

			Class<?>[] parameterTypes = new Class<?>[] { java.lang.String.class };

			Object[] parameterValues = new Object[] { key };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.portal.kernel.util.UnicodeProperties getSettingsProperties() {
		try {
			String methodName = "getSettingsProperties";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.kernel.util.UnicodeProperties returnObj = (com.liferay.portal.kernel.util.UnicodeProperties)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getKey() {
		try {
			String methodName = "getKey";

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

	public BaseModel<?> getTicketCommentRemoteModel() {
		return _ticketCommentRemoteModel;
	}

	public void setTicketCommentRemoteModel(
		BaseModel<?> ticketCommentRemoteModel) {
		_ticketCommentRemoteModel = ticketCommentRemoteModel;
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

		Class<?> remoteModelClass = _ticketCommentRemoteModel.getClass();

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

		Object returnValue = method.invoke(_ticketCommentRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TicketCommentLocalServiceUtil.addTicketComment(this);
		}
		else {
			TicketCommentLocalServiceUtil.updateTicketComment(this);
		}
	}

	@Override
	public TicketComment toEscapedModel() {
		return (TicketComment)ProxyUtil.newProxyInstance(TicketComment.class.getClassLoader(),
			new Class[] { TicketComment.class }, new AutoEscapeBeanHandler(this));
	}

	public TicketComment toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		TicketCommentClp clone = new TicketCommentClp();

		clone.setTicketCommentId(getTicketCommentId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setTicketEntryId(getTicketEntryId());
		clone.setBody(getBody());
		clone.setType(getType());
		clone.setFormat(getFormat());
		clone.setVisibility(getVisibility());
		clone.setSettings(getSettings());
		clone.setStatus(getStatus());

		return clone;
	}

	public int compareTo(TicketComment ticketComment) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				ticketComment.getCreateDate());

		if (value != 0) {
			return value;
		}

		if (getTicketCommentId() < ticketComment.getTicketCommentId()) {
			value = -1;
		}
		else if (getTicketCommentId() > ticketComment.getTicketCommentId()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(obj instanceof TicketCommentClp)) {
			return false;
		}

		TicketCommentClp ticketComment = (TicketCommentClp)obj;

		long primaryKey = ticketComment.getPrimaryKey();

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
		StringBundler sb = new StringBundler(25);

		sb.append("{ticketCommentId=");
		sb.append(getTicketCommentId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", ticketEntryId=");
		sb.append(getTicketEntryId());
		sb.append(", body=");
		sb.append(getBody());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", format=");
		sb.append(getFormat());
		sb.append(", visibility=");
		sb.append(getVisibility());
		sb.append(", settings=");
		sb.append(getSettings());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TicketComment");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>ticketCommentId</column-name><column-value><![CDATA[");
		sb.append(getTicketCommentId());
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
			"<column><column-name>ticketEntryId</column-name><column-value><![CDATA[");
		sb.append(getTicketEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>body</column-name><column-value><![CDATA[");
		sb.append(getBody());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>format</column-name><column-value><![CDATA[");
		sb.append(getFormat());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>visibility</column-name><column-value><![CDATA[");
		sb.append(getVisibility());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>settings</column-name><column-value><![CDATA[");
		sb.append(getSettings());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _ticketCommentId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _ticketEntryId;
	private String _body;
	private int _type;
	private String _format;
	private int _visibility;
	private String _settings;
	private int _status;
	private BaseModel<?> _ticketCommentRemoteModel;
}