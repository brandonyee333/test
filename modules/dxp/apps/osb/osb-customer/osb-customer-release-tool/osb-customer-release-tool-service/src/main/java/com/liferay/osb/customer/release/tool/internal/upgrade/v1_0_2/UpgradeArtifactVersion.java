/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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