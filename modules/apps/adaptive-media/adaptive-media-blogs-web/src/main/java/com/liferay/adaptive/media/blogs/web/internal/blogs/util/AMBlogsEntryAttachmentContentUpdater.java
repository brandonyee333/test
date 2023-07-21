/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.adaptive.media.blogs.web.internal.blogs.util;

import com.liferay.blogs.util.BlogsEntryAttachmentContentUpdater;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepository;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(
	property = "service.ranking:Integer=2",
	service = BlogsEntryAttachmentContentUpdater.class
)
public class AMBlogsEntryAttachmentContentUpdater
	extends BlogsEntryAttachmentContentUpdater {

	public AMBlogsEntryAttachmentContentUpdater() {
	}

	protected AMBlogsEntryAttachmentContentUpdater(
		PortletFileRepository portletFileRepository) {

		_portletFileRepository = portletFileRepository;
	}

	@Override
	protected String getBlogsEntryAttachmentFileEntryImgTag(
		FileEntry blogsEntryAttachmentFileEntry) {

		String fileEntryURL = _portletFileRepository.getPortletFileEntryURL(
			null, blogsEntryAttachmentFileEntry, StringPool.BLANK);

		StringBundler sb = new StringBundler(5);

		sb.append("<img data-fileEntryId=\"");
		sb.append(
			String.valueOf(blogsEntryAttachmentFileEntry.getFileEntryId()));
		sb.append("\" src=\"");
		sb.append(fileEntryURL);
		sb.append("\" />");

		return sb.toString();
	}

	@Reference
	private PortletFileRepository _portletFileRepository;

}