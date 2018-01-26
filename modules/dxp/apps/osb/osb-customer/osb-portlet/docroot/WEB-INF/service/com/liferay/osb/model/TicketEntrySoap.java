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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TicketEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.TicketEntryServiceSoap
 * @generated
 */
@ProviderType
public class TicketEntrySoap implements Serializable {
	public static TicketEntrySoap toSoapModel(TicketEntry model) {
		TicketEntrySoap soapModel = new TicketEntrySoap();

		soapModel.setTicketEntryId(model.getTicketEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setOrderEntryId(model.getOrderEntryId());
		soapModel.setProductEntryId(model.getProductEntryId());
		soapModel.setSupportResponseId(model.getSupportResponseId());
		soapModel.setOfferingEntryId(model.getOfferingEntryId());
		soapModel.setSupportRegionId(model.getSupportRegionId());
		soapModel.setLanguageId(model.getLanguageId());
		soapModel.setTicketId(model.getTicketId());
		soapModel.setSubject(model.getSubject());
		soapModel.setDescription(model.getDescription());
		soapModel.setReproductionSteps(model.getReproductionSteps());
		soapModel.setSeverity(model.getSeverity());
		soapModel.setStatus(model.getStatus());
		soapModel.setWeight(model.getWeight());
		soapModel.setEscalationLevel(model.getEscalationLevel());
		soapModel.setEnvName(model.getEnvName());
		soapModel.setEnvOS(model.getEnvOS());
		soapModel.setEnvOSCustom(model.getEnvOSCustom());
		soapModel.setEnvDB(model.getEnvDB());
		soapModel.setEnvJVM(model.getEnvJVM());
		soapModel.setEnvAS(model.getEnvAS());
		soapModel.setEnvLFR(model.getEnvLFR());
		soapModel.setEnvBrowser(model.getEnvBrowser());
		soapModel.setEnvBrowserCustom(model.getEnvBrowserCustom());
		soapModel.setEnvCS(model.getEnvCS());
		soapModel.setEnvSearch(model.getEnvSearch());
		soapModel.setComponent(model.getComponent());
		soapModel.setSubcomponent(model.getSubcomponent());
		soapModel.setSubcomponentCustom(model.getSubcomponentCustom());
		soapModel.setResolution(model.getResolution());
		soapModel.setHoldDate(model.getHoldDate());
		soapModel.setClosedDate(model.getClosedDate());
		soapModel.setDueDate(model.getDueDate());
		soapModel.setIgnoreDueDate(model.getIgnoreDueDate());
		soapModel.setCustomerModifiedDate(model.getCustomerModifiedDate());
		soapModel.setWorkerModifiedDate(model.getWorkerModifiedDate());

		return soapModel;
	}

	public static TicketEntrySoap[] toSoapModels(TicketEntry[] models) {
		TicketEntrySoap[] soapModels = new TicketEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TicketEntrySoap[][] toSoapModels(TicketEntry[][] models) {
		TicketEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TicketEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new TicketEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TicketEntrySoap[] toSoapModels(List<TicketEntry> models) {
		List<TicketEntrySoap> soapModels = new ArrayList<TicketEntrySoap>(models.size());

		for (TicketEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TicketEntrySoap[soapModels.size()]);
	}

	public TicketEntrySoap() {
	}

	public long getPrimaryKey() {
		return _ticketEntryId;
	}

	public void setPrimaryKey(long pk) {
		setTicketEntryId(pk);
	}

	public long getTicketEntryId() {
		return _ticketEntryId;
	}

	public void setTicketEntryId(long ticketEntryId) {
		_ticketEntryId = ticketEntryId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public long getOrderEntryId() {
		return _orderEntryId;
	}

	public void setOrderEntryId(long orderEntryId) {
		_orderEntryId = orderEntryId;
	}

	public long getProductEntryId() {
		return _productEntryId;
	}

	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;
	}

	public long getSupportResponseId() {
		return _supportResponseId;
	}

	public void setSupportResponseId(long supportResponseId) {
		_supportResponseId = supportResponseId;
	}

	public long getOfferingEntryId() {
		return _offeringEntryId;
	}

	public void setOfferingEntryId(long offeringEntryId) {
		_offeringEntryId = offeringEntryId;
	}

	public long getSupportRegionId() {
		return _supportRegionId;
	}

	public void setSupportRegionId(long supportRegionId) {
		_supportRegionId = supportRegionId;
	}

	public String getLanguageId() {
		return _languageId;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	public long getTicketId() {
		return _ticketId;
	}

	public void setTicketId(long ticketId) {
		_ticketId = ticketId;
	}

	public String getSubject() {
		return _subject;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getReproductionSteps() {
		return _reproductionSteps;
	}

	public void setReproductionSteps(String reproductionSteps) {
		_reproductionSteps = reproductionSteps;
	}

	public int getSeverity() {
		return _severity;
	}

	public void setSeverity(int severity) {
		_severity = severity;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public int getWeight() {
		return _weight;
	}

	public void setWeight(int weight) {
		_weight = weight;
	}

	public int getEscalationLevel() {
		return _escalationLevel;
	}

	public void setEscalationLevel(int escalationLevel) {
		_escalationLevel = escalationLevel;
	}

	public String getEnvName() {
		return _envName;
	}

	public void setEnvName(String envName) {
		_envName = envName;
	}

	public int getEnvOS() {
		return _envOS;
	}

	public void setEnvOS(int envOS) {
		_envOS = envOS;
	}

	public String getEnvOSCustom() {
		return _envOSCustom;
	}

	public void setEnvOSCustom(String envOSCustom) {
		_envOSCustom = envOSCustom;
	}

	public int getEnvDB() {
		return _envDB;
	}

	public void setEnvDB(int envDB) {
		_envDB = envDB;
	}

	public int getEnvJVM() {
		return _envJVM;
	}

	public void setEnvJVM(int envJVM) {
		_envJVM = envJVM;
	}

	public int getEnvAS() {
		return _envAS;
	}

	public void setEnvAS(int envAS) {
		_envAS = envAS;
	}

	public int getEnvLFR() {
		return _envLFR;
	}

	public void setEnvLFR(int envLFR) {
		_envLFR = envLFR;
	}

	public int getEnvBrowser() {
		return _envBrowser;
	}

	public void setEnvBrowser(int envBrowser) {
		_envBrowser = envBrowser;
	}

	public String getEnvBrowserCustom() {
		return _envBrowserCustom;
	}

	public void setEnvBrowserCustom(String envBrowserCustom) {
		_envBrowserCustom = envBrowserCustom;
	}

	public int getEnvCS() {
		return _envCS;
	}

	public void setEnvCS(int envCS) {
		_envCS = envCS;
	}

	public String getEnvSearch() {
		return _envSearch;
	}

	public void setEnvSearch(String envSearch) {
		_envSearch = envSearch;
	}

	public int getComponent() {
		return _component;
	}

	public void setComponent(int component) {
		_component = component;
	}

	public int getSubcomponent() {
		return _subcomponent;
	}

	public void setSubcomponent(int subcomponent) {
		_subcomponent = subcomponent;
	}

	public String getSubcomponentCustom() {
		return _subcomponentCustom;
	}

	public void setSubcomponentCustom(String subcomponentCustom) {
		_subcomponentCustom = subcomponentCustom;
	}

	public int getResolution() {
		return _resolution;
	}

	public void setResolution(int resolution) {
		_resolution = resolution;
	}

	public Date getHoldDate() {
		return _holdDate;
	}

	public void setHoldDate(Date holdDate) {
		_holdDate = holdDate;
	}

	public Date getClosedDate() {
		return _closedDate;
	}

	public void setClosedDate(Date closedDate) {
		_closedDate = closedDate;
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	public boolean getIgnoreDueDate() {
		return _ignoreDueDate;
	}

	public boolean isIgnoreDueDate() {
		return _ignoreDueDate;
	}

	public void setIgnoreDueDate(boolean ignoreDueDate) {
		_ignoreDueDate = ignoreDueDate;
	}

	public Date getCustomerModifiedDate() {
		return _customerModifiedDate;
	}

	public void setCustomerModifiedDate(Date customerModifiedDate) {
		_customerModifiedDate = customerModifiedDate;
	}

	public Date getWorkerModifiedDate() {
		return _workerModifiedDate;
	}

	public void setWorkerModifiedDate(Date workerModifiedDate) {
		_workerModifiedDate = workerModifiedDate;
	}

	private long _ticketEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _accountEntryId;
	private long _orderEntryId;
	private long _productEntryId;
	private long _supportResponseId;
	private long _offeringEntryId;
	private long _supportRegionId;
	private String _languageId;
	private long _ticketId;
	private String _subject;
	private String _description;
	private String _reproductionSteps;
	private int _severity;
	private int _status;
	private int _weight;
	private int _escalationLevel;
	private String _envName;
	private int _envOS;
	private String _envOSCustom;
	private int _envDB;
	private int _envJVM;
	private int _envAS;
	private int _envLFR;
	private int _envBrowser;
	private String _envBrowserCustom;
	private int _envCS;
	private String _envSearch;
	private int _component;
	private int _subcomponent;
	private String _subcomponentCustom;
	private int _resolution;
	private Date _holdDate;
	private Date _closedDate;
	private Date _dueDate;
	private boolean _ignoreDueDate;
	private Date _customerModifiedDate;
	private Date _workerModifiedDate;
}