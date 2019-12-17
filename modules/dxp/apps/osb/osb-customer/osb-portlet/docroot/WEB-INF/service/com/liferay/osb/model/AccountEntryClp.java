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
		attributes.put("koroneikiAccountKey", getKoroneikiAccountKey());
		attributes.put("dossieraAccountKey", getDossieraAccountKey());
		attributes.put("name", getName());
		attributes.put("code", getCode());
		attributes.put("instructions", getInstructions());
		attributes.put("activeSupport", getActiveSupport());
		attributes.put("activeTicketSupport", getActiveTicketSupport());
		attributes.put("lastZendeskAuditDate", getLastZendeskAuditDate());
		attributes.put("status", getStatus());

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

		String koroneikiAccountKey = (String)attributes.get(
				"koroneikiAccountKey");

		if (koroneikiAccountKey != null) {
			setKoroneikiAccountKey(koroneikiAccountKey);
		}

		String dossieraAccountKey = (String)attributes.get("dossieraAccountKey");

		if (dossieraAccountKey != null) {
			setDossieraAccountKey(dossieraAccountKey);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String code = (String)attributes.get("code");

		if (code != null) {
			setCode(code);
		}

		String instructions = (String)attributes.get("instructions");

		if (instructions != null) {
			setInstructions(instructions);
		}

		Boolean activeSupport = (Boolean)attributes.get("activeSupport");

		if (activeSupport != null) {
			setActiveSupport(activeSupport);
		}

		Boolean activeTicketSupport = (Boolean)attributes.get(
				"activeTicketSupport");

		if (activeTicketSupport != null) {
			setActiveTicketSupport(activeTicketSupport);
		}

		Date lastZendeskAuditDate = (Date)attributes.get("lastZendeskAuditDate");

		if (lastZendeskAuditDate != null) {
			setLastZendeskAuditDate(lastZendeskAuditDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
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
	public String getKoroneikiAccountKey() {
		return _koroneikiAccountKey;
	}

	@Override
	public void setKoroneikiAccountKey(String koroneikiAccountKey) {
		_koroneikiAccountKey = koroneikiAccountKey;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setKoroneikiAccountKey",
						String.class);

				method.invoke(_accountEntryRemoteModel, koroneikiAccountKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getDossieraAccountKey() {
		return _dossieraAccountKey;
	}

	@Override
	public void setDossieraAccountKey(String dossieraAccountKey) {
		_dossieraAccountKey = dossieraAccountKey;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setDossieraAccountKey",
						String.class);

				method.invoke(_accountEntryRemoteModel, dossieraAccountKey);
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
	public boolean getActiveSupport() {
		return _activeSupport;
	}

	@Override
	public boolean isActiveSupport() {
		return _activeSupport;
	}

	@Override
	public void setActiveSupport(boolean activeSupport) {
		_activeSupport = activeSupport;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setActiveSupport",
						boolean.class);

				method.invoke(_accountEntryRemoteModel, activeSupport);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getActiveTicketSupport() {
		return _activeTicketSupport;
	}

	@Override
	public boolean isActiveTicketSupport() {
		return _activeTicketSupport;
	}

	@Override
	public void setActiveTicketSupport(boolean activeTicketSupport) {
		_activeTicketSupport = activeTicketSupport;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setActiveTicketSupport",
						boolean.class);

				method.invoke(_accountEntryRemoteModel, activeTicketSupport);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getLastZendeskAuditDate() {
		return _lastZendeskAuditDate;
	}

	@Override
	public void setLastZendeskAuditDate(Date lastZendeskAuditDate) {
		_lastZendeskAuditDate = lastZendeskAuditDate;

		if (_accountEntryRemoteModel != null) {
			try {
				Class<?> clazz = _accountEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setLastZendeskAuditDate",
						Date.class);

				method.invoke(_accountEntryRemoteModel, lastZendeskAuditDate);
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
	public long getCorpProjectId() {
		try {
			String methodName = "getCorpProjectId";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Long returnObj = (Long)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getDescription() {
		try {
			String methodName = "getDescription";

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
	public java.lang.String getDossieraAccountURL() {
		try {
			String methodName = "getDossieraAccountURL";

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
	public java.lang.String getTier() {
		try {
			String methodName = "getTier";

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
		clone.setKoroneikiAccountKey(getKoroneikiAccountKey());
		clone.setDossieraAccountKey(getDossieraAccountKey());
		clone.setName(getName());
		clone.setCode(getCode());
		clone.setInstructions(getInstructions());
		clone.setActiveSupport(getActiveSupport());
		clone.setActiveTicketSupport(getActiveTicketSupport());
		clone.setLastZendeskAuditDate(getLastZendeskAuditDate());
		clone.setStatus(getStatus());

		return clone;
	}

	@Override
	public int compareTo(AccountEntry accountEntry) {
		long primaryKey = accountEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(35);

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
		sb.append(", koroneikiAccountKey=");
		sb.append(getKoroneikiAccountKey());
		sb.append(", dossieraAccountKey=");
		sb.append(getDossieraAccountKey());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", code=");
		sb.append(getCode());
		sb.append(", instructions=");
		sb.append(getInstructions());
		sb.append(", activeSupport=");
		sb.append(getActiveSupport());
		sb.append(", activeTicketSupport=");
		sb.append(getActiveTicketSupport());
		sb.append(", lastZendeskAuditDate=");
		sb.append(getLastZendeskAuditDate());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

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
			"<column><column-name>koroneikiAccountKey</column-name><column-value><![CDATA[");
		sb.append(getKoroneikiAccountKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dossieraAccountKey</column-name><column-value><![CDATA[");
		sb.append(getDossieraAccountKey());
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
			"<column><column-name>instructions</column-name><column-value><![CDATA[");
		sb.append(getInstructions());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>activeSupport</column-name><column-value><![CDATA[");
		sb.append(getActiveSupport());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>activeTicketSupport</column-name><column-value><![CDATA[");
		sb.append(getActiveTicketSupport());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastZendeskAuditDate</column-name><column-value><![CDATA[");
		sb.append(getLastZendeskAuditDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
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
	private String _koroneikiAccountKey;
	private String _dossieraAccountKey;
	private String _name;
	private String _code;
	private String _instructions;
	private boolean _activeSupport;
	private boolean _activeTicketSupport;
	private Date _lastZendeskAuditDate;
	private int _status;
	private BaseModel<?> _accountEntryRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}