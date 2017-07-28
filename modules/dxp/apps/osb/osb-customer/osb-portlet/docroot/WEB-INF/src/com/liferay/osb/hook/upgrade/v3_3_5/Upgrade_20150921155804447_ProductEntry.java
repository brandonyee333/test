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

package com.liferay.osb.hook.upgrade.v3_3_5;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Amos Fong
 */
public class Upgrade_20150921155804447_ProductEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20150921155804447L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL("alter table OSB_ProductEntry add column type_ INTEGER");
		runSQL("alter table OSB_ProductEntry add column platform VARCHAR(75)");
		runSQL(
			"alter table OSB_ProductEntry add column platformVersion " +
				"VARCHAR(75)");

		runSQL(
			"update OSB_ProductEntry set platform = '" +
				ProductEntryConstants.PLATFORM_LIFERAY_PORTAL + "'");
	}

}

*/

}