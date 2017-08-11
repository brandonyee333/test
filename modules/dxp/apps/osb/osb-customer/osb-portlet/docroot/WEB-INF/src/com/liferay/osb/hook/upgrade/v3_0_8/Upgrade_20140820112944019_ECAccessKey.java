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

package com.liferay.osb.hook.upgrade.v3_0_8;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

*/

/**
 * @author Ryan Park
 */
public class Upgrade_20140820112944019_ECAccessKey extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20140820112944019L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable("ECommerce_ECAccessKey")) {
			return;
		}

		StringBundler sb = new StringBundler(6);

		sb.append("create table ECommerce_ECAccessKey ");
		sb.append("(ecAccessKeyId LONG not null primary key, groupId LONG, ");
		sb.append("companyId LONG, userId LONG, userName VARCHAR(75) null, ");
		sb.append("createDate DATE null, modifiedDate DATE null, ");
		sb.append("name VARCHAR(75) null, token VARCHAR(75) null, ");
		sb.append("key_ TEXT null, notificationURL STRING null)");

		runSQL(sb.toString());

		runSQL("create index IX_E5247106 on ECommerce_ECAccessKey (groupId)");
		runSQL("create index IX_78819C5 on ECommerce_ECAccessKey (token)");
	}

	 */

}