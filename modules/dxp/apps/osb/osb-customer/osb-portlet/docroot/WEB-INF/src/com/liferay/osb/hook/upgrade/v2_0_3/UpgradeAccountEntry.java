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

package com.liferay.osb.hook.upgrade.v2_0_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Alan Zhang
 */
public class UpgradeAccountEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateAccountEntry();
	}

	protected void updateAccountEntry() throws Exception {
		if (hasColumn("OSB_AccountEntry", "priority")) {
			return;
		}

		runSQL("alter table OSB_AccountEntry add column priority INTEGER");

		runSQL("update OSB_AccountEntry set priority = 0");
	}

}