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
import com.liferay.osb.service.TicketWorkerLocalServiceUtil;

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
public class TicketWorkerClp extends BaseModelImpl<TicketWorker>
	implements TicketWorker {
	public TicketWorkerClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return TicketWorker.class;
	}

	@Override
	public String getModelClassName() {
		return TicketWorker.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _ticketWorkerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTicketWorkerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketWorkerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketWorkerId", getTicketWorkerId());
		attributes.put("userId", getUserId());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("sourceClassNameId", getSourceClassNameId());
		attributes.put("sourceClassPK", getSourceClassPK());
		attributes.put("role", getRole());
		attributes.put("primary", getPrimary());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketWorkerId = (Long)attributes.get("ticketWorkerId");

		if (ticketWorkerId != null) {
			setTicketWorkerId(ticketWorkerId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		Long sourceClassNameId = (Long)attributes.get("sourceClassNameId");

		if (sourceClassNameId != null) {
			setSourceClassNameId(sourceClassNameId);
		}

		Long sourceClassPK = (Long)attributes.get("sourceClassPK");

		if (sourceClassPK != null) {
			setSourceClassPK(sourceClassPK);
		}

		Integer role = (Integer)attributes.get("role");

		if (role != null) {
			setRole(role);
		}

		Boolean primary = (Boolean)attributes.get("primary");

		if (primary != null) {
			setPrimary(primary);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getTicketWorkerId() {
		return _ticketWorkerId;
	}

	@Override
	public void setTicketWorkerId(long ticketWorkerId) {
		_ticketWorkerId = ticketWorkerId;

		if (_ticketWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _ticketWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketWorkerId", long.class);

				method.invoke(_ticketWorkerRemoteModel, ticketWorkerId);
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

		if (_ticketWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _ticketWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_ticketWorkerRemoteModel, userId);
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
	public long getTicketEntryId() {
		return _ticketEntryId;
	}

	@Override
	public void setTicketEntryId(long ticketEntryId) {
		_ticketEntryId = ticketEntryId;

		if (_ticketWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _ticketWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketEntryId", long.class);

				method.invoke(_ticketWorkerRemoteModel, ticketEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSourceClassNameId() {
		return _sourceClassNameId;
	}

	@Override
	public void setSourceClassNameId(long sourceClassNameId) {
		_sourceClassNameId = sourceClassNameId;

		if (_ticketWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _ticketWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setSourceClassNameId",
						long.class);

				method.invoke(_ticketWorkerRemoteModel, sourceClassNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSourceClassPK() {
		return _sourceClassPK;
	}

	@Override
	public void setSourceClassPK(long sourceClassPK) {
		_sourceClassPK = sourceClassPK;

		if (_ticketWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _ticketWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setSourceClassPK", long.class);

				method.invoke(_ticketWorkerRemoteModel, sourceClassPK);
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

		if (_ticketWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _ticketWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setRole", int.class);

				method.invoke(_ticketWorkerRemoteModel, role);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getPrimary() {
		return _primary;
	}

	@Override
	public boolean isPrimary() {
		return _primary;
	}

	@Override
	public void setPrimary(boolean primary) {
		_primary = primary;

		if (_ticketWorkerRemoteModel != null) {
			try {
				Class<?> clazz = _ticketWorkerRemoteModel.getClass();

				Method method = clazz.getMethod("setPrimary", boolean.class);

				method.invoke(_ticketWorkerRemoteModel, primary);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
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
	public TicketEntry getTicketEntry() {
		try {
			String methodName = "getTicketEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			TicketEntry returnObj = (TicketEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getTicketWorkerRemoteModel() {
		return _ticketWorkerRemoteModel;
	}

	public void setTicketWorkerRemoteModel(BaseModel<?> ticketWorkerRemoteModel) {
		_ticketWorkerRemoteModel = ticketWorkerRemoteModel;
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

		Class<?> remoteModelClass = _ticketWorkerRemoteModel.getClass();

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

		Object returnValue = method.invoke(_ticketWorkerRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			TicketWorkerLocalServiceUtil.addTicketWorker(this);
		}
		else {
			TicketWorkerLocalServiceUtil.updateTicketWorker(this);
		}
	}

	@Override
	public TicketWorker toEscapedModel() {
		return (TicketWorker)ProxyUtil.newProxyInstance(TicketWorker.class.getClassLoader(),
			new Class[] { TicketWorker.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		TicketWorkerClp clone = new TicketWorkerClp();

		clone.setTicketWorkerId(getTicketWorkerId());
		clone.setUserId(getUserId());
		clone.setTicketEntryId(getTicketEntryId());
		clone.setSourceClassNameId(getSourceClassNameId());
		clone.setSourceClassPK(getSourceClassPK());
		clone.setRole(getRole());
		clone.setPrimary(getPrimary());

		return clone;
	}

	@Override
	public int compareTo(TicketWorker ticketWorker) {
		long primaryKey = ticketWorker.getPrimaryKey();

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

		if (!(obj instanceof TicketWorkerClp)) {
			return false;
		}

		TicketWorkerClp ticketWorker = (TicketWorkerClp)obj;

		long primaryKey = ticketWorker.getPrimaryKey();

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
		StringBundler sb = new StringBundler(15);

		sb.append("{ticketWorkerId=");
		sb.append(getTicketWorkerId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", ticketEntryId=");
		sb.append(getTicketEntryId());
		sb.append(", sourceClassNameId=");
		sb.append(getSourceClassNameId());
		sb.append(", sourceClassPK=");
		sb.append(getSourceClassPK());
		sb.append(", role=");
		sb.append(getRole());
		sb.append(", primary=");
		sb.append(getPrimary());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TicketWorker");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>ticketWorkerId</column-name><column-value><![CDATA[");
		sb.append(getTicketWorkerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ticketEntryId</column-name><column-value><![CDATA[");
		sb.append(getTicketEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sourceClassNameId</column-name><column-value><![CDATA[");
		sb.append(getSourceClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sourceClassPK</column-name><column-value><![CDATA[");
		sb.append(getSourceClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>role</column-name><column-value><![CDATA[");
		sb.append(getRole());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>primary</column-name><column-value><![CDATA[");
		sb.append(getPrimary());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _ticketWorkerId;
	private long _userId;
	private long _ticketEntryId;
	private long _sourceClassNameId;
	private long _sourceClassPK;
	private int _role;
	private boolean _primary;
	private BaseModel<?> _ticketWorkerRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}