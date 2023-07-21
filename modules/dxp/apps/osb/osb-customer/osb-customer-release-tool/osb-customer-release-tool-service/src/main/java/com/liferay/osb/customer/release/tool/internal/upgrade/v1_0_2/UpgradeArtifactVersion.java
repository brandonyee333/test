/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.release.tool.internal.upgrade.v1_0_2;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Jenny Chen
 */
public class UpgradeArtifactVersion extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"delete from OSBCustomer_ArtifactVersion where " +
				"releaseAssetCategoryId = 120353977 and group_ = " +
					"'com.liferay' and name like '%commerce%'");

		runSQL(
			"update OSBCustomer_ArtifactVersion set owner = 3 where group_ = " +
				"'com.liferay.commerce'");
	}

}