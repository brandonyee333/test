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
import com.liferay.osb.service.TicketCallLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
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
public class TicketCallClp extends BaseModelImpl<TicketCall>
	implements TicketCall {
	public TicketCallClp() {
	}

	public Class<?> getModelClass() {
		return TicketCall.class;
	}

	public String getModelClassName() {
		return TicketCall.class.getName();
	}

	public long getPrimaryKey() {
		return _ticketCallId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTicketCallId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_ticketCallId);
	}

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
	}

	public long getTicketCallId() {
		return _ticketCallId;
	}

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

	public long getUserId() {
		return _userId;
	}

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

	public Date getCreateDate() {
		return _createDate;
	}

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

	public long getTicketEntryId() {
		return _ticketEntryId;
	}

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

	public int getType() {
		return _type;
	}

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

	public Date getCallDate() {
		return _callDate;
	}

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

	public long getCallLength() {
		return _callLength;
	}

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

	public String getCustomerName() {
		return _customerName;
	}

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

	public String getCustomerContact() {
		return _customerContact;
	}

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

	public String getConfirmation() {
		return _confirmation;
	}

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

	public String getInstructions() {
		return _instructions;
	}

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

	public void persist() throws SystemException {
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

	public TicketCall toUnescapedModel() {
		return this;
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

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
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
	private String _userUuid;
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
}