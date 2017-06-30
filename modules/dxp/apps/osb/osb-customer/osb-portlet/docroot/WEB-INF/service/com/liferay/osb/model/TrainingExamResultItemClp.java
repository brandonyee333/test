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
import com.liferay.osb.service.TrainingExamResultItemLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class TrainingExamResultItemClp extends BaseModelImpl<TrainingExamResultItem>
	implements TrainingExamResultItem {
	public TrainingExamResultItemClp() {
	}

	public Class<?> getModelClass() {
		return TrainingExamResultItem.class;
	}

	public String getModelClassName() {
		return TrainingExamResultItem.class.getName();
	}

	public long getPrimaryKey() {
		return _trainingExamResultItemId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTrainingExamResultItemId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_trainingExamResultItemId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingExamResultItemId", getTrainingExamResultItemId());
		attributes.put("trainingExamResultId", getTrainingExamResultId());
		attributes.put("trainingExamResultSectionId",
			getTrainingExamResultSectionId());
		attributes.put("name", getName());
		attributes.put("status", getStatus());
		attributes.put("key", getKey());
		attributes.put("distractorCount", getDistractorCount());
		attributes.put("type", getType());
		attributes.put("response", getResponse());
		attributes.put("score", getScore());
		attributes.put("time", getTime());
		attributes.put("learningResources", getLearningResources());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingExamResultItemId = (Long)attributes.get(
				"trainingExamResultItemId");

		if (trainingExamResultItemId != null) {
			setTrainingExamResultItemId(trainingExamResultItemId);
		}

		Long trainingExamResultId = (Long)attributes.get("trainingExamResultId");

		if (trainingExamResultId != null) {
			setTrainingExamResultId(trainingExamResultId);
		}

		Long trainingExamResultSectionId = (Long)attributes.get(
				"trainingExamResultSectionId");

		if (trainingExamResultSectionId != null) {
			setTrainingExamResultSectionId(trainingExamResultSectionId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		Integer distractorCount = (Integer)attributes.get("distractorCount");

		if (distractorCount != null) {
			setDistractorCount(distractorCount);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String response = (String)attributes.get("response");

		if (response != null) {
			setResponse(response);
		}

		String score = (String)attributes.get("score");

		if (score != null) {
			setScore(score);
		}

		Integer time = (Integer)attributes.get("time");

		if (time != null) {
			setTime(time);
		}

		String learningResources = (String)attributes.get("learningResources");

		if (learningResources != null) {
			setLearningResources(learningResources);
		}
	}

	public long getTrainingExamResultItemId() {
		return _trainingExamResultItemId;
	}

	public void setTrainingExamResultItemId(long trainingExamResultItemId) {
		_trainingExamResultItemId = trainingExamResultItemId;

		if (_trainingExamResultItemRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultItemRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingExamResultItemId",
						long.class);

				method.invoke(_trainingExamResultItemRemoteModel,
					trainingExamResultItemId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getTrainingExamResultId() {
		return _trainingExamResultId;
	}

	public void setTrainingExamResultId(long trainingExamResultId) {
		_trainingExamResultId = trainingExamResultId;

		if (_trainingExamResultItemRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultItemRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingExamResultId",
						long.class);

				method.invoke(_trainingExamResultItemRemoteModel,
					trainingExamResultId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getTrainingExamResultSectionId() {
		return _trainingExamResultSectionId;
	}

	public void setTrainingExamResultSectionId(long trainingExamResultSectionId) {
		_trainingExamResultSectionId = trainingExamResultSectionId;

		if (_trainingExamResultItemRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultItemRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingExamResultSectionId",
						long.class);

				method.invoke(_trainingExamResultItemRemoteModel,
					trainingExamResultSectionId);
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

		if (_trainingExamResultItemRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultItemRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_trainingExamResultItemRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;

		if (_trainingExamResultItemRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultItemRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", String.class);

				method.invoke(_trainingExamResultItemRemoteModel, status);
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

		if (_trainingExamResultItemRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultItemRemoteModel.getClass();

				Method method = clazz.getMethod("setKey", String.class);

				method.invoke(_trainingExamResultItemRemoteModel, key);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getDistractorCount() {
		return _distractorCount;
	}

	public void setDistractorCount(int distractorCount) {
		_distractorCount = distractorCount;

		if (_trainingExamResultItemRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultItemRemoteModel.getClass();

				Method method = clazz.getMethod("setDistractorCount", int.class);

				method.invoke(_trainingExamResultItemRemoteModel,
					distractorCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;

		if (_trainingExamResultItemRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultItemRemoteModel.getClass();

				Method method = clazz.getMethod("setType", String.class);

				method.invoke(_trainingExamResultItemRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getResponse() {
		return _response;
	}

	public void setResponse(String response) {
		_response = response;

		if (_trainingExamResultItemRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultItemRemoteModel.getClass();

				Method method = clazz.getMethod("setResponse", String.class);

				method.invoke(_trainingExamResultItemRemoteModel, response);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getScore() {
		return _score;
	}

	public void setScore(String score) {
		_score = score;

		if (_trainingExamResultItemRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultItemRemoteModel.getClass();

				Method method = clazz.getMethod("setScore", String.class);

				method.invoke(_trainingExamResultItemRemoteModel, score);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getTime() {
		return _time;
	}

	public void setTime(int time) {
		_time = time;

		if (_trainingExamResultItemRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultItemRemoteModel.getClass();

				Method method = clazz.getMethod("setTime", int.class);

				method.invoke(_trainingExamResultItemRemoteModel, time);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getLearningResources() {
		return _learningResources;
	}

	public void setLearningResources(String learningResources) {
		_learningResources = learningResources;

		if (_trainingExamResultItemRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultItemRemoteModel.getClass();

				Method method = clazz.getMethod("setLearningResources",
						String.class);

				method.invoke(_trainingExamResultItemRemoteModel,
					learningResources);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.lang.String getSectionKey() {
		try {
			String methodName = "getSectionKey";

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

	public java.lang.String getScoreResult() {
		try {
			String methodName = "getScoreResult";

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

	public java.lang.String getStatusLabel() {
		try {
			String methodName = "getStatusLabel";

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

	public BaseModel<?> getTrainingExamResultItemRemoteModel() {
		return _trainingExamResultItemRemoteModel;
	}

	public void setTrainingExamResultItemRemoteModel(
		BaseModel<?> trainingExamResultItemRemoteModel) {
		_trainingExamResultItemRemoteModel = trainingExamResultItemRemoteModel;
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

		Class<?> remoteModelClass = _trainingExamResultItemRemoteModel.getClass();

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

		Object returnValue = method.invoke(_trainingExamResultItemRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TrainingExamResultItemLocalServiceUtil.addTrainingExamResultItem(this);
		}
		else {
			TrainingExamResultItemLocalServiceUtil.updateTrainingExamResultItem(this);
		}
	}

	@Override
	public TrainingExamResultItem toEscapedModel() {
		return (TrainingExamResultItem)ProxyUtil.newProxyInstance(TrainingExamResultItem.class.getClassLoader(),
			new Class[] { TrainingExamResultItem.class },
			new AutoEscapeBeanHandler(this));
	}

	public TrainingExamResultItem toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		TrainingExamResultItemClp clone = new TrainingExamResultItemClp();

		clone.setTrainingExamResultItemId(getTrainingExamResultItemId());
		clone.setTrainingExamResultId(getTrainingExamResultId());
		clone.setTrainingExamResultSectionId(getTrainingExamResultSectionId());
		clone.setName(getName());
		clone.setStatus(getStatus());
		clone.setKey(getKey());
		clone.setDistractorCount(getDistractorCount());
		clone.setType(getType());
		clone.setResponse(getResponse());
		clone.setScore(getScore());
		clone.setTime(getTime());
		clone.setLearningResources(getLearningResources());

		return clone;
	}

	public int compareTo(TrainingExamResultItem trainingExamResultItem) {
		int value = 0;

		value = getName().compareTo(trainingExamResultItem.getName());

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

		if (!(obj instanceof TrainingExamResultItemClp)) {
			return false;
		}

		TrainingExamResultItemClp trainingExamResultItem = (TrainingExamResultItemClp)obj;

		long primaryKey = trainingExamResultItem.getPrimaryKey();

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
		StringBundler sb = new StringBundler(25);

		sb.append("{trainingExamResultItemId=");
		sb.append(getTrainingExamResultItemId());
		sb.append(", trainingExamResultId=");
		sb.append(getTrainingExamResultId());
		sb.append(", trainingExamResultSectionId=");
		sb.append(getTrainingExamResultSectionId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", key=");
		sb.append(getKey());
		sb.append(", distractorCount=");
		sb.append(getDistractorCount());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", response=");
		sb.append(getResponse());
		sb.append(", score=");
		sb.append(getScore());
		sb.append(", time=");
		sb.append(getTime());
		sb.append(", learningResources=");
		sb.append(getLearningResources());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TrainingExamResultItem");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>trainingExamResultItemId</column-name><column-value><![CDATA[");
		sb.append(getTrainingExamResultItemId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>trainingExamResultId</column-name><column-value><![CDATA[");
		sb.append(getTrainingExamResultId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>trainingExamResultSectionId</column-name><column-value><![CDATA[");
		sb.append(getTrainingExamResultSectionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>key</column-name><column-value><![CDATA[");
		sb.append(getKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>distractorCount</column-name><column-value><![CDATA[");
		sb.append(getDistractorCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>response</column-name><column-value><![CDATA[");
		sb.append(getResponse());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>score</column-name><column-value><![CDATA[");
		sb.append(getScore());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>time</column-name><column-value><![CDATA[");
		sb.append(getTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>learningResources</column-name><column-value><![CDATA[");
		sb.append(getLearningResources());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _trainingExamResultItemId;
	private long _trainingExamResultId;
	private long _trainingExamResultSectionId;
	private String _name;
	private String _status;
	private String _key;
	private int _distractorCount;
	private String _type;
	private String _response;
	private String _score;
	private int _time;
	private String _learningResources;
	private BaseModel<?> _trainingExamResultItemRemoteModel;
}