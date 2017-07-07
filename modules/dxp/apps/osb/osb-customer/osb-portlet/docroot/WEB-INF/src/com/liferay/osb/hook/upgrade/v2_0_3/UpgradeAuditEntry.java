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

package com.liferay.osb.hook.upgrade.v2_0_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.AuditEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.Date;
import java.util.List;

*/

/**
 * @author Lin Cui
 */
public class UpgradeAuditEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	protected void doUpgrade() throws Exception {
		updateAuditEntry();
	}

	protected void updateAuditEntry() throws Exception {
		Date now = new Date();

		List<AccountEntry> accountEntries =
			AccountEntryLocalServiceUtil.getAccountEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (AccountEntry accountEntry : accountEntries) {
			List<AccountWorker> accountWorkers =
				accountEntry.getAccountWorkers();

			long auditSetId = AuditEntryLocalServiceUtil.getNextAuditSetId(
				AccountEntry.class.getName(), accountEntry.getAccountEntryId());

			for (AccountWorker accountWorker : accountWorkers) {
				User user = UserLocalServiceUtil.getUserById(
					accountWorker.getUserId());

				long classNameId = PortalUtil.getClassNameId(
					AccountEntry.class.getName());
				long fieldClassNameId = PortalUtil.getClassNameId(
					AccountWorker.class.getName());

				AuditEntryLocalServiceUtil.addAuditEntry(
					OSBConstants.USER_DEFAULT_USER_ID, StringPool.BLANK, now,
					classNameId, accountEntry.getAccountEntryId(), auditSetId,
					fieldClassNameId, accountWorker.getAccountWorkerId(),
					AuditEntryConstants.ACTION_ASSIGN,
					AuditEntryConstants.FIELD_USER, VisibilityConstants.WORKERS,
					StringPool.BLANK, StringPool.BLANK, user.getFullName(),
					String.valueOf(user.getUserId()));

				AuditEntryLocalServiceUtil.addAuditEntry(
					OSBConstants.USER_DEFAULT_USER_ID, StringPool.BLANK, now,
					classNameId, accountEntry.getAccountEntryId(), auditSetId,
					fieldClassNameId, accountWorker.getAccountWorkerId(),
					AuditEntryConstants.ACTION_ASSIGN,
					AuditEntryConstants.FIELD_ROLE, VisibilityConstants.WORKERS,
					StringPool.BLANK, StringPool.BLANK,
					accountWorker.getRoleLabel(),
					String.valueOf(accountWorker.getRole()));
			}
		}
	}

}
*/

}