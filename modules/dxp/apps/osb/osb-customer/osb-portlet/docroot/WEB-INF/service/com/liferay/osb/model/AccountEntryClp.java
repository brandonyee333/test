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

import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

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
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class AccountEntryClp extends BaseModelImpl<AccountEntry>
	implements AccountEntry {
	public AccountEntryClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return AccountEntry.class;
	}

	@Override
	public String getModelClassName() {
		return AccountEntry.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _accountEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedUserId", getModifiedUserId());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("corpProjectUuid", getCorpProjectUuid());
		attributes.put("corpProjectId", getCorpProjectId());
		attributes.put("corpEntryName", getCorpEntryName());
		attributes.put("name", getName());
		attributes.put("code", getCode());
		attributes.put("redirectAccountEntryId", getRedirectAccountEntryId());
		attributes.put("type", getType());
		attributes.put("industry", getIndustry());
		attributes.put("countryId", getCountryId());
		attributes.put("partnerEntryId", getPartnerEntryId());
		attributes.put("partnerManagedSupport", getPartnerManagedSupport());
		attributes.put("tier", getTier());
		attributes.put("maxCustomers", getMaxCustomers());
		attributes.put("instructions", getInstructions());
		attributes.put("notes", getNotes());
		attributes.put("highestSupportResponseId", getHighestSupportResponseId());
		attributes.put("lastAuditDate", getLastAuditDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("statusMessage", getStatusMessage());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
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

		Long modifiedUserId = (Long)attributes.get("modifiedUserId");

		if (modifiedUserId != null) {
			setModifiedUserId(modifiedUserId);
		}

		String modifiedUserName = (String)attributes.get("modifiedUserName");

		if (modifiedUserName != null) {
			setModifiedUserName(modifiedUserName);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String corpProjectUuid = (String)attributes.get("corpProjectUuid");

		if (corpProjectUuid != null) {
			setCorpProjectUuid(corpProjectUuid);
		}

		Long corpProjectId = (Long)attributes.get("corpProjectId");

		if (corpProjectId != null) {
			setCorpProjectId(corpProjectId);
		}

		String corpEntryName = (String)attributes.get("corpEntryName");

		if (corpEntryName != null) {
			setCorpEntryName(corpEntryName);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String code = (String)attributes.get("code");

		if (code != null) {
			setCode(code);
		}

		Long redirectAccountEntryId = (Long)attributes.get(
				"redirectAccountEntryId");

		if (redirectAccountEntryId != null) {
			setRedirectAccountEntryId(redirectAccountEntryId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer industry = (Integer)attributes.get("industry");

		if (industry != null) {
			setIndustry(industry);
		}

		Long countryId = (Long)attributes.get("countryId");

		if (countryId != null) {
			setCountryId(countryId);
		}

		Long partnerEntryId = (Long)attributes.get("partnerEntryId");

		if (partnerEntryId != null) {
			setPartnerEntryId(partnerEntryId);
		}

		Boolean partnerManagedSupport = (Boolean)attributes.get(
				"partnerManagedSupport");

		if (partnerManagedSupport != null) {
			setPartnerManagedSupport(partnerManagedSupport);
		}

		Integer tier = (Integer)attributes.get("tier");

		if (tier != null) {
			setTier(tier);
		}

		Integer maxCustomers = (Integer)attributes.get("maxCustomers");

		if (maxCustomers != null) {
			setMaxCustomers(maxCustomers);
		}

		String instructions = (String)attributes.get("instructions");

		if (instructions != null) {
			setInstructions(instructions);
		}

		String notes = (String)attributes.get("notes");

		if (notes != null) {
			setNotes(notes);
		}

		Long highestSupportResponseId = (Long)attributes.get(
				"highestSupportResponseId");

		if (highestSupportResponseId != null) {
			setHighestSupportResponseId(highestSupportResponseId);
		}

		Date lastAuditDate = (Date)attributes.get("lastAuditDate");

		if (lastAuditDate != null) {
			setLastAuditDate(lastAuditDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		String statusMessage = (String)attributes.get("statusMessage");

		if (statusMessage != null) {
			setStatusMessage(statusMessage);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getAccountEntryId() {
		return _accountEntryId;
	}

	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryId", long.class);

				method.invoke(_accountEntryRemoteModel, accountEntryId);
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

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_accountEntryRemoteModel, companyId);
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

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_accountEntryRemoteModel, userId);
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

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_accountEntryRemoteModel, userName);
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

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_accountEntryRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getModifiedUserId() {
		return _modifiedUserId;
	}

	@Override
	public void setModifiedUserId(long modifiedUserId) {
		_modifiedUserId = modifiedUserId;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedUserId", long.class);

				method.invoke(_accountEntryRemoteModel, modifiedUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getModifiedUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getModifiedUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setModifiedUserUuid(String modifiedUserUuid) {
	}

	@Override
	public String getModifiedUserName() {
		return _modifiedUserName;
	}

	@Override
	public void setModifiedUserName(String modifiedUserName) {
		_modifiedUserName = modifiedUserName;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedUserName",
						String.class);

				method.invoke(_accountEntryRemoteModel, modifiedUserName);
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

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_accountEntryRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCorpProjectUuid() {
		return _corpProjectUuid;
	}

	@Override
	public void setCorpProjectUuid(String corpProjectUuid) {
		_corpProjectUuid = corpProjectUuid;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCorpProjectUuid",
						String.class);

				method.invoke(_accountEntryRemoteModel, corpProjectUuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCorpProjectId() {
		return _corpProjectId;
	}

	@Override
	public void setCorpProjectId(long corpProjectId) {
		_corpProjectId = corpProjectId;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCorpProjectId", long.class);

				method.invoke(_accountEntryRemoteModel, corpProjectId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCorpEntryName() {
		return _corpEntryName;
	}

	@Override
	public void setCorpEntryName(String corpEntryName) {
		_corpEntryName = corpEntryName;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCorpEntryName", String.class);

				method.invoke(_accountEntryRemoteModel, corpEntryName);
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

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_accountEntryRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCode() {
		return _code;
	}

	@Override
	public void setCode(String code) {
		_code = code;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCode", String.class);

				method.invoke(_accountEntryRemoteModel, code);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getRedirectAccountEntryId() {
		return _redirectAccountEntryId;
	}

	@Override
	public void setRedirectAccountEntryId(long redirectAccountEntryId) {
		_redirectAccountEntryId = redirectAccountEntryId;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setRedirectAccountEntryId",
						long.class);

				method.invoke(_accountEntryRemoteModel, redirectAccountEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_type = type;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_accountEntryRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getIndustry() {
		return _industry;
	}

	@Override
	public void setIndustry(int industry) {
		_industry = industry;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setIndustry", int.class);

				method.invoke(_accountEntryRemoteModel, industry);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCountryId() {
		return _countryId;
	}

	@Override
	public void setCountryId(long countryId) {
		_countryId = countryId;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCountryId", long.class);

				method.invoke(_accountEntryRemoteModel, countryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getPartnerEntryId() {
		return _partnerEntryId;
	}

	@Override
	public void setPartnerEntryId(long partnerEntryId) {
		_partnerEntryId = partnerEntryId;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPartnerEntryId", long.class);

				method.invoke(_accountEntryRemoteModel, partnerEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getPartnerManagedSupport() {
		return _partnerManagedSupport;
	}

	@Override
	public boolean isPartnerManagedSupport() {
		return _partnerManagedSupport;
	}

	@Override
	public void setPartnerManagedSupport(boolean partnerManagedSupport) {
		_partnerManagedSupport = partnerManagedSupport;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPartnerManagedSupport",
						boolean.class);

				method.invoke(_accountEntryRemoteModel, partnerManagedSupport);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getTier() {
		return _tier;
	}

	@Override
	public void setTier(int tier) {
		_tier = tier;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setTier", int.class);

				method.invoke(_accountEntryRemoteModel, tier);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getMaxCustomers() {
		return _maxCustomers;
	}

	@Override
	public void setMaxCustomers(int maxCustomers) {
		_maxCustomers = maxCustomers;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setMaxCustomers", int.class);

				method.invoke(_accountEntryRemoteModel, maxCustomers);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getInstructions() {
		return _instructions;
	}

	@Override
	public void setInstructions(String instructions) {
		_instructions = instructions;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setInstructions", String.class);

				method.invoke(_accountEntryRemoteModel, instructions);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getNotes() {
		return _notes;
	}

	@Override
	public void setNotes(String notes) {
		_notes = notes;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setNotes", String.class);

				method.invoke(_accountEntryRemoteModel, notes);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getHighestSupportResponseId() {
		return _highestSupportResponseId;
	}

	@Override
	public void setHighestSupportResponseId(long highestSupportResponseId) {
		_highestSupportResponseId = highestSupportResponseId;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setHighestSupportResponseId",
						long.class);

				method.invoke(_accountEntryRemoteModel, highestSupportResponseId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getLastAuditDate() {
		return _lastAuditDate;
	}

	@Override
	public void setLastAuditDate(Date lastAuditDate) {
		_lastAuditDate = lastAuditDate;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setLastAuditDate", Date.class);

				method.invoke(_accountEntryRemoteModel, lastAuditDate);
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

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_accountEntryRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserId", long.class);

				method.invoke(_accountEntryRemoteModel, statusByUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getStatusByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getStatusByUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@Override
	public String getStatusByUserName() {
		return _statusByUserName;
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserName",
						String.class);

				method.invoke(_accountEntryRemoteModel, statusByUserName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusDate", Date.class);

				method.invoke(_accountEntryRemoteModel, statusDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getStatusMessage() {
		return _statusMessage;
	}

	@Override
	public void setStatusMessage(String statusMessage) {
		_statusMessage = statusMessage;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusMessage", String.class);

				method.invoke(_accountEntryRemoteModel, statusMessage);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public java.util.List<AccountAttachment> getAccountAttachments(
		long accountProjectId) {
		try {
			String methodName = "getAccountAttachments";

			Class<?>[] parameterTypes = new Class<?>[] { long.class };

			Object[] parameterValues = new Object[] { accountProjectId };

			java.util.List<AccountAttachment> returnObj = (java.util.List<AccountAttachment>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.List<AccountCustomer> getAccountCustomers() {
		try {
			String methodName = "getAccountCustomers";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<AccountCustomer> returnObj = (java.util.List<AccountCustomer>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.List<AccountWorker> getAccountWorkers() {
		try {
			String methodName = "getAccountWorkers";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<AccountWorker> returnObj = (java.util.List<AccountWorker>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public com.liferay.portal.kernel.model.Address getAddress() {
		try {
			String methodName = "getAddress";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.kernel.model.Address returnObj = (com.liferay.portal.kernel.model.Address)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getEWSADossieraProjectKey() {
		try {
			String methodName = "getEWSADossieraProjectKey";

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
	public java.lang.String getIndustryLabel() {
		try {
			String methodName = "getIndustryLabel";

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
	public java.lang.String[] getLanguageIds() {
		try {
			String methodName = "getLanguageIds";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String[] returnObj = (java.lang.String[])invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.List<OfferingEntry> getOfferingEntries() {
		try {
			String methodName = "getOfferingEntries";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<OfferingEntry> returnObj = (java.util.List<OfferingEntry>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.List<OrderEntry> getOrderEntries() {
		try {
			String methodName = "getOrderEntries";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<OrderEntry> returnObj = (java.util.List<OrderEntry>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public PartnerEntry getPartnerEntry() {
		try {
			String methodName = "getPartnerEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			PartnerEntry returnObj = (PartnerEntry)invokeOnRemoteModel(methodName,
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
	public long[] getSupportRegionIds() {
		try {
			String methodName = "getSupportRegionIds";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			long[] returnObj = (long[])invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.List<SupportRegion> getSupportRegions() {
		try {
			String methodName = "getSupportRegions";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<SupportRegion> returnObj = (java.util.List<SupportRegion>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getTypeLabel() {
		try {
			String methodName = "getTypeLabel";

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
	public void setAddress(com.liferay.portal.kernel.model.Address address) {
		try {
			String methodName = "setAddress";

			Class<?>[] parameterTypes = new Class<?>[] {
					com.liferay.portal.kernel.model.Address.class
				};

			Object[] parameterValues = new Object[] { address };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public void setLanguageIds(java.lang.String[] languageIds) {
		try {
			String methodName = "setLanguageIds";

			Class<?>[] parameterTypes = new Class<?>[] { java.lang.String.class };

			Object[] parameterValues = new Object[] { languageIds };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public void setSupportRegionIds(long[] supportRegionIds) {
		try {
			String methodName = "setSupportRegionIds";

			Class<?>[] parameterTypes = new Class<?>[] { long.class };

			Object[] parameterValues = new Object[] { supportRegionIds };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public BaseModel<?> getAccountEntryRemoteModel() {
		return _accountEntryRemoteModel;
	}

	public void setAccountEntryRemoteModel(BaseModel<?> accountEntryRemoteModel) {
		_accountEntryRemoteModel = accountEntryRemoteModel;
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

		Class<?> remoteModelClass = _accountEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_accountEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			AccountEntryLocalServiceUtil.addAccountEntry(this);
		}
		else {
			AccountEntryLocalServiceUtil.updateAccountEntry(this);
		}
	}

	@Override
	public AccountEntry toEscapedModel() {
		return (AccountEntry)ProxyUtil.newProxyInstance(AccountEntry.class.getClassLoader(),
			new Class[] { AccountEntry.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AccountEntryClp clone = new AccountEntryClp();

		clone.setAccountEntryId(getAccountEntryId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedUserId(getModifiedUserId());
		clone.setModifiedUserName(getModifiedUserName());
		clone.setModifiedDate(getModifiedDate());
		clone.setCorpProjectUuid(getCorpProjectUuid());
		clone.setCorpProjectId(getCorpProjectId());
		clone.setCorpEntryName(getCorpEntryName());
		clone.setName(getName());
		clone.setCode(getCode());
		clone.setRedirectAccountEntryId(getRedirectAccountEntryId());
		clone.setType(getType());
		clone.setIndustry(getIndustry());
		clone.setCountryId(getCountryId());
		clone.setPartnerEntryId(getPartnerEntryId());
		clone.setPartnerManagedSupport(getPartnerManagedSupport());
		clone.setTier(getTier());
		clone.setMaxCustomers(getMaxCustomers());
		clone.setInstructions(getInstructions());
		clone.setNotes(getNotes());
		clone.setHighestSupportResponseId(getHighestSupportResponseId());
		clone.setLastAuditDate(getLastAuditDate());
		clone.setStatus(getStatus());
		clone.setStatusByUserId(getStatusByUserId());
		clone.setStatusByUserName(getStatusByUserName());
		clone.setStatusDate(getStatusDate());
		clone.setStatusMessage(getStatusMessage());

		return clone;
	}

	@Override
	public int compareTo(AccountEntry accountEntry) {
		int value = 0;

		value = getName().compareToIgnoreCase(accountEntry.getName());

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

		if (!(obj instanceof AccountEntryClp)) {
			return false;
		}

		AccountEntryClp accountEntry = (AccountEntryClp)obj;

		long primaryKey = accountEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(61);

		sb.append("{accountEntryId=");
		sb.append(getAccountEntryId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedUserId=");
		sb.append(getModifiedUserId());
		sb.append(", modifiedUserName=");
		sb.append(getModifiedUserName());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", corpProjectUuid=");
		sb.append(getCorpProjectUuid());
		sb.append(", corpProjectId=");
		sb.append(getCorpProjectId());
		sb.append(", corpEntryName=");
		sb.append(getCorpEntryName());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", code=");
		sb.append(getCode());
		sb.append(", redirectAccountEntryId=");
		sb.append(getRedirectAccountEntryId());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", industry=");
		sb.append(getIndustry());
		sb.append(", countryId=");
		sb.append(getCountryId());
		sb.append(", partnerEntryId=");
		sb.append(getPartnerEntryId());
		sb.append(", partnerManagedSupport=");
		sb.append(getPartnerManagedSupport());
		sb.append(", tier=");
		sb.append(getTier());
		sb.append(", maxCustomers=");
		sb.append(getMaxCustomers());
		sb.append(", instructions=");
		sb.append(getInstructions());
		sb.append(", notes=");
		sb.append(getNotes());
		sb.append(", highestSupportResponseId=");
		sb.append(getHighestSupportResponseId());
		sb.append(", lastAuditDate=");
		sb.append(getLastAuditDate());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusByUserId=");
		sb.append(getStatusByUserId());
		sb.append(", statusByUserName=");
		sb.append(getStatusByUserName());
		sb.append(", statusDate=");
		sb.append(getStatusDate());
		sb.append(", statusMessage=");
		sb.append(getStatusMessage());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(94);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AccountEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>accountEntryId</column-name><column-value><![CDATA[");
		sb.append(getAccountEntryId());
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
			"<column><column-name>modifiedUserId</column-name><column-value><![CDATA[");
		sb.append(getModifiedUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedUserName</column-name><column-value><![CDATA[");
		sb.append(getModifiedUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>corpProjectUuid</column-name><column-value><![CDATA[");
		sb.append(getCorpProjectUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>corpProjectId</column-name><column-value><![CDATA[");
		sb.append(getCorpProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>corpEntryName</column-name><column-value><![CDATA[");
		sb.append(getCorpEntryName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>code</column-name><column-value><![CDATA[");
		sb.append(getCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>redirectAccountEntryId</column-name><column-value><![CDATA[");
		sb.append(getRedirectAccountEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>industry</column-name><column-value><![CDATA[");
		sb.append(getIndustry());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>countryId</column-name><column-value><![CDATA[");
		sb.append(getCountryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>partnerEntryId</column-name><column-value><![CDATA[");
		sb.append(getPartnerEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>partnerManagedSupport</column-name><column-value><![CDATA[");
		sb.append(getPartnerManagedSupport());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>tier</column-name><column-value><![CDATA[");
		sb.append(getTier());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>maxCustomers</column-name><column-value><![CDATA[");
		sb.append(getMaxCustomers());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>instructions</column-name><column-value><![CDATA[");
		sb.append(getInstructions());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notes</column-name><column-value><![CDATA[");
		sb.append(getNotes());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>highestSupportResponseId</column-name><column-value><![CDATA[");
		sb.append(getHighestSupportResponseId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastAuditDate</column-name><column-value><![CDATA[");
		sb.append(getLastAuditDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusDate</column-name><column-value><![CDATA[");
		sb.append(getStatusDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusMessage</column-name><column-value><![CDATA[");
		sb.append(getStatusMessage());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _accountEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _modifiedUserId;
	private String _modifiedUserName;
	private Date _modifiedDate;
	private String _corpProjectUuid;
	private long _corpProjectId;
	private String _corpEntryName;
	private String _name;
	private String _code;
	private long _redirectAccountEntryId;
	private int _type;
	private int _industry;
	private long _countryId;
	private long _partnerEntryId;
	private boolean _partnerManagedSupport;
	private int _tier;
	private int _maxCustomers;
	private String _instructions;
	private String _notes;
	private long _highestSupportResponseId;
	private Date _lastAuditDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _statusMessage;
	private BaseModel<?> _accountEntryRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}