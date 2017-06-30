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

package com.liferay.osb.hook.upgrade.v3_4_5;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amos Fong
 */
public class Upgrade_20160315105316316_Organization extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160315105316316L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<Long> inactiveUserIds = new ArrayList<Long>();

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