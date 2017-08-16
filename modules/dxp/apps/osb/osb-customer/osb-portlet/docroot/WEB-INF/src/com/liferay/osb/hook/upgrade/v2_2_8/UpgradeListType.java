/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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