/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.mediawiki.internal.matchers;

import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.CallbackMatcher;

import java.util.regex.MatchResult;

import javax.portlet.PortletURL;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class PortletURLMatcher extends CallbackMatcher {

	public PortletURLMatcher(PortletURL portletURL) {
		_portletURL = portletURL;

		LiferayPortletURL liferayPortletURL = (LiferayPortletURL)portletURL;

		liferayPortletURL.setParameter("title", _TITLE_PLACEHOLDER, false);
	}

	public String replaceMatches(CharSequence charSequence) {
		return replaceMatches(charSequence, _callback);
	}

	private static final String _TITLE_PLACEHOLDER = "__TITLE_PLACEHOLDER__";

	private final Callback _callback = new Callback() {

		@Override
		public String foundMatch(MatchResult matchResult) {
			String portletURLString = _portletURL.toString();

			String title = matchResult.group(1);

			title = title.replace(CharPool.UNDERLINE, CharPool.PLUS);

			String url = portletURLString.replace(_TITLE_PLACEHOLDER, title);

			return "<a href=\"" + url + "\"";
		}

	};

	private final PortletURL _portletURL;

}