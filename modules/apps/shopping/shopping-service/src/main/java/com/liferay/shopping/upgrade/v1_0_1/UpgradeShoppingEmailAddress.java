/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.upgrade.v1_0_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.shopping.internal.upgrade.v1_0_1.util.ShoppingOrderTable;

/**
 * @author Tibor Lipusz
 */
public class UpgradeShoppingEmailAddress extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		alter(
			ShoppingOrderTable.class,
			new AlterColumnType("billingEmailAddress", "VARCHAR(254) null"),
			new AlterColumnType("shippingEmailAddress", "VARCHAR(254) null"),
			new AlterColumnType("ppReceiverEmail", "VARCHAR(254) null"),
			new AlterColumnType("ppPayerEmail", "VARCHAR(254) null"));
	}

}