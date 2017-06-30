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

package com.liferay.osb.hook.upgrade.v3_6_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Haote Chou
 */
public class Upgrade_20161102150010569_TrainingEvent
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20161102150010569L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"update OSB_TrainingEvent set portalMinorVersion = 50000 " +
				"where portalMinorVersion = 22000");

		runSQL(
			"update OSB_TrainingEvent set portalMinorVersion = 50001 " +
				"where portalMinorVersion = 22001");

		runSQL(
			"update OSB_TrainingEvent set portalMinorVersion = 50002 " +
				"where portalMinorVersion = 22002");

		runSQL(
			"update OSB_TrainingEvent set portalMinorVersion = 50003 " +
				"where portalMinorVersion = 22003");

		runSQL(
			"update OSB_TrainingEvent set portalMinorVersion = 50004 " +
				"where portalMinorVersion = 22004");

		runSQL(
			"update OSB_TrainingEvent set portalMinorVersion = 50005 " +
				"where portalMinorVersion = 22005");
	}

}