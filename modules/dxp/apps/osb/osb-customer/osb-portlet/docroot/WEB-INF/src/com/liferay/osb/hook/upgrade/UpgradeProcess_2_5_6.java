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

import com.liferay.osb.hook.upgrade.v2_5_6.UpgradeListType;
import com.liferay.osb.hook.upgrade.v2_5_6.UpgradeTicketAttachment;
import com.liferay.osb.hook.upgrade.v2_5_6.UpgradeTicketLink;
import com.liferay.osb.hook.upgrade.v2_5_6.UpgradeTicketSolution;
import com.liferay.osb.hook.upgrade.v2_5_6.UpgradeTicketStatus;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Alan Zhang
 * @author Sharon Li
 */
public class UpgradeProcess_2_5_6 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return 256;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeListType.class);
		upgrade(UpgradeTicketAttachment.class);
		upgrade(UpgradeTicketLink.class);
		upgrade(UpgradeTicketSolution.class);
		upgrade(UpgradeTicketStatus.class);
	}

}