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

package com.liferay.osb.hook.upgrade.v3_0_7;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

*/

/**
 * @author Sharon Li
 */
public class Upgrade_20140813162146667_ListType extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20140813162146667L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateListType(27020, "jboss-as-7.1");
		updateListType(27050, "jboss-eap-5.1");
		updateListType(27042, "jboss-eap-5.2");
		updateListType(27040, "jboss-eap-6.0");
		updateListType(27049, "jboss-eap-6.1");

		insertListType(
			27051, "jboss-eap-6.2", "com.liferay.osb.model.TicketEntry.envAS");
		insertListType(
			28031, "postgresql-9.3", "com.liferay.osb.model.TicketEntry.envDB");
		insertListType(
			30034, "debian-6.0", "com.liferay.osb.model.TicketEntry.envOS");
	}

	private void updateListType(int listTypeId, String name) throws Exception {
		StringBundler sb = new StringBundler(4);

		sb.append("update ListType set name = '");
		sb.append(name);
		sb.append("' where listTypeId = ");
		sb.append(listTypeId);

		runSQL(sb.toString());
	}

	 */

}