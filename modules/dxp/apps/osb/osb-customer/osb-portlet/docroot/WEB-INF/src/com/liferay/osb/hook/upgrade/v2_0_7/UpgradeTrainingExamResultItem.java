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
public class UpgradeTrainingExamResultItem extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	protected void doUpgrade() throws Exception {
		updateTrainingExamResultItem();
	}

	protected void updateTrainingExamResultItem() throws Exception {
		if (tableHasColumn("OSB_TrainingExamResultItem", "createDate")) {
			runSQL(
				"alter table OSB_TrainingExamResultItem drop column " +
					"createDate");
		}

		if (tableHasColumn("OSB_TrainingExamResultItem", "distracterCounter")) {
			runSQL(
				"alter table OSB_TrainingExamResultItem change " +
					"distracterCounter distractorCounter INTEGER");
		}

		if (tableHasColumn("OSB_TrainingExamResultItem", "modifiedDate")) {
			runSQL(
				"alter table OSB_TrainingExamResultItem drop column " +
					"modifiedDate");
		}

		if (tableHasColumn("OSB_TrainingExamResultItem", "userId")) {
			runSQL("alter table OSB_TrainingExamResultItem drop column userId");
		}

		if (tableHasColumn("OSB_TrainingExamResultItem", "userName")) {
			runSQL(
				"alter table OSB_TrainingExamResultItem drop column userName");
		}
	}

	 */

}