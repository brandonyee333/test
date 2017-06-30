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
 * This class is a wrapper for {@link PartnerWorkerService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PartnerWorkerService
 * @generated
 */
public class PartnerWorkerServiceWrapper implements PartnerWorkerService,
	ServiceWrapper<PartnerWorkerService> {
	public PartnerWorkerServiceWrapper(
		PartnerWorkerService partnerWorkerService) {
		_partnerWorkerService = partnerWorkerService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _partnerWorkerService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_partnerWorkerService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _partnerWorkerService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PartnerWorkerService getWrappedPartnerWorkerService() {
		return _partnerWorkerService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPartnerWorkerService(
		PartnerWorkerService partnerWorkerService) {
		_partnerWorkerService = partnerWorkerService;
	}

	public PartnerWorkerService getWrappedService() {
		return _partnerWorkerService;
	}

	public void setWrappedService(PartnerWorkerService partnerWorkerService) {
		_partnerWorkerService = partnerWorkerService;
	}

	private PartnerWorkerService _partnerWorkerService;
}