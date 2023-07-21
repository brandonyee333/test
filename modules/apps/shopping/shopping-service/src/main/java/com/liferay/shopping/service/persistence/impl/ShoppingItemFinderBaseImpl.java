/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.shopping.model.ShoppingItem;
import com.liferay.shopping.service.persistence.ShoppingItemPersistence;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ShoppingItemFinderBaseImpl
	extends BasePersistenceImpl<ShoppingItem> {

	public ShoppingItemFinderBaseImpl() {
		setModelClass(ShoppingItem.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("fields", "fields_");
		dbColumnNames.put("featured", "featured_");
		dbColumnNames.put("sale", "sale_");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return getShoppingItemPersistence().getBadColumnNames();
	}

	/**
	 * Returns the shopping item persistence.
	 *
	 * @return the shopping item persistence
	 */
	public ShoppingItemPersistence getShoppingItemPersistence() {
		return shoppingItemPersistence;
	}

	/**
	 * Sets the shopping item persistence.
	 *
	 * @param shoppingItemPersistence the shopping item persistence
	 */
	public void setShoppingItemPersistence(
		ShoppingItemPersistence shoppingItemPersistence) {

		this.shoppingItemPersistence = shoppingItemPersistence;
	}

	@BeanReference(type = ShoppingItemPersistence.class)
	protected ShoppingItemPersistence shoppingItemPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		ShoppingItemFinderBaseImpl.class);

}