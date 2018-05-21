/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.hook.upgrade.v2_3_0;

import com.liferay.osb.model.impl.AccountAttachmentImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Brent Krone-Schmidt
 */
public class UpgradeAccountAttachment extends UpgradeProcess {

	@Override
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