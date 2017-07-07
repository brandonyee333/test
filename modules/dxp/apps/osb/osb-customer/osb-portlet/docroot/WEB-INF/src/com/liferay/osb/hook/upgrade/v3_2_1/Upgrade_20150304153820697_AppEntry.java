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

package com.liferay.osb.hook.upgrade.v3_2_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Ryan Park
 */
public class Upgrade_20150304153820697_AppEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20150304153820697L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"update OSB_AppEntry set version = '1.1.0' where appEntryId = " +
				"43707691",
			false);
		runSQL(
			"update OSB_AppVersion set version = '1.1.0' where " +
				"appVersionId = 49834567",
			false);
	}

}
*/

}