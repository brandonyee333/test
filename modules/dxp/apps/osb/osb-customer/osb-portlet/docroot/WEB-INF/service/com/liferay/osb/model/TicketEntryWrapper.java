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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link TicketEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketEntry
 * @generated
 */
public class TicketEntryWrapper implements TicketEntry,
	ModelWrapper<TicketEntry> {
	public TicketEntryWrapper(TicketEntry ticketEntry) {
		_ticketEntry = ticketEntry;
	}

	public Class<?> getModelClass() {
		return TicketEntry.class;
	}

	public String getModelClassName() {
		return TicketEntry.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketEntryId", getTicketEntryId());
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

	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
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

	/**
	* Returns the primary key of this ticket entry.
	*
	* @return the primary key of this ticket entry
	*/
	public long getPrimaryKey() {
		return _ticketEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this ticket entry.
	*
	* @param primaryKey the primary key of this ticket entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_ticketEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ticket entry ID of this ticket entry.
	*
	* @return the ticket entry ID of this ticket entry
	*/
	public long getTicketEntryId() {
		return _ticketEntry.getTicketEntryId();
	}

	/**
	* Sets the ticket entry ID of this ticket entry.
	*
	* @param ticketEntryId the ticket entry ID of this ticket entry
	*/
	public void setTicketEntryId(long ticketEntryId) {
		_ticketEntry.setTicketEntryId(ticketEntryId);
	}

	/**
	* Returns the user ID of this ticket entry.
	*
	* @return the user ID of this ticket entry
	*/
	public long getUserId() {
		return _ticketEntry.getUserId();
	}

	/**
	* Sets the user ID of this ticket entry.
	*
	* @param userId the user ID of this ticket entry
	*/
	public void setUserId(long userId) {
		_ticketEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this ticket entry.
	*
	* @return the user uuid of this ticket entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this ticket entry.
	*
	* @param userUuid the user uuid of this ticket entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_ticketEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this ticket entry.
	*
	* @return the user name of this ticket entry
	*/
	public java.lang.String getUserName() {
		return _ticketEntry.getUserName();
	}

	/**
	* Sets the user name of this ticket entry.
	*
	* @param userName the user name of this ticket entry
	*/
	public void setUserName(java.lang.String userName) {
		_ticketEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this ticket entry.
	*
	* @return the create date of this ticket entry
	*/
	public java.util.Date getCreateDate() {
		return _ticketEntry.getCreateDate();
	}

	/**
	* Sets the create date of this ticket entry.
	*
	* @param createDate the create date of this ticket entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_ticketEntry.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this ticket entry.
	*
	* @return the modified date of this ticket entry
	*/
	public java.util.Date getModifiedDate() {
		return _ticketEntry.getModifiedDate();
	}

	/**
	* Sets the modified date of this ticket entry.
	*
	* @param modifiedDate the modified date of this ticket entry
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_ticketEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the account entry ID of this ticket entry.
	*
	* @return the account entry ID of this ticket entry
	*/
	public long getAccountEntryId() {
		return _ticketEntry.getAccountEntryId();
	}

	/**
	* Sets the account entry ID of this ticket entry.
	*
	* @param accountEntryId the account entry ID of this ticket entry
	*/
	public void setAccountEntryId(long accountEntryId) {
		_ticketEntry.setAccountEntryId(accountEntryId);
	}

	/**
	* Returns the order entry ID of this ticket entry.
	*
	* @return the order entry ID of this ticket entry
	*/
	public long getOrderEntryId() {
		return _ticketEntry.getOrderEntryId();
	}

	/**
	* Sets the order entry ID of this ticket entry.
	*
	* @param orderEntryId the order entry ID of this ticket entry
	*/
	public void setOrderEntryId(long orderEntryId) {
		_ticketEntry.setOrderEntryId(orderEntryId);
	}

	/**
	* Returns the product entry ID of this ticket entry.
	*
	* @return the product entry ID of this ticket entry
	*/
	public long getProductEntryId() {
		return _ticketEntry.getProductEntryId();
	}

	/**
	* Sets the product entry ID of this ticket entry.
	*
	* @param productEntryId the product entry ID of this ticket entry
	*/
	public void setProductEntryId(long productEntryId) {
		_ticketEntry.setProductEntryId(productEntryId);
	}

	/**
	* Returns the support response ID of this ticket entry.
	*
	* @return the support response ID of this ticket entry
	*/
	public long getSupportResponseId() {
		return _ticketEntry.getSupportResponseId();
	}

	/**
	* Sets the support response ID of this ticket entry.
	*
	* @param supportResponseId the support response ID of this ticket entry
	*/
	public void setSupportResponseId(long supportResponseId) {
		_ticketEntry.setSupportResponseId(supportResponseId);
	}

	/**
	* Returns the offering entry ID of this ticket entry.
	*
	* @return the offering entry ID of this ticket entry
	*/
	public long getOfferingEntryId() {
		return _ticketEntry.getOfferingEntryId();
	}

	/**
	* Sets the offering entry ID of this ticket entry.
	*
	* @param offeringEntryId the offering entry ID of this ticket entry
	*/
	public void setOfferingEntryId(long offeringEntryId) {
		_ticketEntry.setOfferingEntryId(offeringEntryId);
	}

	/**
	* Returns the support region ID of this ticket entry.
	*
	* @return the support region ID of this ticket entry
	*/
	public long getSupportRegionId() {
		return _ticketEntry.getSupportRegionId();
	}

	/**
	* Sets the support region ID of this ticket entry.
	*
	* @param supportRegionId the support region ID of this ticket entry
	*/
	public void setSupportRegionId(long supportRegionId) {
		_ticketEntry.setSupportRegionId(supportRegionId);
	}

	/**
	* Returns the language ID of this ticket entry.
	*
	* @return the language ID of this ticket entry
	*/
	public java.lang.String getLanguageId() {
		return _ticketEntry.getLanguageId();
	}

	/**
	* Sets the language ID of this ticket entry.
	*
	* @param languageId the language ID of this ticket entry
	*/
	public void setLanguageId(java.lang.String languageId) {
		_ticketEntry.setLanguageId(languageId);
	}

	/**
	* Returns the ticket ID of this ticket entry.
	*
	* @return the ticket ID of this ticket entry
	*/
	public long getTicketId() {
		return _ticketEntry.getTicketId();
	}

	/**
	* Sets the ticket ID of this ticket entry.
	*
	* @param ticketId the ticket ID of this ticket entry
	*/
	public void setTicketId(long ticketId) {
		_ticketEntry.setTicketId(ticketId);
	}

	/**
	* Returns the subject of this ticket entry.
	*
	* @return the subject of this ticket entry
	*/
	public java.lang.String getSubject() {
		return _ticketEntry.getSubject();
	}

	/**
	* Sets the subject of this ticket entry.
	*
	* @param subject the subject of this ticket entry
	*/
	public void setSubject(java.lang.String subject) {
		_ticketEntry.setSubject(subject);
	}

	/**
	* Returns the description of this ticket entry.
	*
	* @return the description of this ticket entry
	*/
	public java.lang.String getDescription() {
		return _ticketEntry.getDescription();
	}

	/**
	* Sets the description of this ticket entry.
	*
	* @param description the description of this ticket entry
	*/
	public void setDescription(java.lang.String description) {
		_ticketEntry.setDescription(description);
	}

	/**
	* Returns the reproduction steps of this ticket entry.
	*
	* @return the reproduction steps of this ticket entry
	*/
	public java.lang.String getReproductionSteps() {
		return _ticketEntry.getReproductionSteps();
	}

	/**
	* Sets the reproduction steps of this ticket entry.
	*
	* @param reproductionSteps the reproduction steps of this ticket entry
	*/
	public void setReproductionSteps(java.lang.String reproductionSteps) {
		_ticketEntry.setReproductionSteps(reproductionSteps);
	}

	/**
	* Returns the severity of this ticket entry.
	*
	* @return the severity of this ticket entry
	*/
	public int getSeverity() {
		return _ticketEntry.getSeverity();
	}

	/**
	* Sets the severity of this ticket entry.
	*
	* @param severity the severity of this ticket entry
	*/
	public void setSeverity(int severity) {
		_ticketEntry.setSeverity(severity);
	}

	/**
	* Returns the status of this ticket entry.
	*
	* @return the status of this ticket entry
	*/
	public int getStatus() {
		return _ticketEntry.getStatus();
	}

	/**
	* Sets the status of this ticket entry.
	*
	* @param status the status of this ticket entry
	*/
	public void setStatus(int status) {
		_ticketEntry.setStatus(status);
	}

	/**
	* Returns the weight of this ticket entry.
	*
	* @return the weight of this ticket entry
	*/
	public int getWeight() {
		return _ticketEntry.getWeight();
	}

	/**
	* Sets the weight of this ticket entry.
	*
	* @param weight the weight of this ticket entry
	*/
	public void setWeight(int weight) {
		_ticketEntry.setWeight(weight);
	}

	/**
	* Returns the escalation level of this ticket entry.
	*
	* @return the escalation level of this ticket entry
	*/
	public int getEscalationLevel() {
		return _ticketEntry.getEscalationLevel();
	}

	/**
	* Sets the escalation level of this ticket entry.
	*
	* @param escalationLevel the escalation level of this ticket entry
	*/
	public void setEscalationLevel(int escalationLevel) {
		_ticketEntry.setEscalationLevel(escalationLevel);
	}

	/**
	* Returns the env name of this ticket entry.
	*
	* @return the env name of this ticket entry
	*/
	public java.lang.String getEnvName() {
		return _ticketEntry.getEnvName();
	}

	/**
	* Sets the env name of this ticket entry.
	*
	* @param envName the env name of this ticket entry
	*/
	public void setEnvName(java.lang.String envName) {
		_ticketEntry.setEnvName(envName);
	}

	/**
	* Returns the env o s of this ticket entry.
	*
	* @return the env o s of this ticket entry
	*/
	public int getEnvOS() {
		return _ticketEntry.getEnvOS();
	}

	/**
	* Sets the env o s of this ticket entry.
	*
	* @param envOS the env o s of this ticket entry
	*/
	public void setEnvOS(int envOS) {
		_ticketEntry.setEnvOS(envOS);
	}

	/**
	* Returns the env o s custom of this ticket entry.
	*
	* @return the env o s custom of this ticket entry
	*/
	public java.lang.String getEnvOSCustom() {
		return _ticketEntry.getEnvOSCustom();
	}

	/**
	* Sets the env o s custom of this ticket entry.
	*
	* @param envOSCustom the env o s custom of this ticket entry
	*/
	public void setEnvOSCustom(java.lang.String envOSCustom) {
		_ticketEntry.setEnvOSCustom(envOSCustom);
	}

	/**
	* Returns the env d b of this ticket entry.
	*
	* @return the env d b of this ticket entry
	*/
	public int getEnvDB() {
		return _ticketEntry.getEnvDB();
	}

	/**
	* Sets the env d b of this ticket entry.
	*
	* @param envDB the env d b of this ticket entry
	*/
	public void setEnvDB(int envDB) {
		_ticketEntry.setEnvDB(envDB);
	}

	/**
	* Returns the env j v m of this ticket entry.
	*
	* @return the env j v m of this ticket entry
	*/
	public int getEnvJVM() {
		return _ticketEntry.getEnvJVM();
	}

	/**
	* Sets the env j v m of this ticket entry.
	*
	* @param envJVM the env j v m of this ticket entry
	*/
	public void setEnvJVM(int envJVM) {
		_ticketEntry.setEnvJVM(envJVM);
	}

	/**
	* Returns the env a s of this ticket entry.
	*
	* @return the env a s of this ticket entry
	*/
	public int getEnvAS() {
		return _ticketEntry.getEnvAS();
	}

	/**
	* Sets the env a s of this ticket entry.
	*
	* @param envAS the env a s of this ticket entry
	*/
	public void setEnvAS(int envAS) {
		_ticketEntry.setEnvAS(envAS);
	}

	/**
	* Returns the env l f r of this ticket entry.
	*
	* @return the env l f r of this ticket entry
	*/
	public int getEnvLFR() {
		return _ticketEntry.getEnvLFR();
	}

	/**
	* Sets the env l f r of this ticket entry.
	*
	* @param envLFR the env l f r of this ticket entry
	*/
	public void setEnvLFR(int envLFR) {
		_ticketEntry.setEnvLFR(envLFR);
	}

	/**
	* Returns the env browser of this ticket entry.
	*
	* @return the env browser of this ticket entry
	*/
	public int getEnvBrowser() {
		return _ticketEntry.getEnvBrowser();
	}

	/**
	* Sets the env browser of this ticket entry.
	*
	* @param envBrowser the env browser of this ticket entry
	*/
	public void setEnvBrowser(int envBrowser) {
		_ticketEntry.setEnvBrowser(envBrowser);
	}

	/**
	* Returns the env browser custom of this ticket entry.
	*
	* @return the env browser custom of this ticket entry
	*/
	public java.lang.String getEnvBrowserCustom() {
		return _ticketEntry.getEnvBrowserCustom();
	}

	/**
	* Sets the env browser custom of this ticket entry.
	*
	* @param envBrowserCustom the env browser custom of this ticket entry
	*/
	public void setEnvBrowserCustom(java.lang.String envBrowserCustom) {
		_ticketEntry.setEnvBrowserCustom(envBrowserCustom);
	}

	/**
	* Returns the env c s of this ticket entry.
	*
	* @return the env c s of this ticket entry
	*/
	public int getEnvCS() {
		return _ticketEntry.getEnvCS();
	}

	/**
	* Sets the env c s of this ticket entry.
	*
	* @param envCS the env c s of this ticket entry
	*/
	public void setEnvCS(int envCS) {
		_ticketEntry.setEnvCS(envCS);
	}

	/**
	* Returns the env search of this ticket entry.
	*
	* @return the env search of this ticket entry
	*/
	public java.lang.String getEnvSearch() {
		return _ticketEntry.getEnvSearch();
	}

	/**
	* Sets the env search of this ticket entry.
	*
	* @param envSearch the env search of this ticket entry
	*/
	public void setEnvSearch(java.lang.String envSearch) {
		_ticketEntry.setEnvSearch(envSearch);
	}

	/**
	* Returns the component of this ticket entry.
	*
	* @return the component of this ticket entry
	*/
	public int getComponent() {
		return _ticketEntry.getComponent();
	}

	/**
	* Sets the component of this ticket entry.
	*
	* @param component the component of this ticket entry
	*/
	public void setComponent(int component) {
		_ticketEntry.setComponent(component);
	}

	/**
	* Returns the subcomponent of this ticket entry.
	*
	* @return the subcomponent of this ticket entry
	*/
	public int getSubcomponent() {
		return _ticketEntry.getSubcomponent();
	}

	/**
	* Sets the subcomponent of this ticket entry.
	*
	* @param subcomponent the subcomponent of this ticket entry
	*/
	public void setSubcomponent(int subcomponent) {
		_ticketEntry.setSubcomponent(subcomponent);
	}

	/**
	* Returns the subcomponent custom of this ticket entry.
	*
	* @return the subcomponent custom of this ticket entry
	*/
	public java.lang.String getSubcomponentCustom() {
		return _ticketEntry.getSubcomponentCustom();
	}

	/**
	* Sets the subcomponent custom of this ticket entry.
	*
	* @param subcomponentCustom the subcomponent custom of this ticket entry
	*/
	public void setSubcomponentCustom(java.lang.String subcomponentCustom) {
		_ticketEntry.setSubcomponentCustom(subcomponentCustom);
	}

	/**
	* Returns the resolution of this ticket entry.
	*
	* @return the resolution of this ticket entry
	*/
	public int getResolution() {
		return _ticketEntry.getResolution();
	}

	/**
	* Sets the resolution of this ticket entry.
	*
	* @param resolution the resolution of this ticket entry
	*/
	public void setResolution(int resolution) {
		_ticketEntry.setResolution(resolution);
	}

	/**
	* Returns the hold date of this ticket entry.
	*
	* @return the hold date of this ticket entry
	*/
	public java.util.Date getHoldDate() {
		return _ticketEntry.getHoldDate();
	}

	/**
	* Sets the hold date of this ticket entry.
	*
	* @param holdDate the hold date of this ticket entry
	*/
	public void setHoldDate(java.util.Date holdDate) {
		_ticketEntry.setHoldDate(holdDate);
	}

	/**
	* Returns the closed date of this ticket entry.
	*
	* @return the closed date of this ticket entry
	*/
	public java.util.Date getClosedDate() {
		return _ticketEntry.getClosedDate();
	}

	/**
	* Sets the closed date of this ticket entry.
	*
	* @param closedDate the closed date of this ticket entry
	*/
	public void setClosedDate(java.util.Date closedDate) {
		_ticketEntry.setClosedDate(closedDate);
	}

	/**
	* Returns the due date of this ticket entry.
	*
	* @return the due date of this ticket entry
	*/
	public java.util.Date getDueDate() {
		return _ticketEntry.getDueDate();
	}

	/**
	* Sets the due date of this ticket entry.
	*
	* @param dueDate the due date of this ticket entry
	*/
	public void setDueDate(java.util.Date dueDate) {
		_ticketEntry.setDueDate(dueDate);
	}

	/**
	* Returns the ignore due date of this ticket entry.
	*
	* @return the ignore due date of this ticket entry
	*/
	public boolean getIgnoreDueDate() {
		return _ticketEntry.getIgnoreDueDate();
	}

	/**
	* Returns <code>true</code> if this ticket entry is ignore due date.
	*
	* @return <code>true</code> if this ticket entry is ignore due date; <code>false</code> otherwise
	*/
	public boolean isIgnoreDueDate() {
		return _ticketEntry.isIgnoreDueDate();
	}

	/**
	* Sets whether this ticket entry is ignore due date.
	*
	* @param ignoreDueDate the ignore due date of this ticket entry
	*/
	public void setIgnoreDueDate(boolean ignoreDueDate) {
		_ticketEntry.setIgnoreDueDate(ignoreDueDate);
	}

	/**
	* Returns the customer modified date of this ticket entry.
	*
	* @return the customer modified date of this ticket entry
	*/
	public java.util.Date getCustomerModifiedDate() {
		return _ticketEntry.getCustomerModifiedDate();
	}

	/**
	* Sets the customer modified date of this ticket entry.
	*
	* @param customerModifiedDate the customer modified date of this ticket entry
	*/
	public void setCustomerModifiedDate(java.util.Date customerModifiedDate) {
		_ticketEntry.setCustomerModifiedDate(customerModifiedDate);
	}

	/**
	* Returns the worker modified date of this ticket entry.
	*
	* @return the worker modified date of this ticket entry
	*/
	public java.util.Date getWorkerModifiedDate() {
		return _ticketEntry.getWorkerModifiedDate();
	}

	/**
	* Sets the worker modified date of this ticket entry.
	*
	* @param workerModifiedDate the worker modified date of this ticket entry
	*/
	public void setWorkerModifiedDate(java.util.Date workerModifiedDate) {
		_ticketEntry.setWorkerModifiedDate(workerModifiedDate);
	}

	public boolean isNew() {
		return _ticketEntry.isNew();
	}

	public void setNew(boolean n) {
		_ticketEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _ticketEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_ticketEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _ticketEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _ticketEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_ticketEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _ticketEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_ticketEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TicketEntryWrapper((TicketEntry)_ticketEntry.clone());
	}

	public int compareTo(com.liferay.osb.model.TicketEntry ticketEntry) {
		return _ticketEntry.compareTo(ticketEntry);
	}

	@Override
	public int hashCode() {
		return _ticketEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TicketEntry> toCacheModel() {
		return _ticketEntry.toCacheModel();
	}

	public com.liferay.osb.model.TicketEntry toEscapedModel() {
		return new TicketEntryWrapper(_ticketEntry.toEscapedModel());
	}

	public com.liferay.osb.model.TicketEntry toUnescapedModel() {
		return new TicketEntryWrapper(_ticketEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ticketEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _ticketEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_ticketEntry.persist();
	}

	public com.liferay.osb.model.AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.getAccountEntry();
	}

	public int getAccountTier()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.getAccountTier();
	}

	public java.lang.String getComponentIcon() {
		return _ticketEntry.getComponentIcon();
	}

	public java.lang.String getComponentLabel() {
		return _ticketEntry.getComponentLabel();
	}

	public java.lang.String getDisplayId()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.getDisplayId();
	}

	public java.lang.String getEnvASLabel() {
		return _ticketEntry.getEnvASLabel();
	}

	public java.lang.String getEnvBrowserLabel() {
		return _ticketEntry.getEnvBrowserLabel();
	}

	public java.lang.String getEnvCSLabel() {
		return _ticketEntry.getEnvCSLabel();
	}

	public java.lang.String getEnvDBLabel() {
		return _ticketEntry.getEnvDBLabel();
	}

	public java.lang.String getEnvJVMLabel() {
		return _ticketEntry.getEnvJVMLabel();
	}

	public java.lang.String getEnvLFRLabel() {
		return _ticketEntry.getEnvLFRLabel();
	}

	public java.lang.String getEnvOSLabel() {
		return _ticketEntry.getEnvOSLabel();
	}

	public java.util.List<java.lang.String> getEnvSearchLabels() {
		return _ticketEntry.getEnvSearchLabels();
	}

	public java.lang.String getEscalationLevelLabel() {
		return _ticketEntry.getEscalationLevelLabel();
	}

	public java.lang.String getLanguageLabel() {
		return _ticketEntry.getLanguageLabel();
	}

	public com.liferay.osb.model.OfferingEntry getOfferingEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.getOfferingEntry();
	}

	public com.liferay.osb.model.OrderEntry getOrderEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.getOrderEntry();
	}

	public com.liferay.osb.model.ProductEntry getProductEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.getProductEntry();
	}

	public java.lang.String getResolutionLabel() {
		return _ticketEntry.getResolutionLabel();
	}

	public java.lang.String getSeverityLabel() {
		return _ticketEntry.getSeverityLabel();
	}

	public java.lang.String getStatusLabel() {
		return _ticketEntry.getStatusLabel();
	}

	public java.lang.String getSubcomponentLabel() {
		return _ticketEntry.getSubcomponentLabel();
	}

	public java.lang.String getSupportPhaseLabel() {
		return _ticketEntry.getSupportPhaseLabel();
	}

	public com.liferay.osb.model.SupportRegion getSupportRegion()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.getSupportRegion();
	}

	public com.liferay.osb.model.SupportResponse getSupportResponse()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.getSupportResponse();
	}

	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.getTicketAttachments();
	}

	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		int[] types, int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.getTicketAttachments(types, visibilities);
	}

	public int getTicketAttachmentsCount(int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.getTicketAttachmentsCount(visibilities);
	}

	public java.util.Map<java.lang.Long, java.lang.String> getTicketInformationFieldsMap()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.getTicketInformationFieldsMap();
	}

	public java.util.List<com.liferay.osb.model.TicketInformation> getTicketInformationList()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.getTicketInformationList();
	}

	public java.lang.String getWeightLabel() {
		return _ticketEntry.getWeightLabel();
	}

	public double getWork()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.getWork();
	}

	public boolean hasParticipant(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.hasParticipant(userId);
	}

	public boolean isPendingCustomer()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.isPendingCustomer();
	}

	public boolean isPendingLiferay()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.isPendingLiferay();
	}

	public boolean isPendingPartner()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntry.isPendingPartner();
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

		if (Validator.equals(_ticketEntry, ticketEntryWrapper._ticketEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TicketEntry getWrappedTicketEntry() {
		return _ticketEntry;
	}

	public TicketEntry getWrappedModel() {
		return _ticketEntry;
	}

	public void resetOriginalValues() {
		_ticketEntry.resetOriginalValues();
	}

	private TicketEntry _ticketEntry;
}