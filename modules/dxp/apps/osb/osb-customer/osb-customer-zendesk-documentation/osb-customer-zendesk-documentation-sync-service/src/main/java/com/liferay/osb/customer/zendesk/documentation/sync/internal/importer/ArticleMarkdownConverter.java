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

import com.liferay.knowledge.base.markdown.converter.MarkdownConverter;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.osb.customer.zendesk.documentation.sync.exception.DocumentationImportException;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.zip.ZipReader;

import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Amos Fong
 */
public class ArticleMarkdownConverter {

	public ArticleMarkdownConverter(
			ZipReader zipReader, String zipReaderEntry, String markdown)
		throws PortalException {

		MarkdownConverter markdownConverter =
			ArticleMarkdownConverterFactoryUtil.create();

		try {
			markdownConverter.parse(markdown);

			_html = markdownConverter.convert(markdown);
		}
		catch (IOException ioe) {
			throw new DocumentationImportException(
				"Unable to convert Markdown to HTML: " +
					ioe.getLocalizedMessage(),
				ioe);
		}

		String heading = getHeading(markdown);

		if (Validator.isNull(heading)) {
			throw new DocumentationImportException(
				"Unable to extract title heading from file: " + zipReaderEntry);
		}

		_id = markdownConverter.getURLTitle();

		_urlTitle = formatURLTitle(_id);

		if (Validator.isNull(_urlTitle)) {
			throw new DocumentationImportException(
				"Missing title heading ID in file: " + zipReaderEntry);
		}

		_title = HtmlUtil.unescape(heading);

		_sourceURL = buildSourceURL(zipReader, zipReaderEntry);

		processAttachments(zipReader, zipReaderEntry, _html);
	}

	public Map<String, byte[]> getAttachments() {
		return _attachments;
	}

	public String getHtml() {
		return _html;
	}

	public String getId() {
		return _id;
	}

	public String getSourceURL() {
		return _sourceURL;
	}

	public String getTitle() {
		return _title;
	}

	public String getUrlTitle() {
		return _urlTitle;
	}

	protected String buildSourceURL(ZipReader zipReader, String fileEntryName)
		throws PortalException {

		Map<String, String> metadata = _getMetadata(zipReader);

		String baseSourceURL = metadata.get(_METADATA_BASE_SOURCE_URL);

		if (!Validator.isUrl(baseSourceURL)) {
			return null;
		}

		int pos = baseSourceURL.length() - 1;

		while (pos >= 0) {
			char c = baseSourceURL.charAt(pos);

			if (c != CharPool.SLASH) {
				break;
			}

			pos--;
		}

		StringBundler sb = new StringBundler(3);

		sb.append(baseSourceURL.substring(0, pos + 1));

		if (!fileEntryName.startsWith(StringPool.SLASH)) {
			sb.append(StringPool.SLASH);
		}

		sb.append(FileUtil.replaceSeparator(fileEntryName));

		return sb.toString();
	}

	protected String formatURLTitle(String urlTitle) {
		if (urlTitle == null) {
			return null;
		}

		if (!urlTitle.startsWith(StringPool.SLASH)) {
			urlTitle = StringPool.SLASH + urlTitle;
		}

		int urlTitleMaxLength = ModelHintsUtil.getMaxLength(
			KBArticle.class.getName(), "urlTitle");

		while (urlTitle.length() > urlTitleMaxLength) {
			int pos = urlTitle.lastIndexOf(StringPool.DASH);

			if (pos == -1) {
				urlTitle = urlTitle.substring(0, urlTitleMaxLength);
			}
			else {
				urlTitle = urlTitle.substring(0, pos);
			}
		}

		return urlTitle;
	}

	protected String getHeading(String html) {
		int x = html.indexOf("#");

		int y = html.indexOf(StringPool.NEW_LINE, x);

		String heading = html.substring(x + 1, y);

		return heading.trim();
	}

	protected void processAttachments(
		ZipReader zipReader, String zipReaderEntry, String html) {

		Path path = Paths.get(zipReaderEntry);

		Path parentPath = path.getParent();

		int x = 0;

		while ((x = html.indexOf("<img", x)) > -1) {
			int y = html.indexOf(">", x);

			if (y < 0) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Expected close tag for image " + html.substring(x));
				}

				break;
			}

			String imageTag = html.substring(x, y);

			String[] lines = StringUtil.split(imageTag, StringPool.QUOTE);

			String imageSrc = null;

			for (int i = 0; i < lines.length; i++) {
				if (lines[i].endsWith("src=")) {
					if ((i + 1) < lines.length) {
						imageSrc = lines[i + 1];
					}

					break;
				}
			}

			if (Validator.isNull(imageSrc)) {
				if (_log.isWarnEnabled()) {
					_log.warn("Missing src attribute for image " + imageTag);
				}
			}
			else {
				String imagePath =
					parentPath.toString() + StringPool.SLASH + imageSrc;

				byte[] bytes = zipReader.getEntryAsByteArray(imagePath);

				if (bytes == null) {
					_log.error("Unable to read image " + imagePath);
				}
				else {
					_attachments.put(imageSrc, bytes);
				}
			}

			x += 4;
		}
	}

	private Map<String, String> _getMetadata(ZipReader zipReader)
		throws PortalException {

		InputStream inputStream = null;

		try {
			inputStream = zipReader.getEntryAsInputStream(".METADATA");

			if (inputStream == null) {
				return Collections.emptyMap();
			}

			Properties properties = new Properties();

			properties.load(inputStream);

			Map<String, String> metadata = new HashMap<>(properties.size());

			for (Map.Entry<Object, Object> entry : properties.entrySet()) {
				Object key = entry.getKey();
				Object value = entry.getValue();

				if (value != null) {
					metadata.put(key.toString(), value.toString());
				}
			}

			return metadata;
		}
		catch (IOException ioe) {
			throw new DocumentationImportException(ioe);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	private static final String _METADATA_BASE_SOURCE_URL = "base.source.url";

	private static final Log _log = LogFactoryUtil.getLog(
		ArticleMarkdownConverter.class);

	private final Map<String, byte[]> _attachments = new HashMap<>();
	private final String _html;
	private final String _id;
	private final String _sourceURL;
	private final String _title;
	private final String _urlTitle;

}