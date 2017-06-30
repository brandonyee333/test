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
import com.liferay.osb.service.TicketInformationLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class TicketInformationClp extends BaseModelImpl<TicketInformation>
	implements TicketInformation {
	public TicketInformationClp() {
	}

	public Class<?> getModelClass() {
		return TicketInformation.class;
	}

	public String getModelClassName() {
		return TicketInformation.class.getName();
	}

	public long getPrimaryKey() {
		return _ticketInformationId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTicketInformationId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_ticketInformationId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketInformationId", getTicketInformationId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("fieldId", getFieldId());
		attributes.put("data", getData());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketInformationId = (Long)attributes.get("ticketInformationId");

		if (ticketInformationId != null) {
			setTicketInformationId(ticketInformationId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		Long fieldId = (Long)attributes.get("fieldId");

		if (fieldId != null) {
			setFieldId(fieldId);
		}

		String data = (String)attributes.get("data");

		if (data != null) {
			setData(data);
		}
	}

	public long getTicketInformationId() {
		return _ticketInformationId;
	}

	public void setTicketInformationId(long ticketInformationId) {
		_ticketInformationId = ticketInformationId;

		if (_ticketInformationRemoteModel != null) {
			try {
				Class<?> clazz = _ticketInformationRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketInformationId",
						long.class);

				method.invoke(_ticketInformationRemoteModel, ticketInformationId);
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

		if (_ticketInformationRemoteModel != null) {
			try {
				Class<?> clazz = _ticketInformationRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_ticketInformationRemoteModel, createDate);
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

		if (_ticketInformationRemoteModel != null) {
			try {
				Class<?> clazz = _ticketInformationRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_ticketInformationRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getTicketEntryId() {
		return _ticketEntryId;
	}

	public void setTicketEntryId(long ticketEntryId) {
		_ticketEntryId = ticketEntryId;

		if (_ticketInformationRemoteModel != null) {
			try {
				Class<?> clazz = _ticketInformationRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketEntryId", long.class);

				method.invoke(_ticketInformationRemoteModel, ticketEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getFieldId() {
		return _fieldId;
	}

	public void setFieldId(long fieldId) {
		_fieldId = fieldId;

		if (_ticketInformationRemoteModel != null) {
			try {
				Class<?> clazz = _ticketInformationRemoteModel.getClass();

				Method method = clazz.getMethod("setFieldId", long.class);

				method.invoke(_ticketInformationRemoteModel, fieldId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getData() {
		return _data;
	}

	public void setData(String data) {
		_data = data;

		if (_ticketInformationRemoteModel != null) {
			try {
				Class<?> clazz = _ticketInformationRemoteModel.getClass();

				Method method = clazz.getMethod("setData", String.class);

				method.invoke(_ticketInformationRemoteModel, data);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getTicketInformationRemoteModel() {
		return _ticketInformationRemoteModel;
	}

	public void setTicketInformationRemoteModel(
		BaseModel<?> ticketInformationRemoteModel) {
		_ticketInformationRemoteModel = ticketInformationRemoteModel;
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

		Class<?> remoteModelClass = _ticketInformationRemoteModel.getClass();

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

		Object returnValue = method.invoke(_ticketInformationRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TicketInformationLocalServiceUtil.addTicketInformation(this);
		}
		else {
			TicketInformationLocalServiceUtil.updateTicketInformation(this);
		}
	}

	@Override
	public TicketInformation toEscapedModel() {
		return (TicketInformation)ProxyUtil.newProxyInstance(TicketInformation.class.getClassLoader(),
			new Class[] { TicketInformation.class },
			new AutoEscapeBeanHandler(this));
	}

	public TicketInformation toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		TicketInformationClp clone = new TicketInformationClp();

		clone.setTicketInformationId(getTicketInformationId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setTicketEntryId(getTicketEntryId());
		clone.setFieldId(getFieldId());
		clone.setData(getData());

		return clone;
	}

	public int compareTo(TicketInformation ticketInformation) {
		long primaryKey = ticketInformation.getPrimaryKey();

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

		if (!(obj instanceof TicketInformationClp)) {
			return false;
		}

		TicketInformationClp ticketInformation = (TicketInformationClp)obj;

		long primaryKey = ticketInformation.getPrimaryKey();

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
		StringBundler sb = new StringBundler(13);

		sb.append("{ticketInformationId=");
		sb.append(getTicketInformationId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", ticketEntryId=");
		sb.append(getTicketEntryId());
		sb.append(", fieldId=");
		sb.append(getFieldId());
		sb.append(", data=");
		sb.append(getData());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TicketInformation");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>ticketInformationId</column-name><column-value><![CDATA[");
		sb.append(getTicketInformationId());
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
			"<column><column-name>ticketEntryId</column-name><column-value><![CDATA[");
		sb.append(getTicketEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fieldId</column-name><column-value><![CDATA[");
		sb.append(getFieldId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>data</column-name><column-value><![CDATA[");
		sb.append(getData());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _ticketInformationId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _ticketEntryId;
	private long _fieldId;
	private String _data;
	private BaseModel<?> _ticketInformationRemoteModel;
}