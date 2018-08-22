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
import com.liferay.knowledge.base.markdown.converter.factory.MarkdownConverterFactoryUtil;
import com.liferay.osb.customer.zendesk.documentation.sync.exception.DocumentationImportException;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
			MarkdownConverterFactoryUtil.create();

		String html = null;

		try {
			html = markdownConverter.convert(markdown);
		}
		catch (IOException ioe) {
			throw new DocumentationImportException(
				"Unable to convert Markdown to HTML: " +
					ioe.getLocalizedMessage(),
				ioe);
		}

		String heading = getHeading(html);

		if (Validator.isNull(heading)) {
			throw new DocumentationImportException(
				"Unable to extract title heading from file: " + zipReaderEntry);
		}

		_id = getId(heading);

		if (Validator.isNull(_id)) {
			throw new DocumentationImportException(
				"Missing title heading ID in file: " + zipReaderEntry);
		}

		String title = HtmlUtil.unescape(heading);

		int x = title.indexOf("[](id=");

		if (x != -1) {
			title = title.substring(0, x);
		}

		_title = title;

		html = stripIds(html);

		_html = stripHeading(html);

		_sourceURL = buildSourceURL(zipReader, zipReaderEntry);

		processAttachments(zipReader, zipReaderEntry, html);
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

	protected String getHeading(String html) {
		int x = html.indexOf("<h1>");
		int y = html.indexOf("</h1>");

		if ((x == -1) || (y == -1) || (x > y)) {
			return null;
		}

		return html.substring(x + 4, y);
	}

	protected String getId(String heading) {
		String id = null;

		int x = heading.indexOf("[](id=");

		if (x == -1) {
			return null;
		}

		int y = heading.indexOf(StringPool.CLOSE_PARENTHESIS, x);

		if (y > (x + 1)) {
			int equalsSign = heading.indexOf(StringPool.EQUAL, x);

			id = heading.substring(equalsSign + 1, y);

			id = StringUtil.replace(id, CharPool.SPACE, CharPool.DASH);

			id = StringUtil.toLowerCase(id);
		}

		return id;
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

	protected String stripHeading(String html) {
		int index = html.indexOf("</h1>");

		if (index == -1) {
			return html;
		}

		return html.substring(index + 5);
	}

	protected String stripIds(String content) {
		int index = content.indexOf("[](id=");

		if (index == -1) {
			return content;
		}

		StringBundler sb = new StringBundler();

		do {
			int x = content.indexOf(StringPool.EQUAL, index);

			int y = content.indexOf(StringPool.CLOSE_PARENTHESIS, x);

			if (y != -1) {
				int z = content.indexOf("</h", y);

				if (z != (y + 1)) {
					sb.append(content.substring(0, y + 1));
				}
				else {
					sb.append(
						StringUtil.trimTrailing(content.substring(0, index)));
				}

				content = content.substring(y + 1);
			}
			else {
				if (_log.isWarnEnabled()) {
					String msg = content.substring(index);

					// Get the invalid id text from the content

					int spaceIndex = content.indexOf(StringPool.SPACE);

					if (spaceIndex != -1) {
						msg = content.substring(index, spaceIndex);
					}

					_log.warn(
						"Missing ')' for web content containing header id " +
							msg);
				}

				// Since no close parenthesis remains in the content, stop
				// stripping out IDs and simply include all of the remaining
				// content

				break;
			}
		}
		while ((index = content.indexOf("[](id=")) != -1);

		sb.append(content);

		return sb.toString();
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

}