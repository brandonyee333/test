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
import com.liferay.osb.service.SupportWorkerLocalServiceUtil;

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

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class SupportWorkerClp extends BaseModelImpl<SupportWorker>
	implements SupportWorker {
	public SupportWorkerClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SupportWorker.class;
	}

	@Override
	public String getModelClassName() {
		return SupportWorker.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _supportWorkerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSupportWorkerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _supportWorkerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportWorkerId", getSupportWorkerId());
		attributes.put("userId", getUserId());
		attributes.put("supportTeamId", getSupportTeamId());
		attributes.put("supportLaborId", getSupportLaborId());
		attributes.put("autoAssign", getAutoAssign());
		attributes.put("assignedWork", getAssignedWork());
		attributes.put("maxWork", getMaxWork());
		attributes.put("escalationLevel", getEscalationLevel());
		attributes.put("role", getRole());
		attributes.put("escalationLevel2Role", getEscalationLevel2Role());
		attributes.put("notifications", getNotifications());
		attributes.put("clockedIn", getClockedIn());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportWorkerId = (Long)attributes.get("supportWorkerId");

		if (supportWorkerId != null) {
			setSupportWorkerId(supportWorkerId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long supportTeamId = (Long)attributes.get("supportTeamId");

		if (supportTeamId != null) {
			setSupportTeamId(supportTeamId);
		}

		Long supportLaborId = (Long)attributes.get("supportLaborId");

		if (supportLaborId != null) {
			setSupportLaborId(supportLaborId);
		}

		Boolean autoAssign = (Boolean)attributes.get("autoAssign");

		if (autoAssign != null) {
			setAutoAssign(autoAssign);
		}

		Double assignedWork = (Double)attributes.get("assignedWork");

		if (assignedWork != null) {
			setAssignedWork(assignedWork);
		}

		Double maxWork = (Double)attributes.get("maxWork");

		if (maxWork != null) {
			setMaxWork(maxWork);
		}

		Integer escalationLevel = (Integer)attributes.get("escalationLevel");

		if (escalationLevel != null) {
			setEscalationLevel(escalationLevel);
		}

		Integer role = (Integer)attributes.get("role");

		if (role != null) {
			setRole(role);
		}

		Integer escalationLevel2Role = (Integer)attributes.get(
				"escalationLevel2Role");

		if (escalationLevel2Role != null) {
			setEscalationLevel2Role(escalationLevel2Role);
		}

		Integer notifications = (Integer)attributes.get("notifications");

		if (notifications != null) {
			setNotifications(notifications);
		}

		Boolean clockedIn = (Boolean)attributes.get("clockedIn");

		if (clockedIn != null) {
			setClockedIn(clockedIn);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getSupportWorkerId() {
		return _supportWorkerId;
	}

	@Override
	public void setSupportWorkerId(long supportWorkerId) {
		_supportWorkerId = supportWorkerId;

		if (_supportWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportWorkerId", long.class);

				method.invoke(_supportWorkerRemoteModel, supportWorkerId);
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

		if (_supportWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_supportWorkerRemoteModel, userId);
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
	public long getSupportTeamId() {
		return _supportTeamId;
	}

	@Override
	public void setSupportTeamId(long supportTeamId) {
		_supportTeamId = supportTeamId;

		if (_supportWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportTeamId", long.class);

				method.invoke(_supportWorkerRemoteModel, supportTeamId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSupportLaborId() {
		return _supportLaborId;
	}

	@Override
	public void setSupportLaborId(long supportLaborId) {
		_supportLaborId = supportLaborId;

		if (_supportWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportLaborId", long.class);

				method.invoke(_supportWorkerRemoteModel, supportLaborId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getAutoAssign() {
		return _autoAssign;
	}

	@Override
	public boolean isAutoAssign() {
		return _autoAssign;
	}

	@Override
	public void setAutoAssign(boolean autoAssign) {
		_autoAssign = autoAssign;

		if (_supportWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setAutoAssign", boolean.class);

				method.invoke(_supportWorkerRemoteModel, autoAssign);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public double getAssignedWork() {
		return _assignedWork;
	}

	@Override
	public void setAssignedWork(double assignedWork) {
		_assignedWork = assignedWork;

		if (_supportWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setAssignedWork", double.class);

				method.invoke(_supportWorkerRemoteModel, assignedWork);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public double getMaxWork() {
		return _maxWork;
	}

	@Override
	public void setMaxWork(double maxWork) {
		_maxWork = maxWork;

		if (_supportWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setMaxWork", double.class);

				method.invoke(_supportWorkerRemoteModel, maxWork);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getEscalationLevel() {
		return _escalationLevel;
	}

	@Override
	public void setEscalationLevel(int escalationLevel) {
		_escalationLevel = escalationLevel;

		if (_supportWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setEscalationLevel", int.class);

				method.invoke(_supportWorkerRemoteModel, escalationLevel);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getRole() {
		return _role;
	}

	@Override
	public void setRole(int role) {
		_role = role;

		if (_supportWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setRole", int.class);

				method.invoke(_supportWorkerRemoteModel, role);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getEscalationLevel2Role() {
		return _escalationLevel2Role;
	}

	@Override
	public void setEscalationLevel2Role(int escalationLevel2Role) {
		_escalationLevel2Role = escalationLevel2Role;

		if (_supportWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setEscalationLevel2Role",
						int.class);

				method.invoke(_supportWorkerRemoteModel, escalationLevel2Role);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getNotifications() {
		return _notifications;
	}

	@Override
	public void setNotifications(int notifications) {
		_notifications = notifications;

		if (_supportWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setNotifications", int.class);

				method.invoke(_supportWorkerRemoteModel, notifications);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getClockedIn() {
		return _clockedIn;
	}

	@Override
	public boolean isClockedIn() {
		return _clockedIn;
	}

	@Override
	public void setClockedIn(boolean clockedIn) {
		_clockedIn = clockedIn;

		if (_supportWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _supportWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setClockedIn", boolean.class);

				method.invoke(_supportWorkerRemoteModel, clockedIn);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public java.util.List<java.lang.Integer> getAccountTiers() {
		try {
			String methodName = "getAccountTiers";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<java.lang.Integer> returnObj = (java.util.List<java.lang.Integer>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.List<java.lang.Integer> getComponents() {
		try {
			String methodName = "getComponents";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<java.lang.Integer> returnObj = (java.util.List<java.lang.Integer>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getNotificationsLabel() {
		try {
			String methodName = "getNotificationsLabel";

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
	public java.lang.String getRoleLabel() {
		try {
			String methodName = "getRoleLabel";

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
	public java.util.List<java.lang.Integer> getSeverities() {
		try {
			String methodName = "getSeverities";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<java.lang.Integer> returnObj = (java.util.List<java.lang.Integer>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public SupportLabor getSupportLabor() {
		try {
			String methodName = "getSupportLabor";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			SupportLabor returnObj = (SupportLabor)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public SupportTeam getSupportTeam() {
		try {
			String methodName = "getSupportTeam";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			SupportTeam returnObj = (SupportTeam)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.Long getTimeUntilClose() {
		try {
			String methodName = "getTimeUntilClose";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.Long returnObj = (java.lang.Long)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.Long getTimeUntilOpen() {
		try {
			String methodName = "getTimeUntilOpen";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.Long returnObj = (java.lang.Long)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public boolean isActive() {
		try {
			String methodName = "isActive";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public boolean isAvailable() {
		try {
			String methodName = "isAvailable";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getSupportWorkerRemoteModel() {
		return _supportWorkerRemoteModel;
	}

	public void setSupportWorkerRemoteModel(
		BaseModel<?> supportWorkerRemoteModel) {
		_supportWorkerRemoteModel = supportWorkerRemoteModel;
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

		Class<?> remoteModelClass = _supportWorkerRemoteModel.getClass();

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

		Object returnValue = method.invoke(_supportWorkerRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			SupportWorkerLocalServiceUtil.addSupportWorker(this);
		}
		else {
			SupportWorkerLocalServiceUtil.updateSupportWorker(this);
		}
	}

	@Override
	public SupportWorker toEscapedModel() {
		return (SupportWorker)ProxyUtil.newProxyInstance(SupportWorker.class.getClassLoader(),
			new Class[] { SupportWorker.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SupportWorkerClp clone = new SupportWorkerClp();

		clone.setSupportWorkerId(getSupportWorkerId());
		clone.setUserId(getUserId());
		clone.setSupportTeamId(getSupportTeamId());
		clone.setSupportLaborId(getSupportLaborId());
		clone.setAutoAssign(getAutoAssign());
		clone.setAssignedWork(getAssignedWork());
		clone.setMaxWork(getMaxWork());
		clone.setEscalationLevel(getEscalationLevel());
		clone.setRole(getRole());
		clone.setEscalationLevel2Role(getEscalationLevel2Role());
		clone.setNotifications(getNotifications());
		clone.setClockedIn(getClockedIn());

		return clone;
	}

	@Override
	public int compareTo(SupportWorker supportWorker) {
		long primaryKey = supportWorker.getPrimaryKey();

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

		if (!(obj instanceof SupportWorkerClp)) {
			return false;
		}

		SupportWorkerClp supportWorker = (SupportWorkerClp)obj;

		long primaryKey = supportWorker.getPrimaryKey();

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

		sb.append("{supportWorkerId=");
		sb.append(getSupportWorkerId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", supportTeamId=");
		sb.append(getSupportTeamId());
		sb.append(", supportLaborId=");
		sb.append(getSupportLaborId());
		sb.append(", autoAssign=");
		sb.append(getAutoAssign());
		sb.append(", assignedWork=");
		sb.append(getAssignedWork());
		sb.append(", maxWork=");
		sb.append(getMaxWork());
		sb.append(", escalationLevel=");
		sb.append(getEscalationLevel());
		sb.append(", role=");
		sb.append(getRole());
		sb.append(", escalationLevel2Role=");
		sb.append(getEscalationLevel2Role());
		sb.append(", notifications=");
		sb.append(getNotifications());
		sb.append(", clockedIn=");
		sb.append(getClockedIn());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.SupportWorker");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>supportWorkerId</column-name><column-value><![CDATA[");
		sb.append(getSupportWorkerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportTeamId</column-name><column-value><![CDATA[");
		sb.append(getSupportTeamId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportLaborId</column-name><column-value><![CDATA[");
		sb.append(getSupportLaborId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>autoAssign</column-name><column-value><![CDATA[");
		sb.append(getAutoAssign());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assignedWork</column-name><column-value><![CDATA[");
		sb.append(getAssignedWork());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>maxWork</column-name><column-value><![CDATA[");
		sb.append(getMaxWork());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>escalationLevel</column-name><column-value><![CDATA[");
		sb.append(getEscalationLevel());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>role</column-name><column-value><![CDATA[");
		sb.append(getRole());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>escalationLevel2Role</column-name><column-value><![CDATA[");
		sb.append(getEscalationLevel2Role());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notifications</column-name><column-value><![CDATA[");
		sb.append(getNotifications());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>clockedIn</column-name><column-value><![CDATA[");
		sb.append(getClockedIn());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _supportWorkerId;
	private long _userId;
	private long _supportTeamId;
	private long _supportLaborId;
	private boolean _autoAssign;
	private double _assignedWork;
	private double _maxWork;
	private int _escalationLevel;
	private int _role;
	private int _escalationLevel2Role;
	private int _notifications;
	private boolean _clockedIn;
	private BaseModel<?> _supportWorkerRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}