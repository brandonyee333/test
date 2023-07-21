/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Amos Fong
 */
public class UpgradeSchema extends UpgradeProcess {

	public UpgradeSchema() {
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"alter table OSB_LicenseKey add column koroneikiAccountKey " +
				"varchar(75)");
		runSQL(
			"alter table OSB_LicenseKey add column " +
				"koroneikiProductPurchaseKey varchar(75)");

		runSQL(
			"alter table OSB_LicenseKeySet add column koroneikiAccountKey " +
				"varchar(75)");

		runSQL("alter table OSB_ProductEntry add column licenses tinyint");
	}

}