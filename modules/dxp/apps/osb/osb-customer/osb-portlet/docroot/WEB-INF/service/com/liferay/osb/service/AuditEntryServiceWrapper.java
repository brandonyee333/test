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
 * This class is a wrapper for {@link AuditEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AuditEntryService
 * @generated
 */
public class AuditEntryServiceWrapper implements AuditEntryService,
	ServiceWrapper<AuditEntryService> {
	public AuditEntryServiceWrapper(AuditEntryService auditEntryService) {
		_auditEntryService = auditEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _auditEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_auditEntryService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _auditEntryService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AuditEntryService getWrappedAuditEntryService() {
		return _auditEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAuditEntryService(AuditEntryService auditEntryService) {
		_auditEntryService = auditEntryService;
	}

	public AuditEntryService getWrappedService() {
		return _auditEntryService;
	}

	public void setWrappedService(AuditEntryService auditEntryService) {
		_auditEntryService = auditEntryService;
	}

	private AuditEntryService _auditEntryService;
}