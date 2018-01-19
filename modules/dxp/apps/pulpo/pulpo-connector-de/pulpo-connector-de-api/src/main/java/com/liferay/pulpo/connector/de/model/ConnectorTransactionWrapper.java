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

package com.liferay.pulpo.connector.de.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ConnectorTransaction}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ConnectorTransaction
 * @generated
 */
@ProviderType
public class ConnectorTransactionWrapper implements ConnectorTransaction,
	ModelWrapper<ConnectorTransaction> {
	public ConnectorTransactionWrapper(
		ConnectorTransaction connectorTransaction) {
		_connectorTransaction = connectorTransaction;
	}

	@Override
	public Class<?> getModelClass() {
		return ConnectorTransaction.class;
	}

	@Override
	public String getModelClassName() {
		return ConnectorTransaction.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("connectorTransactionId", getConnectorTransactionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("operation", getOperation());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long connectorTransactionId = (Long)attributes.get(
				"connectorTransactionId");

		if (connectorTransactionId != null) {
			setConnectorTransactionId(connectorTransactionId);
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

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String operation = (String)attributes.get("operation");

		if (operation != null) {
			setOperation(operation);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ConnectorTransactionWrapper((ConnectorTransaction)_connectorTransaction.clone());
	}

	@Override
	public int compareTo(ConnectorTransaction connectorTransaction) {
		return _connectorTransaction.compareTo(connectorTransaction);
	}

	/**
	* Returns the fully qualified class name of this connector transaction.
	*
	* @return the fully qualified class name of this connector transaction
	*/
	@Override
	public java.lang.String getClassName() {
		return _connectorTransaction.getClassName();
	}

	/**
	* Returns the class name ID of this connector transaction.
	*
	* @return the class name ID of this connector transaction
	*/
	@Override
	public long getClassNameId() {
		return _connectorTransaction.getClassNameId();
	}

	/**
	* Returns the class pk of this connector transaction.
	*
	* @return the class pk of this connector transaction
	*/
	@Override
	public long getClassPK() {
		return _connectorTransaction.getClassPK();
	}

	/**
	* Returns the company ID of this connector transaction.
	*
	* @return the company ID of this connector transaction
	*/
	@Override
	public long getCompanyId() {
		return _connectorTransaction.getCompanyId();
	}

	/**
	* Returns the connector transaction ID of this connector transaction.
	*
	* @return the connector transaction ID of this connector transaction
	*/
	@Override
	public long getConnectorTransactionId() {
		return _connectorTransaction.getConnectorTransactionId();
	}

	/**
	* Returns the create date of this connector transaction.
	*
	* @return the create date of this connector transaction
	*/
	@Override
	public Date getCreateDate() {
		return _connectorTransaction.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _connectorTransaction.getExpandoBridge();
	}

	/**
	* Returns the modified date of this connector transaction.
	*
	* @return the modified date of this connector transaction
	*/
	@Override
	public Date getModifiedDate() {
		return _connectorTransaction.getModifiedDate();
	}

	/**
	* Returns the operation of this connector transaction.
	*
	* @return the operation of this connector transaction
	*/
	@Override
	public java.lang.String getOperation() {
		return _connectorTransaction.getOperation();
	}

	/**
	* Returns the primary key of this connector transaction.
	*
	* @return the primary key of this connector transaction
	*/
	@Override
	public long getPrimaryKey() {
		return _connectorTransaction.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _connectorTransaction.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this connector transaction.
	*
	* @return the status of this connector transaction
	*/
	@Override
	public java.lang.String getStatus() {
		return _connectorTransaction.getStatus();
	}

	/**
	* Returns the user ID of this connector transaction.
	*
	* @return the user ID of this connector transaction
	*/
	@Override
	public long getUserId() {
		return _connectorTransaction.getUserId();
	}

	/**
	* Returns the user name of this connector transaction.
	*
	* @return the user name of this connector transaction
	*/
	@Override
	public java.lang.String getUserName() {
		return _connectorTransaction.getUserName();
	}

	/**
	* Returns the user uuid of this connector transaction.
	*
	* @return the user uuid of this connector transaction
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _connectorTransaction.getUserUuid();
	}

	/**
	* Returns the uuid of this connector transaction.
	*
	* @return the uuid of this connector transaction
	*/
	@Override
	public java.lang.String getUuid() {
		return _connectorTransaction.getUuid();
	}

	@Override
	public int hashCode() {
		return _connectorTransaction.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _connectorTransaction.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _connectorTransaction.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _connectorTransaction.isNew();
	}

	@Override
	public void persist() {
		_connectorTransaction.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_connectorTransaction.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_connectorTransaction.setClassName(className);
	}

	/**
	* Sets the class name ID of this connector transaction.
	*
	* @param classNameId the class name ID of this connector transaction
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_connectorTransaction.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this connector transaction.
	*
	* @param classPK the class pk of this connector transaction
	*/
	@Override
	public void setClassPK(long classPK) {
		_connectorTransaction.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this connector transaction.
	*
	* @param companyId the company ID of this connector transaction
	*/
	@Override
	public void setCompanyId(long companyId) {
		_connectorTransaction.setCompanyId(companyId);
	}

	/**
	* Sets the connector transaction ID of this connector transaction.
	*
	* @param connectorTransactionId the connector transaction ID of this connector transaction
	*/
	@Override
	public void setConnectorTransactionId(long connectorTransactionId) {
		_connectorTransaction.setConnectorTransactionId(connectorTransactionId);
	}

	/**
	* Sets the create date of this connector transaction.
	*
	* @param createDate the create date of this connector transaction
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_connectorTransaction.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_connectorTransaction.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_connectorTransaction.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_connectorTransaction.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this connector transaction.
	*
	* @param modifiedDate the modified date of this connector transaction
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_connectorTransaction.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_connectorTransaction.setNew(n);
	}

	/**
	* Sets the operation of this connector transaction.
	*
	* @param operation the operation of this connector transaction
	*/
	@Override
	public void setOperation(java.lang.String operation) {
		_connectorTransaction.setOperation(operation);
	}

	/**
	* Sets the primary key of this connector transaction.
	*
	* @param primaryKey the primary key of this connector transaction
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_connectorTransaction.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_connectorTransaction.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this connector transaction.
	*
	* @param status the status of this connector transaction
	*/
	@Override
	public void setStatus(java.lang.String status) {
		_connectorTransaction.setStatus(status);
	}

	/**
	* Sets the user ID of this connector transaction.
	*
	* @param userId the user ID of this connector transaction
	*/
	@Override
	public void setUserId(long userId) {
		_connectorTransaction.setUserId(userId);
	}

	/**
	* Sets the user name of this connector transaction.
	*
	* @param userName the user name of this connector transaction
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_connectorTransaction.setUserName(userName);
	}

	/**
	* Sets the user uuid of this connector transaction.
	*
	* @param userUuid the user uuid of this connector transaction
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_connectorTransaction.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this connector transaction.
	*
	* @param uuid the uuid of this connector transaction
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_connectorTransaction.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ConnectorTransaction> toCacheModel() {
		return _connectorTransaction.toCacheModel();
	}

	@Override
	public ConnectorTransaction toEscapedModel() {
		return new ConnectorTransactionWrapper(_connectorTransaction.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _connectorTransaction.toString();
	}

	@Override
	public ConnectorTransaction toUnescapedModel() {
		return new ConnectorTransactionWrapper(_connectorTransaction.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _connectorTransaction.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ConnectorTransactionWrapper)) {
			return false;
		}

		ConnectorTransactionWrapper connectorTransactionWrapper = (ConnectorTransactionWrapper)obj;

		if (Objects.equals(_connectorTransaction,
					connectorTransactionWrapper._connectorTransaction)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _connectorTransaction.getStagedModelType();
	}

	@Override
	public ConnectorTransaction getWrappedModel() {
		return _connectorTransaction;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _connectorTransaction.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _connectorTransaction.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_connectorTransaction.resetOriginalValues();
	}

	private final ConnectorTransaction _connectorTransaction;
}