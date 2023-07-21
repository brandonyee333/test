/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.sync.internal.importer;

import com.liferay.osb.customer.zendesk.documentation.sync.exception.DocumentationImportException;
import com.liferay.osb.customer.zendesk.documentation.sync.importer.DocumentationImporter;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection;
import com.liferay.osb.customer.zendesk.documentation.sync.service.ZendeskArticleLocalServiceUtil;
import com.liferay.osb.customer.zendesk.documentation.sync.service.ZendeskSectionLocalServiceUtil;
import com.liferay.osb.customer.zendesk.util.ZendeskLocaleUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.zip.ZipReader;

import java.util.Locale;
import java.util.Map;

/**
 * @author Jenny Chen
 */
public class DocumentationTranslationArchiveImporter
	extends DocumentationArchiveImporter implements DocumentationImporter {

	public DocumentationTranslationArchiveImporter(
			ZendeskLocaleUtil zendeskLocaleUtil, ZipReader zipReader,
			ZendeskCategory zendeskCategory, Locale locale,
			String[] markdownImporterArticleExtensions,
			String markdownImporterArticleIntro)
		throws PortalException {

		super(
			zendeskLocaleUtil, zipReader, zendeskCategory, locale,
			markdownImporterArticleExtensions, markdownImporterArticleIntro);
	}

	@Override
	protected void deleteRemovedArticles() throws Exception {
	}

	@Override
	protected void deleteRemovedSections() throws Exception {
	}

	@Override
	protected void processEntry(String entry) throws Exception {
		if (!ArrayUtil.exists(
				markdownImporterArticleExtensions, entry::endsWith)) {

			return;
		}

		String markdown = zipReader.getEntryAsString(entry);

		ArticleMarkdownConverter articleMarkdownConverter =
			new ArticleMarkdownConverter(zipReader, entry, markdown);

		if (isZendeskSection(entry)) {
			processZendeskSection(
				articleMarkdownConverter.getId(),
				articleMarkdownConverter.getTitle());
		}

		processZendeskArticle(
			articleMarkdownConverter.getId(),
			articleMarkdownConverter.getTitle(),
			articleMarkdownConverter.getUrlTitle(),
			articleMarkdownConverter.getHtml(),
			articleMarkdownConverter.getAttachments());
	}

	@Override
	protected void processZendeskArticle(
			String documentationKey, String title, String urlTitle, String body,
			Map<String, byte[]> attachments)
		throws Exception {

		if (currentZendeskSection == null) {
			throw new DocumentationImportException(
				"Article " + documentationKey + " is not in a section.");
		}

		ZendeskArticle zendeskArticle =
			ZendeskArticleLocalServiceUtil.fetchZendeskArticle(
				zendeskCategory.getZendeskCategoryId(), documentationKey);

		if (zendeskArticle != null) {
			ZendeskArticle previousZendeskArticle =
				ZendeskArticleLocalServiceUtil.fetchZendeskArticle(
					zendeskArticle.getZendeskCategoryId(),
					zendeskArticle.getPreviousArticleDocumentationKey());

			ZendeskArticle nextZendeskArticle =
				ZendeskArticleLocalServiceUtil.fetchZendeskArticle(
					zendeskArticle.getZendeskCategoryId(),
					zendeskArticle.getNextArticleDocumentationKey());

			String curBody =
				body +
					getBottomNavigationHtml(
						locale, previousZendeskArticle, nextZendeskArticle);

			curBody = replaceDocumentationOriginalURLs(locale, curBody);

			ZendeskArticleLocalServiceUtil.updateZendeskArticleTranslation(
				zendeskArticle.getZendeskArticleId(), locale, title, curBody);
		}
	}

	@Override
	protected void processZendeskSection(String documentationKey, String name)
		throws Exception {

		ZendeskSection zendeskSection =
			ZendeskSectionLocalServiceUtil.fetchZendeskSection(
				zendeskCategory.getZendeskCategoryId(), documentationKey);

		if (zendeskSection != null) {
			ZendeskSectionLocalServiceUtil.updateZendeskSectionTranslation(
				zendeskSection.getZendeskSectionId(), locale, name);
		}

		currentZendeskSection = zendeskSection;
	}

}