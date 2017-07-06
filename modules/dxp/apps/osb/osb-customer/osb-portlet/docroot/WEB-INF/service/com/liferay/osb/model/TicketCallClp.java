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

import com.liferay.osb.service.ClpSerializer;
import com.liferay.osb.service.TicketCallLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class TicketCallClp extends BaseModelImpl<TicketCall>
	implements TicketCall {
	public TicketCallClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return TicketCall.class;
	}

	@Override
	public String getModelClassName() {
		return TicketCall.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _ticketCallId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTicketCallId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketCallId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketCallId", getTicketCallId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("type", getType());
		attributes.put("callDate", getCallDate());
		attributes.put("callLength", getCallLength());
		attributes.put("customerName", getCustomerName());
		attributes.put("customerContact", getCustomerContact());
		attributes.put("confirmation", getConfirmation());
		attributes.put("instructions", getInstructions());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketCallId = (Long)attributes.get("ticketCallId");

		if (ticketCallId != null) {
			setTicketCallId(ticketCallId);
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

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Date callDate = (Date)attributes.get("callDate");

		if (callDate != null) {
			setCallDate(callDate);
		}

		Long callLength = (Long)attributes.get("callLength");

		if (callLength != null) {
			setCallLength(callLength);
		}

		String customerName = (String)attributes.get("customerName");

		if (customerName != null) {
			setCustomerName(customerName);
		}

		String customerContact = (String)attributes.get("customerContact");

		if (customerContact != null) {
			setCustomerContact(customerContact);
		}

		String confirmation = (String)attributes.get("confirmation");

		if (confirmation != null) {
			setConfirmation(confirmation);
		}

		String instructions = (String)attributes.get("instructions");

		if (instructions != null) {
			setInstructions(instructions);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getTicketCallId() {
		return _ticketCallId;
	}

	@Override
	public void setTicketCallId(long ticketCallId) {
		_ticketCallId = ticketCallId;

		if (_ticketCallRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCallRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketCallId", long.class);

				method.invoke(_ticketCallRemoteModel, ticketCallId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_ticketCallRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCallRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_ticketCallRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_ticketCallRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCallRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_ticketCallRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_ticketCallRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCallRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_ticketCallRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getTicketEntryId() {
		return _ticketEntryId;
	}

	@Override
	public void setTicketEntryId(long ticketEntryId) {
		_ticketEntryId = ticketEntryId;

		if (_ticketCallRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCallRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketEntryId", long.class);

				method.invoke(_ticketCallRemoteModel, ticketEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_type = type;

		if (_ticketCallRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCallRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_ticketCallRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCallDate() {
		return _callDate;
	}

	@Override
	public void setCallDate(Date callDate) {
		_callDate = callDate;

		if (_ticketCallRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCallRemoteModel.getClass();

				Method method = clazz.getMethod("setCallDate", Date.class);

				method.invoke(_ticketCallRemoteModel, callDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCallLength() {
		return _callLength;
	}

	@Override
	public void setCallLength(long callLength) {
		_callLength = callLength;

		if (_ticketCallRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCallRemoteModel.getClass();

				Method method = clazz.getMethod("setCallLength", long.class);

				method.invoke(_ticketCallRemoteModel, callLength);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCustomerName() {
		return _customerName;
	}

	@Override
	public void setCustomerName(String customerName) {
		_customerName = customerName;

		if (_ticketCallRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCallRemoteModel.getClass();

				Method method = clazz.getMethod("setCustomerName", String.class);

				method.invoke(_ticketCallRemoteModel, customerName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCustomerContact() {
		return _customerContact;
	}

	@Override
	public void setCustomerContact(String customerContact) {
		_customerContact = customerContact;

		if (_ticketCallRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCallRemoteModel.getClass();

				Method method = clazz.getMethod("setCustomerContact",
						String.class);

				method.invoke(_ticketCallRemoteModel, customerContact);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getConfirmation() {
		return _confirmation;
	}

	@Override
	public void setConfirmation(String confirmation) {
		_confirmation = confirmation;

		if (_ticketCallRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCallRemoteModel.getClass();

				Method method = clazz.getMethod("setConfirmation", String.class);

				method.invoke(_ticketCallRemoteModel, confirmation);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getInstructions() {
		return _instructions;
	}

	@Override
	public void setInstructions(String instructions) {
		_instructions = instructions;

		if (_ticketCallRemoteModel != null) {
			try {
				Class<?> clazz = _ticketCallRemoteModel.getClass();

				Method method = clazz.getMethod("setInstructions", String.class);

				method.invoke(_ticketCallRemoteModel, instructions);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public java.lang.String getCallLengthLabel() {
		try {
			String methodName = "getCallLengthLabel";

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

	@Override
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

	public BaseModel<?> getTicketCallRemoteModel() {
		return _ticketCallRemoteModel;
	}

	public void setTicketCallRemoteModel(BaseModel<?> ticketCallRemoteModel) {
		_ticketCallRemoteModel = ticketCallRemoteModel;
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

		Class<?> remoteModelClass = _ticketCallRemoteModel.getClass();

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

		Object returnValue = method.invoke(_ticketCallRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			TicketCallLocalServiceUtil.addTicketCall(this);
		}
		else {
			TicketCallLocalServiceUtil.updateTicketCall(this);
		}
	}

	@Override
	public TicketCall toEscapedModel() {
		return (TicketCall)ProxyUtil.newProxyInstance(TicketCall.class.getClassLoader(),
			new Class[] { TicketCall.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		TicketCallClp clone = new TicketCallClp();

		clone.setTicketCallId(getTicketCallId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setTicketEntryId(getTicketEntryId());
		clone.setType(getType());
		clone.setCallDate(getCallDate());
		clone.setCallLength(getCallLength());
		clone.setCustomerName(getCustomerName());
		clone.setCustomerContact(getCustomerContact());
		clone.setConfirmation(getConfirmation());
		clone.setInstructions(getInstructions());

		return clone;
	}

	@Override
	public int compareTo(TicketCall ticketCall) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), ticketCall.getCreateDate());

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

		if (!(obj instanceof TicketCallClp)) {
			return false;
		}

		TicketCallClp ticketCall = (TicketCallClp)obj;

		long primaryKey = ticketCall.getPrimaryKey();

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
		StringBundler sb = new StringBundler(25);

		sb.append("{ticketCallId=");
		sb.append(getTicketCallId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", ticketEntryId=");
		sb.append(getTicketEntryId());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", callDate=");
		sb.append(getCallDate());
		sb.append(", callLength=");
		sb.append(getCallLength());
		sb.append(", customerName=");
		sb.append(getCustomerName());
		sb.append(", customerContact=");
		sb.append(getCustomerContact());
		sb.append(", confirmation=");
		sb.append(getConfirmation());
		sb.append(", instructions=");
		sb.append(getInstructions());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TicketCall");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>ticketCallId</column-name><column-value><![CDATA[");
		sb.append(getTicketCallId());
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
			"<column><column-name>ticketEntryId</column-name><column-value><![CDATA[");
		sb.append(getTicketEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>callDate</column-name><column-value><![CDATA[");
		sb.append(getCallDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>callLength</column-name><column-value><![CDATA[");
		sb.append(getCallLength());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>customerName</column-name><column-value><![CDATA[");
		sb.append(getCustomerName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>customerContact</column-name><column-value><![CDATA[");
		sb.append(getCustomerContact());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>confirmation</column-name><column-value><![CDATA[");
		sb.append(getConfirmation());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>instructions</column-name><column-value><![CDATA[");
		sb.append(getInstructions());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _ticketCallId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _ticketEntryId;
	private int _type;
	private Date _callDate;
	private long _callLength;
	private String _customerName;
	private String _customerContact;
	private String _confirmation;
	private String _instructions;
	private BaseModel<?> _ticketCallRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}