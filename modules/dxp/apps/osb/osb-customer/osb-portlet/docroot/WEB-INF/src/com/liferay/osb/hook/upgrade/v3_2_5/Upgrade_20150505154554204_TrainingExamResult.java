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

package com.liferay.osb.hook.upgrade.v3_2_5;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Douglas Wong
 */
public class Upgrade_20150505154554204_TrainingExamResult
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20150505154554204L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateTrainingCustomer();
	}

	protected void updateTrainingCustomer() throws Exception {
		if (!tableHasColumn("OSB_TrainingExamResult", "status")) {
			runSQL(
				"alter table OSB_TrainingExamResult add column status INTEGER");

			runSQL(
				"create index IX_CD97224A on OSB_TrainingExamResult " +
					"(startDate, grade, status)");
		}
	}

}