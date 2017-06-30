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

import com.liferay.osb.service.AssetReceiptRedeemTokenLocalServiceUtil;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetReceiptRedeemTokenClp extends BaseModelImpl<AssetReceiptRedeemToken>
	implements AssetReceiptRedeemToken {
	public AssetReceiptRedeemTokenClp() {
	}

	public Class<?> getModelClass() {
		return AssetReceiptRedeemToken.class;
	}

	public String getModelClassName() {
		return AssetReceiptRedeemToken.class.getName();
	}

	public long getPrimaryKey() {
		return _AssetReceiptRedeemTokenId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAssetReceiptRedeemTokenId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_AssetReceiptRedeemTokenId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("AssetReceiptRedeemTokenId",
			getAssetReceiptRedeemTokenId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("redeemEmailAddress", getRedeemEmailAddress());
		attributes.put("redeemDate", getRedeemDate());
		attributes.put("token", getToken());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long AssetReceiptRedeemTokenId = (Long)attributes.get(
				"AssetReceiptRedeemTokenId");

		if (AssetReceiptRedeemTokenId != null) {
			setAssetReceiptRedeemTokenId(AssetReceiptRedeemTokenId);
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

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String redeemEmailAddress = (String)attributes.get("redeemEmailAddress");

		if (redeemEmailAddress != null) {
			setRedeemEmailAddress(redeemEmailAddress);
		}

		Date redeemDate = (Date)attributes.get("redeemDate");

		if (redeemDate != null) {
			setRedeemDate(redeemDate);
		}

		String token = (String)attributes.get("token");

		if (token != null) {
			setToken(token);
		}
	}

	public long getAssetReceiptRedeemTokenId() {
		return _AssetReceiptRedeemTokenId;
	}

	public void setAssetReceiptRedeemTokenId(long AssetReceiptRedeemTokenId) {
		_AssetReceiptRedeemTokenId = AssetReceiptRedeemTokenId;

		if (_assetReceiptRedeemTokenRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRedeemTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetReceiptRedeemTokenId",
						long.class);

				method.invoke(_assetReceiptRedeemTokenRemoteModel,
					AssetReceiptRedeemTokenId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;

		if (_assetReceiptRedeemTokenRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRedeemTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_assetReceiptRedeemTokenRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;

		if (_assetReceiptRedeemTokenRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRedeemTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_assetReceiptRedeemTokenRemoteModel, userName);
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

		if (_assetReceiptRedeemTokenRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRedeemTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_assetReceiptRedeemTokenRemoteModel, createDate);
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

		if (_assetReceiptRedeemTokenRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRedeemTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_assetReceiptRedeemTokenRemoteModel, classNameId);
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

		if (_assetReceiptRedeemTokenRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRedeemTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_assetReceiptRedeemTokenRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getRedeemEmailAddress() {
		return _redeemEmailAddress;
	}

	public void setRedeemEmailAddress(String redeemEmailAddress) {
		_redeemEmailAddress = redeemEmailAddress;

		if (_assetReceiptRedeemTokenRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRedeemTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setRedeemEmailAddress",
						String.class);

				method.invoke(_assetReceiptRedeemTokenRemoteModel,
					redeemEmailAddress);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getRedeemDate() {
		return _redeemDate;
	}

	public void setRedeemDate(Date redeemDate) {
		_redeemDate = redeemDate;

		if (_assetReceiptRedeemTokenRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRedeemTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setRedeemDate", Date.class);

				method.invoke(_assetReceiptRedeemTokenRemoteModel, redeemDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getToken() {
		return _token;
	}

	public void setToken(String token) {
		_token = token;

		if (_assetReceiptRedeemTokenRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRedeemTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setToken", String.class);

				method.invoke(_assetReceiptRedeemTokenRemoteModel, token);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAssetReceiptRedeemTokenRemoteModel() {
		return _assetReceiptRedeemTokenRemoteModel;
	}

	public void setAssetReceiptRedeemTokenRemoteModel(
		BaseModel<?> assetReceiptRedeemTokenRemoteModel) {
		_assetReceiptRedeemTokenRemoteModel = assetReceiptRedeemTokenRemoteModel;
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

		Class<?> remoteModelClass = _assetReceiptRedeemTokenRemoteModel.getClass();

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

		Object returnValue = method.invoke(_assetReceiptRedeemTokenRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AssetReceiptRedeemTokenLocalServiceUtil.addAssetReceiptRedeemToken(this);
		}
		else {
			AssetReceiptRedeemTokenLocalServiceUtil.updateAssetReceiptRedeemToken(this);
		}
	}

	@Override
	public AssetReceiptRedeemToken toEscapedModel() {
		return (AssetReceiptRedeemToken)ProxyUtil.newProxyInstance(AssetReceiptRedeemToken.class.getClassLoader(),
			new Class[] { AssetReceiptRedeemToken.class },
			new AutoEscapeBeanHandler(this));
	}

	public AssetReceiptRedeemToken toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AssetReceiptRedeemTokenClp clone = new AssetReceiptRedeemTokenClp();

		clone.setAssetReceiptRedeemTokenId(getAssetReceiptRedeemTokenId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setRedeemEmailAddress(getRedeemEmailAddress());
		clone.setRedeemDate(getRedeemDate());
		clone.setToken(getToken());

		return clone;
	}

	public int compareTo(AssetReceiptRedeemToken assetReceiptRedeemToken) {
		long primaryKey = assetReceiptRedeemToken.getPrimaryKey();

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

		if (!(obj instanceof AssetReceiptRedeemTokenClp)) {
			return false;
		}

		AssetReceiptRedeemTokenClp assetReceiptRedeemToken = (AssetReceiptRedeemTokenClp)obj;

		long primaryKey = assetReceiptRedeemToken.getPrimaryKey();

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

		sb.append("{AssetReceiptRedeemTokenId=");
		sb.append(getAssetReceiptRedeemTokenId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", redeemEmailAddress=");
		sb.append(getRedeemEmailAddress());
		sb.append(", redeemDate=");
		sb.append(getRedeemDate());
		sb.append(", token=");
		sb.append(getToken());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AssetReceiptRedeemToken");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>AssetReceiptRedeemTokenId</column-name><column-value><![CDATA[");
		sb.append(getAssetReceiptRedeemTokenId());
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
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>redeemEmailAddress</column-name><column-value><![CDATA[");
		sb.append(getRedeemEmailAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>redeemDate</column-name><column-value><![CDATA[");
		sb.append(getRedeemDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>token</column-name><column-value><![CDATA[");
		sb.append(getToken());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _AssetReceiptRedeemTokenId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private long _classNameId;
	private long _classPK;
	private String _redeemEmailAddress;
	private Date _redeemDate;
	private String _token;
	private BaseModel<?> _assetReceiptRedeemTokenRemoteModel;
}