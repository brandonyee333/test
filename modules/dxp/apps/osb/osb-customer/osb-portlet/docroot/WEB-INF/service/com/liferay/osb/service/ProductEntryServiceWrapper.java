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
 * This class is a wrapper for {@link ProductEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProductEntryService
 * @generated
 */
public class ProductEntryServiceWrapper implements ProductEntryService,
	ServiceWrapper<ProductEntryService> {
	public ProductEntryServiceWrapper(ProductEntryService productEntryService) {
		_productEntryService = productEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _productEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_productEntryService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _productEntryService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ProductEntryService getWrappedProductEntryService() {
		return _productEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedProductEntryService(
		ProductEntryService productEntryService) {
		_productEntryService = productEntryService;
	}

	public ProductEntryService getWrappedService() {
		return _productEntryService;
	}

	public void setWrappedService(ProductEntryService productEntryService) {
		_productEntryService = productEntryService;
	}

	private ProductEntryService _productEntryService;
}