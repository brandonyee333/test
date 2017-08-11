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

package com.liferay.osb.hook.upgrade.v3_1_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Calvin Keum
 */
public class Upgrade_20141104153407828_TrainingCustomer extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20141104153407828L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"update OSB_ExternalIdMapper set classPK = 17156318 where " +
				"externalIdMapperId = 37752551");

		runSQL(
			"update OSB_TrainingCustomer set userId = 17156318 where " +
				"trainingCustomerId in " +
					"(37752540, 37752550, 39432516, 44769608)");

		runSQL(
			"update OSB_UserProfile set emailAddress = " +
				"'pablo.herrero.garcia@everis.com' where userProfileId = " +
					"37752548");

		runSQL(
			"update OSB_UserProfileHistory set emailAddress = " +
				"'pablo.herrero.garcia@everis.com' where " +
					"userProfileHistoryId in (37752549, 39432515, 44769607)");
	}

	 */

}