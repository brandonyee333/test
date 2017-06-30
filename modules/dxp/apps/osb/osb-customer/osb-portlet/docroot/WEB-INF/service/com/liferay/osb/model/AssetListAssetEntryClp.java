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

import com.liferay.osb.service.AssetListAssetEntryLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

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
public class AssetListAssetEntryClp extends BaseModelImpl<AssetListAssetEntry>
	implements AssetListAssetEntry {
	public AssetListAssetEntryClp() {
	}

	public Class<?> getModelClass() {
		return AssetListAssetEntry.class;
	}

	public String getModelClassName() {
		return AssetListAssetEntry.class.getName();
	}

	public long getPrimaryKey() {
		return _assetListAssetEntryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAssetListAssetEntryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_assetListAssetEntryId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetListAssetEntryId", getAssetListAssetEntryId());
		attributes.put("assetListId", getAssetListId());
		attributes.put("assetEntryId", getAssetEntryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetListAssetEntryId = (Long)attributes.get(
				"assetListAssetEntryId");

		if (assetListAssetEntryId != null) {
			setAssetListAssetEntryId(assetListAssetEntryId);
		}

		Long assetListId = (Long)attributes.get("assetListId");

		if (assetListId != null) {
			setAssetListId(assetListId);
		}

		Long assetEntryId = (Long)attributes.get("assetEntryId");

		if (assetEntryId != null) {
			setAssetEntryId(assetEntryId);
		}
	}

	public long getAssetListAssetEntryId() {
		return _assetListAssetEntryId;
	}

	public void setAssetListAssetEntryId(long assetListAssetEntryId) {
		_assetListAssetEntryId = assetListAssetEntryId;

		if (_assetListAssetEntryRemoteModel != null) {
			try {
				Class<?> clazz = _assetListAssetEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetListAssetEntryId",
						long.class);

				method.invoke(_assetListAssetEntryRemoteModel,
					assetListAssetEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAssetListId() {
		return _assetListId;
	}

	public void setAssetListId(long assetListId) {
		_assetListId = assetListId;

		if (_assetListAssetEntryRemoteModel != null) {
			try {
				Class<?> clazz = _assetListAssetEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetListId", long.class);

				method.invoke(_assetListAssetEntryRemoteModel, assetListId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAssetEntryId() {
		return _assetEntryId;
	}

	public void setAssetEntryId(long assetEntryId) {
		_assetEntryId = assetEntryId;

		if (_assetListAssetEntryRemoteModel != null) {
			try {
				Class<?> clazz = _assetListAssetEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetEntryId", long.class);

				method.invoke(_assetListAssetEntryRemoteModel, assetEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public com.liferay.portlet.asset.model.AssetEntry getAssetEntry() {
		try {
			String methodName = "getAssetEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portlet.asset.model.AssetEntry returnObj = (com.liferay.portlet.asset.model.AssetEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getAssetListAssetEntryRemoteModel() {
		return _assetListAssetEntryRemoteModel;
	}

	public void setAssetListAssetEntryRemoteModel(
		BaseModel<?> assetListAssetEntryRemoteModel) {
		_assetListAssetEntryRemoteModel = assetListAssetEntryRemoteModel;
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

		Class<?> remoteModelClass = _assetListAssetEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_assetListAssetEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AssetListAssetEntryLocalServiceUtil.addAssetListAssetEntry(this);
		}
		else {
			AssetListAssetEntryLocalServiceUtil.updateAssetListAssetEntry(this);
		}
	}

	@Override
	public AssetListAssetEntry toEscapedModel() {
		return (AssetListAssetEntry)ProxyUtil.newProxyInstance(AssetListAssetEntry.class.getClassLoader(),
			new Class[] { AssetListAssetEntry.class },
			new AutoEscapeBeanHandler(this));
	}

	public AssetListAssetEntry toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AssetListAssetEntryClp clone = new AssetListAssetEntryClp();

		clone.setAssetListAssetEntryId(getAssetListAssetEntryId());
		clone.setAssetListId(getAssetListId());
		clone.setAssetEntryId(getAssetEntryId());

		return clone;
	}

	public int compareTo(AssetListAssetEntry assetListAssetEntry) {
		long primaryKey = assetListAssetEntry.getPrimaryKey();

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

		if (!(obj instanceof AssetListAssetEntryClp)) {
			return false;
		}

		AssetListAssetEntryClp assetListAssetEntry = (AssetListAssetEntryClp)obj;

		long primaryKey = assetListAssetEntry.getPrimaryKey();

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

		sb.append("{assetListAssetEntryId=");
		sb.append(getAssetListAssetEntryId());
		sb.append(", assetListId=");
		sb.append(getAssetListId());
		sb.append(", assetEntryId=");
		sb.append(getAssetEntryId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AssetListAssetEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>assetListAssetEntryId</column-name><column-value><![CDATA[");
		sb.append(getAssetListAssetEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assetListId</column-name><column-value><![CDATA[");
		sb.append(getAssetListId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assetEntryId</column-name><column-value><![CDATA[");
		sb.append(getAssetEntryId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _assetListAssetEntryId;
	private long _assetListId;
	private long _assetEntryId;
	private BaseModel<?> _assetListAssetEntryRemoteModel;
}