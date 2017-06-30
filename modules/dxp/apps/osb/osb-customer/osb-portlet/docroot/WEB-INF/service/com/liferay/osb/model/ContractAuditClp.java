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
import com.liferay.osb.service.ContractAuditLocalServiceUtil;

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
public class ContractAuditClp extends BaseModelImpl<ContractAudit>
	implements ContractAudit {
	public ContractAuditClp() {
	}

	public Class<?> getModelClass() {
		return ContractAudit.class;
	}

	public String getModelClassName() {
		return ContractAudit.class.getName();
	}

	public long getPrimaryKey() {
		return _contractAuditId;
	}

	public void setPrimaryKey(long primaryKey) {
		setContractAuditId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_contractAuditId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("contractAuditId", getContractAuditId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("userEmailAddress", getUserEmailAddress());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("contractEntryId", getContractEntryId());
		attributes.put("signatoryClassNameId", getSignatoryClassNameId());
		attributes.put("signatoryClassPK", getSignatoryClassPK());
		attributes.put("productClassNameId", getProductClassNameId());
		attributes.put("productClassPK", getProductClassPK());
		attributes.put("type", getType());
		attributes.put("languageId", getLanguageId());
		attributes.put("version", getVersion());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long contractAuditId = (Long)attributes.get("contractAuditId");

		if (contractAuditId != null) {
			setContractAuditId(contractAuditId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		String userEmailAddress = (String)attributes.get("userEmailAddress");

		if (userEmailAddress != null) {
			setUserEmailAddress(userEmailAddress);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long contractEntryId = (Long)attributes.get("contractEntryId");

		if (contractEntryId != null) {
			setContractEntryId(contractEntryId);
		}

		Long signatoryClassNameId = (Long)attributes.get("signatoryClassNameId");

		if (signatoryClassNameId != null) {
			setSignatoryClassNameId(signatoryClassNameId);
		}

		Long signatoryClassPK = (Long)attributes.get("signatoryClassPK");

		if (signatoryClassPK != null) {
			setSignatoryClassPK(signatoryClassPK);
		}

		Long productClassNameId = (Long)attributes.get("productClassNameId");

		if (productClassNameId != null) {
			setProductClassNameId(productClassNameId);
		}

		Long productClassPK = (Long)attributes.get("productClassPK");

		if (productClassPK != null) {
			setProductClassPK(productClassPK);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String languageId = (String)attributes.get("languageId");

		if (languageId != null) {
			setLanguageId(languageId);
		}

		Integer version = (Integer)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}
	}

	public long getContractAuditId() {
		return _contractAuditId;
	}

	public void setContractAuditId(long contractAuditId) {
		_contractAuditId = contractAuditId;

		if (_contractAuditRemoteModel != null) {
			try {
				Class<?> clazz = _contractAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setContractAuditId", long.class);

				method.invoke(_contractAuditRemoteModel, contractAuditId);
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

		if (_contractAuditRemoteModel != null) {
			try {
				Class<?> clazz = _contractAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_contractAuditRemoteModel, userId);
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

		if (_contractAuditRemoteModel != null) {
			try {
				Class<?> clazz = _contractAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_contractAuditRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getUserEmailAddress() {
		return _userEmailAddress;
	}

	public void setUserEmailAddress(String userEmailAddress) {
		_userEmailAddress = userEmailAddress;

		if (_contractAuditRemoteModel != null) {
			try {
				Class<?> clazz = _contractAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setUserEmailAddress",
						String.class);

				method.invoke(_contractAuditRemoteModel, userEmailAddress);
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

		if (_contractAuditRemoteModel != null) {
			try {
				Class<?> clazz = _contractAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_contractAuditRemoteModel, createDate);
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

		if (_contractAuditRemoteModel != null) {
			try {
				Class<?> clazz = _contractAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_contractAuditRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getContractEntryId() {
		return _contractEntryId;
	}

	public void setContractEntryId(long contractEntryId) {
		_contractEntryId = contractEntryId;

		if (_contractAuditRemoteModel != null) {
			try {
				Class<?> clazz = _contractAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setContractEntryId", long.class);

				method.invoke(_contractAuditRemoteModel, contractEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getSignatoryClassNameId() {
		return _signatoryClassNameId;
	}

	public void setSignatoryClassNameId(long signatoryClassNameId) {
		_signatoryClassNameId = signatoryClassNameId;

		if (_contractAuditRemoteModel != null) {
			try {
				Class<?> clazz = _contractAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setSignatoryClassNameId",
						long.class);

				method.invoke(_contractAuditRemoteModel, signatoryClassNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getSignatoryClassPK() {
		return _signatoryClassPK;
	}

	public void setSignatoryClassPK(long signatoryClassPK) {
		_signatoryClassPK = signatoryClassPK;

		if (_contractAuditRemoteModel != null) {
			try {
				Class<?> clazz = _contractAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setSignatoryClassPK",
						long.class);

				method.invoke(_contractAuditRemoteModel, signatoryClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getProductClassNameId() {
		return _productClassNameId;
	}

	public void setProductClassNameId(long productClassNameId) {
		_productClassNameId = productClassNameId;

		if (_contractAuditRemoteModel != null) {
			try {
				Class<?> clazz = _contractAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setProductClassNameId",
						long.class);

				method.invoke(_contractAuditRemoteModel, productClassNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getProductClassPK() {
		return _productClassPK;
	}

	public void setProductClassPK(long productClassPK) {
		_productClassPK = productClassPK;

		if (_contractAuditRemoteModel != null) {
			try {
				Class<?> clazz = _contractAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setProductClassPK", long.class);

				method.invoke(_contractAuditRemoteModel, productClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;

		if (_contractAuditRemoteModel != null) {
			try {
				Class<?> clazz = _contractAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setType", String.class);

				method.invoke(_contractAuditRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getLanguageId() {
		return _languageId;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;

		if (_contractAuditRemoteModel != null) {
			try {
				Class<?> clazz = _contractAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setLanguageId", String.class);

				method.invoke(_contractAuditRemoteModel, languageId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getVersion() {
		return _version;
	}

	public void setVersion(int version) {
		_version = version;

		if (_contractAuditRemoteModel != null) {
			try {
				Class<?> clazz = _contractAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setVersion", int.class);

				method.invoke(_contractAuditRemoteModel, version);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getContractAuditRemoteModel() {
		return _contractAuditRemoteModel;
	}

	public void setContractAuditRemoteModel(
		BaseModel<?> contractAuditRemoteModel) {
		_contractAuditRemoteModel = contractAuditRemoteModel;
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

		Class<?> remoteModelClass = _contractAuditRemoteModel.getClass();

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

		Object returnValue = method.invoke(_contractAuditRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			ContractAuditLocalServiceUtil.addContractAudit(this);
		}
		else {
			ContractAuditLocalServiceUtil.updateContractAudit(this);
		}
	}

	@Override
	public ContractAudit toEscapedModel() {
		return (ContractAudit)ProxyUtil.newProxyInstance(ContractAudit.class.getClassLoader(),
			new Class[] { ContractAudit.class }, new AutoEscapeBeanHandler(this));
	}

	public ContractAudit toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		ContractAuditClp clone = new ContractAuditClp();

		clone.setContractAuditId(getContractAuditId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setUserEmailAddress(getUserEmailAddress());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setContractEntryId(getContractEntryId());
		clone.setSignatoryClassNameId(getSignatoryClassNameId());
		clone.setSignatoryClassPK(getSignatoryClassPK());
		clone.setProductClassNameId(getProductClassNameId());
		clone.setProductClassPK(getProductClassPK());
		clone.setType(getType());
		clone.setLanguageId(getLanguageId());
		clone.setVersion(getVersion());

		return clone;
	}

	public int compareTo(ContractAudit contractAudit) {
		long primaryKey = contractAudit.getPrimaryKey();

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

		if (!(obj instanceof ContractAuditClp)) {
			return false;
		}

		ContractAuditClp contractAudit = (ContractAuditClp)obj;

		long primaryKey = contractAudit.getPrimaryKey();

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
		StringBundler sb = new StringBundler(29);

		sb.append("{contractAuditId=");
		sb.append(getContractAuditId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", userEmailAddress=");
		sb.append(getUserEmailAddress());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", contractEntryId=");
		sb.append(getContractEntryId());
		sb.append(", signatoryClassNameId=");
		sb.append(getSignatoryClassNameId());
		sb.append(", signatoryClassPK=");
		sb.append(getSignatoryClassPK());
		sb.append(", productClassNameId=");
		sb.append(getProductClassNameId());
		sb.append(", productClassPK=");
		sb.append(getProductClassPK());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", languageId=");
		sb.append(getLanguageId());
		sb.append(", version=");
		sb.append(getVersion());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.ContractAudit");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>contractAuditId</column-name><column-value><![CDATA[");
		sb.append(getContractAuditId());
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
			"<column><column-name>userEmailAddress</column-name><column-value><![CDATA[");
		sb.append(getUserEmailAddress());
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
			"<column><column-name>contractEntryId</column-name><column-value><![CDATA[");
		sb.append(getContractEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>signatoryClassNameId</column-name><column-value><![CDATA[");
		sb.append(getSignatoryClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>signatoryClassPK</column-name><column-value><![CDATA[");
		sb.append(getSignatoryClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>productClassNameId</column-name><column-value><![CDATA[");
		sb.append(getProductClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>productClassPK</column-name><column-value><![CDATA[");
		sb.append(getProductClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>languageId</column-name><column-value><![CDATA[");
		sb.append(getLanguageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append(getVersion());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _contractAuditId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private String _userEmailAddress;
	private Date _createDate;
	private Date _modifiedDate;
	private long _contractEntryId;
	private long _signatoryClassNameId;
	private long _signatoryClassPK;
	private long _productClassNameId;
	private long _productClassPK;
	private String _type;
	private String _languageId;
	private int _version;
	private BaseModel<?> _contractAuditRemoteModel;
}