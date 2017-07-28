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

package com.liferay.osb.hook.upgrade.v3_2_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Abhishek Jain
 */
public class Upgrade_20150331115540763_TrainingEvent extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20150331115540763L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateTrainingEvent();
	}

	protected void updateTrainingEvent() throws Exception {
		if (isColumnType("OSB_TrainingEvent", "emailAddress", "longtext")) {
			return;
		}

		runSQL("alter table OSB_TrainingEvent modify emailAddress longtext");
	}

}

*/

}