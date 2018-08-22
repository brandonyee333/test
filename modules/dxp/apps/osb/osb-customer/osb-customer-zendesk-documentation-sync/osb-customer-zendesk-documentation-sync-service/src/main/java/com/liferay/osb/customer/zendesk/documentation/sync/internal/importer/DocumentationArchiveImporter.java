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
import com.liferay.osb.customer.zendesk.documentation.sync.configuration.ZendeskDocumentationSyncConfiguration;
import com.liferay.osb.customer.zendesk.documentation.sync.exception.DocumentationImportException;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection;
import com.liferay.osb.customer.zendesk.documentation.sync.service.ZendeskArticleLocalServiceUtil;
import com.liferay.osb.customer.zendesk.documentation.sync.service.ZendeskSectionLocalServiceUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.zip.ZipReader;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class DocumentationArchiveImporter {

	public DocumentationArchiveImporter(
			ZipReader zipReader, ZendeskCategory zendeskCategory)
		throws PortalException {

		_zipReader = zipReader;
		_zendeskCategory = zendeskCategory;

		_zendeskDocumentationSyncConfiguration =
			ConfigurableUtil.createConfigurable(
				ZendeskDocumentationSyncConfiguration.class,
				Collections.emptyMap());
	}

	public void importArchive() throws Exception {
		List<String> entries = _zipReader.getEntries();

		if (entries == null) {
			throw new DocumentationImportException(
				"The uploaded file is not a ZIP archive or it is corrupted");
		}

		Collections.sort(entries);

		for (String entry : entries) {
			String[] articleExtensions =
				_zendeskDocumentationSyncConfiguration.
					markdownImporterArticleExtensions();

			if (!ArrayUtil.exists(articleExtensions, entry::endsWith)) {
				continue;
			}

			String markdown = _zipReader.getEntryAsString(entry);

			ArticleMarkdownConverter articleMarkdownConverter =
				new ArticleMarkdownConverter(_zipReader, entry, markdown);

			Path path = Paths.get(entry);

			if (isZendeskSection(path)) {
				String title = articleMarkdownConverter.getTitle();

				processZendeskSection(articleMarkdownConverter.getId(), title);

				if (!title.startsWith("Intro") &&
					(ZendeskArticleLocalServiceUtil.getZendeskArticleCount(
						_currentZendeskSection.getZendeskSectionId()) != 1)) {

					title = "Introduction to " + title;
				}

				processZendeskArticle(
					articleMarkdownConverter.getId(), title,
					articleMarkdownConverter.getHtml(),
					articleMarkdownConverter.getAttachments());
			}
			else {
				processZendeskArticle(
					articleMarkdownConverter.getId(),
					articleMarkdownConverter.getTitle(),
					articleMarkdownConverter.getHtml(),
					articleMarkdownConverter.getAttachments());
			}
		}

		deleteRemovedArticles();

		deleteRemovedSections();
	}

	protected void deleteRemovedArticles() throws Exception {
		List<ZendeskArticle> zendeskArticles =
			ZendeskArticleLocalServiceUtil.getZendeskCategoryArticles(
				_zendeskCategory.getZendeskCategoryId());

		for (ZendeskArticle zendeskArticle : zendeskArticles) {
			if (!_zendeskArticleKeys.contains(
					zendeskArticle.getDocumentationKey())) {

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
			if (!_zendeskSectionKeys.contains(
					zendeskSection.getDocumentationKey())) {

				ZendeskSectionLocalServiceUtil.deleteZendeskSection(
					zendeskSection);
			}
		}
	}

	protected boolean isZendeskSection(Path path) {
		if (path.getNameCount() != 3) {
			return false;
		}

		Path pathFileName = path.getFileName();

		String fileName = pathFileName.toString();

		if (fileName.endsWith(
				_zendeskDocumentationSyncConfiguration.
					markdownImporterArticleIntro())) {

			return true;
		}

		return false;
	}

	protected void processZendeskArticle(
			String documentationKey, String title, String body,
			Map<String, byte[]> attachments)
		throws Exception {

		if (_currentZendeskSection == null) {
			throw new DocumentationImportException(
				"Article " + documentationKey + " is not in a section.");
		}

		Map<String, String> titleMap = new HashMap<>();
		Map<String, String> bodyMap = new HashMap<>();

		for (String locale : ZendeskLocales.LOCALES_ENABLED) {
			titleMap.put(locale, title);
			bodyMap.put(locale, body);
		}

		ZendeskArticle zendeskArticle =
			ZendeskArticleLocalServiceUtil.fetchZendeskArticle(
				_zendeskCategory.getZendeskCategoryId(), documentationKey);

		if (zendeskArticle == null) {
			zendeskArticle = ZendeskArticleLocalServiceUtil.addZendeskArticle(
				_currentZendeskSection.getZendeskSectionId(), documentationKey,
				titleMap, bodyMap, _zendeskArticlePosition,
				_zendeskCategory.getRemoteLabelNames(), attachments);
		}
		else {
			ZendeskArticleLocalServiceUtil.updateZendeskArticle(
				zendeskArticle.getZendeskArticleId(),
				_currentZendeskSection.getZendeskSectionId(), documentationKey,
				titleMap, bodyMap, _zendeskArticlePosition,
				_zendeskCategory.getRemoteLabelNames(), attachments);
		}

		_zendeskArticleKeys.add(documentationKey);

		_zendeskArticlePosition++;
	}

	protected void processZendeskSection(String documentationKey, String name)
		throws Exception {

		Map<String, String> nameMap = new HashMap<>();

		for (String locale : ZendeskLocales.LOCALES_ENABLED) {
			nameMap.put(locale, name);
		}

		ZendeskSection zendeskSection =
			ZendeskSectionLocalServiceUtil.fetchZendeskSection(
				_zendeskCategory.getZendeskCategoryId(), documentationKey);

		if (zendeskSection == null) {
			zendeskSection = ZendeskSectionLocalServiceUtil.addZendeskSection(
				_zendeskCategory.getZendeskCategoryId(), documentationKey,
				nameMap, Collections.emptyMap(), _zendeskSectionPosition);
		}
		else {
			zendeskSection =
				ZendeskSectionLocalServiceUtil.updateZendeskSection(
					zendeskSection.getZendeskSectionId(),
					_zendeskCategory.getZendeskCategoryId(), documentationKey,
					nameMap, Collections.emptyMap(), _zendeskSectionPosition);
		}

		_currentZendeskSection = zendeskSection;

		_zendeskSectionKeys.add(documentationKey);

		_zendeskArticlePosition = 0;
		_zendeskSectionPosition++;
	}

	private ZendeskSection _currentZendeskSection;
	private List<String> _zendeskArticleKeys = new ArrayList<>();
	private int _zendeskArticlePosition;
	private final ZendeskCategory _zendeskCategory;
	private volatile ZendeskDocumentationSyncConfiguration
		_zendeskDocumentationSyncConfiguration;
	private List<String> _zendeskSectionKeys = new ArrayList<>();
	private int _zendeskSectionPosition;
	private final ZipReader _zipReader;

}