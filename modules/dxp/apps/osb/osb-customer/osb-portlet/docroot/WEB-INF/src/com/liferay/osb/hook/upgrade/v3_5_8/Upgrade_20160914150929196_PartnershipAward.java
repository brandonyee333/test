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

package com.liferay.osb.hook.upgrade.v3_5_8;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

*/

/**
 * @author Ryan Park
 */
public class Upgrade_20160914150929196_PartnershipAward
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20160914150929196L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(5);

		sb.append("create table OSBPartnership_PartnershipAward ");
		sb.append("(partnershipAwardId LONG not null primary key, ");
		sb.append("userId LONG, userName VARCHAR(75) null, ");
		sb.append("createDate DATE null, modifiedDate DATE null, ");
		sb.append("partnershipEntryId LONG, awardEntryId LONG, year INTEGER)");

		runSQL(sb.toString());
	}

}
*/

}