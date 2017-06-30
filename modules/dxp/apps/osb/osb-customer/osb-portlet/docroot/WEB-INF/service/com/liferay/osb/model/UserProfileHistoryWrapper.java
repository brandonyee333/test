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
 * This class is a wrapper for {@link UserProfileHistory}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       UserProfileHistory
 * @generated
 */
public class UserProfileHistoryWrapper implements UserProfileHistory,
	ModelWrapper<UserProfileHistory> {
	public UserProfileHistoryWrapper(UserProfileHistory userProfileHistory) {
		_userProfileHistory = userProfileHistory;
	}

	public Class<?> getModelClass() {
		return UserProfileHistory.class;
	}

	public String getModelClassName() {
		return UserProfileHistory.class.getName();
	}

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

	/**
	* Returns the primary key of this user profile history.
	*
	* @return the primary key of this user profile history
	*/
	public long getPrimaryKey() {
		return _userProfileHistory.getPrimaryKey();
	}

	/**
	* Sets the primary key of this user profile history.
	*
	* @param primaryKey the primary key of this user profile history
	*/
	public void setPrimaryKey(long primaryKey) {
		_userProfileHistory.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the user profile history ID of this user profile history.
	*
	* @return the user profile history ID of this user profile history
	*/
	public long getUserProfileHistoryId() {
		return _userProfileHistory.getUserProfileHistoryId();
	}

	/**
	* Sets the user profile history ID of this user profile history.
	*
	* @param userProfileHistoryId the user profile history ID of this user profile history
	*/
	public void setUserProfileHistoryId(long userProfileHistoryId) {
		_userProfileHistory.setUserProfileHistoryId(userProfileHistoryId);
	}

	/**
	* Returns the user ID of this user profile history.
	*
	* @return the user ID of this user profile history
	*/
	public long getUserId() {
		return _userProfileHistory.getUserId();
	}

	/**
	* Sets the user ID of this user profile history.
	*
	* @param userId the user ID of this user profile history
	*/
	public void setUserId(long userId) {
		_userProfileHistory.setUserId(userId);
	}

	/**
	* Returns the user uuid of this user profile history.
	*
	* @return the user uuid of this user profile history
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userProfileHistory.getUserUuid();
	}

	/**
	* Sets the user uuid of this user profile history.
	*
	* @param userUuid the user uuid of this user profile history
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_userProfileHistory.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this user profile history.
	*
	* @return the create date of this user profile history
	*/
	public java.util.Date getCreateDate() {
		return _userProfileHistory.getCreateDate();
	}

	/**
	* Sets the create date of this user profile history.
	*
	* @param createDate the create date of this user profile history
	*/
	public void setCreateDate(java.util.Date createDate) {
		_userProfileHistory.setCreateDate(createDate);
	}

	/**
	* Returns the fully qualified class name of this user profile history.
	*
	* @return the fully qualified class name of this user profile history
	*/
	public java.lang.String getClassName() {
		return _userProfileHistory.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_userProfileHistory.setClassName(className);
	}

	/**
	* Returns the class name ID of this user profile history.
	*
	* @return the class name ID of this user profile history
	*/
	public long getClassNameId() {
		return _userProfileHistory.getClassNameId();
	}

	/**
	* Sets the class name ID of this user profile history.
	*
	* @param classNameId the class name ID of this user profile history
	*/
	public void setClassNameId(long classNameId) {
		_userProfileHistory.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this user profile history.
	*
	* @return the class p k of this user profile history
	*/
	public long getClassPK() {
		return _userProfileHistory.getClassPK();
	}

	/**
	* Sets the class p k of this user profile history.
	*
	* @param classPK the class p k of this user profile history
	*/
	public void setClassPK(long classPK) {
		_userProfileHistory.setClassPK(classPK);
	}

	/**
	* Returns the email address of this user profile history.
	*
	* @return the email address of this user profile history
	*/
	public java.lang.String getEmailAddress() {
		return _userProfileHistory.getEmailAddress();
	}

	/**
	* Sets the email address of this user profile history.
	*
	* @param emailAddress the email address of this user profile history
	*/
	public void setEmailAddress(java.lang.String emailAddress) {
		_userProfileHistory.setEmailAddress(emailAddress);
	}

	/**
	* Returns the first name of this user profile history.
	*
	* @return the first name of this user profile history
	*/
	public java.lang.String getFirstName() {
		return _userProfileHistory.getFirstName();
	}

	/**
	* Sets the first name of this user profile history.
	*
	* @param firstName the first name of this user profile history
	*/
	public void setFirstName(java.lang.String firstName) {
		_userProfileHistory.setFirstName(firstName);
	}

	/**
	* Returns the last name of this user profile history.
	*
	* @return the last name of this user profile history
	*/
	public java.lang.String getLastName() {
		return _userProfileHistory.getLastName();
	}

	/**
	* Sets the last name of this user profile history.
	*
	* @param lastName the last name of this user profile history
	*/
	public void setLastName(java.lang.String lastName) {
		_userProfileHistory.setLastName(lastName);
	}

	/**
	* Returns the legal entity name of this user profile history.
	*
	* @return the legal entity name of this user profile history
	*/
	public java.lang.String getLegalEntityName() {
		return _userProfileHistory.getLegalEntityName();
	}

	/**
	* Sets the legal entity name of this user profile history.
	*
	* @param legalEntityName the legal entity name of this user profile history
	*/
	public void setLegalEntityName(java.lang.String legalEntityName) {
		_userProfileHistory.setLegalEntityName(legalEntityName);
	}

	public boolean isNew() {
		return _userProfileHistory.isNew();
	}

	public void setNew(boolean n) {
		_userProfileHistory.setNew(n);
	}

	public boolean isCachedModel() {
		return _userProfileHistory.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_userProfileHistory.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _userProfileHistory.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _userProfileHistory.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_userProfileHistory.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _userProfileHistory.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_userProfileHistory.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new UserProfileHistoryWrapper((UserProfileHistory)_userProfileHistory.clone());
	}

	public int compareTo(
		com.liferay.osb.model.UserProfileHistory userProfileHistory) {
		return _userProfileHistory.compareTo(userProfileHistory);
	}

	@Override
	public int hashCode() {
		return _userProfileHistory.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.UserProfileHistory> toCacheModel() {
		return _userProfileHistory.toCacheModel();
	}

	public com.liferay.osb.model.UserProfileHistory toEscapedModel() {
		return new UserProfileHistoryWrapper(_userProfileHistory.toEscapedModel());
	}

	public com.liferay.osb.model.UserProfileHistory toUnescapedModel() {
		return new UserProfileHistoryWrapper(_userProfileHistory.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _userProfileHistory.toString();
	}

	public java.lang.String toXmlString() {
		return _userProfileHistory.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_userProfileHistory.persist();
	}

	public java.lang.String getFullName() {
		return _userProfileHistory.getFullName();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserProfileHistoryWrapper)) {
			return false;
		}

		UserProfileHistoryWrapper userProfileHistoryWrapper = (UserProfileHistoryWrapper)obj;

		if (Validator.equals(_userProfileHistory,
					userProfileHistoryWrapper._userProfileHistory)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public UserProfileHistory getWrappedUserProfileHistory() {
		return _userProfileHistory;
	}

	public UserProfileHistory getWrappedModel() {
		return _userProfileHistory;
	}

	public void resetOriginalValues() {
		_userProfileHistory.resetOriginalValues();
	}

	private UserProfileHistory _userProfileHistory;
}