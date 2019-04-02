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

package com.liferay.osb.hook.upgrade.v3_4_6;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Jeremy Fu
 */
public class Upgrade_20160328162938553_AccountEntry extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(3);

		sb.append("update OSB_AccountEntry set status = 2 where ((select ");
		sb.append("count(*) from OSB_OfferingEntry where accountEntryId = ");
		sb.append("OSB_AccountEntry.accountEntryId) < 1)");

		runSQL(sb.toString());
	}

}