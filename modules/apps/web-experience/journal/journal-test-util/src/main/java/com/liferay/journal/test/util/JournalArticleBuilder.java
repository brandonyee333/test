/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.test.util;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolderConstants;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;

import java.util.Locale;
import java.util.Map;

/**
 * @author André de Oliveira
 */
public class JournalArticleBuilder {

	public JournalArticle addArticle() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_groupId);

		long userId = serviceContext.getUserId();

		long folderId = JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID;
		Map<Locale, String> titleMap = _journalArticleTitle.getValues();
		Map<Locale, String> descriptionMap = null;
		String contentString = _journalArticleContent.getContentString();
		String ddmStructureKey = "BASIC-WEB-CONTENT";
		String ddmTemplateKey = "BASIC-WEB-CONTENT";

		return JournalArticleLocalServiceUtil.addArticle(
			userId, _groupId, folderId, titleMap, descriptionMap, contentString,
			ddmStructureKey, ddmTemplateKey, serviceContext);
	}

	public void setContent(JournalArticleContent content) {
		_journalArticleContent = content;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setTitle(JournalArticleTitle title) {
		_journalArticleTitle = title;
	}

	private Long _groupId;
	private JournalArticleContent _journalArticleContent;
	private JournalArticleTitle _journalArticleTitle;

}