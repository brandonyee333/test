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

package com.liferay.osb.hook.upgrade.v2_3_0;

import com.liferay.compat.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.service.TicketAttachmentLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;

import java.util.List;

/**
 * @author Alan Zhang
 * @author Brent Krone-Schmidt
 */
public class UpgradeTicketAttachment extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateTicketAttachmentIndex();
		updateTicketAttachments();
	}

	protected void updateTicketAttachment(TicketAttachment ticketAttachment) {
		long companyId = OSBConstants.COMPANY_ID;
		long repositoryId = CompanyConstants.SYSTEM;

		String dirName =
			OSBConstants.ATTACHMENTS_DIR_SUPPORT +
				ticketAttachment.getTicketEntryId();

		String newDirName =
			dirName + "/" + ticketAttachment.getTicketAttachmentId();

		try {
			DLStoreUtil.addDirectory(companyId, repositoryId, newDirName);

			DLStoreUtil.updateFile(
				companyId, repositoryId,
				dirName + "/" + ticketAttachment.getFileName(),
				newDirName + "/" + ticketAttachment.getFileName());
		}
		catch (Exception e) {
		}
	}

	protected void updateTicketAttachmentIndex() throws Exception {
		runSQL("drop index IX_161D6F5C on OSB_TicketAttachment");

		runSQL(
			"create index IX_161D6F5C on OSB_TicketAttachment " +
				"(ticketEntryId, fileName, visibility)");
	}

	protected void updateTicketAttachments() throws Exception {
		List<TicketAttachment> ticketAttachments =
			TicketAttachmentLocalServiceUtil.getTicketAttachments(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			updateTicketAttachment(ticketAttachment);
		}
	}

}