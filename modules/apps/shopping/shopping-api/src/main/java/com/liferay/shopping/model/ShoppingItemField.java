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
 * The extended model interface for the ShoppingItemField service. Represents a row in the &quot;ShoppingItemField&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingItemFieldModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.shopping.model.impl.ShoppingItemFieldImpl"
)
@ProviderType
public interface ShoppingItemField
	extends PersistedModel, ShoppingItemFieldModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.shopping.model.impl.ShoppingItemFieldImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ShoppingItemField, Long>
		ITEM_FIELD_ID_ACCESSOR = new Accessor<ShoppingItemField, Long>() {

			@Override
			public Long get(ShoppingItemField shoppingItemField) {
				return shoppingItemField.getItemFieldId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ShoppingItemField> getTypeClass() {
				return ShoppingItemField.class;
			}

		};

	public String[] getValuesArray();

	public void setValuesArray(String[] valuesArray);

}