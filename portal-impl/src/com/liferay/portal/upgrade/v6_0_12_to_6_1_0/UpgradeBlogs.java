/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v6_0_12_to_6_1_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.upgrade.v6_1_0.util.BlogsEntryTable;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeBlogs extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		alter(
			BlogsEntryTable.class,
			new AlterColumnType("smallImageURL", "STRING null"));
	}

}