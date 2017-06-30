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

import com.liferay.osb.service.ClpSerializer;
import com.liferay.osb.service.ContractEntryLocalServiceUtil;

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class ContractEntryClp extends BaseModelImpl<ContractEntry>
	implements ContractEntry {
	public ContractEntryClp() {
	}

	public Class<?> getModelClass() {
		return ContractEntry.class;
	}

	public String getModelClassName() {
		return ContractEntry.class.getName();
	}

	public long getPrimaryKey() {
		return _contractEntryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setContractEntryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_contractEntryId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("contractEntryId", getContractEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("type", getType());
		attributes.put("version", getVersion());
		attributes.put("content", getContent());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long contractEntryId = (Long)attributes.get("contractEntryId");

		if (contractEntryId != null) {
			setContractEntryId(contractEntryId);
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

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer version = (Integer)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}
	}

	public long getContractEntryId() {
		return _contractEntryId;
	}

	public void setContractEntryId(long contractEntryId) {
		_contractEntryId = contractEntryId;

		if (_contractEntryRemoteModel != null) {
			try {
				Class<?> clazz = _contractEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setContractEntryId", long.class);

				method.invoke(_contractEntryRemoteModel, contractEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;

		if (_contractEntryRemoteModel != null) {
			try {
				Class<?> clazz = _contractEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_contractEntryRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;

		if (_contractEntryRemoteModel != null) {
			try {
				Class<?> clazz = _contractEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_contractEntryRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_contractEntryRemoteModel != null) {
			try {
				Class<?> clazz = _contractEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_contractEntryRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;

		if (_contractEntryRemoteModel != null) {
			try {
				Class<?> clazz = _contractEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_contractEntryRemoteModel, classNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;

		if (_contractEntryRemoteModel != null) {
			try {
				Class<?> clazz = _contractEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_contractEntryRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;

		if (_contractEntryRemoteModel != null) {
			try {
				Class<?> clazz = _contractEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_contractEntryRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getVersion() {
		return _version;
	}

	public void setVersion(int version) {
		_version = version;

		if (_contractEntryRemoteModel != null) {
			try {
				Class<?> clazz = _contractEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setVersion", int.class);

				method.invoke(_contractEntryRemoteModel, version);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getContent() {
		return _content;
	}

	public String getContent(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getContent(languageId);
	}

	public String getContent(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getContent(languageId, useDefault);
	}

	public String getContent(String languageId) {
		return LocalizationUtil.getLocalization(getContent(), languageId);
	}

	public String getContent(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getContent(), languageId,
			useDefault);
	}

	public String getContentCurrentLanguageId() {
		return _contentCurrentLanguageId;
	}

	public String getContentCurrentValue() {
		Locale locale = getLocale(_contentCurrentLanguageId);

		return getContent(locale);
	}

	public Map<Locale, String> getContentMap() {
		return LocalizationUtil.getLocalizationMap(getContent());
	}

	public void setContent(String content) {
		_content = content;

		if (_contractEntryRemoteModel != null) {
			try {
				Class<?> clazz = _contractEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setContent", String.class);

				method.invoke(_contractEntryRemoteModel, content);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public void setContent(String content, Locale locale) {
		setContent(content, locale, LocaleUtil.getDefault());
	}

	public void setContent(String content, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(content)) {
			setContent(LocalizationUtil.updateLocalization(getContent(),
					"Content", content, languageId, defaultLanguageId));
		}
		else {
			setContent(LocalizationUtil.removeLocalization(getContent(),
					"Content", languageId));
		}
	}

	public void setContentCurrentLanguageId(String languageId) {
		_contentCurrentLanguageId = languageId;
	}

	public void setContentMap(Map<Locale, String> contentMap) {
		setContentMap(contentMap, LocaleUtil.getDefault());
	}

	public void setContentMap(Map<Locale, String> contentMap,
		Locale defaultLocale) {
		if (contentMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			Locale[] locales = LanguageUtil.getAvailableLocales();

			for (Locale locale : locales) {
				String content = contentMap.get(locale);

				setContent(content, locale, defaultLocale);
			}
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

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

	public BaseModel<?> getContractEntryRemoteModel() {
		return _contractEntryRemoteModel;
	}

	public void setContractEntryRemoteModel(
		BaseModel<?> contractEntryRemoteModel) {
		_contractEntryRemoteModel = contractEntryRemoteModel;
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

		Class<?> remoteModelClass = _contractEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_contractEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			ContractEntryLocalServiceUtil.addContractEntry(this);
		}
		else {
			ContractEntryLocalServiceUtil.updateContractEntry(this);
		}
	}

	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		setContent(getContent(defaultImportLocale), defaultImportLocale,
			defaultImportLocale);
	}

	@Override
	public ContractEntry toEscapedModel() {
		return (ContractEntry)ProxyUtil.newProxyInstance(ContractEntry.class.getClassLoader(),
			new Class[] { ContractEntry.class }, new AutoEscapeBeanHandler(this));
	}

	public ContractEntry toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		ContractEntryClp clone = new ContractEntryClp();

		clone.setContractEntryId(getContractEntryId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setType(getType());
		clone.setVersion(getVersion());
		clone.setContent(getContent());

		return clone;
	}

	public int compareTo(ContractEntry contractEntry) {
		long primaryKey = contractEntry.getPrimaryKey();

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

		if (!(obj instanceof ContractEntryClp)) {
			return false;
		}

		ContractEntryClp contractEntry = (ContractEntryClp)obj;

		long primaryKey = contractEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{contractEntryId=");
		sb.append(getContractEntryId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", version=");
		sb.append(getVersion());
		sb.append(", content=");
		sb.append(getContent());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.ContractEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>contractEntryId</column-name><column-value><![CDATA[");
		sb.append(getContractEntryId());
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
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append(getVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>content</column-name><column-value><![CDATA[");
		sb.append(getContent());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _contractEntryId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private long _classNameId;
	private long _classPK;
	private int _type;
	private int _version;
	private String _content;
	private String _contentCurrentLanguageId;
	private BaseModel<?> _contractEntryRemoteModel;
}