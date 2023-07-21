/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.messageboards.util;

import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.service.MBMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Eudaldo Alonso
 */
public class MBMessageAttachmentsUtil {

	public static MBMessage fetchMessage(long fileEntryId)
		throws PortalException {

		return MBMessageLocalServiceUtil.fetchMBMessage(
			getMessageId(fileEntryId));
	}

	public static MBMessage getMessage(long fileEntryId)
		throws PortalException {

		return MBMessageLocalServiceUtil.getMBMessage(
			getMessageId(fileEntryId));
	}

	protected static long getMessageId(long fileEntryId)
		throws PortalException {

		FileEntry fileEntry = PortletFileRepositoryUtil.getPortletFileEntry(
			fileEntryId);

		Folder folder = PortletFileRepositoryUtil.getPortletFolder(
			fileEntry.getFolderId());

		return GetterUtil.getLong(folder.getName());
	}

}