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

import com.liferay.osb.service.AccountEnvironmentAttachmentLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

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
public class AccountEnvironmentAttachmentClp extends BaseModelImpl<AccountEnvironmentAttachment>
	implements AccountEnvironmentAttachment {
	public AccountEnvironmentAttachmentClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return AccountEnvironmentAttachment.class;
	}

	@Override
	public String getModelClassName() {
		return AccountEnvironmentAttachment.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _accountEnvironmentAttachmentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountEnvironmentAttachmentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountEnvironmentAttachmentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountEnvironmentAttachmentId",
			getAccountEnvironmentAttachmentId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEnvironmentId", getAccountEnvironmentId());
		attributes.put("fileName", getFileName());
		attributes.put("fileSize", getFileSize());
		attributes.put("type", getType());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountEnvironmentAttachmentId = (Long)attributes.get(
				"accountEnvironmentAttachmentId");

		if (accountEnvironmentAttachmentId != null) {
			setAccountEnvironmentAttachmentId(accountEnvironmentAttachmentId);
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

		Long accountEnvironmentId = (Long)attributes.get("accountEnvironmentId");

		if (accountEnvironmentId != null) {
			setAccountEnvironmentId(accountEnvironmentId);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		Long fileSize = (Long)attributes.get("fileSize");

		if (fileSize != null) {
			setFileSize(fileSize);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getAccountEnvironmentAttachmentId() {
		return _accountEnvironmentAttachmentId;
	}

	@Override
	public void setAccountEnvironmentAttachmentId(
		long accountEnvironmentAttachmentId) {
		_accountEnvironmentAttachmentId = accountEnvironmentAttachmentId;

		if (_accountEnvironmentAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEnvironmentAttachmentId",
						long.class);

				method.invoke(_accountEnvironmentAttachmentRemoteModel,
					accountEnvironmentAttachmentId);
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

		if (_accountEnvironmentAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_accountEnvironmentAttachmentRemoteModel, userId);
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

		if (_accountEnvironmentAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_accountEnvironmentAttachmentRemoteModel, userName);
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

		if (_accountEnvironmentAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_accountEnvironmentAttachmentRemoteModel,
					createDate);
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

		if (_accountEnvironmentAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_accountEnvironmentAttachmentRemoteModel,
					modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAccountEnvironmentId() {
		return _accountEnvironmentId;
	}

	@Override
	public void setAccountEnvironmentId(long accountEnvironmentId) {
		_accountEnvironmentId = accountEnvironmentId;

		if (_accountEnvironmentAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEnvironmentId",
						long.class);

				method.invoke(_accountEnvironmentAttachmentRemoteModel,
					accountEnvironmentId);
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

		if (_accountEnvironmentAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setFileName", String.class);

				method.invoke(_accountEnvironmentAttachmentRemoteModel, fileName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getFileSize() {
		return _fileSize;
	}

	@Override
	public void setFileSize(long fileSize) {
		_fileSize = fileSize;

		if (_accountEnvironmentAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setFileSize", long.class);

				method.invoke(_accountEnvironmentAttachmentRemoteModel, fileSize);
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

		if (_accountEnvironmentAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_accountEnvironmentAttachmentRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean fileExists() {
		try {
			String methodName = "fileExists";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public int getContentLength() {
		try {
			String methodName = "getContentLength";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Integer returnObj = (Integer)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getFileDir() {
		try {
			String methodName = "getFileDir";

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

	public BaseModel<?> getAccountEnvironmentAttachmentRemoteModel() {
		return _accountEnvironmentAttachmentRemoteModel;
	}

	public void setAccountEnvironmentAttachmentRemoteModel(
		BaseModel<?> accountEnvironmentAttachmentRemoteModel) {
		_accountEnvironmentAttachmentRemoteModel = accountEnvironmentAttachmentRemoteModel;
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

		Class<?> remoteModelClass = _accountEnvironmentAttachmentRemoteModel.getClass();

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

		Object returnValue = method.invoke(_accountEnvironmentAttachmentRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			AccountEnvironmentAttachmentLocalServiceUtil.addAccountEnvironmentAttachment(this);
		}
		else {
			AccountEnvironmentAttachmentLocalServiceUtil.updateAccountEnvironmentAttachment(this);
		}
	}

	@Override
	public AccountEnvironmentAttachment toEscapedModel() {
		return (AccountEnvironmentAttachment)ProxyUtil.newProxyInstance(AccountEnvironmentAttachment.class.getClassLoader(),
			new Class[] { AccountEnvironmentAttachment.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AccountEnvironmentAttachmentClp clone = new AccountEnvironmentAttachmentClp();

		clone.setAccountEnvironmentAttachmentId(getAccountEnvironmentAttachmentId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAccountEnvironmentId(getAccountEnvironmentId());
		clone.setFileName(getFileName());
		clone.setFileSize(getFileSize());
		clone.setType(getType());

		return clone;
	}

	@Override
	public int compareTo(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {
		long primaryKey = accountEnvironmentAttachment.getPrimaryKey();

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

		if (!(obj instanceof AccountEnvironmentAttachmentClp)) {
			return false;
		}

		AccountEnvironmentAttachmentClp accountEnvironmentAttachment = (AccountEnvironmentAttachmentClp)obj;

		long primaryKey = accountEnvironmentAttachment.getPrimaryKey();

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
		StringBundler sb = new StringBundler(19);

		sb.append("{accountEnvironmentAttachmentId=");
		sb.append(getAccountEnvironmentAttachmentId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", accountEnvironmentId=");
		sb.append(getAccountEnvironmentId());
		sb.append(", fileName=");
		sb.append(getFileName());
		sb.append(", fileSize=");
		sb.append(getFileSize());
		sb.append(", type=");
		sb.append(getType());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AccountEnvironmentAttachment");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>accountEnvironmentAttachmentId</column-name><column-value><![CDATA[");
		sb.append(getAccountEnvironmentAttachmentId());
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
			"<column><column-name>accountEnvironmentId</column-name><column-value><![CDATA[");
		sb.append(getAccountEnvironmentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileName</column-name><column-value><![CDATA[");
		sb.append(getFileName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileSize</column-name><column-value><![CDATA[");
		sb.append(getFileSize());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _accountEnvironmentAttachmentId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _accountEnvironmentId;
	private String _fileName;
	private long _fileSize;
	private int _type;
	private BaseModel<?> _accountEnvironmentAttachmentRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}