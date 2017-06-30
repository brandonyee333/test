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
import com.liferay.osb.service.SupportTeamLanguageLocalServiceUtil;

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
public class SupportTeamLanguageClp extends BaseModelImpl<SupportTeamLanguage>
	implements SupportTeamLanguage {
	public SupportTeamLanguageClp() {
	}

	public Class<?> getModelClass() {
		return SupportTeamLanguage.class;
	}

	public String getModelClassName() {
		return SupportTeamLanguage.class.getName();
	}

	public long getPrimaryKey() {
		return _supportTeamLanguageId;
	}

	public void setPrimaryKey(long primaryKey) {
		setSupportTeamLanguageId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_supportTeamLanguageId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportTeamLanguageId", getSupportTeamLanguageId());
		attributes.put("supportTeamId", getSupportTeamId());
		attributes.put("languageId", getLanguageId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportTeamLanguageId = (Long)attributes.get(
				"supportTeamLanguageId");

		if (supportTeamLanguageId != null) {
			setSupportTeamLanguageId(supportTeamLanguageId);
		}

		Long supportTeamId = (Long)attributes.get("supportTeamId");

		if (supportTeamId != null) {
			setSupportTeamId(supportTeamId);
		}

		String languageId = (String)attributes.get("languageId");

		if (languageId != null) {
			setLanguageId(languageId);
		}
	}

	public long getSupportTeamLanguageId() {
		return _supportTeamLanguageId;
	}

	public void setSupportTeamLanguageId(long supportTeamLanguageId) {
		_supportTeamLanguageId = supportTeamLanguageId;

		if (_supportTeamLanguageRemoteModel != null) {
			try {
				Class<?> clazz = _supportTeamLanguageRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportTeamLanguageId",
						long.class);

				method.invoke(_supportTeamLanguageRemoteModel,
					supportTeamLanguageId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getSupportTeamId() {
		return _supportTeamId;
	}

	public void setSupportTeamId(long supportTeamId) {
		_supportTeamId = supportTeamId;

		if (_supportTeamLanguageRemoteModel != null) {
			try {
				Class<?> clazz = _supportTeamLanguageRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportTeamId", long.class);

				method.invoke(_supportTeamLanguageRemoteModel, supportTeamId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getLanguageId() {
		return _languageId;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;

		if (_supportTeamLanguageRemoteModel != null) {
			try {
				Class<?> clazz = _supportTeamLanguageRemoteModel.getClass();

				Method method = clazz.getMethod("setLanguageId", String.class);

				method.invoke(_supportTeamLanguageRemoteModel, languageId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSupportTeamLanguageRemoteModel() {
		return _supportTeamLanguageRemoteModel;
	}

	public void setSupportTeamLanguageRemoteModel(
		BaseModel<?> supportTeamLanguageRemoteModel) {
		_supportTeamLanguageRemoteModel = supportTeamLanguageRemoteModel;
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

		Class<?> remoteModelClass = _supportTeamLanguageRemoteModel.getClass();

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

		Object returnValue = method.invoke(_supportTeamLanguageRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			SupportTeamLanguageLocalServiceUtil.addSupportTeamLanguage(this);
		}
		else {
			SupportTeamLanguageLocalServiceUtil.updateSupportTeamLanguage(this);
		}
	}

	@Override
	public SupportTeamLanguage toEscapedModel() {
		return (SupportTeamLanguage)ProxyUtil.newProxyInstance(SupportTeamLanguage.class.getClassLoader(),
			new Class[] { SupportTeamLanguage.class },
			new AutoEscapeBeanHandler(this));
	}

	public SupportTeamLanguage toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		SupportTeamLanguageClp clone = new SupportTeamLanguageClp();

		clone.setSupportTeamLanguageId(getSupportTeamLanguageId());
		clone.setSupportTeamId(getSupportTeamId());
		clone.setLanguageId(getLanguageId());

		return clone;
	}

	public int compareTo(SupportTeamLanguage supportTeamLanguage) {
		int value = 0;

		value = getLanguageId().compareTo(supportTeamLanguage.getLanguageId());

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

		if (!(obj instanceof SupportTeamLanguageClp)) {
			return false;
		}

		SupportTeamLanguageClp supportTeamLanguage = (SupportTeamLanguageClp)obj;

		long primaryKey = supportTeamLanguage.getPrimaryKey();

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

		sb.append("{supportTeamLanguageId=");
		sb.append(getSupportTeamLanguageId());
		sb.append(", supportTeamId=");
		sb.append(getSupportTeamId());
		sb.append(", languageId=");
		sb.append(getLanguageId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.SupportTeamLanguage");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>supportTeamLanguageId</column-name><column-value><![CDATA[");
		sb.append(getSupportTeamLanguageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportTeamId</column-name><column-value><![CDATA[");
		sb.append(getSupportTeamId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>languageId</column-name><column-value><![CDATA[");
		sb.append(getLanguageId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _supportTeamLanguageId;
	private long _supportTeamId;
	private String _languageId;
	private BaseModel<?> _supportTeamLanguageRemoteModel;
}