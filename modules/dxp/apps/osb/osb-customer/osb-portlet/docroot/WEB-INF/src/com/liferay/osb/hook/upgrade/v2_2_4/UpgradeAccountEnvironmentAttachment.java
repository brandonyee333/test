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

package com.liferay.osb.hook.upgrade.v2_2_4;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.osb.model.impl.AccountEnvironmentAttachmentModelImpl;

*/

/**
 * @author Lin Cui
 */
public class UpgradeAccountEnvironmentAttachment extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	protected void doUpgrade() throws Exception {
		updateAccountEnvironmentAttachment();
	}

	protected void updateAccountEnvironmentAttachment() throws Exception {
		if (hasTable("OSB_AccountEnvironmentAttachment")) {
			return;
		}

		runSQL(AccountEnvironmentAttachmentModelImpl.TABLE_SQL_CREATE);

		runSQL(
			"create index IX_132CE63E on OSB_AccountEnvironmentAttachment " +
				"(accountEnvironmentId)");
		runSQL(
			"create index IX_4F61A1D7 on OSB_AccountEnvironmentAttachment " +
				"(accountEnvironmentId, type_)");
	}

}
*/

}