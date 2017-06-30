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
 * This class is a wrapper for {@link OrderEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OrderEntryService
 * @generated
 */
public class OrderEntryServiceWrapper implements OrderEntryService,
	ServiceWrapper<OrderEntryService> {
	public OrderEntryServiceWrapper(OrderEntryService orderEntryService) {
		_orderEntryService = orderEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _orderEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_orderEntryService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _orderEntryService.invokeMethod(name, parameterTypes, arguments);
	}

	public java.util.List<com.liferay.osb.model.OrderEntry> getOrderEntries(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryService.getOrderEntries(corpProjectId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public OrderEntryService getWrappedOrderEntryService() {
		return _orderEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedOrderEntryService(OrderEntryService orderEntryService) {
		_orderEntryService = orderEntryService;
	}

	public OrderEntryService getWrappedService() {
		return _orderEntryService;
	}

	public void setWrappedService(OrderEntryService orderEntryService) {
		_orderEntryService = orderEntryService;
	}

	private OrderEntryService _orderEntryService;
}