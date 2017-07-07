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

package com.liferay.osb.hook.upgrade.v3_4_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TicketEntryConstants;

*/

/**
 * @author Jenny Chen
 */
public class Upgrade_20160222090748639_ListType extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20160222090748639L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			TicketEntryConstants.COMPONENT_LIFERAY_CONNECTED_SERVICES,
			"liferay-connected-services",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
	}

}
*/

}