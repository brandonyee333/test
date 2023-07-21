/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service.persistence.impl;

import com.liferay.osb.customer.admin.model.ProductEntry;
import com.liferay.osb.customer.admin.service.persistence.ProductEntryPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProductEntryFinderBaseImpl
	extends BasePersistenceImpl<ProductEntry> {

	public ProductEntryFinderBaseImpl() {
		setModelClass(ProductEntry.class);
	}

	/**
	 * Returns the product entry persistence.
	 *
	 * @return the product entry persistence
	 */
	public ProductEntryPersistence getProductEntryPersistence() {
		return productEntryPersistence;
	}

	/**
	 * Sets the product entry persistence.
	 *
	 * @param productEntryPersistence the product entry persistence
	 */
	public void setProductEntryPersistence(
		ProductEntryPersistence productEntryPersistence) {

		this.productEntryPersistence = productEntryPersistence;
	}

	@BeanReference(type = ProductEntryPersistence.class)
	protected ProductEntryPersistence productEntryPersistence;

}