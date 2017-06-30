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
 * This class is a wrapper for {@link SupportWorkerAccountTierService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportWorkerAccountTierService
 * @generated
 */
public class SupportWorkerAccountTierServiceWrapper
	implements SupportWorkerAccountTierService,
		ServiceWrapper<SupportWorkerAccountTierService> {
	public SupportWorkerAccountTierServiceWrapper(
		SupportWorkerAccountTierService supportWorkerAccountTierService) {
		_supportWorkerAccountTierService = supportWorkerAccountTierService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _supportWorkerAccountTierService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_supportWorkerAccountTierService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportWorkerAccountTierService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SupportWorkerAccountTierService getWrappedSupportWorkerAccountTierService() {
		return _supportWorkerAccountTierService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSupportWorkerAccountTierService(
		SupportWorkerAccountTierService supportWorkerAccountTierService) {
		_supportWorkerAccountTierService = supportWorkerAccountTierService;
	}

	public SupportWorkerAccountTierService getWrappedService() {
		return _supportWorkerAccountTierService;
	}

	public void setWrappedService(
		SupportWorkerAccountTierService supportWorkerAccountTierService) {
		_supportWorkerAccountTierService = supportWorkerAccountTierService;
	}

	private SupportWorkerAccountTierService _supportWorkerAccountTierService;
}