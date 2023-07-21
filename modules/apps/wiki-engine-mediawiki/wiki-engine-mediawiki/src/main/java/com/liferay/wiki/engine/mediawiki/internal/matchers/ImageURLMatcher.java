/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.mediawiki.internal.matchers;

import com.liferay.portal.kernel.util.CallbackMatcher;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.URLCodec;

import java.util.regex.MatchResult;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class ImageURLMatcher extends CallbackMatcher {

	public ImageURLMatcher(String attachmentURLPrefix) {
		_attachmentURLPrefix = attachmentURLPrefix;

		setRegex(_REGEX);
	}

	public String replaceMatches(CharSequence charSequence) {
		return replaceMatches(charSequence, _callback);
	}

	private static final String _REGEX =
		"<a href=\"[^\"]*?Special:Upload[^\"]*?topic=Image:([^\"]*?)\".*?</a>";

	private final String _attachmentURLPrefix;

	private final Callback _callback = new Callback() {

		@Override
		public String foundMatch(MatchResult matchResult) {
			String title = StringUtil.replace(
				matchResult.group(1), "%5F", StringPool.UNDERLINE);

			String url = _attachmentURLPrefix + URLCodec.encodeURL(title);

			StringBundler sb = new StringBundler(5);

			sb.append("<img alt=\"");
			sb.append(title);
			sb.append("\" class=\"wikiimg\" src=\"");
			sb.append(url);
			sb.append("\" />");

			return sb.toString();
		}

	};

}