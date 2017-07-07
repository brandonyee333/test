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

/*import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.osb.model.TrainingEvent;

*/

/**
 * @author Calvin Keum
 */
public class UpgradeTrainingCustomer extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	protected void doUpgrade() throws Exception {
		updateTrainingCustomer();
	}

	protected void updateTrainingCustomer() throws Exception {
		runSQL("drop index IX_AC17F7AC on OSB_TrainingCustomer");

		runSQL("drop index IX_EAB729B2 on OSB_TrainingCustomer");

		if (!tableHasColumn("OSB_TrainingCustomer", "classNameId")) {
			runSQL(
				"alter table OSB_TrainingCustomer add column classNameId LONG");

			runSQL(
				"update OSB_TrainingCustomer set classNameId = " +
					PortalUtil.getClassNameId(TrainingEvent.class));

			runSQL(
				"create index IX_27854F5 on OSB_TrainingCustomer " +
					"(userId, classNameId)");
		}

		if (tableHasColumn("OSB_TrainingCustomer", "trainingEventId") &&
			!tableHasColumn("OSB_TrainingCustomer", "classPK")) {

			runSQL(
				"alter table OSB_TrainingCustomer change column " +
					"trainingEventId classPK LONG");

			runSQL(
				"create index IX_B8FB20D4 on OSB_TrainingCustomer " +
					"(classNameId, classPK)");

			runSQL(
				"create index IX_2248428E on OSB_TrainingCustomer " +
					"(userId, classNameId, classPK)");
		}
	}

}
*/

}