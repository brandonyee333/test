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

package com.liferay.osb.hook.upgrade.v3_6_0;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.impl.DeveloperEntryModelImpl;

import java.io.IOException;

import java.sql.SQLException;

/**
 * @author Yury Butrymovich
 */
public class Upgrade_20161017153308278_DeveloperEntry
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20161017153308278L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasColumn(DeveloperEntryModelImpl.TABLE_NAME, "dossieraKey")) {
			runSQL(
				"alter table " + DeveloperEntryModelImpl.TABLE_NAME +
					" change column dossieraKey dossieraAccountKey " +
						"VARCHAR(75) null");

			runSQL(
				"drop index IX_58B01EA on " +
					DeveloperEntryModelImpl.TABLE_NAME, false);

			runSQL(
				"create index IX_B0F6096B on " +
					DeveloperEntryModelImpl.TABLE_NAME +
						" (dossieraAccountKey)");
		}
	}

	protected void runSQL(String template, boolean failOnError)
		throws IOException, SQLException {

		if (failOnError) {
			runSQL(template);
		}
		else {
			try {
				runSQL(template);
			}
			catch (Exception e) {
			}
		}
	}

}