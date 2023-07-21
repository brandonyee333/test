/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_1_0;

import com.liferay.petra.string.StringBundler;

/**
 * @author Ethan Bustad
 */
public class UpgradeResourceAction extends BaseUpgradeClassName {

	@Override
	protected void doUpgrade() throws Exception {
		for (String className : CLASS_NAMES) {
			runSQL(
				StringBundler.concat(
					"update ResourceAction set name = '", className,
					"' where name = '", getStaleClassName(className), "'"));
		}
	}

}