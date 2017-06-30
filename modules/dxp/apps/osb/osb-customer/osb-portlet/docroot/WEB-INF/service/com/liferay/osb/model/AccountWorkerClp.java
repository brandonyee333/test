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

import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class AccountWorkerClp extends BaseModelImpl<AccountWorker>
	implements AccountWorker {
	public AccountWorkerClp() {
	}

	public Class<?> getModelClass() {
		return AccountWorker.class;
	}

	public String getModelClassName() {
		return AccountWorker.class.getName();
	}

	public long getPrimaryKey() {
		return _accountWorkerId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAccountWorkerId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_accountWorkerId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountWorkerId", getAccountWorkerId());
		attributes.put("userId", getUserId());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("role", getRole());
		attributes.put("notifications", getNotifications());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountWorkerId = (Long)attributes.get("accountWorkerId");

		if (accountWorkerId != null) {
			setAccountWorkerId(accountWorkerId);
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
	}

	public long getAccountWorkerId() {
		return _accountWorkerId;
	}

	public void setAccountWorkerId(long accountWorkerId) {
		_accountWorkerId = accountWorkerId;

		if (_accountWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _accountWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountWorkerId", long.class);

				method.invoke(_accountWorkerRemoteModel, accountWorkerId);
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

		if (_accountWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _accountWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_accountWorkerRemoteModel, userId);
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

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;

		if (_accountWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _accountWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryId", long.class);

				method.invoke(_accountWorkerRemoteModel, accountEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getRole() {
		return _role;
	}

	public void setRole(int role) {
		_role = role;

		if (_accountWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _accountWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setRole", int.class);

				method.invoke(_accountWorkerRemoteModel, role);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getNotifications() {
		return _notifications;
	}

	public void setNotifications(int notifications) {
		_notifications = notifications;

		if (_accountWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _accountWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setNotifications", int.class);

				method.invoke(_accountWorkerRemoteModel, notifications);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public com.liferay.osb.model.AccountEntry getAccountEntry() {
		try {
			String methodName = "getAccountEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.AccountEntry returnObj = (com.liferay.osb.model.AccountEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

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

	public BaseModel<?> getAccountWorkerRemoteModel() {
		return _accountWorkerRemoteModel;
	}

	public void setAccountWorkerRemoteModel(
		BaseModel<?> accountWorkerRemoteModel) {
		_accountWorkerRemoteModel = accountWorkerRemoteModel;
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

		Class<?> remoteModelClass = _accountWorkerRemoteModel.getClass();

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

		Object returnValue = method.invoke(_accountWorkerRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AccountWorkerLocalServiceUtil.addAccountWorker(this);
		}
		else {
			AccountWorkerLocalServiceUtil.updateAccountWorker(this);
		}
	}

	@Override
	public AccountWorker toEscapedModel() {
		return (AccountWorker)ProxyUtil.newProxyInstance(AccountWorker.class.getClassLoader(),
			new Class[] { AccountWorker.class }, new AutoEscapeBeanHandler(this));
	}

	public AccountWorker toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AccountWorkerClp clone = new AccountWorkerClp();

		clone.setAccountWorkerId(getAccountWorkerId());
		clone.setUserId(getUserId());
		clone.setAccountEntryId(getAccountEntryId());
		clone.setRole(getRole());
		clone.setNotifications(getNotifications());

		return clone;
	}

	public int compareTo(AccountWorker accountWorker) {
		long primaryKey = accountWorker.getPrimaryKey();

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

		if (!(obj instanceof AccountWorkerClp)) {
			return false;
		}

		AccountWorkerClp accountWorker = (AccountWorkerClp)obj;

		long primaryKey = accountWorker.getPrimaryKey();

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
		StringBundler sb = new StringBundler(11);

		sb.append("{accountWorkerId=");
		sb.append(getAccountWorkerId());
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

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AccountWorker");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>accountWorkerId</column-name><column-value><![CDATA[");
		sb.append(getAccountWorkerId());
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

	private long _accountWorkerId;
	private long _userId;
	private String _userUuid;
	private long _accountEntryId;
	private int _role;
	private int _notifications;
	private BaseModel<?> _accountWorkerRemoteModel;
}