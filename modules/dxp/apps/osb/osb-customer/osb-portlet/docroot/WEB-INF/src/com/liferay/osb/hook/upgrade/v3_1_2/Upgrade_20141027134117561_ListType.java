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

package com.liferay.osb.hook.upgrade.v3_1_2;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Sharon Li
 */
public class Upgrade_20141027134117561_ListType extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			23061, "3.1.1",
			"com.liferay.osb.model.ProductEntry.socialOfficeAllVersions");
	}

}