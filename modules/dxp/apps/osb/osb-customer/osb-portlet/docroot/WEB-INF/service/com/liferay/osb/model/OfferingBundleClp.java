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
import com.liferay.osb.service.OfferingBundleLocalServiceUtil;

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
public class OfferingBundleClp extends BaseModelImpl<OfferingBundle>
	implements OfferingBundle {
	public OfferingBundleClp() {
	}

	public Class<?> getModelClass() {
		return OfferingBundle.class;
	}

	public String getModelClassName() {
		return OfferingBundle.class.getName();
	}

	public long getPrimaryKey() {
		return _offeringBundleId;
	}

	public void setPrimaryKey(long primaryKey) {
		setOfferingBundleId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_offeringBundleId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("offeringBundleId", getOfferingBundleId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long offeringBundleId = (Long)attributes.get("offeringBundleId");

		if (offeringBundleId != null) {
			setOfferingBundleId(offeringBundleId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	public long getOfferingBundleId() {
		return _offeringBundleId;
	}

	public void setOfferingBundleId(long offeringBundleId) {
		_offeringBundleId = offeringBundleId;

		if (_offeringBundleRemoteModel != null) {
			try {
				Class<?> clazz = _offeringBundleRemoteModel.getClass();

				Method method = clazz.getMethod("setOfferingBundleId",
						long.class);

				method.invoke(_offeringBundleRemoteModel, offeringBundleId);
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

		if (_offeringBundleRemoteModel != null) {
			try {
				Class<?> clazz = _offeringBundleRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_offeringBundleRemoteModel, userId);
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

		if (_offeringBundleRemoteModel != null) {
			try {
				Class<?> clazz = _offeringBundleRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_offeringBundleRemoteModel, userName);
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

		if (_offeringBundleRemoteModel != null) {
			try {
				Class<?> clazz = _offeringBundleRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_offeringBundleRemoteModel, createDate);
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

		if (_offeringBundleRemoteModel != null) {
			try {
				Class<?> clazz = _offeringBundleRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_offeringBundleRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions() {
		try {
			String methodName = "getOfferingDefinitions";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.osb.model.OfferingDefinition> returnObj = (java.util.List<com.liferay.osb.model.OfferingDefinition>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getOfferingBundleRemoteModel() {
		return _offeringBundleRemoteModel;
	}

	public void setOfferingBundleRemoteModel(
		BaseModel<?> offeringBundleRemoteModel) {
		_offeringBundleRemoteModel = offeringBundleRemoteModel;
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

		Class<?> remoteModelClass = _offeringBundleRemoteModel.getClass();

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

		Object returnValue = method.invoke(_offeringBundleRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			OfferingBundleLocalServiceUtil.addOfferingBundle(this);
		}
		else {
			OfferingBundleLocalServiceUtil.updateOfferingBundle(this);
		}
	}

	@Override
	public OfferingBundle toEscapedModel() {
		return (OfferingBundle)ProxyUtil.newProxyInstance(OfferingBundle.class.getClassLoader(),
			new Class[] { OfferingBundle.class },
			new AutoEscapeBeanHandler(this));
	}

	public OfferingBundle toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		OfferingBundleClp clone = new OfferingBundleClp();

		clone.setOfferingBundleId(getOfferingBundleId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setName(getName());

		return clone;
	}

	public int compareTo(OfferingBundle offeringBundle) {
		int value = 0;

		value = getName().compareTo(offeringBundle.getName());

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

		if (!(obj instanceof OfferingBundleClp)) {
			return false;
		}

		OfferingBundleClp offeringBundle = (OfferingBundleClp)obj;

		long primaryKey = offeringBundle.getPrimaryKey();

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
		StringBundler sb = new StringBundler(11);

		sb.append("{offeringBundleId=");
		sb.append(getOfferingBundleId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.OfferingBundle");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>offeringBundleId</column-name><column-value><![CDATA[");
		sb.append(getOfferingBundleId());
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
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _offeringBundleId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private String _name;
	private BaseModel<?> _offeringBundleRemoteModel;
}