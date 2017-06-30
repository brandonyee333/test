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
import com.liferay.osb.service.TicketLinkLocalServiceUtil;

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
public class TicketLinkClp extends BaseModelImpl<TicketLink>
	implements TicketLink {
	public TicketLinkClp() {
	}

	public Class<?> getModelClass() {
		return TicketLink.class;
	}

	public String getModelClassName() {
		return TicketLink.class.getName();
	}

	public long getPrimaryKey() {
		return _ticketLinkId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTicketLinkId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_ticketLinkId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketLinkId", getTicketLinkId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("ticketSolutionId", getTicketSolutionId());
		attributes.put("url", getUrl());
		attributes.put("type", getType());
		attributes.put("visibility", getVisibility());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketLinkId = (Long)attributes.get("ticketLinkId");

		if (ticketLinkId != null) {
			setTicketLinkId(ticketLinkId);
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

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		Long ticketSolutionId = (Long)attributes.get("ticketSolutionId");

		if (ticketSolutionId != null) {
			setTicketSolutionId(ticketSolutionId);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer visibility = (Integer)attributes.get("visibility");

		if (visibility != null) {
			setVisibility(visibility);
		}
	}

	public long getTicketLinkId() {
		return _ticketLinkId;
	}

	public void setTicketLinkId(long ticketLinkId) {
		_ticketLinkId = ticketLinkId;

		if (_ticketLinkRemoteModel != null) {
			try {
				Class<?> clazz = _ticketLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketLinkId", long.class);

				method.invoke(_ticketLinkRemoteModel, ticketLinkId);
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

		if (_ticketLinkRemoteModel != null) {
			try {
				Class<?> clazz = _ticketLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_ticketLinkRemoteModel, userId);
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

		if (_ticketLinkRemoteModel != null) {
			try {
				Class<?> clazz = _ticketLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_ticketLinkRemoteModel, userName);
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

		if (_ticketLinkRemoteModel != null) {
			try {
				Class<?> clazz = _ticketLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_ticketLinkRemoteModel, createDate);
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

		if (_ticketLinkRemoteModel != null) {
			try {
				Class<?> clazz = _ticketLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketEntryId", long.class);

				method.invoke(_ticketLinkRemoteModel, ticketEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getTicketSolutionId() {
		return _ticketSolutionId;
	}

	public void setTicketSolutionId(long ticketSolutionId) {
		_ticketSolutionId = ticketSolutionId;

		if (_ticketLinkRemoteModel != null) {
			try {
				Class<?> clazz = _ticketLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketSolutionId",
						long.class);

				method.invoke(_ticketLinkRemoteModel, ticketSolutionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;

		if (_ticketLinkRemoteModel != null) {
			try {
				Class<?> clazz = _ticketLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setUrl", String.class);

				method.invoke(_ticketLinkRemoteModel, url);
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

		if (_ticketLinkRemoteModel != null) {
			try {
				Class<?> clazz = _ticketLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_ticketLinkRemoteModel, type);
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

		if (_ticketLinkRemoteModel != null) {
			try {
				Class<?> clazz = _ticketLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setVisibility", int.class);

				method.invoke(_ticketLinkRemoteModel, visibility);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
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

	public com.liferay.osb.model.TicketEntry getTicketEntry() {
		try {
			String methodName = "getTicketEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.TicketEntry returnObj = (com.liferay.osb.model.TicketEntry)invokeOnRemoteModel(methodName,
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

	public BaseModel<?> getTicketLinkRemoteModel() {
		return _ticketLinkRemoteModel;
	}

	public void setTicketLinkRemoteModel(BaseModel<?> ticketLinkRemoteModel) {
		_ticketLinkRemoteModel = ticketLinkRemoteModel;
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

		Class<?> remoteModelClass = _ticketLinkRemoteModel.getClass();

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

		Object returnValue = method.invoke(_ticketLinkRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TicketLinkLocalServiceUtil.addTicketLink(this);
		}
		else {
			TicketLinkLocalServiceUtil.updateTicketLink(this);
		}
	}

	@Override
	public TicketLink toEscapedModel() {
		return (TicketLink)ProxyUtil.newProxyInstance(TicketLink.class.getClassLoader(),
			new Class[] { TicketLink.class }, new AutoEscapeBeanHandler(this));
	}

	public TicketLink toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		TicketLinkClp clone = new TicketLinkClp();

		clone.setTicketLinkId(getTicketLinkId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setTicketEntryId(getTicketEntryId());
		clone.setTicketSolutionId(getTicketSolutionId());
		clone.setUrl(getUrl());
		clone.setType(getType());
		clone.setVisibility(getVisibility());

		return clone;
	}

	public int compareTo(TicketLink ticketLink) {
		long primaryKey = ticketLink.getPrimaryKey();

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

		if (!(obj instanceof TicketLinkClp)) {
			return false;
		}

		TicketLinkClp ticketLink = (TicketLinkClp)obj;

		long primaryKey = ticketLink.getPrimaryKey();

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

		sb.append("{ticketLinkId=");
		sb.append(getTicketLinkId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", ticketEntryId=");
		sb.append(getTicketEntryId());
		sb.append(", ticketSolutionId=");
		sb.append(getTicketSolutionId());
		sb.append(", url=");
		sb.append(getUrl());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", visibility=");
		sb.append(getVisibility());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TicketLink");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>ticketLinkId</column-name><column-value><![CDATA[");
		sb.append(getTicketLinkId());
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
			"<column><column-name>ticketEntryId</column-name><column-value><![CDATA[");
		sb.append(getTicketEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ticketSolutionId</column-name><column-value><![CDATA[");
		sb.append(getTicketSolutionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>url</column-name><column-value><![CDATA[");
		sb.append(getUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>visibility</column-name><column-value><![CDATA[");
		sb.append(getVisibility());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _ticketLinkId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private long _ticketEntryId;
	private long _ticketSolutionId;
	private String _url;
	private int _type;
	private int _visibility;
	private BaseModel<?> _ticketLinkRemoteModel;
}