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

package com.liferay.osb.hook.upgrade.v3_0_9;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Amos Fong
 */
public class Upgrade_20140911175154027_ListType extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20140911175154027L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			23060, "3.1.0",
			"com.liferay.osb.model.ProductEntry.socialOfficeAllVersions");
		insertListType(
			25005, "3.1",
			"com.liferay.osb.model.ProductEntry.socialOfficeMinorVersions");
	}

}