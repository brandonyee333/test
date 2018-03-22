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
import com.liferay.osb.service.OfferingBundleLocalServiceUtil;

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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class OfferingBundleClp extends BaseModelImpl<OfferingBundle>
	implements OfferingBundle {
	public OfferingBundleClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return OfferingBundle.class;
	}

	@Override
	public String getModelClassName() {
		return OfferingBundle.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _offeringBundleId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOfferingBundleId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _offeringBundleId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("offeringBundleId", getOfferingBundleId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("name", getName());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long offeringBundleId = (Long)attributes.get("offeringBundleId");

		if (offeringBundleId != null) {
			setOfferingBundleId(offeringBundleId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getOfferingBundleId() {
		return _offeringBundleId;
	}

	@Override
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

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_offeringBundleRemoteModel != null) {
			try {
				Class<?> clazz = _offeringBundleRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_offeringBundleRemoteModel, companyId);
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

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
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

	@Override
	public String getName() {
		return _name;
	}

	@Override
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

	@Override
	public java.util.List<OfferingDefinition> getOfferingDefinitions() {
		try {
			String methodName = "getOfferingDefinitions";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<OfferingDefinition> returnObj = (java.util.List<OfferingDefinition>)invokeOnRemoteModel(methodName,
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

	@Override
	public void persist() {
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

	@Override
	public Object clone() {
		OfferingBundleClp clone = new OfferingBundleClp();

		clone.setOfferingBundleId(getOfferingBundleId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setName(getName());

		return clone;
	}

	@Override
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
		StringBundler sb = new StringBundler(13);

		sb.append("{offeringBundleId=");
		sb.append(getOfferingBundleId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
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

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.OfferingBundle");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>offeringBundleId</column-name><column-value><![CDATA[");
		sb.append(getOfferingBundleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
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
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private String _name;
	private BaseModel<?> _offeringBundleRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}