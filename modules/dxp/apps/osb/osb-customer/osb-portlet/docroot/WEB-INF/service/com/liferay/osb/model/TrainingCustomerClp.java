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
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;

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
public class TrainingCustomerClp extends BaseModelImpl<TrainingCustomer>
	implements TrainingCustomer {
	public TrainingCustomerClp() {
	}

	public Class<?> getModelClass() {
		return TrainingCustomer.class;
	}

	public String getModelClassName() {
		return TrainingCustomer.class.getName();
	}

	public long getPrimaryKey() {
		return _trainingCustomerId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTrainingCustomerId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_trainingCustomerId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingCustomerId", getTrainingCustomerId());
		attributes.put("userId", getUserId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("userProfileHistoryId", getUserProfileHistoryId());
		attributes.put("comments", getComments());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingCustomerId = (Long)attributes.get("trainingCustomerId");

		if (trainingCustomerId != null) {
			setTrainingCustomerId(trainingCustomerId);
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

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	public long getTrainingCustomerId() {
		return _trainingCustomerId;
	}

	public void setTrainingCustomerId(long trainingCustomerId) {
		_trainingCustomerId = trainingCustomerId;

		if (_trainingCustomerRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCustomerRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingCustomerId",
						long.class);

				method.invoke(_trainingCustomerRemoteModel, trainingCustomerId);
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

		if (_trainingCustomerRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCustomerRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_trainingCustomerRemoteModel, userId);
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

		if (_trainingCustomerRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCustomerRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_trainingCustomerRemoteModel, classNameId);
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

		if (_trainingCustomerRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCustomerRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_trainingCustomerRemoteModel, classPK);
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

		if (_trainingCustomerRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCustomerRemoteModel.getClass();

				Method method = clazz.getMethod("setUserProfileHistoryId",
						long.class);

				method.invoke(_trainingCustomerRemoteModel, userProfileHistoryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;

		if (_trainingCustomerRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCustomerRemoteModel.getClass();

				Method method = clazz.getMethod("setComments", String.class);

				method.invoke(_trainingCustomerRemoteModel, comments);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;

		if (_trainingCustomerRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCustomerRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_trainingCustomerRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.lang.String getType() {
		try {
			String methodName = "getType";

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

	public java.lang.String getTrainingSurveyResultHTML() {
		try {
			String methodName = "getTrainingSurveyResultHTML";

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

	public java.lang.String getLastName() {
		try {
			String methodName = "getLastName";

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

	public java.lang.String getEmailAddress() {
		try {
			String methodName = "getEmailAddress";

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

	public java.lang.String getTrainingCertificateKey() {
		try {
			String methodName = "getTrainingCertificateKey";

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

	public boolean isTrainingUser() {
		try {
			String methodName = "isTrainingUser";

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

	public java.lang.String getFirstName() {
		try {
			String methodName = "getFirstName";

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

	public BaseModel<?> getTrainingCustomerRemoteModel() {
		return _trainingCustomerRemoteModel;
	}

	public void setTrainingCustomerRemoteModel(
		BaseModel<?> trainingCustomerRemoteModel) {
		_trainingCustomerRemoteModel = trainingCustomerRemoteModel;
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

		Class<?> remoteModelClass = _trainingCustomerRemoteModel.getClass();

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

		Object returnValue = method.invoke(_trainingCustomerRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TrainingCustomerLocalServiceUtil.addTrainingCustomer(this);
		}
		else {
			TrainingCustomerLocalServiceUtil.updateTrainingCustomer(this);
		}
	}

	@Override
	public TrainingCustomer toEscapedModel() {
		return (TrainingCustomer)ProxyUtil.newProxyInstance(TrainingCustomer.class.getClassLoader(),
			new Class[] { TrainingCustomer.class },
			new AutoEscapeBeanHandler(this));
	}

	public TrainingCustomer toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		TrainingCustomerClp clone = new TrainingCustomerClp();

		clone.setTrainingCustomerId(getTrainingCustomerId());
		clone.setUserId(getUserId());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setUserProfileHistoryId(getUserProfileHistoryId());
		clone.setComments(getComments());
		clone.setStatus(getStatus());

		return clone;
	}

	public int compareTo(TrainingCustomer trainingCustomer) {
		long primaryKey = trainingCustomer.getPrimaryKey();

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

		if (!(obj instanceof TrainingCustomerClp)) {
			return false;
		}

		TrainingCustomerClp trainingCustomer = (TrainingCustomerClp)obj;

		long primaryKey = trainingCustomer.getPrimaryKey();

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
		StringBundler sb = new StringBundler(15);

		sb.append("{trainingCustomerId=");
		sb.append(getTrainingCustomerId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", userProfileHistoryId=");
		sb.append(getUserProfileHistoryId());
		sb.append(", comments=");
		sb.append(getComments());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TrainingCustomer");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>trainingCustomerId</column-name><column-value><![CDATA[");
		sb.append(getTrainingCustomerId());
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
		sb.append(
			"<column><column-name>comments</column-name><column-value><![CDATA[");
		sb.append(getComments());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _trainingCustomerId;
	private long _userId;
	private String _userUuid;
	private long _classNameId;
	private long _classPK;
	private long _userProfileHistoryId;
	private String _comments;
	private int _status;
	private BaseModel<?> _trainingCustomerRemoteModel;
}