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
 * The extended model interface for the ShoppingCart service. Represents a row in the &quot;ShoppingCart&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingCartModel
 * @generated
 */
@ImplementationClassName("com.liferay.shopping.model.impl.ShoppingCartImpl")
@ProviderType
public interface ShoppingCart extends PersistedModel, ShoppingCartModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.shopping.model.impl.ShoppingCartImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ShoppingCart, Long> CART_ID_ACCESSOR =
		new Accessor<ShoppingCart, Long>() {

			@Override
			public Long get(ShoppingCart shoppingCart) {
				return shoppingCart.getCartId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ShoppingCart> getTypeClass() {
				return ShoppingCart.class;
			}

		};

	public void addItemId(long itemId, String fields);

	public ShoppingCoupon getCoupon()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.util.Map<ShoppingCartItem, Integer> getItems();

	public int getItemsSize();

}