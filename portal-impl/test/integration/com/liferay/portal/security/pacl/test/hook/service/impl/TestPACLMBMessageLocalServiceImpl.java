/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.test.hook.service.impl;

import com.liferay.message.boards.kernel.service.MBMessageLocalService;
import com.liferay.message.boards.kernel.service.MBMessageLocalServiceWrapper;

/**
 * @author Brian Wing Shun Chan
 */
public class TestPACLMBMessageLocalServiceImpl
	extends MBMessageLocalServiceWrapper {

	public TestPACLMBMessageLocalServiceImpl(
		MBMessageLocalService mbMessageLocalService) {

		super(mbMessageLocalService);
	}

	@Override
	public int getMBMessagesCount() {
		return -123;
	}

}