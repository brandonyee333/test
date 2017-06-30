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
import com.liferay.osb.service.CorpMembershipRequestLocalServiceUtil;

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
public class CorpMembershipRequestClp extends BaseModelImpl<CorpMembershipRequest>
	implements CorpMembershipRequest {
	public CorpMembershipRequestClp() {
	}

	public Class<?> getModelClass() {
		return CorpMembershipRequest.class;
	}

	public String getModelClassName() {
		return CorpMembershipRequest.class.getName();
	}

	public long getPrimaryKey() {
		return _corpMembershipRequestId;
	}

	public void setPrimaryKey(long primaryKey) {
		setCorpMembershipRequestId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_corpMembershipRequestId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("corpMembershipRequestId", getCorpMembershipRequestId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("corpEntryId", getCorpEntryId());
		attributes.put("key", getKey());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long corpMembershipRequestId = (Long)attributes.get(
				"corpMembershipRequestId");

		if (corpMembershipRequestId != null) {
			setCorpMembershipRequestId(corpMembershipRequestId);
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

		Long corpEntryId = (Long)attributes.get("corpEntryId");

		if (corpEntryId != null) {
			setCorpEntryId(corpEntryId);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	public long getCorpMembershipRequestId() {
		return _corpMembershipRequestId;
	}

	public void setCorpMembershipRequestId(long corpMembershipRequestId) {
		_corpMembershipRequestId = corpMembershipRequestId;

		if (_corpMembershipRequestRemoteModel != null) {
			try {
				Class<?> clazz = _corpMembershipRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setCorpMembershipRequestId",
						long.class);

				method.invoke(_corpMembershipRequestRemoteModel,
					corpMembershipRequestId);
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

		if (_corpMembershipRequestRemoteModel != null) {
			try {
				Class<?> clazz = _corpMembershipRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_corpMembershipRequestRemoteModel, userId);
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

		if (_corpMembershipRequestRemoteModel != null) {
			try {
				Class<?> clazz = _corpMembershipRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_corpMembershipRequestRemoteModel, userName);
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

		if (_corpMembershipRequestRemoteModel != null) {
			try {
				Class<?> clazz = _corpMembershipRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_corpMembershipRequestRemoteModel, createDate);
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

		if (_corpMembershipRequestRemoteModel != null) {
			try {
				Class<?> clazz = _corpMembershipRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_corpMembershipRequestRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getCorpEntryId() {
		return _corpEntryId;
	}

	public void setCorpEntryId(long corpEntryId) {
		_corpEntryId = corpEntryId;

		if (_corpMembershipRequestRemoteModel != null) {
			try {
				Class<?> clazz = _corpMembershipRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setCorpEntryId", long.class);

				method.invoke(_corpMembershipRequestRemoteModel, corpEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;

		if (_corpMembershipRequestRemoteModel != null) {
			try {
				Class<?> clazz = _corpMembershipRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setKey", String.class);

				method.invoke(_corpMembershipRequestRemoteModel, key);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;

		if (_corpMembershipRequestRemoteModel != null) {
			try {
				Class<?> clazz = _corpMembershipRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setEmailAddress", String.class);

				method.invoke(_corpMembershipRequestRemoteModel, emailAddress);
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

		if (_corpMembershipRequestRemoteModel != null) {
			try {
				Class<?> clazz = _corpMembershipRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_corpMembershipRequestRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getCorpMembershipRequestRemoteModel() {
		return _corpMembershipRequestRemoteModel;
	}

	public void setCorpMembershipRequestRemoteModel(
		BaseModel<?> corpMembershipRequestRemoteModel) {
		_corpMembershipRequestRemoteModel = corpMembershipRequestRemoteModel;
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

		Class<?> remoteModelClass = _corpMembershipRequestRemoteModel.getClass();

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

		Object returnValue = method.invoke(_corpMembershipRequestRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			CorpMembershipRequestLocalServiceUtil.addCorpMembershipRequest(this);
		}
		else {
			CorpMembershipRequestLocalServiceUtil.updateCorpMembershipRequest(this);
		}
	}

	@Override
	public CorpMembershipRequest toEscapedModel() {
		return (CorpMembershipRequest)ProxyUtil.newProxyInstance(CorpMembershipRequest.class.getClassLoader(),
			new Class[] { CorpMembershipRequest.class },
			new AutoEscapeBeanHandler(this));
	}

	public CorpMembershipRequest toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		CorpMembershipRequestClp clone = new CorpMembershipRequestClp();

		clone.setCorpMembershipRequestId(getCorpMembershipRequestId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setCorpEntryId(getCorpEntryId());
		clone.setKey(getKey());
		clone.setEmailAddress(getEmailAddress());
		clone.setStatus(getStatus());

		return clone;
	}

	public int compareTo(CorpMembershipRequest corpMembershipRequest) {
		long primaryKey = corpMembershipRequest.getPrimaryKey();

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

		if (!(obj instanceof CorpMembershipRequestClp)) {
			return false;
		}

		CorpMembershipRequestClp corpMembershipRequest = (CorpMembershipRequestClp)obj;

		long primaryKey = corpMembershipRequest.getPrimaryKey();

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

		sb.append("{corpMembershipRequestId=");
		sb.append(getCorpMembershipRequestId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", corpEntryId=");
		sb.append(getCorpEntryId());
		sb.append(", key=");
		sb.append(getKey());
		sb.append(", emailAddress=");
		sb.append(getEmailAddress());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.CorpMembershipRequest");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>corpMembershipRequestId</column-name><column-value><![CDATA[");
		sb.append(getCorpMembershipRequestId());
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
			"<column><column-name>corpEntryId</column-name><column-value><![CDATA[");
		sb.append(getCorpEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>key</column-name><column-value><![CDATA[");
		sb.append(getKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>emailAddress</column-name><column-value><![CDATA[");
		sb.append(getEmailAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _corpMembershipRequestId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _corpEntryId;
	private String _key;
	private String _emailAddress;
	private int _status;
	private BaseModel<?> _corpMembershipRequestRemoteModel;
}