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

package com.liferay.osb.hook.upgrade.v3_1_5;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Calvin Keum
 */
public class Upgrade_20141208163052068_UserProfile extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20141208163052068L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"update OSB_UserProfile set emailAddress = " +
				"'cnukvd100@gmail.com' where userProfileId = 46313414");

		runSQL(
			"update OSB_UserProfileHistory set emailAddress = " +
				"'cnukvd100@gmail.com' where userProfileHistoryId = 46313415");
	}

}