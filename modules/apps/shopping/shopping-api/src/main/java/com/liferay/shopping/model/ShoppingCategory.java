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
 * The extended model interface for the ShoppingCategory service. Represents a row in the &quot;ShoppingCategory&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingCategoryModel
 * @generated
 */
@ImplementationClassName("com.liferay.shopping.model.impl.ShoppingCategoryImpl")
@ProviderType
public interface ShoppingCategory
	extends PersistedModel, ShoppingCategoryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.shopping.model.impl.ShoppingCategoryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ShoppingCategory, Long> CATEGORY_ID_ACCESSOR =
		new Accessor<ShoppingCategory, Long>() {

			@Override
			public Long get(ShoppingCategory shoppingCategory) {
				return shoppingCategory.getCategoryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ShoppingCategory> getTypeClass() {
				return ShoppingCategory.class;
			}

		};

	public boolean isRoot();

}