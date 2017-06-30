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
 * This class is a wrapper for {@link ContractAuditService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContractAuditService
 * @generated
 */
public class ContractAuditServiceWrapper implements ContractAuditService,
	ServiceWrapper<ContractAuditService> {
	public ContractAuditServiceWrapper(
		ContractAuditService contractAuditService) {
		_contractAuditService = contractAuditService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _contractAuditService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_contractAuditService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _contractAuditService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void addContractAudit(long contractEntryId,
		java.lang.String signatoryClassName, long signatoryClassPK,
		java.lang.String productClassName, long productClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_contractAuditService.addContractAudit(contractEntryId,
			signatoryClassName, signatoryClassPK, productClassName,
			productClassPK);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ContractAuditService getWrappedContractAuditService() {
		return _contractAuditService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedContractAuditService(
		ContractAuditService contractAuditService) {
		_contractAuditService = contractAuditService;
	}

	public ContractAuditService getWrappedService() {
		return _contractAuditService;
	}

	public void setWrappedService(ContractAuditService contractAuditService) {
		_contractAuditService = contractAuditService;
	}

	private ContractAuditService _contractAuditService;
}