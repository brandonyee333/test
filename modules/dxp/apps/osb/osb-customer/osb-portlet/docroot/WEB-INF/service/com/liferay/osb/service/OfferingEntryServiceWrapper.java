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
 * This class is a wrapper for {@link OfferingEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OfferingEntryService
 * @generated
 */
public class OfferingEntryServiceWrapper implements OfferingEntryService,
	ServiceWrapper<OfferingEntryService> {
	public OfferingEntryServiceWrapper(
		OfferingEntryService offeringEntryService) {
		_offeringEntryService = offeringEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _offeringEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_offeringEntryService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _offeringEntryService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public java.util.List<com.liferay.osb.model.OfferingEntry> getAccountEntryOfferingEntries(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryService.getAccountEntryOfferingEntries(accountEntryId);
	}

	public java.util.List<com.liferay.osb.model.OfferingEntry> getOrderEntryOfferingEntries(
		long orderEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryService.getOrderEntryOfferingEntries(orderEntryId);
	}

	public com.liferay.osb.model.OfferingEntry updateOfferingEntry(
		long offeringEntryId, long accountEntryId, long orderEntryId,
		long productEntryId, long supportResponseId,
		java.lang.String productDescription, int type, int version,
		boolean licenses, long licenseLifetime, long maxConcurrentUsers,
		long maxUsers, boolean supportTickets, long supportLifetime,
		int sizing, int quantity)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryService.updateOfferingEntry(offeringEntryId,
			accountEntryId, orderEntryId, productEntryId, supportResponseId,
			productDescription, type, version, licenses, licenseLifetime,
			maxConcurrentUsers, maxUsers, supportTickets, supportLifetime,
			sizing, quantity);
	}

	public com.liferay.osb.model.OfferingEntry updateStatus(
		long offeringEntryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryService.updateStatus(offeringEntryId, status);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public OfferingEntryService getWrappedOfferingEntryService() {
		return _offeringEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedOfferingEntryService(
		OfferingEntryService offeringEntryService) {
		_offeringEntryService = offeringEntryService;
	}

	public OfferingEntryService getWrappedService() {
		return _offeringEntryService;
	}

	public void setWrappedService(OfferingEntryService offeringEntryService) {
		_offeringEntryService = offeringEntryService;
	}

	private OfferingEntryService _offeringEntryService;
}