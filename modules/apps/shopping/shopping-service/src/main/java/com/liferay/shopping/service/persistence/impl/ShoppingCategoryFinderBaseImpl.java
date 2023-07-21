/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.shopping.model.ShoppingCategory;
import com.liferay.shopping.service.persistence.ShoppingCategoryPersistence;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ShoppingCategoryFinderBaseImpl
	extends BasePersistenceImpl<ShoppingCategory> {

	public ShoppingCategoryFinderBaseImpl() {
		setModelClass(ShoppingCategory.class);
	}

	/**
	 * Returns the shopping category persistence.
	 *
	 * @return the shopping category persistence
	 */
	public ShoppingCategoryPersistence getShoppingCategoryPersistence() {
		return shoppingCategoryPersistence;
	}

	/**
	 * Sets the shopping category persistence.
	 *
	 * @param shoppingCategoryPersistence the shopping category persistence
	 */
	public void setShoppingCategoryPersistence(
		ShoppingCategoryPersistence shoppingCategoryPersistence) {

		this.shoppingCategoryPersistence = shoppingCategoryPersistence;
	}

	@BeanReference(type = ShoppingCategoryPersistence.class)
	protected ShoppingCategoryPersistence shoppingCategoryPersistence;

}