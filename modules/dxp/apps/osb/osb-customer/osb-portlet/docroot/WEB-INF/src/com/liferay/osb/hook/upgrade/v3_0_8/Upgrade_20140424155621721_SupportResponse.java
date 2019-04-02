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

package com.liferay.osb.hook.upgrade.v3_0_8;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Brent Krone-Schmidt
 */
public class Upgrade_20140424155621721_SupportResponse
	extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		renameColumns();

		updateLanguageIds();
		updateSupportLevels();
	}

	protected void renameColumns() throws Exception {
		if (hasColumn("OSB_SupportResponse", "languageId")) {
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
			"update OSB_SupportResponse set supportLevel = 1 where name like " +
				"'%platinum%'");
		runSQL(
			"update OSB_SupportResponse set supportLevel = 2 where name like " +
				"'%gold%'");
		runSQL(
			"update OSB_SupportResponse set supportLevel = 3 where name like " +
				"'%silver%'");
		runSQL(
			"update OSB_SupportResponse set supportLevel = 4 where (name " +
				"like '%basic%') or (name like '%limited%')");
	}

}