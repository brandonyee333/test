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

package com.liferay.osb.hook.upgrade.v2_3_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import UpgradeProcess;
import com.liferay.osb.model.impl.AccountAttachmentImpl;

*/

/**
 * @author Brent Krone-Schmidt
 */
public class UpgradeAccountAttachment extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	protected void doUpgrade() throws Exception {
		updateAccountAttachment();
	}

	protected void updateAccountAttachment() throws Exception {
		runSQL(AccountAttachmentImpl.TABLE_SQL_CREATE);

		runSQL(
			"create index IX_20B2A5A9 on OSB_AccountAttachment " +
				"(accountEntryId)");
		runSQL(
			"create index IX_5DD4C40C on OSB_AccountAttachment " +
				"(accountEntryId, type_)");
		runSQL(
			"create index IX_E16F4B91 on OSB_AccountAttachment " +
				"(accountEntryId, fileName, type_)");
	}

}

*/

}