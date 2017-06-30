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
import com.liferay.osb.service.PartnerEntryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
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
public class PartnerEntryClp extends BaseModelImpl<PartnerEntry>
	implements PartnerEntry {
	public PartnerEntryClp() {
	}

	public Class<?> getModelClass() {
		return PartnerEntry.class;
	}

	public String getModelClassName() {
		return PartnerEntry.class.getName();
	}

	public long getPrimaryKey() {
		return _partnerEntryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setPartnerEntryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_partnerEntryId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("partnerEntryId", getPartnerEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedUserId", getModifiedUserId());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentPartnerEntryId", getParentPartnerEntryId());
		attributes.put("dossieraAccountKey", getDossieraAccountKey());
		attributes.put("code", getCode());
		attributes.put("notes", getNotes());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long partnerEntryId = (Long)attributes.get("partnerEntryId");

		if (partnerEntryId != null) {
			setPartnerEntryId(partnerEntryId);
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

		Long modifiedUserId = (Long)attributes.get("modifiedUserId");

		if (modifiedUserId != null) {
			setModifiedUserId(modifiedUserId);
		}

		String modifiedUserName = (String)attributes.get("modifiedUserName");

		if (modifiedUserName != null) {
			setModifiedUserName(modifiedUserName);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long parentPartnerEntryId = (Long)attributes.get("parentPartnerEntryId");

		if (parentPartnerEntryId != null) {
			setParentPartnerEntryId(parentPartnerEntryId);
		}

		String dossieraAccountKey = (String)attributes.get("dossieraAccountKey");

		if (dossieraAccountKey != null) {
			setDossieraAccountKey(dossieraAccountKey);
		}

		String code = (String)attributes.get("code");

		if (code != null) {
			setCode(code);
		}

		String notes = (String)attributes.get("notes");

		if (notes != null) {
			setNotes(notes);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	public long getPartnerEntryId() {
		return _partnerEntryId;
	}

	public void setPartnerEntryId(long partnerEntryId) {
		_partnerEntryId = partnerEntryId;

		if (_partnerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _partnerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPartnerEntryId", long.class);

				method.invoke(_partnerEntryRemoteModel, partnerEntryId);
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

		if (_partnerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _partnerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_partnerEntryRemoteModel, userId);
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

		if (_partnerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _partnerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_partnerEntryRemoteModel, userName);
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

		if (_partnerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _partnerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_partnerEntryRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getModifiedUserId() {
		return _modifiedUserId;
	}

	public void setModifiedUserId(long modifiedUserId) {
		_modifiedUserId = modifiedUserId;

		if (_partnerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _partnerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedUserId", long.class);

				method.invoke(_partnerEntryRemoteModel, modifiedUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getModifiedUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getModifiedUserId(), "uuid",
			_modifiedUserUuid);
	}

	public void setModifiedUserUuid(String modifiedUserUuid) {
		_modifiedUserUuid = modifiedUserUuid;
	}

	public String getModifiedUserName() {
		return _modifiedUserName;
	}

	public void setModifiedUserName(String modifiedUserName) {
		_modifiedUserName = modifiedUserName;

		if (_partnerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _partnerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedUserName",
						String.class);

				method.invoke(_partnerEntryRemoteModel, modifiedUserName);
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

		if (_partnerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _partnerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_partnerEntryRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getParentPartnerEntryId() {
		return _parentPartnerEntryId;
	}

	public void setParentPartnerEntryId(long parentPartnerEntryId) {
		_parentPartnerEntryId = parentPartnerEntryId;

		if (_partnerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _partnerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setParentPartnerEntryId",
						long.class);

				method.invoke(_partnerEntryRemoteModel, parentPartnerEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getDossieraAccountKey() {
		return _dossieraAccountKey;
	}

	public void setDossieraAccountKey(String dossieraAccountKey) {
		_dossieraAccountKey = dossieraAccountKey;

		if (_partnerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _partnerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setDossieraAccountKey",
						String.class);

				method.invoke(_partnerEntryRemoteModel, dossieraAccountKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		_code = code;

		if (_partnerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _partnerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCode", String.class);

				method.invoke(_partnerEntryRemoteModel, code);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getNotes() {
		return _notes;
	}

	public void setNotes(String notes) {
		_notes = notes;

		if (_partnerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _partnerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setNotes", String.class);

				method.invoke(_partnerEntryRemoteModel, notes);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;

		if (_partnerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _partnerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_partnerEntryRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.util.List<com.liferay.osb.model.PartnerWorker> getPartnerWorkers() {
		try {
			String methodName = "getPartnerWorkers";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.osb.model.PartnerWorker> returnObj = (java.util.List<com.liferay.osb.model.PartnerWorker>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries() {
		try {
			String methodName = "getAccountEntries";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.osb.model.AccountEntry> returnObj = (java.util.List<com.liferay.osb.model.AccountEntry>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.osb.model.PartnerEntry getParentPartnerEntry() {
		try {
			String methodName = "getParentPartnerEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.PartnerEntry returnObj = (com.liferay.osb.model.PartnerEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.List<com.liferay.osb.model.PartnerEntry> getChildPartnerEntries(
		boolean recursive) {
		try {
			String methodName = "getChildPartnerEntries";

			Class<?>[] parameterTypes = new Class<?>[] { boolean.class };

			Object[] parameterValues = new Object[] { recursive };

			java.util.List<com.liferay.osb.model.PartnerEntry> returnObj = (java.util.List<com.liferay.osb.model.PartnerEntry>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public long[] getSupportRegionIds() {
		try {
			String methodName = "getSupportRegionIds";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			long[] returnObj = (long[])invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.osb.model.SupportRegion getSupportRegion() {
		try {
			String methodName = "getSupportRegion";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.SupportRegion returnObj = (com.liferay.osb.model.SupportRegion)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getStatusLabel() {
		try {
			String methodName = "getStatusLabel";

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

	public BaseModel<?> getPartnerEntryRemoteModel() {
		return _partnerEntryRemoteModel;
	}

	public void setPartnerEntryRemoteModel(BaseModel<?> partnerEntryRemoteModel) {
		_partnerEntryRemoteModel = partnerEntryRemoteModel;
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

		Class<?> remoteModelClass = _partnerEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_partnerEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			PartnerEntryLocalServiceUtil.addPartnerEntry(this);
		}
		else {
			PartnerEntryLocalServiceUtil.updatePartnerEntry(this);
		}
	}

	@Override
	public PartnerEntry toEscapedModel() {
		return (PartnerEntry)ProxyUtil.newProxyInstance(PartnerEntry.class.getClassLoader(),
			new Class[] { PartnerEntry.class }, new AutoEscapeBeanHandler(this));
	}

	public PartnerEntry toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		PartnerEntryClp clone = new PartnerEntryClp();

		clone.setPartnerEntryId(getPartnerEntryId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedUserId(getModifiedUserId());
		clone.setModifiedUserName(getModifiedUserName());
		clone.setModifiedDate(getModifiedDate());
		clone.setParentPartnerEntryId(getParentPartnerEntryId());
		clone.setDossieraAccountKey(getDossieraAccountKey());
		clone.setCode(getCode());
		clone.setNotes(getNotes());
		clone.setStatus(getStatus());

		return clone;
	}

	public int compareTo(PartnerEntry partnerEntry) {
		int value = 0;

		value = getCode().toLowerCase()
					.compareTo(partnerEntry.getCode().toLowerCase());

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

		if (!(obj instanceof PartnerEntryClp)) {
			return false;
		}

		PartnerEntryClp partnerEntry = (PartnerEntryClp)obj;

		long primaryKey = partnerEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(25);

		sb.append("{partnerEntryId=");
		sb.append(getPartnerEntryId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedUserId=");
		sb.append(getModifiedUserId());
		sb.append(", modifiedUserName=");
		sb.append(getModifiedUserName());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", parentPartnerEntryId=");
		sb.append(getParentPartnerEntryId());
		sb.append(", dossieraAccountKey=");
		sb.append(getDossieraAccountKey());
		sb.append(", code=");
		sb.append(getCode());
		sb.append(", notes=");
		sb.append(getNotes());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.PartnerEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>partnerEntryId</column-name><column-value><![CDATA[");
		sb.append(getPartnerEntryId());
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
			"<column><column-name>modifiedUserId</column-name><column-value><![CDATA[");
		sb.append(getModifiedUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedUserName</column-name><column-value><![CDATA[");
		sb.append(getModifiedUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentPartnerEntryId</column-name><column-value><![CDATA[");
		sb.append(getParentPartnerEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dossieraAccountKey</column-name><column-value><![CDATA[");
		sb.append(getDossieraAccountKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>code</column-name><column-value><![CDATA[");
		sb.append(getCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notes</column-name><column-value><![CDATA[");
		sb.append(getNotes());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _partnerEntryId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private long _modifiedUserId;
	private String _modifiedUserUuid;
	private String _modifiedUserName;
	private Date _modifiedDate;
	private long _parentPartnerEntryId;
	private String _dossieraAccountKey;
	private String _code;
	private String _notes;
	private int _status;
	private BaseModel<?> _partnerEntryRemoteModel;
}