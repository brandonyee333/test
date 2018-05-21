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

package com.liferay.osb.hook.upgrade.v3_4_3;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Yury Butrymovich
 */
public class Upgrade_20160216164357019_ListType extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160216164357019L;
	}

	protected void doUpgrade() throws Exception {
		upgradeListType();
	}

	protected void upgradeListType() {
		try {
			insertListType(
				22005, "7.0",
				"com.liferay.osb.model.ProductEntry.portalMinorVersions");
		}
		catch (Exception e) {
		}
	}

}