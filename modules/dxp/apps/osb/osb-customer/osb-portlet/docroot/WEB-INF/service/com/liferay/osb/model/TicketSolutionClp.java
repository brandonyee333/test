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
import com.liferay.osb.service.TicketSolutionLocalServiceUtil;

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
public class TicketSolutionClp extends BaseModelImpl<TicketSolution>
	implements TicketSolution {
	public TicketSolutionClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return TicketSolution.class;
	}

	@Override
	public String getModelClassName() {
		return TicketSolution.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _ticketSolutionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTicketSolutionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketSolutionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketSolutionId", getTicketSolutionId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("summary", getSummary());
		attributes.put("useCustomerSummary", getUseCustomerSummary());
		attributes.put("issueType", getIssueType());
		attributes.put("solution", getSolution());
		attributes.put("type", getType());
		attributes.put("customerSpecific", getCustomerSpecific());
		attributes.put("environmentSpecific", getEnvironmentSpecific());
		attributes.put("versionSpecific", getVersionSpecific());
		attributes.put("reviewForKB", getReviewForKB());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("statusMessage", getStatusMessage());
		attributes.put("statusReason", getStatusReason());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketSolutionId = (Long)attributes.get("ticketSolutionId");

		if (ticketSolutionId != null) {
			setTicketSolutionId(ticketSolutionId);
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

		String summary = (String)attributes.get("summary");

		if (summary != null) {
			setSummary(summary);
		}

		Boolean useCustomerSummary = (Boolean)attributes.get(
				"useCustomerSummary");

		if (useCustomerSummary != null) {
			setUseCustomerSummary(useCustomerSummary);
		}

		Integer issueType = (Integer)attributes.get("issueType");

		if (issueType != null) {
			setIssueType(issueType);
		}

		String solution = (String)attributes.get("solution");

		if (solution != null) {
			setSolution(solution);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Boolean customerSpecific = (Boolean)attributes.get("customerSpecific");

		if (customerSpecific != null) {
			setCustomerSpecific(customerSpecific);
		}

		Boolean environmentSpecific = (Boolean)attributes.get(
				"environmentSpecific");

		if (environmentSpecific != null) {
			setEnvironmentSpecific(environmentSpecific);
		}

		Boolean versionSpecific = (Boolean)attributes.get("versionSpecific");

		if (versionSpecific != null) {
			setVersionSpecific(versionSpecific);
		}

		Boolean reviewForKB = (Boolean)attributes.get("reviewForKB");

		if (reviewForKB != null) {
			setReviewForKB(reviewForKB);
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

		Integer statusReason = (Integer)attributes.get("statusReason");

		if (statusReason != null) {
			setStatusReason(statusReason);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getTicketSolutionId() {
		return _ticketSolutionId;
	}

	@Override
	public void setTicketSolutionId(long ticketSolutionId) {
		_ticketSolutionId = ticketSolutionId;

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketSolutionId",
						long.class);

				method.invoke(_ticketSolutionRemoteModel, ticketSolutionId);
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

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_ticketSolutionRemoteModel, userId);
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

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_ticketSolutionRemoteModel, userName);
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

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_ticketSolutionRemoteModel, createDate);
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

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketEntryId", long.class);

				method.invoke(_ticketSolutionRemoteModel, ticketEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSummary() {
		return _summary;
	}

	@Override
	public void setSummary(String summary) {
		_summary = summary;

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setSummary", String.class);

				method.invoke(_ticketSolutionRemoteModel, summary);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getUseCustomerSummary() {
		return _useCustomerSummary;
	}

	@Override
	public boolean isUseCustomerSummary() {
		return _useCustomerSummary;
	}

	@Override
	public void setUseCustomerSummary(boolean useCustomerSummary) {
		_useCustomerSummary = useCustomerSummary;

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setUseCustomerSummary",
						boolean.class);

				method.invoke(_ticketSolutionRemoteModel, useCustomerSummary);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getIssueType() {
		return _issueType;
	}

	@Override
	public void setIssueType(int issueType) {
		_issueType = issueType;

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setIssueType", int.class);

				method.invoke(_ticketSolutionRemoteModel, issueType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSolution() {
		return _solution;
	}

	@Override
	public void setSolution(String solution) {
		_solution = solution;

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setSolution", String.class);

				method.invoke(_ticketSolutionRemoteModel, solution);
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

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_ticketSolutionRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getCustomerSpecific() {
		return _customerSpecific;
	}

	@Override
	public boolean isCustomerSpecific() {
		return _customerSpecific;
	}

	@Override
	public void setCustomerSpecific(boolean customerSpecific) {
		_customerSpecific = customerSpecific;

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setCustomerSpecific",
						boolean.class);

				method.invoke(_ticketSolutionRemoteModel, customerSpecific);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getEnvironmentSpecific() {
		return _environmentSpecific;
	}

	@Override
	public boolean isEnvironmentSpecific() {
		return _environmentSpecific;
	}

	@Override
	public void setEnvironmentSpecific(boolean environmentSpecific) {
		_environmentSpecific = environmentSpecific;

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvironmentSpecific",
						boolean.class);

				method.invoke(_ticketSolutionRemoteModel, environmentSpecific);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getVersionSpecific() {
		return _versionSpecific;
	}

	@Override
	public boolean isVersionSpecific() {
		return _versionSpecific;
	}

	@Override
	public void setVersionSpecific(boolean versionSpecific) {
		_versionSpecific = versionSpecific;

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setVersionSpecific",
						boolean.class);

				method.invoke(_ticketSolutionRemoteModel, versionSpecific);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getReviewForKB() {
		return _reviewForKB;
	}

	@Override
	public boolean isReviewForKB() {
		return _reviewForKB;
	}

	@Override
	public void setReviewForKB(boolean reviewForKB) {
		_reviewForKB = reviewForKB;

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setReviewForKB", boolean.class);

				method.invoke(_ticketSolutionRemoteModel, reviewForKB);
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

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_ticketSolutionRemoteModel, status);
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

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserId", long.class);

				method.invoke(_ticketSolutionRemoteModel, statusByUserId);
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

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserName",
						String.class);

				method.invoke(_ticketSolutionRemoteModel, statusByUserName);
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

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusDate", Date.class);

				method.invoke(_ticketSolutionRemoteModel, statusDate);
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

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusMessage", String.class);

				method.invoke(_ticketSolutionRemoteModel, statusMessage);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getStatusReason() {
		return _statusReason;
	}

	@Override
	public void setStatusReason(int statusReason) {
		_statusReason = statusReason;

		if (_ticketSolutionRemoteModel != null) {
			try {
				Class<?> clazz = _ticketSolutionRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusReason", int.class);

				method.invoke(_ticketSolutionRemoteModel, statusReason);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
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

	public BaseModel<?> getTicketSolutionRemoteModel() {
		return _ticketSolutionRemoteModel;
	}

	public void setTicketSolutionRemoteModel(
		BaseModel<?> ticketSolutionRemoteModel) {
		_ticketSolutionRemoteModel = ticketSolutionRemoteModel;
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

		Class<?> remoteModelClass = _ticketSolutionRemoteModel.getClass();

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

		Object returnValue = method.invoke(_ticketSolutionRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			TicketSolutionLocalServiceUtil.addTicketSolution(this);
		}
		else {
			TicketSolutionLocalServiceUtil.updateTicketSolution(this);
		}
	}

	@Override
	public TicketSolution toEscapedModel() {
		return (TicketSolution)ProxyUtil.newProxyInstance(TicketSolution.class.getClassLoader(),
			new Class[] { TicketSolution.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		TicketSolutionClp clone = new TicketSolutionClp();

		clone.setTicketSolutionId(getTicketSolutionId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setTicketEntryId(getTicketEntryId());
		clone.setSummary(getSummary());
		clone.setUseCustomerSummary(getUseCustomerSummary());
		clone.setIssueType(getIssueType());
		clone.setSolution(getSolution());
		clone.setType(getType());
		clone.setCustomerSpecific(getCustomerSpecific());
		clone.setEnvironmentSpecific(getEnvironmentSpecific());
		clone.setVersionSpecific(getVersionSpecific());
		clone.setReviewForKB(getReviewForKB());
		clone.setStatus(getStatus());
		clone.setStatusByUserId(getStatusByUserId());
		clone.setStatusByUserName(getStatusByUserName());
		clone.setStatusDate(getStatusDate());
		clone.setStatusMessage(getStatusMessage());
		clone.setStatusReason(getStatusReason());

		return clone;
	}

	@Override
	public int compareTo(TicketSolution ticketSolution) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				ticketSolution.getCreateDate());

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

		if (!(obj instanceof TicketSolutionClp)) {
			return false;
		}

		TicketSolutionClp ticketSolution = (TicketSolutionClp)obj;

		long primaryKey = ticketSolution.getPrimaryKey();

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
		StringBundler sb = new StringBundler(41);

		sb.append("{ticketSolutionId=");
		sb.append(getTicketSolutionId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", ticketEntryId=");
		sb.append(getTicketEntryId());
		sb.append(", summary=");
		sb.append(getSummary());
		sb.append(", useCustomerSummary=");
		sb.append(getUseCustomerSummary());
		sb.append(", issueType=");
		sb.append(getIssueType());
		sb.append(", solution=");
		sb.append(getSolution());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", customerSpecific=");
		sb.append(getCustomerSpecific());
		sb.append(", environmentSpecific=");
		sb.append(getEnvironmentSpecific());
		sb.append(", versionSpecific=");
		sb.append(getVersionSpecific());
		sb.append(", reviewForKB=");
		sb.append(getReviewForKB());
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
		sb.append(", statusReason=");
		sb.append(getStatusReason());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(64);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TicketSolution");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>ticketSolutionId</column-name><column-value><![CDATA[");
		sb.append(getTicketSolutionId());
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
			"<column><column-name>summary</column-name><column-value><![CDATA[");
		sb.append(getSummary());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>useCustomerSummary</column-name><column-value><![CDATA[");
		sb.append(getUseCustomerSummary());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>issueType</column-name><column-value><![CDATA[");
		sb.append(getIssueType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>solution</column-name><column-value><![CDATA[");
		sb.append(getSolution());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>customerSpecific</column-name><column-value><![CDATA[");
		sb.append(getCustomerSpecific());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>environmentSpecific</column-name><column-value><![CDATA[");
		sb.append(getEnvironmentSpecific());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>versionSpecific</column-name><column-value><![CDATA[");
		sb.append(getVersionSpecific());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reviewForKB</column-name><column-value><![CDATA[");
		sb.append(getReviewForKB());
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
		sb.append(
			"<column><column-name>statusReason</column-name><column-value><![CDATA[");
		sb.append(getStatusReason());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _ticketSolutionId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _ticketEntryId;
	private String _summary;
	private boolean _useCustomerSummary;
	private int _issueType;
	private String _solution;
	private int _type;
	private boolean _customerSpecific;
	private boolean _environmentSpecific;
	private boolean _versionSpecific;
	private boolean _reviewForKB;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _statusMessage;
	private int _statusReason;
	private BaseModel<?> _ticketSolutionRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}