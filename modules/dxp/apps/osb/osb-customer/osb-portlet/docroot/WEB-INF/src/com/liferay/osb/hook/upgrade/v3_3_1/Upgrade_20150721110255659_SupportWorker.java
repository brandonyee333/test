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

package com.liferay.osb.hook.upgrade.v3_3_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.SupportWorkerConstants;
import com.liferay.osb.model.impl.SupportWorkerImpl;

/**
 * @author Brent Krone-Schmidt
 */
public class Upgrade_20150721110255659_SupportWorker
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20150721110255659L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasColumn(
				SupportWorkerImpl.TABLE_NAME, "escalationLevel2Role")) {

			return;
		}

		runSQL(
			"alter table " + SupportWorkerImpl.TABLE_NAME + " add " +
				"escalationLevel2Role INTEGER");

		runSQL(
			"update " + SupportWorkerImpl.TABLE_NAME + " set " +
				"escalationLevel2Role = " +
					SupportWorkerConstants.ESCALATION_LEVEL_2_ROLE_PRIMARY);
	}

}