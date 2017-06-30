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

package com.liferay.osb.hook.upgrade.v3_0_4;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.impl.AuditActionImpl;

/**
 * @author Amos Fong
 */
public class Upgrade_20140616124619166_AuditAction extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20140616124619166L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable(AuditActionImpl.TABLE_NAME)) {
			return;
		}

		runSQL(AuditActionImpl.TABLE_SQL_CREATE);

		runSQL(
			"create index IX_27C83F8B on OSB_AuditAction (classNameId, " +
				"classPK)");
		runSQL("create index IX_1A167D81 on OSB_AuditAction (modifiedDate)");
		runSQL(
			"create index IX_81C75F40 on OSB_AuditAction (modifiedDate, " +
				"classNameId, classPK, action)");
	}

}