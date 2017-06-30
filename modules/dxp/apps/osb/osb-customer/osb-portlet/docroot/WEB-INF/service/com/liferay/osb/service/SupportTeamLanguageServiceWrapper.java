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
 * This class is a wrapper for {@link SupportTeamLanguageService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportTeamLanguageService
 * @generated
 */
public class SupportTeamLanguageServiceWrapper
	implements SupportTeamLanguageService,
		ServiceWrapper<SupportTeamLanguageService> {
	public SupportTeamLanguageServiceWrapper(
		SupportTeamLanguageService supportTeamLanguageService) {
		_supportTeamLanguageService = supportTeamLanguageService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _supportTeamLanguageService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_supportTeamLanguageService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportTeamLanguageService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SupportTeamLanguageService getWrappedSupportTeamLanguageService() {
		return _supportTeamLanguageService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSupportTeamLanguageService(
		SupportTeamLanguageService supportTeamLanguageService) {
		_supportTeamLanguageService = supportTeamLanguageService;
	}

	public SupportTeamLanguageService getWrappedService() {
		return _supportTeamLanguageService;
	}

	public void setWrappedService(
		SupportTeamLanguageService supportTeamLanguageService) {
		_supportTeamLanguageService = supportTeamLanguageService;
	}

	private SupportTeamLanguageService _supportTeamLanguageService;
}