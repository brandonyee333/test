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
 * This class is a wrapper for {@link SupportWorkerSeverityService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportWorkerSeverityService
 * @generated
 */
public class SupportWorkerSeverityServiceWrapper
	implements SupportWorkerSeverityService,
		ServiceWrapper<SupportWorkerSeverityService> {
	public SupportWorkerSeverityServiceWrapper(
		SupportWorkerSeverityService supportWorkerSeverityService) {
		_supportWorkerSeverityService = supportWorkerSeverityService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _supportWorkerSeverityService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_supportWorkerSeverityService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportWorkerSeverityService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SupportWorkerSeverityService getWrappedSupportWorkerSeverityService() {
		return _supportWorkerSeverityService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSupportWorkerSeverityService(
		SupportWorkerSeverityService supportWorkerSeverityService) {
		_supportWorkerSeverityService = supportWorkerSeverityService;
	}

	public SupportWorkerSeverityService getWrappedService() {
		return _supportWorkerSeverityService;
	}

	public void setWrappedService(
		SupportWorkerSeverityService supportWorkerSeverityService) {
		_supportWorkerSeverityService = supportWorkerSeverityService;
	}

	private SupportWorkerSeverityService _supportWorkerSeverityService;
}