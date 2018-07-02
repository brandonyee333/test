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