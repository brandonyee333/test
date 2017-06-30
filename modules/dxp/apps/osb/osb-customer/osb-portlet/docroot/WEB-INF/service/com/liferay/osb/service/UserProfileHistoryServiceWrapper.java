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
 * This class is a wrapper for {@link UserProfileHistoryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       UserProfileHistoryService
 * @generated
 */
public class UserProfileHistoryServiceWrapper
	implements UserProfileHistoryService,
		ServiceWrapper<UserProfileHistoryService> {
	public UserProfileHistoryServiceWrapper(
		UserProfileHistoryService userProfileHistoryService) {
		_userProfileHistoryService = userProfileHistoryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _userProfileHistoryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_userProfileHistoryService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _userProfileHistoryService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public UserProfileHistoryService getWrappedUserProfileHistoryService() {
		return _userProfileHistoryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedUserProfileHistoryService(
		UserProfileHistoryService userProfileHistoryService) {
		_userProfileHistoryService = userProfileHistoryService;
	}

	public UserProfileHistoryService getWrappedService() {
		return _userProfileHistoryService;
	}

	public void setWrappedService(
		UserProfileHistoryService userProfileHistoryService) {
		_userProfileHistoryService = userProfileHistoryService;
	}

	private UserProfileHistoryService _userProfileHistoryService;
}