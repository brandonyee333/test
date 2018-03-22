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
import com.liferay.osb.service.TicketEntryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
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
public class TicketEntryClp extends BaseModelImpl<TicketEntry>
	implements TicketEntry {
	public TicketEntryClp() {
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
	public long getPrimaryKey() {
		return _ticketEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTicketEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getTicketEntryId() {
		return _ticketEntryId;
	}

	@Override
	public void setTicketEntryId(long ticketEntryId) {
		_ticketEntryId = ticketEntryId;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketEntryId", long.class);

				method.invoke(_ticketEntryRemoteModel, ticketEntryId);
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

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_ticketEntryRemoteModel, companyId);
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

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_ticketEntryRemoteModel, userId);
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

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_ticketEntryRemoteModel, userName);
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

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_ticketEntryRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_ticketEntryRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAccountEntryId() {
		return _accountEntryId;
	}

	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryId", long.class);

				method.invoke(_ticketEntryRemoteModel, accountEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getOrderEntryId() {
		return _orderEntryId;
	}

	@Override
	public void setOrderEntryId(long orderEntryId) {
		_orderEntryId = orderEntryId;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setOrderEntryId", long.class);

				method.invoke(_ticketEntryRemoteModel, orderEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getProductEntryId() {
		return _productEntryId;
	}

	@Override
	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setProductEntryId", long.class);

				method.invoke(_ticketEntryRemoteModel, productEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSupportResponseId() {
		return _supportResponseId;
	}

	@Override
	public void setSupportResponseId(long supportResponseId) {
		_supportResponseId = supportResponseId;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportResponseId",
						long.class);

				method.invoke(_ticketEntryRemoteModel, supportResponseId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getOfferingEntryId() {
		return _offeringEntryId;
	}

	@Override
	public void setOfferingEntryId(long offeringEntryId) {
		_offeringEntryId = offeringEntryId;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setOfferingEntryId", long.class);

				method.invoke(_ticketEntryRemoteModel, offeringEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSupportRegionId() {
		return _supportRegionId;
	}

	@Override
	public void setSupportRegionId(long supportRegionId) {
		_supportRegionId = supportRegionId;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportRegionId", long.class);

				method.invoke(_ticketEntryRemoteModel, supportRegionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getLanguageId() {
		return _languageId;
	}

	@Override
	public void setLanguageId(String languageId) {
		_languageId = languageId;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setLanguageId", String.class);

				method.invoke(_ticketEntryRemoteModel, languageId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getTicketId() {
		return _ticketId;
	}

	@Override
	public void setTicketId(long ticketId) {
		_ticketId = ticketId;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketId", long.class);

				method.invoke(_ticketEntryRemoteModel, ticketId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSubject() {
		return _subject;
	}

	@Override
	public void setSubject(String subject) {
		_subject = subject;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSubject", String.class);

				method.invoke(_ticketEntryRemoteModel, subject);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public void setDescription(String description) {
		_description = description;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_ticketEntryRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getReproductionSteps() {
		return _reproductionSteps;
	}

	@Override
	public void setReproductionSteps(String reproductionSteps) {
		_reproductionSteps = reproductionSteps;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setReproductionSteps",
						String.class);

				method.invoke(_ticketEntryRemoteModel, reproductionSteps);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSeverity() {
		return _severity;
	}

	@Override
	public void setSeverity(int severity) {
		_severity = severity;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSeverity", int.class);

				method.invoke(_ticketEntryRemoteModel, severity);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_ticketEntryRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getWeight() {
		return _weight;
	}

	@Override
	public void setWeight(int weight) {
		_weight = weight;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setWeight", int.class);

				method.invoke(_ticketEntryRemoteModel, weight);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getEscalationLevel() {
		return _escalationLevel;
	}

	@Override
	public void setEscalationLevel(int escalationLevel) {
		_escalationLevel = escalationLevel;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setEscalationLevel", int.class);

				method.invoke(_ticketEntryRemoteModel, escalationLevel);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getEnvName() {
		return _envName;
	}

	@Override
	public void setEnvName(String envName) {
		_envName = envName;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvName", String.class);

				method.invoke(_ticketEntryRemoteModel, envName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getEnvOS() {
		return _envOS;
	}

	@Override
	public void setEnvOS(int envOS) {
		_envOS = envOS;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvOS", int.class);

				method.invoke(_ticketEntryRemoteModel, envOS);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getEnvOSCustom() {
		return _envOSCustom;
	}

	@Override
	public void setEnvOSCustom(String envOSCustom) {
		_envOSCustom = envOSCustom;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvOSCustom", String.class);

				method.invoke(_ticketEntryRemoteModel, envOSCustom);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getEnvDB() {
		return _envDB;
	}

	@Override
	public void setEnvDB(int envDB) {
		_envDB = envDB;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvDB", int.class);

				method.invoke(_ticketEntryRemoteModel, envDB);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getEnvJVM() {
		return _envJVM;
	}

	@Override
	public void setEnvJVM(int envJVM) {
		_envJVM = envJVM;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvJVM", int.class);

				method.invoke(_ticketEntryRemoteModel, envJVM);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getEnvAS() {
		return _envAS;
	}

	@Override
	public void setEnvAS(int envAS) {
		_envAS = envAS;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvAS", int.class);

				method.invoke(_ticketEntryRemoteModel, envAS);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getEnvLFR() {
		return _envLFR;
	}

	@Override
	public void setEnvLFR(int envLFR) {
		_envLFR = envLFR;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvLFR", int.class);

				method.invoke(_ticketEntryRemoteModel, envLFR);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getEnvBrowser() {
		return _envBrowser;
	}

	@Override
	public void setEnvBrowser(int envBrowser) {
		_envBrowser = envBrowser;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvBrowser", int.class);

				method.invoke(_ticketEntryRemoteModel, envBrowser);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getEnvBrowserCustom() {
		return _envBrowserCustom;
	}

	@Override
	public void setEnvBrowserCustom(String envBrowserCustom) {
		_envBrowserCustom = envBrowserCustom;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvBrowserCustom",
						String.class);

				method.invoke(_ticketEntryRemoteModel, envBrowserCustom);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getEnvCS() {
		return _envCS;
	}

	@Override
	public void setEnvCS(int envCS) {
		_envCS = envCS;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvCS", int.class);

				method.invoke(_ticketEntryRemoteModel, envCS);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getEnvSearch() {
		return _envSearch;
	}

	@Override
	public void setEnvSearch(String envSearch) {
		_envSearch = envSearch;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvSearch", String.class);

				method.invoke(_ticketEntryRemoteModel, envSearch);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getComponent() {
		return _component;
	}

	@Override
	public void setComponent(int component) {
		_component = component;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setComponent", int.class);

				method.invoke(_ticketEntryRemoteModel, component);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSubcomponent() {
		return _subcomponent;
	}

	@Override
	public void setSubcomponent(int subcomponent) {
		_subcomponent = subcomponent;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSubcomponent", int.class);

				method.invoke(_ticketEntryRemoteModel, subcomponent);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSubcomponentCustom() {
		return _subcomponentCustom;
	}

	@Override
	public void setSubcomponentCustom(String subcomponentCustom) {
		_subcomponentCustom = subcomponentCustom;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSubcomponentCustom",
						String.class);

				method.invoke(_ticketEntryRemoteModel, subcomponentCustom);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getResolution() {
		return _resolution;
	}

	@Override
	public void setResolution(int resolution) {
		_resolution = resolution;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setResolution", int.class);

				method.invoke(_ticketEntryRemoteModel, resolution);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getHoldDate() {
		return _holdDate;
	}

	@Override
	public void setHoldDate(Date holdDate) {
		_holdDate = holdDate;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setHoldDate", Date.class);

				method.invoke(_ticketEntryRemoteModel, holdDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getClosedDate() {
		return _closedDate;
	}

	@Override
	public void setClosedDate(Date closedDate) {
		_closedDate = closedDate;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setClosedDate", Date.class);

				method.invoke(_ticketEntryRemoteModel, closedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getDueDate() {
		return _dueDate;
	}

	@Override
	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setDueDate", Date.class);

				method.invoke(_ticketEntryRemoteModel, dueDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getIgnoreDueDate() {
		return _ignoreDueDate;
	}

	@Override
	public boolean isIgnoreDueDate() {
		return _ignoreDueDate;
	}

	@Override
	public void setIgnoreDueDate(boolean ignoreDueDate) {
		_ignoreDueDate = ignoreDueDate;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setIgnoreDueDate",
						boolean.class);

				method.invoke(_ticketEntryRemoteModel, ignoreDueDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCustomerModifiedDate() {
		return _customerModifiedDate;
	}

	@Override
	public void setCustomerModifiedDate(Date customerModifiedDate) {
		_customerModifiedDate = customerModifiedDate;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCustomerModifiedDate",
						Date.class);

				method.invoke(_ticketEntryRemoteModel, customerModifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getWorkerModifiedDate() {
		return _workerModifiedDate;
	}

	@Override
	public void setWorkerModifiedDate(Date workerModifiedDate) {
		_workerModifiedDate = workerModifiedDate;

		if (_ticketEntryRemoteModel != null) {
			try {
				Class<?> clazz = _ticketEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setWorkerModifiedDate",
						Date.class);

				method.invoke(_ticketEntryRemoteModel, workerModifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public AccountEntry getAccountEntry() {
		try {
			String methodName = "getAccountEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			AccountEntry returnObj = (AccountEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public int getAccountTier() {
		try {
			String methodName = "getAccountTier";

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

	@Override
	public java.lang.String getComponentIcon() {
		try {
			String methodName = "getComponentIcon";

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

	@Override
	public java.lang.String getComponentLabel() {
		try {
			String methodName = "getComponentLabel";

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

	@Override
	public java.lang.String getDisplayId() {
		try {
			String methodName = "getDisplayId";

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

	@Override
	public java.lang.String getEnvASLabel() {
		try {
			String methodName = "getEnvASLabel";

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

	@Override
	public java.lang.String getEnvBrowserLabel() {
		try {
			String methodName = "getEnvBrowserLabel";

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

	@Override
	public java.lang.String getEnvCSLabel() {
		try {
			String methodName = "getEnvCSLabel";

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

	@Override
	public java.lang.String getEnvDBLabel() {
		try {
			String methodName = "getEnvDBLabel";

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

	@Override
	public java.lang.String getEnvJVMLabel() {
		try {
			String methodName = "getEnvJVMLabel";

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

	@Override
	public java.lang.String getEnvLFRLabel() {
		try {
			String methodName = "getEnvLFRLabel";

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

	@Override
	public java.lang.String getEnvOSLabel() {
		try {
			String methodName = "getEnvOSLabel";

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

	@Override
	public java.util.List<java.lang.String> getEnvSearchLabels() {
		try {
			String methodName = "getEnvSearchLabels";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<java.lang.String> returnObj = (java.util.List<java.lang.String>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getEscalationLevelLabel() {
		try {
			String methodName = "getEscalationLevelLabel";

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

	@Override
	public java.lang.String getLanguageLabel() {
		try {
			String methodName = "getLanguageLabel";

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

	@Override
	public OfferingEntry getOfferingEntry() {
		try {
			String methodName = "getOfferingEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			OfferingEntry returnObj = (OfferingEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public OrderEntry getOrderEntry() {
		try {
			String methodName = "getOrderEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			OrderEntry returnObj = (OrderEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public ProductEntry getProductEntry() {
		try {
			String methodName = "getProductEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			ProductEntry returnObj = (ProductEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getResolutionLabel() {
		try {
			String methodName = "getResolutionLabel";

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

	@Override
	public java.lang.String getSeverityLabel() {
		try {
			String methodName = "getSeverityLabel";

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

	@Override
	public java.lang.String getStatusLabel() {
		try {
			String methodName = "getStatusLabel";

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

	@Override
	public java.lang.String getSubcomponentLabel() {
		try {
			String methodName = "getSubcomponentLabel";

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

	@Override
	public java.lang.String getSupportPhaseLabel() {
		try {
			String methodName = "getSupportPhaseLabel";

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

	@Override
	public SupportRegion getSupportRegion() {
		try {
			String methodName = "getSupportRegion";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			SupportRegion returnObj = (SupportRegion)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public SupportResponse getSupportResponse() {
		try {
			String methodName = "getSupportResponse";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			SupportResponse returnObj = (SupportResponse)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.List<TicketAttachment> getTicketAttachments() {
		try {
			String methodName = "getTicketAttachments";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<TicketAttachment> returnObj = (java.util.List<TicketAttachment>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.List<TicketAttachment> getTicketAttachments(int[] types,
		int[] visibilities) {
		try {
			String methodName = "getTicketAttachments";

			Class<?>[] parameterTypes = new Class<?>[] { int.class, int.class };

			Object[] parameterValues = new Object[] { types, visibilities };

			java.util.List<TicketAttachment> returnObj = (java.util.List<TicketAttachment>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public int getTicketAttachmentsCount(int[] visibilities) {
		try {
			String methodName = "getTicketAttachmentsCount";

			Class<?>[] parameterTypes = new Class<?>[] { int.class };

			Object[] parameterValues = new Object[] { visibilities };

			Integer returnObj = (Integer)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public Map<java.lang.Long, java.lang.String> getTicketInformationFieldsMap() {
		try {
			String methodName = "getTicketInformationFieldsMap";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Map<java.lang.Long, java.lang.String> returnObj = (Map<java.lang.Long, java.lang.String>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.List<TicketInformation> getTicketInformationList() {
		try {
			String methodName = "getTicketInformationList";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<TicketInformation> returnObj = (java.util.List<TicketInformation>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getWeightLabel() {
		try {
			String methodName = "getWeightLabel";

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

	@Override
	public double getWork() {
		try {
			String methodName = "getWork";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Double returnObj = (Double)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public boolean isPendingCustomer() {
		try {
			String methodName = "isPendingCustomer";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public boolean isPendingLiferay() {
		try {
			String methodName = "isPendingLiferay";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public boolean isPendingPartner() {
		try {
			String methodName = "isPendingPartner";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getTicketEntryRemoteModel() {
		return _ticketEntryRemoteModel;
	}

	public void setTicketEntryRemoteModel(BaseModel<?> ticketEntryRemoteModel) {
		_ticketEntryRemoteModel = ticketEntryRemoteModel;
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

		Class<?> remoteModelClass = _ticketEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_ticketEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			TicketEntryLocalServiceUtil.addTicketEntry(this);
		}
		else {
			TicketEntryLocalServiceUtil.updateTicketEntry(this);
		}
	}

	@Override
	public TicketEntry toEscapedModel() {
		return (TicketEntry)ProxyUtil.newProxyInstance(TicketEntry.class.getClassLoader(),
			new Class[] { TicketEntry.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		TicketEntryClp clone = new TicketEntryClp();

		clone.setTicketEntryId(getTicketEntryId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAccountEntryId(getAccountEntryId());
		clone.setOrderEntryId(getOrderEntryId());
		clone.setProductEntryId(getProductEntryId());
		clone.setSupportResponseId(getSupportResponseId());
		clone.setOfferingEntryId(getOfferingEntryId());
		clone.setSupportRegionId(getSupportRegionId());
		clone.setLanguageId(getLanguageId());
		clone.setTicketId(getTicketId());
		clone.setSubject(getSubject());
		clone.setDescription(getDescription());
		clone.setReproductionSteps(getReproductionSteps());
		clone.setSeverity(getSeverity());
		clone.setStatus(getStatus());
		clone.setWeight(getWeight());
		clone.setEscalationLevel(getEscalationLevel());
		clone.setEnvName(getEnvName());
		clone.setEnvOS(getEnvOS());
		clone.setEnvOSCustom(getEnvOSCustom());
		clone.setEnvDB(getEnvDB());
		clone.setEnvJVM(getEnvJVM());
		clone.setEnvAS(getEnvAS());
		clone.setEnvLFR(getEnvLFR());
		clone.setEnvBrowser(getEnvBrowser());
		clone.setEnvBrowserCustom(getEnvBrowserCustom());
		clone.setEnvCS(getEnvCS());
		clone.setEnvSearch(getEnvSearch());
		clone.setComponent(getComponent());
		clone.setSubcomponent(getSubcomponent());
		clone.setSubcomponentCustom(getSubcomponentCustom());
		clone.setResolution(getResolution());
		clone.setHoldDate(getHoldDate());
		clone.setClosedDate(getClosedDate());
		clone.setDueDate(getDueDate());
		clone.setIgnoreDueDate(getIgnoreDueDate());
		clone.setCustomerModifiedDate(getCustomerModifiedDate());
		clone.setWorkerModifiedDate(getWorkerModifiedDate());

		return clone;
	}

	@Override
	public int compareTo(TicketEntry ticketEntry) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				ticketEntry.getModifiedDate());

		value = value * -1;

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

		if (!(obj instanceof TicketEntryClp)) {
			return false;
		}

		TicketEntryClp ticketEntry = (TicketEntryClp)obj;

		long primaryKey = ticketEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(85);

		sb.append("{ticketEntryId=");
		sb.append(getTicketEntryId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", accountEntryId=");
		sb.append(getAccountEntryId());
		sb.append(", orderEntryId=");
		sb.append(getOrderEntryId());
		sb.append(", productEntryId=");
		sb.append(getProductEntryId());
		sb.append(", supportResponseId=");
		sb.append(getSupportResponseId());
		sb.append(", offeringEntryId=");
		sb.append(getOfferingEntryId());
		sb.append(", supportRegionId=");
		sb.append(getSupportRegionId());
		sb.append(", languageId=");
		sb.append(getLanguageId());
		sb.append(", ticketId=");
		sb.append(getTicketId());
		sb.append(", subject=");
		sb.append(getSubject());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", reproductionSteps=");
		sb.append(getReproductionSteps());
		sb.append(", severity=");
		sb.append(getSeverity());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", weight=");
		sb.append(getWeight());
		sb.append(", escalationLevel=");
		sb.append(getEscalationLevel());
		sb.append(", envName=");
		sb.append(getEnvName());
		sb.append(", envOS=");
		sb.append(getEnvOS());
		sb.append(", envOSCustom=");
		sb.append(getEnvOSCustom());
		sb.append(", envDB=");
		sb.append(getEnvDB());
		sb.append(", envJVM=");
		sb.append(getEnvJVM());
		sb.append(", envAS=");
		sb.append(getEnvAS());
		sb.append(", envLFR=");
		sb.append(getEnvLFR());
		sb.append(", envBrowser=");
		sb.append(getEnvBrowser());
		sb.append(", envBrowserCustom=");
		sb.append(getEnvBrowserCustom());
		sb.append(", envCS=");
		sb.append(getEnvCS());
		sb.append(", envSearch=");
		sb.append(getEnvSearch());
		sb.append(", component=");
		sb.append(getComponent());
		sb.append(", subcomponent=");
		sb.append(getSubcomponent());
		sb.append(", subcomponentCustom=");
		sb.append(getSubcomponentCustom());
		sb.append(", resolution=");
		sb.append(getResolution());
		sb.append(", holdDate=");
		sb.append(getHoldDate());
		sb.append(", closedDate=");
		sb.append(getClosedDate());
		sb.append(", dueDate=");
		sb.append(getDueDate());
		sb.append(", ignoreDueDate=");
		sb.append(getIgnoreDueDate());
		sb.append(", customerModifiedDate=");
		sb.append(getCustomerModifiedDate());
		sb.append(", workerModifiedDate=");
		sb.append(getWorkerModifiedDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(130);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TicketEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>ticketEntryId</column-name><column-value><![CDATA[");
		sb.append(getTicketEntryId());
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
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountEntryId</column-name><column-value><![CDATA[");
		sb.append(getAccountEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>orderEntryId</column-name><column-value><![CDATA[");
		sb.append(getOrderEntryId());
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
			"<column><column-name>offeringEntryId</column-name><column-value><![CDATA[");
		sb.append(getOfferingEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportRegionId</column-name><column-value><![CDATA[");
		sb.append(getSupportRegionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>languageId</column-name><column-value><![CDATA[");
		sb.append(getLanguageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ticketId</column-name><column-value><![CDATA[");
		sb.append(getTicketId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>subject</column-name><column-value><![CDATA[");
		sb.append(getSubject());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reproductionSteps</column-name><column-value><![CDATA[");
		sb.append(getReproductionSteps());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>severity</column-name><column-value><![CDATA[");
		sb.append(getSeverity());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>weight</column-name><column-value><![CDATA[");
		sb.append(getWeight());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>escalationLevel</column-name><column-value><![CDATA[");
		sb.append(getEscalationLevel());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>envName</column-name><column-value><![CDATA[");
		sb.append(getEnvName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>envOS</column-name><column-value><![CDATA[");
		sb.append(getEnvOS());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>envOSCustom</column-name><column-value><![CDATA[");
		sb.append(getEnvOSCustom());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>envDB</column-name><column-value><![CDATA[");
		sb.append(getEnvDB());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>envJVM</column-name><column-value><![CDATA[");
		sb.append(getEnvJVM());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>envAS</column-name><column-value><![CDATA[");
		sb.append(getEnvAS());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>envLFR</column-name><column-value><![CDATA[");
		sb.append(getEnvLFR());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>envBrowser</column-name><column-value><![CDATA[");
		sb.append(getEnvBrowser());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>envBrowserCustom</column-name><column-value><![CDATA[");
		sb.append(getEnvBrowserCustom());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>envCS</column-name><column-value><![CDATA[");
		sb.append(getEnvCS());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>envSearch</column-name><column-value><![CDATA[");
		sb.append(getEnvSearch());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>component</column-name><column-value><![CDATA[");
		sb.append(getComponent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>subcomponent</column-name><column-value><![CDATA[");
		sb.append(getSubcomponent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>subcomponentCustom</column-name><column-value><![CDATA[");
		sb.append(getSubcomponentCustom());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>resolution</column-name><column-value><![CDATA[");
		sb.append(getResolution());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>holdDate</column-name><column-value><![CDATA[");
		sb.append(getHoldDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>closedDate</column-name><column-value><![CDATA[");
		sb.append(getClosedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dueDate</column-name><column-value><![CDATA[");
		sb.append(getDueDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ignoreDueDate</column-name><column-value><![CDATA[");
		sb.append(getIgnoreDueDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>customerModifiedDate</column-name><column-value><![CDATA[");
		sb.append(getCustomerModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workerModifiedDate</column-name><column-value><![CDATA[");
		sb.append(getWorkerModifiedDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
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
	private BaseModel<?> _ticketEntryRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}