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

package com.liferay.osb.hook.upgrade.v3_4_7;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Ryan Park
 */
public class Upgrade_20160413135602994_ECProcessorCallAudit
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160413135602994L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable("ECommerce_ECProcessorCallAudit")) {
			return;
		}

		StringBundler sb = new StringBundler(6);

		sb.append("create table ECommerce_ECProcessorCallAudit");
		sb.append("(ecProcessorCallAuditId LONG not null primary key, ");
		sb.append("groupId LONG, companyId LONG, userId LONG, ");
		sb.append("userName VARCHAR(75) null, createDate DATE null, ");
		sb.append("className VARCHAR(75) null, operationType LONG, ");
		sb.append("ipAddress VARCHAR(75) null, hostName VARCHAR(75) null)");

		runSQL(sb.toString());
	}

}