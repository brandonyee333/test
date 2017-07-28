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

package com.liferay.osb.hook.upgrade.v3_0_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Calvin Keum
 */
public class Upgrade_20140519170534985_TrainingCustomer extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20140519170534985L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"update OSB_ExternalIdMapper set classPK = 11261385 where " +
				"externalIdMapperId = 37707230");

		runSQL(
			"update OSB_TrainingCustomer set userId = 11261385 where " +
				"trainingCustomerId = 37707229");

		runSQL(
			"update OSB_UserProfile set userId = 11261385, emailAddress = " +
				"'anilgurjar86@gmail.com' where userProfileId = 37707227");

		runSQL(
			"update OSB_UserProfileHistory set userId = 11261385, " +
				"emailAddress = 'anilgurjar86@gmail.com' where " +
					"userProfileHistoryId = 37707228");
	}

}

*/

}