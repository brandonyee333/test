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
import com.liferay.osb.service.OfferingDefinitionLocalServiceUtil;

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
public class OfferingDefinitionClp extends BaseModelImpl<OfferingDefinition>
	implements OfferingDefinition {
	public OfferingDefinitionClp() {
	}

	public Class<?> getModelClass() {
		return OfferingDefinition.class;
	}

	public String getModelClassName() {
		return OfferingDefinition.class.getName();
	}

	public long getPrimaryKey() {
		return _offeringDefinitionId;
	}

	public void setPrimaryKey(long primaryKey) {
		setOfferingDefinitionId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_offeringDefinitionId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("offeringDefinitionId", getOfferingDefinitionId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("productEntryId", getProductEntryId());
		attributes.put("supportResponseId", getSupportResponseId());
		attributes.put("productDescription", getProductDescription());
		attributes.put("licenses", getLicenses());
		attributes.put("unlimitedLicenses", getUnlimitedLicenses());
		attributes.put("maxConcurrentUsers", getMaxConcurrentUsers());
		attributes.put("maxUsers", getMaxUsers());
		attributes.put("supportTickets", getSupportTickets());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long offeringDefinitionId = (Long)attributes.get("offeringDefinitionId");

		if (offeringDefinitionId != null) {
			setOfferingDefinitionId(offeringDefinitionId);
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

		Long productEntryId = (Long)attributes.get("productEntryId");

		if (productEntryId != null) {
			setProductEntryId(productEntryId);
		}

		Long supportResponseId = (Long)attributes.get("supportResponseId");

		if (supportResponseId != null) {
			setSupportResponseId(supportResponseId);
		}

		String productDescription = (String)attributes.get("productDescription");

		if (productDescription != null) {
			setProductDescription(productDescription);
		}

		Boolean licenses = (Boolean)attributes.get("licenses");

		if (licenses != null) {
			setLicenses(licenses);
		}

		Boolean unlimitedLicenses = (Boolean)attributes.get("unlimitedLicenses");

		if (unlimitedLicenses != null) {
			setUnlimitedLicenses(unlimitedLicenses);
		}

		Long maxConcurrentUsers = (Long)attributes.get("maxConcurrentUsers");

		if (maxConcurrentUsers != null) {
			setMaxConcurrentUsers(maxConcurrentUsers);
		}

		Long maxUsers = (Long)attributes.get("maxUsers");

		if (maxUsers != null) {
			setMaxUsers(maxUsers);
		}

		Boolean supportTickets = (Boolean)attributes.get("supportTickets");

		if (supportTickets != null) {
			setSupportTickets(supportTickets);
		}
	}

	public long getOfferingDefinitionId() {
		return _offeringDefinitionId;
	}

	public void setOfferingDefinitionId(long offeringDefinitionId) {
		_offeringDefinitionId = offeringDefinitionId;

		if (_offeringDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _offeringDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setOfferingDefinitionId",
						long.class);

				method.invoke(_offeringDefinitionRemoteModel,
					offeringDefinitionId);
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

		if (_offeringDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _offeringDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_offeringDefinitionRemoteModel, userId);
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

		if (_offeringDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _offeringDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_offeringDefinitionRemoteModel, userName);
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

		if (_offeringDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _offeringDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_offeringDefinitionRemoteModel, createDate);
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

		if (_offeringDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _offeringDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_offeringDefinitionRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getProductEntryId() {
		return _productEntryId;
	}

	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;

		if (_offeringDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _offeringDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setProductEntryId", long.class);

				method.invoke(_offeringDefinitionRemoteModel, productEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getSupportResponseId() {
		return _supportResponseId;
	}

	public void setSupportResponseId(long supportResponseId) {
		_supportResponseId = supportResponseId;

		if (_offeringDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _offeringDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportResponseId",
						long.class);

				method.invoke(_offeringDefinitionRemoteModel, supportResponseId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getProductDescription() {
		return _productDescription;
	}

	public void setProductDescription(String productDescription) {
		_productDescription = productDescription;

		if (_offeringDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _offeringDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setProductDescription",
						String.class);

				method.invoke(_offeringDefinitionRemoteModel, productDescription);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getLicenses() {
		return _licenses;
	}

	public boolean isLicenses() {
		return _licenses;
	}

	public void setLicenses(boolean licenses) {
		_licenses = licenses;

		if (_offeringDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _offeringDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenses", boolean.class);

				method.invoke(_offeringDefinitionRemoteModel, licenses);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getUnlimitedLicenses() {
		return _unlimitedLicenses;
	}

	public boolean isUnlimitedLicenses() {
		return _unlimitedLicenses;
	}

	public void setUnlimitedLicenses(boolean unlimitedLicenses) {
		_unlimitedLicenses = unlimitedLicenses;

		if (_offeringDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _offeringDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setUnlimitedLicenses",
						boolean.class);

				method.invoke(_offeringDefinitionRemoteModel, unlimitedLicenses);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getMaxConcurrentUsers() {
		return _maxConcurrentUsers;
	}

	public void setMaxConcurrentUsers(long maxConcurrentUsers) {
		_maxConcurrentUsers = maxConcurrentUsers;

		if (_offeringDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _offeringDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setMaxConcurrentUsers",
						long.class);

				method.invoke(_offeringDefinitionRemoteModel, maxConcurrentUsers);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getMaxUsers() {
		return _maxUsers;
	}

	public void setMaxUsers(long maxUsers) {
		_maxUsers = maxUsers;

		if (_offeringDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _offeringDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setMaxUsers", long.class);

				method.invoke(_offeringDefinitionRemoteModel, maxUsers);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getSupportTickets() {
		return _supportTickets;
	}

	public boolean isSupportTickets() {
		return _supportTickets;
	}

	public void setSupportTickets(boolean supportTickets) {
		_supportTickets = supportTickets;

		if (_offeringDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _offeringDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportTickets",
						boolean.class);

				method.invoke(_offeringDefinitionRemoteModel, supportTickets);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.lang.String getMaxConcurrentUsersLabel() {
		try {
			String methodName = "getMaxConcurrentUsersLabel";

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

	public com.liferay.osb.model.SupportResponse getSupportResponse() {
		try {
			String methodName = "getSupportResponse";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.SupportResponse returnObj = (com.liferay.osb.model.SupportResponse)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public int getQuantity() {
		try {
			String methodName = "getQuantity";

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

	public java.lang.String getMaxUsersLabel() {
		try {
			String methodName = "getMaxUsersLabel";

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

	public java.lang.String getSupportTicketsLabel() {
		try {
			String methodName = "getSupportTicketsLabel";

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

	public com.liferay.osb.model.ProductEntry getProductEntry() {
		try {
			String methodName = "getProductEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.ProductEntry returnObj = (com.liferay.osb.model.ProductEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getLicensesLabel() {
		try {
			String methodName = "getLicensesLabel";

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

	public BaseModel<?> getOfferingDefinitionRemoteModel() {
		return _offeringDefinitionRemoteModel;
	}

	public void setOfferingDefinitionRemoteModel(
		BaseModel<?> offeringDefinitionRemoteModel) {
		_offeringDefinitionRemoteModel = offeringDefinitionRemoteModel;
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

		Class<?> remoteModelClass = _offeringDefinitionRemoteModel.getClass();

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

		Object returnValue = method.invoke(_offeringDefinitionRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			OfferingDefinitionLocalServiceUtil.addOfferingDefinition(this);
		}
		else {
			OfferingDefinitionLocalServiceUtil.updateOfferingDefinition(this);
		}
	}

	@Override
	public OfferingDefinition toEscapedModel() {
		return (OfferingDefinition)ProxyUtil.newProxyInstance(OfferingDefinition.class.getClassLoader(),
			new Class[] { OfferingDefinition.class },
			new AutoEscapeBeanHandler(this));
	}

	public OfferingDefinition toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		OfferingDefinitionClp clone = new OfferingDefinitionClp();

		clone.setOfferingDefinitionId(getOfferingDefinitionId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setProductEntryId(getProductEntryId());
		clone.setSupportResponseId(getSupportResponseId());
		clone.setProductDescription(getProductDescription());
		clone.setLicenses(getLicenses());
		clone.setUnlimitedLicenses(getUnlimitedLicenses());
		clone.setMaxConcurrentUsers(getMaxConcurrentUsers());
		clone.setMaxUsers(getMaxUsers());
		clone.setSupportTickets(getSupportTickets());

		return clone;
	}

	public int compareTo(OfferingDefinition offeringDefinition) {
		int value = 0;

		if (getProductEntryId() < offeringDefinition.getProductEntryId()) {
			value = -1;
		}
		else if (getProductEntryId() > offeringDefinition.getProductEntryId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (getSupportResponseId() < offeringDefinition.getSupportResponseId()) {
			value = -1;
		}
		else if (getSupportResponseId() > offeringDefinition.getSupportResponseId()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(obj instanceof OfferingDefinitionClp)) {
			return false;
		}

		OfferingDefinitionClp offeringDefinition = (OfferingDefinitionClp)obj;

		long primaryKey = offeringDefinition.getPrimaryKey();

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

		sb.append("{offeringDefinitionId=");
		sb.append(getOfferingDefinitionId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", productEntryId=");
		sb.append(getProductEntryId());
		sb.append(", supportResponseId=");
		sb.append(getSupportResponseId());
		sb.append(", productDescription=");
		sb.append(getProductDescription());
		sb.append(", licenses=");
		sb.append(getLicenses());
		sb.append(", unlimitedLicenses=");
		sb.append(getUnlimitedLicenses());
		sb.append(", maxConcurrentUsers=");
		sb.append(getMaxConcurrentUsers());
		sb.append(", maxUsers=");
		sb.append(getMaxUsers());
		sb.append(", supportTickets=");
		sb.append(getSupportTickets());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.OfferingDefinition");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>offeringDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getOfferingDefinitionId());
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
			"<column><column-name>productEntryId</column-name><column-value><![CDATA[");
		sb.append(getProductEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportResponseId</column-name><column-value><![CDATA[");
		sb.append(getSupportResponseId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>productDescription</column-name><column-value><![CDATA[");
		sb.append(getProductDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>licenses</column-name><column-value><![CDATA[");
		sb.append(getLicenses());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>unlimitedLicenses</column-name><column-value><![CDATA[");
		sb.append(getUnlimitedLicenses());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>maxConcurrentUsers</column-name><column-value><![CDATA[");
		sb.append(getMaxConcurrentUsers());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>maxUsers</column-name><column-value><![CDATA[");
		sb.append(getMaxUsers());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportTickets</column-name><column-value><![CDATA[");
		sb.append(getSupportTickets());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _offeringDefinitionId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _productEntryId;
	private long _supportResponseId;
	private String _productDescription;
	private boolean _licenses;
	private boolean _unlimitedLicenses;
	private long _maxConcurrentUsers;
	private long _maxUsers;
	private boolean _supportTickets;
	private BaseModel<?> _offeringDefinitionRemoteModel;
}