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

package com.liferay.osb.hook.upgrade.v3_6_2;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AuditEntryConstants;

*/

/**
 * @author Amos Fong
 */
public class Upgrade_20161117112922258_AccountEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

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
*/

}