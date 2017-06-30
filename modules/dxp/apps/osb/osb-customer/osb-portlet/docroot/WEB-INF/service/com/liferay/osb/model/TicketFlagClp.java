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
import com.liferay.osb.service.TicketFlagLocalServiceUtil;

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
public class TicketFlagClp extends BaseModelImpl<TicketFlag>
	implements TicketFlag {
	public TicketFlagClp() {
	}

	public Class<?> getModelClass() {
		return TicketFlag.class;
	}

	public String getModelClassName() {
		return TicketFlag.class.getName();
	}

	public long getPrimaryKey() {
		return _ticketFlagId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTicketFlagId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_ticketFlagId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketFlagId", getTicketFlagId());
		attributes.put("userId", getUserId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("type", getType());
		attributes.put("flag", getFlag());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketFlagId = (Long)attributes.get("ticketFlagId");

		if (ticketFlagId != null) {
			setTicketFlagId(ticketFlagId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer flag = (Integer)attributes.get("flag");

		if (flag != null) {
			setFlag(flag);
		}
	}

	public long getTicketFlagId() {
		return _ticketFlagId;
	}

	public void setTicketFlagId(long ticketFlagId) {
		_ticketFlagId = ticketFlagId;

		if (_ticketFlagRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFlagRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketFlagId", long.class);

				method.invoke(_ticketFlagRemoteModel, ticketFlagId);
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

		if (_ticketFlagRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFlagRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_ticketFlagRemoteModel, userId);
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_ticketFlagRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFlagRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_ticketFlagRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;

		if (_ticketFlagRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFlagRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryId", long.class);

				method.invoke(_ticketFlagRemoteModel, accountEntryId);
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

		if (_ticketFlagRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFlagRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketEntryId", long.class);

				method.invoke(_ticketFlagRemoteModel, ticketEntryId);
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

		if (_ticketFlagRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFlagRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_ticketFlagRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getFlag() {
		return _flag;
	}

	public void setFlag(int flag) {
		_flag = flag;

		if (_ticketFlagRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFlagRemoteModel.getClass();

				Method method = clazz.getMethod("setFlag", int.class);

				method.invoke(_ticketFlagRemoteModel, flag);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getTicketFlagRemoteModel() {
		return _ticketFlagRemoteModel;
	}

	public void setTicketFlagRemoteModel(BaseModel<?> ticketFlagRemoteModel) {
		_ticketFlagRemoteModel = ticketFlagRemoteModel;
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

		Class<?> remoteModelClass = _ticketFlagRemoteModel.getClass();

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

		Object returnValue = method.invoke(_ticketFlagRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TicketFlagLocalServiceUtil.addTicketFlag(this);
		}
		else {
			TicketFlagLocalServiceUtil.updateTicketFlag(this);
		}
	}

	@Override
	public TicketFlag toEscapedModel() {
		return (TicketFlag)ProxyUtil.newProxyInstance(TicketFlag.class.getClassLoader(),
			new Class[] { TicketFlag.class }, new AutoEscapeBeanHandler(this));
	}

	public TicketFlag toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		TicketFlagClp clone = new TicketFlagClp();

		clone.setTicketFlagId(getTicketFlagId());
		clone.setUserId(getUserId());
		clone.setModifiedDate(getModifiedDate());
		clone.setAccountEntryId(getAccountEntryId());
		clone.setTicketEntryId(getTicketEntryId());
		clone.setType(getType());
		clone.setFlag(getFlag());

		return clone;
	}

	public int compareTo(TicketFlag ticketFlag) {
		long primaryKey = ticketFlag.getPrimaryKey();

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

		if (!(obj instanceof TicketFlagClp)) {
			return false;
		}

		TicketFlagClp ticketFlag = (TicketFlagClp)obj;

		long primaryKey = ticketFlag.getPrimaryKey();

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

		sb.append("{ticketFlagId=");
		sb.append(getTicketFlagId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", accountEntryId=");
		sb.append(getAccountEntryId());
		sb.append(", ticketEntryId=");
		sb.append(getTicketEntryId());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", flag=");
		sb.append(getFlag());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TicketFlag");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>ticketFlagId</column-name><column-value><![CDATA[");
		sb.append(getTicketFlagId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountEntryId</column-name><column-value><![CDATA[");
		sb.append(getAccountEntryId());
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
			"<column><column-name>flag</column-name><column-value><![CDATA[");
		sb.append(getFlag());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _ticketFlagId;
	private long _userId;
	private String _userUuid;
	private Date _modifiedDate;
	private long _accountEntryId;
	private long _ticketEntryId;
	private int _type;
	private int _flag;
	private BaseModel<?> _ticketFlagRemoteModel;
}