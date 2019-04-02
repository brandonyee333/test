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

package com.liferay.osb.hook.upgrade.v3_4_5;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amos Fong
 */
public class Upgrade_20160315105316316_Organization extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		List<Long> inactiveUserIds = new ArrayList<>();

		List<User> users = UserLocalServiceUtil.getOrganizationUsers(
			OSBConstants.ORGANIZATION_LIFERAY_INC_ID);

		for (User user : users) {
			if (!user.isActive()) {
				inactiveUserIds.add(user.getUserId());
			}
		}

		UserLocalServiceUtil.unsetOrganizationUsers(
			OSBConstants.ORGANIZATION_LIFERAY_INC_ID,
			ArrayUtil.toArray(inactiveUserIds.toArray(new Long[0])));
	}

}