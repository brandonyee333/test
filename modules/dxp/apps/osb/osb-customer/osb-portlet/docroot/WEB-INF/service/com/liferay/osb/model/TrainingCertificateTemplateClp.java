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
import com.liferay.osb.service.TrainingCertificateTemplateLocalServiceUtil;

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
public class TrainingCertificateTemplateClp extends BaseModelImpl<TrainingCertificateTemplate>
	implements TrainingCertificateTemplate {
	public TrainingCertificateTemplateClp() {
	}

	public Class<?> getModelClass() {
		return TrainingCertificateTemplate.class;
	}

	public String getModelClassName() {
		return TrainingCertificateTemplate.class.getName();
	}

	public long getPrimaryKey() {
		return _trainingCertificateTemplateId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTrainingCertificateTemplateId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_trainingCertificateTemplateId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingCertificateTemplateId",
			getTrainingCertificateTemplateId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingCertificateTemplateId = (Long)attributes.get(
				"trainingCertificateTemplateId");

		if (trainingCertificateTemplateId != null) {
			setTrainingCertificateTemplateId(trainingCertificateTemplateId);
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

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	public long getTrainingCertificateTemplateId() {
		return _trainingCertificateTemplateId;
	}

	public void setTrainingCertificateTemplateId(
		long trainingCertificateTemplateId) {
		_trainingCertificateTemplateId = trainingCertificateTemplateId;

		if (_trainingCertificateTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingCertificateTemplateId",
						long.class);

				method.invoke(_trainingCertificateTemplateRemoteModel,
					trainingCertificateTemplateId);
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

		if (_trainingCertificateTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_trainingCertificateTemplateRemoteModel, userId);
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

		if (_trainingCertificateTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_trainingCertificateTemplateRemoteModel, userName);
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

		if (_trainingCertificateTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_trainingCertificateTemplateRemoteModel,
					createDate);
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

		if (_trainingCertificateTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_trainingCertificateTemplateRemoteModel,
					modifiedDate);
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

		if (_trainingCertificateTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_trainingCertificateTemplateRemoteModel, name);
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

		if (_trainingCertificateTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_trainingCertificateTemplateRemoteModel,
					description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;

		if (_trainingCertificateTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_trainingCertificateTemplateRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.lang.String getBadgesDir() {
		try {
			String methodName = "getBadgesDir";

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

	public java.lang.String getTemplatesDir() {
		try {
			String methodName = "getTemplatesDir";

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

	public BaseModel<?> getTrainingCertificateTemplateRemoteModel() {
		return _trainingCertificateTemplateRemoteModel;
	}

	public void setTrainingCertificateTemplateRemoteModel(
		BaseModel<?> trainingCertificateTemplateRemoteModel) {
		_trainingCertificateTemplateRemoteModel = trainingCertificateTemplateRemoteModel;
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

		Class<?> remoteModelClass = _trainingCertificateTemplateRemoteModel.getClass();

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

		Object returnValue = method.invoke(_trainingCertificateTemplateRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TrainingCertificateTemplateLocalServiceUtil.addTrainingCertificateTemplate(this);
		}
		else {
			TrainingCertificateTemplateLocalServiceUtil.updateTrainingCertificateTemplate(this);
		}
	}

	@Override
	public TrainingCertificateTemplate toEscapedModel() {
		return (TrainingCertificateTemplate)ProxyUtil.newProxyInstance(TrainingCertificateTemplate.class.getClassLoader(),
			new Class[] { TrainingCertificateTemplate.class },
			new AutoEscapeBeanHandler(this));
	}

	public TrainingCertificateTemplate toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		TrainingCertificateTemplateClp clone = new TrainingCertificateTemplateClp();

		clone.setTrainingCertificateTemplateId(getTrainingCertificateTemplateId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setType(getType());

		return clone;
	}

	public int compareTo(
		TrainingCertificateTemplate trainingCertificateTemplate) {
		long primaryKey = trainingCertificateTemplate.getPrimaryKey();

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

		if (!(obj instanceof TrainingCertificateTemplateClp)) {
			return false;
		}

		TrainingCertificateTemplateClp trainingCertificateTemplate = (TrainingCertificateTemplateClp)obj;

		long primaryKey = trainingCertificateTemplate.getPrimaryKey();

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
		StringBundler sb = new StringBundler(17);

		sb.append("{trainingCertificateTemplateId=");
		sb.append(getTrainingCertificateTemplateId());
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
		sb.append(", type=");
		sb.append(getType());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TrainingCertificateTemplate");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>trainingCertificateTemplateId</column-name><column-value><![CDATA[");
		sb.append(getTrainingCertificateTemplateId());
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
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _trainingCertificateTemplateId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private int _type;
	private BaseModel<?> _trainingCertificateTemplateRemoteModel;
}