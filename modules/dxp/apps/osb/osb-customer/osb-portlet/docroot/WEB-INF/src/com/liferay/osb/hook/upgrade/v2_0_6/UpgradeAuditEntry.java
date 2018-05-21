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

package com.liferay.osb.hook.upgrade.v2_0_6;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Lin Cui
 */
public class UpgradeAuditEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateAuditEntry();
	}

	protected void updateAuditEntry() throws Exception {
		runSQL(
			"create index IX_558935D2 on OSB_AuditEntry (createDate, " +
				"classNameId)");
	}

}