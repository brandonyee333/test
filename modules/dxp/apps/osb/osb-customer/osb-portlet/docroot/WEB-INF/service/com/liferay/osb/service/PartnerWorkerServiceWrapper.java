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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PartnerWorkerService}.
 *
 * @author Brian Wing Shun Chan
 * @see PartnerWorkerService
 * @generated
 */
@ProviderType
public class PartnerWorkerServiceWrapper implements PartnerWorkerService,
	ServiceWrapper<PartnerWorkerService> {
	public PartnerWorkerServiceWrapper(
		PartnerWorkerService partnerWorkerService) {
		_partnerWorkerService = partnerWorkerService;
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _partnerWorkerService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _partnerWorkerService.getOSGiServiceIdentifier();
	}

	@Override
	public PartnerWorkerService getWrappedService() {
		return _partnerWorkerService;
	}

	@Override
	public void setWrappedService(PartnerWorkerService partnerWorkerService) {
		_partnerWorkerService = partnerWorkerService;
	}

	private PartnerWorkerService _partnerWorkerService;
}