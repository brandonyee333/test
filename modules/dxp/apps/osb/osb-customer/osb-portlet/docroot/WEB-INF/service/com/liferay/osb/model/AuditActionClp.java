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

import com.liferay.osb.service.AuditActionLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class AuditActionClp extends BaseModelImpl<AuditAction>
	implements AuditAction {
	public AuditActionClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return AuditAction.class;
	}

	@Override
	public String getModelClassName() {
		return AuditAction.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _auditActionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAuditActionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _auditActionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("auditActionId", getAuditActionId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("mappingClassPK", getMappingClassPK());
		attributes.put("action", getAction());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long auditActionId = (Long)attributes.get("auditActionId");

		if (auditActionId != null) {
			setAuditActionId(auditActionId);
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

		Long mappingClassPK = (Long)attributes.get("mappingClassPK");

		if (mappingClassPK != null) {
			setMappingClassPK(mappingClassPK);
		}

		Integer action = (Integer)attributes.get("action");

		if (action != null) {
			setAction(action);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getAuditActionId() {
		return _auditActionId;
	}

	@Override
	public void setAuditActionId(long auditActionId) {
		_auditActionId = auditActionId;

		if (_auditActionRemoteModel != null) {
			try {
				Class<?> clazz = _auditActionRemoteModel.getClass();

				Method method = clazz.getMethod("setAuditActionId", long.class);

				method.invoke(_auditActionRemoteModel, auditActionId);
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

		if (_auditActionRemoteModel != null) {
			try {
				Class<?> clazz = _auditActionRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_auditActionRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;

		if (_auditActionRemoteModel != null) {
			try {
				Class<?> clazz = _auditActionRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_auditActionRemoteModel, classNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_classPK = classPK;

		if (_auditActionRemoteModel != null) {
			try {
				Class<?> clazz = _auditActionRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_auditActionRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getMappingClassPK() {
		return _mappingClassPK;
	}

	@Override
	public void setMappingClassPK(long mappingClassPK) {
		_mappingClassPK = mappingClassPK;

		if (_auditActionRemoteModel != null) {
			try {
				Class<?> clazz = _auditActionRemoteModel.getClass();

				Method method = clazz.getMethod("setMappingClassPK", long.class);

				method.invoke(_auditActionRemoteModel, mappingClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getAction() {
		return _action;
	}

	@Override
	public void setAction(int action) {
		_action = action;

		if (_auditActionRemoteModel != null) {
			try {
				Class<?> clazz = _auditActionRemoteModel.getClass();

				Method method = clazz.getMethod("setAction", int.class);

				method.invoke(_auditActionRemoteModel, action);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAuditActionRemoteModel() {
		return _auditActionRemoteModel;
	}

	public void setAuditActionRemoteModel(BaseModel<?> auditActionRemoteModel) {
		_auditActionRemoteModel = auditActionRemoteModel;
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

		Class<?> remoteModelClass = _auditActionRemoteModel.getClass();

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

		Object returnValue = method.invoke(_auditActionRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			AuditActionLocalServiceUtil.addAuditAction(this);
		}
		else {
			AuditActionLocalServiceUtil.updateAuditAction(this);
		}
	}

	@Override
	public AuditAction toEscapedModel() {
		return (AuditAction)ProxyUtil.newProxyInstance(AuditAction.class.getClassLoader(),
			new Class[] { AuditAction.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AuditActionClp clone = new AuditActionClp();

		clone.setAuditActionId(getAuditActionId());
		clone.setModifiedDate(getModifiedDate());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setMappingClassPK(getMappingClassPK());
		clone.setAction(getAction());

		return clone;
	}

	@Override
	public int compareTo(AuditAction auditAction) {
		long primaryKey = auditAction.getPrimaryKey();

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

		if (!(obj instanceof AuditActionClp)) {
			return false;
		}

		AuditActionClp auditAction = (AuditActionClp)obj;

		long primaryKey = auditAction.getPrimaryKey();

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
		StringBundler sb = new StringBundler(13);

		sb.append("{auditActionId=");
		sb.append(getAuditActionId());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", mappingClassPK=");
		sb.append(getMappingClassPK());
		sb.append(", action=");
		sb.append(getAction());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AuditAction");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>auditActionId</column-name><column-value><![CDATA[");
		sb.append(getAuditActionId());
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
			"<column><column-name>mappingClassPK</column-name><column-value><![CDATA[");
		sb.append(getMappingClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>action</column-name><column-value><![CDATA[");
		sb.append(getAction());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _auditActionId;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private long _mappingClassPK;
	private int _action;
	private BaseModel<?> _auditActionRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}