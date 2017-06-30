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
import com.liferay.osb.service.PortalReleaseLocalServiceUtil;

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
public class PortalReleaseClp extends BaseModelImpl<PortalRelease>
	implements PortalRelease {
	public PortalReleaseClp() {
	}

	public Class<?> getModelClass() {
		return PortalRelease.class;
	}

	public String getModelClassName() {
		return PortalRelease.class.getName();
	}

	public long getPrimaryKey() {
		return _portalReleaseId;
	}

	public void setPrimaryKey(long primaryKey) {
		setPortalReleaseId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_portalReleaseId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("portalReleaseId", getPortalReleaseId());
		attributes.put("versionName", getVersionName());
		attributes.put("buildNumber", getBuildNumber());
		attributes.put("fixPackName", getFixPackName());
		attributes.put("ee", getEe());
		attributes.put("marketplaceSupport", getMarketplaceSupport());
		attributes.put("osgiSupport", getOsgiSupport());
		attributes.put("paclSupport", getPaclSupport());
		attributes.put("hidden", getHidden());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long portalReleaseId = (Long)attributes.get("portalReleaseId");

		if (portalReleaseId != null) {
			setPortalReleaseId(portalReleaseId);
		}

		String versionName = (String)attributes.get("versionName");

		if (versionName != null) {
			setVersionName(versionName);
		}

		Integer buildNumber = (Integer)attributes.get("buildNumber");

		if (buildNumber != null) {
			setBuildNumber(buildNumber);
		}

		String fixPackName = (String)attributes.get("fixPackName");

		if (fixPackName != null) {
			setFixPackName(fixPackName);
		}

		Boolean ee = (Boolean)attributes.get("ee");

		if (ee != null) {
			setEe(ee);
		}

		Boolean marketplaceSupport = (Boolean)attributes.get(
				"marketplaceSupport");

		if (marketplaceSupport != null) {
			setMarketplaceSupport(marketplaceSupport);
		}

		Boolean osgiSupport = (Boolean)attributes.get("osgiSupport");

		if (osgiSupport != null) {
			setOsgiSupport(osgiSupport);
		}

		Boolean paclSupport = (Boolean)attributes.get("paclSupport");

		if (paclSupport != null) {
			setPaclSupport(paclSupport);
		}

		Boolean hidden = (Boolean)attributes.get("hidden");

		if (hidden != null) {
			setHidden(hidden);
		}
	}

	public long getPortalReleaseId() {
		return _portalReleaseId;
	}

	public void setPortalReleaseId(long portalReleaseId) {
		_portalReleaseId = portalReleaseId;

		if (_portalReleaseRemoteModel != null) {
			try {
				Class<?> clazz = _portalReleaseRemoteModel.getClass();

				Method method = clazz.getMethod("setPortalReleaseId", long.class);

				method.invoke(_portalReleaseRemoteModel, portalReleaseId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getVersionName() {
		return _versionName;
	}

	public void setVersionName(String versionName) {
		_versionName = versionName;

		if (_portalReleaseRemoteModel != null) {
			try {
				Class<?> clazz = _portalReleaseRemoteModel.getClass();

				Method method = clazz.getMethod("setVersionName", String.class);

				method.invoke(_portalReleaseRemoteModel, versionName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getBuildNumber() {
		return _buildNumber;
	}

	public void setBuildNumber(int buildNumber) {
		_buildNumber = buildNumber;

		if (_portalReleaseRemoteModel != null) {
			try {
				Class<?> clazz = _portalReleaseRemoteModel.getClass();

				Method method = clazz.getMethod("setBuildNumber", int.class);

				method.invoke(_portalReleaseRemoteModel, buildNumber);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getFixPackName() {
		return _fixPackName;
	}

	public void setFixPackName(String fixPackName) {
		_fixPackName = fixPackName;

		if (_portalReleaseRemoteModel != null) {
			try {
				Class<?> clazz = _portalReleaseRemoteModel.getClass();

				Method method = clazz.getMethod("setFixPackName", String.class);

				method.invoke(_portalReleaseRemoteModel, fixPackName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getEe() {
		return _ee;
	}

	public boolean isEe() {
		return _ee;
	}

	public void setEe(boolean ee) {
		_ee = ee;

		if (_portalReleaseRemoteModel != null) {
			try {
				Class<?> clazz = _portalReleaseRemoteModel.getClass();

				Method method = clazz.getMethod("setEe", boolean.class);

				method.invoke(_portalReleaseRemoteModel, ee);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getMarketplaceSupport() {
		return _marketplaceSupport;
	}

	public boolean isMarketplaceSupport() {
		return _marketplaceSupport;
	}

	public void setMarketplaceSupport(boolean marketplaceSupport) {
		_marketplaceSupport = marketplaceSupport;

		if (_portalReleaseRemoteModel != null) {
			try {
				Class<?> clazz = _portalReleaseRemoteModel.getClass();

				Method method = clazz.getMethod("setMarketplaceSupport",
						boolean.class);

				method.invoke(_portalReleaseRemoteModel, marketplaceSupport);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getOsgiSupport() {
		return _osgiSupport;
	}

	public boolean isOsgiSupport() {
		return _osgiSupport;
	}

	public void setOsgiSupport(boolean osgiSupport) {
		_osgiSupport = osgiSupport;

		if (_portalReleaseRemoteModel != null) {
			try {
				Class<?> clazz = _portalReleaseRemoteModel.getClass();

				Method method = clazz.getMethod("setOsgiSupport", boolean.class);

				method.invoke(_portalReleaseRemoteModel, osgiSupport);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getPaclSupport() {
		return _paclSupport;
	}

	public boolean isPaclSupport() {
		return _paclSupport;
	}

	public void setPaclSupport(boolean paclSupport) {
		_paclSupport = paclSupport;

		if (_portalReleaseRemoteModel != null) {
			try {
				Class<?> clazz = _portalReleaseRemoteModel.getClass();

				Method method = clazz.getMethod("setPaclSupport", boolean.class);

				method.invoke(_portalReleaseRemoteModel, paclSupport);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getHidden() {
		return _hidden;
	}

	public boolean isHidden() {
		return _hidden;
	}

	public void setHidden(boolean hidden) {
		_hidden = hidden;

		if (_portalReleaseRemoteModel != null) {
			try {
				Class<?> clazz = _portalReleaseRemoteModel.getClass();

				Method method = clazz.getMethod("setHidden", boolean.class);

				method.invoke(_portalReleaseRemoteModel, hidden);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean hasPaclSupport() {
		try {
			String methodName = "hasPaclSupport";

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

	public boolean isEE() {
		try {
			String methodName = "isEE";

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

	public boolean hasOsgiSupport() {
		try {
			String methodName = "hasOsgiSupport";

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

	public boolean hasMarketplaceSupport() {
		try {
			String methodName = "hasMarketplaceSupport";

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

	public BaseModel<?> getPortalReleaseRemoteModel() {
		return _portalReleaseRemoteModel;
	}

	public void setPortalReleaseRemoteModel(
		BaseModel<?> portalReleaseRemoteModel) {
		_portalReleaseRemoteModel = portalReleaseRemoteModel;
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

		Class<?> remoteModelClass = _portalReleaseRemoteModel.getClass();

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

		Object returnValue = method.invoke(_portalReleaseRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			PortalReleaseLocalServiceUtil.addPortalRelease(this);
		}
		else {
			PortalReleaseLocalServiceUtil.updatePortalRelease(this);
		}
	}

	@Override
	public PortalRelease toEscapedModel() {
		return (PortalRelease)ProxyUtil.newProxyInstance(PortalRelease.class.getClassLoader(),
			new Class[] { PortalRelease.class }, new AutoEscapeBeanHandler(this));
	}

	public PortalRelease toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		PortalReleaseClp clone = new PortalReleaseClp();

		clone.setPortalReleaseId(getPortalReleaseId());
		clone.setVersionName(getVersionName());
		clone.setBuildNumber(getBuildNumber());
		clone.setFixPackName(getFixPackName());
		clone.setEe(getEe());
		clone.setMarketplaceSupport(getMarketplaceSupport());
		clone.setOsgiSupport(getOsgiSupport());
		clone.setPaclSupport(getPaclSupport());
		clone.setHidden(getHidden());

		return clone;
	}

	public int compareTo(PortalRelease portalRelease) {
		int value = 0;

		if (getBuildNumber() < portalRelease.getBuildNumber()) {
			value = -1;
		}
		else if (getBuildNumber() > portalRelease.getBuildNumber()) {
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

		if (!(obj instanceof PortalReleaseClp)) {
			return false;
		}

		PortalReleaseClp portalRelease = (PortalReleaseClp)obj;

		long primaryKey = portalRelease.getPrimaryKey();

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

		sb.append("{portalReleaseId=");
		sb.append(getPortalReleaseId());
		sb.append(", versionName=");
		sb.append(getVersionName());
		sb.append(", buildNumber=");
		sb.append(getBuildNumber());
		sb.append(", fixPackName=");
		sb.append(getFixPackName());
		sb.append(", ee=");
		sb.append(getEe());
		sb.append(", marketplaceSupport=");
		sb.append(getMarketplaceSupport());
		sb.append(", osgiSupport=");
		sb.append(getOsgiSupport());
		sb.append(", paclSupport=");
		sb.append(getPaclSupport());
		sb.append(", hidden=");
		sb.append(getHidden());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.PortalRelease");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>portalReleaseId</column-name><column-value><![CDATA[");
		sb.append(getPortalReleaseId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>versionName</column-name><column-value><![CDATA[");
		sb.append(getVersionName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>buildNumber</column-name><column-value><![CDATA[");
		sb.append(getBuildNumber());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fixPackName</column-name><column-value><![CDATA[");
		sb.append(getFixPackName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ee</column-name><column-value><![CDATA[");
		sb.append(getEe());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>marketplaceSupport</column-name><column-value><![CDATA[");
		sb.append(getMarketplaceSupport());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>osgiSupport</column-name><column-value><![CDATA[");
		sb.append(getOsgiSupport());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paclSupport</column-name><column-value><![CDATA[");
		sb.append(getPaclSupport());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hidden</column-name><column-value><![CDATA[");
		sb.append(getHidden());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _portalReleaseId;
	private String _versionName;
	private int _buildNumber;
	private String _fixPackName;
	private boolean _ee;
	private boolean _marketplaceSupport;
	private boolean _osgiSupport;
	private boolean _paclSupport;
	private boolean _hidden;
	private BaseModel<?> _portalReleaseRemoteModel;
}