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

import com.liferay.osb.service.AssetRecommendationSetLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetRecommendationSetClp extends BaseModelImpl<AssetRecommendationSet>
	implements AssetRecommendationSet {
	public AssetRecommendationSetClp() {
	}

	public Class<?> getModelClass() {
		return AssetRecommendationSet.class;
	}

	public String getModelClassName() {
		return AssetRecommendationSet.class.getName();
	}

	public long getPrimaryKey() {
		return _assetRecommendationSetId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAssetRecommendationSetId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_assetRecommendationSetId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetRecommendationSetId", getAssetRecommendationSetId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetRecommendationSetId = (Long)attributes.get(
				"assetRecommendationSetId");

		if (assetRecommendationSetId != null) {
			setAssetRecommendationSetId(assetRecommendationSetId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}
	}

	public long getAssetRecommendationSetId() {
		return _assetRecommendationSetId;
	}

	public void setAssetRecommendationSetId(long assetRecommendationSetId) {
		_assetRecommendationSetId = assetRecommendationSetId;

		if (_assetRecommendationSetRemoteModel != null) {
			try {
				Class<?> clazz = _assetRecommendationSetRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetRecommendationSetId",
						long.class);

				method.invoke(_assetRecommendationSetRemoteModel,
					assetRecommendationSetId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;

		if (_assetRecommendationSetRemoteModel != null) {
			try {
				Class<?> clazz = _assetRecommendationSetRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_assetRecommendationSetRemoteModel, classNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;

		if (_assetRecommendationSetRemoteModel != null) {
			try {
				Class<?> clazz = _assetRecommendationSetRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_assetRecommendationSetRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAssetRecommendationSetRemoteModel() {
		return _assetRecommendationSetRemoteModel;
	}

	public void setAssetRecommendationSetRemoteModel(
		BaseModel<?> assetRecommendationSetRemoteModel) {
		_assetRecommendationSetRemoteModel = assetRecommendationSetRemoteModel;
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

		Class<?> remoteModelClass = _assetRecommendationSetRemoteModel.getClass();

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

		Object returnValue = method.invoke(_assetRecommendationSetRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AssetRecommendationSetLocalServiceUtil.addAssetRecommendationSet(this);
		}
		else {
			AssetRecommendationSetLocalServiceUtil.updateAssetRecommendationSet(this);
		}
	}

	@Override
	public AssetRecommendationSet toEscapedModel() {
		return (AssetRecommendationSet)ProxyUtil.newProxyInstance(AssetRecommendationSet.class.getClassLoader(),
			new Class[] { AssetRecommendationSet.class },
			new AutoEscapeBeanHandler(this));
	}

	public AssetRecommendationSet toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AssetRecommendationSetClp clone = new AssetRecommendationSetClp();

		clone.setAssetRecommendationSetId(getAssetRecommendationSetId());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());

		return clone;
	}

	public int compareTo(AssetRecommendationSet assetRecommendationSet) {
		long primaryKey = assetRecommendationSet.getPrimaryKey();

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

		if (!(obj instanceof AssetRecommendationSetClp)) {
			return false;
		}

		AssetRecommendationSetClp assetRecommendationSet = (AssetRecommendationSetClp)obj;

		long primaryKey = assetRecommendationSet.getPrimaryKey();

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

		sb.append("{assetRecommendationSetId=");
		sb.append(getAssetRecommendationSetId());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AssetRecommendationSet");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>assetRecommendationSetId</column-name><column-value><![CDATA[");
		sb.append(getAssetRecommendationSetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _assetRecommendationSetId;
	private long _classNameId;
	private long _classPK;
	private BaseModel<?> _assetRecommendationSetRemoteModel;
}