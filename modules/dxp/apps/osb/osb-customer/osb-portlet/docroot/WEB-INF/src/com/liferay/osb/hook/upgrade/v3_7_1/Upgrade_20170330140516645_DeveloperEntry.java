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

package com.liferay.osb.hook.upgrade.v3_7_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Ryan Park
 */
public class Upgrade_20170330140516645_DeveloperEntry
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20170330140516645L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"alter table OSB_DeveloperEntry add column profileDescription " +
				"STRING");

		runSQL(
			"alter table OSB_DeveloperEntry add column profileEmailAddress " +
				"STRING");

		runSQL("alter table OSB_DeveloperEntry add column profileLogoId LONG");

		runSQL(
			"alter table OSB_DeveloperEntry change column website " +
				"profileWebsite STRING");
	}

}
*/

}