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
 * This class is a wrapper for {@link AppPackagePluginService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppPackagePluginService
 * @generated
 */
public class AppPackagePluginServiceWrapper implements AppPackagePluginService,
	ServiceWrapper<AppPackagePluginService> {
	public AppPackagePluginServiceWrapper(
		AppPackagePluginService appPackagePluginService) {
		_appPackagePluginService = appPackagePluginService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _appPackagePluginService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_appPackagePluginService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _appPackagePluginService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.AppPackagePlugin addAppPackagePlugin(
		long appVersionId, java.lang.String fileName, java.io.File file,
		int compatibility, boolean compatibilityPlus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginService.addAppPackagePlugin(appVersionId,
			fileName, file, compatibility, compatibilityPlus);
	}

	public com.liferay.osb.model.AppPackagePlugin deleteAppPackagePlugin(
		long appPackagePluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginService.deleteAppPackagePlugin(appPackagePluginId);
	}

	public void deleteInvalidAppPackagePlugins(long appVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appPackagePluginService.deleteInvalidAppPackagePlugins(appVersionId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AppPackagePluginService getWrappedAppPackagePluginService() {
		return _appPackagePluginService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAppPackagePluginService(
		AppPackagePluginService appPackagePluginService) {
		_appPackagePluginService = appPackagePluginService;
	}

	public AppPackagePluginService getWrappedService() {
		return _appPackagePluginService;
	}

	public void setWrappedService(
		AppPackagePluginService appPackagePluginService) {
		_appPackagePluginService = appPackagePluginService;
	}

	private AppPackagePluginService _appPackagePluginService;
}