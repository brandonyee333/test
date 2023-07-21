/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.messageboards.util;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.SubscriptionSender;

/**
 * @author Roberto Díaz
 */
public class MBDiscussionSubcriptionSender extends SubscriptionSender {

	protected void sendEmailNotification(User user) throws Exception {
		if (PrefsPropsUtil.getBoolean(
				companyId, PropsKeys.DISCUSSION_EMAIL_COMMENTS_ADDED_ENABLED)) {

			super.sendEmailNotification(user);
		}
	}

}