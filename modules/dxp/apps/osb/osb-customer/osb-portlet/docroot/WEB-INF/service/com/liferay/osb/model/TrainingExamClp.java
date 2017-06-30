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
import com.liferay.osb.service.TrainingExamLocalServiceUtil;

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
public class TrainingExamClp extends BaseModelImpl<TrainingExam>
	implements TrainingExam {
	public TrainingExamClp() {
	}

	public Class<?> getModelClass() {
		return TrainingExam.class;
	}

	public String getModelClassName() {
		return TrainingExam.class.getName();
	}

	public long getPrimaryKey() {
		return _trainingExamId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTrainingExamId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_trainingExamId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingExamId", getTrainingExamId());
		attributes.put("trainingCertificateTemplateId",
			getTrainingCertificateTemplateId());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingExamId = (Long)attributes.get("trainingExamId");

		if (trainingExamId != null) {
			setTrainingExamId(trainingExamId);
		}

		Long trainingCertificateTemplateId = (Long)attributes.get(
				"trainingCertificateTemplateId");

		if (trainingCertificateTemplateId != null) {
			setTrainingCertificateTemplateId(trainingCertificateTemplateId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	public long getTrainingExamId() {
		return _trainingExamId;
	}

	public void setTrainingExamId(long trainingExamId) {
		_trainingExamId = trainingExamId;

		if (_trainingExamRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingExamId", long.class);

				method.invoke(_trainingExamRemoteModel, trainingExamId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getTrainingCertificateTemplateId() {
		return _trainingCertificateTemplateId;
	}

	public void setTrainingCertificateTemplateId(
		long trainingCertificateTemplateId) {
		_trainingCertificateTemplateId = trainingCertificateTemplateId;

		if (_trainingExamRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingCertificateTemplateId",
						long.class);

				method.invoke(_trainingExamRemoteModel,
					trainingCertificateTemplateId);
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

		if (_trainingExamRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_trainingExamRemoteModel, name);
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

	public java.lang.String getTestKey() {
		try {
			String methodName = "getTestKey";

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

	public BaseModel<?> getTrainingExamRemoteModel() {
		return _trainingExamRemoteModel;
	}

	public void setTrainingExamRemoteModel(BaseModel<?> trainingExamRemoteModel) {
		_trainingExamRemoteModel = trainingExamRemoteModel;
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

		Class<?> remoteModelClass = _trainingExamRemoteModel.getClass();

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

		Object returnValue = method.invoke(_trainingExamRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TrainingExamLocalServiceUtil.addTrainingExam(this);
		}
		else {
			TrainingExamLocalServiceUtil.updateTrainingExam(this);
		}
	}

	@Override
	public TrainingExam toEscapedModel() {
		return (TrainingExam)ProxyUtil.newProxyInstance(TrainingExam.class.getClassLoader(),
			new Class[] { TrainingExam.class }, new AutoEscapeBeanHandler(this));
	}

	public TrainingExam toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		TrainingExamClp clone = new TrainingExamClp();

		clone.setTrainingExamId(getTrainingExamId());
		clone.setTrainingCertificateTemplateId(getTrainingCertificateTemplateId());
		clone.setName(getName());

		return clone;
	}

	public int compareTo(TrainingExam trainingExam) {
		long primaryKey = trainingExam.getPrimaryKey();

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

		if (!(obj instanceof TrainingExamClp)) {
			return false;
		}

		TrainingExamClp trainingExam = (TrainingExamClp)obj;

		long primaryKey = trainingExam.getPrimaryKey();

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

		sb.append("{trainingExamId=");
		sb.append(getTrainingExamId());
		sb.append(", trainingCertificateTemplateId=");
		sb.append(getTrainingCertificateTemplateId());
		sb.append(", name=");
		sb.append(getName());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TrainingExam");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>trainingExamId</column-name><column-value><![CDATA[");
		sb.append(getTrainingExamId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>trainingCertificateTemplateId</column-name><column-value><![CDATA[");
		sb.append(getTrainingCertificateTemplateId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _trainingExamId;
	private long _trainingCertificateTemplateId;
	private String _name;
	private BaseModel<?> _trainingExamRemoteModel;
}