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
import com.liferay.osb.service.SupportWorkerSeverityLocalServiceUtil;

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
public class SupportWorkerSeverityClp extends BaseModelImpl<SupportWorkerSeverity>
	implements SupportWorkerSeverity {
	public SupportWorkerSeverityClp() {
	}

	public Class<?> getModelClass() {
		return SupportWorkerSeverity.class;
	}

	public String getModelClassName() {
		return SupportWorkerSeverity.class.getName();
	}

	public long getPrimaryKey() {
		return _supportWorkerSeverityId;
	}

	public void setPrimaryKey(long primaryKey) {
		setSupportWorkerSeverityId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_supportWorkerSeverityId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportWorkerSeverityId", getSupportWorkerSeverityId());
		attributes.put("supportWorkerId", getSupportWorkerId());
		attributes.put("severity", getSeverity());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportWorkerSeverityId = (Long)attributes.get(
				"supportWorkerSeverityId");

		if (supportWorkerSeverityId != null) {
			setSupportWorkerSeverityId(supportWorkerSeverityId);
		}

		Long supportWorkerId = (Long)attributes.get("supportWorkerId");

		if (supportWorkerId != null) {
			setSupportWorkerId(supportWorkerId);
		}

		Integer severity = (Integer)attributes.get("severity");

		if (severity != null) {
			setSeverity(severity);
		}
	}

	public long getSupportWorkerSeverityId() {
		return _supportWorkerSeverityId;
	}

	public void setSupportWorkerSeverityId(long supportWorkerSeverityId) {
		_supportWorkerSeverityId = supportWorkerSeverityId;

		if (_supportWorkerSeverityRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerSeverityRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportWorkerSeverityId",
						long.class);

				method.invoke(_supportWorkerSeverityRemoteModel,
					supportWorkerSeverityId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getSupportWorkerId() {
		return _supportWorkerId;
	}

	public void setSupportWorkerId(long supportWorkerId) {
		_supportWorkerId = supportWorkerId;

		if (_supportWorkerSeverityRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerSeverityRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportWorkerId", long.class);

				method.invoke(_supportWorkerSeverityRemoteModel, supportWorkerId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getSeverity() {
		return _severity;
	}

	public void setSeverity(int severity) {
		_severity = severity;

		if (_supportWorkerSeverityRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerSeverityRemoteModel.getClass();

				Method method = clazz.getMethod("setSeverity", int.class);

				method.invoke(_supportWorkerSeverityRemoteModel, severity);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSupportWorkerSeverityRemoteModel() {
		return _supportWorkerSeverityRemoteModel;
	}

	public void setSupportWorkerSeverityRemoteModel(
		BaseModel<?> supportWorkerSeverityRemoteModel) {
		_supportWorkerSeverityRemoteModel = supportWorkerSeverityRemoteModel;
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

		Class<?> remoteModelClass = _supportWorkerSeverityRemoteModel.getClass();

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

		Object returnValue = method.invoke(_supportWorkerSeverityRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			SupportWorkerSeverityLocalServiceUtil.addSupportWorkerSeverity(this);
		}
		else {
			SupportWorkerSeverityLocalServiceUtil.updateSupportWorkerSeverity(this);
		}
	}

	@Override
	public SupportWorkerSeverity toEscapedModel() {
		return (SupportWorkerSeverity)ProxyUtil.newProxyInstance(SupportWorkerSeverity.class.getClassLoader(),
			new Class[] { SupportWorkerSeverity.class },
			new AutoEscapeBeanHandler(this));
	}

	public SupportWorkerSeverity toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		SupportWorkerSeverityClp clone = new SupportWorkerSeverityClp();

		clone.setSupportWorkerSeverityId(getSupportWorkerSeverityId());
		clone.setSupportWorkerId(getSupportWorkerId());
		clone.setSeverity(getSeverity());

		return clone;
	}

	public int compareTo(SupportWorkerSeverity supportWorkerSeverity) {
		int value = 0;

		if (getSeverity() < supportWorkerSeverity.getSeverity()) {
			value = -1;
		}
		else if (getSeverity() > supportWorkerSeverity.getSeverity()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(obj instanceof SupportWorkerSeverityClp)) {
			return false;
		}

		SupportWorkerSeverityClp supportWorkerSeverity = (SupportWorkerSeverityClp)obj;

		long primaryKey = supportWorkerSeverity.getPrimaryKey();

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

		sb.append("{supportWorkerSeverityId=");
		sb.append(getSupportWorkerSeverityId());
		sb.append(", supportWorkerId=");
		sb.append(getSupportWorkerId());
		sb.append(", severity=");
		sb.append(getSeverity());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.SupportWorkerSeverity");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>supportWorkerSeverityId</column-name><column-value><![CDATA[");
		sb.append(getSupportWorkerSeverityId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportWorkerId</column-name><column-value><![CDATA[");
		sb.append(getSupportWorkerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>severity</column-name><column-value><![CDATA[");
		sb.append(getSeverity());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _supportWorkerSeverityId;
	private long _supportWorkerId;
	private int _severity;
	private BaseModel<?> _supportWorkerSeverityRemoteModel;
}