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

import com.liferay.osb.service.AssetListLocalServiceUtil;
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
public class AssetListClp extends BaseModelImpl<AssetList> implements AssetList {
	public AssetListClp() {
	}

	public Class<?> getModelClass() {
		return AssetList.class;
	}

	public String getModelClassName() {
		return AssetList.class.getName();
	}

	public long getPrimaryKey() {
		return _assetListId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAssetListId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_assetListId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetListId", getAssetListId());
		attributes.put("assetCategoryId", getAssetCategoryId());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetListId = (Long)attributes.get("assetListId");

		if (assetListId != null) {
			setAssetListId(assetListId);
		}

		Long assetCategoryId = (Long)attributes.get("assetCategoryId");

		if (assetCategoryId != null) {
			setAssetCategoryId(assetCategoryId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	public long getAssetListId() {
		return _assetListId;
	}

	public void setAssetListId(long assetListId) {
		_assetListId = assetListId;

		if (_assetListRemoteModel != null) {
			try {
				Class<?> clazz = _assetListRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetListId", long.class);

				method.invoke(_assetListRemoteModel, assetListId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAssetCategoryId() {
		return _assetCategoryId;
	}

	public void setAssetCategoryId(long assetCategoryId) {
		_assetCategoryId = assetCategoryId;

		if (_assetListRemoteModel != null) {
			try {
				Class<?> clazz = _assetListRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetCategoryId", long.class);

				method.invoke(_assetListRemoteModel, assetCategoryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;

		if (_assetListRemoteModel != null) {
			try {
				Class<?> clazz = _assetListRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_assetListRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.lang.String getTypeLabel() {
		try {
			String methodName = "getTypeLabel";

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

	public java.util.List<com.liferay.portlet.asset.model.AssetEntry> getAssetEntries(
		boolean visible) {
		try {
			String methodName = "getAssetEntries";

			Class<?>[] parameterTypes = new Class<?>[] { boolean.class };

			Object[] parameterValues = new Object[] { visible };

			java.util.List<com.liferay.portlet.asset.model.AssetEntry> returnObj =
				(java.util.List<com.liferay.portlet.asset.model.AssetEntry>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getAssetListRemoteModel() {
		return _assetListRemoteModel;
	}

	public void setAssetListRemoteModel(BaseModel<?> assetListRemoteModel) {
		_assetListRemoteModel = assetListRemoteModel;
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

		Class<?> remoteModelClass = _assetListRemoteModel.getClass();

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

		Object returnValue = method.invoke(_assetListRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AssetListLocalServiceUtil.addAssetList(this);
		}
		else {
			AssetListLocalServiceUtil.updateAssetList(this);
		}
	}

	@Override
	public AssetList toEscapedModel() {
		return (AssetList)ProxyUtil.newProxyInstance(AssetList.class.getClassLoader(),
			new Class[] { AssetList.class }, new AutoEscapeBeanHandler(this));
	}

	public AssetList toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AssetListClp clone = new AssetListClp();

		clone.setAssetListId(getAssetListId());
		clone.setAssetCategoryId(getAssetCategoryId());
		clone.setType(getType());

		return clone;
	}

	public int compareTo(AssetList assetList) {
		long primaryKey = assetList.getPrimaryKey();

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

		if (!(obj instanceof AssetListClp)) {
			return false;
		}

		AssetListClp assetList = (AssetListClp)obj;

		long primaryKey = assetList.getPrimaryKey();

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

		sb.append("{assetListId=");
		sb.append(getAssetListId());
		sb.append(", assetCategoryId=");
		sb.append(getAssetCategoryId());
		sb.append(", type=");
		sb.append(getType());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AssetList");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>assetListId</column-name><column-value><![CDATA[");
		sb.append(getAssetListId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assetCategoryId</column-name><column-value><![CDATA[");
		sb.append(getAssetCategoryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _assetListId;
	private long _assetCategoryId;
	private int _type;
	private BaseModel<?> _assetListRemoteModel;
}