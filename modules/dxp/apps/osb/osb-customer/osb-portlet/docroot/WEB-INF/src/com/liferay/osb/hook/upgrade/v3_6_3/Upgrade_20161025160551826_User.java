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

package com.liferay.osb.hook.upgrade.v3_6_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.List;

*/

/**
 * @author Kyle Bischof
 */
public class Upgrade_20161025160551826_User extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20161025160551826L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<User> users = UserLocalServiceUtil.getUsers(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (User user : users) {
			if (user.isDefaultUser() || !user.isActive()) {
				continue;
			}

			if (!UserLocalServiceUtil.hasRoleUser(
					OSBConstants.ROLE_VERIFIED_USER_ID, user.getUserId())) {

				UserLocalServiceUtil.addRoleUsers(
					OSBConstants.ROLE_VERIFIED_USER_ID,
					new long[] {user.getUserId()});
			}
		}
	}

}

*/

}