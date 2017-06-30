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
 * This class is a wrapper for {@link AppPackageService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppPackageService
 * @generated
 */
public class AppPackageServiceWrapper implements AppPackageService,
	ServiceWrapper<AppPackageService> {
	public AppPackageServiceWrapper(AppPackageService appPackageService) {
		_appPackageService = appPackageService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _appPackageService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_appPackageService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _appPackageService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.osb.model.AppPackage addAppPackage(long appEntryId,
		long appVersionId, int compatibility, boolean compatibilityPlus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackageService.addAppPackage(appEntryId, appVersionId,
			compatibility, compatibilityPlus);
	}

	public void addAppPackageSrc(long appPackageId, java.lang.String fileName,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appPackageService.addAppPackageSrc(appPackageId, fileName, file);
	}

	public com.liferay.osb.model.AppPackage deleteAppPackage(long appPackageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackageService.deleteAppPackage(appPackageId);
	}

	public void deleteAppPackageSrc(long appPackageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appPackageService.deleteAppPackageSrc(appPackageId);
	}

	public com.liferay.osb.model.AppPackage fetchAppPackage(long appVersionId,
		int compatibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackageService.fetchAppPackage(appVersionId, compatibility);
	}

	public com.liferay.osb.model.AppPackage getAppPackage(long appPackageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackageService.getAppPackage(appPackageId);
	}

	public com.liferay.osb.model.AppPackage updateAppPackage(
		long appPackageId, boolean compatibilityPlus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackageService.updateAppPackage(appPackageId,
			compatibilityPlus);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AppPackageService getWrappedAppPackageService() {
		return _appPackageService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAppPackageService(AppPackageService appPackageService) {
		_appPackageService = appPackageService;
	}

	public AppPackageService getWrappedService() {
		return _appPackageService;
	}

	public void setWrappedService(AppPackageService appPackageService) {
		_appPackageService = appPackageService;
	}

	private AppPackageService _appPackageService;
}