/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.social.networking.internal.upgrade.v1_0_0.util.MeetupsEntryTable;
import com.liferay.social.networking.internal.upgrade.v1_0_0.util.MeetupsRegistrationTable;
import com.liferay.social.networking.internal.upgrade.v1_0_0.util.WallEntryTable;

/**
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 */
public class UpgradeNamespace extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		renameTable(
			StringUtil.replaceFirst(
				MeetupsEntryTable.TABLE_NAME, "SN_", "WOL_"),
			MeetupsEntryTable.TABLE_NAME, MeetupsEntryTable.TABLE_COLUMNS,
			MeetupsEntryTable.TABLE_SQL_CREATE,
			MeetupsEntryTable.TABLE_SQL_DROP);

		renameTable(
			StringUtil.replaceFirst(
				MeetupsRegistrationTable.TABLE_NAME, "SN_", "WOL_"),
			MeetupsRegistrationTable.TABLE_NAME,
			MeetupsRegistrationTable.TABLE_COLUMNS,
			MeetupsRegistrationTable.TABLE_SQL_CREATE,
			MeetupsRegistrationTable.TABLE_SQL_DROP);

		renameTable(
			StringUtil.replaceFirst(WallEntryTable.TABLE_NAME, "SN_", "WOL_"),
			WallEntryTable.TABLE_NAME, WallEntryTable.TABLE_COLUMNS,
			WallEntryTable.TABLE_SQL_CREATE, WallEntryTable.TABLE_SQL_DROP);
	}

	protected void renameTable(
			String oldTableName, String newTableName, Object[][] tableColumns,
			String tableSqlCreate, String tableSqlDrop)
		throws Exception {

		try (LoggingTimer loggingTimer = new LoggingTimer(newTableName)) {
			if (hasRows(newTableName)) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						StringBundler.concat(
							"Not renaming ", oldTableName, " to ", newTableName,
							" because ", newTableName, " has data"));
				}

				return;
			}

			if (!hasRows(oldTableName)) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						StringBundler.concat(
							"Not renaming ", oldTableName, " to ", newTableName,
							" because ", oldTableName, " has no data"));
				}

				return;
			}

			runSQL(tableSqlDrop);

			UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
				oldTableName, tableColumns);

			upgradeTable.setCreateSQL(tableSqlCreate);

			upgradeTable.updateTable();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeNamespace.class);

}