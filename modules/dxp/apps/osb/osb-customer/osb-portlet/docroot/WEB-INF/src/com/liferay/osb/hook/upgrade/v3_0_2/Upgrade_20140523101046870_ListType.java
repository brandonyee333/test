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

package com.liferay.osb.hook.upgrade.v3_0_2;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Sharon Li
 */
public class Upgrade_20140523101046870_ListType extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20140523101046870L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeListType();
	}

	protected void upgradeListType() {
		try {
			insertListType(
				23043, "3.0.3",
				"com.liferay.osb.model.ProductEntry.socialOfficeAllVersions");
		}
		catch (Exception e) {
		}
	}

}