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

package com.liferay.osb.hook.upgrade.v3_0_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

*/

/**
 * @author Joan Kim
 */
public class Upgrade_20140609182317851_AppEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20140609182317851L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeAppEntry();
	}

	protected void upgradeAppEntry() throws Exception {
		if (!tableHasColumn("OSB_AppEntry", "developerEntryId")) {
			runSQL("alter table OSB_AppEntry add column developerEntryId LONG");
		}

		StringBundler sb = new StringBundler(6);

		sb.append("update OSB_AppEntry inner join OSB_DeveloperEntry on ");
		sb.append("OSB_AppEntry.ownerClassPK = OSB_DeveloperEntry.classPK ");
		sb.append("and OSB_AppEntry.ownerClassNameId = ");
		sb.append("OSB_DeveloperEntry.classNameId set ");
		sb.append("OSB_AppEntry.developerEntryId = ");
		sb.append("OSB_DeveloperEntry.developerEntryId");

		runSQL(sb.toString());
	}

}

*/

}