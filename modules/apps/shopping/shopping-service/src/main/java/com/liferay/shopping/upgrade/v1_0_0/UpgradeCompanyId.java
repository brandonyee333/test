/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.upgrade.v1_0_0;

import com.liferay.portal.kernel.upgrade.BaseUpgradeCompanyId;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeCompanyId extends BaseUpgradeCompanyId {

	@Override
	protected void doUpgrade() throws Exception {
		super.doUpgrade();

		updateShoppingOrderItem();
	}

	@Override
	protected TableUpdater[] getTableUpdaters() {
		return new TableUpdater[] {
			new TableUpdater("ShoppingItemField", "ShoppingItem", "itemId"),
			new TableUpdater("ShoppingItemPrice", "ShoppingItem", "itemId")
		};
	}

	protected void updateShoppingOrderItem() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			runSQL("alter table ShoppingOrderItem add companyId LONG");

			StringBundler sb = new StringBundler(6);

			sb.append("update ShoppingOrderItem set companyId = (select ");
			sb.append("max(ShoppingItem.companyId) from ShoppingItem where ");
			sb.append("SUBSTR(ShoppingOrderItem.itemId, 1, INSTR('|', ");
			sb.append("ShoppingOrderItem.itemId)) = ");
			sb.append("CAST_TEXT(ShoppingItem.itemId)) where ");
			sb.append("ShoppingOrderItem.itemId like '%|%' ");

			runSQL(sb.toString());

			sb = new StringBundler(5);

			sb.append("update ShoppingOrderItem set companyId = (select ");
			sb.append("max(ShoppingItem.companyId) from ShoppingItem where ");
			sb.append("ShoppingOrderItem.itemId = ");
			sb.append("CAST_TEXT(ShoppingItem.itemId)) where ");
			sb.append("ShoppingOrderItem.itemId not like '%|%' ");

			runSQL(sb.toString());
		}
	}

}