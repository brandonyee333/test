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

package com.liferay.osb.hook.upgrade.v3_7_5;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

*/

/**
 * @author Kyle Bischof
 */
public class Upgrade_20170530081027591_User extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

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
*/

}