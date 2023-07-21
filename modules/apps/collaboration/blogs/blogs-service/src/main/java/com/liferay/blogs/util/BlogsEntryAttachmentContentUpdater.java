/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.editor.EditorConstants;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portlet.blogs.BlogsEntryAttachmentFileEntryReference;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alejandro Tardín
 */
@Component(service = BlogsEntryAttachmentContentUpdater.class)
public class BlogsEntryAttachmentContentUpdater {

	public String updateContent(
		String content,
		List<BlogsEntryAttachmentFileEntryReference>
			blogsEntryAttachmentFileEntryReferences) {

		for (BlogsEntryAttachmentFileEntryReference
				blogsEntryAttachmentFileEntryReference :
					blogsEntryAttachmentFileEntryReferences) {

			StringBundler sb = new StringBundler(8);

			sb.append("<\\s*?img");
			sb.append(_ATTRIBUTE_LIST_REGEXP);
			sb.append(EditorConstants.ATTRIBUTE_DATA_IMAGE_ID);
			sb.append("\\s*?=\\s*?\"");
			sb.append(
				blogsEntryAttachmentFileEntryReference.
					getTempBlogsEntryAttachmentFileEntryId());
			sb.append("\"");
			sb.append(_ATTRIBUTE_LIST_REGEXP);
			sb.append("/>");

			content = content.replaceAll(
				sb.toString(),
				getBlogsEntryAttachmentFileEntryImgTag(
					blogsEntryAttachmentFileEntryReference.
						getBlogsEntryAttachmentFileEntry()));
		}

		return content;
	}

	protected String getBlogsEntryAttachmentFileEntryImgTag(
		FileEntry blogsEntryAttachmentFileEntry) {

		String fileEntryURL = PortletFileRepositoryUtil.getPortletFileEntryURL(
			null, blogsEntryAttachmentFileEntry, StringPool.BLANK);

		return "<img src=\"" + fileEntryURL + "\" />";
	}

	private static final String _ATTRIBUTE_LIST_REGEXP =
		"(\\s*?\\w+\\s*?=\\s*?\"[^\"]*\")*?\\s*?";

}