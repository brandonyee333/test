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
 * This class is a wrapper for {@link AppFlagService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppFlagService
 * @generated
 */
public class AppFlagServiceWrapper implements AppFlagService,
	ServiceWrapper<AppFlagService> {
	public AppFlagServiceWrapper(AppFlagService appFlagService) {
		_appFlagService = appFlagService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _appFlagService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_appFlagService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _appFlagService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.osb.model.AppFlag addAppFlag(long appEntryId,
		long appVersionId, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appFlagService.addAppFlag(appEntryId, appVersionId, type);
	}

	public com.liferay.osb.model.AppFlag deleteAppFlag(long appFlagId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appFlagService.deleteAppFlag(appFlagId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AppFlagService getWrappedAppFlagService() {
		return _appFlagService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAppFlagService(AppFlagService appFlagService) {
		_appFlagService = appFlagService;
	}

	public AppFlagService getWrappedService() {
		return _appFlagService;
	}

	public void setWrappedService(AppFlagService appFlagService) {
		_appFlagService = appFlagService;
	}

	private AppFlagService _appFlagService;
}