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

import com.liferay.osb.service.AppPackagePluginLocalServiceUtil;
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
public class AppPackagePluginClp extends BaseModelImpl<AppPackagePlugin>
	implements AppPackagePlugin {
	public AppPackagePluginClp() {
	}

	public Class<?> getModelClass() {
		return AppPackagePlugin.class;
	}

	public String getModelClassName() {
		return AppPackagePlugin.class.getName();
	}

	public long getPrimaryKey() {
		return _appPackagePluginId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAppPackagePluginId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_appPackagePluginId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("appPackagePluginId", getAppPackagePluginId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("appEntryId", getAppEntryId());
		attributes.put("appVersionId", getAppVersionId());
		attributes.put("appPackageId", getAppPackageId());
		attributes.put("assetAttachmentId", getAssetAttachmentId());
		attributes.put("fileName", getFileName());
		attributes.put("bundleSymbolicName", getBundleSymbolicName());
		attributes.put("bundleVersion", getBundleVersion());
		attributes.put("contextName", getContextName());
		attributes.put("paclEnabled", getPaclEnabled());
		attributes.put("relengHash", getRelengHash());
		attributes.put("portalRestartRequired", getPortalRestartRequired());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long appPackagePluginId = (Long)attributes.get("appPackagePluginId");

		if (appPackagePluginId != null) {
			setAppPackagePluginId(appPackagePluginId);
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

		Long appPackageId = (Long)attributes.get("appPackageId");

		if (appPackageId != null) {
			setAppPackageId(appPackageId);
		}

		Long assetAttachmentId = (Long)attributes.get("assetAttachmentId");

		if (assetAttachmentId != null) {
			setAssetAttachmentId(assetAttachmentId);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		String bundleSymbolicName = (String)attributes.get("bundleSymbolicName");

		if (bundleSymbolicName != null) {
			setBundleSymbolicName(bundleSymbolicName);
		}

		String bundleVersion = (String)attributes.get("bundleVersion");

		if (bundleVersion != null) {
			setBundleVersion(bundleVersion);
		}

		String contextName = (String)attributes.get("contextName");

		if (contextName != null) {
			setContextName(contextName);
		}

		Boolean paclEnabled = (Boolean)attributes.get("paclEnabled");

		if (paclEnabled != null) {
			setPaclEnabled(paclEnabled);
		}

		String relengHash = (String)attributes.get("relengHash");

		if (relengHash != null) {
			setRelengHash(relengHash);
		}

		Boolean portalRestartRequired = (Boolean)attributes.get(
				"portalRestartRequired");

		if (portalRestartRequired != null) {
			setPortalRestartRequired(portalRestartRequired);
		}
	}

	public long getAppPackagePluginId() {
		return _appPackagePluginId;
	}

	public void setAppPackagePluginId(long appPackagePluginId) {
		_appPackagePluginId = appPackagePluginId;

		if (_appPackagePluginRemoteModel != null) {
			try {
				Class<?> clazz = _appPackagePluginRemoteModel.getClass();

				Method method = clazz.getMethod("setAppPackagePluginId",
						long.class);

				method.invoke(_appPackagePluginRemoteModel, appPackagePluginId);
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

		if (_appPackagePluginRemoteModel != null) {
			try {
				Class<?> clazz = _appPackagePluginRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_appPackagePluginRemoteModel, createDate);
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

		if (_appPackagePluginRemoteModel != null) {
			try {
				Class<?> clazz = _appPackagePluginRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_appPackagePluginRemoteModel, modifiedDate);
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

		if (_appPackagePluginRemoteModel != null) {
			try {
				Class<?> clazz = _appPackagePluginRemoteModel.getClass();

				Method method = clazz.getMethod("setAppEntryId", long.class);

				method.invoke(_appPackagePluginRemoteModel, appEntryId);
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

		if (_appPackagePluginRemoteModel != null) {
			try {
				Class<?> clazz = _appPackagePluginRemoteModel.getClass();

				Method method = clazz.getMethod("setAppVersionId", long.class);

				method.invoke(_appPackagePluginRemoteModel, appVersionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAppPackageId() {
		return _appPackageId;
	}

	public void setAppPackageId(long appPackageId) {
		_appPackageId = appPackageId;

		if (_appPackagePluginRemoteModel != null) {
			try {
				Class<?> clazz = _appPackagePluginRemoteModel.getClass();

				Method method = clazz.getMethod("setAppPackageId", long.class);

				method.invoke(_appPackagePluginRemoteModel, appPackageId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAssetAttachmentId() {
		return _assetAttachmentId;
	}

	public void setAssetAttachmentId(long assetAttachmentId) {
		_assetAttachmentId = assetAttachmentId;

		if (_appPackagePluginRemoteModel != null) {
			try {
				Class<?> clazz = _appPackagePluginRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetAttachmentId",
						long.class);

				method.invoke(_appPackagePluginRemoteModel, assetAttachmentId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;

		if (_appPackagePluginRemoteModel != null) {
			try {
				Class<?> clazz = _appPackagePluginRemoteModel.getClass();

				Method method = clazz.getMethod("setFileName", String.class);

				method.invoke(_appPackagePluginRemoteModel, fileName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getBundleSymbolicName() {
		return _bundleSymbolicName;
	}

	public void setBundleSymbolicName(String bundleSymbolicName) {
		_bundleSymbolicName = bundleSymbolicName;

		if (_appPackagePluginRemoteModel != null) {
			try {
				Class<?> clazz = _appPackagePluginRemoteModel.getClass();

				Method method = clazz.getMethod("setBundleSymbolicName",
						String.class);

				method.invoke(_appPackagePluginRemoteModel, bundleSymbolicName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getBundleVersion() {
		return _bundleVersion;
	}

	public void setBundleVersion(String bundleVersion) {
		_bundleVersion = bundleVersion;

		if (_appPackagePluginRemoteModel != null) {
			try {
				Class<?> clazz = _appPackagePluginRemoteModel.getClass();

				Method method = clazz.getMethod("setBundleVersion", String.class);

				method.invoke(_appPackagePluginRemoteModel, bundleVersion);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getContextName() {
		return _contextName;
	}

	public void setContextName(String contextName) {
		_contextName = contextName;

		if (_appPackagePluginRemoteModel != null) {
			try {
				Class<?> clazz = _appPackagePluginRemoteModel.getClass();

				Method method = clazz.getMethod("setContextName", String.class);

				method.invoke(_appPackagePluginRemoteModel, contextName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getPaclEnabled() {
		return _paclEnabled;
	}

	public boolean isPaclEnabled() {
		return _paclEnabled;
	}

	public void setPaclEnabled(boolean paclEnabled) {
		_paclEnabled = paclEnabled;

		if (_appPackagePluginRemoteModel != null) {
			try {
				Class<?> clazz = _appPackagePluginRemoteModel.getClass();

				Method method = clazz.getMethod("setPaclEnabled", boolean.class);

				method.invoke(_appPackagePluginRemoteModel, paclEnabled);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getRelengHash() {
		return _relengHash;
	}

	public void setRelengHash(String relengHash) {
		_relengHash = relengHash;

		if (_appPackagePluginRemoteModel != null) {
			try {
				Class<?> clazz = _appPackagePluginRemoteModel.getClass();

				Method method = clazz.getMethod("setRelengHash", String.class);

				method.invoke(_appPackagePluginRemoteModel, relengHash);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getPortalRestartRequired() {
		return _portalRestartRequired;
	}

	public boolean isPortalRestartRequired() {
		return _portalRestartRequired;
	}

	public void setPortalRestartRequired(boolean portalRestartRequired) {
		_portalRestartRequired = portalRestartRequired;

		if (_appPackagePluginRemoteModel != null) {
			try {
				Class<?> clazz = _appPackagePluginRemoteModel.getClass();

				Method method = clazz.getMethod("setPortalRestartRequired",
						boolean.class);

				method.invoke(_appPackagePluginRemoteModel,
					portalRestartRequired);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.lang.String getType() {
		try {
			String methodName = "getType";

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

	public com.liferay.osb.model.AssetAttachment getAssetAttachment() {
		try {
			String methodName = "getAssetAttachment";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.AssetAttachment returnObj = (com.liferay.osb.model.AssetAttachment)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean hasConflictingContextName() {
		try {
			String methodName = "hasConflictingContextName";

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

	public com.liferay.osb.model.AppPackage getAppPackage() {
		try {
			String methodName = "getAppPackage";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.AppPackage returnObj = (com.liferay.osb.model.AppPackage)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.portal.kernel.util.UnicodeProperties getPACLProperties() {
		try {
			String methodName = "getPACLProperties";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.kernel.util.UnicodeProperties returnObj = (com.liferay.portal.kernel.util.UnicodeProperties)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isTypeBundle() {
		try {
			String methodName = "isTypeBundle";

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

	public boolean isType(java.lang.String type) {
		try {
			String methodName = "isType";

			Class<?>[] parameterTypes = new Class<?>[] { java.lang.String.class };

			Object[] parameterValues = new Object[] { type };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getIdentifyingKey() {
		try {
			String methodName = "getIdentifyingKey";

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

	public java.lang.String getIdentifyingName() {
		try {
			String methodName = "getIdentifyingName";

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

	public BaseModel<?> getAppPackagePluginRemoteModel() {
		return _appPackagePluginRemoteModel;
	}

	public void setAppPackagePluginRemoteModel(
		BaseModel<?> appPackagePluginRemoteModel) {
		_appPackagePluginRemoteModel = appPackagePluginRemoteModel;
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

		Class<?> remoteModelClass = _appPackagePluginRemoteModel.getClass();

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

		Object returnValue = method.invoke(_appPackagePluginRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AppPackagePluginLocalServiceUtil.addAppPackagePlugin(this);
		}
		else {
			AppPackagePluginLocalServiceUtil.updateAppPackagePlugin(this);
		}
	}

	@Override
	public AppPackagePlugin toEscapedModel() {
		return (AppPackagePlugin)ProxyUtil.newProxyInstance(AppPackagePlugin.class.getClassLoader(),
			new Class[] { AppPackagePlugin.class },
			new AutoEscapeBeanHandler(this));
	}

	public AppPackagePlugin toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AppPackagePluginClp clone = new AppPackagePluginClp();

		clone.setAppPackagePluginId(getAppPackagePluginId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAppEntryId(getAppEntryId());
		clone.setAppVersionId(getAppVersionId());
		clone.setAppPackageId(getAppPackageId());
		clone.setAssetAttachmentId(getAssetAttachmentId());
		clone.setFileName(getFileName());
		clone.setBundleSymbolicName(getBundleSymbolicName());
		clone.setBundleVersion(getBundleVersion());
		clone.setContextName(getContextName());
		clone.setPaclEnabled(getPaclEnabled());
		clone.setRelengHash(getRelengHash());
		clone.setPortalRestartRequired(getPortalRestartRequired());

		return clone;
	}

	public int compareTo(AppPackagePlugin appPackagePlugin) {
		long primaryKey = appPackagePlugin.getPrimaryKey();

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

		if (!(obj instanceof AppPackagePluginClp)) {
			return false;
		}

		AppPackagePluginClp appPackagePlugin = (AppPackagePluginClp)obj;

		long primaryKey = appPackagePlugin.getPrimaryKey();

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
		StringBundler sb = new StringBundler(29);

		sb.append("{appPackagePluginId=");
		sb.append(getAppPackagePluginId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", appEntryId=");
		sb.append(getAppEntryId());
		sb.append(", appVersionId=");
		sb.append(getAppVersionId());
		sb.append(", appPackageId=");
		sb.append(getAppPackageId());
		sb.append(", assetAttachmentId=");
		sb.append(getAssetAttachmentId());
		sb.append(", fileName=");
		sb.append(getFileName());
		sb.append(", bundleSymbolicName=");
		sb.append(getBundleSymbolicName());
		sb.append(", bundleVersion=");
		sb.append(getBundleVersion());
		sb.append(", contextName=");
		sb.append(getContextName());
		sb.append(", paclEnabled=");
		sb.append(getPaclEnabled());
		sb.append(", relengHash=");
		sb.append(getRelengHash());
		sb.append(", portalRestartRequired=");
		sb.append(getPortalRestartRequired());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AppPackagePlugin");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>appPackagePluginId</column-name><column-value><![CDATA[");
		sb.append(getAppPackagePluginId());
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
			"<column><column-name>appPackageId</column-name><column-value><![CDATA[");
		sb.append(getAppPackageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assetAttachmentId</column-name><column-value><![CDATA[");
		sb.append(getAssetAttachmentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileName</column-name><column-value><![CDATA[");
		sb.append(getFileName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>bundleSymbolicName</column-name><column-value><![CDATA[");
		sb.append(getBundleSymbolicName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>bundleVersion</column-name><column-value><![CDATA[");
		sb.append(getBundleVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>contextName</column-name><column-value><![CDATA[");
		sb.append(getContextName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paclEnabled</column-name><column-value><![CDATA[");
		sb.append(getPaclEnabled());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>relengHash</column-name><column-value><![CDATA[");
		sb.append(getRelengHash());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portalRestartRequired</column-name><column-value><![CDATA[");
		sb.append(getPortalRestartRequired());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _appPackagePluginId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _appEntryId;
	private long _appVersionId;
	private long _appPackageId;
	private long _assetAttachmentId;
	private String _fileName;
	private String _bundleSymbolicName;
	private String _bundleVersion;
	private String _contextName;
	private boolean _paclEnabled;
	private String _relengHash;
	private boolean _portalRestartRequired;
	private BaseModel<?> _appPackagePluginRemoteModel;
}