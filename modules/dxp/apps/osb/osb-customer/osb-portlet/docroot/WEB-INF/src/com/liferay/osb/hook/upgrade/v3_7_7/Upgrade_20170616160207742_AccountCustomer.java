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

package com.liferay.osb.hook.upgrade.v3_7_7;

import com.liferay.osb.exception.AccountEntryMaximumCustomersException;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20170616160207742_AccountCustomer
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20170616160207742L;
	}

	protected void addAccountCustomer(long userId, long accountEntryId)
		throws PortalException {

		AccountCustomerLocalServiceUtil.addAccountCustomer(
			OSBConstants.USER_DEFAULT_USER_ID, userId, accountEntryId,
			AccountCustomerConstants.ROLE_WATCHER,
			AccountCustomerConstants.NOTIFICATIONS_ALL);
	}

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBundler sb = new StringBundler(17);

		sb.append("select OSB_AccountEntry.accountEntryId, Users_Orgs.userId ");
		sb.append("FROM OSB_CorpProject inner join OSB_AccountEntry on ");
		sb.append("OSB_AccountEntry.corpProjectId = ");
		sb.append("OSB_CorpProject.corpProjectId inner join Users_Orgs on ");
		sb.append("Users_Orgs.organizationId = ");
		sb.append("OSB_CorpProject.organizationId left join ");
		sb.append("OSB_AccountCustomer on OSB_AccountCustomer.accountEntryId ");
		sb.append("= OSB_AccountEntry.accountEntryId and ");
		sb.append("OSB_AccountCustomer.userId = Users_Orgs.userId where ");
		sb.append("(OSB_AccountEntry.corpProjectId != 0) and ((");
		sb.append("OSB_AccountEntry.status = ");
		sb.append(WorkflowConstants.STATUS_APPROVED);
		sb.append(") or (OSB_AccountEntry.status = ");
		sb.append(WorkflowConstants.STATUS_CLOSED);
		sb.append(") or (OSB_AccountEntry.status = ");
		sb.append(WorkflowConstants.STATUS_EXPIRED);
		sb.append(")) and (OSB_AccountCustomer.userId is null)");

		try {
			ps = connection.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long accountEntryId = rs.getLong("accountEntryId");
				long userId = rs.getLong("userId");

				try {
					addAccountCustomer(userId, accountEntryId);
				}
				catch (AccountEntryMaximumCustomersException aemce) {
					AccountEntry accountEntry =
						AccountEntryLocalServiceUtil.getAccountEntry(
							accountEntryId);

					int maxCustomers = accountEntry.getMaxCustomers();

					accountEntry.setMaxCustomers(maxCustomers + 1);

					AccountEntryLocalServiceUtil.updateAccountEntry(
						accountEntry);

					addAccountCustomer(userId, accountEntryId);
				}
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

}