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
import com.liferay.osb.service.SecurityPatchLocalServiceUtil;

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
public class SecurityPatchClp extends BaseModelImpl<SecurityPatch>
	implements SecurityPatch {
	public SecurityPatchClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SecurityPatch.class;
	}

	@Override
	public String getModelClassName() {
		return SecurityPatch.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _securityPatchId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSecurityPatchId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _securityPatchId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("securityPatchId", getSecurityPatchId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("ticketAttachmentId", getTicketAttachmentId());
		attributes.put("portletId", getPortletId());
		attributes.put("envLFR", getEnvLFR());
		attributes.put("name", getName());
		attributes.put("fileName", getFileName());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long securityPatchId = (Long)attributes.get("securityPatchId");

		if (securityPatchId != null) {
			setSecurityPatchId(securityPatchId);
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

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Long ticketAttachmentId = (Long)attributes.get("ticketAttachmentId");

		if (ticketAttachmentId != null) {
			setTicketAttachmentId(ticketAttachmentId);
		}

		String portletId = (String)attributes.get("portletId");

		if (portletId != null) {
			setPortletId(portletId);
		}

		Integer envLFR = (Integer)attributes.get("envLFR");

		if (envLFR != null) {
			setEnvLFR(envLFR);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getSecurityPatchId() {
		return _securityPatchId;
	}

	@Override
	public void setSecurityPatchId(long securityPatchId) {
		_securityPatchId = securityPatchId;

		if (_securityPatchRemoteModel != null) {
			try {
				Class<?> clazz = _securityPatchRemoteModel.getClass();

				Method method = clazz.getMethod("setSecurityPatchId", long.class);

				method.invoke(_securityPatchRemoteModel, securityPatchId);
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

		if (_securityPatchRemoteModel != null) {
			try {
				Class<?> clazz = _securityPatchRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_securityPatchRemoteModel, userId);
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

		if (_securityPatchRemoteModel != null) {
			try {
				Class<?> clazz = _securityPatchRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_securityPatchRemoteModel, userName);
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

		if (_securityPatchRemoteModel != null) {
			try {
				Class<?> clazz = _securityPatchRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_securityPatchRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAccountEntryId() {
		return _accountEntryId;
	}

	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;

		if (_securityPatchRemoteModel != null) {
			try {
				Class<?> clazz = _securityPatchRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryId", long.class);

				method.invoke(_securityPatchRemoteModel, accountEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getTicketAttachmentId() {
		return _ticketAttachmentId;
	}

	@Override
	public void setTicketAttachmentId(long ticketAttachmentId) {
		_ticketAttachmentId = ticketAttachmentId;

		if (_securityPatchRemoteModel != null) {
			try {
				Class<?> clazz = _securityPatchRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketAttachmentId",
						long.class);

				method.invoke(_securityPatchRemoteModel, ticketAttachmentId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPortletId() {
		return _portletId;
	}

	@Override
	public void setPortletId(String portletId) {
		_portletId = portletId;

		if (_securityPatchRemoteModel != null) {
			try {
				Class<?> clazz = _securityPatchRemoteModel.getClass();

				Method method = clazz.getMethod("setPortletId", String.class);

				method.invoke(_securityPatchRemoteModel, portletId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getEnvLFR() {
		return _envLFR;
	}

	@Override
	public void setEnvLFR(int envLFR) {
		_envLFR = envLFR;

		if (_securityPatchRemoteModel != null) {
			try {
				Class<?> clazz = _securityPatchRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvLFR", int.class);

				method.invoke(_securityPatchRemoteModel, envLFR);
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

		if (_securityPatchRemoteModel != null) {
			try {
				Class<?> clazz = _securityPatchRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_securityPatchRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getFileName() {
		return _fileName;
	}

	@Override
	public void setFileName(String fileName) {
		_fileName = fileName;

		if (_securityPatchRemoteModel != null) {
			try {
				Class<?> clazz = _securityPatchRemoteModel.getClass();

				Method method = clazz.getMethod("setFileName", String.class);

				method.invoke(_securityPatchRemoteModel, fileName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSecurityPatchRemoteModel() {
		return _securityPatchRemoteModel;
	}

	public void setSecurityPatchRemoteModel(
		BaseModel<?> securityPatchRemoteModel) {
		_securityPatchRemoteModel = securityPatchRemoteModel;
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

		Class<?> remoteModelClass = _securityPatchRemoteModel.getClass();

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

		Object returnValue = method.invoke(_securityPatchRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			SecurityPatchLocalServiceUtil.addSecurityPatch(this);
		}
		else {
			SecurityPatchLocalServiceUtil.updateSecurityPatch(this);
		}
	}

	@Override
	public SecurityPatch toEscapedModel() {
		return (SecurityPatch)ProxyUtil.newProxyInstance(SecurityPatch.class.getClassLoader(),
			new Class[] { SecurityPatch.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SecurityPatchClp clone = new SecurityPatchClp();

		clone.setSecurityPatchId(getSecurityPatchId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setAccountEntryId(getAccountEntryId());
		clone.setTicketAttachmentId(getTicketAttachmentId());
		clone.setPortletId(getPortletId());
		clone.setEnvLFR(getEnvLFR());
		clone.setName(getName());
		clone.setFileName(getFileName());

		return clone;
	}

	@Override
	public int compareTo(SecurityPatch securityPatch) {
		long primaryKey = securityPatch.getPrimaryKey();

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

		if (!(obj instanceof SecurityPatchClp)) {
			return false;
		}

		SecurityPatchClp securityPatch = (SecurityPatchClp)obj;

		long primaryKey = securityPatch.getPrimaryKey();

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

		sb.append("{securityPatchId=");
		sb.append(getSecurityPatchId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", accountEntryId=");
		sb.append(getAccountEntryId());
		sb.append(", ticketAttachmentId=");
		sb.append(getTicketAttachmentId());
		sb.append(", portletId=");
		sb.append(getPortletId());
		sb.append(", envLFR=");
		sb.append(getEnvLFR());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", fileName=");
		sb.append(getFileName());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.SecurityPatch");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>securityPatchId</column-name><column-value><![CDATA[");
		sb.append(getSecurityPatchId());
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
			"<column><column-name>accountEntryId</column-name><column-value><![CDATA[");
		sb.append(getAccountEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ticketAttachmentId</column-name><column-value><![CDATA[");
		sb.append(getTicketAttachmentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portletId</column-name><column-value><![CDATA[");
		sb.append(getPortletId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>envLFR</column-name><column-value><![CDATA[");
		sb.append(getEnvLFR());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileName</column-name><column-value><![CDATA[");
		sb.append(getFileName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _securityPatchId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _accountEntryId;
	private long _ticketAttachmentId;
	private String _portletId;
	private int _envLFR;
	private String _name;
	private String _fileName;
	private BaseModel<?> _securityPatchRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}