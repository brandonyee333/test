/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.token.auth.internal.upgrade.v1_1_0;

import com.liferay.osb.loop.token.auth.model.TokenAuthEntry;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Timothy Bell
 */
public abstract class BaseUpgradeClassName extends UpgradeProcess {

	protected static String getStaleClassName(String className) {
		return StringUtil.replace(
			className, "osb.loop.token.auth", "tokenauth");
	}

	protected static final String[] CLASS_NAMES = {
		"com.liferay.osb.loop.token.auth", TokenAuthEntry.class.getName()
	};

}