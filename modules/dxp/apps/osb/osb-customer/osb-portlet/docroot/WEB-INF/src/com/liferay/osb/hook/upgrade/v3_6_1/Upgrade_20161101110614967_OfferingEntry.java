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

package com.liferay.osb.hook.upgrade.v3_6_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Amos Fong
 */
public class Upgrade_20161101110614967_OfferingEntry
	extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasIndex("OSB_OfferingEntry", "IX_E0CF6A9F")) {
			runSQL(
				"create index IX_E0CF6A9F on OSB_OfferingEntry " +
					"(supportEndDate)");
		}

		if (!hasIndex("OSB_OfferingEntry", "IX_CE314FD7")) {
			runSQL("create index IX_CE314FD7 on OSB_OfferingEntry (version)");
		}
	}

}