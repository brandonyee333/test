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

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskLocales;
import com.liferay.osb.customer.zendesk.documentation.sync.exception.DocumentationImportException;
import com.liferay.osb.customer.zendesk.documentation.sync.importer.DocumentationImporter;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection;
import com.liferay.osb.customer.zendesk.documentation.sync.service.ZendeskArticleLocalServiceUtil;
import com.liferay.osb.customer.zendesk.documentation.sync.service.ZendeskSectionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.zip.ZipReader;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Amos Fong
 */
public class DocumentationArchiveImporter implements DocumentationImporter {

	public DocumentationArchiveImporter(
			ZipReader zipReader, ZendeskCategory zendeskCategory,
			String[] markdownImporterArticleExtensions,
			String markdownImporterArticleIntro)
		throws PortalException {

		_zipReader = zipReader;
		_zendeskCategory = zendeskCategory;
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

		deleteRemovedArticles();

		deleteRemovedSections();
	}

	protected void deleteRemovedArticles() throws Exception {
		List<ZendeskArticle> zendeskArticles =
			ZendeskArticleLocalServiceUtil.getZendeskCategoryArticles(
				_zendeskCategory.getZendeskCategoryId());

		for (ZendeskArticle zendeskArticle : zendeskArticles) {
			if (!_zendeskArticles.contains(zendeskArticle)) {
				ZendeskArticleLocalServiceUtil.deleteZendeskArticle(
					zendeskArticle);
			}
		}
	}

	protected void deleteRemovedSections() throws Exception {
		List<ZendeskSection> zendeskSections =
			ZendeskSectionLocalServiceUtil.getZendeskSections(
				_zendeskCategory.getZendeskCategoryId());

		for (ZendeskSection zendeskSection : zendeskSections) {
			if (!_zendeskSections.contains(zendeskSection)) {
				ZendeskSectionLocalServiceUtil.deleteZendeskSection(
					zendeskSection);
			}
		}
	}

	protected String getBottomNavigationHtml(
		String locale, ZendeskArticle previousZendeskArticle,
		ZendeskArticle nextZendeskArticle) {

		StringBundler sb = new StringBundler(12);

		sb.append("<div class=\"article-siblings\">");

		if (previousZendeskArticle != null) {
			sb.append("<span class=\"previous\"><a href=\"");
			sb.append(previousZendeskArticle.getRemoteHtmlURL(locale));
			sb.append("\">&#171; ");
			sb.append(previousZendeskArticle.getRemoteTitle(locale));
			sb.append("</a></span>");
		}

		if (nextZendeskArticle != null) {
			sb.append("<span class=\"next\"><a href=\"");
			sb.append(nextZendeskArticle.getRemoteHtmlURL(locale));
			sb.append("\">");
			sb.append(nextZendeskArticle.getRemoteTitle(locale));
			sb.append(" &#187;</a></span>");
		}

		sb.append("</div>");

		return sb.toString();
	}

	protected boolean isZendeskSection(String entry) {
		Path path = Paths.get(entry);

		if (path.getNameCount() == 2) {
			return true;
		}

		if (path.getNameCount() != 3) {
			return false;
		}

		Path pathFileName = path.getFileName();

		String fileName = pathFileName.toString();

		if (fileName.endsWith(_markdownImporterArticleIntro)) {
			return true;
		}

		return false;
	}

	protected void processEntry(String entry) throws Exception {
		if (!ArrayUtil.exists(
				_markdownImporterArticleExtensions, entry::endsWith)) {

			return;
		}

		String markdown = _zipReader.getEntryAsString(entry);

		ArticleMarkdownConverter articleMarkdownConverter =
			new ArticleMarkdownConverter(_zipReader, entry, markdown);

		String title = articleMarkdownConverter.getTitle();

		if (isZendeskSection(entry)) {
			processZendeskSection(articleMarkdownConverter.getId(), title);

			if (!title.startsWith("Intro") &&
				(ZendeskArticleLocalServiceUtil.getZendeskArticleCount(
					_currentZendeskSection.getZendeskSectionId()) != 1)) {

				title = "Introduction to " + title;
			}
		}

		processZendeskArticle(
			articleMarkdownConverter.getId(), title,
			articleMarkdownConverter.getUrlTitle(),
			articleMarkdownConverter.getHtml(),
			articleMarkdownConverter.getAttachments());
	}

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

		ZendeskArticle previousZendeskArticle = null;
		ZendeskArticle nextZendeskArticle = null;

		if (_iterationCount > 0) {
			int index = _zendeskArticles.indexOf(zendeskArticle);

			if (index >= 1) {
				previousZendeskArticle = _zendeskArticles.get(index - 1);
			}

			if (index < (_zendeskArticles.size() - 1)) {
				nextZendeskArticle = _zendeskArticles.get(index + 1);
			}
		}

