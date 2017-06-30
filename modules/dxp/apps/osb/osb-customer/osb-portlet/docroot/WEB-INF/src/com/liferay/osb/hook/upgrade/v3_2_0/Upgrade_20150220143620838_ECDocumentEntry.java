/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.hook.upgrade.v3_2_0;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Douglas Wong
 */
public class Upgrade_20150220143620838_ECDocumentEntry
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20150220143620838L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasColumn("ECommerce_ECDocumentEntry", "subtotal") &&
			tableHasColumn("ECommerce_ECDocumentEntry", "taxAmount") &&
			tableHasColumn("ECommerce_ECDocumentEntry", "total")) {

			return;
		}

		if (!tableHasColumn("ECommerce_ECDocumentEntry", "subtotal")) {
			runSQL(
				"alter table ECommerce_ECDocumentEntry add column subtotal " +
					"DOUBLE");
		}

		if (!tableHasColumn("ECommerce_ECDocumentEntry", "taxAmount")) {
			runSQL(
				"alter table ECommerce_ECDocumentEntry add column taxAmount " +
					"DOUBLE");
		}

		if (!tableHasColumn("ECommerce_ECDocumentEntry", "total")) {
			runSQL(
				"alter table ECommerce_ECDocumentEntry add column total " +
					"DOUBLE");
		}

		StringBundler sb = new StringBundler(14);

		sb.append("update ECommerce_ECDocumentEntry ecDocumentEntry ");
		sb.append("inner join (select ecDocumentItem.ecDocumentEntryId, ");
		sb.append("SUM(ecDocumentItem.unitPrice * ecDocumentItem.quantity) ");
		sb.append("subtotal, SUM(ecDocumentItem.taxAmount) taxAmount, ");
		sb.append("SUM(ecDocumentItem.taxAmount + (ecDocumentItem.unitPrice ");
		sb.append("* ecDocumentItem.quantity)) total from ");
		sb.append("ECommerce_ECDocumentItem ecDocumentItem group by ");
		sb.append("ecDocumentItem.ecDocumentEntryId) ecDocumentItems on ");
		sb.append("ecDocumentEntry.ecDocumentEntryId = ");
		sb.append("ecDocumentItems.ecDocumentEntryId set ");
		sb.append("ecDocumentEntry.subtotal = ecDocumentItems.subtotal, ");
		sb.append("ecDocumentEntry.taxAmount = ecDocumentItems.taxAmount, ");
		sb.append("ecDocumentEntry.total = ecDocumentItems.total where ");
		sb.append("ecDocumentEntry.type_ = 1");

		runSQL(sb.toString());
	}

}