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
import com.liferay.osb.service.TrainingExamResultLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class TrainingExamResultClp extends BaseModelImpl<TrainingExamResult>
	implements TrainingExamResult {
	public TrainingExamResultClp() {
	}

	public Class<?> getModelClass() {
		return TrainingExamResult.class;
	}

	public String getModelClassName() {
		return TrainingExamResult.class.getName();
	}

	public long getPrimaryKey() {
		return _trainingExamResultId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTrainingExamResultId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_trainingExamResultId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingExamResultId", getTrainingExamResultId());
		attributes.put("createDate", getCreateDate());
		attributes.put("trainingExamId", getTrainingExamId());
		attributes.put("recordType", getRecordType());
		attributes.put("registrationNumber", getRegistrationNumber());
		attributes.put("formKey", getFormKey());
		attributes.put("startDate", getStartDate());
		attributes.put("testScore", getTestScore());
		attributes.put("correctCount", getCorrectCount());
		attributes.put("incorrectCount", getIncorrectCount());
		attributes.put("skippedCount", getSkippedCount());
		attributes.put("grade", getGrade());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingExamResultId = (Long)attributes.get("trainingExamResultId");

		if (trainingExamResultId != null) {
			setTrainingExamResultId(trainingExamResultId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long trainingExamId = (Long)attributes.get("trainingExamId");

		if (trainingExamId != null) {
			setTrainingExamId(trainingExamId);
		}

		Integer recordType = (Integer)attributes.get("recordType");

		if (recordType != null) {
			setRecordType(recordType);
		}

		String registrationNumber = (String)attributes.get("registrationNumber");

		if (registrationNumber != null) {
			setRegistrationNumber(registrationNumber);
		}

		String formKey = (String)attributes.get("formKey");

		if (formKey != null) {
			setFormKey(formKey);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		String testScore = (String)attributes.get("testScore");

		if (testScore != null) {
			setTestScore(testScore);
		}

		Integer correctCount = (Integer)attributes.get("correctCount");

		if (correctCount != null) {
			setCorrectCount(correctCount);
		}

		Integer incorrectCount = (Integer)attributes.get("incorrectCount");

		if (incorrectCount != null) {
			setIncorrectCount(incorrectCount);
		}

		Integer skippedCount = (Integer)attributes.get("skippedCount");

		if (skippedCount != null) {
			setSkippedCount(skippedCount);
		}

		Integer grade = (Integer)attributes.get("grade");

		if (grade != null) {
			setGrade(grade);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	public long getTrainingExamResultId() {
		return _trainingExamResultId;
	}

	public void setTrainingExamResultId(long trainingExamResultId) {
		_trainingExamResultId = trainingExamResultId;

		if (_trainingExamResultRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingExamResultId",
						long.class);

				method.invoke(_trainingExamResultRemoteModel,
					trainingExamResultId);
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

		if (_trainingExamResultRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_trainingExamResultRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getTrainingExamId() {
		return _trainingExamId;
	}

	public void setTrainingExamId(long trainingExamId) {
		_trainingExamId = trainingExamId;

		if (_trainingExamResultRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingExamId", long.class);

				method.invoke(_trainingExamResultRemoteModel, trainingExamId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getRecordType() {
		return _recordType;
	}

	public void setRecordType(int recordType) {
		_recordType = recordType;

		if (_trainingExamResultRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultRemoteModel.getClass();

				Method method = clazz.getMethod("setRecordType", int.class);

				method.invoke(_trainingExamResultRemoteModel, recordType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getRegistrationNumber() {
		return _registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		_registrationNumber = registrationNumber;

		if (_trainingExamResultRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultRemoteModel.getClass();

				Method method = clazz.getMethod("setRegistrationNumber",
						String.class);

				method.invoke(_trainingExamResultRemoteModel, registrationNumber);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getFormKey() {
		return _formKey;
	}

	public void setFormKey(String formKey) {
		_formKey = formKey;

		if (_trainingExamResultRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultRemoteModel.getClass();

				Method method = clazz.getMethod("setFormKey", String.class);

				method.invoke(_trainingExamResultRemoteModel, formKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;

		if (_trainingExamResultRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultRemoteModel.getClass();

				Method method = clazz.getMethod("setStartDate", Date.class);

				method.invoke(_trainingExamResultRemoteModel, startDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getTestScore() {
		return _testScore;
	}

	public void setTestScore(String testScore) {
		_testScore = testScore;

		if (_trainingExamResultRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultRemoteModel.getClass();

				Method method = clazz.getMethod("setTestScore", String.class);

				method.invoke(_trainingExamResultRemoteModel, testScore);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getCorrectCount() {
		return _correctCount;
	}

	public void setCorrectCount(int correctCount) {
		_correctCount = correctCount;

		if (_trainingExamResultRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultRemoteModel.getClass();

				Method method = clazz.getMethod("setCorrectCount", int.class);

				method.invoke(_trainingExamResultRemoteModel, correctCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getIncorrectCount() {
		return _incorrectCount;
	}

	public void setIncorrectCount(int incorrectCount) {
		_incorrectCount = incorrectCount;

		if (_trainingExamResultRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultRemoteModel.getClass();

				Method method = clazz.getMethod("setIncorrectCount", int.class);

				method.invoke(_trainingExamResultRemoteModel, incorrectCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getSkippedCount() {
		return _skippedCount;
	}

	public void setSkippedCount(int skippedCount) {
		_skippedCount = skippedCount;

		if (_trainingExamResultRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultRemoteModel.getClass();

				Method method = clazz.getMethod("setSkippedCount", int.class);

				method.invoke(_trainingExamResultRemoteModel, skippedCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getGrade() {
		return _grade;
	}

	public void setGrade(int grade) {
		_grade = grade;

		if (_trainingExamResultRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultRemoteModel.getClass();

				Method method = clazz.getMethod("setGrade", int.class);

				method.invoke(_trainingExamResultRemoteModel, grade);
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

		if (_trainingExamResultRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_trainingExamResultRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public com.liferay.osb.model.TrainingCertificateTemplate getTrainingCertificateTemplate() {
		try {
			String methodName = "getTrainingCertificateTemplate";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.TrainingCertificateTemplate returnObj = (com.liferay.osb.model.TrainingCertificateTemplate)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean hasPassedGrade() {
		try {
			String methodName = "hasPassedGrade";

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

	public com.liferay.osb.model.TrainingExam getTrainingExam() {
		try {
			String methodName = "getTrainingExam";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.TrainingExam returnObj = (com.liferay.osb.model.TrainingExam)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getCandidateKey() {
		try {
			String methodName = "getCandidateKey";

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

	public com.liferay.osb.model.TrainingCustomer getTrainingCustomer() {
		try {
			String methodName = "getTrainingCustomer";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.TrainingCustomer returnObj = (com.liferay.osb.model.TrainingCustomer)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getResult() {
		try {
			String methodName = "getResult";

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

	public BaseModel<?> getTrainingExamResultRemoteModel() {
		return _trainingExamResultRemoteModel;
	}

	public void setTrainingExamResultRemoteModel(
		BaseModel<?> trainingExamResultRemoteModel) {
		_trainingExamResultRemoteModel = trainingExamResultRemoteModel;
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

		Class<?> remoteModelClass = _trainingExamResultRemoteModel.getClass();

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

		Object returnValue = method.invoke(_trainingExamResultRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TrainingExamResultLocalServiceUtil.addTrainingExamResult(this);
		}
		else {
			TrainingExamResultLocalServiceUtil.updateTrainingExamResult(this);
		}
	}

	@Override
	public TrainingExamResult toEscapedModel() {
		return (TrainingExamResult)ProxyUtil.newProxyInstance(TrainingExamResult.class.getClassLoader(),
			new Class[] { TrainingExamResult.class },
			new AutoEscapeBeanHandler(this));
	}

	public TrainingExamResult toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		TrainingExamResultClp clone = new TrainingExamResultClp();

		clone.setTrainingExamResultId(getTrainingExamResultId());
		clone.setCreateDate(getCreateDate());
		clone.setTrainingExamId(getTrainingExamId());
		clone.setRecordType(getRecordType());
		clone.setRegistrationNumber(getRegistrationNumber());
		clone.setFormKey(getFormKey());
		clone.setStartDate(getStartDate());
		clone.setTestScore(getTestScore());
		clone.setCorrectCount(getCorrectCount());
		clone.setIncorrectCount(getIncorrectCount());
		clone.setSkippedCount(getSkippedCount());
		clone.setGrade(getGrade());
		clone.setStatus(getStatus());

		return clone;
	}

	public int compareTo(TrainingExamResult trainingExamResult) {
		int value = 0;

		value = DateUtil.compareTo(getStartDate(),
				trainingExamResult.getStartDate());

		value = value * -1;

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

		if (!(obj instanceof TrainingExamResultClp)) {
			return false;
		}

		TrainingExamResultClp trainingExamResult = (TrainingExamResultClp)obj;

		long primaryKey = trainingExamResult.getPrimaryKey();

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
		StringBundler sb = new StringBundler(27);

		sb.append("{trainingExamResultId=");
		sb.append(getTrainingExamResultId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", trainingExamId=");
		sb.append(getTrainingExamId());
		sb.append(", recordType=");
		sb.append(getRecordType());
		sb.append(", registrationNumber=");
		sb.append(getRegistrationNumber());
		sb.append(", formKey=");
		sb.append(getFormKey());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", testScore=");
		sb.append(getTestScore());
		sb.append(", correctCount=");
		sb.append(getCorrectCount());
		sb.append(", incorrectCount=");
		sb.append(getIncorrectCount());
		sb.append(", skippedCount=");
		sb.append(getSkippedCount());
		sb.append(", grade=");
		sb.append(getGrade());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TrainingExamResult");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>trainingExamResultId</column-name><column-value><![CDATA[");
		sb.append(getTrainingExamResultId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>trainingExamId</column-name><column-value><![CDATA[");
		sb.append(getTrainingExamId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recordType</column-name><column-value><![CDATA[");
		sb.append(getRecordType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registrationNumber</column-name><column-value><![CDATA[");
		sb.append(getRegistrationNumber());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>formKey</column-name><column-value><![CDATA[");
		sb.append(getFormKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>testScore</column-name><column-value><![CDATA[");
		sb.append(getTestScore());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>correctCount</column-name><column-value><![CDATA[");
		sb.append(getCorrectCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>incorrectCount</column-name><column-value><![CDATA[");
		sb.append(getIncorrectCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>skippedCount</column-name><column-value><![CDATA[");
		sb.append(getSkippedCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>grade</column-name><column-value><![CDATA[");
		sb.append(getGrade());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _trainingExamResultId;
	private Date _createDate;
	private long _trainingExamId;
	private int _recordType;
	private String _registrationNumber;
	private String _formKey;
	private Date _startDate;
	private String _testScore;
	private int _correctCount;
	private int _incorrectCount;
	private int _skippedCount;
	private int _grade;
	private int _status;
	private BaseModel<?> _trainingExamResultRemoteModel;
}