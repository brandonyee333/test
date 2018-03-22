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

package com.liferay.osb.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.TicketEntry;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TicketEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TicketEntry
 * @generated
 */
@ProviderType
public class TicketEntryCacheModel implements CacheModel<TicketEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketEntryCacheModel)) {
			return false;
		}

		TicketEntryCacheModel ticketEntryCacheModel = (TicketEntryCacheModel)obj;

		if (ticketEntryId == ticketEntryCacheModel.ticketEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ticketEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(85);

		sb.append("{ticketEntryId=");
		sb.append(ticketEntryId);
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
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", orderEntryId=");
		sb.append(orderEntryId);
		sb.append(", productEntryId=");
		sb.append(productEntryId);
		sb.append(", supportResponseId=");
		sb.append(supportResponseId);
		sb.append(", offeringEntryId=");
		sb.append(offeringEntryId);
		sb.append(", supportRegionId=");
		sb.append(supportRegionId);
		sb.append(", languageId=");
		sb.append(languageId);
		sb.append(", ticketId=");
		sb.append(ticketId);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", description=");
		sb.append(description);
		sb.append(", reproductionSteps=");
		sb.append(reproductionSteps);
		sb.append(", severity=");
		sb.append(severity);
		sb.append(", status=");
		sb.append(status);
		sb.append(", weight=");
		sb.append(weight);
		sb.append(", escalationLevel=");
		sb.append(escalationLevel);
		sb.append(", envName=");
		sb.append(envName);
		sb.append(", envOS=");
		sb.append(envOS);
		sb.append(", envOSCustom=");
		sb.append(envOSCustom);
		sb.append(", envDB=");
		sb.append(envDB);
		sb.append(", envJVM=");
		sb.append(envJVM);
		sb.append(", envAS=");
		sb.append(envAS);
		sb.append(", envLFR=");
		sb.append(envLFR);
		sb.append(", envBrowser=");
		sb.append(envBrowser);
		sb.append(", envBrowserCustom=");
		sb.append(envBrowserCustom);
		sb.append(", envCS=");
		sb.append(envCS);
		sb.append(", envSearch=");
		sb.append(envSearch);
		sb.append(", component=");
		sb.append(component);
		sb.append(", subcomponent=");
		sb.append(subcomponent);
		sb.append(", subcomponentCustom=");
		sb.append(subcomponentCustom);
		sb.append(", resolution=");
		sb.append(resolution);
		sb.append(", holdDate=");
		sb.append(holdDate);
		sb.append(", closedDate=");
		sb.append(closedDate);
		sb.append(", dueDate=");
		sb.append(dueDate);
		sb.append(", ignoreDueDate=");
		sb.append(ignoreDueDate);
		sb.append(", customerModifiedDate=");
		sb.append(customerModifiedDate);
		sb.append(", workerModifiedDate=");
		sb.append(workerModifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TicketEntry toEntityModel() {
		TicketEntryImpl ticketEntryImpl = new TicketEntryImpl();

		ticketEntryImpl.setTicketEntryId(ticketEntryId);
		ticketEntryImpl.setCompanyId(companyId);
		ticketEntryImpl.setUserId(userId);

		if (userName == null) {
			ticketEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			ticketEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ticketEntryImpl.setCreateDate(null);
		}
		else {
			ticketEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ticketEntryImpl.setModifiedDate(null);
		}
		else {
			ticketEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		ticketEntryImpl.setAccountEntryId(accountEntryId);
		ticketEntryImpl.setOrderEntryId(orderEntryId);
		ticketEntryImpl.setProductEntryId(productEntryId);
		ticketEntryImpl.setSupportResponseId(supportResponseId);
		ticketEntryImpl.setOfferingEntryId(offeringEntryId);
		ticketEntryImpl.setSupportRegionId(supportRegionId);

		if (languageId == null) {
			ticketEntryImpl.setLanguageId(StringPool.BLANK);
		}
		else {
			ticketEntryImpl.setLanguageId(languageId);
		}

		ticketEntryImpl.setTicketId(ticketId);

		if (subject == null) {
			ticketEntryImpl.setSubject(StringPool.BLANK);
		}
		else {
			ticketEntryImpl.setSubject(subject);
		}

		if (description == null) {
			ticketEntryImpl.setDescription(StringPool.BLANK);
		}
		else {
			ticketEntryImpl.setDescription(description);
		}

		if (reproductionSteps == null) {
			ticketEntryImpl.setReproductionSteps(StringPool.BLANK);
		}
		else {
			ticketEntryImpl.setReproductionSteps(reproductionSteps);
		}

		ticketEntryImpl.setSeverity(severity);
		ticketEntryImpl.setStatus(status);
		ticketEntryImpl.setWeight(weight);
		ticketEntryImpl.setEscalationLevel(escalationLevel);

		if (envName == null) {
			ticketEntryImpl.setEnvName(StringPool.BLANK);
		}
		else {
			ticketEntryImpl.setEnvName(envName);
		}

		ticketEntryImpl.setEnvOS(envOS);

		if (envOSCustom == null) {
			ticketEntryImpl.setEnvOSCustom(StringPool.BLANK);
		}
		else {
			ticketEntryImpl.setEnvOSCustom(envOSCustom);
		}

		ticketEntryImpl.setEnvDB(envDB);
		ticketEntryImpl.setEnvJVM(envJVM);
		ticketEntryImpl.setEnvAS(envAS);
		ticketEntryImpl.setEnvLFR(envLFR);
		ticketEntryImpl.setEnvBrowser(envBrowser);

		if (envBrowserCustom == null) {
			ticketEntryImpl.setEnvBrowserCustom(StringPool.BLANK);
		}
		else {
			ticketEntryImpl.setEnvBrowserCustom(envBrowserCustom);
		}

		ticketEntryImpl.setEnvCS(envCS);

		if (envSearch == null) {
			ticketEntryImpl.setEnvSearch(StringPool.BLANK);
		}
		else {
			ticketEntryImpl.setEnvSearch(envSearch);
		}

		ticketEntryImpl.setComponent(component);
		ticketEntryImpl.setSubcomponent(subcomponent);

		if (subcomponentCustom == null) {
			ticketEntryImpl.setSubcomponentCustom(StringPool.BLANK);
		}
		else {
			ticketEntryImpl.setSubcomponentCustom(subcomponentCustom);
		}

		ticketEntryImpl.setResolution(resolution);

		if (holdDate == Long.MIN_VALUE) {
			ticketEntryImpl.setHoldDate(null);
		}
		else {
			ticketEntryImpl.setHoldDate(new Date(holdDate));
		}

		if (closedDate == Long.MIN_VALUE) {
			ticketEntryImpl.setClosedDate(null);
		}
		else {
			ticketEntryImpl.setClosedDate(new Date(closedDate));
		}

		if (dueDate == Long.MIN_VALUE) {
			ticketEntryImpl.setDueDate(null);
		}
		else {
			ticketEntryImpl.setDueDate(new Date(dueDate));
		}

		ticketEntryImpl.setIgnoreDueDate(ignoreDueDate);

		if (customerModifiedDate == Long.MIN_VALUE) {
			ticketEntryImpl.setCustomerModifiedDate(null);
		}
		else {
			ticketEntryImpl.setCustomerModifiedDate(new Date(
					customerModifiedDate));
		}

		if (workerModifiedDate == Long.MIN_VALUE) {
			ticketEntryImpl.setWorkerModifiedDate(null);
		}
		else {
			ticketEntryImpl.setWorkerModifiedDate(new Date(workerModifiedDate));
		}

		ticketEntryImpl.resetOriginalValues();

		return ticketEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ticketEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		accountEntryId = objectInput.readLong();

		orderEntryId = objectInput.readLong();

		productEntryId = objectInput.readLong();

		supportResponseId = objectInput.readLong();

		offeringEntryId = objectInput.readLong();

		supportRegionId = objectInput.readLong();
		languageId = objectInput.readUTF();

		ticketId = objectInput.readLong();
		subject = objectInput.readUTF();
		description = objectInput.readUTF();
		reproductionSteps = objectInput.readUTF();

		severity = objectInput.readInt();

		status = objectInput.readInt();

		weight = objectInput.readInt();

		escalationLevel = objectInput.readInt();
		envName = objectInput.readUTF();

		envOS = objectInput.readInt();
		envOSCustom = objectInput.readUTF();

		envDB = objectInput.readInt();

		envJVM = objectInput.readInt();

		envAS = objectInput.readInt();

		envLFR = objectInput.readInt();

		envBrowser = objectInput.readInt();
		envBrowserCustom = objectInput.readUTF();

		envCS = objectInput.readInt();
		envSearch = objectInput.readUTF();

		component = objectInput.readInt();

		subcomponent = objectInput.readInt();
		subcomponentCustom = objectInput.readUTF();

		resolution = objectInput.readInt();
		holdDate = objectInput.readLong();
		closedDate = objectInput.readLong();
		dueDate = objectInput.readLong();

		ignoreDueDate = objectInput.readBoolean();
		customerModifiedDate = objectInput.readLong();
		workerModifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(ticketEntryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(accountEntryId);

		objectOutput.writeLong(orderEntryId);

		objectOutput.writeLong(productEntryId);

		objectOutput.writeLong(supportResponseId);

		objectOutput.writeLong(offeringEntryId);

		objectOutput.writeLong(supportRegionId);

		if (languageId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(languageId);
		}

		objectOutput.writeLong(ticketId);

		if (subject == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subject);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (reproductionSteps == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reproductionSteps);
		}

		objectOutput.writeInt(severity);

		objectOutput.writeInt(status);

		objectOutput.writeInt(weight);

		objectOutput.writeInt(escalationLevel);

		if (envName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(envName);
		}

		objectOutput.writeInt(envOS);

		if (envOSCustom == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(envOSCustom);
		}

		objectOutput.writeInt(envDB);

		objectOutput.writeInt(envJVM);

		objectOutput.writeInt(envAS);

		objectOutput.writeInt(envLFR);

		objectOutput.writeInt(envBrowser);

		if (envBrowserCustom == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(envBrowserCustom);
		}

		objectOutput.writeInt(envCS);

		if (envSearch == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(envSearch);
		}

		objectOutput.writeInt(component);

		objectOutput.writeInt(subcomponent);

		if (subcomponentCustom == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subcomponentCustom);
		}

		objectOutput.writeInt(resolution);
		objectOutput.writeLong(holdDate);
		objectOutput.writeLong(closedDate);
		objectOutput.writeLong(dueDate);

		objectOutput.writeBoolean(ignoreDueDate);
		objectOutput.writeLong(customerModifiedDate);
		objectOutput.writeLong(workerModifiedDate);
	}

	public long ticketEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long accountEntryId;
	public long orderEntryId;
	public long productEntryId;
	public long supportResponseId;
	public long offeringEntryId;
	public long supportRegionId;
	public String languageId;
	public long ticketId;
	public String subject;
	public String description;
	public String reproductionSteps;
	public int severity;
	public int status;
	public int weight;
	public int escalationLevel;
	public String envName;
	public int envOS;
	public String envOSCustom;
	public int envDB;
	public int envJVM;
	public int envAS;
	public int envLFR;
	public int envBrowser;
	public String envBrowserCustom;
	public int envCS;
	public String envSearch;
	public int component;
	public int subcomponent;
	public String subcomponentCustom;
	public int resolution;
	public long holdDate;
	public long closedDate;
	public long dueDate;
	public boolean ignoreDueDate;
	public long customerModifiedDate;
	public long workerModifiedDate;
}