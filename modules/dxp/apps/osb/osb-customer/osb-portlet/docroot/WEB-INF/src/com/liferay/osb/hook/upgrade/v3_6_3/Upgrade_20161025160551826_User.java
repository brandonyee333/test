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

package com.liferay.osb.hook.upgrade.v3_6_3;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20161025160551826_User extends BaseUpgradeProcess {

	@Override
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