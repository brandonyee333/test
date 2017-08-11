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

package com.liferay.osb.hook.upgrade.v3_1_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Rachael Koestartyo
 */
public class Upgrade_20140926155045619_SalesPartnershipAssignment
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20140926155045619L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasColumn(
				"OSB_SalesPartnershipAssignment",
				"salesPartnershipCountryId")) {

			return;
		}

		runSQL(
			"alter table OSB_SalesPartnershipAssignment change column " +
				"classPK salesPartnershipCountryId LONG");
		runSQL(
			"alter table OSB_SalesPartnershipAssignment drop column " +
				"classNameId");

		runSQL(
			"drop index IX_5FCE1901 on OSB_SalesPartnershipAssignment", false);

		runSQL(
			"create unique index IX_5F651E76 on " +
				"OSB_SalesPartnershipAssignment (salesPartnershipId, " +
					"salesPartnershipCountryId)");
	}

	 */

}