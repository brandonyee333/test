/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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