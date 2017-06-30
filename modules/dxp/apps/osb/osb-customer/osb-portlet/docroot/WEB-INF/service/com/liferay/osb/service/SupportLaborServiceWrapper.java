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
 * This class is a wrapper for {@link SupportLaborService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportLaborService
 * @generated
 */
public class SupportLaborServiceWrapper implements SupportLaborService,
	ServiceWrapper<SupportLaborService> {
	public SupportLaborServiceWrapper(SupportLaborService supportLaborService) {
		_supportLaborService = supportLaborService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _supportLaborService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_supportLaborService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportLaborService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SupportLaborService getWrappedSupportLaborService() {
		return _supportLaborService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSupportLaborService(
		SupportLaborService supportLaborService) {
		_supportLaborService = supportLaborService;
	}

	public SupportLaborService getWrappedService() {
		return _supportLaborService;
	}

	public void setWrappedService(SupportLaborService supportLaborService) {
		_supportLaborService = supportLaborService;
	}

	private SupportLaborService _supportLaborService;
}