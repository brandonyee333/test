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

import com.liferay.osb.service.AssetAttachmentLocalServiceUtil;
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
public class AssetAttachmentClp extends BaseModelImpl<AssetAttachment>
	implements AssetAttachment {
	public AssetAttachmentClp() {
	}

	public Class<?> getModelClass() {
		return AssetAttachment.class;
	}

	public String getModelClassName() {
		return AssetAttachment.class.getName();
	}

	public long getPrimaryKey() {
		return _assetAttachmentId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAssetAttachmentId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_assetAttachmentId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetAttachmentId", getAssetAttachmentId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("fileName", getFileName());
		attributes.put("type", getType());
		attributes.put("rank", getRank());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetAttachmentId = (Long)attributes.get("assetAttachmentId");

		if (assetAttachmentId != null) {
			setAssetAttachmentId(assetAttachmentId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
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

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer rank = (Integer)attributes.get("rank");

		if (rank != null) {
			setRank(rank);
		}
	}

	public long getAssetAttachmentId() {
		return _assetAttachmentId;
	}

	public void setAssetAttachmentId(long assetAttachmentId) {
		_assetAttachmentId = assetAttachmentId;

		if (_assetAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _assetAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetAttachmentId",
						long.class);

				method.invoke(_assetAttachmentRemoteModel, assetAttachmentId);
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

		if (_assetAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _assetAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_assetAttachmentRemoteModel, userId);
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

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_assetAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _assetAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_assetAttachmentRemoteModel, createDate);
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

		if (_assetAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _assetAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_assetAttachmentRemoteModel, classNameId);
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

		if (_assetAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _assetAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_assetAttachmentRemoteModel, classPK);
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

		if (_assetAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _assetAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setFileName", String.class);

				method.invoke(_assetAttachmentRemoteModel, fileName);
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

		if (_assetAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _assetAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_assetAttachmentRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getRank() {
		return _rank;
	}

	public void setRank(int rank) {
		_rank = rank;

		if (_assetAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _assetAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setRank", int.class);

				method.invoke(_assetAttachmentRemoteModel, rank);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.io.InputStream getFileAsStream() {
		try {
			String methodName = "getFileAsStream";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.io.InputStream returnObj = (java.io.InputStream)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getPath() {
		try {
			String methodName = "getPath";

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

	public java.lang.String getDir() {
		try {
			String methodName = "getDir";

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

	public BaseModel<?> getAssetAttachmentRemoteModel() {
		return _assetAttachmentRemoteModel;
	}

	public void setAssetAttachmentRemoteModel(
		BaseModel<?> assetAttachmentRemoteModel) {
		_assetAttachmentRemoteModel = assetAttachmentRemoteModel;
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

		Class<?> remoteModelClass = _assetAttachmentRemoteModel.getClass();

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

		Object returnValue = method.invoke(_assetAttachmentRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AssetAttachmentLocalServiceUtil.addAssetAttachment(this);
		}
		else {
			AssetAttachmentLocalServiceUtil.updateAssetAttachment(this);
		}
	}

	@Override
	public AssetAttachment toEscapedModel() {
		return (AssetAttachment)ProxyUtil.newProxyInstance(AssetAttachment.class.getClassLoader(),
			new Class[] { AssetAttachment.class },
			new AutoEscapeBeanHandler(this));
	}

	public AssetAttachment toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AssetAttachmentClp clone = new AssetAttachmentClp();

		clone.setAssetAttachmentId(getAssetAttachmentId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setFileName(getFileName());
		clone.setType(getType());
		clone.setRank(getRank());

		return clone;
	}

	public int compareTo(AssetAttachment assetAttachment) {
		int value = 0;

		if (getRank() < assetAttachment.getRank()) {
			value = -1;
		}
		else if (getRank() > assetAttachment.getRank()) {
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

		if (!(obj instanceof AssetAttachmentClp)) {
			return false;
		}

		AssetAttachmentClp assetAttachment = (AssetAttachmentClp)obj;

		long primaryKey = assetAttachment.getPrimaryKey();

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
		StringBundler sb = new StringBundler(17);

		sb.append("{assetAttachmentId=");
		sb.append(getAssetAttachmentId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", fileName=");
		sb.append(getFileName());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", rank=");
		sb.append(getRank());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AssetAttachment");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>assetAttachmentId</column-name><column-value><![CDATA[");
		sb.append(getAssetAttachmentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
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
			"<column><column-name>fileName</column-name><column-value><![CDATA[");
		sb.append(getFileName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>rank</column-name><column-value><![CDATA[");
		sb.append(getRank());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _assetAttachmentId;
	private long _userId;
	private String _userUuid;
	private Date _createDate;
	private long _classNameId;
	private long _classPK;
	private String _fileName;
	private int _type;
	private int _rank;
	private BaseModel<?> _assetAttachmentRemoteModel;
}