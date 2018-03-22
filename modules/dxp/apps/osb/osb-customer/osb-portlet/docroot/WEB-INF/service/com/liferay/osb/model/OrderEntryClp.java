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
import com.liferay.osb.service.OrderEntryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class OrderEntryClp extends BaseModelImpl<OrderEntry>
	implements OrderEntry {
	public OrderEntryClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return OrderEntry.class;
	}

	@Override
	public String getModelClassName() {
		return OrderEntry.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _orderEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOrderEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _orderEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("orderEntryId", getOrderEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedUserId", getModifiedUserId());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("purchaseOrderKey", getPurchaseOrderKey());
		attributes.put("startDate", getStartDate());
		attributes.put("prorated", getProrated());
		attributes.put("actualStartDate", getActualStartDate());
		attributes.put("renewCount", getRenewCount());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("statusMessage", getStatusMessage());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long orderEntryId = (Long)attributes.get("orderEntryId");

		if (orderEntryId != null) {
			setOrderEntryId(orderEntryId);
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

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		String purchaseOrderKey = (String)attributes.get("purchaseOrderKey");

		if (purchaseOrderKey != null) {
			setPurchaseOrderKey(purchaseOrderKey);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Boolean prorated = (Boolean)attributes.get("prorated");

		if (prorated != null) {
			setProrated(prorated);
		}

		Date actualStartDate = (Date)attributes.get("actualStartDate");

		if (actualStartDate != null) {
			setActualStartDate(actualStartDate);
		}

		Integer renewCount = (Integer)attributes.get("renewCount");

		if (renewCount != null) {
			setRenewCount(renewCount);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		String statusMessage = (String)attributes.get("statusMessage");

		if (statusMessage != null) {
			setStatusMessage(statusMessage);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_orderEntryRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getOrderEntryId() {
		return _orderEntryId;
	}

	@Override
	public void setOrderEntryId(long orderEntryId) {
		_orderEntryId = orderEntryId;

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setOrderEntryId", long.class);

				method.invoke(_orderEntryRemoteModel, orderEntryId);
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

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_orderEntryRemoteModel, userId);
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

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_orderEntryRemoteModel, userName);
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

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_orderEntryRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getModifiedUserId() {
		return _modifiedUserId;
	}

	@Override
	public void setModifiedUserId(long modifiedUserId) {
		_modifiedUserId = modifiedUserId;

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedUserId", long.class);

				method.invoke(_orderEntryRemoteModel, modifiedUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getModifiedUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getModifiedUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setModifiedUserUuid(String modifiedUserUuid) {
	}

	@Override
	public String getModifiedUserName() {
		return _modifiedUserName;
	}

	@Override
	public void setModifiedUserName(String modifiedUserName) {
		_modifiedUserName = modifiedUserName;

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedUserName",
						String.class);

				method.invoke(_orderEntryRemoteModel, modifiedUserName);
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

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_orderEntryRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAccountEntryId() {
		return _accountEntryId;
	}

	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryId", long.class);

				method.invoke(_orderEntryRemoteModel, accountEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPurchaseOrderKey() {
		return _purchaseOrderKey;
	}

	@Override
	public void setPurchaseOrderKey(String purchaseOrderKey) {
		_purchaseOrderKey = purchaseOrderKey;

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPurchaseOrderKey",
						String.class);

				method.invoke(_orderEntryRemoteModel, purchaseOrderKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStartDate", Date.class);

				method.invoke(_orderEntryRemoteModel, startDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getProrated() {
		return _prorated;
	}

	@Override
	public boolean isProrated() {
		return _prorated;
	}

	@Override
	public void setProrated(boolean prorated) {
		_prorated = prorated;

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setProrated", boolean.class);

				method.invoke(_orderEntryRemoteModel, prorated);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getActualStartDate() {
		return _actualStartDate;
	}

	@Override
	public void setActualStartDate(Date actualStartDate) {
		_actualStartDate = actualStartDate;

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setActualStartDate", Date.class);

				method.invoke(_orderEntryRemoteModel, actualStartDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getRenewCount() {
		return _renewCount;
	}

	@Override
	public void setRenewCount(int renewCount) {
		_renewCount = renewCount;

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setRenewCount", int.class);

				method.invoke(_orderEntryRemoteModel, renewCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_orderEntryRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserId", long.class);

				method.invoke(_orderEntryRemoteModel, statusByUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getStatusByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getStatusByUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@Override
	public String getStatusByUserName() {
		return _statusByUserName;
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserName",
						String.class);

				method.invoke(_orderEntryRemoteModel, statusByUserName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusDate", Date.class);

				method.invoke(_orderEntryRemoteModel, statusDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getStatusMessage() {
		return _statusMessage;
	}

	@Override
	public void setStatusMessage(String statusMessage) {
		_statusMessage = statusMessage;

		if (_orderEntryRemoteModel != null) {
			try {
				Class<?> clazz = _orderEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusMessage", String.class);

				method.invoke(_orderEntryRemoteModel, statusMessage);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public AccountEntry getAccountEntry() {
		try {
			String methodName = "getAccountEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			AccountEntry returnObj = (AccountEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.List<OfferingEntry> getOfferingEntries() {
		try {
			String methodName = "getOfferingEntries";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<OfferingEntry> returnObj = (java.util.List<OfferingEntry>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getSalesforceOpportunityKey() {
		try {
			String methodName = "getSalesforceOpportunityKey";

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

	@Override
	public void setOfferingEntries(
		java.util.List<OfferingEntry> offeringEntries) {
		try {
			String methodName = "setOfferingEntries";

			Class<?>[] parameterTypes = new Class<?>[] { java.util.List.class };

			Object[] parameterValues = new Object[] { offeringEntries };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public BaseModel<?> getOrderEntryRemoteModel() {
		return _orderEntryRemoteModel;
	}

	public void setOrderEntryRemoteModel(BaseModel<?> orderEntryRemoteModel) {
		_orderEntryRemoteModel = orderEntryRemoteModel;
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

		Class<?> remoteModelClass = _orderEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_orderEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			OrderEntryLocalServiceUtil.addOrderEntry(this);
		}
		else {
			OrderEntryLocalServiceUtil.updateOrderEntry(this);
		}
	}

	@Override
	public OrderEntry toEscapedModel() {
		return (OrderEntry)ProxyUtil.newProxyInstance(OrderEntry.class.getClassLoader(),
			new Class[] { OrderEntry.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		OrderEntryClp clone = new OrderEntryClp();

		clone.setUuid(getUuid());
		clone.setOrderEntryId(getOrderEntryId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedUserId(getModifiedUserId());
		clone.setModifiedUserName(getModifiedUserName());
		clone.setModifiedDate(getModifiedDate());
		clone.setAccountEntryId(getAccountEntryId());
		clone.setPurchaseOrderKey(getPurchaseOrderKey());
		clone.setStartDate(getStartDate());
		clone.setProrated(getProrated());
		clone.setActualStartDate(getActualStartDate());
		clone.setRenewCount(getRenewCount());
		clone.setStatus(getStatus());
		clone.setStatusByUserId(getStatusByUserId());
		clone.setStatusByUserName(getStatusByUserName());
		clone.setStatusDate(getStatusDate());
		clone.setStatusMessage(getStatusMessage());

		return clone;
	}

	@Override
	public int compareTo(OrderEntry orderEntry) {
		long primaryKey = orderEntry.getPrimaryKey();

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

		if (!(obj instanceof OrderEntryClp)) {
			return false;
		}

		OrderEntryClp orderEntry = (OrderEntryClp)obj;

		long primaryKey = orderEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(39);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", orderEntryId=");
		sb.append(getOrderEntryId());
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
		sb.append(", accountEntryId=");
		sb.append(getAccountEntryId());
		sb.append(", purchaseOrderKey=");
		sb.append(getPurchaseOrderKey());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", prorated=");
		sb.append(getProrated());
		sb.append(", actualStartDate=");
		sb.append(getActualStartDate());
		sb.append(", renewCount=");
		sb.append(getRenewCount());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusByUserId=");
		sb.append(getStatusByUserId());
		sb.append(", statusByUserName=");
		sb.append(getStatusByUserName());
		sb.append(", statusDate=");
		sb.append(getStatusDate());
		sb.append(", statusMessage=");
		sb.append(getStatusMessage());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(61);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.OrderEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>orderEntryId</column-name><column-value><![CDATA[");
		sb.append(getOrderEntryId());
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
			"<column><column-name>accountEntryId</column-name><column-value><![CDATA[");
		sb.append(getAccountEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>purchaseOrderKey</column-name><column-value><![CDATA[");
		sb.append(getPurchaseOrderKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>prorated</column-name><column-value><![CDATA[");
		sb.append(getProrated());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualStartDate</column-name><column-value><![CDATA[");
		sb.append(getActualStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>renewCount</column-name><column-value><![CDATA[");
		sb.append(getRenewCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusDate</column-name><column-value><![CDATA[");
		sb.append(getStatusDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusMessage</column-name><column-value><![CDATA[");
		sb.append(getStatusMessage());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _orderEntryId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _modifiedUserId;
	private String _modifiedUserName;
	private Date _modifiedDate;
	private long _accountEntryId;
	private String _purchaseOrderKey;
	private Date _startDate;
	private boolean _prorated;
	private Date _actualStartDate;
	private int _renewCount;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _statusMessage;
	private BaseModel<?> _orderEntryRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}