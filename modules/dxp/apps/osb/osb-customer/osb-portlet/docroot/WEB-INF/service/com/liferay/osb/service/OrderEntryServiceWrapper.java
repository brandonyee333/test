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
 * Provides a wrapper for {@link OrderEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see OrderEntryService
 * @generated
 */
@ProviderType
public class OrderEntryServiceWrapper implements OrderEntryService,
	ServiceWrapper<OrderEntryService> {
	public OrderEntryServiceWrapper(OrderEntryService orderEntryService) {
		_orderEntryService = orderEntryService;
	}

	@Override
	public com.liferay.osb.model.OrderEntry getOrderEntry(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderEntryService.getOrderEntry(uuid);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _orderEntryService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _orderEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.model.OrderEntry> getOrderEntries(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderEntryService.getOrderEntries(corpProjectId);
	}

	@Override
	public OrderEntryService getWrappedService() {
		return _orderEntryService;
	}

	@Override
	public void setWrappedService(OrderEntryService orderEntryService) {
		_orderEntryService = orderEntryService;
	}

	private OrderEntryService _orderEntryService;
}