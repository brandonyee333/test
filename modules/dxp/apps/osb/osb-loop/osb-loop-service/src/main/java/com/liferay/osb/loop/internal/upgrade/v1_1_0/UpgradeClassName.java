/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.internal.upgrade.v1_1_0;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Calvin Keum
 */
public class UpgradeClassName extends BaseUpgradeClassName {

	@Override
	protected void doUpgrade() throws Exception {
		for (String className : CLASS_NAMES) {
			StringBundler sb = new StringBundler(5);

			sb.append("update ClassName_ set value = '");
			sb.append(className);
			sb.append("' where value = '");
			sb.append(getStaleClassName(className));
			sb.append(StringPool.APOSTROPHE);

			runSQL(sb.toString());
		}
	}

}