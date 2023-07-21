/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.upgrade.v1_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.shopping.internal.upgrade.v1_0_0.util.ShoppingCartTable;
import com.liferay.shopping.internal.upgrade.v1_0_0.util.ShoppingOrderItemTable;
import com.liferay.shopping.internal.upgrade.v1_0_0.util.ShoppingOrderTable;

/**
 * @author Kenneth Chang
 */
public class UpgradeShopping extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		alter(
			ShoppingCartTable.class,
			new AlterColumnType("itemIds", "TEXT null"));

		alter(
			ShoppingOrderTable.class,
			new AlterColumnType("comments", "TEXT null"));

		alter(
			ShoppingOrderItemTable.class,
			new AlterColumnType("itemId", "TEXT null"));
	}

}