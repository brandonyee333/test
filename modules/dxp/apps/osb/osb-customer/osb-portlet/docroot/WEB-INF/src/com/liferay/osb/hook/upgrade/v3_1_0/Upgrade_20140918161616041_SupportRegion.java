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

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Amos Fong
 */
public class Upgrade_20140918161616041_SupportRegion
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20140918161616041L;
	}

	protected void addTimeZoneId() throws Exception {
		if (hasColumn("OSB_SupportRegion", "timeZoneId")) {
			return;
		}

		runSQL(
			"alter table OSB_SupportRegion add column timeZoneId VARCHAR(75)");
	}

	@Override
	protected void doUpgrade() throws Exception {
		addTimeZoneId();
		populateTimeZoneId();
	}

	protected void populateTimeZoneId() throws Exception {
		runSQL(
			"update OSB_SupportRegion set timeZoneId = 'Australia/Sydney' " +
				"where name = 'Australia'");
		runSQL(
			"update OSB_SupportRegion set timeZoneId = 'America/Sao_Paulo' " +
				"where name = 'Brazil'");
		runSQL(
			"update OSB_SupportRegion set timeZoneId = 'Asia/Shanghai' where " +
				"name = 'China'");
		runSQL(
			"update OSB_SupportRegion set timeZoneId = 'Europe/Paris' where " +
				"name = 'Hungary'");
		runSQL(
			"update OSB_SupportRegion set timeZoneId = 'Asia/Calcutta' where " +
				"name = 'India'");
		runSQL(
			"update OSB_SupportRegion set timeZoneId = 'UTC' where name = " +
				"'Spain'");
		runSQL(
			"update OSB_SupportRegion set timeZoneId = 'America/Los_Angeles' " +
				"where name = 'US'");
	}

}