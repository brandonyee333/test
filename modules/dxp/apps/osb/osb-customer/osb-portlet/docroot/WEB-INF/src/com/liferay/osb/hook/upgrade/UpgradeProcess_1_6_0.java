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

import com.liferay.osb.hook.upgrade.v1_6_0.UpgradeMarketplace;
import com.liferay.osb.hook.upgrade.v1_6_0.UpgradeTicketAttachment;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Ryan Park
 * @author Lin Cui
 */
public class UpgradeProcess_1_6_0 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return 160;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeMarketplace.class);
		upgrade(UpgradeTicketAttachment.class);
	}

}