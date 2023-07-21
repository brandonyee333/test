/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.privatemessaging.internal.upgrade.v1_0_0;

import com.liferay.message.boards.kernel.service.MBThreadLocalService;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.social.privatemessaging.model.PrivateMessagingConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Scott Lee
 */
public class UpgradePrivateMessaging extends UpgradeProcess {

	public UpgradePrivateMessaging(MBThreadLocalService mBThreadLocalService) {
		_mBThreadLocalService = mBThreadLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = connection.prepareStatement(
			"select mbThreadId from PM_UserThread");

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			long mbThreadId = rs.getLong(1);

			_mBThreadLocalService.moveThread(
				GroupConstants.DEFAULT_PARENT_GROUP_ID,
				PrivateMessagingConstants.PRIVATE_MESSAGING_CATEGORY_ID,
				mbThreadId);
		}
	}

	private final MBThreadLocalService _mBThreadLocalService;

}