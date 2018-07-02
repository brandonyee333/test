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

package com.liferay.osb.hook.upgrade.v4_0_3;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.ProductEntry;

/**
 * @author Wesley Gong
 */
public class Upgrade_20180523174852043_ListType extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20180523174852043L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			41100, "7.1",
			ProductEntry.class.getName() + ".digitalEnterpriseAllVersions");
		insertListType(
			43001, "7.1",
			ProductEntry.class.getName() + ".digitalEnterpriseMinorVersions");
	}

}