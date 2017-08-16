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

package com.liferay.osb.hook.upgrade.v3_4_0;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.TicketEntry;

/**
 * @author Haijian Yang
 */
public class Upgrade_20151127163927683_ListType extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20151127163927683L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			AuditEntryConstants.FIELD_ADDITIONAL_COMMENTS,
			"additional-comments", TicketEntry.class.getName() + ".field");
		insertListType(
			AuditEntryConstants.FIELD_CLUSTER_NUMBER_OF_NODES,
			"number-of-nodes", TicketEntry.class.getName() + ".field");
		insertListType(
			AuditEntryConstants.FIELD_CLUSTER_SERVER_COMMUNICATION_TYPE,
			"server-communication-type",
			TicketEntry.class.getName() + ".field");
		insertListType(
			AuditEntryConstants.FIELD_DATA_FOLDER_UPLOAD_METHOD,
			"data-upload-method", TicketEntry.class.getName() + ".field");
		insertListType(
			AuditEntryConstants.FIELD_DATABASE_UPLOAD_METHOD,
			"database-upload-method", TicketEntry.class.getName() + ".field");
		insertListType(
			AuditEntryConstants.FIELD_DOC_LIB_PERSISTENCE,
			"document-library-persistence-method",
			TicketEntry.class.getName() + ".field");
		insertListType(
			AuditEntryConstants.FIELD_HOST_NAMES, "host-names",
			TicketEntry.class.getName() + ".field");
		insertListType(
			AuditEntryConstants.FIELD_IP_ADDRESSES, "ip-addresses",
			TicketEntry.class.getName() + ".field");
		insertListType(
			AuditEntryConstants.FIELD_LICENSE_PURPOSE, "license-purpose",
			TicketEntry.class.getName() + ".field");
		insertListType(
			AuditEntryConstants.FIELD_LICENSE_TYPE, "license-type",
			TicketEntry.class.getName() + ".field");
		insertListType(
			AuditEntryConstants.FIELD_SERVER_CONFIGURATIONS,
			"server-configuration", TicketEntry.class.getName() + ".field");
		insertListType(
			AuditEntryConstants.FIELD_SERVER_IDS, "server-ids-mac-addresses",
			TicketEntry.class.getName() + ".field");
		insertListType(
			AuditEntryConstants.FIELD_STEPS_TO_UPGRADE, "upgrade-steps",
			TicketEntry.class.getName() + ".field");
		insertListType(
			AuditEntryConstants.FIELD_SUBCOMPONENT, "subcomponent",
			TicketEntry.class.getName() + ".field");
		insertListType(
			AuditEntryConstants.FIELD_SUPPORT_REGION, "support-region",
			TicketEntry.class.getName() + ".field");
		insertListType(
			AuditEntryConstants.FIELD_UPGRADE_TO_ENV_LFR, "upgrade-version",
			TicketEntry.class.getName() + ".field");
	}

}