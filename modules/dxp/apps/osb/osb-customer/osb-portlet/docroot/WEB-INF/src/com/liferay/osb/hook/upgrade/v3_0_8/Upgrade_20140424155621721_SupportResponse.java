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
public class Upgrade_20140424155621721_SupportResponse extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20140424155621721L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		renameColumns();

		updateLanguageIds();
		updateSupportLevels();
	}

	protected void renameColumns() throws Exception {
		if (tableHasColumn("OSB_SupportResponse", "languageId")) {
			return;
		}

		runSQL(
			"alter table OSB_SupportResponse change localeType languageId " +
				"VARCHAR(75)");
	}

	protected void updateLanguageIds() throws Exception {
		runSQL(
			"update OSB_SupportResponse set languageId = 'all' where " +
				"languageId = '0'");
		runSQL(
			"update OSB_SupportResponse set languageId = 'zh_CN' where " +
				"languageId = '1'");
		runSQL(
			"update OSB_SupportResponse set languageId = 'en_US' where " +
				"languageId = '2'");
		runSQL(
			"update OSB_SupportResponse set languageId = 'pt_BR' where " +
				"languageId = '3'");
		runSQL(
			"update OSB_SupportResponse set languageId = 'es_ES' where " +
				"languageId = '4'");
	}

	protected void updateSupportLevels() throws Exception {
		runSQL(
			"update OSB_SupportResponse set supportLevel = 1 where " +
				"name like '%platinum%'");
		runSQL(
			"update OSB_SupportResponse set supportLevel = 2 where " +
				"name like '%gold%'");
		runSQL(
			"update OSB_SupportResponse set supportLevel = 3 where " +
				"name like '%silver%'");
		runSQL(
			"update OSB_SupportResponse set supportLevel = 4 where " +
				"(name like '%basic%') or (name like '%limited%')");
	}

}

*/

}