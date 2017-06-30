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

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

/**
 * @author Amos Fong
 */
public class Upgrade_20160223143315110_CorpProject extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160223143315110L;
	}

	protected void addCorpProject(AccountEntry accountEntry)
		throws PortalException, SystemException {

		CorpProject corpProject = CorpProjectLocalServiceUtil.addCorpProject(
			OSBConstants.USER_AMOS_FONG_USER_ID, 0, StringPool.BLANK,
			StringPool.BLANK, accountEntry.getName());

		AccountEntryLocalServiceUtil.updateCorpProject(
			accountEntry.getAccountEntryId(), corpProject.getCorpProjectId());

		List<AccountCustomer> accountCustomers =
			accountEntry.getAccountCustomers();

		long[] userIds = new long[accountCustomers.size()];

		for (int i = 0; i < accountCustomers.size(); i++) {
			AccountCustomer accountCustomer = accountCustomers.get(i);

			userIds[i] = accountCustomer.getUserId();
		}

		CorpProjectLocalServiceUtil.addCorpProjectUsers(
			corpProject.getCorpProjectId(), userIds);

		CorpProjectLocalServiceUtil.addUserCorpProjectRoles(
			OSBConstants.USER_DEFAULT_USER_ID, corpProject.getCorpProjectId(),
			userIds, OSBConstants.ROLE_OSB_CORP_ADMIN_ID);
		CorpProjectLocalServiceUtil.addUserCorpProjectRoles(
			OSBConstants.USER_DEFAULT_USER_ID, corpProject.getCorpProjectId(),
			userIds, OSBConstants.ROLE_OSB_CORP_BUYER_ID);
		CorpProjectLocalServiceUtil.addUserCorpProjectRoles(
			OSBConstants.USER_DEFAULT_USER_ID, corpProject.getCorpProjectId(),
			userIds, OSBConstants.ROLE_OSB_CORP_LCS_USER_ID);
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<AccountEntry> accountEntries =
			AccountEntryLocalServiceUtil.getAccountEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (AccountEntry accountEntry : accountEntries) {
			if (accountEntry.getCorpProjectId() > 0) {
				continue;
			}

			if ((accountEntry.getAccountEntryId() ==
					OSBConstants.ACCOUNT_ENTRY_LRDCOM_ID) ||
				(accountEntry.getType() == AccountEntryConstants.TYPE_TRIAL)) {

				continue;
			}

			String name = accountEntry.getName();

			if (name.endsWith("CLOSED") || name.endsWith("SP")) {
				continue;
			}

			try {
				addCorpProject(accountEntry);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		Upgrade_20160223143315110_CorpProject.class);

}