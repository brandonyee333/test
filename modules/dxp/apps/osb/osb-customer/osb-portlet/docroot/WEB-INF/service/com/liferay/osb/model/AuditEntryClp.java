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

import com.liferay.osb.service.AuditEntryLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
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
public class AuditEntryClp extends BaseModelImpl<AuditEntry>
	implements AuditEntry {
	public AuditEntryClp() {
	}

	public Class<?> getModelClass() {
		return AuditEntry.class;
	}

	public String getModelClassName() {
		return AuditEntry.class.getName();
	}

	public long getPrimaryKey() {
		return _auditEntryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAuditEntryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_auditEntryId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("auditEntryId", getAuditEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("previousAuditEntryId", getPreviousAuditEntryId());
		attributes.put("auditSetId", getAuditSetId());
		attributes.put("fieldClassNameId", getFieldClassNameId());
		attributes.put("fieldClassPK", getFieldClassPK());
		attributes.put("action", getAction());
		attributes.put("field", getField());
		attributes.put("visibility", getVisibility());
		attributes.put("oldLabel", getOldLabel());
		attributes.put("oldValue", getOldValue());
		attributes.put("newLabel", getNewLabel());
		attributes.put("newValue", getNewValue());
		attributes.put("i18n", getI18n());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long auditEntryId = (Long)attributes.get("auditEntryId");

		if (auditEntryId != null) {
			setAuditEntryId(auditEntryId);
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

		Long previousAuditEntryId = (Long)attributes.get("previousAuditEntryId");

		if (previousAuditEntryId != null) {
			setPreviousAuditEntryId(previousAuditEntryId);
		}

		Long auditSetId = (Long)attributes.get("auditSetId");

		if (auditSetId != null) {
			setAuditSetId(auditSetId);
		}

		Long fieldClassNameId = (Long)attributes.get("fieldClassNameId");

		if (fieldClassNameId != null) {
			setFieldClassNameId(fieldClassNameId);
		}

		Long fieldClassPK = (Long)attributes.get("fieldClassPK");

		if (fieldClassPK != null) {
			setFieldClassPK(fieldClassPK);
		}

		Integer action = (Integer)attributes.get("action");

		if (action != null) {
			setAction(action);
		}

		Integer field = (Integer)attributes.get("field");

		if (field != null) {
			setField(field);
		}

		Integer visibility = (Integer)attributes.get("visibility");

		if (visibility != null) {
			setVisibility(visibility);
		}

		String oldLabel = (String)attributes.get("oldLabel");

		if (oldLabel != null) {
			setOldLabel(oldLabel);
		}

		String oldValue = (String)attributes.get("oldValue");

		if (oldValue != null) {
			setOldValue(oldValue);
		}

		String newLabel = (String)attributes.get("newLabel");

		if (newLabel != null) {
			setNewLabel(newLabel);
		}

		String newValue = (String)attributes.get("newValue");

		if (newValue != null) {
			setNewValue(newValue);
		}

		Boolean i18n = (Boolean)attributes.get("i18n");

		if (i18n != null) {
			setI18n(i18n);
		}
	}

	public long getAuditEntryId() {
		return _auditEntryId;
	}

	public void setAuditEntryId(long auditEntryId) {
		_auditEntryId = auditEntryId;

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setAuditEntryId", long.class);

				method.invoke(_auditEntryRemoteModel, auditEntryId);
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

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_auditEntryRemoteModel, userId);
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

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_auditEntryRemoteModel, userName);
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

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_auditEntryRemoteModel, createDate);
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

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_auditEntryRemoteModel, classNameId);
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

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_auditEntryRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getPreviousAuditEntryId() {
		return _previousAuditEntryId;
	}

	public void setPreviousAuditEntryId(long previousAuditEntryId) {
		_previousAuditEntryId = previousAuditEntryId;

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPreviousAuditEntryId",
						long.class);

				method.invoke(_auditEntryRemoteModel, previousAuditEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAuditSetId() {
		return _auditSetId;
	}

	public void setAuditSetId(long auditSetId) {
		_auditSetId = auditSetId;

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setAuditSetId", long.class);

				method.invoke(_auditEntryRemoteModel, auditSetId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getFieldClassNameId() {
		return _fieldClassNameId;
	}

	public void setFieldClassNameId(long fieldClassNameId) {
		_fieldClassNameId = fieldClassNameId;

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setFieldClassNameId",
						long.class);

				method.invoke(_auditEntryRemoteModel, fieldClassNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getFieldClassPK() {
		return _fieldClassPK;
	}

	public void setFieldClassPK(long fieldClassPK) {
		_fieldClassPK = fieldClassPK;

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setFieldClassPK", long.class);

				method.invoke(_auditEntryRemoteModel, fieldClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getAction() {
		return _action;
	}

	public void setAction(int action) {
		_action = action;

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setAction", int.class);

				method.invoke(_auditEntryRemoteModel, action);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getField() {
		return _field;
	}

	public void setField(int field) {
		_field = field;

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setField", int.class);

				method.invoke(_auditEntryRemoteModel, field);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getVisibility() {
		return _visibility;
	}

	public void setVisibility(int visibility) {
		_visibility = visibility;

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setVisibility", int.class);

				method.invoke(_auditEntryRemoteModel, visibility);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getOldLabel() {
		return _oldLabel;
	}

	public void setOldLabel(String oldLabel) {
		_oldLabel = oldLabel;

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setOldLabel", String.class);

				method.invoke(_auditEntryRemoteModel, oldLabel);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getOldValue() {
		return _oldValue;
	}

	public void setOldValue(String oldValue) {
		_oldValue = oldValue;

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setOldValue", String.class);

				method.invoke(_auditEntryRemoteModel, oldValue);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getNewLabel() {
		return _newLabel;
	}

	public void setNewLabel(String newLabel) {
		_newLabel = newLabel;

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setNewLabel", String.class);

				method.invoke(_auditEntryRemoteModel, newLabel);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getNewValue() {
		return _newValue;
	}

	public void setNewValue(String newValue) {
		_newValue = newValue;

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setNewValue", String.class);

				method.invoke(_auditEntryRemoteModel, newValue);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getI18n() {
		return _i18n;
	}

	public boolean isI18n() {
		return _i18n;
	}

	public void setI18n(boolean i18n) {
		_i18n = i18n;

		if (_auditEntryRemoteModel != null) {
			try {
				Class<?> clazz = _auditEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setI18n", boolean.class);

				method.invoke(_auditEntryRemoteModel, i18n);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.lang.String getActionLabel() {
		try {
			String methodName = "getActionLabel";

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

	public java.lang.String getFieldClassNameIdLabel() {
		try {
			String methodName = "getFieldClassNameIdLabel";

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

	public java.lang.String getVisibilityLabel() {
		try {
			String methodName = "getVisibilityLabel";

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

	public java.lang.String getFieldLabel() {
		try {
			String methodName = "getFieldLabel";

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

	public BaseModel<?> getAuditEntryRemoteModel() {
		return _auditEntryRemoteModel;
	}

	public void setAuditEntryRemoteModel(BaseModel<?> auditEntryRemoteModel) {
		_auditEntryRemoteModel = auditEntryRemoteModel;
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

		Class<?> remoteModelClass = _auditEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_auditEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AuditEntryLocalServiceUtil.addAuditEntry(this);
		}
		else {
			AuditEntryLocalServiceUtil.updateAuditEntry(this);
		}
	}

	@Override
	public AuditEntry toEscapedModel() {
		return (AuditEntry)ProxyUtil.newProxyInstance(AuditEntry.class.getClassLoader(),
			new Class[] { AuditEntry.class }, new AutoEscapeBeanHandler(this));
	}

	public AuditEntry toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AuditEntryClp clone = new AuditEntryClp();

		clone.setAuditEntryId(getAuditEntryId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setPreviousAuditEntryId(getPreviousAuditEntryId());
		clone.setAuditSetId(getAuditSetId());
		clone.setFieldClassNameId(getFieldClassNameId());
		clone.setFieldClassPK(getFieldClassPK());
		clone.setAction(getAction());
		clone.setField(getField());
		clone.setVisibility(getVisibility());
		clone.setOldLabel(getOldLabel());
		clone.setOldValue(getOldValue());
		clone.setNewLabel(getNewLabel());
		clone.setNewValue(getNewValue());
		clone.setI18n(getI18n());

		return clone;
	}

	public int compareTo(AuditEntry auditEntry) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), auditEntry.getCreateDate());

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

		if (!(obj instanceof AuditEntryClp)) {
			return false;
		}

		AuditEntryClp auditEntry = (AuditEntryClp)obj;

		long primaryKey = auditEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(37);

		sb.append("{auditEntryId=");
		sb.append(getAuditEntryId());
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
		sb.append(", previousAuditEntryId=");
		sb.append(getPreviousAuditEntryId());
		sb.append(", auditSetId=");
		sb.append(getAuditSetId());
		sb.append(", fieldClassNameId=");
		sb.append(getFieldClassNameId());
		sb.append(", fieldClassPK=");
		sb.append(getFieldClassPK());
		sb.append(", action=");
		sb.append(getAction());
		sb.append(", field=");
		sb.append(getField());
		sb.append(", visibility=");
		sb.append(getVisibility());
		sb.append(", oldLabel=");
		sb.append(getOldLabel());
		sb.append(", oldValue=");
		sb.append(getOldValue());
		sb.append(", newLabel=");
		sb.append(getNewLabel());
		sb.append(", newValue=");
		sb.append(getNewValue());
		sb.append(", i18n=");
		sb.append(getI18n());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AuditEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>auditEntryId</column-name><column-value><![CDATA[");
		sb.append(getAuditEntryId());
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
			"<column><column-name>previousAuditEntryId</column-name><column-value><![CDATA[");
		sb.append(getPreviousAuditEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>auditSetId</column-name><column-value><![CDATA[");
		sb.append(getAuditSetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fieldClassNameId</column-name><column-value><![CDATA[");
		sb.append(getFieldClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fieldClassPK</column-name><column-value><![CDATA[");
		sb.append(getFieldClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>action</column-name><column-value><![CDATA[");
		sb.append(getAction());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>field</column-name><column-value><![CDATA[");
		sb.append(getField());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>visibility</column-name><column-value><![CDATA[");
		sb.append(getVisibility());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>oldLabel</column-name><column-value><![CDATA[");
		sb.append(getOldLabel());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>oldValue</column-name><column-value><![CDATA[");
		sb.append(getOldValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>newLabel</column-name><column-value><![CDATA[");
		sb.append(getNewLabel());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>newValue</column-name><column-value><![CDATA[");
		sb.append(getNewValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>i18n</column-name><column-value><![CDATA[");
		sb.append(getI18n());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _auditEntryId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private long _classNameId;
	private long _classPK;
	private long _previousAuditEntryId;
	private long _auditSetId;
	private long _fieldClassNameId;
	private long _fieldClassPK;
	private int _action;
	private int _field;
	private int _visibility;
	private String _oldLabel;
	private String _oldValue;
	private String _newLabel;
	private String _newValue;
	private boolean _i18n;
	private BaseModel<?> _auditEntryRemoteModel;
}