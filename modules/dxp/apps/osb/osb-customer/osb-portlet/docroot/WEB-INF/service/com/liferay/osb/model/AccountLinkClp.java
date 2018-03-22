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

import com.liferay.osb.service.AccountLinkLocalServiceUtil;
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
public class AccountLinkClp extends BaseModelImpl<AccountLink>
	implements AccountLink {
	public AccountLinkClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return AccountLink.class;
	}

	@Override
	public String getModelClassName() {
		return AccountLink.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _accountLinkId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountLinkId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountLinkId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountLinkId", getAccountLinkId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("url", getUrl());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountLinkId = (Long)attributes.get("accountLinkId");

		if (accountLinkId != null) {
			setAccountLinkId(accountLinkId);
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

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getAccountLinkId() {
		return _accountLinkId;
	}

	@Override
	public void setAccountLinkId(long accountLinkId) {
		_accountLinkId = accountLinkId;

		if (_accountLinkRemoteModel != null) {
			try {
				Class<?> clazz = _accountLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountLinkId", long.class);

				method.invoke(_accountLinkRemoteModel, accountLinkId);
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

		if (_accountLinkRemoteModel != null) {
			try {
				Class<?> clazz = _accountLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_accountLinkRemoteModel, userId);
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

		if (_accountLinkRemoteModel != null) {
			try {
				Class<?> clazz = _accountLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_accountLinkRemoteModel, userName);
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

		if (_accountLinkRemoteModel != null) {
			try {
				Class<?> clazz = _accountLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_accountLinkRemoteModel, createDate);
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

		if (_accountLinkRemoteModel != null) {
			try {
				Class<?> clazz = _accountLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryId", long.class);

				method.invoke(_accountLinkRemoteModel, accountEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUrl() {
		return _url;
	}

	@Override
	public void setUrl(String url) {
		_url = url;

		if (_accountLinkRemoteModel != null) {
			try {
				Class<?> clazz = _accountLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setUrl", String.class);

				method.invoke(_accountLinkRemoteModel, url);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAccountLinkRemoteModel() {
		return _accountLinkRemoteModel;
	}

	public void setAccountLinkRemoteModel(BaseModel<?> accountLinkRemoteModel) {
		_accountLinkRemoteModel = accountLinkRemoteModel;
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

		Class<?> remoteModelClass = _accountLinkRemoteModel.getClass();

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

		Object returnValue = method.invoke(_accountLinkRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			AccountLinkLocalServiceUtil.addAccountLink(this);
		}
		else {
			AccountLinkLocalServiceUtil.updateAccountLink(this);
		}
	}

	@Override
	public AccountLink toEscapedModel() {
		return (AccountLink)ProxyUtil.newProxyInstance(AccountLink.class.getClassLoader(),
			new Class[] { AccountLink.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AccountLinkClp clone = new AccountLinkClp();

		clone.setAccountLinkId(getAccountLinkId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setAccountEntryId(getAccountEntryId());
		clone.setUrl(getUrl());

		return clone;
	}

	@Override
	public int compareTo(AccountLink accountLink) {
		long primaryKey = accountLink.getPrimaryKey();

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

		if (!(obj instanceof AccountLinkClp)) {
			return false;
		}

		AccountLinkClp accountLink = (AccountLinkClp)obj;

		long primaryKey = accountLink.getPrimaryKey();

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
		StringBundler sb = new StringBundler(13);

		sb.append("{accountLinkId=");
		sb.append(getAccountLinkId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", accountEntryId=");
		sb.append(getAccountEntryId());
		sb.append(", url=");
		sb.append(getUrl());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AccountLink");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>accountLinkId</column-name><column-value><![CDATA[");
		sb.append(getAccountLinkId());
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
			"<column><column-name>url</column-name><column-value><![CDATA[");
		sb.append(getUrl());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _accountLinkId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _accountEntryId;
	private String _url;
	private BaseModel<?> _accountLinkRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}