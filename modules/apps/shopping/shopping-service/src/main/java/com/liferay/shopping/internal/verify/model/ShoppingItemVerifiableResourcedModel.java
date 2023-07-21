/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.internal.verify.model;

import com.liferay.portal.kernel.verify.model.VerifiableResourcedModel;
import com.liferay.shopping.model.ShoppingItem;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingItemVerifiableResourcedModel
	implements VerifiableResourcedModel {

	@Override
	public String getModelName() {
		return ShoppingItem.class.getName();
	}

	@Override
	public String getPrimaryKeyColumnName() {
		return "itemId";
	}

	@Override
	public String getTableName() {
		return "ShoppingItem";
	}

	@Override
	public String getUserIdColumnName() {
		return "userId";
	}

}