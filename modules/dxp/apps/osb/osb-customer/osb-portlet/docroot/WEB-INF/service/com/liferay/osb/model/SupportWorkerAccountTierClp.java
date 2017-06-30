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
import com.liferay.osb.service.SupportWorkerAccountTierLocalServiceUtil;

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
public class SupportWorkerAccountTierClp extends BaseModelImpl<SupportWorkerAccountTier>
	implements SupportWorkerAccountTier {
	public SupportWorkerAccountTierClp() {
	}

	public Class<?> getModelClass() {
		return SupportWorkerAccountTier.class;
	}

	public String getModelClassName() {
		return SupportWorkerAccountTier.class.getName();
	}

	public long getPrimaryKey() {
		return _supportWorkerAccountTierId;
	}

	public void setPrimaryKey(long primaryKey) {
		setSupportWorkerAccountTierId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_supportWorkerAccountTierId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportWorkerAccountTierId",
			getSupportWorkerAccountTierId());
		attributes.put("supportWorkerId", getSupportWorkerId());
		attributes.put("accountTier", getAccountTier());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportWorkerAccountTierId = (Long)attributes.get(
				"supportWorkerAccountTierId");

		if (supportWorkerAccountTierId != null) {
			setSupportWorkerAccountTierId(supportWorkerAccountTierId);
		}

		Long supportWorkerId = (Long)attributes.get("supportWorkerId");

		if (supportWorkerId != null) {
			setSupportWorkerId(supportWorkerId);
		}

		Integer accountTier = (Integer)attributes.get("accountTier");

		if (accountTier != null) {
			setAccountTier(accountTier);
		}
	}

	public long getSupportWorkerAccountTierId() {
		return _supportWorkerAccountTierId;
	}

	public void setSupportWorkerAccountTierId(long supportWorkerAccountTierId) {
		_supportWorkerAccountTierId = supportWorkerAccountTierId;

		if (_supportWorkerAccountTierRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerAccountTierRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportWorkerAccountTierId",
						long.class);

				method.invoke(_supportWorkerAccountTierRemoteModel,
					supportWorkerAccountTierId);
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

		if (_supportWorkerAccountTierRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerAccountTierRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportWorkerId", long.class);

				method.invoke(_supportWorkerAccountTierRemoteModel,
					supportWorkerId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getAccountTier() {
		return _accountTier;
	}

	public void setAccountTier(int accountTier) {
		_accountTier = accountTier;

		if (_supportWorkerAccountTierRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerAccountTierRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountTier", int.class);

				method.invoke(_supportWorkerAccountTierRemoteModel, accountTier);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSupportWorkerAccountTierRemoteModel() {
		return _supportWorkerAccountTierRemoteModel;
	}

	public void setSupportWorkerAccountTierRemoteModel(
		BaseModel<?> supportWorkerAccountTierRemoteModel) {
		_supportWorkerAccountTierRemoteModel = supportWorkerAccountTierRemoteModel;
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

		Class<?> remoteModelClass = _supportWorkerAccountTierRemoteModel.getClass();

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

		Object returnValue = method.invoke(_supportWorkerAccountTierRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			SupportWorkerAccountTierLocalServiceUtil.addSupportWorkerAccountTier(this);
		}
		else {
			SupportWorkerAccountTierLocalServiceUtil.updateSupportWorkerAccountTier(this);
		}
	}

	@Override
	public SupportWorkerAccountTier toEscapedModel() {
		return (SupportWorkerAccountTier)ProxyUtil.newProxyInstance(SupportWorkerAccountTier.class.getClassLoader(),
			new Class[] { SupportWorkerAccountTier.class },
			new AutoEscapeBeanHandler(this));
	}

	public SupportWorkerAccountTier toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		SupportWorkerAccountTierClp clone = new SupportWorkerAccountTierClp();

		clone.setSupportWorkerAccountTierId(getSupportWorkerAccountTierId());
		clone.setSupportWorkerId(getSupportWorkerId());
		clone.setAccountTier(getAccountTier());

		return clone;
	}

	public int compareTo(SupportWorkerAccountTier supportWorkerAccountTier) {
		int value = 0;

		if (getAccountTier() < supportWorkerAccountTier.getAccountTier()) {
			value = -1;
		}
		else if (getAccountTier() > supportWorkerAccountTier.getAccountTier()) {
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

		if (!(obj instanceof SupportWorkerAccountTierClp)) {
			return false;
		}

		SupportWorkerAccountTierClp supportWorkerAccountTier = (SupportWorkerAccountTierClp)obj;

		long primaryKey = supportWorkerAccountTier.getPrimaryKey();

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

		sb.append("{supportWorkerAccountTierId=");
		sb.append(getSupportWorkerAccountTierId());
		sb.append(", supportWorkerId=");
		sb.append(getSupportWorkerId());
		sb.append(", accountTier=");
		sb.append(getAccountTier());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.SupportWorkerAccountTier");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>supportWorkerAccountTierId</column-name><column-value><![CDATA[");
		sb.append(getSupportWorkerAccountTierId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportWorkerId</column-name><column-value><![CDATA[");
		sb.append(getSupportWorkerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountTier</column-name><column-value><![CDATA[");
		sb.append(getAccountTier());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _supportWorkerAccountTierId;
	private long _supportWorkerId;
	private int _accountTier;
	private BaseModel<?> _supportWorkerAccountTierRemoteModel;
}