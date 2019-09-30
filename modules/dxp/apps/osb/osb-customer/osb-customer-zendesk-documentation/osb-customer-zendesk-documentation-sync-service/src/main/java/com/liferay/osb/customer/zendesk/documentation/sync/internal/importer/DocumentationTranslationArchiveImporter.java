/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.zendesk.documentation.sync.internal.importer;

import com.liferay.osb.customer.zendesk.documentation.sync.exception.DocumentationImportException;
import com.liferay.osb.customer.zendesk.documentation.sync.importer.DocumentationImporter;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection;
import com.liferay.osb.customer.zendesk.documentation.sync.service.ZendeskArticleLocalServiceUtil;
import com.liferay.osb.customer.zendesk.documentation.sync.service.ZendeskSectionLocalServiceUtil;
import com.liferay.osb.customer.zendesk.documentation.sync.util.ZendeskLocaleUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.zip.ZipReader;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Jenny Chen
 */
public class DocumentationTranslationArchiveImporter
	extends DocumentationArchiveImporter implements DocumentationImporter {

	public DocumentationTranslationArchiveImporter(
			ZipReader zipReader, ZendeskCategory zendeskCategory, String locale,
			String[] markdownImporterArticleExtensions,
			String markdownImporterArticleIntro)
		throws PortalException {

		super(
			zipReader, zendeskCategory, locale,
			markdownImporterArticleExtensions, markdownImporterArticleIntro);

		_zipReader = zipReader;
		_zendeskCategory = zendeskCategory;
		_locale = locale;
		_markdownImporterArticleExtensions = markdownImporterArticleExtensions;
		_markdownImporterArticleIntro = markdownImporterArticleIntro;
	}

	@Override
	public void importArticles() throws Exception {
		List<String> entries = _zipReader.getEntries();

		if (entries == null) {
			throw new DocumentationImportException(
				"The uploaded file is not a ZIP archive or it is corrupted");
		}

		Collections.sort(entries);

		_iterationCount = 0;

		for (String entry : entries) {
			processEntry(entry);
		}

		_iterationCount++;

		for (String entry : entries) {
			processEntry(entry);
		}
	}

	@Override
	protected String getBottomNavigationHtml(
		String locale, ZendeskArticle previousZendeskArticle,
		ZendeskArticle nextZendeskArticle) {

		Map<Locale, String> previousTitleMap = new HashMap<>();

		if (previousZendeskArticle != null) {
			previousTitleMap = previousZendeskArticle.getRemoteTitleMap();
		}

		Map<Locale, String> nextTitleMap = new HashMap<>();

		if (nextZendeskArticle != null) {
			nextTitleMap = nextZendeskArticle.getRemoteTitleMap();
		}

		Locale remoteLocale = ZendeskLocaleUtil.convertToLocale(locale);

		StringBundler sb = new StringBundler(12);

		sb.append("<div class=\"article-siblings\">");

		if (previousZendeskArticle != null) {
			sb.append("<span class=\"previous\"><a href=\"");
			sb.append(previousZendeskArticle.getRemoteHtmlURL(locale));
			sb.append("\">&#171; ");
			sb.append(previousTitleMap.get(remoteLocale));
			sb.append("</a></span>");
		}

		if (nextZendeskArticle != null) {
			sb.append("<span class=\"next\"><a href=\"");
			sb.append(nextZendeskArticle.getRemoteHtmlURL(locale));
			sb.append("\">");
			sb.append(nextTitleMap.get(remoteLocale));
			sb.append(" &#187;</a></span>");
		}

		sb.append("</div>");

		return sb.toString();
	}

	@Override
	protected void processEntry(String entry) throws Exception {
		if (!ArrayUtil.exists(
				_markdownImporterArticleExtensions, entry::endsWith)) {

			return;
		}

		String markdown = _zipReader.getEntryAsString(entry);

		ArticleMarkdownConverter articleMarkdownConverter =
			new ArticleMarkdownConverter(_zipReader, entry, markdown);

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

		if (_currentZendeskSection == null) {
			throw new DocumentationImportException(
				"Article " + documentationKey + " is not in a section.");
		}

		ZendeskArticle zendeskArticle =
			ZendeskArticleLocalServiceUtil.fetchZendeskArticle(
				_zendeskCategory.getZendeskCategoryId(), documentationKey);

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
						_locale, previousZendeskArticle, nextZendeskArticle);

			curBody = replaceDocumentationOriginalURLs(_locale, curBody);

			ZendeskArticleLocalServiceUtil.updateZendeskArticleTranslation(
				zendeskArticle.getZendeskArticleId(), _locale, title, curBody);
		}
	}

	@Override
	protected void processZendeskSection(String documentationKey, String name)
		throws Exception {

		ZendeskSection zendeskSection =
			ZendeskSectionLocalServiceUtil.fetchZendeskSection(
				_zendeskCategory.getZendeskCategoryId(), documentationKey);

		if (zendeskSection != null) {
			ZendeskSectionLocalServiceUtil.updateZendeskSectionTranslation(
				zendeskSection.getZendeskSectionId(), _locale, name,
				StringPool.BLANK);
		}

		_currentZendeskSection = zendeskSection;
	}

	private ZendeskSection _currentZendeskSection;
	private int _iterationCount;
	private final String _locale;
	private final String[] _markdownImporterArticleExtensions;
	private final String _markdownImporterArticleIntro;
	private final ZendeskCategory _zendeskCategory;
	private final ZipReader _zipReader;

}