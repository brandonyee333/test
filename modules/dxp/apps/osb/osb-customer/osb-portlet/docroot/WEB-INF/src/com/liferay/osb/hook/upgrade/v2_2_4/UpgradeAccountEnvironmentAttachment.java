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

package com.liferay.osb.hook.upgrade.v2_2_4;

import com.liferay.osb.model.impl.AccountEnvironmentAttachmentModelImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Lin Cui
 */
public class UpgradeAccountEnvironmentAttachment extends UpgradeProcess {

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