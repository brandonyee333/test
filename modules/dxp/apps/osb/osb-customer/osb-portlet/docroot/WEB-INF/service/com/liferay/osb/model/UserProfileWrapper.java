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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserProfile}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       UserProfile
 * @generated
 */
public class UserProfileWrapper implements UserProfile,
	ModelWrapper<UserProfile> {
	public UserProfileWrapper(UserProfile userProfile) {
		_userProfile = userProfile;
	}

	public Class<?> getModelClass() {
		return UserProfile.class;
	}

	public String getModelClassName() {
		return UserProfile.class.getName();
	}

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

	/**
	* Returns the primary key of this user profile.
	*
	* @return the primary key of this user profile
	*/
	public long getPrimaryKey() {
		return _userProfile.getPrimaryKey();
	}

	/**
	* Sets the primary key of this user profile.
	*
	* @param primaryKey the primary key of this user profile
	*/
	public void setPrimaryKey(long primaryKey) {
		_userProfile.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the user profile ID of this user profile.
	*
	* @return the user profile ID of this user profile
	*/
	public long getUserProfileId() {
		return _userProfile.getUserProfileId();
	}

	/**
	* Sets the user profile ID of this user profile.
	*
	* @param userProfileId the user profile ID of this user profile
	*/
	public void setUserProfileId(long userProfileId) {
		_userProfile.setUserProfileId(userProfileId);
	}

	/**
	* Returns the user ID of this user profile.
	*
	* @return the user ID of this user profile
	*/
	public long getUserId() {
		return _userProfile.getUserId();
	}

	/**
	* Sets the user ID of this user profile.
	*
	* @param userId the user ID of this user profile
	*/
	public void setUserId(long userId) {
		_userProfile.setUserId(userId);
	}

	/**
	* Returns the user uuid of this user profile.
	*
	* @return the user uuid of this user profile
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userProfile.getUserUuid();
	}

	/**
	* Sets the user uuid of this user profile.
	*
	* @param userUuid the user uuid of this user profile
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_userProfile.setUserUuid(userUuid);
	}

	/**
	* Returns the email address of this user profile.
	*
	* @return the email address of this user profile
	*/
	public java.lang.String getEmailAddress() {
		return _userProfile.getEmailAddress();
	}

	/**
	* Sets the email address of this user profile.
	*
	* @param emailAddress the email address of this user profile
	*/
	public void setEmailAddress(java.lang.String emailAddress) {
		_userProfile.setEmailAddress(emailAddress);
	}

	/**
	* Returns the first name of this user profile.
	*
	* @return the first name of this user profile
	*/
	public java.lang.String getFirstName() {
		return _userProfile.getFirstName();
	}

	/**
	* Sets the first name of this user profile.
	*
	* @param firstName the first name of this user profile
	*/
	public void setFirstName(java.lang.String firstName) {
		_userProfile.setFirstName(firstName);
	}

	/**
	* Returns the last name of this user profile.
	*
	* @return the last name of this user profile
	*/
	public java.lang.String getLastName() {
		return _userProfile.getLastName();
	}

	/**
	* Sets the last name of this user profile.
	*
	* @param lastName the last name of this user profile
	*/
	public void setLastName(java.lang.String lastName) {
		_userProfile.setLastName(lastName);
	}

	/**
	* Returns the legal entity name of this user profile.
	*
	* @return the legal entity name of this user profile
	*/
	public java.lang.String getLegalEntityName() {
		return _userProfile.getLegalEntityName();
	}

	/**
	* Sets the legal entity name of this user profile.
	*
	* @param legalEntityName the legal entity name of this user profile
	*/
	public void setLegalEntityName(java.lang.String legalEntityName) {
		_userProfile.setLegalEntityName(legalEntityName);
	}

	public boolean isNew() {
		return _userProfile.isNew();
	}

	public void setNew(boolean n) {
		_userProfile.setNew(n);
	}

	public boolean isCachedModel() {
		return _userProfile.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_userProfile.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _userProfile.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _userProfile.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_userProfile.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _userProfile.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_userProfile.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new UserProfileWrapper((UserProfile)_userProfile.clone());
	}

	public int compareTo(com.liferay.osb.model.UserProfile userProfile) {
		return _userProfile.compareTo(userProfile);
	}

	@Override
	public int hashCode() {
		return _userProfile.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.UserProfile> toCacheModel() {
		return _userProfile.toCacheModel();
	}

	public com.liferay.osb.model.UserProfile toEscapedModel() {
		return new UserProfileWrapper(_userProfile.toEscapedModel());
	}

	public com.liferay.osb.model.UserProfile toUnescapedModel() {
		return new UserProfileWrapper(_userProfile.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _userProfile.toString();
	}

	public java.lang.String toXmlString() {
		return _userProfile.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_userProfile.persist();
	}

	public java.lang.String getFullName() {
		return _userProfile.getFullName();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserProfileWrapper)) {
			return false;
		}

		UserProfileWrapper userProfileWrapper = (UserProfileWrapper)obj;

		if (Validator.equals(_userProfile, userProfileWrapper._userProfile)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public UserProfile getWrappedUserProfile() {
		return _userProfile;
	}

	public UserProfile getWrappedModel() {
		return _userProfile;
	}

	public void resetOriginalValues() {
		_userProfile.resetOriginalValues();
	}

	private UserProfile _userProfile;
}