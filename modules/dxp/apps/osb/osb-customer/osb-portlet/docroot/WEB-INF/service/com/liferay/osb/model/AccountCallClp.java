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

import com.liferay.osb.service.AccountCallLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

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
public class AccountCallClp extends BaseModelImpl<AccountCall>
	implements AccountCall {
	public AccountCallClp() {
	}

	public Class<?> getModelClass() {
		return AccountCall.class;
	}

	public String getModelClassName() {
		return AccountCall.class.getName();
	}

	public long getPrimaryKey() {
		return _accountCallId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAccountCallId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_accountCallId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountCallId", getAccountCallId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedUserId", getModifiedUserId());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("type", getType());
		attributes.put("callDate", getCallDate());
		attributes.put("callLength", getCallLength());
		attributes.put("summary", getSummary());
		attributes.put("clientsPresent", getClientsPresent());
		attributes.put("notes", getNotes());
		attributes.put("actionItems", getActionItems());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountCallId = (Long)attributes.get("accountCallId");

		if (accountCallId != null) {
			setAccountCallId(accountCallId);
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

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
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

		String summary = (String)attributes.get("summary");

		if (summary != null) {
			setSummary(summary);
		}

		String clientsPresent = (String)attributes.get("clientsPresent");

		if (clientsPresent != null) {
			setClientsPresent(clientsPresent);
		}

		String notes = (String)attributes.get("notes");

		if (notes != null) {
			setNotes(notes);
		}

		String actionItems = (String)attributes.get("actionItems");

		if (actionItems != null) {
			setActionItems(actionItems);
		}
	}

	public long getAccountCallId() {
		return _accountCallId;
	}

	public void setAccountCallId(long accountCallId) {
		_accountCallId = accountCallId;

		if (_accountCallRemoteModel != null) {
			try {
				Class<?> clazz = _accountCallRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountCallId", long.class);

				method.invoke(_accountCallRemoteModel, accountCallId);
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

		if (_accountCallRemoteModel != null) {
			try {
				Class<?> clazz = _accountCallRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_accountCallRemoteModel, createDate);
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

		if (_accountCallRemoteModel != null) {
			try {
				Class<?> clazz = _accountCallRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedUserId", long.class);

				method.invoke(_accountCallRemoteModel, modifiedUserId);
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

		if (_accountCallRemoteModel != null) {
			try {
				Class<?> clazz = _accountCallRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedUserName",
						String.class);

				method.invoke(_accountCallRemoteModel, modifiedUserName);
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

		if (_accountCallRemoteModel != null) {
			try {
				Class<?> clazz = _accountCallRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_accountCallRemoteModel, modifiedDate);
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

		if (_accountCallRemoteModel != null) {
			try {
				Class<?> clazz = _accountCallRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryId", long.class);

				method.invoke(_accountCallRemoteModel, accountEntryId);
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

		if (_accountCallRemoteModel != null) {
			try {
				Class<?> clazz = _accountCallRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_accountCallRemoteModel, type);
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

		if (_accountCallRemoteModel != null) {
			try {
				Class<?> clazz = _accountCallRemoteModel.getClass();

				Method method = clazz.getMethod("setCallDate", Date.class);

				method.invoke(_accountCallRemoteModel, callDate);
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

		if (_accountCallRemoteModel != null) {
			try {
				Class<?> clazz = _accountCallRemoteModel.getClass();

				Method method = clazz.getMethod("setCallLength", long.class);

				method.invoke(_accountCallRemoteModel, callLength);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getSummary() {
		return _summary;
	}

	public void setSummary(String summary) {
		_summary = summary;

		if (_accountCallRemoteModel != null) {
			try {
				Class<?> clazz = _accountCallRemoteModel.getClass();

				Method method = clazz.getMethod("setSummary", String.class);

				method.invoke(_accountCallRemoteModel, summary);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getClientsPresent() {
		return _clientsPresent;
	}

	public void setClientsPresent(String clientsPresent) {
		_clientsPresent = clientsPresent;

		if (_accountCallRemoteModel != null) {
			try {
				Class<?> clazz = _accountCallRemoteModel.getClass();

				Method method = clazz.getMethod("setClientsPresent",
						String.class);

				method.invoke(_accountCallRemoteModel, clientsPresent);
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

		if (_accountCallRemoteModel != null) {
			try {
				Class<?> clazz = _accountCallRemoteModel.getClass();

				Method method = clazz.getMethod("setNotes", String.class);

				method.invoke(_accountCallRemoteModel, notes);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getActionItems() {
		return _actionItems;
	}

	public void setActionItems(String actionItems) {
		_actionItems = actionItems;

		if (_accountCallRemoteModel != null) {
			try {
				Class<?> clazz = _accountCallRemoteModel.getClass();

				Method method = clazz.getMethod("setActionItems", String.class);

				method.invoke(_accountCallRemoteModel, actionItems);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getCallLengthMinutes() {
		try {
			String methodName = "getCallLengthMinutes";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Integer returnObj = (Integer)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
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

	public int getCallLengthSeconds() {
		try {
			String methodName = "getCallLengthSeconds";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Integer returnObj = (Integer)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public int getCallLengthHours() {
		try {
			String methodName = "getCallLengthHours";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Integer returnObj = (Integer)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getAccountCallRemoteModel() {
		return _accountCallRemoteModel;
	}

	public void setAccountCallRemoteModel(BaseModel<?> accountCallRemoteModel) {
		_accountCallRemoteModel = accountCallRemoteModel;
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

		Class<?> remoteModelClass = _accountCallRemoteModel.getClass();

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

		Object returnValue = method.invoke(_accountCallRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AccountCallLocalServiceUtil.addAccountCall(this);
		}
		else {
			AccountCallLocalServiceUtil.updateAccountCall(this);
		}
	}

	@Override
	public AccountCall toEscapedModel() {
		return (AccountCall)ProxyUtil.newProxyInstance(AccountCall.class.getClassLoader(),
			new Class[] { AccountCall.class }, new AutoEscapeBeanHandler(this));
	}

	public AccountCall toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AccountCallClp clone = new AccountCallClp();

		clone.setAccountCallId(getAccountCallId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedUserId(getModifiedUserId());
		clone.setModifiedUserName(getModifiedUserName());
		clone.setModifiedDate(getModifiedDate());
		clone.setAccountEntryId(getAccountEntryId());
		clone.setType(getType());
		clone.setCallDate(getCallDate());
		clone.setCallLength(getCallLength());
		clone.setSummary(getSummary());
		clone.setClientsPresent(getClientsPresent());
		clone.setNotes(getNotes());
		clone.setActionItems(getActionItems());

		return clone;
	}

	public int compareTo(AccountCall accountCall) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), accountCall.getCreateDate());

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

		if (!(obj instanceof AccountCallClp)) {
			return false;
		}

		AccountCallClp accountCall = (AccountCallClp)obj;

		long primaryKey = accountCall.getPrimaryKey();

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
		StringBundler sb = new StringBundler(27);

		sb.append("{accountCallId=");
		sb.append(getAccountCallId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedUserId=");
		sb.append(getModifiedUserId());
		sb.append(", modifiedUserName=");
		sb.append(getModifiedUserName());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", accountEntryId=");
		sb.append(getAccountEntryId());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", callDate=");
		sb.append(getCallDate());
		sb.append(", callLength=");
		sb.append(getCallLength());
		sb.append(", summary=");
		sb.append(getSummary());
		sb.append(", clientsPresent=");
		sb.append(getClientsPresent());
		sb.append(", notes=");
		sb.append(getNotes());
		sb.append(", actionItems=");
		sb.append(getActionItems());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AccountCall");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>accountCallId</column-name><column-value><![CDATA[");
		sb.append(getAccountCallId());
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
			"<column><column-name>accountEntryId</column-name><column-value><![CDATA[");
		sb.append(getAccountEntryId());
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
			"<column><column-name>summary</column-name><column-value><![CDATA[");
		sb.append(getSummary());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>clientsPresent</column-name><column-value><![CDATA[");
		sb.append(getClientsPresent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notes</column-name><column-value><![CDATA[");
		sb.append(getNotes());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actionItems</column-name><column-value><![CDATA[");
		sb.append(getActionItems());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _accountCallId;
	private Date _createDate;
	private long _modifiedUserId;
	private String _modifiedUserUuid;
	private String _modifiedUserName;
	private Date _modifiedDate;
	private long _accountEntryId;
	private int _type;
	private Date _callDate;
	private long _callLength;
	private String _summary;
	private String _clientsPresent;
	private String _notes;
	private String _actionItems;
	private BaseModel<?> _accountCallRemoteModel;
}