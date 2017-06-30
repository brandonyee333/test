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
 * This class is a wrapper for {@link AppEntryRelService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppEntryRelService
 * @generated
 */
public class AppEntryRelServiceWrapper implements AppEntryRelService,
	ServiceWrapper<AppEntryRelService> {
	public AppEntryRelServiceWrapper(AppEntryRelService appEntryRelService) {
		_appEntryRelService = appEntryRelService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _appEntryRelService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_appEntryRelService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _appEntryRelService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.osb.model.AppEntryRel addAppEntryRel(long appEntryId1,
		long appEntryId2, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appEntryRelService.addAppEntryRel(appEntryId1, appEntryId2, type);
	}

	public com.liferay.osb.model.AppEntryRel deleteAppEntryRel(
		long appEntryRelId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appEntryRelService.deleteAppEntryRel(appEntryRelId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AppEntryRelService getWrappedAppEntryRelService() {
		return _appEntryRelService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAppEntryRelService(
		AppEntryRelService appEntryRelService) {
		_appEntryRelService = appEntryRelService;
	}

	public AppEntryRelService getWrappedService() {
		return _appEntryRelService;
	}

	public void setWrappedService(AppEntryRelService appEntryRelService) {
		_appEntryRelService = appEntryRelService;
	}

	private AppEntryRelService _appEntryRelService;
}