		Map<String, String> titleMap = new HashMap<>();
		Map<String, String> bodyMap = new HashMap<>();

		for (String locale : ZendeskLocales.LOCALES_ENABLED) {
			titleMap.put(locale, title);

			String curBody =
				body +
					getBottomNavigationHtml(
						locale, previousZendeskArticle, nextZendeskArticle);

			curBody = replaceDocumentationOriginalURLs(locale, curBody);

			bodyMap.put(locale, curBody);
		}

		String documentationOriginalURL =
			_zendeskCategory.getDocumentationOriginalURL();

		if (documentationOriginalURL.endsWith(StringPool.SLASH)) {
			documentationOriginalURL = documentationOriginalURL.substring(
				0, documentationOriginalURL.length() - 1);
		}

		documentationOriginalURL += urlTitle;

		if (zendeskArticle == null) {
			zendeskArticle = ZendeskArticleLocalServiceUtil.addZendeskArticle(
				_currentZendeskSection.getZendeskSectionId(), documentationKey,
				documentationOriginalURL, titleMap, bodyMap,
				_zendeskArticlePosition, _zendeskCategory.getRemoteLabelNames(),
				attachments);
		}
		else {
			zendeskArticle =
				ZendeskArticleLocalServiceUtil.updateZendeskArticle(
					zendeskArticle.getZendeskArticleId(),
					_currentZendeskSection.getZendeskSectionId(),
					documentationKey, documentationOriginalURL, titleMap,
					bodyMap, _zendeskArticlePosition,
					_zendeskCategory.getRemoteLabelNames(), attachments);
		}

		if (_iterationCount == 0) {
			zendeskArticle.setRemoteTitle(ZendeskLocales.US, title);

			_zendeskArticles.add(zendeskArticle);
		}

		_zendeskArticlePosition++;
	}

	protected void processZendeskSection(String documentationKey, String name)
		throws Exception {

		ZendeskSection zendeskSection =
			ZendeskSectionLocalServiceUtil.fetchZendeskSection(
				_zendeskCategory.getZendeskCategoryId(), documentationKey);

		if (_iterationCount == 0) {
			Map<String, String> nameMap = new HashMap<>();

			for (String locale : ZendeskLocales.LOCALES_ENABLED) {
				nameMap.put(locale, name);
			}

			if (zendeskSection == null) {
				zendeskSection =
					ZendeskSectionLocalServiceUtil.addZendeskSection(
						_zendeskCategory.getZendeskCategoryId(),
						documentationKey, nameMap, Collections.emptyMap(),
						_zendeskSectionPosition);
			}
			else {
				zendeskSection =
					ZendeskSectionLocalServiceUtil.updateZendeskSection(
						zendeskSection.getZendeskSectionId(),
						_zendeskCategory.getZendeskCategoryId(),
						documentationKey, nameMap, Collections.emptyMap(),
						_zendeskSectionPosition);
			}

			_zendeskSections.add(zendeskSection);
		}

		_currentZendeskSection = zendeskSection;

		_zendeskArticlePosition = 0;
		_zendeskSectionPosition++;
	}

	protected String replaceDocumentationOriginalURLs(
		String locale, String body) {

		Matcher matcher = _originalURLPattern.matcher(body);

		while (matcher.find()) {
			String originalURL = matcher.group(1);

			ZendeskArticle zendeskArticle =
				ZendeskArticleLocalServiceUtil.fetchZendeskArticle(originalURL);

			if (zendeskArticle != null) {
				String href = matcher.group();

				String zendeskArticleHref =
					"href=\"" + zendeskArticle.getRemoteHtmlURL(locale) +
						href.substring(href.length() - 1);

				body = StringUtil.replace(body, href, zendeskArticleHref);
			}
			else if (_iterationCount > 0) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to find linked article " + originalURL);
				}
			}
		}

		return body;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DocumentationArchiveImporter.class);

	private static final Pattern _originalURLPattern = Pattern.compile(
		"href=\"([^\"]+/-/knowledge_base/[^\"#]+)[\"#]");

	private ZendeskSection _currentZendeskSection;
	private int _iterationCount;
	private final String[] _markdownImporterArticleExtensions;
	private final String _markdownImporterArticleIntro;
	private int _zendeskArticlePosition;
	private List<ZendeskArticle> _zendeskArticles = new ArrayList<>();
	private final ZendeskCategory _zendeskCategory;
	private int _zendeskSectionPosition;
	private List<ZendeskSection> _zendeskSections = new ArrayList<>();
	private final ZipReader _zipReader;

}