/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.model.impl;

import com.liferay.shopping.model.ShoppingCategoryConstants;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingCategoryImpl extends ShoppingCategoryBaseImpl {

	@Override
	public boolean isRoot() {
		if (getParentCategoryId() ==
				ShoppingCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {

			return true;
		}

		return false;
	}

}