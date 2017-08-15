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

/*import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.model.Organization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

*/

/**
 * @author Joan H. Kim
 */
public class Upgrade_20160920131304136_CorpEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20160920131304136L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeCorpEntry();
	}

	protected void upgradeCorpEntry() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			StringBundler sb = new StringBundler(9);

			sb.append("select * from OSB_CorpEntry inner join Group_ on ");
			sb.append("OSB_CorpEntry.organizationId = Group_.classPK and ");
			sb.append("Group_.classNameId = ");
			sb.append(PortalUtil.getClassNameId(Organization.class));
			sb.append(" inner join Layout on Group_.groupId = Layout.groupId ");
			sb.append("where OSB_CorpEntry.status = ");
			sb.append(WorkflowConstants.STATUS_APPROVED);
			sb.append(" and Group_.site = 1 and Layout.privateLayout = 0 ");
			sb.append("and Layout.friendlyURL = '/profile'");

			ps = connection.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				CorpEntryLocalServiceUtil.updateSite(
					rs.getLong("corpEntryId"),
					WorkflowConstants.STATUS_APPROVED, true);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	 */

}