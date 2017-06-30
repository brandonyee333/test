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

package com.liferay.osb.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link UserProfileLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       UserProfileLocalService
 * @generated
 */
public class UserProfileLocalServiceWrapper implements UserProfileLocalService,
	ServiceWrapper<UserProfileLocalService> {
	public UserProfileLocalServiceWrapper(
		UserProfileLocalService userProfileLocalService) {
		_userProfileLocalService = userProfileLocalService;
	}

	/**
	* Adds the user profile to the database. Also notifies the appropriate model listeners.
	*
	* @param userProfile the user profile
	* @return the user profile that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.UserProfile addUserProfile(
		com.liferay.osb.model.UserProfile userProfile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.addUserProfile(userProfile);
	}

	/**
	* Creates a new user profile with the primary key. Does not add the user profile to the database.
	*
	* @param userProfileId the primary key for the new user profile
	* @return the new user profile
	*/
	public com.liferay.osb.model.UserProfile createUserProfile(
		long userProfileId) {
		return _userProfileLocalService.createUserProfile(userProfileId);
	}

	/**
	* Deletes the user profile with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userProfileId the primary key of the user profile
	* @return the user profile that was removed
	* @throws PortalException if a user profile with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.UserProfile deleteUserProfile(
		long userProfileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.deleteUserProfile(userProfileId);
	}

	/**
	* Deletes the user profile from the database. Also notifies the appropriate model listeners.
	*
	* @param userProfile the user profile
	* @return the user profile that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.UserProfile deleteUserProfile(
		com.liferay.osb.model.UserProfile userProfile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.deleteUserProfile(userProfile);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userProfileLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.UserProfile fetchUserProfile(
		long userProfileId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.fetchUserProfile(userProfileId);
	}

	/**
	* Returns the user profile with the primary key.
	*
	* @param userProfileId the primary key of the user profile
	* @return the user profile
	* @throws PortalException if a user profile with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.UserProfile getUserProfile(long userProfileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.getUserProfile(userProfileId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the user profiles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user profiles
	* @param end the upper bound of the range of user profiles (not inclusive)
	* @return the range of user profiles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.UserProfile> getUserProfiles(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.getUserProfiles(start, end);
	}

	/**
	* Returns the number of user profiles.
	*
	* @return the number of user profiles
	* @throws SystemException if a system exception occurred
	*/
	public int getUserProfilesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.getUserProfilesCount();
	}

	/**
	* Updates the user profile in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userProfile the user profile
	* @return the user profile that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.UserProfile updateUserProfile(
		com.liferay.osb.model.UserProfile userProfile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.updateUserProfile(userProfile);
	}

	/**
	* Updates the user profile in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userProfile the user profile
	* @param merge whether to merge the user profile with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the user profile that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.UserProfile updateUserProfile(
		com.liferay.osb.model.UserProfile userProfile, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.updateUserProfile(userProfile, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _userProfileLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_userProfileLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _userProfileLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.UserProfile addUserProfile(long userId,
		java.lang.String emailAddress, java.lang.String firstName,
		java.lang.String lastName, java.lang.String legalEntityName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.addUserProfile(userId, emailAddress,
			firstName, lastName, legalEntityName);
	}

	public java.util.List<com.liferay.osb.model.UserProfile> getUserProfiles(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.getUserProfiles(userId);
	}

	public int getUserProfilesCount(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userProfileLocalService.getUserProfilesCount(userId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public UserProfileLocalService getWrappedUserProfileLocalService() {
		return _userProfileLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedUserProfileLocalService(
		UserProfileLocalService userProfileLocalService) {
		_userProfileLocalService = userProfileLocalService;
	}

	public UserProfileLocalService getWrappedService() {
		return _userProfileLocalService;
	}

	public void setWrappedService(
		UserProfileLocalService userProfileLocalService) {
		_userProfileLocalService = userProfileLocalService;
	}

	private UserProfileLocalService _userProfileLocalService;
}