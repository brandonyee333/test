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
import com.liferay.osb.service.TrainingCertificateLocalServiceUtil;

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
public class TrainingCertificateClp extends BaseModelImpl<TrainingCertificate>
	implements TrainingCertificate {
	public TrainingCertificateClp() {
	}

	public Class<?> getModelClass() {
		return TrainingCertificate.class;
	}

	public String getModelClassName() {
		return TrainingCertificate.class.getName();
	}

	public long getPrimaryKey() {
		return _trainingCertificateId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTrainingCertificateId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_trainingCertificateId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingCertificateId", getTrainingCertificateId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("trainingCustomerId", getTrainingCustomerId());
		attributes.put("userProfileHistoryId", getUserProfileHistoryId());
		attributes.put("key", getKey());
		attributes.put("certifiedDate", getCertifiedDate());
		attributes.put("comments", getComments());
		attributes.put("surveyStatus", getSurveyStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingCertificateId = (Long)attributes.get(
				"trainingCertificateId");

		if (trainingCertificateId != null) {
			setTrainingCertificateId(trainingCertificateId);
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

		Long trainingCustomerId = (Long)attributes.get("trainingCustomerId");

		if (trainingCustomerId != null) {
			setTrainingCustomerId(trainingCustomerId);
		}

		Long userProfileHistoryId = (Long)attributes.get("userProfileHistoryId");

		if (userProfileHistoryId != null) {
			setUserProfileHistoryId(userProfileHistoryId);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		Date certifiedDate = (Date)attributes.get("certifiedDate");

		if (certifiedDate != null) {
			setCertifiedDate(certifiedDate);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		Integer surveyStatus = (Integer)attributes.get("surveyStatus");

		if (surveyStatus != null) {
			setSurveyStatus(surveyStatus);
		}
	}

	public long getTrainingCertificateId() {
		return _trainingCertificateId;
	}

	public void setTrainingCertificateId(long trainingCertificateId) {
		_trainingCertificateId = trainingCertificateId;

		if (_trainingCertificateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingCertificateId",
						long.class);

				method.invoke(_trainingCertificateRemoteModel,
					trainingCertificateId);
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

		if (_trainingCertificateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_trainingCertificateRemoteModel, userId);
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

		if (_trainingCertificateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_trainingCertificateRemoteModel, userName);
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

		if (_trainingCertificateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_trainingCertificateRemoteModel, createDate);
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

		if (_trainingCertificateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_trainingCertificateRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getTrainingCustomerId() {
		return _trainingCustomerId;
	}

	public void setTrainingCustomerId(long trainingCustomerId) {
		_trainingCustomerId = trainingCustomerId;

		if (_trainingCertificateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingCustomerId",
						long.class);

				method.invoke(_trainingCertificateRemoteModel,
					trainingCustomerId);
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

		if (_trainingCertificateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateRemoteModel.getClass();

				Method method = clazz.getMethod("setUserProfileHistoryId",
						long.class);

				method.invoke(_trainingCertificateRemoteModel,
					userProfileHistoryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;

		if (_trainingCertificateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateRemoteModel.getClass();

				Method method = clazz.getMethod("setKey", String.class);

				method.invoke(_trainingCertificateRemoteModel, key);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getCertifiedDate() {
		return _certifiedDate;
	}

	public void setCertifiedDate(Date certifiedDate) {
		_certifiedDate = certifiedDate;

		if (_trainingCertificateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateRemoteModel.getClass();

				Method method = clazz.getMethod("setCertifiedDate", Date.class);

				method.invoke(_trainingCertificateRemoteModel, certifiedDate);
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

		if (_trainingCertificateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateRemoteModel.getClass();

				Method method = clazz.getMethod("setComments", String.class);

				method.invoke(_trainingCertificateRemoteModel, comments);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getSurveyStatus() {
		return _surveyStatus;
	}

	public void setSurveyStatus(int surveyStatus) {
		_surveyStatus = surveyStatus;

		if (_trainingCertificateRemoteModel != null) {
			try {
				Class<?> clazz = _trainingCertificateRemoteModel.getClass();

				Method method = clazz.getMethod("setSurveyStatus", int.class);

				method.invoke(_trainingCertificateRemoteModel, surveyStatus);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getTrainingCertificateRemoteModel() {
		return _trainingCertificateRemoteModel;
	}

	public void setTrainingCertificateRemoteModel(
		BaseModel<?> trainingCertificateRemoteModel) {
		_trainingCertificateRemoteModel = trainingCertificateRemoteModel;
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

		Class<?> remoteModelClass = _trainingCertificateRemoteModel.getClass();

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

		Object returnValue = method.invoke(_trainingCertificateRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TrainingCertificateLocalServiceUtil.addTrainingCertificate(this);
		}
		else {
			TrainingCertificateLocalServiceUtil.updateTrainingCertificate(this);
		}
	}

	@Override
	public TrainingCertificate toEscapedModel() {
		return (TrainingCertificate)ProxyUtil.newProxyInstance(TrainingCertificate.class.getClassLoader(),
			new Class[] { TrainingCertificate.class },
			new AutoEscapeBeanHandler(this));
	}

	public TrainingCertificate toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		TrainingCertificateClp clone = new TrainingCertificateClp();

		clone.setTrainingCertificateId(getTrainingCertificateId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setTrainingCustomerId(getTrainingCustomerId());
		clone.setUserProfileHistoryId(getUserProfileHistoryId());
		clone.setKey(getKey());
		clone.setCertifiedDate(getCertifiedDate());
		clone.setComments(getComments());
		clone.setSurveyStatus(getSurveyStatus());

		return clone;
	}

	public int compareTo(TrainingCertificate trainingCertificate) {
		long primaryKey = trainingCertificate.getPrimaryKey();

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

		if (!(obj instanceof TrainingCertificateClp)) {
			return false;
		}

		TrainingCertificateClp trainingCertificate = (TrainingCertificateClp)obj;

		long primaryKey = trainingCertificate.getPrimaryKey();

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
		StringBundler sb = new StringBundler(23);

		sb.append("{trainingCertificateId=");
		sb.append(getTrainingCertificateId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", trainingCustomerId=");
		sb.append(getTrainingCustomerId());
		sb.append(", userProfileHistoryId=");
		sb.append(getUserProfileHistoryId());
		sb.append(", key=");
		sb.append(getKey());
		sb.append(", certifiedDate=");
		sb.append(getCertifiedDate());
		sb.append(", comments=");
		sb.append(getComments());
		sb.append(", surveyStatus=");
		sb.append(getSurveyStatus());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TrainingCertificate");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>trainingCertificateId</column-name><column-value><![CDATA[");
		sb.append(getTrainingCertificateId());
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
			"<column><column-name>trainingCustomerId</column-name><column-value><![CDATA[");
		sb.append(getTrainingCustomerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userProfileHistoryId</column-name><column-value><![CDATA[");
		sb.append(getUserProfileHistoryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>key</column-name><column-value><![CDATA[");
		sb.append(getKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>certifiedDate</column-name><column-value><![CDATA[");
		sb.append(getCertifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comments</column-name><column-value><![CDATA[");
		sb.append(getComments());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>surveyStatus</column-name><column-value><![CDATA[");
		sb.append(getSurveyStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _trainingCertificateId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _trainingCustomerId;
	private long _userProfileHistoryId;
	private String _key;
	private Date _certifiedDate;
	private String _comments;
	private int _surveyStatus;
	private BaseModel<?> _trainingCertificateRemoteModel;
}