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

package com.liferay.osb.hook.upgrade.v1_4_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Lin Cui
 */
public class UpgradeSupportResponse extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateSupportResponse();
	}

	protected void updateSupportResponse() throws Exception {
		if (hasColumn("OSB_SupportResponse", "supportLevel")) {
			return;
		}

		runSQL(
			"alter table OSB_SupportResponse add column supportLevel INTEGER");
		runSQL(
			"create index IX_21D8E69E on OSB_SupportResponse (supportLevel)");
		runSQL("update OSB_SupportResponse set supportLevel = 0");
	}

}