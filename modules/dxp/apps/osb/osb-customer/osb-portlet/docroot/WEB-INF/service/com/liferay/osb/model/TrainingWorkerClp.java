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
import com.liferay.osb.service.TrainingWorkerLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
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
public class TrainingWorkerClp extends BaseModelImpl<TrainingWorker>
	implements TrainingWorker {
	public TrainingWorkerClp() {
	}

	public Class<?> getModelClass() {
		return TrainingWorker.class;
	}

	public String getModelClassName() {
		return TrainingWorker.class.getName();
	}

	public long getPrimaryKey() {
		return _trainingWorkerId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTrainingWorkerId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_trainingWorkerId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingWorkerId", getTrainingWorkerId());
		attributes.put("userId", getUserId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("userProfileHistoryId", getUserProfileHistoryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingWorkerId = (Long)attributes.get("trainingWorkerId");

		if (trainingWorkerId != null) {
			setTrainingWorkerId(trainingWorkerId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long userProfileHistoryId = (Long)attributes.get("userProfileHistoryId");

		if (userProfileHistoryId != null) {
			setUserProfileHistoryId(userProfileHistoryId);
		}
	}

	public long getTrainingWorkerId() {
		return _trainingWorkerId;
	}

	public void setTrainingWorkerId(long trainingWorkerId) {
		_trainingWorkerId = trainingWorkerId;

		if (_trainingWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _trainingWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingWorkerId",
						long.class);

				method.invoke(_trainingWorkerRemoteModel, trainingWorkerId);
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

		if (_trainingWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _trainingWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_trainingWorkerRemoteModel, userId);
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

	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;

		if (_trainingWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _trainingWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_trainingWorkerRemoteModel, classNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;

		if (_trainingWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _trainingWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_trainingWorkerRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getUserProfileHistoryId() {
		return _userProfileHistoryId;
	}

	public void setUserProfileHistoryId(long userProfileHistoryId) {
		_userProfileHistoryId = userProfileHistoryId;

		if (_trainingWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _trainingWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setUserProfileHistoryId",
						long.class);

				method.invoke(_trainingWorkerRemoteModel, userProfileHistoryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getTrainingWorkerRemoteModel() {
		return _trainingWorkerRemoteModel;
	}

	public void setTrainingWorkerRemoteModel(
		BaseModel<?> trainingWorkerRemoteModel) {
		_trainingWorkerRemoteModel = trainingWorkerRemoteModel;
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

		Class<?> remoteModelClass = _trainingWorkerRemoteModel.getClass();

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

		Object returnValue = method.invoke(_trainingWorkerRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TrainingWorkerLocalServiceUtil.addTrainingWorker(this);
		}
		else {
			TrainingWorkerLocalServiceUtil.updateTrainingWorker(this);
		}
	}

	@Override
	public TrainingWorker toEscapedModel() {
		return (TrainingWorker)ProxyUtil.newProxyInstance(TrainingWorker.class.getClassLoader(),
			new Class[] { TrainingWorker.class },
			new AutoEscapeBeanHandler(this));
	}

	public TrainingWorker toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		TrainingWorkerClp clone = new TrainingWorkerClp();

		clone.setTrainingWorkerId(getTrainingWorkerId());
		clone.setUserId(getUserId());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setUserProfileHistoryId(getUserProfileHistoryId());

		return clone;
	}

	public int compareTo(TrainingWorker trainingWorker) {
		long primaryKey = trainingWorker.getPrimaryKey();

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

		if (!(obj instanceof TrainingWorkerClp)) {
			return false;
		}

		TrainingWorkerClp trainingWorker = (TrainingWorkerClp)obj;

		long primaryKey = trainingWorker.getPrimaryKey();

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

		sb.append("{trainingWorkerId=");
		sb.append(getTrainingWorkerId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", userProfileHistoryId=");
		sb.append(getUserProfileHistoryId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TrainingWorker");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>trainingWorkerId</column-name><column-value><![CDATA[");
		sb.append(getTrainingWorkerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userProfileHistoryId</column-name><column-value><![CDATA[");
		sb.append(getUserProfileHistoryId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _trainingWorkerId;
	private long _userId;
	private String _userUuid;
	private long _classNameId;
	private long _classPK;
	private long _userProfileHistoryId;
	private BaseModel<?> _trainingWorkerRemoteModel;
}