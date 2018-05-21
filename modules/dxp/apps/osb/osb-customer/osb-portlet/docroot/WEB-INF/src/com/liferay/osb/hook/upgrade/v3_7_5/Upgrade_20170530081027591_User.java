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

package com.liferay.osb.hook.upgrade.v3_7_5;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20170530081027591_User extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20170530081027591L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(6);

		sb.append("update User_ inner join Users_Roles on User_.userId = ");
		sb.append("Users_Roles.userId set emailAddressVerified = 0 where ");
		sb.append("status != ");
		sb.append(WorkflowConstants.STATUS_APPROVED);
		sb.append(" and emailAddressVerified = 1 and roleId != ");
		sb.append(OSBConstants.ROLE_VERIFIED_USER_ID);

		runSQL(sb.toString());
	}

}