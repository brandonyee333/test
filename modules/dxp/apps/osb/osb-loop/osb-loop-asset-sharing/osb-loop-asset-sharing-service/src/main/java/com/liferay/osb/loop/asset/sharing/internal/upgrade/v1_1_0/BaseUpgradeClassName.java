/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.sharing.internal.upgrade.v1_1_0;

import com.liferay.osb.loop.asset.sharing.model.AssetSharingEntry;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Calvin Keum
 */
public abstract class BaseUpgradeClassName extends UpgradeProcess {

	protected static String getStaleClassName(String className) {
		return StringUtil.replace(className, ".osb.loop.", StringPool.PERIOD);
	}

	protected static final String CLASS_NAME =
		AssetSharingEntry.class.getName();

}