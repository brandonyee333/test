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

package com.liferay.osb.hook.upgrade.v3_0_6;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Amos Fong
 */
public class Upgrade_20140726125755083_AuditAction extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20140726125755083L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasColumn("OSB_AuditAction", "mappingClassPK")) {
			return;
		}

		runSQL("alter table OSB_AuditAction add column mappingClassPK bigint");

		runSQL("drop index IX_27C83F8B on OSB_AuditAction", false);
		runSQL("drop index IX_81C75F40 on OSB_AuditAction", false);
		runSQL("drop index IX_8BA3E497 on OSB_AuditAction", false);

		runSQL(
			"create index IX_2194BE84 on OSB_AuditAction (classNameId, " +
				"classPK, mappingClassPK)");
		runSQL(
			"create index IX_6E655F39 on OSB_AuditAction (modifiedDate, " +
				"classNameId, classPK, mappingClassPK, action)");
		runSQL(
			"create index IX_EA525690 on OSB_AuditAction (modifiedDate, " +
				"classNameId, mappingClassPK, action)");
	}

}
*/

}