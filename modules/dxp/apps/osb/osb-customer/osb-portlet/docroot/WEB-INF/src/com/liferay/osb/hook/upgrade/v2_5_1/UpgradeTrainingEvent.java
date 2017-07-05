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

package com.liferay.osb.hook.upgrade.v2_5_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Wesley Gong
 */
public class UpgradeTrainingEvent extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateTrainingEvent();
	}

	protected void updateTrainingEvent() throws Exception {
		if (!tableHasColumn("OSB_TrainingEvent", "DDLRecordSetId")) {
			runSQL(
				"alter table OSB_TrainingEvent add column DDLRecordSetId LONG");

			runSQL(
				"create index IX_CD618D79 on OSB_TrainingEvent " +
					"(DDLRecordSetId)");
		}

		if (!tableHasColumn("OSB_TrainingEvent", "name")) {
			runSQL(
				"alter table OSB_TrainingEvent add column name VARCHAR(150)");
		}
	}

}