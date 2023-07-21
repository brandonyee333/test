/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.ticket.internal.upgrade.v1_0_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Amos Fong
 */
public class UpgradeTicketAttachment extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("OSBCustomer_TicketAttachment", "regionRestricted")) {
			runSQL(
				"alter table OSBCustomer_TicketAttachment add column " +
					"regionRestricted boolean");
			runSQL(
				"alter table OSBCustomer_TicketAttachment add column " +
					"userRole int");
		}
	}

}