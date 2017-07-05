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

package com.liferay.osb.hook.upgrade.v3_7_5;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.AuditEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20170601083600373_AccountWorker
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20170601083600373L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<AccountWorker> accountWorkers =
			AccountWorkerLocalServiceUtil.getAccountWorkers(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		long classNameId = PortalUtil.getClassNameId(
			AccountEntry.class.getName());
		long fieldClassNameId = PortalUtil.getClassNameId(
			AccountWorker.class.getName());

		for (AccountWorker accountWorker : accountWorkers) {
			if (!ArrayUtil.contains(
					AccountWorkerConstants.ROLES_DEPRECATED,
					accountWorker.getRole())) {

				continue;
			}

			int oldRole = accountWorker.getRole();

			accountWorker.setRole(AccountWorkerConstants.ROLE_SALES);

			AccountWorkerLocalServiceUtil.updateAccountWorker(
				accountWorker, false);

			long auditSetId = AuditEntryLocalServiceUtil.getNextAuditSetId(
				AccountEntry.class.getName(),
				accountWorker.getAccountEntryId());

			User user = UserLocalServiceUtil.getUser(accountWorker.getUserId());

			AuditEntryLocalServiceUtil.addAuditEntry(
				OSBConstants.USER_DEFAULT_USER_ID, StringPool.BLANK, new Date(),
				classNameId, accountWorker.getAccountEntryId(), auditSetId,
				fieldClassNameId, accountWorker.getAccountWorkerId(),
				AuditEntryConstants.ACTION_ASSIGN,
				AuditEntryConstants.FIELD_USER, VisibilityConstants.WORKERS,
				user.getFullName(), String.valueOf(user.getUserId()),
				user.getFullName(), String.valueOf(user.getUserId()));

			AuditEntryLocalServiceUtil.addAuditEntry(
				OSBConstants.USER_DEFAULT_USER_ID, StringPool.BLANK, new Date(),
				classNameId, accountWorker.getAccountEntryId(), auditSetId,
				fieldClassNameId, accountWorker.getAccountWorkerId(),
				AuditEntryConstants.ACTION_UPDATE,
				AuditEntryConstants.FIELD_ROLE, VisibilityConstants.WORKERS,
				AccountWorkerConstants.getRoleLabel(oldRole),
				String.valueOf(oldRole), accountWorker.getRoleLabel(),
				String.valueOf(accountWorker.getRole()));
		}
	}

}