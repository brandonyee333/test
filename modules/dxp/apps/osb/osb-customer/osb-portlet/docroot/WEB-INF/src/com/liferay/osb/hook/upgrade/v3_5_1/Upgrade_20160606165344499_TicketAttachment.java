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

package com.liferay.osb.hook.upgrade.v3_5_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.TicketAttachmentConstants;
import com.liferay.osb.service.TicketAttachmentLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20160606165344499_TicketAttachment
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160606165344499L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!tableHasColumn("OSB_TicketAttachment", "extractedText")) {
			runSQL(
				"alter table OSB_TicketAttachment add column extractedText " +
					"STRING");
		}

		if (!hasIndex("OSB_TicketAttachment", "IX_8415F49F")) {
			runSQL("create index IX_8415F49F on OSB_TicketAttachment (type_)");
		}

		updateTicketAttachments();
	}

	protected void updateTicketAttachments() throws SystemException {
		int[] types = {
			TicketAttachmentConstants.TYPE_NEW_PATCH_LEVEL,
			TicketAttachmentConstants.TYPE_NEW_PORTAL_EXT,
			TicketAttachmentConstants.TYPE_PATCH_LEVEL,
			TicketAttachmentConstants.TYPE_PORTAL_EXT
		};

		List<TicketAttachment> ticketAttachments =
			TicketAttachmentLocalServiceUtil.getTicketAttachments(types);

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			TicketAttachmentLocalServiceUtil.updateExtractedText(
				ticketAttachment);
		}
	}

}