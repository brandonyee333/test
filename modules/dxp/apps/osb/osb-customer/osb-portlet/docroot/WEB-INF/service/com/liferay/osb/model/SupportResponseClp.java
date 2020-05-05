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
import com.liferay.osb.service.SupportResponseLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class SupportResponseClp extends BaseModelImpl<SupportResponse>
	implements SupportResponse {
	public SupportResponseClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SupportResponse.class;
	}

	@Override
	public String getModelClassName() {
		return SupportResponse.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _supportResponseId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSupportResponseId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _supportResponseId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportResponseId", getSupportResponseId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("supportLevel", getSupportLevel());
		attributes.put("severity1Response", getSeverity1Response());
		attributes.put("severity1Resolution", getSeverity1Resolution());
		attributes.put("severity2Response", getSeverity2Response());
		attributes.put("severity2Resolution", getSeverity2Resolution());
		attributes.put("severity3Response", getSeverity3Response());
		attributes.put("severity3Resolution", getSeverity3Resolution());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportResponseId = (Long)attributes.get("supportResponseId");

		if (supportResponseId != null) {
			setSupportResponseId(supportResponseId);
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

		Integer supportLevel = (Integer)attributes.get("supportLevel");

		if (supportLevel != null) {
			setSupportLevel(supportLevel);
		}

		Integer severity1Response = (Integer)attributes.get("severity1Response");

		if (severity1Response != null) {
			setSeverity1Response(severity1Response);
		}

		Integer severity1Resolution = (Integer)attributes.get(
				"severity1Resolution");

		if (severity1Resolution != null) {
			setSeverity1Resolution(severity1Resolution);
		}

		Integer severity2Response = (Integer)attributes.get("severity2Response");

		if (severity2Response != null) {
			setSeverity2Response(severity2Response);
		}

		Integer severity2Resolution = (Integer)attributes.get(
				"severity2Resolution");

		if (severity2Resolution != null) {
			setSeverity2Resolution(severity2Resolution);
		}

		Integer severity3Response = (Integer)attributes.get("severity3Response");

		if (severity3Response != null) {
			setSeverity3Response(severity3Response);
		}

		Integer severity3Resolution = (Integer)attributes.get(
				"severity3Resolution");

		if (severity3Resolution != null) {
			setSeverity3Resolution(severity3Resolution);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getSupportResponseId() {
		return _supportResponseId;
	}

	@Override
	public void setSupportResponseId(long supportResponseId) {
		_supportResponseId = supportResponseId;

		if (_supportResponseRemoteModel != null) {
			try {
				Class<?> clazz = _supportResponseRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportResponseId",
						long.class);

				method.invoke(_supportResponseRemoteModel, supportResponseId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_supportResponseRemoteModel != null) {
			try {
				Class<?> clazz = _supportResponseRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_supportResponseRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_supportResponseRemoteModel != null) {
			try {
				Class<?> clazz = _supportResponseRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_supportResponseRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_supportResponseRemoteModel != null) {
			try {
				Class<?> clazz = _supportResponseRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_supportResponseRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_supportResponseRemoteModel != null) {
			try {
				Class<?> clazz = _supportResponseRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_supportResponseRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;

		if (_supportResponseRemoteModel != null) {
			try {
				Class<?> clazz = _supportResponseRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_supportResponseRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSupportLevel() {
		return _supportLevel;
	}

	@Override
	public void setSupportLevel(int supportLevel) {
		_supportLevel = supportLevel;

		if (_supportResponseRemoteModel != null) {
			try {
				Class<?> clazz = _supportResponseRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportLevel", int.class);

				method.invoke(_supportResponseRemoteModel, supportLevel);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSeverity1Response() {
		return _severity1Response;
	}

	@Override
	public void setSeverity1Response(int severity1Response) {
		_severity1Response = severity1Response;

		if (_supportResponseRemoteModel != null) {
			try {
				Class<?> clazz = _supportResponseRemoteModel.getClass();

				Method method = clazz.getMethod("setSeverity1Response",
						int.class);

				method.invoke(_supportResponseRemoteModel, severity1Response);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSeverity1Resolution() {
		return _severity1Resolution;
	}

	@Override
	public void setSeverity1Resolution(int severity1Resolution) {
		_severity1Resolution = severity1Resolution;

		if (_supportResponseRemoteModel != null) {
			try {
				Class<?> clazz = _supportResponseRemoteModel.getClass();

				Method method = clazz.getMethod("setSeverity1Resolution",
						int.class);

				method.invoke(_supportResponseRemoteModel, severity1Resolution);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSeverity2Response() {
		return _severity2Response;
	}

	@Override
	public void setSeverity2Response(int severity2Response) {
		_severity2Response = severity2Response;

		if (_supportResponseRemoteModel != null) {
			try {
				Class<?> clazz = _supportResponseRemoteModel.getClass();

				Method method = clazz.getMethod("setSeverity2Response",
						int.class);

				method.invoke(_supportResponseRemoteModel, severity2Response);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSeverity2Resolution() {
		return _severity2Resolution;
	}

	@Override
	public void setSeverity2Resolution(int severity2Resolution) {
		_severity2Resolution = severity2Resolution;

		if (_supportResponseRemoteModel != null) {
			try {
				Class<?> clazz = _supportResponseRemoteModel.getClass();

				Method method = clazz.getMethod("setSeverity2Resolution",
						int.class);

				method.invoke(_supportResponseRemoteModel, severity2Resolution);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSeverity3Response() {
		return _severity3Response;
	}

	@Override
	public void setSeverity3Response(int severity3Response) {
		_severity3Response = severity3Response;

		if (_supportResponseRemoteModel != null) {
			try {
				Class<?> clazz = _supportResponseRemoteModel.getClass();

				Method method = clazz.getMethod("setSeverity3Response",
						int.class);

				method.invoke(_supportResponseRemoteModel, severity3Response);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSeverity3Resolution() {
		return _severity3Resolution;
	}

	@Override
	public void setSeverity3Resolution(int severity3Resolution) {
		_severity3Resolution = severity3Resolution;

		if (_supportResponseRemoteModel != null) {
			try {
				Class<?> clazz = _supportResponseRemoteModel.getClass();

				Method method = clazz.getMethod("setSeverity3Resolution",
						int.class);

				method.invoke(_supportResponseRemoteModel, severity3Resolution);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSeverityResolution(int severityLevel) {
		try {
			String methodName = "getSeverityResolution";

			Class<?>[] parameterTypes = new Class<?>[] { int.class };

			Object[] parameterValues = new Object[] { severityLevel };

			Integer returnObj = (Integer)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public int getSeverityResponse(int severityLevel) {
		try {
			String methodName = "getSeverityResponse";

			Class<?>[] parameterTypes = new Class<?>[] { int.class };

			Object[] parameterValues = new Object[] { severityLevel };

			Integer returnObj = (Integer)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getSupportLevelLabel() {
		try {
			String methodName = "getSupportLevelLabel";

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

	@Override
	public java.lang.String getSupportLevelLabel(Locale locale) {
		try {
			String methodName = "getSupportLevelLabel";

			Class<?>[] parameterTypes = new Class<?>[] { Locale.class };

			Object[] parameterValues = new Object[] { locale };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public boolean isPlatinumLevel() {
		try {
			String methodName = "isPlatinumLevel";

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

	public BaseModel<?> getSupportResponseRemoteModel() {
		return _supportResponseRemoteModel;
	}

	public void setSupportResponseRemoteModel(
		BaseModel<?> supportResponseRemoteModel) {
		_supportResponseRemoteModel = supportResponseRemoteModel;
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

		Class<?> remoteModelClass = _supportResponseRemoteModel.getClass();

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

		Object returnValue = method.invoke(_supportResponseRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			SupportResponseLocalServiceUtil.addSupportResponse(this);
		}
		else {
			SupportResponseLocalServiceUtil.updateSupportResponse(this);
		}
	}

	@Override
	public SupportResponse toEscapedModel() {
		return (SupportResponse)ProxyUtil.newProxyInstance(SupportResponse.class.getClassLoader(),
			new Class[] { SupportResponse.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SupportResponseClp clone = new SupportResponseClp();

		clone.setSupportResponseId(getSupportResponseId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setSupportLevel(getSupportLevel());
		clone.setSeverity1Response(getSeverity1Response());
		clone.setSeverity1Resolution(getSeverity1Resolution());
		clone.setSeverity2Response(getSeverity2Response());
		clone.setSeverity2Resolution(getSeverity2Resolution());
		clone.setSeverity3Response(getSeverity3Response());
		clone.setSeverity3Resolution(getSeverity3Resolution());

		return clone;
	}

	@Override
	public int compareTo(SupportResponse supportResponse) {
		int value = 0;

		value = getName().compareToIgnoreCase(supportResponse.getName());

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

		if (!(obj instanceof SupportResponseClp)) {
			return false;
		}

		SupportResponseClp supportResponse = (SupportResponseClp)obj;

		long primaryKey = supportResponse.getPrimaryKey();

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
		StringBundler sb = new StringBundler(27);

		sb.append("{supportResponseId=");
		sb.append(getSupportResponseId());
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
		sb.append(", supportLevel=");
		sb.append(getSupportLevel());
		sb.append(", severity1Response=");
		sb.append(getSeverity1Response());
		sb.append(", severity1Resolution=");
		sb.append(getSeverity1Resolution());
		sb.append(", severity2Response=");
		sb.append(getSeverity2Response());
		sb.append(", severity2Resolution=");
		sb.append(getSeverity2Resolution());
		sb.append(", severity3Response=");
		sb.append(getSeverity3Response());
		sb.append(", severity3Resolution=");
		sb.append(getSeverity3Resolution());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.SupportResponse");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>supportResponseId</column-name><column-value><![CDATA[");
		sb.append(getSupportResponseId());
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
			"<column><column-name>supportLevel</column-name><column-value><![CDATA[");
		sb.append(getSupportLevel());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>severity1Response</column-name><column-value><![CDATA[");
		sb.append(getSeverity1Response());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>severity1Resolution</column-name><column-value><![CDATA[");
		sb.append(getSeverity1Resolution());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>severity2Response</column-name><column-value><![CDATA[");
		sb.append(getSeverity2Response());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>severity2Resolution</column-name><column-value><![CDATA[");
		sb.append(getSeverity2Resolution());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>severity3Response</column-name><column-value><![CDATA[");
		sb.append(getSeverity3Response());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>severity3Resolution</column-name><column-value><![CDATA[");
		sb.append(getSeverity3Resolution());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _supportResponseId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private int _supportLevel;
	private int _severity1Response;
	private int _severity1Resolution;
	private int _severity2Response;
	private int _severity2Resolution;
	private int _severity3Response;
	private int _severity3Resolution;
	private BaseModel<?> _supportResponseRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}