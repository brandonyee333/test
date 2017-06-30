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

import com.liferay.osb.service.AppPackageLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
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
public class AppPackageClp extends BaseModelImpl<AppPackage>
	implements AppPackage {
	public AppPackageClp() {
	}

	public Class<?> getModelClass() {
		return AppPackage.class;
	}

	public String getModelClassName() {
		return AppPackage.class.getName();
	}

	public long getPrimaryKey() {
		return _appPackageId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAppPackageId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_appPackageId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("appPackageId", getAppPackageId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("appEntryId", getAppEntryId());
		attributes.put("appVersionId", getAppVersionId());
		attributes.put("compatibility", getCompatibility());
		attributes.put("compatibilityPlus", getCompatibilityPlus());
		attributes.put("prepackaged", getPrepackaged());
		attributes.put("downloadCount", getDownloadCount());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long appPackageId = (Long)attributes.get("appPackageId");

		if (appPackageId != null) {
			setAppPackageId(appPackageId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long appEntryId = (Long)attributes.get("appEntryId");

		if (appEntryId != null) {
			setAppEntryId(appEntryId);
		}

		Long appVersionId = (Long)attributes.get("appVersionId");

		if (appVersionId != null) {
			setAppVersionId(appVersionId);
		}

		Integer compatibility = (Integer)attributes.get("compatibility");

		if (compatibility != null) {
			setCompatibility(compatibility);
		}

		Boolean compatibilityPlus = (Boolean)attributes.get("compatibilityPlus");

		if (compatibilityPlus != null) {
			setCompatibilityPlus(compatibilityPlus);
		}

		Boolean prepackaged = (Boolean)attributes.get("prepackaged");

		if (prepackaged != null) {
			setPrepackaged(prepackaged);
		}

		Integer downloadCount = (Integer)attributes.get("downloadCount");

		if (downloadCount != null) {
			setDownloadCount(downloadCount);
		}
	}

	public long getAppPackageId() {
		return _appPackageId;
	}

	public void setAppPackageId(long appPackageId) {
		_appPackageId = appPackageId;

		if (_appPackageRemoteModel != null) {
			try {
				Class<?> clazz = _appPackageRemoteModel.getClass();

				Method method = clazz.getMethod("setAppPackageId", long.class);

				method.invoke(_appPackageRemoteModel, appPackageId);
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

		if (_appPackageRemoteModel != null) {
			try {
				Class<?> clazz = _appPackageRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_appPackageRemoteModel, createDate);
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

		if (_appPackageRemoteModel != null) {
			try {
				Class<?> clazz = _appPackageRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_appPackageRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAppEntryId() {
		return _appEntryId;
	}

	public void setAppEntryId(long appEntryId) {
		_appEntryId = appEntryId;

		if (_appPackageRemoteModel != null) {
			try {
				Class<?> clazz = _appPackageRemoteModel.getClass();

				Method method = clazz.getMethod("setAppEntryId", long.class);

				method.invoke(_appPackageRemoteModel, appEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAppVersionId() {
		return _appVersionId;
	}

	public void setAppVersionId(long appVersionId) {
		_appVersionId = appVersionId;

		if (_appPackageRemoteModel != null) {
			try {
				Class<?> clazz = _appPackageRemoteModel.getClass();

				Method method = clazz.getMethod("setAppVersionId", long.class);

				method.invoke(_appPackageRemoteModel, appVersionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getCompatibility() {
		return _compatibility;
	}

	public void setCompatibility(int compatibility) {
		_compatibility = compatibility;

		if (_appPackageRemoteModel != null) {
			try {
				Class<?> clazz = _appPackageRemoteModel.getClass();

				Method method = clazz.getMethod("setCompatibility", int.class);

				method.invoke(_appPackageRemoteModel, compatibility);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getCompatibilityPlus() {
		return _compatibilityPlus;
	}

	public boolean isCompatibilityPlus() {
		return _compatibilityPlus;
	}

	public void setCompatibilityPlus(boolean compatibilityPlus) {
		_compatibilityPlus = compatibilityPlus;

		if (_appPackageRemoteModel != null) {
			try {
				Class<?> clazz = _appPackageRemoteModel.getClass();

				Method method = clazz.getMethod("setCompatibilityPlus",
						boolean.class);

				method.invoke(_appPackageRemoteModel, compatibilityPlus);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getPrepackaged() {
		return _prepackaged;
	}

	public boolean isPrepackaged() {
		return _prepackaged;
	}

	public void setPrepackaged(boolean prepackaged) {
		_prepackaged = prepackaged;

		if (_appPackageRemoteModel != null) {
			try {
				Class<?> clazz = _appPackageRemoteModel.getClass();

				Method method = clazz.getMethod("setPrepackaged", boolean.class);

				method.invoke(_appPackageRemoteModel, prepackaged);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getDownloadCount() {
		return _downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		_downloadCount = downloadCount;

		if (_appPackageRemoteModel != null) {
			try {
				Class<?> clazz = _appPackageRemoteModel.getClass();

				Method method = clazz.getMethod("setDownloadCount", int.class);

				method.invoke(_appPackageRemoteModel, downloadCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public com.liferay.osb.model.AppVersion getAppVersion() {
		try {
			String methodName = "getAppVersion";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.AppVersion returnObj = (com.liferay.osb.model.AppVersion)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.List<com.liferay.osb.model.AppPackagePlugin> getAppPackagePlugins() {
		try {
			String methodName = "getAppPackagePlugins";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.osb.model.AppPackagePlugin> returnObj = (java.util.List<com.liferay.osb.model.AppPackagePlugin>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isPortalRestartRequired() {
		try {
			String methodName = "isPortalRestartRequired";

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

	public com.liferay.portal.kernel.plugin.Version getVersion() {
		try {
			String methodName = "getVersion";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.kernel.plugin.Version returnObj = (com.liferay.portal.kernel.plugin.Version)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.osb.model.AppEntry getAppEntry() {
		try {
			String methodName = "getAppEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.AppEntry returnObj = (com.liferay.osb.model.AppEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getAppPackageRemoteModel() {
		return _appPackageRemoteModel;
	}

	public void setAppPackageRemoteModel(BaseModel<?> appPackageRemoteModel) {
		_appPackageRemoteModel = appPackageRemoteModel;
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

		Class<?> remoteModelClass = _appPackageRemoteModel.getClass();

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

		Object returnValue = method.invoke(_appPackageRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AppPackageLocalServiceUtil.addAppPackage(this);
		}
		else {
			AppPackageLocalServiceUtil.updateAppPackage(this);
		}
	}

	@Override
	public AppPackage toEscapedModel() {
		return (AppPackage)ProxyUtil.newProxyInstance(AppPackage.class.getClassLoader(),
			new Class[] { AppPackage.class }, new AutoEscapeBeanHandler(this));
	}

	public AppPackage toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AppPackageClp clone = new AppPackageClp();

		clone.setAppPackageId(getAppPackageId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAppEntryId(getAppEntryId());
		clone.setAppVersionId(getAppVersionId());
		clone.setCompatibility(getCompatibility());
		clone.setCompatibilityPlus(getCompatibilityPlus());
		clone.setPrepackaged(getPrepackaged());
		clone.setDownloadCount(getDownloadCount());

		return clone;
	}

	public int compareTo(AppPackage appPackage) {
		int value = 0;

		if (getCompatibility() < appPackage.getCompatibility()) {
			value = -1;
		}
		else if (getCompatibility() > appPackage.getCompatibility()) {
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

		if (!(obj instanceof AppPackageClp)) {
			return false;
		}

		AppPackageClp appPackage = (AppPackageClp)obj;

		long primaryKey = appPackage.getPrimaryKey();

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
		StringBundler sb = new StringBundler(19);

		sb.append("{appPackageId=");
		sb.append(getAppPackageId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", appEntryId=");
		sb.append(getAppEntryId());
		sb.append(", appVersionId=");
		sb.append(getAppVersionId());
		sb.append(", compatibility=");
		sb.append(getCompatibility());
		sb.append(", compatibilityPlus=");
		sb.append(getCompatibilityPlus());
		sb.append(", prepackaged=");
		sb.append(getPrepackaged());
		sb.append(", downloadCount=");
		sb.append(getDownloadCount());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AppPackage");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>appPackageId</column-name><column-value><![CDATA[");
		sb.append(getAppPackageId());
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
			"<column><column-name>appEntryId</column-name><column-value><![CDATA[");
		sb.append(getAppEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>appVersionId</column-name><column-value><![CDATA[");
		sb.append(getAppVersionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>compatibility</column-name><column-value><![CDATA[");
		sb.append(getCompatibility());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>compatibilityPlus</column-name><column-value><![CDATA[");
		sb.append(getCompatibilityPlus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>prepackaged</column-name><column-value><![CDATA[");
		sb.append(getPrepackaged());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>downloadCount</column-name><column-value><![CDATA[");
		sb.append(getDownloadCount());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _appPackageId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _appEntryId;
	private long _appVersionId;
	private int _compatibility;
	private boolean _compatibilityPlus;
	private boolean _prepackaged;
	private int _downloadCount;
	private BaseModel<?> _appPackageRemoteModel;
}