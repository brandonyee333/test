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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link TicketEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketEntry
 * @generated
 */
@ProviderType
public class TicketEntryWrapper implements TicketEntry,
	ModelWrapper<TicketEntry> {
	public TicketEntryWrapper(TicketEntry ticketEntry) {
		_ticketEntry = ticketEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return TicketEntry.class;
	}

	@Override
	public String getModelClassName() {
		return TicketEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("orderEntryId", getOrderEntryId());
		attributes.put("productEntryId", getProductEntryId());
		attributes.put("supportResponseId", getSupportResponseId());
		attributes.put("offeringEntryId", getOfferingEntryId());
		attributes.put("supportRegionId", getSupportRegionId());
		attributes.put("languageId", getLanguageId());
		attributes.put("ticketId", getTicketId());
		attributes.put("subject", getSubject());
		attributes.put("description", getDescription());
		attributes.put("reproductionSteps", getReproductionSteps());
		attributes.put("severity", getSeverity());
		attributes.put("status", getStatus());
		attributes.put("weight", getWeight());
		attributes.put("escalationLevel", getEscalationLevel());
		attributes.put("envName", getEnvName());
		attributes.put("envOS", getEnvOS());
		attributes.put("envOSCustom", getEnvOSCustom());
		attributes.put("envDB", getEnvDB());
		attributes.put("envJVM", getEnvJVM());
		attributes.put("envAS", getEnvAS());
		attributes.put("envLFR", getEnvLFR());
		attributes.put("envBrowser", getEnvBrowser());
		attributes.put("envBrowserCustom", getEnvBrowserCustom());
		attributes.put("envCS", getEnvCS());
		attributes.put("envSearch", getEnvSearch());
		attributes.put("component", getComponent());
		attributes.put("subcomponent", getSubcomponent());
		attributes.put("subcomponentCustom", getSubcomponentCustom());
		attributes.put("resolution", getResolution());
		attributes.put("holdDate", getHoldDate());
		attributes.put("closedDate", getClosedDate());
		attributes.put("dueDate", getDueDate());
		attributes.put("ignoreDueDate", getIgnoreDueDate());
		attributes.put("customerModifiedDate", getCustomerModifiedDate());
		attributes.put("workerModifiedDate", getWorkerModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
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

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Long orderEntryId = (Long)attributes.get("orderEntryId");

		if (orderEntryId != null) {
			setOrderEntryId(orderEntryId);
		}

		Long productEntryId = (Long)attributes.get("productEntryId");

		if (productEntryId != null) {
			setProductEntryId(productEntryId);
		}

		Long supportResponseId = (Long)attributes.get("supportResponseId");

		if (supportResponseId != null) {
			setSupportResponseId(supportResponseId);
		}

		Long offeringEntryId = (Long)attributes.get("offeringEntryId");

		if (offeringEntryId != null) {
			setOfferingEntryId(offeringEntryId);
		}

		Long supportRegionId = (Long)attributes.get("supportRegionId");

		if (supportRegionId != null) {
			setSupportRegionId(supportRegionId);
		}

		String languageId = (String)attributes.get("languageId");

		if (languageId != null) {
			setLanguageId(languageId);
		}

		Long ticketId = (Long)attributes.get("ticketId");

		if (ticketId != null) {
			setTicketId(ticketId);
		}

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String reproductionSteps = (String)attributes.get("reproductionSteps");

		if (reproductionSteps != null) {
			setReproductionSteps(reproductionSteps);
		}

		Integer severity = (Integer)attributes.get("severity");

		if (severity != null) {
			setSeverity(severity);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Integer weight = (Integer)attributes.get("weight");

		if (weight != null) {
			setWeight(weight);
		}

		Integer escalationLevel = (Integer)attributes.get("escalationLevel");

		if (escalationLevel != null) {
			setEscalationLevel(escalationLevel);
		}

		String envName = (String)attributes.get("envName");

		if (envName != null) {
			setEnvName(envName);
		}

		Integer envOS = (Integer)attributes.get("envOS");

		if (envOS != null) {
			setEnvOS(envOS);
		}

		String envOSCustom = (String)attributes.get("envOSCustom");

		if (envOSCustom != null) {
			setEnvOSCustom(envOSCustom);
		}

		Integer envDB = (Integer)attributes.get("envDB");

		if (envDB != null) {
			setEnvDB(envDB);
		}

		Integer envJVM = (Integer)attributes.get("envJVM");

		if (envJVM != null) {
			setEnvJVM(envJVM);
		}

		Integer envAS = (Integer)attributes.get("envAS");

		if (envAS != null) {
			setEnvAS(envAS);
		}

		Integer envLFR = (Integer)attributes.get("envLFR");

		if (envLFR != null) {
			setEnvLFR(envLFR);
		}

		Integer envBrowser = (Integer)attributes.get("envBrowser");

		if (envBrowser != null) {
			setEnvBrowser(envBrowser);
		}

		String envBrowserCustom = (String)attributes.get("envBrowserCustom");

		if (envBrowserCustom != null) {
			setEnvBrowserCustom(envBrowserCustom);
		}

		Integer envCS = (Integer)attributes.get("envCS");

		if (envCS != null) {
			setEnvCS(envCS);
		}

		String envSearch = (String)attributes.get("envSearch");

		if (envSearch != null) {
			setEnvSearch(envSearch);
		}

		Integer component = (Integer)attributes.get("component");

		if (component != null) {
			setComponent(component);
		}

		Integer subcomponent = (Integer)attributes.get("subcomponent");

		if (subcomponent != null) {
			setSubcomponent(subcomponent);
		}

		String subcomponentCustom = (String)attributes.get("subcomponentCustom");

		if (subcomponentCustom != null) {
			setSubcomponentCustom(subcomponentCustom);
		}

		Integer resolution = (Integer)attributes.get("resolution");

		if (resolution != null) {
			setResolution(resolution);
		}

		Date holdDate = (Date)attributes.get("holdDate");

		if (holdDate != null) {
			setHoldDate(holdDate);
		}

		Date closedDate = (Date)attributes.get("closedDate");

		if (closedDate != null) {
			setClosedDate(closedDate);
		}

		Date dueDate = (Date)attributes.get("dueDate");

		if (dueDate != null) {
			setDueDate(dueDate);
		}

		Boolean ignoreDueDate = (Boolean)attributes.get("ignoreDueDate");

		if (ignoreDueDate != null) {
			setIgnoreDueDate(ignoreDueDate);
		}

		Date customerModifiedDate = (Date)attributes.get("customerModifiedDate");

		if (customerModifiedDate != null) {
			setCustomerModifiedDate(customerModifiedDate);
		}

		Date workerModifiedDate = (Date)attributes.get("workerModifiedDate");

		if (workerModifiedDate != null) {
			setWorkerModifiedDate(workerModifiedDate);
		}
	}

	@Override
	public AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntry.getAccountEntry();
	}

	@Override
	public OfferingEntry getOfferingEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntry.getOfferingEntry();
	}

	@Override
	public OrderEntry getOrderEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntry.getOrderEntry();
	}

	@Override
	public ProductEntry getProductEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntry.getProductEntry();
	}

	@Override
	public SupportRegion getSupportRegion()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntry.getSupportRegion();
	}

	@Override
	public SupportResponse getSupportResponse()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntry.getSupportResponse();
	}

	@Override
	public TicketEntry toEscapedModel() {
		return new TicketEntryWrapper(_ticketEntry.toEscapedModel());
	}

	@Override
	public TicketEntry toUnescapedModel() {
		return new TicketEntryWrapper(_ticketEntry.toUnescapedModel());
	}

	/**
	* Returns the ignore due date of this ticket entry.
	*
	* @return the ignore due date of this ticket entry
	*/
	@Override
	public boolean getIgnoreDueDate() {
		return _ticketEntry.getIgnoreDueDate();
	}

	@Override
	public boolean isCachedModel() {
		return _ticketEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _ticketEntry.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this ticket entry is ignore due date.
	*
	* @return <code>true</code> if this ticket entry is ignore due date; <code>false</code> otherwise
	*/
	@Override
	public boolean isIgnoreDueDate() {
		return _ticketEntry.isIgnoreDueDate();
	}

	@Override
	public boolean isNew() {
		return _ticketEntry.isNew();
	}

	@Override
	public boolean isPendingCustomer() {
		return _ticketEntry.isPendingCustomer();
	}

	@Override
	public boolean isPendingLiferay() {
		return _ticketEntry.isPendingLiferay();
	}

	@Override
	public boolean isPendingPartner() {
		return _ticketEntry.isPendingPartner();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ticketEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TicketEntry> toCacheModel() {
		return _ticketEntry.toCacheModel();
	}

	@Override
	public double getWork() {
		return _ticketEntry.getWork();
	}

	@Override
	public int compareTo(TicketEntry ticketEntry) {
		return _ticketEntry.compareTo(ticketEntry);
	}

	@Override
	public int getAccountTier()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntry.getAccountTier();
	}

	/**
	* Returns the component of this ticket entry.
	*
	* @return the component of this ticket entry
	*/
	@Override
	public int getComponent() {
		return _ticketEntry.getComponent();
	}

	/**
	* Returns the env as of this ticket entry.
	*
	* @return the env as of this ticket entry
	*/
	@Override
	public int getEnvAS() {
		return _ticketEntry.getEnvAS();
	}

	/**
	* Returns the env browser of this ticket entry.
	*
	* @return the env browser of this ticket entry
	*/
	@Override
	public int getEnvBrowser() {
		return _ticketEntry.getEnvBrowser();
	}

	/**
	* Returns the env cs of this ticket entry.
	*
	* @return the env cs of this ticket entry
	*/
	@Override
	public int getEnvCS() {
		return _ticketEntry.getEnvCS();
	}

	/**
	* Returns the env db of this ticket entry.
	*
	* @return the env db of this ticket entry
	*/
	@Override
	public int getEnvDB() {
		return _ticketEntry.getEnvDB();
	}

	/**
	* Returns the env jvm of this ticket entry.
	*
	* @return the env jvm of this ticket entry
	*/
	@Override
	public int getEnvJVM() {
		return _ticketEntry.getEnvJVM();
	}

	/**
	* Returns the env lfr of this ticket entry.
	*
	* @return the env lfr of this ticket entry
	*/
	@Override
	public int getEnvLFR() {
		return _ticketEntry.getEnvLFR();
	}

	/**
	* Returns the env os of this ticket entry.
	*
	* @return the env os of this ticket entry
	*/
	@Override
	public int getEnvOS() {
		return _ticketEntry.getEnvOS();
	}

	/**
	* Returns the escalation level of this ticket entry.
	*
	* @return the escalation level of this ticket entry
	*/
	@Override
	public int getEscalationLevel() {
		return _ticketEntry.getEscalationLevel();
	}

	/**
	* Returns the resolution of this ticket entry.
	*
	* @return the resolution of this ticket entry
	*/
	@Override
	public int getResolution() {
		return _ticketEntry.getResolution();
	}

	/**
	* Returns the severity of this ticket entry.
	*
	* @return the severity of this ticket entry
	*/
	@Override
	public int getSeverity() {
		return _ticketEntry.getSeverity();
	}

	/**
	* Returns the status of this ticket entry.
	*
	* @return the status of this ticket entry
	*/
	@Override
	public int getStatus() {
		return _ticketEntry.getStatus();
	}

	/**
	* Returns the subcomponent of this ticket entry.
	*
	* @return the subcomponent of this ticket entry
	*/
	@Override
	public int getSubcomponent() {
		return _ticketEntry.getSubcomponent();
	}

	@Override
	public int getTicketAttachmentsCount(int[] visibilities) {
		return _ticketEntry.getTicketAttachmentsCount(visibilities);
	}

	/**
	* Returns the weight of this ticket entry.
	*
	* @return the weight of this ticket entry
	*/
	@Override
	public int getWeight() {
		return _ticketEntry.getWeight();
	}

	@Override
	public int hashCode() {
		return _ticketEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TicketEntryWrapper((TicketEntry)_ticketEntry.clone());
	}

	@Override
	public java.lang.String getComponentIcon() {
		return _ticketEntry.getComponentIcon();
	}

	@Override
	public java.lang.String getComponentLabel() {
		return _ticketEntry.getComponentLabel();
	}

	/**
	* Returns the description of this ticket entry.
	*
	* @return the description of this ticket entry
	*/
	@Override
	public java.lang.String getDescription() {
		return _ticketEntry.getDescription();
	}

	@Override
	public java.lang.String getDisplayId()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntry.getDisplayId();
	}

	@Override
	public java.lang.String getEnvASLabel() {
		return _ticketEntry.getEnvASLabel();
	}

	/**
	* Returns the env browser custom of this ticket entry.
	*
	* @return the env browser custom of this ticket entry
	*/
	@Override
	public java.lang.String getEnvBrowserCustom() {
		return _ticketEntry.getEnvBrowserCustom();
	}

	@Override
	public java.lang.String getEnvBrowserLabel() {
		return _ticketEntry.getEnvBrowserLabel();
	}

	@Override
	public java.lang.String getEnvCSLabel() {
		return _ticketEntry.getEnvCSLabel();
	}

	@Override
	public java.lang.String getEnvDBLabel() {
		return _ticketEntry.getEnvDBLabel();
	}

	@Override
	public java.lang.String getEnvJVMLabel() {
		return _ticketEntry.getEnvJVMLabel();
	}

	@Override
	public java.lang.String getEnvLFRLabel() {
		return _ticketEntry.getEnvLFRLabel();
	}

	/**
	* Returns the env name of this ticket entry.
	*
	* @return the env name of this ticket entry
	*/
	@Override
	public java.lang.String getEnvName() {
		return _ticketEntry.getEnvName();
	}

	/**
	* Returns the env os custom of this ticket entry.
	*
	* @return the env os custom of this ticket entry
	*/
	@Override
	public java.lang.String getEnvOSCustom() {
		return _ticketEntry.getEnvOSCustom();
	}

	@Override
	public java.lang.String getEnvOSLabel() {
		return _ticketEntry.getEnvOSLabel();
	}

	/**
	* Returns the env search of this ticket entry.
	*
	* @return the env search of this ticket entry
	*/
	@Override
	public java.lang.String getEnvSearch() {
		return _ticketEntry.getEnvSearch();
	}

	@Override
	public java.lang.String getEscalationLevelLabel() {
		return _ticketEntry.getEscalationLevelLabel();
	}

	/**
	* Returns the language ID of this ticket entry.
	*
	* @return the language ID of this ticket entry
	*/
	@Override
	public java.lang.String getLanguageId() {
		return _ticketEntry.getLanguageId();
	}

	@Override
	public java.lang.String getLanguageLabel() {
		return _ticketEntry.getLanguageLabel();
	}

	/**
	* Returns the reproduction steps of this ticket entry.
	*
	* @return the reproduction steps of this ticket entry
	*/
	@Override
	public java.lang.String getReproductionSteps() {
		return _ticketEntry.getReproductionSteps();
	}

	@Override
	public java.lang.String getResolutionLabel() {
		return _ticketEntry.getResolutionLabel();
	}

	@Override
	public java.lang.String getSeverityLabel() {
		return _ticketEntry.getSeverityLabel();
	}

	@Override
	public java.lang.String getStatusLabel() {
		return _ticketEntry.getStatusLabel();
	}

	/**
	* Returns the subcomponent custom of this ticket entry.
	*
	* @return the subcomponent custom of this ticket entry
	*/
	@Override
	public java.lang.String getSubcomponentCustom() {
		return _ticketEntry.getSubcomponentCustom();
	}

	@Override
	public java.lang.String getSubcomponentLabel() {
		return _ticketEntry.getSubcomponentLabel();
	}

	/**
	* Returns the subject of this ticket entry.
	*
	* @return the subject of this ticket entry
	*/
	@Override
	public java.lang.String getSubject() {
		return _ticketEntry.getSubject();
	}

	@Override
	public java.lang.String getSupportPhaseLabel() {
		return _ticketEntry.getSupportPhaseLabel();
	}

	/**
	* Returns the user name of this ticket entry.
	*
	* @return the user name of this ticket entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _ticketEntry.getUserName();
	}

	/**
	* Returns the user uuid of this ticket entry.
	*
	* @return the user uuid of this ticket entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _ticketEntry.getUserUuid();
	}

	@Override
	public java.lang.String getWeightLabel() {
		return _ticketEntry.getWeightLabel();
	}

	@Override
	public java.lang.String toString() {
		return _ticketEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _ticketEntry.toXmlString();
	}

	/**
	* Returns the closed date of this ticket entry.
	*
	* @return the closed date of this ticket entry
	*/
	@Override
	public Date getClosedDate() {
		return _ticketEntry.getClosedDate();
	}

	/**
	* Returns the create date of this ticket entry.
	*
	* @return the create date of this ticket entry
	*/
	@Override
	public Date getCreateDate() {
		return _ticketEntry.getCreateDate();
	}

	/**
	* Returns the customer modified date of this ticket entry.
	*
	* @return the customer modified date of this ticket entry
	*/
	@Override
	public Date getCustomerModifiedDate() {
		return _ticketEntry.getCustomerModifiedDate();
	}

	/**
	* Returns the due date of this ticket entry.
	*
	* @return the due date of this ticket entry
	*/
	@Override
	public Date getDueDate() {
		return _ticketEntry.getDueDate();
	}

	/**
	* Returns the hold date of this ticket entry.
	*
	* @return the hold date of this ticket entry
	*/
	@Override
	public Date getHoldDate() {
		return _ticketEntry.getHoldDate();
	}

	/**
	* Returns the modified date of this ticket entry.
	*
	* @return the modified date of this ticket entry
	*/
	@Override
	public Date getModifiedDate() {
		return _ticketEntry.getModifiedDate();
	}

	/**
	* Returns the worker modified date of this ticket entry.
	*
	* @return the worker modified date of this ticket entry
	*/
	@Override
	public Date getWorkerModifiedDate() {
		return _ticketEntry.getWorkerModifiedDate();
	}

	@Override
	public java.util.List<java.lang.String> getEnvSearchLabels() {
		return _ticketEntry.getEnvSearchLabels();
	}

	@Override
	public java.util.List<TicketAttachment> getTicketAttachments() {
		return _ticketEntry.getTicketAttachments();
	}

	@Override
	public java.util.List<TicketAttachment> getTicketAttachments(int[] types,
		int[] visibilities) {
		return _ticketEntry.getTicketAttachments(types, visibilities);
	}

	@Override
	public java.util.List<TicketInformation> getTicketInformationList() {
		return _ticketEntry.getTicketInformationList();
	}

	@Override
	public Map<java.lang.Long, java.lang.String> getTicketInformationFieldsMap()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntry.getTicketInformationFieldsMap();
	}

	/**
	* Returns the account entry ID of this ticket entry.
	*
	* @return the account entry ID of this ticket entry
	*/
	@Override
	public long getAccountEntryId() {
		return _ticketEntry.getAccountEntryId();
	}

	/**
	* Returns the company ID of this ticket entry.
	*
	* @return the company ID of this ticket entry
	*/
	@Override
	public long getCompanyId() {
		return _ticketEntry.getCompanyId();
	}

	/**
	* Returns the offering entry ID of this ticket entry.
	*
	* @return the offering entry ID of this ticket entry
	*/
	@Override
	public long getOfferingEntryId() {
		return _ticketEntry.getOfferingEntryId();
	}

	/**
	* Returns the order entry ID of this ticket entry.
	*
	* @return the order entry ID of this ticket entry
	*/
	@Override
	public long getOrderEntryId() {
		return _ticketEntry.getOrderEntryId();
	}

	/**
	* Returns the primary key of this ticket entry.
	*
	* @return the primary key of this ticket entry
	*/
	@Override
	public long getPrimaryKey() {
		return _ticketEntry.getPrimaryKey();
	}

	/**
	* Returns the product entry ID of this ticket entry.
	*
	* @return the product entry ID of this ticket entry
	*/
	@Override
	public long getProductEntryId() {
		return _ticketEntry.getProductEntryId();
	}

	/**
	* Returns the support region ID of this ticket entry.
	*
	* @return the support region ID of this ticket entry
	*/
	@Override
	public long getSupportRegionId() {
		return _ticketEntry.getSupportRegionId();
	}

	/**
	* Returns the support response ID of this ticket entry.
	*
	* @return the support response ID of this ticket entry
	*/
	@Override
	public long getSupportResponseId() {
		return _ticketEntry.getSupportResponseId();
	}

	/**
	* Returns the ticket entry ID of this ticket entry.
	*
	* @return the ticket entry ID of this ticket entry
	*/
	@Override
	public long getTicketEntryId() {
		return _ticketEntry.getTicketEntryId();
	}

	/**
	* Returns the ticket ID of this ticket entry.
	*
	* @return the ticket ID of this ticket entry
	*/
	@Override
	public long getTicketId() {
		return _ticketEntry.getTicketId();
	}

	/**
	* Returns the user ID of this ticket entry.
	*
	* @return the user ID of this ticket entry
	*/
	@Override
	public long getUserId() {
		return _ticketEntry.getUserId();
	}

	@Override
	public void persist() {
		_ticketEntry.persist();
	}

	/**
	* Sets the account entry ID of this ticket entry.
	*
	* @param accountEntryId the account entry ID of this ticket entry
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_ticketEntry.setAccountEntryId(accountEntryId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ticketEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the closed date of this ticket entry.
	*
	* @param closedDate the closed date of this ticket entry
	*/
	@Override
	public void setClosedDate(Date closedDate) {
		_ticketEntry.setClosedDate(closedDate);
	}

	/**
	* Sets the company ID of this ticket entry.
	*
	* @param companyId the company ID of this ticket entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_ticketEntry.setCompanyId(companyId);
	}

	/**
	* Sets the component of this ticket entry.
	*
	* @param component the component of this ticket entry
	*/
	@Override
	public void setComponent(int component) {
		_ticketEntry.setComponent(component);
	}

	/**
	* Sets the create date of this ticket entry.
	*
	* @param createDate the create date of this ticket entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_ticketEntry.setCreateDate(createDate);
	}

	/**
	* Sets the customer modified date of this ticket entry.
	*
	* @param customerModifiedDate the customer modified date of this ticket entry
	*/
	@Override
	public void setCustomerModifiedDate(Date customerModifiedDate) {
		_ticketEntry.setCustomerModifiedDate(customerModifiedDate);
	}

	/**
	* Sets the description of this ticket entry.
	*
	* @param description the description of this ticket entry
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_ticketEntry.setDescription(description);
	}

	/**
	* Sets the due date of this ticket entry.
	*
	* @param dueDate the due date of this ticket entry
	*/
	@Override
	public void setDueDate(Date dueDate) {
		_ticketEntry.setDueDate(dueDate);
	}

	/**
	* Sets the env as of this ticket entry.
	*
	* @param envAS the env as of this ticket entry
	*/
	@Override
	public void setEnvAS(int envAS) {
		_ticketEntry.setEnvAS(envAS);
	}

	/**
	* Sets the env browser of this ticket entry.
	*
	* @param envBrowser the env browser of this ticket entry
	*/
	@Override
	public void setEnvBrowser(int envBrowser) {
		_ticketEntry.setEnvBrowser(envBrowser);
	}

	/**
	* Sets the env browser custom of this ticket entry.
	*
	* @param envBrowserCustom the env browser custom of this ticket entry
	*/
	@Override
	public void setEnvBrowserCustom(java.lang.String envBrowserCustom) {
		_ticketEntry.setEnvBrowserCustom(envBrowserCustom);
	}

	/**
	* Sets the env cs of this ticket entry.
	*
	* @param envCS the env cs of this ticket entry
	*/
	@Override
	public void setEnvCS(int envCS) {
		_ticketEntry.setEnvCS(envCS);
	}

	/**
	* Sets the env db of this ticket entry.
	*
	* @param envDB the env db of this ticket entry
	*/
	@Override
	public void setEnvDB(int envDB) {
		_ticketEntry.setEnvDB(envDB);
	}

	/**
	* Sets the env jvm of this ticket entry.
	*
	* @param envJVM the env jvm of this ticket entry
	*/
	@Override
	public void setEnvJVM(int envJVM) {
		_ticketEntry.setEnvJVM(envJVM);
	}

	/**
	* Sets the env lfr of this ticket entry.
	*
	* @param envLFR the env lfr of this ticket entry
	*/
	@Override
	public void setEnvLFR(int envLFR) {
		_ticketEntry.setEnvLFR(envLFR);
	}

	/**
	* Sets the env name of this ticket entry.
	*
	* @param envName the env name of this ticket entry
	*/
	@Override
	public void setEnvName(java.lang.String envName) {
		_ticketEntry.setEnvName(envName);
	}

	/**
	* Sets the env os of this ticket entry.
	*
	* @param envOS the env os of this ticket entry
	*/
	@Override
	public void setEnvOS(int envOS) {
		_ticketEntry.setEnvOS(envOS);
	}

	/**
	* Sets the env os custom of this ticket entry.
	*
	* @param envOSCustom the env os custom of this ticket entry
	*/
	@Override
	public void setEnvOSCustom(java.lang.String envOSCustom) {
		_ticketEntry.setEnvOSCustom(envOSCustom);
	}

	/**
	* Sets the env search of this ticket entry.
	*
	* @param envSearch the env search of this ticket entry
	*/
	@Override
	public void setEnvSearch(java.lang.String envSearch) {
		_ticketEntry.setEnvSearch(envSearch);
	}

	/**
	* Sets the escalation level of this ticket entry.
	*
	* @param escalationLevel the escalation level of this ticket entry
	*/
	@Override
	public void setEscalationLevel(int escalationLevel) {
		_ticketEntry.setEscalationLevel(escalationLevel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ticketEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ticketEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ticketEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the hold date of this ticket entry.
	*
	* @param holdDate the hold date of this ticket entry
	*/
	@Override
	public void setHoldDate(Date holdDate) {
		_ticketEntry.setHoldDate(holdDate);
	}

	/**
	* Sets whether this ticket entry is ignore due date.
	*
	* @param ignoreDueDate the ignore due date of this ticket entry
	*/
	@Override
	public void setIgnoreDueDate(boolean ignoreDueDate) {
		_ticketEntry.setIgnoreDueDate(ignoreDueDate);
	}

	/**
	* Sets the language ID of this ticket entry.
	*
	* @param languageId the language ID of this ticket entry
	*/
	@Override
	public void setLanguageId(java.lang.String languageId) {
		_ticketEntry.setLanguageId(languageId);
	}

	/**
	* Sets the modified date of this ticket entry.
	*
	* @param modifiedDate the modified date of this ticket entry
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ticketEntry.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ticketEntry.setNew(n);
	}

	/**
	* Sets the offering entry ID of this ticket entry.
	*
	* @param offeringEntryId the offering entry ID of this ticket entry
	*/
	@Override
	public void setOfferingEntryId(long offeringEntryId) {
		_ticketEntry.setOfferingEntryId(offeringEntryId);
	}

	/**
	* Sets the order entry ID of this ticket entry.
	*
	* @param orderEntryId the order entry ID of this ticket entry
	*/
	@Override
	public void setOrderEntryId(long orderEntryId) {
		_ticketEntry.setOrderEntryId(orderEntryId);
	}

	/**
	* Sets the primary key of this ticket entry.
	*
	* @param primaryKey the primary key of this ticket entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_ticketEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ticketEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product entry ID of this ticket entry.
	*
	* @param productEntryId the product entry ID of this ticket entry
	*/
	@Override
	public void setProductEntryId(long productEntryId) {
		_ticketEntry.setProductEntryId(productEntryId);
	}

	/**
	* Sets the reproduction steps of this ticket entry.
	*
	* @param reproductionSteps the reproduction steps of this ticket entry
	*/
	@Override
	public void setReproductionSteps(java.lang.String reproductionSteps) {
		_ticketEntry.setReproductionSteps(reproductionSteps);
	}

	/**
	* Sets the resolution of this ticket entry.
	*
	* @param resolution the resolution of this ticket entry
	*/
	@Override
	public void setResolution(int resolution) {
		_ticketEntry.setResolution(resolution);
	}

	/**
	* Sets the severity of this ticket entry.
	*
	* @param severity the severity of this ticket entry
	*/
	@Override
	public void setSeverity(int severity) {
		_ticketEntry.setSeverity(severity);
	}

	/**
	* Sets the status of this ticket entry.
	*
	* @param status the status of this ticket entry
	*/
	@Override
	public void setStatus(int status) {
		_ticketEntry.setStatus(status);
	}

	/**
	* Sets the subcomponent of this ticket entry.
	*
	* @param subcomponent the subcomponent of this ticket entry
	*/
	@Override
	public void setSubcomponent(int subcomponent) {
		_ticketEntry.setSubcomponent(subcomponent);
	}

	/**
	* Sets the subcomponent custom of this ticket entry.
	*
	* @param subcomponentCustom the subcomponent custom of this ticket entry
	*/
	@Override
	public void setSubcomponentCustom(java.lang.String subcomponentCustom) {
		_ticketEntry.setSubcomponentCustom(subcomponentCustom);
	}

	/**
	* Sets the subject of this ticket entry.
	*
	* @param subject the subject of this ticket entry
	*/
	@Override
	public void setSubject(java.lang.String subject) {
		_ticketEntry.setSubject(subject);
	}

	/**
	* Sets the support region ID of this ticket entry.
	*
	* @param supportRegionId the support region ID of this ticket entry
	*/
	@Override
	public void setSupportRegionId(long supportRegionId) {
		_ticketEntry.setSupportRegionId(supportRegionId);
	}

	/**
	* Sets the support response ID of this ticket entry.
	*
	* @param supportResponseId the support response ID of this ticket entry
	*/
	@Override
	public void setSupportResponseId(long supportResponseId) {
		_ticketEntry.setSupportResponseId(supportResponseId);
	}

	/**
	* Sets the ticket entry ID of this ticket entry.
	*
	* @param ticketEntryId the ticket entry ID of this ticket entry
	*/
	@Override
	public void setTicketEntryId(long ticketEntryId) {
		_ticketEntry.setTicketEntryId(ticketEntryId);
	}

	/**
	* Sets the ticket ID of this ticket entry.
	*
	* @param ticketId the ticket ID of this ticket entry
	*/
	@Override
	public void setTicketId(long ticketId) {
		_ticketEntry.setTicketId(ticketId);
	}

	/**
	* Sets the user ID of this ticket entry.
	*
	* @param userId the user ID of this ticket entry
	*/
	@Override
	public void setUserId(long userId) {
		_ticketEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this ticket entry.
	*
	* @param userName the user name of this ticket entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_ticketEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this ticket entry.
	*
	* @param userUuid the user uuid of this ticket entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_ticketEntry.setUserUuid(userUuid);
	}

	/**
	* Sets the weight of this ticket entry.
	*
	* @param weight the weight of this ticket entry
	*/
	@Override
	public void setWeight(int weight) {
		_ticketEntry.setWeight(weight);
	}

	/**
	* Sets the worker modified date of this ticket entry.
	*
	* @param workerModifiedDate the worker modified date of this ticket entry
	*/
	@Override
	public void setWorkerModifiedDate(Date workerModifiedDate) {
		_ticketEntry.setWorkerModifiedDate(workerModifiedDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketEntryWrapper)) {
			return false;
		}

		TicketEntryWrapper ticketEntryWrapper = (TicketEntryWrapper)obj;

		if (Objects.equals(_ticketEntry, ticketEntryWrapper._ticketEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public TicketEntry getWrappedModel() {
		return _ticketEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ticketEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ticketEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ticketEntry.resetOriginalValues();
	}

	private final TicketEntry _ticketEntry;
}