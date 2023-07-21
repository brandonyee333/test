/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ShoppingOrderItem service. Represents a row in the &quot;ShoppingOrderItem&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingOrderItemModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.shopping.model.impl.ShoppingOrderItemImpl"
)
@ProviderType
public interface ShoppingOrderItem
	extends PersistedModel, ShoppingOrderItemModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.shopping.model.impl.ShoppingOrderItemImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ShoppingOrderItem, Long>
		ORDER_ITEM_ID_ACCESSOR = new Accessor<ShoppingOrderItem, Long>() {

			@Override
			public Long get(ShoppingOrderItem shoppingOrderItem) {
				return shoppingOrderItem.getOrderItemId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ShoppingOrderItem> getTypeClass() {
				return ShoppingOrderItem.class;
			}

		};

}