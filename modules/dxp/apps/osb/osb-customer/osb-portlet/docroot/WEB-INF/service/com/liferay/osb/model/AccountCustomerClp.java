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

import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
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

import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class AccountCustomerClp extends BaseModelImpl<AccountCustomer>
	implements AccountCustomer {
	public AccountCustomerClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return AccountCustomer.class;
	}

	@Override
	public String getModelClassName() {
		return AccountCustomer.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _accountCustomerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountCustomerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountCustomerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountCustomerId", getAccountCustomerId());
		attributes.put("userId", getUserId());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("role", getRole());
		attributes.put("notifications", getNotifications());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountCustomerId = (Long)attributes.get("accountCustomerId");

		if (accountCustomerId != null) {
			setAccountCustomerId(accountCustomerId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Integer role = (Integer)attributes.get("role");

		if (role != null) {
			setRole(role);
		}

		Integer notifications = (Integer)attributes.get("notifications");

		if (notifications != null) {
			setNotifications(notifications);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getAccountCustomerId() {
		return _accountCustomerId;
	}

	@Override
	public void setAccountCustomerId(long accountCustomerId) {
		_accountCustomerId = accountCustomerId;

		if (_accountCustomerRemoteModel != null) {
			try {
				Class<?> clazz = _accountCustomerRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountCustomerId",
						long.class);

				method.invoke(_accountCustomerRemoteModel, accountCustomerId);
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

		if (_accountCustomerRemoteModel != null) {
			try {
				Class<?> clazz = _accountCustomerRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_accountCustomerRemoteModel, userId);
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
	public long getAccountEntryId() {
		return _accountEntryId;
	}

	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;

		if (_accountCustomerRemoteModel != null) {
			try {
				Class<?> clazz = _accountCustomerRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryId", long.class);

				method.invoke(_accountCustomerRemoteModel, accountEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getRole() {
		return _role;
	}

	@Override
	public void setRole(int role) {
		_role = role;

		if (_accountCustomerRemoteModel != null) {
			try {
				Class<?> clazz = _accountCustomerRemoteModel.getClass();

				Method method = clazz.getMethod("setRole", int.class);

				method.invoke(_accountCustomerRemoteModel, role);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getNotifications() {
		return _notifications;
	}

	@Override
	public void setNotifications(int notifications) {
		_notifications = notifications;

		if (_accountCustomerRemoteModel != null) {
			try {
				Class<?> clazz = _accountCustomerRemoteModel.getClass();

				Method method = clazz.getMethod("setNotifications", int.class);

				method.invoke(_accountCustomerRemoteModel, notifications);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public AccountEntry getAccountEntry() {
		try {
			String methodName = "getAccountEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			AccountEntry returnObj = (AccountEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getNotificationsLabel() {
		try {
			String methodName = "getNotificationsLabel";

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
	public java.lang.String getRoleLabel() {
		try {
			String methodName = "getRoleLabel";

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
	public boolean hasNotificationsOn() {
		try {
			String methodName = "hasNotificationsOn";

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

	public BaseModel<?> getAccountCustomerRemoteModel() {
		return _accountCustomerRemoteModel;
	}

	public void setAccountCustomerRemoteModel(
		BaseModel<?> accountCustomerRemoteModel) {
		_accountCustomerRemoteModel = accountCustomerRemoteModel;
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

		Class<?> remoteModelClass = _accountCustomerRemoteModel.getClass();

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

		Object returnValue = method.invoke(_accountCustomerRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			AccountCustomerLocalServiceUtil.addAccountCustomer(this);
		}
		else {
			AccountCustomerLocalServiceUtil.updateAccountCustomer(this);
		}
	}

	@Override
	public AccountCustomer toEscapedModel() {
		return (AccountCustomer)ProxyUtil.newProxyInstance(AccountCustomer.class.getClassLoader(),
			new Class[] { AccountCustomer.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AccountCustomerClp clone = new AccountCustomerClp();

		clone.setAccountCustomerId(getAccountCustomerId());
		clone.setUserId(getUserId());
		clone.setAccountEntryId(getAccountEntryId());
		clone.setRole(getRole());
		clone.setNotifications(getNotifications());

		return clone;
	}

	@Override
	public int compareTo(AccountCustomer accountCustomer) {
		long primaryKey = accountCustomer.getPrimaryKey();

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

		if (!(obj instanceof AccountCustomerClp)) {
			return false;
		}

		AccountCustomerClp accountCustomer = (AccountCustomerClp)obj;

		long primaryKey = accountCustomer.getPrimaryKey();

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
		StringBundler sb = new StringBundler(11);

		sb.append("{accountCustomerId=");
		sb.append(getAccountCustomerId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", accountEntryId=");
		sb.append(getAccountEntryId());
		sb.append(", role=");
		sb.append(getRole());
		sb.append(", notifications=");
		sb.append(getNotifications());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AccountCustomer");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>accountCustomerId</column-name><column-value><![CDATA[");
		sb.append(getAccountCustomerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountEntryId</column-name><column-value><![CDATA[");
		sb.append(getAccountEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>role</column-name><column-value><![CDATA[");
		sb.append(getRole());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notifications</column-name><column-value><![CDATA[");
		sb.append(getNotifications());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _accountCustomerId;
	private long _userId;
	private long _accountEntryId;
	private int _role;
	private int _notifications;
	private BaseModel<?> _accountCustomerRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}