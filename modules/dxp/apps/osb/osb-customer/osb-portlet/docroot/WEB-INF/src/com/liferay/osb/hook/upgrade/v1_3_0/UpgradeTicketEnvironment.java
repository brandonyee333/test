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

package com.liferay.osb.hook.upgrade.v1_3_0;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TicketEntryConstants;

/**
 * @author Alan Zhang
 */
public class UpgradeTicketEnvironment extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			TicketEntryConstants.ENV_AS_TCSERVER_2_6, "tcServer-2.6",
			"com.liferay.osb.model.TicketEntry.envAS");
		insertListType(
			TicketEntryConstants.ENV_OS_MAC_OS_X_10_5_PLUS, "mac-os-x-10.5+",
			"com.liferay.osb.model.TicketEntry.envOS");
		insertListType(
			TicketEntryConstants.ENV_OS_SOLARIS_EXPRESS_11,
			"solaris-express-11", "com.liferay.osb.model.TicketEntry.envOS");
		insertListType(
			TicketEntryConstants.ENV_OS_UBUNTU_10, "ubuntu-10",
			"com.liferay.osb.model.TicketEntry.envOS");
		insertListType(
			TicketEntryConstants.ENV_OS_UBUNTU_11, "ubuntu-11",
			"com.liferay.osb.model.TicketEntry.envOS");
	}

}