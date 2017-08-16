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

package com.liferay.osb.hook.upgrade.v1_4_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Lin Cui
 */
public class UpgradeSupportResponse extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateSupportResponse();
	}

	protected void updateSupportResponse() throws Exception {
		if (hasColumn("OSB_SupportResponse", "supportLevel")) {
			return;
		}

		runSQL(
			"alter table OSB_SupportResponse add column supportLevel INTEGER");
		runSQL(
			"create index IX_21D8E69E on OSB_SupportResponse (supportLevel)");
		runSQL("update OSB_SupportResponse set supportLevel = 0");
	}

}