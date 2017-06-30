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
 * This class is a wrapper for {@link ContractEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContractEntryService
 * @generated
 */
public class ContractEntryServiceWrapper implements ContractEntryService,
	ServiceWrapper<ContractEntryService> {
	public ContractEntryServiceWrapper(
		ContractEntryService contractEntryService) {
		_contractEntryService = contractEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _contractEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_contractEntryService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _contractEntryService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.ContractEntry addContractEntry(
		long classNameId, long classPK, int type,
		java.util.Map<java.util.Locale, java.lang.String> contentMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryService.addContractEntry(classNameId, classPK,
			type, contentMap, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ContractEntryService getWrappedContractEntryService() {
		return _contractEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedContractEntryService(
		ContractEntryService contractEntryService) {
		_contractEntryService = contractEntryService;
	}

	public ContractEntryService getWrappedService() {
		return _contractEntryService;
	}

	public void setWrappedService(ContractEntryService contractEntryService) {
		_contractEntryService = contractEntryService;
	}

	private ContractEntryService _contractEntryService;
}