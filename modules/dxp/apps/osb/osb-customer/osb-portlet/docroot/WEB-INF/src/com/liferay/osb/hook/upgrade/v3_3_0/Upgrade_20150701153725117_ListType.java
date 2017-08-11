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

package com.liferay.osb.hook.upgrade.v3_3_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TicketEntry;

*/

/**
 * @author Jeremy Fu
 */
public class Upgrade_20150701153725117_ListType extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20150701153725117L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			27052, "jboss-eap-6.3", TicketEntry.class.getName() + ".envAS");
		insertListType(
			27053, "jboss-eap-6.4", TicketEntry.class.getName() + ".envAS");
	}

	 */

}