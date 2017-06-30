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
 * This class is a wrapper for {@link SupportTeamService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportTeamService
 * @generated
 */
public class SupportTeamServiceWrapper implements SupportTeamService,
	ServiceWrapper<SupportTeamService> {
	public SupportTeamServiceWrapper(SupportTeamService supportTeamService) {
		_supportTeamService = supportTeamService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _supportTeamService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_supportTeamService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportTeamService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SupportTeamService getWrappedSupportTeamService() {
		return _supportTeamService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSupportTeamService(
		SupportTeamService supportTeamService) {
		_supportTeamService = supportTeamService;
	}

	public SupportTeamService getWrappedService() {
		return _supportTeamService;
	}

	public void setWrappedService(SupportTeamService supportTeamService) {
		_supportTeamService = supportTeamService;
	}

	private SupportTeamService _supportTeamService;
}