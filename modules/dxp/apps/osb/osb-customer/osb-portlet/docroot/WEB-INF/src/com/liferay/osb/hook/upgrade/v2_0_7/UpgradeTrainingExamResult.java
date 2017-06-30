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

package com.liferay.osb.hook.upgrade.v2_0_7;

import com.liferay.compat.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Peter Shin
 */
public class UpgradeTrainingExamResult extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateTrainingExamResult();
	}

	protected void updateTrainingExamResult() throws Exception {
		if (tableHasColumn("OSB_TrainingExamResult", "candidateKey")) {
			runSQL(
				"alter table OSB_TrainingExamResult drop column candidateKey");
		}

		if (tableHasColumn("OSB_TrainingExamResult", "candidateRecordKey")) {
			runSQL(
				"alter table OSB_TrainingExamResult drop column " +
					"candidateRecordKey");
		}

		if (tableHasColumn("OSB_TrainingExamResult", "modifiedDate")) {
			runSQL(
				"alter table OSB_TrainingExamResult drop column modifiedDate");
		}

		if (tableHasColumn("OSB_TrainingExamResult", "userName")) {
			runSQL("alter table OSB_TrainingExamResult drop column userName");
		}

		if (tableHasColumn("OSB_TrainingExamResult", "userProfileHistoryId")) {
			runSQL(
				"alter table OSB_TrainingExamResult drop column " +
					"userProfileHistoryId");
		}

		runSQL(
			"create unique index IX_ED881F93 on OSB_TrainingExamResult " +
				"(registrationNumber)");
		runSQL("create index IX_8CD229F7 on OSB_TrainingExamResult (userId)");
	}

}