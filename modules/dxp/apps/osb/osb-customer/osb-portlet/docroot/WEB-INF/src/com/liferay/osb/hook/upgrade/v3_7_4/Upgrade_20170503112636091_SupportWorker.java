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

package com.liferay.osb.hook.upgrade.v3_7_4;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.service.SupportWorkerLocalServiceUtil;

*/

/**
 * @author Kyle Bischof
 */
public class Upgrade_20170503112636091_SupportWorker extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20170503112636091L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasColumn("OSB_SupportWorker", "autoAssign")) {
			return;
		}

		runSQL("alter table OSB_SupportWorker add column autoAssign BOOLEAN");

		runSQL("update OSB_SupportWorker set autoAssign = 1");
		runSQL("update OSB_SupportWorker set maxWork = 1 where maxWork = 0");

		SupportWorkerLocalServiceUtil.recalculateUtilization();
	}

	 */

}