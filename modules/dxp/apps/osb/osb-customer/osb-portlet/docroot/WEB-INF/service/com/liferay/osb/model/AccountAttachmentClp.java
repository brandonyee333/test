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

import com.liferay.osb.service.AccountAttachmentLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
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
public class AccountAttachmentClp extends BaseModelImpl<AccountAttachment>
	implements AccountAttachment {
	public AccountAttachmentClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return AccountAttachment.class;
	}

	@Override
	public String getModelClassName() {
		return AccountAttachment.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _accountAttachmentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountAttachmentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountAttachmentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountAttachmentId", getAccountAttachmentId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("accountProjectId", getAccountProjectId());
		attributes.put("fileName", getFileName());
		attributes.put("fileSize", getFileSize());
		attributes.put("type", getType());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountAttachmentId = (Long)attributes.get("accountAttachmentId");

		if (accountAttachmentId != null) {
			setAccountAttachmentId(accountAttachmentId);
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

		Long accountProjectId = (Long)attributes.get("accountProjectId");

		if (accountProjectId != null) {
			setAccountProjectId(accountProjectId);
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
	public long getAccountAttachmentId() {
		return _accountAttachmentId;
	}

	@Override
	public void setAccountAttachmentId(long accountAttachmentId) {
		_accountAttachmentId = accountAttachmentId;

		if (_accountAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountAttachmentId",
						long.class);

				method.invoke(_accountAttachmentRemoteModel, accountAttachmentId);
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

		if (_accountAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_accountAttachmentRemoteModel, userId);
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

		if (_accountAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_accountAttachmentRemoteModel, userName);
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

		if (_accountAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_accountAttachmentRemoteModel, createDate);
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

		if (_accountAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryId", long.class);

				method.invoke(_accountAttachmentRemoteModel, accountEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAccountProjectId() {
		return _accountProjectId;
	}

	@Override
	public void setAccountProjectId(long accountProjectId) {
		_accountProjectId = accountProjectId;

		if (_accountAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountProjectId",
						long.class);

				method.invoke(_accountAttachmentRemoteModel, accountProjectId);
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

		if (_accountAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setFileName", String.class);

				method.invoke(_accountAttachmentRemoteModel, fileName);
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

		if (_accountAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setFileSize", long.class);

				method.invoke(_accountAttachmentRemoteModel, fileSize);
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

		if (_accountAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_accountAttachmentRemoteModel, type);
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

	public BaseModel<?> getAccountAttachmentRemoteModel() {
		return _accountAttachmentRemoteModel;
	}

	public void setAccountAttachmentRemoteModel(
		BaseModel<?> accountAttachmentRemoteModel) {
		_accountAttachmentRemoteModel = accountAttachmentRemoteModel;
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

		Class<?> remoteModelClass = _accountAttachmentRemoteModel.getClass();

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

		Object returnValue = method.invoke(_accountAttachmentRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			AccountAttachmentLocalServiceUtil.addAccountAttachment(this);
		}
		else {
			AccountAttachmentLocalServiceUtil.updateAccountAttachment(this);
		}
	}

	@Override
	public AccountAttachment toEscapedModel() {
		return (AccountAttachment)ProxyUtil.newProxyInstance(AccountAttachment.class.getClassLoader(),
			new Class[] { AccountAttachment.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AccountAttachmentClp clone = new AccountAttachmentClp();

		clone.setAccountAttachmentId(getAccountAttachmentId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setAccountEntryId(getAccountEntryId());
		clone.setAccountProjectId(getAccountProjectId());
		clone.setFileName(getFileName());
		clone.setFileSize(getFileSize());
		clone.setType(getType());

		return clone;
	}

	@Override
	public int compareTo(AccountAttachment accountAttachment) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				accountAttachment.getCreateDate());

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

		if (!(obj instanceof AccountAttachmentClp)) {
			return false;
		}

		AccountAttachmentClp accountAttachment = (AccountAttachmentClp)obj;

		long primaryKey = accountAttachment.getPrimaryKey();

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

		sb.append("{accountAttachmentId=");
		sb.append(getAccountAttachmentId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", accountEntryId=");
		sb.append(getAccountEntryId());
		sb.append(", accountProjectId=");
		sb.append(getAccountProjectId());
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
		sb.append("com.liferay.osb.model.AccountAttachment");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>accountAttachmentId</column-name><column-value><![CDATA[");
		sb.append(getAccountAttachmentId());
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
			"<column><column-name>accountProjectId</column-name><column-value><![CDATA[");
		sb.append(getAccountProjectId());
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

	private long _accountAttachmentId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _accountEntryId;
	private long _accountProjectId;
	private String _fileName;
	private long _fileSize;
	private int _type;
	private BaseModel<?> _accountAttachmentRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}