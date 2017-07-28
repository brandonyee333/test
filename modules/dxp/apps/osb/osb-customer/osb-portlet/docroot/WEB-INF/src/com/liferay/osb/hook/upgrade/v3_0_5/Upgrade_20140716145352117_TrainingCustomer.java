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

package com.liferay.osb.hook.upgrade.v3_0_5;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Calvin Keum
 */
public class Upgrade_20140716145352117_TrainingCustomer extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20140716145352117L;
	}

	@Override
	protected void doUpgrade() throws Exception {

		// sr9815121

		runSQL(
			"update OSB_ExternalIdMapper set classPK = 3151239 where " +
				"externalIdMapperId = 37752628");

		runSQL(
			"update OSB_TrainingCustomer set userId = 3151239 where " +
				"trainingCustomerId = 37752617");

		runSQL(
			"update OSB_TrainingCustomer set userId = 3151239 where " +
				"trainingCustomerId = 37752627");

		runSQL(
			"update OSB_UserProfile set userId = 3151239, emailAddress = " +
				"'dgarciaor@gmail.com' where userProfileId = 37752625");

		runSQL(
			"update OSB_UserProfile set userId = 3151239, emailAddress = " +
				"'dgarciaor@gmail.com' where userProfileId = 39083150");

		runSQL(
			"update OSB_UserProfileHistory set userId = 3151239, " +
				"emailAddress = 'dgarciaor@gmail.com' where " +
					"userProfileHistoryId = 37752626");

		runSQL(
			"update OSB_UserProfileHistory set userId = 3151239, " +
				"emailAddress = 'dgarciaor@gmail.com' where " +
					"userProfileHistoryId = 39083151");

		// sr9879584

		runSQL(
			"update OSB_ExternalIdMapper set classPK = 15507777 where " +
				"externalIdMapperId = 38399118");

		runSQL(
			"update OSB_TrainingCustomer set userId = 15507777 where " +
				"trainingCustomerId = 38399107");

		runSQL(
			"update OSB_TrainingCustomer set userId = 15507777, " +
				"userProfileHistoryId = 40130356 where trainingCustomerId = " +
					"38399117");

		runSQL(
			"update OSB_TrainingCustomer set userId = 15507777 where " +
				"trainingCustomerId = 40130357");

		runSQL(
			"update OSB_UserProfile set userId = 15507777 where " +
				"userProfileId = 40130355");

		runSQL(
			"update OSB_UserProfileHistory set userId = 15507777 where " +
				"userProfileHistoryId = 40130356");

		runSQL("delete from OSB_UserProfile where userProfileId = 38399115");

		runSQL(
			"delete from OSB_UserProfileHistory where userProfileHistoryId " +
				"= 38399116");
	}

}

*/

}