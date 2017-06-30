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
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;

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
public class ExternalIdMapperClp extends BaseModelImpl<ExternalIdMapper>
	implements ExternalIdMapper {
	public ExternalIdMapperClp() {
	}

	public Class<?> getModelClass() {
		return ExternalIdMapper.class;
	}

	public String getModelClassName() {
		return ExternalIdMapper.class.getName();
	}

	public long getPrimaryKey() {
		return _externalIdMapperId;
	}

	public void setPrimaryKey(long primaryKey) {
		setExternalIdMapperId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_externalIdMapperId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("externalIdMapperId", getExternalIdMapperId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("type", getType());
		attributes.put("externalId", getExternalId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long externalIdMapperId = (Long)attributes.get("externalIdMapperId");

		if (externalIdMapperId != null) {
			setExternalIdMapperId(externalIdMapperId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String externalId = (String)attributes.get("externalId");

		if (externalId != null) {
			setExternalId(externalId);
		}
	}

	public long getExternalIdMapperId() {
		return _externalIdMapperId;
	}

	public void setExternalIdMapperId(long externalIdMapperId) {
		_externalIdMapperId = externalIdMapperId;

		if (_externalIdMapperRemoteModel != null) {
			try {
				Class<?> clazz = _externalIdMapperRemoteModel.getClass();

				Method method = clazz.getMethod("setExternalIdMapperId",
						long.class);

				method.invoke(_externalIdMapperRemoteModel, externalIdMapperId);
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

		if (_externalIdMapperRemoteModel != null) {
			try {
				Class<?> clazz = _externalIdMapperRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_externalIdMapperRemoteModel, createDate);
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

		if (_externalIdMapperRemoteModel != null) {
			try {
				Class<?> clazz = _externalIdMapperRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_externalIdMapperRemoteModel, modifiedDate);
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

		if (_externalIdMapperRemoteModel != null) {
			try {
				Class<?> clazz = _externalIdMapperRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_externalIdMapperRemoteModel, classNameId);
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

		if (_externalIdMapperRemoteModel != null) {
			try {
				Class<?> clazz = _externalIdMapperRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_externalIdMapperRemoteModel, classPK);
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

		if (_externalIdMapperRemoteModel != null) {
			try {
				Class<?> clazz = _externalIdMapperRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_externalIdMapperRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getExternalId() {
		return _externalId;
	}

	public void setExternalId(String externalId) {
		_externalId = externalId;

		if (_externalIdMapperRemoteModel != null) {
			try {
				Class<?> clazz = _externalIdMapperRemoteModel.getClass();

				Method method = clazz.getMethod("setExternalId", String.class);

				method.invoke(_externalIdMapperRemoteModel, externalId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getExternalIdMapperRemoteModel() {
		return _externalIdMapperRemoteModel;
	}

	public void setExternalIdMapperRemoteModel(
		BaseModel<?> externalIdMapperRemoteModel) {
		_externalIdMapperRemoteModel = externalIdMapperRemoteModel;
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

		Class<?> remoteModelClass = _externalIdMapperRemoteModel.getClass();

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

		Object returnValue = method.invoke(_externalIdMapperRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			ExternalIdMapperLocalServiceUtil.addExternalIdMapper(this);
		}
		else {
			ExternalIdMapperLocalServiceUtil.updateExternalIdMapper(this);
		}
	}

	@Override
	public ExternalIdMapper toEscapedModel() {
		return (ExternalIdMapper)ProxyUtil.newProxyInstance(ExternalIdMapper.class.getClassLoader(),
			new Class[] { ExternalIdMapper.class },
			new AutoEscapeBeanHandler(this));
	}

	public ExternalIdMapper toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		ExternalIdMapperClp clone = new ExternalIdMapperClp();

		clone.setExternalIdMapperId(getExternalIdMapperId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setType(getType());
		clone.setExternalId(getExternalId());

		return clone;
	}

	public int compareTo(ExternalIdMapper externalIdMapper) {
		long primaryKey = externalIdMapper.getPrimaryKey();

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

		if (!(obj instanceof ExternalIdMapperClp)) {
			return false;
		}

		ExternalIdMapperClp externalIdMapper = (ExternalIdMapperClp)obj;

		long primaryKey = externalIdMapper.getPrimaryKey();

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
		StringBundler sb = new StringBundler(15);

		sb.append("{externalIdMapperId=");
		sb.append(getExternalIdMapperId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", externalId=");
		sb.append(getExternalId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.ExternalIdMapper");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>externalIdMapperId</column-name><column-value><![CDATA[");
		sb.append(getExternalIdMapperId());
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
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>externalId</column-name><column-value><![CDATA[");
		sb.append(getExternalId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _externalIdMapperId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private int _type;
	private String _externalId;
	private BaseModel<?> _externalIdMapperRemoteModel;
}