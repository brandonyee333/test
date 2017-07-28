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

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import UpgradeProcess;

*/

/**
 * @author Peter Shin
 */
public class UpgradeTrainingExamResultSection extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	protected void doUpgrade() throws Exception {
		updateTrainingExamResultSection();
	}

	protected void updateTrainingExamResultSection() throws Exception {
		if (tableHasColumn("OSB_TrainingExamResultSection", "createDate")) {
			runSQL(
				"alter table OSB_TrainingExamResultSection drop column " +
					"createDate");
		}

		if (tableHasColumn("OSB_TrainingExamResultSection", "modifiedDate")) {
			runSQL(
				"alter table OSB_TrainingExamResultSection drop column " +
					"modifiedDate");
		}

		if (tableHasColumn(
				"OSB_TrainingExamResultSection", "trainingExamSectionId")) {

			runSQL(
				"alter table OSB_TrainingExamResultSection change " +
					"trainingExamSectionId trainingExamResultSectionId LONG");
		}

		if (tableHasColumn("OSB_TrainingExamResultSection", "userId")) {
			runSQL(
				"alter table OSB_TrainingExamResultSection drop column userId");
		}

		if (tableHasColumn("OSB_TrainingExamResultSection", "userName")) {
			runSQL(
				"alter table OSB_TrainingExamResultSection drop column " +
					"userName");
		}
	}

}

*/

}