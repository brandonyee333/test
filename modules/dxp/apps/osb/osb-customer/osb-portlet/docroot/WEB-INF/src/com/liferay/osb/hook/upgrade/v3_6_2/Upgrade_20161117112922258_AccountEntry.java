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

package com.liferay.osb.hook.upgrade.v3_6_2;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AuditEntryConstants;

/**
 * @author Amos Fong
 */
public class Upgrade_20161117112922258_AccountEntry extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20161117112922258L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			AuditEntryConstants.FIELD_CORP_PROJECT, "corp-project",
			"com.liferay.osb.model.AuditEntry.field");
		insertListType(
			AuditEntryConstants.FIELD_CORP_ENTRY_NAME, "corp-entry-name",
			"com.liferay.osb.model.AuditEntry.field");
		insertListType(
			AuditEntryConstants.FIELD_NAME, "name",
			"com.liferay.osb.model.AuditEntry.field");
		insertListType(
			AuditEntryConstants.FIELD_CODE, "code",
			"com.liferay.osb.model.AuditEntry.field");
		insertListType(
			AuditEntryConstants.FIELD_TYPE, "type",
			"com.liferay.osb.model.AuditEntry.field");
		insertListType(
			AuditEntryConstants.FIELD_INDUSTRY, "industry",
			"com.liferay.osb.model.AuditEntry.field");
		insertListType(
			AuditEntryConstants.FIELD_PARTNER_MANAGED_SUPPORT,
			"partner-managed-support",
			"com.liferay.osb.model.AuditEntry.field");
		insertListType(
			AuditEntryConstants.FIELD_INSTRUCTIONS, "instructions",
			"com.liferay.osb.model.AuditEntry.field");
		insertListType(
			AuditEntryConstants.FIELD_NOTES, "notes",
			"com.liferay.osb.model.AuditEntry.field");
		insertListType(
			AuditEntryConstants.FIELD_RENEWAL_CONTACT_USER,
			"renewal-contact-user", "com.liferay.osb.model.AuditEntry.field");
		insertListType(
			AuditEntryConstants.FIELD_STATUS_MESSAGE, "status-message",
			"com.liferay.osb.model.AuditEntry.field");
		insertListType(
			AuditEntryConstants.FIELD_ADDRESS, "address",
			"com.liferay.osb.model.AuditEntry.field");
		insertListType(
			AuditEntryConstants.FIELD_LANGUAGES, "languages",
			"com.liferay.osb.model.AuditEntry.field");
		insertListType(
			AuditEntryConstants.FIELD_SUPPORT_REGIONS, "support-regions",
			"com.liferay.osb.model.AuditEntry.field");
	}

}