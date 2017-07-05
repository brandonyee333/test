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

import com.liferay.osb.service.AccountEntryLanguageLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class AccountEntryLanguageClp extends BaseModelImpl<AccountEntryLanguage>
	implements AccountEntryLanguage {
	public AccountEntryLanguageClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return AccountEntryLanguage.class;
	}

	@Override
	public String getModelClassName() {
		return AccountEntryLanguage.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _accountEntryLanguageId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountEntryLanguageId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountEntryLanguageId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountEntryLanguageId", getAccountEntryLanguageId());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("languageId", getLanguageId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountEntryLanguageId = (Long)attributes.get(
				"accountEntryLanguageId");

		if (accountEntryLanguageId != null) {
			setAccountEntryLanguageId(accountEntryLanguageId);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		String languageId = (String)attributes.get("languageId");

		if (languageId != null) {
			setLanguageId(languageId);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getAccountEntryLanguageId() {
		return _accountEntryLanguageId;
	}

	@Override
	public void setAccountEntryLanguageId(long accountEntryLanguageId) {
		_accountEntryLanguageId = accountEntryLanguageId;

		if (_accountEntryLanguageRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryLanguageRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryLanguageId",
						long.class);

				method.invoke(_accountEntryLanguageRemoteModel,
					accountEntryLanguageId);
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

		if (_accountEntryLanguageRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryLanguageRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryId", long.class);

				method.invoke(_accountEntryLanguageRemoteModel, accountEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getLanguageId() {
		return _languageId;
	}

	@Override
	public void setLanguageId(String languageId) {
		_languageId = languageId;

		if (_accountEntryLanguageRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryLanguageRemoteModel.getClass();

				Method method = clazz.getMethod("setLanguageId", String.class);

				method.invoke(_accountEntryLanguageRemoteModel, languageId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAccountEntryLanguageRemoteModel() {
		return _accountEntryLanguageRemoteModel;
	}

	public void setAccountEntryLanguageRemoteModel(
		BaseModel<?> accountEntryLanguageRemoteModel) {
		_accountEntryLanguageRemoteModel = accountEntryLanguageRemoteModel;
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

		Class<?> remoteModelClass = _accountEntryLanguageRemoteModel.getClass();

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

		Object returnValue = method.invoke(_accountEntryLanguageRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			AccountEntryLanguageLocalServiceUtil.addAccountEntryLanguage(this);
		}
		else {
			AccountEntryLanguageLocalServiceUtil.updateAccountEntryLanguage(this);
		}
	}

	@Override
	public AccountEntryLanguage toEscapedModel() {
		return (AccountEntryLanguage)ProxyUtil.newProxyInstance(AccountEntryLanguage.class.getClassLoader(),
			new Class[] { AccountEntryLanguage.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AccountEntryLanguageClp clone = new AccountEntryLanguageClp();

		clone.setAccountEntryLanguageId(getAccountEntryLanguageId());
		clone.setAccountEntryId(getAccountEntryId());
		clone.setLanguageId(getLanguageId());

		return clone;
	}

	@Override
	public int compareTo(AccountEntryLanguage accountEntryLanguage) {
		int value = 0;

		value = getLanguageId().compareTo(accountEntryLanguage.getLanguageId());

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

		if (!(obj instanceof AccountEntryLanguageClp)) {
			return false;
		}

		AccountEntryLanguageClp accountEntryLanguage = (AccountEntryLanguageClp)obj;

		long primaryKey = accountEntryLanguage.getPrimaryKey();

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
		StringBundler sb = new StringBundler(7);

		sb.append("{accountEntryLanguageId=");
		sb.append(getAccountEntryLanguageId());
		sb.append(", accountEntryId=");
		sb.append(getAccountEntryId());
		sb.append(", languageId=");
		sb.append(getLanguageId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AccountEntryLanguage");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>accountEntryLanguageId</column-name><column-value><![CDATA[");
		sb.append(getAccountEntryLanguageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountEntryId</column-name><column-value><![CDATA[");
		sb.append(getAccountEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>languageId</column-name><column-value><![CDATA[");
		sb.append(getLanguageId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _accountEntryLanguageId;
	private long _accountEntryId;
	private String _languageId;
	private BaseModel<?> _accountEntryLanguageRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}