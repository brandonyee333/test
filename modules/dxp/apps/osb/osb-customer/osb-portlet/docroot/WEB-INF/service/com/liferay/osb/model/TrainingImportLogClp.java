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
import com.liferay.osb.service.TrainingImportLogLocalServiceUtil;

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
public class TrainingImportLogClp extends BaseModelImpl<TrainingImportLog>
	implements TrainingImportLog {
	public TrainingImportLogClp() {
	}

	public Class<?> getModelClass() {
		return TrainingImportLog.class;
	}

	public String getModelClassName() {
		return TrainingImportLog.class.getName();
	}

	public long getPrimaryKey() {
		return _trainingImportLogId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTrainingImportLogId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_trainingImportLogId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingImportLogId", getTrainingImportLogId());
		attributes.put("type", getType());
		attributes.put("importDate", getImportDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingImportLogId = (Long)attributes.get("trainingImportLogId");

		if (trainingImportLogId != null) {
			setTrainingImportLogId(trainingImportLogId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Date importDate = (Date)attributes.get("importDate");

		if (importDate != null) {
			setImportDate(importDate);
		}
	}

	public long getTrainingImportLogId() {
		return _trainingImportLogId;
	}

	public void setTrainingImportLogId(long trainingImportLogId) {
		_trainingImportLogId = trainingImportLogId;

		if (_trainingImportLogRemoteModel != null) {
			try {
				Class<?> clazz = _trainingImportLogRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingImportLogId",
						long.class);

				method.invoke(_trainingImportLogRemoteModel, trainingImportLogId);
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

		if (_trainingImportLogRemoteModel != null) {
			try {
				Class<?> clazz = _trainingImportLogRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_trainingImportLogRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getImportDate() {
		return _importDate;
	}

	public void setImportDate(Date importDate) {
		_importDate = importDate;

		if (_trainingImportLogRemoteModel != null) {
			try {
				Class<?> clazz = _trainingImportLogRemoteModel.getClass();

				Method method = clazz.getMethod("setImportDate", Date.class);

				method.invoke(_trainingImportLogRemoteModel, importDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getTrainingImportLogRemoteModel() {
		return _trainingImportLogRemoteModel;
	}

	public void setTrainingImportLogRemoteModel(
		BaseModel<?> trainingImportLogRemoteModel) {
		_trainingImportLogRemoteModel = trainingImportLogRemoteModel;
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

		Class<?> remoteModelClass = _trainingImportLogRemoteModel.getClass();

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

		Object returnValue = method.invoke(_trainingImportLogRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TrainingImportLogLocalServiceUtil.addTrainingImportLog(this);
		}
		else {
			TrainingImportLogLocalServiceUtil.updateTrainingImportLog(this);
		}
	}

	@Override
	public TrainingImportLog toEscapedModel() {
		return (TrainingImportLog)ProxyUtil.newProxyInstance(TrainingImportLog.class.getClassLoader(),
			new Class[] { TrainingImportLog.class },
			new AutoEscapeBeanHandler(this));
	}

	public TrainingImportLog toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		TrainingImportLogClp clone = new TrainingImportLogClp();

		clone.setTrainingImportLogId(getTrainingImportLogId());
		clone.setType(getType());
		clone.setImportDate(getImportDate());

		return clone;
	}

	public int compareTo(TrainingImportLog trainingImportLog) {
		int value = 0;

		value = DateUtil.compareTo(getImportDate(),
				trainingImportLog.getImportDate());

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

		if (!(obj instanceof TrainingImportLogClp)) {
			return false;
		}

		TrainingImportLogClp trainingImportLog = (TrainingImportLogClp)obj;

		long primaryKey = trainingImportLog.getPrimaryKey();

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

		sb.append("{trainingImportLogId=");
		sb.append(getTrainingImportLogId());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", importDate=");
		sb.append(getImportDate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TrainingImportLog");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>trainingImportLogId</column-name><column-value><![CDATA[");
		sb.append(getTrainingImportLogId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>importDate</column-name><column-value><![CDATA[");
		sb.append(getImportDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _trainingImportLogId;
	private int _type;
	private Date _importDate;
	private BaseModel<?> _trainingImportLogRemoteModel;
}