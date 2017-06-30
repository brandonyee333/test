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
import com.liferay.osb.service.SupportTeamLocalServiceUtil;

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
public class SupportTeamClp extends BaseModelImpl<SupportTeam>
	implements SupportTeam {
	public SupportTeamClp() {
	}

	public Class<?> getModelClass() {
		return SupportTeam.class;
	}

	public String getModelClassName() {
		return SupportTeam.class.getName();
	}

	public long getPrimaryKey() {
		return _supportTeamId;
	}

	public void setPrimaryKey(long primaryKey) {
		setSupportTeamId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_supportTeamId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportTeamId", getSupportTeamId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentSupportTeamId", getParentSupportTeamId());
		attributes.put("supportLaborId", getSupportLaborId());
		attributes.put("locationSupportRegionId", getLocationSupportRegionId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("type", getType());
		attributes.put("assignedWork", getAssignedWork());
		attributes.put("maxWork", getMaxWork());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportTeamId = (Long)attributes.get("supportTeamId");

		if (supportTeamId != null) {
			setSupportTeamId(supportTeamId);
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

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long parentSupportTeamId = (Long)attributes.get("parentSupportTeamId");

		if (parentSupportTeamId != null) {
			setParentSupportTeamId(parentSupportTeamId);
		}

		Long supportLaborId = (Long)attributes.get("supportLaborId");

		if (supportLaborId != null) {
			setSupportLaborId(supportLaborId);
		}

		Long locationSupportRegionId = (Long)attributes.get(
				"locationSupportRegionId");

		if (locationSupportRegionId != null) {
			setLocationSupportRegionId(locationSupportRegionId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Double assignedWork = (Double)attributes.get("assignedWork");

		if (assignedWork != null) {
			setAssignedWork(assignedWork);
		}

		Double maxWork = (Double)attributes.get("maxWork");

		if (maxWork != null) {
			setMaxWork(maxWork);
		}
	}

	public long getSupportTeamId() {
		return _supportTeamId;
	}

	public void setSupportTeamId(long supportTeamId) {
		_supportTeamId = supportTeamId;

		if (_supportTeamRemoteModel != null) {
			try {
				Class<?> clazz = _supportTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportTeamId", long.class);

				method.invoke(_supportTeamRemoteModel, supportTeamId);
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

		if (_supportTeamRemoteModel != null) {
			try {
				Class<?> clazz = _supportTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_supportTeamRemoteModel, userId);
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

		if (_supportTeamRemoteModel != null) {
			try {
				Class<?> clazz = _supportTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_supportTeamRemoteModel, userName);
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

		if (_supportTeamRemoteModel != null) {
			try {
				Class<?> clazz = _supportTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_supportTeamRemoteModel, createDate);
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

		if (_supportTeamRemoteModel != null) {
			try {
				Class<?> clazz = _supportTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_supportTeamRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getParentSupportTeamId() {
		return _parentSupportTeamId;
	}

	public void setParentSupportTeamId(long parentSupportTeamId) {
		_parentSupportTeamId = parentSupportTeamId;

		if (_supportTeamRemoteModel != null) {
			try {
				Class<?> clazz = _supportTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setParentSupportTeamId",
						long.class);

				method.invoke(_supportTeamRemoteModel, parentSupportTeamId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getSupportLaborId() {
		return _supportLaborId;
	}

	public void setSupportLaborId(long supportLaborId) {
		_supportLaborId = supportLaborId;

		if (_supportTeamRemoteModel != null) {
			try {
				Class<?> clazz = _supportTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportLaborId", long.class);

				method.invoke(_supportTeamRemoteModel, supportLaborId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getLocationSupportRegionId() {
		return _locationSupportRegionId;
	}

	public void setLocationSupportRegionId(long locationSupportRegionId) {
		_locationSupportRegionId = locationSupportRegionId;

		if (_supportTeamRemoteModel != null) {
			try {
				Class<?> clazz = _supportTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setLocationSupportRegionId",
						long.class);

				method.invoke(_supportTeamRemoteModel, locationSupportRegionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;

		if (_supportTeamRemoteModel != null) {
			try {
				Class<?> clazz = _supportTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_supportTeamRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;

		if (_supportTeamRemoteModel != null) {
			try {
				Class<?> clazz = _supportTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_supportTeamRemoteModel, description);
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

		if (_supportTeamRemoteModel != null) {
			try {
				Class<?> clazz = _supportTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_supportTeamRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public double getAssignedWork() {
		return _assignedWork;
	}

	public void setAssignedWork(double assignedWork) {
		_assignedWork = assignedWork;

		if (_supportTeamRemoteModel != null) {
			try {
				Class<?> clazz = _supportTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setAssignedWork", double.class);

				method.invoke(_supportTeamRemoteModel, assignedWork);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public double getMaxWork() {
		return _maxWork;
	}

	public void setMaxWork(double maxWork) {
		_maxWork = maxWork;

		if (_supportTeamRemoteModel != null) {
			try {
				Class<?> clazz = _supportTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setMaxWork", double.class);

				method.invoke(_supportTeamRemoteModel, maxWork);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.util.List<java.lang.String> getLanguageIds() {
		try {
			String methodName = "getLanguageIds";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<java.lang.String> returnObj = (java.util.List<java.lang.String>)invokeOnRemoteModel(methodName,
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

	public com.liferay.osb.model.SupportTeam getParentSupportTeam() {
		try {
			String methodName = "getParentSupportTeam";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.SupportTeam returnObj = (com.liferay.osb.model.SupportTeam)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions() {
		try {
			String methodName = "getSupportRegions";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.osb.model.SupportRegion> returnObj = (java.util.List<com.liferay.osb.model.SupportRegion>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.List<com.liferay.osb.model.SupportTeam> getChildSupportTeams() {
		try {
			String methodName = "getChildSupportTeams";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.osb.model.SupportTeam> returnObj = (java.util.List<com.liferay.osb.model.SupportTeam>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.List<com.liferay.osb.model.SupportTeam> getChildSupportTeams(
		boolean recursive) {
		try {
			String methodName = "getChildSupportTeams";

			Class<?>[] parameterTypes = new Class<?>[] { boolean.class };

			Object[] parameterValues = new Object[] { recursive };

			java.util.List<com.liferay.osb.model.SupportTeam> returnObj = (java.util.List<com.liferay.osb.model.SupportTeam>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getSupportTeamRemoteModel() {
		return _supportTeamRemoteModel;
	}

	public void setSupportTeamRemoteModel(BaseModel<?> supportTeamRemoteModel) {
		_supportTeamRemoteModel = supportTeamRemoteModel;
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

		Class<?> remoteModelClass = _supportTeamRemoteModel.getClass();

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

		Object returnValue = method.invoke(_supportTeamRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			SupportTeamLocalServiceUtil.addSupportTeam(this);
		}
		else {
			SupportTeamLocalServiceUtil.updateSupportTeam(this);
		}
	}

	@Override
	public SupportTeam toEscapedModel() {
		return (SupportTeam)ProxyUtil.newProxyInstance(SupportTeam.class.getClassLoader(),
			new Class[] { SupportTeam.class }, new AutoEscapeBeanHandler(this));
	}

	public SupportTeam toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		SupportTeamClp clone = new SupportTeamClp();

		clone.setSupportTeamId(getSupportTeamId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setParentSupportTeamId(getParentSupportTeamId());
		clone.setSupportLaborId(getSupportLaborId());
		clone.setLocationSupportRegionId(getLocationSupportRegionId());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setType(getType());
		clone.setAssignedWork(getAssignedWork());
		clone.setMaxWork(getMaxWork());

		return clone;
	}

	public int compareTo(SupportTeam supportTeam) {
		int value = 0;

		value = getName().toLowerCase()
					.compareTo(supportTeam.getName().toLowerCase());

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

		if (!(obj instanceof SupportTeamClp)) {
			return false;
		}

		SupportTeamClp supportTeam = (SupportTeamClp)obj;

		long primaryKey = supportTeam.getPrimaryKey();

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

		sb.append("{supportTeamId=");
		sb.append(getSupportTeamId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", parentSupportTeamId=");
		sb.append(getParentSupportTeamId());
		sb.append(", supportLaborId=");
		sb.append(getSupportLaborId());
		sb.append(", locationSupportRegionId=");
		sb.append(getLocationSupportRegionId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", assignedWork=");
		sb.append(getAssignedWork());
		sb.append(", maxWork=");
		sb.append(getMaxWork());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.SupportTeam");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>supportTeamId</column-name><column-value><![CDATA[");
		sb.append(getSupportTeamId());
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
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentSupportTeamId</column-name><column-value><![CDATA[");
		sb.append(getParentSupportTeamId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportLaborId</column-name><column-value><![CDATA[");
		sb.append(getSupportLaborId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>locationSupportRegionId</column-name><column-value><![CDATA[");
		sb.append(getLocationSupportRegionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assignedWork</column-name><column-value><![CDATA[");
		sb.append(getAssignedWork());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>maxWork</column-name><column-value><![CDATA[");
		sb.append(getMaxWork());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _supportTeamId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _parentSupportTeamId;
	private long _supportLaborId;
	private long _locationSupportRegionId;
	private String _name;
	private String _description;
	private int _type;
	private double _assignedWork;
	private double _maxWork;
	private BaseModel<?> _supportTeamRemoteModel;
}