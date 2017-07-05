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

package com.liferay.osb.hook.upgrade.v2_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Lin Cui
 */
public class UpgradeTicketAttachment extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateTicketAttachment();
	}

	protected void updateTicketAttachment() throws Exception {
		if (tableHasColumn("OSB_TicketAttachment", "type_")) {
			return;
		}

		runSQL("alter table OSB_TicketAttachment add column type_ INTEGER");

		runSQL("update OSB_TicketAttachment set type_ = 0");
	}

}