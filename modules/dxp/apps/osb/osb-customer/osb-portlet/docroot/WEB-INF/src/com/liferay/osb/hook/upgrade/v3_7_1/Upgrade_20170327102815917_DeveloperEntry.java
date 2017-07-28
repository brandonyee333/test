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

package com.liferay.osb.hook.upgrade.v3_7_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.DeveloperEntryConstants;

*/

/**
 * @author Ryan Park
 */
public class Upgrade_20170327102815917_DeveloperEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20170327102815917L;
	}

	protected void createType() throws Exception {
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL("alter table OSB_DeveloperEntry add column type_ INTEGER");

		runSQL(
			"create index IX_C19D5AB3 on OSB_DeveloperEntry " +
				"(legalEntityName, type_, status)");
		runSQL(
			"create index IX_147C0BEC on OSB_DeveloperEntry (type_, status)");

		runSQL(
			"update OSB_DeveloperEntry set type_ = " +
				DeveloperEntryConstants.TYPE_USER + " where classNameId = " +
					_CLASS_NAME_USER_ID);
		runSQL(
			"update OSB_DeveloperEntry set type_ = " +
				DeveloperEntryConstants.TYPE_COMPANY + " where classNameId = " +
					_CLASS_NAME_CORP_ENTRY_ID);

		runSQL("drop index IX_82B70104 on OSB_DeveloperEntry", false);
		runSQL("drop index IX_CABDDB1E on OSB_DeveloperEntry", false);
		runSQL("drop index IX_10C569A5 on OSB_DeveloperEntry", false);

		runSQL("alter table OSB_DeveloperEntry drop column classNameId");
		runSQL("alter table OSB_DeveloperEntry drop column classPK");
	}

	private static final long _CLASS_NAME_CORP_ENTRY_ID = 12291273;

	private static final long _CLASS_NAME_USER_ID = 9;

}

*/

}