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

package com.liferay.osb.hook.upgrade.v3_0_8;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Brent Krone-Schmidt
 */
public class Upgrade_20140424155644227_SupportTeamLanguage
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20140424155644227L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		renameTable();
		renameColumns();

		updateLanguageIds();
	}

	protected void renameColumns() throws Exception {
		if (!tableHasColumn(
				"OSB_SupportTeamLanguage", "supportTeamLanguageId")) {

			runSQL(
				"alter table OSB_SupportTeamLanguage change " +
					"supportTeamLocaleId supportTeamLanguageId LONG");
		}

		if (!tableHasColumn("OSB_SupportTeamLanguage", "languageId")) {
			runSQL(
				"alter table OSB_SupportTeamLanguage change localeType " +
					"languageId VARCHAR(75)");
		}
	}

	protected void renameTable() throws Exception {
		if (hasTable("OSB_SupportTeamLanguage")) {
			return;
		}

		runSQL("rename table OSB_SupportTeamLocale to OSB_SupportTeamLanguage");

		runSQL("drop index IX_6FD3090C on OSB_SupportTeamLanguage");

		runSQL(
			"create index IX_45C068E on OSB_SupportTeamLanguage " +
				"(supportTeamId)");
	}

	protected void updateLanguageIds() throws Exception {
		runSQL(
			"update OSB_SupportTeamLanguage set languageId = 'all' where " +
				"languageId = '0'");
		runSQL(
			"update OSB_SupportTeamLanguage set languageId = 'zh_CN' where " +
				"languageId = '1'");
		runSQL(
			"update OSB_SupportTeamLanguage set languageId = 'en_US' where " +
				"languageId = '2'");
		runSQL(
			"update OSB_SupportTeamLanguage set languageId = 'pt_BR' where " +
				"languageId = '3'");
		runSQL(
			"update OSB_SupportTeamLanguage set languageId = 'es_ES' where " +
				"languageId = '4'");
	}

}

*/

}