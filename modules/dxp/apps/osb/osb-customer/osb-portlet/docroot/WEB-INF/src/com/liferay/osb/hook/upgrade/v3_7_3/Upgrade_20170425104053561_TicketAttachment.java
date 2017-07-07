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

package com.liferay.osb.hook.upgrade.v3_7_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TicketAttachmentConstants;

*/

/**
 * @author Kyle Bischof
 */
public class Upgrade_20170425104053561_TicketAttachment
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20170425104053561L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"update OSB_TicketAttachment set type_ = " +
				TicketAttachmentConstants.TYPE_LARGE_HOTFIX + " where " +
					"fileName like 'liferay-hotfix%' and type_ = " +
						TicketAttachmentConstants.TYPE_LARGE_FILE);
	}

}
*/

}