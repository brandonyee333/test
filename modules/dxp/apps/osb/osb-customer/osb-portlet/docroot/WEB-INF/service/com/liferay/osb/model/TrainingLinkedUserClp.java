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
import com.liferay.osb.service.TrainingLinkedUserLocalServiceUtil;

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
public class TrainingLinkedUserClp extends BaseModelImpl<TrainingLinkedUser>
	implements TrainingLinkedUser {
	public TrainingLinkedUserClp() {
	}

	public Class<?> getModelClass() {
		return TrainingLinkedUser.class;
	}

	public String getModelClassName() {
		return TrainingLinkedUser.class.getName();
	}

	public long getPrimaryKey() {
		return _trainingLinkedUserId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTrainingLinkedUserId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_trainingLinkedUserId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingLinkedUserId", getTrainingLinkedUserId());
		attributes.put("userId", getUserId());
		attributes.put("primaryUserId", getPrimaryUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingLinkedUserId = (Long)attributes.get("trainingLinkedUserId");

		if (trainingLinkedUserId != null) {
			setTrainingLinkedUserId(trainingLinkedUserId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long primaryUserId = (Long)attributes.get("primaryUserId");

		if (primaryUserId != null) {
			setPrimaryUserId(primaryUserId);
		}
	}

	public long getTrainingLinkedUserId() {
		return _trainingLinkedUserId;
	}

	public void setTrainingLinkedUserId(long trainingLinkedUserId) {
		_trainingLinkedUserId = trainingLinkedUserId;

		if (_trainingLinkedUserRemoteModel != null) {
			try {
				Class<?> clazz = _trainingLinkedUserRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingLinkedUserId",
						long.class);

				method.invoke(_trainingLinkedUserRemoteModel,
					trainingLinkedUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getTrainingLinkedUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getTrainingLinkedUserId(), "uuid",
			_trainingLinkedUserUuid);
	}

	public void setTrainingLinkedUserUuid(String trainingLinkedUserUuid) {
		_trainingLinkedUserUuid = trainingLinkedUserUuid;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;

		if (_trainingLinkedUserRemoteModel != null) {
			try {
				Class<?> clazz = _trainingLinkedUserRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_trainingLinkedUserRemoteModel, userId);
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

	public long getPrimaryUserId() {
		return _primaryUserId;
	}

	public void setPrimaryUserId(long primaryUserId) {
		_primaryUserId = primaryUserId;

		if (_trainingLinkedUserRemoteModel != null) {
			try {
				Class<?> clazz = _trainingLinkedUserRemoteModel.getClass();

				Method method = clazz.getMethod("setPrimaryUserId", long.class);

				method.invoke(_trainingLinkedUserRemoteModel, primaryUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getPrimaryUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getPrimaryUserId(), "uuid",
			_primaryUserUuid);
	}

	public void setPrimaryUserUuid(String primaryUserUuid) {
		_primaryUserUuid = primaryUserUuid;
	}

	public com.liferay.portal.model.User getUser() {
		try {
			String methodName = "getUser";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.model.User returnObj = (com.liferay.portal.model.User)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.portal.model.User getPrimaryUser() {
		try {
			String methodName = "getPrimaryUser";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.model.User returnObj = (com.liferay.portal.model.User)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isPrimaryUser() {
		try {
			String methodName = "isPrimaryUser";

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

	public BaseModel<?> getTrainingLinkedUserRemoteModel() {
		return _trainingLinkedUserRemoteModel;
	}

	public void setTrainingLinkedUserRemoteModel(
		BaseModel<?> trainingLinkedUserRemoteModel) {
		_trainingLinkedUserRemoteModel = trainingLinkedUserRemoteModel;
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

		Class<?> remoteModelClass = _trainingLinkedUserRemoteModel.getClass();

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

		Object returnValue = method.invoke(_trainingLinkedUserRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TrainingLinkedUserLocalServiceUtil.addTrainingLinkedUser(this);
		}
		else {
			TrainingLinkedUserLocalServiceUtil.updateTrainingLinkedUser(this);
		}
	}

	@Override
	public TrainingLinkedUser toEscapedModel() {
		return (TrainingLinkedUser)ProxyUtil.newProxyInstance(TrainingLinkedUser.class.getClassLoader(),
			new Class[] { TrainingLinkedUser.class },
			new AutoEscapeBeanHandler(this));
	}

	public TrainingLinkedUser toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		TrainingLinkedUserClp clone = new TrainingLinkedUserClp();

		clone.setTrainingLinkedUserId(getTrainingLinkedUserId());
		clone.setUserId(getUserId());
		clone.setPrimaryUserId(getPrimaryUserId());

		return clone;
	}

	public int compareTo(TrainingLinkedUser trainingLinkedUser) {
		long primaryKey = trainingLinkedUser.getPrimaryKey();

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

		if (!(obj instanceof TrainingLinkedUserClp)) {
			return false;
		}

		TrainingLinkedUserClp trainingLinkedUser = (TrainingLinkedUserClp)obj;

		long primaryKey = trainingLinkedUser.getPrimaryKey();

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
		StringBundler sb = new StringBundler(7);

		sb.append("{trainingLinkedUserId=");
		sb.append(getTrainingLinkedUserId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", primaryUserId=");
		sb.append(getPrimaryUserId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TrainingLinkedUser");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>trainingLinkedUserId</column-name><column-value><![CDATA[");
		sb.append(getTrainingLinkedUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>primaryUserId</column-name><column-value><![CDATA[");
		sb.append(getPrimaryUserId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _trainingLinkedUserId;
	private String _trainingLinkedUserUuid;
	private long _userId;
	private String _userUuid;
	private long _primaryUserId;
	private String _primaryUserUuid;
	private BaseModel<?> _trainingLinkedUserRemoteModel;
}