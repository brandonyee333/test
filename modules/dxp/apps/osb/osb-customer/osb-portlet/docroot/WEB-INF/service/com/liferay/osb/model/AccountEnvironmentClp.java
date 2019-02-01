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

import com.liferay.osb.service.AccountEnvironmentLocalServiceUtil;
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
public class AccountEnvironmentClp extends BaseModelImpl<AccountEnvironment>
	implements AccountEnvironment {
	public AccountEnvironmentClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return AccountEnvironment.class;
	}

	@Override
	public String getModelClassName() {
		return AccountEnvironment.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _accountEnvironmentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountEnvironmentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountEnvironmentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountEnvironmentId", getAccountEnvironmentId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("productEntryId", getProductEntryId());
		attributes.put("name", getName());
		attributes.put("envOS", getEnvOS());
		attributes.put("envOSCustom", getEnvOSCustom());
		attributes.put("envDB", getEnvDB());
		attributes.put("envJVM", getEnvJVM());
		attributes.put("envAS", getEnvAS());
		attributes.put("envLFR", getEnvLFR());
		attributes.put("envCommerce", getEnvCommerce());
		attributes.put("envBrowser", getEnvBrowser());
		attributes.put("envCS", getEnvCS());
		attributes.put("envSearch", getEnvSearch());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountEnvironmentId = (Long)attributes.get("accountEnvironmentId");

		if (accountEnvironmentId != null) {
			setAccountEnvironmentId(accountEnvironmentId);
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

		Long productEntryId = (Long)attributes.get("productEntryId");

		if (productEntryId != null) {
			setProductEntryId(productEntryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
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

		Integer envCommerce = (Integer)attributes.get("envCommerce");

		if (envCommerce != null) {
			setEnvCommerce(envCommerce);
		}

		Integer envBrowser = (Integer)attributes.get("envBrowser");

		if (envBrowser != null) {
			setEnvBrowser(envBrowser);
		}

		Integer envCS = (Integer)attributes.get("envCS");

		if (envCS != null) {
			setEnvCS(envCS);
		}

		String envSearch = (String)attributes.get("envSearch");

		if (envSearch != null) {
			setEnvSearch(envSearch);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getAccountEnvironmentId() {
		return _accountEnvironmentId;
	}

	@Override
	public void setAccountEnvironmentId(long accountEnvironmentId) {
		_accountEnvironmentId = accountEnvironmentId;

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEnvironmentId",
						long.class);

				method.invoke(_accountEnvironmentRemoteModel,
					accountEnvironmentId);
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

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_accountEnvironmentRemoteModel, userId);
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

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_accountEnvironmentRemoteModel, userName);
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

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_accountEnvironmentRemoteModel, createDate);
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

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_accountEnvironmentRemoteModel, modifiedDate);
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

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryId", long.class);

				method.invoke(_accountEnvironmentRemoteModel, accountEntryId);
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

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setProductEntryId", long.class);

				method.invoke(_accountEnvironmentRemoteModel, productEntryId);
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

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_accountEnvironmentRemoteModel, name);
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

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvOS", int.class);

				method.invoke(_accountEnvironmentRemoteModel, envOS);
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

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvOSCustom", String.class);

				method.invoke(_accountEnvironmentRemoteModel, envOSCustom);
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

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvDB", int.class);

				method.invoke(_accountEnvironmentRemoteModel, envDB);
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

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvJVM", int.class);

				method.invoke(_accountEnvironmentRemoteModel, envJVM);
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

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvAS", int.class);

				method.invoke(_accountEnvironmentRemoteModel, envAS);
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

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvLFR", int.class);

				method.invoke(_accountEnvironmentRemoteModel, envLFR);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getEnvCommerce() {
		return _envCommerce;
	}

	@Override
	public void setEnvCommerce(int envCommerce) {
		_envCommerce = envCommerce;

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvCommerce", int.class);

				method.invoke(_accountEnvironmentRemoteModel, envCommerce);
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

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvBrowser", int.class);

				method.invoke(_accountEnvironmentRemoteModel, envBrowser);
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

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvCS", int.class);

				method.invoke(_accountEnvironmentRemoteModel, envCS);
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

		if (_accountEnvironmentRemoteModel != null) {
			try {
				Class<?> clazz = _accountEnvironmentRemoteModel.getClass();

				Method method = clazz.getMethod("setEnvSearch", String.class);

				method.invoke(_accountEnvironmentRemoteModel, envSearch);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
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
	public java.lang.String getEnvCommerceLabel() {
		try {
			String methodName = "getEnvCommerceLabel";

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

	public BaseModel<?> getAccountEnvironmentRemoteModel() {
		return _accountEnvironmentRemoteModel;
	}

	public void setAccountEnvironmentRemoteModel(
		BaseModel<?> accountEnvironmentRemoteModel) {
		_accountEnvironmentRemoteModel = accountEnvironmentRemoteModel;
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

		Class<?> remoteModelClass = _accountEnvironmentRemoteModel.getClass();

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

		Object returnValue = method.invoke(_accountEnvironmentRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			AccountEnvironmentLocalServiceUtil.addAccountEnvironment(this);
		}
		else {
			AccountEnvironmentLocalServiceUtil.updateAccountEnvironment(this);
		}
	}

	@Override
	public AccountEnvironment toEscapedModel() {
		return (AccountEnvironment)ProxyUtil.newProxyInstance(AccountEnvironment.class.getClassLoader(),
			new Class[] { AccountEnvironment.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AccountEnvironmentClp clone = new AccountEnvironmentClp();

		clone.setAccountEnvironmentId(getAccountEnvironmentId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAccountEntryId(getAccountEntryId());
		clone.setProductEntryId(getProductEntryId());
		clone.setName(getName());
		clone.setEnvOS(getEnvOS());
		clone.setEnvOSCustom(getEnvOSCustom());
		clone.setEnvDB(getEnvDB());
		clone.setEnvJVM(getEnvJVM());
		clone.setEnvAS(getEnvAS());
		clone.setEnvLFR(getEnvLFR());
		clone.setEnvCommerce(getEnvCommerce());
		clone.setEnvBrowser(getEnvBrowser());
		clone.setEnvCS(getEnvCS());
		clone.setEnvSearch(getEnvSearch());

		return clone;
	}

	@Override
	public int compareTo(AccountEnvironment accountEnvironment) {
		int value = 0;

		value = getName().compareTo(accountEnvironment.getName());

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

		if (!(obj instanceof AccountEnvironmentClp)) {
			return false;
		}

		AccountEnvironmentClp accountEnvironment = (AccountEnvironmentClp)obj;

		long primaryKey = accountEnvironment.getPrimaryKey();

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
		StringBundler sb = new StringBundler(37);

		sb.append("{accountEnvironmentId=");
		sb.append(getAccountEnvironmentId());
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
		sb.append(", productEntryId=");
		sb.append(getProductEntryId());
		sb.append(", name=");
		sb.append(getName());
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
		sb.append(", envCommerce=");
		sb.append(getEnvCommerce());
		sb.append(", envBrowser=");
		sb.append(getEnvBrowser());
		sb.append(", envCS=");
		sb.append(getEnvCS());
		sb.append(", envSearch=");
		sb.append(getEnvSearch());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AccountEnvironment");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>accountEnvironmentId</column-name><column-value><![CDATA[");
		sb.append(getAccountEnvironmentId());
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
			"<column><column-name>productEntryId</column-name><column-value><![CDATA[");
		sb.append(getProductEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
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
			"<column><column-name>envCommerce</column-name><column-value><![CDATA[");
		sb.append(getEnvCommerce());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>envBrowser</column-name><column-value><![CDATA[");
		sb.append(getEnvBrowser());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>envCS</column-name><column-value><![CDATA[");
		sb.append(getEnvCS());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>envSearch</column-name><column-value><![CDATA[");
		sb.append(getEnvSearch());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _accountEnvironmentId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _accountEntryId;
	private long _productEntryId;
	private String _name;
	private int _envOS;
	private String _envOSCustom;
	private int _envDB;
	private int _envJVM;
	private int _envAS;
	private int _envLFR;
	private int _envCommerce;
	private int _envBrowser;
	private int _envCS;
	private String _envSearch;
	private BaseModel<?> _accountEnvironmentRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}