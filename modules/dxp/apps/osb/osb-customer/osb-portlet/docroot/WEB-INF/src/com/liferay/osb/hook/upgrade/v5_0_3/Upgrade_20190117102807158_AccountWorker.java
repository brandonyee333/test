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

package com.liferay.osb.hook.upgrade.v5_0_3;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Amos Fong
 */
public class Upgrade_20190117102807158_AccountWorker
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20190117102807158L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(4);

		sb.append("delete aw1 from OSB_AccountWorker aw1 inner join ");
		sb.append("OSB_AccountWorker aw2 on (aw1.accountEntryId = ");
		sb.append("aw2.accountEntryId) and (aw1.userId = aw2.userId) where ");
		sb.append("aw1.accountWorkerId > aw2.accountWorkerId");

		runSQL(sb.toString());
	}

}