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
 * This class is a wrapper for {@link SecurityPatchService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SecurityPatchService
 * @generated
 */
public class SecurityPatchServiceWrapper implements SecurityPatchService,
	ServiceWrapper<SecurityPatchService> {
	public SecurityPatchServiceWrapper(
		SecurityPatchService securityPatchService) {
		_securityPatchService = securityPatchService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _securityPatchService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_securityPatchService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _securityPatchService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.SecurityPatch getSecurityPatch(
		long securityPatchId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _securityPatchService.getSecurityPatch(securityPatchId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SecurityPatchService getWrappedSecurityPatchService() {
		return _securityPatchService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSecurityPatchService(
		SecurityPatchService securityPatchService) {
		_securityPatchService = securityPatchService;
	}

	public SecurityPatchService getWrappedService() {
		return _securityPatchService;
	}

	public void setWrappedService(SecurityPatchService securityPatchService) {
		_securityPatchService = securityPatchService;
	}

	private SecurityPatchService _securityPatchService;
}