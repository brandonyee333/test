/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.hook.upgrade.v2_0_3;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.AuditEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;

/**
 * @author Lin Cui
 */
public class UpgradeAuditEntry extends UpgradeProcess {

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