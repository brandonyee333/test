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

package com.liferay.osb.hook.upgrade.v2_2_8;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Calvin Keum
 */
public class UpgradeListType extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeListType();
	}

	protected void upgradeListType() {
		try {
			insertListType(
				22004, "6.2",
				"com.liferay.osb.model.ProductEntry.portalMinorVersions");

			insertListType(
				23040, "3.0.0",
				"com.liferay.osb.model.ProductEntry.socialOfficeAllVersions");
			insertListType(
				24002, "3",
				"com.liferay.osb.model.ProductEntry.socialOfficeMajorVersions");
			insertListType(
				25004, "3.0",
				"com.liferay.osb.model.ProductEntry.socialOfficeMinorVersions");
		}
		catch (Exception e) {
		}
	}

}