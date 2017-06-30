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

package com.liferay.osb.hook.upgrade.v3_1_3;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.DDLRecordSetConstants;

/**
 * @author Val Nagy
 */
public class Upgrade_20141028150228596_DDLRecordSet extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20141028150228596L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeDDLRecordset();
	}

	protected void upgradeDDLRecordset() throws Exception {
		runSQL(
			"delete from DDLRecordSet where recordSetId not in " +
				"(select DDLRecordSetId from OSB_TrainingEvent) and " +
					"scope = " + DDLRecordSetConstants.SCOPE_TRAINING_EVENTS);
	}

}