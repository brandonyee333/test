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
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;

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
public class PartnerWorkerClp extends BaseModelImpl<PartnerWorker>
	implements PartnerWorker {
	public PartnerWorkerClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return PartnerWorker.class;
	}

	@Override
	public String getModelClassName() {
		return PartnerWorker.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _partnerWorkerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPartnerWorkerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _partnerWorkerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("partnerWorkerId", getPartnerWorkerId());
		attributes.put("userId", getUserId());
		attributes.put("partnerEntryId", getPartnerEntryId());
		attributes.put("role", getRole());
		attributes.put("notifications", getNotifications());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long partnerWorkerId = (Long)attributes.get("partnerWorkerId");

		if (partnerWorkerId != null) {
			setPartnerWorkerId(partnerWorkerId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long partnerEntryId = (Long)attributes.get("partnerEntryId");

		if (partnerEntryId != null) {
			setPartnerEntryId(partnerEntryId);
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
	public long getPartnerWorkerId() {
		return _partnerWorkerId;
	}

	@Override
	public void setPartnerWorkerId(long partnerWorkerId) {
		_partnerWorkerId = partnerWorkerId;

		if (_partnerWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _partnerWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setPartnerWorkerId", long.class);

				method.invoke(_partnerWorkerRemoteModel, partnerWorkerId);
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

		if (_partnerWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _partnerWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_partnerWorkerRemoteModel, userId);
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
	public long getPartnerEntryId() {
		return _partnerEntryId;
	}

	@Override
	public void setPartnerEntryId(long partnerEntryId) {
		_partnerEntryId = partnerEntryId;

		if (_partnerWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _partnerWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setPartnerEntryId", long.class);

				method.invoke(_partnerWorkerRemoteModel, partnerEntryId);
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

		if (_partnerWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _partnerWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setRole", int.class);

				method.invoke(_partnerWorkerRemoteModel, role);
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

		if (_partnerWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _partnerWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setNotifications", int.class);

				method.invoke(_partnerWorkerRemoteModel, notifications);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
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
	public PartnerEntry getPartnerEntry() {
		try {
			String methodName = "getPartnerEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			PartnerEntry returnObj = (PartnerEntry)invokeOnRemoteModel(methodName,
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
	public boolean isActive() {
		try {
			String methodName = "isActive";

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

	public BaseModel<?> getPartnerWorkerRemoteModel() {
		return _partnerWorkerRemoteModel;
	}

	public void setPartnerWorkerRemoteModel(
		BaseModel<?> partnerWorkerRemoteModel) {
		_partnerWorkerRemoteModel = partnerWorkerRemoteModel;
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

		Class<?> remoteModelClass = _partnerWorkerRemoteModel.getClass();

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

		Object returnValue = method.invoke(_partnerWorkerRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			PartnerWorkerLocalServiceUtil.addPartnerWorker(this);
		}
		else {
			PartnerWorkerLocalServiceUtil.updatePartnerWorker(this);
		}
	}

	@Override
	public PartnerWorker toEscapedModel() {
		return (PartnerWorker)ProxyUtil.newProxyInstance(PartnerWorker.class.getClassLoader(),
			new Class[] { PartnerWorker.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		PartnerWorkerClp clone = new PartnerWorkerClp();

		clone.setPartnerWorkerId(getPartnerWorkerId());
		clone.setUserId(getUserId());
		clone.setPartnerEntryId(getPartnerEntryId());
		clone.setRole(getRole());
		clone.setNotifications(getNotifications());

		return clone;
	}

	@Override
	public int compareTo(PartnerWorker partnerWorker) {
		long primaryKey = partnerWorker.getPrimaryKey();

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

		if (!(obj instanceof PartnerWorkerClp)) {
			return false;
		}

		PartnerWorkerClp partnerWorker = (PartnerWorkerClp)obj;

		long primaryKey = partnerWorker.getPrimaryKey();

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

		sb.append("{partnerWorkerId=");
		sb.append(getPartnerWorkerId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", partnerEntryId=");
		sb.append(getPartnerEntryId());
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
		sb.append("com.liferay.osb.model.PartnerWorker");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>partnerWorkerId</column-name><column-value><![CDATA[");
		sb.append(getPartnerWorkerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>partnerEntryId</column-name><column-value><![CDATA[");
		sb.append(getPartnerEntryId());
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

	private long _partnerWorkerId;
	private long _userId;
	private long _partnerEntryId;
	private int _role;
	private int _notifications;
	private BaseModel<?> _partnerWorkerRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}