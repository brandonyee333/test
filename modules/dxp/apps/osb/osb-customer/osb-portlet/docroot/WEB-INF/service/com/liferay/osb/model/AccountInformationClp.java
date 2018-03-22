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

import com.liferay.osb.service.AccountInformationLocalServiceUtil;
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
public class AccountInformationClp extends BaseModelImpl<AccountInformation>
	implements AccountInformation {
	public AccountInformationClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return AccountInformation.class;
	}

	@Override
	public String getModelClassName() {
		return AccountInformation.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _accountInformationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountInformationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountInformationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountInformationId", getAccountInformationId());
		attributes.put("modifiedUserId", getModifiedUserId());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("accountProjectId", getAccountProjectId());
		attributes.put("fieldId", getFieldId());
		attributes.put("data", getData());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountInformationId = (Long)attributes.get("accountInformationId");

		if (accountInformationId != null) {
			setAccountInformationId(accountInformationId);
		}

		Long modifiedUserId = (Long)attributes.get("modifiedUserId");

		if (modifiedUserId != null) {
			setModifiedUserId(modifiedUserId);
		}

		String modifiedUserName = (String)attributes.get("modifiedUserName");

		if (modifiedUserName != null) {
			setModifiedUserName(modifiedUserName);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Long accountProjectId = (Long)attributes.get("accountProjectId");

		if (accountProjectId != null) {
			setAccountProjectId(accountProjectId);
		}

		Integer fieldId = (Integer)attributes.get("fieldId");

		if (fieldId != null) {
			setFieldId(fieldId);
		}

		String data = (String)attributes.get("data");

		if (data != null) {
			setData(data);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getAccountInformationId() {
		return _accountInformationId;
	}

	@Override
	public void setAccountInformationId(long accountInformationId) {
		_accountInformationId = accountInformationId;

		if (_accountInformationRemoteModel != null) {
			try {
				Class<?> clazz = _accountInformationRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountInformationId",
						long.class);

				method.invoke(_accountInformationRemoteModel,
					accountInformationId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getModifiedUserId() {
		return _modifiedUserId;
	}

	@Override
	public void setModifiedUserId(long modifiedUserId) {
		_modifiedUserId = modifiedUserId;

		if (_accountInformationRemoteModel != null) {
			try {
				Class<?> clazz = _accountInformationRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedUserId", long.class);

				method.invoke(_accountInformationRemoteModel, modifiedUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getModifiedUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getModifiedUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setModifiedUserUuid(String modifiedUserUuid) {
	}

	@Override
	public String getModifiedUserName() {
		return _modifiedUserName;
	}

	@Override
	public void setModifiedUserName(String modifiedUserName) {
		_modifiedUserName = modifiedUserName;

		if (_accountInformationRemoteModel != null) {
			try {
				Class<?> clazz = _accountInformationRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedUserName",
						String.class);

				method.invoke(_accountInformationRemoteModel, modifiedUserName);
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

		if (_accountInformationRemoteModel != null) {
			try {
				Class<?> clazz = _accountInformationRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_accountInformationRemoteModel, modifiedDate);
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

		if (_accountInformationRemoteModel != null) {
			try {
				Class<?> clazz = _accountInformationRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryId", long.class);

				method.invoke(_accountInformationRemoteModel, accountEntryId);
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

		if (_accountInformationRemoteModel != null) {
			try {
				Class<?> clazz = _accountInformationRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountProjectId",
						long.class);

				method.invoke(_accountInformationRemoteModel, accountProjectId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getFieldId() {
		return _fieldId;
	}

	@Override
	public void setFieldId(int fieldId) {
		_fieldId = fieldId;

		if (_accountInformationRemoteModel != null) {
			try {
				Class<?> clazz = _accountInformationRemoteModel.getClass();

				Method method = clazz.getMethod("setFieldId", int.class);

				method.invoke(_accountInformationRemoteModel, fieldId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getData() {
		return _data;
	}

	@Override
	public void setData(String data) {
		_data = data;

		if (_accountInformationRemoteModel != null) {
			try {
				Class<?> clazz = _accountInformationRemoteModel.getClass();

				Method method = clazz.getMethod("setData", String.class);

				method.invoke(_accountInformationRemoteModel, data);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAccountInformationRemoteModel() {
		return _accountInformationRemoteModel;
	}

	public void setAccountInformationRemoteModel(
		BaseModel<?> accountInformationRemoteModel) {
		_accountInformationRemoteModel = accountInformationRemoteModel;
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

		Class<?> remoteModelClass = _accountInformationRemoteModel.getClass();

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

		Object returnValue = method.invoke(_accountInformationRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			AccountInformationLocalServiceUtil.addAccountInformation(this);
		}
		else {
			AccountInformationLocalServiceUtil.updateAccountInformation(this);
		}
	}

	@Override
	public AccountInformation toEscapedModel() {
		return (AccountInformation)ProxyUtil.newProxyInstance(AccountInformation.class.getClassLoader(),
			new Class[] { AccountInformation.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AccountInformationClp clone = new AccountInformationClp();

		clone.setAccountInformationId(getAccountInformationId());
		clone.setModifiedUserId(getModifiedUserId());
		clone.setModifiedUserName(getModifiedUserName());
		clone.setModifiedDate(getModifiedDate());
		clone.setAccountEntryId(getAccountEntryId());
		clone.setAccountProjectId(getAccountProjectId());
		clone.setFieldId(getFieldId());
		clone.setData(getData());

		return clone;
	}

	@Override
	public int compareTo(AccountInformation accountInformation) {
		long primaryKey = accountInformation.getPrimaryKey();

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

		if (!(obj instanceof AccountInformationClp)) {
			return false;
		}

		AccountInformationClp accountInformation = (AccountInformationClp)obj;

		long primaryKey = accountInformation.getPrimaryKey();

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
		StringBundler sb = new StringBundler(17);

		sb.append("{accountInformationId=");
		sb.append(getAccountInformationId());
		sb.append(", modifiedUserId=");
		sb.append(getModifiedUserId());
		sb.append(", modifiedUserName=");
		sb.append(getModifiedUserName());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", accountEntryId=");
		sb.append(getAccountEntryId());
		sb.append(", accountProjectId=");
		sb.append(getAccountProjectId());
		sb.append(", fieldId=");
		sb.append(getFieldId());
		sb.append(", data=");
		sb.append(getData());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AccountInformation");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>accountInformationId</column-name><column-value><![CDATA[");
		sb.append(getAccountInformationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedUserId</column-name><column-value><![CDATA[");
		sb.append(getModifiedUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedUserName</column-name><column-value><![CDATA[");
		sb.append(getModifiedUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
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
			"<column><column-name>fieldId</column-name><column-value><![CDATA[");
		sb.append(getFieldId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>data</column-name><column-value><![CDATA[");
		sb.append(getData());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _accountInformationId;
	private long _modifiedUserId;
	private String _modifiedUserName;
	private Date _modifiedDate;
	private long _accountEntryId;
	private long _accountProjectId;
	private int _fieldId;
	private String _data;
	private BaseModel<?> _accountInformationRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}