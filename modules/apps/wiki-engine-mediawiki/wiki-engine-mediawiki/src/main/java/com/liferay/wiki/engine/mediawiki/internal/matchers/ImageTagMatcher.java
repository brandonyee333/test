/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.mediawiki.internal.matchers;

import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.util.CallbackMatcher;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.regex.MatchResult;

/**
 * @author Shinn Lok
 */
public class ImageTagMatcher extends CallbackMatcher {

	public ImageTagMatcher() {
		setRegex(_REGEX);
	}

	public String replaceMatches(CharSequence charSequence) {
		return replaceMatches(charSequence, _callback);
	}

	private static final String _REGEX = "\\[\\[Image:[^\\]]+\\]\\]";

	private final Callback _callback = new Callback() {

		@Override
		public String foundMatch(MatchResult matchResult) {
			String title = matchResult.group(0);

			return StringUtil.replace(title, CharPool.UNDERLINE, "%5F");
		}

	};

}