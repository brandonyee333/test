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
import com.liferay.osb.service.UserProfileHistoryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
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
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class UserProfileHistoryClp extends BaseModelImpl<UserProfileHistory>
	implements UserProfileHistory {
	public UserProfileHistoryClp() {
	}

	public Class<?> getModelClass() {
		return UserProfileHistory.class;
	}

	public String getModelClassName() {
		return UserProfileHistory.class.getName();
	}

	public long getPrimaryKey() {
		return _userProfileHistoryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setUserProfileHistoryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_userProfileHistoryId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userProfileHistoryId", getUserProfileHistoryId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("firstName", getFirstName());
		attributes.put("lastName", getLastName());
		attributes.put("legalEntityName", getLegalEntityName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userProfileHistoryId = (Long)attributes.get("userProfileHistoryId");

		if (userProfileHistoryId != null) {
			setUserProfileHistoryId(userProfileHistoryId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
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

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		String firstName = (String)attributes.get("firstName");

		if (firstName != null) {
			setFirstName(firstName);
		}

		String lastName = (String)attributes.get("lastName");

		if (lastName != null) {
			setLastName(lastName);
		}

		String legalEntityName = (String)attributes.get("legalEntityName");

		if (legalEntityName != null) {
			setLegalEntityName(legalEntityName);
		}
	}

	public long getUserProfileHistoryId() {
		return _userProfileHistoryId;
	}

	public void setUserProfileHistoryId(long userProfileHistoryId) {
		_userProfileHistoryId = userProfileHistoryId;

		if (_userProfileHistoryRemoteModel != null) {
			try {
				Class<?> clazz = _userProfileHistoryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserProfileHistoryId",
						long.class);

				method.invoke(_userProfileHistoryRemoteModel,
					userProfileHistoryId);
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

		if (_userProfileHistoryRemoteModel != null) {
			try {
				Class<?> clazz = _userProfileHistoryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_userProfileHistoryRemoteModel, userId);
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

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_userProfileHistoryRemoteModel != null) {
			try {
				Class<?> clazz = _userProfileHistoryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_userProfileHistoryRemoteModel, createDate);
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

		if (_userProfileHistoryRemoteModel != null) {
			try {
				Class<?> clazz = _userProfileHistoryRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_userProfileHistoryRemoteModel, classNameId);
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

		if (_userProfileHistoryRemoteModel != null) {
			try {
				Class<?> clazz = _userProfileHistoryRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_userProfileHistoryRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;

		if (_userProfileHistoryRemoteModel != null) {
			try {
				Class<?> clazz = _userProfileHistoryRemoteModel.getClass();

				Method method = clazz.getMethod("setEmailAddress", String.class);

				method.invoke(_userProfileHistoryRemoteModel, emailAddress);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;

		if (_userProfileHistoryRemoteModel != null) {
			try {
				Class<?> clazz = _userProfileHistoryRemoteModel.getClass();

				Method method = clazz.getMethod("setFirstName", String.class);

				method.invoke(_userProfileHistoryRemoteModel, firstName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;

		if (_userProfileHistoryRemoteModel != null) {
			try {
				Class<?> clazz = _userProfileHistoryRemoteModel.getClass();

				Method method = clazz.getMethod("setLastName", String.class);

				method.invoke(_userProfileHistoryRemoteModel, lastName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getLegalEntityName() {
		return _legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		_legalEntityName = legalEntityName;

		if (_userProfileHistoryRemoteModel != null) {
			try {
				Class<?> clazz = _userProfileHistoryRemoteModel.getClass();

				Method method = clazz.getMethod("setLegalEntityName",
						String.class);

				method.invoke(_userProfileHistoryRemoteModel, legalEntityName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.lang.String getFullName() {
		try {
			String methodName = "getFullName";

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

	public BaseModel<?> getUserProfileHistoryRemoteModel() {
		return _userProfileHistoryRemoteModel;
	}

	public void setUserProfileHistoryRemoteModel(
		BaseModel<?> userProfileHistoryRemoteModel) {
		_userProfileHistoryRemoteModel = userProfileHistoryRemoteModel;
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

		Class<?> remoteModelClass = _userProfileHistoryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_userProfileHistoryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			UserProfileHistoryLocalServiceUtil.addUserProfileHistory(this);
		}
		else {
			UserProfileHistoryLocalServiceUtil.updateUserProfileHistory(this);
		}
	}

	@Override
	public UserProfileHistory toEscapedModel() {
		return (UserProfileHistory)ProxyUtil.newProxyInstance(UserProfileHistory.class.getClassLoader(),
			new Class[] { UserProfileHistory.class },
			new AutoEscapeBeanHandler(this));
	}

	public UserProfileHistory toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		UserProfileHistoryClp clone = new UserProfileHistoryClp();

		clone.setUserProfileHistoryId(getUserProfileHistoryId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setEmailAddress(getEmailAddress());
		clone.setFirstName(getFirstName());
		clone.setLastName(getLastName());
		clone.setLegalEntityName(getLegalEntityName());

		return clone;
	}

	public int compareTo(UserProfileHistory userProfileHistory) {
		long primaryKey = userProfileHistory.getPrimaryKey();

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

		if (!(obj instanceof UserProfileHistoryClp)) {
			return false;
		}

		UserProfileHistoryClp userProfileHistory = (UserProfileHistoryClp)obj;

		long primaryKey = userProfileHistory.getPrimaryKey();

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

		sb.append("{userProfileHistoryId=");
		sb.append(getUserProfileHistoryId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", emailAddress=");
		sb.append(getEmailAddress());
		sb.append(", firstName=");
		sb.append(getFirstName());
		sb.append(", lastName=");
		sb.append(getLastName());
		sb.append(", legalEntityName=");
		sb.append(getLegalEntityName());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.UserProfileHistory");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userProfileHistoryId</column-name><column-value><![CDATA[");
		sb.append(getUserProfileHistoryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
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
			"<column><column-name>emailAddress</column-name><column-value><![CDATA[");
		sb.append(getEmailAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>firstName</column-name><column-value><![CDATA[");
		sb.append(getFirstName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastName</column-name><column-value><![CDATA[");
		sb.append(getLastName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>legalEntityName</column-name><column-value><![CDATA[");
		sb.append(getLegalEntityName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _userProfileHistoryId;
	private long _userId;
	private String _userUuid;
	private Date _createDate;
	private long _classNameId;
	private long _classPK;
	private String _emailAddress;
	private String _firstName;
	private String _lastName;
	private String _legalEntityName;
	private BaseModel<?> _userProfileHistoryRemoteModel;
}