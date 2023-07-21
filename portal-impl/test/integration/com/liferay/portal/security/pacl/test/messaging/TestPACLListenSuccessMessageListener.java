/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.test.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class TestPACLListenSuccessMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		MessageBusUtil.sendMessage(
			message.getResponseDestinationName(), message);
	}

}