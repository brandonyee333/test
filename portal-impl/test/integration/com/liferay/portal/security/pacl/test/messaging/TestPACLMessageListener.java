/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.test.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.PortalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class TestPACLMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		message.setPayload(getResults(message));

		MessageBusUtil.sendMessage(
			message.getResponseDestinationName(), message);
	}

	protected Map<String, Object> getResults(Message message) throws Exception {
		Map<String, Object> results = new HashMap<>();

		try {
			int buildNumber = PortalServiceUtil.getBuildNumber();

			results.put("PortalServiceUtil#getBuildNumber", buildNumber);
		}
		catch (SecurityException se) {
		}

		try {
			User user = UserLocalServiceUtil.getUser(
				TestPropsValues.getUserId());

			results.put("UserLocalServiceUtil#getUser", user);
		}
		catch (SecurityException se) {
		}

		return results;
	}

}