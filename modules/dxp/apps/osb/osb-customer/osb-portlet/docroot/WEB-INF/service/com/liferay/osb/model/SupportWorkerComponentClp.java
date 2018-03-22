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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.service.ClpSerializer;
import com.liferay.osb.service.SupportWorkerComponentLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class SupportWorkerComponentClp extends BaseModelImpl<SupportWorkerComponent>
	implements SupportWorkerComponent {
	public SupportWorkerComponentClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SupportWorkerComponent.class;
	}

	@Override
	public String getModelClassName() {
		return SupportWorkerComponent.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _supportWorkerComponentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSupportWorkerComponentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _supportWorkerComponentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportWorkerComponentId", getSupportWorkerComponentId());
		attributes.put("supportWorkerId", getSupportWorkerId());
		attributes.put("component", getComponent());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportWorkerComponentId = (Long)attributes.get(
				"supportWorkerComponentId");

		if (supportWorkerComponentId != null) {
			setSupportWorkerComponentId(supportWorkerComponentId);
		}

		Long supportWorkerId = (Long)attributes.get("supportWorkerId");

		if (supportWorkerId != null) {
			setSupportWorkerId(supportWorkerId);
		}

		Integer component = (Integer)attributes.get("component");

		if (component != null) {
			setComponent(component);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getSupportWorkerComponentId() {
		return _supportWorkerComponentId;
	}

	@Override
	public void setSupportWorkerComponentId(long supportWorkerComponentId) {
		_supportWorkerComponentId = supportWorkerComponentId;

		if (_supportWorkerComponentRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerComponentRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportWorkerComponentId",
						long.class);

				method.invoke(_supportWorkerComponentRemoteModel,
					supportWorkerComponentId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSupportWorkerId() {
		return _supportWorkerId;
	}

	@Override
	public void setSupportWorkerId(long supportWorkerId) {
		_supportWorkerId = supportWorkerId;

		if (_supportWorkerComponentRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerComponentRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportWorkerId", long.class);

				method.invoke(_supportWorkerComponentRemoteModel,
					supportWorkerId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getComponent() {
		return _component;
	}

	@Override
	public void setComponent(int component) {
		_component = component;

		if (_supportWorkerComponentRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerComponentRemoteModel.getClass();

				Method method = clazz.getMethod("setComponent", int.class);

				method.invoke(_supportWorkerComponentRemoteModel, component);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSupportWorkerComponentRemoteModel() {
		return _supportWorkerComponentRemoteModel;
	}

	public void setSupportWorkerComponentRemoteModel(
		BaseModel<?> supportWorkerComponentRemoteModel) {
		_supportWorkerComponentRemoteModel = supportWorkerComponentRemoteModel;
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

		Class<?> remoteModelClass = _supportWorkerComponentRemoteModel.getClass();

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

		Object returnValue = method.invoke(_supportWorkerComponentRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			SupportWorkerComponentLocalServiceUtil.addSupportWorkerComponent(this);
		}
		else {
			SupportWorkerComponentLocalServiceUtil.updateSupportWorkerComponent(this);
		}
	}

	@Override
	public SupportWorkerComponent toEscapedModel() {
		return (SupportWorkerComponent)ProxyUtil.newProxyInstance(SupportWorkerComponent.class.getClassLoader(),
			new Class[] { SupportWorkerComponent.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SupportWorkerComponentClp clone = new SupportWorkerComponentClp();

		clone.setSupportWorkerComponentId(getSupportWorkerComponentId());
		clone.setSupportWorkerId(getSupportWorkerId());
		clone.setComponent(getComponent());

		return clone;
	}

	@Override
	public int compareTo(SupportWorkerComponent supportWorkerComponent) {
		int value = 0;

		if (getComponent() < supportWorkerComponent.getComponent()) {
			value = -1;
		}
		else if (getComponent() > supportWorkerComponent.getComponent()) {
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

		if (!(obj instanceof SupportWorkerComponentClp)) {
			return false;
		}

		SupportWorkerComponentClp supportWorkerComponent = (SupportWorkerComponentClp)obj;

		long primaryKey = supportWorkerComponent.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{supportWorkerComponentId=");
		sb.append(getSupportWorkerComponentId());
		sb.append(", supportWorkerId=");
		sb.append(getSupportWorkerId());
		sb.append(", component=");
		sb.append(getComponent());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.SupportWorkerComponent");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>supportWorkerComponentId</column-name><column-value><![CDATA[");
		sb.append(getSupportWorkerComponentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportWorkerId</column-name><column-value><![CDATA[");
		sb.append(getSupportWorkerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>component</column-name><column-value><![CDATA[");
		sb.append(getComponent());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _supportWorkerComponentId;
	private long _supportWorkerId;
	private int _component;
	private BaseModel<?> _supportWorkerComponentRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}