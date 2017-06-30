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
 * This class is a wrapper for {@link SupportWorkerService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportWorkerService
 * @generated
 */
public class SupportWorkerServiceWrapper implements SupportWorkerService,
	ServiceWrapper<SupportWorkerService> {
	public SupportWorkerServiceWrapper(
		SupportWorkerService supportWorkerService) {
		_supportWorkerService = supportWorkerService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _supportWorkerService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_supportWorkerService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportWorkerService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void clockInOut(long supportWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_supportWorkerService.clockInOut(supportWorkerId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SupportWorkerService getWrappedSupportWorkerService() {
		return _supportWorkerService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSupportWorkerService(
		SupportWorkerService supportWorkerService) {
		_supportWorkerService = supportWorkerService;
	}

	public SupportWorkerService getWrappedService() {
		return _supportWorkerService;
	}

	public void setWrappedService(SupportWorkerService supportWorkerService) {
		_supportWorkerService = supportWorkerService;
	}

	private SupportWorkerService _supportWorkerService;
}