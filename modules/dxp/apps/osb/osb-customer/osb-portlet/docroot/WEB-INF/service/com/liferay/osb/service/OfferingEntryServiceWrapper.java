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
 * Provides a wrapper for {@link OfferingEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingEntryService
 * @generated
 */
@ProviderType
public class OfferingEntryServiceWrapper implements OfferingEntryService,
	ServiceWrapper<OfferingEntryService> {
	public OfferingEntryServiceWrapper(
		OfferingEntryService offeringEntryService) {
		_offeringEntryService = offeringEntryService;
	}

	@Override
	public com.liferay.osb.model.OfferingEntry updateOfferingEntry(
		long offeringEntryId, long accountEntryId, long orderEntryId,
		long productEntryId, long supportResponseId,
		java.lang.String productDescription, int type, int version,
		boolean licenses, long licenseLifetime, long maxConcurrentUsers,
		long maxUsers, boolean supportTickets, long supportLifetime,
		int sizing, int quantity)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntryService.updateOfferingEntry(offeringEntryId,
			accountEntryId, orderEntryId, productEntryId, supportResponseId,
			productDescription, type, version, licenses, licenseLifetime,
			maxConcurrentUsers, maxUsers, supportTickets, supportLifetime,
			sizing, quantity);
	}

	@Override
	public com.liferay.osb.model.OfferingEntry updateStatus(
		long offeringEntryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntryService.updateStatus(offeringEntryId, status);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _offeringEntryService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _offeringEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.model.OfferingEntry> getAccountEntryOfferingEntries(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntryService.getAccountEntryOfferingEntries(accountEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.OfferingEntry> getOrderEntryOfferingEntries(
		long orderEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntryService.getOrderEntryOfferingEntries(orderEntryId);
	}

	@Override
	public OfferingEntryService getWrappedService() {
		return _offeringEntryService;
	}

	@Override
	public void setWrappedService(OfferingEntryService offeringEntryService) {
		_offeringEntryService = offeringEntryService;
	}

	private OfferingEntryService _offeringEntryService;
}