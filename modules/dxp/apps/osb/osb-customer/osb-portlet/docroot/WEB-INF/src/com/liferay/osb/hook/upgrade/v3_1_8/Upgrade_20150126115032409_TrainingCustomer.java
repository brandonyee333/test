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

package com.liferay.osb.hook.upgrade.v3_1_8;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Val Nagy
 */
public class Upgrade_20150126115032409_TrainingCustomer
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20150126115032409L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"update OSB_ExternalIdMapper set classPK = 34637503 where " +
				"externalIdMapperId = 38404643");

		runSQL(
			"update OSB_TrainingCustomer set userId = 34637503 where " +
				"trainingCustomerId in (38404642, 44769465)");

		runSQL(
			"update OSB_UserProfile set emailAddress = " +
				"'josemaria.munoz@vass.es' where userId = 17553189");

		runSQL(
			"update OSB_UserProfileHistory set emailAddress = " +
				"'josemaria.munoz@vass.es' where userProfileHistoryId in " +
					"(38404641, 44769464, 44769468, 44876270)");
	}

}
*/

}