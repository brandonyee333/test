/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProductEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntryService
 * @generated
 */
public class ProductEntryServiceWrapper
	implements ProductEntryService, ServiceWrapper<ProductEntryService> {

	public ProductEntryServiceWrapper(ProductEntryService productEntryService) {
		_productEntryService = productEntryService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _productEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public ProductEntryService getWrappedService() {
		return _productEntryService;
	}

	@Override
	public void setWrappedService(ProductEntryService productEntryService) {
		_productEntryService = productEntryService;
	}

	private ProductEntryService _productEntryService;

}