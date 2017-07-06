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
 * Provides a wrapper for {@link ContractEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContractEntryService
 * @generated
 */
@ProviderType
public class ContractEntryServiceWrapper implements ContractEntryService,
	ServiceWrapper<ContractEntryService> {
	public ContractEntryServiceWrapper(
		ContractEntryService contractEntryService) {
		_contractEntryService = contractEntryService;
	}

	@Override
	public com.liferay.osb.model.ContractEntry addContractEntry(
		long classNameId, long classPK, int type,
		java.util.Map<java.util.Locale, java.lang.String> contentMap,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryService.addContractEntry(classNameId, classPK,
			type, contentMap, serviceContext);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _contractEntryService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _contractEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public ContractEntryService getWrappedService() {
		return _contractEntryService;
	}

	@Override
	public void setWrappedService(ContractEntryService contractEntryService) {
		_contractEntryService = contractEntryService;
	}

	private ContractEntryService _contractEntryService;
}