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

package com.liferay.pulpo.connector.de.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.liferay.pulpo.connector.de.model.ConnectorTransaction;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ConnectorTransaction in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ConnectorTransaction
 * @generated
 */
@ProviderType
public class ConnectorTransactionCacheModel implements CacheModel<ConnectorTransaction>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ConnectorTransactionCacheModel)) {
			return false;
		}

		ConnectorTransactionCacheModel connectorTransactionCacheModel = (ConnectorTransactionCacheModel)obj;

		if (connectorTransactionId == connectorTransactionCacheModel.connectorTransactionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, connectorTransactionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{connectorTransactionId=");
		sb.append(connectorTransactionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", connectorTransactionUuid=");
		sb.append(connectorTransactionUuid);
		sb.append(", operation=");
		sb.append(operation);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ConnectorTransaction toEntityModel() {
		ConnectorTransactionImpl connectorTransactionImpl = new ConnectorTransactionImpl();

		connectorTransactionImpl.setConnectorTransactionId(connectorTransactionId);
		connectorTransactionImpl.setCompanyId(companyId);
		connectorTransactionImpl.setUserId(userId);

		if (userName == null) {
			connectorTransactionImpl.setUserName("");
		}
		else {
			connectorTransactionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			connectorTransactionImpl.setCreateDate(null);
		}
		else {
			connectorTransactionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			connectorTransactionImpl.setModifiedDate(null);
		}
		else {
			connectorTransactionImpl.setModifiedDate(new Date(modifiedDate));
		}

		connectorTransactionImpl.setClassNameId(classNameId);
		connectorTransactionImpl.setClassPK(classPK);

		if (connectorTransactionUuid == null) {
			connectorTransactionImpl.setConnectorTransactionUuid("");
		}
		else {
			connectorTransactionImpl.setConnectorTransactionUuid(connectorTransactionUuid);
		}

		if (operation == null) {
			connectorTransactionImpl.setOperation("");
		}
		else {
			connectorTransactionImpl.setOperation(operation);
		}

		if (status == null) {
			connectorTransactionImpl.setStatus("");
		}
		else {
			connectorTransactionImpl.setStatus(status);
		}

		connectorTransactionImpl.resetOriginalValues();

		return connectorTransactionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		connectorTransactionId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
		connectorTransactionUuid = objectInput.readUTF();
		operation = objectInput.readUTF();
		status = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(connectorTransactionId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		if (connectorTransactionUuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(connectorTransactionUuid);
		}

		if (operation == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(operation);
		}

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}
	}

	public long connectorTransactionId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String connectorTransactionUuid;
	public String operation;
	public String status;
}