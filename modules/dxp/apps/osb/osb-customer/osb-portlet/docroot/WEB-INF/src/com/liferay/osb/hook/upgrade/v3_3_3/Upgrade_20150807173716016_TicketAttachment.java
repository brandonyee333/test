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

package com.liferay.osb.hook.upgrade.v3_3_3;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.FileRepository;
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.support.util.FileRepositoryUtil;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.List;

/**
 * @author Alan Zhang
 */
public class Upgrade_20150807173716016_TicketAttachment
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20150807173716016L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_upgradeIndex();

		_renameFiles();
	}

	private void _doRenameFiles(List<TicketAttachment> ticketAttachments) {
		for (int i = 0; i < ticketAttachments.size(); i++) {
			TicketAttachment ticketAttachment = ticketAttachments.get(i);

			FileRepository fileRepository =
				SupportUtil.getFirstActiveFileRepository(
					ticketAttachment.getAvailableFileRepositoryIdsSet());

			boolean delete = false;

			if (i == (ticketAttachments.size() - 1)) {
				delete = true;
			}

			try {
				String message = FileRepositoryUtil.renameFile(
					fileRepository.getFileRepositoryId(),
					ticketAttachment.getTicketEntryId(),
					ticketAttachment.getFileName(),
					ticketAttachment.getFilePath(),
					ticketAttachment.getReplicate(), delete);

				if (message.equals("success")) {
					if (_log.isInfoEnabled()) {
						StringBundler sb = new StringBundler(4);

						sb.append("Update successful for ticket ");
						sb.append(ticketAttachment.getTicketEntryId());
						sb.append(" ticketAttachment ");
						sb.append(ticketAttachment.getTicketAttachmentId());

						_log.info(sb.toString());
					}
				}
				else {
					StringBundler sb = new StringBundler(4);

					sb.append("Update failed for ticket ");
					sb.append(ticketAttachment.getTicketEntryId());
					sb.append(" ticketAttachment ");
					sb.append(ticketAttachment.getTicketAttachmentId());

					_log.error(sb.toString());
				}
			}
			catch (Exception e) {
				StringBundler sb = new StringBundler(4);

				sb.append("Update failed for ticket ");
				sb.append(ticketAttachment.getTicketEntryId());
				sb.append(" ticketAttachment ");
				sb.append(ticketAttachment.getTicketAttachmentId());

				_log.error(sb.toString(), e);
			}
		}
	}

	private void _renameFiles() {
		/*List<TicketAttachment> ticketAttachments =
			TicketAttachmentLocalServiceUtil.getTicketAttachments(
				new Date(), TicketAttachmentConstants.TYPE_LARGE_FILE);

		Map<String, ArrayList<TicketAttachment>> ticketAttachmentGroup =
			new HashMap<>();

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			ArrayList<TicketAttachment> ticketAttachmentList =
				ticketAttachmentGroup.get(ticketAttachment.getFileName());

			if (ticketAttachmentList == null) {
				ticketAttachmentList = new ArrayList<>();

				ticketAttachmentGroup.put(
					ticketAttachment.getFileName(), ticketAttachmentList);
			}

			ticketAttachmentList.add(ticketAttachment);
		}

		for (String key : ticketAttachmentGroup.keySet()) {
			_doRenameFiles(ticketAttachmentGroup.get(key));
		}*/
	}

	private void _upgradeIndex() throws Exception {
		if (!hasIndex("OSB_TicketAttachment", "IX_C5D75521")) {
			runSQL(
				"create index IX_C5D75521 on OSB_TicketAttachment " +
					"(createDate, type_)");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		Upgrade_20150807173716016_TicketAttachment.class);

}