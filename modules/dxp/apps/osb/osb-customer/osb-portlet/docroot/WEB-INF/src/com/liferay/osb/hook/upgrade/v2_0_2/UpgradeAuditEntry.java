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

package com.liferay.osb.hook.upgrade.v2_0_2;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Lin Cui
 */
public class UpgradeAuditEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateAuditEntry();
	}

	protected void updateAuditEntry() throws Exception {
		if (hasTable("OSB_AuditEntry")) {
			return;
		}

		runSQL("rename table OSB_TicketAudit to OSB_AuditEntry");

		runSQL(
			"alter table OSB_AuditEntry change ticketAuditId auditEntryId " +
				"LONG");
		runSQL(
			"alter table OSB_AuditEntry change previousAuditId " +
				"previousAuditEntryId LONG");
		runSQL(
			"alter table OSB_AuditEntry change classNameId fieldClassNameId " +
				"LONG");
		runSQL("alter table OSB_AuditEntry change classPK fieldClassPK LONG");
		runSQL("alter table OSB_AuditEntry change ticketEntryId classPK LONG");

		runSQL("alter table OSB_AuditEntry add column classNameId LONG");

		runSQL("update OSB_AuditEntry set classNameId = 1400973");

		runSQL("drop index IX_D6A28541 on OSB_AuditEntry");
		runSQL("drop index IX_71197B83 on OSB_AuditEntry");

		runSQL(
			"create index IX_6DC23F99 on OSB_AuditEntry (classNameId, " +
				"classPK, visibility)");
		runSQL(
			"create index IX_A70C3087 on OSB_AuditEntry (fieldClassNameId, " +
				"fieldClassPK, field)");

		runSQL(
			"update Counter set name = replace(name, " +
				"'com.liferay.osb.model.TicketAudit', " +
					"'com.liferay.osb.model.AuditEntry#1400973')");

		runSQL(
			"update ListType set type_ = " +
				"'com.liferay.osb.model.AuditEntry.field' where (listTypeId " +
					">= 34001) and (listTypeId <= 34099)");
	}

}