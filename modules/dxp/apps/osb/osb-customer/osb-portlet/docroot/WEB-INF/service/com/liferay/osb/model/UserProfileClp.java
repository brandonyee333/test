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
import com.liferay.osb.service.UserProfileLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class UserProfileClp extends BaseModelImpl<UserProfile>
	implements UserProfile {
	public UserProfileClp() {
	}

	public Class<?> getModelClass() {
		return UserProfile.class;
	}

	public String getModelClassName() {
		return UserProfile.class.getName();
	}

	public long getPrimaryKey() {
		return _userProfileId;
	}

	public void setPrimaryKey(long primaryKey) {
		setUserProfileId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_userProfileId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userProfileId", getUserProfileId());
		attributes.put("userId", getUserId());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("firstName", getFirstName());
		attributes.put("lastName", getLastName());
		attributes.put("legalEntityName", getLegalEntityName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userProfileId = (Long)attributes.get("userProfileId");

		if (userProfileId != null) {
			setUserProfileId(userProfileId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
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

	public long getUserProfileId() {
		return _userProfileId;
	}

	public void setUserProfileId(long userProfileId) {
		_userProfileId = userProfileId;

		if (_userProfileRemoteModel != null) {
			try {
				Class<?> clazz = _userProfileRemoteModel.getClass();

				Method method = clazz.getMethod("setUserProfileId", long.class);

				method.invoke(_userProfileRemoteModel, userProfileId);
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

		if (_userProfileRemoteModel != null) {
			try {
				Class<?> clazz = _userProfileRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_userProfileRemoteModel, userId);
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

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;

		if (_userProfileRemoteModel != null) {
			try {
				Class<?> clazz = _userProfileRemoteModel.getClass();

				Method method = clazz.getMethod("setEmailAddress", String.class);

				method.invoke(_userProfileRemoteModel, emailAddress);
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

		if (_userProfileRemoteModel != null) {
			try {
				Class<?> clazz = _userProfileRemoteModel.getClass();

				Method method = clazz.getMethod("setFirstName", String.class);

				method.invoke(_userProfileRemoteModel, firstName);
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

		if (_userProfileRemoteModel != null) {
			try {
				Class<?> clazz = _userProfileRemoteModel.getClass();

				Method method = clazz.getMethod("setLastName", String.class);

				method.invoke(_userProfileRemoteModel, lastName);
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

		if (_userProfileRemoteModel != null) {
			try {
				Class<?> clazz = _userProfileRemoteModel.getClass();

				Method method = clazz.getMethod("setLegalEntityName",
						String.class);

				method.invoke(_userProfileRemoteModel, legalEntityName);
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

	public BaseModel<?> getUserProfileRemoteModel() {
		return _userProfileRemoteModel;
	}

	public void setUserProfileRemoteModel(BaseModel<?> userProfileRemoteModel) {
		_userProfileRemoteModel = userProfileRemoteModel;
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

		Class<?> remoteModelClass = _userProfileRemoteModel.getClass();

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

		Object returnValue = method.invoke(_userProfileRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			UserProfileLocalServiceUtil.addUserProfile(this);
		}
		else {
			UserProfileLocalServiceUtil.updateUserProfile(this);
		}
	}

	@Override
	public UserProfile toEscapedModel() {
		return (UserProfile)ProxyUtil.newProxyInstance(UserProfile.class.getClassLoader(),
			new Class[] { UserProfile.class }, new AutoEscapeBeanHandler(this));
	}

	public UserProfile toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		UserProfileClp clone = new UserProfileClp();

		clone.setUserProfileId(getUserProfileId());
		clone.setUserId(getUserId());
		clone.setEmailAddress(getEmailAddress());
		clone.setFirstName(getFirstName());
		clone.setLastName(getLastName());
		clone.setLegalEntityName(getLegalEntityName());

		return clone;
	}

	public int compareTo(UserProfile userProfile) {
		long primaryKey = userProfile.getPrimaryKey();

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

		if (!(obj instanceof UserProfileClp)) {
			return false;
		}

		UserProfileClp userProfile = (UserProfileClp)obj;

		long primaryKey = userProfile.getPrimaryKey();

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
		StringBundler sb = new StringBundler(13);

		sb.append("{userProfileId=");
		sb.append(getUserProfileId());
		sb.append(", userId=");
		sb.append(getUserId());
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
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.UserProfile");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userProfileId</column-name><column-value><![CDATA[");
		sb.append(getUserProfileId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
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

	private long _userProfileId;
	private long _userId;
	private String _userUuid;
	private String _emailAddress;
	private String _firstName;
	private String _lastName;
	private String _legalEntityName;
	private BaseModel<?> _userProfileRemoteModel;
}