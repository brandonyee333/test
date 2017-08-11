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

package com.liferay.osb.hook.upgrade.v2_1_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.osb.model.TrainingEvent;

*/

/**
 * @author Calvin Keum
 */
public class UpgradeTrainingWorker extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	protected void doUpgrade() throws Exception {
		updateTrainingWorker();
	}

	protected void updateTrainingWorker() throws Exception {
		runSQL("drop index IX_D7AED4C on OSB_TrainingWorker");

		runSQL("drop index IX_F31A7F52 on OSB_TrainingWorker");

		if (!tableHasColumn("OSB_TrainingWorker", "classNameId")) {
			runSQL(
				"alter table OSB_TrainingWorker add column classNameId LONG");

			runSQL(
				"update OSB_TrainingWorker set classNameId = " +
					PortalUtil.getClassNameId(TrainingEvent.class));
		}

		if (tableHasColumn("OSB_TrainingWorker", "trainingEventId") &&
			!tableHasColumn("OSB_TrainingWorker", "classPK")) {

			runSQL(
				"alter table OSB_TrainingWorker change column " +
					"trainingEventId classPK LONG");

			runSQL(
				"create index IX_930AF34 on OSB_TrainingWorker " +
					"(classNameId, classPK)");

			runSQL(
				"create index IX_FCB970EE on OSB_TrainingWorker " +
					"(userId, classNameId, classPK)");
		}
	}

	 */

}