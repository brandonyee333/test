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
import com.liferay.osb.service.TrainingCourseLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class TrainingCourseClp extends BaseModelImpl<TrainingCourse>
	implements TrainingCourse {
	public TrainingCourseClp() {
	}

	public Class<?> getModelClass() {
		return TrainingCourse.class;
	}

	public String getModelClassName() {
		return TrainingCourse.class.getName();
	}

	public long getPrimaryKey() {
		return _trainingCourseId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTrainingCourseId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_trainingCourseId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingCourseId", getTrainingCourseId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("creditAmount", getCreditAmount());
		attributes.put("courseURL", getCourseURL());
		attributes.put("archived", getArchived());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingCourseId = (Long)attributes.get("trainingCourseId");

		if (trainingCourseId != null) {
			setTrainingCourseId(trainingCourseId);
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

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Integer creditAmount = (Integer)attributes.get("creditAmount");

		if (creditAmount != null) {
			setCreditAmount(creditAmount);
		}

		String courseURL = (String)attributes.get("courseURL");

		if (courseURL != null) {
			setCourseURL(courseURL);
		}

		Boolean archived = (Boolean)attributes.get("archived");

		if (archived != null) {
			setArchived(archived);
		}
	}

	public long getTrainingCourseId() {
		return _trainingCourseId;
	}

	public void setTrainingCourseId(long trainingCourseId) {
		_trainingCourseId = trainingCourseId;

		if (_trainingCourseRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCourseRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingCourseId",
						long.class);

				method.invoke(_trainingCourseRemoteModel, trainingCourseId);
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

		if (_trainingCourseRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCourseRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_trainingCourseRemoteModel, userId);
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

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;

		if (_trainingCourseRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCourseRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_trainingCourseRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_trainingCourseRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCourseRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_trainingCourseRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_trainingCourseRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCourseRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_trainingCourseRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;

		if (_trainingCourseRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCourseRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_trainingCourseRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;

		if (_trainingCourseRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCourseRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_trainingCourseRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getCreditAmount() {
		return _creditAmount;
	}

	public void setCreditAmount(int creditAmount) {
		_creditAmount = creditAmount;

		if (_trainingCourseRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCourseRemoteModel.getClass();

				Method method = clazz.getMethod("setCreditAmount", int.class);

				method.invoke(_trainingCourseRemoteModel, creditAmount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getCourseURL() {
		return _courseURL;
	}

	public void setCourseURL(String courseURL) {
		_courseURL = courseURL;

		if (_trainingCourseRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCourseRemoteModel.getClass();

				Method method = clazz.getMethod("setCourseURL", String.class);

				method.invoke(_trainingCourseRemoteModel, courseURL);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getArchived() {
		return _archived;
	}

	public boolean isArchived() {
		return _archived;
	}

	public void setArchived(boolean archived) {
		_archived = archived;

		if (_trainingCourseRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCourseRemoteModel.getClass();

				Method method = clazz.getMethod("setArchived", boolean.class);

				method.invoke(_trainingCourseRemoteModel, archived);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getTrainingCourseRemoteModel() {
		return _trainingCourseRemoteModel;
	}

	public void setTrainingCourseRemoteModel(
		BaseModel<?> trainingCourseRemoteModel) {
		_trainingCourseRemoteModel = trainingCourseRemoteModel;
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

		Class<?> remoteModelClass = _trainingCourseRemoteModel.getClass();

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

		Object returnValue = method.invoke(_trainingCourseRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TrainingCourseLocalServiceUtil.addTrainingCourse(this);
		}
		else {
			TrainingCourseLocalServiceUtil.updateTrainingCourse(this);
		}
	}

	@Override
	public TrainingCourse toEscapedModel() {
		return (TrainingCourse)ProxyUtil.newProxyInstance(TrainingCourse.class.getClassLoader(),
			new Class[] { TrainingCourse.class },
			new AutoEscapeBeanHandler(this));
	}

	public TrainingCourse toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		TrainingCourseClp clone = new TrainingCourseClp();

		clone.setTrainingCourseId(getTrainingCourseId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setCreditAmount(getCreditAmount());
		clone.setCourseURL(getCourseURL());
		clone.setArchived(getArchived());

		return clone;
	}

	public int compareTo(TrainingCourse trainingCourse) {
		int value = 0;

		value = getName().toLowerCase()
					.compareTo(trainingCourse.getName().toLowerCase());

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

		if (!(obj instanceof TrainingCourseClp)) {
			return false;
		}

		TrainingCourseClp trainingCourse = (TrainingCourseClp)obj;

		long primaryKey = trainingCourse.getPrimaryKey();

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
		StringBundler sb = new StringBundler(21);

		sb.append("{trainingCourseId=");
		sb.append(getTrainingCourseId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", creditAmount=");
		sb.append(getCreditAmount());
		sb.append(", courseURL=");
		sb.append(getCourseURL());
		sb.append(", archived=");
		sb.append(getArchived());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TrainingCourse");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>trainingCourseId</column-name><column-value><![CDATA[");
		sb.append(getTrainingCourseId());
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
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>creditAmount</column-name><column-value><![CDATA[");
		sb.append(getCreditAmount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>courseURL</column-name><column-value><![CDATA[");
		sb.append(getCourseURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>archived</column-name><column-value><![CDATA[");
		sb.append(getArchived());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _trainingCourseId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private int _creditAmount;
	private String _courseURL;
	private boolean _archived;
	private BaseModel<?> _trainingCourseRemoteModel;
}