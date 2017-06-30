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
 * This class is a wrapper for {@link AppVersionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppVersionService
 * @generated
 */
public class AppVersionServiceWrapper implements AppVersionService,
	ServiceWrapper<AppVersionService> {
	public AppVersionServiceWrapper(AppVersionService appVersionService) {
		_appVersionService = appVersionService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _appVersionService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_appVersionService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _appVersionService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.osb.model.AppVersion deleteAppVersion(long appVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appVersionService.deleteAppVersion(appVersionId);
	}

	public com.liferay.osb.model.AppVersion getAppVersion(long appVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appVersionService.getAppVersion(appVersionId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AppVersionService getWrappedAppVersionService() {
		return _appVersionService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAppVersionService(AppVersionService appVersionService) {
		_appVersionService = appVersionService;
	}

	public AppVersionService getWrappedService() {
		return _appVersionService;
	}

	public void setWrappedService(AppVersionService appVersionService) {
		_appVersionService = appVersionService;
	}

	private AppVersionService _appVersionService;
}