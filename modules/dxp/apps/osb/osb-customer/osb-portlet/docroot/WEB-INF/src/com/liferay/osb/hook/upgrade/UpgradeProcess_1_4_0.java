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

package com.liferay.osb.hook.upgrade;

import com.liferay.osb.hook.upgrade.v1_4_0.UpgradeAccountEntry;
import com.liferay.osb.hook.upgrade.v1_4_0.UpgradeSupportResponse;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Lin Cui
 */
public class UpgradeProcess_1_4_0 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return 140;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeAccountEntry.class);
		upgrade(UpgradeSupportResponse.class);
	}

}