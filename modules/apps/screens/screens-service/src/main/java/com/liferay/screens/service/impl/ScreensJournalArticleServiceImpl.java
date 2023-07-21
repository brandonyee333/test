/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.screens.service.impl;

import com.liferay.dynamic.data.mapping.kernel.DDMTemplate;
import com.liferay.dynamic.data.mapping.kernel.DDMTemplateManagerUtil;
import com.liferay.journal.model.JournalArticleResource;
import com.liferay.journal.service.permission.JournalArticlePermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.screens.service.base.ScreensJournalArticleServiceBaseImpl;

import java.util.Locale;

/**
 * @author Javier Gamarra
 */
public class ScreensJournalArticleServiceImpl
	extends ScreensJournalArticleServiceBaseImpl {

	@Override
	public String getJournalArticleContent(long classPK, Locale locale)
		throws PortalException {

		JournalArticleResource journalArticleResource =
			journalArticleResourceLocalService.getArticleResource(classPK);

		JournalArticlePermission.check(
			getPermissionChecker(), journalArticleResource.getGroupId(),
			journalArticleResource.getArticleId(), ActionKeys.VIEW);

		return journalArticleLocalService.getArticleContent(
			journalArticleResource.getGroupId(),
			journalArticleResource.getArticleId(), null, null,
			getLanguageId(locale), null, null);
	}

	@Override
	public String getJournalArticleContent(
			long classPK, long ddmTemplateId, Locale locale)
		throws PortalException {

		JournalArticleResource journalArticleResource =
			journalArticleResourceLocalService.getArticleResource(classPK);

		JournalArticlePermission.check(
			getPermissionChecker(), journalArticleResource.getGroupId(),
			journalArticleResource.getArticleId(), ActionKeys.VIEW);

		return journalArticleLocalService.getArticleContent(
			journalArticleResource.getGroupId(),
			journalArticleResource.getArticleId(), null,
			getDDMTemplateKey(ddmTemplateId), getLanguageId(locale), null,
			null);
	}

	@Override
	public String getJournalArticleContent(
			long groupId, String articleId, long ddmTemplateId, Locale locale)
		throws PortalException {

		JournalArticlePermission.check(
			getPermissionChecker(), groupId, articleId, ActionKeys.VIEW);

		return journalArticleLocalService.getArticleContent(
			groupId, articleId, null, getDDMTemplateKey(ddmTemplateId),
			getLanguageId(locale), null, null);
	}

	protected String getDDMTemplateKey(long ddmTemplateId)
		throws PortalException {

		String ddmTemplateKey = null;

		DDMTemplate ddmTemplate = DDMTemplateManagerUtil.getTemplate(
			ddmTemplateId);

		if (ddmTemplate != null) {
			ddmTemplateKey = ddmTemplate.getTemplateKey();
		}

		return ddmTemplateKey;
	}

	protected String getLanguageId(Locale locale) {
		if (locale == null) {
			locale = LocaleUtil.getSiteDefault();
		}

		return LocaleUtil.toLanguageId(locale);
	}

}