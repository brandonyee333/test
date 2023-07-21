/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.mediawiki.internal.matchers;

import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.CallbackMatcher;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.wiki.model.WikiPage;

import java.util.regex.MatchResult;

/**
 * @author Kenneth Chang
 */
public class DirectTagMatcher extends CallbackMatcher {

	public DirectTagMatcher(WikiPage page) {
		_page = page;

		setRegex(_REGEX);
	}

	public String replaceMatches(CharSequence charSequence) {
		return replaceMatches(charSequence, _callback);
	}

	private static final String _REGEX = "\\[\\[([^\\]]+)\\]\\]";

	private final Callback _callback = new Callback() {

		@Override
		public String foundMatch(MatchResult matchResult) {
			String fileName = matchResult.group(1);

			if (!fileName.contains(StringPool.UNDERLINE)) {
				return null;
			}

			if (fileName.indexOf(CharPool.PIPE) >= 0) {
				fileName = StringUtil.extractFirst(fileName, CharPool.PIPE);
			}

			try {
				for (FileEntry fileEntry : _page.getAttachmentsFileEntries()) {
					if (!fileName.equals(fileEntry.getTitle())) {
						continue;
					}

					fileName = StringUtil.replace(
						fileName, CharPool.UNDERLINE, "%5F");

					return StringUtil.replace(
						matchResult.group(0), matchResult.group(1), fileName);
				}
			}
			catch (Exception e) {
			}

			return null;
		}

	};

	private final WikiPage _page;

}