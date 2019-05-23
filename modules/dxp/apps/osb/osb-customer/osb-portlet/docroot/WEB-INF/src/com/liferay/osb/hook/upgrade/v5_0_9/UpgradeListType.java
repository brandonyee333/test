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

package com.liferay.osb.hook.upgrade.v5_0_9;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Jenny Chen
 */
public class UpgradeListType extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			41200, "7.2",
			"com.liferay.osb.model.ProductEntry.digitalEnterpriseAllVersions");
		insertListType(
			43002, "7.2",
			"com.liferay.osb.model.ProductEntry." +
				"digitalEnterpriseMinorVersions");
	}

}