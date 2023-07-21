/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.upgrade.v1_0_0;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;
import com.liferay.shopping.constants.ShoppingPortletKeys;

/**
 * @author Peter Fellwock
 */
public class UpgradePortletId extends BaseUpgradePortletId {

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			{"34", ShoppingPortletKeys.SHOPPING},
			{"97", ShoppingPortletKeys.SHOPPING_ADMIN}
		};
	}

}