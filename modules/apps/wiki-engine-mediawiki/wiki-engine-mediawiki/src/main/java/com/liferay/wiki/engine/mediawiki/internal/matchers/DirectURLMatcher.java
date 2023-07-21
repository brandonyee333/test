/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.mediawiki.internal.matchers;

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.CallbackMatcher;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.wiki.model.WikiPage;

import java.util.regex.MatchResult;

/**
 * @author Kenneth Chang
 */
public class DirectURLMatcher extends CallbackMatcher {

	public DirectURLMatcher(WikiPage page, String attachmentURLPrefix) {
		_page = page;
		_attachmentURLPrefix = attachmentURLPrefix;

		setRegex(_URL_REGEX);
	}

	public String replaceMatches(CharSequence charSequence) {
		return replaceMatches(charSequence, _callback);
	}

	private static final String _URL_REGEX =
		"<a href=\"[^\"]*?Special:Edit[^\"]*?topic=[^\"]*?\".*?title=\"" +
			"([^\"]*?)\".*?>(.*?)</a>";

	private final String _attachmentURLPrefix;

	private final Callback _callback = new Callback() {

		@Override
		public String foundMatch(MatchResult matchResult) {
			String fileName = StringUtil.replace(
				matchResult.group(1), "%5F", StringPool.UNDERLINE);
			String title = StringUtil.replace(
				matchResult.group(2), "%5F", StringPool.UNDERLINE);

			if (Validator.isNull(title)) {
				title = fileName;
			}

			String url = _attachmentURLPrefix + URLCodec.encodeURL(fileName);

			try {
				for (FileEntry fileEntry : _page.getAttachmentsFileEntries()) {
					if (!fileName.equals(fileEntry.getTitle())) {
						continue;
					}

					StringBundler sb = new StringBundler(5);

					sb.append("<a href=\"");
					sb.append(url);
					sb.append("\">");
					sb.append(title);
					sb.append("</a>");

					return sb.toString();
				}
			}
			catch (Exception e) {
			}

			return null;
		}

	};

	private final WikiPage _page;

}